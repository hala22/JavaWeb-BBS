package com.hala.service;

import java.util.List;

import com.hala.entity.User;

public interface IUserService {
	
	/**
	 * 注册的业务逻辑
	 * @param user 带有参数的用户对象
	 * @return 1成功 -2重名失败 其他失败
	 */
	public int register(User user);
	
	/**
	 * 登录的业务逻辑
	 * 这里返回User是因为后边要在域对象中存入
	 * @param user
	 * @return 成功返回User对象 失败返回null
	 */
	public User login(User user);
	
	/**
	 * 验证用户名是否存在(注册时Ajax，发消息时获取接收者id)
	 * @param name
	 * @return 存在返回User对象，不存在返回null
	 */
	public User checkName(String name);

	/**
	 * 排除自身,查询所有用户名称
	 * @param name 自身用户名
	 * @return 成功返回带有id和name的用户集合,失败返回null
	 */
	public List<User> ShowAllReceiverNames(String name);

}
