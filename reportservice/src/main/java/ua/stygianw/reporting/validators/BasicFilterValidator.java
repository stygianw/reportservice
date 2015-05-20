package ua.stygianw.reporting.validators;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.stygianw.reporting.report.filters.BasicFilter;

@Component
public class BasicFilterValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return BasicFilter.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
		if (arg0 == null) {
			return;
		}
		
		BasicFilter filter = (BasicFilter)arg0;
								
		if(checkNullAndFuture(filter.getNotEarlierThan())) {
				arg1.rejectValue("notEarlierThan", "time.startdate.future");
		}
		
		if(checkNullAndFuture(filter.getNotLaterThan())) {
			arg1.rejectValue("notLaterThan", "time.enddate.future");
		}
		if(filter.getNotLaterThan() != null && filter.getNotEarlierThan() != null) {
			if(filter.getNotLaterThan().getTime() < filter.getNotEarlierThan().getTime()) {
			arg1.rejectValue("notLaterThan", "time.backward");
			}
		}
		
		if(filter.getNamesToFilter() == null) {
			arg1.rejectValue("namesToFilter", "names.empty");
		}
		
	}
	
	public boolean checkNullAndFuture(Date date) {
		return date != null && date.getTime() > System.currentTimeMillis();
	}

}
