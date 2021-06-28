 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约用户一览表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function doSignerEdit(signerId){
			window.location="<%=path%>/inner/companymanage/gotoSignerEdit.action?cmd=edit&signerId="+signerId;
			
		}
		
		function doSignerAdd(){
			window.location="<%=path%>/inner/companymanage/gotoSignerEdit.action?cmd=add&compId="+tableForm.compId.value;
			
		}
		
		function doBack(){
			window.location = "<%=path%>/inner/companymanage/doCompanyEditBack.action";
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
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="doSignerAdd()">新增</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:100px" onclick="doBack()">返回</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="企业网上签约用户一览表"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		
			<form name="tableForm" id="tableForm" method="post"  action="">
			<input type="hidden" name="compId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compId")) %>"/>
			<%						
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
				    out.println(htmlview);
			}%>
			</form>
		</div>
</body>
</html>
