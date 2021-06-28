 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%@ page language="java" import="java.util.*" %>
 <%@ page language="java" import="com.netcom.nkestate.system.vo.*"%>
 <%@ page language="java" import="com.netcom.nkestate.framework.util.*"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登陆公告显示</title>
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
	<form id="frmInfo" name="frmInfo" action="">
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:95%;background-color: #7DD5FF" align="center">   
		<% 
		List<BulletinVO> list = (List<BulletinVO>)request.getAttribute("bulletinList");
		for(BulletinVO vo:list){
		%>
		<tr height="20">
        	<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: black;">标题：</font></td>
        	<td class="input_right" >
        		<font style="font-size:14px;"><%=vo.getTitle() %></font>
        	</td>
		</tr>
		
		<tr height="20">
        	<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: black">修改时间：</font></td>
        	<td class="input_right" bgcolor="white">
        		<%
        			long updDate = vo.getUpdDate();
        			long updTime = vo.getUpdTime();
        			String date =updDate+ StringUtil.getFullwidthStr(String.valueOf(updTime), 6);
        			date = DateUtil.formatDateTime(DateUtil.parseDateTime2(date));
        		
        		%>
        		<%=date %>
        	</td>
        	
		</tr>
        <tr>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">公告内容：</font></td>
        	<td class="input_right" bgcolor="white">
				<%=vo.getContent() %>
			</td>        		
		</tr>
		<tr>
			
		</tr>
		<%} %>
	</table>	
		<br/>
	</form>
</body>
</html>
