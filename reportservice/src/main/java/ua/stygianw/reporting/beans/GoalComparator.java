package ua.stygianw.reporting.beans;

import java.util.Comparator;

public class GoalComparator implements Comparator<Goal> {

	@Override
	public int compare(Goal o1, Goal o2) {
		if (o1.getStartdate().getTime() > o2.getStartdate().getTime()) {
			return -1;
		} else if (o1.getStartdate().getTime() < o2.getStartdate().getTime()) {
			return 1;
		} 
		return 0;
	}
	
}
