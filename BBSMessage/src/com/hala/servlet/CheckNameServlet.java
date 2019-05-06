package com.hala.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.User;
import com.hala.service.IUserService;
import com.hala.service.impl.UserServiceImpl;

public class CheckNameServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				//1.获取用户名
				String name=req.getParameter("name");
				//2.数据库验证
				IUserService us=new UserServiceImpl();
				User checkName=us.checkName(name);
				//3.处理返回结果
				resp.getWriter().write((checkName==null)?"ok":"error");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

}
