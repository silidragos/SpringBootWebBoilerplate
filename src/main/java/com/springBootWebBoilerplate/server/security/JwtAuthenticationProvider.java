package com.springBootWebBoilerplate.server.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springBootWebBoilerplate.server.entities.User;
import com.springBootWebBoilerplate.server.services.JWTService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	private final JWTService jwtService;
	
    public JwtAuthenticationProvider() {
        this(null);
    }
	
    @Autowired
    public JwtAuthenticationProvider(JWTService jwtService) {
        this.jwtService = jwtService;
    }	
    
    @Override
    public Authentication authenticate(Authentication authentication){	
	    	User possibleProfile = jwtService.verify((String)authentication.getCredentials());
	    	if(possibleProfile == null){
	    		throw new BadCredentialsException("Token not valid");
	    	}
	    	System.out.println("Authenticate " + possibleProfile.getEmail());
	    	return new JwtAuthenticatedProfile(possibleProfile);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
