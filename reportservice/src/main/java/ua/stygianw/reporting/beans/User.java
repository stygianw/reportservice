package ua.stygianw.reporting.beans;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * @author StygianW
 * Bean representing users
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "passwd", length = 20)
	private String passwd;
	
	@Column(name = "name", length = 45)
	private String name;
	
	@Column(name = "surname", length = 45)
	private String surname;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="user")
	private List<Goal> goals;
	
	
	public User() {
		
	}
	
	public User(String login, String passwd, String name, String surname) {
		this.login = login;
		this.passwd = passwd;
		this.name = name;
		this.surname = surname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Goal> getGoals() {
		goals.sort(new GoalComparator());
		return goals;
	}
	

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
	
	
}
