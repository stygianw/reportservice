package ua.stygianw.reporting.datagen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.report.filters.BasicFilter;



public abstract class Report {
	
		
	private BasicFilter filter;
	protected List<Goal> filteredGoalsList;
	
	private Map<String,Long> resultFooter;
	private String descriptionFooter;
	private Map<String,List<Goal>> reportBody;
	
	public Map<String, Long> getResultFooter() {
		return resultFooter;
	}

	public String getDescriptionFooter() {
		return descriptionFooter;
	}
	
	public Map<String, List<Goal>> getReportBody() {
		return reportBody;
	}
		
	public List<Goal> getFilteredGoalsList() {
		return filteredGoalsList;
	}

	public Report(BasicFilter filter, List<Goal> goalsFromRep) {
		
		this.filter = filter;
		filteredGoalsList = filter.filterGoals(goalsFromRep);
		reportBody = generateBody();
		resultFooter = generateResultFooter();
		descriptionFooter = setDescriptionFooter();
				
	}
	
	
	public Map<String,List<Goal>> generateBody() {
		
		return filteredGoalsList.stream()
				.collect(Collectors.groupingBy(new NameMapper(), Collectors.toList()));
		
	}
	
	public abstract Map<String,Long> generateResultFooter();
	
	public abstract String setDescriptionFooter();
	
}
