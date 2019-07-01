var key;
$(document).ready(function(){
	var $itemId=$("input[name='dictItemVO.itemId']");
	$("input[name='dictItemVO.parentName']").val("根节点");
	if(""!=$itemId.val()){
		findDictItem($itemId.val());
		$("#btnAdd").css("display","");
	}
	$(".ztree").each(function(){
		var itemId=$(this).attr('treeId');
		if(""!=itemId){
			var dictItemTree=$(this).attr('id');
			dictItemZTree(itemId,dictItemTree);
		}
	});
	key = $("#key");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
	$("#btnAdd").click(function(){
		addDictItem();
		this.style.display = "none";
	});
	$("#btnDelete").click(function(){
		if (window.confirm("确定删除此记录？")){
			deleteDictItem();
		}
	});
});
function dictItemZTree(dictItemId,dictItemTree){
    var treeUrl=jsCtx+"/services/lookup/dictionary/dictItemTree/"+dictItemId;
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
				title: "itemCode",
				name:"itemName"
			},
			isSimpleData : true, 
			simpleData: {
				enable:true,
				idKey:'itemId',
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
    		$.fn.zTree.init($("#"+dictItemTree), setting, zNodes);
    		var zTree = $.fn.zTree.getZTreeObj(dictItemTree);
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
	var zTree = $.fn.zTree.getZTreeObj("dictItemTree");
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
	var treeObj = $.fn.zTree.getZTreeObj("dictItemTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#dictItemTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("dictItemTree");
	for(var i=0;i<nodes.length;i++){
		var _node=nodes[i];
		if(_node.children){
			nodesFilter(_node.children,value);
		}
		nodes[i].highlight=false;
		treeObj.updateNode(nodes[i]);
		if(nodes[i].itemName.indexOf(value)!=-1){
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
	var zTree = $.fn.zTree.getZTreeObj("dictItemTree");
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	if (value === ""){
		var treeObj = $.fn.zTree.getZTreeObj("dictItemTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}
function findDictItem(itemId){
	var dictUrl=jsCtx+"/services/lookup/dictionary/dictItem/"+itemId;
	$.ajax({
    	url:dictUrl,
    	type:"GET",
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var json = eval(data.Rows);
    		fillForm(json,"dictItemVO");
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
function zTreeOnClick(event, treeId, treeNode){
	findDictItem(treeNode.itemId);
	var $parentId = $("input[name='dictItemVO.parentId']");
	var $parentName = $("input[name='dictItemVO.parentName']");
	$parentId.val(treeNode.parentId);
	var parentNode = treeNode.getParentNode();
	$parentName.val(parentNode?parentNode.itemName:"根节点");
	$("#btnAdd").css("display","");
}
function addDictItem(){
	var $itemId = $("input[name='dictItemVO.itemId']");
	var $itemName = $("input[name='dictItemVO.itemName']");
	var $itemCode = $("input[name='dictItemVO.itemCode']");
	var $description = $("input[name='dictItemVO.description']");
	var $parentId = $("input[name='dictItemVO.parentId']");
	var $parentName = $("input[name='dictItemVO.parentName']");
	$parentId.val($itemId.val());
	$parentName.val($itemName.val());
	$itemId.val("");
	$itemName.val("");
	$itemCode.val("");
	$description.val("");
}

function deleteDictItem(){
	if(!confirm("确认需要删除吗？"))return;
	$("#dictItemForm").attr('action',jsCtx+"/dict/deleteDictItem");
	$("#dictItemForm").submit();
}

function searchDict(){
	$("#dictItemForm").attr('action',jsCtx+"/dict/findDictList");
	document.forms["dictItemForm"].submit();
}






