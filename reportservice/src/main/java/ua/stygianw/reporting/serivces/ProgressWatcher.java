package ua.stygianw.reporting.serivces;

import javax.servlet.http.HttpSession;
import ua.stygianw.reporting.datagen.Report;


public class ProgressWatcher implements Runnable {
	
	private Report report;
	private int listLimit;
	private int fakeAmount = 0;
	private HttpSession sess;
	
	public ProgressWatcher(Report report, HttpSession sess) {
		this.report = report;
		this.sess = sess;
		listLimit = report.getFilteredGoalsList().size();
	}

	
	public float getPercentage() {
		if (listLimit == 0) {
			return 1;
		}
		return (float)fakeAmount / ((float)listLimit + (float)report.getResultFooter().keySet().size());
	}
	
	
	public void checkFakeLimit() throws InterruptedException {
				
		while(calculateFakeDifference() > 0) {
			Thread.sleep(1000);
			fakeAmount++;
		}
		Thread.sleep(3000);
		sess.setAttribute("watcher", null);
		
	}
	
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
