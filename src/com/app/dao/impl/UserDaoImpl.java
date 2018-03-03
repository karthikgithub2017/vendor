package com.app.dao.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IUserDao;
import com.app.model.User;

@Repository
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	private HibernateTemplate ht;

	public int saveUser(User user) {
		return (Integer)ht.save(user);
	}

	@Override
	public User getUserByNameAndPwd(String un, String pwd) {
		User user=null;
		String hql="from "+User.class.getName()
				+" where (userEmail=? or userContact=?) and userPwd=?";
		List<User> userList=ht.find(hql, un,un,pwd);
		if(userList!=null && userList.size()>0){
			user=userList.get(0);
		}
		return user;
	}
}
