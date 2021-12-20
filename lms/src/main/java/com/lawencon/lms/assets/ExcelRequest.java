package com.lawencon.lms.assets;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.dto.assets.SaveAssetsReqDto;

import liquibase.pro.packaged.ce;

public class ExcelRequest {
	

	static String type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] headers = {"items_code","invoices_code","assets_name","statuses_assets_code","statuses_in_out_code","assets_expired"};
	static String sheets = "assets";
	
	public static boolean excelFormat(MultipartFile file) {
		if(!type.equals(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	public static List<SaveAssetsReqDto> excelToAssets (InputStream input){
		try {
			Workbook workbook = new XSSFWorkbook(input);
			
			Sheet sheet = workbook.getSheet(sheets);
			Iterator<Row> rows = sheet.iterator();
			
			List<SaveAssetsReqDto> assets = new ArrayList<SaveAssetsReqDto>();
			int rowNum = 0;
			while(rows.hasNext()) {
				Row row = rows.next();
				
				//cek header upload same with header
				if(rowNum==0) {
					rowNum++;
					continue;
				}
				
				//set data in cell into asset
				else {
					Iterator<Cell> cellInRow = row.iterator();
					
					SaveAssetsReqDto asset = new SaveAssetsReqDto();
					
					int cellIndex =0;
					while(cellInRow.hasNext()) {
					
						String data = null;
						Cell cell = cellInRow.next();

						//change cell type to string
						CellType type = cell.getCellType();
						if(cellInRow!=null) {
							if(type == CellType.STRING) {
								data = cell.getStringCellValue();
							}else if(type == CellType.NUMERIC) {
								data = String.valueOf(cell.getNumericCellValue());
							}else if(type == CellType.BOOLEAN) {
								data = String.valueOf(cell.getBooleanCellValue());
							}
							else if(type==CellType.BLANK) {
								data = null;
							}
						}
						else {
							data = null;
						}
						
						switch(cellIndex) {
						case 0:
							asset.setItemsCode(data);
							break;
						case 1:
							asset.setInvoicesCode(data);
							break;
						case 2:
							asset.setAssetsName(data);
							break;
						case 3:
							asset.setStatusesAssetsCode(data);
							break;
						case 4:
							asset.setStatusesInOutCode(data);
						case 5:
							asset.setAssetsExpired(data);
						default :
							break;
						}
						cellIndex++;
					}
					assets.add(asset);
				}
			}
			workbook.close();
			return assets;
		} catch (Exception e) {
			throw new RuntimeException("fail to parse Excel file: "+e.getMessage());
		}
	}
}