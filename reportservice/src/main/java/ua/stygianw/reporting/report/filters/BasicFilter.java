package ua.stygianw.reporting.report.filters;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ua.stygianw.reporting.beans.Goal;


/**
 * @author StygianW
 * The basic filter performs a basic filtering of raw goals collection prior to report building.
 * Filtration is done by user names, and dates of goal start and end dates 
 */
public class BasicFilter {
	
	private List<Integer> namesToFilter;
	private Date notEarlierThan;
	private Date notLaterThan;
	
	public List<Integer> getNamesToFilter() {
		
		return namesToFilter;
	}
	public void setNamesToFilter(List<Integer> namesToFilter) {
		this.namesToFilter = namesToFilter;
	}
	public Date getNotEarlierThan() {
		return notEarlierThan;
	}
	public void setNotEarlierThan(Date notEarlierThan) {
		this.notEarlierThan = notEarlierThan;
	}
	public Date getNotLaterThan() {
		return notLaterThan;
	}
	public void setNotLaterThan(Date notLaterThan) {
		this.notLaterThan = notLaterThan;
	}
	
	public List<Goal> filterGoals(List<Goal> users) {
		return users.stream()
				.filter((m) -> getNamesToFilter().contains(m.getUser().getUserId()))
				.filter((m) -> getNotEarlierThan() == null ? true : m.getStartdate().getTime() >= getNotEarlierThan().getTime())
				.filter((m) -> getNotLaterThan() == null ? true : (m.getEnddate() != null ? m.getEnddate().getTime() <= getNotLaterThan().getTime() : false))
				.collect(Collectors.toList());
	}
	
}
