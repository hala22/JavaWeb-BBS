<%@page import="com.hala.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>宁宁 在线短信平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />

</head>
<body>

<%
	//从session域中获取数据,记得导包
	Object obj=session.getAttribute("user");
	User user=(User)obj;
	//运用金刚葫芦娃也可以如下
	//Object obj1=pageContext.findAttribute("user");
	//Object obj2=pageContext.getAttribute("user",PageContext.SESSION_SCOPE);
%>

	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<div class="menu">
			<!-- 这里使用jsp很简单的可以调用user的方法取值，与java中一致 -->
			<!--下边是EL表达式写法，作用与jsp完全一样，注意取getUsername只需要如下写即可-->
			<%-- ${user.username} --%>
				<span>当前用户：<a href="#"><%=user.getUsername() %></a></span> 
				<span><a href="doShowAllReceiverNames">发短消息</a></span> 
				<span><a href="doExit">退出</a></span>
			</div>
			<!--错误信息  -->
			<div id="error"></div>
			<!--短消息列表  -->
			<div class="content messageList">
				<!-- 9显示-->
				<ul>
				<c:forEach var="temp" items="${list}">
					<li class="${temp.state==1?'unReaded':'rrr' }">
						<a href="doShowOne?id=${temp.id}">
						${temp.title},${temp.msg_date} ------------来自：${temp.sendUser.username}
						</a>
					</li>
				</c:forEach>
				</ul>
			
				<!-- 10显示导航标签 -->
				<!-- 注意：这边只要拼接page=？下标就可以了 -->
				<div class="pager">
					<ul id="ul_1">
						<a href="doShow?page=${pager.prevPage}">上一页</a>&nbsp;
						<c:forEach items="${pager.groupList}" var="i"> 
							<c:if test="${ i == pager.currentPage }">
								${i}&nbsp;
							</c:if>  
							<c:if test="${ i != pager.currentPage }">
								<a href="doShow?page=${i}">${i}</a>&nbsp;
							</c:if>
						</c:forEach>
						<a href="doShow?page=${pager.nextPage}">下一页</a><br/>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
