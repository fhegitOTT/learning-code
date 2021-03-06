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
var bootPATH = __CreateJSPath("js/application.js");

document.write('<script type="text/javascript" src="' + bootPATH + '/js/easyui/jquery.min.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/easyui/jquery.easyui.min.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/easyui/easyui-lang-zh_CN.js "></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/validator.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/jquery.cookie.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/common.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + '/js/yjCookie.js"></script>');

document.write('<link rel="stylesheet" type="text/css" href="' + bootPATH + '/js/easyui/themes/default/easyui.css"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + bootPATH + '/js/easyui/themes/icon.css"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + bootPATH + '/css/table_default_style.css"/>');

document.write('<script type="text/javascript" src="' + bootPATH + '/js/alertify/alertify.min.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="' + bootPATH + '/js/alertify/alertify.core.css"/>');
document.write('<link rel="stylesheet" type="text/css" href="' + bootPATH + '/js/alertify/alertify.default.css"/>');


function showContractDetail(contractId){
	window.open(bootPATH+'/salecontractquery/contractDetailNavigating.action?contractID='+contractId);
}

function showMonitorDetail(monitorId){
	window.open(bootPATH+'/monitorquery/monitorDetailNavigating.action?monitorID='+monitorId);
}

function showHouseDetail(houseId){
	window.open(bootPATH+'/housequery/houseDetailNavigating.action?houseID='+houseId);
}


function checkIdcard(idcard)
{
    var Errors=new Array(
    "????????????!",
    "???????????????????????????!",
    "????????????????????????????????????????????????????????????!",
    "???????????????????????????!",
    "?????????????????????!"
    );
    var area={11:"??????",12:"??????",13:"??????",14:"??????",15:"?????????",21:"??????",22:"??????",23:"?????????",31:"??????",32:"??????",33:"??????",34:"??????",35:"??????",36:"??????",37:"??????",41:"??????",42:"??????",43:"??????",44:"??????",45:"??????",46:"??????",50:"??????",51:"??????",52:"??????",53:"??????",54:"??????",61:"??????",62:"??????",63:"??????",64:"??????",65:"??????",71:"??????",81:"??????",82:"??????",91:"??????"}
    var idcard,Y,JYM;
    var S,M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //????
    if(area[parseInt(idcard.substr(0,2))]==null) 
    {
        alert(Errors[4]);
        return false;
    }
    //???????????
    switch(idcard.length){
        case 15:
            if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//??????????
            } else {
                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//??????????
            }
            if(ereg.test(idcard)) 
                return true;
            else 
                {
                alert(Errors[2]);
                return false;
            }
            break;
        case 18:
        //18???????
        //?????????? 
        //????:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
        //????:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
        if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//???????????????
        } else {
            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//???????????????
        }
        //??????????
        if(ereg.test(idcard)){
        //?????
            S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
            + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
            + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
            + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
            + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
            + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
            + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
            + parseInt(idcard_array[7]) * 1 
            + parseInt(idcard_array[8]) * 6
            + parseInt(idcard_array[9]) * 3 ;
            Y = S % 11;
            M = "F";
            JYM = "10X98765432";
            M = JYM.substr(Y,1);//?????
            if(M == idcard_array[17].toUpperCase()) {
                return true;
            }else 
            {
                alert(Errors[3]);
                return false;
            }
        }else{ 
            alert(Errors[2]);
            return false;
        }
        break;
    default:
        alert(Errors[1]);
        break;
    }
    return false;
}


