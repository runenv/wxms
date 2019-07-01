<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/views/app/basic.jsp"%>
<%-- <jsp:include page="/views/app/basic.jsp" /> --%>
<title>ECP888无线端产品管理后台</title>
<script type="text/javascript">
$(function(){
	getCurrentRole();//获取当前角色
	
	/* $('#home').panel({
		href : '${ctx}/user/listUser',
		onLoad : function() {
		}
	}); */
	
	//刷新
    $("#m-refresh").click(function(){
        var currTab = $('#tabs').tabs('getSelected');    //获取选中的标签项
        var url = $(currTab.panel('options').content).attr('src');  //获取该选项卡中内容标签的src属性
        var title = currTab.panel('options').title; 
        /* 重新设置该标签 */
        $('#tabs').tabs('update',{
            tab:currTab,
            options:{
                content: addPanel(title,url,1)
            }
        })
    });
    //关闭所有
    $("#m-closeall").click(function(){
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            $('#tabs').tabs('close',title);    
        });
    });
    //关闭其他
    $("#m-closeother").click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            
            if(currTitle != title){
                $('#tabs').tabs('close',title);            
            }
        });
    });
    //关闭当前
    $("#m-close").click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        $('#tabs').tabs('close', currTitle);
    });
	//为选项卡绑定右键
    $(".tabs li").live('contextmenu',function(e){
        /* 选中当前触发事件的选项卡 */
        var subtitle =$(this).text();
        $('#tabs').tabs('select',subtitle);
        //显示快捷菜单
        $('#menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        return false;
    });
});

