 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同主模板列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		
	 	function viewTemp(templateID,type){
			if(templateID!=''&&templateID!=null){
				var url = "<%=path%>/inner/contractquery/viewTemplate.action?templateID="+templateID+"&type="+type;
				parent.openDialog("主模板浏览",url,600,500);
			}else{
				alert("选择的模板无法查看！");
			}
	 	}
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("contractID");
			var sel = 0;
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked){
					sel+=1;
					selID = tempIDS[i].value;
				}
			}
			if(sel!=1){
				return "";
			}else{
				return selID;
			}
		}
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
	<div id="p" class="easyui-panel" title="合同主模板列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		<form name="tableForm" id="tableForm" method="post"  action="">
			<input type="hidden" name="comp_ID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("comp_ID"))%>" />
			<input type="hidden" name="legalManCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("legalManCode"))%>" />
			<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("projectName"))%>" />
			<input type="hidden" name="compName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("compName"))%>" />
			<input type="hidden" name="startDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("startDate"))%>" />
			<input type="hidden" name="endDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("endDate"))%>" />
			<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("startID"))%>" />
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}%>
		</form>
	</div>
</body>
</html>
