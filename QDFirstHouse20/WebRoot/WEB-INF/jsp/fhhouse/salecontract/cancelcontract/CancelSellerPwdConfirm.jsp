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
						<c:if test="${msg!=null}">
						<div align="center" style="font-size: 28px;height: 95px;padding-top: 35px;">${msg}</div>
						</c:if>
						<c:if test="${msg==null}">
						<input type="hidden" name="sellerPwd" value="${sellerPwd }">
						<input type="hidden" name="cId" value="${contractId }" />	
						<form name="frmInfo1" id="form2" target="listfrm" action="<%=basePath%>/inner/contractmanage/cancelQueryResult.action" method="post">
							<table width="100%">
							<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;甲方名称：									
									<c:if test="${list1!=null}">
										<c:forEach items="${list1}" var="a">
											${a.sellerName }
										</c:forEach>
									</c:if>
								</td>
					    	</tr>				
							<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;密码：<input class="easyui-textbox" type="password" name="pwd" id="pwd"></input>
								</td>
					    	</tr>
					    	<tr style="height: 30px;">
								<td align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doQuery1()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">提    交</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doReset1()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重    置</a>
								</td>
					    	</tr>
							<tr style="height: 30px;">
								<td align="center">
									还有以下用户尚未验证：								
								</td>
					    	</tr>
							<tr style="height: 30px;">
								<td align="center">
									<c:if test="${list2!=null}">
										<c:forEach items="${list2}" var="b">
											${b.buyerName }
										</c:forEach>
									</c:if>
								</td>
					    	</tr>
						    </table>
						</form>
						</c:if>
					</div>
</body>
</html>
