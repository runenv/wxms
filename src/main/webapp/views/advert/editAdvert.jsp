<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/advert/editAdvert.js?version=${version}"></script>
<title>编辑资讯</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<form id="advertForm" action="${ctx}/advert/saveAdvert" method="post" commandName="advertForm">
	<input type="hidden" id="advertVO.advertId" name="advertVO.advertId" value="${advertForm.advertVO.advertId}" />
	<input type="hidden" id="advertVO.orderNum" name="advertVO.orderNum" value="${advertForm.advertVO.orderNum}" />
	<input type="hidden" id="advertVO.advertStatus" name="advertVO.advertStatus" value="${advertForm.advertVO.advertStatus}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="advertVO.oldTitle" id="advertVO.oldTitle" type="hidden" 
					value="${advertForm.advertVO.title}" />
				<input name="advertVO.title" id="advertVO.title" type="text" size="50"
					value="${advertForm.advertVO.title}" class="required" />
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
							<input type="hidden" name="advertVO.imgUrlPath" id="advertVOImgUrlPath" />
							<img width="640px;" height="140px;" id="uploadImage" src="${ctx}/file/fileDownload/${advertForm.advertVO.imgUrl}"><br>
							<input type="file" id="myAdvertImage" name="myfiles" onchange="$('#advertVOImgUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Advert.ajaxFileUpload('myAdvertImage','uploadImage','imgesMsg')" />
						</td>
					</tr>
					<tr>
						<td>
							H5配图（640*240）：<br>
							<input type="hidden" name="advertVO.htmlUrl" id="advertVOHtmlUrl" value="${advertForm.advertVO.htmlUrl}" />
							<input type="hidden" name="advertVO.htmlUrlPath" id="advertVOHtmlUrlPath" />
							<img width="640px;" height="240px;" id="uploadImage1" src="${ctx}/file/fileDownload/${advertForm.advertVO.htmlUrl}"><br>
							<input type="file" id="myAdvertImage1" name="myfiles"  onchange="$('#advertVOHtmlUrlPath').val(this.value)" /><br>
							<input type="button" value="上传图片" onclick="Advert.ajaxFileUpload('myAdvertImage1','uploadImage1','imgesMsg')" />
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
				<input name="advertVO.startDate" id="advertVOStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();"
					value="${advertForm.advertVO.startDate}" type="text" class="required" /> 至
				<input name="advertVO.endDate" id="advertVOEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'advertVOStartDate\')}'})"
					onchange="this.focus();this.blur();"
					value="${advertForm.advertVO.endDate}" type="text" class="required" />	
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
				<input name="advertVO.openUrl" id="advertVO.openUrl"  size="50"
					value="${advertForm.advertVO.openUrl}" type="text" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="advertVO.classId" id="advertVOClassId"
					defaultValue="${advertForm.advertVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="advertVO.lotteryId" id="advertVOLotteryId"
					defaultValue="${advertForm.advertVO.lotteryId}">
					
				</select>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="advertVO.targetUser" name="advertVO.targetUser" value="${advertForm.advertVO.targetUser}" />
				<input type="hidden" name="advertVO.targetUserPath" id="advertVOTargetUserPath" />
				<div id="userMsg"></div>
				<input type="file" id="targetUser" name="myfiles" 
					onchange="$('#advertVOTargetUserPath').val(this.value)" /><br>
				<input type="button" id="targetUserBtn" value="导入Excel" />
				<c:if test="${advertForm.advertVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;border-top:1px solid #5ab0e2;">
						<a href="${ctx}/file/fileDownload/${advertForm.advertVO.targetUserAttachVO.attachId}">${advertForm.advertVO.targetUserAttachVO.attachName}</a>
						<img style="cursor: pointer;" id="targetUserImg" alt="${advertForm.advertVO.targetUserAttachVO.attachId}" src="${ctx}/images/bg-cancel.png" />
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<input name="advertVO.testUser" id="advertVO.testUser"  size="50"
					value="${advertForm.advertVO.testUser}" type="text" />
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
		<c:forEach items="${advertForm.advertVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
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
							<input type="checkbox" name="advertVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<input class="permission AdvertService$update" type="button" value="提交" id="btnSaveAdvert" />
		<input type="button" value="返回" onclick="location.href='${ctx}/advert/listAdvert';" />
	</div>
</form>
<ckeditor:replace replace="advertVOContent" basePath="${ctx}/js/ckeditor/" />
</body>
</html>