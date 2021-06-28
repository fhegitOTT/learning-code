<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@page import="com.netcom.nkestate.common.Constant"%>
<%
	String basePath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>认购协议</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" />
		<link href="<%=basePath%>/css/laydate.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<style type="text/css">
			.input {
				width: 100%;
				BORDER-TOP: #000000 0px solid;
				BORDER-LEFT: #000000 0px solid;
				BORDER-RIGHT: #000000 0px solid;
				BORDER-BOTTOM: #000000 1px solid;
				background-color: #ffffff;
			}
			
			.input2 {
				BORDER-TOP: #000000 0px solid;
				BORDER-LEFT: #000000 0px solid;
				BORDER-RIGHT: #000000 0px solid;
				BORDER-BOTTOM: #000000 1px solid;
				background-color: #ffffff;
			}
			
			.input3 {
				BORDER-TOP: #000000 0px solid;
				BORDER-LEFT: #000000 0px solid;
				BORDER-RIGHT: #000000 0px solid;
				BORDER-BOTTOM: #000000 1px solid;
				background-color: #FFFFCC;
			}
			
			td {
				line-height: 200%;
				font-size: 14px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				addCT_563();
			});

			function addCT_563(){
				var proDis = <%=request.getAttribute("proDis")%>;
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_311',
					dataType : "json",
					success : function(data){
						if(data.length>0){
							$.each(data,function (index,option){
								if(option.code==proDis){
									$('#CT_563').text(option.name);
								}
							});
						}
					},
					error : function(){
						alert("查询房屋土地管理局名出错！");
					}
				});
			}
			
			function doSave(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/outer/signcontract/saveEarnestContract.action",
					data : $("#form1").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							parent.parent.doBack();
						}else{
							alert(data[0].message);
						}
						document.form1.save.disabled=false;
					},
					error : function(){
						alert("检查失败！");
					}
				});	
			}
		
			function doSubmit(){
				if(!doCheck()){
					return;
				}
				document.form1.submit.disabled=true;
				$("#cmd").val('submit');
				doSave();
				$("#cmd").val('');
				document.form1.submit.disabled=false;
			}
			
			function doCheck(){
				pass=true;
				//必填项
				$(".mustadd").each(function(){
					if($(this).val() == ''){
					  alert("该项不可为空！");
					  $(this).focus();
					  pass=false;
					  return pass;
					}
				})
				//建筑面积验证
				var a=$("input[name='N1_11']").val();
				var parnt = /^[0-9]\d*(\.\d+)?$/;
				var b=$("input[name='cellarArea']").val();
				if(a==''&& b==''){
					alert("建筑面积和地下附属面积不能同时为空！");
					  pass=false;
					  return pass;
				}	
		        if(!parnt.test(b) && b!=''){
		            alert("面积必须是大于0的数字!");
		            $("#cellarArea").focus();
		            pass=false;
		            return pass;    
		        }
		        //身份证验证
		        var c=$("#buyerCardname").val();
		        var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		        if(c=='1'){
		        	var d=$("input[name='buyerCardcode']").val();
		        	if(!pattern.test(d) && d!=''){
		        		alert("身份证号码不符合要求！");
			            $("#buyerCardcode").focus();
			            pass=false;
			            return pass;   		        		
		        	}
		        }
		        return pass; 
		       
			}
			
			//国籍检查
			function doSelect(obj){
				var a=$("#buyerNationality").val();
		        if(a!='1'){
		        	$("#buyerProvince").val('');
		        }if(a=='1'){
		        	$("#buyerProvince").val('-1');
		        }
			}
			
			function doChange(val){
				$("#N1_8").empty();
				if(val=='0'){
					$("#N1_8").prepend("<option value=0 selected=selected>商品房预售许可证</option>");
					$("#N1_9").val('${permitNo}');
					$("#N1_11").val('${planarea}');
					$("input[name='conType']").each(function(){ 
      		 			this.value='预售';
  					})
				}if(val=='1'){
					$("#N1_8").prepend("<option value=1 selected=selected>权属登记登记证明</option>");
					$("#N1_9").val('${realno}');
					$("#N1_11").val('${area}');
					$("input[name='conType']").each(function(){ 
      		 			this.value='出售';
  					})
				}
			}
			
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#openDL").show().dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true
			});
			$("#openDL").dialog('open');
			$("#openDL").dialog('center');
			$('#openDL').dialog('refresh', url);
		
		}
		
		//将阿拉伯数字转为大写中文汉字
   	 	function convertCurrency(money) {
			 //汉字的数字
			 var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
			 //基本单位
			 var cnIntRadice = new Array('', '拾', '佰', '仟');
			 //对应整数部分扩展单位
			 var cnIntUnits = new Array('', '万', '亿', '兆');
			 //对应小数部分单位
			 var cnDecUnits = new Array('角', '分', '毫', '厘');
			 //整数金额时后面跟的字符
			 var cnInteger = '整';
			 //整型完以后的单位
			 var cnIntLast = '元';
			 //最大处理的数字
			 var maxNum = 999999999999999.9999;
			 //金额整数部分
			 var integerNum;
			 //金额小数部分
			 var decimalNum;
			 //输出的中文金额字符串
			 var chineseStr = '';
			 //分离金额后用的数组，预定义
			 var parts;
			 if (money == '') { return ''; }
			 money = parseFloat(money);
			 if (money >= maxNum) {
			   //超出最大处理数字
			   return '';
			 }
			 if (money == 0) {
			   chineseStr = cnNums[0] + cnIntLast + cnInteger;
			   return chineseStr;
			 }
			 //转换为字符串
			 money = money.toString();
			 if (money.indexOf('.') == -1) {
			   integerNum = money;
			   decimalNum = '';
			 } else {
			   parts = money.split('.');
			   integerNum = parts[0];
			   decimalNum = parts[1].substr(0, 4);
			 }
			 //获取整型部分转换
			 if (parseInt(integerNum, 10) > 0) {
			   var zeroCount = 0;
			   var IntLen = integerNum.length;
			   for (var i = 0; i < IntLen; i++) {
			     var n = integerNum.substr(i, 1);
			     var p = IntLen - i - 1;
			     var q = p / 4;
			     var m = p % 4;
			     if (n == '0') {
			       zeroCount++;
			     } else {
			       if (zeroCount > 0) {
			         chineseStr += cnNums[0];
			       }
			       //归零
			       zeroCount = 0;
			       chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
			     }
			     if (m == 0 && zeroCount < 4) {
			       chineseStr += cnIntUnits[q];
			     }
			   }
			   chineseStr += cnIntLast;
			 }
			 //小数部分
			 if (decimalNum != '') {
			   var decLen = decimalNum.length;
			   for (var i = 0; i < decLen; i++) {
			     var n = decimalNum.substr(i, 1);
			     if (n != '0') {
			       chineseStr += cnNums[Number(n)] + cnDecUnits[i];
			     }
			   }
			 }
			 if (chineseStr == '') {
			   chineseStr += cnNums[0] + cnIntLast + cnInteger;
			 } else if (decimalNum == '') {
			   chineseStr += cnInteger;
			 }
			 return chineseStr;
		}
		
   		//金额只能数字和带2位小数的数字
		function clearNoNum(obj){  
		    	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
		        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
		        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
		        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
		        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  
		
		}

		function LM_isInteger(inputString){
			var c;
			for(var i=0;i<inputString.length;i++)
			{
				c=inputString.charAt(i);
				if(c < "0" || c > "9")
					return(false);
			}
			return(true);
		}

		function getDate(dateVal) {
			if (dateVal != "") {
				var now,nowTime,afterTime,afterDate,yyyy,mm,dd,mmStr,ddStr;
				now=new Date();
				nowTime=now.getTime();
				afterTime=nowTime+(eval(dateVal))*24*60*60*1000;
				afterDate=new Date(afterTime);
				yyyy=afterDate.getFullYear();
				mm=afterDate.getMonth()+1;
				mmStr=mm.toString();
				if(mmStr.length==1){
					mmStr="0"+mmStr;
				}
				dd=afterDate.getDate();
				ddStr=dd.toString();
				if(ddStr.length==1){
					ddStr="0"+ddStr;
				}
				return yyyy+mmStr+ddStr;
			}
			return "";
		}
		
		function setN4_2(){
			var day = $('#N4_1').val();
			if (LM_isInteger(day))
				var N4_2 = getDate(day);
			else 
			{ 
				alert("请输入正确的数字!");
				$('#N4_1').val("");
				$('#N4_2').val("");
				return;
			}
			$('#N4_2').val(N4_2);
		}

		$(function(){
			var type = '<%=request.getParameter("ctype")%>';
			if(type == '1'){
				doChange('0');
			}
			if(type == '2'){
				doChange('1');
			}
		});
		</script>
	</head>
	<body>
		<div align=center>
			<form id="form1" name="form1" method="post" action="<%=basePath%>/outer/signContractmanage/saveEarnestContract.action">
				<input type="hidden" name="contractID" value="<%=request.getParameter("contractID")%>" />
				<input type="hidden" name="startID" value="${startID }" />
				<input type="hidden" name="type" value="3" />
				<input type="hidden" name="landUse" value="${landUse }" />
				<center>
					<b id="cont" style="font-size: 15pt">认购协议</b>
				</center>
				<center style="font-size: 12pt">
					<b> （供商品房预订时使用） </b>
					<br/>
					<b id="cont"> 合同号：<%=request.getParameter("contractID")%> </b>
				</center>
				<table width="95%" align="center">
					<tr>
						<td class="seller" align="center">
							<table cellspacing=16 cellpadding=0 border=0 style="text-align: left;">
								<tr>
									<td>
										<table width=100%>
											<tr>
												<td width=100>
													甲方(出卖人):
												</td>
												<td>
													<input type="text" size="50" maxlength="100" class="input3" name="sellerName" readonly="readonly" value="${sellerInfoVO.sellerName }" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width=100%>
											<tr>
												<td width=100>
													住所：
												</td>
												<td>
													<input type="text" size="30" maxlength="100" class="input3" readonly="readonly" name="sellerAddress" value="${sellerInfoVO.sellerAddress}" />
												</td>
												<td width=100>
													邮编：
												</td>
												<td>
													<input type="text" size="30" maxlength="6" class="input3" name="sellerPostcode" value="${sellerInfoVO.sellerPostcode}" readonly="readonly" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width=100%>
											<tr>
												<td width=100>
													营业执照号码：
												</td>
												<td>
													<input type="text" maxlength="50" class="input3" readonly="readonly" value="${sellerInfoVO.sellerBizcardcode}"/>
												</td>
												<td width=100>
													资格证书号码：
												</td>
												<td>
													<input type="text" name="sellerCertcode" maxlength="50" class="input3" readonly="readonly" value="${sellerInfoVO.sellerCertcode}"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width=100%>
											<tr>
												<td width=100>
													法定代表人：
												</td>
												<td>
													<input readonly="readonly" type="text" name="sellerDelegate" maxlength="100" class="input3" value="${sellerInfoVO.sellerDelegate}"/>
												</td>
												<td width=100>
													联系电话：
												</td>
												<td>
													<input type="text" name="sellerDlgCall" maxlength="20" class="input2" value="${sellerInfoVO.sellerDlgCall}"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width=100%>
											<tr>
												<td width=100>
													委托代理人：
												</td>
												<td>
													<input type="text" name="sellerProxy" maxlength="50" class="input2" value="${sellerInfoVO.sellerProxy}"/>
												</td>
												<td width=100>
													联系电话：
												</td>
												<td>
													<input type="text" name="sellerProxyCall" maxlength="20" class="input2" value="${sellerInfoVO.sellerProxyCall}"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										甲、乙双方遵循自愿、公平和诚实信用的原则、经协商一致，就乙方向甲方预订《
										<input readonly="readonly" type="text" id="projectName" name="projectName" style="width: 100px" maxlength="80" class="input3" value="${projectName}" />
										》商品房事宜，订立本合同。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<b><font face=黑体>第一条</font>
										</b> 乙方预定
										<input value="${chvo.roadName}" class="input2" name="N1_1" type="text" id="N1_1" style="width: 150px" maxlength="60" />
										路
										<input value="${chvo.laneName }" class="input2" name="N1_2" type="text" id="N1_2" style="width: 150px" maxlength="80" />
										号《
										<input readonly="readonly" name="project_Name" type="text" id="project_Name" style="width: 100px" maxlength="80" class="input3" value="${projectName }" />
										》
										<input readonly="readonly" name="N1_4" type="text" id="N1_4" style="width: 100px" class="input3" value="${chvo.subLane }" />
										幢
										<input readonly="readonly" name="N1_6" type="text" id="N1_6" style="width: 100px" class="input3" value="${chvo.streetNumber }" />
										单元
										<input readonly="readonly" name="N1_7" type="text" id="N1_7" style="width: 100px" class="input3" value="${roomNumber }" />
										室(以下简称该房屋)。甲方已领取该房屋
										<select name="N1_8" id="N1_8"></select>
										（证书号：
										<input readonly="readonly" class="input3" name="N1_9" type="text" id="N1_9" style="width: 330px" maxlength="10" value="${permitNo }" />
										），并经
										<input name="N1_10" type="text" id="N1_10" style="width: 200px" maxlength="40" class="input2" value="" />
										测绘机构测绘，该房屋建筑面积为
										<input readonly="readonly" name="N1_11" type="text" id="N1_11" style="width: 100px" maxlength="16" class="input3" value="${area }" />
										平方米，另有地下附属面积
										<input name="cellarArea" type="text" id="cellarArea" size="14" maxlength="16" class="input2" value="" />
										平方米。该房屋定于
										<input type="text" class="input2" name="N1_12" id="N1_12" value="" />
										<script type="text/javascript" src="<%=basePath%>/js/laydate.js"></script>
										<script>
											//执行一个laydate实例
											laydate.render({
											  elem: '#N1_12' //指定元素
											});
										</script>
										交付。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face=黑体><b>第二条</b></font> 乙方预订的该房屋每平方米建筑面积买卖单价为人民币
										<input value="" class="input2" name="N2_1" type="text" id="N2_1" size="68" maxlength="16" onkeyup="clearNoNum(this);form1.N2_2.value=convertCurrency(form1.N2_1.value);" />
										元。 (大写)：
										<input readonly="readonly" class="input3" value="" name="N2_2" type="text" id="N2_2" size="46" />
										。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										乙方预订的该房屋总房价为人民币
										<input value="" class="input2" name="N2_3" type="text" id="N2_3" style="width: 300px" maxlength="16" onkeyup="clearNoNum(this);form1.N2_4.value=convertCurrency(form1.N2_3.value);" />
										元。 (大写)：
										<input readonly="readonly" class="input3" value="" name="N2_4" type="text" id="N2_4" size="68" />
										。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										乙方采取
										<select name="N2_5" id="N2_5" class="mustadd">
											<option value="0">一次性付款</option>
											<option value="1">分期付款</option>
											<option value="2">抵押贷款付款</option>
										</select>
										方式。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face=黑体><b>第三条</b></font> 乙方同意签订本合同时，支付定金人民币
										<input class="input2" value="" name="N3_1" type="text" id="N3_1" style="width: 200px" maxlength="16" onkeyup="clearNoNum(this);form1.N3_2.value=convertCurrency(form1.N3_1.value);" />
										元， (大写)：
										<input readonly="readonly" class="input3" value="" name="N3_2" type="text" id="N3_2" size="68" maxlength="16" />
										，作为甲、乙方双方当事人订立商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的担保，签订商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同后，乙方支付的定金转为房价款。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face=黑体><b>第四条 </b>
										</font> 甲、乙双方商定，预订期为
										<input value="" class="input2" name="N4_1" type="text" id="N4_1" size="10" maxlength="2" onkeyup="setN4_2()"/>
										天，乙方于
										<input type="text" class="input2" name="N4_2" id="N4_2" value="" readonly="readonly" />
										前到
										<input class="input2" value="" name="N4_3" type="text" id="N4_3" size="20" maxlength="60" />
										与甲方签订《
										<span id="CT_563">青岛市</span>商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同》。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face=黑体><b>第五条</b>
										</font> 甲方同意将发布或提供的广告、售楼书、样品所标明的房屋平面布局、结构、建筑质量、装饰标准及附属设施、配套设施等状况作为商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的附件。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<b>第六条</b> 在本合同的第四条约定的预订期限内，除本合同第七条、第八条约定的情形外，甲方拒绝签订商品房
										<input readonly="readonly" value="预售" class="input3" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的，双倍返还已收取的定金；乙方拒绝签订商品房
										<input readonly="readonly" value="预售" class="input3" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的，无权要求甲方返还已收取的定金。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face="黑体"><b>第七条</b>
										</font> 有下列情况之一，乙方拒绝签订商品房
										<input readonly="readonly" value="预售" class="input3" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的，甲方应全额返还乙方已支付的定金。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										1、甲乙双方在签订商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同时，因面积误差处理、房屋交付、房屋质量、 违约责任、争议解决方式等条款，存在分歧，不能协商一致；
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										2、甲乙双方签订本合同后、签订商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同前，由司法机关、行政机关依法限制该房屋房地产权利的。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<b><font face=黑体>第八条</font>
										</b> 有下列情况之一，乙方拒绝签订商品房
										<input readonly="readonly" class="input3" value="预售" name="conType" type="text" id="conType" size="2" maxlength="10" />
										合同的，甲方应双倍返还乙方已支付的定金：
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										1、甲方未遵守本合同第二条、第五条约定的；
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										2、甲方未告知乙方在签订本合同前该房屋已存在的抵押、预租、查封等事实的。
									</td>
								</tr>
								<tr>
									<td class="tiaokuan">
										<font face=黑体><b>第九条</b>
										</font> 本协议一式
										<input class="input2" value="" name="N9_1" type="text" id="N9_1" size="5" maxlength="4" />
										份，甲、乙双方各执
										<input class="input2" value="" name="N9_2" type="text" id="N9_2" size="6" maxlength="4" />
										份，
										<input class="input2" value="" name="N9_3" type="text" id="N9_3" size="20" maxlength="40" />
										、
										<input class="input2" value="" name="N9_4" type="text" id="N9_4" size="20" maxlength="40" />
										各执一份。
									</td>
								</tr>
								<tr class="jiange">
									<td>
										&nbsp;
									</td>
								</tr>
								<tr class="jiange">
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td align="center">
										<table cellspacing=0 cellpadding=0 width="80%" border=0>
											<tr style="height: 50px">
												<td width="50%">
													甲方：（签章）
												</td>
												<td width="50%">
													乙方：（签定）
												</td>
											</tr>
											<tr style="height: 50px">
												<td width="50%">
													&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
												<td width="50%">
													&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</td>
											</tr>
											<tr style="height: 50px">
												<td width="50%">
													甲方拟签人员：
												</td>
												<td width="50%">
													销售员证书号：
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<input type="hidden" name="cmd" id="cmd" value="" />
							<div align="center">
								<p style="TEXT-INDENT: 0px; LINE-HEIGHT: 200%" align=center>
									<input type="button" name="save" value="保存" onclick="javascript:{this.disabled=true;doSave();}" />
									<input type="button" name="submit" value="提交" onclick="doSubmit()" />
									<input type="button" name="back" value="返回" onclick="history.go(-1);" />
									<input type="button" name="close" value="关闭" onclick=" if(confirm('将关闭此窗体，未保存的信息将丢失，是否关闭？')) parent.parent.doBack();" />
									<br />
								</p>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="openDL"></div>
	</body>
</html>
