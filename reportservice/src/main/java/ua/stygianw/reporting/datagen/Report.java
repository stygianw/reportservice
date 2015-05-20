package ua.stygianw.reporting.datagen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.report.filters.BasicFilter;



/**
 * @author StygianW
 *Abstract layer for report generation strategies
 */
public abstract class Report {
	
	/**
	 *The report would consist of list of names and result footer, which would reflect 
	 *the calculation result of a selected strategy
	 */
	
	//BasicFilter would initially filter the full list of goals fed into the generator
	//through constructor. The filter criteria are set by the user on the client side
	private BasicFilter filter;
	
	//List of filtered goals
	protected List<Goal> filteredGoalsList;
	
	
	
	//ResultFooter depicts the calculation result for each involved person
	private Map<String,Long> resultFooter;
	
	//Description footer is a string representing selected strategy
	private String descriptionFooter;
	
	//Report body represents a list of goals for each involved person
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
	
	
	//The raw goals list is transformed here into a map, where goals are assigned to each involved person
	public Map<String,List<Goal>> generateBody() {
		
		return filteredGoalsList.stream()
				.collect(Collectors.groupingBy(new NameMapper(), Collectors.toList()));
		
	}
	
	public abstract Map<String,Long> generateResultFooter();
	
	public abstract String setDescriptionFooter();
	
}
