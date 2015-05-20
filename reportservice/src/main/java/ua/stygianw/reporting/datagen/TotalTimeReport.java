package ua.stygianw.reporting.datagen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.report.filters.BasicFilter;

public class TotalTimeReport extends Report {

		
	public TotalTimeReport(BasicFilter filter, List<Goal> goalsFromRep) {
		super(filter, goalsFromRep);
		
	}

	@Override
	public Map<String, Long> generateResultFooter() {

		return filteredGoalsList.stream().collect(
				Collectors.groupingBy(new NameMapper(),
						Collectors.summingLong(Goal::getTimeDifference)));
	}

	@Override
	public String setDescriptionFooter() {
		return "Total time spent for goals: ";
	}

}
