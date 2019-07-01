<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>Content</title>
<script type="text/javascript">
</script>
</head>
<body>
    <div id="advertImage">
        <c:if test="${advertForm.advertVO.imgUrl != '' }">
        	<img src="${ctx}/file/fileDownload/${advertForm.advertVO.imgUrl}">
        </c:if>
        <c:if test="${advertForm.advertVO.htmlUrl != '' }">
        	<img src="${ctx}/file/fileDownload/${advertForm.advertVO.htmlUrl}">
        </c:if>
    </div>
</body>
</html>