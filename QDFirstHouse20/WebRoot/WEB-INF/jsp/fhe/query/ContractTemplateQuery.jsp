
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同主模板查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript">
		
		function doQuery(){
			var startDate = $("#startDate").datebox("getValue");
	 		var overDate = $("#endDate").datebox("getValue");
	 		if(startDate!=null && overDate!=""){
	 			 if(startDate>overDate){
	 				alert("结束时间不能在开始时间之前！");	
					return;
		 		 }
	 		}
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

			var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';
            $('#openDL').dialog({
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

	 	$(function(){
	 		doQuery();
		 })
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'合同主模板查询',iconCls:'icon-ok'" style="background-color: #d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height: 90px; padding-top: 5px;" id="div1">
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/inner/contractquery/queryContractTemplateList.action">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="1">
								<tr>
									<td>
										<table width="100%" border="0" align="center">
											<tr>
												<td>
													<div align="right">
														企业代码：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-textbox" name="comp_ID" id="comp_ID" style="width: 120px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														法人代码：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-textbox" name="legalManCode" id="legalManCode" style="width: 120px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														项目名称：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-textbox" name="projectName" style="width: 120px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														<a style="width:80px"></a>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div align="right">
														企业名称：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-textbox" name="compName" style="width: 120px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														创建日期从：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-datebox" name="startDate" id="startDate" style="width: 120px;"/>
														至 
														<input class="easyui-datebox" name="endDate" id="endDate" style="width: 120px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														开盘编号：
													</div>
												</td>
												<td>
													<div align="left">
														<input class="easyui-textbox" name="startID" style="width: 120px;" size="10" maxlength="6"/>
													</div>
												</td>
												<td>
													<div align="right">
														<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
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
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm"></iframe>
					</div>
					<div id="openDL"></div>
				</div>
			</div>
		</div>
	</body>
</html>