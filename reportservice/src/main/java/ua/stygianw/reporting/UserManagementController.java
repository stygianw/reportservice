package ua.stygianw.reporting;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import ua.stygianw.reporting.repositories.UsersRepository;
import ua.stygianw.reporting.validators.UsersValidator;



/**
 * @author StygianW
 *Controller handling user operations
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes({"user"})
public class UserManagementController {

	private static final String START = "redirect:/";
	
	@Autowired
	UsersRepository rep;
	
	@Autowired
	UsersValidator validator;
	
	public UserManagementController(UsersRepository rep) {
		this.rep = rep;
	}
	
	public UserManagementController() {
		
	}

	@InitBinder
	public void setRequiredFields(WebDataBinder binder) {
		binder.setRequiredFields("login", "passwd", "name", "surname");
	}
	
	@RequestMapping(value = "add")
	public String addUser(Model m) {
		m.addAttribute("user", new User());
		return "addUser";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute(value = "user") User user, BindingResult result) {
			if (user.getUserId() == 0) {
				validator.validate(user, result);
			}
			
			if(result.hasErrors()) {
				return "addUser";
			}
		
			if (user.getUserId() != 0) {
				rep.update(user);
			} else {
				rep.save(user);
			}
			return START;
		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteUser(@ModelAttribute("user") User user, BindingResult result) {
		rep.delete(user);
		return START;
	}
	
		
	@RequestMapping(value = "details/{id}/{action}")
	public String showDetails(@PathVariable("id") String id, @PathVariable("action") String action, Model model, HttpSession sess) {
		User user = rep.findById(Integer.valueOf(id));
		model.addAttribute("user", user);
		if(action.equals("edit")) {
			
			return "addUser";
		}
		
		return "userDetails";
	}
	
	
}
