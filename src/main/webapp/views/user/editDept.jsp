<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/views/user/editDept.js?version=${version}"></script>
<title>编辑部门信息</title>
<style type="text/css">
	div.content_wrap {width: 100%;height:80%;}
	div.content_wrap div.left{float: left;width: 20%;border: 1px solid #5ab0e2;margin:2px 2px}
	div.content_wrap div.right{float: right;width: 78%;}
	div.zTreeBackground {height:80%;text-align:left;}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<form id="deptForm" action="${ctx}/dept/saveDept" method="post" commandName="deptForm">
		<div class="content_wrap">
			<div class="zTreeBackground left">
					<input type="text" id="key" value="" class="empty searchInput" /><br/>
	     			<ul  style="margin-bottom:2px;margin-left:25px;" treeId="${deptForm.deptVO.deptId}" id="deptTree" class="ztree"></ul>
			</div>
			<div class="right">
				<table cellpadding="0" cellspacing="0" border="0" class="table-input">
					<tr>
						<td width="15%" class="table-input-label">部门:<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="hidden" name="deptVO.deptId" value="${deptForm.deptVO.deptId}" />
							<input name="deptVO.deptName" id="deptVO.deptName" type="text" ltype="text"
								value="${deptForm.deptVO.deptName }" class="required" 
								validate="{required:true,minlength:1,maxlength:30}" />
						</td>
					</tr>
					<tr>
						<td class="table-input-label">描述:</td>
						<td class="table-input-td">
							<textarea name="deptVO.description" id="deptVO.description" clos="200" rows="3" warp="virtual" >${deptForm.deptVO.description }</textarea>
						</td>
					</tr>
					<tr>
						<td class="table-input-label">状态:<label style="color:red;">*</label></td>
						<td class="table-input-td" >
							<select name="deptVO.deptStatus" id="deptVO.deptStatus" ltype="select" value="${deptForm.deptVO.deptStatus }">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="table-input-label">父名称</td>
						<td class="table-input-td">
							<input type="hidden" name="deptVO.parentId" />
							<input style="border: none;" type="text" name="deptVO.parentName" size="50" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><br>
							<input style="display:none;" id="btnAdd" type="button" value="新增子项" />
							<input type="submit" value="保存" id="Button1" class="permission DeptService$update" />
							<c:if test="${null != deptForm.deptVO.deptId}">
								<input class="permission DeptService$delete" id="btnDelete" type="button" value="删除" />
							</c:if>
							<input type="button" value="返回" onclick="location.href='${ctx}/dept/listDept';" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br /> 
	</form>
</body>
</html>