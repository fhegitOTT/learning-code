 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>代理商录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">
		
		function doQuery(){
			document.frmInfo1.submit();
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
		
		function closeInfo(){
			$("#openDL").dialog('close'); 
		}
		
		function doSave(){
			if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/inner/startunit/saveStartTime.action",
					data : $("#frmInfo").serialize(),	//序列化表格内容转化为字符串
					cache: false,
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							$("#openDL").dialog('close');
							window.location.reload();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("开盘时间设定失败");
					}
				});
	 	}

		$(function(){
			doQuery();
		})
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'青岛市网上房地产申报资格认证',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/inner/startunit/startUnitQueryList.action">
							<table width="100%">
							<tr>
								<td>
									&nbsp;&nbsp;项目名称：<input class="easyui-textbox" type="text" name="projectName" id="projectName"></input>
									&nbsp;&nbsp;许可证编号：<input class="easyui-textbox" type="text" name="presellDeSc" id="presellDeSc"></input>
									&nbsp;&nbsp;销售状态：<select name="issalable" id="issalable" class="easyui-combobox">
											<option value="0">未开盘</option>
											<option value="1">已开盘</option>
											<option value="2">暂停销售</option>
										</select>
						  			&nbsp;&nbsp;收件编号：<input class="easyui-textbox" type="text" name="documentID" id="documentID"></input>
									&nbsp;&nbsp;开盘编号：<input class="easyui-textbox" type="text" name="startCode" id="startCode"></input>
									&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
								</td>
					    	</tr> 
						    </table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>
