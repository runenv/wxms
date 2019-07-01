//去左空格
function ltrim(str) {
	return str.replace(/^\s*/, "");
}
// 去右空格
function rtrim(str) {
	return str.replace(/\s*$/, "");
}
//去左右空格
function trim(str) {
	return rtrim(ltrim(str));
}
function getCookie(sName) {
    var aCookie = document.cookie.split(";");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined) return v;
        return unescape(v);
    }
    return null;
}

function loadjscssfile(filename, filetype) {
	var fileref = "";
	if (filetype == "css") { // 判断文件类型
		fileref = document.createElement("link");
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename);
	}
	if (typeof fileref != "undefined"){
		document.getElementsByTagName("head")[0].appendChild(fileref);
	}
}

Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(), //day 
        "h+": this.getHours(), //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
        "S": this.getMilliseconds() //millisecond 
    };
    if (/(y+)/.test(format)) 
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) 
        if (new RegExp("(" + k + ")").test(format)) 
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

//获取文件扩展名
function getExt(f_path) {
	var ext = '';
	if (f_path != null && trim(f_path).length > 0) {
		f_path = trim(f_path);
		ext = f_path.substring(f_path.lastIndexOf(".") + 1, f_path.length);
	}
	return ext;
}

//验证文件扩展名
function chk_ext(f_path,type) {
	var ext = getExt(f_path);
	// 根据需求定制
	//var accept_ext = new Array(type);
	var accept_ext = type.split(",");
	var flag = false;
	if (ext != '') {
		for (var i = 0; i < accept_ext.length; i++) {
			if (ext.toUpperCase() == accept_ext[i]){
				flag = true;
				break;
			}
		}
	}
	return flag;
}

/**
 * 弹出框属性设置
 * @author ecp875
 * @since 2014-08-05
 * @returns
 */
function getPopupOptions(width,height){
	var x=(screen.width-width)/2;
	var y=(screen.height-height)/2;
	var options = "dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogTop:"
		+y+"px;dialogLeft:"+x+"px;status:no";
	return options;
}
/**
 * 弹出框
 * @author ecp875
 * @since 2014-08-05
 * @param page
 * @returns
 */
function openModelWin(page,width,height) {
	var options = getPopupOptions(width,height);
	return window.showModalDialog(page,null,options);
}

/**
 * 填充Form
 * @author ecp875
 * @since 2014-08-05
 * @param json
 * @param vo
 */
