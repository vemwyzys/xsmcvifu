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
    <link rel="icon" href="<%=path %>/resource/image/back/icon.ico" type="image/x-ico">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/indexAd.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css" />

    <script type="text/javascript" src="<%=path %>/resource/js/resizeImage.js"></script>
    <title>首页广告</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">首页广告<p class="add"><img src="<%=path %>/resource/image/back/add.png" />新增广告</p></div>

        <table class="table" cellspacing="0" cellpadding="0">
            <tr class="tableTitle">
                <td class="caption">标题</td>
                <td class="pic">图片</td>
                <td class="location">位置</td>
                <td class="link">链接地址</td>
                <td class="operate">操作</td>
            </tr>
            
            <!--  顶部广告显示 -->
            <c:forEach items="${headerAdsList}" var="ads" varStatus="status">
           		<tr class="tableInfo">
            		<td class="caption">${ads.adsTitle}</td>
            		<td class="pic">
                    	<p class="goodsImg"><img src="${fileServiceRootUrl}${ads.adsImage}" onload="resizeImage(this)" /></p>
               		</td>
               		<td class="location">首页顶部</td>
                	<td class="link">${ads.adsLink}</td>
                	<td class="operate">
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111002 }">
                    			<c:if test="${!status.first}">
                    	    		<input type="text" value="上&nbsp;移" class="up" onfocus="this.blur()" onclick="upMove('${ads.adsId}')"/>	
                    	    	</c:if>
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111003 }">
                    	    	<c:if test="${!status.last}">
                    	    		<input type="text" value="下&nbsp;移" class="down" onfocus="this.blur()" onclick="downMove('${ads.adsId}')" />	
                    	    	</c:if>
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111005 }">
                    			<input type="text" value="修&nbsp;改" class="change" onfocus="this.blur()" onclick="modify('${ads.adsId}','${ads.adsTitle}','${ads.adsImage}','${ads.adsLink}','${ads.showPosition}')" />
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111004 }">
                    			<input type="text" value="删&nbsp;除" class="delete" onfocus="this.blur()" onclick="delete_ads('${ads.adsId}')" />
                    		</c:if>
                    	</c:forEach>
                	</td>
            	</tr>
            </c:forEach>
            
            <!--  底部广告显示 -->
            <c:forEach items="${bottomAdsList}" var="ads" varStatus="status">
            	<tr class="tableInfo">
                	<td class="caption">${ads.adsTitle}</td>
                	<td class="pic">
                    	<p class="goodsImg"><img src="${fileServiceRootUrl}${ads.adsImage}" onload="resizeImage(this)" /></p>
                	</td>
               		<td class="location">首页底部</td>
                	<td class="link">${ads.adsLink}</td>
                	<td class="operate">
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111002 }">
                    			<c:if test="${!status.first}">
                    	    		<input type="text" value="上&nbsp;移" class="up" onfocus="this.blur()" onclick="upMove('${ads.adsId}')"/>	
                    	    	</c:if>
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111003 }">
                    			<c:if test="${!status.last}">
                    	    		<input type="text" value="下&nbsp;移" class="down" onfocus="this.blur()" onclick="downMove('${ads.adsId}')" />	
                    	    	</c:if>
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111005 }">
                    			<input type="text" value="修&nbsp;改" class="change" onfocus="this.blur()" onclick="modify('${ads.adsId}','${ads.adsTitle}','${ads.adsImage}','${ads.adsLink}','${ads.showPosition}')" />
                    		</c:if>
                    	</c:forEach>
                    	<c:forEach items="${backer_rolePowerList}" var="power">
                    		<c:if test="${power == 111004 }">
                    			<input type="text" value="删&nbsp;除" class="delete" onfocus="this.blur()" onclick="delete_ads('${ads.adsId}')" />
                    		</c:if>
                    	</c:forEach>
                	</td>
            	</tr>
            </c:forEach>
        </table>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
        <div class="add_pop">
            <form id="addForm" action="<%=path %>/backerWeb/systemAdsManage/add.htm" method="post" enctype="multipart/form-data">
            	<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">新增广告</span>
            	<p class="entry_tips">
                	<label class="sign">标题<span>*</span>：</label>
                	<input type="text" class="box" placeholder="请输入标题，2-16个字符" maxlength="16" id="add_adsTitle" name="add_adsTitle"/>
            	</p>
            	<p class="entry_tips">
                	<label class="sign">图片<span>*</span>：</label>
                	<input type="file" class="file" id="changead_a2" name="add_file" onchange="document.getElementById('changead_t2').value = this.value;" accept="image/png,image/jpg,image/gif,image/jpeg,image/bmp" />
                	<input type="text" id="changead_t2" name="modify_adsImage" class="choose" placeholder="请选择图片" onfocus="this.blur()" />
                	<input type="text"  onclick="document.getElementById('changead_a2').click();"  value="选择图片" class="choose_button" onfocus="this.blur();" />
            	</p>
            	<p class="entry_tips">
                	<label class="sign">链接：</label>
                	<input type="text" class="box" placeholder="非必填，请输入链接地址" maxlength="500" id="add_adsLink" name="add_adsLink" />
            	</p>
            	<p class="entry_tips">
                	<label class="sign">位置<span>*</span>：</label>
                	<select class="choice" id="addSelect" name="addSelect">
                    	<option>请选择显示位置</option>
                    	<option value="0">首页顶部</option>
                    	<option value="1">首页底部</option>
                	</select>
            	</p>

            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()"  />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()"  onclick="addHandle()" />
            	</div>	
            </form>
        </div>

        <div class="change_pop">
        	<form id="modifyForm" action="<%=path %>/backerWeb/systemAdsManage/modify.htm" method="post">
        		<input type="hidden" id="modify_showPosition" name="modify_showPosition">
        		<input type="hidden" id="modify_adsId" name="modify_adsId">
        		<span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">修改广告</span>
            	<p class="entry_tips">
                	<label class="sign">标题<span>*</span>：</label>
                	<input type="text" class="box" placeholder="请输入标题，2-16个字符" maxlength="16" id="modify_adsTitle" name="modify_adsTitle" />
            	</p>
            	<p class="entry_tips">
                	<label class="sign">图片：</label>
                	<input type="file" class="file" id="changead_a1" name="change_file" onchange="document.getElementById('changead_t1').value = this.value;" accept="image/png,image/jpg,image/gif,image/jpeg,image/bmp"  />
                	<input type="text" id="changead_t1" name="modify_adsImage" class="choose" placeholder="图片不更改时请不要选择图片" onfocus="this.blur()" />
                	<input type="text"  onclick="document.getElementById('changead_a1').click();"  value="选择图片" class="choose_button" onfocus="this.blur();"  />
           		</p>
            	<p class="entry_tips">
                	<label class="sign">链接：</label>
                	<input type="text" class="box" placeholder="非必填，请输入链接地址" maxlength="500" id="modify_adsLink" name="modify_adsLink"/>
            	</p>
            	<p class="entry_tips">
                	<label class="sign">位置<span>*</span>：</label>
                	<select class="choice" id="modify_select" name="modify_select">
                    	<option>请选择显示位置</option>
                    	<option value="0">首页顶部</option>
                    	<option value="1">首页底部</option>
                	</select>
            	</p>

            	<div class="Button">
                	<input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" onclick="cancleHandle()" />
                	<input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="modifyHandle()" />
            	</div>	
        	</form>
        </div>

        <div class="delete_pop">
            <span class="rt"><img src="<%=path %>/resource/image/back/location_img.png">删除操作</span>
            <p class="mask_tips">确定删除该广告？</p>

            <div class="Button">
                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="deleteHandle()" />
            </div>
        </div>
    </div>
