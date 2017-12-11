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

    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/withdrawalRecord.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/simpleTips.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/back/public.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/need/laydate.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/resource/js/skins/dahong/laydate.css">

    <title>提现记录</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="title">提现记录</div>
		<form id="queryForm" action="<%=path %>/backerWeb/backerTakeCashRecord/show.htm" method="post">
	        <div class="top">
	            <p class="condition">订单号：<input id="orderNo" name="orderNo" value="${orderNo }"  type="text" class="query" placeholder="请输入订单号 " 
	            							maxlength="25" onkeyup="value=value.replace(/[^\d]/g,'')" onkeyup="value=value.replace(/[^\d]/g,'')"/></p>
	            <p class="condition">用户账号：<input id="userAccount" name="userAccount" value="${userAccount }" type="text" class="query" placeholder="请输入用户账号 " 
	            							maxlength="16" onkeyup="value=value.replace(/[\W]/g,'')" onblur="value=value.replace(/[\W]/g,'')"/></p>
	            <p class="condition">收款方式：
	                <select class="choose" name="takeCashWay">
	                	<c:if test="${takeCashWay == -1 }">
	                		<option value="-1" selected="selected">全部</option>
		                    <option value="1">支付宝</option>
		                    <option value="2">微信</option>
	                	</c:if>
	                	
	                	<c:if test="${takeCashWay == 1 }">
	                		<option value="-1">全部</option>
		                    <option value="1" selected="selected">支付宝</option>
		                    <option value="2">微信</option>
	                	</c:if>
	                	
	                	<c:if test="${takeCashWay == 2 }">
	                		<option value="-1">全部</option>
		                    <option value="1">支付宝</option>
		                    <option value="2" selected="selected">微信</option>
	                	</c:if>
	                    
	                </select>
	            </p>
	            <p class="condition">
	                申请时间：
	                从<input placeholder="请选择起始时间" id="startTime" name="startTime" value="${startTime }" readonly="readonly" class="startTime" onClick="laydate({istime: true,format:'YYYY-MM-DD hh:mm:ss'})">
	                到<input placeholder="请选择结束时间" id="endTime" name="endTime" value="${endTime }" readonly="readonly" class="endTime" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
	            </p>
	            <p class="condition">提现状态：
	                <select class="choose" name="takeCashStatus">
	                	<c:if test="${takeCashStatus == -1 }">
	                		<option value="-1" selected="selected">全部</option>
	                		<option value="0">待打款</option>
	                		<option value="1">打款成功</option>
	                		<option value="2">打款失败</option>
	                	</c:if>
	                	
	                	<c:if test="${takeCashStatus == 0 }">
	                		<option value="-1">全部</option>
	                		<option value="0" selected="selected">待打款</option>
	                		<option value="1">打款成功</option>
	                		<option value="2">打款失败</option>
	                	</c:if>
	                	
	                	<c:if test="${takeCashStatus == 1 }">
	                		<option value="-1">全部</option>
	                		<option value="0">待打款</option>
	                		<option value="1" selected="selected">打款成功</option>
	                		<option value="2">打款失败</option>
	                	</c:if>
	                	
	                	<c:if test="${takeCashStatus == 2 }">
	                		<option value="-1">全部</option>
	                		<option value="0">待打款</option>
	                		<option value="1">打款成功</option>
	                		<option value="2" selected="selected">打款失败</option>
	                	</c:if>
	                    
	                </select>
	            </p>
				<input type="hidden" id="query_pageNumber" name="pageNumber" value="${pageNumber}">
				<c:forEach items="${backer_rolePowerList}" var="powerId">
					<c:if test="${powerId == 141001 }">
			            <input type="text" value="查&nbsp;询" class="ask" onfocus="this.blur()" onclick="queryHandle()"/>
					</c:if>
				</c:forEach>
				<c:forEach items="${backer_rolePowerList}" var="powerId">
					<c:if test="${powerId == 141002 }">
			            <input type="text" value="导&nbsp;出" class="educe" onfocus="this.blur()" onclick="exportData()"/>
					</c:if>
				</c:forEach>
				
	        </div>
		</form>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr class="tableTitle">
                <td class="order">订单号</td>
                <td class="account">用户账号</td>
                <td class="money">提现金额</td>
                <td class="type">收款方式</td>
                <td class="receive">收款账号</td>
                <td class="time">申请时间</td>
                <td class="state">提现状态</td>
                <td class="mark">提现备注</td>
                <td class="operate">操作</td>
            </tr>
            <c:forEach items="${list}" var="takeCashRecord">
	            <tr class="tableInfo">
	                <td class="order">${takeCashRecord.orderNo }</td>
	                <td class="account">${takeCashRecord.userAccount }</td>
	                <td class="money"><fmt:formatNumber type="number" value="${takeCashRecord.takeCashAmount }" maxFractionDigits="2"/>元</td>
	                <td class="type">
	                	<c:if test="${takeCashRecord.takeCashWay == 1 }">
	                		支付宝
	                	</c:if>
	                	<c:if test="${takeCashRecord.takeCashWay == 2 }">
	                		微信
	                	</c:if>
	                </td>
	                <td class="receive">${takeCashRecord.takeCashAccount }</td>
	                <td class="time"><fmt:formatDate value="${takeCashRecord.addTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                <td class="state">
	                	<c:if test="${takeCashRecord.takeCashStatus == 0}">
	                		待打款
	                	</c:if>
	                	<c:if test="${takeCashRecord.takeCashStatus == 1}">
	                		打款成功
	                	</c:if>
	                	<c:if test="${takeCashRecord.takeCashStatus == 2}">
	                		打款失败
	                	</c:if>
	                </td>
	                <td class="mark">${takeCashRecord.remarks }</td>
	                <td class="operate">
	                	<c:forEach items="${backer_rolePowerList}" var="powerId">
		                	<c:if test="${takeCashRecord.takeCashStatus == 0 && powerId == 141003}">
			                    <input type="text" value="付&nbsp;款" class="pay" onfocus="this.blur()" onclick="openPayOperation('${takeCashRecord.orderNo}')"/>
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
                	<span>${pageNumber + 1}</span>/<span>${totalPageNumber + 1}</span>
                </span>
                <input type="text" value="下一页>" onfocus="this.blur()" onclick="nextPage()"/>
            </p>
        </div>
    </div>
