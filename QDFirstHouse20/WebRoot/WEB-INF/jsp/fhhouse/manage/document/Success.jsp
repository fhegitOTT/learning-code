<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String basePath = request.getContextPath();
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>录入成功页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript">
		function gotoPrint(){
				window.open("<%=basePath%>/inner/document/documentPrint.action?document_Id="+${sequence });
			}
			
		function goback(){
			window.location.href="<%=basePath%>/inner/document/documentCreate.action";
			}
		</script>	

  </head>
  
 <body bgcolor="white" align="center">

 
<form name="RegistForm" method="post" action="docinput" onsubmit="return false;">
<input type="hidden" name="CurrPageCount" value=""/>
<table width="60%" border="0" align="center" cellpadding="0" cellspacing="1" class="bottom" bgcolor="5E9B5E">


  <tr> 
    <td height="100" bgcolor="E6F8E3"><div align="center"> 
        <br>
        <table width="100%" height="32" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td align="center" width="98%" class=css><font color="2B7619">收件编号为：${sequence }新建完成</font></td>
          </tr>
        </table>
        <br>
      </div></td>
  </tr>
 
</table>
<p align="center"> 
  <a href="javascript:goback()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px">新建</a>
  <a href="javascript:gotoPrint()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">打印</a>
</p>
 </form>

</body>
</html>
