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
	
		//查看房屋列表
		function lookHouse(building_id){
			openDialog("房屋查看","<%=path%>/inner/house/lookHouseList.action?buildingID="+building_id,900,450);
			
			//window.location = "<%=path%>/inner/house/lookHouseList.action?buildingID="+building_id;
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
	
		function doSubmit(){
 			var selectVal = findSelectID();
 			var notSelectVal = findNotSelectID();
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/house/changeStatus.action?selectVal="+selectVal+"&notSelectVal="+notSelectVal,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						$("#openDL").dialog('close');
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("房屋操作失败！");
				}
			});	
		}
		
		function doBack(){
			$("#openDL").dialog('close');
		}
		
	 	//获取选择ID数组
		function findSelectID(){
 			var arr=[];
  			$("input[name='status']:checked").each(function(){ 
      		 		arr.push(this.value);
  			})
		  	var json = JSON.stringify(arr);//数组转换成json，都在了，数组和json
		  	return json;
		}
		
 		//获取未选择ID数组
		function findNotSelectID(){
 			var arr=[];
  			$("input[name='status']").each(function(){
  					if(this.checked==false){
  						arr.push(this.value);
  					} 
  			})
		  	var json = JSON.stringify(arr);//数组转换成json，都在了，数组和json
		  	return json;
		}
		</script>
	</head>
	
	<body style="margin-right: 1px; margin-left: 1px">

		<div id="p" class="easyui-panel" title="楼幢信息列表" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post" action="<%=path%>/inner/house/buildingInfo.action">
			
				<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startID")) %>"/>
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						out.println(htmlview);
					}
				%>
			</form>
			<div align="center">
				<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
			</div>
		</div>
		<div id="openDL">
    	
    </div>	
	</body>
</html>
