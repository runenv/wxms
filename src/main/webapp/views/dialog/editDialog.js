$(document).ready(function(){
	$("#dialogForm").validate({
		rules: {
			"dialogVO.title":{
				lengthInUtf8Bytes:{
					param:[1,39]
				}
			}
		},
		errorPlacement:function(error,element) {
			if (element.attr("name") == "dialogVO.endDate"){
				//error.appendTo($("#endDateSpan"));
				error.css("display","inline");
				element[0].style.display="inline";
			}
			if (element.attr("name") == "dialogVO.noticeId"){
				//error.appendTo($("#endDateSpan"));
				error.css("display","inline");
				element[0].style.display="inline";
			}
			error.insertAfter(element);
		},
		onfocusout: function( element, event ) {
			if (element.name == "dialogVO.endDate"){
				element.style.display="inline";
					$("label[for='dialogVOEndDate']").css("display","inline");
			}
			if (element.name == "dialogVO.noticeId"){
				element.style.display="inline";
				$("label[for='dialogVONoticeId']").css("display","inline");
			}
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
				this.element(element);
			}
		}
	});  
	CKEDITOR.instances.dialogVOContent.setData($("#dialogVOContent").val());
	Dialog.renderPlatform();
	Dialog.renderVersion();
	Dialog.renderDirectFlag();
	Dialog.findCatalog($("#dialogVO\\.dialogId").val());
	$("#btnSaveDialog").click(function(){
		Dialog.saveDialog();
	});
	$("#targetUserBtn").click(function(){
		Dialog.importTargetUser();
		return false;
	});

	setTimeout(function(){
		var lookupId = "dialogVOClassId";
		var lookup = $("#"+lookupId).attr("lookup");
		Dialog.initDictComb(lookupId, lookup, "0");
		$("#dialogVOClassId option[value='-1']").remove();
		$("#dialogVOClassId option[value='3']").remove();
		
		lookupId = "dialogVOLotteryId";
		var $option=$("#dialogVOClassId option:selected");
		var parentId = $option.attr("title");
		Dialog.initDictComb(lookupId, lookup, parentId);
		if($("#"+lookupId) != "undefined" && $("#"+lookupId).val() ==504){
			$("#noticeIdTR").show();
		}
	},300);
	
	$("#dialogVOClassId").change(function(){
		var lookupId = "dialogVOLotteryId";
		var lookup = $(this).attr("lookup");
		var $option=$("option:selected",this);
		var parentId = $option.attr("title");
		Dialog.initDictComb(lookupId, lookup, parentId);
		if($("#"+lookupId) != "undefined" && $("#"+lookupId).val() ==504){
			$("#noticeIdTR").show();
		}else{
			$("#noticeIdTR").hide();
		}
	});
	
	$("#dialogVOLotteryId").change(function(){
		if(this.value == 504){
			$("#noticeIdTR").show();
		}else{
			$("#noticeIdTR").hide();
		}
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
	$("#dialogVO\\.title").blur(function(){
		//if(this.value == "" || $("#dialogVO\\.dialogId").val() !="")return;
		var oldTitle = $("#dialogVO\\.oldTitle").val();
		if(this.value == "")return;
		if($("#dialogVO\\.dialogId").val() !="" &&  this.value == oldTitle){
			$("#dialogVO\\.title").removeClass("checkTitle error");
			$("#dialogForm").valid();
			return;
		}
		var userParam = "{\"title\":\""+ this.value +"\"}";
		$.ajax({
	    	url:jsCtx+"/dialog/checkTitle",
	    	data:userParam,
	    	type:"POST",
	    	async:false,
	    	dataType:"json",
	    	contentType:'application/json;charset=UTF-8',
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data);
	    		if("undefined" != typeof(json) && json.statusCode != 0){
	    			$("#dialogVO\\.title").addClass("checkTitle");
	    			$("#dialogForm").valid();	
	    		}else{
	    			$("#dialogVO\\.title").removeClass("checkTitle error");
	    			$("#dialogForm").valid();	
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

var Dialog = {
	saveDialog:function(){
		var editorData = CKEDITOR.instances.dialogVOContent.getData();
		var dialogForm = $("#dialogForm");
		var contentObj = $("#dialogVOContent");
		contentObj.val(editorData);
		var utf8Lenth = FormUtils.lengthInUtf8Bytes(contentObj.val()); 
		if(utf8Lenth > 9000){
			alert("内容不能大于9000个字符!");
			return;
		}
		dialogForm.submit();
	},
	importTargetUser:function(){
		//var result = openModelWin(jsCtx+"/views/dialog/uploadFile.jsp?"+jsVersion,500,300);
		Dialog.ajaxFileUpload('targetUser','','userMsg');
	},
	renderPlatform:function(){
		var platform = 'NOTICE_PLATFORM';
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
		           	var elements = "<input type=\"checkbox\" name=\"dialogVO.arrId\" value=\""+item.itemCode+"\">"+item.itemName+"&nbsp;";
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
	renderVersion:function(){
		var dialogVersion = 'PUSH_VERSION';
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(dialogVersion)+"/0";
		$.ajax({
	    	url:lookupUrl,
	    	type:"GET",
	    	cache:false,
	    	async:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var chkAll = "<input type=\"checkbox\" onclick=\"FormUtils.checkedAll('.dialogVersionChk',this.checked);\" name=\"dialogVersionChkAll\" value=\"\"/>全选<br>";
	           	$("#"+dialogVersion).append(chkAll);
				var json = eval(data.Rows);
		       	$.each(json, function(i,item){
		           	var elements = "<span style=\"display:inline-block;width:18%;\"><input type=\"checkbox\" class=\"dialogVersionChk\" name=\"dialogVO.arrId2\" value=\""+item.itemCode+"\"/>"+item.itemName+"&nbsp;</span>";
		           	$("#"+dialogVersion).append(elements);
		        });
	    	},
	    	error:function(){
				try{			
					console.log("renderVersion failure!");
				}catch(e){}
	    	}
	    });
	},
	renderDirectFlag:function(){
		var directFlag = 'DIALOG_DIRECT';
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
		           	var elements = "<input type=\"radio\" class=\"directFlagRdo\" name=\"dialogVO.directFlag\" value=\""+item.itemCode+"\">"+item.itemName;
		           	$("#"+directFlag).append(elements);
		        });
		       	var defaultValue = $("#"+directFlag).attr("defaultValue");
		       	$("input[name='dialogVO.directFlag']:eq(0)").attr("checked",true);
		       	var directFlagObj = $("input[name='dialogVO.directFlag']");
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
		       		var selectedvalue = $("input[name='dialogVO.directFlag']:checked").val();
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
	findCatalog:function(dialogId){
		if(""==dialogId) return;
		var dialogUrl=jsCtx+"/services/business/dialog/getDialog/"+dialogId;
		$.ajax({
	    	url:dialogUrl,
	    	type:"GET",
	    	async:true,
	    	cache:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data.Rows);
	    		//初始化平台复选框
	    		var platformIds= json.arrId;
				$("input[name='dialogVO\\.arrId']").each(function(){
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
				//初始化版本复选框
	    		var versionIds= json.arrId2;
				$("input:checkbox[name='dialogVO\\.arrId2']").each(function(){
					$(this).attr("checked",false);
					if(versionIds instanceof Array){
						for(var arr=0;arr<versionIds.length;arr++){
							if($(this).val() == versionIds[arr]){
								$(this).attr("checked",true);
								break;
							}
						}
					}else{
						if($(this).val() == versionIds){
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
			if(!Dialog.validateFile(elementId,"JPG,GIF,PNG")) return;
		}else{
			uploadUrl = jsCtx+'/file/fileUpload?fileName=';
			if(!Dialog.validateFile(elementId,"XLS,XLSX")) return;
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




