 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>收件录入</title>
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
		
		$(doQuery);
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'系统日志查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/inner/Pricemanage/gotoGuidePriceLogList">
							<table width="100%">
							<tr>
						     	<td style="width:15%;text-align:right;font-size:15px">开始时间范围:从</td>
								<td>
							      <input class="easyui-datebox" name="startDate" id="startDate" data-options="editable:false"/>
								  <input class="easyui-combobox" name="startHour" id="startHour" style="width:45px" 
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_004',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
					： 
					<input class="easyui-combobox" name="startMinute" id="startMinute" style="width:45px" 
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
					：
					<input class="easyui-combobox" name="startSecond" id="startSecond" style="width:45px"
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
								</td>
							</tr>				
							<tr>
						     	<td style="width:15%;text-align:right;font-size:15px">到</td>
								<td>
							       <input class="easyui-datebox" name="endDate" id="endDate" data-options="editable:false"/>
							      <input class="easyui-combobox" name="endHour" id="endHour" style="width:45px" 
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_004',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
					： 
					<input class="easyui-combobox" name="endMinute" id="endMinute" style="width:45px" 
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
					：
					<input class="easyui-combobox" name="endSecond" id="endSecond" style="width:45px"
					data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
							       <a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>						
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