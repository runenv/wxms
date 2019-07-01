/**
* jQuery Validate扩展验证方法
* @author ecp875
* @since 2014-10-08
*/
jQuery.validator.addMethod("isRequired", function(value, element) {
	return $.validator.prototype.optional(element) || ""!=value; 
}, $.validator.format("该字段不能为空"));

jQuery.validator.addMethod("checkUserName", function(value, element) {
	return this.optional(element) || false; 
}, $.validator.format("用户名已经存在"));

jQuery.validator.addMethod("checkTitle", function(value, element) {
	return this.optional(element) || false; 
}, $.validator.format("标题已经存在"));

jQuery.validator.addMethod("lengthInUtf8Bytes", function(value, element,param) {
	var utf8Lenth = lengthInUtf8Bytes(value);    
	return this.optional(element) || (utf8Lenth >= param[0] && utf8Lenth <= param[1]); 
}, $.validator.format("请输入{0}-{1}之间的字符(一个汉子占3个字符)"));

// 判断浮点数value是否大于或等于0
jQuery.validator.addMethod("isFloatGteZero", function(value, element) { 
     value=parseFloat(value);      
     return this.optional(element) || value>=0;       
}, "浮点数必须大于或等于0");

//判断浮点型  
jQuery.validator.addMethod("isFloat", function(value, element) {       
     return this.optional(element) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
}, "只能包含数字、小数点等字符"); 
 
// 匹配integer
jQuery.validator.addMethod("isInteger", function(value, element) {       
     return this.optional(element) || (/^[-\+]?\d+$/.test(value) && parseInt(value)>=0);       
}, "匹配integer");  
 
// 判断数值类型，包括整数和浮点数
jQuery.validator.addMethod("isNumber", function(value, element) {       
     return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
}, "匹配数值类型，包括整数和浮点数");  

// 只能输入[0-9]数字
jQuery.validator.addMethod("isDigits", function(value, element) {       
     return this.optional(element) || /^\d+$/.test(value);       
}, "只能输入0-9数字"); 
    
// 判断中文字符 
jQuery.validator.addMethod("isChinese", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
}, "只能包含中文字符。");

//判断英文字符 
jQuery.validator.addMethod("isEnglish", function(value, element) {       
     return this.optional(element) || /^[A-Za-z]+$/.test(value);       
}, "只能包含英文字符。"); 

//字符验证，只能包含中文、英文、数字、下划线等字符。    
jQuery.validator.addMethod("stringCheck", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
}, "只能包含中文、英文、数字、下划线等字符");   

// 匹配english  
jQuery.validator.addMethod("isEnglish", function(value, element) {       
     return this.optional(element) || /^[A-Za-z]+$/.test(value);       
}, "匹配english");   

// 匹配汉字  
jQuery.validator.addMethod("isChinese", function(value, element) {       
     return this.optional(element) || /^[\u4e00-\u9fa5]+$/.test(value);       
}, "匹配汉字");   

// 匹配中文(包括汉字和字符) 
jQuery.validator.addMethod("isChineseChar", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
}, "匹配中文(包括汉字和字符) "); 
  
// 判断是否为合法字符(a-zA-Z0-9-_)
jQuery.validator.addMethod("isRightfulString", function(value, element) {       
     return this.optional(element) || /^[A-Za-z0-9_-]+$/.test(value);       
}, "判断是否为合法字符(a-zA-Z0-9-_)");

//匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
jQuery.validator.addMethod("isPwd", function(value, element) {       
     return this.optional(element) || /^\w{6,12}$/.test(value);       
}, "请输入6-12之间，只能包含英文、数字和下划线。"); 

// 手机号码验证    
jQuery.validator.addMethod("isMobile", function(value, element) {    
  var length = value.length;    
  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
}, "请输入正确格式的手机号码。");

// 电话号码验证    
jQuery.validator.addMethod("isPhone", function(value, element) {    
  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
  return this.optional(element) || (tel.test(value));    
}, "请输入正确格式的电话号码。");

//匹配qq      
jQuery.validator.addMethod("isQq", function(value, element) {       
     return this.optional(element) || /^[1-9]\d{4,12}$/;       
}, "请输入正确格式的QQ");

//邮政编码验证    
jQuery.validator.addMethod("isZipCode", function(value, element) {    
  var zip = /^[0-9]{6}$/;    
  return this.optional(element) || (zip.test(value));    
}, "请输入正确格式的邮政编码。"); 

//身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
  //var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
  return this.optional(element) || isIdCardNo(value);    
}, "请输入正确格式的身份证号码。"); 

//IP地址验证   
jQuery.validator.addMethod("isIp", function(value, element) {    
  return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
}, "请输入正确格式的IP地址。");

//URL地址校验
jQuery.validator.addMethod("isUrl", function(value, element) {    
  return this.optional(element) || /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/.test(value);    
}, "请输入正确格式的http地址。");

//身份证号码的验证规则
function isIdCardNo(num) {
	//if (isNaN(num)) {alert("输入的不是数字！"); return false;} 
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
	else {
		//alert("输入的数字位数不对。"); 
		return false;
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		}
		if (!B) {
			//alert("输入的身份证号 "+ a[0] +" 里出生日期不对。"); 
			return false;
		}
	}
	if (!re.test(num)) {
		//alert("身份证最后一位只能是数字和字母。");
		return false;
	}
	return true;
}

function lengthInUtf8Bytes(str) {
	var m = encodeURIComponent(str).match(/%[89ABab]/g);
	return str.length + (m ? m.length : 0); 
}
