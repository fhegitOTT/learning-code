<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同查询列表（甲方）</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">
		
	 	function doSearchSigner(id){
	 		var url = "<%=path%>/inner/contractquery/gotoSignerInfo.action?signerID="+id;
	 		parent.openDialog("签约人信息",url,400,400);
	 	}
	 	
	 	function doSearchContract(contractID,type){
	 		var url = "<%=path%>/inner/contractquery/basicInfoContract.action?contractID="+contractID+"&type="+type;
	 		parent.openDialog("网签合同基本信息",url,700,400);
	 	}
	 	
	 	function doSearchText(contractID,type){
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID+"&type="+type);
	 	}
	 	
	 	function viewEarnest(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/ContractPdf/viewEarnestContract.action?contractID="+val);
	 	}
	 	
	 	
		function registerPrint(){
	 		var val = findSelectID();
	 		//alert(val);
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.open("<%=path%>/inner/contractquery/registerPrint.action?contractID="+val);
			
			//parent.window.location="<%=path%>/inner/contractquery/registerPrint.action?contractID="+val;
			
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
		}
		</script>
	</head>
	
	<body style="margin-right: 1px; margin-left: 1px">

		<div id="p" class="easyui-panel" title="合同查询列表（甲方）" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post" action="">
				<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd"))%>"/>
				
				<input type="hidden" name="contractID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("contractID"))%>"/>
				
				<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startID"))%>"/>
				<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectName"))%>"/>
				<input type="hidden" name="signDate1" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("signDate1"))%>"/>
				<input type="hidden" name="signDate2" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("signDate2"))%>"/>
				<input type="hidden" name="type1" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("type1"))%>"/>
				<input type="hidden" name="status1" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status1"))%>"/>
				<input type="hidden" name="district" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("district"))%>"/>
				<input type="hidden" name="road" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("road"))%>"/>
				<input type="hidden" name="laneName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("laneName"))%>"/>
				<input type="hidden" name="subLane" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("subLane"))%>"/>
				<input type="hidden" name="buildingNumber" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("buildingNumber"))%>"/>
				<input type="hidden" name="confirmDate1" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("confirmDate1"))%>"/>
				<input type="hidden" name="confirmDate2" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("confirmDate2"))%>"/>
				
				<input type="hidden" name="sellerName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerName"))%>"/>
				<input type="hidden" name="compCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compCode"))%>"/>
				<input type="hidden" name="sellerAddress" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerAddress"))%>"/>
				<input type="hidden" name="sellerCertcode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerCertcode"))%>"/>
				<input type="hidden" name="sellerDlgCall" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerDlgCall"))%>"/>
				<input type="hidden" name="sellerDelegate" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerDelegate"))%>"/>
				<input type="hidden" name="sellerProxy" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("sellerProxy"))%>"/>
				<input type="hidden" name="signDate3" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("signDate3"))%>"/>
				<input type="hidden" name="signDate4" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("signDate4"))%>"/>
				<input type="hidden" name="type2" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("type2"))%>"/>
				<input type="hidden" name="confirmDate3" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("confirmDate3"))%>"/>
				<input type="hidden" name="confirmDate4" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("confirmDate4"))%>"/>
				<input type="hidden" name="status2" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status2"))%>"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						out.println(htmlview);
					}
				%>
			</form>
			<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:120px" onclick="viewEarnest()">显示认购协议</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="registerPrint()">登记表打印</a>&nbsp;&nbsp;
			</div>
		</div>
			
	</body>
</html>
