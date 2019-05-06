package com.hala.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hala.dao.IUserDao;
import com.hala.entity.User;
import com.hala.utils.JDBCUtils;

public class UserDaoImpl implements IUserDao{

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		//通过jdbc模版获取数据库操作对象
		QueryRunner run=JDBCUtils.getQueryRunner();
		String sql="INSERT INTO user(username,password,email) VALUES(?,?,?)";
		int rows=-1;//影响的行数
		try {
			rows=run.update(sql,user.getUsername(),
					user.getPassword(),user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public User queryByName(String name) {
		// TODO Auto-generated method stub
		QueryRunner run=JDBCUtils.getQueryRunner();
		String sql="SELECT * FROM user WHERE username=?";
		User user=null;
		try {
			user=run.query(sql, new BeanHandler<>(User.class),name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User queryByUser(User user) {
		// TODO Auto-generated method stub
		QueryRunner run=JDBCUtils.getQueryRunner();
		String sql="SELECT * FROM user WHERE username=? AND password=?";
		User result=null;
		try {
			result=run.query(sql,new BeanHandler<>(User.class),
					user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<User> queryAll(String name) {
		//1获取数据库操作对象
		QueryRunner run = JDBCUtils.getQueryRunner();
		// 2.执行
		String sql = "SELECT id,username FROM user WHERE username != ?";
		List<User> list = null;
		try {
			list = run.query(sql, new BeanListHandler<>(User.class), name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
