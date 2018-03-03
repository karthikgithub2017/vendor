package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.service.ILocationService;

@Component
public class LocationValidator {
	@Autowired
	private ILocationService service;
	
	public String doValidateInputName(String locName){
		String errorMsg="";
		if(service.isLocationWithNameExist(locName)){
			errorMsg="'"+locName+"' already exist.";
		}
		return errorMsg;
	}
	
	public String validateLocDelete(int locId){
		String msg="";
		if(service.isLocationUsedWithVendor(locId)){
			msg="Location with id:"+locId+", can not be deleted";
		}
		return msg;
	}
}
