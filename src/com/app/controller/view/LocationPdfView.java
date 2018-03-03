package com.app.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.Location;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class LocationPdfView extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document,
			PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse arg4)
			throws Exception {
		//logic
		document.add(new Paragraph("Welcome to PDF"));
		//read data
		List<Location> locList=(List<Location>)map.get("locListObj");
		
		PdfPTable table=new PdfPTable(3);
		table.addCell("Location Id");
		table.addCell("Location Name");
		table.addCell("Location Type");
		
		for(Location loc:locList){
			table.addCell(""+loc.getLocId());
			table.addCell(loc.getLocName());
			table.addCell(loc.getLocType());
		}
		
		document.add(table);
		
		
		
	}

}
