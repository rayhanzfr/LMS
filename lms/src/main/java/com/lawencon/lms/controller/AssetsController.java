package com.lawencon.lms.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.assets.ExcelRequest;
import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.GetTotalAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.JasperAssets;
import com.lawencon.lms.service.AssetsService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("assets")
public class AssetsController {

	@Autowired
	private AssetsService assetsService;

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		GetAllAssetsDto result = new GetAllAssetsDto();
		try {
			result = assetsService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/expired")
	public ResponseEntity<?> getAssetsExpired() throws Exception {
		List<JasperAssets> result = new ArrayList<>();
		try {
			result = assetsService.getAssetsExpired();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetByIdAssetsDto.class))) })
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@RequestParam(value = "id") String id) throws Exception {
		GetByIdAssetsDto result = assetsService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetByIdAssetsDto.class))) })
	@GetMapping("{assetsName}")
	public ResponseEntity<?> findByAssetsName(@RequestParam(value = "assetsName") String assetsName) throws Exception {
		GetByIdAssetsDto result = assetsService.findByAssetsName(assetsName);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/items")
	public ResponseEntity<?> findByItemsCode(@RequestParam(value = "itemsCode") String itemsCode) throws Exception {
		GetAllAssetsDto result = assetsService.findByItemsCode(itemsCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/itemsBrands")
	public ResponseEntity<?> findByItemsBrandsCode(@RequestParam(value = "itemsBrandsCode") String itemsBrandsCode)
			throws Exception {
		GetAllAssetsDto result = assetsService.findByItemsBrandsCode(itemsBrandsCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/itemsTypes")
	public ResponseEntity<?> findByItemsTypesCode(@RequestParam(value = "itemsTypesCode") String itemsTypesCode)
			throws Exception {
		GetAllAssetsDto result = assetsService.findByItemsTypesCode(itemsTypesCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/statusesAssets")
	public ResponseEntity<?> findByStatusesAssetsCode(
			@RequestParam(value = "statusesAssetsCode") String statusesAssetsCode) throws Exception {
		GetAllAssetsDto result = assetsService.findByStatusesAssetsCode(statusesAssetsCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/statusesInOut")
	public ResponseEntity<?> findByStatusesInOutCode(
			@RequestParam(value = "statusesInOutCode") String statusesInOutCode) throws Exception {
		GetAllAssetsDto result = assetsService.findByStatusesInOutCode(statusesInOutCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = GetAllAssetsDto.class))) })
	@GetMapping("/req")
	public ResponseEntity<?> findByReq(@RequestParam(value = "itemsCode", required = false) String itemsCode,
			@RequestParam(value = "itemsTypesCode", required = false) String itemsTypesCode,
			@RequestParam(value = "itemsBrandsCode", required = false) String itemsBrandsCode,
			@RequestParam(value = "statusesAssetsCode", required = false) String statusesAssetsCode,
			@RequestParam(value = "statusesInOutCode", required = false) String statusesInOutCode,
			@RequestParam(value = "total", required = false) Integer total) throws Exception {
		GetTotalAssetsReqDto result = assetsService.getTotalreq(itemsCode, itemsBrandsCode, itemsTypesCode,
				statusesAssetsCode, statusesInOutCode, total);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = SaveAssetsResDto.class))) })
	@PostMapping
	public ResponseEntity<?> save(@RequestBody SaveAssetsReqDto save) throws Exception {
		SaveAssetsResDto result = assetsService.save(save);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "201", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = UpdateAssetsResDto.class))) })
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UpdateAssetsReqDto update) throws Exception {
		UpdateAssetsResDto result = assetsService.update(update);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = Assets.class))) })
	@DeleteMapping()
	public ResponseEntity<?> removeById(@RequestParam(value = "id") String id) throws Exception {
		boolean result = assetsService.removeById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelRequest.excelFormat(file)) {
			try {
				assetsService.saveFile(file);
				message = "Uploaded successfully: " + file.getOriginalFilename();
				return new ResponseEntity<>(message, HttpStatus.CREATED);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
			}
		}

		message = "Please upload an excel file!";
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/download")
	public HttpEntity<?> reportSample() throws Exception {
		List<JasperAssets> data = assetsService.getAssetsExpired();

		Map<String, Object> map = new HashMap<>();
		map.put("company", "items");

		byte[] out = JasperUtil.responseToByteArray(data, "assets-report", map);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		return new HttpEntity<>(out, headers);
	}
}
