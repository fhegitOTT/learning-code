 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业项目密码修改</title>
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
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<form name="frmInfo" id="frmInfo" method="post"  action="">
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="projectId" value="${projectId}"/>
	<input type="hidden" name="compId" value="${compId}"/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr>
			<td colspan="2" align="center" bgcolor="white">修改项目密码</td>
		</tr>
		<%
		String cmd = request.getAttribute("cmd").toString();
		//System.out.println("cmd="+cmd);
		if("update".equals(cmd)){ %>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">原密码：</font></td>
        	<td class="input_right" bgcolor="white"><input class="easyui-textbox" type="password" name="oldPwd" id="oldPwd" data-options="required:true,validType:'length[0,50]'" style="width:200px"></input></td>
		</tr>
		<%} %>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">新密码：</font></td>
        	<td class="input_right" bgcolor="white"><input class="easyui-textbox" type="password" name="newPwd" id="newPwd" data-options="required:true,validType:'length[6,50]'" style="width:200px"></input></td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">确认新密码：</font></td>
        	<td class="input_right" bgcolor="white"><input class="easyui-textbox" type="password" name="newPwdConfirm" id="newPwdConfirm" data-options="required:true,validType:'length[6,50]'" style="width:200px"></input></td>
		</tr>
	</table>	
	
	<br/>
	<div align="center">
		<a href="javascript:doPwdSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
	</div>	
	</form>
</body>
</html>
