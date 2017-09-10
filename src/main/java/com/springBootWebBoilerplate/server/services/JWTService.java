package com.springBootWebBoilerplate.server.services;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootWebBoilerplate.server.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTService.class);
	
	private static final String ISSUER="com.medicalTutor";
	private static byte[] secretKey;
	
	static{
		secretKey="qGt5mXTu9aCpiNBfE46chZRQuWTXrOy86wWMpjfXkBuh6C9c0VGCYREGttfu".getBytes();
	}
	
	@Autowired
	UserService userService;
	
	//Argument to be changed with User
	public String tokenFor(String userId){
		Date expiration = Date.from(LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.UTC));
		return Jwts.builder()
				.setSubject(userId)
				.setExpiration(expiration)
				.setIssuer(ISSUER)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public User verify(String token){
		LOGGER.info("Verifying token " + token);
		try{
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return userService.getUserByUnqiueId(claims.getBody().getSubject().toString());
		}catch(ExpiredJwtException  e){
			LOGGER.info("Expired JWT " + token);
			return null;
		}catch(MalformedJwtException e){
			LOGGER.error("Bad JWT " + token);
			return null;			
		}
	}
}
