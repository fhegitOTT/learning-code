<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>开盘列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">
		function submitStartUnit(){
	 		var status=$("input[name='condition']:checked").val();
	 		var val = findSelectID();
			if(val=='[]'){
				alert("请先选择！");
				return;
			}
			var url = "<%=path%>/inner/startunit/openOperateReason.action";
			openDialog("操作原因",url,650,300,val,status);
		}
		
		function openStartUnitReason(START_ID){
			var url = "<%=path%>/inner/startunit/openOperateReasonList.action?startID="+START_ID;
			openReasonDialog("操作原因记录",url,700,300);
		}
		
		function saveReason(reason,startIDs,status){
	 		if(reason == ''){
	 			$.messager.alert("提示","请输入操作原因！");
	 			return;
	 		}
	 		var json = {"reason":reason,"startIDs":startIDs,"status":status};
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/startunit/doSaveStartUnitReason.action",
					data : json,
					dataType : "json",
					success : function(data) {
						if (data[0].result == "success") {
							alert('保存成功');
							$('#dlg').dialog('close');
							parent.window.location="<%=path%>/inner/startunit/doSubmit.action?status="+status+"&startIds="+startIDs;
						} else {
							alert(data[0].message);
							$('#dlg').dialog('close');
						}
						
					},
					error : function() {
						alert("保存失败！");
					}
			});
		}
		
		function doClose(){
	 		tableForm.submit();
	 	}
		
		function openDialog(tempTitle,url,tempWidth,tempHeight,startIDs,status){
			$('#dlg').dialog({
	            title: tempTitle,
	            iconCls:"icon-edit",
	            collapsible: true,
	            minimizable: true,
	            maximizable: true,
	            resizable: true,
	            width: tempWidth,
	            height: tempHeight,
	            modal: true,
	            href: url,
	            buttons: [{
	                text: '保存',
	                iconCls: 'icon-ok',
	                algin:"center",
	                handler: function () {
	                    	var reason = $("#reason").val();
	                    	saveReason(reason,startIDs,status);
	                	}
	            	}, 
	            	{
	                text: '关闭',
	                iconCls: 'icon-cancel',
	                algin:"center",
	                handler: function () {
	                    $('#dlg').dialog('close');
	                }
	            	}]
	        });

		}
		function openReasonDialog(tempTitle,url,tempWidth,tempHeight){
			$('#reasondlg').dialog({
	            title: tempTitle,
	            iconCls:"icon-edit",
	            collapsible: true,
	            minimizable: true,
	            maximizable: true,
	            resizable: true,
	            width: tempWidth,
	            height: tempHeight,
	            modal: true,
	            href: url,
	            buttons: [
	            	{
	                text: '关闭',
	                iconCls: 'icon-cancel',
	                algin:"center",
	                handler: function () {
	                    $('#reasondlg').dialog('close');
	                }
	            	}]
	        });

		}
		
		function doStartUnitInfo(START_ID,presell_desc,project_id,seller_name){
			var url = "<%=path%>/inner/startunit/startUnitBasicInfo.action?startID="+START_ID+"&presellDesc="+presell_desc+"&projectID="+project_id+"&sellerName="+seller_name;
			url=encodeURI(url);
	 		parent.openDialog("开盘单元信息",url,650,400);
		}
		
		function doStartTime(START_ID){
			var url = "<%=path%>/inner/startunit/setUpStartTime.action?startID="+START_ID;
			parent.openDialog("开盘时间设定",url,650,250);
		}
		
	 	//获取选择ID数组
		function findSelectID(){
 			var arr=[];
  			$("input[type='checkbox']:checked").each(function(){ 
      		 		arr.push(this.value);
  			})
		  	var json = JSON.stringify(arr);//数组转换成json，都在了，数组和json
		  	return json;
			//alert(json);
		}
		</script>
	</head>
	
	<body style="margin-right: 1px; margin-left: 1px">

		<div id="p" class="easyui-panel" title="开盘查询列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post" action="">
				<div align="center" style="padding-top: 15px;padding-bottom: 15px;">
					<input name="condition" type="radio" onclick="doSearch()" value="0" checked="checked"/><font size="4">立即开盘</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="condition" type="radio" onclick="doSearch()" value="1" /><font size="4">不开盘</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="condition" type="radio" onclick="doSearch()" value="2" /><font size="4">暂停销售</font>
				</div>		
				<input type="hidden" name="projectName" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectName"))%>"/>
				<input type="hidden" name="presellDeSc" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("presellDeSc"))%>"/>
				<input type="hidden" name="issalable" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("issalable"))%>"/>
				<input type="hidden" name="documentID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("documentID"))%>"/>
				<input type="hidden" name="startCode" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startCode"))%>"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						out.println(htmlview);
					}
				%>
			</form>
			<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="submitStartUnit()">提交</a>&nbsp;&nbsp;
			</div>
		</div>
		<div id="dlg"></div>
		<div id="reasondlg"></div>	
	</body>
</html>
