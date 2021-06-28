<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.PresellAttach2MoneyVO"%>
<%@page import="java.util.List"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售合同付款项</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
		<style type="text/css">
			.input{width:100%;
					   BORDER-TOP: #000000 0px solid;
					   BORDER-LEFT: #000000 0px solid;
					   BORDER-RIGHT: #000000 0px solid;
					   BORDER-BOTTOM: #000000 1px solid;
					   background-color:#ffffff;
					  }
			.input2{BORDER-TOP: #000000 0px solid;
					    BORDER-LEFT: #000000 0px solid;
					    BORDER-RIGHT: #000000 0px solid;
					    BORDER-BOTTOM: #000000 1px solid;
					    background-color:#ffffff;
					   }
			.input3{BORDER-TOP: #000000 0px solid;
					    BORDER-LEFT: #000000 0px solid;
					    BORDER-RIGHT: #000000 0px solid;
					    BORDER-BOTTOM: #000000 1px solid;
					    background-color:#FFFFCC;
					   }
			td{line-height:200%;font-size:14px;}
		</style>
		<script type="text/javascript">
			function addattach2money(contractID,operatype){
				var payment = $('#paymentMode').val();
				if($('#paymentMode').val()!='银行按揭贷款'){
					$('#borrowMode').val("");
				}
				//selectBorrowMode(payment);
				var totalMoney = $('#totalMoney').val();
				if(totalMoney!=0){
					var url = "<%=basePath%>/outer/signcontract/operaAttach2Money.action?"+$('#attach4').serialize();
					parent.openDialog("付款项",url,700,500);
				}else{
					alert("总价不能为0，请先保存总价！");
				}
			}
			
			function delattachmoney(id,contractID,serial){
				if(contractID!=null&&contractID!=''&&serial!=null&&serial!=''){
					if(confirm('确认删除付款项'+serial+'吗？')){
						$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : '<%=basePath%>/outer/signcontract/delattachmoney.action',  
							data : {ID:id,contractID:contractID,stateSign:serial},
							dataType : "json",
							success : function(data){
								if(data[0].result=='success'){
									alert(data[0].msg);
									parent.doUpdate('附件四编辑');
									parent.doBack();
								}else{
									alert(data[0].msg);
								}
							},
							error : function(){
								alert("删除乙方出错！");
							}
						});
					}
				}
			}

			$(function(){
				$('#paymentMode').val("${paVo.paymentMode}");
				$('#borrowMode').val("${paVo.borrowMode}");
			});

			function selectBorrowMode(payment){
				if(payment=="银行按揭贷款"){
					$('#borrowMode').val("银行按揭贷款");
				}else{
					$('#borrowMode').val("");
				}
			}
		</script>
	</head>
	<body>
		<form id="attach4" name="attach4" method="post" action="">
		<input type="hidden" id="contractID" name="contractID" value="<%=request.getParameter("contractID") %>" />
		<table cellspacing=16 cellpadding=0 border=0 align="center" width="95%">
			<tr>
				<td style="text-align:center">
					<b><font size=5>附&nbsp; 件&nbsp; 四</font></b>
				</td>
			</tr>
			<tr>
				<td>
					付款金额：<input type="text" id="totalMoney" name="totalMoney" value="${paVo.totalMoneyStr}" class="input3" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>
					付款方式：
					<select id="paymentMode" name="paymentMode" style="width:120px;" onchange="selectBorrowMode(this.value)">
						<option value="一次性付款">一次性付款</option>
						<option value="分期付款">分期付款</option>
						<option value="银行按揭贷款">银行按揭贷款</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					贷款方式：
					<select id="borrowMode" name="borrowMode" style="width:120px;">
						<option value=""></option>
						<option value="银行按揭贷款">银行按揭贷款</option>
						<option value="住房公积金贷款">住房公积金贷款</option>
						<option value="公积金组合贷款">公积金组合贷款</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="addam" onclick="addattach2money('<%=request.getParameter("contractID") %>','add')" value="新增"/>
				</td>
			</tr>
			<% 
				List<PresellAttach2MoneyVO> palist = (List<PresellAttach2MoneyVO>)request.getAttribute("palist");
				if(palist!=null&&!palist.isEmpty()){
					for(int i=0;i<palist.size();i++){
						PresellAttach2MoneyVO pa = palist.get(i);
			%>
			<tr>
				<td>
					<%=i+1 %>、
					<% if(pa.getState()==4){ %>
					贷款申请时间：
					<% }else{ %>
					付款时间:
					<%} %>
					<input type="text" value="<%=pa.getFkDate()==0?"":pa.getFkDate() %>" class="input3" readonly="readonly">
					&nbsp;&nbsp;
					
					<% if(pa.getState()==4){ %>
					贷款金额：
					<% }else{ %>
					付款金额：
					<%} %>
					<input type="text" value="<%=pa.getMoney()==0?"":pa.getMoneyStr() %>" class="input3" readonly="readonly">
					&nbsp;&nbsp;
					款项：
					<% if(pa.getState()==1||pa.getState()==2||pa.getState()==3){ %>
					<input type="text" value="房款" class="input3" readonly="readonly" style="width:50px">
					<% }else if(pa.getState()==4){ %>
					<input type="text" value="贷款" class="input3" readonly="readonly" style="width:50px">
					<% }else if(pa.getState()==9){ %>
					<input type="text" value="定金" class="input3" readonly="readonly" style="width:50px">
					<% }else{%>
					<input type="text" value="" class="input3" readonly="readonly" style="width:50px">
					<% } %>
					&nbsp;&nbsp;
					<% if(pa.getState()==4){ %>
					贷款机构：<input type="text" value="<%=pa.getBankName()==null?"":pa.getBankName() %>" class="input3" readonly="readonly">
					&nbsp;&nbsp;
					放款时间：<input type="text" value="<%=pa.getDkDate()==null?"":pa.getDkDate() %>" class="input3" readonly="readonly">
					&nbsp;&nbsp;
					<% } %>
					<% if((i+1)==palist.size()){ %>
					<input type="button" name="delam" onclick="delattachmoney('<%=pa.getAttach2MoneyID() %>','<%=pa.getContractID() %>','<%=pa.getStateSign() %>')" value="删除"/>
					<%} %>
					<br/>
				</td>
			</tr>
			<%	
					}
				}
			%>
		</table>
		</form>
	</body>
</html>