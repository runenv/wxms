<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/bulletin/editBulletin.js?version=${version}"></script>
<title>编辑公告</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<form id="bulletinForm" action="${ctx}/bulletin/saveBulletin" method="post" commandName="bulletinForm">
	<input type="hidden" id="bulletinVO.bulletinId" name="bulletinVO.bulletinId" value="${bulletinForm.bulletinVO.bulletinId}" />
	<input type="hidden" id="bulletinVO.orderNum" name="bulletinVO.orderNum" value="${bulletinForm.bulletinVO.orderNum}" />
	<input type="hidden" id="bulletinVO.bulletinStatus" name="bulletinVO.bulletinStatus" value="${bulletinForm.bulletinVO.bulletinStatus}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">H5公告标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="bulletinVO.oldTitle" id="bulletinVO.oldTitle" type="hidden"
					value="${bulletinForm.bulletinVO.title}" />
				<input name="bulletinVO.title" id="bulletinVO.title" type="text" size="50"
					value="${bulletinForm.bulletinVO.title}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">WAP公告标题:</td>
			<td class="table-input-td">
				<input type="text" size="50" id="bulletinVO.titleWap" name="bulletinVO.titleWap" 
					value="${bulletinForm.bulletinVO.titleWap}" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">跳转类型:</td>
			<td class="table-input-td" class="table-input-td" id="BULLETIN_DIRECT" 
				defaultValue="${bulletinForm.bulletinVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR" style="display:none;">
			<td width="8%;" class="table-input-label">公告打开URL:</td>
			<td class="table-input-td">
				<input name="bulletinVO.openUrl" id="bulletinVO.openUrl"  size="50"
					value="${bulletinForm.bulletinVO.openUrl}" type="text" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="bulletinVO.classId" id="bulletinVOClassId"
					defaultValue="${bulletinForm.bulletinVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="bulletinVO.lotteryId" id="bulletinVOLotteryId"
					defaultValue="${bulletinForm.bulletinVO.lotteryId}">
					
				</select>
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
							<input type="hidden" name="bulletinVO.htmlUrlPath" id="bulletinVOHtmlUrlPath" />
							<img width="640px;" height="240px;" id="uploadImage1" src="${ctx}/file/fileDownload/${bulletinForm.bulletinVO.htmlUrl}"><br>
							<input type="file" id="myBulletinImage1" name="myfiles"  onchange="$('#bulletinVOHtmlUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Bulletin.ajaxFileUpload('myBulletinImage1','uploadImage1','imgesMsg')" />
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
				<input name="bulletinVO.startDate" id="bulletinVOStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();"
					value="${bulletinForm.bulletinVO.startDate}" type="text" class="required" /> 至
				<input name="bulletinVO.endDate" id="bulletinVOEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'bulletinVOStartDate\')}'})"
					onchange="this.focus();this.blur();"
					value="${bulletinForm.bulletinVO.endDate}" type="text" class="required" />	
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
				<input type="hidden" name="bulletinVO.targetUserPath" id="bulletinVOTargetUserPath" />
				<div id="userMsg"></div>
				<input type="file" id="targetUser" name="myfiles" 
					onchange="$('#bulletinVOTargetUserPath').val(this.value)" /><br>
				<input type="button" id="targetUserBtn" value="导入Excel" />
				<c:if test="${bulletinForm.bulletinVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;border-top:1px solid #5ab0e2;">
						<a href="${ctx}/file/fileDownload/${bulletinForm.bulletinVO.targetUserAttachVO.attachId}">${bulletinForm.bulletinVO.targetUserAttachVO.attachName}</a>
						<img style="cursor: pointer;" id="targetUserImg" alt="${bulletinForm.bulletinVO.targetUserAttachVO.attachId}" src="${ctx}/images/bg-cancel.png" />
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<input name="bulletinVO.testUser" id="bulletinVO.testUser"  size="50"
					value="${bulletinForm.bulletinVO.testUser}" type="text" />
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
		<c:forEach items="${bulletinForm.bulletinVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
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
							<input type="checkbox" name="bulletinVO.arrId1" alt="channel-${partnerEntry.key}"
								value="${partner.partnerid}" />${partner.partnername}
						</span>
					</c:forEach>
					</td></tr></table>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div style="text-align: center;">
		<input class="permission BulletinService$update" type="button" value="提交" id="btnSaveBulletin" />
		<input type="button" value="返回" onclick="location.href='${ctx}/bulletin/listBulletin';" />
	</div>
</form>
<ckeditor:replace replace="bulletinVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>