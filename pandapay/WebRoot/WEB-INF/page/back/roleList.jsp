<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=path %>/resource/image/back/icon.ico" type="image/x-ico">
	
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/accountRole.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    
    <title>账号角色</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">
            账号角色
            <c:forEach items="${backer_rolePowerList}" var="powerId">
       			<c:if test="${powerId == 171002}">
          			<a href="<%=path%>/backerWeb/backerRole/roleAddPage.htm" class="add"><img src="<%=path %>/resource/image/back/add.png" />新增角色</a>
       			</c:if>
       		</c:forEach>
        </div>
		
		<form id="queryForm" action="<%=path%>/backerWeb/backerRole/show.htm" method="post">
			<input hidden="hidden" id="query_pageNumber" name="pageNumber" value="${pageNumber}"/>
	        <div class="top">
	            <p class="condition">角色名称：
	            	<input type="text" class="query" placeholder="请输入角色名称" maxlength="16"
	            		id="query_roleName" name="roleName" value="${roleName}" />
	            </p>
				
	            <input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryFormHandle()"/>
	        </div>
		</form>

        <table class="table" cellpadding="0" cellspacing="0">
            <tr class="tableTitle">
                <td class="name">角色名称</td>
                <td class="jurisdiction">角色权限</td>
                <td class="time">添加时间</td>
                <td class="operate">操作</td>
            </tr>
            
            <c:forEach items="${roleList }" var="item">
	            <tr class="tableInfo">
	                <td class="name">${item.roleName }</td>
	                <td class="jurisdiction">${item.rolePower }</td>
	                <td class="time"><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td class="operate">
	                	<c:forEach items="${backer_rolePowerList}" var="powerId">
			       			<c:if test="${powerId == 171003}">
			          			<a class="change" onclick="roleModifyPage('${item.roleId}')">修改角色</a>
			       			</c:if>
			       		</c:forEach>
	                	<c:forEach items="${backer_rolePowerList}" var="powerId">
			       			<c:if test="${powerId == 171004}">
			          			<input type="text" value="删&nbsp;除" class="delete" onfocus="this.blur()" onclick="roleDeletePage('${item.roleId}')" />
			       			</c:if>
			       		</c:forEach>
	                </td>
	            </tr>
            </c:forEach>
        </table>

        <div class="changePage">
            <p class="total">共${totalNumber}条</p>
            <p class="jump">
                <input type="text" id="pageGoNumber"  value="${pageNumber+1}"
                	autocomplete="off" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" />
                <input type="text" value="跳&nbsp;转" class="jumpButton" onfocus="this.blur()" onclick="pagego()"/>
            </p>
            <p class="page">
                <input type="text" value="<上一页" onfocus="this.blur()" onclick="pagepre()"/>
                <span class="pageNumber"><span>${pageNumber+1}</span>/<span>${totalPageNumber+1}</span></span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="pagenext()"/>
            </p>
        </div>
        
    </div>
</div>

<div class="mask">
    <div class="mask_content">
        <div class="delete_pop">
            <span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">删除操作</span>
            <p class="mask_tips">删除该角色？</p>

            <div class="Button">
                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="roleDeleteHandle()" />
            </div>
        </div>
    </div>
</div>

</body>

<form id="modifyForm" action="<%=path%>/backerWeb/backerRole/roleModifyPage.htm" method="post">
	<input hidden="hidden" id="modify_roleId" name="modify_roleId"/>
</form>

<form id="deleteForm" action="<%=path%>/backerWeb/backerRole/roleDelete.htm" method="post">
	<input hidden="hidden" id="delete_roleId" name="delete_roleId"/>
	<input hidden="hidden" name="pageNumber" value="${pageNumber}"/>
	<input hidden="hidden" name="roleName" value="${roleName}"/>
</form>

<script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>

<script type="text/javascript">
    var popObj;
    $(function(){
        $(".delete").click(function(){
            $(".mask").css("display" , "block");
            $(".delete_pop").css("display" , "block");
            popObj = ".delete_pop";
        });
        $(".cancel").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
        /* $(".yes").click(function(){
            $(".mask").hide(/* 
            $(popObj).hide();
        }); */
    });
</script>

<script type="text/javascript">
	window.onload = function()
	{
		var code = ${code};
		var message = '${message}';
		if(code != 1 && message != "")
		{
			openTips(message);
			return false;
		}
	}
	
	function queryFormHandle()
	{
		document.getElementById("query_pageNumber").value = 0;
  		$("#queryForm").submit();
	}
	
	function roleModifyPage(proleId)
	{
		document.getElementById("modify_roleId").value = proleId;
  		$("#modifyForm").submit();
	}
	
	function roleDeletePage(proleId)
	{
		document.getElementById("delete_roleId").value = proleId;
	}
	function roleDeleteHandle()
	{
		$("#deleteForm").submit();
	}
</script>

<script type="text/javascript">
	//上一页
  	function pagepre()
  	{
  		var pageNumber = ${pageNumber };
  		
  		if(pageNumber > 0){
  			pageNumber = pageNumber - 1;
  			
    		document.getElementById("query_pageNumber").value = pageNumber;
			$("#queryForm").submit();
  		}else{
			openTips("当前已是第一页");
  		}
  	}
  	//下一页
  	function pagenext()
  	{
  		var pageNumber = ${pageNumber };
  		var totalPageNumber = ${totalPageNumber };
		  		
  		if(pageNumber < totalPageNumber){
  			pageNumber = pageNumber + 1;
  			
    		document.getElementById("query_pageNumber").value = pageNumber;
			$("#queryForm").submit();
  		}else{
			openTips("当前已是最后一页");
  		}
  	}
  	//页面跳转
  	function pagego()
  	{
  		var pageNumber = ${pageNumber };
  		var totalPageNumber = ${totalPageNumber };
  		
  		var pageGoNumber = document.getElementById("pageGoNumber").value;
  		if(pageGoNumber == null || pageGoNumber == "" || pageGoNumber <= 0){
  			pageGoNumber = 1;
  		}
  		if(pageGoNumber > totalPageNumber){
  			pageGoNumber = totalPageNumber + 1;
  		}
  		document.getElementById("pageGoNumber").value = pageGoNumber;
  		
 		pageNumber = pageGoNumber - 1;
 		
 		document.getElementById("query_pageNumber").value = pageNumber;
		$("#queryForm").submit();
  	}
</script>

</html>