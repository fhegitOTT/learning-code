<%@page import="com.netcom.nkestate.fhhouse.permit.vo.ApplyFileVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path=request.getContextPath();
	List fileList = (List)request.getAttribute("fileList");
 %>
<head>
<title>收件收据</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body onLoad="javascript:on_load();">
	<table cellspacing=0 cellpadding=0 width=95% align=center>
		<tr height="20" valign="middle">
			<td width="50%" align="left">
				<font face='C39HrP24DhTt' size=10>*${transID}*</font>
			</td>
		</tr>
	</table>
	<h1 align="center">收件回执</h1>
	<table cellspacing=0 cellpadding=0 width=95% align=center>
		<tr>
			<td>
				<table border=0 width=100% bgcolor=white align=center>
					<tr height=40>
						<td align=left width =55%>收件编号:<font size=7>${transID}</font>
						</td>
						<td align=left width =45%>收件类型:商品房预售许可</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				 <table cellspacing=1 cellpadding=1 bgcolor=black width=100%>
				 	<tr valign=bottom bgcolor=white height=25>
						<td align=left width=15%>&nbsp;申请权利人：</td>
						<td align=left width=40%>&nbsp;${applicant}</td>
						<td align=left width=15%>联系电话：</td>
						<td width=30%></td>
					</tr>
					<tr valign=bottom bgcolor=white height=25><td align=left width=15%>&nbsp;申请义务人：</td><td align=left colspan=3>&nbsp;</td></tr>
					<tr valign=bottom bgcolor=white height=25><td align=left width=15%>&nbsp;不动产座落：</td><td  align=left width=50%>&nbsp;${location } </td><td></td><td></td></tr>
					
					</table>
					<table border=0 cellspacing=1 cellpadding=1 bgcolor=black width=100% align=center>
					<tr bgcolor=white>
						<td align=center width=35%>证件名称</td>
						<td align=center width=30%>证件号码（材料说明）</td>
						<td align=center width=15%>证件类别<br><font size=2>(原件/复印件)</font></td>
						<td align=center width=8% > 份数</td>
						<td align=center width=12%> 收缴/校验</td>
					</tr> 
					<%if(fileList != null && fileList.size()>0){
						ApplyFileVO vo = null;
						for(int i = 0;i < fileList.size();i++){
							 vo = (ApplyFileVO)fileList.get(i);%>
							<tr bgcolor=white>
								<td align=left>&nbsp;<%=vo.getFileName() == null ? "" : vo.getFileName()%></td>
								<td align=center>&nbsp;&nbsp;<%=vo.getRemark() == null ? "" : vo.getRemark()%></td>
								<td align=center >&nbsp;<%=vo.getFileType()%></td>
								<td align=center >&nbsp;<%=vo.getFileCount()%></td>
								<td align=center >&nbsp;收缴</td>
							</tr>
					<%  
						}
					} 
					%>
					<tr bgcolor=white><td align=center>合计</td><td align=center>${fileNum}</td><td align=center width=10%>&nbsp;</td><td align=center> ${fileCount} </td><td align=center>&nbsp;</td></tr>
					<tr bgcolor=white>
						<td colspan=1 align=left>收件人</td><td align=left>${acceptUser}</td>
						<td colspan=1 align=left>收件日期</td><td colspan=2 align=left width=35%>&nbsp;${nowDate}</td>
					</tr>
				 </table>
			</td>
		</tr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=90% align=center>
					<tr valign=bottom bgcolor=white height=25>
						<td align=left width=20%>&nbsp;申请权利人</td>
						<td align=left >&nbsp;${applicant }</td>
						<td align=left width=20%>&nbsp;申请义务人</td>
						<td align=left>&nbsp;</td>
					</tr>
					<tr valign=bottom bgcolor=white height=15>
						<td align=left width=20%>&nbsp;</td>
						<td align=center >(签名)</td>
						<td align=left width=20%>&nbsp;</td>
						<td align=center>(签名)</td>
					</tr>
					
					<tr height=10 bgcolor=white><td colspan=4></td></tr>
					<tr valign=bottom bgcolor=white height=25>
						<td align=right width=20%>&nbsp;</td>
						<td align=left width=30%>&nbsp;&nbsp;</td>
						<td align=right width=20%>&nbsp;服务电话：</td>
						<td align=left>${tel}</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
    	function MyPrint()
		{
			window.focus();
			window.print();
		}

		function on_load()
		{
		       MyPrint();
		}
    </script>
</body>
</html>