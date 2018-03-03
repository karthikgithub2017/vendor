package com.app.dao;

import java.util.List;

import com.app.model.Customer;
import com.app.model.Item;

public interface ICustomerDao {

	public int saveCustomer(Customer cust);
	public Customer getCustomerByEmail(String custEmail);
	public List<Customer> getAllCust();
	public List<Item> getItemsOfCustbyId(int custId);
	
}
