<%@page import="com.ecp888.wxms.vo.dict.DictionaryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/app/basic.jsp"%>
<script type="text/javascript" src="${ctx}/views/push/editPush.js?version=${version}"></script>
<title>编辑Push</title>
<script type="text/javascript">

</script>
</head>
<body>
<form id="pushForm" action="${ctx}/push/savePush" method="post" commandName="pushForm">
	<input type="hidden" id="pushVO.pushId" name="pushVO.pushId" value="${pushForm.pushVO.pushId}" />
	<input type="hidden" id="pushVO.orderNum" name="pushVO.orderNum" value="${pushForm.pushVO.orderNum}" />
	<input type="hidden" id="pushVO.pushStatus" name="pushVO.pushStatus" value="${pushForm.pushVO.pushStatus}" />
	<input type="hidden" id="id_tags" name="pushVO.taskDate" value="${pushForm.pushVO.taskDate}" />
	<table cellpadding="0" cellspacing="0" border="0" class="table-input">
		<tr>
			<td width="8%;" class="table-input-label">Push标题:<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<input name="pushVO.oldTitle" id="pushVO.oldTitle" type="hidden"
					value="${pushForm.pushVO.title}" />
				<input name="pushVO.title" id="pushVO.title" type="text" size="40"
					value="${pushForm.pushVO.title}" class="required" />	
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">Push内容(<=70字):<label style="color:red;">*</label></td>
			<td class="table-input-td">
				<textarea name="pushVO.content" id="pushVOContent" rows="2" cols="40" class="required">${pushForm.pushVO.content}</textarea>	
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
				<input name="pushVO.startDatee" class="required" id="pushVOStartDatee" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();" type="text" />
				<input type="button" id="tag_search" value="设定" />
				<div id="selected-tags">
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
				<input name="pushVO.startDate" id="pushVOStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					onchange="this.focus();this.blur();"
					value="${pushForm.pushVO.startDate}" type="text" class="required" /> 至
				<input name="pushVO.endDate" id="pushVOEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'pushVOStartDate\')}'})"
					onchange="this.focus();this.blur();"
					value="${pushForm.pushVO.endDate}" type="text" class="required" />	
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
				<input class="isUrl" name="pushVO.openUrl" id="pushVO.openUrl"  size="50"
					value="${pushForm.pushVO.openUrl}" type="text" />
			</td>
		</tr>
		<tr id="classIdTR" style="display:none;">
			<td width="8%;" class="table-input-label">活动类型:</td>
			<td class="table-input-td">
				<select name="pushVO.classId" id="pushVOClassId"
					defaultValue="${pushForm.pushVO.classId}"
					lookup="<%=DictionaryVO.NOTICE_CLASS%>">
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转页面:
				<select name="pushVO.lotteryId" id="pushVOLotteryId"
					defaultValue="${pushForm.pushVO.lotteryId}">
					
				</select>
				<span id="noticeIdTR" style="display:none;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告位ID:
					<input name="pushVO.noticeId" id="pushVONoticeId"  size="30" class="required digits"
							value="${pushForm.pushVO.noticeId}" type="text" />
				</span>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">目标用户:</td>
			<td class="table-input-td">
				<input type="hidden" id="pushVO.targetUser" name="pushVO.targetUser" value="${pushForm.pushVO.targetUser}" />
				<input type="hidden" name="pushVO.targetUserPath" id="pushVOTargetUserPath" />
				<div id="userMsg"></div>
				<input type="file" id="targetUser" name="myfiles" 
					onchange="$('#pushVOTargetUserPath').val(this.value)" /><br>
				<input type="button" id="targetUserBtn" value="导入Excel" />
				<c:if test="${pushForm.pushVO.targetUserAttachVO.attachName != null}">
					<div id="targetUserDiv" style="padding:3px;border-top:1px solid #5ab0e2;">
						<a href="${ctx}/file/fileDownload/${pushForm.pushVO.targetUserAttachVO.attachId}">${pushForm.pushVO.targetUserAttachVO.attachName}</a>
						<img style="cursor: pointer;" id="targetUserImg" alt="${pushForm.pushVO.targetUserAttachVO.attachId}" src="${ctx}/images/bg-cancel.png" />
					</div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="8%;" class="table-input-label">测试用户:</td>
			<td class="table-input-td">
				<input name="pushVO.testUser" id="pushVO.testUser"  size="50"
					value="${pushForm.pushVO.testUser}" type="text" />
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
				<input type="checkbox" id="checkAllChannelCheckbox"
					onclick="FormUtils.checkedAll(':checkbox[alt*=\'channel-\']', this.checked);" />全选</td>
		</tr>
		<tbody id="allPartner">
		<c:forEach items="${pushForm.pushVO.partnerMap}" var="partnerEntry" varStatus="varPartnerStatus">
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
							<input type="checkbox" name="pushVO.arrId1" alt="channel-${partnerEntry.key}"
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
		<input class="permission PushService$update" type="button" value="提交" id="btnSavePush" />
		<input type="button" value="返回" onclick="location.href='${ctx}/push/listPush';" />
	</div>
</form>
</body>
</html>