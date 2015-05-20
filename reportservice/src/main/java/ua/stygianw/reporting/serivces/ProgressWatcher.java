package ua.stygianw.reporting.serivces;

import javax.servlet.http.HttpSession;
import ua.stygianw.reporting.datagen.Report;


/**
 * @author StygianW
 * The progress watcher supplies information to the client about the progress of report building
 */
public class ProgressWatcher implements Runnable {
	
	//The report under observation
	private Report report;
	
	//The final report size promise
	private int listLimit;
	
	//Variable representing fake amount of report entries
	private int fakeAmount = 0;
	
	//The session attribute would be nullified after watching ends
	private HttpSession sess;
	
	public ProgressWatcher(Report report, HttpSession sess) {
		this.report = report;
		this.sess = sess;
		listLimit = report.getFilteredGoalsList().size();
	}

	//Supplies the percentage of report readiness to the client
	public float getPercentage() {
		if (listLimit == 0) {
			return 1;
		}
		return (float)fakeAmount / ((float)listLimit + (float)report.getResultFooter().keySet().size());
	}
	
	
	//Performs the fake report building check
	//Imitates adding of one entry per second
	public void checkFakeLimit() throws InterruptedException {
				
		while(calculateFakeDifference() > 0) {
			Thread.sleep(1000);
			fakeAmount++;
		}
		Thread.sleep(3000);
		
		//The watcher deletes itself from user session after the report has been build and watching ends
		sess.setAttribute("watcher", null);
		
	}
	
	//Performs the true report building check
	//Compares the report size promise and the real size of report collection, returns the difference
	//Not used anywhere in the project, as such implementation is workable only with big amounts of data
	private int calculateTrueDifference() {
		int mustBe = listLimit + report.getResultFooter().keySet().size();
		int reallyIs = report.getReportBody().values().size() + report.getResultFooter().values().size();
				
		return mustBe - reallyIs;
		
	}
	
	private int calculateFakeDifference() {
		int mustBe = listLimit + report.getResultFooter().keySet().size();
		int reallyIs = fakeAmount;
		
		return mustBe - reallyIs;
	}

	@Override
	public void run() {
		try {
			checkFakeLimit();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	
}
