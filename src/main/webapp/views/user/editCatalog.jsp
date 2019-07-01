<%@ page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>编辑栏目</title>
<script type="text/javascript" src="${ctx}/views/user/editCatalog.js?version=${version}"></script>
<style type="text/css">
	div.content_wrap {width: 100%;height:100%;}
	div.content_wrap div.left{float: left;width: 20%;border: 1px solid #5ab0e2;margin:2px 2px}
	div.content_wrap div.right{float: right;width: 78%;}
	div.zTreeBackground {height:80%;text-align:left;}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<form id="catalogForm" action="${ctx}/catalog/saveCatalog" method="post" commandName="catalogForm">
		<input type="hidden" name="catalogVO.catalogId" value="${catalogForm.catalogVO.catalogId}" />
		<div class="content_wrap">
			<div class="zTreeBackground left">
					<input type="text" id="key" value="" class="empty searchInput" /><br/>
	     			<ul  style="margin-bottom:2px;margin-left:25px;" treeId="${catalogForm.catalogVO.catalogId}" id="catalogTree" class="ztree"></ul>
			</div>
			<div class="right">
				<table cellpadding="0" cellspacing="0" border="0" class="table-input">
					<tr>
						<td width="15%" class="table-input-label">栏目名称
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="text" name="catalogVO.catalogName" class="required"
								value="${catalogForm.catalogVO.catalogName}" />
						</td>
					</tr>
					<tr>
						<td class="table-input-label">栏目值
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="text" name="catalogVO.catalogValue" class="required"
								value="${catalogForm.catalogVO.catalogValue}" />
						</td>
					</tr>
					<tr>
						<td class="table-input-label">栏目URL
							</td>
						<td class="table-input-td">
							<textarea rows="1" cols="50" name="catalogVO.catalogUrl" 
								>${catalogForm.catalogVO.catalogUrl}</textarea>
						</td>
					</tr>
					<tr>
						<td class="table-input-label">栏目描述</td>
						<td class="table-input-td">
							<textarea rows="1" cols="50" name="catalogVO.catalogDescription" 
								>${catalogForm.catalogVO.catalogDescription}</textarea>
						</td>
					</tr>
					<tr title="依次按序号、名称升序排列">
						<td class="table-input-label">排序号</td>
						<td class="table-input-td">
							<input class="digits" type="text" name="catalogVO.orderNum" /></td>
					</tr>
					<tr>
						<td class="table-input-label">状态
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<select id="catalogVOCatalogFlag" name="catalogVO.catalogFlag" defaultValue="${catalogForm.catalogVO.catalogFlag}"
								class="select required" lookup="<%=DictionaryVO.DICT_STATUS %>">
							</select></td>
					</tr>
					<tr>
						<td class="table-input-label">父名称</td>
						<td class="table-input-td">
							<input type="hidden" name="catalogVO.parentId" />
							<input style="border: none;" type="text" name="catalogVO.parentName" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="table-input-label">授予角色：</td>
						<td class="table-input-td">
							<div id="initRoleChk">
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><br>
							<input style="display:none;" id="btnAdd" type="button" value="新增子项" />
							<input class="permission CatalogService$update" type="submit" value="保存" />
							<c:if test="${null != catalogForm.catalogVO.catalogId}">
								<input class="permission CatalogService$delete" id="btnDelete" type="button" value="删除" />
							</c:if>
							<input type="button" value="返回" onclick="location.href='${ctx}/catalog/findCatalogList';" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>