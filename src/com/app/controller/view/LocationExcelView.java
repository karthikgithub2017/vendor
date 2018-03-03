package com.app.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.app.model.Location;


public class LocationExcelView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> map,
			HSSFWorkbook book, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {

		//create sheet using book
		HSSFSheet sheet=book.createSheet("Location");
		
		//read data from map
		List<Location> locList=(List<Location>)map.get("locListObj");
		
		//set the head
		setHead(sheet);
		
		//set body
		setBody(sheet, locList);
	}
	
	private void setHead(HSSFSheet sheet){
		HSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("Location Id");
		row.createCell(1).setCellValue("Location Name");
		row.createCell(2).setCellValue("Location Type");
		
	}
	public void setBody(HSSFSheet sheet,List<Location> locList){
		int rownum=1;
		for(Location loc:locList){
			HSSFRow row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(loc.getLocId());
			row.createCell(1).setCellValue(loc.getLocName());
			row.createCell(2).setCellValue(loc.getLocType());
		}
	}
	
	
	
}
