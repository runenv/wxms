var key;
$(document).ready(function(){
	$("#dictForm").validate();
	var $dictId=$("input[name='dictionaryVO.dictionaryId']");
	$("input[name='dictionaryVO.parentName']").val("根节点");
	if(""!=$dictId.val()){
		findDict($dictId.val());
		$("#btnAdd").css("display","");
	}else{
		var dictItemLink=$("#dictItemLink").remove();
	}
	$(".ztree").each(function(){
		var dictId=$(this).attr('treeId');
		if(""!=dictId){
			var dictTree=$(this).attr('id');
			dictZTree(dictId,dictTree);
		}
	});
	key = $("#key");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
	$("#btnAdd").click(function(){
		addDict();
		this.style.display = "none";
	});
	$("#btnDelete").click(function(){
		if (window.confirm("确定删除此记录？")){
			deleteDict();
		}
	});
});
function dictZTree(dictId,dictTree){
    var treeUrl=jsCtx+"/services/lookup/dictionary/dictTree/"+dictId;
    var setting = {
    	/*check:{
    		enable:true,
    		async:true,
    		chkStyle:'checkbox',
    		asynaParam:["dictionaryName","dictionaryId"],
    		chkboxType:{'Y':'p','N':'s'}
    	},*/
    	/* check:{
    		enable:true,
    		chkStyle:'radio',
    		radioType:"all"
    	}, */
		data: {
			key: {
				title: "dictionaryName",
				name:"dictionaryName"
			},
			isSimpleData : true, 
			simpleData: {
				enable:true,
				idKey:'dictionaryId',
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
    		$.fn.zTree.init($("#"+dictTree), setting, zNodes);
    		var zTree = $.fn.zTree.getZTreeObj(dictTree);
    		zTree.expandAll(true);
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
function updateNodes(highlight,nodeList) {
	var zTree = $.fn.zTree.getZTreeObj("dictTree");
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
	var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#dictTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("dictTree");
	for(var i=0;i<nodes.length;i++){
		var _node=nodes[i];
		if(_node.children){
			nodesFilter(_node.children,value);
		}
		nodes[i].highlight=false;
		treeObj.updateNode(nodes[i]);
		if(nodes[i].dictionaryName.indexOf(value)!=-1){
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
	var zTree = $.fn.zTree.getZTreeObj("dictTree");
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	if (value === ""){
		var treeObj = $.fn.zTree.getZTreeObj("dictTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}
function findDict(dictId){
	var dictUrl=jsCtx+"/services/lookup/dictionary/dict/"+dictId;
	$.ajax({
    	url:dictUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var json = eval(data.Rows);
    		fillForm(json,"dictionaryVO");
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
function zTreeOnClick(event, treeId, treeNode){
	findDict(treeNode.dictionaryId);
	var $parentId = $("input[name='dictionaryVO.parentId']");
	var $parentName = $("input[name='dictionaryVO.parentName']");
	$parentId.val(treeNode.parentId);
	var parentNode = treeNode.getParentNode();
	$parentName.val(parentNode?parentNode.dictionaryName:"根节点");
	$("#btnAdd").css("display","");
}
function addDict(){
	var $dictId = $("input[name='dictionaryVO.dictionaryId']");
	var $dictName = $("input[name='dictionaryVO.dictionaryName']");
	var $description = $("input[name='dictionaryVO.description']");
	var $parentId = $("input[name='dictionaryVO.parentId']");
	var $parentName = $("input[name='dictionaryVO.parentName']");
	$parentId.val($dictId.val());
	$parentName.val($dictName.val());
	$dictId.val("");
	$dictName.val("");
	$description.val("");
}

function deleteDict(){
	if(!confirm("确认需要删除吗？"))return;
	$("#dictForm").attr('action',jsCtx+"/dict/deleteDict");
	$("#dictForm").submit();
}

function searchDictItem(){
	$("#dictForm").attr('action',jsCtx+"/dict/findDictItemList");
	document.forms["dictForm"].submit();
}

function checkedValue(){
	/*var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	var node=treeObj.getNodeByParam("id",arr[j]);
	if(node){
		treeObj.selectNode(node);
		var nodes=treeObj.getSelectedNodes();
		treeObj.checkNode(nodes[i],true,true);
	}*/
}




