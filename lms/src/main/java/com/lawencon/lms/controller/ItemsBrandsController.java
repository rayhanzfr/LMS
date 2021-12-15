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
import com.lawencon.lms.dto.itemsbrands.SaveItemsBrandsResDto;
import com.lawencon.lms.dto.itemsbrands.UpdateItemsBrandsResDto;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.service.ItemsBrandsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("itemsBrands")
public class ItemsBrandsController extends ConnHandler {
	
	@Autowired
	private ItemsBrandsService itemsBrandsService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ItemsBrands.class)))})
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		List<ItemsBrands> result = new ArrayList<>();
		result = itemsBrandsService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ItemsBrands.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@RequestParam("id") String id)throws Exception {
		ItemsBrands result = new ItemsBrands();
		result = itemsBrandsService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ItemsBrands.class)))
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getByCode(@RequestParam("code") String code)throws Exception {
		ItemsBrands result = new ItemsBrands();
		result = itemsBrandsService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SaveItemsBrandsResDto.class)))
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ItemsBrands data)throws Exception {
		SaveItemsBrandsResDto result = itemsBrandsService.save(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateItemsBrandsResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ItemsBrands data) throws Exception {
		UpdateItemsBrandsResDto result = itemsBrandsService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ItemsBrands.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable("id") String id) throws Exception {
		Boolean result = itemsBrandsService.removeById(id);
		if (result==false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
	}
}
