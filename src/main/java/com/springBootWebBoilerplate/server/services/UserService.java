package com.springBootWebBoilerplate.server.services;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springBootWebBoilerplate.server.dao.UserDAO;
import com.springBootWebBoilerplate.server.entities.User;
import com.springBootWebBoilerplate.server.entities.enums.UserStatusEnum;

@Service
public class UserService {
	final static Logger LOGGER = Logger.getLogger(UserService.class);
	@Autowired
	UserDAO userDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void registerUser(String email, String password) throws SQLIntegrityConstraintViolationException{
		User user;
		try{
			user = userDao.getUserByEmail(email);
			LOGGER.info("Email " + email + " already exists");
			throw new SQLIntegrityConstraintViolationException("Email already exists!");
		}catch(NoResultException e){
			//do nothing
		}catch(NonUniqueResultException e){
			LOGGER.error("Email " + email + " is duplicated in DB");
			throw new SQLIntegrityConstraintViolationException("Found duplicate emails!");			
		}
		
		user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setStatus(UserStatusEnum.SIMPLE);
		userDao.save(user);
	}
	
	public User getUserByCredentials(String email, String password) throws SQLIntegrityConstraintViolationException{
		
		try{
			User user = userDao.getUserByEmail(email);
			if(passwordEncoder.matches(password, user.getPassword())){
				return user;
			}else{
				LOGGER.info("User " + email + " used bad password");
				return null;
			}
		}
		catch(NoResultException e){
			return null;
		}catch(NonUniqueResultException e){
			LOGGER.error("Email " + email + " is duplicated in DB");
			throw new SQLIntegrityConstraintViolationException("Found duplicate emails!");			
		}
	}
	
	public User getUserByUnqiueId(String uuid){
		try{
			User user = userDao.getUserByUnqiueId(uuid);
			System.out.println("Got user " + user.getEmail());
			return user;
		}catch(NoResultException e){
			LOGGER.warn("User with unique id" + uuid + " does not exist!");
			return null;
		}catch(NonUniqueResultException e){
			LOGGER.error("Unique id " + uuid + " is duplicated in DB");
			return null;		
		} 
	}
}
