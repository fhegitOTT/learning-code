<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
%>
<head>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<title>房屋交接书申请</title>
	<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>

	<script type="text/javascript">
	function doCheckSellerPwd(){
		if(!$("#frmInfo1").form("validate")){
	 			return;
	 	}
		var contractID=$("input[name='contractID']").val();
		var url="<%=basePath%>/outer/manage/deliverSellerPwdConfirm.action?contractID="+contractID;
		window.openDialog("合同甲方密码确认",url,400,300);
	}
	
	
 	function doRegist(){
 		var contractID=$("input[name='contractID']").val();
 		$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=basePath%>/outer/manage/doDeliverSubmit.action",
				data : $("#frmInfo1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						var url="<%=basePath%>/outer/manage/deliverApplyOK.action?deliverID="+data[0].message;
						window.openDialog("交接书申请",url,400,200);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("提交失败！");
				}
			});
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
	
		function doQuery1(){
	 		var a=$("input[name='pwd']").val();
	 		var b=$("input[name='cId']").val();
	 		var c=$("input[name='sellerPwd']").val();
	 		if(a==""||a==null){
	 			alert("请输入密码！");
	 			return;
	 			}
	 		if(a!=c){
	 			alert("输入的密码不正确！请重新输入!");
	 			$("#pwd").textbox("setValue", "");
	 			return;
	 			}
			var url="<%=basePath%>/outer/manage/deliverBuyerPwdConfirm.action?pwd="+a+"&cId="+b;
			window.openDialog("合同乙方密码确认",url,400,300);
		}
		
		function doQuery2(){
			var a =document.getElementsByName("pwd1");
			var b =document.getElementsByName("realPwd");
	 		var c=$("input[name='conId']").val();
			for (var i = 0 ; i < a.length; i++){
				for (var i = 0 ; i < b.length; i++){
					if(a[i].value==""||a[i].value==null){
						alert("请输入所有用户的密码！");
						return;
					}
					if(a[i].value!=b[i].value){
						alert("密码输入错误！请重新输入！");
						a[i].value="";
						return;
					}
				}
			}
			
			doRegist();
		}
		
 		function doReset1(){
	 		$("#form2").form('clear');
		}
		
		function doReset2(){
	 		$("#form3").form('clear');
		}
		
		//打印交接书
		function printDeliver(){
			var a=$("input[name='deliverID']").val();
			window.open("<%=basePath%>/inner/ContractPdf/doSearchDeliverContract.action?deliverID="+a);
		}
		
		//打印登记申请书
		function printApply(){
			var a=$("input[name='contractID']").val();
			window.open("<%=basePath%>/inner/contractquery/registerPrint.action?contractID="+a);
		}		
		   
		function doBack(){
			window.location="<%=basePath%>/outer/manage/deliverApply.action";
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
		function clearNoNum(obj) {  
		    	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
		        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
		        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
		        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
		        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  
		
		}

		function doLoadSubT(){
			var stid = $('#subTID').textbox('getValue');
			if(stid==null || stid==''  || stid<0){
				frmInfo1.n3_1.value='';
			}else{
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/outer/templatemanage/getSubTemplateContent.action?subtmplID="+stid,
					data : "",
					dataType : "json",
					success : function(data){
						//$('#n3_1').textbox('setValue',data[0].content);
						frmInfo1.n3_1.value=data[0].content;
					},
					error : function(){
						alert("获取子模板内容失败！");
					}
				});
			}
		}
		
		$(function(){
		    $("input",$("#n2_1").next("span")).blur(function(){
		    	var money = $('#n2_1').textbox('getValue');
		        var chval = convertCurrency(money);
		        $('#n2_2').textbox('setValue',chval);
		    });
		    $("input",$("#n2_3").next("span")).blur(function(){
		    	var money = $('#n2_3').textbox('getValue');
		        var chval = convertCurrency(money);
		        $('#n2_4').textbox('setValue',chval);
		    });
		})
		
		  	 	

</script>
</head>

