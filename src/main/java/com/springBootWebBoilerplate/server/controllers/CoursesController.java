package com.springBootWebBoilerplate.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springBootWebBoilerplate.server.dao.UserDAO;
import com.springBootWebBoilerplate.server.entities.User;

@Controller
public class CoursesController {
	
	private final UserDAO repository;
	
	@Autowired
	public CoursesController(final UserDAO repository){
		this.repository = repository;
	}

    @RequestMapping("/home")
    public ResponseEntity<String> greeting() {
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getEmail());
        return new ResponseEntity<>("hello, " + repository, HttpStatus.OK);
    }

}