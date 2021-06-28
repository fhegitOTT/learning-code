<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO"%>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%	
	String contextRoot = request.getContextPath();
	StartUnitVO svo = (StartUnitVO)session.getAttribute("svo");
	String firstDate=(String)session.getAttribute("firstDate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资质打印</title>

<STYLE TYPE="text/css">
<!--
.out{
	BORDER-RIGHT:  windowtext 1pt solid;
	BORDER-TOP: windowtext 1pt solid;
	BORDER-LEFT: windowtext 1pt solid;
	BORDER-BOTTOM: windowtext 1pt solid;
}
.comm{
	border: 1px solid #FFFFFF;
}
table {
	font-size: 12px;
}
-->
</STYLE>
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/new.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/common.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/default.css" type="text/css">
<script language="javascript">
function Print()
{
   document.all.PrintDiv1.style.visibility="hidden";
    
   window.print();
   document.all.PrintDiv1.style.visibility="visible";
   
}
</script> 
</head>
<script src="<%=contextRoot%>/zh/js/common.js"></script>
<script language="javascript">		

	function doPrint(){
		if(isBusy()) {
			return;
		}
		document.forms["PrintForm"].actionType.value = "SCR3052_goPrint";
		document.forms["PrintForm"].submit();
	}
</script>
<body>
<table width="650" border="0" cellpadding="0" cellspacing="1" align="center">
  <tr> 
    <td bgcolor="#FFFFFF" height="22" ><div align="center""> <font color="#000000" size="+2"><strong>青岛市网上房地产合同备案入网资质审核表<br>
        <br>
        </strong></font></div></td>
  </tr>
  <tr> 
    <td bgcolor="#FFFFFF" valign="top"  width="100%"> <br> <table width="100%">
        <tr> 
          <td bgcolor="#FFFFFF" width="50%" height="20" valign="top" class="css">&nbsp;</td>
          <td align="right" width="*">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td> <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#003300" class=css>
        <tr bgcolor="#FFFFFF"> 
          <td width="91"> <div align="right"> 
              <div align="right">开盘编号：</div>
            </div></td>
          <td colspan="3">${svo.start_Code }</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="91"> <div align="right">类型选择：</div></td>
          <td width="161"> <div align="left"> 				
          		<%if("0".equals(svo.getAttribute("type").toString())){ %>
					预售许可证
				<%}if("1".equals(svo.getAttribute("type").toString())){ %>
					权属证明
				<%} %> </div></td>
          <td colspan="2"> <%=svo.getAttribute("presell_desc") %></td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="91"> <div align="right">收件编号：</div></td>
          <td colspan="3"> ${svo.document_ID }</td>
        </tr>
        <%
            List<EnterpriseQualifyVO> list4 = (List<EnterpriseQualifyVO>) session.getAttribute("list4");
            for(EnterpriseQualifyVO eqvo:list4){
         %>
        <tr bgcolor="#FFFFFF"> 
          <td width="91"> <div align="right">项目甲方：</div></td>
          <td colspan="3"> 
            <p><font color="#003300"><%=eqvo.getName() %> </font></p></td>
        </tr>
		<%
			}
		 %>
        <tr bgcolor="#FFFFFF"> 
          <td width="91"> <p align="right">备注：</p></td>
          <td colspan="3" valign="top"> ${svo.firstMark }</td>
        </tr>
      </table>
      <br> 
      <table width="95%" border="0" align="center" bgcolor="#003300" cellpadding="3" cellspacing="1" class=css>
        <tr bgcolor="#FFFFFF"> 
          <td colspan="5">楼幢信息</td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td width="133">楼幢编号</td>
          <td width="266">楼幢名称</td>
          <td width="157"> <div align="center">参考单价（元）</div></td>
        </tr>
        <%	
        	List<BuildingHouseVO> list2 = (List<BuildingHouseVO>) session.getAttribute("list2");
        	for(BuildingHouseVO bhvo:list2){
         %> 
        <tr bgcolor="#FFFFFF">
          <td width="133"><%=bhvo.getBuilding_ID() %></td>
          <td width="266"><%=bhvo.getAttribute("building_name") %></td>
          <td width="157"> <div align="center"><%=bhvo.getReference_Price() %></div></td>
        </tr>
           <%
          		}
           %>
      </table></td>
  <tr>
<td>
<div align="center"> 
  <br>
      <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#003300" class=css>
        <tr bgcolor="#FFFFFF"> 
          <td width="12%" class="css"> <div align="center"><font color="#003300">操作员信息</font><br>
            </div>
            </td>
          <td width="88%" class="css"> <table width="98%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#003300">
              <tr bgcolor="#FFFFFF" class="css"> 
                <td width="20%"> <p align="center"><font color="#003300">个人帐号</font> 
                </td>
                <td width="20%"> <p align="center"><font color="#003300">姓名</font> 
                </td>
                <td width="20%"> <p align="center"><font color="#003300">证件名称</font> 
                </td>
                <td width="20%"> <p align="center"><font color="#003300">证件号码</font> 
                </td>
                <td width="20%"> <p align="center"><font color="#003300">身份证号码</font> 
                </td>
              </tr>
        <%	
        	List<SignerVO> list3 = (List<SignerVO>) session.getAttribute("list3");
        	for(SignerVO signer:list3){
         %> 
              <tr bgcolor="#FFFFFF" class="css"> 
                <td width="20%"><font color="#003300"><%=signer.getLoginName() %></font></td>
                <td width="20%"><font color="#003300"><%=signer.getName() %>　</font></td>
                <td width="20%"><font color="#003300"><%=signer.getCardName() %>　</font></td>
                <td width="20%"><font color="#003300"><%=signer.getCardCode() %>　</font></td>
                <td width="20%"><font color="#003300"><%=signer.getBrokercert() %>　</font></td>
              </tr>
          <%
          		}
           %>
            </table>
            <p><br>
            </p></td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td class="css"><div align="center">初 </div>
            <p align="center">审</p>
            <p align="center">意</p>
            <p align="center">见</p></td>
          <td width="88%" class="css">${svo.firstMark }　 
            <p> 初审人：${svo.firstCensor }</p>
            <p> 初审日期：${firstDate }</p></td>
        </tr>
        <tr bgcolor="#FFFFFF"> 
          <td class="css"> <div align="center">复 </div>
            <p align="center">审</p>
            <p align="center">意</p>
            <p align="center">见 <br>
            </p></td>
          <td class="css"> <p><font color="#003300"><font color="#333333">${finalMark }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              </font></font><br>
            </p></td>
        </tr>
      </table></td>
  </tr>
</table>
  <p>
    <script language="JavaScript" type="text/JavaScript">
    function printCensor() {
      document.all.PrintDiv.style.visibility="hidden";
   window.print();
   document.all.PrintDiv.style.visibility="visible";
}
</script>
    <!--p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="bottom" value="打印"  onClick="printCensor()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="关闭" name="B2"  onClick="window.close()"></p>
    <p>　</p>
    </div>
    <p>&nbsp;</p>
    <p>　</p>
    <p>　</p>

  <p align="justify">&nbsp;</p-->
  </p>
</body>
</html>