package com.springBootWebBoilerplate.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springBootWebBoilerplate.server.security.JwtAuthFilter;
import com.springBootWebBoilerplate.server.security.JwtAuthenticationEntryPoint;
import com.springBootWebBoilerplate.server.security.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEndPoint;
	
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		auth.authenticationProvider(jwtAuthenticationProvider);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        
        http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().cors()
		.and().authorizeRequests()
	    		.antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/user/register", "/user/login").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/home").hasAuthority("ROLE_USER")//ROLE_ADMIN
        		.anyRequest().authenticated()
                .and()
        		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        		.exceptionHandling()
        		.authenticationEntryPoint(jwtAuthEndPoint);
    }
}