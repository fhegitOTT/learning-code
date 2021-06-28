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
		//浏览主模板
	 	function viewTemplate(templateID,type){
			if(templateID!=''&&templateID!=null){
				parent.$('#isAdd').val('yes');
				var url = "<%=path%>/outer/contracttemplatemanage/viewTemplate.action?templateID="+templateID+"&type="+type+"&opera=view";
				parent.openDialog("主模板浏览",url,900,500);
			}else{
				alert("选择的模板无法查看！");
			}
	 	}
	 	//删除主模板
	 	function doDelete(templateID,type){
 	 		var msg = "删除操作不能回退，是否确定删除此模板？";
 	 		if (confirm(msg)==true){
 	 			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/outer/contracttemplatemanage/deleteTemplate.action",
					data : "templateID="+templateID+"&type="+type,
					dataType : "json",
					success : function(msg){
						if(msg[0].result=="success"){
							alert(msg[0].msg);
							parent.doQuery();
						}else{
							alert(msg[0].msg);
						}
					},
					error : function(){
						alert("删除出错！");
					}
				});
 	 		}else{
 	 			return false;
 	 		}
 	 	}
 	 	//修改主模板
	 	function doModify(templateID,type){
	 		if(templateID!=''&&templateID!=null){
				var url = "<%=path%>/outer/contracttemplatemanage/viewTemplate.action?templateID="+templateID+"&type="+type+"&opera=modify";
				parent.openDialog("主模板修改",url,600,500);
			}else{
				alert("选择的模板无法查看！");
			}
	 	}

	 	function doCopy(){
		 	var tempID = findSelectID();
		 	if(tempID!=""){
		 		var url = "<%=path%>/outer/contracttemplatemanage/copyTemplate.action?tempID="+tempID;
				parent.openDialog("主模板复制",url,600,500);
		 	}else{
			 	alert("请先选择！");
		 	}
	 	}

	 	//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("tempID");
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
			<input type="hidden" name="projectID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("projectID")) %>" />
			<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("startID")) %>" />
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}%>
				<br/>
			<div align="center">
		<a href="javascript:doCopy()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px">复制模板</a>
	</div>
		</form>
	</div>
</body>
</html>
