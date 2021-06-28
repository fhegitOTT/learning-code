 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>限购信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">

	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
					<div data-options="region:'north',split:true,border:false" style="height:75px;padding-top:15px;" id="div1"> 
						<form name="frmInfo1" id="frmInfo1" target="listfrm" action="<%=basePath%>/outer/signcontract/saveXgInfo.action" method="post">
							<input type="hidden" name="houseID" value="${houseID }" />	
							<input type="hidden" name="cID" value="${contractID }" />
							<c:choose>
							<c:when test="${list!=null && fn:length(list) > 0 }">
								<table width="90%" border="1" align="center">
									<tr style="height: 30px;">
										<td align="center">姓名</td>
										<td align="center">身份证号</td>
									</tr>
									<c:forEach items="${list}" var="xgBuyerInfoVO">
									<tr style="height: 30px;">
										<td align="center">${xgBuyerInfoVO.buyerName }</td>
										<td align="center">${xgBuyerInfoVO.buyerCardno }</td>
									</tr>
									</c:forEach>
								</table>
							</c:when>
							<c:otherwise>
								<table width="100%">
								<tr style="height: 30px;">
									<td align="center">
										&nbsp;&nbsp;编号：<input class="easyui-numberbox" type="text" name="xgID" id="xgID" value="${xgID }"></input>
										&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:xgInfoQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查    询</a>
									</td>
						    	</tr>
						    	</table>
							    <div align="center" style="padding-top: 20px;">
							    	<%  String htmlview = String.valueOf(request.getAttribute("htmlView"));
										if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
										   out.println(htmlview);
									%>
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doXgSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提    交</a>
									<%
										}
									 %>
							    </div>
						    </c:otherwise>
						    </c:choose>
						</form>
					</div>
</body>
</html>
