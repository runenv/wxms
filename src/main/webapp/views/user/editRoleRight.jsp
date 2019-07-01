<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/app/basic.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑用户信息</title>
<style type="text/css">
	div.content_wrap {width: 100%;height:80%;}
	div.content_wrap div.left{float: left;width: 30%;border: 1px solid #5ab0e2;margin:2px 2px;overflow: scroll;}
	div.content_wrap div.right{float: right;width: 78%;}
	div.zTreeBackground {height:96%;text-align:left;}
</style>
<script type="text/javascript">
var key;
$(document).ready(function(){
	$("#userRoleForm").validate(); 
	$(".ztree").each(function(){
		var treeId=$(this).attr('treeId');
		if(""!=treeId){
			var roleRightTree=$(this).attr('id');
			roleRightZTree(treeId,roleRightTree);
		}
	});
	key = $("#key");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
});

function roleRightZTree(treeId,roleRightTree){
    var treeUrl=jsCtx+"/services/user/roleRight/roleRightTree/"+treeId;
    var setting = {
    	check:{
    		enable:true,
    		async:true,
    		chkStyle:'checkbox',
    		asynaParam:["rightName","rightId"],
    		chkboxType:{'Y':'ps','N':'ps'}
    	},
    	/* check:{
    		enable:true,
    		chkStyle:'radio',
    		radioType:"all"
    	}, */
		data: {
			key: {
				title: "r",
				name:"rightName"
			},
			isSimpleData : true, 
			simpleData: {
				enable:true,
				idKey:'rightId',
				pIdKey:'parentId'
			}				
		},
		view: {
			fontCss: getFontCss
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
    $.ajax({
    	url:treeUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:20000,
    	success:function(data){
    		var zNodes = eval(data.Rows);
    		$.fn.zTree.init($("#"+roleRightTree), setting, zNodes);
    		var zTree = $.fn.zTree.getZTreeObj(roleRightTree);
    		zTree.expandAll(true);
    		setTimeout(function(){
    			initRoleRight();
    		},1000);
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}
function setFont(treeId, treeNode) {
   if (treeNode && treeNode.isParent) {
	      return {color: "blue"};
   } else {
	      return null;
   }
}
function zTreeOnClick(event, treeId, treeNode){
	
}
/**
 * 初始化角色权限
 */
function initRoleRight(){
	var $roleId=$("input[name='roleVO.roleRightVO.roleId']");
	var rightUrl=jsCtx+"/services/user/roleRight/roleRight/"+$roleId.val();
	if($roleId.value!=""){
		$.ajax({
	    	url:rightUrl,
	    	type:"GET",
	    	cache:false,
	    	dataType:"json",
	    	timeout:10000,
	    	success:function(data){
    			var treeObj = $.fn.zTree.getZTreeObj("roleRightTree");
    			var nodes = treeObj.getNodes();
    			checkedRight(treeObj,nodes,data.Rows);
	    	},
	    	error:function(){
	    		alert("获取角色权限失败");
	    	}
	    });
	}
}
/**
 * 递归选中节点
 */
function checkedRight(treeObj,nodes,datas){
	for(var n=0;n<nodes.length;n++){
		if(nodes[n].children){
			checkedRight(treeObj,nodes[n].children,datas);
		}
		$.each(datas, function(i,item){
			if(nodes[n].rightId==item.rightId){
				treeObj.checkNode(nodes[n],true,true);
			}
		});	
	}
}
/**
 * 保存选中节点
 */
function saveRoleRight(){
	var $rightForm=$("#roleForm");
	var $rightIds=$("input[name='roleVO.roleRightVO.rightIds']");
	var treeObj = $.fn.zTree.getZTreeObj("roleRightTree");
	var checkedNodes = treeObj.getCheckedNodes();
	var nodesStr="";
	for(var i=0;i<checkedNodes.length;i++){
		if(!checkedNodes[i].isParent && checkedNodes[i].rightId){
			nodesStr+=checkedNodes[i].rightId;
			if(i<checkedNodes.length-1){
				nodesStr+=",";
			}
		}
	}
	$rightIds.val(nodesStr);
	$rightForm.attr('action',"${ctx}/role/saveRoleRight");
	$rightForm.submit();
}
function synchAppResource(){
	if(!confirm("您确定初始化系统权限吗?")) return;
	var synchUrl=jsCtx+"/services/user/roleRight/update";
	$.ajax({
    	url:synchUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:100000,
    	success:function(data){
    		var $treeId=$("input[name='roleVO.roleRightVO.roleId']");
    		roleRightZTree($treeId.val(),"roleRightTree");
    	},
    	error:function(){
    		alert("获取角色权限失败");
    	}
    });
}

//树检索
function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}
function searchNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("roleRightTree");
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	if (value === ""){
		var treeObj = $.fn.zTree.getZTreeObj("roleRightTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}
function updateNodes(highlight,nodeList) {
	var zTree = $.fn.zTree.getZTreeObj("roleRightTree");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
function treeFilter(value){
	var treeObj = $.fn.zTree.getZTreeObj("roleRightTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#roleRightTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("roleRightTree");
	for(var i=0;i<nodes.length;i++){
		var _node=nodes[i];
		if(_node.children){
			nodesFilter(_node.children,value);
		}
		nodes[i].highlight=false;
		treeObj.updateNode(nodes[i]);
		if(nodes[i].rightName.indexOf(value)!=-1){
			var tempNode=_node;
			$("#"+tempNode.tId).show();
			nodes[i].highlight=true;
			treeObj.updateNode(nodes[i]);
			while(tempNode=tempNode.getParentNode()){
				$("#"+tempNode.tId).show();
			}
		}
	}
}
function checkedRightAll(checked){
	var treeObj = $.fn.zTree.getZTreeObj("roleRightTree");
    treeObj.checkAllNodes(checked);
}
</script>
</head>
<body>
	授予角色权限<span style="color:red;font-size:12px;">(温馨提示：提交后需重新登陆权限才能生效)</span>
	<form:form id="roleForm" action="${ctx}/role/saveRoleRight" method="post" commandName="roleForm" >
		<form:hidden path="roleVO.roleRightVO.roleId"/>
		<form:hidden path="roleVO.roleRightVO.rightIds"/>
		<br>
		<span style="padding-left: 2px;">
			<input type="text" id="key" class="empty searchInput" size="46" />
			<input type="checkbox" id="checkAllChannelCheckbox"
					onclick="checkedRightAll(this.checked);" />全选
		</span>
		<div class="content_wrap">
			<div class="zTreeBackground left">
     			<ul style="margin-bottom:2px;margin-left:25px;" treeId="21" id="roleRightTree" class="ztree"></ul>
			</div>
		</div>
		<input type="button" value="同步" onclick="synchAppResource();" class="button-submit" />
		<input type="button" value="提交" id="Button1" onclick="saveRoleRight();" class="button-submit" />&nbsp;&nbsp;
		<input type="button" value="返回" onclick="location.href='${ctx}/role/listRole';"
			class="button-back" />
	</form:form>
</body>
</html>