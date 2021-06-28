 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>房屋关联列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function doPermitEdit(){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			parent.window.location="<%=path%>/inner/permitmanage/gotoPermitEdit.action?cmd=edit&permitID="+val;
		}
		
		//添加房屋关联
		function doAddHouse(){
	 		//openDialog("新增房屋关联","<%=path%>/inner/permitmanage/gotoAddHouse.action?permitID=${permitID}&transID=${transID}",1000,500);
	 		//var TargetUrl = "<%=path%>/inner/permitmanage/gotoAddHouse.action?permitID=${permitID}&transID=${transID}";
			//window.open(TargetUrl,"","toolbar=no,menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
			window.location="<%=path%>/inner/permitmanage/gotoAddHouse.action?cmd=${cmd}&permitID=${permitID}&transID=${transID}&districtID=${districtID}";
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
		
		
		function doBack(){
			window.location="<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
		}
		
		function doDeleteHouse(){
	 		var houseIDs = findSelectID();
			if(houseIDs == ''){
				alert("请先选择！");
				return;
			}
			$.messager.confirm("操作提示","您确定要删除该房屋关联？",function(data){
				if(data){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/deleteHouseRelate.action?cmd=${cmd}&type=del",
						data : {"houseID":houseIDs,"permitID":"${permitID}"},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								window.location.reload();
							}else{
								alert(data[0].message);
							}
						},
						error : function(){
							alert("删除异常！");
						}
					});
				}
			});
		}
		function doDeleteAll(){
			$.messager.confirm("操作提示","您确定要删除所有的该房屋关联？",function(data){
				if(data){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/deleteHouseRelate.action?cmd=${cmd}&type=delall",
						data : {"permitID":"${permitID}"},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								//window.location.reload();
							}else{
								alert(data[0].message);
							}
							window.location.reload();
						},
						error : function(){
							alert("删除异常！");
						}
					});
				}
			});
			
			
		}
		
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("houseID");
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked && tempIDS[i].value != 'on'){
					selID += tempIDS[i].value+",";
				}
			}
			return selID;
		}
		
		//计算条数
 		function getNewTotal(columns,scale){
	 		var total = 0;
	 		
	 		var param = Math.pow(10, scale);
	 		
	 		$("table.default_tableCss").find("tr").each(function () {
	            var arrtd = $(this).children();
	            var tdContext = $.trim(arrtd.eq(columns).text());
	            if(isNaN(tdContext)){
	            	tdContext = 0;
	 			}
	            total += param * tdContext;
	         })
	        
	 		total = Math.round(total) / param;
	 		
	 		return total;
 		
 		}
 		
 		$(function(){
			updateArea();
		});	
		function updateArea(){
   			num=0;
   			var flarea = getNewTotal(6,2);
   			var planFlarea = getNewTotal(8,2);
   			var cellarflarea = getNewTotal(7,2);
   			$("#span_flarea").html(flarea);
   			$("#span_cellarflarea").html(cellarflarea);
   			$("#span_planflarea").html(planFlarea);
   			$("table.default_tableCss").find("tr").each(function (){
   				num=num+1;
   			})
   			if(num>0){
   				num=num-1;
   			}
   			$("#span_num").html(num);
   			
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:120px" onclick="doAddHouse()">添加房屋关联</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:120px" onclick="doDeleteHouse()">删除房屋关联</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:130px" onclick="doDeleteAll()">删除所有房屋关联</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:130px" onclick="doBack()">返回</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="<c:if test="${logo!='edit'}">已关联的房屋列表</c:if>" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<input type="hidden" name="permitID" value="<%=request.getParameter("permitID")%>"/>
				<%  
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   //System.out.println(htmlview);
						   out.println(htmlview);
					}
				%>
				<div id="countDiv">
			     	<table id="totaltable">
			     	<tr><td align="right">合计>房屋总建筑面积（单位：平方米）：</td><td> <span id="span_flarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋总地下面积：</td><td> <span id="span_cellarflarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋总预测面积：</td><td><span id="span_planflarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋套数：</td><td><span id="span_num"></span></td>
			     	</tr>
			     	</table>
	 			</div>
			</form>
		</div>
		<div id="openDL">
    	
   	 	</div>
</body>
</html>
