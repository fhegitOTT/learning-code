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
			
			document.frmInfo1.submit();
		}
		
		function checkData(){
			var startDate = $("#startDate").datebox("getValue");
	 		var overtDate = $("#overtDate").datebox("getValue");
	 		if(startDate!=null && overDate!=""){
	 			 if(startDate>overDate){
	 				alert("结束时间不能在开始时间之前！");	
					return false;
		 		 }
	 		}

	 		return true;
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'收件一览',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/inner/document/gotoDocumentList.action">
							<table width="100%">
							<!--<input type="text" name="name1" value="${companyName }" />-->
							<tr>
								<td>
									&nbsp;&nbsp;收件编号：<input class="easyui-textbox" type="text" name="documentId" id="documentId"></input>
									&nbsp;&nbsp;项目名称：<input class="easyui-textbox" type="text" name="projectName" id="projectName"></input>
									&nbsp;&nbsp;存放位置：<input class="easyui-textbox" type="text" name="position" id="position"></input>
									&nbsp;&nbsp;企业名称：<input class="easyui-textbox" type="text" name="companyName" id="companyName"></input><br/>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;创建日期从：<input class="easyui-datebox" type="text" name="startDate" id="startDate"></input>
									&nbsp;&nbsp;- 至<input class="easyui-datebox" type="text" name="overtDate" id="overtDate"></input>
									&nbsp;&nbsp;流转状态：<select id="userId" name="userId" class="easyui-combobox">
									   						<option value="" >全部</option>
									   					<c:if test="${requestScope.list1!=null}">
										   					<c:forEach items="${requestScope.list1}" var="a">
										   						<option value="${a.userId }1">${a.displayName }</option>
										   					</c:forEach>
									   					</c:if>
									   					<c:if test="${requestScope.list2!=null}">
										   					<c:forEach items="${requestScope.list2}" var="b">
										   						<option value="${b.userId }2">${b.userName}</option>
										   					</c:forEach>
									   					</c:if>
				        								</select>
									&nbsp;&nbsp;收件状态：<select id="status" name="status" class="easyui-combobox">
									   						<option value="" >全部</option>
					        								<option value="0" >未退回</option>
					        								<option value="1" >已退回</option>
				        								</select>
									&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
								</td>
					    	</tr> 
						    </table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src="<%=basePath%>/inner/document/gotoDocumentList.action"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>