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
		
		<script language="vbscript">
			function formatDouble(val,val2)
				formatDouble = replace(formatnumber(val,val2),",","")
			end function
		</script>
		<SCRIPT language="javascript">				
		</SCRIPT>
	</head>
	<body class="openDlg_style">
	<form name="form1" id="form1" method="post" action="">
		<input type="hidden" name="contractID" value="<%=request.getParameter("contractID") %>" />
		<input type="hidden" name="houseId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("houseId"))%>" />
		<input type="hidden" name="houseID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("houseId"))%>" />
		<input type="hidden" name="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startID"))%>">
		<input type="hidden" name="compID" value="${sellerInfoVO.compID}" />
		<input type="hidden" name="type" value="2" />
		<input type="hidden" name="cmdFHE" id="cmdFHE" value="" />
		<center ><b STYLE="font-size:15pt" >青岛市商品房买卖合同（现售）</b></center>
		<center STYLE="font-size:12pt" >（合同号：${contractID }）</center>
		<br><br>
		<table width="95%" align="center">
			<tr>
				<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中 华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商 达成一致意见，签订本商品房买卖合同。</td>
			</tr>
			<tr><td align="center" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;<b STYLE="font-size:12pt" ><br>第一章	合同当事人<br></b></tr>
			<tr><td align="center" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;<b>甲方</b></td></tr>
			<table width="60%" align="center">
			<tr>
				<table width=60% align="center">
				<tr>
				<td width="20%">甲方(出卖人):</td>
				<td width="30%"><input type="text"  size="50"  name="sellerName" maxlength="100" class="input3" readonly value="${sellerInfoVO.sellerName}"></td>			
				</tr>
				</table>
			</tr>
			<tr>
				<table width=60% align="center">
				<tr align="left">
				<td width="20%">住所：</td>
				<td width="30%"><input type="text" name="sellerAddress" size="30" maxlength="100" class="input3" readonly value="${sellerInfoVO.sellerAddress}"></td>
				<td width="20%" >邮编：</td>
				<td width="30%" ><input type="text" name="sellerPostcode" size="30"  maxlength="6" class="input3"  readonly value="${sellerInfoVO.sellerPostcode}"></td>
				</tr>
				</table>
			</tr>
			<tr>
				<table width=60% align="center">
				<tr align="left">
				<td width="20%">营业执照号码：</td>
				<td width="30%"><input type="text" name="sellerBizcardcode" maxlength="50" class="input3" readonly value="${sellerInfoVO.sellerBizcardcode}"></td>
				<td width="20%">资格证书号码：</td>
				<td width="30%"><input type="text" name="sellerCertcode" maxlength="50" class="input3"  readonly value="${sellerInfoVO.sellerCertcode}"></td>
				</tr>
				</table>
			</tr>
			<tr>
				<table width=60% align="center">
				<tr align="left">
				<td width="20%">委托代理人：</td>
				<td width="30%"><input type="text" name="sellerProxy" maxlength="50" class="input2"   value="${sellerInfoVO.sellerProxy}"></td>
				<td width="20%">联系电话：</td>
				<td width="30%"><input type="text" name="sellerProxyCall" maxlength="20" class="input2"   value="${sellerInfoVO.sellerProxyCall}"></td>
				</tr>
				</table>
			</tr>
			</table>
			<tr><td colspan="4"><hr></td></tr>
			<!-- 合同内容 start -->
			<tr><td  width=100% align="center" colspan="4"><b STYLE="font-size:12pt" ><br>第二章  商品房基本状况<br></b></td></tr>
			<tr><td width=100%>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第一条  项目建设依据</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人以
						<input type="text" name="f0101" size="40" maxlength="100" class="input2" value="${csdVo.f0101 }"  features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  土地坐落"><font color="red">*</font>
						方式取得坐落于<input type="text" name="f0102" size="40" maxlength="100" class="input2" value="${csdVo.f0102 }"  features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  土地坐落"><font color="red">*</font>地块的建设用地使用权。
						该地块
						<input type="text" name="f0103" size="40" maxlength="100" class="input2" value="${csdVo.f0103 }"  features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  土地坐落"><font color="red">*</font>		
						为<input type="text" name="f0104" size="30" value="${csdVo.f0104 }" maxlength="100" class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  地块证号"><font color="red">*</font>，
						土地使用权面积为<input type="text" name="f0105" value="${csdVo.f0105 }" size="15" maxlength="50" class="input2" features="bmust=1,datatype=1,maxlength=50,showtitle=第一条  土地面积"><font color="red">*</font>平方米。
						买受人购买的商品房（以下简称该商品房）所占用的土地用途为<input type="text" name="f0106" size="20" value="${csdVo.f0106 }"  maxlength="50" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  土地用途"><font color="red">*</font>，
						土地使用权终止日期为
						<input type="text" class="input2" name="f0107" id="f0107"  value="${csdVo.f0107 }" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  土地使用权终止日期"  />。
						
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.出卖人经批准，在上述地块上建设的商品房项目核准名称为
						<input type="text" name="f0108" size="30" value="${csdVo.f0108 }" maxlength="50" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  项目名称"><font color="red">*</font>，
						建设工程规划许可证号为<input type="text" name="f0109" size="40" value="${csdVo.f0109 }"  maxlength="100" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  建设工程规划许可证号"><font color="red">*</font>，
						建筑工程施工许可证号为<input type="text" name="f0110" size="40"  value="${csdVo.f0110 }" maxlength="100" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  建筑工程施工许可证号"><font color="red">*</font>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二条  销售依据。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房已取得不动产登记证明号为
						<input type="text" name="f0203" size="30" maxlength="50" value="${csdVo.f0203}"  class="input3" readonly features="bmust=1,datatype=0,maxlength=50,showtitle=第二条  不动产登记证明号"><font color="red">*</font>，
						房屋登记机构为
						<input type="text" name="f0205" size="30" maxlength="50" value="${csdVo.f0205}"  class="input3" readonly features="bmust=1,datatype=0,maxlength=50,showtitle=第二条  房屋登记机构"><font color="red">*</font>。
					</td></tr>
				</table>
			</td></tr>
			<!-- 合同附件 end -->

	</table>
			<div align="center">
				<input type="button" onclick="doSubmit();" name="submit" value="提交"/>
				<input type="button" onclick="javascript:{this.disabled=true;gotoSave();}" name="save" value="保存"/>
				<input type="button" name="back" value="返回" onclick="history.go(-1);" />
			</div>
		<input type="hidden" name="fujiancount" value="12">   


			
	</form>
	
	<script type="text/javascript">
			function doSubmit(){
			//var status=$("#status").val();
			document.form1.submit.disabled=true;
			if(confirm("确定要提交吗?")){
				$("#cmdFHE").val('editSubmit');
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/outer/signcontractFHE/signXGCheckFHE.action',  
					data : $("#form1").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=='success'){
						alert("123")
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
		alert("456 cmdFHE="+$("#cmdFHE").val()+" "+$("#form1").serialize())//'editSubmit'
		/*
			if(!CheckDataValid(form1)){
			alert("7")
				document.form1.save.disabled=false;
				return;
			}
			if(!doSelectCheck()){
			alert("8")
				document.form1.save.disabled=false;
				return;
			}
			*/
			alert("910")
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
	
	</script>
	</body>
</html>