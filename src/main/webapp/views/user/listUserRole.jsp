<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<script type="text/javascript">
	function searchUserRole()
	{
		document.forms["userForm"]["pageVO.pageNo"].value = 1;
		document.forms["userForm"].submit();
	}
	function editUserRole(userRoleId,roleId){
		var roleIdObj=$("input[name='userVO.userRoleProgramVO.roleVO.roleId']")[0];
		var userRoleIdObj=$("input[name='userVO.userRoleProgramVO.userRoleProgramId']")[0];
		roleIdObj.value=roleId;
		userRoleIdObj.value=userRoleId;
		$("#userForm").attr('action',"${ctx}/user/editUserRole");
		$("#userForm").submit();
	}
	function addUserRole(){
		$("#userForm").attr('action',"${ctx}/user/editUserRole");
		$("#userForm").submit();
	}	
	function deleteUserRole(userRoleId,roleId){
		var userRoleIdObj=$("input[name='userVO.userRoleProgramVO.userRoleProgramId']")[0];
		userRoleIdObj.value=userRoleId;
		$("#userForm").attr('action',"${ctx}/user/deleteUserRole");
		$("#userForm").submit();
	}
</script>
</head>
<body>
	<form:form id="userForm" name="userForm" action="${ctx}/user/listUserRole" method="post" commandName="userForm">
		<form:hidden path="userVO.userId"/>
		<form:hidden path="userVO.userRoleProgramVO.userRoleProgramId"/>
		<form:hidden path="userVO.userRoleProgramVO.roleVO.roleId"/>
		
		<div>
			<div style="width: 100%;">
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="userVO.searchValue"/>
							<input id="btnOK" onclick="searchUserRole()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1">
					<tr class="a1">
						<td style="width: 120px; height: 23px;">角色</td>
						<td style="width: 120px; height: 23px;">用户</td>	
						<%--<td style="width: 168px; height: 23px;">program</td>--%>
						<td>操作</td>
					</tr>
					<c:if test="${!empty userForm.userVO.userRoleProgramList}">
						<c:forEach var="item" items="${userForm.userVO.userRoleProgramList}" >
							<tr>
								<td>
									<%--<s:hidden name="roleVO.roleId" /> --%>
									<c:out value="${item.roleVO.roleName}" />
								</td>
								<td>
									<%--<s:hidden name="userId" />--%>
									<c:out value="${item.userName}" />
								</td>	
								<%--<td>
									无
								</td>--%>
								<td>
									<a class="permission UserRoleProgramService$update" 
										href="javascript:editUserRole(${item.userRoleProgramId},${item.roleVO.roleId});">编辑</a>&nbsp;&nbsp;
									<a class="permission UserRoleProgramService$delete" 
										href="javascript:deleteUserRole(${item.userRoleProgramId},${item.roleVO.roleId});">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="3" align="right"><app:pages formName="userForm" /></td></tr>
				</table>
				<br>
				<div style="text-align: center;">
					<input class="permission UserRoleProgramService$update" type="button" value="新增" onclick="addUserRole();" />
					<input type="button" value="返回" onclick="location.href='${ctx}/user/listUser';" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>