package com.hala.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hala.entity.Message;
import com.hala.entity.Pager;
import com.hala.entity.User;
import com.hala.service.IMessageService;
import com.hala.service.impl.MessageServiceImpl;

/**
 * Servlet implementation class ShowMsgListServlet
 */
@WebServlet("/doShow")
public class ShowMsgListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1获取page值
		String page = req.getParameter("page");
		int p = 1;// 下标值 默认为1
		if (page != null) {
			p = Integer.parseInt(page);
		}
		Pager pager = new Pager(p);// 2.初始化分页对象
		//3.编写service层方法传入pager对象
		IMessageService ms = new MessageServiceImpl();
		Object obj = req.getSession().getAttribute("user");// 从session中获取当前登录的用户id,在LoginServlet中存入的
		User user = (User) obj;
		List<Message> list = ms.getAllMessageById(pager, user.getId());// 9个属于当前登录用户的消息对象的集合	
		//7.将集合和page对象存入request作用域中
		req.setAttribute("list", list);
		req.setAttribute("pager", pager);
		// 8.转发到显示界面
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
