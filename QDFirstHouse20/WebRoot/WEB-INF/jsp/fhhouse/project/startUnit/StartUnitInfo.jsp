<%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内网公告查看</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">
	
		</script>	
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}
			
			.input_required:before{
				content:"* ";
				color:red;
			}
			tr{
				background-color:white;
			}			
		</style>
		
		
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:95%;background-color: #7DD5FF" align="center">   
		<tr height="25">
        	<td class="input_left" bgcolor="#5BADFF" width=15%><font style="color: black">开盘编号：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="title" class="easyui-textbox" type="text" value="${suvo.start_Code}"  style="width: 450px"></input>
        	</td>
		</tr>
		
		<tr height="25">
        	<td class="input_left" bgcolor="#5BADFF" width=25%><font style="color: black">许可证/权属证明编号：</font></td>
        	<td class="input_right" bgcolor="white">
         		<input name="title" class="easyui-textbox" type="text" value="${desc}"  style="width: 450px"></input>
        	</td>
		</tr>
		
		<tr height="25">
        	<td class="input_left" bgcolor="#5BADFF" width=15%><font style="color: black">收件编号：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="title" class="easyui-textbox" type="text" value="${suvo.document_ID}"  style="width: 450px"></input>
        	</td>
		</tr>

		<tr height="25">
        	<td class="input_left" bgcolor="#5BADFF" width=15%><font style="color: black">项目名称：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="title" class="easyui-textbox" type="text" value="${pvo.projectName}"  style="width: 450px"></input>
        	</td>
		</tr>
		
		<tr height="25">
        	<td class="input_left" bgcolor="#5BADFF" width=15%><font style="color: black">项目甲方：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="title" class="easyui-textbox" type="text" value="${eqvo.name}"  style="width: 450px"></input>
        	</td>
		</tr>
		
        <tr>
        	<td class="input_left" bgcolor="#5BADFF"><font style="color: black">备注：</font></td>
        	<td class="input_right" bgcolor="white">
				<textarea rows="8" name="content" cols="55" class="easyui-validatebox" >${eqvo.firstMark}&#10;初审意见：${suvo.firstMark}</textarea>
			</td>        		
		</tr>		
	</table>	
		<br/>
		
		<div align="center">
			<a href="javascript:closeInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">关闭</a>
		</div>
</body>
</html>
