package com.lawencon.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.base.ConnHandler;
import com.lawencon.lms.dto.permissions.SavePermissionsResDto;
import com.lawencon.lms.dto.permissions.UpdatePermissionsResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.service.PermissionsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("permissions")
public class PermissionsController extends ConnHandler {
	
	@Autowired
	private PermissionsService permissionsService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Permissions.class)))})
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		List<Permissions> result = new ArrayList<>();
		result = permissionsService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Permissions.class)))
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam("id") String id)throws Exception {
		Permissions result = new Permissions();
		result = permissionsService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Roles.class)))
	@GetMapping("/code")
	public ResponseEntity<?> getByCode(@RequestParam("code") String code)throws Exception {
		Permissions result = new Permissions();
		result = permissionsService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SavePermissionsResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Permissions data)throws Exception {
		SavePermissionsResDto result = permissionsService.save(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdatePermissionsResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Permissions data) throws Exception {
		UpdatePermissionsResDto result = permissionsService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Permissions.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		Boolean result = permissionsService.removeById(id);
		if (result==false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
	}
}
