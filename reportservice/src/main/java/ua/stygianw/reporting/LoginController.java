package ua.stygianw.reporting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(
			@RequestParam(required = false, value = "error") String error,
			@RequestParam(required = false, value = "msg") String msg,
			Model model) {
		
		if (error != null) {
			model.addAttribute("error", "Invalid user data. Please check!");
		}
		
		if(msg != null) {
			model.addAttribute("msg", "You have been logged out successfully.");
		}
		
		return "login";
		
		
		
	}

}
