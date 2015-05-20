package ua.stygianw.reporting.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author StygianW
 * Abstract generic repository represents the repo layer for CRUD operations.
 * @param <T>
 */
@Transactional
public abstract class GenericRepository<T> {
	
	@PersistenceContext
	EntityManager em;
	
	public GenericRepository() {
		
	}
	
	public GenericRepository(EntityManager em) {
		this.em = em;
	}
	
	public abstract List<T> getAll();
	
	public abstract void save(T entity);
	
	public abstract void update(T entity);
	
	public abstract T findById(int id);
	
	public abstract List<T> findByQuery(String query);
	
	public abstract void delete(T entity);

}
