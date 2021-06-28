<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售许可证</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
	<script type="text/javascript">
		function doQuery(){
			document.frmInfo1.submit();
		}

		$(function(){
			doQuery();
		})
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'预售许可证查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" action="<%=basePath%>/inner/permitmanage/gotoPermitQueryList.action?cmd=${cmd}" method="post">
							<table width="100%">
							<tr>
								<td>
									&nbsp;企业名称：<input class="easyui-textbox" type="text" name="companyName" id="companyName"></input>
									&nbsp;预售许可证号：<input class="easyui-textbox" type="text" name="permitNo" id="permitNo"></input>
									&nbsp;&nbsp;
									<c:if test="${cmd=='query'}">
									&nbsp;&nbsp;状态：<select id="status" class="easyui-combobox" name="status" style="width: 100px">
										<option value="10">全部</option>
										<option value="1">受理</option>
										<option value="2">审核</option>
										<option value="3">发证</option>
										<option value="-1">归档</option>
									</select>
									</c:if>
									<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
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
		<div id="openDL"></div>	
</body>
</html>
