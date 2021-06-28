<%@ page contentType = "text/html; charset=UTF-8" %>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>预售合同付款项编辑</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/RMBUtil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<link href="<%=basePath%>/css/laydate.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/js/laydate.js"></script>	
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
			function doSave(){
				if(checkData()){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/outer/signcontract/saveAttach.action',  
					data : $('#attach4').serialize(),
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
						alert("保存附件四出错！");
					}
				});
				}
			}

			function changestate(state){
				if(state==4){
					$('#bankName').removeAttr("disabled");
					$('#dkDate').removeAttr("disabled","");
					$('#bankName').css("background-color","#FFFFFF");
					$('#dkDate').css("background-color","#FFFFFF");
				}else{
					$('#bankName').attr("disabled","disabled");
					$('#dkDate').attr("disabled","disabled");
					$('#bankName').val("");
					$('#dkDate').val("");
					$('#bankName').css("background-color","#FFFFCC");
					$('#dkDate').css("background-color","#FFFFCC");
				}
			}

			function checkData(){
				if($('#paymentMode').val()==""){
					alert("付款方式不能为空，请重新选择！");
					return false;
				}else if($('#fkDate').val()==""){
						alert("付款时间/贷款申请时间不能为空，请重新选择！");
						$('#fkDate').focus();
						return false;
				}else if($('#money').val()==""){
						alert("付款金额不能为空，请重新选择！");
						$('#money').focus();
						return false;
				}else if($('#state').val()==4){
						if($('#bankName').val()==""){
							alert("贷款机构不能为空，请重新选择！");
							$('#bankName').focus();
							return false;
						}else if($('#dkDate').val()==""){
							alert("放款时间不能为空，请重新选择！");
							$('#dkDate').focus();
							return false;
						}else{
							return true;
						}
				}else{
					return true;
				}
			}
		</script>
	</head>
	<body>
		<form id="attach4" name="attach4" method="post" action="">
			<input type="hidden" id="contractID" name="contractID" value="${pa.contractID }"/>
			<input type="hidden" id="stateSign" name="stateSign" value="${pa.stateSign }"/>
			<input type="hidden" id="totalMoney" name="totalMoney" value="${pa.totalMoney }"/>
			<table  cellspacing=16 cellpadding=0 border=0 align="center">
				<tr>
					<td>
						付款方式：<input type="text" id="paymentMode" name="paymentMode" value="${pa.paymentMode}" class="input3" readonly="readonly"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						贷款方式：<input type="text" name="borrowMode" value="${pa.borrowMode}" class="input3" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>
						付款时间/贷款申请时间：<input type="text" name="fkDate" id="fkDate" value="" class="input2"/>
						<script type="text/javascript">
							//执行一个laydate实例
							laydate.render({
							  elem: '#fkDate' //指定元素
							  ,format: 'yyyyMMdd'
							});
						</script>
						付款金额：<input type="text" name="money" id="money" value="" class="input2"/>
						款项：
						<select id="state" name="state" style="width:50px;" onchange="changestate(this.value);">
							<!-- <option value="9">定金</option> -->
							<c:if test="${pa.paymentMode eq '一次性付款'}"><option value="1">房款</option></c:if>
							<c:if test="${pa.paymentMode eq '分期付款'}"><option value="2">房款</option></c:if>
							<c:if test="${pa.paymentMode eq '银行按揭贷款'}"><option value="3">房款</option></c:if>
							<c:if test="${pa.paymentMode eq '银行按揭贷款'}"><option value="4">贷款</option></c:if>
						</select>
						贷款机构：<input type="text" name="bankName" id="bankName" value="" class="input3" disabled/>
						放款时间：<input type="text" name="dkDate" id="dkDate" class="input3" disabled/>
						<script type="text/javascript">
							//执行一个laydate实例
							laydate.render({
							  elem: '#dkDate' //指定元素
							  ,format: 'yyyyMMdd'
							});
						</script>
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