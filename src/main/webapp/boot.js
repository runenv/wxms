getJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    try{
    	console.log("jsCtx: "+path);
    }catch(e){}
    return path;
};

getJSVersion = function (js) {
	var version = "version=1.0";
	var scripts = document.getElementsByTagName("script");
	for (var i = 0, l = scripts.length; i < l; i++) {
		var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            version = ss[1];
            break;
        }
	}
	return version;
};

var jsCtx = getJSPath("/boot.js");
var jsVersion=getJSVersion("?");

document.write('<link rel="stylesheet" type="text/css" href="' + jsCtx + '/css/app.css?' + jsVersion + '"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + jsCtx + '/js/zTree/zTreeStyle/zTreeStyle.css?' + jsVersion + '"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + jsCtx + '/css/login.css?' + jsVersion + '"/>');

document.write('<script type="text/javascript" src="' + jsCtx + '/js/app/json2.js?' + jsVersion + '" ></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/jquery-1.7.2.js?' + jsVersion + '" ></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/jquery.validate.js?' + jsVersion + '" ></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/jquery.metadata.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/validate-methods.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/ajaxfileupload.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/zTree/jquery.ztree.core-3.4.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/zTree/jquery.ztree.exhide-3.4.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/zTree/jquery.ztree.excheck-3.4.js?' + jsVersion + '"></script>');

document.write('<script type="text/javascript" src="' + jsCtx + '/js/My97DatePicker/WdatePicker.js?' + jsVersion + '"></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/jquery/messages_cn.js?' + jsVersion + '" ></script>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/app/app.js?' + jsVersion + '" ></script>');

document.write('<link rel="stylesheet" type="text/css" href="' + jsCtx + '/js/easyui/themes/default/easyui.css?' + jsVersion + '"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + jsCtx + '/js/easyui/themes/icon.css?' + jsVersion + '"/>');
document.write('<script type="text/javascript" src="' + jsCtx + '/js/easyui/jquery.easyui.min.js?' + jsVersion + '"></script>');



