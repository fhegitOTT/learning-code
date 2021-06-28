 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>流转结果清单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function docManageList(){
			document.tableForm.action="<%=path%>/inner/document/docResultList.action";
			document.tableForm.submit();
		}
		
		function doCompanyEdit2(DOCUMENTID){
			parent.window.location="<%=path%>/inner/document/showDocManage.action?document_Id="+DOCUMENTID;
		}
		
		function showDocManage(dID){
			openDialog("档案流转","<%=path%>/inner/document/docManageRecord.action?dID="+dID,650,350);
		}
		
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#openDL").show().dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true
			});
			$("#openDL").dialog('open');
			$("#openDL").dialog('center');
			$('#openDL').dialog('refresh', url);
		
		}
		
		function cancel(){
			$("#openDL").dialog('close'); 
		}
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right">
		</div>
		<div id="p" class="easyui-panel" title="收件一览 "
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
	
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="companyName" value="${companyName }" />
					<input type="hidden" name="projectName" value="${projectName }" />
					<input type="hidden" name="position" value="${position }" />
					<input type="hidden" name="documentId" value="${documentId }" />
					<input type="hidden" name="startDate" value="${startDate }" />
					<input type="hidden" name="overDate" value="${overDate }" />
					<input type="hidden" name="userId" value="${userId }" />
					<input type="hidden" name="status" value="${status }" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
						    session.setAttribute("htmlview",htmlview);
					}%>
					</form>
		</div>
		<div id="openDL">
    	
    </div>
</body>
</html>
