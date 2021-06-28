<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同查询（乙方）</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>

		<style type=text/css>
			* {color: #555;}
			body {font-size: 12px;margin: 0;font: Arial, Helvetica, "宋体", sans-serif;}
			.toplogo {width: auto;line-height: 40px;margin-left: 10px;display: inline;float: left;overflow-y: hidden;}
			/*最底部*/
			.bottom {overflow-y: hidden}
			.bottom_ban {width: auto;line-height: 40px;margin-left: 18px;display: inline;float: left;overflow-y: hidden;}
			.bottom_shi {width: auto;line-height: 40px;margin-right: 20px;display: inline;float: right;overflow-y: hidden;}
		</style>

	<script type="text/javascript">
	
	 	function doQuery(){
	 		document.frmInfo.submit();
	 	}
	 	function doClean(){
	 		$("#frmInfo").form('clear');
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
		function doSearchCompany(compID,signerID){
	 		var url = "<%=path%>/inner/contractquery/gotoCompanyInfo?compID="+compID+"&signerID="+signerID;
	 		openDialog("签约企业信息",url,500,400);
	 	}
	 	function doBack(signerID){
	 		var url = "<%=path%>/inner/contractquery/gotoSignerInfo.action?signerID="+signerID;
	 		openDialog("签约人信息",url,400,400);
	 	}
	 	function doClose(){
	 		$("#openDL").dialog("close");
	 	}
	 	
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'合同查询(乙方)',iconCls:'icon-ok'" style="background-color: #d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height: 30%; padding-top: 5px; background-color: #d9e4f2;" id="div1">
						<form name="frmInfo" id="frmInfo" target="listfrm" style="height: 100%" method="post" action="<%=path%>/inner/contractquery/gotoBuyerQueryList.action">
							<table width="100%" align="center">
								<tr>
									<td colspan="6" style="font-size: 18px; text-align: center">
										合同 查询（按乙方）
									</td>
								</tr>
								<tr>
									<td>
										<div align="right">
											乙方名称：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="buyerName" id="buyerName" />
									</td>
									<td>
										<div align="right">
											乙方证件号码：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="buyerCardcode" id="buyerCardcode"/>
									</td>
								
									<td>
										<div align="right">
											乙方出生年月日：
										</div>
									</td>
									<td>
										<input class="easyui-datebox" name="buyerBirth" id="buyerBirth" />
									</td>
								</tr>
								<tr>
									<td>
										<div align="right">
											乙方国籍：
										</div>
									</td>
									<td>
										<input class="easyui-combobox" name="buyerNationality" id="buyerNationality"
											data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_504&hasFirst=1',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
									</td>
									<td>
										<div align="right">
											证件选择：
										</div>
									</td>
									<td>
										<input class="easyui-combobox" name="buyerCardname" id="buyerCardname"
											data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_506&hasFirst=1',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
									</td>
									<td>
										<div align="right">
											委托/法定代理人：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="buyerProxy" id="buyerProxy" type="text"/>
									</td>
									
								</tr>
								<tr>
									<td>
										<div align="right">
											个人/公司：
										</div>
									</td>
									<td>
										<input class="easyui-combobox" name="buyerType" id="buyerType" type="text" value="-999"
											data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_525&hasFirst=1',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
									</td>
									<td>
										<div align="right">
											所在省市：
										</div>
									</td>
									<td>
										<input class="easyui-combobox" name="buyerProvince" id="buyerProvince" value="-999"
											data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_524&hasFirst=1',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
									</td>
									<td>
										<div align="right">
											乙方住所（址）：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="buyerAddress" id="buyerAddress"/>
									</td>
								</tr>
								<tr>
									<td colspan="5"></td>
									<td>
										<div align="left">
											<a href="javascript:doClean()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width: 80px">重 	置</a>
											<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查 	询</a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="95%" width="100%" id="listfrm" name="listfrm"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>
	</body>
</html>
