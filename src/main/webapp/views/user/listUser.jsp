<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<style easyui="true" type="text/css">
</style>
<script type="text/javascript">
	function searchUser()
	{
		document.forms["userForm"]["pageVO.pageNo"].value = 1;
		document.forms["userForm"].submit();
	}
	function editUser(userId){
		var userIdObj=$("input[name='userVO.userId']")[0];
		userIdObj.value=userId;
		$("#userForm").attr('action',"${ctx}/user/add");
		$("#userForm").submit();
	}
	function addUser(){
		var userIdObj=$("input[name='userVO.userId']")[0];
		userIdObj.value="";
		$("#userForm").attr('action',"${ctx}/user/add");
		$("#userForm").submit();
	}	
	function deleteUser(userId){
		if (window.confirm("确定删除该用户？")){
			var userIdObj=$("input[name='userVO.userId']")[0];
			userIdObj.value=userId;
			$("#userForm").attr('action',"${ctx}/user/delete");
			$("#userForm").submit();
		}
	}
	function listUserRole(userId){
		var userIdObj=$("input[name='userVO.userId']")[0];
		userIdObj.value=userId;
		$("#userForm").attr('action',"${ctx}/user/listUserRole");
		$("#userForm").submit();
	}
	
	$(document).ready(function(){
		
	});
</script>
</head>
<body>
	<form:form id="userForm" name="userForm" action="${ctx}/user/listUser" method="post" commandName="userForm">
		<form:hidden path="userVO.userId"/>
		<div>
			<div id="searchbar" style="text-align:right;">
				
			</div>
			<div>
				<table class="t2" style="width: 100%;" border="1">
					<tr>
						<td>
							<form:input path="userVO.searchValue"/>
							<input id="btnOK" onclick="searchUser()" value="查找" type="button">
						</td>
					</tr>
				</table>
				<table class="t2" style="width: 100%;" border="1" cellSpacing="0" cellPadding="0">
					<tr class="a1">
						<td>姓名</td>
						<td>性别</td>
						<td>邮箱</td>
						<td>电话</td>
						<td>手机</td>
						<td>地址</td>
						<td>操作</td>
					</tr>
					<c:if test="${!empty userForm.userList}">
						<c:forEach var="item" items="${userForm.userList}">
							<tr >
								<td>
									<%-- <s:hidden name="userId" />--%>
									<c:out value="${item.userName}" />
								</td>
								<td>
									<c:out value="${item.userSexName}" />
								</td>
								<td>
									<c:out value="${item.userEmail}" />
								</td>
								<td>
									<c:out value="${item.userPhone}" />
								</td>
								<td>
									<c:out value="${item.userMobile}" />
								</td>
								<td>
									<c:out value="${item.userAddress}" />
								</td>
								<td>
									<a href="javascript:editUser(${item.userId});" class="permission UserService$save">编辑</a>&nbsp;&nbsp;
									<a href="javascript:deleteUser(${item.userId});" class="permission UserService$delete">删除</a>&nbsp;&nbsp;
									<a class="permission UserRoleProgramService$update" href="javascript:listUserRole(${item.userId});">授权</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr><td colspan="7" align="right"><app:pages formName="userForm" /></td></tr>
				</table>
			</div>
			<br>
			<div style="text-align: center;">
				<input type="button" value="新增" onclick="addUser();" class="permission UserService$save" /> 
			</div>
		</div>
	</form:form>
</body>
</html>