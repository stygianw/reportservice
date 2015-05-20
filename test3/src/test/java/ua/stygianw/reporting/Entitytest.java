package ua.stygianw.reporting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.beans.User;

import com.mysql.jdbc.Driver;

public class Entitytest {

	EntityManagerFactory factory;
	EntityManager manager;
	@Before
	public void setUp() throws Exception {
				
		
	}

	@After
	public void tearDown() throws Exception {
		
		manager.close();
		
	}
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		com.mysql.jdbc.Driver drv = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		//Connection conn = DriverManager.getConnection("jdbc://localhost:3066/usergoals");
		Assert.assertNotNull(drv);
		factory = Persistence.createEntityManagerFactory("ua.stygianw.user");
		manager = factory.createEntityManager();
		Assert.assertNotNull(manager);
		User user = manager.createQuery("SELECT user FROM User user", User.class).setMaxResults(1).getSingleResult();
		Assert.assertNotNull(user);
		Assert.assertEquals("log1", user.getLogin());
	}
	
	@Test
	public void test2() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		factory = Persistence.createEntityManagerFactory("ua.stygianw.entitymanager");
		manager = factory.createEntityManager();
		User user1 = manager.createQuery("SELECT user FROM User user WHERE login = :log", User.class)
				.setParameter("log", "bobo").getSingleResult();
//		Assert.assertNotNull(user1);
//		User newUser = new User("bobo2", "blood", "jare", "inkinen");
	/*	manager.getTransaction().begin();
		manager.persist(newUser);
		manager.getTransaction().commit();*/
		Assert.assertNotNull(user1);
		Goal goal = new Goal("Buy milk", 
				LocalDateTime.now(), 
				LocalDateTime.of(LocalDate.of(2015, Month.JUNE, 23), LocalTime.of(15, 24)));
		
		/*User us = manager.merge(user1);
		user1.getGoals().add(goal);
		manager.getTransaction().begin();
		user1.getGoals().add(goal);*/
		manager.getTransaction().begin();
		goal.setUser(user1);
		manager.persist(goal);
		manager.getTransaction().commit();
		
	}

}
