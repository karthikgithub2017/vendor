package com.app.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Location;
import com.app.service.ILocationService;
import com.app.util.LocationUtil;
import com.app.validator.LocationValidator;

@Controller
public class LocationController {
	
	@Autowired
	private ILocationService serv;
	@Autowired
	private ServletContext context;
	@Autowired
	private LocationUtil util;
	@Autowired
	private LocationValidator validator;


	/**
	 * 1.This method is used to
	 * display the Location Register page
	 * on Entering URL '/locReg' in browser at last
	 *  
	 */

	@RequestMapping("/locReg")
	public String showPage(){
		return "LocationReg";
	}

	/**
	 * 2. This method is used to
	 * fetch data from UI as Object 
	 * using ModelAttribute.
	 * 
	 * Send this data to SL using
	 * service saveMethod.
	 * 
	 *  After Saving data it returns
	 *  primary key value as int type
	 * 
	 * Send success message to UI
	 * using ModelMap
	 */
	@RequestMapping(value="/insertLoc",method=RequestMethod.POST)
	public String saveLocation(@ModelAttribute("location")Location loc,ModelMap map){
		//first validate 
		String msg=validator.doValidateInputName(loc.getLocName());
		if(!"".equals(msg)){
			map.addAttribute("msg",msg);
		}else{
			//then save
			//call service layer method
			int locId=serv.saveLocation(loc);

			//const message
			String m="Location saved with Id:"+locId;
			//send message to UI
			map.addAttribute("msg", m);
		}
		return "LocationReg";
	}

	/**
	 * 3. THis method is used to
	 * fetch Data from DB using
	 * service-DAL layers.
	 *  Send this data to UI using
	 *  ModelMap
	 * It will be called on click
	 * hyper link in Location Reg Page
	 */
	@RequestMapping("/viewAllLocs")
	public String showAllLocs(ModelMap map){
		//read data using service
		List<Location> locList=serv.getAllLocations();
		//send data to UI
		map.addAttribute("locListObj", locList);
		return "LocationData";
	}

	/**
	 * 4 Delete Location
	 */
	@RequestMapping("/deleteLoc")
	public String deleteLoc(@RequestParam("locId")int locId,ModelMap map){
		String page="";
		String msg=validator.validateLocDelete(locId);
		if(!"".equals(msg)){
			page="LocationData";
			map.addAttribute("msg",msg);
			List<Location> locList=serv.getAllLocations();
			map.addAttribute("locListObj", locList);
		}else{
		//call service layer delete method
		serv.deleteLocById(locId);
		page="redirect:viewAllLocs";
		}
		return page;
	}

	/**
	 * 5.Show Edit Page
	 */
	@RequestMapping("/showEditLoc")
	public String showLocEdit(@RequestParam("locId")int locId,ModelMap map){
		Location loc=serv.getLocationById(locId);
		map.addAttribute("locObj", loc);

		return "LocationDataEdit";
	}

	/**
	 * 6.Update Location Data
	 */
	@RequestMapping(value="/updateLoc",method=RequestMethod.POST)
	public String updateLoc(@ModelAttribute("location")Location loc){
		serv.updateLocation(loc);
		return "redirect:viewAllLocs";
	}

	/**
	 * 7.Export data to Excel
	 */
	@RequestMapping("/locExcelView")
	public String generateExcel(ModelMap map){
		List<Location> locList=serv.getAllLocations();
		map.addAttribute("locListObj", locList);
		return "LocExcel";
	}
	@RequestMapping("/locPdfView")
	public String generatePdf(ModelMap map){
		map.addAttribute("locListObj", serv.getAllLocations());
		return "LocPdf";
	}
	/**
	 * 8. Generate Charts
	 */
	@RequestMapping("/viewLocReport")
	public String generateChart(){
		List<Object[]> list=serv.getLocTypeWiseCount();
		String path=context.getRealPath("/");
		util.generatePie(path, list);
		util.generateBar(path, list);
		return "LocationReport";
	}
}
