<%@page import="com.netcom.nkestate.framework.MiniConfiger"%>
<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	String djUrl=MiniConfiger.getProperty("view.DJServiceURL");
 	djUrl=djUrl.replaceAll("/default/login.loginService.flow","/default/framework/Error.jsp");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加房屋关联</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript" src="<%=path%>/js/HouseQuery.js"></script>
	
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
			<table style="width: 100%">
					<tr>
						<td width="100px">
							查询方式
						</td>
						<td>
							<input type="radio" class="easyui-validatebox" name="chooseType" value="1" />房屋坐落 
							<input type="radio" class="easyui-validatebox" name="chooseType" value="2" />地号
							<input type="radio" class="easyui-validatebox" name="chooseType" value="3" />房屋编号
						</td>
						<td >
							<input type="button"  value="查询"  onclick="javascript:query()" >
						</td>
					</tr>
					<tr align="right">
						<td colspan="3">
							<font color="#000000">区 市：</font> 
							<c:if test="${districtID != 0}">
							<input class="easyui-combobox" name="districtID" id="districtID" value="${districtID }"
								data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
								multiple:false,editable:false,formatter: formatNullForCombobox" readonly="readonly"/>
							</c:if>
							<c:if test="${districtID == 0}">
							<input class="easyui-combobox" name="districtID" id="districtID" value="${districtID }"
								data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
								multiple:false,editable:false,formatter: formatNullForCombobox" />
							</c:if>
							</td>
					</tr>
			</table>
			
			<div id="locationNameDiv" style="display: block; width: 80%; float: left;background-color: #D2CECD" align="left">
				<table>
						<tr>
							<td><font color="#000000">房屋坐落：</font>	<input class="easyui-textbox" style="width:100px;" name="roadName" id="roadName"/>&nbsp;&nbsp;<font color="#000000">路</font></td>
							<td><input class="easyui-textbox" style="width:80px;" id="laneName" name="laneName" /></td>
							<td class="form_label" style="width: 5%" align="center" ><font color="#000000">号</font></td>
							<td><input class="easyui-textbox" style="width:80px;" id="subLaneName" name="subLaneName"/></td>
							<td class="form_label" style="width: 5%" align="center" ><font color="#000000">栋</font></td>
							<td><input class="easyui-textbox" style="width:80px;" id="streetNumber" name="streetNumber" /></td>
							<td class="form_label" style="width: 5%" align="center"><font color="#000000">单元</font></td>
							<td class="form_label" style="width: 15%" align="right"></td>
							<td class="form_label" style="width: 15%" align="right"><input type="button"  value="所有权利"  onclick="javascript:queryAllRights()" ></td>
						</tr>
				</table>
			</div>
			<div id="lotNumberDiv" width="100%" style="display: none; width: 80%; float: left ;background-color: #D2CECD" align="left">
				<table>
					<tr>
						<td><font color="#000000">地块编号：</font><input class="nui-textbox" id="lotID" name="lotID" style="width: 80px"  /></td>
						<td><font color="#000000">地籍号：</font><input class="nui-textbox" id="lotNumber" name="lotNumber" style="width: 140px" /></td>
					</tr>
				</table>
			</div>
			<div id="houseIDDiv" width="100%" style="display: none; width: 80%; float: left ;background-color: #D2CECD"	align="left">
				<table>
					<tr>
						<td ><font color="#000000">房屋编号：</font><input class="nui-textbox" id="houseIDs" title="请输入房屋编号" style="width: 140px" /></td>
					</tr>
				</table>
			</div>
			
			<div id="locationDiv" style="display: block;width:100%;background-color: #D2CECD" align="left">
				<table style="width: 95%">
					<tr >
						<td class="form_label" style="width: 70%;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="span_location">
							坐落： <input class="easyui-combobox" id="locationID"  style="width: 200px;" name="locationID" data-options="valueField:'id',textField:'text'"/>&nbsp;</span>
							<span id="span_lot" style="display:none;">
							宗地： <input class="easyui-combobox"  id="lotComboBox" style="width: 200px;" data-options="valueField:'id',textField:'text'"/>&nbsp;</span>
							幢号： <input class="easyui-combobox" id="buildingID" name="buildingID" style="width: 200px;" data-options="valueField:'id',textField:'text'" />
							<span id="span_landuse" style="display:none;">
							宗地用途： <input class="easyui-combobox" id="landuseComboBox" style="width: 200px;" data-options="valueField:'id',textField:'text'"/>&nbsp;</span>
						</td>
					</tr>
				</table>
			</div>
		<form name="frmInfo1" id="form1" action="<%=path%>/inner/permitmanage/gotoQueryHouseList.action" method="post">
			<div id="houseGrid" class="easyui-panel" title="已关联的房屋列表" style="width:100%;height:300px;background:#fafafa;" data-options="iconCls:'icon-collapse',noheader:false">
			</div>
			<div align="center" id="buttonID">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:110px" onclick="doSave()" id="btSave">保存</a>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:110px" onclick="doBack()">返回</a>&nbsp;&nbsp;
			</div>
		</form>
		<script type="text/javascript">	
		var path = "<%=path%>";
		var djUrl = "<%=djUrl%>";
		function doSave(){
			//获取选中的houseID.
			disableButton();
			var houseIDs = findSelectID();
			if(houseIDs == null || houseIDs.length == 0){
				$.messager.alert("提示","请先选择房屋！");
				enableButton();
				return;
			}
			var houseArr = houseIDs.split(',');
			if(houseArr == null || houseArr.length == 0){
				$.messager.alert("提示","请先选择房屋！");
				enableButton();
				return;
			}
			
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSaveHouseRelate.action",
				data : {"houseIDs":houseIDs,"permitID":${permitID},"transID":${transID},"cmd":'${cmd}'},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						enableButton();
						doBack();
					}else{
						$.messager.alert("提示信息",data[0].message);
						enableButton();
					}
				},
				error : function(){
					$.messager.alert("提示信息","保存异常！");
					enableButton();
				}
			});
			
		}
		function doBack(){
			window.location="<%=path%>/inner/permitmanage/gotoHouseRelateList.action?cmd=${cmd}&permitID=${permitID}";
		}
		
		function disableButton(){
			$("#btSave").css("color", "#CCC");  
	        $('#btSave').linkbutton('disable');  
		}
		
		function enableButton(){
			$("#btSave").css("color", "black");  
	        $('#btSave').linkbutton('enable');  
		}
		
		
	</script>
</body>
</html>
