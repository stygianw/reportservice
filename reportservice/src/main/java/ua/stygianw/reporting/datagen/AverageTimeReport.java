package ua.stygianw.reporting.datagen;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.report.filters.BasicFilter;


public class AverageTimeReport extends Report {
	
	
	
	public AverageTimeReport(BasicFilter filter, List<Goal> goalsFromRep) {
		super(filter, goalsFromRep);
		
	}

	@Override
	public Map<String, Long> generateResultFooter() {
		
		return filteredGoalsList.stream().collect(
				Collectors.groupingBy(new NameMapper(), Collectors
						.collectingAndThen(Collectors
								.averagingLong(Goal::getTimeDifference),
								result -> {
									return Double.valueOf(result).longValue();
								})));

	}

	@Override
	public String setDescriptionFooter() {
		return "Average time of goals handling: ";
	}

}
