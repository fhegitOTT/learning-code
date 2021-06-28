 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质录入列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function doCompanyEdit(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/companymanage/gotoCompanyEdit.action?cmd=edit&compId="+val;
			
		}
		
		function doCompanyEdit2(compId){
			parent.window.location="<%=path%>/inner/companymanage/gotoCompanyEdit.action?logo=${logo}&cmd=edit&compId="+compId;
		}
		
		function doCompanyAdd(){
			parent.window.location="<%=path%>/inner/companymanage/gotoCompanyEdit.action?cmd=add";
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
			parent.window.location="<%=path%>/inner/projectmanage/gotoProjectEditList.action?logo=${logo}&compId="+val;
			
		}
		
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selCompID");
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
		<c:if test="${logo!='edit'}">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="doCompanyAdd()">新增企业</a>&nbsp;&nbsp;
		</c:if>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doCompanyEdit()">企业信息</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoProject()">项目信息</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:120px" onclick="gotoSigner()">签约人信息</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="<c:if test="${logo!='edit'}">未提交审核的企业列表</c:if>" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<input type="hidden" name="compCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compCode"))%>"/>
				<input type="hidden" name="compName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compName"))%>"/>
				<input type="hidden" name="documentId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("documentId"))%>"/>
				<%  String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}%>
			</form>
		</div>
</body>
</html>
