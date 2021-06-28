  <%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业项目列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">
		function doBack(){
			window.location = "<%=path%>/inner/companymanage/doCompanyEditBack.action";
	 	}
	 	
		function gotoProjectEdit(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			openDialog("项目编辑","<%=path%>/inner/projectmanage/gotoProjectEdit.action?cmd=edit&projectId="+val+"&compId="+tableForm.compId.value,450,300);
			
		}
		
		function gotoProjectAdd(){
			openDialog("项目编辑","<%=path%>/inner/projectmanage/gotoProjectEdit.action?cmd=add&compId="+tableForm.compId.value,450,300);
		}
		
		
		function gotoPresell(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.location="<%=path%>/inner/projectmanage/gotoPresellEditList.action?projectId="+val+"&compId="+tableForm.compId.value;
			
		}
		
		function doFirstSubmit(){
			var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/projectmanage/doProjectFirstSubmit.action?projectId="+val,
					data : {},
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert('提交审核成功');
							window.loaction = window.loaction;
							window.location.reload();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("提交审核失败！");
					}
				});
	 	}
	 	
	 	function doProjectSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		if(frmInfo.cmd.value=='edit'&&frmInfo.status.value!='0'){
	 			if(!confirm('修改项目信息后，项目需要重新审核，确定要执行吗？')) {
	 				return;
	 			}
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/projectmanage/doProjectSave.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert('保存成功');
							$("#openDL").dialog('close');
							tableForm.submit();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("保存失败！");
					}
				});
	 	}
		
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selProjectID");
			var sel = 0;
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked){
					sel+=1;
					//alert(pays[i].value);
					selID = tempIDS[i].value;
				}
			}
			if(sel!=1){
				return "";
			}else{
				return selID;
			}
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
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="gotoProjectAdd()">新增项目</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoProjectEdit()">编辑项目</a>&nbsp;&nbsp;
			<c:if test="${backFlag=='edit'}">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doFirstSubmit()">提交审核</a>&nbsp;&nbsp;
			</c:if>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:110px" onclick="gotoPresell()">许可证信息</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px" onclick="doBack()">返回</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="青岛市网上房地产签约企业项目管理企业项目列表 "
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="<%=path%>/inner/projectmanage/gotoProjectEditList.action">
				<input type="hidden" name="compId" value="${compId}"/>
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
				    out.println(htmlview);
			}%>
			</form>
		</div>
		<div id="openDL"></div>
</body>
</html>
