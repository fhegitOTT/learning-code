 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售许可录入列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function doPermitEdit(){
	 		var val = findSelectID();
			if(val==''){
				$.messager.alert("提示","请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/permitmanage/gotoPermitEdit.action?cmd=edit&permitID="+val;
		}
		function doPermitEditHref(permitID,transactionID){
			parent.window.location="<%=path%>/inner/permitmanage/gotoPermitEdit.action?cmd=edit&permitID="+permitID+"&transactionID="+transactionID;
		}
		
		function doPermitAdd(){
	 		//openDialog("新增案件申请","<%=path%>/inner/permitmanage/gotoPermitApply.action",650,400);
	 		parent.window.location="<%=path%>/inner/permitmanage/gotoPermitEdit.action?cmd=add";
		}
		
		function gotoPhoto(){
			var val = findSelectID();
			if(val==''){
				$.messager.alert("提示","请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/permitmanage/gotoPhoto.action?cmd=${cmd}&permitID="+val;
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
		
		
		function gotoHouseRelateList(){
			var val = findSelectID();
			if(val==''){
				$.messager.alert("提示","请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/permitmanage/gotoHouseRelateList.action?cmd=${cmd}&permitID="+val;
		}
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("permitID");
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
		
		function doPermitDel(){
			var val = findSelectID();
			if(val==''){
				$.messager.alert("提示","请先选择！");
				return;
			}
			$.messager.confirm("操作提示","确定要删除？",function(data){
				if(data){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/doPermitDelByPermitID.action",
						data : {"permitID":val},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								parent.window.location="<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
							}else{
								alert(data[0].message);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							alert("删除异常！");
						}
					});
				}
			});
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="doPermitAdd()">新增</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doPermitEdit()">编辑</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doPermitDel()">删除</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoPhoto()">收件</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:120px" onclick="gotoHouseRelateList()">楼盘关联</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="<c:if test="${logo!='edit'}">未提交审核的预售许可证列表</c:if>" style="width:100%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<%  
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}
				%>
			</form>
		</div>
</body>
</html>
