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
			<input type="hidden" name="id" value="${nltVo.id }"/>
			<br/>
			<br/>
				<table border="2" align="center" cellspacing="0" width="95%">
					<tr style="text-align:center;height:35px">
						<td colspan="2" style="font-size:16px">非登录时间设置</td>
					</tr>
					<tr>
						<td style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">标题:</td>
						<td><input class="easyui-textbox" type="text" name="title" id="title" data-options="required:true" style="width:60%"  value="${nltVo.title}"/></td>
					</tr>
					<tr>
						<td style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">开始时间:</td>
						<td>
							<div id = "start"  style="width:165px; height: 20px;display:none;float:left;">
								<input class="easyui-datebox" name="startDate" id="startDate" data-options="editable:false" style="width:153px;" value="${startDate }"/>
								- 
							</div>
							
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
					<tr>
						<td style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">结束时间:</td>
						<td>
							<div id="end" style="width: 165px; height: 20px;display:none;float:left;">
								<input class="easyui-datebox" name="endDate" id="endDate" data-options="editable:false" style="width:153px;"   value="${endDate }"/>
								- 
							</div>
							
							<input class="easyui-combobox" name="endHour" id="endHour" style="width:45px"  value="${endHour }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_004',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
					： 
					<input class="easyui-combobox" name="endMinute" id="endMinute" style="width:45px" value="${endMinute }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
					：
					<input class="easyui-combobox" name="endSecond" id="endSecond" style="width:45px" value="${endSecond }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_005',valueField:'name',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
						</td>
					</tr>
					<tr>
						<td  style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">缓冲时间:</td>
						<td style="font-size:15px"><input class="easyui-numberbox" type="text" name="cacheTime" id="cacheTime" data-options="required:true" value="${nltVo.cacheTime }"/>分钟 （即允许该时间范围内可继续操作，但已不能再登录）</td>
					</tr>
					<tr>
						<td style="width:15%;text-align:right;font-size:15px;background-color:#5BADFF;color: white">类型:</td>
						<td>
						<c:choose>  
   							<c:when test="${empty nltVo.id }">
								<input class="easyui-combobox" name="typename" id="typename" value="0"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_401',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox,
					onSelect: function(rec){
					var rec=$('#typename').combobox('getValue'); 
					doReset(); 
					if(rec==0){
						$('#start' ).css('display','none');$('#end' ).css('display','none');$('#typename').combobox('setValue','0');
					}else if(rec==1){
						$('#start' ).css('display','block');$('#end' ).css('display','block');$('#typename').combobox('setValue','1');
						$('#startDate').datebox({required:true});$('#endDate').datebox({required:true});}}" />
   							</c:when>  
   							<c:otherwise>
								<input class="easyui-combobox" name="typename" id="typename" value="${nltVo.type }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_401',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox," />
   							</c:otherwise>  
						</c:choose>  
						</td>
					</tr>
					<tr><td colspan="2" style="font-size:16px">注意：若每天的时间不一样时，请根据日期分别增加记录，以避免日期交叉的设置。</td></tr>
				</table>
			</form>
			<br/>
			<br/>
			<div style="width:100%;text-align:center;">
				<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
				&nbsp;&nbsp;
				<c:if test="${empty nltVo.id }">
				<a href="javascript:doReset()" id="resetBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
				</c:if>
				&nbsp;&nbsp;
				<a href="javascript:doClose()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
			</div>
		</div>
	</body>
</html>
