<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/accountRole_add.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    
    <title>修改角色</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">修改角色</div>

        <div>
            <ul class="addAffiche">
                <li class="infoTitle"><img src="<%=path %>/resource/image/back/location_img.png">修改角色</li>
                
                <li class="addRole">
	                <form id="modifyForm" action="<%=path%>/backerWeb/backerRole/roleModify.htm" method="post">
	                	<input type="hidden" id="modify_roleId" name="modify_roleId" value="${role.roleId}">
	                	<input type="hidden" id="modify_powerIdList" name="modify_powerIdList">
	                    <label class="caption">角色名称<span>*</span>：</label>
	                    <input type="text" class="txt" placeholder="请输入角色名称，2-16个字" maxlength="16" autocomplete="off" 
	                    	id="modify_roleName" name="modify_roleName" value="${role.roleName}"/>
	                </form>
                </li>
                
                <li class="addRole">
                	<label class="caption">角色权限<span>*</span>：</label>
                    <p class="role"> 
                        <span class="limits">业务模块</span>
                        <span class="child">业务功能</span>
                    </p>
                    
                     <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="111000" value="111000" onchange="onChangeHandle(111000, 1, 111006)" />首页广告</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="111006" value="111006" onchange="onChangeHandle(111006, 2, 111006)" />查询</label>
                            <label class="button"><input type="checkbox" id="111001" value="111001" onchange="onChangeHandle(111001, 3, 111006)" />新增广告</label>
                            <label class="button"><input type="checkbox" id="111002" value="111002" onchange="onChangeHandle(111002, 3, 111006)" />上移</label>
                            <label class="button"><input type="checkbox" id="111003" value="111003" onchange="onChangeHandle(111003, 3, 111006)" />下移</label>
                            <label class="button"><input type="checkbox" id="111004" value="111004" onchange="onChangeHandle(111004, 3, 111006)" />删除</label>
                            <label class="button"><input type="checkbox" id="111005" value="111005" onchange="onChangeHandle(111005, 3, 111006)" />修改</label>
                        </span>
                    </p>
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="121000" value="121000" onchange="onChangeHandle(121000, 1, 121001)" />用户账号</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="121001" value="121001" onchange="onChangeHandle(121001, 2, 121001)" />查询用户</label>
                            <label class="button"><input type="checkbox" id="121002" value="121002" onchange="onChangeHandle(121002, 3, 121001)" />增加金额</label>
                            <label class="button"><input type="checkbox" id="121003" value="121003" onchange="onChangeHandle(121003, 3, 121001)" />减少金额</label>
                            <label class="button"><input type="checkbox" id="121004" value="121004" onchange="onChangeHandle(121004, 3, 121001)" />调整交易信息</label>
                            <label class="button"><input type="checkbox" id="121005" value="121005" onchange="onChangeHandle(121005, 3, 121001)" />实名认证</label>
                            <label class="button"><input type="checkbox" id="121006" value="121006" onchange="onChangeHandle(121006, 3, 121001)" />实名认证审核通过</label>
                            <label class="button"><input type="checkbox" id="121007" value="121007" onchange="onChangeHandle(121007, 3, 121001)" />实名认证审核拒绝</label>
                        	<label class="button"><input type="checkbox" id="121008" value="121008" onchange="onChangeHandle(121008, 3, 121001)" />用户账号导出数据</label>
                        	<label class="button"><input type="checkbox" id="121009" value="121009" onchange="onChangeHandle(121009, 3, 121001)" />调整身份信息</label>
                        </span>
                    </p>
                    
                     <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="131000" value="131000" onchange="onChangeHandle(131000, 1, 131001)" />订单管理</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="131001" value="131001" onchange="onChangeHandle(131001, 2, 131001)" />查询</label>
                            <label class="button"><input type="checkbox" id="131002" value="131002" onchange="onChangeHandle(131002, 3, 131001)" />导出数据</label>
                        </span>
                    </p>
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="141000" value="141000" onchange="onChangeHandle(141000, 1, 141001)" />提现管理</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="141001" value="141001" onchange="onChangeHandle(141001, 2, 141001)" />查询</label>
                            <label class="button"><input type="checkbox" id="141002" value="141002" onchange="onChangeHandle(141002, 3, 141001)" />导出数据</label>
                            <label class="button"><input type="checkbox" id="141003" value="141003" onchange="onChangeHandle(141003, 3, 141001)" />打款</label>
                        </span>
                    </p>
                    
                     <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="151000" value="151000" onchange="onChangeHandle(151000, 1, 151001)" />邀请码管理</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="151001" value="151001" onchange="onChangeHandle(151001, 2, 151001)" />查询</label>
                            <label class="button"><input type="checkbox" id="151002" value="151002" onchange="onChangeHandle(151002, 3, 151001)" />导出数据</label>
                        </span>
                    </p>
                    
                    <p class="role">
                    	<label class="limitsChoose"><input type="checkbox" id="161000" value="161000" onchange="onChangeHandle(161000, 1, 161001)" />APP版本管理</label>
                        <span class="childChoose">
                        	<label class="button"><input type="checkbox" id="161001" value="161001" onchange="onChangeHandle(161001, 2, 161001)" />查询</label>
                            <label class="button"><input type="checkbox" id="161002" value="161002" onchange="onChangeHandle(161002, 3, 161001)" />新增版本</label>
                            <label class="button"><input type="checkbox" id="161003" value="161003" onchange="onChangeHandle(161003, 3, 161003)" />删除</label>
                        </span>
                    </p>
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="171000" value="171000" onchange="onChangeHandle(171000, 1, 171001)" />角色管理</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="171001" value="171001" onchange="onChangeHandle(171001, 2, 171001)" />查询</label>
                            <label class="button"><input type="checkbox" id="171002" value="171002" onchange="onChangeHandle(171002, 3, 171001)" />新增角色</label>
                            <label class="button"><input type="checkbox" id="171003" value="171003" onchange="onChangeHandle(171003, 3, 171001)" />修改角色</label>
                            <label class="button"><input type="checkbox" id="171004" value="171004" onchange="onChangeHandle(171004, 3, 171001)" />删除角色</label>
                        </span>
                    </p>
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="171100" value="171100" onchange="onChangeHandle(171100, 1, 171101)" />管理员帐号</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="171101" value="171101" onchange="onChangeHandle(171101, 2, 171101)" />查询</label>
                            <label class="button"><input type="checkbox" id="171102" value="171102" onchange="onChangeHandle(171102, 3, 171101)" />新增管理员</label>
                            <label class="button"><input type="checkbox" id="171103" value="171103" onchange="onChangeHandle(171103, 3, 171101)" />修改角色</label>
                            <label class="button"><input type="checkbox" id="171104" value="171104" onchange="onChangeHandle(171104, 3, 171101)" />删除</label>
                            <label class="button"><input type="checkbox" id="171105" value="171105" onchange="onChangeHandle(171105, 3, 171101)" />重置密码</label>
                        </span>
                    </p>
                    
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="181000" value="181000" onchange="onChangeHandle(181000, 1, 181001)" />管理员登录记录</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="181001" value="181001" onchange="onChangeHandle(181001, 2, 181001)" />查询</label>
                        </span>
                    </p>
                    
                    <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="181100" value="181100" onchange="onChangeHandle(181100, 1, 181101)" />用户钱包操作记录</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="181101" value="181101" onchange="onChangeHandle(181101, 2, 181101)" />查询</label>
                            <label class="button"><input type="checkbox" id="181102" value="181102" onchange="onChangeHandle(181102, 2, 181101)" />导出数据</label>
                        </span>
                    </p>
                    
                     <p class="role">
                        <label class="limitsChoose"><input type="checkbox" id="181200" value="181200" onchange="onChangeHandle(181200, 1, 181201)" />发放邀请码记录</label>
                        <span class="childChoose">
                            <label class="button"><input type="checkbox" id="181201" value="181201" onchange="onChangeHandle(181201, 2, 181201)" />查询</label>
                        </span>
                    </p>
                    
                </li>
            </ul>
            
            <p class="bottom">
                <input type="text" value="取&nbsp;消" onfocus="this.blur()" class="cancel" onclick="cancelHandle()" />
                <input type="text" value="确&nbsp;定" onfocus="this.blur()" class="confirm" onclick="sureHandle()" />
            </p>
            
        </div>
    </div>
