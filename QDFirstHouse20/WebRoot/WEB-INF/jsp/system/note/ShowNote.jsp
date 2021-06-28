 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%@ page language="java" import="java.util.*" %>
 <%@ page language="java" import="com.netcom.nkestate.system.vo.AttachmentVO"%>
 <%@ page language="java" import="com.netcom.nkestate.system.vo.AttachmentFileVO"%>
 <%@ page language="java" import="com.netcom.nkestate.system.bo.AttachmentBO"%>
 <%@ page language="java" import="com.netcom.nkestate.common.StringStamper"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>平台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>	
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
		

	<script type="text/javascript">
	 	
	 	
	 	
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<table width="100%" border=0>
		<tr>
			<td align="center">
				<font size="3">${noteVO.subject}</font>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${noteVO.content}
			</td>
		</tr>
		<tr>
			<td align="right">
				<fmt:formatDate value='${noteVO.writeTime}' pattern='yyyy-MM-dd'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>
