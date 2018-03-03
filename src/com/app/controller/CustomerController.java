package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Customer;
import com.app.model.Item;
import com.app.service.ICustomerService;
import com.app.util.CoDecUtil;
import com.app.util.CodeUtil;
import com.app.util.CommonUtil;

@Controller
public class CustomerController {
	@Autowired
	private ICustomerService service;
	@Autowired
	private CodeUtil codeUtil;
	@Autowired
	private CoDecUtil codecUtil;
	@Autowired
	private CommonUtil commonUtil;
	
	
	
	//1.show Reg page
	@RequestMapping("/custReg")
	public String showPg(){
		return "CustomerReg";
	}
	
	
	//2.save customer
	@RequestMapping(value="/insertCust",method=RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer")Customer cust,ModelMap map){
		//generate pwd,token using codeutil
		String pwd=codeUtil.genPwd();
		String token=codeUtil.genToken();
		
		//encode values
		String ePwd=codecUtil.doEncode(pwd);
		String eToken=codecUtil.doEncode(token);
		
		//set encoded data to object
		cust.setAccToken(eToken);
		cust.setPassword(ePwd);
		
		//save data to DB using service
		int custId=service.saveCustomer(cust);
		
		//send email
		String text="UserName:"+cust.getCustEmail()+"("+cust.getCustType()+"),"
		+" your password is: "+pwd+" and token is :"+token;
		commonUtil.sendEmail(cust.getCustEmail(), "Welcome to Customer ..", text);
		
		//send msg to UI
		map.addAttribute("msg","Customer Saved with id:"+custId);
		return "CustomerReg";
	}
	
	@RequestMapping("/viewAllCust")
	public String viewAllCust(ModelMap map){
		List<Customer> custList=service.getAllCust();
		map.addAttribute("custList", custList);
		return "CustomerData";
	}
	
	@RequestMapping("/showCustItem")
	public String getCustItems(@RequestParam("custId")int custId,ModelMap map){
		List<Item> itemList=service.getItemsOfCustbyId(custId);
		map.addAttribute("itemList", itemList);
		map.addAttribute("CustomerId", custId);
		return "CustomerItem";
	}
}
