package com.hala.dao;

import java.util.List;

import com.hala.entity.User;

public interface IUserDao {
	
	/**
	 * 插入用户
	 * @param user
	 * @return 1成功，其他失败
	 */
	public int insert(User user);
	
	/**
	 * 根据用户名查询（重名验证）
	 * @param name
	 * @return
	 */
	public User queryByName(String name);
	
	/**
	 * 根据用户名，密码查询（登录）
	 * @param user
	 * @return
	 */
	public User queryByUser(User user);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<User> queryAll(String name);


}
