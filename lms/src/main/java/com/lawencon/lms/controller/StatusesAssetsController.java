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

import com.lawencon.lms.dto.statusesassets.SaveStatusesAssetsResDto;
import com.lawencon.lms.dto.statusesassets.UpdateStatusesAssetsResDto;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.service.StatusesAssetsService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("statuses-assets")
public class StatusesAssetsController {
	@Autowired
	private StatusesAssetsService statusesAssetsService;

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = StatusesAssets.class)))})
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		List<StatusesAssets> listStatusesAssets = new ArrayList<StatusesAssets>();
		listStatusesAssets = statusesAssetsService.findAll();
		return new ResponseEntity<>(listStatusesAssets, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = StatusesAssets.class)))})
	@GetMapping("/id")
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) throws Exception {
		StatusesAssets result = new StatusesAssets();
		result = statusesAssetsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = StatusesAssets.class)))})
	@GetMapping("/code")
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) throws Exception {
		StatusesAssets result = new StatusesAssets();
		result = statusesAssetsService.findByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SaveStatusesAssetsResDto.class)))})
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody StatusesAssets statusesAssets) throws Exception {
		SaveStatusesAssetsResDto result = new SaveStatusesAssetsResDto();
		result = statusesAssetsService.save(statusesAssets);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "201", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UpdateStatusesAssetsResDto.class)))})
	@PutMapping
	public ResponseEntity<?> update(@RequestBody StatusesAssets statusesAssets) throws Exception {
		UpdateStatusesAssetsResDto result = new UpdateStatusesAssetsResDto();
		result = statusesAssetsService.update(statusesAssets);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = StatusesAssets.class)))})
	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id) throws Exception {
		Boolean result = statusesAssetsService.removeById(id);
		if (result == false) {
			return new ResponseEntity<>("FAILED", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
	}
}
