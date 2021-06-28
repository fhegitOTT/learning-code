<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 	String path = request.getContextPath();
 	//session.setAttribute("LgUser", null);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>签约用户登录</title>
<link href="css/loginstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/application.js"></script>
<script type="text/javascript" src="<%=path%>/js/safe/jsencrypt.js"></script>
<script type="text/javascript">

	var CryptoAgent = "";
	function loadMsg() {
	    var message="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("message"))%>";
	    if(message=="0"){
	    	alert("用户名或密码错误！");
	    }else if(message=="1"){
	    	alert("数据库访问出错！");
	    }else if(message=="2"){
	    	alert("此签约人用户无效！");
	    }else if(message=="3"){
	    	alert("输入验证码错误！");
	    }else if(message=="8"){
	    	alert("您已连续多次登录失败，请<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("minutes"))%>分钟后重试！");
	    }
           
	}
	
	$(function(){
		try{
			var eDiv = document.createElement("div");
            if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
                if (window.navigator.cpuClass == "x86") {
                    eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\"CryptoKit.SDDR.x86.cab\" classid=\"clsid:88BE7301-91C1-4DD7-9705-44C782273E07\" ></object>";
                }
                else {
                    eDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\"CryptoKit.SDDR.x64.cab\" classid=\"clsid:EC5FE2ED-1721-4AF1-AA96-30048392A4E2\" ></object>";
                }
            }
            else {
                eDiv.innerHTML = "<embed id=\"CryptoAgent\" type=\"application/npCryptoKit.SDDR.x86\" style=\"height: 0px; width: 0px\">";
            }
            document.body.appendChild(eDiv);
            CryptoAgent = document.getElementById("CryptoAgent");
	        var cspList = "CFCA CSP v4.0||CFCA Ulan CSP v4.0";
	        CryptoAgent.SetSM2CSPList(cspList);
		}catch(e){
			console.log(e);
		}
		document.getElementById("loginName").focus(); 
		//首次获取验证码
    	$("#imgVerify").attr("src","<%=path%>/login/getVerify.action?"+Math.random());
    	secBoard(1);
	});



	//点击回车键
	function onKeyEnter(event){
		if(13 == event.keyCode || 108 == event.keyCode){
			checkUser();
		}
	}



	function checkUser() {
		var loginName = document.getElementById("loginName").value;
		var password = document.getElementById("password").value;
		var loginType = document.getElementById("loginType").value;
		
		if (loginName == "") {
			alert("用户名不能为空");
			return false;
		}
		if (password == "") {
			alert("密码不能为空");
			return false;
		}
		var inputStr = $("#check_input").val();
	    if(inputStr==null || inputStr==""){
	    	alert("请输入验证码！");
	        $("#check_input").focus();
	        return false;
	    }

	    password = encryptPassword(password); //添加密码加密
	    $('#pwd').val(password);
	    
        inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
        $.ajax({
            url : "<%=path%>/login/checkVerify.action",
            data: {inputStr:inputStr},
            success : function(datas) {
                if(datas == "FALSE"){
                	alert("输入验证码错误！");
                	
                	$("#imgVerify").attr("src","<%=path%>/login/getVerify.action?"+Math.random());
                	$("#check_input").focus();
                }else{
                	$.ajax({
			            url : "<%=path%>/login/checkSignerLogin.action",
			            data: {loginName:loginName,password:password,inputStr:inputStr,loginType:loginType},
			            success : function(datas) {
			                if(datas == "TRUE"){
			                	document.getElementById("loginform").submit();
			                }else if(datas == "E1"){
			                	alert("用户名或密码错误！");
			                }else if(datas == "E3"){
			                	alert("签约人无效！");
			                }else if(datas == "E4"){
			                	alert("非登录时间未设置，请先从内网设置非登录时间。 ");
			                }else if(datas == "E5"){
			                	alert("系统维护中，不可登录。 ");
			                }else if(datas == "ERROR"){
			                	alert("验证用户信息时出错 ！");
			                }
			            }
			        });
                }
            }
        });
	}
	
	//获取验证码
	function getVerify(obj){
	    obj.src = "<%=path%>/login/getVerify.action?"+Math.random();
	}

	function encryptPassword(password){
		var encrypt = new JSEncrypt();
		encrypt.setPublicKey(publicKey);
		return encrypt.encrypt(password);
	}
	
	function IEVersion() {//检查是否为IE浏览器，以及IE版本
		var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
		var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
		var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
		if(isIE) {
			var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
			reIE.test(userAgent);
			var fIEVersion = parseFloat(RegExp["$1"]);
			if(fIEVersion == 7) {
				return 7;
			} else if(fIEVersion == 8) {
				return 8;
			} else if(fIEVersion == 9) {
				return 9;
			} else if(fIEVersion == 10) {
				return 10;
			} else {
				return 6;//IE版本<=7
			}   
		} else if(isEdge) {
			return 15;//edge
		} else if(isIE11) {
			return 11; //IE11  
		}else{
			return 20;//不是ie浏览器
		}
	}

	function IEcheck(){
		//debugger;
		var x = IEVersion();
		
		if(x <= 8){
			document.getElementById("bodyBg").style.cssText = "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/login_o_bg.png',sizingMethod='scale');";
			
		}else{
			document.getElementById("bodyBg").style.cssText = "background-image:url(images/login_o_bg.png);background-position: center;background-repeat: no-repeat;background-attachment: fixed;overflow:hidden;background-size:100% 100%;"; 
			
		}
	
	}
	function secBoard(n)
	{
		var i=0;
		for(i=1;i<2;i++){
			//secTable.cells[i].className="tabl2";
			//secTable.cells[n].className="tabl1";
			document.getElementById("td"+i).className="tabl2";
			//document.getElementById("td"+n).className="tabl1";
		}
		if(n=="1"){
		
			loginform.loginName.readOnly = true;
			loginform.loginName.value="";
			loginform.loginType.value="1";
		    if(HTCOM.CheckKeyWorkStatus()!=1){
		    	try {
	                var bSelectCertResult = CryptoAgent.SelectCertificate("", "", "");
	                // Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
	                if (!bSelectCertResult){
	                    //var errorDesc = CryptoAgent.GetLastErrorDesc();
	                    alert("未检测到设备！");
		                return;
	                }
	                var InfoTypeID = "SubjectCN";
	             	var InfoContent = CryptoAgent.GetSignCertInfo(InfoTypeID);
	             	if (!InfoContent) {
		               //var errorDesc = CryptoAgent.GetLastErrorDesc();
		               alert("未检测到设备！");
		               return;
		            }
		            InfoContent = InfoContent.substring(InfoContent.indexOf("@")+1);
		            loginform.loginName.value = InfoContent.substring(0,InfoContent.indexOf("@"));
                 }catch (e) {
	                //var errorDesc = CryptoAgent.GetLastErrorDesc();
	                alert("未检测到设备！");
	                return;
	             }
				
			}else{
				try{
					var ContainList = HTCOM.HCOMListContainers(0);
					var ContainNameArr = ContainList.split("|");
					var certdata = HTCOM.HCOMReadCert(0,ContainNameArr[0],1);
					var loginname = HTCOM.HCOMGetCertCN(certdata);
					//alert(loginname);
					loginform.loginName.value = loginname;
				} catch(e) {
					alert("获取证书信息失败！");
					return;
				}
			}
		}else{
			loginform.loginName.readOnly = false;
			loginform.loginName.value="";
			loginform.loginType.value="0";
		}
	}
	
