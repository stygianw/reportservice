package ua.stygianw.reporting;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.stygianw.reporting.serivces.ProgressWatcher;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	
	@RequestMapping("page")
	public String showPage() {
		return "ajaxtest";
	}
	
	
	@RequestMapping("progress")
	@ResponseBody
	public String getProgress(HttpSession sess) {
		return String.format("%.0f", ((ProgressWatcher)sess.getAttribute("watcher")).getPercentage() * 100);
	}
	
	
	
}
