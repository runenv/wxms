<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色列表</title>

<script type="text/javascript">
	function searchRole()
	{
		document.forms["roleForm"]["pageVO.pageNo"].value = 1;
		document.forms["roleForm"].submit();
	}
	function editRole(roleId){
		var roleIdObj=$("input[name='roleVO.roleId']")[0];
		roleIdObj.value=roleId;
		$("#roleForm").attr('action',"${ctx}/role/editRole");
		$("#roleForm").submit();
	}
	function addRole(){
		$("#roleForm").attr('action',"${ctx}/role/editRole");
		$("#roleForm").submit();
	}	
	function deleteRole(roleId){
		if (window.confirm("确定删除该角色？")){
			var roleIdObj=$("input[name='roleVO.roleId']")[0];
			roleIdObj.value=roleId;
			$("#roleForm").attr('action',"${ctx}/role/deleteRole");
			$("#roleForm").submit();
		}
	}
	function listRoleRight(roleId){
		$("#roleForm").attr('action',"${ctx}/role/listRoleRight");
		$("input[name='roleVO.roleId']").val(roleId);
		$("#roleForm").submit();
	}
</script>
</head>
<body>
	<form:form id="roleForm" name="roleForm" action="${ctx}/role/listRole" method="post" commandName="roleForm">
		<form:hidden path="roleVO.roleId"/>
		<div>
			<div style="width: 100%;" class="l-grid-body-inner">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="roleVO.searchValue"/>
							<input id="btnOK" onclick="searchRole()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td>角色</td>
						<td>角色描述</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty roleForm.roleList}">
						<c:forEach var="item" items="${roleForm.roleList}" >
							<tr>
								<td>
									<c:out value="${item.roleName}" />
								</td>
								<td>
									<c:out value="${item.roleDescription}" />
								</td>
								<td>
									<c:out value="${item.roleStatusName}" />
								</td>
								<td>
									<a class="permission RoleService$update" href="javascript:editRole(${item.roleId});">编辑</a>&nbsp;&nbsp;
									<a class="permission RoleService$delete" href="javascript:deleteRole(${item.roleId});">删除</a>&nbsp;&nbsp;
									<c:choose>
										<c:when test="${sessionScope.userSession.currentRoleId == 1}">
											<a href="javascript:listRoleRight(${item.roleId});">授权</a>
										</c:when>
										<c:otherwise>
											<a class="permission RoleService$updateRight" href="javascript:listRoleRight(${item.roleId});">授权</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="4" align="right"><app:pages formName="roleForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission RoleService$update" type="button" value="新增" onclick="addRole();" />
				</div>
			</div>
			<!-- 分页区 -->
			<div class="l-bar-group  l-bar-message">
				
			</div>
		</div>
	</form:form>
</body>
</html>