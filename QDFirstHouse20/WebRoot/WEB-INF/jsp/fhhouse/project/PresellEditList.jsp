  <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>项目许可证列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">
		function doBack(){
			window.location = "<%=path%>/inner/projectmanage/gotoProjectEditList.action?compId="+tableForm.compId.value;
	 	}
	 	
		function gotoPresellEdit(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			openDialog("许可证编辑","<%=path%>/inner/projectmanage/gotoPresellEditFrame.action?cmd=edit&startId="+val+"&compId="+tableForm.compId.value+"&projectId="+tableForm.projectId.value,650,400);
			
		}
		
		function gotoPresellAdd(){
			openDialog("许可证新增","<%=path%>/inner/projectmanage/gotoPresellEditFrame.action?cmd=add&compId="+tableForm.compId.value+"&projectId="+tableForm.projectId.value,650,400);
		}
		
		
		function gotoBuildingRelate(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			window.location="<%=path%>/inner/projectmanage/gotoStartBuildingRelation.action?startId="+val+"&compId="+tableForm.compId.value+"&projectId="+tableForm.projectId.value;
			
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
					url : "<%=path%>/inner/projectmanage/doPresellFirstSubmit.action?startId="+val,
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
	 	
	 	function doClose(){
	 		//alert(123);
	 		$("#openDL").dialog('close');
			tableForm.submit();
	 	}
	 	

		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selStartID");
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
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="gotoPresellAdd()">新增</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoPresellEdit()">编辑</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoBuildingRelate()">楼栋维护</a>&nbsp;&nbsp;
			<c:if test="${backFlag=='edit'}">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="doFirstSubmit()">提交审核</a>&nbsp;&nbsp;
			</c:if>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px" onclick="doBack()">返回</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="预售许可证一览 (开盘单元)"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="<%=path%>/inner/projectmanage/gotoPresellEditList.action">
				<input type="hidden" name="compId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compId")) %>"/>
				<input type="hidden" name="projectId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectId")) %>"/>
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
