<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>收件录入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />


<style type="text/css">
	@media print{
			.noprint{
				display: none
			}
	}
	*{
		line-height: 30px;
	
	}
	p{
		text-indent: 2em;
	}
	table tr td{
		font-family: '宋体';
	}
	.uSpan{
		border-bottom: solid 1px;
	}
</style>
<script type="text/javascript">
	function MyPrint(){
		window.print();
	}
</script>
</head>
<%
	DeliverContractVO dcVo = new DeliverContractVO();

	Object obj = request.getAttribute("vo");
	String houseId= (String)request.getAttribute("houseId");
	String contractID= (String)request.getAttribute("contractID");
	if (obj != null){
		dcVo = (DeliverContractVO) obj;
	
%>
<body style="margin: auto 10px ">

	<button type="button" class="noprint" onclick="MyPrint()" style="position: absolute;top:20px;left:30px;width: 50px;line-height: 20px;">打印</button>
	<div style="width:100%;text-align: right;">
		<img width="" height="50px" src="<%=request.getContextPath()%>/inner/ContractPdf/getBarCode.action?strInfo=<%=dcVo.getContractID()%>"/>
	</div>
	<div style="width:100%;font-family: '宋体'" >
	<center><p style="font-size: 30px;font-family:  '宋体';text-indent: 0">房 屋 交 接 书</p></center>
	<center>
		<img  width="" height="50px" src="<%=request.getContextPath()%>/inner/ContractPdf/getBarCode.action?strInfo=<%=dcVo.getHouseID()%>"/>
	</center>
	
	<table style="width:100%;">
		<tr><td width="120px">甲方（卖方）：</td><td style="margin-left: 15px;border-bottom: solid 1px;"><%=dcVo.getSeller()%></td></tr>
		<tr><td width="120px">乙方（买方）：</td><td style="margin-left: 15px;border-bottom: solid 1px;"><%=dcVo.getBuyer()%></td></tr>
	</table>
	<p><span class="uSpan"> <%=dcVo.getDeliverYear()%> </span>年<span class="uSpan"> <%=dcVo.getDeliverMonth()%> </span>月<span class="uSpan"> <%=dcVo.getDeliverDay()%> </span>日，甲、乙双方对<span class="uSpan"> <%=dcVo.getLocation()%> </span>室进行交接，双方确认：</p>
	<p>
		1、甲方交付给乙方的房屋为<span class="uSpan"> <%=dcVo.getLocation()%> </span>室(以下简称该房屋)。该房屋的实测建筑面积为
		<span class="uSpan"> <%=StringUtil.convertDouble(dcVo.getN1_7())%> </span>
		平方米（相应占有的土地使用分摊面积为
		<span class="uSpan"> <%=StringUtil.convertDouble(dcVo.getN1_8())%> </span>
		平方米），另有地下附属面积
		<span class="uSpan"> <%=StringUtil.convertDouble(dcVo.getCellarArea())%> </span>
		平方米，实测建筑面积的测绘机构为
		<span class="uSpan"> <%=dcVo.getChargeDep()%> </span>
		认定的
		<span class="uSpan"> <%=dcVo.getN1_9()%> </span>
		新建商品房房地产权证号（权属证明）:
		<span class="uSpan"> <%=dcVo.getN1_10()%> </span> 
		。
	</p>
	<p style="text-indent: 0;margin-left: 2em;">
		2、该房屋的总房价款为人民币
		<span class="uSpan"> <%=StringUtil.convertDouble(dcVo.getN2_1())%> </span>元。
		
		<br/> 
		<span class="uSpan"> (大写)：<%=dcVo.getN2_2()%>。 </span>
		<br/>乙方<span class="uSpan"> <% if(dcVo.getAttribute("paytype_dict_name") != null){
				out.print(dcVo.getAttribute("paytype_dict_name").toString());
			}else{
				out.print(" ");
			}
		%><%=StringUtil.convertDouble(dcVo.getN2_3()) %>元。 </span>
	</p>
	<p style="text-indent: 0;margin-left: 2em;">
		<span class="uSpan"> (大写)：<%=dcVo.getN2_4() %>  。 </span>
		<br/>甲方已开具<span class="uSpan"> <%
				if(dcVo.getAttribute("receipttype_dict_name") != null){
					out.print(dcVo.getAttribute("receipttype_dict_name").toString());
				}else{
					out.print(" ");
				}
				%> </span>给乙方。
		
	<p style="text-indent: 0;margin-left: 2em;">
		3、<br/>
		本交接书由甲乙双方签字生效。
	</p>
		<table style="width: 100%">
			<tr>
				<td height="100px">甲方签字:</td>
				<td>乙方签字:</td>
			</tr>
			<tr>
				<td>日期：&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
				<td>日期：&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
			</tr>
		</table>
	</p>
	</div>
</body>
<%
	}else{
		out.print("未查到相关交接书!");
	}
%>
</html>