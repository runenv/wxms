<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js" ></script>


<title>登陆</title>
<script type="text/javascript">
$(function() {
	$("#logo").css("margin-top", ($(window).height() - 460) / 2 + "px");
	$("input[name='username']").focus();
	/* $("#forget-password").click(function (e) {
		$("#userForm").hide();$("#forgotform").show(200);
		e.preventDefault();
	 });
	$("#forget-btn").click(function (e) {
	       $("#userForm").slideDown(200);
	       $("#forgotform").slideUp(200);
	       e.preventDefault();
	 }); */
	$("#userForm").submit(function(event) {
		event.preventDefault();
		var param = JSON.stringify($("#userForm").serializeObject());
		request("${ctx}/user/loginByAjax", "GET", param, true, success);
	});
});

//将$.ajax函数 转化成一个简单的接口  
function request(url, method, param, async, callback) {
	$.ajax({
		type : method,
		async : async,
		contentType : "application/json;charset=UTF-8",
		dataType : "json",
		url : url,
		data : param,
		success : callback,
		error : function(data) {
			try{
				console.log("failure!");
			}catch(e){}
		}
	});
}
function success(data) {
	try{
		console.log("success");
	}catch(e){}
}
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
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
	<FORM id="userForm" method="post" commandName="userForm" />
		<DIV id="logo">
			<%-- <IMG alt=HongCMS src="${ctx}/images/logo-login.png"> --%>
		</DIV>
		<DIV id="login">
			<P id="info">请输入用户名和密码</P>
			<DIV class="control-group">
				<SPAN class="icon-user"></SPAN>
				<INPUT type="text" name="userVO.userName" value="test1"	placeholder="userName">
			</DIV>
			<DIV class="control-group">
				<SPAN class="icon-lock"></SPAN>
				<INPUT type="password" name="userVO.userPassword" value="test1"	placeholder="Password">
			</DIV>
			<DIV class="remember-me">
				<!-- <INPUT id="rm" value="1" type="checkbox" name="remember">
				<LABEL	for="rm">记住我</LABEL> -->
				<A id="forget-password" href="/">忘记密码?</A>
			</DIV>
			<DIV class="login-btn">
				<INPUT id="login-btn" value="登 录" type="submit" name="submit">
			</DIV>
		</DIV>
		<DIV id="login-copyright">
			2014 Copyright &copy; 
		</DIV>
	</FORM>
</body>
</html>