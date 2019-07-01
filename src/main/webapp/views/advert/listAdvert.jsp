<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资讯列表</title>

<script type="text/javascript">
	function searchAdvert()
	{
		document.forms["advertForm"]["pageVO.pageNo"].value = 1;
		document.forms["advertForm"].submit();
	}
	function editAdvert(advertId){
		var advertIdObj=$("input[name='advertVO.advertId']")[0];
		advertIdObj.value=advertId;
		$("#advertForm").attr('action',"${ctx}/advert/editAdvert");
		$("#advertForm").submit();
	}
	function viewAdvert(advertId){
		var advertIdObj=$("input[name='advertVO.advertId']")[0];
		advertIdObj.value=advertId;
		$("#advertForm").attr('action',"${ctx}/advert/viewAdvert");
		$("#advertForm").submit();
	}
	function addAdvert(){
		var advertIdObj=$("input[name='advertVO.advertId']");
		advertIdObj.val("");
		$("#advertForm").attr('action',"${ctx}/advert/editAdvert");
		$("#advertForm").submit();
	}	
	function deleteAdvert(advertId){
		if (window.confirm("确定删除该资讯？")){
			var advertIdObj=$("input[name='advertVO.advertId']")[0];
			advertIdObj.value=advertId;
			$("#advertForm").attr('action',"${ctx}/advert/deleteAdvert");
			$("#advertForm").submit();
		}
	}
	function orderAdvert(advertId,direction){
		var advertIdObj=$("input[name='advertVO.advertId']")[0];
		advertIdObj.value=advertId;
		$("#advertVODirection").val(direction);
		$("#advertForm").attr('action',"${ctx}/advert/orderAdvert");
		$("#advertForm").submit();
	}
	function releaseAdvert(advertId,advertStatus){
		var $advertId = $("input[name='advertVO.advertId']");
		$advertId.val(advertId);
		var $advertStatus = $("input[name='advertVO.advertStatus']");
		$advertStatus.val(advertStatus);
		$("#advertForm").attr('action',"${ctx}/advert/releaseAdvert");
		$("#advertForm").submit();
	}
</script>
</head>
<body>
	<form:form id="advertForm" name="advertForm" action="${ctx}/advert/listAdvert" method="post" commandName="advertForm">
		<form:hidden path="advertVO.advertId"/>
		<form:hidden path="advertVO.advertStatus"/>
		<input type="hidden" id="advertVODirection" name="advertVO.direction" />
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="advertVO.searchValue"/>
							<input id="btnOK" onclick="searchAdvert()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 6%;">序号</td>
						<td>排列序号</td>
						<td>资讯标题</td>
						<td >类别</td>
						<td >开始日期</td>
						<td >截止日期</td>
						<td >发布平台</td>
						<td >状态</td>
						<td style="width: 12%;">操作</td>
					</tr>
					<c:if test="${!empty advertForm.advertList}">
						<c:forEach var="item" varStatus="varStatus" items="${advertForm.advertList}" >
							<tr>
								<td>
									<c:out value="${item.orderNum}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${varStatus.index ==0}">
											<a href="javascript:orderAdvert(${item.advertId},-1);">↓</a>
										</c:when>
										<c:when test="${varStatus.last}">
											<a href="javascript:orderAdvert(${item.advertId},1);">↑</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:orderAdvert(${item.advertId},1);">↑</a>
											&nbsp;<a href="javascript:orderAdvert(${item.advertId},-1);">↓</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<a href="javascript:viewAdvert(${item.advertId});">
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
										<c:when test="${item.advertStatus==1}">
											<a class="permission AdvertService$release" 
												href="javascript:releaseAdvert(${item.advertId},2);">撤销</a>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a class="permission AdvertService$release" 
												href="javascript:releaseAdvert(${item.advertId},1);">发布</a>&nbsp;&nbsp;
										</c:otherwise>
									</c:choose>
									<c:if test="${item.advertStatus!=1}">
										<a class="permission AdvertService$update" href="javascript:editAdvert(${item.advertId});">编辑</a>&nbsp;&nbsp;
										<a class="permission AdvertService$delete" href="javascript:deleteAdvert(${item.advertId});">删除</a>&nbsp;&nbsp;
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="9" align="right"><app:pages formName="advertForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission AdvertService$update" type="button" value="新增" onclick="addAdvert();" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>