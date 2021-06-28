 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售许可发证列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		
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
		
		
		function doPermitAuditDetail(permitID,transactionID){
			parent.window.location="<%=path%>/inner/permitmanage/gotoPermitDetail.action?cmd=print&permitID="+permitID+"&transactionID="+transactionID;
		}
		//打印
		function doPrintPermit(){
			var str = findSelectID();
			if(str==''){
				$.messager.alert("提示","请先选择！");
				return;
			}
			//parent.window.location="<%=path%>/inner/permitmanage/gotoPermitInfoMenu.action?cmd=print&permitID="+val;
			var permitID = str.substring(str.indexOf(":")+1,str.indexOf("#"));
			var transactionID =  str.substring(str.lastIndexOf(":")+1,str.lastIndexOf("#"));
			var TargetUrl = "<%=path%>/inner/permitmanage/gotoPermitInfoMenu.action?cmd=print&permitID="+permitID;
			window.open(TargetUrl,"","height=600px,width=1000px,toolbar=no,menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");	
 	 	}
 	 	

 	 	function doSubmitPermit(){
			var str = findSelectID();
			if(str==''){
				$.messager.alert("提示","请先选择！");
				return;
			}else{
				$.messager.confirm("操作提示","您确定要提交归档？",function(data){
				if(data){
					var permitID = str.substring(str.indexOf(":")+1,str.indexOf("#"));
					var transactionID =  str.substring(str.lastIndexOf(":")+1,str.lastIndexOf("#"));
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/doSubmitPermitArc.action",
						data : {"permitID":permitID,"transactionID":transactionID},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								parent.window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
							}else{
								alert(data[0].message);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							alert("提交异常！");
						}
					});
					}
				});
			}
			

 	 	}
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right" style="display: ">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:150px" onclick="doPrintPermit()">打印</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:150px" onclick="doSubmitPermit()">提交归档</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="待发证的预售许可证列表" style="width:100%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<%  
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   //System.out.println(htmlview);
						   out.println(htmlview);
					}
				%>
			</form>
		</div>
</body>
</html>
