package ua.stygianw.reporting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class HibernateTest extends TestCase {

	EntityManagerFactory factory;
		

	@Test
	protected void runT() throws Throwable {
		// TODO Auto-generated method stub
		EntityManager mng = factory.createEntityManager();
	}

	@Override
	protected void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory("ua.stygianw.userbean");
	}
	
	

}
