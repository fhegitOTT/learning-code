__CreateJSPath = function (js) {
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
    return path;
}

//bootPath
var bootPATH = __CreateJSPath("js/fwaq.js");
document.write('<script src="' + bootPATH + 'js/easyui/jquery.min.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/easyui/jquery.easyui.min.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/validator.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/jquery.cookie.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/common.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/yjCookie.js" type="text/javascript"></script>');

document.write('<link href="' + bootPATH + 'js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/icon.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/table_default_style.css" rel="stylesheet" type="text/css" />');

document.write('<script src="' + bootPATH + 'js/alertify/alertify.min.js" type="text/javascript"></script>');
document.write('<link href="' + bootPATH + 'js/alertify/alertify.core.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'js/alertify/alertify.default.css" rel="stylesheet" type="text/css" />');

