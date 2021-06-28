 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>项目编辑页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			tr{
				background-color:white;
			}		
		</style>
</head>
<body id="body-layout">
	<form id="frmInfo" name="frmInfo" method="post" >
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="compId" value="${compId}"/>
	<input type="hidden" name="status" value="${projectVO.status}"/>
	<input type="hidden" name="project_ID" value="${projectVO.project_ID}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_right input_required" bgcolor="#5BADFF" width="40%"><font style="color: #003300">项目地址：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="projectAdr" id="projectAdr"  data-options="required:true,validType:'length[0,100]'"  value="${projectVO.projectAdr}" style="width: 250px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor=#5BADFF><font style="color: #003300">项目名称：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="projectName" id="projectName"  data-options="required:true,validType:'length[0,100]'"  value="${projectVO.projectName}" style="width: 250px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: #003300">所在区市：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-combobox" name="districtID" id="districtID" value="${projectVO.districtID }"
					data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: #003300">土地使用权出让合同编号：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="lotNum" id="lotNum"  data-options="required:true,validType:'length[0,100]'"  value="${projectVO.lotNum}" style="width: 200px"></input>
			</td>
		</tr>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doProjectSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
	</div>
	<br/>
	<div id="openDL"></div>
	</form>
</body>
</html>
