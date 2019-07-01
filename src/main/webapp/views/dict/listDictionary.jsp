<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典列表</title>
<script type="text/javascript">
	function searchDict(){
		document.forms["dictForm"]["pageVO.pageNo"].value = 1;
		document.forms["dictForm"].submit();
	}
	function searchDictItem(dictionaryId){
		var dictItemIdObj=$("input[name='dictItemVO.dictionaryId']")[0];
		dictItemIdObj.value=dictionaryId;
		$("#dictForm").attr('action',"${ctx}/dict/findDictItemList");
		document.forms["dictForm"].submit();
	}
	function addDict(){
		$("#dictForm").attr('action',"${ctx}/dict/editDict");
		$("#dictForm").submit();
	}
	function editDict(dictionaryId){
		var dictionaryIdObj=$("input[name='dictionaryVO.dictionaryId']")[0];
		dictionaryIdObj.value=dictionaryId;
		$("#dictForm").attr('action',"${ctx}/dict/editDict");
		$("#dictForm").submit();
	}
	function deleteDict(dictionaryId){
		if (window.confirm("确定删除此记录？")){
			var dictionaryIdObj=$("input[name='dictionaryVO.dictionaryId']")[0];
			dictionaryIdObj.value=dictionaryId;
			$("#dictForm").attr('action',"${ctx}/dict/deleteDict");
			$("#dictForm").submit();
		}
	}
</script>
</head>
<body>
	<form id="dictForm" name="dictForm" action="${ctx}/dict/findDictList" method="post" commandName="dictForm">
		<input type="hidden" name="dictionaryVO.dictionaryId" value="${dictForm.dictionaryVO.dictionaryId}" />
		<input type="hidden" name="dictItemVO.dictionaryId" value="${dictForm.dictItemVO.dictionaryId}" />
		<div>
			<div>
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<input id="txtKey" type="text" name="dictionaryVO.searchValue"> 
							<input id="btnOK" onclick="searchDict()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td>类别别名称</td>
						<td>描述</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty dictForm.dictionaryList}">
						<c:forEach var="item" items="${dictForm.dictionaryList}">
							<tr>
								<td>
									<a href="javascript:searchDictItem(${item.dictionaryId});">
										<c:out value="${item.dictionaryName}"/></a>
								</td>
								<td>
									<c:out value="${item.description}"/></td>
								<td>
									<c:out value="${item.dictionaryStatusName}"/></td>
								<td>
									<a class="permission DictService$update" href="javascript:editDict(${item.dictionaryId});">编辑</a>&nbsp;&nbsp;
									<a class="permission DictService$delete" href="javascript:deleteDict(${item.dictionaryId});">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="4" align="right"><app:pages formName="dictForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input type="button" value="新增" onclick="addDict();" class="permission DictService$update" /></div>
			</div>
		</div>
	</form>
</body>
</html>