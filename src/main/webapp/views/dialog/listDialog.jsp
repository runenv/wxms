<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>弹窗列表</title>

<script type="text/javascript">
	function searchDialog()
	{
		document.forms["dialogForm"]["pageVO.pageNo"].value = 1;
		document.forms["dialogForm"].submit();
	}
	function editDialog(dialogId){
		var dialogIdObj=$("input[name='dialogVO.dialogId']")[0];
		dialogIdObj.value=dialogId;
		$("#dialogForm").attr('action',"${ctx}/dialog/editDialog");
		$("#dialogForm").submit();
	}
	function viewDialog(dialogId){
		var dialogIdObj=$("input[name='dialogVO.dialogId']")[0];
		dialogIdObj.value=dialogId;
		$("#dialogForm").attr('action',"${ctx}/dialog/viewDialog");
		$("#dialogForm").submit();
	}
	function addDialog(){
		var dialogIdObj=$("input[name='dialogVO.dialogId']");
		dialogIdObj.val("");
		$("#dialogForm").attr('action',"${ctx}/dialog/editDialog");
		$("#dialogForm").submit();
	}	
	function deleteDialog(dialogId){
		if (window.confirm("确定删除该弹窗？")){
			var dialogIdObj=$("input[name='dialogVO.dialogId']")[0];
			dialogIdObj.value=dialogId;
			$("#dialogForm").attr('action',"${ctx}/dialog/deleteDialog");
			$("#dialogForm").submit();
		}
	}
	function orderDialog(dialogId,direction){
		var dialogIdObj=$("input[name='dialogVO.dialogId']")[0];
		dialogIdObj.value=dialogId;
		$("#dialogVODirection").val(direction);
		$("#dialogForm").attr('action',"${ctx}/dialog/orderDialog");
		$("#dialogForm").submit();
	}
	function releaseDialog(dialogId,dialogStatus){
		var $dialogId = $("input[name='dialogVO.dialogId']");
		$dialogId.val(dialogId);
		var $dialogStatus = $("input[name='dialogVO.dialogStatus']");
		$dialogStatus.val(dialogStatus);
		$("#dialogForm").attr('action',"${ctx}/dialog/releaseDialog");
		$("#dialogForm").submit();
	}
</script>
</head>
<body>
	<form:form id="dialogForm" name="dialogForm" action="${ctx}/dialog/listDialog" method="post" commandName="dialogForm">
		<form:hidden path="dialogVO.dialogId"/>
		<form:hidden path="dialogVO.dialogStatus"/>
		<input type="hidden" id="dialogVODirection" name="dialogVO.direction" />
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="dialogVO.searchValue"/>
							<input id="btnOK" onclick="searchDialog()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 6%;">序号</td>
						<td>弹窗标题</td>
						<td >类别</td>
						<td >开始日期</td>
						<td >截止日期</td>
						<td >发布平台</td>
						<td >状态</td>
						<td style="width: 12%;">操作</td>
					</tr>
					<c:if test="${!empty dialogForm.dialogList}">
						<c:forEach var="item" varStatus="varStatus" items="${dialogForm.dialogList}" >
							<tr>
								<td>
									<c:out value="${item.orderNum}" />
								</td>
								<td>
									<a href="javascript:viewDialog(${item.dialogId});">
									<c:out value="${item.title}" /></a>
								</td>
								<td>
									<c:out value="${item.className}" />
								</td>
								<td>
									<c:out value="${item.startDate}" />
								</td>
								<td>
									<c:out value="${item.endDate}" />
								</td>
								<td>
									<c:out value="${item.platform}" />
								</td>
								<td>
									<c:out value="${item.actStatusName}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${item.dialogStatus==1}">
											<a class="permission DialogService$release" 
												href="javascript:releaseDialog(${item.dialogId},2);">撤销</a>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a class="permission DialogService$release" 
												href="javascript:releaseDialog(${item.dialogId},1);">发布</a>&nbsp;&nbsp;
										</c:otherwise>
									</c:choose>
									<c:if test="${item.dialogStatus!=1}">
										<a class="permission DialogService$update" href="javascript:editDialog(${item.dialogId});">编辑</a>&nbsp;&nbsp;
										<a class="permission DialogService$delete" href="javascript:deleteDialog(${item.dialogId});">删除</a>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="8" align="right"><app:pages formName="dialogForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission DialogService$update" type="button" value="新增" onclick="addDialog();" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>