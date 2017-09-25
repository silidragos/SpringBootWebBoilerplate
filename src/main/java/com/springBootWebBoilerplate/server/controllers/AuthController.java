package com.springBootWebBoilerplate.server.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springBootWebBoilerplate.server.entities.User;
import com.springBootWebBoilerplate.server.entities.meta.RegisterPOSTRequest;
import com.springBootWebBoilerplate.server.services.JWTService;
import com.springBootWebBoilerplate.server.services.UserService;

@Controller
public class AuthController {
	final static Logger LOGGER = LogManager.getLogger(AuthController.class);
	@Autowired
	UserService userService;
	@Autowired
	JWTService jwtService;
	
	@RequestMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody RegisterPOSTRequest userInfo) {
		LOGGER.info("User " + userInfo.getEmail() + " tries to register");
		
        try {
			userService.registerUser(userInfo.getEmail(), userInfo.getPassword());
		} catch (SQLIntegrityConstraintViolationException e) {
	    	return new ResponseEntity<>("DUPLICATE_EMAIL", HttpStatus.BAD_REQUEST);
		}
    	
    	return new ResponseEntity<>("hello, " + userInfo.getEmail(), HttpStatus.OK);
    }
	
	@RequestMapping("/user/login")
	public ResponseEntity<String> register(@RequestBody String encodedCredentials){
		String[] credentials;
		try {
			credentials = new String(Base64.getDecoder().decode(encodedCredentials),"utf-8").split(":");
		} catch (UnsupportedEncodingException|IllegalArgumentException e1) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);	
		}
		String email = credentials[0];
		String password = credentials[1];
		
		LOGGER.info("User " + email + " tries to login");
		
		try{
			User user = userService.getUserByCredentials(email,password);
			if(user!=null){
				HttpHeaders headers = new HttpHeaders();
				headers.add("Token", jwtService.tokenFor(user.getUniqueId()));
				return new ResponseEntity<String>("", headers, HttpStatus.OK);			
			}else{
				return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);		
			}
		}catch(SQLIntegrityConstraintViolationException e){
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);			
		}
	}
}