function addPanel(title,url,isIframe){
	if(null==url || ""==url)return;
	var currTab = $('#tabs').tabs('getTab', title);
	if(currTab){
		var index = $('#tabs').tabs('getTabIndex', currTab);
		$('#tabs').tabs('close', index);
	}
	if(isIframe==1){
		var content ='<iframe id="mainframe" scrolling="auto" frameborder="0" border="0" style="width:100%;height:100%;" src="'+ url +'"></iframe>';
		$('#tabs').tabs('add',{
			title: title,
			content: content,
			closable: true
		});
	}else{
		$('#tabs').tabs('add',{
			title: title,
			href : url,
			closable: true
		});
	}
}
function removePanel(){
	var tab = $('#tabs').tabs('getSelected');
	if (tab){
		var index = $('#tabs').tabs('getTabIndex', tab);
		$('#tabs').tabs('close', index);
	}
}
function getCurrentRole(){
	//设置当前的用户角色
	$('.lookup2').each(function(){
		var curRoleId="${sessionScope.userSession.currentRoleId}";
		var lookupId=$(this).attr("id");
		var lookup = $(this).attr("lookup");
		if(lookup=="curRole"){
			var lookupUrl="${ctx}/services/user/role/currentRole";
			$.getJSON(lookupUrl,function(data) {
		       	var json = eval(data.Rows);
		       	$.each(json, function(i,item){
		       		var op="<option value='"+item.roleId+"'>"+item.roleName+"</option>";
		           	$("#"+lookupId).append(op);
		           	$("#"+lookupId).val(curRoleId);
	           });
	       }); 
		}
	});
}
function switchRole(roleId){
	location.href="${ctx}/user/switchRole?roleId="+roleId;
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:8px;overflow: hidden;">
		<div style="font-family:Arial;font-size:26px;font-weight:normal;font-style:normal;text-decoration:none;color:#FFFFFF;">ECP888无线端产品管理后台</div>
		<div style="text-align: right">
			欢迎您, ${sessionScope.userSession.userName} &nbsp;&nbsp;|&nbsp;&nbsp;
			<c:if test="${null!=sessionScope.userSession}">
				<a href="${ctx}/user/logout">安全退出</a> &nbsp;&nbsp;|&nbsp;&nbsp;
			</c:if>
			角色:<select id="cmbCurRole" name="userVO.roleId"
					onchange="switchRole(this.value);" class="required lookup2" lookup="curRole"></select>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'栏目管理'" style="width:200px;padding:5px;">
		<input type="text" id="catalogKey" value="" class="catalogEmpty searchInput" /><br/>
	    <ul style="margin-bottom:2px;margin-left:1px;" treeId="0" id="catalogMenuTree" class="ztree"></ul>
	</div>
	<%--<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;"></div> --%>
	<div data-options="region:'south',border:false" style="height:30px;background:#A9FACD;padding:6px;">
		<div style="height:26px;text-align:center;">
	        Copyright &copy; 壹天讯科技有限公司版权所有
	    </div>
	</div>
	<div id="mainPanle" data-options="region:'center'" border="true">
		<div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div id="home" title="Home">
				<div class="cs-home-remark" style="padding:6px auto;">
					<h3>欢迎您使用ECP888无线端产品管理系统</h3> <br>
				</div>
			</div>
		</div>
	</div>
	<%--contextmenu --%>
	<div id="menu" class="easyui-menu" style="width:150px;">
	    <div id="m-refresh">刷新</div>
	    <div class="menu-sep"></div>
	    <div id="m-close">关闭</div>
	    <div class="menu-sep"></div>
	    <div id="m-closeother">关闭其他</div>
	    <div class="menu-sep"></div>
	    <div id="m-closeall">全部关闭</div>
	</div>
	<div id="loginDiv">
		<div id="login">
			<form id="loginForm" action="" commandName="userForm">
			<P id="infoAlert">请输入用户名和密码</P>
			<div class="control-group">
				<SPAN class="icon-user"></SPAN>
				<INPUT type="text" id="userName" name="userName" value="test1"	placeholder="userName">
			</div>
			<div class="control-group">
				<SPAN class="icon-lock"></SPAN>
				<INPUT type="password" id="userPassword" name="userPassword" value="test1"	placeholder="Password">
			</div>
			<div class="remember-me">
				<A id="forget-password" href="/">忘记密码?</A>
			</div>
			<div id="loginErrorMsg">
			</div>
			<div class="login-btn">
				<input id="loginBtn" value="登 录" name="loginBtn" type="button" />
			</div></form>
		</div>
	</div>
	<div id="showImageDiv"></div>
</body>
<script type="text/javascript">
var key;
$(document).ready(function(){
	$(".ztree").each(function(){
		var catalogId=$(this).attr('treeId');
		if(""!=catalogId){
			var catalogTree=$(this).attr('id');
			catalogZTree(catalogId,catalogTree);
		}
	});
	key = $("#catalogKey");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
});
function catalogZTree(catalogId,catalogTree){
    var treeUrl=jsCtx+"/services/catalog/catalog/catalogTreeByParentId/"+catalogId;
    var setting = {
		data: {
			key: {
				title: "orderNum",
				name:"catalogName"
			},
			isSimpleData : true, 
			simpleData: {
				enable:true,
				idKey:'catalogId',
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
    	timeout:10000,
    	success:function(data){
    		var zNodes = eval(data.Rows);
    		$.fn.zTree.init($("#"+catalogTree), setting, zNodes);
    		var zTree = $.fn.zTree.getZTreeObj(catalogTree);
    		zTree.expandAll(true);
    	},
    	error:function(){
    		alert("请求栏目树失败");
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
function updateNodes(highlight,nodeList) {
	var zTree = $.fn.zTree.getZTreeObj("catalogMenuTree");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
function expendByLevel(tree,level){
	var zTree = $.fn.zTree.getZTreeObj(tree);
	var nodeList = zTree.getNodes();
	for( var i=0 ;i<nodeList.length; i++) {
		if(nodeList[i].level==level){
			nodeList[i].open = true;
		}
		zTree.updateNode(nodeList[i]);
	}
}
function treeFilter(value){
	var treeObj = $.fn.zTree.getZTreeObj("catalogMenuTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#catalogMenuTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("catalogMenuTree");
	for(var i=0;i<nodes.length;i++){
		var _node=nodes[i];
		if(_node.children){
			nodesFilter(_node.children,value);
		}
		nodes[i].highlight=false;
		treeObj.updateNode(nodes[i]);
		if(nodes[i].catalogName.indexOf(value)!=-1){
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
function focusKey(e) {
	if (key.hasClass("catalogEmpty")) {
		key.removeClass("catalogEmpty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("catalogEmpty");
	}
}
function searchNode(e) {
	var value = $.trim(key.get(0).value);
	if (key.hasClass("catalogEmpty")) {
		value = "";
	}
	if (value === ""){
		var treeObj = $.fn.zTree.getZTreeObj("catalogMenuTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}

function zTreeOnClick(event, treeId, treeNode){
	if("undefined"==typeof(treeNode.catalogUrl))return;
	var menuUrl = treeNode.catalogUrl+"?"+jsVersion;
	addPanel(treeNode.catalogValue,menuUrl,1);
}

function checkedValue(){
	
}
</script>
</html>