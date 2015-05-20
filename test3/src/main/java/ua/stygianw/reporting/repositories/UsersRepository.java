package ua.stygianw.reporting.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;


import ua.stygianw.reporting.beans.User;


@Repository(value = "usersRepository")
public class UsersRepository extends GenericRepository<User>{

	public UsersRepository() {
		super();
		
	}
	
	public UsersRepository(EntityManager em) {
		super(em);
						
	}

	
	@Override
	public void save(User entity) {
		//em.getTransaction().begin();
		em.persist(entity);
		//em.getTransaction().commit();
		
	}

	@Override
	public void update(User entity) {
		
		em.merge(entity);
		
	}

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findByQuery(String query) {
		return em.createQuery(query, User.class).getResultList();
	}

	@Override
	public void delete(User entity) {
		User merged = em.merge(entity);
		em.remove(merged);
		
		
	}

	@Override
	public List<User> getAll() {
		return em.createQuery("SELECT user from User user", User.class).getResultList();
	}

}
