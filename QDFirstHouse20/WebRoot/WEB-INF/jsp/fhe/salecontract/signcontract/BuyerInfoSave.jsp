 <%@ page contentType = "text/html; charset=UTF-8" %>
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
	function doBack(){
		//var contractID = $('#contractID').val();
		//parent.go1(contractID);
		$.ajax({
	   		type:"post",
	   		url:"<%=basePath%>/outer/signcontract/editBuyer1.action",
	   		async:false,
	   		data:{contractID:contractID},
	   		success:function(data){
	   			alert("data[0].htmlView ="+data[0].htmlView )
				var url = "<%=basePath%>/outer/signcontractFHE/operaBuyerInfoFHE.action?contractID="+contractID;
				parent.openDialog("乙方新增2",url,700,400);
											
						
	   		}
	   	})
		}
		</script>
		
	</head>
	<body >
		<form id="buyer" name="buyer" method="post" action="<%=basePath%>/outer/signContractmanage/saveBuyerInfo.action">
			<input type="hidden" id="contractID" name="contractID" value="<%=request.getParameter("contractID") %>"/>
			<input type="hidden" id="serial" name="serial" value="${buyer.serial }"/>
			<table  cellspacing=16 cellpadding=0 border=0 align="center">
				<tr>
					<td>
						受让方(乙方)： <input type="text" name="buyerName" size="50" style="width:300" />
					</td>
				</tr>
				
				<tr>
					<td>
						性别：<select style="width:90px" id="sex" name="buyerSex"><option value="1" selected>男</option><option value="0">女</option></select>
						<input type="hidden" id="sexID" />
					</td>
				</tr>
				<tr>
					<td>
						住所(址)： <input type="text" name="buyerAddress" style="width:185" maxlength="100" size="45" />
						邮箱： <input type="text" name="buyerPostcode" size="14" style="width:145" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td>
						联系电话： <input type="text" name="buyerCall" size="14" style="width:122" maxlength="20" />
					</td>
				</tr>
			
			</table>
			<div align="center">
				<input type="button" name="submit" onclick="doSaveBuyer();" value="保存"/>
				<input type="reset" name="reset" value="重置"/>
				<input type="button" name="doBack2" onclick="parent.go1(${contractID});" value="返回"/>
			</div>

		</form>
		<script type="text/javascript">		
		function doSaveBuyer(){
			var contractID = $('#contractID').val();
			var serial = $('#serial').val();
			if(serial==''){
				$('#serial').val('0');
			}
	    	if(contractID!=null&&contractID!=''){
	    		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/outer/signcontractFHE/saveBuyerInfoFHE.action',  
					data : $('#buyer').serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=='success'){
							alert(data[0].msg);
							parent.closeDialog();
							//editSellerFHE
							location.href="<%=basePath%>/outer/signcontractFHE/editBuyer1FHE.action?contractID="+contractID;
							/*//parent.doBack();  //关闭弹窗
							//parent.doUpdate('乙方编辑');
							 //alert("parent="+parent.HTMLElement)*/
						}else{
							alert(data[0].msg);
							parent.go1(${contractID});
						}
					},
					error : function(){
						alert("乙方保存出错！");
					}
				});
		    }
		}	
</script>
	</body>


</html>