package com.hala.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.User;
import com.hala.service.IUserService;
import com.hala.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取用户输入的数据
		String name=req.getParameter("username");
		String pwd=req.getParameter("password");
		String email=req.getParameter("email");
		if(name==null||"".equals(name)||pwd==null||
				"".equals(pwd)||email==null||"".equals(email)) {
			resp.getWriter().write("未输入完整！");
			resp.sendRedirect("register.jsp");
			return;
		}//判断用户输入是否完全
		//2.将数据封装成对象
		User user=new User(name,pwd,email);
		//3.调用service层完成注册
		IUserService us=new UserServiceImpl();
		int row=us.register(user);
		//4.根据结果进行相关处理
		if(row==1) {
			resp.getWriter().write("注册成功！");
		}else if(row==-2) {
			resp.getWriter().write("用户名已存在！");
		}else {
			resp.getWriter().write("注册失败！");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
