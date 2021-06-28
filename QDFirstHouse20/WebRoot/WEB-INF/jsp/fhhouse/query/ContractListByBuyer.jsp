<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同查询列表（乙方）</title>
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
	 	function doSearchContract(contractID,signerID,serial){
	 		var url = "<%=path%>/inner/contractquery/gotoContractBasicInfo.action?contractID="+contractID+"&signerID="+signerID+"&serial="+serial;
	 		//window.open(url,"_blank"," directories=no, status=no,resizable=no, copyhistory=yes");
	 		parent.openDialog("网签合同基本信息",url,650,400);
	 	}
	 	function doSearchText(contractID,type){
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID+"&type="+type);
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
			var tempIDS = document.getElementsByName("selContractID");
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
	
	<body style="margin-right: 1px; margin-left: 1px">
		<div id="p" class="easyui-panel" title="合同查询列表（乙方）" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form action="">
				<input type="hidden" name="buyerName" value="${buyerName }"/>
				<input type="hidden" name="buyerCardcode" value="${buyerCardcode }"/>
				<input type="hidden" name="buyerBirth" value="${buyerBirth }"/>
				<input type="hidden" name="buyerNationality" value="${buyerNationality }"/>
				<input type="hidden" name="buyerProxy" value="${buyerProxy }"/>
				<input type="hidden" name="buyerCardname" value="${buyerCardname }"/>
				<input type="hidden" name="buyerType" value="${buyerType }"/>
				<input type="hidden" name="buyerProvince" value="${buyerProvince }"/>
				<input type="hidden" name="buyerAddress" value="${buyerAddress }"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						out.println(htmlview);
					}
				%>
			</form>
			<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:120px" onclick="viewEarnestC()">显示认购协议</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="registerPrint()">登记表打印</a>&nbsp;&nbsp;
			</div>
		</div>
	</body>
</html>
