 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>费用总账查看</title>
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
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<form id="frmInfo" name="frmInfo" action="">
	<br>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:95%;background-color: #7DD5FF" align="center">   
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF" width=40%><font style="color: black">累计交纳金额(元)：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input class="easyui-textbox" type="text" value="${EXCOUNT_ADD}" data-options="editable:false" style="width: 180px"></input>
        	</td>
		</tr>
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">已经使用金额(元)：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input class="easyui-textbox" type="text" value="${CHARGECOUNT_ADD}" data-options="editable:false" style="width: 180px"></input>
        	</td>
		</tr>
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">已经冻结金额(元)：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input class="easyui-textbox" type="text" value="${FREEZEMONEY}" data-options="editable:false" style="width: 180px"></input>
        	</td>
		</tr>
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">已经退款金额(元)：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input class="easyui-textbox" type="text" value="${EXCOUNT}" data-options="editable:false" style="width: 180px"></input>
        	</td>
		</tr>
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">当前可用余额(元)：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input class="easyui-textbox" type="text" value="${BALANCE}" data-options="editable:false" style="width: 180px"></input>
        	</td>
		</tr>
		
	</table>	
		<br/>
	</form>
</body>
</html>
