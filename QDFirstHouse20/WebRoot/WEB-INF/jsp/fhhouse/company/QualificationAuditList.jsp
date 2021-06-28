<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质审核列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">	
		function doSearch(id,auditid){
			var status = "<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status"))%>";
			if(auditid==0){
				parent.window.location="<%=path%>/inner/companymanage/gotoStartUnitAudit.action?id="+id+"&status="+status;
			}
			if(auditid==1){
				parent.window.location="<%=path%>/inner/companymanage/gotoSignerAudit.action?id="+id+"&status="+status;
			}
			if(auditid==2){
				parent.window.location="<%=path%>/inner/companymanage/gotoProjectAudit.action?id="+id+"&status="+status;
			}
			if(auditid==4){
				parent.window.location="<%=path%>/inner/companymanage/gotoCompanyAudit.action?id="+id+"&status="+status;
			}
		}
		
		function doQuery(){
			var val=$("input[name='status']").val();
			if(val=='audit'){
				document.form.action="<%=path%>/inner/companymanage/gotoCompanyAuditQuery";	
			}if(val=='publish'){
				document.form.action="<%=path%>/inner/companymanage/gotoCompanyPublishQuery";
			}
			document.form.submit();
		}
		
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px;">
		<div id="p" class="easyui-panel" title="<c:if test="${status=='audit'}">资质审核列表</c:if><c:if test="${status=='publish'}">资质发布列表</c:if>" style="width: 95%; height: 95%; background: #fafafa;"
			data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="form" id="form" method="post" action="">
				<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("project")) %>"/>
				<input type="hidden" name="companyName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("company")) %>"/>
				<input type="hidden" name="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status")) %>"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if (htmlview != null && !"".equals(htmlview)&& !htmlview.equals("null")) {
						out.println(htmlview);
					}
				%>
			</form>
		</div>
	</body>
</html>
