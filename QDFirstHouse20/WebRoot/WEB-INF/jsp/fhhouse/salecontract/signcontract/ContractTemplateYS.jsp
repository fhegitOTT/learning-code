<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%
%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.util.*" %> 

<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
<html>
	<head>
		<title>青岛市商品房买卖合同（预售）</title>
		<link href="../../css/style.css" type="text/css" rel="stylesheet">
		<link href="<%=basePath%>/css/laydate.css" rel="stylesheet" type="text/css" />
  		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
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
		
		<script language="javascript">
		</script>
		<script language="vbscript">
			function formatDouble(val,val2)
				formatDouble = replace(formatnumber(val,val2),",","")
			end function
		</script>
		<SCRIPT language="javascript">
		//去左空格; 
	function ltrim(s){ 
		return s.replace( /^\s*/, ""); 
	} 
	//去右空格; 
	function rtrim(s){ 
		return s.replace( /\s*$/, ""); 
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

	function aNumber(num)
	{
		var numArray = new Array()
		var unit = "亿万圆$"
		for (var i=0; i<unit.length; i++)
		{
		var re = eval("/"+ (numArray[i-1] ? unit.charAt(i-1) : "") +"(.*)"+unit.charAt(i)+"/")
		if (num.match(re))
		{
		numArray[i] = num.match(re)[1].replace(/^拾/, "壹拾")
		numArray[i] = numArray[i].replace(/[零壹贰叁肆伍陆柒捌玖]/g, function ($1)
		{
		return "零壹贰叁肆伍陆柒捌玖".indexOf($1)
		})
		numArray[i] = numArray[i].replace(/[分角拾佰仟]/g, function ($1)
		{
		return "*"+Math.pow(10, "分角 拾佰仟 ".indexOf($1)-2)+"+"
		}).replace(/^\*|\+$/g, "").replace(/整/, "0")
		numArray[i] = "(" + numArray[i] + ")*"+Math.ceil(Math.pow(10, (2-i)*4))
		}
		else numArray[i] = 0
		}
		return eval(numArray.join("+"))
	}

	//判读合同的计价方式
	function contractMoneyChoose(type){
		//alert(type);
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f0602.readOnly = false;
			form1.f0603.readOnly = false;
			form1.f0618.readOnly = false;
			form1.f0619.readOnly = false;
			//2
			form1.f0605.value = "";
			form1.f0606.value = "";
			form1.f0607.value = "";
			form1.f0608.value = "";
			form1.f0609.value = "";
			form1.f0627.value = "";
			form1.f0605.readOnly = true;
			form1.f0606.readOnly = true;
			form1.f0608.readOnly = true;
			form1.f0609.readOnly = true;
			//3
			form1.f0610.value = "";
			form1.f0611.value = "";
			form1.f0624.value = "";
			form1.f0638.value = "";
			form1.f0610.readOnly = true;
			form1.f0624.readOnly = true;
			
			//4
			form1.f0612.value = "";
			form1.f0613.value = "";
			form1.f0614.value = "";
			form1.f0612.readOnly = true;
			form1.f0613.readOnly = true;
			
			//5
			form1.f0629.value = "";
			form1.f0631.value = "";
			form1.f0632.value = "";
			form1.f0629.readOnly = true;
			form1.f0631.readOnly = true;
			
			//6
			form1.f0634.value = "";
			form1.f0636.value = "";
			form1.f0637.value = "";
			form1.f0634.readOnly = true;
			form1.f0636.readOnly = true;
		}
		if(type == '2'){
			//alert(2);
			//1
			form1.f0602.value = "";
			form1.f0603.value = "";
			form1.f0604.value = "";
			form1.f0618.value = "";
			form1.f0619.value = "";
			form1.f0626.value = "";
			form1.f0602.readOnly = true;
			form1.f0603.readOnly = true;
			form1.f0618.readOnly = true;
			form1.f0619.readOnly = true;
			//2
			form1.f0605.readOnly = false;
			form1.f0606.readOnly = false;
			form1.f0608.readOnly = false;
			form1.f0609.readOnly = false;
			//3
			form1.f0610.value = "";
			form1.f0611.value = "";
			form1.f0624.value = "";
			form1.f0638.value = "";
			form1.f0610.readOnly = true;
			form1.f0624.readOnly = true;
			
			//4
			form1.f0612.value = "";
			form1.f0613.value = "";
			form1.f0614.value = "";
			form1.f0612.readOnly = true;
			form1.f0613.readOnly = true;
			
			//5
			form1.f0629.value = "";
			form1.f0631.value = "";
			form1.f0632.value = "";
			form1.f0629.readOnly = true;
			form1.f0631.readOnly = true;
			
			//6
			form1.f0634.value = "";
			form1.f0636.value = "";
			form1.f0637.value = "";
			form1.f0634.readOnly = true;
			form1.f0636.readOnly = true;
		}
		if(type == '3'){
			//alert(3);
			//1
			form1.f0602.value = "";
			form1.f0603.value = "";
			form1.f0604.value = "";
			form1.f0618.value = "";
			form1.f0619.value = "";
			form1.f0626.value = "";
			form1.f0602.readOnly = true;
			form1.f0603.readOnly = true;
			form1.f0618.readOnly = true;
			form1.f0619.readOnly = true;
			//2
			form1.f0605.value = "";
			form1.f0606.value = "";
			form1.f0607.value = "";
			form1.f0608.value = "";
			form1.f0609.value = "";
			form1.f0627.value = "";
			form1.f0605.readOnly = true;
			form1.f0606.readOnly = true;
			form1.f0608.readOnly = true;
			form1.f0609.readOnly = true;
			//3
			form1.f0610.readOnly = false;
			form1.f0610.readOnly = false;
			form1.f0624.readOnly = false;
			
			//4
			form1.f0612.value = "";
			form1.f0613.value = "";
			form1.f0614.value = "";
			form1.f0612.readOnly = true;
			form1.f0613.readOnly = true;
			
			//5
			form1.f0629.value = "";
			form1.f0631.value = "";
			form1.f0632.value = "";
			form1.f0629.readOnly = true;
			form1.f0631.readOnly = true;
			
			//6
			form1.f0634.value = "";
			form1.f0636.value = "";
			form1.f0637.value = "";
			form1.f0634.readOnly = true;
			form1.f0636.readOnly = true;
		}
		if(type == '4'){
			//alert(4);
			//1
			form1.f0602.value = "";
			form1.f0603.value = "";
			form1.f0604.value = "";
			form1.f0618.value = "";
			form1.f0619.value = "";
			form1.f0626.value = "";
			form1.f0602.readOnly = true;
			form1.f0603.readOnly = true;
			form1.f0618.readOnly = true;
			form1.f0619.readOnly = true;
			//2
			form1.f0605.value = "";
			form1.f0606.value = "";
			form1.f0607.value = "";
			form1.f0608.value = "";
			form1.f0609.value = "";
			form1.f0627.value = "";
			form1.f0605.readOnly = true;
			form1.f0606.readOnly = true;
			form1.f0608.readOnly = true;
			form1.f0609.readOnly = true;
			//3
			form1.f0610.value = "";
			form1.f0611.value = "";
			form1.f0624.value = "";
			form1.f0638.value = "";
			form1.f0610.readOnly = true;
			form1.f0624.readOnly = true;
			
			//4
			form1.f0612.readOnly = false;
			form1.f0613.readOnly = false;
			form1.f0613.readOnly = false;
			
			//5
			form1.f0629.value = "";
			form1.f0631.value = "";
			form1.f0632.value = "";
			form1.f0629.readOnly = true;
			form1.f0631.readOnly = true;
			
			//6
			form1.f0634.value = "";
			form1.f0636.value = "";
			form1.f0637.value = "";
			form1.f0634.readOnly = true;
			form1.f0636.readOnly = true;
		}
		
		if(type == '5'){
			//alert(5);
			//1
			form1.f0602.value = "";
			form1.f0603.value = "";
			form1.f0604.value = "";
			form1.f0618.value = "";
			form1.f0619.value = "";
			form1.f0626.value = "";
			form1.f0602.readOnly = true;
			form1.f0603.readOnly = true;
			form1.f0618.readOnly = true;
			form1.f0619.readOnly = true;
			//2
			form1.f0605.value = "";
			form1.f0606.value = "";
			form1.f0607.value = "";
			form1.f0608.value = "";
			form1.f0609.value = "";
			form1.f0627.value = "";
			form1.f0605.readOnly = true;
			form1.f0606.readOnly = true;
			form1.f0608.readOnly = true;
			form1.f0609.readOnly = true;
			//3
			form1.f0610.value = "";
			form1.f0611.value = "";
			form1.f0624.value = "";
			form1.f0638.value = "";
			form1.f0610.readOnly = true;
			form1.f0624.readOnly = true;
			
			//4
			form1.f0612.value = "";
			form1.f0613.value = "";
			form1.f0614.value = "";
			form1.f0612.readOnly = true;
			form1.f0613.readOnly = true;
			
			//5
			form1.f0629.readOnly = false;
			form1.f0631.readOnly = false;
			
			//6
			form1.f0634.value = "";
			form1.f0636.value = "";
			form1.f0637.value = "";
			form1.f0634.readOnly = true;
			form1.f0636.readOnly = true;
		}
		
		if(type == '6'){
			//alert(6);
			//1
			form1.f0602.value = "";
			form1.f0603.value = "";
			form1.f0604.value = "";
			form1.f0618.value = "";
			form1.f0619.value = "";
			form1.f0626.value = "";
			form1.f0602.readOnly = true;
			form1.f0603.readOnly = true;
			form1.f0618.readOnly = true;
			form1.f0619.readOnly = true;
			//2
			form1.f0605.value = "";
			form1.f0606.value = "";
			form1.f0607.value = "";
			form1.f0608.value = "";
			form1.f0609.value = "";
			form1.f0627.value = "";
			form1.f0605.readOnly = true;
			form1.f0606.readOnly = true;
			form1.f0608.readOnly = true;
			form1.f0609.readOnly = true;
			//3
			form1.f0610.value = "";
			form1.f0611.value = "";
			form1.f0624.value = "";
			form1.f0638.value = "";
			form1.f0610.readOnly = true;
			form1.f0624.readOnly = true;
			
			//4
			form1.f0612.value = "";
			form1.f0613.value = "";
			form1.f0614.value = "";
			form1.f0612.readOnly = true;
			form1.f0613.readOnly = true;
			
			//5
			form1.f0629.value = "";
			form1.f0631.value = "";
			form1.f0632.value = "";
			form1.f0629.readOnly = true;
			form1.f0631.readOnly = true;
			
			//6
			form1.f0634.readOnly = false;
			form1.f0636.readOnly = false;
		}
		
	
	}
	
	//条款7选择
	function tk7_change(type){
		if(type == '1' || type ==''){
			//1
			form1.f0708.readOnly = true;
			//2
			form1.f0709.value = "";
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0709.readOnly = true;
			form1.f0710.readOnly = true;
			form1.f0711.readOnly = true;
			form1.f0713.readOnly = true;
			form1.f0714.readOnly = true;
			//3
			form1.f0715.value = "";
			form1.f0717.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0720.value = "";
			form1.f0721.value = "";
			form1.f0722.value = "";
			form1.f0723.value = "";
			form1.f0715.readOnly = true;
			form1.f0717.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0720.readOnly = true;
			form1.f0721.readOnly = true;
			form1.f0723.readOnly = true;
			//4
			form1.f0724.value = "";
			form1.f0724.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f0708.value = "";
			form1.f0708.readOnly = true;
			//2
			form1.f0709.readOnly = true;
			form1.f0710.readOnly = false;
			form1.f0711.readOnly = false;
			form1.f0713.readOnly = true;
			form1.f0714.readOnly = false;
			//3
			form1.f0715.value = "";
			form1.f0717.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0720.value = "";
			form1.f0721.value = "";
			form1.f0722.value = "";
			form1.f0723.value = "";
			form1.f0715.readOnly = true;
			form1.f0717.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0720.readOnly = true;
			form1.f0721.readOnly = true;
			form1.f0723.readOnly = true;
			//4
			form1.f0724.value = "";
			form1.f0724.readOnly = true;
		}
		if(type == '3'){
			//1
			form1.f0708.value = "";
			form1.f0708.readOnly = true;
			//2
			form1.f0709.value = "";
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0709.readOnly = true;
			form1.f0710.readOnly = true;
			form1.f0711.readOnly = true;
			form1.f0713.readOnly = true;
			form1.f0714.readOnly = true;
			//3
			form1.f0715.readOnly = false;
			form1.f0717.readOnly = true;
			form1.f0718.readOnly = false;
			form1.f0720.readOnly = false;
			form1.f0721.readOnly = false;
			form1.f0723.readOnly = false;
			//4
			form1.f0724.value = "";
			form1.f0724.readOnly = true;
		}
		if(type == '4'){
			//1
			form1.f0708.value = "";
			form1.f0708.readOnly = true;
			//2
			form1.f0709.value = "";
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0709.readOnly = true;
			form1.f0710.readOnly = true;
			form1.f0711.readOnly = true;
			form1.f0713.readOnly = true;
			form1.f0714.readOnly = true;
			//3
			form1.f0715.value = "";
			form1.f0717.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0720.value = "";
			form1.f0721.value = "";
			form1.f0722.value = "";
			form1.f0723.value = "";
			form1.f0715.readOnly = true;
			form1.f0717.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0720.readOnly = true;
			form1.f0721.readOnly = true;
			form1.f0723.readOnly = true;
			//4
			form1.f0724.readOnly = false;
		}
	}
	
	//条款8选择
	function tk8_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f0802.readOnly = false;
			form1.f0803.readOnly = false;
			form1.f0804.readOnly = false;
			form1.f0805.readOnly = false;
			form1.f0806.readOnly = false;
			form1.f0807.readOnly = false;
			//2
			form1.f0808.value = "";
			form1.f0808.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f0802.value = "";
			form1.f0803.value = "";
			form1.f0804.value = "";
			form1.f0805.value = "";
			form1.f0806.value = "";
			form1.f0807.value = "";
			form1.f0802.readOnly = true;
			form1.f0803.readOnly = true;
			form1.f0804.readOnly = true;
			form1.f0805.readOnly = true;
			form1.f0806.readOnly = true;
			form1.f0807.readOnly = true;
			//2
			form1.f0808.readOnly = false;
		}
	}
	
	//条款12选择
	function tk12_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f1202.readOnly = false;
			form1.f1203.readOnly = false;
			form1.f1204.readOnly = false;
			form1.f1205.readOnly = false;
			form1.f1206.readOnly = false;
			form1.f1207.readOnly = false;
			//2
			form1.f1208.value = "";
			form1.f1208.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f1202.value = "";
			form1.f1203.value = "";
			form1.f1204.value = "";
			form1.f1205.value = "";
			form1.f1206.value = "";
			form1.f1207.value = "";
			form1.f1202.readOnly = true;
			form1.f1203.readOnly = true;
			form1.f1204.readOnly = true;
			form1.f1205.readOnly = true;
			form1.f1206.readOnly = true;
			form1.f1207.readOnly = true;
			//2
			form1.f1208.readOnly = false;
		}
	}
	
	//条款13选择
	function tk13_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f1302.readOnly = false;
			//2
			form1.f1303.value = "";
			form1.f1303.readOnly = true;
			//3
			form1.f1304.value = "";
			form1.f1304.readOnly = true;
			//4
			form1.f1305.value = "";
			form1.f1305.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f1302.value = "";
			form1.f1302.readOnly = true;
			//2
			form1.f1303.readOnly = false;
			//3
			form1.f1304.value = "";
			form1.f1304.readOnly = true;
			//4
			form1.f1305.value = "";
			form1.f1305.readOnly = true;
		}
		if(type == '3'){
			//1
			form1.f1302.value = "";
			form1.f1302.readOnly = true;
			//2
			form1.f1303.value = "";
			form1.f1303.readOnly = true;
			//3
			
			form1.f1304.readOnly = false;
			//4
			form1.f1305.value = "";
			form1.f1305.readOnly = true;
		}
		if(type == '4'){
			//1
			form1.f1302.value = "";
			form1.f1302.readOnly = true;
			//2
			form1.f1303.value = "";
			form1.f1303.readOnly = true;
			//3
			form1.f1304.value = "";
			form1.f1304.readOnly = true;
			//4
			
			form1.f1305.readOnly = false;
		}
	}
	
	//条款20选择
	function tk20_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f2003.readOnly = false;
			form1.f2004.readOnly = false;
			//2
			form1.f2005.value = "";
			form1.f2005.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f2003.value = "";
			form1.f2004.value = "";
			form1.f2003.readOnly = true;
			form1.f2004.readOnly = true;
			//
			form1.f2005.readOnly = false;

		}
	}
	//条款27选择
	function tk27_change(type){
		if(type == '1' || type ==''){
			form1.f2702.value = "";
			form1.f2702.readOnly = true;
		}
		if(type == '2'){
			form1.f2702.readOnly = false;
		}
	}

	$(function(){
		addN1_1();
		addN1_3();
		addN3_1();
		addN4_1();
		addN5_1();
		addN5_2();
		addN5_5();
		addN6_4();
		addN8_5();
		addN8_7();
		addN8_18();
		addN14_2();
		addN14_11();
		addN14_14();
		addN18_4();
		addN22_2();
		addFJ7_1();
		addFJ7_3();
		addFJ7_5();
		addFJ7_7();
		addFJ7_9();
		addFJ7_11();
		addFJ7_13();
		addFJ7_16();
		addFJ7_18();
		addFJ7_20();
		addFJ7_23();
		var opera = '<%=request.getAttribute("opera")%>';
		if(opera=='view'){
			$('input,textarea',$('form[name="form1"]')).attr('readonly',true);
			$('select',$('form[name="form1"]')).attr('disabled',true);
		}	
		$("input[ name='f0603']").blur(function(){
		    	var money = $("input[ name='f0603']").val();
		        var chval = convertCurrency(money);
		        $("input[ name='f0604' ]").val(chval);		        
		    });
		$("input[ name='f0606']").blur(function(){
	    	var money = $("input[ name='f0606']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0607' ]").val(chval);		        
	    });
		$("input[ name='f0610']").blur(function(){
	    	var money = $("input[ name='f0610']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0611' ]").val(chval);		        
	    });
		$("input[ name='f0613']").blur(function(){
	    	var money = $("input[ name='f0613']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0614' ]").val(chval);		        
	    });
	    $("input[ name='f0619']").blur(function(){
	    	var money = $("input[ name='f0619']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0626' ]").val(chval);		        
	    });
	    $("input[ name='f0609']").blur(function(){
	    	var money = $("input[ name='f0609']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0627' ]").val(chval);		        
	    });
	    $("input[ name='f0624']").blur(function(){
	    	var money = $("input[ name='f0624']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0638' ]").val(chval);		        
	    });
	    $("input[ name='f0631']").blur(function(){
	    	var money = $("input[ name='f0631']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0632' ]").val(chval);		        
	    });
	    $("input[ name='f0636']").blur(function(){
	    	var money = $("input[ name='f0636']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0637' ]").val(chval);		        
	    });
		$("input[ name='f0701']").blur(function(){
	    	var money = $("input[ name='f0701']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0702' ]").val(chval);		        
	    });
		$("input[ name='f0711']").blur(function(){
	    	var money = $("input[ name='f0711']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0712' ]").val(chval);		        
	    });
		$("input[ name='f0718']").blur(function(){
	    	var money = $("input[ name='f0718']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0719' ]").val(chval);		        
	    });
		$("input[ name='f0721']").blur(function(){
	    	var money = $("input[ name='f0721']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0722' ]").val(chval);		        
	    });
		contractMoneyChoose(form1.f0601.value==""?"1":form1.f0601.value);
		tk7_change(form1.f0707.value==""?"1":form1.f0707.value);
		tk8_change(form1.f0801.value==""?"1":form1.f0801.value);
		tk12_change(form1.f1201.value==""?"1":form1.f1201.value);
		tk13_change(form1.f1301.value==""?"1":form1.f1301.value);
		tk20_change(form1.f2002.value==""?"1":form1.f2002.value);
		tk27_change(form1.f2701.value==""?"1":form1.f2701.value);
	});	
     
	
	function addN1_1(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_10',
			dataType : "json",
			success : function(data){
				$('#N1_1').empty();
				var N1_1id = $('#N1_1id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N1_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN1_3(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_11',
			dataType : "json",
			success : function(data){
				$('#N1_3').empty();
				var N1_1id = $('#N1_3id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N1_3').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	
	function addN3_1(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_12',
			dataType : "json",
			success : function(data){
				$('#N3_1').empty();
				var N1_1id = $('#N3_1id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N3_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N3_1').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}

	function addN4_1(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_13',
			dataType : "json",
			success : function(data){
				$('#N4_1').empty();
				var N1_1id = $('#N4_1id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N4_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN5_1(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_34',
			dataType : "json",
			success : function(data){
				$('#N5_1').empty();
				var N1_1id = $('#N5_1id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N5_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N5_1').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}		
	
	function addN5_2(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_35',
			dataType : "json",
			success : function(data){
				$('#N5_2').empty();
				var N1_1id = $('#N5_2id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N5_2').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N5_2').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}

	function addN5_5(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_36',
			dataType : "json",
			success : function(data){
				$('#N5_5').empty();
				var N1_1id = $('#N5_5id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N5_5').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N5_5').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}		

	function addN6_4(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_14',
			dataType : "json",
			success : function(data){
				$('#N6_4').empty();
				var N1_1id = $('#N6_4id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N6_4').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N6_4').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN8_18(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_18',
			dataType : "json",
			success : function(data){
				$('#N8_18').empty();
				var N1_1id = $('#N8_18id').val();
				if(data.length>0){
					$('#N8_18').append('<option value=></option>');
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N8_18').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N8_18').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN14_11(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_15',
			dataType : "json",
			success : function(data){
				$('#N14_11').empty();
				var N1_1id = $('#N14_11id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N14_11').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N14_11').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN18_4(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_17',
			dataType : "json",
			success : function(data){
				$('#N18_4').empty();
				var N1_1id = $('#N18_4id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N18_4').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N18_4').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN22_2(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_16',
			dataType : "json",
			success : function(data){
				$('#N22_2').empty();
				var N1_1id = $('#N22_2id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N22_2').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N22_2').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_1(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_20',
			dataType : "json",
			success : function(data){
				$('#FJ7_1').empty();
				var N1_1id = $('#FJ7_1id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_1').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_3(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_21',
			dataType : "json",
			success : function(data){
				$('#FJ7_3').empty();
				var N1_1id = $('#FJ7_3id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_3').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_3').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_5(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_22',
			dataType : "json",
			success : function(data){
				$('#FJ7_5').empty();
				var N1_1id = $('#FJ7_5id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_5').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_5').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_7(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_23',
			dataType : "json",
			success : function(data){
				$('#FJ7_7').empty();
				var N1_1id = $('#FJ7_7id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_7').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_7').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_9(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_24',
			dataType : "json",
			success : function(data){
				$('#FJ7_9').empty();
				var N1_1id = $('#FJ7_9id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_9').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_9').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_11(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_25',
			dataType : "json",
			success : function(data){
				$('#FJ7_11').empty();
				var N1_1id = $('#FJ7_11id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_11').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_11').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_13(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_26',
			dataType : "json",
			success : function(data){
				$('#FJ7_13').empty();
				var N1_1id = $('#FJ7_13id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_13').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_13').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_16(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_27',
			dataType : "json",
			success : function(data){
				$('#FJ7_16').empty();
				var N1_1id = $('#FJ7_16id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_16').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_16').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_18(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_28',
			dataType : "json",
			success : function(data){
				$('#FJ7_18').empty();
				var N1_1id = $('#FJ7_18id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_18').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_18').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_20(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_29',
			dataType : "json",
			success : function(data){
				$('#FJ7_20').empty();
				var N1_1id = $('#FJ7_20id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_20').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_20').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addFJ7_23(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_30',
			dataType : "json",
			success : function(data){
				$('#FJ7_23').empty();
				var N1_1id = $('#FJ7_23id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#FJ7_23').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#FJ7_23').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}

	function addN14_2(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_14',
			dataType : "json",
			success : function(data){
				$('#N14_2').empty();
				var N1_1id = $('#N14_2id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N14_2').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N14_2').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN14_14(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_15',
			dataType : "json",
			success : function(data){
				$('#N14_14').empty();
				var N1_1id = $('#N14_14id').val();
				if(data.length>0){
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N14_14').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N14_14').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}
	
	function addN8_5(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_37',
			dataType : "json",
			success : function(data){
				$('#N8_5').empty();
				var N1_1id = $('#N8_5id').val();
				if(data.length>0){
					$('#N8_5').append('<option value=></option>');
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N8_5').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N8_5').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
			}
		});
	}	
	
	function addN8_7(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : '<%=basePath%>/system/getNewDictionaryJson.action',  
			data : 'dictype=DIC_38',
			dataType : "json",
			success : function(data){
				$('#N8_7').empty();
				var N1_1id = $('#N8_7id').val();
				if(data.length>0){
					$('#N8_7').append('<option value=></option>');
					$.each(data,function (index,option){
						if(option.name==N1_1id){
							$('#N8_7').append('<option value='+option.name+' selected>'+option.name+'</option>');
						}else{
							$('#N8_7').append('<option value='+option.name+'>'+option.name+'</option>');
						}
					});
				}
			},
			error : function(){
				alert("查询出错！");
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
			/*var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';
            $('#openDL').dialog({
                content: content,
                noheader: false,
                border: true,
                resizable: false,//定义对话框是否可调整尺寸。
                maximized: false,//默认最大化
                modal: true,
			});*/
		}
		
		function doSave(){
			//alert(1);
			if(!CheckDataValid(form1)){
				return;
			}
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/contracttemplatemanage/savePresellTemplate.action',  
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						alert(data[0].msg);
						parent.location.href="<%=basePath%>/outer/contracttemplatemanage/queryTemplate.action";
					}else{
						alert(data[0].msg);
					}
				},
				error : function(){
					alert("保存出错！");
				}
			});
		}
		function doQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontract/gotoXgInfoCheck.action',  
				data : $("#frmInfo1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						//alert(data[0].msg);
					}else{
						alert(data[0].msg);
					}
				},
				error : function(){
					alert("查询出错！");
				}
			});
		}
		
		function doclose(){
			parent.location.href="<%=basePath%>/outer/contracttemplatemanage/queryTemplate.action";
		}
</SCRIPT>
	</head>
	<body class="openDlg_style">
	<form name="form1" id="form1" method="post" action="">
		<input type="hidden" name="templateID" value="${templateID}">
		<input type="hidden" name="type" id="type" value="${type}">
		<input type="hidden" name="maxPrice" value="100000">
		<center ><b STYLE="font-size:15pt" >商品房买卖合同</b></center>
		<center ><b STYLE="font-size:15pt" >（预售）</b></center>
		<center STYLE="font-size:12pt" >模板名称:<input type="text"  size="40" maxlength="20" name="tempname"  value="${ptVo.tempname}" features="bmust=1,datatype=0,maxlength=20,showtitle=模板名称"></center>
		<br><br>
		<table width="95%" align="center">
			<tr><td>
					<table width=100%	>
						<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商达成一致意见，签订本商品房买卖合同。</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第一章	合同当事人</b></td></tr>
		
	
			<tr><td>
				<table width=100%>
					<tr><td width=100>甲方(出卖人):</td>
						<td>
							<input type="text"  size="50"  name="sellerName" maxlength="100" class="input3" readonly value="${eqvo.name}">
							<input type="hidden" name="ID" value="${sellerID}">
						</td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>住所：</td>
						<td><input type="text" name="sellerAddress" size="30" maxlength="100" class="input3" readonly value="${eqvo.regadr}"></td>
						<td width=100>邮编：</td>
						<td><input type="text" name="sellerPostcode" size="30"  maxlength="6" class="input3"  readonly value="${eqvo.post}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>营业执照号码：</td>
						<td><input type="text" name="sellerBizcardcode" maxlength="50" class="input3" readonly value="${eqvo.bizregister_Num}"></td>
						<td width=100>资格证书号码：</td>
						<td><input type="text" name="sellerCertcode" maxlength="50" class="input3"  readonly value="${eqvo.aptitudeNum}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>法定代表人：</td>
						<td><input readonly="readonly" type="text" name="sellerDelegate" maxlength="100" class="input3"  readonly  value="${eqvo.delegate}"></td>
						<td width=100>联系电话：</td>
						<td><input type="text" name="sellerDlgCall" maxlength="20" class="input3"  readonly  value="${eqvo.dlg_Call}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>委托代理人：</td>
						<td><input type="text" name="sellerProxy" maxlength="50" class="input3"  readonly   value="${eqvo.proxy}"></td>
						<td width=100>联系电话：</td>
						<td><input type="text" name="sellerProxyCall" maxlength="20" class="input3"  readonly   value="${eqvo.proxy_Call}">
						</td>
					</tr>
				</table>
			</td></tr>
	<tr><td><hr></td></tr>
			<%
			List<BuyerInfoVO> buyerList = (List<BuyerInfoVO>)request.getAttribute("buyerList");
			if(buyerList!=null&&buyerList.size()>0){
				for(BuyerInfoVO buyer:buyerList){
		%>
			<tr><td>
				<table width=100%	>
					<tr><td width=100>买受人:</td>
						<td><input  size="50"  value="<%=buyer.getBuyerName()!=null?buyer.getBuyerName():"" %>" readonly type="text" name="buyerName" maxlength="100" class="input3" ></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td width=100>国籍：</td>
						<td><input  value="<%=buyer.getAttribute("buyer_nationality_dict_name") %>" type="text" name="" maxlength="100" readonly class="input3"></td>
						<td width=100>所在省市：</td>
						<td><input  value="<%=buyer.getAttribute("buyer_province")==null?"":buyer.getAttribute("buyer_province_dict_name") %>" type="text" name="" maxlength="100" class="input3" readonly></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>个人/公司：</td>
						<td><input type="text" size="20" value="<%=buyer.getAttribute("buyer_type_dict_name") %>" name="" maxlength="100" class="input3" readonly></td>
						<td width=100>邮编：</td>
						<td><input type="text" size="20"  value="<%=buyer.getBuyerPostcode()!=null?buyer.getBuyerPostcode():"" %>" name="" maxlength="100" class="input3" readonly></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>出生日期：</td>
						<td><input type="text" name="" value="<%=(buyer.getBuyerBirth()!=null?buyer.getBuyerBirth():"") %>"  maxlength="10" class="input3" readonly></td>
						<td width=100>性别：</td>
						<td><input type="text" name="" size="20"  value="<%=buyer.getAttribute("buyer_sex_dict_name") %>"  maxlength="100" class="input3" readonly></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td width=100>住所（址）:</td>
						<td><input size="50" value="<%=(buyer.getBuyerAddress()!=null?buyer.getBuyerAddress():"") %>" type="text" name="" maxlength="100" readonly class="input3" ></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td width=100>联系电话:</td>
						<td><input size="30" value="<%=buyer.getBuyerCall()!=null?buyer.getBuyerCall():"" %>" type="text" name="" maxlength="100" readonly class="input3" ></td>
					</tr>
				</table>
			</td></tr>			
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>证件名称：</td>
						<td><input  value="<%=buyer.getAttribute("buyer_cardname_dict_name") %>" type="text" name="" maxlength="6" readonly class="input3" ></td>
						<td width=100>号码：</td>
						<td><input  value="<%=buyer.getBuyerCardcode()!=null?buyer.getBuyerCardcode():"" %>"  type="text" name="" maxlength="20" readonly class="input3" ></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>委托/代理人：</td>
						<td><input value="<%=buyer.getBuyerProxy()!=null?buyer.getBuyerProxy():"" %>"  type="text" size="50" name="" maxlength="100" readonly class="input3" ></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>住所（址）：</td>
						<td><input  value="<%=buyer.getBuyerProxyAdr()!=null?buyer.getBuyerProxyAdr():"" %>" type="text" name="" maxlength="100" readonly class="input3" ></td>
						<td width=100>联系电话：</td>
						<td><input  value="<%=buyer.getBuyerProxyCall()!=null?buyer.getBuyerProxyCall():"" %>" type="text" name="" maxlength="100" readonly class="input3" ></td>
					</tr>
				</table>
			</td></tr>
			<tr><td><hr></td></tr>
		<%	
				}	
			}
		%>

			<!-- 合同内容 start -->
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第二章	商品房基本状况</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第一条  项目建设依据。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人以
						<input type="text" name="f0101" size="40" maxlength="100" class="input3" readonly value="${ptVo.f0101}"  features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  地块"><font color="red"></font>
						方式取得坐落于<input type="text" name="f0102" size="40" maxlength="100" class="input3" readonly value="${ptVo.f0102}"  features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  地块"><font color="red"></font>地块的建设用地使用权。
						该地块
						<input type="text" name="f0103" size="40" maxlength="100" class="input3" readonly value="${ptVo.f0103}"  features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  地块"><font color="red"></font>
						为<input type="text" name="f0104" size="50" value="${ptVo.f0104 }" maxlength="100" class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  地块证号"><font color="red"></font>，
						土地使用权面积为<input type="text" name="f0105" value="${ptVo.f0105 }" size="15" maxlength="50" class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  土地面积"><font color="red"></font>平方米。
						买受人购买的商品房（以下简称该商品房）所占用的土地用途为<input type="text" name="f0106" size="20" value="${ptVo.f0106}"  maxlength="50" class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  规划用途"><font color="red"></font>，
						土地使用权终止日期为<input type="text" name="f0107" maxlength="13" value="${ptVo.f0107}" size="15" class="input_text input3" readonly features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第一条 土地使用终止年限">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.出卖人经批准，在上述地块上建设的商品房项目核准名称为
						<input type="text" name="f0108" size="30" value="${ptVo.f0108}" maxlength="50" class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  项目名称"><font color="red"></font>,
						建设工程规划许可证号为<input type="text" name="f0109"  value="${ptVo.f0109}" size="50" maxlength="100" class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  建设工程规划许可证号"><font color="red"></font>，
						建筑工程施工许可证号为<input type="text" name="f0110" size="50"  value="${ptVo.f0110}" maxlength="100" class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  施工许可证号"><font color="red"></font>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二条  预售依据。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房已由
						<input type="text" name="f0201" size="35" maxlength="50" value="${ptVo.f0201}"  class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第二条  预售批准机关">批准预售，
						预售许可证号为<input type="text" name="f0202" value="${ptVo.f0202 }" size="30" maxlength="50"  class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第二条  预售许可证号">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第三条  商品房基本情况。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房的规划用途为
						<input type="text" name="f0301" size="20" maxlength="50" value="${ptVo.f0301}"  readonly  class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  用途"><font color="red"></font>
						。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房所在建筑物的主体结构为
						<input type="text" name="f0302" size="10" maxlength="50" value="${ptVo.f0302}"  readonly   class="input3" readonly features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  建筑结构"><font color="red"></font>，
						建筑总层数为<input type="text" name="f0303" size="10" maxlength="10" value="${ptVo.f0303}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  层数"><font color="red"></font>层，
						其中地上<input type="text" name="f0304" size="10" maxlength="10" value="${ptVo.f0304}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条 地上层数"><font color="red"></font>层，
						地下<input type="text" name="f0305" size="10" maxlength="10" value="${ptVo.f0305}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  地下层数"><font color="red"></font>层.
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.该商品房为第一条规定项目中的
						<input readonly type="text" name="f0306" value="${ptVo.f0306}"  size="50" maxlength="100"  readonly  class="input3" readonly style="width: auto;overflow-x:visible; overflow-y:visible" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  房号"><font color="red"></font>
						。房屋竣工后，如房号发生改变，不影响该商品房的特定位置。该商品房的平面图见附件一。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.该商品房的房产测绘机构为
						<input type="text" name="f0307" value="${ptVo.f0307}"  size="50" maxlength="100"  class="input2" style="width: auto;overflow-x:visible; overflow-y:visible" features="bmust=0,datatype=0,maxlength=1000,showtitle=第三条  测绘机构"><font color="red"></font>，
						其预测建筑面积共<input type="text" name="f0308" value="${ptVo.f0308}"  size="10" maxlength="50" readonly   class="input3" readonly features="bmust=0,datatype=1,maxlength=50,showtitle=第三条  建筑面积"><font color="red"></font>平方米，
						其中套内建筑面积<input type="text" name="f0309" value="${ptVo.f0309}"  size="10" maxlength="50" readonly  class="input3" readonly features="bmust=0,datatype=1,maxlength=50,showtitle=第三条  套内建筑面积"><font color="red"></font>平方米，
						分摊共有建筑面积<input type="text" name="f0310" value="${ptVo.f0310 }"  size="10" maxlength="50" readonly  class="input3" readonly features="bmust=0,datatype=1,maxlength=50,showtitle=第三条  分摊建筑面积">平方米。该商品房共用部位见附件二。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房层高为
						<input type="text" name="f0311" size="10" maxlength="10" value="${ptVo.f0311}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  层高"><font color="red"></font>米，
						有<input type="text" name="f0312" size="10" value="${ptVo.f0312}"  maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  阳台"><font color="red"></font>个阳台，
						其中<input type="text" name="f0313" size="10" value="${ptVo.f0313}"  maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  封闭式阳台"><font color="red"></font>个阳台为封闭式，
						<input type="text" name="f0314" size="10" value="${ptVo.f0314}"  maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  非封闭式阳台"><font color="red"></font>个阳台为非封闭式。阳台是否封闭以规划设计文件为准。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第四条 抵押情况。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;与该商品房有关的抵押情况为
						<select name="f0401" style="width:100px;">
							<option value=""></option>
							<!-- 
							<option value="抵押" <c:if test="${ptVo.f0401=='抵押'}">selected</c:if> >抵押</option>   
							<option value="未抵押" <c:if test="${ptVo.f0401=='未抵押'}">selected</c:if> >未抵押</option>
							 -->
						</select>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押类型：</td>
							<td><input type="text" name="f0402" size="30" value="${ptVo.f0402}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>抵押人：</td>
							<td><input type="text" name="f0403" size="30" value="${ptVo.f0403}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押权人：</td>
							<td><input type="text" name="f0404" size="30" value="${ptVo.f0404}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>抵押登记机构：</td>
							<td><input type="text" name="f0405" size="30" value="${ptVo.f0405}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押登记日期：</td>
							<td><input type="text" name="f0406" size="30" value="${ptVo.f0406}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>债务履行期限：</td>
							<td><input type="text" name="f0407" size="30" value="${ptVo.f0407}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押类型：</td>
							<td><input type="text" name="f0408" size="30" value="${ptVo.f0408}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>抵押人：</td>
							<td><input type="text" name="f0409" size="30" value="${ptVo.f0409}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押权人：</td>
							<td><input type="text" name="f0410" size="30" value="${ptVo.f0410}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>抵押登记机构：</td>
							<td><input type="text" name="f0411" size="30" value="${ptVo.f0411}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >
					<table width=100%	>
						<tr>
							<td width=150>&nbsp;&nbsp;&nbsp;&nbsp;抵押登记日期：</td>
							<td><input type="text" name="f0412" size="30" value="${ptVo.f0412}"  maxlength="20"  class="input3" readonly></td>
							<td width=150>债务履行期限：</td>
							<td><input type="text" name="f0413" size="30" value="${ptVo.f0413}"  maxlength="20"  class="input3" readonly></td>
						</tr>
					</table>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;抵押权人同意该商品房转让的证明及关于抵押的相关约定见附件三。</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第五条 房屋权利状况承诺。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人对该商品房享有合法权利；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房没有出售给除本合同买受人以外的其他人；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.该商品房没有司法查封或其他限制转让的情况；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.<input type="text" name="f0501" size="100" value="${ptVo.f0501}"  maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第五条  4">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.<input type="text" name="f0502" size="100" value="${ptVo.f0502}"  maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第五条  5">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;如该商品房权利状况与上述情况不符，导致不能完成本合同登记备案或房屋所有权转移登记的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f0503" size="10" value="${ptVo.f0503}"  maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第五条  利息">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付
						<%-- <select name="f0504" style="width:100px;">
						<option value="已付房款一倍" <c:if test="${ptVo.f0504=='已付房款一倍'}">selected</c:if> >已付房款一倍</option>   
						<option value="买受人全部损失" <c:if test="${ptVo.f0504=='买受人全部损失'}">selected</c:if> >买受人全部损失</option>
						</select> --%>
						<select id="N6_4" name="f0504" style="width:120px;"></select>
						<input type="hidden" id="N6_4id" value="${ptVo.f0504 }"/>
       					的赔偿金。
						</td>
					</tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第三章   商品房价款</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第六条 计价方式与价款。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人与买受人按照下列第
						<select name="f0601"  onChange="contractMoneyChoose(this.value);">
							<option value="1" <c:if test="${ptVo.f0601=='1'}">selected</c:if> >1</option>
						    <option value="2" <c:if test="${ptVo.f0601=='2'}">selected</c:if> >2</option>
							<option value="3" <c:if test="${ptVo.f0601=='3'}">selected</c:if> >3</option>
						    <option value="4" <c:if test="${ptVo.f0601=='4'}">selected</c:if> >4</option>
						    <option value="5" <c:if test="${ptVo.f0601=='5'}">selected</c:if> >5</option>
						    <option value="6" <c:if test="${ptVo.f0601=='6'}">selected</c:if> >6</option>
						</select>种方式计算该商品房价款：
					</td></tr>
				</table>
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.按照套内建筑面积计算，该商品房单价(不包含房屋装修)为每平方米
				<select name="f0615">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0602" value="${ptVo.f0602}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积单价">元，
				总价款(不包含房屋装修)为
				<select name="f0616">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" id="f0603" name="f0603" value="${ptVo.f0603}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积总金额">元
				（大写<input type="text" id="f0604" name="f0604" size="50" value="${ptVo.f0604}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  套内建筑面积总金额大写">元整）。
				该商品房装修价格为每平方米
				<select name="f0617">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0618" value="${ptVo.f0618}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积 装修标准">元，
				装修总价为人民币<input type="text" name="f0619" size="50" value="${ptVo.f0619}" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积 装修总价">元
				（大写<input type="text" id="f0626" name="f0626" size="50" value="${ptVo.f0626}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  套内装修总价总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.按照建筑面积计算，该商品房单价（不包含房屋装修）为每平方米
				<select name="f0620">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0605" value="${ptVo.f0605}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积单价">元，
				总价款（不包含房屋装修）为
				<select name="f0621">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0606" size="30" value="${ptVo.f0606}" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积总金额">元
				（大写<input type="text" name="f0607" size="50" maxlength="50" value="${ptVo.f0607}" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  建筑面积总金额大写">元整）。
				该商品房装修价格为每平方米
				<select name="f0622">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0608" value="${ptVo.f0608}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积 装修标准">元，
				装修总价为人民币<input type="text" name="f0609" value="${ptVo.f0609}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积 装修总价">元
				（大写<input type="text" id="f0627" name="f0627" size="50" value="${ptVo.f0627}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  建筑面积 装修总价总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.按照套计算，该商品房总价款(不包含房屋装修)为
				<select name="f0623">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0610" value="${ptVo.f0610}" size="30" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  按套总金额">元
				（大写<input type="text" name="f0611" size="50" value="${ptVo.f0611}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  按套总金额大写">元整），
				装修总价为人民币<input type="text" name="f0624" size="50" value="${ptVo.f0624}" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  装修总价">元
				（大写<input type="text" id="f0638" name="f0638" size="50" value="${ptVo.f0638}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  按套装修总价总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.按照
				<input type="text" name="f0612" size="50" value="${ptVo.f0612}" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  4">计算，
				该商品房总价款为
				<select name="f0625">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0613" value="${ptVo.f0613}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  4中总金额">元
				（大写<input type="text" name="f0614" size="50" value="${ptVo.f0614}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  4中总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.按照套内建筑面积计算，该商品房单价为每平方米
				<select name="f0628">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0629" value="${ptVo.f0629}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  计价方式5 单价">元，
				总价款为
				<select name="f0630">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" id="f0631" name="f0631" value="${ptVo.f0631}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  计价方式5 总金额">元
				（大写<input type="text" id="f0632" name="f0632" size="50" value="${ptVo.f0632}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  计价方式5 总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.按照建筑面积计算，该商品房单价为每平方米
				<select name="f0633">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0634" value="${ptVo.f0634}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  计价方式6 单价">元，
				总价款为
				<select name="f0635">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" id="f0636" name="f0636" value="${ptVo.f0636}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  计价方式6 总金额">元
				（大写<input type="text" id="f0637" name="f0637" size="50" value="${ptVo.f0637}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  计价方式6 总金额大写">元整）。
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第七条 付款方式及期限。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）签订本合同前，买受人已向出卖人支付定金
					<select name="f0728">
						<option value="人民币">人民币</option>
					</select>
					（币种）<input type="text" name="f0701" value="${ptVo.f0701}" size="30" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  订金金额">元
					（大写<input type="text" name="f0702" size="50" value="${ptVo.f0702}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条   订金金额大写">元整），
					该定金于<input type="text" name="f0703" value="${ptVo.f0703}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第七条">
					<%-- <select name="f0704" style="width:100px;" onchange="form1.f0703.value=this.value;">
						<option value=""></option>
						<option value="本合同签订" <c:if test="${ptVo.f0704=='本合同签订'}">selected</c:if> >本合同签订</option>   
						<option value="交付首付款" <c:if test="${ptVo.f0704=='交付首付款'}">selected</c:if> >交付首付款</option>
					</select>时 --%>
					<select id="N8_5" name="f0704" style="width:100px;" onchange="form1.f0703.value=this.value;"></select>时
						<input type="hidden" id="N8_5id" value="${ptVo.f0704 }"/>
						<input type="text" name="f0705" value="${ptVo.f0705}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第七条">
					<%-- <select name="f0706"  style="width:100px;" onchange="form1.f0705.value=this.value;">
						<option value=""></option>
						<option value="抵作" <c:if test="${ptVo.f0706=='抵作'}">selected</c:if> >抵作</option>   
					</select>商品房价款。 --%><select id="N8_7" name="f0706" style="width:100px;" onchange="form1.f0705.value=this.value;"></select>商品房价款。
						<input type="hidden" id="N8_7id" value="${ptVo.f0706 }"/>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）买受人采取下列第
						<select name="f0707"  onchange="tk7_change(this.value);">
							<option value="1" <c:if test="${ptVo.f0707=='1'}">selected</c:if> >1</option>   
						    <option value="2" <c:if test="${ptVo.f0707=='2'}">selected</c:if> >2</option> 
							<option value="3" <c:if test="${ptVo.f0707=='3'}">selected</c:if> >3</option>   
						    <option value="4" <c:if test="${ptVo.f0707=='4'}">selected</c:if> >4</option> 
						</select>种方式付款：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.一次性付款。买受人应当在
						<input type="text" name="f0708" id="f0708" maxlength="13" value="${ptVo.f0708}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第七条 买受人付款方式1付款时间">
						<script>
								//执行一个laydate实例
								laydate.render({
								  elem: '#f0708' //指定元素
								});
						</script>
						日前支付该商品房全部价款。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.分期付款。买受人应当在
						<input type="text" name="f0709" id="f0709" maxlength="13" value="${ptVo.f0709}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第七条 买受人付款方式2付款时间">
						<script>
								//执行一个laydate实例
								laydate.render({
								  elem: '#f0709' //指定元素
								});
						</script>
						日前分
						<input type="text" name="f0710" value="${ptVo.f0710}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  买受人付款方式2分期数">期支付该商品房全部价款，
						首期房价款
						<select name="f0729">
							<option value="人民币">人民币</option>
						</select>
						（币种）<input type="text" name="f0711" value="${ptVo.f0711}" size="30" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  买受人付款方式2首期房价款">元
						（大写<input type="text" name="f0712" size="50" value="${ptVo.f0712}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条   买受人付款方式2首期房价款金额大写">元整），
						应当与<input type="text" name="f0713" id="f0713" maxlength="13" value="${ptVo.f0713}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第七条  买受人付款方式2首期房价款付款时间">
						<script>
								//执行一个laydate实例
								laydate.render({
								  elem: '#f0713' //指定元素
								});
						</script>
						日前支付。
						<br><textarea class="input_text" name="f0714" rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第七条  买受人付款方式2补充">${ptVo.f0714}</textarea>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.贷款方式付款：
						<input type="text" name="f0715" value="${ptVo.f0715}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第七条 3.贷款方式付款">
						<%-- <select name="f0716" style="width:100px;" onchange="form1.f0715.value=this.value;">
						<option value=""></option>
						<option value="公积金贷款" <c:if test="${ptVo.f0716=='公积金贷款'}">selected</c:if> >公积金贷款</option>   
						<option value="商业贷款" <c:if test="${ptVo.f0716=='商业贷款'}">selected</c:if> >商业贷款</option>
						<option value="公积金组合贷款" <c:if test="${ptVo.f0716=='公积金组合贷款'}">selected</c:if> >公积金组合贷款</option>
						</select> --%>
						<select id="N8_18" name="f0716" style="width:100px;" onchange="form1.f0715.value=this.value;"></select>。
						<input type="hidden" id="N8_18id" value="${ptVo.f0716}" />
						买受人应当于<input type="text" id="f0717" name="f0717" maxlength="13" value="${ptVo.f0717}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第七条 买受人付款方式3付款时间">
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0717' //指定元素
							});
						</script>
						日前
						支付首期房价款<select name="f0730">
										<option value="人民币">人民币</option>
								   </select>
						（币种）<input type="text" name="f0718" value="${ptVo.f0718}" size="30" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  买受人付款方式3首期房价款">元
						（大写<input type="text" name="f0719" size="50" value="${ptVo.f0719}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条   买受人付款方式3首期房价款金额大写">元整），
						占全部房价款的<input type="text" name="f0720" value="${ptVo.f0720}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  买受人付款方式3首付房价款占比"> ％。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;余款<select name="f0731">
															<option value="人民币">人民币</option>
														</select>
						（币种）<input type="text" name="f0721" value="${ptVo.f0721}" size="30" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  买受人付款方式3余款">元
						（大写<input type="text" name="f0722" size="50" value="${ptVo.f0722}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条   买受人付款方式3余款金额大写">元整），
						向<input type="text" name="f0723" value="${ptVo.f0723}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  买受人付款方式3贷款机构">申请贷款支付。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.其他方式：
						<textarea class="input_text" name="f0724" rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第七条  买受人付款方式4其他">${ptVo.f0724}</textarea>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）出售该商品房的全部房价款应当存入预售资金监管账户，用于本工程建设。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房的预售资金监管机构为<input type="text" name="f0725" value="${ptVo.f0725}" size="50" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=预售资金监管机构">，
					预售资金监管账户名称为<input type="text" name="f0726" value="${ptVo.f0726}" size="50" maxlength="50"   class="input2"  features="bmust=0,datatype=0,maxlength=50,showtitle=预售资金监管账户">,
                                                  账号为<input type="text" name="f0727" value="${ptVo.f0727}" size="50" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=预售资金监管账号">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房价款的计价方式、总价款、付款方式及期限的具体约定见附件四。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第八条 逾期付款责任。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;除不可抗力外，买受人未按照约定时间付款的，双方同意按照下列第
						<select name="f0801"  onchange="tk8_change(this.value);">
							<option value="1" <c:if test="${ptVo.f0801=='1'}">selected</c:if> >1</option>   
						    <option value="2" <c:if test="${ptVo.f0801=='2'}">selected</c:if> >2</option> 
						</select>种方式处理：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.按照逾期时间，分别处理（（1）和（2）不作累加）。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）逾期在
						<input type="text" name="f0802" value="${ptVo.f0802}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  逾期天数">日之内，买受人按日计算向出卖人支付逾期应付款万分之
						<input type="text" name="f0803" value="${ptVo.f0803}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  逾期违约金">的违约金。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）逾期超过
						<input type="text" name="f0804" value="${ptVo.f0804}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条 逾期超过天数">日（该期限应当与本条第（1）项中的期限相同）后，出卖人有权解除合同。出卖人解除合同的，应当书面通知买受人。买受人应当自解除合同通知送达之日起
						<input type="text" name="f0805" value="${ptVo.f0805}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  出卖人书面催告天数">日内按照累计应付款的
						<input type="text" name="f0806" value="${ptVo.f0806}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  逾期超过违约金">%向出卖人支付违约金，同时，出卖人退还买受人已付全部房款（含已付贷款部分）。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人不解除合同的，买受人按日计算向出卖人支付逾期应付款万分之
						<input type="text" name="f0807" value="${ptVo.f0807}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条   支付逾期应付款比率">（该比率不低于第（1）项中的比率）的违约金。<br>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;本条所称逾期应付款是指依照第七条及附件四约定的到期应付款与该期实际已付款的差额；采取分期付款的，按照相应的分期应付款与该期的实际已付款的差额确定。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.
						<textarea class="input_text" name="f0808"  rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第八条处理方式2">${ptVo.f0808}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第四章 商品房交付条件与交付手续</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第九条 商品房交付条件。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房交付时应当符合下列第1、2、
						<input type="text" name="f0901" value="${ptVo.f0901}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第九条条件">项所列条件：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房已取得建设工程竣工验收备案证明文件；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房已取得房屋测绘报告；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f0902" value="${ptVo.f0902}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第九条  3">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.<input type="text" name="f0903" value="${ptVo.f0903}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第九条  4">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，出卖人还需提供《住宅使用说明书》和《住宅质量保证书》。</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十条 商品房相关设施设备交付条件。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）基础设施设备</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.供水、排水：交付时供水、排水配套设施齐全，并与城市公共供水、排水管网连接。使用自建设施供水的，供水的水质符合国家规定的饮用水卫生标准，</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1001" value="${ptVo.f1001}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条1补充">;</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.供电：交付时纳入城市供电网络并正式供电，</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1002" value="${ptVo.f1002}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条2补充">;</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.供暖：交付时供热系统符合供热配建标准，使用城市集中供热的，纳入城市集中供热管网，</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1003" value="${ptVo.f1003}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条3补充">;</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.燃气：交付时完成室内燃气管道的敷设，并与城市燃气管网连接，保证燃气供应，</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1004" value="${ptVo.f1004}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条4补充">;</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.电话通信：交付时线路敷设到户；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.有线电视：交付时线路敷设到户；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;7.宽带网络：交付时线路敷设到户。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;以上第1、2、3项由出卖人负责办理开通手续并承担相关费用；第4、5、6、7项需要买受人自行办理开通手续。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;如果在约定期限内基础设施设备未达到交付使用条件，双方同意按照下列第
						<select name="f1005">
						<option value="（1）" <c:if test="${ptVo.f1005=='（1）'}">selected</c:if> >（1）</option>   
						<option value="（2）" <c:if test="${ptVo.f1005=='（2）'}">selected</c:if> >（2）</option> 
						</select>种方式处理：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）以上设施中第1、2、3、4项在约定交付日未达到交付条件的，出卖人按照本合同第十二条的约定承担逾期交付责任。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;第5项未按时达到交付使用条件的，出卖人按日向买受人支付
						<input type="text" name="f1006" value="${ptVo.f1006}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条违约第5项时违约金">元的违约金；
						第6项未按时达到交付使用条件的，出卖人按日向买受人支付
						<input type="text" name="f1007" value="${ptVo.f1007}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条违约第6项时违约金">元的违约金；
						第7项未按时达到交付使用条件的，出卖人按日向买受人支付
						<input type="text" name="f1008" value="${ptVo.f1008}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条违约第7项时违约金">元的违约金。
						出卖人采取措施保证相关设施于约定交付日后<input type="text" name="f1009" value="${ptVo.f1009}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条1达到交付使用条件日期数">日之内达到交付使用条件。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）
						<textarea class="input_text" name="f1010"  rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十条  （一）（2）">${ptVo.f1010}</textarea>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）公共服务及其他配套设施（以建设工程规划许可为准）</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.小区内绿地率：<input type="text" name="f1011" maxlength="13" value="${ptVo.f1011}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)1绿化率达到时间">日达到
						<input type="text" name="f1012" value="${ptVo.f1012}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)1绿化率标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.小区内非市政道路：<input type="text" name="f1013" maxlength="13" value="${ptVo.f1013}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)2非市政道路达到时间">日达到
						<input type="text" name="f1014" value="${ptVo.f1014}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)2非市政道路标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.规划的车位、车库：<input type="text" name="f1015" maxlength="13" value="${ptVo.f1015}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)3规划的车位、车库达到时间">日达到
						<input type="text" name="f1016" value="${ptVo.f1016}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)3规划的车位、车库标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.物业服务用房：<input type="text" name="f1017" maxlength="13" value="${ptVo.f1017}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)4.物业服务用达到时间">日达到
						<input type="text" name="f1018" value="${ptVo.f1018}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)4.物业服务用房标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.医疗卫生机构：<input type="text" name="f1019" maxlength="13" value="${ptVo.f1019}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)5.医疗卫生机构达到时间">日达到
						<input type="text" name="f1020" value="${ptVo.f1020}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)5.医疗卫生机构标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.幼儿园：<input type="text" name="f1021" maxlength="13" value="${ptVo.f1021}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)6.幼儿园达到时间">日达到
						<input type="text" name="f1022" value="${ptVo.f1022}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)6.幼儿园标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;7.学校：<input type="text" name="f1023" maxlength="13" value="${ptVo.f1023}" size="15" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十条  (二)7.学校达到时间">日达到
						<input type="text" name="f1024" value="${ptVo.f1024}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)7.学校标准">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;8.
						<input type="text" name="f1025" value="${ptVo.f1025}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)8">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;9.
						<input type="text" name="f1026" value="${ptVo.f1026}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)9">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;以上设施未达到上述条件的，双方同意按照以下方式处理：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.小区内绿地率未达到上述约定条件的，<input type="text" name="f1027" value="${ptVo.f1027}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)处理方式1">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.小区内非市政道路未达到上述约定条件的，<input type="text" name="f1028" value="${ptVo.f1028}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)处理方式2">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.规划的车位、车库未达到上述约定条件的，<input type="text" name="f1029" value="${ptVo.f1029}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)处理方式3">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.物业服务用房未达到上述约定条件的，<input type="text" name="f1030" value="${ptVo.f1030}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)处理方式4">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.其他设施未达到上述约定条件的，<input type="text" name="f1031" value="${ptVo.f1031}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十条  (二)处理方式5">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;关于本项目内相关设施设备的具体约定见附件五。</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十一条 交付时间和手续。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）出卖人应当在<input type="text" id="f1101" name="f1101" maxlength="13" value="${ptVo.f1101}" size="15" class="input_text" readonly="readonly" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 交付时间"> 
					<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f1101' //指定元素
							});
					</script>
					日前向买受人交付该商品房。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）该商品房达到第九条、第十条约定的交付条件后，出卖人应当在交付日期届满前
						<input type="text" name="f1102" value="${ptVo.f1102}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=3,minvalue=10,maxlength=50,showtitle=第十一条  交付日期届满前">日（不少于10日）将查验房屋的时间、办理交付手续的时间地点以及应当携带的证件材料的通知书面送达买受人。买受人未收到交付通知书的，以本合同约定的交付日期届满之日为办理交付手续的时间，以该商品房所在地为办理交付手续的地点。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1103" value="${ptVo.f1103}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 补充">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;交付该商品房时，出卖人应当出示满足第九条约定的证明文件。出卖人不出示证明文件或者出示的证明文件不齐全，不能满足第九条约定条件的，买受人有权拒绝接收，由此产生的逾期交付责任由出卖人承担，并按照第十二条处理。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）查验房屋</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.办理交付手续前，买受人有权对该商品房进行查验，出卖人不得以缴纳相关税费或者签署物业管理文件作为买受人查验和办理交付手续的前提条件。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.买受人查验的该商品房存在下列除地基基础和主体结构外的其他质量问题的，由出卖人按照有关工程和产品质量规范、标准自查验次日起
						<input type="text" name="f1104" value="${ptVo.f1104}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 修复时限">日内负责修复，并承担修复费用，修复后再行交付。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）屋面、墙面、地面渗漏或开裂等；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）管道堵塞；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（3）门窗翘裂、五金件损坏；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（4）灯具、电器等电气设备不能正常使用；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（5）<input type="text" name="f1105" value="${ptVo.f1105}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 2（5）">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（6）<input type="text" name="f1106" value="${ptVo.f1106}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 2（6）">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.查验该商品房后，双方应当签署商品房交接单。由于买受人原因导致该商品房未能按期交付的，双方同意按照以下方式处理：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）<input type="text" name="f1107" value="${ptVo.f1107}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 3（1）">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）<input type="text" name="f1108" value="${ptVo.f1108}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十一条 3（2）">；</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十二条 逾期交付责任。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;除不可抗力外，出卖人未按照第十一条约定的时间将该商品房交付买受人的，双方同意按照下列第
						<select name="f1201" onchange="tk12_change(this.value);">
							<option value="1" <c:if test="${ptVo.f1201=='1'}">selected</c:if> >1</option>   
						    <option value="2" <c:if test="${ptVo.f1201=='2'}">selected</c:if> >2</option> 
						</select>种方式处理：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.按照逾期时间，分别处理（（1）和（2）不作累加）。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）逾期在
						<input type="text" name="f1202" value="${ptVo.f1202}" size="5" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条  逾期不超过天数">日之内（该期限应当不多于第八条第1（1）项中的期限），自第十一条约定的交付期限届满之次日起至实际交付之日止，出卖人按日计算向买受人支付全部房价款万分之
						<input type="text" name="f1203" value="${ptVo.f1203}" size="5" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条  逾期不超过违约金">的违约金（该违约金比率应当不低于第八条第1（1）项中的比率）。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）逾期超过
						<input type="text" name="f1204" value="${ptVo.f1204}" size="5" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条  逾期超过天数">日（该期限应当与本条第（1）项中的期限相同）后，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1205" value="${ptVo.f1205}" size="5" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条  买受人累计已付款比率"> %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息；同时，出卖人按照全部房价款的
						<input type="text" name="f1206" value="${ptVo.f1206}" size="5" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条  向买受人支付违约金比率">%向买受人支付违约金。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;买受人要求继续履行合同的，合同继续履行，出卖人按日计算向买受人支付全部房价款万分之
						<input type="text" name="f1207" value="${ptVo.f1207}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十二条    继续履行合同违约金">（该比率应当不低于本条第1（1）项中的比率）的违约金。<br>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2、
						<textarea class="input_text" name="f1208"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十二条 处理方式2">${ptVo.f1208}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第五章 面积差异处理方式</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十三条 面积差异处理。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房交付时，出卖人应当向买受人出示房屋测绘报告，并向买受人提供该商品房的面积实测数据（以下简称实测面积）。实测面积与第三条载明的预测面积发生误差的，双方同意按照第
					<select name="f1301"  onchange="tk13_change(this.value);">
						<option value="1" <c:if test="${ptVo.f1301=='1'}">selected</c:if> >1</option>   
						<option value="2" <c:if test="${ptVo.f1301=='2'}">selected</c:if> >2</option> 
						<option value="3" <c:if test="${ptVo.f1301=='3'}">selected</c:if> >3</option>   
						<option value="4" <c:if test="${ptVo.f1301=='4'}">selected</c:if> >4</option> 
						</select>种方式处理：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.根据第六条按照套内建筑面积计价的约定，双方同意按照下列原则处理：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）套内建筑面积误差比绝对值在3%以内（含3%）的，据实结算房价款；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）套内建筑面积误差比绝对值超出3%时，买受人有权解除合同。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1302" value="${ptVo.f1302}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十三条  计息利率">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人选择不解除合同的，实测套内建筑面积大于预测套内建筑面积时，套内建筑面积误差比在3%以内（含3%）部分的房价款由买受人补足；超出3%部分的房价款由出卖人承担，产权归买受人所有。实测套内建筑面积小于预测套内建筑面积时，套内建筑面积误差比绝对值在3%以内（含3%）部分的房价款由出卖人返还买受人；绝对值超出3%部分的房价款由出卖人双倍返还买受人。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;套内建筑面积误差比＝（实测套内建筑面积－预测套内建筑面积）/预测套内建筑面积×100%</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.根据第六条按照建筑面积计价的约定，双方同意按照下列原则处理：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）建筑面积、套内建筑面积误差比绝对值均在3%以内（含3%）的，根据实测建筑面积结算房价款；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）建筑面积、套内建筑面积误差比绝对值其中有一项超出3%时，买受人有权解除合同。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1303" value="${ptVo.f1303}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十三条  计息利率">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人选择不解除合同的，实测建筑面积大于预测建筑面积时，建筑面积误差比在3%以内（含3%）部分的房价款由买受人补足，超出3%部分的房价款由出卖人承担，产权归买受人所有。实测建筑面积小于预测建筑面积时，建筑面积误差比绝对值在3%以内（含3%）部分的房价款由出卖人返还买受人；绝对值超出3%部分的房价款由出卖人双倍返还买受人。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;建筑面积误差比＝（实测建筑面积－预测建筑面积）/预测建筑面积×100%</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（3）因设计变更造成面积差异，双方不解除合同的，应当签署补充协议。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.根据第六条按照套计价的，出卖人承诺在房屋平面图中标明详细尺寸，并约定误差范围。该商品房交付时，套型与设计图纸不一致或者相关尺寸超出约定的误差范围，双方约定如下：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1304"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十三条 处理方式3">${ptVo.f1304}</textarea>。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.双方自行约定：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1305"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十三条 处理方式3">${ptVo.f1305}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第六章 规划设计变更</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十四条 规划变更。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）出卖人应当按照城乡规划主管部门核发的建设工程规划许可证规定的条件建设商品房，不得擅自变更。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;双方签订合同后，涉及该商品房规划用途、面积、容积率、绿地率、基础设施、公共服务及其他配套设施等规划许可内容经城乡规划主管部门批准变更的，出卖人应当在变更确立之日起10日内将书面通知送达买受人。出卖人未在规定期限内通知买受人的，买受人有权解除合同。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）买受人应当在通知送达之日起15日内做出是否解除合同的书面答复。买受人逾期未予以书面答复的，视同接受变更。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1401" value="${ptVo.f1401}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=1,minvalue=0.01,maxlength=50,showtitle=第十四条  计息利率">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息；同时，出卖人按照全部房价款的
						<input type="text" name="f1402" value="${ptVo.f1402}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=1,minvalue=0.01,maxlength=50,showtitle=第十四条  违约金百分比">%向买受人支付违约金。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，有权要求出卖人赔偿由此造成的损失，双方约定如下：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1403"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十四条  双方约定">${ptVo.f1403}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十五条 设计变更。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）双方签订合同后，出卖人按照法定程序变更建筑工程施工图设计文件，涉及下列可能影响买受人所购商品房质量或使用功能情形的，出卖人应当在变更确立之日起10日内将书面通知送达买受人。出卖人未在规定期限内通知买受人的，买受人有权解除合同。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房结构形式、户型、空间尺寸、朝向；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.供热、采暖方式；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1501" value="${ptVo.f1501}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十五条  3">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.<input type="text" name="f1502" value="${ptVo.f1502}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十五条  4">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.<input type="text" name="f1503" value="${ptVo.f1503}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十五条  5">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）买受人应当在通知送达之日起15日内做出是否解除合同的书面答复。买受人逾期未予以书面答复的，视同接受变更。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1504" value="${ptVo.f1504}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十五条  计付利息">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息；同时，
						出卖人按照全部房价款的<input type="text" name="f1505" value="${ptVo.f1505}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十五条  违约金百分比">%向买受人支付违约金。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，有权要求出卖人赔偿由此造成的损失，双方约定如下：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1506"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十五条  双方约定">${ptVo.f1506}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第七章 商品房质量及保修责任</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十六条 商品房质量。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）地基基础和主体结构</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人承诺该商品房地基基础和主体结构合格，并符合国家及行业标准。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;经检测不合格的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1601" value="${ptVo.f1601}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  计付利息">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付
						<%-- <select name="f1602" style="width:100px;">
						<option value="已付房款一倍" <c:if test="${ptVo.f1602=='已付房款一倍'}">selected</c:if> >已付房款一倍</option>   
						<option value="买受人全部损失" <c:if test="${ptVo.f1602=='买受人全部损失'}">selected</c:if> >买受人全部损失</option>
						</select> --%>
						<select id="N14_2" name="f1602" style="width:120px;"></select>
						<input type="hidden" id="N14_2id" value="${ptVo.f1602 }"/>
						的赔偿金。因此而发生的检测费用由出卖人承担。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，<input type="text" name="f1603" value="${ptVo.f1603}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  买受人不解除合同约束">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）其他质量问题</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房质量应当符合有关工程质量规范、标准和施工图设计文件的要求。发现除地基基础和主体结构外质量问题的，双方按照以下方式处理：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）及时更换、修理；如给买受人造成损失的，还应当承担相应赔偿责任。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1604" value="${ptVo.f1604}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  其他质量问题1"></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）经过更换、修理，仍然严重影响正常使用的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1605" value="${ptVo.f1605}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  计付利息">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人承担相应赔偿责任。因此而发生的检测费用由出卖人承担。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，<input type="text" name="f1606" value="${ptVo.f1606}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  买受人不解除合同约束">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）装饰装修及设备标准</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房应当使用合格的建筑材料、构配件和设备，装置、装修、装饰所用材料的产品质量必须符合国家的强制性标准及双方约定的标准。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;不符合上述标准的，买受人有权要求出卖人按照下列第（1）、
						<input type="text" name="f1607" value="${ptVo.f1607}" size="5" maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=10,showtitle=第十六条  处理方式">、
						<input type="text" name="f1608" value="${ptVo.f1608}" size="5" maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=10,showtitle=第十六条  处理方式">、
						<!-- <input type="text" name="f1609" value="${ptVo.f1609}" size="5" maxlength="10"  class="input2" features="bmust=0,datatype=0,maxlength=10,showtitle=第十六条  处理方式"> -->方式处理（可多选）
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（1）及时更换、修理；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）出卖人赔偿双倍的装饰、设备差价；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（3）<input type="text" name="f1609" value="${ptVo.f1609}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  （三）（3）">；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（4）<input type="text" name="f1610" value="${ptVo.f1610}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  （三）（4）">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;具体装饰装修及相关设备标准的约定见附件六。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（四）室内空气质量、建筑隔声和民用建筑节能措施</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房室内空气质量符合
						<%-- <select name="f1611" style="width:100px;">
						<option value="国家" <c:if test="${ptVo.f1611=='国家'}">selected</c:if> >国家</option>   
						<option value="地方" <c:if test="${ptVo.f1611=='地方'}">selected</c:if> >地方</option>
						</select> --%>
						<select id="N14_11" name="f1611" style="width:100px;"></select>
						<input type="hidden" id="N14_11id" value="${ptVo.f1611 }"/>
						标准，标准名称：<input type="text" name="f1612" value="${ptVo.f1612}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  标准名称1">，
						标准文号：<input type="text" name="f1613" value="${ptVo.f1613}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  标准文号1">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，建筑隔声情况符合
						<%-- <select name="f1614" style="width:100px;">
						<option value="国家" <c:if test="${ptVo.f1614=='国家'}">selected</c:if> >国家</option>   
						<option value="地方" <c:if test="${ptVo.f1614=='地方'}">selected</c:if> >地方</option>
						</select> --%>
						<select id="N14_14" name="f1614" style="width:100px;"></select>
						<input type="hidden" id="N14_14id" value="${ptVo.f1614 }"/>
						标准，标准名称：<input type="text" name="f1615" value="${ptVo.f1615}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  标准名称2">，
						标准文号：<input type="text" name="f1616" value="${ptVo.f1616}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  标准文号2">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房室内空气质量或建筑隔声情况经检测不符合标准，由出卖人负责整改，整改后仍不符合标准的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f1617" value="${ptVo.f1617}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  计付利息">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人承担相应赔偿责任。经检测不符合标准的，检测费用由出卖人承担，整改后再次检测发生的费用仍由出卖人承担。因整改导致该商品房逾期交付的，出卖人应当承担逾期交付责任。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房应当符合国家有关民用建筑节能强制性标准的要求。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;未达到标准的，出卖人应当按照相应标准要求补做节能措施，并承担全部费用；给买受人造成损失的，出卖人应当承担相应赔偿责任。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1618" value="${ptVo.f1618}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十六条  补充">。</td></tr>					
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十七条 保修责任。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）商品房实行保修制度。该商品房为住宅的，出卖人自该商品房交付之日起，按照《住宅质量保证书》承诺的内容承担相应的保修责任。该商品房为非住宅的，双方应当签订补充协议详细约定保修范围、保修期限和保修责任等内容。具体内容见附件七。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）下列情形，出卖人不承担保修责任：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.因不可抗力造成的房屋及其附属设施的损害；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.因买受人不当使用造成的房屋及其附属设施的损害；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1701" value="${ptVo.f1701}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十七条  补充">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）在保修期内，买受人要求维修的书面通知送达出卖人
						<input type="text" name="f1702" value="${ptVo.f1702}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十七条  时限">日内，出卖人既不履行保修义务也不提出书面异议的，买受人可以自行或委托他人进行维修，维修费用及维修期间造成的其他损失由出卖人承担。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十八条 质量担保。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人不按照第十六条、第十七条约定承担相关责任的，由
						<input type="text" name="f1801" value="${ptVo.f1801}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第十八条  担保人">承担连带责任。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;关于质量担保的证明见附件八。</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第八章 合同备案与房屋登记</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十九条 预售合同登记备案。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）出卖人应当自本合同签订之日起
						<input type="text" name="f1901" value="${ptVo.f1901}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=3,maxvalue=30,maxlength=50,showtitle=第十九条  办理备案手续期限">日内（不超过30日）办理商品房预售合同登记备案手续，并将本合同登记备案情况告知买受人。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）有关预售合同登记备案的其他约定如下：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1902"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十九条 其他约定">${ptVo.f1902}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十条 房屋登记。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）双方同意共同向房屋登记机构申请办理该商品房的房屋所有权转移登记。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）因出卖人的原因，买受人未能在该商品房交付之日起
						<input type="text" name="f2001" value="${ptVo.f2001}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十条  取得所有权证书期限">日内取得该商品房的房屋所有权证书的，双方同意按照下列第
						<select name="f2002"  onchange="tk20_change(this.value);">
						<option value="1" <c:if test="${ptVo.f2002=='1'}">selected</c:if> >1</option>   
						<option value="2" <c:if test="${ptVo.f2002=='2'}">selected</c:if> >2</option> 
						</select>种方式处理：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照
						<input type="text" name="f2003" value="${ptVo.f2003}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十条  计息利率">%（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。买受人不解除合同的，自买受人应当完成房屋所有权登记的期限届满之次日起至实际完成房屋所有权登记之日止，出卖人按日计算向买受人支付全部房价款万分之
						<input type="text" name="f2004" value="${ptVo.f2004}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十条  违约金比例">的违约金。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.
						<textarea class="input_text" name="f2005"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第二十条 处理方式2">${ptVo.f2005}</textarea>。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）因买受人的原因未能在约定期限内完成该商品房的房屋所有权转移登记的，出卖人不承担责任。</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第九章 前期物业管理</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十一条 前期物业管理。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）出卖人依法选聘的前期物业服务企业为
						<input type="text" name="f2101" value="${ptVo.f2101}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十一条  服务企业">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）物业服务时间从
						<input type="text" name="f2102" id="f2102" value="${ptVo.f2102}" maxlength="13" size="15" class="input_text" readonly="readonly" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第二十一条 物业服务开始时间">
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f2102' //指定元素
							});
						</script>
						日到
						<input type="text" name="f2103" id="f2103" value="${ptVo.f2103}" maxlength="13" size="15" class="input_text" readonly="readonly" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第二十一条 物业服务截止时间">
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f2103' //指定元素
							});
						</script> 
						。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）物业服务期间，物业收费计费方式为
						<%-- <select name="f2104"  style="width:100px;">
						<option value="包干制" <c:if test="${ptVo.f2104=='包干制'}">selected</c:if> >包干制</option>   
						<option value="酬金制" <c:if test="${ptVo.f2104=='酬金制'}">selected</c:if> >酬金制</option>	
						</select> --%>
						<select id="N18_4" name="f2104" style="width:100px;" features="bmust=1,datatype=0,maxlength=50,showtitle=第二十一条  物业收费计费方式"></select>
						<input type="hidden" id="N18_4id" value="${ptVo.f2104}"/>
						。物业服务费为
						<input type="text" name="f2105" value="${ptVo.f2105}" size="10" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十一条 物业服务费">元/月●平方米（建筑面积）。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（四）买受人同意由出卖人选聘的前期物业服务企业代为查验并承接物业共用部位、共用设施设备，出卖人应当将物业共用部位、共用设施设备承接查验的备案情况书面告知买受人。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（五）买受人已详细阅读前期物业服务合同和临时管理规约，同意由出卖人依法选聘的物业服务企业实施前期物业管理，遵守临时管理规约。业主委员会成立后，由业主大会决定选聘或续聘物业服务企业。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房前期物业服务合同、临时管理规约见附件九。</td></tr>
				</table>
			</td></tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第十章 其他事项</b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十二条 建筑物区分所有权。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）买受人对其建筑物专有部分享有占有、使用、收益和处分的权利。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）以下部位归业主共有：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.建筑物的基础、承重结构、外墙、屋顶等基本结构部分，通道、楼梯、大堂等公共通行部分，消防、公共照明等附属设施、设备，避难层、设备层或者设备间等结构部分；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房所在建筑区划内的道路（属于城镇公共道路的除外）、绿地（属于城镇公共绿地或者明示属于个人的除外）、占用业主共有的道路或者其他场地用于停放汽车的车位、物业服务用房；</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f2201" value="${ptVo.f2201}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十二条 （二）3">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（三）双方对其他配套设施约定如下：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.规划的车位、车库：
							<input type="text" name="f2202" value="${ptVo.f2202}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十二条 （三）1">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.会所：
							<input type="text" name="f2203" value="${ptVo.f2203}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十二条 （三）2">；
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.
							<input type="text" name="f2204" value="${ptVo.f2204}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十二条 （三）3">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十三条 税费。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;双方应当按照国家的有关规定，向相应部门缴纳因该商品房买卖发生的税费。因预测面积与实测面积差异，导致买受人不能享受税收优惠政策而增加的税收负担，由
						<input type="text" name="f2301" value="${ptVo.f2301}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十三条 税费承担方">承担。
					</td></tr>					
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十四条 销售和使用承诺。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人承诺不采取分割拆零销售、返本销售或者变相返本销售的方式销售商品房；不采取售后包租或者变相售后包租的方式销售未竣工商品房。</td></tr>					
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.出卖人承诺按照规划用途进行建设和出售，不擅自改变该商品房使用性质，并按照规划用途办理房屋登记。出卖人不得擅自改变与该商品房有关的共用部位和设施的使用性质。</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.出卖人承诺对商品房的销售，不涉及依法或者依规划属于买受人共有的共用部位和设施的处分。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.出卖人承诺已将遮挡或妨碍房屋正常使用的情况告知买受人。具体内容见附件十。</td></tr>		
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.买受人使用该商品房期间，不得擅自改变该商品房的用途、建筑主体结构和承重结构。</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.<input type="text" name="f2401" value="${ptVo.f2401}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十四条 6">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;7.<input type="text" name="f2402" value="${ptVo.f2402}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十四条 7">。</td></tr>	
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十五条 送达。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人和买受人保证在本合同中记载的通讯地址、联系电话均真实有效。任何根据本合同发出的文件，均应采用书面形式，以
						<%-- <select name="f2501"  style="width:100px;">
						<option value="邮政快递" <c:if test="${ptVo.f2501=='邮政快递'}">selected</c:if> >邮政快递</option>   
						<option value="邮寄挂号信" <c:if test="${ptVo.f2501=='邮寄挂号信'}">selected</c:if> >邮寄挂号信</option>
						</select> --%>
						<select id="N22_2" name="f2501" style="width:100px;" features="bmust=1,datatype=0,maxlength=50,showtitle=第二十五条  送达方式"></select>
						<input type="hidden" id="N22_2id" value="${ptVo.f2501 }"/>
						方式送达对方。任何一方变更通讯地址、联系电话的，应在变更之日起
						<input type="text" name="f2502" value="${ptVo.f2502}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十五条  书面通知时限">日内书面通知对方。变更的一方未履行通知义务导致送达不能的，应承担相应的法律责任。
					</td></tr>					
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十六条 买受人信息保护。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人对买受人信息负有保密义务。非因法律、法规规定或国家安全机关、公安机关、检察机关、审判机关、纪检监察部门执行公务的需要，未经买受人书面同意，出卖人及其销售人员和相关工作人员不得对外披露买受人信息，或将买受人信息用于履行本合同之外的其他用途。</td></tr>					
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十七条 争议解决方式。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;本合同在履行过程中发生的争议，由双方当事人协商解决，也可通过消费者协会等相关机构调解；或按照下列第
						<select name="f2701"  onchange="tk27_change(this.value);">
						<option value="1" <c:if test="${ptVo.f2701=='1'}">selected</c:if> >1</option>   
						<option value="2" <c:if test="${ptVo.f2701=='2'}">selected</c:if> >2</option> 
						</select>种方式解决：
					</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.依法向房屋所在地人民法院起诉。</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.提交<input type="text" name="f2702" value="${ptVo.f2702}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十七条  仲裁委员会">仲裁委员会仲裁。
					</td></tr>					
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十八条 补充协议。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;对本合同中未约定或约定不明的内容，双方可根据具体情况签订书面补充协议（补充协议见附件十一）。</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;补充协议中含有不合理的减轻或免除本合同中约定应当由出卖人承担的责任，或不合理的加重买受人责任、排除买受人主要权利内容的，仍以本合同为准。</td></tr>				
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十九条 合同生效。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;本合同自双方签字或盖章之日起生效。本合同的解除应当采用书面形式。</td></tr>	
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;本合同及附件共
						<input type="text" name="f2901" value="${ptVo.f2901}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  页数">页，
						一式<input type="text" name="f2902" value="${ptVo.f2902}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  总份数">份，
						其中出卖人<input type="text" name="f2903" value="${ptVo.f2903}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  出卖人份数">份，
						买受人<input type="text" name="f2904" value="${ptVo.f2904}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  买受人份数">份，
						[<input type="text" name="f2905" value="${ptVo.f2905}" size="15" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  人1">]
						<input type="text" name="f2906" value="${ptVo.f2906}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  份数">份，
						[<input type="text" name="f2907" value="${ptVo.f2907}" size="15" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  人1">]
						<input type="text" name="f2908" value="${ptVo.f2908}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第二十九条  份数">份。合同附件与本合同具有同等法律效力。
					</td></tr>				
				</table>
			</td></tr>
			
			<!-- 合同内容 end -->
			<!-- 合同附件 start -->
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件一   房屋平面图（应当标明方位）</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.房屋分层分户图（应当标明详细尺寸，并约定误差范围）。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.建设工程规划方案总平面图。</td></tr>
					<tr><td ><br><br><br><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件二   关于该商品房共用部位的具体说明（可附图说明）</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.纳入该商品房分摊的共用部位的名称、面积和所在位置。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.未纳入该商品房分摊的共用部位的名称、所在位置。</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3902" rows="15" style="width:100%">${attachmap['3902']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件三   抵押权人同意该商品房转让的证明及关于抵押的相关约定</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.抵押权人同意该商品房转让的证明。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.解除抵押的条件和时间。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.关于抵押的其他约定。</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3903" rows="15" style="width:100%">${attachmap['3903']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件四   关于该商品房价款的计价方式、总价款、付款方式及期限的约定</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3904" rows="15" style="width:100%">${attachmap['3904']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件五   关于本项目内相关设施、设备的具体约定</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.相关设施的位置及用途。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.其他约定。</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3905" rows="15" style="width:100%">${attachmap['3905']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件六   关于装饰装修及相关设备标准的约定</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;交付的商品房达不到本附件约定装修标准的，按照本合同第十六条第（三）款约定处理。出卖人未经双方约定增加的装置、装修、装饰，视为无条件赠送给买受人。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;双方就装饰装修主要材料和设备的品牌、产地、规格、数量等内容约定如下：</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;1.外墙：
						<%-- <select id="FJ7_1" name="f9001"  style="width:100px;" >
						<option value="瓷砖" <c:if test="${ptVo.f9001=='瓷砖'}">selected</c:if> >瓷砖</option>   
						<option value="涂料" <c:if test="${ptVo.f9001=='涂料'}">selected</c:if> >涂料</option> 
						<option value="玻璃幕墙" <c:if test="${ptVo.f9001=='玻璃幕墙'}">selected</c:if> >玻璃幕墙</option>   
						</select> --%>
						<select id="FJ7_1" name="f9001" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_1id" value="${ptVo.f9001 }" />
						;
						<input type="text" name="f9002" value="${ptVo.f9002}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 外墙">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;2.起居室：<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）内墙：
						<%-- <select id="FJ7_3" name="f9003"   style="width:100px;" >
						<option value="瓷砖" <c:if test="${ptVo.f9003=='瓷砖'}">selected</c:if> >瓷砖</option>  
						<option value="壁纸" <c:if test="${ptVo.f9003=='壁纸'}">selected</c:if> >壁纸</option>  
						</select> --%>
						<select id="FJ7_3" name="f9003" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_3id" value="${ptVo.f9003 }" />
						;
						<input type="text" name="f9004" value="${ptVo.f9004}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 内墙">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）顶棚：
						<%-- <select id="FJ7_5" name="f9005"   style="width:100px;" >
						<option value="涂料" <c:if test="${ptVo.f9005=='涂料'}">selected</c:if> >涂料</option>
						<option value="石膏板吊顶" <c:if test="${ptVo.f9005=='石膏板吊顶'}">selected</c:if> >石膏板吊顶</option>
						</select> --%>
						<select id="FJ7_5" name="f9005" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_5id" value="${ptVo.f9005 }" />
						;
						<input type="text" name="f9006" value="${ptVo.f9006}" size="70" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 顶棚">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）室内地面：
						<%-- <select id="FJ7_7" name="f9007" style="width:100px;" >
						<option value="大理石" <c:if test="${ptVo.f9007=='大理石'}">selected</c:if> >大理石</option>
						<option value="花岗岩" <c:if test="${ptVo.f9007=='花岗岩'}">selected</c:if> >花岗岩</option>
						<option value="水泥抹面" <c:if test="${ptVo.f9007=='水泥抹面'}">selected</c:if> >水泥抹面</option>
						<option value="实木地板" <c:if test="${ptVo.f9007=='实木地板'}">selected</c:if> >实木地板</option>
						</select> --%>
						<select id="FJ7_7" name="f9007" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_7id" value="${ptVo.f9007 }" />
						;
						<input type="text" name="f9008" value="${ptVo.f9008}" size="70" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 室内地面">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;3.厨房：<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）地面：
						<%-- <select id="FJ7_9" name="f9009"   style="width:100px;" >
						<option value="瓷砖" <c:if test="${ptVo.f9009=='瓷砖'}">selected</c:if> >瓷砖</option>
						<option value="水泥抹面" <c:if test="${ptVo.f9009=='水泥抹面'}">selected</c:if> >水泥抹面</option>
						</select> --%>
						<select id="FJ7_9" name="f9009" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_9id" value="${ptVo.f9009 }" />
						;
						<input type="text" name="f9010" value="${ptVo.f9010}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 厨房地面">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）墙面：
						<%-- <select id="FJ7_11" name="f9011"   style="width:100px;" >
						<option value="瓷砖" <c:if test="${ptVo.f9011=='瓷砖'}">selected</c:if> >瓷砖</option>
						<option value="耐水腻子" <c:if test="${ptVo.f9011=='耐水腻子'}">selected</c:if> >耐水腻子</option>
						</select> --%>
						<select id="FJ7_11" name="f9011" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_11id" value="${ptVo.f9011 }" />
						;
						<input type="text" name="f9012" value="${ptVo.f9012}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 厨房墙面">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）顶棚：
						<%-- <select id="FJ7_13" name="f9013"   style="width:100px;" >
						<option value="水泥抹面" <c:if test="${ptVo.f9013=='水泥抹面'}">selected</c:if> >水泥抹面</option>
						<option value="石膏板吊顶" <c:if test="${ptVo.f9013=='石膏板吊顶'}">selected</c:if> >石膏板吊顶</option>
						</select> --%>
						<select id="FJ7_13" name="f9013" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_13id" value="${ptVo.f9013 }" />
						;
						<input type="text" name="f9014" value="${ptVo.f9014}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 厨房顶棚">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）厨具：
						<input type="text" name="f9015" value="${ptVo.f9015}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 厨房厨具">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;4.卫生间：<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）地面：
						<%-- <select id="FJ7_16" name="f9016"   style="width:100px;" >
						<option value="水泥抹面" <c:if test="${ptVo.f9016=='水泥抹面'}">selected</c:if> >水泥抹面</option>
						<option value="瓷砖" <c:if test="${ptVo.f9016=='瓷砖'}">selected</c:if> >瓷砖</option>
						</select> --%>
						<select id="FJ7_16" name="f9016" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_16id" value="${ptVo.f9016 }" />
						;
						<input type="text" name="f9017" value="${ptVo.f9017}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  卫生间地面">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）墙面：
						<%-- <select id="FJ7_18" name="f9018"   style="width:100px;" >
							<option value="耐水腻子" <c:if test="${ptVo.f9018=='耐水腻子'}">selected</c:if> >耐水腻子</option>
							<option value="瓷砖" <c:if test="${ptVo.f9018=='瓷砖'}">selected</c:if> >瓷砖</option>
						</select> --%>
						<select id="FJ7_18" name="f9018" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_18id" value="${ptVo.f9018 }" />
						;
						<input type="text" name="f9019" value="${ptVo.f9019}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 卫生间墙面">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）顶棚：
					    <%-- <select id="FJ7_20" name="f9020"  style="width:100px;" >
						<option value="水泥抹面" <c:if test="${ptVo.f9020=='水泥抹面'}">selected</c:if> >水泥抹面</option>
						<option value="石膏板吊顶" <c:if test="${ptVo.f9020=='石膏板吊顶'}">selected</c:if> >石膏板吊顶</option>
						</select> --%>
						<select id="FJ7_20" name="f9020" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_20id" value="${ptVo.f9020 }" />
						;
						<input type="text" name="f9021" value="${ptVo.f9021}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 卫生间顶棚">。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）卫生器具：
						<input type="text" name="f9022" value="${ptVo.f9022}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六 卫生间卫生器具">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;5.阳台：
						<%-- <select id="FJ7_23" name="f9023"  style="width:100px;" >
						<option value="塑钢封闭" <c:if test="${ptVo.f9023=='塑钢封闭'}">selected</c:if> >塑钢封闭</option>
						<option value="铝合金封闭" <c:if test="${ptVo.f9023=='铝合金封闭'}">selected</c:if> >铝合金封闭</option>
						<option value="断桥铝合金封闭" <c:if test="${ptVo.f9023=='断桥铝合金封闭'}">selected</c:if> >断桥铝合金封闭</option>
					    <option value="不封闭" <c:if test="${ptVo.f9023=='不封闭'}">selected</c:if> >不封闭</option>
						</select> --%>
						<select id="FJ7_23" name="f9023" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_23id" value="${ptVo.f9023 }" />
						;
						<input type="text" name="f9024" value="${ptVo.f9024}" size="70" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  阳台">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;6.电梯：<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）品牌：<input type="text" name="f9025" value="${ptVo.f9025}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  电梯品牌">；<br>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）型号：<input type="text" name="f9026" value="${ptVo.f9026}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  电梯型号">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;7.管道：
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9027" value="${ptVo.f9027}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  管道">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;8.窗户：
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9028" value="${ptVo.f9028}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  窗户">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;9.<input type="text" name="f9029" value="${ptVo.f9029}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  约定9">。
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;10.<input type="text" name="f9030" value="${ptVo.f9030}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件六  约定10">。
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件七   关于保修范围、保修期限和保修责任的约定</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，出卖人应当提供《住宅质量保证书》；该商品房为非住宅的，双方可参照《住宅质量保证书》中的内容对保修范围、保修期限和保修责任等进行约定。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房的保修期自房屋交付之日起计算，关于保修期限的约定不应低于《建设工程质量管理条例》第四十条规定的最低保修期限。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）保修项目、期限及责任的约定：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.地基基础和主体结构：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：
						<input type="text" name="f9101" value="${ptVo.f9101}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限">不得低于设计文件规定的该工程的合理使用年限）；<br>
						<input type="text" name="f9102" value="${ptVo.f9102}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限说明">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.屋面防水工程、有防水要求的卫生间、房间和外墙面的防渗漏：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：
						<input type="text" name="f9103" value="${ptVo.f9103}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限2">（不得低于5年）；<br>
						<input type="text" name="f9104" value="${ptVo.f9104}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限2说明">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.供热、供冷系统和设备：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：
						<input type="text" name="f9105" value="${ptVo.f9105}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限3">（不得低于2个采暖期、供冷期）；<br>
						<input type="text" name="f9106" value="${ptVo.f9106}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限3说明">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.电气管线、给排水管道、设备安装：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：
						<input type="text" name="f9107" value="${ptVo.f9107}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限4">（不得低于2年）；<br>
						<input type="text" name="f9108" value="${ptVo.f9108}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限4说明">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.装修工程：</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：
						<input type="text" name="f9109" value="${ptVo.f9109}" size="20" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限5">（不得低于2年）；<br>
						<input type="text" name="f9110" value="${ptVo.f9110}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  保修期限5说明">。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.<input type="text" name="f9111" value="${ptVo.f9111}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  补充6">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;7.<input type="text" name="f9112" value="${ptVo.f9112}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  补充7">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;8.<input type="text" name="f9113" value="${ptVo.f9113}" size="100" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=附件七  补充8">。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）其他约定：</td></tr>
					<tr><td align="center">
						<textarea type="input_text" name="f9114"  rows="15" style="width:100%" features="bmust=0,datatype=0,maxlength=2000,showtitle=附件七  （二）其他约定">${ptVo.f9114}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件八   关于质量担保的证明</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3908" rows="15" style="width:100%">${attachmap['3908']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td ><b>附件九   关于前期物业管理的约定</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.前期物业服务合同。</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.临时管理规约。</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3909" rows="15" style="width:100%">${attachmap['3909']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
		<tr><td>
				<table width=100%	>
					<tr><td ><b>附件十   出卖人关于遮挡或妨碍房屋正常使用情况的说明</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（如：该商品房公共管道检修口、柱子、变电箱等有遮挡或妨碍房屋正常使用的情况）</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3910" rows="15" style="width:100%">${attachmap['3910']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
					<tr><td ><b>附件十一   补充协议</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content3911" rows="15" style="width:100%">${attachmap['3911']}</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<div align="center">
				<c:if test="${opera !='view'}">
				<input type="button" onclick="doSave();" value="保存"/>
				<input type="button" name="back" value="返回" onclick="doclose();" />
				</c:if>
		       	</div>			
			</td></tr>
			<!-- 合同附件 end -->
	</table>
		<input type="hidden" name="fujiancount" value="9">   
		<div align="center">		
		</div>
			<div id="openDL"></div>
	</form>
	</body>
</html>
