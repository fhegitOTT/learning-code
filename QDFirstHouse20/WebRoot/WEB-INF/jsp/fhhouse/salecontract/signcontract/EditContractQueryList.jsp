 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>草签和确认合同列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function viewPrint(contractID,type){
			window.open("<%=path%>/inner/ContractPdf/doSearchText.action?contractID="+contractID+"&type="+type);
		}
	
		function doContractEdit(contractID,type,contractversion,status){
			if(type=='1' && contractversion!='111'){
				alert("该合同为老版合同，不允许编辑！");
				return;
			}
			if(type=='2' && contractversion!='211'){
				alert("该合同为老版合同，不允许编辑！");
				return;
			}
			parent.window.location="<%=path%>/outer/signcontract/operateContract.action?contractID="+contractID+"&type="+type+"&status="+status;
		}
		
		function delContract(contractID,type){
			var statu = confirm("确定要删除吗?");
			if(!statu){
			   return false;
			}
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/outer/signcontract/delContractID.action?contractID="+contractID+"&type="+type,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						parent.doQuery();
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("合同删除失败！");
				}
			});
		}
		
		
		function xgInfo(contractID,type,houseID){
			if(type==1||type==2){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=path%>/outer/signcontract/gotoXgCheck.action',  
					data : 'houseId='+houseID,
					dataType : "json",
					success : function(data){
						if(data[0].result=='success'){
							alert(data[0].msg);
							//var url="<%=path%>/outer/signcontract/gotoXgView.action?contractID="+contractID+"&houseID="+houseID;
							//parent.openDialog("住房情况证明",url,400,300);
						}else{
							var url="<%=path%>/outer/signcontract/gotoXgView.action?contractID="+contractID+"&houseID="+houseID;
							parent.openDialog("住房情况证明",url,400,300);
						}
					},
					error : function(){
						alert("检查失败！");
					}
				});
			}else{
				alert("该合同为认购协议，无需验证限购信息！");
			}
		}
		
		parent.enabledButton();
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<c:if test="${cmd=='edit'}">
		<div id="p" class="easyui-panel" title="草签合同列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		</c:if>
		<c:if test="${cmd=='waiting'}">
		<div id="p" class="easyui-panel" title="待签合同列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		</c:if>
			<form name="tableForm" id="tableForm" method="post"  action="">
				<input type="hidden" name="cmd" value="${cmd }" />
				<input type="hidden" name="contractID" value="${contractID }" />
				<input type="hidden" name="type" value="${type }" />
				<input type="hidden" name="projectName" value="${projectName }" />
				<input type="hidden" name="buyer" value="${buyer }" />
				<input type="hidden" name="startDate" value="${startDate }" />
				<input type="hidden" name="overDate" value="${overDate }" />
				<input type="hidden" name="startCode" value="${startCode }" />
				<input type="hidden" name="district" value="${district }" />
				<input type="hidden" name="road" value="${road }" />
				<input type="hidden" name="alley" value="${alley }" />
				<input type="hidden" name="buildingNumber" value="${buildingNumber }" />
				<input type="hidden" name="cell" value="${cell }" />
			
				<%  String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}%>
			</form>
		</div>
</body>
</html>