</div>

<form id="deleteForm" action="<%=path %>/backerWeb/systemAdsManage/delete.htm" method="post">
    <input type="hidden" id="delete_id" name="delete_id">
</form>
<form id="upMoveForm" action="<%=path %>/backerWeb/systemAdsManage/upMove.htm" method="post">
    <input type="hidden" id="upMove_id" name="upMove_id">
</form>
<form id="downMoveForm" action="<%=path %>/backerWeb/systemAdsManage/downMove.htm" method="post">
    <input type="hidden" id="downMove_id" name="downMove_id">
</form>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript">
    var popObj;
    $(function(){
        $(".add").click(function(){
            $(".mask").css("display","block");
            $(".add_pop").css("display","block");
            popObj = ".add_pop"
        });
        $(".change").click(function(){
            $(".mask").css("display","block");
            $(".change_pop").css("display","block");
            popObj = ".change_pop"
        });
        $(".delete").click(function(){
            $(".mask").css("display","block");
            $(".delete_pop").css("display","block");
            popObj = ".delete_pop"
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
	var message = '${message}';
	if(code != 1 && message != ''){
	    openTips(message);
	    return false;
	}
}
function addHandle(){
  var title = document.getElementById("add_adsTitle").value;
  var image_file = document.getElementById("changead_t2").value;
  var select = document.getElementById("addSelect");
  var type = image_file.substring(image_file.lastIndexOf(".")+1, image_file.lenght).toLowerCase();
  
  if(title == null || title == "" || title.length < 2){
  	openTips("广告标题非法， 2~40位");
  	return;
  }
  if(image_file == null || image_file == "" || image_file.lenght < 5){
  	openTips("请选择广告图片");
  	return;
  }
  if(select == null || select == ""){
  	openTips("请选择广告展示位置");
  	return;
  }
  if(select.value == null || select.value == "" || select.value == "请选择显示位置"){
  	openTips("请选择广告展示位置");
  	return;
  }
  if(type != "bmp" && type != "jpg" && type != "jpeg" && type != "png" ){
  	openTips("请上传图片格式文件");
  	return;
  }
  
  $("#addForm").submit();
}
function upMove(id){
	if(id == null || id == ""){
		openTips("参数错误！");
		return;
	}
	document.getElementById("upMove_id").value = id;
	$("#upMoveForm").submit();
}
function downMove(id){
	if(id == null || id == ""){
		openTips("参数错误！");
		return;
	}
	document.getElementById("downMove_id").value = id;
	$("#downMoveForm").submit();
}
function modify(id, title, image, link, position){
	if(id == null || id == ""){
	    openTips("参数错误");
	    return;
	}
	
	document.getElementById("modify_adsId").value = id;
	document.getElementById("modify_adsTitle").value = title;
	//document.getElementById("changead_t1").value = image;
	document.getElementById("modify_adsLink").value = link;
	document.getElementById("modify_showPosition").value = position;
	
	var select = document.getElementById("modify_select");
	if(select != null || select != ""){
		var index = parseInt(position) + 1;
		select[index].selected = true;
	}
}
function modifyHandle(){
	var id = document.getElementById("modify_adsId").value;
	var title = document.getElementById("modify_adsTitle").value;
	var link = document.getElementById("modify_adsLink").value;
	var position = document.getElementById("modify_showPosition");
	var image_file = document.getElementById("changead_a1").value;
	var type = image_file.substring(image_file.lastIndexOf(".")+1, image_file.lenght).toLowerCase();
	
	var select = document.getElementById("modify_select");
	position.value = select.value;
	if(id == null || id == ""){
		openTips("参数错误");
		return;
	}
	if(title == null || title == "" || title.length < 2){
		openTips("广告名非法，要求2~40位");
		return;
	}
	if(position == null || position == "" || position.value == "请选择显示位置"){
		openTips("请选择广告展示位置");
		return;
	}
	if(type != "bmp" && type != "jpg" && type != "jpeg" && type != "png" ){
  	openTips("请上传图片格式文件");
  	return;
    }
	
	$("#modifyForm").submit();
}
function delete_ads(id){
	if(id == null || id == ""){
		openTips("参数错误");
		return;
	}
	
	document.getElementById("delete_id").value = id;
}
function deleteHandle(){
	var id =  document.getElementById("delete_id").value;
	if(id == null || id == ""){
		openTips("参数错误");
		return;
	}
	
	$("#deleteForm").submit();
}
</script>
</body>
</html>
