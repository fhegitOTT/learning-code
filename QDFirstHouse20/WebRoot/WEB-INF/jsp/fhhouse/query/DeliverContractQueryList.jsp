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
		
	 	function doClean(){
	 		$("#frmInfo").form('clear');
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
		function doSearchContract(contractID){
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID);
	 	}
	 	function doSearchDeliver(deliverID){
			window.open("<%=path%>/inner/ContractPdf/doSearchDeliverContract.action?deliverID="+deliverID);
	 	}
	 	function viewEarnestC(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/ContractPdf/viewEarnestContract.action?contractID="+val);
	 	}
	 	function registerPrint(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/contractquery/registerPrint.action?contractID="+val);
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
		<div id="p" class="easyui-panel" title="交接书查询列表"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
				
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="contractID" value="${contractID }" />
					<input type="hidden" name="buyer" value="${buyer }" />
					<input type="hidden" name="startDate" value="${startDate }" />
					<input type="hidden" name="overDate" value="${overDate }" />
					<input type="hidden" name="seller" value="${seller }" />
					<input type="hidden" name="status" value="${status }" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
					}%>
					</form>
				<div align="center">
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:120px" onclick="viewEarnestC()">显示认购协议</a>&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="registerPrint()">登记表打印</a>&nbsp;&nbsp;
				</div>
		</div>
</body>
</html>
