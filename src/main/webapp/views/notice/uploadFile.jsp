<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<title>上传文件</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
//
function validateFile() {
	var file1 = document.all.file1;
	if("underfined"!=typeof(file1) && file1.value ==""){
		alert("导入的文件不能为空！");
		return false;
	}
	if (!chk_ext(file1.value,"XLS","XLSX")) {
		alert("请导入excel格式的文件！");
		file1.value = "";
		return false;
	}
	return true;
}

function uploadUser(){
	if(!validateFile()) return;
	 $("#fileForm").submit();
	/* var ajaxUrl = getContextPath()+"/UploadFile";
	$.ajax({
        cache: false,
        type: "POST",
        url: ajaxUrl,
        data:$('#fileForm').serialize(),
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            //$("#commonLayout_appcreshi").parent().html(data);
            alert(data);
        }
    });  */
}

function winclose() {
	var userAgent = window.navigator.userAgent;
	if (userAgent.indexOf("Firefox") != -1) {  
		window.location.href = 'about:blank';   
	} 
	window.returnValue = "1";
	window.close();
}

</script>
</head>
<!-- <base target="_self"> -->
<body>
	<table align="center" style="padding-top: 100px;">
		<tr>
			<td>
				<form id="fileForm" enctype="multipart/form-data" target="uploadFrame" action="${ctx}/UploadFile" method="post">
					<input type="file" name="file1" id="file1" >
					<a href="${ctx}/DownloadFile" target="uploadFrame">下载模板</a> 
					<!-- <input type="file" name="file2"> <br>
					<input type="file" name="file3"> -->
				</form></td>
		</tr>
		<tr align="right">
			<td><span style="color: red;" id="">文件格式:xls,xlsx</span>
			</td>
		</tr>
		<tr align="right">
			<td><input type="button" value="导入" onclick="uploadUser();" /> 
			    <input type="button" value="关闭" onclick="winclose();" />
			</td>
		</tr>
	</table>
	<iframe name="uploadFrame" style="border:0px;" height="60px;" style="display:"></iframe>
</body>
</html>