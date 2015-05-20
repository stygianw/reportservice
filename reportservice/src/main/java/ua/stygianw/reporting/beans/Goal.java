package ua.stygianw.reporting.beans;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author StygianW
 * Bean representing user goals
 */
@Entity
@Table(name = "goals")
public class Goal {
	
	@Id
	@Column(name = "goalId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int goalId;
	
	@Column(name = "description")
	private String description;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate")
	private Date startdate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enddate")
	private Date enddate;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", insertable=true)
	private User user;
	
	public Goal() {
		
		
	}
	
	public Goal(String description, Date startdate,
			Date enddate) {
		
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int id) {
		this.goalId = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
			
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public long getTimeDifference() {
		LocalDateTime startLocal = new Timestamp(startdate.getTime()).toLocalDateTime();
		if (enddate == null) {
			return ChronoUnit.DAYS.between(startLocal, LocalDateTime.now());
		}
		LocalDateTime endLocal = new Timestamp(enddate.getTime()).toLocalDateTime();
		return ChronoUnit.DAYS.between(startLocal, endLocal);
		
	}
	
	
	
}
