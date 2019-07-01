<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑用户信息</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#userForm").validate({
		debug: false,
		rules: {
			"userVO.userName":{
				required: true,
				stringCheck:{},
				lengthInUtf8Bytes:{
					param:[1,48]
				}
			},
			"userVO.userAddress":{
				lengthInUtf8Bytes:{
					param:[1,100]
				}
			}
		}
	});
	$("#userVOUserName").blur(function(){
		//if(this.value == "" || $("#userVOUserId").val() !="")return;
		var oldUserName = $("#userVO\\.oldUserName").val();
		if(this.value == "")return;
		if($("#userVOUserId").val() !="" &&  this.value == oldUserName){
			$("#userVOUserName").removeClass("checkUserName error");
			$("#userForm").valid();
			return;
		}
		var userParam = "{\"userName\":\""+ this.value +"\"}";
		$.ajax({
	    	url:jsCtx+"/user/checkUserName",
	    	data:userParam,
	    	type:"POST",
	    	async:false,
	    	dataType:"json",
	    	contentType:'application/json;charset=UTF-8',
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data);
	    		if("undefined" != typeof(json) && json.statusCode != 0){
	    			$("#userVOUserName").addClass("checkUserName");
	    			$("#userForm").valid();	
	    		}else{
	    			$("#userVOUserName").removeClass("checkUserName error");
	    			$("#userForm").valid();	
	    		}
	    	},
	    	error:function(){
	    		try{
	    			console.log("check userName failure!");
	    		}catch(e){}
	    	}
	    });
	});
}); 
	
function getUserAge(){
	var brithday=$("#userVOUserBirthDay");
	if(brithday.val()=="")return;
	var age = getAge(brithday.val());
	$("#userVOUserAge").val(age);
}
function getAge(strBirthday){      
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];
    var d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth){
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0){
                    returnAge = ageDiff - 1;
                }else{
                    returnAge = ageDiff ;
                }
            }else{
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0){
                    returnAge = ageDiff - 1;
                }else{
                    returnAge = ageDiff ;
                }
            }
        }else{
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    return returnAge;//返回周岁年龄
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
	<br>
	<form id="userForm" action="${ctx}/user/save" method="post">
		<div>
			<table cellpadding="0" cellspacing="0" border="0" class="table-input">
				<tr>
					<td style="width: 8%;" class="table-input-label">姓名<label style="color:red;">*</label>:</td>
					<td class="table-input-td">
						<input type="hidden" name="userVO.userId" id="userVOUserId" value="${userForm.userVO.userId}" />
						<input type="hidden" name="userVO.userAge" id="userVOUserAge" 
							value="${userForm.userVO.userAge}" />
						<input name="userVO.oldUserName" id="userVO.oldUserName" type="hidden" 
							value="${userForm.userVO.userName}" />	
						<input name="userVO.userName" id="userVOUserName" type="text" 
							value="${userForm.userVO.userName}" class="required" 
							validate="{required:true,minlength:3,maxlength:10}" />
					</td>
					<td style="width: 8%;" class="table-input-label">姓别:<label style="color:red;">*</label></td>
					<td class="table-input-td">
						<c:choose>
							<c:when test="${userForm.userVO.userSex==1}">
								男<input type="radio" class="required"  name="userVO.userSex" value="1" checked="checked"/>
								女<input type="radio" class="required"  name="userVO.userSex" value="0" />
							</c:when>
							<c:otherwise>
								男<input type="radio" name="userVO.userSex" value="1" />
								女<input type="radio" name="userVO.userSex" value="0" checked="checked"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="table-input-label">地址:</td>
					<td class="table-input-td">
						<textarea cols="100" name="userVO.userAddress" id="userAddress"
							rows="2" class="l-textarea" style="width: 200px">${userForm.userVO.userAddress }</textarea>
					</td>
					<td class="table-input-label">出生日期<label style="color:red;">*</label>:</td>
					<td class="table-input-td">
						<input name="userVO.userBirthDay" id="userVOUserBirthDay"
						 	value="${userForm.userVO.userBirthDay }" type="text" 
						 	onchange="getUserAge();this.focus();this.blur();" class="required"
						 	onclick="WdatePicker({maxDate:'%y-%M-%d'})" />
					</td>
				</tr>
				<tr>
					<td class="table-input-label">邮箱:</td>
					<td class="table-input-td">
						<input name="userVO.userEmail" id="userVO.userEmail"
						 value="${userForm.userVO.userEmail }" class="email"
						 type="text" />
					</td>
					<td class="table-input-label">电话:</td>
					<td class="table-input-td">
						<input name="userVO.userPhone" id="userVO.userPhone" 
							value="${userForm.userVO.userPhone }" class="isPhone"
							type="text"   />
					</td>
				</tr>
				<tr>
					<td class="table-input-label">手机:</td>
					<td class="table-input-td">
						<input name="userVO.userMobile" id="userVO.userMobile" 
							value="${userForm.userVO.userMobile }" class="isMobile"
							type="text" />
					</td>
					<td class="table-input-label">邮编:</td>
					<td class="table-input-td">
						<input name="userVO.userZip" id="userVO.userZip" 
							value="${userForm.userVO.userZip }" class="isZipCode"
							type="text"  />
					</td>
				</tr>
				<tr>
					<td class="table-input-label">密码:<label style="color:red;">*</label></td>
					<td class="table-input-td">
						<input name="userVO.userPassword" id="userVO.userPassword" type="text" 
							value="${userForm.userVO.userPassword}" class="required isPwd" />
					</td>
					<td class="table-input-label">部门:<label style="color:red;">*</label></td>
					<td class="table-input-td">
						<select class="required" name="userVO.deptId" id="userVO.deptId" ltype="select" value="${userForm.userVO.deptId }">
							<option value="1071">地平线子公司</option>
						</select>
					</td>
				</tr> 
			</table><br />
			<div style="text-align: center;">
				 <input type="submit" value="提交" id="Button1" class="permission UserService$save" /> 
				 <input type="button" value="返回" onclick="location.href='${ctx}/user/listUser';" />
			</div>
		</div>	
	</form>
</body>
</html>