 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>平台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>

		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}			
		</style>
		
		
		
		
	<script type="text/javascript">
		$(function (){
			doQuery();
		});
		
		function doQuery(){
			frmInfo.submit();
		}
		
		function add(){
			location = "<%=basePath%>/system/queryNote.action?opFlag=add";
		}
		
		function edit(){
			list.window.showNoteDetail();
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:true,border:false" style="height:40px;padding-top:5px;overflow: hidden;"> 
			<form name="frmInfo" action="<%=basePath %>/system/queryNotes.action" target="list">
				<input type="hidden" name="p_isFromQuery" id="p_isFromQuery" value="1"/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="add()">新增公告</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="edit()">编辑公告</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<iframe frameborder="0" height="99%" width="100%" id="list" name="list"></iframe>
		</div>
	</div>
</body>
</html>
