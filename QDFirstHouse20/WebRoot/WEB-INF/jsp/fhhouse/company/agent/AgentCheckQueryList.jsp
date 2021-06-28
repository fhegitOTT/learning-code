 <%@ page contentType = "text/html; charset=UTF-8" %>
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
		
		function doCompanyEdit2(agentID){
			parent.window.location="<%=path%>/inner/agentmanage/gotoCheckAgentEdit.action?cmd=edit&agentID="+agentID;
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
			parent.window.location="<%=path%>/inner/projectmanage/gotoProjectEditList.action?compId="+val;
			
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
	</div>
		<div id="p" class="easyui-panel" title="待审核的销售代理商列表"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="compCode" value="${compCode }" />
					<input type="hidden" name="compName" value="${compName }" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
					}%>
					</form>
		</div>
</body>
</html>
