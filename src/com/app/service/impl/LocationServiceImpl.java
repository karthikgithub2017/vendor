package com.app.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ILocationDao;
import com.app.model.Location;
import com.app.service.ILocationService;

@Service
public class LocationServiceImpl implements ILocationService {
	@Autowired
	private ILocationDao dao;

	@Override
	public int saveLocation(Location loc) {
		return dao.saveLocation(loc);
	}
	
	@Override
	public List<Location> getAllLocations() {
		List<Location> locList=dao.getAllLocations();
		if(locList!=null && locList.size()>1){
			Collections.sort(locList);
		}
		return locList;
	}
	
	@Override
	public void deleteLocById(int locId) {
		dao.deleteLocById(locId);
	}

	@Override
	public Location getLocationById(int locId) {
		return dao.getLocationById(locId);
	}

	@Override
	public void updateLocation(Location loc) {
		dao.updateLocation(loc);
	}

	@Override
	public List<Object[]> getLocTypeWiseCount() {
		return dao.getLocTypeWiseCount();
	}
	
	@Override
	public boolean isLocationWithNameExist(String locName) {
		return dao.isLocationWithNameExist(locName);
	}
	@Override
	public boolean isLocationUsedWithVendor(int locId) {
		return dao.isLocationUsedWithVendor(locId);
	}
	
}