</div>


<div class="mask">
    <div class="mask_content">
    	<form id="payOperationForm" action="<%=path %>/backerWeb/backerTakeCashRecord/payOperation.htm" method="post">
	        <div class="pay_pop">
	            <span class="rt"><img src="<%=path%>/resource/image/back/location_img.png">付款操作</span>
	            <p class="entry_tips">
	                <label class="sign">提现状态<span>*</span>：</label>
	                <select class="choice" id="takeCashStatus_payOperation" name="takeCashStatus_payOperation">
	                    <option value = "-1">请选择提现状态</option>
	                    <option value = "1">打款成功</option>
	                    <option value = "2">打款失败</option>
	                </select>
	            </p>
	            <p class="entry_tips">
	                <label class="sign">提现备注：</label>
	                <textarea id="remarks_payOperation" name="remarks_payOperation" class="txt" placeholder="请输入提现备注，最多50个字" maxlength="50"></textarea>
	            </p>
	        </div>
	        <input id="orderNo_payOperation" name=orderNo_payOperation type="hidden">
		</form>
        <div class="Button">
            <input type="text" class="cancel" value="取&nbsp;&nbsp;消" onfocus="this.blur()" />
            <input type="text" class="yes" value="确&nbsp;&nbsp;定" onfocus="this.blur()" onclick="payOperation()" />
        </div>
    </div>
</div>

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

    $(function(){
        $(".pay").click(function(){
            $(".mask").css("display","block");
            $(".pay_pop").css("display","block");
            popObj = ".pay_pop"
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
	
	function openPayOperation(orderNo){
		if(orderNo == null || orderNo == "" || orderNo == ''){
			openTips("参数错误！");
			return;
		}
	
		document.getElementById("orderNo_payOperation").value = orderNo;
	}
	
	function payOperation(){
		var takeCashStatus = document.getElementById("takeCashStatus_payOperation").value;
		
		if(takeCashStatus == null || takeCashStatus == "" || takeCashStatus == '' || takeCashStatus == "-1"){
			openTips("请选择提现状态！");
			return;
		}
		
		$("#payOperationForm").submit();
	}
	
	var flag = true;
	//导出数据
	function exportData(){
        if(flag == false){
			return;
		}        
        
        flag = false;
        
        var orderNo = '${orderNo}';
        var userAccount = '${userAccount}';
        var takeCashWay = '${takeCashWay}';
        var startTime = '${startTime}';
        var endTime = '${endTime}';
        var takeCashStatus = '${takeCashStatus}';
        
        $.ajax({
            url: '<%=path%>' + "/backerWeb/backerTakeCashRecord/exportData", //方法路径URL
            data:{
                orderNo : orderNo,
                userAccount : userAccount,
                takeCashWay : takeCashWay,
                startTime : startTime,
                endTime: endTime,
                takeCashStatus : takeCashStatus,
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
