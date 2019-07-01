<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/push/editPush.js?version=${version}"></script>
<title>查看Push</title>
<style type="text/css">
#selected-tags a.selected-tag:hover {
	background: none repeat scroll 0 0 #efefef;
	background-postion: 3px 3px;
	background-repeat: no-repeat;
	cursor: pointer;
	padding-left: 18px;
}
</style>
<script type="text/javascript">
function editPush(){
	$("#pushForm").attr('action',"${ctx}/push/editPush");
	$("#pushForm").submit();
}
$(document).ready(function(){
	setTimeout(function(){
		$("input[name='pushVO\\.pushType']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='pushVO\\.arrId']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='pushVO\\.directFlag']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='pushVersionChkAll']").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("input[name='pushVO\\.arrId2']").each(function(){
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
		var href = jsCtx +'/push/showImage?pushVO.pushId=${pushForm.pushVO.pushId}&type=1';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
	$('#uploadImage1').click(function(){
		var href = jsCtx +'/push/showImage?pushVO.pushId=${pushForm.pushVO.pushId}&type=2';
		uploadImageDialog.dialog('refresh', href);
		uploadImageDialog.dialog("open");
	});
	$("#selected-tags").unbind("click");
});

</script>
</head>
<body>
<form id="pushForm" action="${ctx}/push/editPush" method="post" commandName="pushForm">
	<input type="hidden" id="pushVO.pushId" name="pushVO.pushId" value="${pushForm.pushVO.pushId}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">Push标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${pushForm.pushVO.title}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">Push内容(<=70字):<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${pushForm.pushVO.content}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">推送类型:</td>
			<td class="table-input-td" id="PUSH_TYPE" defaultValue="${pushForm.pushVO.pushType}">
			</td>
		</tr>
		<tr id="pushDateTR" style="display:none;">
			<td width="8%;" class="table-input-label">推送时间:</td>
			<td class="table-input-td">
				<div id="selected-tags" disabled="disabled">
					<c:forEach items="${pushForm.pushVO.taskDateArray}" var="taskDateItem">
						<a class="selected-tag">${taskDateItem}</a>
					</c:forEach>
				</div>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">客户端名称:</td>
			<td class="table-input-td" id="PUSH_PLATFORM" defaultValue="${pushForm.pushVO.platform}">
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">有效期:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<c:out value="${pushForm.pushVO.startDate}" /> 至 
				<c:out value="${pushForm.pushVO.endDate}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">跳转类型:</td>
			<td class="table-input-td" class="table-input-td" id="PUSH_DIRECT"
				defaultValue="${pushForm.pushVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR" style="display:none;">
			<td width="8%;" class="table-input-label">Push打开URL:</td>
			<td class="table-input-td">
				<c:out value="${pushForm.pushVO.openUrl}" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="pushVO.classId" id="pushVOClassId" disabled="disabled"
					defaultValue="${pushForm.pushVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="pushVO.lotteryId" id="pushVOLotteryId" disabled="disabled"
					defaultValue="${pushForm.pushVO.lotteryId}">
					
				</select>
				<span id="noticeIdTR" style="display:none;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告位ID:
					<c:out value="${pushForm.pushVO.noticeId}" />		
				</span>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="pushVO.targetUser" name="pushVO.targetUser" value="${pushForm.pushVO.targetUser}" />
				<c:if test="${pushForm.pushVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;">
						<a href="${ctx}/file/fileDownload/${pushForm.pushVO.targetUserAttachVO.attachId}">${pushForm.pushVO.targetUserAttachVO.attachName}</a>
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<c:out value="${pushForm.pushVO.testUser}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">推送版本:</td>
			<td class="table-input-td" id="PUSH_VERSION" defaultValue="${pushForm.pushVO.version}">
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
		<c:forEach items="${pushForm.pushVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
			<tr>
				<td width="8%;">
					<a href="javascript:FormUtils.slideZone($('.itemPartner${varPartnerStatus.index}'),100);">${partnerEntry.key}</a>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%;" class="itemPartner${varPartnerStatus.index}"><tr><td>
					<c:forEach items="${partnerEntry.value}" var="partner" varStatus="varStatus">
						<span style="display:inline-block;width:18%;">
							<input type="checkbox" name="pushVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<c:if test="${pushForm.pushVO.pushStatus!=1}">
			<input class="permission PushService$update" type="button" value="修改" id="btnSavePush" onclick="editPush();" />
		</c:if>
		<input type="button" value="返回" id="btnbackPush" onclick="location.href='${ctx}/push/listPush';" />
	</div>
</form>
</body>
</html>