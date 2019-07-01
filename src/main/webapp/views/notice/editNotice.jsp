<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/notice/editNotice.js?version=${version}"></script>
<title>编辑广告位</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<form id="noticeForm" action="${ctx}/notice/saveNotice" method="post" commandName="noticeForm">
	<input type="hidden" id="noticeVO.noticeId" name="noticeVO.noticeId" value="${noticeForm.noticeVO.noticeId}" />
	<input type="hidden" id="noticeVO.orderNum" name="noticeVO.orderNum" value="${noticeForm.noticeVO.orderNum}" />
	<input type="hidden" id="noticeVO.noticeStatus" name="noticeVO.noticeStatus" value="${noticeForm.noticeVO.noticeStatus}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="noticeVO.oldTitle" id="noticeVO.oldTitle" type="hidden"
					value="${noticeForm.noticeVO.title}" />
				<input name="noticeVO.title" id="noticeVO.title" type="text" size="50"
					value="${noticeForm.noticeVO.title}" class="required" />
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
							<input type="hidden" name="noticeVO.imgUrl" id="noticeVOImgUrl" value="${noticeForm.noticeVO.imgUrl}" />
							<input type="hidden" name="noticeVO.imgUrlPath" id="noticeVOImgUrlPath" />
							<img width="640px;" height="140px;" id="uploadImage" src="${ctx}/file/fileDownload/${noticeForm.noticeVO.imgUrl}"><br>
							<input type="file" id="myNoticeImage" name="myfiles" onchange="$('#noticeVOImgUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Notice.ajaxFileUpload('myNoticeImage','uploadImage','imgesMsg')" />
						</td>
					</tr>
					<tr>
						<td>
							H5配图（640*240）：<br>
							<input type="hidden" name="noticeVO.htmlUrl" id="noticeVOHtmlUrl" value="${noticeForm.noticeVO.htmlUrl}" />
							<input type="hidden" name="noticeVO.htmlUrlPath" id="noticeVOHtmlUrlPath" />
							<img width="640px;" height="240px;" id="uploadImage1" src="${ctx}/file/fileDownload/${noticeForm.noticeVO.htmlUrl}"><br>
							<input type="file" id="myNoticeImage1" name="myfiles"  onchange="$('#noticeVOHtmlUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Notice.ajaxFileUpload('myNoticeImage1','uploadImage1','imgesMsg')" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">内容:</td>
			<td class="table-input-td">
				<input name="noticeVO.content" id="noticeVOContent" type="text" size="40"
					value="${noticeForm.noticeVO.content}" class="required" />
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">发布平台:</td>
			<td class="table-input-td" id="NOTICE_PLATFORM" defaultValue="${noticeForm.noticeVO.platform}">
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">有效期:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="noticeVO.startDate" id="noticeVOStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();"
					value="${noticeForm.noticeVO.startDate}" type="text" class="required" /> 至
				<input name="noticeVO.endDate" id="noticeVOEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'noticeVOStartDate\')}'})"
					onchange="this.focus();this.blur();"
					value="${noticeForm.noticeVO.endDate}" type="text" class="required" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">跳转类型:</td>
			<td class="table-input-td" class="table-input-td" id="NOTICE_DIRECT"
				defaultValue="${noticeForm.noticeVO.directFlag}">
			</td>
		</tr>
		<tr id="openUrlTR" style="display:none;">
			<td width="8%;" class="table-input-label">广告位打开URL:</td>
			<td class="table-input-td">
				<input name="noticeVO.openUrl" id="noticeVO.openUrl"  size="50"
					value="${noticeForm.noticeVO.openUrl}" type="text" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="noticeVO.classId" id="noticeVOClassId"
					defaultValue="${noticeForm.noticeVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="noticeVO.lotteryId" id="noticeVOLotteryId"
					defaultValue="${noticeForm.noticeVO.lotteryId}">
					
				</select>
			</td>
		</tr>
		<%--
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="noticeVO.targetUser" name="noticeVO.targetUser" value="${noticeForm.noticeVO.targetUser}" />
				<input type="hidden" name="noticeVO.targetUserPath" id="noticeVOTargetUserPath" value="${noticeForm.noticeVO.targetUserPath}" />
				<div id="userMsg"></div>
				<input type="file" id="targetUser" name="myfiles"
					onchange="$('#noticeVOTargetUserPath').val(this.value)" />&nbsp;&nbsp;
				<input type="button" id="targetUserBtn" value="导入Excel" />
				<c:if test="${noticeForm.noticeVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;border-top:1px solid #5ab0e2;">
						<a href="${ctx}/file/fileDownload/${noticeForm.noticeVO.targetUserAttachVO.attachId}">${noticeForm.noticeVO.targetUserAttachVO.attachName}</a>
						<img style="cursor: pointer;" id="targetUserImg" alt="${noticeForm.noticeVO.targetUserAttachVO.attachId}" src="${ctx}/images/bg-cancel.png" />
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<input name="noticeVO.testUser" id="noticeVO.testUser"  size="50"
					value="${noticeForm.noticeVO.testUser}" type="text" />
			</td>
		</tr> --%>
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
		<c:forEach items="${noticeForm.noticeVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
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
							<input type="checkbox" name="noticeVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<input class="permission NoticeService$update" type="button" value="提交" id="btnSaveNotice" />
		<input type="button" value="返回" onclick="location.href='${ctx}/notice/listNotice';" />
	</div>
</form>
<ckeditor:replace replace="noticeVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>