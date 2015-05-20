package ua.stygianw.reporting.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.stygianw.reporting.beans.User;
import ua.stygianw.reporting.repositories.UsersRepository;

@Component
public class UsersValidator implements Validator {

	@Autowired
	UsersRepository rep;

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0.getClass());
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		if (arg0 == null) {
			return;
		}

		User user = (User) arg0;
		System.out.println("val");
		if (rep.findByNameAndValue("login", user.getLogin()).size() > 0) {
			System.out.println("val");
			arg1.rejectValue("login", "logins.exists");
		}

	}

}
