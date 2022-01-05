package com.lawencon.lms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.security.jwt.JwtComponent;
import com.lawencon.lms.service.UsersService;

@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{

	private UsersService usersService;
	private BCryptPasswordEncoder passEncoder;
	private UsersDao usersDao;
	private JwtComponent jwtComponent;
	private ObjectMapper objectMapper;
	
	@Autowired
	public AppSecurity(UsersService usersService,
			BCryptPasswordEncoder passEncoder,
			UsersDao usersDao,
			JwtComponent jwtComponent,
			ObjectMapper objectMapper) {
		this.usersService = usersService;
		this.passEncoder = passEncoder;
		this.usersDao = usersDao;
		this.jwtComponent = jwtComponent;
		this.objectMapper = objectMapper;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
//		.and().httpBasic();
		http.addFilter(new AuthenticationFilter(objectMapper, super.authenticationManager(), jwtComponent, usersDao));
		http.addFilter(new AuthorizationFilter(super.authenticationManager(), jwtComponent));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(passEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/users");
		web.ignoring().antMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**","/histories/**");

	}
}
