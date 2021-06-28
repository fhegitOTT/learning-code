<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): zhdw
  - Date: 2016-08-09 11:03:16
  - Description:
-->
<head>
<title>说明</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
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
    	a{
    		text-decoration: none;
    	}
    </style>
</head>
<body>
<table border=0 width=92% align=center cellspacing=1 cellpadding=1 bgcolor=navy>
	<td align=center colspan=2 style="color:navy;height: 30px" ><font color=white>设置说明</font></td>
  <tr bgcolor="EFF5FE">
		<td width=165 align=right>&nbsp;&nbsp;南康客户端套件下载：</td>
    <td align=center style="color:red;height: 50px"><a href="<%=path%>/help/tools/nkclientsuite40_setup_x86.zip">下载客户端套件(含套打控件、文件上传控件)</a></td>
  </tr>
  <tr bgcolor="EFF5FE">
		<td width=165 align=right>&nbsp;&nbsp;良田高拍仪驱动下载：</td>
    <td align=center style="color:red;height: 50px"><a href="<%=path%>/help/tools/良田高拍仪驱动程序.zip">下载良田高拍仪驱动 (请右键另存到本地再行安装)</a></td>
  </tr> 
</table>
</body>
</html>