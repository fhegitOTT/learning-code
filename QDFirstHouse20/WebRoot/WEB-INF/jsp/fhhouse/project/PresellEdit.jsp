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
		<title>许可证编辑页面</title>
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
		<script type="text/javascript">
			function checkDocument(){
				var docId = $("#document_ID").textbox("getValue");
				var reg=/^[1-9]\d*$|^0$/;
				if (!reg.test(docId)){
					alert("请输入数字!");
					return;
				}
				if (docId ==''){
					alert("请输入收件编号!");
					return;
				}
				openDialog("收件编号检查","<%=path%>/inner/projectmanage/gotoDocumentCheck.action?docId="+docId,350,180);
			}
			
			function doPresellSave(){
		 		if(!$("#frmInfo").form("validate")){
		 			return;
		 		}
		 		var cmd=$("input[name='cmd']").val();
		 		var status=$("input[name='status']").val();
		 		if(cmd=='edit'&&status!='0'){
		 			if(!confirm('修改预售许可证信息后，预售许可证需要重新审核，确定要执行吗？')) {
		 				return;
		 			}
		 		}
		 		
		 		$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/projectmanage/doPresellSave.action",
						data : $("#frmInfo").serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert('保存成功');
								//parent.window.close();
								parent.doClose();
							}else{
								alert(data[0].message);
							}
						},
						error : function(){
							alert("保存失败！");
						}
					});
		 	}
			
			function openDialog(tempTitle,url,tempWidth,tempHeight){
				$("#openDL2").show().dialog({
					title : tempTitle,
					width : tempWidth,
					height: tempHeight,
					modal:true
				});
				$("#openDL2").dialog('open');
				$("#openDL2").dialog('center');
				$('#openDL2').dialog('refresh', url);
			}
		</script>
</head>
<body id="body-layout">
	<form id="frmInfo" name="frmInfo" method="post" >
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="compId" value="${compId}"/>
	<input type="hidden" name="start_ID" value="${unitVO.start_ID}"/>
	<input type="hidden" name="presell_ID" value="${presellVO.presell_ID}"/>
	<input type="hidden" name="project_ID" value="${presellVO.project_ID}"/>
	<input type="hidden" name="status" value="${unitVO.status}"/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_right input_required" bgcolor="#5BADFF" width="30%"><font style="color: #003300">开盘编号：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="start_Code" id="start_Code"  data-options="required:true,validType:'length[0,10]'"  value="${unitVO.start_Code}" style="width: 120px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor=#5BADFF><font style="color: #003300">坐落：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="bookingOffice" id="bookingOffice"  data-options="required:true,validType:'length[0,50]'"  value="${unitVO.bookingOffice}" style="width: 250px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor=#5BADFF><font style="color: #003300">售楼电话：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="bookingPhone" id="bookingPhone"  data-options="validType:'length[0,20]'"  value="${unitVO.bookingPhone}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: #003300">许可证信息：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-combobox" name="type" id="type" value="${presellVO.type}"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_312',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
				<input class="easyui-textbox" type="text" name="presell_Desc" id="presell_Desc"  data-options="required:true,validType:'length[0,40]'"  value="${presellVO.presell_Desc}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: #003300">收件编号：</font></td>
			<td class="input_right" bgcolor="white">
				<input class="easyui-textbox" type="text" name="document_ID" id="document_ID"  data-options="required:true,validType:'length[0,40]'"  value="${unitVO.document_ID}" style="width: 150px"></input>
				&nbsp;<a href="javascript:checkDocument()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:60px">...</a>
			</td>
		</tr>
		<tr height="25">
			<td class="input_right input_required" bgcolor="#5BADFF">
				<font style="color: #003300">初审意见</font>
			</td>
			<td class="input_right" bgcolor="white">
				<textarea rows="4" name="firstMark" id="firstMark" cols="45" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${unitVO.firstMark}</textarea>
			</td> 
		</tr>
		
	</table>
	<br/>
	<div align="center">
		<a href="javascript:doPresellSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
	</div>
	<div id="openDL2"></div>
	</form>
</body>
</html>
