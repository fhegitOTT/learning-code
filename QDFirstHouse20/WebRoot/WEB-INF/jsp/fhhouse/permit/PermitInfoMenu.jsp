<%@page import="com.netcom.nkestate.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
 	String path = request.getContextPath();
try{
 %>
<!-- 
  - Author(s): gcf
  - Date: 2019-01-15 17:05:29
  - Description:
-->
<head>
<title>许可证打印选择页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script type="text/javascript" src="<%=path%>/js/application.js"></script>	
</head>
<body leftmargin="0" topmargin=0 bgcolor="EFF5FE">
	<table cellspacing=0 cellpadding=0 width=100% height=100%>
		<tr valign="middle">
			<td valign="middle">
				<table border=0 bgcolor="navy" width=100% cellspacing=1 cellpadding=1>
				<tr><td colspan=2 bgcolor="505499">
					<table cellspacing=0 cellpadding=0 width=100%>
						<tr height=20 valign="middle">
							<td valign="middle" align=center  width=100%>
								<input class=button type="button" id="btnMain" name="btnMain" onclick="printPermit(1)" value="打印正本" >
								<input class=button type="button" id="btnSecond" name="btnSecond" onclick="printPermit(2)" value="打印副本" >
								<input class=button type="button" id="btnThird" name="btnThird" onclick="printPermit(3)" value="打印存根" >
							</td>
						</tr>
					</table>
				</td></tr>
				</table>
				<table border=0 width=100%>
				<tr height=10><td></td></tr>
				<tr>
				<td align=center>
				<input class=button type="button" id="btnCancel" name="btnCancel" onclick="Quit()" value="关闭窗口" ></td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function printPermit(flag){
		var TargetUrl = "<%=path%>/inner/permitmanage/gotoPermitInfoPrint.action?cmd=${cmd}&transactionID=${transactionID}&districtID=${districtID}&permitID=${permitID}&printFlag="+flag;
		window.open(TargetUrl,"","height=600px,width=1000px,toolbar=no,menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");	
	}
	
	function Quit(){
	  if (typeof(window.parent)=="object") {
	    window.parent.close();
	  } else {
	    window.close();
	  }
	}
</script>
<%}catch(Exception e){
	out.print(e.getMessage());
} %>
</html>