 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
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
		function doDocumentEdit(){
	 		var val = findSelectID();
	 		//alert(val);
			if(val==''){
				alert("请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/document/documentEdit.action?cmd=edit&document_Id="+val;
			
		}
		
		function doCompanyEdit2(DOCUMENTID){
			parent.window.location="<%=path%>/inner/document/documentEdit.action?cmd=show&document_Id="+DOCUMENTID;
		}
		
		function doDocumentAdd(){
			parent.window.location="<%=path%>/inner/document/documentCreate.action";
		}
		
		function gotoSigner(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/companymanage/gotoSignerEditList.action?compId="+val;
			
		}
		
		function gotoProject(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/companymanage/gotoProjectEditList.action?compId="+val;
			
		}
		
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selDocumentID");
			var sel = 0;
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked){
					sel+=1;
					//alert(pays[i].value);
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
		<div align="right">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="doDocumentAdd()">新建</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doDocumentEdit()">修改</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="收件一览 "
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
				
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="companyName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("companyName")) %>" />
					<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectName")) %>" />
					<input type="hidden" name="position" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("position")) %>" />
					<input type="hidden" name="documentId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("documentId")) %>" />
					<input type="hidden" name="startDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startDate")) %>" />
					<input type="hidden" name="overDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("overDate")) %>" />
					<input type="hidden" name="userId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("userId")) %>" />
					<input type="hidden" name="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status")) %>" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
					}%>
					</form>
		</div>
</body>
</html>
