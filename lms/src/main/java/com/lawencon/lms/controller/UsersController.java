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
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.UsersService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("users")
public class UsersController extends BaseController {

	@Autowired
	private UsersService usersService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@GetMapping
	public ResponseEntity<?>findAll()throws Exception{
		List<Users> user = usersService.findAll();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@GetMapping("/id")
	public ResponseEntity<?>findById(@RequestParam(value = "id", required = false) String id) throws Exception{
		Users user = usersService.findById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@GetMapping("/email")
	public ResponseEntity<?>findByCode(@RequestParam(value = "email",required = false) String email) throws Exception{
		Users user = usersService.findByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@PostMapping
	public ResponseEntity<?>save(@RequestBody Users user) throws Exception{
		SaveUsersResDto save = usersService.save(user);
		return new ResponseEntity<>(save,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@PutMapping
	public ResponseEntity<?>update(@RequestBody Users user) throws Exception{
		UpdateUsersResDto update = usersService.update(user);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Users.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = usersService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
