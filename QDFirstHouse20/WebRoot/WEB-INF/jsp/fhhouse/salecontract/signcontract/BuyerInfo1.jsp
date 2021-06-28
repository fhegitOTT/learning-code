 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同买方编辑页面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<style type="text/css">
			
		</style>
		<script type="text/javascript">
		 	function doAdd(contractID){
				var url = "<%=basePath%>/outer/signcontract/operaBuyerInfo.action?contractID="+contractID;
				parent.openDialog("乙方新增",url,700,400);
			}
			function doModify(contractID,serial){
				var url = "<%=basePath%>/outer/signcontract/operaBuyerInfo.action?contractID="+contractID+"&serial="+serial;
				parent.openDialog("乙方修改",url,700,400);
			}
			function doDelete(contractID,serial,buyerName){
				if(serial==1){
					alert('请勿删除第一顺序的乙方！请修改！');
					return false;
				}
				if(confirm('确定删除'+buyerName+'吗?')){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontract/deleteBuyer.action',  
						data : 'contractID='+contractID+'&serial='+serial,
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doUpdate('乙方编辑');
							}else{
								alert(data[0].msg);
							}
						},
						error : function(){
							alert("删除乙方出错！");
						}
					});
				}
			}
		</script>
	</head>
	<body >
		<form id="buyerinfo" >
			<input type="button" name="buyer" value="新增乙方"  onclick="doAdd('<%=request.getParameter("contractID") %>');"/>
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}%>
		</form>
		<div id="openDL"></div>
	</body>
</html>