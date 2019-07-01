<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告列表</title>

<script type="text/javascript">
	function searchBulletin()
	{
		document.forms["bulletinForm"]["pageVO.pageNo"].value = 1;
		document.forms["bulletinForm"].submit();
	}
	function editBulletin(bulletinId){
		var bulletinIdObj=$("input[name='bulletinVO.bulletinId']")[0];
		bulletinIdObj.value=bulletinId;
		$("#bulletinForm").attr('action',"${ctx}/bulletin/editBulletin");
		$("#bulletinForm").submit();
	}
	function viewBulletin(bulletinId){
		var bulletinIdObj=$("input[name='bulletinVO.bulletinId']")[0];
		bulletinIdObj.value=bulletinId;
		$("#bulletinForm").attr('action',"${ctx}/bulletin/viewBulletin");
		$("#bulletinForm").submit();
	}
	function addBulletin(){
		var bulletinIdObj=$("input[name='bulletinVO.bulletinId']");
		bulletinIdObj.val("");
		$("#bulletinForm").attr('action',"${ctx}/bulletin/editBulletin");
		$("#bulletinForm").submit();
	}	
	function deleteBulletin(bulletinId){
		if (window.confirm("确定删除该公告？")){
			var bulletinIdObj=$("input[name='bulletinVO.bulletinId']")[0];
			bulletinIdObj.value=bulletinId;
			$("#bulletinForm").attr('action',"${ctx}/bulletin/deleteBulletin");
			$("#bulletinForm").submit();
		}
	}
	function orderBulletin(bulletinId,direction){
		var bulletinIdObj=$("input[name='bulletinVO.bulletinId']")[0];
		bulletinIdObj.value=bulletinId;
		$("#bulletinVODirection").val(direction);
		$("#bulletinForm").attr('action',"${ctx}/bulletin/orderBulletin");
		$("#bulletinForm").submit();
	}
	function releaseBulletin(bulletinId,bulletinStatus){
		var $bulletinId = $("input[name='bulletinVO.bulletinId']");
		$bulletinId.val(bulletinId);
		var $bulletinStatus = $("input[name='bulletinVO.bulletinStatus']");
		$bulletinStatus.val(bulletinStatus);
		$("#bulletinForm").attr('action',"${ctx}/bulletin/releaseBulletin");
		$("#bulletinForm").submit();
	}
</script>
</head>
<body>
	<form:form id="bulletinForm" name="bulletinForm" action="${ctx}/bulletin/listBulletin" method="post" commandName="bulletinForm">
		<form:hidden path="bulletinVO.bulletinId"/>
		<form:hidden path="bulletinVO.bulletinStatus"/>
		<input type="hidden" id="bulletinVODirection" name="bulletinVO.direction" />
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="bulletinVO.searchValue"/>
							<input id="btnOK" onclick="searchBulletin()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 6%;">序号</td>
						<%--  <td>排列序号</td>--%>
						<td>公告标题</td>
						<td >开始日期</td>
						<td >截止日期</td>
						<td >发布平台</td>
						<td >状态</td>
						<td style="width: 12%;">操作</td>
					</tr>
					<c:if test="${!empty bulletinForm.bulletinList}">
						<c:forEach var="item" varStatus="varStatus" items="${bulletinForm.bulletinList}" >
							<tr>
								<td>
									<c:out value="${item.orderNum}" />
								</td>
								<%-- 
								<td>
									<c:choose>
										<c:when test="${varStatus.index ==0}">
											<a href="javascript:orderBulletin(${item.bulletinId},-1);">↓</a>
										</c:when>
										<c:when test="${varStatus.last}">
											<a href="javascript:orderBulletin(${item.bulletinId},1);">↑</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:orderBulletin(${item.bulletinId},1);">↑</a>
											&nbsp;<a href="javascript:orderBulletin(${item.bulletinId},-1);">↓</a>
										</c:otherwise>
									</c:choose>
								</td>--%>
								<td>
									<a href="javascript:viewBulletin(${item.bulletinId});">
									<c:out value="${item.title}" /></a>
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
										<c:when test="${item.bulletinStatus==1}">
											<a class="permission BulletinService$release" 
												href="javascript:releaseBulletin(${item.bulletinId},2);">撤销</a>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a class="permission BulletinService$release" 
												href="javascript:releaseBulletin(${item.bulletinId},1);">发布</a>&nbsp;&nbsp;
										</c:otherwise>
									</c:choose>
									<c:if test="${item.bulletinStatus!=1}">
										<a class="permission BulletinService$update" href="javascript:editBulletin(${item.bulletinId});">编辑</a>&nbsp;&nbsp;
										<a class="permission BulletinService$delete" href="javascript:deleteBulletin(${item.bulletinId});">删除</a>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="7" align="right"><app:pages formName="bulletinForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission BulletinService$update" type="button" value="新增" onclick="addBulletin();" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>