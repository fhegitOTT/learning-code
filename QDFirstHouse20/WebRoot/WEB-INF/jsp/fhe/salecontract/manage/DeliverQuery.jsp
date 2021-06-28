 <%@ page contentType = "text/html; charset=UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>收件录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">
		
		function doQuery(){
			var startDate = $("#startDate").datebox("getValue");
	 		var overDate = $("#overDate").datebox("getValue");
	 		if(startDate!=null && overDate!=""){
	 			 if(startDate>overDate){
	 				alert("结束时间不能在开始时间之前！");	
					return;
		 		 }
	 		}
			document.frmInfo1.submit();
		}
		
		function checkData(){
			var startDate = $("#startDate").datebox("getValue");
	 		var overDate = $("#overDate").datebox("getValue");
	 		if(startDate!=null && overDate!=""){
	 			 if(startDate>overDate){
	 				alert("结束时间不能在开始时间之前！");	
					return false;
		 		 }
	 		}

	 		return true;
		}

		$(function(){
            doQuery();
        });
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'交接书查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/outer/manage/deliverQueryList.action">
							<table width="100%">
							<tr>
								<td>
									&nbsp;&nbsp;合同编号：<input class="easyui-numberbox" type="text" name="contractID" id="contractID"></input>
									&nbsp;&nbsp;乙方：<input class="easyui-textbox" type="text" name="buyer" id="buyer"></input>
									&nbsp;&nbsp;签订日期从：<input class="easyui-datebox" type="text" name="startDate" id="startDate"></input>
									&nbsp;&nbsp;至&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="overDate" id="overDate"></input>
								&nbsp;&nbsp;开盘编号：<input class="easyui-textbox" type="text" name="startCode" id="startCode"></input>
									&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
								</td>
							</tr>
						    </table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm"></iframe>
					</div>
				</div>
			</div>
		</div>
</body>
</html>