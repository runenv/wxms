<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/dialog/editDialog.js?version=${version}"></script>
<title>编辑弹窗</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<form id="dialogForm" action="${ctx}/dialog/saveDialog" method="post" commandName="dialogForm">
	<input type="hidden" id="dialogVO.dialogId" name="dialogVO.dialogId" value="${dialogForm.dialogVO.dialogId}" />
	<input type="hidden" id="dialogVO.orderNum" name="dialogVO.orderNum" value="${dialogForm.dialogVO.orderNum}" />
	<input type="hidden" id="dialogVO.dialogStatus" name="dialogVO.dialogStatus" value="${dialogForm.dialogVO.dialogStatus}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="dialogVO.oldTitle" id="dialogVO.oldTitle" type="hidden"
					value="${dialogForm.dialogVO.title}" />
				<input name="dialogVO.title" id="dialogVO.title" type="text" size="50"
					value="${dialogForm.dialogVO.title}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">弹窗背景图:</td>
			<td class="table-input-td">
				<div id="imgesMsg"></div>
				<table class="t2" style="width:100%;">
					<tr>
						<td colspan="2">
							<input type="hidden" name="dialogVO.imgUrl" id="dialogVOImgUrl" value="${dialogForm.dialogVO.imgUrl}" />
							<input type="hidden" name="dialogVO.imgUrlPath" id="dialogVOImgUrlPath" />
							<img width="640px;" height="140px;" id="uploadImage" src="${ctx}/file/fileDownload/${dialogForm.dialogVO.imgUrl}"><br>
							<input type="file" id="myDialogImage" name="myfiles" onchange="$('#dialogVOImgUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Dialog.ajaxFileUpload('myDialogImage','uploadImage','imgesMsg')" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">弹窗内容:</td>
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
				<input name="dialogVO.startDate" id="dialogVOStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();"
					value="${dialogForm.dialogVO.startDate}" type="text" class="required" /> 至
				<input name="dialogVO.endDate" id="dialogVOEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'dialogVOStartDate\')}'})"
					onchange="this.focus();this.blur();"
					value="${dialogForm.dialogVO.endDate}" type="text" class="required" />	
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
				<input name="dialogVO.openUrl" id="dialogVO.openUrl"  size="50"
					value="${dialogForm.dialogVO.openUrl}" type="text" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="dialogVO.classId" id="dialogVOClassId"
					defaultValue="${dialogForm.dialogVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="dialogVO.lotteryId" id="dialogVOLotteryId"
					defaultValue="${dialogForm.dialogVO.lotteryId}">
					
				</select>
				<span id="noticeIdTR" style="display:none;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告位ID:
					<input name="dialogVO.noticeId" id="dialogVONoticeId"  size="30" class="required digits"
							value="${dialogForm.dialogVO.noticeId}" type="text" />
				</span>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="dialogVO.targetUser" name="dialogVO.targetUser" value="${dialogForm.dialogVO.targetUser}" />
				<input type="hidden" name="dialogVO.targetUserPath" id="dialogVOTargetUserPath" />
				<div id="userMsg"></div>
				<input type="file" id="targetUser" name="myfiles"
					onchange="$('#dialogVOTargetUserPath').val(this.value)" /><br>
				<input type="button" id="targetUserBtn" value="导入Excel" />
				<c:if test="${dialogForm.dialogVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;border-top:1px solid #5ab0e2;">
						<a href="${ctx}/file/fileDownload/${dialogForm.dialogVO.targetUserAttachVO.attachId}">${dialogForm.dialogVO.targetUserAttachVO.attachName}</a>
						<img style="cursor: pointer;" id="targetUserImg" alt="${dialogForm.dialogVO.targetUserAttachVO.attachId}" src="${ctx}/images/bg-cancel.png" />
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<input name="dialogVO.testUser" id="dialogVO.testUser"  size="50"
					value="${dialogForm.dialogVO.testUser}" type="text" />
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
				<input type="checkbox" id="checkAllChannelCheckbox"
					onclick="FormUtils.checkedAll(':checkbox[alt*=\'channel-\']', this.checked);" />全选</td>
		</tr>
		<tbody id="allPartner">
		<c:forEach items="${dialogForm.dialogVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
			<tr>
				<td width="8%;">
					<input type="checkbox" alt="channel-" value=""
						onclick="FormUtils.checkedAll(':checkbox[alt=\'channel-${partnerEntry.key}\']', this.checked);" />
						<a href="javascript:FormUtils.slideZone($('.itemPartner${varPartnerStatus.index}'),100);">${partnerEntry.key}</a>
				</td>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%;" class="itemPartner${varPartnerStatus.index}"><tr><td>
					<c:forEach items="${partnerEntry.value}" var="partner" varStatus="varStatus">
						<span style="display:inline-block;width:18%;">
							<input type="checkbox" name="dialogVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<input class="permission DialogService$update" type="button" value="提交" id="btnSaveDialog" />
		<input type="button" value="返回" onclick="location.href='${ctx}/dialog/listDialog';" />
	</div>
</form>
<ckeditor:replace replace="dialogVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>