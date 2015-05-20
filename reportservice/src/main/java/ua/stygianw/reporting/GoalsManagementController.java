package ua.stygianw.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.beans.User;
import ua.stygianw.reporting.repositories.GoalsRepository;
import ua.stygianw.reporting.repositories.UsersRepository;
import ua.stygianw.reporting.validators.GoalValidator;


/**
 * @author StygianW
 *Controllers handling goals CRUD operations
 */
@Controller
@RequestMapping("/goals")
@SessionAttributes({ "user", "goal", "pageHeader" })
public class GoalsManagementController {

	/**
	 * Setting up repositories, validators and root redirect link 
	 */
	private static final String START = "redirect:/";

	@Autowired
	UsersRepository uRep;

	@Autowired
	GoalsRepository gRep;

	@Autowired
	GoalValidator validator;

	
	
	/**Adding required fields and setting custom date format for model validation allowing dates to be null
	 * @param binder
	 */
	@InitBinder
	public void setCustomDateBinder(WebDataBinder binder) {
		
		binder.setRequiredFields("description", "startdate");
			
		DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		fmt.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(fmt, true));

	}

	@RequestMapping("add/{id}")
	public String addGoal(@PathVariable("id") String userId, Model model) {

		User user = uRep.findById(Integer.valueOf(userId));
		String header = String.format("Add new goal for user %s %s",
				user.getName(), user.getSurname());
		model.addAttribute("pageHeader", header);
		model.addAttribute("user", user);
		model.addAttribute("goal", new Goal());
		return "addGoal";

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerGoal(@ModelAttribute("goal") Goal goal,
			BindingResult result, HttpSession sess) {

		validator.validate(goal, result);

		if (result.hasErrors()) {
			return "addGoal";
		}

		User user = (User) sess.getAttribute("user");

		goal.setUser(user);

		if (goal.getGoalId() != 0) {
			gRep.update(goal);
		} else {

			gRep.save(goal);
		}
		
		return START;
	}

	@RequestMapping("remove")
	public String removeGoal(@ModelAttribute("goal") Goal goal, BindingResult result,
			@ModelAttribute("user") User user, BindingResult result2) {
		goal.setUser(user);
		gRep.delete(goal);
		return START;
	}

	@RequestMapping("edit/{id}")
	public String editGoal(@PathVariable("id") String goalId, Model model) {

		Goal goal = gRep.findById(Integer.valueOf(goalId));
		String header = String.format("Edit goal #%d for user %s %s", goal
				.getGoalId(), goal.getUser().getName(), goal.getUser()
				.getSurname());
		model.addAttribute("pageHeader", header);
		model.addAttribute("goal", goal);
		model.addAttribute("user", goal.getUser());
		System.out.println(goal.getUser().getName());
		return "addGoal";
	}
}
