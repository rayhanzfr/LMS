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
import org.springframework.web.bind.annotation.RequestParam;

import com.lawencon.lms.dto.statusesinout.SaveStatusesInOutResDto;
import com.lawencon.lms.dto.statusesinout.UpdateStatusesInOutResDto;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.service.StatusesInOutService;

public class StatusesInOutController {
	
	@Autowired
	private StatusesInOutService statusesInOutService;
	
	@GetMapping
	public ResponseEntity<?>findAll()throws Exception{
		List<StatusesInOut> result = statusesInOutService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?>findById(@RequestParam(value = "id") String id) throws Exception{
		StatusesInOut result=  statusesInOutService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<?>findByCode(@RequestParam(value = "code") String code) throws Exception{
		StatusesInOut result = statusesInOutService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>save(@RequestBody StatusesInOut statusesInOut)throws Exception{
		SaveStatusesInOutResDto save = statusesInOutService.save(statusesInOut);
		return new ResponseEntity<>(save,HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<?>update(@RequestBody StatusesInOut statusesInOut) throws Exception{
		UpdateStatusesInOutResDto update = statusesInOutService.update(statusesInOut);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = statusesInOutService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
