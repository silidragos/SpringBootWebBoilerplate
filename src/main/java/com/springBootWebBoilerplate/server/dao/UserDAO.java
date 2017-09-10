package com.springBootWebBoilerplate.server.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.springBootWebBoilerplate.server.entities.User;

@Repository
public class UserDAO extends GenericDAO<User>{

	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) throws NoResultException{
		return (User) this.em.createNamedQuery(User.FIND_BY_EMAIL)
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public User getUserByUnqiueId(String uuid){
		return (User) this.em.createNamedQuery(User.FIND_BY_UNIQUE_ID)
				.setParameter("uuid", uuid)
				.getSingleResult();
	}
}
