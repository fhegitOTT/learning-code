 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统出错</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			.td1{font-size:16px;color:red; }
		</style>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto" >
	<form id="frmInfo" name="frmInfo" action="">
	<br/>
	<table cellpadding="0" cellspacing="0px" width="95%" align="center">
		<tr height="100">
			<td align="left" class='td1'>&nbsp;</td>
		</tr>
		<tr height="30">
			<td align="center"><img src="<%=path %>/images/error/error.jpg"  alt="系统出错" /></td>
		</tr>
	</table>
	</form>
</body>
</html>
