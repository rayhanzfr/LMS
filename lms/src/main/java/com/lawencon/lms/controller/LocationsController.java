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

import com.lawencon.lms.dto.locations.SaveLocationsResDto;
import com.lawencon.lms.dto.locations.UpdateLocationsResDto;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.service.LocationsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("locations")
public class LocationsController {
	@Autowired
	private LocationsService locationsService;

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Locations.class)))})
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		List<Locations> listLocations = new ArrayList<Locations>();
		listLocations = locationsService.findAll();
		return new ResponseEntity<>(listLocations, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Locations.class)))})
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) throws Exception {
		Locations result = new Locations();
		result = locationsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Locations.class)))})
	@GetMapping("/code/{code}")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		Locations result = new Locations();
		result = locationsService.findByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SaveLocationsResDto.class)))})
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Locations locations) throws Exception {
		SaveLocationsResDto result = new SaveLocationsResDto();
		result = locationsService.save(locations);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UpdateLocationsResDto.class)))})
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Locations locations) throws Exception {
		UpdateLocationsResDto result = new UpdateLocationsResDto();
		result = locationsService.update(locations);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Locations.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id) throws Exception {
		Boolean result = locationsService.removeById(id);
		if (result == false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
	}
}
