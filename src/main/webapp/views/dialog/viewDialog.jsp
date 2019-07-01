<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/dialog/editDialog.js?version=${version}"></script>
<title>查看弹窗</title>
<script type="text/javascript">
function editDialog(){
	$("#dialogForm").attr('action',"${ctx}/dialog/editDialog");
	$("#dialogForm").submit();
}
$(document).ready(function(){
	setTimeout(function(){
		$("input[name='dialogVO\\.arrId']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='dialogVO\\.directFlag']").each(function(){
			$(this).attr("disabled","disabled");
		});
		var editor = CKEDITOR.instances.dialogVOContent;
	    editor.setReadOnly(true);
	    
	    $("input[name='dialogVersionChkAll']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='dialogVO\\.arrId2']").each(function(){
			$(this).attr("disabled","disabled");
		});
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
	$('#uploadImage').click(function(){
		var href = jsCtx +'/dialog/showImage?dialogVO.dialogId=${dialogForm.dialogVO.dialogId}&type=1';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
	$('#uploadImage1').click(function(){
		var href = jsCtx +'/dialog/showImage?dialogVO.dialogId=${dialogForm.dialogVO.dialogId}&type=2';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
});

</script>
</head>
<body>
<form id="dialogForm" action="${ctx}/dialog/editDialog" method="post" commandName="dialogForm">
	<input type="hidden" id="dialogVO.dialogId" name="dialogVO.dialogId" value="${dialogForm.dialogVO.dialogId}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${dialogForm.dialogVO.title}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">图片:</td>
			<td class="table-input-td">
				<div id="imgesMsg"></div>
				<table class="t2" style="width:100%;">
					<tr>
						<td>
							<input type="hidden" name="dialogVO.imgUrl" id="dialogVOImgUrl" value="${dialogForm.dialogVO.imgUrl}" />
							<img style="cursor:pointer;" width="640px;" height="140px;" id="uploadImage" src="${ctx}/file/fileDownload/${dialogForm.dialogVO.imgUrl}"><br>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">内容:</td>
			<td class="table-input-td">
				<input name="dialogVO.content" id="dialogVOContent" type="text" size="40"
					value="${dialogForm.dialogVO.content}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">发布平台:</td>
			<td class="table-input-td" id="NOTICE_PLATFORM" defaultValue="${dialogForm.dialogVO.platform}">
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">有效期:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${dialogForm.dialogVO.startDate}" /> 至
				<c:out value="${dialogForm.dialogVO.endDate}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">跳转类型:</td>
			<td class="table-input-td" class="table-input-td" id="DIALOG_DIRECT"
				defaultValue="${dialogForm.dialogVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR" style="display:none;">
			<td width="8%;" class="table-input-label">弹窗打开URL:</td>
			<td class="table-input-td">
				<c:out value="${dialogForm.dialogVO.openUrl}" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="dialogVO.classId" id="dialogVOClassId" disabled="disabled"
					defaultValue="${dialogForm.dialogVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="dialogVO.lotteryId" id="dialogVOLotteryId" disabled="disabled"
					defaultValue="${dialogForm.dialogVO.lotteryId}">
				</select>
				<span id="noticeIdTR" style="display:none;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告位ID:
					<c:out value="${dialogForm.dialogVO.noticeId}" />		
				</span>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="dialogVO.targetUser" name="dialogVO.targetUser" value="${dialogForm.dialogVO.targetUser}" />
				<c:if test="${dialogForm.dialogVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;">
						<a href="${ctx}/file/fileDownload/${dialogForm.dialogVO.targetUserAttachVO.attachId}">${dialogForm.dialogVO.targetUserAttachVO.attachName}</a>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<c:out value="${dialogForm.dialogVO.testUser}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">版本:</td>
			<td class="table-input-td" id="PUSH_VERSION" defaultValue="${dialogForm.dialogVO.version}">
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
		<c:forEach items="${dialogForm.dialogVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
			<tr>
				<td width="8%;">
					<a href="javascript:FormUtils.slideZone($('.itemPartner${varPartnerStatus.index}'),100);">${partnerEntry.key}</a>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%;" class="itemPartner${varPartnerStatus.index}"><tr><td>
					<c:forEach items="${partnerEntry.value}" var="partner" varStatus="varStatus">
						<span style="display:inline-block;width:18%;">
							<input type="checkbox" name="dialogVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<c:if test="${dialogForm.dialogVO.dialogStatus!=1}">
			<input class="permission DialogService$update" type="button" value="修改" id="btnSaveDialog" onclick="editDialog();" />
		</c:if>
		<input type="button" value="返回" id="btnBackDialog" onclick="location.href='${ctx}/dialog/listDialog';" />
	</div>
</form>
<ckeditor:replace replace="dialogVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>