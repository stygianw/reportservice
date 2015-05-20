package ua.stygianw.reporting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.stygianw.reporting.beans.User;
import ua.stygianw.reporting.datagen.AverageTimeReport;
import ua.stygianw.reporting.datagen.NotFinishedCountReport;
import ua.stygianw.reporting.datagen.Report;
import ua.stygianw.reporting.datagen.TotalTimeReport;
import ua.stygianw.reporting.enums.AvailableReports;
import ua.stygianw.reporting.report.filters.BasicFilter;
import ua.stygianw.reporting.repositories.GoalsRepository;
import ua.stygianw.reporting.repositories.UsersRepository;
import ua.stygianw.reporting.serivces.ProgressWatcher;
import ua.stygianw.reporting.serivces.io.FileGenerator;
import ua.stygianw.reporting.serivces.io.PdfFileGenerator;
import ua.stygianw.reporting.serivces.io.TxtFileGenerator;
import ua.stygianw.reporting.validators.BasicFilterValidator;


/**
 * @author StygianW
 * Controller handling filtering, generation and file-saving of reports
 */
@Controller
@RequestMapping("report")
@SessionAttributes({ "users", "report", "reports" })
public class ReportController {

	
	/**
	 *Setting up repositories, filters, file writing buffer, 
	 * available report generation and file creation strategies as well as shared thread pool
	 */
	@Autowired
	UsersRepository rep;
	@Autowired
	GoalsRepository goalsRep;
	@Autowired
	BasicFilterValidator validator;

	private static final int BUFFER = 4096;

	private static final Map<AvailableReports, Class<? extends Report>> availableReports = new HashMap<>();

	private static final Map<String, Class<? extends FileGenerator>> availableFileGenerators = new HashMap<>();

	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	static {
		availableReports.put(AvailableReports.AVERAGE_TIME_ALL,
				AverageTimeReport.class);
		availableReports.put(AvailableReports.TOTAL_TIME_ALL,
				TotalTimeReport.class);
		availableReports.put(AvailableReports.COUNT_UNFINISHED,
				NotFinishedCountReport.class);

		availableFileGenerators.put("txt", TxtFileGenerator.class);
		availableFileGenerators.put("pdf", PdfFileGenerator.class);
	}

	/**Setting custom date binding format
	 * @param binder
	 */
	@InitBinder
	public void setCustomDateBinder(WebDataBinder binder) {
		DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		fmt.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(fmt, true));

	}

	@RequestMapping("filter")
	public String showFilter(Model model) {
		List<User> users = rep.getAll();
		model.addAttribute("reports", availableReports);
		model.addAttribute("users", users);
		model.addAttribute("filter", new BasicFilter());
		return "filter";
	}

	
	/**
	 * 
	 * This method processes income data from the client UI for report generation,
	 * instantiates the report generation stratedy and starts asynchronously the report building progress watcher.
	 * The filter criteria are packed into BasicFilter data type.
	 */
	@RequestMapping(value = "process", method = RequestMethod.POST)
	public String getFilterResult(@ModelAttribute("filter") BasicFilter filter,
			BindingResult result, Model model, HttpSession sess,
			HttpServletRequest request) throws InterruptedException,
			ServletException {

		validator.validate(filter, result);
		if (result.hasErrors()) {
			return "filter";
		}
		
		//Searching for the strategy selected by the user on the client side
		AvailableReports key = availableReports
				.keySet()
				.stream()
				.filter(m -> m.getId().equals(
						request.getParameter("selectedReport"))).findFirst()
				.get();

		//Reflective instantiation of report generation strategy
		Class<? extends Report> reportToShow = availableReports.get(key);
		Report report = null;
		try {
			report = reportToShow.getDeclaredConstructor(BasicFilter.class,
					List.class).newInstance(filter, goalsRep.getAll());
		} catch (Exception e) {
			throw new ServletException(e);
		}

		//Creating the progress watcher and starting it asynchronously
		ProgressWatcher watcher = new ProgressWatcher(report, sess);
		Thread thr = new Thread(watcher);
		threadPool.execute(thr);
		sess.setAttribute("watcher", watcher);
		model.addAttribute("report", report);
		return "progress";

	}

	@RequestMapping("show")
	public String showReport() {

		return "filterResult";

	}

	/**
	 *This method establishes the file generation strategy, retrieves the written file 
	 *and passes it out to the client
	 */
	@RequestMapping("download/{type}")
	public String generateReport(@PathVariable("type") String type,
			HttpSession sess, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		//Defining absolute path of web container, setting the target folder
		String appPath = request.getServletContext().getRealPath("");
		String relativePath = "/WEB-INF/generated_files/";
		
		//Reflective instantiation of file generation strategy
		Class<? extends FileGenerator> generatorClass = availableFileGenerators.get(type);
		FileGenerator generator = null;
		try {
			generator = generatorClass
					.getDeclaredConstructor(Report.class, String.class)
					.newInstance((Report) sess.getAttribute("report"), appPath + relativePath);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		//Retrieving the generated file and opening streams, preparing HTTP response parameters
		Path generatedFile = generator.generateReport();
		InputStream in = Files.newInputStream(generatedFile,
				StandardOpenOption.READ, StandardOpenOption.DELETE_ON_CLOSE);
		OutputStream out = response.getOutputStream();
		response.setContentType(generator.getMimeType());
		String responseHeaderKey = "Content-Disposition";
		String responseHeaderValue = String.format(
				"attachment; filename=\"report.%s\"", generator.getExtension());
		response.setHeader(responseHeaderKey, responseHeaderValue);

		byte[] recordedBytes = new byte[BUFFER];
		int readResult;
		
		//Byte-wise passing of file to servlet output stream
		while ((readResult = in.read(recordedBytes)) != -1) {
			out.write(recordedBytes, 0, readResult);
		}

		in.close();
		out.close();

		return "filterResult";
	}

}
