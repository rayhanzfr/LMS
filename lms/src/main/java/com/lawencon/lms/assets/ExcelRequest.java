package com.lawencon.lms.assets;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.service.InvoicesService;
import com.lawencon.lms.service.ItemsService;
import com.lawencon.lms.service.StatusesAssetsService;
import com.lawencon.lms.service.StatusesInOutService;

public class ExcelRequest {
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private InvoicesService invoicesService;
	
	@Autowired
	private StatusesAssetsService statusesAssetsService;
	
	@Autowired
	private StatusesInOutService statusesInOutService;

	String type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	String[] headers = {"items_code","invoices_code","assets_name","statuses_assets_code","statuses_in_out_code","assets_expired"};
	String sheets = "Assets";
	
	public boolean excelFormat(MultipartFile file) {
		if(!type.equals(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	public List<Assets> excelToAssets (InputStream input){
		try {
			Workbook workbook = new XSSFWorkbook(input);
			
			Sheet sheet = workbook.getSheet(sheets);
			Iterator<Row> rows = sheet.iterator();
			
			List<Assets> assets = new ArrayList<Assets>();
			int rowNum = 0;
			while(rows.hasNext()) {
				Row row = rows.next();
				
				if(rowNum==0) {
					rowNum++;
					continue;
				}
				
				Iterator<Cell> cellInRow = row.iterator();
				
				Assets asset = new Assets();
				
				int cellIndex =0;
				while(cellInRow.hasNext()) {
					Cell cell = cellInRow.next();
					switch(cellIndex) {
					case 0:
						Items item = itemsService.findByCode(cell.getStringCellValue());
						asset.setItems(item);
						break;
					case 1:
						Invoices invoices = invoicesService.findByCode(cell.getStringCellValue());
						asset.setInvoices(invoices);
						break;
					case 2:
						asset.setAssetsName(cell.getStringCellValue());
						break;
					case 3:
						StatusesAssets statusesAssets = statusesAssetsService.findByCode(cell.getStringCellValue());
						asset.setStatusesAssets(statusesAssets);
						break;
					case 4:
						StatusesInOut statusesInOut = statusesInOutService.findByCode(cell.getStringCellValue());
						asset.setStatusesInOut(statusesInOut);
					case 5:
						asset.setAssetsExpired(LocalDate.parse(cell.getStringCellValue()));
						default :
							break;
					}
					cellIndex++;
				}
				assets.add(asset);
			}
			workbook.close();
			return assets;
		} catch (Exception e) {
			throw new RuntimeException("fail to parse Excel file: "+e.getMessage());
		}
	}
}