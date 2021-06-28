 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售许可证新生成案件页面</title>
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
			
			tr{
				background-color:white;
			}		
		</style>
		

	<script type="text/javascript">

	 	function doBack(){
			window.location = "<%=path%>/inner/permitmanage/gotoPermitAddQueryList.action";
	 	}
	 	
	 	function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		window.location = "<%=path%>/inner/permitmanage/gotoPermitEdit.action?cmd=add&transactionID="+${transactionID};
	 	}
	 	
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="3"><font style="color: white"></font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">新生成案件号</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="transactionID" readonly="readonly"  value="${transactionID}" style="width: 500px"></input>
			</td>
		</tr>
	</table>
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交</a>&nbsp;&nbsp;
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	</form>
</body>
</html>
