package com.lawencon.lms.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.login.LoginReqDto;
import com.lawencon.lms.dto.login.LoginResDto;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.security.jwt.JwtComponent;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private ObjectMapper objectMapper;
	private AuthenticationManager authenticationManager;
	private JwtComponent jwtComponent;
	private UsersDao usersDao;

	public AuthenticationFilter(
			ObjectMapper objectMapper,
			AuthenticationManager authenticationManager,
			JwtComponent jwtComponent, 
			UsersDao usersDao) {
		this.objectMapper = objectMapper;
		this.authenticationManager = authenticationManager;
		this.jwtComponent = jwtComponent;
		this.usersDao = usersDao;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LoginReqDto loginReqDto = new LoginReqDto();

		try {
			loginReqDto = objectMapper.readValue(request.getInputStream(), loginReqDto.getClass());

		} catch (Exception e) {

		}

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginReqDto.getUsersEmail(), loginReqDto.getUsersPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		Users users = new Users();
		try {
			users = usersDao.findByEmail(authResult.getName());
			String token = jwtComponent.generateToken(users.getId(), users.getUsersEmail());
			String roleCode = users.getRoles().getRolesCode();
			
			LoginResDto loginResDto = new LoginResDto();
			loginResDto.setToken(token);
			loginResDto.setUsersId(users.getId());
			loginResDto.setRolesCode(roleCode);
			response.setContentType("/application/json");
			response.getWriter().append(objectMapper.writeValueAsString(loginResDto));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> mapError = new HashMap<>();
		mapError.put("msg", "login failed, check email and password");
		response.getWriter().append(objectMapper.writeValueAsString(mapError));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
}
