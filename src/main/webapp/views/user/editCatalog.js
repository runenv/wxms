var key;
$(document).ready(function(){
	$("#catalogForm").validate({
		rules: {
			"catalogVO.catalogDescription":{
				lengthInUtf8Bytes:{
					param:[1,99]
				}
			}
		}
	});
	initRoleChk();
	var $catalogId=$("input[name='catalogVO.catalogId']");
	$("input[name='catalogVO.parentName']").val("根节点");
	if(""!=$catalogId.val()){
		findCatalog($catalogId.val());
		$("#btnAdd").css("display","");
	}
	$(".ztree").each(function(){
		var catalogId=$(this).attr('treeId');
		if(""!=catalogId){
			var catalogTree=$(this).attr('id');
			catalogZTree(catalogId,catalogTree);
		}
	});
	key = $("#key");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", searchNode)
	.bind("input", searchNode);
	$("#btnAdd").click(function(){
		addCatalog();
		this.style.display = "none";
	});
	$("#btnDelete").click(function(){
		deleteCatalog();
	});
});
function catalogZTree(catalogId,catalogTree){
    var treeUrl=jsCtx+"/services/catalog/catalog/catalogTree/"+catalogId;
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
	var zTree = $.fn.zTree.getZTreeObj("catalogTree");
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
	var treeObj = $.fn.zTree.getZTreeObj("catalogTree");
	treeObj.expandAll(true);
	var nodes=treeObj.getNodes();
	$("#catalogTree li").hide();
	nodesFilter(nodes,value);
}
function nodesFilter(nodes,value){
	var treeObj=$.fn.zTree.getZTreeObj("catalogTree");
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
		var treeObj = $.fn.zTree.getZTreeObj("catalogTree");
		treeObj.refresh();
		return;
	}
	treeFilter(value);
}
function findCatalog(catalogId){
	var catalogUrl=jsCtx+"/services/catalog/catalog/catalog/"+catalogId;
	$.ajax({
    	url:catalogUrl,
    	type:"GET",
    	async:true,
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var $catalogUrl = $("textarea[name='catalogVO.catalogUrl']");
    		var $description = $("textarea[name='catalogVO.catalogDescription']");
    		$catalogUrl.val("");
    		$description.val("");
    		var json = eval(data.Rows);
    		fillForm(json,"catalogVO");
    		//初始化角色复选框
    		var roleIds= json.arrId;
			$("input[name='catalogVO\\.arrId']").each(function(){
				$(this).attr("checked",false);
				if(roleIds instanceof Array){
					for(var arr=0;arr<roleIds.length;arr++){
						if($(this).val() == roleIds[arr]){
							$(this).attr("checked",true);
							break;
						}
					}
				}else{
					if($(this).val() == roleIds){
						$(this).attr("checked",true);
					}
				}
			});
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}
function zTreeOnClick(event, treeId, treeNode){
	findCatalog(treeNode.catalogId);
	var $parentId = $("input[name='catalogVO.parentId']");
	var $parentName = $("input[name='catalogVO.parentName']");
	$parentId.val(treeNode.parentId);
	var parentNode = treeNode.getParentNode();
	$parentName.val(parentNode?parentNode.catalogName:"根节点");
	$("#btnAdd").css("display","");
}
function addCatalog(){
	var $catalogId = $("input[name='catalogVO.catalogId']");
	var $catalogName = $("input[name='catalogVO.catalogName']");
	var $catalogValue = $("input[name='catalogVO.catalogValue']");
	var $catalogUrl = $("textarea[name='catalogVO.catalogUrl']");
	var $description = $("textarea[name='catalogVO.catalogDescription']");
	var $parentId = $("input[name='catalogVO.parentId']");
	var $parentName = $("input[name='catalogVO.parentName']");
	$parentId.val($catalogId.val());
	$parentName.val($catalogName.val());
	$catalogId.val("");
	$catalogName.val("");
	$catalogValue.val("");
	$catalogUrl.val("");
	$description.val("");
}

function deleteCatalog(){
	if(!confirm("确认需要删除吗？"))return;
	$("#catalogForm").attr('action',jsCtx+"/catalog/deleteCatalog");
	$("#catalogForm").submit();
}

function searchCatalogItem(){
	$("#catalogForm").attr('action',jsCtx+"/catalog/findCatalogItemList");
	document.forms["catalogForm"].submit();
}

function checkedValue(){
	/*var treeObj = $.fn.zTree.getZTreeObj("catalogTree");
	var node=treeObj.getNodeByParam("id",arr[j]);
	if(node){
		treeObj.selectNode(node);
		var nodes=treeObj.getSelectedNodes();
		treeObj.checkNode(nodes[i],true,true);
	}*/
}

function initRoleChk(){
	var lookupUrl=jsCtx+"/services/user/role/lookupRole";
	$.ajax({
    	url:lookupUrl,
    	type:"GET",
    	async:false,
    	cache:false,
    	dataType:"json",
    	timeout:10000,
    	success:function(data){
    		var json = eval(data.Rows);
    		$.each(json, function(i,item){
           		var chk="<input type='checkbox' name='catalogVO.arrId' value='"+item.roleId+"' />"+item.roleName;
           		$("#initRoleChk").append(chk);
    		});
    	},
    	error:function(){
    		alert("请求失败");
    	}
    });
}



