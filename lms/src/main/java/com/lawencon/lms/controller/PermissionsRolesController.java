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

import com.lawencon.lms.dto.permissionsroles.SavePermissionsRolesResDto;
import com.lawencon.lms.dto.permissionsroles.UpdatePermissionsRolesResDto;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.service.PermissionsRolesService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("permissions-roles")
public class PermissionsRolesController extends BaseController{
	@Autowired
	private PermissionsRolesService permissionsRolesService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = PermissionsRoles.class)))})
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		List<PermissionsRoles> result = new ArrayList<>();
		result = permissionsRolesService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PermissionsRoles.class)))
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam("id") String id)throws Exception {
		PermissionsRoles result = new PermissionsRoles();
		result = permissionsRolesService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SavePermissionsRolesResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PermissionsRoles data)throws Exception {
		SavePermissionsRolesResDto result = permissionsRolesService.save(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdatePermissionsRolesResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody PermissionsRoles data) throws Exception {
		UpdatePermissionsRolesResDto result = permissionsRolesService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PermissionsRoles.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		Boolean result = permissionsRolesService.removeById(id);
		if (result==false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
	}
}
