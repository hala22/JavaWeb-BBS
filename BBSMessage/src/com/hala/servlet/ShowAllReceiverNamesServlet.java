package com.hala.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.User;
import com.hala.service.IUserService;
import com.hala.service.impl.UserServiceImpl;
@WebServlet("/doShowAllReceiverNames")
public class ShowAllReceiverNamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询数据库
		IUserService us = new UserServiceImpl();
		//从session域中获取之前登录成功后的用户名
		String name = ((User)request.getSession().getAttribute("user")).getUsername();
		List<User> list = us.ShowAllReceiverNames(name);
		//存入request域中
		request.setAttribute("nameList", list);
		//转发至newMsg.jsp
		request.getRequestDispatcher("newMsg.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
