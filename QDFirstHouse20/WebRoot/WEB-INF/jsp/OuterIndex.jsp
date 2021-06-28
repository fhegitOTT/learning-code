<%@page import="com.netcom.nkestate.system.action.LoginAction"%>
<%@page import="com.netcom.nkestate.system.vo.SmObjectVO"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@page import="com.netcom.nkestate.framework.MiniConfiger"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.common.NKRSAUtil"%>



<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 	String path = request.getContextPath();
 	SmUserVO userVO = (SmUserVO)session.getAttribute("LgUser");
 	int loginType = 0;
 	if(userVO!=null){
 		loginType = userVO.getLoginType();
 	}

	String datetime = DateUtil.format(DateUtil.getSysDate(), "yyyy-MM-dd HH:mm:ss");
	String encodeUserInfo = NKRSAUtil.encryptBASE64ForPage(
				NKRSAUtil.encryptByPublicKey(userVO.getUserId()+"@@"+datetime, NKRSAUtil.PUBLIC_KEY_STR)
	);
 	
 	String url = MiniConfiger.getProperty("kfqyyslUrl");	
 	String kfqyyslUrl = url+"/LoginSSO.jsp?data="+encodeUserInfo;
 %>
<!DOCTYPE html>
<html>
<head  id="Head1">
	<meta charset="UTF-8">
	<title>青岛市新建商品房网上备案系统</title>
	<link rel="stylesheet" href="<%=path%>/css/default.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/js/easyui/themes/icon.css" >
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/js/easyui/themes/default/easyui.css"  id="easyuiTheme">
	
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/easyui/jquery.min.js" ></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js" ></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/easyui/easyui-lang-zh_CN.js" ></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/jquery.cookie.js" ></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/changeEasyuiTheme.js" ></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/syUtils.js"></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/validator.js"></script>
	<script charset="UTF-8" type="text/javascript" src="<%=path%>/js/tipshow.js"></script>
	<script type="text/javascript" src="<%=path%>/js/outlook.js"></script>
	<style type="text/css">
#topnav,#topnav a {
	color: #666;
	font-size: 19px;
	text-decoration: none;
}
#topnav a:hover {
	color: #fff;
	font-size: 19px;
	text-decoration: none;
}
#topnav a {
	height: 26px;
	text-align: center;
	line-height: 26px;
	padding-right: 15px;
	padding-left: 15px;
	padding-bottom: 3px;	
	border: 1px 1px 0px 1px solid #95b9e7;
}	

.test{
		
}

.test:HOVER{
	font-color:red;
	text-decoration:none;
}
	
	#mainPanle .tabs-panels>.panel>.panel-body {
		overflow: hidden;
	}
</style>
</head>
<body class="easyui-layout" onload="" >
<object id="HTCOM" classid="CLSID:1D92A4DB-A74E-4DD6-8D9A-CE8E8706BC1F"></object >
	<div data-options="region:'north',border:false" style="height:100px;padding-top: 10px;overflow:hidden; background: url('<%=path%>/images/outer_index_01.gif') no-repeat center;">
		<div id="dao_right_bg"	style=" width: 630px; height: 50px; float: right;text-align: right">
			<a style="color: #FF3333; font-size: 14"><b>欢迎您：${LgUser.displayName}</b></a>&nbsp;&nbsp;
			<br><a style="color: #FF3333; font-size: 14"><b>所属企业：${LgUser.orgName}</b></a>&nbsp;&nbsp;
			<br><a style="color: #FF3333; font-size: 14"><b>登录时间：${loginTime}</b></a>&nbsp;&nbsp;
		</div>		
		<div style="background:#ececec;height:26px; width:100%;border:1px solid #fff;padding: 0px;position: absolute; left: 0px; bottom: 0px;" id="topnav">
			&nbsp;&nbsp;
		</div>
		<div style="position: absolute; right: 5px; bottom: 0px; " >
		<table border=0>
			<tr>
				<td>
					<table border=0 id="marTable">
						<tr>
							<td height="20px" valign="middle">
								<marquee direction="left"  onMouseOut="this.start()" onMouseOver="this.stop()" scrollamount="2" width="200px" height="14px" 
								id="noteMarquee" style="vertical-align: middle;"></marquee>&nbsp;&nbsp;	
							</td>
						</tr>
					</table>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-panel">控制面板</a>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="gotoKFQYYSL()" iconCls="icon-leave" plain="true">企业预受理</a>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" onclick="logout()" iconCls="icon-leave" plain="true">退出</a>
				</td>
			</tr>
		</table>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="modifyPassword();">修改密码</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'导航'" style="width:150px;">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:20px;">
		<div class="footer">技术支持：上海南康科技有限公司</div>
	</div>
	<div id="mainPanle" region="center"style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true">
		</div>
	</div>
	<div id="modifyPassword">
    	
    </div>
    <div id="showNotice">
    	
    </div>
    <div id="showHostInfo">
    	
    </div>
