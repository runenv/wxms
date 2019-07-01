$(document).ready(function(){
	$("#pushForm").validate({
		rules: {
			"pushVO.title":{
				lengthInUtf8Bytes:{
					param:[1,39]
				}
			},
			"pushVO.content":{
				lengthInUtf8Bytes:{
					param:[1,70]
				}
			}
		},
		errorPlacement:function(error,element) {
			if (element.attr("name") == "pushVO.endDate"){
				//error.appendTo($("#endDateSpan"));
				error.css("display","inline");
				element[0].style.display="inline";
			}
			if (element.attr("name") == "pushVO.noticeId"){
				//error.appendTo($("#endDateSpan"));
				error.css("display","inline");
				element[0].style.display="inline";
			}
			error.insertAfter(element);
		},
		onfocusout: function( element, event ) {
			if (element.name == "pushVO.endDate"){
				element.style.display="inline";
				$("label[for='pushVOEndDate']").css("display","inline");
			}
			if (element.name == "pushVO.noticeId"){
				element.style.display="inline";
				$("label[for='pushVONoticeId']").css("display","inline");
			}
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
			   this.element(element);
			}
		}
	});
	Push.init();
	Push.renderPushType();
	Push.renderDirectFlag();
	Push.renderPlatform();
	Push.renderVersion();
	Push.findCatalog($("#pushVO\\.pushId").val());
	$("#btnSavePush").click(function(){
		$('#pushVOStartDatee').removeClass("required error");
		$("#pushVO\\.title").addClass("required");
		$("#pushVOContent").addClass("required lengthInUtf8Bytes");
		$("#pushVOStartDate").addClass("required");
		$("#pushVOEndDate").addClass("required");
		Push.savePush();
	});
	$("#targetUserBtn").click(function(){
		Push.importTargetUser();
	});

	setTimeout(function(){
		var lookupId = "pushVOClassId";
		var lookup = $("#"+lookupId).attr("lookup");
		Push.initDictComb(lookupId, lookup, "0");
		$("#pushVOClassId option[value='-1']").remove();
		$("#pushVOClassId option[value='3']").remove();
		
		lookupId = "pushVOLotteryId";
		var $option=$("#pushVOClassId option:selected");
		var parentId = $option.attr("title");
		Push.initDictComb(lookupId, lookup, parentId);
		
		if($("#"+lookupId) != "undefined" && $("#"+lookupId).val() ==504){
			$("#noticeIdTR").show();
		}
	},300);
	
	$("#pushVOClassId").change(function(){
		var lookupId = "pushVOLotteryId";
		var lookup = $(this).attr("lookup");
		var $option=$("option:selected",this);
		var parentId = $option.attr("title");
		Push.initDictComb(lookupId, lookup, parentId);
		if($("#"+lookupId) != "undefined" && $("#"+lookupId).val() ==504){
			$("#noticeIdTR").show();
		}else{
			$("#noticeIdTR").hide();
		}
	});
	
	$("#pushVOLotteryId").change(function(){
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
	$("#pushVO\\.title").blur(function(){
		//if(this.value == "" || $("#pushVO\\.pushId").val() !="")return;
		var oldTitle = $("#pushVO\\.oldTitle").val();
		if(this.value == "")return;
		if($("#pushVO\\.pushId").val() !="" &&  this.value == oldTitle){
			$("#pushVO\\.title").removeClass("checkTitle error");
			$("#pushForm").valid();	
			return;
		}
		var userParam = "{\"title\":\""+ this.value +"\"}";
		$.ajax({
	    	url:jsCtx+"/push/checkTitle",
	    	data:userParam,
	    	type:"POST",
	    	async:false,
	    	dataType:"json",
	    	contentType:'application/json;charset=UTF-8',
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data);
	    		if("undefined" != typeof(json) && json.statusCode != 0){
	    			$("#pushVO\\.title").addClass("checkTitle");
	    			$("#pushForm").valid();	
	    		}else{
	    			$("#pushVO\\.title").removeClass("checkTitle error");
	    			$("#pushForm").valid();	
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

var Push = {
	init : function() {
		var selectedTags = {};
		$(".selected-tag").each(function(){
			selectedTags[this.text] = true;
		});
		var list= $('#tag-list');
		var search = $('#pushVOStartDatee');
		var device_tags = $('#id_tags');
		var loader = $('#loader');
		var selectedDisplay = $('#selected-tags');
		$("#tag_search").click(function(e) {
			var target = $(e.target);
			var selected = search.val();
			search.addClass("required");
			$("#pushVO\\.title").removeClass("required error");
			$("#pushVOContent").removeClass("required lengthInUtf8Bytes error");
			$("#pushVOStartDate").removeClass("required error");
			$("#pushVOEndDate").removeClass("required error");
			if(""==selected){
				$("#pushForm").valid();	
				return;
			}
			if (!selectedTags[selected]) {
				selectedDisplay.append($('<a class="selected-tag"></a>').text(search.val()));
			}
			selectedTags[selected] = true;
			
			var taskDateValue = [];
			$.each(selectedTags, function(k, v) {
				if (v) taskDateValue.push(k);
			});
			device_tags.val(taskDateValue.join(','));
			//search.val("");
		});
		$("#selected-tags").click(function(e) {
			var target = $(e.target);
			if (target.hasClass('selected-tag')) {
				delete selectedTags[target.text()];
				target.remove();
				var revalue = [];
				$.each(selectedTags, function(k, v) {
					//alert("v:"+v + "k:"+k);
					if (v) revalue.push(k);
				});
				device_tags.val(revalue.join(','));
			}
		});
	},	
	savePush:function(){
		var pushForm = $("#pushForm");
		pushForm.submit();
	},
	importTargetUser:function(){
		//var result = openModelWin(jsCtx+"/views/push/uploadFile.jsp?"+jsVersion,500,300);
		Push.ajaxFileUpload('targetUser','','userMsg');
	},
	renderPushType:function(){
		var pushType = 'PUSH_TYPE';
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(pushType)+"/0";
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
		           	var elements = "<input type=\"radio\" class=\"pushDateRdo\" name=\"pushVO.pushType\" value=\""+item.itemCode+"\">"+item.itemName;
		           	$("#"+pushType).append(elements);
		        });
		       	var defaultValue = $("#"+pushType).attr("defaultValue");
		       	$("input[name='pushVO.pushType']:eq(0)").attr("checked",true);
		       	var pushTypeObj = $("input[name='pushVO.pushType']");
		       	pushTypeObj.each(function(){
		       		if(this.value == defaultValue){
		       			this.checked = true;
		       			if(this.value == 0){
		       				$("#pushDateTR").show();
		       			}else{
		       				$("#pushDateTR").hide();
		       			}
		       		}
		       	});
		       	//推送时间
		       	$(".pushDateRdo").click(function() {
		       		var selectedvalue = $("input[name='pushVO.pushType']:checked").val();
		       		if(selectedvalue == 0){
		       			$("#pushDateTR").show();
		       		}else{
		       			$("#pushDateTR").hide();
		       		}
		       	});
	    	},
	    	error:function(){
				try{			
					console.log("renderPushType failure!");
				}catch(e){}
	    	}
	    });
	},
	renderDirectFlag:function(){
		var directFlag = 'PUSH_DIRECT';
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
		           	var elements = "<input type=\"radio\" class=\"directFlagRdo\" name=\"pushVO.directFlag\" value=\""+item.itemCode+"\">"+item.itemName;
		           	$("#"+directFlag).append(elements);
		        });
		       	var defaultValue = $("#"+directFlag).attr("defaultValue");
		       	$("input[name='pushVO.directFlag']:eq(0)").attr("checked",true);
		       	var directFlagObj = $("input[name='pushVO.directFlag']");
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
		       		var selectedvalue = $("input[name='pushVO.directFlag']:checked").val();
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
		var platform = 'PUSH_PLATFORM';
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
		           	var elements = "<input type=\"checkbox\" name=\"pushVO.arrId\" value=\""+item.itemCode+"\">"+item.itemName+"&nbsp;";
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
		var pushVersion = 'PUSH_VERSION';
		var lookupUrl=jsCtx+"/services/lookup/dictionary/lookupDict/"+encodeURI(pushVersion)+"/0";
		$.ajax({
	    	url:lookupUrl,
	    	type:"GET",
	    	cache:false,
	    	async:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var chkAll = "<input type=\"checkbox\" onclick=\"FormUtils.checkedAll('.pushVersionChk',this.checked);\" name=\"pushVersionChkAll\" value=\"\"/>全选<br>";
	           	$("#"+pushVersion).append(chkAll);
				var json = eval(data.Rows);
		       	$.each(json, function(i,item){
		           	var elements = "<span style=\"display:inline-block;width:18%;\"><input type=\"checkbox\" class=\"pushVersionChk\" name=\"pushVO.arrId2\" value=\""+item.itemCode+"\"/>"+item.itemName+"&nbsp;</span>";
		           	$("#"+pushVersion).append(elements);
		        });
	    	},
	    	error:function(){
				try{			
					console.log("renderVersion failure!");
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
	findCatalog:function(pushId){
		if(""==pushId) return;
		var pushUrl=jsCtx+"/services/business/push/getPush/"+pushId;
		$.ajax({
	    	url:pushUrl,
	    	type:"GET",
	    	async:true,
	    	cache:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
	    		var json = eval(data.Rows);
	    		//初始化平台复选框
	    		var platformIds= json.arrId;
				$("input[name='pushVO\\.arrId']").each(function(){
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
				$("input:checkbox[name='pushVO\\.arrId2']").each(function(){
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
			if(!Push.validateFile(elementId,"JPG,GIF,PNG")) return;
		}else{
			uploadUrl = jsCtx+'/file/fileUpload?fileName=';
			if(!Push.validateFile(elementId,"XLS,XLSX")) return;
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




