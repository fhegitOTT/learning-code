<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约人许可证权限查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>

		<style type=text/css>
			* {color: #555;}
			body {font-size: 12px;margin: 0;font: Arial, Helvetica, "宋体", sans-serif;}
			.toplogo {width: auto;line-height: 40px;margin-left: 10px;display: inline;float: left;overflow-y: hidden;}
			/*最底部*/
			.bottom {overflow-y: hidden}
			.bottom_ban {width: auto;line-height: 40px;margin-left: 18px;display: inline;float: left;overflow-y: hidden;}
			.bottom_shi {width: auto;line-height: 40px;margin-right: 20px;display: inline;float: right;overflow-y: hidden;}
		</style>

	<script type="text/javascript">
		
	 	function doQuery(){
	 		document.frmInfo.submit();
	 	}
	 	function doClean(){
	 		$("#frmInfo").form('clear');
	 	}
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'签约人许可证权限查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:15%;padding-top:5px;background-color:#d9e4f2;" id="div1"> 
						<form name="frmInfo" id="frmInfo" target="listfrm" method="post" action="<%=path %>/inner/presellmanage/gotoUserProjectSetQueryList.action">
				<table width="95%">
					<tr>
						<td>
							<div align="right">
								用户名：
							</div>
						</td>
						<td>
							<input class="easyui-textbox" name="signerName" id="signerName" type="text"/>
						</td>
						<td>
							<div align="right">
								许可证描述：
							</div>
						</td>
						<td>
							<input class="easyui-textbox" name="presell_Desc" id="presell_Desc"/>
						</td>
						<td>
							<div align="right">
								开发公司：
							</div>
						</td>
						<td>
							<input class="easyui-textbox" name="companyName" id="companyName"/>
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								项目名称：
							</div>
						</td>
						<td>
							<input class="easyui-textbox" name="projectName" id="projectName" />
						</td>
						<td>
							<div align="right">
								操作时间：
							</div>
						</td>
						<td>
							<input class="easyui-datebox" name="savedate" id="savedate" type="text" size="10" maxlength="8" />
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div style="float:right">
								<a href="javascript:doClean()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重	置</a>
								<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
							</div>		
						</td>
					</tr>
				</table>
				</form>
				</div>
				<div data-options="region:'center',border:false">
					<iframe frameborder="0" height="95%" width="100%" id="listfrm" name="listfrm"></iframe>
				</div>
			</div>
		</div>
		</div>
			
		<div id="openDL"></div>
	</body>
</html>
