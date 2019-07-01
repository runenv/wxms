<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>数据字典列表</title>
<script type="text/javascript">
	function searchCatalog(){
		document.forms["catalogForm"]["pageVO.pageNo"].value = 1;
		document.forms["catalogForm"].submit();
	}
	function searchCatalogItem(catalogId){
		var catalogItemIdObj=$("input[name='catalogItemVO.catalogId']")[0];
		catalogItemIdObj.value=catalogId;
		$("#catalogForm").attr('action',"${ctx}/catalog/findCatalogItemList");
		document.forms["catalogForm"].submit();
	}
	function addCatalog(){
		$("#catalogForm").attr('action',"${ctx}/catalog/editCatalog");
		$("#catalogForm").submit();
	}
	function editCatalog(catalogId){
		var catalogIdObj=$("input[name='catalogVO.catalogId']")[0];
		catalogIdObj.value=catalogId;
		$("#catalogForm").attr('action',"${ctx}/catalog/editCatalog");
		$("#catalogForm").submit();
	}
	function deleteCatalog(catalogId){
		var catalogIdObj=$("input[name='catalogVO.catalogId']")[0];
		catalogIdObj.value=catalogId;
		$("#catalogForm").attr('action',"${ctx}/catalog/deleteCatalog");
		$("#catalogForm").submit();
	}
</script>
</head>
<body>
	<form id="catalogForm" name="catalogForm" action="${ctx}/catalog/findCatalogList" method="post" commandName="catalogForm">
		<input type="hidden" name="catalogVO.catalogId" value="${catalogForm.catalogVO.catalogId}" />
		<div>
			<div style="width: 100%;">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<input id="txtKey" type="text" name="catalogVO.searchValue" value="${catalogForm.catalogVO.searchValue}"> 
							<input id="btnOK" onclick="searchCatalog()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td>栏目名称</td>
						<td>栏目值</td>
						<td>栏目URL</td>
						<td>栏目描述</td>
						<td>排序号</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty catalogForm.catalogList}">
						<c:forEach var="item" items="${catalogForm.catalogList}">
							<tr>
								<td>
									<c:out value="${item.catalogName}"/>
								</td>
								<td>
									<c:out value="${item.catalogValue}"/></td>
								<td>
									<c:out value="${item.catalogUrl}"/></td>
								<td>
									<c:out value="${item.catalogDescription}"/></td>
								<td>
									<c:out value="${item.orderNum}"/></td>
								<td>
									<a class="permission CatalogService$update" href="javascript:editCatalog(${item.catalogId});">编辑</a>&nbsp;&nbsp;
									<a class="permission CatalogService$delete" href="javascript:deleteCatalog(${item.catalogId});">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="6" align="right"><app:pages formName="catalogForm"/></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input type="button" value="新增" onclick="addCatalog();" class="permission CatalogService$update"/>  </div>
			</div>
		</div>
	</form>
</body>
</html>