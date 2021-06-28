 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>出售合同模板浏览</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/RMBUtil.js"></script>
	</head>
	<body >
		<form id="a1moneytemp" name="a1moneytemp" method="post" action="<%=basePath%>/outer/contracttemplatemanage/saveAttach1MoneyTemp.action">
			<input type="hidden" id="ID" name="ID" value="${a1moneyTempVO.ID }"/>
			<input type="hidden" id="templateID" name="templateID" value="${a1moneyTempVO.templateID }"/>
			<input type="hidden" id="serial" name="serial" value="${a1moneyTempVO.serial }"/>
			<table  cellspacing=16 cellpadding=0 border=0 align="center">
				<tr>
					<td>
						${a1moneyTempVO.serial }、乙方于
				  		<input  type="text" size="8" name="paymentDate" value="${a1moneyTempVO.paymentDate }"/>
						前向甲方支付<c:if test="${a1moneyTempVO.serial==1 }">首期</c:if>房款人民币 
						<input  type="text" size="20" id="money" name="money" maxlength="20" value="${a1moneyTempVO.money }" onkeyup="small2big('money','moneyCn')" onchange="small2big('money','moneyCn')"/>
						元；
						<br/><br/>
						(大写) 
						<input readonly="readonly" type="text" size="68" id="moneyCn" name="moneyCn" maxlength="20" value="${a1moneyTempVO.moneyCn }"/>
						。
						<br/>
					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" name="submit" onclick="doSave();" value="保存"/>
				<input type="reset" name="reset" value="重置"/>
			</div>
		</form>
	</body>
</html>