<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典子项列表</title>
<script type="text/javascript">
	function searchDictItem(){
		document.forms["dictForm"]["pageVO.pageNo"].value = 1;
		document.forms["dictForm"].submit();
	}
	function addDictItem(){
		$("#dictForm").attr('action',"${ctx}/dict/editDictItem"); 
		$("#dictForm").submit();
	}
	function editDictItem(dictItemId){
		var dictItemIdObj=$("input[name='dictItemVO.itemId']")[0];
		dictItemIdObj.value=dictItemId;
		$("#dictForm").attr('action',"${ctx}/dict/editDictItem");
		$("#dictForm").submit();
	}
	function deleteDictItem(dictItemId){
		if (window.confirm("确定删除此记录？")){
			var dictItemIdObj=$("input[name='dictItemVO.itemId']")[0];
			dictItemIdObj.value=dictItemId;
			$("#dictForm").attr('action',"${ctx}/dict/deleteDictItem");
			$("#dictForm").submit();
		}
	}
</script>
</head>
<body>
	<form id="dictForm" name="dictForm" action="${ctx}/dict/findDictItemList" method="post" commandName="dictForm">
		<input type="hidden" name="dictItemVO.itemId" value="${dictForm.dictItemVO.itemId}" />
		<input type="hidden" name="dictItemVO.dictionaryId" value="${dictForm.dictItemVO.dictionaryId}" />
		<div>
			<div style="width: 100%;">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<input id="txtKey" type="text" name="dictItemVO.searchValue"> 
							<input id="btnOK" onclick="searchDictItem()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table style="width: 100%;" border="1" class="t2">
					<tr class="a1">
						<td>子项名称</td>
						<td>子项编码</td>	
						<td>父项类别</td>
						<td>描述</td>	
						<td>状态</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty dictForm.dictItemList}">
						<c:forEach var="item" items="${dictForm.dictItemList}">
							<tr>
								<td>
									<c:out value="${item.itemName}"/>
								</td>
								<td>
									<c:out value="${item.itemCode}"/></td>
								<td>
									<c:out value="${item.dictionaryName}"/></td>
								<td>
									<c:out value="${item.description}"/></td>
								<td>
									<c:out value="${item.itemStatusName}"/></td>		
								<td>
									<a class="permission DictService$updateItem" href="javascript:editDictItem(${item.itemId});">编辑</a>&nbsp;&nbsp;
									<a class="permission DictService$deleteItem" href="javascript:deleteDictItem(${item.itemId});">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="6" align="right"><app:pages formName="dictForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;"><input class="permission DictService$updateItem" type="button" value="新增" onclick="addDictItem();" />  </div>
			</div>
		</div>
	</form>
</body>
</html>