<body>
	<form name="frmInfo1" id="frmInfo1" method="post" action="<%=basePath%>/outer/manage/deliverSellerPwdConfirm.action" onsubmit="return false;">
		<input type="hidden" name="contractID" value="${contractId }" />
		<input type="hidden" name="houseID" value="${cdvo.houseID }" />
		<input type="hidden" name="projectName" value="${projectName }" />

		<table cellSpacing="0" cellPadding="2" width="800px" align="center" border="0">
			<tr>
				<td align="center">
					<font size=5><b>房屋交接书</b></font>
				</td>
			</tr>
			<tr>
				<td style="line-height: 32px">
					甲方(卖方)：
					<input class="easyui-textbox" readonly name="seller" type="text" value="${sellerName }" style="width:200px"/>
					<br/>
					乙方(买方)：
					<input class="easyui-textbox" readonly name="buyer" type="text" value="${buyerName }" style="width:200px"/>
				</td>
			</tr>
			<tr>
				<td>
					<P style="line-height:32px">
						&nbsp;&nbsp;&nbsp;
						<input class="easyui-textbox" readonly name="deliverYear" type="text" size="4" maxlength="4" class="txtbgcolor" value="${year }">
						年 
						<input class="easyui-textbox" readonly name="deliverMonth" type="text" size="2" maxlength="2" class="txtbgcolor" value="${month }">
						月 
						<input class="easyui-textbox" readonly name="deliverDay" type="text" size="2" maxlength="2" class="txtbgcolor" value="${day }">
						日 甲、乙双方对
						<input class="easyui-textbox" name="location" type="text" value="${cdvo.location }" data-options="required:true,validType:'length[0,100]'" style="width:300px">
						进行交接，双方确认：
					</P>

					<p style="line-height:32px">
						&nbsp;&nbsp;&nbsp;&nbsp;1、甲方交付给乙方的房屋为
						<input class="easyui-textbox" name="" type="text" value="${cdvo.location }" data-options="required:true,validType:'length[0,100]'" style="width:300px">
						(以下简称该房屋)。 该房屋的实测建筑面积为
						<input class="easyui-numberbox" name="n1_7" type="text" data-options="editable:false,required:true,validType:'length[0,15]',precision:'2',max:'9999999999.99'" value="${flatvo.flarea}">
						平方米 （相应占有的土地使用分摊面积为
						<input class="easyui-numberbox" name="n1_8" type="text" data-options="editable:false,required:true,validType:'length[0,15]',precision:'2',max:'9999999999.99'" value="${flatvo.pro_Area}">
						平方米）， 另有地下附属面积
						<input class="easyui-numberbox" name="cellarArea" data-options="editable:false,required:false,validType:'length[0,15]',precision:'2',max:'9999999999.99'" value="${flatvo.cellar_Area}">
						平方米， 实测建筑面积的测绘机构为
						<input class="easyui-textbox" name="chargeDep" type="text" size="20" maxlength="50" value="${orgName}">
						认定的
						<input class="easyui-textbox" name="n1_9" type="text" size="20" maxlength="50" value="">
						。 新建商品房房地产权属登记证明编号:
						<input class="easyui-textbox" name="n1_10" type="text" value="${realNo}" data-options="editable:false,required:true" style="width:300px">
						。
					</P>
					<P style="line-height:32px">
						&nbsp;&nbsp;&nbsp;&nbsp;2、该房屋的总房价款为人民币
						<input class="easyui-numberbox" name="n2_1" id="n2_1" type="text" data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12" value="">
						元。
						<br/>
						(大写)：
						<input class="easyui-textbox" name="n2_2" id="n2_2" type="text" size="46" maxlength="50" value="" data-options="editable:false,required:true">
						。
						<br/>
						乙方
						<input class="easyui-combobox" name="payType" id="payType" value=""
							data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_527',valueField:'code',textField:'name',
							multiple:false,editable:false,required:false,formatter: formatNullForCombobox,panelHeight:'auto'"
							style="width: 150px" />
						<input class="easyui-numberbox" name="n2_3" type="text" id="n2_3"
							data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12">
						元。
						<br/>
						(大写)：
						<input class="easyui-textbox" name="n2_4" type="text" id="n2_4" size="46" maxlength="50" value="" data-options="editable:false,required:true">
						。 甲方已开具
						<input class="easyui-combobox" name="receiptType" id="receiptType" value=""
							data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_522',valueField:'code',textField:'name',
							multiple:false,editable:false,required:false,formatter: formatNullForCombobox,panelHeight:'auto'"
							style="width: 150px" />
						给乙方。
					</P>
					 <p>&nbsp;&nbsp;&nbsp;&nbsp;3、<br/>
						<textarea rows="10" name="n3_1" id="n3_1" cols="70"></textarea>
						<br/>
					</p>
					<P style="easyui-textbox-HEIGHT: 100%;line-height:20px">
						&nbsp;&nbsp;&nbsp;&nbsp;本交接书由甲乙双方签字生效。
						<br/>
						<br/>
						甲方签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						乙方签字：
						<br/>
						<br/>
						<br/>
						<br/>
						日期：&nbsp;&nbsp; 年&nbsp;&nbsp; 月&nbsp;&nbsp;日
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：&nbsp;&nbsp; 年&nbsp;&nbsp; 月&nbsp;&nbsp;日
					</P>
					<p align="center">
						<a href="javascript:doCheckSellerPwd()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width: 80px">确定</a>
						<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width: 80px">返回</a>&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
	</form>
	<div id="openDL"></div>
</body>