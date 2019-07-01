<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑数据字典</title>
<script type="text/javascript" src="${ctx}/views/dict/editDictionary.js?version=${version}"></script>
<style type="text/css">
	div.content_wrap {width: 100%;height:100%;}
	div.content_wrap div.left{float: left;width: 20%;border: 1px solid #5ab0e2;margin:2px 2px}
	div.content_wrap div.right{float: right;width: 78%;}
	div.zTreeBackground {height:80%;text-align:left;}
</style>
</head>
<body>
	<form id="dictForm" action="${ctx}/dict/saveDict" method="post" commandName="dictForm">
		<input type="hidden" name="dictionaryVO.dictionaryId" value="${dictForm.dictionaryVO.dictionaryId}" />
		<div class="content_wrap">
			<div class="zTreeBackground left">
					<input type="text" id="key" value="" class="empty" /><br/>
	     			<ul  style="margin-bottom:2px;margin-left:25px;" treeId="${dictForm.dictionaryVO.dictionaryId}" id="dictTree" class="ztree"></ul>
			</div>
			<div class="right">
				<table cellpadding="0" cellspacing="0" border="0" class="table-input">
					<tr>
						<td width="15%" class="table-input-label">类别名称
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<input type="text" name="dictionaryVO.dictionaryName" class="required" />
							<a id="dictItemLink" href="javascript:searchDictItem();">查看子项</a>
						</td>
					</tr>
					<tr>
						<td class="table-input-label">描述</td>
						<td class="table-input-td">
							<input type="text" name="dictionaryVO.description" /></td>
					</tr>
					<tr>
						<td class="table-input-label">状态
							<label style="color:red;">*</label></td>
						<td class="table-input-td">
							<select id="dictionaryStatus" name="dictionaryVO.dictionaryStatus"
								class="select required" lookup="<%=DictionaryVO.DICT_STATUS %>">
							</select></td>
					</tr>
					<tr>
						<td class="table-input-label">父名称</td>
						<td class="table-input-td">
							<input type="hidden" name="dictionaryVO.parentId" />
							<input style="border: none;" type="text" name="dictionaryVO.parentName" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><br>
							<input style="display:none;" id="btnAdd" type="button" value="新增子项" />
							<input class="permission DictService$update" type="submit" value="保存" />
							<c:if test="${null != dictForm.dictionaryVO.dictionaryId}">
								<input class="permission DictService$delete" id="btnDelete" type="button" value="删除" />
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