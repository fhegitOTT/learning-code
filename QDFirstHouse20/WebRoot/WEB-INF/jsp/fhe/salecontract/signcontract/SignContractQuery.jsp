<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同签约查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript">
		$(function(){
			$('#projectID').combobox({    
		    	url:'<%=basePath%>/outer/contracttemplatemanage/getUserProjectJson.action',    
		    	valueField:'code',    
		    	textField:'name',
		    	editable:false,
		    	onChange:function(newValue,oldValue){
		    		$('#startID').combobox('setValue','');
					$('#startID').combobox('reload','<%=basePath%>/outer/contracttemplatemanage/getUserPresellJson.action?projectID='+newValue);
		    	}
			}); 
			$('#startID').combobox({    
		  		url:'',    
		   		valueField:'code',    
		    	textField:'name',
    			editable:false,
		    	onChange:function(newValue,oldValue){
		    		$('#buildingID').combobox('setValue','');
					$('#buildingID').combobox('reload','<%=basePath%>/outer/contracttemplatemanage/getUserBuildingJson.action?startID='+newValue);
		    	}
			});
			$('#buildingID').combobox({    
		  		url:'',
		   		valueField:'code',    
		    	textField:'name',
		    	editable:false,
			});  
		});
		
		function doQuery(){
			var projectID = $('#projectID').combobox('getValue');
			var startID = $('#startID').combobox('getValue');
			var buildingID = $('#buildingID').combobox('getValue');
			if(projectID==""||projectID==null){
				alert("请选择项目！");
				return;
			}
			if(startID==""||startID==null){
				alert("请选择预售许可证/权属证明！");
				return;
			}
			if(buildingID==""||buildingID==null){
				alert("请选择楼栋！");
				return;
			}
			//frmInfo1.submit();
	 		$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=basePath%>/outer/signcontractFHE/doQueryCheckFHE.action",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						disabledButton();
						frmInfo1.submit();
						//goList();

						//alert("projectID="+projectID+" startID="+startID+" buildingID="+buildingID)
						//var url="<%=basePath%>/outer/signcontractFHE/querySignContractListFHE.action?projectID="+projectID+"&startID="+startID+"&buildingID="+buildingID;
						//$('#openDL').dialog('refresh', url);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("检查失败！");
				}
			});
		}
		
		function openDialogFHE(tempTitle,url){
			$('#openDialog').dialog('refresh', url);
		}
		
		
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#openDL").show().dialog({
				title : tempTitle,
				width : 500,
				height: 500,
				modal:true
			});
			$("#openDL").dialog('open');
			$("#openDL").dialog('center');
			//$('#openDL').dialog('refresh', url);

			var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';
            $('#openDialog').dialog({
                content: content,
                noheader: false,
                border: true,
                resizable: false,//定义对话框是否可调整尺寸。
                maximized: true,//默认最大化
                modal: true,
			});
		
		}
	 	function doBack(){
	 		$("#openDL").dialog('close');
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
	 	//查询
	 	function goList(){
	 	var url="<%=basePath%>/outer/signcontractFHE/querySignContractListFHE.action"
	 	var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';
	 	
	         $('#openDialog').dialog({
                content: content,
                noheader: false,
                border: true,
                resizable: false,//定义对话框是否可调整尺寸。
                maximized: true,//默认最大化
                modal: true,
			});
	 	}	 	
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div id="openDialog" data-options="region:'center',title:'签约查询',iconCls:'icon-ok'" style="background-color: #d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height: 90px; padding-top: 5px;" id="div1">
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/outer/signcontractFHE/querySignContractListFHE.action">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="1">
								<tr>
									<td>
										<table width="100%" border="0" align="center">
											<tr>
												<td>
													<div align="right">
														请选项目：
													</div>
												</td>
												<td>
													<div align="left">
														<input name="projectID" id="projectID" style="width: 200px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														请选预售许可证/权属证明：
													</div>
												</td>
												<td>
													<div align="left">
														<input name="startID" id="startID" style="width: 180px;"/>
													</div>
												</td>
												
												<td>
													<div align="right">
														请选楼栋：
													</div>
												</td>
												<td>
													<div align="left">
														<input name="buildingID" id="buildingID" style="width: 200px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														<a href="javascript:doQuery()" id="btQuery" name="btQuery" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
													</div>
												</td>

											</tr>
										</table>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src=""></iframe>
					</div>
					<div id="openDL"></div>
				</div>
			</div>
		</div>
	</body>
</html>