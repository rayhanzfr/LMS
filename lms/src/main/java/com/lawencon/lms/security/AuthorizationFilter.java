package com.lawencon.lms.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lawencon.lms.security.jwt.JwtComponent;

import io.jsonwebtoken.Claims;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private JwtComponent jwtComponent;

	public AuthorizationFilter(
			AuthenticationManager authenticationManager,
			JwtComponent jwtComponent) {
		super(authenticationManager);
		this.jwtComponent = jwtComponent;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		
//		String username = "";
		Long userId = 0L;
		try {
			String bodyToken = header.replace("Bearer ", "");
			Claims claims = jwtComponent.parseClaims(bodyToken);
//			username = (String) claims.get("username");
			userId =  Long.valueOf(claims.get("userId").toString()) ;
		} catch (Exception e) {
			
		}
		
//		Authentication auth = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
		Authentication auth = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}
}
