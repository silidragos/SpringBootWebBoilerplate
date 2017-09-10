package com.springBootWebBoilerplate.server.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericDAO<T> {
	@PersistenceContext
	protected EntityManager em;
	

	@PostConstruct
	private void init(){
	}
	
	@Transactional
	public void save(T item) {
		em.persist(item);
	}
	@Transactional
	public void update(T item) {
		em.merge(item);
		em.flush();
	}
	
	@Transactional
	public void delete(T item) {
		em.remove(item);
	}
	
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
