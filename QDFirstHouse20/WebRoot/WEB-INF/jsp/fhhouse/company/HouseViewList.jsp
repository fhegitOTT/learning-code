<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	
	String contextRoot = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>房屋查询</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<STYLE type=text/css>

</STYLE>
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/new.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/common.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/default.css" type="text/css">

</HEAD>

<body bgcolor="5E9B5E">
<table width="100%">
<tr>
</tr>
</table>
   <table border="0" align="center" cellpadding="0" class=css >
		<div align="center">
            <p>
			<FONT color=#003300>&nbsp;<strong font style="font-size: 20;">查看该预售许可证所对应的房屋</strong></FONT>
			</p>
        </div>
		<tr> 
		  <td height=18 bgcolor="#FFFFFF" width="18">&nbsp;</td>
		  <td height=18 valign="bottom" width="200" font style="font-size: 14;">未纳入本开盘单元销售的房屋</td>
		  <td height=18 bgcolor="#00FF99" width="18">&nbsp;</td>
		  <td height=18 valign="bottom" width="200" font style="font-size: 14;">已纳入本开盘单元销售的房屋</td>
		</tr>
	</table>
	 <div align=center>
     <table>
     	<c:if test="${list!=null}">
     		<c:forEach items="${list}" var="a">
	 	<tr>
	 		<c:if test="${a.status==4}">
			 	<td bgcolor="#00FF99" height=30 width="200" align=center><font style="font-size: 14;">${a.room_Number }</font></td>
	 		</c:if>
	 		<c:if test="${a.status==9}">
			 	<td bgcolor="#FFFFFF" height=30 width="200" align=center ><font style="font-size: 14;">${a.room_Number }</font></td>
	 		</c:if>
	 	</tr>
     		</c:forEach>
     	</c:if> 
      </table>
	  </div>
  <P align=center> 
    <INPUT class=bottom style="BACKGROUND-COLOR: #5e9b5e" onclick="window.close();" type=button value=确定 name=save>
    <INPUT class=bottom style="BACKGROUND-COLOR: #5e9b5e" onclick="window.close();" type=button value=关闭 name=cancel>
  </P>
</BODY>
</HTML>
