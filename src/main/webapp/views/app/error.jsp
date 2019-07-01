<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<style easyui="true" type="text/css">
</style>
<script type="text/javascript">
function showDetail(){
	var exCauseObj = $("#exCause");
	exCauseObj.slideToggle(1000);
}
</script>
</head>
<body style="">
	<table class="t2" style="width: 80%;margin:80px auto;" border="1">
		<tr>
			<td>
				<div style="color:red;font-size:12px;">
					[温馨提示]<span <%-- style="cursor:pointer;" --%> onclick="showDetail();" >:</span>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div style="color:red;font-size:12px;">
					${exMsg}
				</div><br>
				<div id="exCause" style="color:red;font-size:12px;display: none;">
					${exCause}
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="返回" id="btnErrorBack" onclick="this.remove();history.back();self.parent.mainFrame.location.reload();" />
			</td>
		</tr>	
	</table>
</body>
</html>