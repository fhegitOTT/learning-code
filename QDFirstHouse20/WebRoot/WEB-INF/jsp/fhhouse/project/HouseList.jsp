 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
<%@ page import="com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>房屋选择页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
</head>
<body id="body-layout">
	<table width="100%">
	<tr><td>
	<form id="houselistfrm" name="houselistfrm" method="post" action="" >
	<input type="hidden" name="startId" value="${startId}"/>
	<input type="hidden" name="buildingId" value="${buildingId}"/>
	<br/>
	<table  border=0 align=center cellPadding=2 cellSpacing=1 class=css>
      <tr> 
        <td bgcolor="#e2eefc">
		 <div align="center"><font color=#003300>&nbsp;<strong>请为该开盘单元添加房屋</strong><br/>
            <table border="0" align="center" cellpadding="0" class=css>
				<tr bgcolor="#e2eefc"> 
				  <td height=18 width="17" class="CONTRACT_STATUS_8">&nbsp;</td>
				  <td height=18 valign="bottom" width="60" class="zczt">已付定金</td>
				  <td height=18 width="17" class="CONTRACT_STATUS_2">&nbsp;</td>
				  <td height=18 valign="bottom" width="60" class="zczt">已签房屋</td>
				  <td height=18 width="17" class="CONTRACT_STATUS_3">&nbsp;</td>
				  <td height=18 valign="bottom" width="67" class="zczt">已登记房屋</td>
				  <td height=18 width="17" class="CONTRACT_STATUS_9">&nbsp;</td>
				  <td height=18 valign="bottom" width="90" class="zczt">未纳入网上销售</td>
				  <td height=18 width="17" class="CONTRACT_STATUS_4">&nbsp;</td>
				  <td height=18 valign="bottom" width="59" class="zczt">可售房屋</td>
				  <td height=18 width="17" valign="bottom" bgcolor="#999999">&nbsp;</td>
				   <td valign="bottom" width="123" class="zczt">已被其它开盘单元添加</td>
				</tr>
			  </table>
		  </font>
		 </div>
        </td>
      </tr>
    </table>
	<table  border=0 align=center cellPadding=2 cellSpacing=2 bgColor=#5e9b5e class=css>
		<% 
		List<List<CHFlatVO>> houseList = (List<List<CHFlatVO>>)request.getAttribute("houseList");
		int num = 0;
		for(List<CHFlatVO> tempList:houseList){
		%>
			<tr>
				<%for(CHFlatVO flatvo:tempList){
					String status = flatvo.getAttribute("status").toString();
					String statusName = flatvo.getAttribute("statusName").toString();
					String checked = flatvo.getAttribute("checked").toString();
				%>
					<td nowrap  height="20" class="CONTRACT_STATUS_<%=status %>" title="<%=statusName %>"> 
				      <div align="center"><font color="#003300">
					  <%=flatvo.getRoom_Number() %>
					  <input type="checkbox" name="HOUSESELECT_<%=num %>" <%if("1".equals(checked)){%>checked<%} %> <%if(!"4".equals(status)){%> disabled style="visibility: hidden"<%} %> ></input>
					  <input type="hidden" name="HOUSE_ID_<%=num %>" value="<%=flatvo.getHouseID() %>"/>
					  </font></div>
				    </td>
				
				<%
				num++;
				} %>
			</tr>
	
		<%} %>
	</table>
	
	<br/>
	<div align="center">
		<font size=2 color="ffffff">全选</font><input type="checkbox" name="allBox" onClick="doSelectAll(this);"></input>&nbsp;&nbsp;
		<a href="javascript:doHouseSelSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
	</div>
	<br/>
	
	</form>
	</td>
	</tr>
	</table>
</body>
</html>
