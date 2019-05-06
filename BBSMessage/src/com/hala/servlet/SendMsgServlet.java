package com.hala.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.Message;
import com.hala.entity.User;
import com.hala.service.IMessageService;
import com.hala.service.impl.MessageServiceImpl;


/**
 * Servlet implementation class SendMsgServlet
 */
@WebServlet("/doSend")
public class SendMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取请求数据
		//从Session域中获取发送者id（这个是在登录成功后存入session域中的）
		Object obj=req.getSession().getAttribute("user");
		User user=(User)obj;
		
		String title=req.getParameter("title");
		String msgContent=req.getParameter("content");
		String date=new Date().toString();
		String toUserName=req.getParameter("toUser");
		
		Message message=new Message(user.getId(), title, msgContent, date);
		
		//2.处理业务逻辑
		IMessageService ms=new MessageServiceImpl();
		int r=ms.sendMsg(message, toUserName);
		resp.getWriter().write("发送成功！");
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
