$(document).ready(function(){
	$("#bulletinForm").validate({
		rules: {
			"bulletinVO.title":{
				lengthInUtf8Bytes:{
					param:[1,39]
				}
			}
		},
		errorPlacement:function(error,element) {
			if (element.attr("name") == "bulletinVO.endDate"){
				//error.appendTo($("#endDateSpan"));
				error.css("display","inline");
				element[0].style.display="inline";
			}
			error.insertAfter(element);
		},
		onfocusout: function( element, event ) {
			if (element.name == "bulletinVO.endDate"){
				element.style.display="inline";
					$("label[for='bulletinVOEndDate']").css("display","inline");
			}
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
				this.element(element);
			}
		}
	});  
	CKEDITOR.instances.bulletinVOContent.setData($("#bulletinVOContent").val());
	Bulletin.renderDirectFlag();
	Bulletin.renderPlatform();
	Bulletin.findCatalog($("#bulletinVO\\.bulletinId").val());
	
	$("#btnSaveBulletin").click(function(){
		Bulletin.saveBulletin();
	});
	$("#targetUserBtn").click(function(){
		Bulletin.importTargetUser();
	});

	setTimeout(function(){
		var lookupId = "bulletinVOClassId";
		var lookup = $("#"+lookupId).attr("lookup");
		Bulletin.initDictComb(lookupId, lookup, "0");
		
		lookupId = "bulletinVOLotteryId";
		var $option=$("#bulletinVOClassId option:selected");
		var parentId = $option.attr("title");
		Bulletin.initDictComb(lookupId, lookup, parentId);
		$("#bulletinVOLotteryId option[value='504']").remove();
	},300);
	
	$("#bulletinVOClassId").change(function(){
		var lookupId = "bulletinVOLotteryId";
		var lookup = $(this).attr("lookup");
		var $option=$("option:selected",this);
		var parentId = $option.attr("title");
		Bulletin.initDictComb(lookupId, lookup, parentId);
		$("#bulletinVOLotteryId option[value='504']").remove();
	});
	//删除目标用户
	$("#targetUserImg").click(function(){
		var attachId = this.alt;
		$.ajax({
	    	url:jsCtx+"/file/fileDelete/"+attachId,
	    	type:"POST",
	    	async:false,
	    	dataType:"json",
	    	contentType:'application/json;charset=UTF-8',
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data);
	    		if("undefined" != typeof(json) && json.statusCode == 0){
	    			$("#targetUserDiv").remove();
	    		}else{
	    			alert(json.errorMsg);
	    		}
	    	},
	    	error:function(){
	    		try{
	    			console.log("delete targetUser failure!");
	    		}catch(e){}
	    	}
	    });
	});
	//标题是否存在校验
	$("#bulletinVO\\.title").blur(function(){
		//if(this.value == "" || $("#bulletinVO\\.bulletinId").val() !="")return;
		var oldTitle = $("#bulletinVO\\.oldTitle").val();
		if(this.value == "")return;
		if($("#bulletinVO\\.bulletinId").val() !="" &&  this.value == oldTitle){
			$("#bulletinVO\\.title").removeClass("checkTitle error");
			$("#bulletinForm").valid();
			return;
		}
		var userParam = "{\"title\":\""+ this.value +"\"}";
		$.ajax({
	    	url:jsCtx+"/bulletin/checkTitle",
	    	data:userParam,
	    	type:"POST",
	    	async:false,
	    	dataType:"json",
	    	contentType:'application/json;charset=UTF-8',
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data);
	    		if("undefined" != typeof(json) && json.statusCode != 0){
	    			$("#bulletinVO\\.title").addClass("checkTitle");
	    			$("#bulletinForm").valid();	
	    		}else{
	    			$("#bulletinVO\\.title").removeClass("checkTitle error");
	    			$("#bulletinForm").valid();	
	    		}
	    	},
	    	error:function(){
	    		try{
	    			console.log("check title failure!");
	    		}catch(e){}
	    	}
	    });
	});
}); 

