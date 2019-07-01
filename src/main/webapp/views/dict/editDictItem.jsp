<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>编辑数据字典</title>
<script src="${ctx}/views/dict/editDictItem.js?version=${version}" type="text/javascript"></script>
<style type="text/css">
	div.content_wrap {width: 100%;height:90%;}
	div.content_wrap div.left{float: left;width:20%;height:500px;border: 1px solid #5ab0e2;margin:2px 2px;overflow: scroll;}
	div.content_wrap div.right{float:right;width:78%;}
	div.zTreeBackground {height:96%;text-align:left;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#dictItemForm").validate();  
	});  

</script>
</head>
<body>
	<form id="dictItemForm" action="${ctx}/dict/saveDictItem" method="post" commandName="dictForm">
		<input type="hidden" name="dictItemVO.itemId" value="${dictForm.dictItemVO.itemId}" />
		<div class="content_wrap">
			<div class="zTreeBackground left">
					<input type="text" id="key" class="empty searchInput" size="32" /><br/>
	     			<ul style="margin-bottom:2px;margin-left:25px;" treeId="${dictForm.dictItemVO.itemId}" id="dictItemTree" class="ztree"></ul>
			</div>
			<div class="right">
				<table cellpadding="0" cellspacing="0" border="0" class="table-input">
					<tr>
						<td width="15%" class="table-input-label">子项名称
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="text" class="required" name="dictItemVO.itemName" value="${dictForm.dictItemVO.itemName}" />
						</td>
					</tr>
					<tr>
						<td class="table-input-label">子项编码
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="text" class="required" name="dictItemVO.itemCode" value="${dictForm.dictItemVO.itemCode}"/></td>
					</tr>
					<tr>
						<td class="table-input-label">父项类别</td>
						<td class="table-input-td">
							<input type="hidden" name="dictItemVO.dictionaryId"  value="${dictForm.dictItemVO.dictionaryId}"/>
							<input type="text" name="dictItemVO.dictionaryName" value="${dictForm.dictItemVO.dictionaryName}" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="table-input-label">描述</td>
						<td class="table-input-td">
							<input type="text" name="dictItemVO.description" value="${dictForm.dictItemVO.description}"/></td>
					</tr>
					<tr>
						<td class="table-input-label">状态
							<label style="color:red;">*</label></td> 
						<td class="table-input-td">
							<select id="dictItemVOItemStatus" name="dictItemVO.itemStatus" defaultValue="${dictForm.dictItemVO.itemStatus}"
								class="select required" lookup="<%=DictionaryVO.DICT_STATUS %>">
							</select>
							</td>
					</tr>
					<tr>
						<td class="table-input-label">父名称</td>
						<td class="table-input-td">
							<input type="hidden" name="dictItemVO.parentId" value="${dictForm.dictItemVO.parentId}" />
							<input style="border: none;" type="text" name="dictItemVO.parentName" value="${dictForm.dictItemVO.parentName}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><br>
							<input style="display:none;" id="btnAdd" type="button" value="新增子项" />
							<input class="permission DictService$updateItem" type="submit" value="保存" />
							<c:if test="${null != dictForm.dictItemVO.itemId}">
								<input class="permission DictService$deleteItem" id="btnDelete" type="button" value="删除" />
							</c:if>
							<input type="button" value="返回" onclick="location.href='${ctx}/dict/findDictList';" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>