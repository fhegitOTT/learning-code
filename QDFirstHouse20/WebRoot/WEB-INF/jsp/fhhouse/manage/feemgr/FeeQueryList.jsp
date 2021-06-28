<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业费用明细清单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	</head>
	<body style="margin-right: 1px; margin-left: 1px">
		<div id="p" class="easyui-panel" title="${epqVoE.name }企业费用明细清单" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form action="">
				<input type="hidden" name="exchangeID" value="${exchangeID }"/>
				<input type="hidden" name="legalManCode" value="${legalManCode }"/>
				<input type="hidden" name="operator" value="${operator }"/>
				<input type="hidden" name="feeRange" value="${feeRange }"/>
				<input type="hidden" name="startDate" value="${startDate }"/>
				<input type="hidden" name="startDate" value="${startDate }"/>
				<input type="hidden" name="endDate" value="${endDate }"/>
				<input type="hidden" name="compID" id="compID" value="${epqVoE.comp_ID }"/>
				<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}
			%>
			</form>
		</div>
	</body>
</html>
