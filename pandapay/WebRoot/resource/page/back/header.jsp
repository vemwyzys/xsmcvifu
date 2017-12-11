<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>

<body>
<div class="header">
    <p class="h_title">
        <img src="<%=path%>/resource/image/back/logo.png">
        熊猫支付后台管理系统
    </p>
    <p class="h_side">
        <span>您好，${backer_backerAccount}</span>
        <a href="<%=path%>/backerWeb/backerWebLogin/loginOut.htm" class="out">退出登录</a>
    </p>
</div>
</body>
</html>