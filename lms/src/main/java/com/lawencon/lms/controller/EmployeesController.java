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

import com.lawencon.lms.dto.employees.SaveEmployeesResDto;
import com.lawencon.lms.dto.employees.UpdateEmployeesResDto;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.service.EmployeesService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("employees")
public class EmployeesController extends BaseController {

	@Autowired
	private EmployeesService employeesService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@GetMapping
	public ResponseEntity<?>findAll() throws Exception{
		List<Employees> result = employeesService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@GetMapping("/id")
	public ResponseEntity<?>findById(@RequestParam(value = "id", required = false) String id) throws Exception{
		Employees result = employeesService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@GetMapping("/code")
	public ResponseEntity<?>findByCode(@RequestParam(value = "code",required = false) String code) throws Exception{
		Employees result = employeesService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@GetMapping("/companies")
	public ResponseEntity<?>findByCompaniesCode() throws Exception{
		List<Employees> result = employeesService.employeesCompany();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@GetMapping("/users")
	public ResponseEntity<?>findByUsersId() throws Exception{
		Employees result = employeesService.findByUserId();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@PostMapping
	public ResponseEntity<?>save(@RequestBody Employees employees) throws Exception{
		SaveEmployeesResDto save = employeesService.save(employees);
		return new ResponseEntity<>(save,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@PutMapping
	public ResponseEntity<?>update(@RequestBody Employees employees) throws Exception{
		UpdateEmployeesResDto update = employeesService.update(employees);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Employees.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = employeesService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
