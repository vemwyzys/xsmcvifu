<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=path %>/resource/image/back/icon.ico" type="image/x-ico">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/login.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css" />
    <title>登录</title>
</head>
<body>
<div class="basicHeader">
    <img src="<%=path %>/resource/image/back/logo.png" class="basicLogo" />熊猫支付后台管理系统
</div>

<div class="basicContent">
    <div class="content">
    	<form id="loginForm" action="<%=path %>/backerWeb/backerWebLogin/login" method="post">
	        <div class="login">
	            <p class="usersLogin">后台登录</p>
	            <p class="loginUsers">
	                <img src="<%=path %>/resource/image/back/username.png">
	                <input id="backerAccount" name="backerAccount" type="text" class="txt" placeholder="请输入账号" value="${backerAccount}" 
	                onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="16"/>
	            </p>
	
	            <p class="password">
	                <img src="<%=path %>/resource/image/back/password.png">
	                <input id="password" name="password" type="password" class="txt" placeholder="请输入密码" autocomplete="off" 
	                 onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="16"/>
	            </p>
	
	            <p class="loginCheck">
	                <input id="pageCode" name="pageCode" type="text" class="checkCode" placeholder="请输入验证码" 
	                onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" maxlength="4"
		                	onkeypress="keypressHandle(event)"/>
	                <img id="kaptchaImage" src="<%=path%>/kaptcha/getKaptchaImage">
	            </p>
	
	            <input type="text" onfocus="this.blur()" value="登&nbsp;&nbsp;录" class="loginButton" onclick="logined()"/>
	        </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>

<script type="text/javascript">
	$(function(){         
		    $('#kaptchaImage').click(function () {//生成验证码  
		     $(this).hide().attr('src', '<%=path%>/kaptcha/getKaptchaImage?' + Math.floor(Math.random()*100) ).fadeIn();  
		     event.cancelBubble=true;  
		    });  
		});   
</script>

<script type="text/javascript">
	window.onload = function(){
		var code = '${code}';
		var message = '${message}';
		if(code != 1 && message != ""){
			openTips(message);
			return;
		}
	}

	function keypressHandle(event)
    {
		//回车执行
    	if (event.keyCode == "13") {
			logined();
		}
    }
    
     function logined() {
    	var validate = validateFunc();
    	if(validate == true){
    		$("#loginForm").submit();
    	}
	}

	function validateFunc() {
		var usernameText = document.getElementById("backerAccount");
		var passwordText = document.getElementById("password");
		var pageCodeText = document.getElementById("pageCode");
	
		var uname = usernameText.value;
		var upass = passwordText.value;
		var upageCode = pageCodeText.value;
		
		if (uname == null || uname == "" || uname.length < 6 || uname.length > 16) {
			openTips("请检查登录用户名的填写，要求：6~16位，a~z、0~9");
			return false;
		}
		if (upass == null || upass == "" || upass.length < 6 || upass.length > 16) {
			openTips("请检查登录密码的填写，要求：6~16位，a~z、0~9");
			return false;
		}
		if (upageCode == null || upageCode == "" || upageCode.length != 4) {
			openTips("请检查验证码的填写");
			return false;
		}
		
		return true;
	}

</script>

</body>
</html>
