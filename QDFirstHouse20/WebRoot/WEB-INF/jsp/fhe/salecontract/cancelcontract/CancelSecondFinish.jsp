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
		<script type="text/javascript">
		</script>	

  </head>
  
 <body bgcolor="white" align="center">

 
<form name="RegistForm" method="post" action="docinput" onsubmit="return false;">
<input type="hidden" name="CurrPageCount" value=""/>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="1" class="bottom">


  <tr> 
    <td height="100"><div align="center"> 
        <br>
        <table width="100%" height="32" border="0" align="center" cellpadding="0" cellspacing="0" >
          <tr> 
            <td align="center" width="98%" class=css ><font color="#5BADFF" size="3">该合同已被成功撤销！撤销编号为：${ID }</font></td>
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
