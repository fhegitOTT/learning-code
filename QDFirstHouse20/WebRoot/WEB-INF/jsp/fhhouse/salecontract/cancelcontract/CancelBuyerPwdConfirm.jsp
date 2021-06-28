 <%@ page contentType = "text/html; charset=UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
					<div data-options="region:'north',split:true,border:false" style="height:95px;padding-top:15px;" id="div1">
						<input type="hidden" name="conId" value="${contractId }" />
						<input type="hidden" name="buyerPwd" value="${buyerPwd }" />
						<c:forEach items="${list}" var="b">
							<input type="hidden" name="realPwd" id="realPwd" value="${b.pwd }"/>
						</c:forEach>
							 
						<form name="frmInfo1" id="form3" target="listfrm" action="<%=basePath%>/inner/contractmanage/cancelQueryResult.action" method="post">
							<table width="100%">
						<c:if test="${list3!=null}">
						<c:forEach items="${list3}" var="a">
							<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;用户名：
									${a.buyerName }&nbsp;&nbsp;密码：<input type="password" name="pwd1" id="pwd1" onblur="upperCase(this)"></input>
								</td>
					    	</tr>										
					    </c:forEach>
						</c:if>
					    	<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery2()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">提    交</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doReset2()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重    置</a>
								</td>
					    	</tr>
						    </table>
						</form>
						
					</div>
</body>
</html>
