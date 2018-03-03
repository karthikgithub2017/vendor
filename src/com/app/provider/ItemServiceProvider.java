package com.app.provider;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Customer;
import com.app.model.Item;
import com.app.service.ICustomerService;
import com.app.service.IItemService;
import com.app.util.JsonUtil;
import com.app.validator.CustomerValidator;

@Component
@Path("/item")
public class ItemServiceProvider {
	
	
	@Autowired
	private ICustomerService custService;
	@Autowired
	private CustomerValidator validator;
	@Autowired
	private JsonUtil jsonUtil;
	@Autowired
	private IItemService service;
	
	
	@POST
	@Path("/saveItem")
	public String saveItem(
			@HeaderParam("userName")String un,		
			@HeaderParam("password")String pwd,		
			@HeaderParam("token")String token,
			String itemJson){

		/**1.basic input validations*/
		if(un == null || "".equals(un.trim()))
			return "User name Can not be empty/null";
		if(pwd == null || "".equals(pwd.trim()))
			return "Password can not be null or empty";
		if(token == null || "".equals(token.trim()))
			return "Token can not be null or empty";
		if(itemJson == null || "".equals(itemJson.trim()))
			return "Item(JSON) can not be null or empty";

		/**2. user name(Customer exist) check*/
		Customer cust=custService.getCustomerByEmail(un);
		if(cust==null)
			return "Invalid Customer(Customer not exist)";
		/**
		 * 3. Request pwd,token matching with DB pwd,token
		 **/
		boolean isValid=validator.isPwdAndTokenValid(pwd, token, cust);
		if(!isValid)
			return "Password/token is invalid";
		/** 
		 * 4. Customer type should be Seller
		 */
		isValid=validator.isCustTypeSeller(cust);
		if(!isValid)
			 return "Customer type is not 'SELLER' to perform this operation";
		/**
		 * 5.convert input JSON to object(Item)
		 */
		Object ob=jsonUtil.jsonToObj(itemJson, Item.class);
		if(ob==null)
			return "Invalid JSON(Item) details";
		Item item=(Item)ob;
		//consider current customer is Item obj customer
		item.setCustId(cust.getCustId());
		/**
		 * 6.save item object
		 */
		int itemId=service.saveItem(item);
		
		return "Item Saved Successfully with Id:"+itemId;
	}



}
