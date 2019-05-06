<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>宁宁最棒 短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css"/>
<script type="text/javascript" src="jquery-3.1.1.js"></script>
</head>
<body>

	<div id="loginTitle" class="png"></div>
	<div id="loginForm" class="userForm png">
		<form action="doLogon" method="post">
			<dl>
				<div id="error" style="visibility:hidden">显示登录失败等错误信息</div>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="username"/>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input type="text" name="password"/>
				</dd>
				<dd>
					<input type="text" name="yanzhengma"/>
					<img id="im_1" src="doCode"/>
					<a href="#" onclick="change()">点击刷新</a>
				</dd>
			</dl>
			<div class="buttons">
				<input class="btn-login png" type="submit" name="submit" value=" " />
				<!-- 跳转的处理 -->
				<a href="register.jsp"><input class="btn-reg png" type="button" name="register" value=" " /></a>
			</div>
		</form>
	</div>
	
	<!-- 验证码处理 -->
	<script type="text/javascript">
		function change(){
			var im_1=document.getElementById("im_1");
			im_1.src="doCode?"+new Date().getTime();
			return false;
		}
	
	//解决错误提示AJAX  ？
	/* 	var $error=$("#error");
		$error.blur(function(){
			$.ajax({
				type:"GET",
				url:"doLogon",
				dataType:"text",
				success:function(msg){
					if(msg!=null&&msg!=""){
						$(this).text(msg);
						$(this).css("visibility","visible");
					}else{
						$(this).css("visibility","hidden");
					}
				}
			});
		}); */
		
	</script>

</body>
</html>