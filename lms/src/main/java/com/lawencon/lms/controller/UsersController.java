package com.lawencon.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.users.SaveUsersResDto;
import com.lawencon.lms.dto.users.UpdateUsersResDto;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping
	public ResponseEntity<?>findAll()throws Exception{
		List<Users> user = usersService.findAll();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?>findById(@RequestParam(value = "id", required = false) String id) throws Exception{
		Users user = usersService.findById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("{email}")
	public ResponseEntity<?>findByCode(@RequestParam(value = "email",required = false) String email) throws Exception{
		Users user = usersService.findByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>save(@RequestBody Users user) throws Exception{
		SaveUsersResDto save = usersService.save(user);
		return new ResponseEntity<>(save,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?>update(@RequestBody Users user) throws Exception{
		UpdateUsersResDto update = usersService.update(user);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = usersService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