var Bulletin = {
	saveBulletin:function(){
		var editorData = CKEDITOR.instances.bulletinVOContent.getData();
		var bulletinForm = $("#bulletinForm");
		var contentObj = $("#bulletinVOContent");
		contentObj.val(editorData);
		var utf8Lenth = FormUtils.lengthInUtf8Bytes(contentObj.val()); 
		if(utf8Lenth > 9000){
			alert("内容不能大于9000个字符!");
			return;
		}
		bulletinForm.submit();
	},
	importTargetUser:function(){
		//var result = openModelWin(jsCtx+"/views/bulletin/uploadFile.jsp?"+jsVersion,500,300);
		Bulletin.ajaxFileUpload('targetUser','','userMsg');
	},
	renderDirectFlag:function(){
		var directFlag = 'BULLETIN_DIRECT';
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(directFlag)+"/0";
		$.ajax({
	    	url:lookupUrl,
	    	type:"GET",
	    	cache:false,
	    	async:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data.Rows);
		       	$.each(json, function(i,item){
		           	var elements = "<input type=\"radio\" class=\"directFlagRdo\" name=\"bulletinVO.directFlag\" value=\""+item.itemCode+"\">"+item.itemName;
		           	$("#"+directFlag).append(elements);
		        });
		       	var defaultValue = $("#"+directFlag).attr("defaultValue");
		       	$("input[name='bulletinVO.directFlag']:eq(0)").attr("checked",true);
		       	var directFlagObj = $("input[name='bulletinVO.directFlag']");
		       	directFlagObj.each(function(){
		       		if(this.value == defaultValue){
		       			this.checked = true;
		       			if(this.value == 1){
		       				$("#openUrlTR").hide();
		       				$("#classIdTR").show();
		       			}else{
		       				$("#openUrlTR").show();
		       				$("#classIdTR").hide();
		       			}
		       		}
		       	});
		       	//
		       	$(".directFlagRdo").click(function() {
		       		var selectedvalue = $("input[name='bulletinVO.directFlag']:checked").val();
		       		if(selectedvalue == 1){
		       			$("#openUrlTR").hide();
	       				$("#classIdTR").show();
		       		}else{
		       			$("#openUrlTR").show();
	       				$("#classIdTR").hide();
		       		}
		       	});
	    	},
	    	error:function(){
				try{			
					console.log("renderDirectFlag failure!");
				}catch(e){}
	    	}
	    });
	},
	renderPlatform:function(){
		var platform = 'BULLETIN_PLATFORM';
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(platform)+"/0";
		$.ajax({
	    	url:lookupUrl,
	    	type:"GET",
	    	cache:false,
	    	async:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data.Rows);
		       	$.each(json, function(i,item){
		           	var elements = "<input type=\"checkbox\" name=\"bulletinVO.arrId\" value=\""+item.itemCode+"\">"+item.itemName+"&nbsp;";
		           	$("#"+platform).append(elements);
		        });
	    	},
	    	error:function(){
				try{			
					console.log("renderPlatform failure!");
				}catch(e){}
	    	}
	    });
	},
	initDictComb:function(lookupId,lookup,parentId){
		var defaultValue = $("#"+lookupId).attr("defaultValue");
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(lookup)+"/"+parentId;
		$("#"+lookupId).empty();
		var op="<option value=''>--------</option>";
       	//$("#"+lookupId).append(op);
       	if("undefined"==typeof(parentId)) return;
       	$.ajax({
        	url:lookupUrl,
        	type:"GET",
        	cache:false,
        	async:false,
        	dataType:"json",
        	timeout:10000,
        	success:function(data){
        		var json = eval(data.Rows);
               	$.each(json, function(i,item){
                   	op="<option title='"+item.itemId+"' value='"+item.itemCode+"'>"+item.itemName+"</option>";
                   	$("#"+lookupId).append(op);
                });
               	try{
               		$("#"+lookupId).val(defaultValue);
               	}catch(e){}
        	},
        	error:function(){
        		try{
        			console.log("Init combo failure!");
        		}catch(e){}
        	}
        });
	},
	findCatalog:function(bulletinId){
		if(""==bulletinId) return;
		var bulletinUrl=jsCtx+"/services/business/bulletin/getBulletin/"+bulletinId;
		$.ajax({
	    	url:bulletinUrl,
	    	type:"GET",
	    	async:true,
	    	cache:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data.Rows);
	    		//初始化平台复选框
	    		var platformIds= json.arrId;
				$("input[name='bulletinVO\\.arrId']").each(function(){
					$(this).attr("checked",false);
					if(platformIds instanceof Array){
						for(var arr=0;arr<platformIds.length;arr++){
							if($(this).val() == platformIds[arr]){
								$(this).attr("checked",true);
								break;
							}
						}
					}else{
						if($(this).val() == platformIds){
							$(this).attr("checked",true);
						}
					}
				});
				//初始化渠道复选框
	    		var channelIds= json.arrId1;
				$("input:checkbox[alt*='channel-']").each(function(){
					$(this).attr("checked",false);
					if(channelIds instanceof Array){
						for(var arr=0;arr<channelIds.length;arr++){
							if($(this).val() == channelIds[arr]){
								$(this).attr("checked",true);
								break;
							}
						}
					}else{
						if($(this).val() == channelIds){
							$(this).attr("checked",true);
						}
					}
				});
	    	},
	    	error:function(){
	    		try{
	    			console.log("初始化平台复选框失败!");
        		}catch(e){}
	    	}
	    });
	},
	ajaxFileUpload:function(elementId,imageId,msg){
	    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
		var uploadUrl = '';
		if(null!=imageId && ""!=imageId){
			uploadUrl = jsCtx+'/file/fileUpload?fileName=';
			if(!Bulletin.validateFile(elementId,"JPG,GIF,PNG")) return;
		}else{
			uploadUrl = jsCtx+'/file/fileUpload?fileName=';
			if(!Bulletin.validateFile(elementId,"XLS,XLSX")) return;
		}
		$.ajaxFileUpload({
	        url:uploadUrl,
	        secureuri:false,              
	        fileElementId:elementId,           //文件选择框的id属性
	        dataType:'text',                       //服务器返回的格式,可以是json或xml等
	        success:function(data, status){
	            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
	            data = data.replace("</PRE>", '');
	            data = data.replace("<pre>", '');
	            data = data.replace("</pre>", ''); //设定上传文件完毕后,服务端会返回给前台[0`filepath]
	            if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	            	if(null!=imageId && ""!=imageId){
	            		$("img[id='"+imageId+"']").attr("src", data.substring(2));
	            	}
	                $('#'+msg).html("上传成功!<br/>");
	            }else{
	                $('#'+msg).html('上传失败，请重试!');
	            }
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	            $('#'+msg).html('上传失败，请重试!');
	        }
	    });
	},
	validateFile:function (id,suffix) {
		var file1 = $("#"+id)[0];
		if("underfined"!=typeof(file1) && file1.value ==""){
			alert("上传的文件不能为空！");
			return false;
		}
		if (!chk_ext(file1.value,suffix)) {
			alert("请上传["+suffix+"]格式的文件！");
			file1.value = "";
			return false;
		}
		return true;
	}
};




