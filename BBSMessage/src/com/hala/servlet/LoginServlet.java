package com.hala.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.User;
import com.hala.service.IUserService;
import com.hala.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取用户输入的数据
		String name=req.getParameter("username");
		String pwd=req.getParameter("password");
		
		if(name==null||"".equals(name)||pwd==null||
			"".equals(pwd)) {
			resp.getWriter().write("未输入完整！");
			resp.sendRedirect("index.jsp");
			return;
		}//判断用户输入是否完全
		//2.将数据封装成对象
		User user=new User(name,pwd);
		
		//3.调用service层验证
		IUserService us=new UserServiceImpl();
		User flag=us.login(user);
		//4.对返回结果处理
		//这里不适合用请求转发，因为要求地址栏发生变化，重定向比较合适
		if(flag==null) {
			resp.getWriter().write("用户不存在！");
			resp.sendRedirect("index.jsp");
		}else{
			String code1=req.getParameter("yanzhengma").toLowerCase();
			String code2=((String)req.getSession().getAttribute("Valicode")).toLowerCase();
			if(code1.equals(code2)) {
				req.getSession().setAttribute("user", flag);
				resp.sendRedirect("doShow");
			}else {
				resp.getWriter().write("验证码输入错误！");
				resp.sendRedirect("index.jsp");
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
