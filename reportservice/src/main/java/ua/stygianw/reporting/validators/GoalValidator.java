package ua.stygianw.reporting.validators;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.stygianw.reporting.beans.Goal;

@Component
public class GoalValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Goal.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
		if (arg0 == null) {
			return;
		}
		
		Goal goal = (Goal)arg0;
		
		if(goal.getEnddate() == null) {
			return;
		}
		
		if(goal.getStartdate().getTime() > System.currentTimeMillis()) {
				arg1.rejectValue("startdate", "time.startdate.future");
		}
		
		if(goal.getEnddate().getTime() > System.currentTimeMillis()) {
			arg1.rejectValue("enddate", "time.enddate.future");
		}
		
		if(goal.getEnddate().getTime() < goal.getStartdate().getTime()) {
			arg1.rejectValue("startdate", "time.backward");
		}
		
		
		
	}

}
