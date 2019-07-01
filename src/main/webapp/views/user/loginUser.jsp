<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/app/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/messages_cn.js"></script>
<title>登陆</title>
<style type="text/css">
BODY {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-FAMILY: "Arial";
	BACKGROUND: url(../images/bg-loginbody.png) #3c3c3c;
	COLOR: #222;
	FONT-SIZE: 13px;
	PADDING-TOP: 0px
}
.hide {
	DISPLAY: none
}

IMG {
	BORDER-BOTTOM: 0px;
	BORDER-LEFT: 0px;
	BORDER-TOP: 0px;
	BORDER-RIGHT: 0px
}
/**validate信息提示区**/
label.error{
	position:absolute;
	display:block;
	z-index:900;
	background:#ffcc66;
	box-shadow:0px 0px 4px #000;
	-moz-box-shadow:0px 0px 4px #000;
	-webkit-box-shadow:0px 0px 4px #000;
	padding:3px 6px;
	border-radius:4px;
	-moz-border-radius:4px;
	webkit-border-radius:4px;
	filter:alpha(opacity:80);
	opacity:0.80;
	-width:150px;
	white-space:normal !important;
	max-width:400px;
	min-width:100px;
	overflow:visible !important;
	word-break:break-all !important;
	min-height:16px;
	line-height:16px !important;
	width:auto !important;
}
label.error span.light{
	color:red;
	font-weight:bold;
}
input.error,textarea.error,select.error{
	background-color:#ffcc66;
	border-color:#ff6600;
	filter:alpha(opacity:80);
	opacity:0.80;
}
</style>
<script type="text/javascript">
function loginUser(){
	if(!$("#userForm").valid())return;
	var loginMsgObj=$("#info");
	var doLoginUrl = "${ctx}/user/validateLogin";
	var param=JSON.stringify($("#userForm").serializeObject());
	$("#loginBtn").attr("disabled","disabled");
	$("#loginBtn").css("background","#EAEAEA");
	$.ajax({
    	url:doLoginUrl,
    	data:param,
    	type:"POST",
    	async:false,
    	dataType:"json",
    	contentType:'application/json;charset=UTF-8',
    	timeout:10000,
    	success:function(data){
    		var json = eval(data);
    		if("undefined" != typeof(json) && json.statusCode == 0){
    			var userForm = document.getElementById("userForm");
    			userForm.action = "${ctx}/user/login";
    			userForm.submit(); 
    		}else{
    			$("#loginBtn").removeAttr("disabled");
    			$("#loginBtn").css("background","#f56c06");
    			loginMsgObj.html(json.errorMsg);
    		}
    	},
    	error:function(){
    		try{
    			$("#loginBtn").removeAttr("disabled");
    			$("#loginBtn").css("background","#f56c06");
    			loginMsgObj.html("<span style=\"color:red;\">登录异常!</span>");
    			console.log("login failure!");
    		}catch(e){}
    	}
    });
}
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
</script>
</head>
<body>
	<form action="${ctx}/user/login" id="userForm" method="post" commandName="userForm" target="_parent">
		<div id="logo">
			<%-- <IMG alt=HongCMS src="${ctx}/images/logo-login.png"> --%>
		</div>
		<div id="login">
			<P id="info">请输入用户名和密码</P>
			<div class="control-group">
				<SPAN class="icon-user"></SPAN>
				<INPUT type="text" name="userName" class="required" placeholder="用户名">
			</div>
			<div class="control-group">
				<SPAN class="icon-lock"></SPAN>
				<INPUT type="password" name="userPassword" class="required" placeholder="密码">
			</div>
			<div class="remember-me">
				<%-- <INPUT id="rm" value="1" type="checkbox" name="remember">
				<LABEL	for="rm">记住我</LABEL> 
				<A id="forget-password" href="/">忘记密码?</A>--%>
			</div>
			<div class="login-btn">
				<INPUT id="loginBtn" value="登 录" name="loginBtn" type="button" onclick="loginUser();" />
			</div>
		</div>
		<%-- <div id="login-copyright">
			2014 Copyright &copy; 
		</div> --%>
	</form>
</body>
</html>