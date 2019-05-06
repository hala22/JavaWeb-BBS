package com.hala.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.Message;
import com.hala.service.IMessageService;
import com.hala.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class ShowOneServlet
 */
@WebServlet("/doShowOne")
public class ShowOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.获得用户点击的message ID
		String id=req.getParameter("id");
		long mid=Long.parseLong(id);
		//2.业务处理
		IMessageService ms=new MessageServiceImpl();
		Message msg=ms.readMsg(mid);
		
		//3.将结果存入域中,资源跳转
		req.setAttribute("msg", msg);
		//System.out.println(msg.getTitle());
		req.getRequestDispatcher("readMsg.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
