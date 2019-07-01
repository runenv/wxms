<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告位列表</title>

<script type="text/javascript">
	function searchNotice()
	{
		document.forms["noticeForm"]["pageVO.pageNo"].value = 1;
		document.forms["noticeForm"].submit();
	}
	function editNotice(noticeId){
		var noticeIdObj=$("input[name='noticeVO.noticeId']")[0];
		noticeIdObj.value=noticeId;
		$("#noticeForm").attr('action',"${ctx}/notice/editNotice");
		$("#noticeForm").submit();
	}
	function viewNotice(noticeId){
		var noticeIdObj=$("input[name='noticeVO.noticeId']")[0];
		noticeIdObj.value=noticeId;
		$("#noticeForm").attr('action',"${ctx}/notice/viewNotice");
		$("#noticeForm").submit();
	}
	function addNotice(){
		var noticeIdObj=$("input[name='noticeVO.noticeId']");
		noticeIdObj.val("");
		$("#noticeForm").attr('action',"${ctx}/notice/editNotice");
		$("#noticeForm").submit();
	}	
	function deleteNotice(noticeId){
		if (window.confirm("确定删除该广告位？")){
			var noticeIdObj=$("input[name='noticeVO.noticeId']")[0];
			noticeIdObj.value=noticeId;
			$("#noticeForm").attr('action',"${ctx}/notice/deleteNotice");
			$("#noticeForm").submit();
		}
	}
	function orderNotice(noticeId,direction){
		var noticeIdObj=$("input[name='noticeVO.noticeId']")[0];
		noticeIdObj.value=noticeId;
		$("#noticeVODirection").val(direction);
		$("#noticeForm").attr('action',"${ctx}/notice/orderNotice");
		$("#noticeForm").submit();
	}
	function releaseNotice(noticeId,noticeStatus){
		var $noticeId = $("input[name='noticeVO.noticeId']");
		$noticeId.val(noticeId);
		var $noticeStatus = $("input[name='noticeVO.noticeStatus']");
		$noticeStatus.val(noticeStatus);
		$("#noticeForm").attr('action',"${ctx}/notice/releaseNotice");
		$("#noticeForm").submit();
	}
</script>
</head>
<body>
	<form:form id="noticeForm" name="noticeForm" action="${ctx}/notice/listNotice" method="post" commandName="noticeForm">
		<form:hidden path="noticeVO.noticeId"/>
		<form:hidden path="noticeVO.noticeStatus"/>
		<input type="hidden" id="noticeVODirection" name="noticeVO.direction" />
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="noticeVO.searchValue"/>
							<input id="btnOK" onclick="searchNotice()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 6%;">序号</td>
						<td>排列序号</td>
						<td>广告位ID</td>
						<td>广告位标题</td>
						<td >类别</td>
						<td >开始日期</td>
						<td >截止日期</td>
						<td >发布平台</td>
						<td >状态</td>
						<td style="width: 12%;">操作</td>
					</tr>
					<c:if test="${!empty noticeForm.noticeList}">
						<c:forEach var="item" varStatus="varStatus" items="${noticeForm.noticeList}" >
							<tr>
								<td>
									<c:out value="${item.orderNum}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${varStatus.index ==0}">
											<a href="javascript:orderNotice(${item.noticeId},-1);">↓</a>
										</c:when>
										<c:when test="${varStatus.last}">
											<a href="javascript:orderNotice(${item.noticeId},1);">↑</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:orderNotice(${item.noticeId},1);">↑</a>
											&nbsp;<a href="javascript:orderNotice(${item.noticeId},-1);">↓</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:out value="${item.noticeId}" />
								</td>
								<td>
									<a href="javascript:viewNotice(${item.noticeId});">
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
										<c:when test="${item.noticeStatus==1}">
											<a class="permission NoticeService$release" 
												href="javascript:releaseNotice(${item.noticeId},2);">撤销</a>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a class="permission NoticeService$release" 
												href="javascript:releaseNotice(${item.noticeId},1);">发布</a>&nbsp;&nbsp;
										</c:otherwise>
									</c:choose>
									<c:if test="${item.noticeStatus!=1}">
										<a class="permission NoticeService$update" href="javascript:editNotice(${item.noticeId});">编辑</a>&nbsp;&nbsp;
										<a class="permission NoticeService$delete" href="javascript:deleteNotice(${item.noticeId});">删除</a>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="10" align="right"><app:pages formName="noticeForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission NoticeService$update" type="button" value="新增" onclick="addNotice();" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>