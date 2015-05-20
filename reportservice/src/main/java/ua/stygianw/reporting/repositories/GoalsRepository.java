package ua.stygianw.reporting.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ua.stygianw.reporting.beans.Goal;
import ua.stygianw.reporting.beans.User;


@Repository("goalsRepository")
public class GoalsRepository extends GenericRepository<Goal>{

	public GoalsRepository() {
		super();
		
	}
	
	public GoalsRepository(EntityManager em) {
		super(em);
		
	}

	public void save(Goal entity) {
		em.persist(entity);
				
	}

	@Override
	public void update(Goal entity) {
		em.merge(entity);
	}

	@Override
	public Goal findById(int id) {
		return em.find(Goal.class, id);
	}

	@Override
	public List<Goal> findByQuery(String query) {
		return em.createQuery(query, Goal.class).getResultList();
	}

	@Override
	public void delete(Goal entity) {
		User us = em.merge(entity.getUser());
		Goal merged = em.merge(entity);
		us.getGoals().remove(merged);
		em.remove(merged);
		
				
	}

	@Override
	public List<Goal> getAll() {
		
		return em.createQuery("SELECT goal FROM Goal goal", Goal.class).getResultList();
	}
	
	
	
	

}
