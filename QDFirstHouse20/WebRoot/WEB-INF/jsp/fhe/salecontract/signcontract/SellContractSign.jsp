<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.*" %> 

<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
<html>
	<head>
		<title>青岛市商品房买卖合同（出售）</title>
		<link href="<%=basePath%>/css/laydate.css" rel="stylesheet" type="text/css" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet">
	    <script language="javascript" src="../../js/calendar.js"></script>
	    <script language="javascript" src="../../js/Common.js"></script>
	    <script language="vbscript" src="../../js/validdata.vbs"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/laydate.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/qdvaliddata/validdata.js"></script>
		<style type="text/css">
		
		</style>
	</head>
	<body style="background-color:#EEEEBB">
		<form name="form1" id="form1" method="post" action="">
			<input type="hidden" name="contractID" value="<%=request.getParameter("contractID") %>" />
			<input type="hidden" name="houseID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("houseID"))%>" />
			<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startID"))%>">
			<input type="hidden" name="cmdFHE" id="cmdFHE" value="" />
			<br><br>
			<center ><b STYLE="font-size:15pt" >青岛市商品房买卖合同（现售）</b></center>
			<center STYLE="font-size:12pt" >（合同号：${contractID }）</center>
			<br><br>
			<table width="95%" align="center">
				<tr >
					<td colspan="4" style="align:center;border:1px solid #33bb66;font-size:20px;">&nbsp;&nbsp;&nbsp;&nbsp;出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中 华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商 达成一致意见，签订本商品房买卖合同。</td>
				</tr>
				<tr style="align:center;">
					<table style="align:center;width:100%;background-color:#DDFFBB;">
						<caption align="top" style="background-color:#DDFFBB">甲方信息</caption>
						<tr><td width="20%" >甲方(出卖人)</td>
							<td width="30%"><input type="text"  size="50"  name="sellerName" maxlength="100" class="input3" readonly value="${sellerInfoVO.sellerName}"></td>			
						</tr>
						<tr>
							<td width="20%">住所：</td>
							<td width="30%"><input type="text" name="sellerAddress" size="30" maxlength="100" class="input3" readonly value="${sellerInfoVO.sellerAddress}"></td>
							<td width="20%" >邮编：</td>
							<td width="30%" ><input type="text" name="sellerPostcode" size="30"  maxlength="6" class="input3"  readonly value="${sellerInfoVO.sellerPostcode}"></td>	
						</tr>
						<tr align="left">
							<td width="20%">营业执照号码：</td>
							<td width="30%"><input type="text" name="sellerBizcardcode" maxlength="50" class="input3" readonly value="${sellerInfoVO.sellerBizcardcode}"></td>
							<td width="20%">资格证书号码：</td>
							<td width="30%"><input type="text" name="sellerCertcode" maxlength="50" class="input3"  readonly value="${sellerInfoVO.sellerCertcode}"></td>
						</tr>
						<tr>
							<td width="20%">委托代理人：</td>
							<td width="30%"><input type="text" name="sellerProxy" maxlength="50" class="input2"   value="${sellerInfoVO.sellerProxy}"></td>
							<td width="20%">联系电话：</td>
							<td width="30%"><input type="text" name="sellerProxyCall" maxlength="20" class="input2"   value="${sellerInfoVO.sellerProxyCall}"></td>		
							<hr>
						</tr>
					</table>
				</tr>
				<tr style="align:center;">
					<table style="align:center;width:100%;background-color:#DDFFBB">
						<caption align="top" style="background-color:#DDFFBB">第一条  项目建设依据</caption>
						<tr>
							<td width="20%">土地坐落:</td>
							<td width="30%"><input type="text"  size="50"  name="xx0101" maxlength="100" class="input3"  value="${cdVO.xx0101}"></td>			
						</tr>
						<tr>
							<td width="20%">土地使用权面积：</td>
							<td width="30%"><input type="text" name="xx0102" size="30" maxlength="100" class="input3"  value="${cdVO.xx0102}"></td>
							<td width="20%">土地用途：</td>
							<td width="30%"><input type="text" name="xx0103" size="30"  maxlength="6" class="input3"   value="${cdVO.xx0103}"></td>	
						</tr>
						<tr align="left">
							<td width="20%">土地使用权终止日期：</td>
							<td width="30%"><input type="text" name="xx0104" maxlength="50" class="input3"  value="${cdVO.xx0104}"></td>
							<td width="20%">用地使用权获取方式：</td>
							<td width="30%"><input type="text" name="xx0105" maxlength="50" class="input3"   value="${cdVO.xx0105}"></td>
							<hr>
						</tr>
					</table>
				</tr>
				<tr style="align:center;">
					<table style="align:center;width:100%;background-color:#DDFFBB">
						<caption align="top" style="background-color:#DDFFBB">第二条  销售依据</caption>
						<tr>
							<td width="20%">不动产登记证明号:</td>
							<td width="30%"><input type="text"  size="50"  name="xx0201" maxlength="100" class="input3" value="${cdVO.xx0201}"></td>			
						</tr>
						<tr>
							<td width="20%">房屋登记机构：</td>
							<td width="30%"><input type="text" name="xx0202" size="30" maxlength="100" class="input3"  value="${cdVO.xx0202}"></td>							</tr>
							<hr>
						</tr>
					</table>
				</tr> 
				<tr style="align:center;">
					<table style="align:center;width:100%;background-color:#DDFFBB">
						<caption align="top" style="background-color:#DDFFBB">第三条  商品房基本情况</caption>
						<tr>
							<td width="20%">商品房的规划用途:</td>
							<td width="30%"><input type="text"  size="50"  name="xx0301" maxlength="100" class="input3" value="${cdVO.xx0301}"></td>			
							<td width="20%">商品房所在建筑物的主体结构:</td>
							<td width="30%"><input type="text"  size="50"  name="xx0302" maxlength="100" class="input3" value="${cdVO.xx0302}"></td>			
						</tr>
						<tr>
							<td width="20%">房产测绘机构：</td>
							<td width="30%"><input type="text" name="xx0303" size="30" maxlength="100" class="input3"  value="${cdVO.xx0303}"></td>
							<td width="20%" >实测建筑面积：</td>
							<td width="30%" ><input type="text" name="xx0304" size="30"  maxlength="6" class="input3"   value="${cdVO.xx0304}"></td>	
						</tr>
						<tr align="left">
							<td width="20%">套内建筑面积：</td>
							<td width="30%"><input type="text" name="xx0305" maxlength="50" class="input3"  value="${cdVO.xx0305}"></td>
							<td width="20%">共有建筑面积：</td>
							<td width="30%"><input type="text" name="xx0306" maxlength="50" class="input3"   value="${cdVO.xx0306}"></td>
							<hr>
						</tr>
					</table> 
				</tr>
			</table>
			<div align="center">
				<hr/>
				<br/>
				<input type="button" onclick="doSubmit();" name="submit" value="草签"/>&nbsp;&nbsp;
				<input type="button" onclick="javascript:{this.disabled=true;gotoSave();}" name="save" value="确认签约"/>&nbsp;&nbsp;
				<%  
				String showFlag=(String)request.getParameter("showFlag");
				if (showFlag.equals("1")){  
				%>
				<input id="goback" type="button" name="back" value="返回" onclick="goback();" />
				<%}
				%>
				<br/>
			</div>
		</form>
		<script type="text/javascript">
			function goback(){
				parent.layer.closeAll();
			}
			function uploadFile(){
				//var status=$("#status").val();
				document.form1.uploadFile.disabled=true;
				if(confirm("确定要提交草签吗?")){
					$("#cmdFHE").val('editSubmit');
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontractFHE/signXGCheckFHE.action',  
						data : $("#form1").serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								doSave();
							}else{
								alert(data[0].msg);
								return false;
							}
						},
						error : function(){
							alert("合同提交出错！");
						}
					});
			}
			document.form1.submit.disabled=false;
		}
		
		
			function doSubmit(){
				//var status=$("#status").val();
				document.form1.submit.disabled=true;
				if(confirm("确定要提交草签吗?")){
					$("#cmdFHE").val('editSubmit');
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontractFHE/signXGCheckFHE.action',  
						data : $("#form1").serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								doSave();
							}else{
								alert(data[0].msg);
								return false;
							}
						},
						error : function(){
							alert("合同提交出错！");
						}
					});
			}
			document.form1.submit.disabled=false;
		}
		
		function doSave(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontractFHE/saveSellContractFHE.action',  
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						alert(data[0].msg);
						parent.$('#isAdd').val('yes');
						//parent.parent.doBack();
					}else{
						alert(data[0].msg);
					}
					document.form1.save.disabled=false;
				},
				error : function(){
					alert("保存出错！");
				}
			});
		}
		
		function gotoSave(){
			document.form1.save.disabled=true;
			if(confirm("确定要签约吗?")){
				$("#cmdFHE").val('waitingSubmit');
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/outer/signcontractFHE/signXGCheckFHE.action',  
					data : $("#form1").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=='success'){
							doSave();
						}else{
							alert(data[0].msg);
							return false;
						}
					},
					error : function(){
						alert("合同提交出错！");
					}
				});
			}
			document.form1.submit.disabled=false;
		}
	</script>
	</body>
	</html>