</div>
</body>


<script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>

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
		
		showPower();
	}
	
	function showPower()
    {
    	 var powerList = ${powerList} ;
    	 for(var i=0; i<powerList.length; i++)
    	 {
    	 	var powerId = powerList[i];
    	 	document.getElementById(powerId).checked = true;
    	 }
    }
	
	function cancelHandle()
  	{
  		window.location.href="<%=path%>/backerWeb/backerRole/show.htm";
  	}
  	
  	function sureHandle()
  	{
  		var powerIdList = "";
  		var inputList = document.getElementsByTagName("input");
  		for (var i = 0; i < inputList.length; i++)
  		{
  			if (inputList[i].type == "checkbox")
  			{
  				var itemInput = inputList[i];
  				if(itemInput.checked)
  				{
  					powerIdList += "," + itemInput.value;
  				}
  			}
 		}
 		
 		var modify_roleName = document.getElementById("modify_roleName").value;
  		
  		if(modify_roleName == null || modify_roleName == "" || modify_roleName.length<2 || modify_roleName.length>16)
  		{
  			openTips("请输入角色名称，要求2到16位");
  			return;
  		}
  		if(powerIdList == null || powerIdList == "")
  		{
  			openTips("请选择角色权限");
  			return;
  		}
  		
  		document.getElementById("modify_powerIdList").value = powerIdList;
  		$("#modifyForm").submit();
  	}
  	
  	function onChangeHandle(powerId, markType, tePowerId)
  	{
  		var selected = false;
  		var curCheckBox = document.getElementById(powerId);
  		if(curCheckBox.checked)
  		{
  			selected = true;
  		}
  		
  		if(markType == 1)
  		{
 			var inputList = document.getElementsByTagName("input");
	  		for (var i = 0; i < inputList.length; i++)
	  		{
	  			if (inputList[i].type == "checkbox")
	  			{
	  				var itemInput = inputList[i];
	  				var inputValue = itemInput.value;
	  				
	  				var tempValue = parseInt(inputValue/100)*100;
	  				if(tempValue == powerId)
	  				{
	  					if(selected)
	  					{
	  						itemInput.checked = true;
	  					}else{
	  						itemInput.checked = false;
	  					}
	  				}
	  			}
  			}
  		}
  		else if(markType == 2)
  		{
  			if(selected)
  			{
  				var inputList = document.getElementsByTagName("input");
		  		for (var i = 0; i < inputList.length; i++)
		  		{
		  			if (inputList[i].type == "checkbox")
		  			{
		  				var itemInput = inputList[i];
		  				var inputValue = itemInput.value;
		  				
		  				var tempValue = parseInt(powerId/100)*100;
		  				if(tempValue == inputValue)
		  				{
	  						itemInput.checked = true;
		  				}
		  			}
	  			}
  			}else{
  				var inputList = document.getElementsByTagName("input");
		  		for (var i = 0; i < inputList.length; i++)
		  		{
		  			if (inputList[i].type == "checkbox")
		  			{
		  				var itemInput = inputList[i];
		  				var inputValue = itemInput.value;
		  				
		  				var tempValue = parseInt(inputValue/100)*100;
		  				var tempValue2 = parseInt(powerId/100)*100;
		  				if(tempValue == tempValue2)
		  				{
	  						itemInput.checked = false;
		  				}
		  			}
	  			}
  			}
  		}
  		else if(markType == 3)
  		{
  			if(selected)
  			{
  				var inputList = document.getElementsByTagName("input");
		  		for (var i = 0; i < inputList.length; i++)
		  		{
		  			if (inputList[i].type == "checkbox")
		  			{
		  				var itemInput = inputList[i];
		  				var inputValue = itemInput.value;
		  				
		  				var tempValue = parseInt(powerId/100)*100;
		  				if(tempValue == inputValue || inputValue == tePowerId)
		  				{
	  						itemInput.checked = true;
		  				}
		  			}
	  			}
	  		}
  		}
  		
  	}
</script>

</html>