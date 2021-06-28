 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业费用查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">
		
		function doQuery(){
			//alert(11111);
			document.frmInfo1.submit();
		}
		function doTotalFee(){
			openDialog("费用总账查询","<%=basePath%>/outer/manage/gotoCompFeeTotal.action?",450,300);
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
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'费用查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" action="<%=basePath%>/outer/manage/gotoCompFeeList.action">
							<table width="100%">
							<tr height="25">
								<td>
									&nbsp;费用时间 从<input class="easyui-datebox" type="text" name="startDate" id="startDate" style="height:26px;width:100px"></input>
							    	~<input class="easyui-datebox" type="text" name="endDate" id="endDate" style="height:26px;width:100px"></input>
								    &nbsp;明细类型：
								   	<select id="feeRange" name="feeRange" class="easyui-combobox" data-options="panelHeight:'auto',formatter: formatNullForCombobox">
								   		<option value="" >全部</option>
				        				<option value="0" >交退费明细</option>
				        				<option value="1" >消费明细</option>
				        			</select>
						    		&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
									&nbsp;&nbsp;<a href="javascript:doTotalFee()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:120px">费用总账查询</a>
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
