<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.manage.vo.DocumentHistoryVO"%>
<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>档案流转履历</title>
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
	<form id="frmInfo" name="frmInfo" action="">
	<br>
	
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:95%;background-color: #7DD5FF" align="center">   
		<tr height="25">
        	<td style="text-align: center;" bgcolor="#5BADFF" width=50%><font style="color: black;">操作日期</font></td>
    	    <td style="text-align: center;" bgcolor="#5BADFF" width=50%><font style="color: black">经手人</font></td>
		</tr>
		
		<%
			List<DocumentHistoryVO> list=(List)request.getAttribute("list");
			if(list!=null && list.size()>0){
				for(DocumentHistoryVO vo:list){
				String date=vo.getCreDate() + StringUtil.getFullwidthStr(String.valueOf(vo.getCreTime()), 6);
				String formatDate=DateUtil.formatDateTime(DateUtil.parseDateTime2(date));
		 %>
		<tr height="25">
        	<td style="text-align: center;" bgcolor="#5BADFF" width=50%><font style="color: black"><%=formatDate %></font></td>
    	    <td style="text-align: center;" bgcolor="#5BADFF" width=50%><font style="color: black"><%=vo.getAttribute("name") %></font></td>
		</tr>
		<%
				}
			}
		 %>
		
	</table>	
		<br/>
		
		<div align="center">
			<a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		</div>
	</form>
</body>
</html>
