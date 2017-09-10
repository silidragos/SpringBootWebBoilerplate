package com.springBootWebBoilerplate.server.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBootWebBoilerplate.server.entities.Test;

@Repository
public class MyTestDAO/* extends JpaRepository<Test, Long>*/{
	@PersistenceContext()
	private EntityManager em;
	
	public Test getTest(){
		return em.find(Test.class, 1L);
	}
}
