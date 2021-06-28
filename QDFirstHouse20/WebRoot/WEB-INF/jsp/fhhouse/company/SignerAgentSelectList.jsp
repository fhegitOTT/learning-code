 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约用户选择代理机构列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		function doSelAgent(agentID,agentName){
			parent.doSelAgent(agentID,agentName);
		}
		function doQueryAgent(){
			frmInfo1.submit();
		}
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow-y:scroll;">
			<form name="frmInfo1" id="frmInfo1" action="<%=path%>/inner/companymanage/gotoAgentSelectList.action" method="post">
				<table width="100%">
				<tr>
					<td>
						&nbsp;企业代码：<input class="easyui-textbox" name="agentCode" id="agentCode" value="${agentCode}" ></input>
						&nbsp;企业名称：<input class="easyui-textbox" name="agentName" id="agentName" value="${agentName}"></input>
						&nbsp;&nbsp;<a href="javascript:doQueryAgent()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
					</td>
		    	</tr> 
			    </table>
			    <%						
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
				    out.println(htmlview);
			}%>
			</form>
			
</body>
</html>
