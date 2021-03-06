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
    <link rel="icon" href="<%=path%>/resource/image/back/icon.ico" type="image/x-ico">

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/userAccount.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/need/laydate.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/skins/dahong/laydate.css">
    <title>用户账号</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">用户账号</div>
		
		<form id="queryForm" action="<%=path %>/backerWeb/backerUserAccount/show.htm" method="post">
	        <div class="top">
	            <p class="condition">用户账号：<input name="userAccount" value="${userAccount }" type="text" class="query" placeholder="请输入用户账号" 
	            							maxlength="16" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">推荐人账号：<input name="inviteUserAccount" value="${inviteUserAccount }" type="text" class="query" placeholder="请输入推荐人账号" 
	            							maxlength="16" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">身份账号：<input name="idCard" value="${idCard }" type="text" class="query" placeholder="请输入身份证账号" 
	            							maxlength="20" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">结算账户：<input name="bankCard" value="${bankCard }" type="text" class="query" placeholder="请输入结算账户" 
	            							maxlength="20" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">
	                注册时间：
	                从<input placeholder="请选择起始时间" name="startTime" value="${startTime }" class="startTime" readonly="readonly" onClick="laydate({istime: true,format:'YYYY-MM-DD hh:mm:ss'})">
	                到<input placeholder="请选择结束时间" name="endTime" value="${endTime }" class="endTime" readonly="readonly" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
	            </p>
	            <p class="condition">认证状态：
	                <select class="choose" name="authenStatus">
	                	<c:if test="${authenStatus == -1 }">
	                		<option value="-1" selected="selected">全部</option>
	                		<option value="0">待认证</option>
	                		<option value="1">认证通过</option>
	                		<option value="2">认证失败</option>
	                	</c:if>
	                	
	                	<c:if test="${authenStatus == 0 }">
	                		<option value="-1">全部</option>
	                		<option value="0" selected="selected">待认证</option>
	                		<option value="1">认证通过</option>
	                		<option value="2">认证失败</option>
	                	</c:if>
	                	
	                	<c:if test="${authenStatus == 1 }">
	                		<option value="-1">全部</option>
	                		<option value="0">待认证</option>
	                		<option value="1" selected="selected">认证通过</option>
	                		<option value="2">认证失败</option>
	                	</c:if>
	                	
	                	<c:if test="${authenStatus == 2 }">
	                		<option value="-1">全部</option>
	                		<option value="0">待认证</option>
	                		<option value="1">认证通过</option>
	                		<option value="2" selected="selected">认证失败</option>
	                	</c:if>
	                    
	                </select>
	            </p>
	            
	            <p class="condition">会员身份：
	                <select class="choose" name="vipIdentity">
	                	<c:if test="${vipIdentity == -1}">
	                		<option value="-1" selected="selected">全部</option>
		                    <option value="0">普通会员</option>
		                	<option value="1">VIP会员</option>
	                	</c:if>
	                	
	                	<c:if test="${vipIdentity == 0}">
	                		<option value="-1">全部</option>
		                    <option value="0" selected="selected">普通会员</option>
		                	<option value="1">VIP会员</option>
	                	</c:if>
	                	
	                	<c:if test="${vipIdentity == 1}">
	                		<option value="-1">全部</option>
		                    <option value="0">普通会员</option>
		                	<option value="1" selected="selected">VIP会员</option>
	                	</c:if>
	                </select>
	            </p>
	            
				<input type="hidden" id="query_pageNumber" name="pageNumber" value="${pageNumber}">
				<c:forEach items="${backer_rolePowerList}" var="powerId">
					<c:if test="${powerId == 121001 }">
			            <input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryHandle()"/>
					</c:if>
					<c:if test="${powerId == 121008 }">
		            	<input type="text" value="导&nbsp;出" class="educe" onfocus="this.blur()" onclick="exportData()" />
		            </c:if>
				</c:forEach>
	        </div>
		</form>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr class="tableTitle">
                <td class="account">用户账号信息</td>
                <td class="money">账户余额</td>
                <td class="contact">联系信息</td>
                <td class="authentic">认证信息</td>
                <td class="trade">交易信息</td>
                <td class="store">店铺信息</td>
                <td class="personal">身份信息</td>
                <td class="operate">操作</td>
            </tr>
            <c:forEach items="${userList }" var="user">
	            <tr class="tableInfo">
	                <td class="account">
	                    <p>账号：<span>${user.userAccount }</span></p>
	                    <p>推荐人：<span>${user.inviteUserAccount }</span></p>
	                    <p>注册：<span><fmt:formatDate value="${user.addTime }" pattern="yyyy-MM-dd HH:mm:ss" /></span></p>
	                </td>
	                <td class="money">
	                    <p>钱包：<span><fmt:formatNumber type="number" value="${user.walletAmount }" maxFractionDigits="2" />元</span></p>
	                    <p>红包：<span><fmt:formatNumber type="number" value="${user.redPacketAmount }" maxFractionDigits="2" />元</span></p>
	                </td>
	                <td class="contact">
	                    <p>联系人：<span>${user.contactsName }</span></p>
	                    <p>电话：<span>${user.userAccount }</span></p>
	                    <p>地址：<span>${user.address }</span></p>
	                </td>
	                <td class="authentic">
	                    <p>身份证号：<span>${user.idCard }</span></p>
	                    <p>结算账户：<span>${user.bankCard }</span></p>
	                </td>
	                <td class="trade">
	                    <p>单笔限额：<span><fmt:formatNumber type="number" value="${user.singleLimitAmount }" maxFractionDigits="2" />元</span></p>
	                    <p>收款费率：<span><fmt:formatNumber type="number" value="${user.receiptRate }" maxFractionDigits="5"/></span></p>
	                </td>
	                <td class="store">
	                    <p>名称：<span>${user.storeName }</span></p>
	                    <p>认证状态：<span>
	                    			<c:if test="${user.authenStatus == 0 }">
	                    				待认证
	                    			</c:if>
	                    			<c:if test="${user.authenStatus == 1 }">
	                    				认证通过
	                    			</c:if>
	                    			<c:if test="${user.authenStatus == 2 }">
	                    				认证失败
	                    			</c:if>
	                    		 </span><fmt:formatDate value="${user.vipOutTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
	                </td>
	                <td class="personal">
	                    <p>身份信息：<span>
	                    			<c:if test="${user.vipIdentity == 0 }">
	                    				普通会员
	                    			</c:if>
	                    			<c:if test="${user.vipIdentity == 1 }">
	                    				VIP会员
	                    			</c:if>
	                    			</span></p>
	                    <p>到期时间：<span></span></p>
	                </td>
	                <td class="operate">
	                	<c:forEach items="${backer_rolePowerList}" var="powerId">
		                	<c:if test="${powerId == 121002 }">
			                    <input type="text" value="增加钱包金额" class="addMoney" onfocus="this.blur()" onclick="openAddWalletAmount('${user.userId}','${user.userAccount }')"/>
			                </c:if>
			            </c:forEach>
			            <c:forEach items="${backer_rolePowerList}" var="powerId">
			                <c:if test="${powerId == 121003 }">
			                    <input type="text" value="减少钱包金额" class="minus" onfocus="this.blur()" onclick="openReduceWalletAmount('${user.userId}','${user.userAccount }', '${user.walletAmount }')"/>
			                </c:if>   
			            </c:forEach>
			            <c:forEach items="${backer_rolePowerList}" var="powerId">
			                <c:if test="${powerId == 121004 }">
			                    <input type="text" value="调整交易信息" class="change" onfocus="this.blur()" onclick="openAdjustDealInfo('${user.userId}','${user.userAccount }')"/>
			                </c:if> 
			            </c:forEach>
			           	<c:forEach items="${backer_rolePowerList}" var="powerId">
			                <c:if test="${powerId == 121005 && user.authenStatus == 0}">
			                    <a style="cursor: pointer;" onclick="confirm('${user.userId}')" class="real">实名认证审核</a>
			                </c:if>
	                	</c:forEach>
	                	<c:forEach items="${backer_rolePowerList}" var="powerId">
			                <c:if test="${powerId == 121009}">
			                    <input type="text" value="调整身份信息" class="changeInfo" onfocus="this.blur()" onclick="openModifyVipIdentity('${user.userId}','${user.userAccount }', '${user.vipIdentity }')"/>
			                </c:if>
	                	</c:forEach>
	                </td>
	            </tr>
            </c:forEach>
        </table>

         <div class="changePage">
            <p class="total">共${totalNumber }条</p>
            <p class="jump">
            	<c:if test="${pageNumber <= totalPageNumber }">
	                <input type="text" id="goPageNumber" value="${pageNumber + 1}" onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            	</c:if>
            	<c:if test="${pageNumber > totalPageNumber }">
	                <input type="text" id="goPageNumber" value="${totalPageNumber + 1}" onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            	</c:if>
                <input type="text" value="跳&nbsp;转" class="jumpButton" onfocus="this.blur()" onclick="goPage()"/>
            </p>
            <p class="page">
                <input type="text" value="<上一页" onfocus="this.blur()" onclick="prePage()"/>
                <span class="pageNumber">
                	<span>${pageNumber + 1 }</span>/<span>${totalPageNumber + 1}</span>
                </span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="nextPage()"/>
            </p>
        </div>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
    	<form id="addWalletAmountForm" action="<%=path %>/backerWeb/backerUserAccount/addWalletAmount.htm" method="post">
	        <div class="addMoney_pop">
	            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">增加钱包金额</span>
	            <p class="entry_tips">
	                <label class="sign">账号：</label>
	                <span id="addWalletAmount_userAccount_span"></span>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">增加金额<span>*</span>：</label>
	                <input id="addWalletAmount_addAmount" name="addAmount" type="text" class="box" 
	                	maxlength="6" onkeyup="value=value.replace(/[^\d\.]/g,'')" onkeyup="value=value.replace(/[^\d\.]/g,'')" placeholder="请输入要增加的金额，单位：元" />
	            </p>
	            <p class="entry_tips">
	                <label class="sign">操作说明<span>*</span>：</label>
	                <textarea id="addWalletAmount_remarks" name="remarks" class="txt" placeholder="请输入操作说明，最多50个字" maxlength="50"></textarea>
	            </p>
	
	            <div class="Button">
	                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
	                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="addWalletAmount()" />
	            </div>
	        </div>
	        
	        <input id="addWalletAmount_userId" name="userId" type="hidden"  />
    	</form>
    	
		<form id="reduceWalletAmountForm" action="<%=path %>/backerWeb/backerUserAccount/reduceWalletAmount.htm" method="post">
	        <div class="minus_pop">
	            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">减少钱包金额</span>
	            <p class="entry_tips">
	                <label class="sign">账号：</label>
	                <span id="reduceWalletAmount_userAccount_span">ASDFGHJKLASDFGHJ</span>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">减少金额<span>*</span>：</label>
	                <input id="reduceWalletAmount_reduceAmount" name="reduceAmount" type="text" class="box" 
	                	maxlength="6" onkeyup="value=value.replace(/[^\d\.]/g,'')" onkeyup="value=value.replace(/[^\d\.]/g,'')" placeholder="请输入要减少的金额，单位：元" />
	            </p>
	            <p class="entry_tips">
	                <label class="sign">操作说明<span>*</span>：</label>
	                <textarea id="reduceWalletAmount_remarks" name="remarks" class="txt" placeholder="请输入操作说明，最多50个字" maxlength="50"></textarea>
	            </p>
	
	            <div class="Button">
	                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
	                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="reduceWalletAmount()" />
	            </div>
	        </div>
	        
	        <input id="reduceWalletAmount_userId" name="userId" type="hidden"  />
		</form>
		
		<form id="adjustDealInfoForm" action="<%=path %>/backerWeb/backerUserAccount/adjustDealInfo.htm" method="post">
	        <div class="change_pop">
	            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">调整交易信息</span>
	            <p class="entry_tips">
	                <label class="sign">账号：</label>
	                <span id="adjustDealInfo_userAccount" >ASDFGHJKLASDFGHJ</span>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">单笔限额<span>*</span>：</label>
	                <input id="adjustDealInfo_singleLimitAmount" name="singleLimitAmount" type="text" class="box" placeholder="请输入限额额度，单位：元" 
	                		onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="6"/>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">收款费率<span>*</span>：</label>
	                <input id="adjustDealInfo_receiptRate" name="receiptRate" type="text" class="box" placeholder="请输入收款费率，如：0.0035" 
	                	onkeyup="value=value.replace(/[^\d\.]/g,'')" onkeyup="value=value.replace(/[^\d\.]/g,'')" maxlength="7"/>
	            </p>
	
	            <div class="Button">
	                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
	                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()"  onclick="adjustDealInfo()"/>
	            </div>
	        </div>
	        <input id="adjustDealInfo_userId" name="userId" type="hidden" />
        </form>
        
        <form id="modifyVipIdentityForm" action="<%=path %>/backerWeb/backerUserAccount/modifyVipIdentity.htm" method="post">
        
	        <div class="changeInfo_pop">
	            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">调整身份信息</span>
	            <p class="entry_tips">
	                <label class="sign">账号：</label>
	                <span id="modifyVipIdentity_userAccount"></span>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">用户身份<span>*</span>：</label>
	                <select class="choice" name="modifyVipIdentity_vipIdentity" id="modifyVipIdentity_vipIdentity">
	                    <option value="0">普通</option>
	                    <option value="1">VIP会员</option>
	                </select>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">到期时间<span>*</span>：</label>
	                <input placeholder="请选择到期时间，若选择会员传当前时间即可" id="modifyVipIdentify_vipOutTime" name="vipOutTime" readonly="readonly" class="overTime" onClick="laydate({istime: true, format:'YYYY-MM-DD hh:mm:ss'})">
	            </p>
	
	            <div class="Button">
	                <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
	                <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()"  onclick="modifyVipIdentity()"/>
	            </div>
	        </div>
	        <input id="modifyVipIdentity_userId" name="userId" type="hidden" />
        </form>
        
    </div>
