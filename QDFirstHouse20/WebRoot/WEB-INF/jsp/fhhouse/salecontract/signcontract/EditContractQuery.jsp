 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>草签和确认合同查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">
		
		function doQuery(){
			if(!checkData()){
	 			return;
	 		}
	 		disabledButton();
			document.form1.submit();
		}
		
		function checkData(){
			var startDate = $("#startDate").datebox("getValue");
	 		var overDate = $("#overDate").datebox("getValue");
	 		if(startDate!=null && overDate!=""){
	 			 if(startDate>overDate){
	 				alert("结束时间不能在开始时间之前！");	
					return false;
		 		 }
	 		}
	 		return true;
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
		
		function doXgDelete(){
			var statu = confirm("确定要删除合同相关的限购信息吗?");
			if(!statu){
			   return false;
			}
			var cID=$("input[name='cID']").val();
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=basePath%>/outer/signcontract/delXgInfo.action?contractID="+cID,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						$("#openDL").dialog('close');
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("限购信息删除失败！");
				}
			});
		}
		
	 	function xgInfoQuery(){
			//document.frmInfo1.submit();
			var xgID=$("input[name='xgID']").val();
			var houseID=$("input[name='houseID']").val();
			var contractID=$("input[name='cID']").val();
			if(xgID==null || xgID==''){
				alert("请输入编号！");
				return;
			}
			var url="<%=basePath%>/outer/signcontract/queryXgInfo.action?xgID="+xgID+"&houseID="+houseID+"&contractID="+contractID;
			openDialog("住房情况证明",url,600,300);
		}
	
	   function doXgSubmit(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontract/gotoSubmitXgInfoCheck.action',  
				data : $("#frmInfo1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						$("#openDL").dialog('close');
						//$("#imitsaleid").val(data[0].xgID);
						document.frmInfo1.submit();
					}else{
						alert(data[0].msg);
					}
				},
				error : function(){
					alert("查询出错！");
				}
			});
		}
		//按钮有效
	 	function enabledButton(){
	 		$("#btQuery").linkbutton("enable");
			$("#btQuery").css("color","black");
	 	}
	 	//按钮无效
	 	function disabledButton(){
	        $('#btQuery').linkbutton('disable');
	        $("#btQuery").css("color", "#CCC");  
	 	}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'<c:if test="${cmd=='edit'}">草签合同</c:if><c:if test="${cmd=='waiting'}">待签合同</c:if>',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:100px;padding-top:5px;" id="div1"> 
						<form name="form1" id="form1" target="listfrm" action="<%=basePath%>/outer/signcontract/queryEditContractList.action" method="post">
							<input type="hidden" name="cmd" value="${cmd }"/>
							<table width="100%">
							<tr>
								<td>
									&nbsp;合同号：<input class="easyui-numberbox" type="text" name="contractID" id="contractID"></input>
									&nbsp;合同类型：<input class="easyui-combobox" name="type" id="type" value=""
											data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_516&hasFirst=1',valueField:'code',textField:'name',
											multiple:false,editable:false,required:false,formatter: formatNullForCombobox"  style="width:150px"/>
									&nbsp;项目名称：<input class="easyui-combobox" name="projectName" id="projectName" value=""
											data-options="url:'<%=basePath%>/outer/contracttemplatemanage/getUserProjectJson.action?hasFirst=1',valueField:'code',textField:'name',
											multiple:false,editable:false,required:false,formatter: formatNullForCombobox"  style="width:200px"/>
									&nbsp;买方：<input class="easyui-textbox" type="text" name="buyer" id="buyer"></input>
								</td>
					    	</tr>
					    	<tr>
					    		<td>
									&nbsp;签订日期：从&nbsp;<input class="easyui-datebox" type="text" name="startDate" id="startDate"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;
									&nbsp;到&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="overDate" id="overDate"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;&nbsp;	
									&nbsp;开盘编号：<input class="easyui-textbox" type="text" name="startCode" id="startCode"></input>
					    		</td>
					    	</tr>
					    	<tr>
					    		<td>
									&nbsp;坐落部位：区/市&nbsp;<input class="easyui-combobox" name="district" id="district" value="-999"
											data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_DISTRICT&hasFirst=1',valueField:'code',textField:'name',
											multiple:false,editable:false,required:false,formatter: formatNullForCombobox"  style="width:150px"/>&nbsp;&nbsp;
												<input class="easyui-textbox" type="text" name="road" id="road"  style="width:150px"/>&nbsp;路&nbsp;
												<input class="easyui-textbox" type="text" name="alley" id="alley"  style="width:150px"/>&nbsp;号&nbsp;
												<input class="easyui-textbox" type="text" name="buildingNumber" id="buildingNumber"  style="width:150px"/>&nbsp;栋&nbsp;
												<input class="easyui-textbox" type="text" name="cell" id="cell" style="width:150px" />&nbsp;单元&nbsp;
									&nbsp;&nbsp;<a href="javascript:doQuery()" id="btQuery" name="btQuery" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
					    		</td>
					    	</tr> 
						    </table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src=""></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>
