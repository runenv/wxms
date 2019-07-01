<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/advert/editAdvert.js?version=${version}"></script>
<title>查看资讯</title>
<script type="text/javascript">
function editAdvert(){
	$("#advertForm").attr('action',"${ctx}/advert/editAdvert");
	$("#advertForm").submit();
}
$(document).ready(function(){
	setTimeout(function(){
		$("input[name='advertVO\\.arrId']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='advertVO\\.directFlag']").each(function(){
			$(this).attr("disabled","disabled");
		});
		var editor = CKEDITOR.instances.advertVOContent;
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
	$('#uploadImage').click(function(){
		var href = jsCtx +'/advert/showImage?advertVO.advertId=${advertForm.advertVO.advertId}&type=1';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
	$('#uploadImage1').click(function(){
		var href = jsCtx +'/advert/showImage?advertVO.advertId=${advertForm.advertVO.advertId}&type=2';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
});

</script>
</head>
<body>
<form id="advertForm" action="${ctx}/advert/editAdvert" method="post" commandName="advertForm">
	<input type="hidden" id="advertVO.advertId" name="advertVO.advertId" value="${advertForm.advertVO.advertId}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${advertForm.advertVO.title}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">图片:</td>
			<td class="table-input-td">
				<div id="imgesMsg"></div>
				<table class="t2" style="width:100%;">
					<tr>
						<td>
							客户端配图（640*140）：<br>
							<input type="hidden" name="advertVO.imgUrl" id="advertVOImgUrl" value="${advertForm.advertVO.imgUrl}" />
							<img style="cursor:pointer;" width="640px;" height="140px;" id="uploadImage" src="${ctx}/file/fileDownload/${advertForm.advertVO.imgUrl}"><br>
						</td>
					</tr>
					<tr>
						<td>
							H5配图（640*240）：<br>
							<input type="hidden" name="advertVO.htmlUrl" id="advertVOHtmlUrl" value="${advertForm.advertVO.htmlUrl}" />
							<img style="cursor:pointer;" width="640px;" height="240px;" id="uploadImage1" src="${ctx}/file/fileDownload/${advertForm.advertVO.htmlUrl}"><br>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">内容:</td>
			<td class="table-input-td">
				<input name="advertVO.content" id="advertVOContent" type="text" size="40"
					value="${advertForm.advertVO.content}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">发布平台:</td>
			<td class="table-input-td" id="NOTICE_PLATFORM" defaultValue="${advertForm.advertVO.platform}">
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">有效期:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${advertForm.advertVO.startDate}" /> 至
				<c:out value="${advertForm.advertVO.endDate}" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">跳转类型:</td>
			<td class="table-input-td" class="table-input-td" id="ADVERT_DIRECT"
				defaultValue="${advertForm.advertVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR" style="display:none;">
			<td width="8%;" class="table-input-label">资讯打开URL:</td>
			<td class="table-input-td">
				<c:out value="${advertForm.advertVO.openUrl}" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="advertVO.classId" id="advertVOClassId" disabled="disabled"
					defaultValue="${advertForm.advertVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="advertVO.lotteryId" id="advertVOLotteryId" disabled="disabled"
					defaultValue="${advertForm.advertVO.lotteryId}">
				</select>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="advertVO.targetUser" name="advertVO.targetUser" value="${advertForm.advertVO.targetUser}" />
				<c:if test="${advertForm.advertVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;">
						<a href="${ctx}/file/fileDownload/${advertForm.advertVO.targetUserAttachVO.attachId}">${advertForm.advertVO.targetUserAttachVO.attachName}</a>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<c:out value="${advertForm.advertVO.testUser}" />
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
		<c:forEach items="${advertForm.advertVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
			<tr>
				<td width="8%;">
					<a href="javascript:FormUtils.slideZone($('.itemPartner${varPartnerStatus.index}'),100);">${partnerEntry.key}</a>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%;" class="itemPartner${varPartnerStatus.index}"><tr><td>
					<c:forEach items="${partnerEntry.value}" var="partner" varStatus="varStatus">
						<span style="display:inline-block;width:18%;">
							<input type="checkbox" name="advertVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<c:if test="${advertForm.advertVO.advertStatus!=1}">
			<input class="permission AdvertService$update" type="button" value="修改" id="btnSaveAdvert" onclick="editAdvert();" />
		</c:if>
		<input type="button" value="返回" id="btnBackAdvert" onclick="location.href='${ctx}/advert/listAdvert';" />
	</div>
</form>
<ckeditor:replace replace="advertVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>