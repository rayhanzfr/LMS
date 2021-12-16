package com.lawencon.lms.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtComponent {
	
	private String secretKey = "EyMFLZpeluJh0Oy5DDYxjrCJlFe1ZHfaLztFUb0PKtnZDjomXcY4iOXcUiRlwMQ5";
	private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	
	public String generateToken(String usersId, String usersEmail) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", usersId);
		claims.put("username", usersEmail);
		String token = Jwts.builder()
		.signWith(key)
		.setClaims(claims)
		.setExpiration(new Date(new Date().getTime() + 3600000))
		.compact();
		
		return token;
	}
	
	public Claims parseClaims(String token) throws Exception {
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
