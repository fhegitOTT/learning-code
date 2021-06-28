 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>收件录入列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		
		function doContractView(contractID){
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID);
		}
		
		function doQueryView(deliverID){
			window.open("<%=path%>/inner/ContractPdf/doSearchDeliverContractHtml.action?deliverID="+deliverID);
		}
		
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div id="p" class="easyui-panel" title="交接书一览"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
				
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="contractID" value="${contractID }" />
					<input type="hidden" name="buyer" value="${buyer }" />
					<input type="hidden" name="startDate" value="${startDate }" />
					<input type="hidden" name="overDate" value="${overDate }" />
					<input type="hidden" name="startID" value="${startID }" />
					<input type="hidden" name="startCode" value="${startCode }" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
					}%>
					</form>
		</div>
</body>
</html>
