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
		function goback(){
			window.location.href="<%=basePath%>/inner/contractmanage/cancelApply.action";
		}
		</script>	

  </head>
  
 <body bgcolor="white" align="center">

 
<form name="RegistForm" method="post" action="docinput" onsubmit="return false;">
<input type="hidden" name="CurrPageCount" value=""/>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="1" class="bottom" bgcolor="#00FF99">
  <tr> 
    <td height="100"><div align="center"> 
        <br>
   <table width="700" border="0" align="center" cellpadding="2" cellspacing="1" class="bottom">
  <tr> 
    <td bgcolor="#00FF99"> 
      <div align="center"> 
        <p><font color="#000000"><br>
       	   合同${cID }成功提交撤销申请！<br>
          <br>
      	   合同撤销申请编号为：${count }</font></p>
      </div>
    </td>
  </tr>
</table>
        <br>
      </div></td>
  </tr>
 
</table>
<p align="center"> 
  <a href="javascript:goback()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
</p>
 </form>

</body>
</html>
