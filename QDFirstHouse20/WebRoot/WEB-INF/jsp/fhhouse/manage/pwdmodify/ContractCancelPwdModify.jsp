 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同乙方密码重置</title>
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
			
			.input_required:before{
				content:"* ";
				color:red;
			}
			tr{
				background-color:white;
			}			
		</style>
		
		<script type="text/javascript">
		
		</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto; ">
	<form name="frmInfo" id="frmInfo" method="post"  action="">
	<input type="hidden" id="contractID" name="contractID" value="${contractID}"/>
	
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr>
			<td colspan="2" align="center" bgcolor="white">重置密码</td>
		</tr>
		<tr>
			<td colspan="2" align="center" bgcolor="white">请选择用户：<input class="easyui-combobox" name="serial" id="buyer"
					data-options="url:'<%=path%>/inner/pwdmodify/queryBuyer.action?contractID=${contractID}',valueField:'serial',textField:'buyer_name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" /></td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">重置密码：</font></td>
        	<td class="input_right" bgcolor="white"><input class="easyui-textbox" type="password" name="newPwd" id="newPwd" data-options="required:true,validType:'length[6,20]'" style="width:200px"></input></td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">确认密码：</font></td>
        	<td class="input_right" bgcolor="white"><input class="easyui-textbox" type="password" name="newPwdConfirm" id="newPwdConfirm" data-options="required:true,validType:'length[6,20]'" style="width:200px"></input></td>
		</tr>
	</table>	
	
	<br/>
	<div align="center">
		<a href="javascript:doPwdSave()" id="saveBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">确认</a>
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:80px">关闭</a>
	</div>
	<div id="newBtn" style="visibility: hidden;margin:0 auto;text-align:center;">
		<p>重置密码成功！</p>
		<a href="javascript:opeanWin()" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">打印</a>
	</div>
	</form>
</body>
</html>
