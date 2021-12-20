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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.lms.dto.items.SaveItemsResDto;
import com.lawencon.lms.dto.items.UpdateItemsResDto;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.service.ItemsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Items.class)))})
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		List<Items> result = new ArrayList<>();
		result = itemsService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Items.class)))
	@GetMapping("/id")
	public ResponseEntity<?> getById(@RequestParam("id") String id)throws Exception {
		Items result = new Items();
		result = itemsService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Items.class)))
	@GetMapping("/code")
	public ResponseEntity<?> getByCode(@RequestParam("code") String code)throws Exception {
		Items result = new Items();
		result = itemsService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SaveItemsResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestPart String data, @RequestPart MultipartFile file)throws Exception {
		SaveItemsResDto result = itemsService.save(new ObjectMapper().readValue(data, Items.class), file);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateItemsResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestPart String data, @RequestPart MultipartFile file) throws Exception {
		UpdateItemsResDto result = itemsService.update(new ObjectMapper().readValue(data, Items.class), file);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Items.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		Boolean result = itemsService.removeById(id);
		if (result==false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
	}
}
