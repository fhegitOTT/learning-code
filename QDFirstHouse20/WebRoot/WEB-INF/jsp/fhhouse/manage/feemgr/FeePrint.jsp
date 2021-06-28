<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>费用打印</title><STYLE TYPE="text/css">
<!--

-->
</STYLE>
<link href="../new.css" rel="stylesheet" type="text/css">
<script language="javascript">
function sub(){
document.all.myDiv.style.visibility="hidden";
window.print();
document.all.myDiv.style.visibility="visible";
}
</script>
</head>

<body>

<table width="75%" border="0" align="center">
<tr><td>
<table width="25%" border="1" style="border: 1px solid #FFFFFF;" align="left">
<tr>
    <td>${fn:substring(epqVo.post,0,1) }</td>
    <td>${fn:substring(epqVo.post,1,2) }</td>
    <td>${fn:substring(epqVo.post,2,3) }</td>
    <td>${fn:substring(epqVo.post,3,4) }</td>
    <td>${fn:substring(epqVo.post,4,5) }</td>
    <td>${fn:substring(epqVo.post,5,6) }</td>
  </tr>
</table>
</td>
</tr>
</table>

<table width="75%" border="0" align="center">
  <tr> 
    <td width="31%">地址：</td>
    <td width="69%">${epqVo.contactadr }</td>
  </tr>
  <tr> 
    <td width="31%">企业名称：</td>
    <td width="69%">${epqVo.name }</td>
  </tr>
  <tr> 
    <td width="31%">法人代表：</td>
    <td width="69%">${epqVo.delegate }</td>
  </tr>
  <tr> 
    <td width="31%">&nbsp;</td>
    <td width="69%">&nbsp;</td>
  </tr>
  <tr> 
    <td width="31%">打印时间：</td>
    <td width="69%">${sysTime }</td>
  </tr>
  <tr>
  <td colspan="4" height=5>
  <hr>
  </td>
  </tr>
  <tr> 
    <td width="31%">&nbsp;</td>
    <td width="69%">&nbsp;</td>
  </tr>
  <tr> 
    <td width="31%">累计交纳金额为：</td>
    <td width="69%">${EXCOUNT_ADD }元</td>
  </tr>
  <tr> 
    <td width="31%">已经使用金额为：</td>
    <td width="69%">${CHARGECOUNT_ADD }元</td>
  </tr>
  <tr> 
    <td width="31%">已经冻结金额为：</td>
    <td width="69%">${FREEZEMONEY }元</td>
  </tr>
  <tr> 
    <td width="31%">已经退款金额为：</td>
    <td width="69%">${EXCOUNT }元</td>
  </tr>
  <tr> 
    <td width="31%">当前可用余额为：</td>
    <td width="69%">${BALANCE }元</td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>

</html>