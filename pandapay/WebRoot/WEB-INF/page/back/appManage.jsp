<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/appManage.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/need/laydate.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/skins/dahong/laydate.css">

    <title>APP版本管理</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">APP版本管理<p class="add">新增版本</p></div>
        <div class="top">
        <form id="queryForm" action="<%=path %>/backerWeb/appManage/show.htm" method="get">
            <input type="hidden" id="pageNumber" name="pageNumber" value="${pageNumber}">
        	<p class="condition">
               	 申请时间：
               	 从<input placeholder="请选择起始时间" class="startTime" readonly="readonly" name="startTime" id="startTime" onClick="laydate({istime: true,format:'YYYY-MM-DD hh:mm:ss'})" value="${startTime}">
               	 到<input placeholder="请选择结束时间" class="endTime"  readonly="readonly" name="endTime" id="endTime"  onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value="${endTime}">
            </p>
            <p class="condition">版本类型：
                <select class="choose" id="versionType" name="versionType">
                    <option value="0">请选择</option>
                    <option value="1">Android</option>
                    <option value="2">IOS</option>
                </select>
            </p>
            <p class="condition">版本号：<input type="text" class="query" id="versionNumber" name="versionNumber" placeholder="请输入版本号 " maxlength="16" 
            	onkeyup="value=value.replace(\d+(\.\d+){0,2},'')" onblur="value=value.replace(\d+(\.\d+){0,2},'')" value="${versionNumber}" /></p>
        	<input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryHandle()" />
        </form>
        </div>

        <table class="table" cellpadding="0" cellspacing="0">
            <tr class="tableTitle">
                <td class="time">添加时间</td>
                <td class="appVersion">版本类型</td>
                <td class="appNumber">版本号</td>
                <td class="appState">更新说明</td>
                <td class="appLink">下载地址</td>
                <td class="operate">操作</td>
            </tr>
			<c:forEach items="${appVersionList}" var="list">
				<tr class="tableInfo">
                	<td class="time"><fmt:formatDate value="${list.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                	<c:if test="${list.versionType == 1}">
                		<td class="appVersion">Android</td>
                	</c:if>
                	<c:if test="${list.versionType == 2}">
                		<td class="appVersion">IOS</td>
                	</c:if>
                	<td class="appNumber">${list.versionNumber}</td>
                	<td class="appState">${list.updateDescribe}</td>
                	<c:if test="${list.versionType == 1}">
                		<td class="appLink">${fileServiceRootUrl}${list.uploadUrl}</td>
                	</c:if>
                	<c:if test="${list.versionType == 2}">
                		<td class="appLink">${list.uploadUrl}</td>
                	</c:if>
                	<td class="operate">
                	<c:forEach items="${backer_rolePowerList}" var="powerId">
                		<c:if test="${powerId == 161003}">
                			<input type="text" value="删除" class="delete" onfocus="this.blur()" onclick="deleteApp('${list.versionId}','${list.versionType}')" />
                		</c:if>
                	</c:forEach>
                </td>
            </tr>
			</c:forEach>          
        </table>

        <div class="changePage">
            <p class="total">共${totalNumber}条</p>
            <p class="jump">
                <input type="text" id="goPageNumber" value="${pageNumber + 1}" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="value=value.replace(/[^\d]/g,'')" />
                <input type="text" value="跳&nbsp;转" class="jumpButton" onfocus="this.blur()" onclick="goPage()" />
            </p>
            <p class="page">
                <input type="text" value="<上一页" onfocus="this.blur()" onclick="prePage()" />
                <span class="pageNumber"><span>${pageNumber+1}</span>/<span>${totalPageNumber+1}</span></span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="nextPage()" />
            </p>
        </div>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
        <div class="add_pop">
            <form id="addForm" action="<%=path %>/backerWeb/appManage/add.htm" method="post" enctype="multipart/form-data">
            	<input type="hidden" id="uploadUrl" name="uploadUrl">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">新增版本</span>
            	<p class="entry_tips">
                	<label class="sign">版本类型<span>*</span>:</label>
                	<select class="choice" onchange="change(this.value)" name="add_versionType" id="add_versionType">
                    	<option value="0">请选择</option>
                    	<option value="1">Android</option>
                    	<option value="2">IOS</option>
                	</select>
            	</p>
            	<p class="entry_tips">
                	<label class="sign">版本号<span>*</span>:</label>
                	<input type="text" class="box" id="add_versionNumber" name="add_versionNumber" placeholder="如：1.0.1" maxlength="16"
                		onkeyup="value=value.replace(\d+(\.\d+){0,2},'')" onblur="value=value.replace(\d+(\.\d+){0,2},'')" />
            	</p>
            	<p class="entry_tips download">
                	<label class="sign">IOS地址<span>*</span>:</label>
                	<input type="text" id="ios_uploadUrl" name="ios_uploadUrl" class="box" placeholder="请输入IOS下载地址" />
            	</p>
            	<p class="entry_tips choose_file">
                	<label class="sign">APK文件<span>*</span>：</label>
                	<input type="file" id="apk_file" name="apk_file" class="file" id="changead_a2" onchange="document.getElementById('changead_t2').value = this.value;" accept="application/vnd.android"/>
                	<input type="text" id="changead_t2"  class="choose" placeholder="请选择文件" onfocus="this.blur()" />
                	<input type="text"  onclick="document.getElementById('changead_a2').click();"  value="选择文件" class="choose_button" onfocus="this.blur();" />
            	</p>
            	<p class="entry_tips">
                	<label class="sign">更新说明<span>*</span>:</label>
                	<textarea class="txt" id="add_updateDescibe" name="add_updateDescribe" placeholder="最多输入200个字符" maxlength="200"></textarea>
            	</p>
            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="addHandle()"  />
            	</div>
            </form>           
        </div>

        <div class="delete_pop">
        	<form id="deleteForm" action="<%=path %>/backerWeb/appManage/delete.htm" method="post">
        	    <input type="hidden" id="deleteId" name="deleteId">
        	    <input type="hidden" name="startTime" value="${startTime}">
        	    <input type="hidden" name="endTime" value="${endTime}">
        	    <input type="hidden" name="versionNumber" value="${versionNumber}">
        	    <input type="hidden" name="versionType" value="${versionType}">
        	    <input type="hidden" id="delete_versionType" name="delete_versionType">
        		<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">删除操作提示</span><br>
            	<p class="mask_tips">确定要删除吗?</p>
            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()"  />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="deleteHandle()" />
            	</div>
        	</form>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/laydate.js"></script>
