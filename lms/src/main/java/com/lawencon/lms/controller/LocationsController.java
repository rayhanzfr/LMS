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
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.service.LocationsService;

@RestController
@RequestMapping("locations")
public class LocationsController {
	@Autowired
	private LocationsService locationsService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Locations> listLocations = new ArrayList<Locations>();
		try {
			listLocations = locationsService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listLocations, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listLocations, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id" ) String id) {
		Locations result = new Locations();
		try {
			result = locationsService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) {
		Locations result = new Locations();
		try {
			result = locationsService.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Locations locations, MultipartFile files) {
		SaveLocationsResDto result = new SaveLocationsResDto();
		try {
			result = locationsService.save(locations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Locations locations) {
		UpdateLocationsResDto result = new UpdateLocationsResDto();
		try {
			result = locationsService.update(locations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id){
		Boolean result = null;
		try {
			result = locationsService.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	} 
}
