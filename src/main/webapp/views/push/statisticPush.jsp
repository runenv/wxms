<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Push数据统计</title>
<script type="text/javascript">
$(function(){
	var PushStatistic = {
		initDictComb:function(lookupId,parentId){
			var lookup = $("#"+lookupId).attr("lookup");
			var defaultValue = $("#"+lookupId).attr("defaultValue");
			var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(lookup)+"/"+parentId;
			$("#"+lookupId).empty();
			var op="<option value=''>全部</option>";
	       	$("#"+lookupId).append(op);
	       	if("undefined"==typeof(parentId)) return;
	       	$.ajax({
	        	url:lookupUrl,
	        	type:"GET",
	        	cache:false,
	        	async:false,
	        	dataType:"json",
	        	timeout:10000,
	        	success:function(data){
	        		var json = eval(data.Rows);
	               	$.each(json, function(i,item){
	                   	op="<option title='"+item.itemId+"' value='"+item.itemCode+"'>"+item.itemName+"</option>";
	                   	$("#"+lookupId).append(op);
	                });
	               	try{
	               		$("#"+lookupId).val(defaultValue);
	               	}catch(e){}
	        	},
	        	error:function(){
	        		try{
	        			console.log("Init combo failure!");
	        		}catch(e){}
	        	}
	        });
		},
		renderPushContent:function(lookupId){
			var defaultValue = $("#"+lookupId).attr("defaultValue");
			var lookupUrl=jsCtx+"/services/business/push/listPush";
			$("#"+lookupId).empty();
			var op;
	       	if("undefined"==typeof(lookupId)) return;
	       	$.ajax({
	        	url:lookupUrl,
	        	type:"GET",
	        	cache:false,
	        	async:false,
	        	dataType:"json",
	        	timeout:10000,
	        	success:function(data){
	        		var json = eval(data.Rows);
	               	$.each(json, function(i,item){
	                   	op="<option value='"+item.pushId+"'>"+item.title+"</option>";
	                   	$("#"+lookupId).append(op);
	                });
	               	try{
	               		$("#"+lookupId).val(defaultValue);
	               	}catch(e){}
	        	},
	        	error:function(){
	        		try{
	        			console.log("Init combo failure!");
	        		}catch(e){}
	        	}
	        });
		},
		statisticPush:function(){
			$("#pushForm").submit();
		}
	};
	
	$("#searchDiv").click(function(){
		FormUtils.slideZone($("#searchZone"), 300);
	});
	var lookupId = "platform";
	PushStatistic.initDictComb(lookupId, "0");
	lookupId = "version";
	PushStatistic.initDictComb(lookupId, "0");
	lookupId = "pushIdCombo";
	PushStatistic.renderPushContent(lookupId);
	$("#searchBtn").click(function(){
		PushStatistic.statisticPush();
	});
});
</script>
</head>
<body>
<form id="pushForm" name="pushForm" action="${ctx}/push/statisticPush" method="post" commandName="pushForm">
<div style="width: 100%;">
	<%--<div id="searchDiv" style="border-bottom: none;" class="table-input-label">查询条件...</div> --%>
	<table id="searchZone" class="table-input" style="width: 100%;" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="table-input-label">
				Push标题：
			</td>
			<td colspan="3" class="table-input-td">
				<select id="pushIdCombo" name="pushStatisticVO.pushId" defaultValue="${pushForm.pushStatisticVO.pushId}"></select>
			</td>
		</tr>
		<tr>
			<td class="table-input-label">
				客户端名称：
			</td>
			<td class="table-input-td">
				<select id="platform" name="pushStatisticVO.platform"  defaultValue="${pushForm.pushStatisticVO.platform}"
					lookup="<%=DictionaryVO.PUSH_PLATFORM%>"></select>
			</td>
			<td class="table-input-label">
				系统版本：
			</td>
			<td class="table-input-td">
				<select id="version" name="pushStatisticVO.version"  defaultValue="${pushForm.pushStatisticVO.version}"
				lookup="<%=DictionaryVO.PUSH_VERSION%>"></select>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="button" id="searchBtn" value="查询" />
			</td>
		</tr>
	</table>
	<table class="t2" style="width: 100%;" border="1">
		<tr class="a1">
			<td style="width: 10%;">客户端名称</td>
			<td>版本</td>
			<td>Push次数</td>
			<td>Push用户数</td>
			<td>Push点击次数</td>
		</tr>
		<c:if test="${!empty pushForm.pushStatisticList}">
			<c:forEach var="item" varStatus="varStatus" items="${pushForm.pushStatisticList}" >
				<tr>
					<td>
						<c:out value="${item.platform}" />
					</td>
					<td>
						<c:out value="${item.version}" />
					</td>
					<td>
						<c:out value="${item.pushCount}" />
					</td>
					<td>
						<c:out value="${item.userCount}" />
					</td>
					<td>
						<c:out value="${item.hitCount}" />
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<br>
</div>
</form>
</body>
</html>