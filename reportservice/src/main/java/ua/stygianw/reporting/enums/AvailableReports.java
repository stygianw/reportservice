package ua.stygianw.reporting.enums;

public enum AvailableReports {
	AVERAGE_TIME_ALL("1", "By average time of all tasks"),
	TOTAL_TIME_ALL("2", "By total time of all tasks"),
	COUNT_UNFINISHED("3", "Count unfinished tasks");
	
	private final String id;
	private final String description;
	
	AvailableReports(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	
	
}
