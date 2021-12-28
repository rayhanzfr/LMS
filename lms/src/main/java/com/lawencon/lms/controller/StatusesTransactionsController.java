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

import com.lawencon.lms.dto.permissions.DeletePermissionsResDto;
import com.lawencon.lms.dto.statusestransactions.DeleteStatusesTransactionsResDto;
import com.lawencon.lms.dto.statusestransactions.SaveStatusesTransactionsResDto;
import com.lawencon.lms.dto.statusestransactions.UpdateStatusesTransactionsResDto;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.service.StatusesTransactionsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("statuses-transactions")
public class StatusesTransactionsController extends BaseController {
	
	@Autowired
	private StatusesTransactionsService statusesTransactionsService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = StatusesTransactions.class)))})
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		List<StatusesTransactions> result = new ArrayList<>();
		result = statusesTransactionsService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = StatusesTransactions.class)))
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam("id") String id)throws Exception {
		StatusesTransactions result = new StatusesTransactions();
		result = statusesTransactionsService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = StatusesTransactions.class)))
	@GetMapping("/code")
	public ResponseEntity<?> getByCode(@RequestParam("code") String code)throws Exception {
		StatusesTransactions result = new StatusesTransactions();
		result = statusesTransactionsService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SaveStatusesTransactionsResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestBody StatusesTransactions data)throws Exception {
		SaveStatusesTransactionsResDto result = statusesTransactionsService.save(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateStatusesTransactionsResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody StatusesTransactions data) throws Exception {
		UpdateStatusesTransactionsResDto result = statusesTransactionsService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteStatusesTransactionsResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		Boolean result = statusesTransactionsService.removeById(id);
		DeleteStatusesTransactionsResDto results = new DeleteStatusesTransactionsResDto();
		if (result==false) {
			results.setMsg("FAILED");
			return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
		} else {
			results.setMsg("SUCCESS");
			return new ResponseEntity<>(results, HttpStatus.OK);
		}
		
	}
}
