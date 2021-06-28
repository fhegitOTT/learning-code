<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.ProjectVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.system.vo.SmObjectVO"%>
<%
	String contextRoot = request.getContextPath();
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
</head>
<script src="<%=contextRoot%>/zh/js/common.js"></script>
<script language="javascript">		
function Print()
{
   document.all.PrintDiv1.style.visibility="hidden";
    
   window.print();
   document.all.PrintDiv1.style.visibility="visible";
   
}
</script>
<body>
<table class=css width="650" border="0" cellpadding="2" cellspacing="0" align="center">
  <tr> 
    <td width="100%" height="22"  ><div align="center""> <font color="#000000" size="+2"><strong>青岛市网上房地产合同备案入网资质审核表<br>
        <br>
        </strong></font></div></td>
  </tr>
  <tr> 
    <td><table class=css width="86%" border="1" align="center" cellpadding="2" cellspacing="0" style="border-collapse:collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
    	<%
    		List<SignerVO> list1=(List<SignerVO>)session.getAttribute("list1");
    		for(SignerVO svo:list1){
    	 %>
        <tr> 
          <td width="108" height="138"> <p align="center">基本信息</p>
            </td>
          <td width="439" height="138" colspan="5" valign="top"> <br> 
            <table class=css width="97%" border="1" align="center" cellpadding="3" cellspacing="0"  style="border-collapse:collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
              <tr > 
                <td width="94" height="25"> <div align="right"><font color="#000000" >&nbsp;&nbsp;&nbsp; 
                    &nbsp;&nbsp;&nbsp;&nbsp;姓名： </font></div></td>
                <td width="324" height="25"><%=svo.getName() %> &nbsp; </td>
              </tr>
              <tr> 
                <td width="94" height="10" > <div align="right">&nbsp;&nbsp;&nbsp; 
                    证件名称： </div></td>
                <td width="324" height="10" ><%=svo.getCardName() %> &nbsp; 
                  </td>
              </tr>
              <tr> 
                <td width="94" height="10" > <div align="right">&nbsp;&nbsp;&nbsp; 
                    证件号码： </div></td>
                <td width="324" height="10" ><%=svo.getCardCode() %> &nbsp; </td>
              </tr>
              <tr> 
                <td width="94" height="12" > <div align="right">&nbsp; 
                    身份证号码： </div></td>
                <td width="324" height="12" ><%=svo.getBrokercert() %> &nbsp;</td>
              </tr>
              <tr> 
                <td height="5" > <div align="right">所属代理商： </div></td>
                <td height="5" >			
               <%
				  if(svo.getAttribute("agentname")!=null){ 
				%><%=svo.getAttribute("agentname") %>&nbsp;
				<%
					}
				 %> 
                </td>
              </tr>
              <tr> 
                <td height="6" > <div align="right">所属企业：</div></td>
                <td height="6" ><%=svo.getAttribute("compname") %> &nbsp;</td>
              </tr>

            </table>
            
          </td>
        </tr>

           <tr> 
             	<td width="108" height="100"> <p align="center">权限列表</p>
            	</td>
                <td height="25" colspan="2"> 
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="white" class="css">
					<%
					List<SmObjectVO> objectList = (List<SmObjectVO>)session.getAttribute("objectList");
					int rownum = 4;//总列数
					int num=1;//当前列数
					int rowsize = 1;//记录数
					for(SmObjectVO objectvo:objectList){
						//判断是否已经关联
						Object signer_id = objectvo.getAttribute("signer_id");
						boolean hasCheck = false;
						if(signer_id!=null && !"".equals(signer_id)){
							hasCheck = true;
						}
						//判断是否第一条
						if(num==1){
							%>
								<tr align =left   style="bgcolor:'E6F8E3';">
							<%
						}
					%>
					
					<td>
						<font color='#003300'><input type="checkbox" name="actionID" value="<%=objectvo.getObjectId() %>" <%if(hasCheck){%>checked<%} %> disabled="disabled"  /><%=objectvo.getName() %></font>
					</td>
					<%
						num++;
						rowsize++;
						//判断当前行的列数是否已经到设置数量
						if(num>rownum && rowsize<=objectList.size()){
							%>
								</tr>
							<%
							num = 1;
						}
					}
					//判断是否存在记录
					if(rowsize>1){
						//判断是否补齐行列
						if(num<=rownum){
							for(int k=num; k<=rownum; k++ ){
							%>
								<td>&nbsp;</td>
							<%
							}
						}
						%>
							</tr>
						<%
					}
					%>
				</table>				

				</td>
            </tr>
        <tr> 
          <td width="108" height="110" > <p align="center">受权项目信息</p></td>
          <td width="439" height="110" colspan="5" valign="top"> <br> <table class=css width="98%" border="1" cellpadding="2" cellspacing="0"   align="center" style="border-collapse:collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
              <tr > 
                <td width="35%"> <p align="center"><nobr>项目名称</nobr> 
                </td>
                <td width="65%" > <p align="center">预售许可证列表（开盘单元） 
                </td>
              </tr>
              <%
              		List<ProjectVO> list2=(List<ProjectVO>)session.getAttribute("list2"); 
              		for(ProjectVO pvo:list2){
               %>
                  <tr> 
                    <td width="35%" ><%=pvo.getProjectName() %>&nbsp;</td>
                    <td width="65%">
                    	<%=pvo.getAttribute("presell_desc") %>
					 </td>
                  </tr>
                <%
                	}
                 %>
            </table></td>
        </tr>
        <tr > 
          <td width="108" height="129" > <div align="center">受权预售许可证信息</div></td>
          <td width="439" height="129" colspan="5" valign="top" > <br> 
            <table class=css width="98%" border="1" cellpadding="2" cellspacing="0"   align="center" style="border-collapse:collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
              <tr > 
                <td width="35%" height="17"> <p align="center">预售许可证（开盘单元）<nobr></nobr> 
                </td>
                <td width="65%" height="17" > <p align="center">相关楼幢 
                </td>
              </tr>
               <%
              		List<StartUnitVO> list3=(List<StartUnitVO>)session.getAttribute("list3"); 
              		for(StartUnitVO suvo:list3){
               %>
                <tr bgcolor="FFFFFF"> 
                  <td width="35%"><%=suvo.getAttribute("presell_desc") %>
				  </td>
                  <td width="65%"><%=suvo.getAttribute("building_name") %>
  	                </td>
	               </tr>
	            <%
	            	}
	             %>
            </table></td>
        </tr>
        <tr > 
          <td width="108" height="138" > <p align="center">初</p>
            <p align="center">审</p>
            <p align="center">意</p>
            <p align="center">见 </td>
          <td width="439" height="138" colspan="5" >　 
            <p></p>
            <p>${firstMark } </p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审人：<%=svo.getFirstCensor() %> </p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审日期：<%=DateUtil.parseDateTime3(String.valueOf(svo.getFirstAuditDate())) %> </p>
            　</td>
        </tr>
        <tr > 
          <td width="108" height="197" > <p align="center">复</p>
            <p align="center">审</p>
            <p align="center">意</p>
            <p align="center">见 </td>
          <td width="439" height="197" colspan="5" >
            <p><font color="#333333"> ${finalMark }</font></p>
            </td>
        </tr>
         <%
        	}
         %>
      </table>
</table>

  


<div align="center">
  <script language="JavaScript" type="text/JavaScript">
    function printCensor() {
      document.all.PrintDiv.style.visibility="hidden";
   window.print();
   document.all.PrintDiv.style.visibility="visible";
}
</script>
  <!--p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="打印"  onClick="printCensor()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="关闭" name="B2"  onClick="window.close()"></p>
    <p>　</p>
    </div>
    <p>&nbsp;</p>
    <p>　</p>
    <p>　</p>

  <p align="justify">&nbsp;</p-->
</div>
</body>
</html>
