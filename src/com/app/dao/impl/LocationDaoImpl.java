package com.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.ILocationDao;
import com.app.model.Location;
import com.app.model.Vendor;

@Repository
public class LocationDaoImpl implements ILocationDao {
	@Autowired
	private HibernateTemplate ht;

	@Override
	public int saveLocation(Location loc) {
		return (Integer)ht.save(loc);
	}
	
	@Override
	public List<Location> getAllLocations() {
		return ht.loadAll(Location.class);
	}
	
	@Override
	public void deleteLocById(int locId) {
		ht.delete(new Location(locId));
	}
	
	@Override
	public Location getLocationById(int locId) {
		return ht.get(Location.class, locId);
	}
	
	@Override
	public void updateLocation(Location loc) {
		ht.update(loc);
	}
	
	@Override
	public List<Object[]> getLocTypeWiseCount() {
		String hql="select locType,count(locType) "
	               +" from "+Location.class.getName()
	               +" group by locType ";
		List<Object[]> lst=ht.find(hql);
		return lst;
	}
	
	@Override
	public boolean isLocationWithNameExist(String locName) {
		boolean isExist=false;
		String hql="from "+Location.class.getName()
				+" where locName=?";
		List<Location> list=ht.find(hql, locName);
		if(list!=null && list.size()>0){
			isExist=true;
		}
		return isExist;
	}
	
	@Override
	public boolean isLocationUsedWithVendor(int locId) {
		boolean flag=false;
		String hql="from "+Vendor.class.getName()+" ven "
				+" join ven.loc as loc "
				+" where loc.locId=?";
		List<Vendor> vList=ht.find(hql, locId);
		if(vList!=null && vList.size()>0){
			flag=true;
		}
		return flag;
	}
	
}
