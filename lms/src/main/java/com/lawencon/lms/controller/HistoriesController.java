package com.lawencon.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lms.dto.histories.HistoriesReportResDto;
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
		List<HistoriesReportResDto> listHistoriesReportResDto = new ArrayList<HistoriesReportResDto>();
		listHistoriesReportResDto = historiesService.findHistoriesReport(companiesCode);
		return new ResponseEntity<>(listHistoriesReportResDto, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestParam("companiesCode") String companiesCode) throws Exception, JRException {
        
        Map<String, Object> map = new HashMap<>();
        
        byte[] data = JasperUtil.responseToByteArray(historiesService.findHistoriesReport(companiesCode), "assets-histories", map);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=assets-histories.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
