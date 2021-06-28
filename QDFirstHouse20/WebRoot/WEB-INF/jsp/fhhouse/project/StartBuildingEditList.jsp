 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
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
		<title>楼栋选择编辑页面</title>
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
			function doDel(){
				listfrmInfo.submit();
			}
			
			function doSave(){
				$("#btnExport").css("color", "#CCC");  
		        $('#btnExport').linkbutton('disable');  
		        //setTimeout('$("#btnExport").linkbutton("enable");$("#btnExport").css("color","black");', 5000);//5秒后恢复 
				$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/projectmanage/doStartBuildingSave.action",
						data : $("#listfrmInfo").serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								parent.window.location=parent.window.location;
							}else{
								alert(data[0].message);
							}
							$('#btnExport').linkbutton('enable');
							$("#btnExport").css("color","black");
						},
						error : function(){
							alert("房屋关联保存出错");
						}
					});
			}
			
			
			function gotoHouseSelect(buildingId){
				parent.gotoHouseSelect(buildingId);
			}
		</script>
</head>
<body id="body-layout">
	<form id="listfrmInfo" name="listfrmInfo" method="post" action="<%=path%>/inner/projectmanage/gotoStartBuildingEditList.action" >
	<input type="hidden" name="compId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compId")) %>"/>
	<input type="hidden" name="projectId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectId")) %>"/>
	<input type="hidden" name="startId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startId")) %>"/>
	<input type="hidden" name="selcmd" value="3"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td align="center" bgcolor="#5BADFF" width="8%"><font style="color: #003300">删除选择</font></td>
			<td align="center" bgcolor="#5BADFF" width="10%"><font style="color: #003300">楼幢编号</font></td>
			<td align="center" bgcolor="#5BADFF"><font style="color: #003300">楼幢名称</font></td>
			<td align="center" bgcolor="#5BADFF" width="20%"><font style="color: #003300">参考价格（元）</font></td>
			<td align="center" bgcolor="#5BADFF" width="15%"><font style="color: #003300">价格幅度</font></td>
		</tr>
		<c:forEach items="${selbuildingList}" var="buildingvo">
			<tr height="25">
				<td align="center" bgcolor="white">
					<input type="checkbox" name="DelSelect_${buildingvo.building_ID}"/>
				</td>
				<td align="center" bgcolor="white"><a href="#" onclick="gotoHouseSelect(${buildingvo.building_ID})">${buildingvo.building_ID}</a></td>
				<td align="center" bgcolor="white">${buildingvo.attribute.location}</td>
				<td align="center" bgcolor="white">
					<input class="easyui-numberbox" type="text" name="Reference_Price_${buildingvo.building_ID}" id="Reference_Price_${buildingvo.building_ID}"  data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12"  value="${buildingvo.reference_Price}" style="width: 150px"></input>
				</td>
				<td align="center" bgcolor="white" >
					<input class="easyui-numberbox" type="text" name="Ratio_${buildingvo.building_ID}" id="Ratio_${buildingvo.building_ID}"  data-options="required:true,validType:'length[0,5]'" precision="2" max="9.99" size="5"  value="${buildingvo.ratio}" style="width: 80px"></input>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doDel()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">删除</a>&nbsp;&nbsp;
		<a href="javascript:doSave()" id="btnExport" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
	</div>
	<br/>
	<div id="openDL"></div>
	</form>
</body>
</html>