function fillForm(json,vo){
	$.each(json, function(key,value){
		var elements = document.getElementsByName(vo+"."+key)[0];
		var $elements ;
		if("undefined"!=typeof(elements)){
			$elements = $(elements);//原生对象转jquery对象
			//$elements[0].type [0] jquery对象转原生对象后获取类型
			if($elements.is("input")){
				$elements.val(value);
			}if($elements.is("textarea")){
				$elements.val(value);
			}else if($elements.is("select")){
				$elements.val(value);
			}
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


/**
 * 权限点控制(移除没有权限的页面元素)
 * @author ecp875
 * @since 2013-06-02
 */
function hasPermission(){
	$(".permission").each(function(){
		var thisObj=$(this);
		var classs = $(this).attr("class");
		if(/permission/.test(classs)){
			var p=classs.split(" ");
			$(p).each(function(i){
				if(p[i].indexOf("$")!=-1){
					var permissionUrl=jsCtx+"/services/user/roleRight/hasPermission/"+p[i];
					$.ajax({
					   	url:permissionUrl,
					   	type:"GET",
					   	cache:false,
					   	dataType:"json",
					   	timeout:10000,
					   	success:function(data){
					   		if(!data){
					   			thisObj.remove();
					   		}
					   	},
					   	error:function(){
					   		alert("请求权限控制失败");
					   	}
					});
				}
			});
		}
	});
}

//渲染所有.select样式的表单下拉框
function renderSelect(){
	$('.select').each(function(){
      	var lookupId=$(this).attr("id");
		var lookup = $(this).attr("lookup");
		var defaultValue = $(this).attr("defaultValue");
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(lookup)+"/0";
		$.getJSON(lookupUrl,function(data) {
           	var json = eval(data.Rows);
           	var op="<option value=''>--------</option>";
           	//$("#"+lookupId).append(op);
           	$.each(json, function(i,item){
               	op="<option title='"+item.itemId+"' value='"+item.itemCode+"'>"+item.itemName+"</option>";
               	$("#"+lookupId).append(op);
            });
           	try{
           		setTimeout(function(){
                   	$("#"+lookupId).val(defaultValue);
        		},100);
           	}catch(e){}
         }); 
	});
}

/**
 * 页面加载时
 * @author ecp875
 * @since 2014-08-05
 * @returns
 */
$(function(){
	renderSelect();
    //操作权限点控制
    hasPermission();
    //session监控  $("form").bind("submit",function(e){});
    var ajaxStack=[];//Ajax等待执行队列(先进先出) shift,push
    $("form").submit(function(e){
    	return checkSession(e);
	});
	$(document).ajaxComplete(function() {
		if(ajaxStack.shift()){
			$("form").submit(function(e){
				return checkSession(e);
		    });
		}
	});
});  
//session监控
function checkSession(event) {
	var checkSession=true;
	var checkUrl = jsCtx+"/services/user/user/checkUserSession";
    /* $.ajax({
        url : "", 
        data : {
            checkSession : true
        },
        async : false,
        complete: function (xhr, textStatus) {
            checkSession = xhr.getResponseHeader("sessionStatus");
        }
    });*/
    $.ajax({
    	url:checkUrl,
    	type:"GET",
    	cache:false,
    	async:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		checkSession = data;
    		try{
    			console.log(data);
    		}catch(e){}
    	},
    	error:function(){
    		try{
    			console.log("Check session failure!");
    		}catch(e){}
    	}
    });
    if (!checkSession) {
    	/*var ssoUrl = jsCtx+"/user/toLogin";
        var options = getPopupOptions(660,360);
        var result = window.showModalDialog(ssoUrl,null,options);*/
    	/*loadjscssfile(jsCtx+"/css/login.css","css");*/
    	
    	var loginDialog = parent.$('#loginDiv', parent.document).dialog({    
            title: '登录',    
            width: 306,    
            height: 260,    
            closed: false,    
            cache: false,
            collapsible:true,
            //href: jsCtx+'/user/listUser',    
            modal: true   
        }); 
    	loginDialog.dialog("open"); 
    	parent.$("#infoAlert").html("请输入用户名和密码");
    	parent.$("#loginBtn").click(function(){
    		var doLoginUrl = jsCtx+"/user/loginByAjax";
    		var param="";
    		var loginMsgObj = null;
    		if(parent){
    			param = JSON.stringify(parent.$("#loginForm").serializeObject());
    			loginMsgObj=parent.$("#infoAlert");
    		}else{
    			param = JSON.stringify($("#loginForm").serializeObject());
    			loginMsgObj=$("#infoAlert");
    		}
    		$.ajax({
    	    	url:doLoginUrl,
    	    	data:param,
    	    	type:"POST",
    	    	async:false,
    	    	dataType:"json",
    	    	contentType:'application/json;charset=UTF-8',
    	    	timeout:10000,
    	    	success:function(data){
    	    		if(null!=data && ""!=data){
    	    			loginDialog.dialog("close");
    	    		}else{
    	    			loginMsgObj.html('<span style="color:red;">用户名不存在或密码错误!</span>');
    	    		}
    	    	},
    	    	error:function(){
    	    		try{
    	    			console.log("login failure!");
    	    		}catch(e){}
    	    	}
    	    });
        });
        return false;
    }
    return true;
}

FormUtils = {
    checkedAll: function(pattern, checked) {
        checked = checked ? checked : false;
        $.each($(pattern), function() {
        	//$(this).attr("checked", "checked");
            this.checked = checked;
        });
    },
    slideZone:function(obj,time){
    	obj.slideToggle(time);
    },
    lengthInUtf8Bytes : function(str) {
		var m = encodeURIComponent(str).match(/%[89ABab]/g);
		return str.length + (m ? m.length : 0); 
	}
};





