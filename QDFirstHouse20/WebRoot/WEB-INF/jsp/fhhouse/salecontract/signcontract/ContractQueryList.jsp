 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>已签合同列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function contractPrint(){
			var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+val);
		}
	
		function doContractEdit(contractID,type){
			parent.window.location="<%=path%>/outer/signContractmanage/createContract.action?contractID="+contractID+"&type="+type;
		}
		$(function() {
			$('body').append('<div id="myWindow" class="easyui-dialog" closed="true"></div>');
		});

		function registerPrint(title, href, width, height, modal, minimizable,
							   maximizable){
			var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			var title = '请选择'
			/*window.open("<%=path%>/inner/contractquery/registerPrint.action?contractID="+val);*/
			var href = "<%=path %>/inner/contractquery/selectApplicationPrint.action?contractID="+val;
			$('#myWindow').window(
					{
						title : title,
						width : width === undefined ? 500 : width,
						height : height === undefined ? 300 : height,
						content : '<iframe scrolling="yes" frameborder="0"  src="'
								+ href
								+ '" style="width:100%;height:98%;"></iframe>',
						modal : modal === undefined ? true : modal,
						minimizable : minimizable === undefined ? false
								: minimizable,
						maximizable : maximizable === undefined ? true
								: maximizable,
						shadow : false,
						cache : false,
						closed : false,
						collapsible : false,
						resizable : true,
						loadingMessage : '正在加载数据，请稍等片刻......'

					});
		}
	 	
	 	//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selContractID");
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
			alert(selID);
		}
		
		function viewEarnest(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/ContractPdf/viewEarnestContract.action?contractID="+val);
	 	}

	 	function payPrint(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/outer/signcontract/checkPayPrint.action?contractID="+val,
					data : {},
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							window.open("<%=path%>/outer/signcontract/payPrint.action?contractID="+val);
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("操作失败！");
					}
				});

	 	}
	 	
	 	//电子签字合同打印预览
	 	function doPreviewPdf(){
	 		var contractID = findSelectID();
				if(contractID > 0 ){
		 			$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=path%>/inner/ContractPdf/checkSignPdf.action',  
						data : {"contractID":contractID},
						dataType : "json",
						success : function(data){
							if(data[0].result=='1'){//有签字合同
								window.open("<%=path%>/inner/ContractPdf/doPreviewSignPdf.action?contractID="+contractID+"&flag=1");
							}else{//没有签字合同直接提示
								alert(data[0].msg);
								return;
							}
						},
						error : function(){
							alert("预览PDF签字合同出现异常！");
						}
					});
		 		}
		 	}
		
		parent.enabledButton();
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div id="p" class="easyui-panel" title="已签合同列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<input type="hidden" name="contractID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("contractID")) %>" />
				<input type="hidden" name="type" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("type")) %>" />
				<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectName")) %>" />
				<input type="hidden" name="buyer" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("buyer")) %>" />
				<input type="hidden" name="startDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startDate")) %>" />
				<input type="hidden" name="overDate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("overDate")) %>" />
				<input type="hidden" name="startCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startCode")) %>" />
				<input type="hidden" name="district" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("district")) %>" />
				<input type="hidden" name="road" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("road")) %>" />
				<input type="hidden" name="alley" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("alley")) %>" />
				<input type="hidden" name="buildingNumber" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("buildingNumber")) %>" />
				<input type="hidden" name="cell" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cell")) %>" />
				<input type="hidden" name="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status")) %>" />
			
				<%  String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}%>
			</form>
			<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:120px" onclick="viewEarnest()">显示认购协议</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:100px" onclick="registerPrint()">登记表打印</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:120px" onclick="contractPrint()">合同打印预览</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:150px" onclick="doPreviewPdf()">电子合同打印预览</a>&nbsp;&nbsp;
		</div>
</body>
</html>
