var key;
$(document).ready(function(){
	$("#deptForm").validate({
		rules: {
			"deptVO.description":{
				lengthInUtf8Bytes:{
					param:[1,99]
				}
			}
		}
	});
	var $deptId=$("input[name='deptVO.deptId']");
	$("input[name='deptVO.parentName']").val("根节点");
	if(""!=$deptId.val()){
		findDept($deptId.val());
		$("#btnAdd").css("display","");
	}
	$(".ztree").each(function(){
		var deptId=$(this).attr('treeId');
		if(""!=deptId){
			var deptTree=$(this).attr('id');
			deptZTree(deptId,deptTree);
		}
	});
	key = $("#key");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
	$("#btnAdd").click(function(){
		addDept();
		this.style.display = "none";
	});
	$("#btnDelete").click(function(){
		deleteDept();
	});
});
function deptZTree(deptId,deptTree){
    var treeUrl=jsCtx+"/services/user/dept/deptTree/"+deptId;
    var setting = {
		data: {
			key: {
				title: "deptName",
				name:"deptName"
			},
			isSimpleData : true, 
			simpleData: {
				enable:true,
				idKey:'deptId',
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
    		$.fn.zTree.init($("#"+deptTree), setting, zNodes);
    		var zTree = $.fn.zTree.getZTreeObj(deptTree);
    		zTree.expandAll(true);
    	},
    	error:function(){
    		alert("请求部门树失败");
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
	var zTree = $.fn.zTree.getZTreeObj("deptTree");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
/**
 * 默认展开层级
 * @author liuhaibo
 * @since 2013-04-05
 * @param nodeList
 * @param level
 */
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
	var treeObj = $.fn.zTree.getZTreeObj("deptTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#deptTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("deptTree");
	for(var i=0;i<nodes.length;i++){
		var _node=nodes[i];
		if(_node.children){
			nodesFilter(_node.children,value);
		}
		nodes[i].highlight=false;
		treeObj.updateNode(nodes[i]);
		if(nodes[i].deptName.indexOf(value)!=-1){
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
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	if (value === ""){
		var treeObj = $.fn.zTree.getZTreeObj("deptTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}
function findDept(deptId){
	var deptUrl=jsCtx+"/services/user/dept/dept/"+deptId;
	$.ajax({
    	url:deptUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var json = eval(data.Rows);
    		fillForm(json,"deptVO");
    		//var data = $("#deptForm").serializeArray(); //自动将form表单封装成json  
    	    //alert(JSON.stringify(data)); 
    		//var jsonuserinfo = $('#deptForm').serializeObject(); 
    		//alert(JSON.stringify(jsonuserinfo)); 
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
function zTreeOnClick(event, treeId, treeNode){
	findDept(treeNode.deptId);
	var $parentId = $("input[name='deptVO.parentId']");
	var $parentName = $("input[name='deptVO.parentName']");
	$parentId.val(treeNode.parentId);
	var parentNode = treeNode.getParentNode();
	$parentName.val(parentNode?parentNode.deptName:"根节点");
	$("#btnAdd").css("display","");
}
function addDept(){
	var $deptId = $("input[name='deptVO.deptId']");
	var $deptName = $("input[name='deptVO.deptName']");
	var $description = $("textarea[name='deptVO.description']");
	//var $description = $("#deptVO\\.description");
	var $parentId = $("input[name='deptVO.parentId']");
	var $parentName = $("input[name='deptVO.parentName']");
	$parentId.val($deptId.val());
	$parentName.val($deptName.val());
	$deptId.val("");
	$deptName.val("");
	$description.val("");
}

function deleteDept(){
	if(!confirm("确认需要删除吗？"))return;
	$("#deptForm").attr('action',jsCtx+"/dept/deleteDept");
	$("#deptForm").submit();
}

function searchDeptItem(){
	$("#deptForm").attr('action',jsCtx+"/dept/findDeptItemList");
	document.forms["deptForm"].submit();
}

function checkedValue(){
}




