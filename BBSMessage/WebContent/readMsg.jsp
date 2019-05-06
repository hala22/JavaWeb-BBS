<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>读短消息</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />

</head>
<body>
	<div id="main">
		<div class="mainbox">
			<div class="title readMessage png"></div>
			<div class="menu">
				<span>当前用户：<a href="index.jsp">${user.username}</a></span> 
				<span><a href="doShowAllReceiverNames">发短消息</a></span> 
				<span><a href="doExit">退出</a></span>
			</div>
			
			<div class="content">
				<div class="message">
					<div class="tmenu">
						<ul class="clearfix">
							<li>题目: ${msg.title}</li>
							<li>来自: ${msg.sendUser.username}</li>
							<li>时间: ${msg.msg_date}</li>
						</ul>
					</div>
					<div class="view">
						<p>${msg.msgContent}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
