<%@ page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>编辑角色信息</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#roleForm").validate({
			rules: {
				"roleVO.roleDescription":{
					lengthInUtf8Bytes:{
						param:[1,99]
					}
				}
			}
		});  
	}); 
</script>
</head>
<body>
	<form id = "roleForm" action="${ctx}/role/saveRole" method="post" commandName="roleForm">
		<div></div>
		<table cellpadding="0" cellspacing="0" border="0" class="table-input">
			<tr>
				<td width="15%" class="table-input-label">角色:<label style="color:red;">*</label></td>
				<td class="table-input-td">
					<input type="hidden" name="roleVO.roleId" value="${roleForm.roleVO.roleId}" />
					<input name="roleVO.roleName" id="roleVO.roleName" type="text" ltype="text"
						value="${roleForm.roleVO.roleName }" class="required" maxlength="5" />
				</td>
				
			</tr>
			<tr>
				<td class="table-input-label">角色描述:</td>
				<td class="table-input-td">
				    <textarea name="roleVO.roleDescription" id="roleVO.roleDescription" clos="200" rows="3" >${roleForm.roleVO.roleDescription }</textarea>
				</td>
			</tr>
			<c:if test="${roleForm.roleVO.roleId != null && '' != roleForm.roleVO.roleId}">
				<tr>
					<td class="table-input-label">状态:<label style="color:red;">*</label></td>
					<td class="table-input-td" >
						<select name="roleVO.roleStatus" id="roleVORoleStatus"
							defaultValue="${roleForm.roleVO.roleStatus}"
							class="select required" lookup="<%=DictionaryVO.DICT_STATUS %>"> 
						</select>
					</td>
				</tr>
			</c:if>
		</table>
		<br /> 
		<div style="text-align: center;">
			<input type="submit" value="提交" id="Button1" class="permission RoleService$update" /> 
			<input type="button" value="返回" onclick="location.href='${ctx}/role/listRole';" />
		</div>	
	</form>
</body>
</html>