<script type="text/javascript">
    function change(valueNum) {
        if (valueNum == 0) {
            $(".download").css("display", "none");
            $(".choose_file").css("display", "none");
        }
        if (valueNum == 1) {
            $(".download").css("display", "none");
            $(".choose_file").css("display", "block");
        }
        if (valueNum == 2) {
            $(".download").css("display", "block");
            $(".choose_file").css("display", "none");
        }
    };
    $(function(){
        var popObj;
        $(".add").click(function(){
            $(".mask").css("display" , "block");
            $(".add_pop").css("display" , "block");
            popObj = ".add_pop";
        });
        $(".Android_change").click(function(){
            $(".mask").css("display" , "block");
            $(".Android_pop").css("display" , "block");
            popObj = ".Android_pop";
        });
        $(".IOS_change").click(function(){
            $(".mask").css("display" , "block");
            $(".IOS_pop").css("display" , "block");
            popObj = ".IOS_pop";
        });

        $(".delete").click(function(){
            $(".mask").css("display" , "block");
            $(".delete_pop").css("display" , "block");
            popObj = ".delete_pop";
        });

        $(".cancel").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
        $(".yes").click(function(){
            //$(".mask").hide();
            //$(popObj).hide();
        });
    });

</script>
<script type="text/javascript">
	window.onload = function(){
	var code = ${code};
	var type = ${versionType};
	var select = document.getElementById("versionType");
	if(select != null && select != ""){
		select[type].selected = true;
	}
	var message = '${message}';
		if(code != 1 && message != ''){
		    openTips(message);
		    return false;
		}
	}
	function queryHandle(){
	    //点击查询时传给控制器的页面值应人为设置为0，不直接传0是因为还有上一页下一页
		document.getElementById('pageNumber').value = "0";
		$('#queryForm').submit(); 
	}
	function addHandle(){
		var versionType = document.getElementById("add_versionType").value;
		var versionNumber = document.getElementById("add_versionNumber").value;
		var updateDescribe = document.getElementById("add_updateDescibe").value;
		var apkFile = document.getElementById("apk_file").value;
		var iosUrl = document.getElementById("ios_uploadUrl").value;
		var type = apkFile.substring(apkFile.lastIndexOf(".")+1, apkFile.lenght).toLowerCase();
		
		var uploadUrl = document.getElementById("uploadUrl");
		
		if(versionType == null || versionType == "" || versionType == 0){
			openTips("请选择版本类型");
			return;
		}
		if(versionNumber == null || versionNumber == ""){
			openTips("请填写版本号");
			return;
		}
		if(versionType == 1){
		    if(apkFile == null || apkFile == ""){
		        openTips("请选择APK文件");
		        return;
		    }
		    if(type != "apk"){
  				openTips("请上传APK格式文件");
  				return;
  			}
		    
		    uploadUrl.value = apkFile;
		}
		if(versionType == 2){
		    if(iosUrl == null || iosUrl == ""){
		    	openTips("请填写IOS下载路径");
		        return;
		    }
		    
		    uploadUrl.value = "http://" + iosUrl;
		}
		if(updateDescribe == null || updateDescribe == ""){
			openTips("请填写更新说明");
			return;
		}
		
		$("#addForm").submit();
		
	}
	function deleteApp(id, type){
		if(id == null || id == "" || type == null || type == "" || type == 0){
			openTips("参数错误");
			return;
		}
		
		document.getElementById("deleteId").value = id;
		document.getElementById("delete_versionType").value = type;
	}
	function deleteHandle(){
		var id = document.getElementById("deleteId").value;
		var type = document.getElementById("delete_versionType").value;
		if(id == null || id == "" || type == null || type == "" || type == 0){
			openTips("参数错误");
			return;
		}
		
		$("#deleteForm").submit();
	}
	//上一页
	function prePage(){
	    var pageNumber = ${pageNumber};
	    if(pageNumber > 0){
	        pageNumber = pageNumber - 1;
	        
	        document.getElementById("pageNumber").value = pageNumber;
	        $('#queryForm').submit();
	    }else{
	    	openTips("当前已是第一页");
	    }
	}
	//下一页
	function nextPage(){
		var pageNumber = ${pageNumber};
		var totalPageNumber = ${totalPageNumber};
		if(pageNumber < totalPageNumber){
		    pageNumber++;
		    document.getElementById("pageNumber").value = pageNumber;
		    $('#queryForm').submit();
		}else{
			openTips("当前已是最后一页");
		}
	}
	//跳转到指定页
	function goPage(){
	    var goPageNumber = document.getElementById('goPageNumber').value;
	    var totalPageNumber = ${totalPageNumber};
	    var pageNumber = ${pageNumber};
	    
	    if(goPageNumber == null || goPageNumber == "" || goPageNumber <= 0){
	        goPageNumber = 1;
	    }
	    if(goPageNumber > totalPageNumber){
	       //openTips("指定跳转页不存在")
	       goPageNumber = ++totalPageNumber;
	    }
	    //显示具体跳转到了哪一页
	    document.getElementById('goPageNumber').value = goPageNumber;
	    pageNumber = goPageNumber - 1;
	    document.getElementById('pageNumber').value = pageNumber;
	    
	    $('#queryForm').submit();
	}
</script>
</body>
</html>
