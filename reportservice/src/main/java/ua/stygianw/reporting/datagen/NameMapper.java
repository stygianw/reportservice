package ua.stygianw.reporting.datagen;

import java.util.function.Function;

import ua.stygianw.reporting.beans.Goal;

/**
 * @author StygianW
 * This class is created to ease the usage of stream collectors
 */
public class NameMapper implements Function<Goal,String> {
	
	@Override
	public String apply(Goal t) {
		return String.format("%s %s", t.getUser().getSurname(), t.getUser().getName());
	}
}
