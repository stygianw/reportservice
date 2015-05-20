package ua.stygianw.reporting.serivces.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.datagen.Report;


public class TxtFileGenerator extends FileGenerator{
		
	private static final String MIME_TYPE = "text/plain";
	
	private static final String EXTENSION = "txt";
	
	public TxtFileGenerator(Report report, String directory) {
		super(report, directory);
		
	}

	@Override
	public String getMimeType() {
		return MIME_TYPE;
	}

	@Override
	public String getExtension() {
		return EXTENSION;
	}

	public Path generateReport() throws IOException {
		
		final String tab = "\t";
		
		String fullPath = directory + constructFilename("report", EXTENSION);
		
		Path path = Paths.get(fullPath);
		
		BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.SYNC);
		
		for (String name : report.getReportBody().keySet()) {
			writer.write("NAME:" + name);
			writer.newLine();
			writer.write("-----------------------");
			writer.newLine();
			for (Goal goal : report.getReportBody().get(name)) {
				writer.write(goal.getDescription() + tab);
				writer.write(getFormattedDate(goal.getStartdate()) + tab);
				if (goal.getEnddate() != null) {
					writer.write(getFormattedDate(goal.getEnddate()) + tab);
				} else { 
					writer.write("IN PROGRESS" + tab);
				}
				writer.write(String.valueOf(goal.getTimeDifference() + tab));
				writer.newLine();
				
			}
			writer.append("----------------------");
			writer.newLine();
			writer.write("RESULT: " + report.getDescriptionFooter() + tab + tab);
			writer.write(String.valueOf(report.getResultFooter().get(name)));
			writer.newLine();
			writer.newLine();
		}
		
		writer.close();
		return path;
	} 
	
	public String getFormattedDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().toString();
	}
}