</body>
<script type="text/javascript">
		var CryptoAgent = "";
		
		$(function() {
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
			 $('#btnEp').click(function() {
                modifyPass();
            })
             $('#btnQuit').click(function() {
                logout();
            })
			//showOneLevelMenu();
			showFirstMenu();
			menuChange('');
			checkModifyPass();
			//加载公告
			loadNote();
			//每隔五分钟刷新一下公告
			//self.setInterval("loadNote()",1000*60*5);
			self.setInterval("checkKeyUser()",1000*60*5);
		});
		
		function checkKeyUser(){
			<%if(loginType==1){%>
				if(HTCOM.CheckKeyWorkStatus()!=1){
					try {
		                var bSelectCertResult = CryptoAgent.SelectCertificate("", "", "");
		                // Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
		                if (!bSelectCertResult){
		                    //var errorDesc = CryptoAgent.GetLastErrorDesc();
		                    logout();
			                //return;
		                }
		            }catch(e){
		            	logout();
			            //return;
		            }
					//logout();
				}
			<%}%>
		}
		 //修改密码
        function checkModifyPass() {
           $.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path %>/login/checkSignerPWChanged.action",
				data: {},
			    success : function(datas) {
					if(datas=="FALSE"){
						modifyPassword();
					}
				},
				error : function(){
					//alertify.error("密码修改失败");
				}
			});
            
        }
		
		//显示头部一级菜单
		function showOneLevelMenu(){
			var menu = "";
			var menu2 = "";
			var index = 1;
			<%
				String parentCodes="";
				SmUserVO user = (SmUserVO)session.getAttribute("LgUser");
				List<SmObjectVO> list = user.getObjectList();
				for(SmObjectVO vo : list){	
					if(vo.getTreeId().length()==0 ){
			%>
				
				menu += "<a name='cmenu' id='btn"+index+"' href='javascript:void(0)' onclick=menuChange('<%=vo.getTreeId()%>')>";
				menu += "<font style='font-size:18px;font-family:楷体'><%=vo.getName()%></font></a>&nbsp;&nbsp;&nbsp;";
				index++;
				
			<%
					
					}
				}
			%>
			
			$("#topnav").append(menu);
		}
		
		
		function menuChange(objectCodes){
			closeAllTabs();
			$.ajax({
					cache : true,
					type : "POST",
					url : "<%=path%>/login/signerMenuChange.action?objectCodes="+objectCodes,
					//data : $('#addComplaintForm').serialize(),
					async : false,
					error : function(request) {
						alert("提交失败！");
					},
					success : function(data) {
						//alert(data.treeStr);
						var _menus = eval("(" + data.treeStr + ")");
						emptyMenu();
						InitLeftMenu(_menus);
					}
				});
		}	
		
		
		
		//默认点击第一个主菜单
		function showFirstMenu(){
			var menu = document.getElementsByName("cmenu");
			if(menu.length>0){
				menu[0].onclick();
				document.getElementById("btn1").style.backgroundColor="#95b9e7";				
			}
			
		}
		
		//更改密码
		function modifyPassword(){	
			openDialog("修改密码","<%=path%>/login/MainNav.action?url=ModifyPassword",450,240);			
		}
		
		
		$(function() {
			$("#topnav a").click(function() {
				$("#topnav a").css("background-color", "#ececec");
				$(this).css("background-color", "#95b9e7");
			});
		});
		
		//注销
		function logout() {
			window.top.location.href = "<%=path%>/login/OuterLogout.action";
		}
	
		
		//关闭所有tab页
		function closeAllTabs(){
			$.each($("#tabs").tabs('tabs'),
				function(index,tab){
					$("#tabs").tabs('close',0);
			});
		}
		
		
		//加载公告
		function loadNote(){
			$("#noteMarquee a").remove();
			var marqueeText = "";
			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path %>/login/checkShowBulletin.action",
					data : {},
					success : function(data){
						if(data=="TRUE"){
							showNoteWindow();
						}
					},
					error : function(){					
						//alert("获取公告失败！");
					}
			});
		}
		
		//显示公告页面
		function showNoteWindow(){
			openNoticeDialog("公告","<%=path%>/login/showBulletin.action",650,500);
		}
		
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#modifyPassword").show().dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true
			});
			$("#modifyPassword").dialog('open');
			$("#modifyPassword").dialog('center');
			$('#modifyPassword').dialog('refresh', url);
		
		}
		function closeDialog(){
			$("#modifyPassword").dialog('close');
		}		
		function openNoticeDialog(tempTitle,url,tempWidth,tempHeight){
			$("#showNotice").show().dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true
			});
			$("#showNotice").dialog('open');
			$("#showNotice").dialog('center');
			$('#showNotice').dialog('refresh', url);
		
		}
		
		//保存修改的密码
		function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		if(!checkPassword()){
	 			return;
	 		}
	 		var password = $("#password").val();
			var password1=$("#newPassword1").val();
			var password2=$("#newPassword2").val();
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path %>/login/modifySignerPassword.action",
					data : {"password":password,"newPassword1":password1,"newPassword2":password2},
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							$("#modifyPassword").dialog('close');
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("密码修改失败");
					}
				});
	 	}
		
		//检查修改密码页面的值
		function checkPassword(){
			var password = $("#password").val();
			var password1 = $("#newPassword1").val();
			var password2 = $("#newPassword2").val();
			if(password1!=password2){
				alert("两次输入的新密码不一致！");
				return false;
			}
			if(password==password1){
				alert("新密码和旧密码重复！");
				return false;
			}
			if(checkPass(password1)<2){
			   alert("密码至少要有数字和字母！");
			   return false;
			}
			return true;
		}
		
		//检查密码的复杂度
		function checkPass(pass){ 
			var ls = 0; 
			if(pass.match(/([a-z])+/)){  ls++; }  
			if(pass.match(/([0-9])+/)){  ls++; }  
			if(pass.match(/([A-Z])+/)){   ls++; }  
			if(pass.match(/[^a-zA-Z0-9]+/)){ ls++;}  
			return ls; 
		} 
		
		
		//单点登录到开发企业预受理系统
		function gotoKFQYYSL(){
			var iTop = (window.screen.availHeight-600)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-1024)/2; //获得窗口的水平位置;
			strFeatures = "height=768, width=1366, top="+iTop+", left="+iLeft+", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no";
			window.open("<%=kfqyyslUrl%>","预审系统", strFeatures);	
		}
		
</script>
</html>