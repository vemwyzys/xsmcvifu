<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0 ,user-scalable=no" />

    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/register.css" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/public.css" />
    <link type="text/css" rel="stylesheet" href="<%=path %>/resource/css/wap/simpleTips.css" />
    <title>账号注册</title>
</head>
<body>
<div class="header">
    <a href="<%=path %>/userWap/wapLogin" class="back"><img src="<%=path %>/resource/image/wap/back_gray.png" class="backImg" /></a>账号注册
</div>


<div class="content">
    <div class="register">
    	<form id="registerForm" action="<%=path %>/userWap/wapRegister/register" method="post">
    		<label class="info">
            	<span class="sign">手机号</span>
            	<input type="text" class="txt" placeholder="请输入登录手机号，11位数字" id="phone" name="phone" maxlength="11" value="${phone}"
            		onkeyup="value=value.replace(/[^\d]/g,'')" onblur="value=value.replace(/[^\d]/g,'')" />
        	</label>
        	<label class="info">
            	<span class="sign">验证码</span>
            	<span class="code">
                	<input type="text" class="checkCode" placeholder="请输入6位短信验证码" maxlength="6" id="vertifyCode" name="vertifyCode" value="${vertifyCode}"
                		onkeyup="value=value.replace(/[^\d]/g,'')" onblur="value=value.replace(/[^\d]/g,'')" />
                	<input type="text" value="获取验证码" onfocus="this.blur()" id="btn" autocomplete="off" onclick="sendCode()" />
            	</span>
        	</label>
        	<label class="info">
            	<span class="sign">登录密码</span>
            	<input type="password" class="txt" placeholder="请输入登录密码，6-16位数字或字母" maxlength="16" id="passwordOne" name="password" 
            		onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" />
        	</label>
        	<label class="info">
            	<span class="sign">重复密码</span>
            	<input type="password" class="txt" placeholder="请输入再次输入登录密码" maxlength="16" id="passwordTwo"
            		onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')" />
        	</label>
        	<label class="info">
            	<span class="sign">邀请码</span>
            	<input type="text" class="txt" placeholder="非必填，请输入邀请码" maxlength="25" id="inviteCode" name="inviteCode" value="${inviteCode}"
            		onkeyup="value=value.replace(/[^\d]/g,'')" onblur="value=value.replace(/[^\d]/g,'')" />
        	</label>
    	</form>
    </div>

    <input type="text" class="loginBtn" value="立 即 注 册" onfocus="this.blur()" onclick="registerHandle()" />
</div>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips_wap.js"></script>
<script type="text/javascript">
    var wait=60;
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.value="获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.value="重新发送(" + wait + ")";
            wait--;
            setTimeout(function() {
                        time(o)
                    },
                    1000)
        }
    }
    //document.getElementById("btn").onclick=function(){time(this);};
</script>

<script type="text/javascript">
	 window.onload = function(){
    	var message = '${message}';
    	openTips(message); 
    }
	function sendCode(){
		var flag = true;
		if(flag == false){
		    return;
		}
		
		flag = false;
		var phone = document.getElementById("phone").value;
		if(phone == null || phone == "" || phone.length != 11){
		    openTips("请填写11位手机号");
		    return;
		}
		var sendBtn= document.getElementById("btn");
		time(sendBtn);
		//发起ajax请求来发送验证码
		$.ajax({
			url : "<%=path%>"+"/userWap/wapRegister/sendCode",
			data:{phone: phone},//参数
			dataType: "json",
			type: "POST",
			async: true, //异步调用
			success: function(data){
				openTips(data.message);
				flag = true;
			},
			
			error: function(){
				openTips("服务器异常！");
				flag = true;
			}
		});
		
	}
	function registerHandle(){
		var phone = document.getElementById("phone").value;
		var vertifyCode = document.getElementById("vertifyCode").value;
		var passwordOne = document.getElementById("passwordOne").value;
		var passwordTwo = document.getElementById("passwordTwo").value;
		
		if(phone == null || phone == "" || phone.length != 11){
			openTips("请填写手机号！");
			return;
		}
		if(vertifyCode == null || vertifyCode == "" || vertifyCode.length != 6){
			openTips("请填写6位验证码");
			return;
		}
		if(passwordOne == null || passwordOne == "" || passwordOne.length < 6){
			openTips("密码非法，要求6~16位");
			return;
		}
		if(passwordOne != passwordTwo){
			openTips("两次密码不一致，请检查");
			return;
		}
		
		$("#registerForm").submit();
	}
</script>
</body>
</html>
