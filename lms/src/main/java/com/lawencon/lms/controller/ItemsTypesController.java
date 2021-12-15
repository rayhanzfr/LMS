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

import com.lawencon.lms.dto.itemstypes.SaveItemsTypesResDto;
import com.lawencon.lms.dto.itemstypes.UpdateItemsTypesResDto;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.service.ItemsTypesService;

public class ItemsTypesController {

	@Autowired
	private ItemsTypesService itemsTypesService;
	
	@GetMapping
	public ResponseEntity<?>findAll()throws Exception{
		List<ItemsTypes> result = itemsTypesService.findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?>findById(@RequestParam(value = "id") String id) throws Exception{
		ItemsTypes result=  itemsTypesService.findById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?>findByCode(@RequestParam(value = "code") String code) throws Exception{
		ItemsTypes result = itemsTypesService.findByCode(code);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>save(@RequestBody ItemsTypes itemsTypes)throws Exception{
		SaveItemsTypesResDto save = itemsTypesService.save(itemsTypes);
		return new ResponseEntity<>(save,HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<?>update(@RequestBody ItemsTypes itemsTypes) throws Exception{
		UpdateItemsTypesResDto update = itemsTypesService.update(itemsTypes);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?>removeById(@RequestParam("id") String id) throws Exception{
		boolean result = itemsTypesService.removeById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
