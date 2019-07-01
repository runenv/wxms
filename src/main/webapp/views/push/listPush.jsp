<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Push列表</title>

<script type="text/javascript">
	function searchPush()
	{
		document.forms["pushForm"]["pageVO.pageNo"].value = 1;
		document.forms["pushForm"].submit();
	}
	function editPush(pushId){
		var pushIdObj=$("input[name='pushVO.pushId']")[0];
		pushIdObj.value=pushId;
		$("#pushForm").attr('action',"${ctx}/push/editPush");
		$("#pushForm").submit();
	}
	function viewPush(pushId){
		var pushIdObj=$("input[name='pushVO.pushId']")[0];
		pushIdObj.value=pushId;
		$("#pushForm").attr('action',"${ctx}/push/viewPush");
		$("#pushForm").submit();
	}
	function addPush(){
		var pushIdObj=$("input[name='pushVO.pushId']");
		pushIdObj.val("");
		$("#pushForm").attr('action',"${ctx}/push/editPush");
		$("#pushForm").submit();
	}	
	function deletePush(pushId){
		if (window.confirm("确定删除该Push？")){
			var pushIdObj=$("input[name='pushVO.pushId']")[0];
			pushIdObj.value=pushId;
			$("#pushForm").attr('action',"${ctx}/push/deletePush");
			$("#pushForm").submit();
		}
	}
	function orderPush(pushId,direction){
		var pushIdObj=$("input[name='pushVO.pushId']")[0];
		pushIdObj.value=pushId;
		$("#pushVODirection").val(direction);
		$("#pushForm").attr('action',"${ctx}/push/orderPush");
		$("#pushForm").submit();
	}
	function releasePush(pushId,pushStatus){
		var $pushId = $("input[name='pushVO.pushId']");
		$pushId.val(pushId);
		var $pushStatus = $("input[name='pushVO.pushStatus']");
		$pushStatus.val(pushStatus);
		$("#pushForm").attr('action',"${ctx}/push/releasePush");
		$("#pushForm").submit();
	}
</script>
</head>
<body>
	<form:form id="pushForm" name="pushForm" action="${ctx}/push/listPush" method="post" commandName="pushForm">
		<form:hidden path="pushVO.pushId"/>
		<form:hidden path="pushVO.pushStatus"/>
		<input type="hidden" id="pushVODirection" name="pushVO.direction" />
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="pushVO.searchValue"/>
							<input id="btnOK" onclick="searchPush()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 6%;">序号</td>
						<td>Push标题</td>
						<td >类别</td>
						<td >目标客户端</td>
						<td >版本</td>
						<td >push时间</td>
						<td >状态</td>
						<td style="width: 12%;">操作</td>
					</tr>
					<c:if test="${!empty pushForm.pushList}">
						<c:forEach var="item" varStatus="varStatus" items="${pushForm.pushList}" >
							<tr>
								<td>
									<c:out value="${item.orderNum}" />
								</td>
								<td>
									<a href="javascript:viewPush(${item.pushId});">
									<c:out value="${item.title}" /></a>
								</td>
								<td>
									<c:out value="${item.className}" />
								</td>
								<td>
									<c:out value="${item.platform}" />
								</td>
								<td>
									<c:out value="${item.version}" />
								</td>
								<td>
									<c:out value="${item.taskDate}" />
								</td>
								<td>
									<c:out value="${item.pushStatusName}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${item.pushStatus==1}">
											<a class="permission PushService$release" 
												href="javascript:releasePush(${item.pushId},2);">撤销</a>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a class="permission PushService$release" 
												href="javascript:releasePush(${item.pushId},1);">发布</a>&nbsp;&nbsp;
										</c:otherwise>
									</c:choose>
									<c:if test="${item.pushStatus!=1}">
										<a class="permission PushService$update" href="javascript:editPush(${item.pushId});">编辑</a>&nbsp;&nbsp;
										<a class="permission PushService$delete" href="javascript:deletePush(${item.pushId});">删除</a>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="8" align="right"><app:pages formName="pushForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission PushService$update" type="button" value="新增" onclick="addPush();" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>