</div>

<form id="confirmForm" action="<%=path %>/backerWeb/backerUserAccount/openRealNameConfirm.htm" method="post">
	<input id="confirmForm_userId" name="userId" type="hidden"/>
</form>

<script type="text/javascript" src="<%=path %>/resource/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/simpleTips.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/laydate.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/loadBackPage.js"></script>
<script type="text/javascript">
    $(function(){
        !function(){
            laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
        }();
    });

    var popObj;
    $(function(){
        $(".addMoney").click(function(){
            $(".mask").css("display","block");
            $(".addMoney_pop").css("display","block");
            popObj = ".addMoney_pop"
        });
        $(".minus").click(function(){
            $(".mask").css("display","block");
            $(".minus_pop").css("display","block");
            popObj = ".minus_pop"
        });
        $(".change").click(function(){
            $(".mask").css("display","block");
            $(".change_pop").css("display","block");
            popObj = ".change_pop"
        });
        $(".changeInfo").click(function(){
            $(".mask").css("display","block");
            $(".changeInfo_pop").css("display","block");
            popObj = ".changeInfo_pop"
        });
        $(".cancel").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
        $(".yes").click(function(){
            $(".mask").hide();
            $(popObj).hide();
        });
    });

    function openTip()
    {
        openTips("阿萨德芳");
    }
