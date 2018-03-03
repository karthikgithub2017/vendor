package com.app.service;

import java.util.List;

import com.app.model.Vendor;

public interface IVendorService {

	public int saveVendor(Vendor ven);
	public void deleteVendor(int venId);
	public void updateVendor(Vendor ven);
	
	public Vendor getVendorById(int venId);
	public List<Vendor> getAllVendors();
}
