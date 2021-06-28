 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质录入查询</title>
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
			<div data-options="region:'center',title:'青岛市网上房地产申报资格认证',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:65px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" action="<%=basePath%>/inner/companymanage/gotoCompanyQueryList.action" method="post">
							<input type="hidden" name="status" value="${status }" />
							<table width="100%">
							<tr>
								<td>
									&nbsp;项目名称：<input name="projectName" class="easyui-textbox" size="18" />
									&nbsp;企业名称：<input name="companyName" class="easyui-textbox" size="18" />
									&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
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
