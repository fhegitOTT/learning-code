<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>开盘列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">
	
		//查看楼幢信息
		function lookBuilding(start_id){
			parent.window.location = "<%=path%>/inner/house/buildingInfo.action?startID="+start_id;
		}
		
		</script>
	</head>
	
	<body style="margin-right: 1px; margin-left: 1px">

		<div id="p" class="easyui-panel" title="开盘单元一览" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post" action="">
			
				<input type="hidden" name="projectID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectID")) %>"/>
				<input type="hidden" name="documentID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("documentID")) %>"/>
				<input type="hidden" name="startCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startCode")) %>"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						out.println(htmlview);
					}
				%>
			</form>
			<div align="center">
				<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
			</div>
		</div>
			
	</body>
</html>
