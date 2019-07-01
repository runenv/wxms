<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>Content</title>
<script type="text/javascript">
   	function postSize() {
		var target = parent.postMessage ? parent : (parent.document.postMessage ? parent.document : undefined);
		if (typeof target != "undefined" && document.body.scrollHeight) {
		  	target.postMessage(document.getElementById("noticeContent").scrollHeight, "*");
		}
	}
</script>
</head>
<body onload="postSize();">
    <div id="noticeContent" class="content">
        ${noticeForm.noticeVO.content}
    </div>
</body>
</html>