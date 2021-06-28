 <%@ page contentType = "text/html; charset=UTF-8" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质录入查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
	<script type="text/javascript">
	
		function doQuery(){
	 		var a=$("input[name='contractId']").val();
	 		var cmd=$("input[name='cmd']").val();
	 		if(a=="" || a==null){
	 			alert("请输入合同编号！");
	 			return;
	 			}
			if(cmd=="apply"){
	 			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/outer/manage/searchContract.action",
					data : "contractID="+a,
					dataType : "json",
					success : function(msg){
						if(msg[0].result=="success"){
							window.location="<%=basePath%>/outer/manage/deliverApplyView.action?contractID="+a;
						}else{
							alert(msg[0].message);
						}
					},
					error : function(){
						alert("该合同不存在！");
					}
				});
				//window.location="<%=basePath%>/outer/manage/deliverApplyView.action?contractID="+a;
			}if(cmd=="cancel"){
	 			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/outer/manage/searchDeliver.action",
					data : "contractID="+a,
					dataType : "json",
					success : function(msg){
						if(msg[0].result=="success"){
							var url="<%=basePath%>/outer/manage/deliverSellerPwdConfirm.action?contractID="+a;
				            window.openDialog("合同甲方密码确认",url,400,300);
						}else{
							alert(msg[0].message);
						}
					},
					error : function(){
						alert("该合同不存在！");
					}
				});
            }
		}
		
		function doReset(){
	 		$("#form1").form('clear');
		}
		function doQuery1(){
	 		var a=$("input[name='pwd']").val();
	 		var b=$("input[name='cId']").val();
	 		var c=$("input[name='sellerPwd']").val();
	 		if(a==""||a==null){
	 			alert("请输入密码！");
	 			return;
	 			}
	 		if(a!=c){
	 			alert("输入的密码不正确！请重新输入!");
	 			$("#pwd").textbox("setValue", "");
	 			return;
	 			}
			var url="<%=basePath%>/outer/manage/deliverBuyerPwdConfirm.action?pwd="+a+"&cId="+b;
			window.openDialog("合同乙方密码确认",url,400,300);
		}
		
		function doQuery2(){
			var a =document.getElementsByName("pwd1");
			var b =document.getElementsByName("realPwd");
	 		var c=$("input[name='conId']").val();
			for (var i = 0 ; i < a.length; i++){
				for (var i = 0 ; i < b.length; i++){
					if(a[i].value==""||a[i].value==null){
						alert("请输入所有用户的密码！");
						return;
					}
					if(a[i].value!=b[i].value){
						alert("密码输入错误！请重新输入！");
						//$("#pwd1").textbox("setValue", "");
						a[i].value="";
						return;
					}
				}
			}
			var url="<%=basePath%>/outer/manage/deliverCancelOK.action?cId="+c;
			window.openDialog("交接书撤销",url,400,200);
		}
		
 		function doReset1(){
	 		$("#form2").form('clear');
		}
		
		function doReset2(){
	 		$("#form3").form('clear');
		}
		
		function confirm(){
	 		var status=$("input[name='status']").val();
	 		var deliverID=$("input[name='deliverID']").val();
			if(status=="0"){
				alert("该交接书已经是撤销状态！不能继续撤销！");
				$("#openDL").dialog('close');
				return;
				}
			alert("撤销成功！");
			window.location="<%=basePath%>/outer/manage/deliverCancelDone.action?deliverID="+deliverID+"&status="+status;
		}
		
		function cancel(){
			window.location="<%=basePath%>/outer/manage/deliverCancel.action";
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
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
	<input type="hidden" name="cmd" value="${cmd }" />
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'<c:if test="${cmd=='apply'}">交接书申请</c:if><c:if test="${cmd=='cancel'}">交接书撤销</c:if>',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
					<div data-options="region:'north',split:true,border:false" style="height:95px;padding-top:35px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" action="<%=basePath%>/outer/manage/deliverApplyView.action" method="post">
							<table width="100%">
							<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;合同编号：<input class="easyui-numberbox" type="text" name="contractId" id="contractId" data-options="required:true"></input>
								</td>
					    	</tr>
					    	<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doReset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重    置</a>
								</td>
					    	</tr>
						    </table>
						</form>
					</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>
