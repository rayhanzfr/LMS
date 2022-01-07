package com.lawencon.lms.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.histories.HistoriesReportResDto;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.JasperAssets;
import com.lawencon.lms.service.HistoriesService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("histories")
public class HistoriesController extends BaseController {
	
	@Autowired
	private HistoriesService historiesService;
	
	@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = HistoriesReportResDto.class)))})
	@GetMapping
	public ResponseEntity<?> findAll(String companiesCode) throws Exception {
		List<Histories> listHistoriesReportResDto = new ArrayList<>();
		listHistoriesReportResDto = historiesService.findAll();
		return new ResponseEntity<>(listHistoriesReportResDto, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
    public HttpEntity<?> generatePdf(@RequestParam("companiesCode") String companiesCode) throws Exception, JRException {
        
		Map<String, Object> res = historiesService.findHistoriesReport();
        List<HistoriesReportResDto> data = (List<HistoriesReportResDto>) res.get("listJasper");
        Map<String, Object> map = new HashMap<>();
        byte[] out = JasperUtil.responseToByteArray(data, "assets-histories", map);

        HttpHeaders headers = new HttpHeaders();
        
		headers.setContentType(MediaType.APPLICATION_PDF);
		return new HttpEntity<>(out, headers);
    }
	@GetMapping("/pdf/non-admin")
    public HttpEntity<?> generatePdfNonAdmin(@RequestParam("companiesCode") String companiesCode) throws Exception, JRException {
        
		Map<String, Object> res = historiesService.findHistoriesReportNonAdmin(companiesCode);
        List<HistoriesReportResDto> data = (List<HistoriesReportResDto>) res.get("listJasper");
        Map<String, Object> map = new HashMap<>();
        byte[] out = JasperUtil.responseToByteArray(data, "assets-histories", map);

        HttpHeaders headers = new HttpHeaders();
        
		headers.setContentType(MediaType.APPLICATION_PDF);
		return new HttpEntity<>(out, headers);
    }
}
