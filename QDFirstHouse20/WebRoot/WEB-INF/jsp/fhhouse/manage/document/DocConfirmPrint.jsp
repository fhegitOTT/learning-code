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
<b><font size="5">������֤��Ŀ�˶���ת��</font>
  </b>
  <br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ռ���ţ�<strong>${vo.document_Id}</strong>
  <table border="1" cellspacing="0" cellpadding="2"   width="633" height="856" style="border-collapse:collapse;" bordercolor="#000000" >
    <tr> 
      <td  height="30" align="center" width="99"> ��ҵ���� </td>
      <td height="30" width="520">&nbsp;${vo.company_Name}</td>
    </tr>
    <tr> 
      <td  height="30" align="center" width="99"> ��Ŀ���� </td>
      <td height="30" width="520">&nbsp;${vo.project_Name}</td>
    </tr>
    <tr> 
      <td  height="30" align="center" width="99" > ��Ŀ��ַ</td>
      <td height="30" width="520" style="WORD-BREAK:BREAK-ALL">&nbsp;${vo.apply_Proj_Addr}</td>
    </tr>
	<tr> 
      <td width="99" height="109" align="center"> ��<br>
        <br> 
         ��<br><br> 
         ��<br><br>
        ��</td>
      <td colspan="2" valign="bottom" height="109" align="right" width="520"> 
        <p>&nbsp;</p>
        <p>&nbsp;</p>
         
         
         
             ����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        ��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; �� </td>
    </tr>
    <tr> 
      <td width="99" height="72" align="center"> 
        <p>��</p> 
        <p>��</p>       
        <p>��</p> 
        <p>��</p>    </td>
      <td colspan="2" valign="top" height="72" width="520"> 
        <p>&nbsp;</p>
          <p>��1���˶�Ԥ�����֤��Ϣ�ͷ��ز�Ȩ֤�����أ��Ǽ���Ϣ��¥�̱����Ƿ�<br>&nbsp;&nbsp;&nbsp;&nbsp;�ѱ��壻��Ѻ���������ơ�������Ϣ�Ƿ��ѱ��塣</p>
          <p>��2���˶Կ������ṩ���ѵǼ���δ�Ǽ��걨��δ�Ǽ��걨���Ƿ���Ԥ��<br>&nbsp;&nbsp;&nbsp;&nbsp;���֤�ͷ��ز�Ȩ֤����ʾ�ķ�Χ�ڡ�</p>
          <p>��3���˶��걨��Ԥ������¥�̲�������Ƿ�׼ȷ�������ڲ�����ݿ�����<br>&nbsp;&nbsp;&nbsp;&nbsp;����Դ�ӡ¥��ƽ��ͼ��</p>
          <p>��4�� </p>
        </td>
    </tr>
    <tr> 
      <td width="99" height="128" align="center">¥<br>
         ��<br>
         ��<br>
         ��<br>
         ��<br>
         ��<br></td>
      <td colspan="2" valign="bottom" height="128" width="520" align="right">�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; ��</td>
    </tr>
    <tr> 
      <td width="99" height="119" align="center"> 
         ��<br>
         Ȩ<br>
         ��<br>
         ��<br>
         ��<br>��<br></td>
      <td colspan="2" valign="bottom" height="128" width="520" align="right">�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; ��</td>
    </tr>
    
  </table>
</div>
</body>
</html>
