package ua.stygianw.reporting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.stygianw.reporting.beans.User;
import ua.stygianw.reporting.repositories.UsersRepository;


@Controller
public class HomeController {
	
	@Autowired
	UsersRepository rep;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		
		List<User> users = rep.getAll(); 
		model.addAttribute("users", users);
		return "home";
		
	}
}
