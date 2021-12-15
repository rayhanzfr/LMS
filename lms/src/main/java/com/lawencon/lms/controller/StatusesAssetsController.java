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

import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.service.StatusesAssetsService;

@RestController
@RequestMapping("statuses-assets")
public class StatusesAssetsController {
	@Autowired
	private StatusesAssetsService statusesAssetsService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<StatusesAssets> listStatusesAssets = new ArrayList<StatusesAssets>();
		try {
			listStatusesAssets = statusesAssetsService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listStatusesAssets, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(listStatusesAssets, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findById(@RequestParam(required = false, name = "id") String id) {
		StatusesAssets result = new StatusesAssets();
		try {
			result = statusesAssetsService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findByCode(@RequestParam(required = false, name = "code") String code) {
		StatusesAssets result = new StatusesAssets();
		try {
			result = statusesAssetsService.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody StatusesAssets statusesAssets, MultipartFile files) {
		StatusesAssets result = new StatusesAssets();
		try {
			result = statusesAssetsService.save(statusesAssets);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody StatusesAssets statusesAssets) {
		StatusesAssets result = new StatusesAssets();
		try {
			result = statusesAssetsService.update(statusesAssets);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> removeById(@PathVariable String id) {
		Boolean result = null;
		try {
			result = statusesAssetsService.removeById(id);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
