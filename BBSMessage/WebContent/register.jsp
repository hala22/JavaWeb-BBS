<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>宁宁 在线短信平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<script type="text/javascript" src="jquery-3.1.1.js"></script>
</head>

<body>
	<div id="regTitle" class="png"></div>
	<div id="regForm" class="userForm png">

<!-- 表单提交地址设置为RegisterServlet -->
		<form action="doRegister" method="post">
			<dl>
				<div id="error" style="visibility:hidden">错误信息</div>
				<dt>用 户 名：</dt>
				<dd>
					<input id="ip_name" type="text" name="username" />
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input type="password" name="password" />
				</dd>
				<dt>确认密码：</dt>
				<dd>
					<input type="password" name="affirm" />
				</dd>
				<dt>邮 箱：</dt>
				<dd>
					<input type="text" name="email" />
				</dd>
			</dl>
			<div class="buttons">
				<input class="btn-reg png" type="submit" name="register" value=" " /><input
					class="btn-reset png" type="reset" name="reset" value=" " />
			</div>
			<div class="goback">
				<a href="index.jsp" class="png">返回登录页</a>
			</div>
		</form>
	</div>
	
	
	<!-- AJAX实现用户名是否存在验证 -->
	
	<!-- JQuery写法 -->
	<script type="text/javascript">
		var $ip_name=$("#ip_name");
		$ip_name.blur(function(){
			var url="doCheckName?name="+$(this).val();
			
			$.ajax({
				type:"GET",
				url:url,
				dataType:"text",
				success:function(msg){
					var $error=$("#error");
					
					if(msg=="ok"){
						$error.css("visibility","hidden");
						//error.style.visibility="hidden";
					}else{
						$error.text("用户名已存在！");
						$error.css("visibility","visible");
						//error.innerText="用户名已存在！";
						//error.style.visibility="visible";
					}
				}
			});
		});
	
	</script>
	
	<!-- javaScript写法 -->
	<!-- <script type="text/javascript">
		var ip_name=document.getElementById("ip_name");
		ip_name.onblur=function(){
			//1.以平台兼容的方式获取XMLHttpRequest对象
			var xmlHttp;
			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("您的浏览器不支持AJAX！");
						return false;
					}
				}
			}
			//2.服务器向浏览器响应请求
			xmlHttp.onreadystatechange = function() {
				//alert(xmlHttp.readyState);
				if (xmlHttp.readyState == 4) {
					//alert(xmlHttp.status);//查看服务器端响应状态
					if (xmlHttp.status == 200) {//服务器响应一切正常
						
						//自己写的地方
						var flag=xmlHttp.responseText;
						var error=document.getElementById("error");
						if(flag=="ok"){
							error.style.visibility="hidden";
						}else{
							error.innerText="用户名已存在！";
							error.style.visibility="visible";
						}
						
						
					}
				}
			}
			
			//3.打开链接（自己写）
			var url="doCheckName?name="+this.value;
			xmlHttp.open("get",url,true);
			//4.发送请求（自己写）
			xmlHttp.send(null);
		}
	
	</script> -->
</body>
</html>
