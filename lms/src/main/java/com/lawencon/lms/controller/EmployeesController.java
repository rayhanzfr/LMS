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

@RestController
@RequestMapping("employees")
public class EmployeesController {

	@Autowired
	private EmployeesService employeesService;
	
	@GetMapping
	public ResponseEntity<?>findAll() throws Exception{
		List<Employees> result = employeesService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?>findById(@RequestParam(value = "id", required = false) String id) throws Exception{
		Employees result = employeesService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/code")
	public ResponseEntity<?>findByCode(@RequestParam(value = "code",required = false) String code) throws Exception{
		Employees result = employeesService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>save(@RequestBody Employees employees) throws Exception{
		SaveEmployeesResDto save = employeesService.save(employees);
		return new ResponseEntity<>(save,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?>update(@RequestBody Employees employees) throws Exception{
		UpdateEmployeesResDto update = employeesService.update(employees);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = employeesService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