</script>
<style type=text/css>

td.tabl2 {
	font-family: "宋体";
	font-size: 14px;
	color: #333434;
	background-image: url(images/login/tab_bg2.gif);
	background-repeat: no-repeat;
	width: 120px;
	text-align: center;
	height: 26px;
	margin-left: 13px;
}
</style>
</head>

<body id="bodyBg"  onload="loadMsg();" >
<object id="HTCOM" classid="CLSID:1D92A4DB-A74E-4DD6-8D9A-CE8E8706BC1F"></object >
<div class="login_bg">
 <div class="login_input">
 <form action="<%=path %>/login/OuterLogin.action" id="loginform" method="post">
 <input type="hidden" name="password" id="pwd" />
 <input type="hidden" name="loginType" id="loginType" value="0" />

 <table border="0" cellspacing="0" cellpadding="0" id="login">
 <tr>
 <td colspan="2">
 	<table border=0 cellspacing=0 cellpadding=0 id=secTable>
    <tr height=20 align=center>
     <!-- <td class="tabl1" name="td0" id="td0" onclick="secBoard(0)"><font color="white">用户名登录</font></td>-->
    <!--  <td class="tabl2" name="td1" id="td1" onclick="secBoard(1)"><font color="white">Key登录</font></td>-->
     <td class="tabl1" name="td0" id="td0" onclick="secBoard(0)"><font color="white">用户名登录</font></td>
   </table>
 </td>
 </tr>
 <tr>
 	<td>
 		<table border="0" cellpadding="0" cellspacing="0">
 			<tr>
 				<td>用户名</td>
 				<td colspan="2" align="left"><label>
				      <input class="input" type="text" name="loginName" id="loginName" />
				    </label>
    			</td>
 			</tr>
 			<tr>
 				<td>密　码</td>
 				<td colspan="2" align="left">
 					<input class="input" type="password" id="password"  onkeyup="onKeyEnter(event);" />
 				</td>
 			</tr>
 			<tr>
				<td>验证码</td>
				<td align="left">
					<input class="input" type="text" name="check_input" id="check_input" maxlength="4"  onkeyup="onKeyEnter(event);" style="width:60px"/>
				</td>
				<td align="left">
					<img id="imgVerify" src="" alt="点击更换验证码" title="点击更换验证码" width="80" height="26" onclick="getVerify(this);"/>
				</td>
			</tr>
 		</table>
 	</td>
 	<td valign="top">
 		<a href="#" id="btLg" name="btLg"  onclick="checkUser()"><img src="images/btn1.png" name="Image1" width="57" height="55" border="0" id="Image1" /></a>
 	</td>
 </tr>
 
 
 </table>
 </form>
 </div>
		<div class="foot">技术支持:上海南康科技有限公司</div>
</div>
</body>
<script type="text/javascript">
	IEcheck();
</script>
</html>
