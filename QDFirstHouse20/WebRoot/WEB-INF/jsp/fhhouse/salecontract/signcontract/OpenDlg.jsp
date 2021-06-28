 <%@ page contentType = "text/html; charset=UTF-8" %>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</HEAD>
<BODY>
<iframe src="" width=100% height=100%  name=detail id=detail framespacing="0" frameborder="1" scrolling=auto></iframe>
</BODY>
<SCRIPT LANGUAGE=javascript>
<!--
var curTarget=new String();
if(window.dialogArguments!=null)curTarget=window.dialogArguments;
document.all("detail").src=curTarget;
window.returnValue = "close";
-->
</SCRIPT>
</HTML>