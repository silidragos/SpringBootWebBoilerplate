package com.springBootWebBoilerplate.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springBootWebBoilerplate.server.security.JwtAuthFilter;

@Configuration
public class MyConfiguration {
	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(jwtAuthFilter);
	    registration.setEnabled(false);
	    return registration;
	} 
	
}
