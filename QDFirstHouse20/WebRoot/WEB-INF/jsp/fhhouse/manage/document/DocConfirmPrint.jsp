<%@ page contentType = "text/html; charset=GBK" %>
<%@page import="java.util.List"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String basePath = request.getContextPath();
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
 <link rel="stylesheet" href="<%=basePath%>/zh/css/new.css"  type="text/css">
<title></title>
</head>
<body bgcolor="#ffffff"  align="center">
<div align="center">
<b><font size="5">入网认证项目核对运转表</font>
  </b>
  <br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收件编号：<strong>${vo.document_Id}</strong>
  <table border="1" cellspacing="0" cellpadding="2"   width="633" height="856" style="border-collapse:collapse;" bordercolor="#000000" >
    <tr> 
      <td  height="30" align="center" width="99"> 企业名称 </td>
      <td height="30" width="520">&nbsp;${vo.company_Name}</td>
    </tr>
    <tr> 
      <td  height="30" align="center" width="99"> 项目名称 </td>
      <td height="30" width="520">&nbsp;${vo.project_Name}</td>
    </tr>
    <tr> 
      <td  height="30" align="center" width="99" > 项目地址</td>
      <td height="30" width="520" style="WORD-BREAK:BREAK-ALL">&nbsp;${vo.apply_Proj_Addr}</td>
    </tr>
	<tr> 
      <td width="99" height="109" align="center"> 送<br>
        <br> 
         核<br><br> 
         意<br><br>
        见</td>
      <td colspan="2" valign="bottom" height="109" align="right" width="520"> 
        <p>&nbsp;</p>
        <p>&nbsp;</p>
         
         
         
             审核人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日 </td>
    </tr>
    <tr> 
      <td width="99" height="72" align="center"> 
        <p>送</p> 
        <p>核</p>       
        <p>内</p> 
        <p>容</p>    </td>
      <td colspan="2" valign="top" height="72" width="520"> 
        <p>&nbsp;</p>
          <p>□1、核对预售许可证信息和房地产权证（土地）登记信息在楼盘表中是否<br>&nbsp;&nbsp;&nbsp;&nbsp;已标清；抵押、行政限制、租赁信息是否已标清。</p>
          <p>□2、核对开发商提供的已登记与未登记申报表；未登记申报表是否在预售<br>&nbsp;&nbsp;&nbsp;&nbsp;许可证和房地产权证所表示的范围内。</p>
          <p>□3、核对申报的预、销售楼盘测绘数据是否准确，及其在测绘数据库中是<br>&nbsp;&nbsp;&nbsp;&nbsp;否可以打印楼层平面图。</p>
          <p>□4、 </p>
        </td>
    </tr>
    <tr> 
      <td width="99" height="128" align="center">楼<br>
         盘<br>
         核<br>
         对<br>
         意<br>
         见<br></td>
      <td colspan="2" valign="bottom" height="128" width="520" align="right">经办人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; 日</td>
    </tr>
    <tr> 
      <td width="99" height="119" align="center"> 
         产<br>
         权<br>
         核<br>
         对<br>
         意<br>见<br></td>
      <td colspan="2" valign="bottom" height="128" width="520" align="right">经办人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; 日</td>
    </tr>
    
  </table>
</div>
</body>
</html>
