 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>收件编号检查页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			tr{
				background-color:white;
			}		
		</style>
</head>
<body id="body-layout">
	<form id="frmInfo" name="frmInfo" method="post" >
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_right" bgcolor="#5BADFF" width="40%"><font style="color: #003300">公司名称：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="company_Name" id="company_Name"  value="${documentVO.company_Name}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor=#5BADFF><font style="color: #003300">项目名称：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="project_Name" id="project_Name"  value="${documentVO.project_Name}" style="width: 200px"></input>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
