package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Customer;
import com.app.util.CoDecUtil;
@Component
public class CustomerValidator {
	@Autowired
	private CoDecUtil codecUtil;

	public boolean isCustTypeConsumer(Customer cust){
		return "CONSUMER".equalsIgnoreCase(cust.getCustType());
	}
	
	public boolean isCustTypeSeller(Customer cust){
		return "SELLER".equalsIgnoreCase(cust.getCustType());
	}
	
	public boolean isPwdAndTokenValid(String reqPwd,String reqToken,Customer cust){
		boolean isValid=false;
		//read values from cust obj
		String encPwd=cust.getPassword();
		String encToken=cust.getAccToken();
		
		//Decode values(pwd,token)
		String dbPwd=codecUtil.doDecode(encPwd);
		String dbToken=codecUtil.doDecode(encToken);
		
		//compare Request and DB values
		isValid = reqPwd.equals(dbPwd) && reqToken.equals(dbToken);
		
		return isValid;
	}
	
}
