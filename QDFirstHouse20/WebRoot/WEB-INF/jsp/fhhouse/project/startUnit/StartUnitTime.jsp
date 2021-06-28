<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>非登录时间一览</title>
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
			
		</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div id="p" class="easyui-panel" title="登录设置" style="width: 100%; height: 100%; background: #fafafa;"
			data-options="iconCls:'icon-collapse',fit:true,noheader:true">
			<form name="frmInfo" id="frmInfo" method="post" action="">
			<input type="hidden" name="startID" value="${startID }"/>
			<br/>
			<br/>
				<table border="2" align="center" cellspacing="0" width="95%">
					<tr style="text-align:center;height:35px">
						<td colspan="2" style="font-size:16px">定时开盘时间设定</td>
					</tr>
					<tr>
						<td style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">开始时间:</td>
						<td>
							<input class="easyui-datebox" name="startDate" id="startDate" data-options="required:true,editable:false" value="${startDate }"/>
							- 
							<input class="easyui-combobox" name="startHour" id="startHour" style="width:45px" value="${startHour }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_004',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
					： 
					<input class="easyui-combobox" name="startMinute" id="startMinute" style="width:45px" value="${startMinute }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
					：
					<input class="easyui-combobox" name="startSecond" id="startSecond" style="width:45px" value="${startSecond }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
						</td>
					</tr>
					
				</table>
			</form>
			<br/>
			<br/>
			<div style="width:100%;text-align:center;">
				<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">设定</a>
				&nbsp;&nbsp;
				<a href="javascript:closeInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">关闭</a>
			</div>
		</div>
	</body>
</html>
