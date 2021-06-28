 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约用户密码重置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>	
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}			
		</style>
		
		

	<script type="text/javascript" language="javascript">
		//判断输入的账号是否可用
	 	function doModifyPwd(){
	 	  var reg = new RegExp("^[0-9]*$"); 
	 	  var loginName = $("#loginName").textbox('getValue');
	 	  if(loginName=='') {
                alert("请输入登录帐号!");
                $("input").focus();
                return;
           }else if(!reg.test(loginName)){
           		alert("请输入数字账号！");
	 		}else{
	 		
	 			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/pwdmodify/findSigner.action",
					data : "loginName="+loginName,
					dataType : "json",
					success : function(msg){
						if(msg[0].result=="success"){
							openDialog("重置密码","<%=path%>/inner/pwdmodify/gotoSignerPwdMoify.action?loginName="+msg[0].message,400,300);
						}else{
							alert(msg[0].message);
						}
					},
					error : function(){
						alert("该用户不存在！");
					}
				});
	 		}
	 		$("#input").focus();
		}
		//清空文本框
		function doClean(){
			$("#loginName").textbox('setValue','');
			$("input").focus();
		}
		
		//打开对话框
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#openDL").show().dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true
			});
			$("#openDL").dialog('open');
			$("#openDL").dialog('center');
			$('#openDL').dialog('refresh', url);
		}
		
		//保存重置的密码
		function doPwdSave(){
			if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/pwdmodify/signerPwdModify.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(msg){
						if(msg[0].result=="success"){
							$("#newBtn").css("visibility","visible");
							$('#saveBtn').linkbutton('disable');
						}else{
							alert(msg[0].message);
						}
					},
					error : function(){
						alert("重置密码失败");
					}
				});
	 	}
	 	 
	 	function doBack(){
	 		$("#openDL").dialog('close');
	 	}
		
		function opeanWin(){
			var string1 = "用户名："+document.getElementById("loginname").value;
			var string2 = "真实姓名："+document.getElementById("name").value;
			var string3 = "重置后密码："+$("#newPwd").textbox('getValue');
			var url = "<%=path%>/inner/pwdmodify/printNewPwd.action?string1="+encodeURI(string1)+"&string2="+encodeURI(string2)+"&string3="+encodeURI(string3);
			window.open(url,"_blank"," directories=no, status=no,resizable=no, copyhistory=yes, width=500, height=500");
		}
	</script>
</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px;">
		<div id="p" class="easyui-panel" title="签约用户查询" style="width: 100%; height: 100%; background: #fafafa;"
			data-options="iconCls:'icon-collapse',fit:true,noheader:true">
			<div style="width: 100%;height:60px; background: #5BADFF;font-size:20px;text-align:center;line-height:60px;color:white">
				签约用户
			</div>
			<form method="post"  action="">
				<br/>
				<table style="text-align:center;margin:0px auto;">
					<tr>
						<td colspan="2" style="font-size:16px">输入需重置密码的登录账号：<input class="easyui-textbox" type="text" name="loginName" id="loginName" data-options="required:true"/></td>
					</tr>
					<tr>
						<td><hr style="border:0px"/></td>
					</tr>
					<tr>
						<td><a href="javascript:doModifyPwd()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交</a></td>
						<td><a href="javascript:doClean()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:80px">重置</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="openDL"></div>
		<br />
	</body>
</html>
