package ua.stygianw.reporting.datagen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.report.filters.BasicFilter;

public class NotFinishedCountReport extends Report {

	public NotFinishedCountReport(BasicFilter filter, List<Goal> goalsFromRep) {
		super(filter, goalsFromRep);
		
	}
	
	@Override
	public Map<String, Long> generateResultFooter() {
		
		return filteredGoalsList
				.stream()
				.filter((m) -> m.getEnddate() == null)
				.collect(
						Collectors.groupingBy(new NameMapper(),
								Collectors.counting()));

	}

	@Override
	public String setDescriptionFooter() {
		return "Unfinished goals number: ";
	}

}
