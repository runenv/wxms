<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑用户信息</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#userRoleForm").validate(); 
	$('.lookup2').each(function(){
		var lookupId=$(this).attr("id");
		var lookup = $(this).attr("lookup");
		if(lookup=="role"){
			var lookupUrl=jsCtx+"/services/user/role/lookupRole";
			$.getJSON(lookupUrl,function(data) {
		       	var json = eval(data.Rows);
		       	var op="<option value=''>--------</option>";
		       	//$("#"+lookupId).append(op);
		       	$.each(json, function(i,item){
		           	op="<option value='"+item.roleId+"'>"+item.roleName+"</option>";
		           	$("#"+lookupId).append(op);
	           });
	       }); 
		}
	});
	setTimeout(function(){
		var $userRoleId=$("input[name='userVO.userRoleProgramVO.userRoleProgramId']");
		if($userRoleId.val()!=""){
			findRole($userRoleId.val());
		}
	},1000);
});

function findRole(userRoleId){
	var dictUrl=jsCtx+"/services/user/userRole/getUserRole/"+userRoleId;
	$.ajax({
    	url:dictUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var json = eval(data.Rows);
    		$("#cmbUserRole").val(json.roleVO.roleId);
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
</script>
</head>
<body>
	<form id="userRoleForm" action="${ctx}/user/saveUserRole" method="post">
		<input type="hidden" name="userVO.userId" value="${userForm.userVO.userId}"/>
		<input type="hidden" name="userVO.userRoleProgramVO.userId" value="${userForm.userVO.userRoleProgramVO.userId }">
		<input type="hidden" name="userVO.userRoleProgramVO.userRoleProgramId" value="${userForm.userVO.userRoleProgramVO.userRoleProgramId }">
		<div></div>
		<table cellpadding="0" cellspacing="0" border="0" class="table-input">
			<tr>
				<td class="table-input-label" style="width:20%">角色:</td>
				<td class="table-input-td">
					<select id="cmbUserRole" name="userVO.userRoleProgramVO.roleVO.roleId"
					 	class="required lookup2" lookup="role"></select>
				</td>
				
			</tr>
			<tr style="display:none;">
				<td class="table-input-label">program:</td>
				<td class="table-input-td">
					无
				</td>
			</tr>
		</table>
		<br />
		<div style="text-align: center;"> 
			<input type="submit" value="提交" id="Button1" class="permission UserRoleProgramService$update" /> 
			<input type="button" value="返回" onclick="location.href='${ctx}/user/listUserRole?userVO.userId=${userForm.userVO.userId}';"
			class="button-back" />
		</div>	
	</form>
</body>
</html>