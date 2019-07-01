<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门列表</title>
<script type="text/javascript">
	function searchDept()
	{
		document.forms["deptForm"]["pageVO.pageNo"].value = 1;
		document.forms["deptForm"].submit();
	}
	function editDept(deptId){
		var deptIdObj=$("input[name='deptVO.deptId']")[0];
		deptIdObj.value=deptId;
		$("#deptForm").attr('action',"${ctx}/dept/editDept");
		$("#deptForm").submit();
	}
	function addDept(){
		$("#deptForm").attr('action',"${ctx}/dept/editDept");
		$("#deptForm").submit();
	}	
	function deleteDept(deptId){
		if (window.confirm("确定删除该部门？")){
			var deptIdObj=$("input[name='deptVO.deptId']")[0];
			deptIdObj.value=deptId;
			$("#deptForm").attr('action',"${ctx}/dept/deleteDept");
			$("#deptForm").submit();
		}
	}
</script>
</head>
<body>
	<form id="deptForm" name="deptForm" action="${ctx}/dept/listDept" method="post" commandName="deptForm">
		<input type="hidden" name="deptVO.deptId" value="${deptForm.deptVO.deptId}" />
		<div id="searchbar">
			<input id="txtKey" type="text" name="deptVO.searchValue" />
			<input id="btnOK" onclick="searchDept()" value="查找" type="button"/>
		</div>
		<div>
			<div style="width: 100%;">
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td>部门</td>
						<td>描述</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty deptForm.deptList}">
						<c:forEach var="item" items="${deptForm.deptList}">
							<tr>
								<td>
									<c:out value="${item.deptName}"/></td>
								<td>
									<c:out value="${item.description}"/></td>
								<td>
									<c:out value="${item.deptStatusName}"/></td>
								<td>
									<a class="permission DeptService$update" href="javascript:editDept(${item.deptId});">编辑</a>&nbsp;&nbsp;
									<a class="permission DeptService$delete" href="javascript:deleteDept(${item.deptId});">删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="4" align="right"><app:pages formName="deptForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;"><input type="button" class="permission DeptService$update" value="新增" onclick="addDept();" />  </div>
			</div>
		</div>
	</form>
</body>
</html>