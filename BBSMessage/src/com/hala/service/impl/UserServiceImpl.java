package com.hala.service.impl;

import java.util.List;

import com.hala.dao.IUserDao;
import com.hala.dao.impl.UserDaoImpl;
import com.hala.entity.User;
import com.hala.service.IUserService;


public class UserServiceImpl implements IUserService {
	IUserDao ud=new UserDaoImpl();//创建dao层对象

	@Override
	public int register(User user) {
		//1.重名验证
		User result=ud.queryByName(user.getUsername());
		if(result!=null) {
			return -2;
		}
		//2.插入数据库
		
		return ud.insert(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User result=null;
		result=ud.queryByUser(user);
		return result;
		
	}

	@Override
	public User checkName(String name) {
		// TODO Auto-generated method stub
		User user=null;
		user=ud.queryByName(name);
		return user;
	}

	@Override
	public List<User> ShowAllReceiverNames(String name) {
		// TODO Auto-generated method stub
		return ud.queryAll(name);
	}

}
