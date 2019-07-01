<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/bulletin/editBulletin.js?version=${version}"></script>
<title>查看公告</title>
<script type="text/javascript">
function editBulletin(){
	$("#bulletinForm").attr('action',"${ctx}/bulletin/editBulletin");
	$("#bulletinForm").submit();
}
$(document).ready(function(){
	setTimeout(function(){
		$("input[name='bulletinVO\\.directFlag']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='bulletinVO\\.arrId']").each(function(){
			$(this).attr("disabled","disabled");
		});
		var editor = CKEDITOR.instances.bulletinVOContent;
	    editor.setReadOnly(true);
	},800);
	var uploadImageDialog = parent.$('#showImageDiv',parent.document).dialog({    
	    title: '图片预览',    
	    width: 646,    
	    height: 240,
	    resizable:true,
	    maximizable:true,
	    closed: false,    
	    cache: false,
	    collapsible:true,
	    modal: true   
	});
	uploadImageDialog.dialog("close");
	$('#uploadImage1').click(function(){
		var href = jsCtx +'/bulletin/showImage?bulletinVO.bulletinId=${bulletinForm.bulletinVO.bulletinId}&type=2';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
});

</script>
</head>
<body>
<form id="bulletinForm" action="${ctx}/bulletin/editBulletin" method="post" commandName="bulletinForm">
	<input type="hidden" id="bulletinVO.bulletinId" name="bulletinVO.bulletinId" value="${bulletinForm.bulletinVO.bulletinId}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">H5公告标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${bulletinForm.bulletinVO.title}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">WAP公告标题:</td>
			<td class="table-input-td">
				<c:out value="${bulletinForm.bulletinVO.titleWap}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">是否跳转:</td>
			<td class="table-input-td" class="table-input-td" id="BULLETIN_DIRECT" 
				defaultValue="${bulletinForm.bulletinVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR">
			<td width="8%;" class="table-input-label">公告打开URL:</td>
			<td class="table-input-td">
				<c:out value="${bulletinForm.bulletinVO.openUrl}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">公告配图:</td>
			<td class="table-input-td">
				<div id="imgesMsg"></div>
				<table class="t2" style="width:100%;">
					<tr>
						<td colspan="2">
							H5配图（640*240）：<br>
							<input type="hidden" name="bulletinVO.htmlUrl" id="bulletinVOHtmlUrl" value="${bulletinForm.bulletinVO.htmlUrl}" />
							<img style="cursor:pointer;" alt="预览" width="640px;" height="240px;" id="uploadImage1" src="${ctx}/file/fileDownload/${bulletinForm.bulletinVO.htmlUrl}"><br>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">公告内容:</td>
			<td class="table-input-td">
				<input name="bulletinVO.content" id="bulletinVOContent" type="text" size="40"
					value="${bulletinForm.bulletinVO.content}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">有效期:</td>
			<td class="table-input-td">
				<c:out value="${bulletinForm.bulletinVO.startDate}" /> 至
				<c:out value="${bulletinForm.bulletinVO.endDate}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">发布平台:</td>
			<td class="table-input-td" id="BULLETIN_PLATFORM" defaultValue="${bulletinForm.bulletinVO.platform}">
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="bulletinVO.targetUser" name="bulletinVO.targetUser" value="${bulletinForm.bulletinVO.targetUser}" />
				<c:if test="${bulletinForm.bulletinVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;">
						<a href="${ctx}/file/fileDownload/${bulletinForm.bulletinVO.targetUserAttachVO.attachId}">${bulletinForm.bulletinVO.targetUserAttachVO.attachName}</a>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<c:out value="${bulletinForm.bulletinVO.testUser}" />
			</td>
		</tr>
	</table>
	<table class="t2" style="width:100%;">
		<tr>
			<td width="8%;" style="cursor:pointer;">
				<a href="javascript:FormUtils.slideZone($('#allPartner'),100);">显示渠道</a>
			</td>
			<td>
				</td>
		</tr>
		<tbody id="allPartner">
		<c:forEach items="${bulletinForm.bulletinVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
			<tr>
				<td width="8%;">
					<a href="javascript:FormUtils.slideZone($('.itemPartner${varPartnerStatus.index}'),100);">${partnerEntry.key}</a>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%;" class="itemPartner${varPartnerStatus.index}"><tr><td>
					<c:forEach items="${partnerEntry.value}" var="partner" varStatus="varStatus">
						<span style="display:inline-block;width:18%;">
							<input type="checkbox" name="bulletinVO.arrId1" alt="channel-${partnerEntry.key}"
								disabled="disabled"
								value="${partner.partnerid}" />${partner.partnername}
	                           <%-- <c:if test="${varStatus.index % 12 eq 0}">
									<br/> </c:if> --%>
						</span>
					</c:forEach>
					</td></tr></table>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div style="text-align: center;">
		<c:if test="${bulletinForm.bulletinVO.bulletinStatus!=1}">
			<input class="permission BulletinService$update" type="button" value="修改" id="btnSaveBulletin" onclick="editBulletin();" />
		</c:if>
		<input type="button" value="返回" id="btnBackBulletin" onclick="location.href='${ctx}/bulletin/listBulletin';" />
	</div>
</form>
<ckeditor:replace replace="bulletinVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>