</script>

<script type="text/javascript">
	
	var globalWalletAmount;
	
	window.onload = function(){
		var code = '${code}';
		var message = '${message}';
		if(code != 1){
			openTips(message);
		}
	}
	
	function queryHandle(){
	    //点击查询时传给控制器的页面值应人为设置为0，不直接传0是因为还有上一页下一页
		document.getElementById('query_pageNumber').value = "0";
		$('#queryForm').submit(); 
	}
	
	//上一页
	function prePage(){
	    var pageNumber = '${pageNumber}';
	    //var backerAccount = document.getElementById('backerAccount').value;
	    if(pageNumber > 0){
	        pageNumber--;
	        
	        document.getElementById("query_pageNumber").value = pageNumber;
	        $('#queryForm').submit();
	    }else{
	    	openTips("当前已是第一页");
	    }
	}
	//下一页
	function nextPage(){
		var pageNumber = '${pageNumber}';
		var totalPageNumber = '${totalPageNumber}';
		if(pageNumber < totalPageNumber){
		    pageNumber++;
		    document.getElementById("query_pageNumber").value = pageNumber;
		    $('#queryForm').submit();
		}else{
			openTips("当前已是最后一页");
		}
	}
	//跳转到指定页
	function goPage(){
	    var goPageNumber = document.getElementById('goPageNumber').value;
	    var totalPageNumber = '${totalPageNumber}';
	    var pageNumber = '${pageNumber}';
	    
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
	    document.getElementById('query_pageNumber').value = pageNumber;
	    $('#queryForm').submit();
	}
	
	function openAddWalletAmount(userId, userAccount){
		if(userId == null || userId == "" || userId == '' || 
			userAccount == null || userAccount == "" || userAccount == ''){
			openTips("参数错误！");
			return;
		}
		
		document.getElementById("addWalletAmount_userId").value = userId;
		document.getElementById("addWalletAmount_userAccount_span").innerHTML = userAccount;
	}
	
	function addWalletAmount(){
		var addAmount = document.getElementById("addWalletAmount_addAmount").value;
		var remarks = document.getElementById("addWalletAmount_remarks").value;
		if(addAmount == null || addAmount == "" || addAmount == ''){
			openTips("请输入增加金额！");
			return;
		}
		if(remarks == null || remarks == "" || remarks == ''){
			openTips("请输入操作说明！");
			return;
		}
		
		if(addAmount <= 0){
			openTips("输入不合法！");
			return;
		}
		
		$("#addWalletAmountForm").submit();
	}
	
	function openReduceWalletAmount(userId, userAccount, walletAmount){
		if(userId == null || userId == "" || userId == '' || 
			userAccount == null || userAccount == "" || userAccount == ''
			|| walletAmount == null || walletAmount == "" ||　walletAmount == ''){
			
			openTips("参数错误！");
			return;
		}
		
		globalWalletAmount = walletAmount;
		document.getElementById("reduceWalletAmount_userId").value = userId;
		document.getElementById("reduceWalletAmount_userAccount_span").innerHTML = userAccount;
	}
	
	function reduceWalletAmount(){
		var reduceAmount = document.getElementById("reduceWalletAmount_reduceAmount").value;
		var remarks = document.getElementById("reduceWalletAmount_remarks").value;
		if(reduceAmount == null || reduceAmount == "" || reduceAmount == ''){
			openTips("请输入减少金额！");
			return;
		}
		if(remarks == null || remarks == "" || remarks == ''){
			openTips("请输入操作说明！");
			return;
		}
		
		if(reduceAmount > parseFloat(globalWalletAmount)){
			openTips("减少的金额超过原本所有的金额");
			return;
		}
		
		if(reduceAmount <= 0){
			openTips("输入不合法！");
			return;
		}
		
		$("#reduceWalletAmountForm").submit();
	}
	
	function openAdjustDealInfo(userId, userAccount){
		if(userId == null || userId == "" || userId == '' || 
			userAccount == null || userAccount == "" || userAccount == ''){
			openTips("参数错误！");
			return;
		}
	
		document.getElementById("adjustDealInfo_userAccount").innerHTML = userAccount;
		document.getElementById("adjustDealInfo_userId").value = userId;
		
	}
	
	//调整交易信息
	function adjustDealInfo(){
		var singleLimitAmount = document.getElementById("adjustDealInfo_singleLimitAmount").value;
		var receiptRate = document.getElementById("adjustDealInfo_receiptRate").value;
		
		if(singleLimitAmount == null || singleLimitAmount == "" || singleLimitAmount == ''){
			openTips("请输入单笔最大限额！");
			return;
		}
		
		if(receiptRate == null || receiptRate == "" || receiptRate == ''){
			openTips("请输入收款费率！");
			return;
		}
		
		if(singleLimitAmount <= 0 || receiptRate <= 0){
			openTips("输入不合法！");
			return;
		}
		
		$("#adjustDealInfoForm").submit();
	}
	
	function openModifyVipIdentity(userId, userAccount, vipIdentity){
		if(userId == null || userId == "" || userId == '' || 
			userAccount == null || userAccount == "" || userAccount == ''
			|| vipIdentity == null || vipIdentity == "" || vipIdentity == ''){
			openTips("参数错误！");
			return;
		}
		
		$("#modifyVipIdentity_vipIdentity").val(vipIdentity)[0].selected = true ;
		document.getElementById("modifyVipIdentity_userAccount").innerHTML = userAccount;
		document.getElementById("modifyVipIdentity_userId").value = userId;
	}
	
	//修改身份信息
	function modifyVipIdentity(){
		var vipOutTime = document.getElementById("modifyVipIdentify_vipOutTime").value;
		if(vipOutTime == null || vipOutTime == "" || vipOutTime == ''){
			openTips("请选择过期时间！");
			return;
		}
		
		$("#modifyVipIdentityForm").submit();
	}
	
	//实名认证
	function confirm(userId){
		
		if(userId == null || userId == "" || userId == ''){
			openTips("参数错误");
			return;
		}
		
		document.getElementById("confirmForm_userId").value = userId;
		$("#confirmForm").submit();
	}
	var flag = true;
	//导出数据
	function exportData(){
        if(flag == false){
			return;
		}        
        
        flag = false;
        
        var userAccount = '${userAccount}';
        var inviteUserAccount = '${inviteUserAccount}';
        var idCard = '${idCard}';
        var bankCard = '${bankCard}';
        var authenStatus = '${authenStatus}';
        var startTime = '${startTime}';
        var endTime = '${endTime}';
        
        $.ajax({
            url: '<%=path%>' + "/backerWeb/backerUserAccount/exportData", //方法路径URL
            data:{
                userAccount : userAccount,
                inviteUserAccount : inviteUserAccount,
                idCard : idCard,
                bankCard : bankCard,
                authenStatus : authenStatus,
                startTime : startTime,
                endTime : endTime
            },//参数
            dataType: "json",
            type: 'POST',
            async: true, //默认异步调用 (false：同步)
            success: function (data) {
                if(data.code == 1){
                     window.location.href = '<%=path%>' + data.data.url;
                }else{
                    openTips(data.message);
                }
                flag = true;
                
            },
            error: function () {
                openTips("服务器异常");
                flag = true;
            }
        });
    }
	
	

</script>

</body>
</html>
