<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<script type="text/javascript">
	function saveUser(){
		var userForm = document.getElementById("userForm");
		userForm.action = "${ctx}/user/save";
		userForm.submit();
	}
</script>
</head>
<body>
	<form action="${ctx}/user/save" id="userForm" method="post" commandName="userForm">
		<table width="80%" align="center"  class="table-input" cellpadding="0" cellspacing="0">
			<tr>
				<td class="table-input-label">用户名</td>
				<td class="table-input-td">
					<input type="hidden" name="userVO.userId" value="${userForm.userVO.userId}" />
					<input type="text" name="userVO.userName" value="${userForm.userVO.userName}" /></td>
				<td class="table-input-label">邮箱</td>
				<td class="table-input-td"><input type="text" name="userVO.userEmail" value="${userForm.userVO.userEmail}" /></td>
			</tr>
			<tr>
				<td class="table-input-label">地址</td>
				<td class="table-input-td"><input type="text" name="userVO.userAddress" value="${userForm.userVO.userAddress}" /></td>
				<td class="table-input-label">手机号码</td>
				<td class="table-input-td"><input type="text" name="userVO.userMobile" value="${userForm.userVO.userMobile}" /></td>
			</tr>
			<tr>
				<input type="button" value="提交" onclick="saveUser();" />
			</tr>
		</table>
	</form>
</body>
</html>