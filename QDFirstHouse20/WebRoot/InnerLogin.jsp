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
<title>内网用户登录</title>
<link href="css/loginstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/application.js"></script>
<script type="text/javascript" src="<%=path%>/js/safe/jsencrypt.js"></script>
<script type="text/javascript">

	function loadMsg() {
	    var message="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("message"))%>";
	    if(message=="0"){
	    	alert("用户名或密码错误！");
	    }else if(message=="1"){
	    	alert("数据库访问出错");
	    }else if(message=="3"){
	    	alert("输入验证码错误！");
	    }else if(message=="8"){
	    	alert("您已连续多次登录失败，请<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("minutes"))%>分钟后重试！");
	    }
	}
		
	$(function(){
		document.getElementById("loginName").focus(); 
		//首次获取验证码
    	//$("#imgVerify").attr("src","<%=path%>/login/getVerify.action?"+Math.random());
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
		
		if (loginName == "") {
			alert("用户名不能为空");
			return false;
		}
		if (password == "") {
			alert("密码不能为空");
			return false;
		}
		var inputStr = $("#check_input").val();
	    //if(inputStr==null || inputStr==""){
	    //	alert("请输入验证码！");
	    //    $("#check_input").focus();
	    //    return false;
	    //}

	    password = encryptPassword(password);  //将密码加密
	    $('#pwd').val(password);		//将加密后的密码存在隐藏域
	    
	    $.ajax({
            url : "<%=path%>/login/checkUserLogin.action",
            data: {loginName:loginName,password:password,inputStr:inputStr},
            success : function(datas) {
                if(datas == "FALSE"){
                	alert("登录用户名或者密码错误 ！");
                }else if(datas == "ERROR"){
                	alert("验证用户信息时出错 ！");
                }else{
                	document.getElementById("loginform").submit();
                }
            }
        });
	    
        //inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
        //$.ajax({
            //url : "<%=path%>/login/checkVerify.action",
            //data: {inputStr:inputStr},
            //success : function(datas) {
            //    if(datas == "FALSE"){
            ///    	alert("输入验证码错误！");
            //    	$("#imgVerify").attr("src","<%=path%>/login/getVerify.action?"+Math.random());
            //    	$("#check_input").focus();
            //    }else{
                	
             //   }
            //}
        //});
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
			document.getElementById("bodyBg").style.cssText = "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/login_i_bg.png',sizingMethod='scale');";
			
		}else{
			document.getElementById("bodyBg").style.cssText = "background-image:url(images/login_i_bg.png);background-position: center;background-repeat: no-repeat;background-attachment: fixed;overflow:hidden;background-size:100% 100%;"; 
			
		}
	
	}
</script>
</head>

<body id="bodyBg"  onload="loadMsg();" >
<div class="InnerLogin_bg">
 <div class="login_input">
 <form action="<%=path %>/login/InnerLogin.action" id="loginform" method="post">
<input type="hidden" name="password" id="pwd"/>
 <table border="0" cellspacing="0" cellpadding="0" id="login">
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
 			<!--  
 			<tr>
				<td>验证码</td>
				<td align="left">
					<input class="input" type="text" name="check_input" id="check_input" maxlength="4"  onkeyup="onKeyEnter(event);" style="width:60px"/>
				</td>
				<td align="left">
					<img id="imgVerify" src="" alt="点击更换验证码" title="点击更换验证码" width="80" height="26" onclick="getVerify(this);"/>
				</td>
			</tr>
			-->
 		</table>
 	</td>
 	<td valign="top">
 		<a href="#" id="btLg" name="btLg" onclick="checkUser()"><img src="images/btn1.png" name="Image1" width="57" height="55" border="0" id="Image1" /></a>
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
