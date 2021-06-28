 <%@ page contentType = "text/html; charset=UTF-8" %>
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
			document.frmInfo1.submit();
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'合同撤销发布',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" action="<%=basePath%>/inner/contractmanage/cancelIssueList.action" method="post">
							<table width="100%">
							<tr>
								<td>
									&nbsp;&nbsp;合同编号：<input class="easyui-numberbox" type="text" name="contractId" id="contractId"></input>
									&nbsp;&nbsp;甲方：<input class="easyui-textbox" type="text" name="sellerName" id="sellerName"></input>
									&nbsp;&nbsp;状态：<select name="status" id="status" class="easyui-combobox">
												<option value="">全部</option>
												<option value="1">复核</option>
												<option value="2">信息已发布</option>
												<option value="3">审核不通过</option>
												<option value="4">审批</option>
												<option value="5">审批退回</option>
												<option value="6" selected="selected">审批通过</option>
												<option value="7">发布失败</option>
										 </select>
									&nbsp;&nbsp;乙方：<input class="easyui-textbox" type="text" name="buyerName" id="buyerName"></input>
								</td>
					    	</tr>
					    	<tr>
					    		<td>
					    		&nbsp;&nbsp;撤销原因：<select name="reason" id="reason" class="easyui-combobox">
													<option value="">全部</option>
													<option value="1">资金问题</option>
													<option value="2">输入错误</option>
													<option value="3">换房退房</option>
													<option value="4">增减权利人</option>
													<option value="5">楼盘表调整</option>
													<option value="6">其他</option>
												 </select>
							&nbsp;&nbsp;日期范围：<input class="easyui-datebox" type="text" name="confirmDate1" id="confirmDate1"  data-options="required:true,validType:'length[0,10]',editable:false"  value=""  style="width:150px"/>&nbsp;
							&nbsp;&nbsp;至&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="confirmDate2" id="confirmDate2"  data-options="required:true,validType:'length[0,10]',editable:false"  value=""  style="width:150px"/>&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
					    		</td>
					    	</tr> 
						    </table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src="<%=basePath%>/inner/contractmanage/cancelIssueList.action?status=6"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>
