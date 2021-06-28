<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
 <%
 	String basePath = request.getContextPath();
 %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<link href="../../css/style.css" type="text/css" rel="stylesheet">
		<link href="<%=basePath%>/css/laydate.css" rel="stylesheet" type="text/css" />
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
			form1.f0702.readOnly = false;
			form1.f0703.readOnly = false;
			form1.f0718.readOnly = false;
			form1.f0719.readOnly = false;
			//2
			form1.f0705.value = "";
			form1.f0706.value = "";
			form1.f0707.value = "";
			form1.f0708.value = "";
			form1.f0709.value = "";
			form1.f0727.value = "";
			form1.f0705.readOnly = true;
			form1.f0706.readOnly = true;
			form1.f0708.readOnly = true;
			form1.f0709.readOnly = true;
			//3
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0724.value = "";
			form1.f0738.value = "";
			form1.f0710.readOnly = true;
			form1.f0724.readOnly = true;
			
			//4
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0712.readOnly = true;
			form1.f0713.readOnly = true;
			
			//5
			form1.f0729.value = "";
			form1.f0731.value = "";
			form1.f0732.value = "";
			form1.f0729.readOnly = true;
			form1.f0731.readOnly = true;
			
			//6
			form1.f0734.value = "";
			form1.f0736.value = "";
			form1.f0737.value = "";
			form1.f0734.readOnly = true;
			form1.f0736.readOnly = true;
		}
		if(type == '2'){
			//alert(2);
			//1
			form1.f0702.value = "";
			form1.f0703.value = "";
			form1.f0704.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0726.value = "";
			form1.f0702.readOnly = true;
			form1.f0703.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0719.readOnly = true;
			//2
			form1.f0705.readOnly = false;
			form1.f0706.readOnly = false;
			form1.f0708.readOnly = false;
			form1.f0709.readOnly = false;
			//3
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0724.value = "";
			form1.f0738.value = "";
			form1.f0710.readOnly = true;
			form1.f0724.readOnly = true;
			
			//4
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0712.readOnly = true;
			form1.f0713.readOnly = true;
			
			//5
			form1.f0729.value = "";
			form1.f0731.value = "";
			form1.f0732.value = "";
			form1.f0729.readOnly = true;
			form1.f0731.readOnly = true;
			
			//6
			form1.f0734.value = "";
			form1.f0736.value = "";
			form1.f0737.value = "";
			form1.f0734.readOnly = true;
			form1.f0736.readOnly = true;
		}
		if(type == '3'){
			//alert(3);
			//1
			form1.f0702.value = "";
			form1.f0703.value = "";
			form1.f0704.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0726.value = "";
			form1.f0702.readOnly = true;
			form1.f0703.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0719.readOnly = true;
			//2
			form1.f0705.value = "";
			form1.f0706.value = "";
			form1.f0707.value = "";
			form1.f0708.value = "";
			form1.f0709.value = "";
			form1.f0727.value = "";
			form1.f0705.readOnly = true;
			form1.f0706.readOnly = true;
			form1.f0708.readOnly = true;
			form1.f0709.readOnly = true;
			//3
			form1.f0710.readOnly = false;
			form1.f0710.readOnly = false;
			form1.f0724.readOnly = false;
			
			//4
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0712.readOnly = true;
			form1.f0713.readOnly = true;
			
			//5
			form1.f0729.value = "";
			form1.f0731.value = "";
			form1.f0732.value = "";
			form1.f0729.readOnly = true;
			form1.f0731.readOnly = true;
			
			//6
			form1.f0734.value = "";
			form1.f0736.value = "";
			form1.f0737.value = "";
			form1.f0734.readOnly = true;
			form1.f0736.readOnly = true;
		}
		if(type == '4'){
			//alert(4);
			//1
			form1.f0702.value = "";
			form1.f0703.value = "";
			form1.f0704.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0726.value = "";
			form1.f0702.readOnly = true;
			form1.f0703.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0719.readOnly = true;
			//2
			form1.f0705.value = "";
			form1.f0706.value = "";
			form1.f0707.value = "";
			form1.f0708.value = "";
			form1.f0709.value = "";
			form1.f0727.value = "";
			form1.f0705.readOnly = true;
			form1.f0706.readOnly = true;
			form1.f0708.readOnly = true;
			form1.f0709.readOnly = true;
			//3
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0724.value = "";
			form1.f0738.value = "";
			form1.f0710.readOnly = true;
			form1.f0724.readOnly = true;
			
			//4
			form1.f0712.readOnly = false;
			form1.f0713.readOnly = false;
			form1.f0713.readOnly = false;
			
			//5
			form1.f0729.value = "";
			form1.f0731.value = "";
			form1.f0732.value = "";
			form1.f0729.readOnly = true;
			form1.f0731.readOnly = true;
			
			//6
			form1.f0734.value = "";
			form1.f0736.value = "";
			form1.f0737.value = "";
			form1.f0734.readOnly = true;
			form1.f0736.readOnly = true;
		}
		
		if(type == '5'){
			//alert(5);
			//1
			form1.f0702.value = "";
			form1.f0703.value = "";
			form1.f0704.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0726.value = "";
			form1.f0702.readOnly = true;
			form1.f0703.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0719.readOnly = true;
			//2
			form1.f0705.value = "";
			form1.f0706.value = "";
			form1.f0707.value = "";
			form1.f0708.value = "";
			form1.f0709.value = "";
			form1.f0727.value = "";
			form1.f0705.readOnly = true;
			form1.f0706.readOnly = true;
			form1.f0708.readOnly = true;
			form1.f0709.readOnly = true;
			//3
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0724.value = "";
			form1.f0738.value = "";
			form1.f0710.readOnly = true;
			form1.f0724.readOnly = true;
			
			//4
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0712.readOnly = true;
			form1.f0713.readOnly = true;
			
			//5
			form1.f0729.readOnly = false;
			form1.f0731.readOnly = false;
			
			//6
			form1.f0734.value = "";
			form1.f0736.value = "";
			form1.f0737.value = "";
			form1.f0734.readOnly = true;
			form1.f0736.readOnly = true;
		}
		
		if(type == '6'){
			//alert(5);
			//1
			form1.f0702.value = "";
			form1.f0703.value = "";
			form1.f0704.value = "";
			form1.f0718.value = "";
			form1.f0719.value = "";
			form1.f0726.value = "";
			form1.f0702.readOnly = true;
			form1.f0703.readOnly = true;
			form1.f0718.readOnly = true;
			form1.f0719.readOnly = true;
			//2
			form1.f0705.value = "";
			form1.f0706.value = "";
			form1.f0707.value = "";
			form1.f0708.value = "";
			form1.f0709.value = "";
			form1.f0727.value = "";
			form1.f0705.readOnly = true;
			form1.f0706.readOnly = true;
			form1.f0708.readOnly = true;
			form1.f0709.readOnly = true;
			//3
			form1.f0710.value = "";
			form1.f0711.value = "";
			form1.f0724.value = "";
			form1.f0738.value = "";
			form1.f0710.readOnly = true;
			form1.f0724.readOnly = true;
			
			//4
			form1.f0712.value = "";
			form1.f0713.value = "";
			form1.f0714.value = "";
			form1.f0712.readOnly = true;
			form1.f0713.readOnly = true;
			
			//5
			form1.f0729.value = "";
			form1.f0731.value = "";
			form1.f0732.value = "";
			form1.f0729.readOnly = true;
			form1.f0731.readOnly = true;
			
			//6
			form1.f0734.readOnly = false;
			form1.f0736.readOnly = false;
		}
	}
	
	//条款8选择
	function tk8_change(type){
		if(type == '1' || type ==''){
			//1
			form1.f0808.readOnly = true;
			//2
			form1.f0809.value = "";
			form1.f0810.value = "";
			form1.f0811.value = "";
			form1.f0812.value = "";
			form1.f0813.value = "";
			form1.f0814.value = "";
			form1.f0809.readOnly = true;
			form1.f0810.readOnly = true;
			form1.f0811.readOnly = true;
			form1.f0813.readOnly = true;
			form1.f0814.readOnly = true;
			//3
			form1.f0815.value = "";
			form1.f0817.value = "";
			form1.f0818.value = "";
			form1.f0819.value = "";
			form1.f0820.value = "";
			form1.f0821.value = "";
			form1.f0822.value = "";
			form1.f0823.value = "";
			form1.f0815.readOnly = true;
			form1.f0817.readOnly = true;
			form1.f0818.readOnly = true;
			form1.f0820.readOnly = true;
			form1.f0821.readOnly = true;
			form1.f0823.readOnly = true;
			//4
			form1.f0824.value = "";
			form1.f0824.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f0808.value = "";
			form1.f0808.readOnly = true;
			//2
			form1.f0809.readOnly = true;
			form1.f0810.readOnly = false;
			form1.f0811.readOnly = false;
			form1.f0813.readOnly = true;
			form1.f0814.readOnly = false;
			//3
			form1.f0815.value = "";
			form1.f0817.value = "";
			form1.f0818.value = "";
			form1.f0819.value = "";
			form1.f0820.value = "";
			form1.f0821.value = "";
			form1.f0822.value = "";
			form1.f0823.value = "";
			form1.f0815.readOnly = true;
			form1.f0817.readOnly = true;
			form1.f0818.readOnly = true;
			form1.f0820.readOnly = true;
			form1.f0821.readOnly = true;
			form1.f0823.readOnly = true;
			//4
			form1.f0824.value = "";
			form1.f0824.readOnly = true;
		}
		if(type == '3'){
			//1
			form1.f0808.value = "";
			form1.f0808.readOnly = true;
			//2
			form1.f0809.value = "";
			form1.f0810.value = "";
			form1.f0811.value = "";
			form1.f0812.value = "";
			form1.f0813.value = "";
			form1.f0814.value = "";
			form1.f0809.readOnly = true;
			form1.f0810.readOnly = true;
			form1.f0811.readOnly = true;
			form1.f0813.readOnly = true;
			form1.f0814.readOnly = true;
			//3
			form1.f0815.readOnly = false;
			form1.f0817.readOnly = true;
			form1.f0818.readOnly = false;
			form1.f0820.readOnly = false;
			form1.f0821.readOnly = false;
			form1.f0823.readOnly = false;
			//4
			form1.f0824.value = "";
			form1.f0824.readOnly = true;
		}
		if(type == '4'){
			//1
			form1.f0808.value = "";
			form1.f0808.readOnly = true;
			//2
			form1.f0809.value = "";
			form1.f0810.value = "";
			form1.f0811.value = "";
			form1.f0812.value = "";
			form1.f0813.value = "";
			form1.f0814.value = "";
			form1.f0809.readOnly = true;
			form1.f0810.readOnly = true;
			form1.f0811.readOnly = true;
			form1.f0813.readOnly = true;
			form1.f0814.readOnly = true;
			//3
			form1.f0815.value = "";
			form1.f0817.value = "";
			form1.f0818.value = "";
			form1.f0819.value = "";
			form1.f0820.value = "";
			form1.f0821.value = "";
			form1.f0822.value = "";
			form1.f0823.value = "";
			form1.f0815.readOnly = true;
			form1.f0817.readOnly = true;
			form1.f0818.readOnly = true;
			form1.f0820.readOnly = true;
			form1.f0821.readOnly = true;
			form1.f0823.readOnly = true;
			//4
			form1.f0824.readOnly = false;
		}
	}
	
	//条款9选择
	function tk9_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f0902.readOnly = false;
			form1.f0903.readOnly = false;
			form1.f0904.readOnly = false;
			form1.f0905.readOnly = false;
			form1.f0906.readOnly = false;
			form1.f0907.readOnly = false;
			//2
			form1.f0908.value = "";
			form1.f0908.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f0902.value = "";
			form1.f0903.value = "";
			form1.f0904.value = "";
			form1.f0905.value = "";
			form1.f0906.value = "";
			form1.f0907.value = "";
			form1.f0902.readOnly = true;
			form1.f0903.readOnly = true;
			form1.f0904.readOnly = true;
			form1.f0905.readOnly = true;
			form1.f0906.readOnly = true;
			form1.f0907.readOnly = true;
			
			//2
			form1.f0908.readOnly = false;
		}
	}
	
	//条款13选择
	function tk13_change(type){
		if(type == '1' || type ==''){
			//alert(1);
			//1
			form1.f1302.readOnly = false;
			form1.f1303.readOnly = false;
			form1.f1304.readOnly = false;
			form1.f1305.readOnly = false;
			form1.f1306.readOnly = false;
			form1.f1307.readOnly = false;
			
			//2
			form1.f1308.value = "";
			form1.f1308.readOnly = true;
		}
		if(type == '2'){
			//1
			form1.f1302.value = "";
			form1.f1303.value = "";
			form1.f1304.value = "";
			form1.f1305.value = "";
			form1.f1306.value = "";
			form1.f1307.value = "";
			form1.f1302.readOnly = true;
			form1.f1303.readOnly = true;
			form1.f1304.readOnly = true;
			form1.f1305.readOnly = true;
			form1.f1306.readOnly = true;
			form1.f1307.readOnly = true;
			
			//2
			form1.f1308.readOnly = false;
		}
	}
	
	//条款24选择
	function tk24_change(type){
		if(type == '1' || type ==''){
			form1.f2402.value = "";
			form1.f2402.readOnly = true;
		}
		if(type == '2'){
			form1.f2402.readOnly = false;
		}
	}
	
	$(function(){
		addN1_1();
		addN1_3();
		addN2_1();
		addN2_2();
		addN2_4();
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
		$("input[ name='f0703']").blur(function(){
	    	var money = $("input[ name='f0703']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0704' ]").val(chval);		        
	    });
		$("input[ name='f0706']").blur(function(){
	    	var money = $("input[ name='f0706']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0707' ]").val(chval);		        
	    });
		$("input[ name='f0710']").blur(function(){
	    	var money = $("input[ name='f0710']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0711' ]").val(chval);		        
	    });
		$("input[ name='f0713']").blur(function(){
	    	var money = $("input[ name='f0713']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0714' ]").val(chval);		        
	    });
	     $("input[ name='f0719']").blur(function(){
	    	var money = $("input[ name='f0719']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0726' ]").val(chval);		        
	    });
	    $("input[ name='f0709']").blur(function(){
	    	var money = $("input[ name='f0709']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0727' ]").val(chval);		        
	    });
	    $("input[ name='f0731']").blur(function(){
	    	var money = $("input[ name='f0731']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0732' ]").val(chval);		        
	    });
	    $("input[ name='f0736']").blur(function(){
	    	var money = $("input[ name='f0736']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0737' ]").val(chval);		        
	    });
	    $("input[ name='f0724']").blur(function(){
	    	var money = $("input[ name='f0724']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0738' ]").val(chval);		        
	    });
		$("input[ name='f0801']").blur(function(){
	    	var money = $("input[ name='f0801']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0802' ]").val(chval);		        
	    });
		$("input[ name='f0811']").blur(function(){
	    	var money = $("input[ name='f0811']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0812' ]").val(chval);		        
	    });
		$("input[ name='f0818']").blur(function(){
	    	var money = $("input[ name='f0818']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0819' ]").val(chval);		        
	    });
		$("input[ name='f0821']").blur(function(){
	    	var money = $("input[ name='f0821']").val();
	        var chval = convertCurrency(money);
	        $("input[ name='f0822' ]").val(chval);		        
	    });
		contractMoneyChoose(form1.f0701.value==""?"1":form1.f0701.value);
		tk8_change(form1.f0807.value==""?"1":form1.f0807.value);
		tk9_change(form1.f0901.value==""?"1":form1.f0901.value);
		tk13_change(form1.f1301.value==""?"1":form1.f1301.value);
		tk24_change(form1.f2401.value==""?"1":form1.f2401.value);
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
							}else{
								$('#N1_1').append('<option value='+option.name+'>'+option.name+'</option>');
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
							}else{
								$('#N1_3').append('<option value='+option.name+'>'+option.name+'</option>');
							}
						});
					}
				},
				error : function(){
					alert("查询出错！");
				}
			});
		}
		
		function addN2_1(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/system/getNewDictionaryJson.action',  
				data : 'dictype=DIC_31',
				dataType : "json",
				success : function(data){
					$('#N2_1').empty();
					var N1_1id = $('#N2_1id').val();
					if(data.length>0){
						$.each(data,function (index,option){
							if(option.name==N1_1id){
								$('#N2_1').append('<option value='+option.name+' selected>'+option.name+'</option>');
							}else{
								$('#N2_1').append('<option value='+option.name+'>'+option.name+'</option>');
							}
						});
					}
				},
				error : function(){
					alert("查询出错！");
				}
			});
		}
		
		function addN2_2(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/system/getNewDictionaryJson.action',  
				data : 'dictype=DIC_32',
				dataType : "json",
				success : function(data){
					$('#N2_2').empty();
					var N1_1id = $('#N2_2id').val();
					if(data.length>0){
						$.each(data,function (index,option){
							if(option.name==N1_1id){
								$('#N2_2').append('<option value='+option.name+' selected>'+option.name+'</option>');
							}else{
								$('#N2_2').append('<option value='+option.name+'>'+option.name+'</option>');
							}
						});
					}
				},
				error : function(){
					alert("查询出错！");
				}
			});
		}
		
		function addN2_4(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/system/getNewDictionaryJson.action',  
				data : 'dictype=DIC_33',
				dataType : "json",
				success : function(data){
					$('#N2_4').empty();
					var N1_1id = $('#N2_4id').val();
					if(data.length>0){
						$.each(data,function (index,option){
							if(option.name==N1_1id){
								$('#N2_4').append('<option value='+option.name+' selected>'+option.name+'</option>');
							}else{
								$('#N2_4').append('<option value='+option.name+'>'+option.name+'</option>');
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
							}else{
								$('#N4_1').append('<option value='+option.name+'>'+option.name+'</option>');
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
					$('#N8_18').append('<option value=></option>');
					var N1_1id = $('#N8_18id').val();
					if(data.length>0){
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
					$('#N8_7').append('<option value=></option>');
					var N1_1id = $('#N8_7id').val();
					if(data.length>0){
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
		
		function doSubmit(){
		disableButton();
		 if(!CheckDataValid(form1)){
				enableButton();
				return;
			}
			if(!doSelectCheck()){
				enableButton();
				return;
			}
			var status=$("#status").val();
			if(status=='0'){	//编辑状态(草签)
				if(confirm("确定要提交吗?")){
					$("#cmd").val('editSubmit');
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontract/signXGCheck.action',  
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
			}
			if(status=='1'){	//待签状态
				if(confirm("确认该合同吗?")){
					$("#cmd").val('waitingSubmit');
					//确认合同验证
					var contractID = $('#contractID').val();
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontract/confirmCheck.action',  
						data : $("#form1").serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								submitContract();
							}else{
								alert(data[0].msg);
								return false;
							}
						},
						error : function(){
							alert("合同确认失败！");
						}
					});
				}
			}
			enableButton();
		}

		function doSave(){
			if(!doSelectCheck()){
				enableButton();
				return;
			}
			if(!CheckDataValid(form1)){
				enableButton();
				return;
			}
			
			var status=$("#status").val();
			if(status=='1'){//待签状态(待签)
				//检查是否含有已签字的电子合同
				var contractID = $('#contractID').val();
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/outer/signcontract/checkContractPdfSign.action',  
					data : {"contractID":contractID},
					dataType : "json",
					success : function(data){
						if(data[0].result == '1'){//没有电子合同，没有签字的
							saveContract();
						}else{
							if(data[0].result == '2'){//有合同并签字的
								if(confirm("该合同已生成电子合同并签字，点保存会删除已签字电子合同，确认继续？")){
									if(confirm("确认继续删除已签字电子合同？")){
										//删除电子合同，清空签字信息。
										deleteSignContract(contractID,1);
									}
								}
							}else if(data[0].result == '3'){//有合同未签字的
								if(confirm("该合同已生成电子合同，点保存会删除电子合同，确认继续？")){
									if(confirm("确认继续删除电子合同？")){
										//删除电子合同。
										deleteSignContract(contractID,0);
									}
								}
							}else if(data[0].result == '3'){
								alert(data[0].msg);
							}
						}
					},
					error : function(){
						alert("检查是否有电子合同出错！");
					}
				});
			}else{
				saveContract();
			}
		}
		
		function deleteSignContract(contractID,flag){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontract/deleteSignContract.action',  
				data : {"contractID":contractID,"flag":flag},
				dataType : "json",
				success : function(data){
					if(data[0].result=='1'){
						saveContract();
					}else{
						alert(data[0].msg);
					}
				},
				error : function(){
					alert("删除电子合同出错！");
				}
			});
		}
		
		function  saveContract(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontract/saveSellContract.action',  
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						alert(data[0].msg);
						if($('#cmd').val()!=''){
							doClose();
						}
					}else{
						alert(data[0].msg);
					}
					enableButton();
				},
				error : function(){
					alert("操作出错！");
				}
			});
		}
		function  submitContract(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : '<%=basePath%>/outer/signcontract/updateSellContract.action',  
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=='success'){
						alert(data[0].msg);
						if($('#cmd').val()!=''){
							doClose();
						}
					}else{
						alert(data[0].msg);
					}
					enableButton();
				},
				error : function(){
					alert("操作出错！");
				}
			});
		}

		function doValidate(){
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
			return pass;
		}

		function doClose(){
			var status=$("#status").val();
			if(status=='0'){	//编辑状态(草签)
				parent.window.location="<%=basePath%>/outer/signcontract/queryEditContract.action";
			}
			if(status=='1'){	//待签状态
				parent.window.location="<%=basePath%>/outer/signcontract/queryWaitingConfirmContract.action";
			}
		}
		
		function doReturn(){
		disableButton();
			if(confirm('确定要退回该合同吗？')){
				parent.window.location="<%=basePath%>/outer/signcontract/returnContract.action?contractID="+${contractID};
				enableButton();
			}
			else{
			  enableButton();
			}
		}
		
   		//金额只能数字和带小数的数字
		function clearNoNum(obj) {  
		    	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
		        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
		        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
		        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
		        //obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  
		}	

		function gotoSave(){
			$('#cmd').val('');
			disableButton();
			doSave();
		}

		//选择后必填检查
		function doSelectCheck(){
			//第七条
			if(form1.f0701.value=='1'){
				if(form1.f0702.value==''){
					alert("第七条 选择1后内容必填！");
					form1.f0702.focus();
					return false;
				}
				if(form1.f0703.value==''){
					alert("第七条 选择1后内容必填！");
					form1.f0703.focus();
					return false;
				}
				if(form1.f0704.value==''){
					alert("第七条 选择1后内容必填！");
					form1.f0704.focus();
					return false;
				}
			}else if(form1.f0701.value=='2'){
				if(form1.f0705.value==''){
					alert("第七条 选择2后内容必填！");
					form1.f0705.focus();
					return false;
				}
				if(form1.f0706.value==''){
					alert("第七条 选择2后内容必填！");
					form1.f0706.focus();
					return false;
				}
				if(form1.f0707.value==''){
					alert("第七条 选择2后内容必填！");
					form1.f0707.focus();
					return false;
				}
				if(form1.f0708.value==''){
					alert("第七条 选择2后内容必填！");
					form1.f0708.focus();
					return false;
				}
				if(form1.f0709.value==''){
					alert("第七条 选择2后内容必填！");
					form1.f0709.focus();
					return false;
				}
			}else if(form1.f0701.value=='3'){
				if(form1.f0710.value==''){
					alert("第七条 选择3后内容必填！");
					form1.f0710.focus();
					return false;
				}
				if(form1.f0711.value==''){
					alert("第七条 选择3后内容必填！");
					form1.f0711.focus();
					return false;
				}
			}else if(form1.f0701.value=='4'){
				if(form1.f0712.value==''){
					alert("第七条 选择4后内容必填！");
					form1.f0712.focus();
					return false;
				}
				if(form1.f0713.value==''){
					alert("第七条 选择4后内容必填！");
					form1.f0713.focus();
					return false;
				}
				if(form1.f0714.value==''){
					alert("第七条 选择4后内容必填！");
					form1.f0714.focus();
					return false;
				}
			}else if(form1.f0701.value=='5'){
				if(form1.f0729.value==''){
					alert("第七条 选择5后内容必填！");
					form1.f0729.focus();
					return false;
				}
				if(form1.f0731.value==''){
					alert("第七条 选择5后内容必填！");
					form1.f0731.focus();
					return false;
				}
			}else if(form1.f0701.value=='6'){
				if(form1.f0734.value==''){
					alert("第七条 选择6后内容必填！");
					form1.f0734.focus();
					return false;
				}
				if(form1.f0736.value==''){
					alert("第七条 选择6后内容必填！");
					form1.f0736.focus();
					return false;
				}
			}
			
			//第八条
			if(form1.f0807.value=='1'){
				if(form1.f0808.value==''){
					alert("第八条 选择1后内容必填！");
					form1.f0808.focus();
					return false;
				}
			}else if(form1.f0807.value=='2'){
				if(form1.f0809.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0809.focus();
					return false;
				}
				if(form1.f0810.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0810.focus();
					return false;
				}
				if(form1.f0811.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0811.focus();
					return false;
				}
				if(form1.f0812.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0812.focus();
					return false;
				}
				if(form1.f0813.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0813.focus();
					return false;
				}
				if(form1.f0814.value==''){
					alert("第八条 选择2后内容必填！");
					form1.f0814.focus();
					return false;
				}
			}else if(form1.f0807.value=='3'){
				if(form1.f0815.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0815.focus();
					return false;
				}
				if(form1.f0817.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0817.focus();
					return false;
				}
				if(form1.f0818.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0818.focus();
					return false;
				}
				if(form1.f0819.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0819.focus();
					return false;
				}
				if(form1.f0820.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0820.focus();
					return false;
				}
				if(form1.f0821.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0821.focus();
					return false;
				}
				if(form1.f0822.value==''){
					alert("第八条 选择3后内容必填！");
					form1.f0822.focus();
					return false;
				}
				if(form1.f0823.value==''){
					alert("第七条 选择3后内容必填！");
					form1.f0823.focus();
					return false;
				}
			}else if(form1.f0807.value=='4'){
				if(form1.f0824.value==''){
					alert("第八条 选择4后内容必填！");
					form1.f0824.focus();
					return false;
				}
			}
		
			//第九条
			if(form1.f0901.value=='1'){
				if(form1.f0902.value==''){
					alert("第九条 选择1后内容必填！");
					form1.f0902.focus();
					return false;
				}
				if(form1.f0903.value==''){
					alert("第九条 选择1后内容必填！");
					form1.f0903.focus();
					return false;
				}
				if(form1.f0904.value==''){
					alert("第九条 选择1后内容必填！");
					form1.f0904.focus();
					return false;
				}
				if(form1.f0905.value==''){
					alert("第九条 选择1后内容必填！");
					form1.f0905.focus();
					return false;
				}
				if(form1.f0906.value==''){
					alert("第八条 选择1后内容必填！");
					form1.f0906.focus();
					return false;
				}
				if(form1.f0907.value==''){
					alert("第九条 选择1后内容必填！");
					form1.f0907.focus();
					return false;
				}
			}else if(form1.f0901.value=='2'){
				if(form1.f0908.value==''){
					alert("第九条 选择2后内容必填！");
					form1.f0908.focus();
					return false;
				}
			}
			
			//第十三条
			if(form1.f1301.value=='1'){
				if(form1.f1302.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1302.focus();
					return false;
				}
				if(form1.f1303.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1303.focus();
					return false;
				}
				if(form1.f1304.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1304.focus();
					return false;
				}
				if(form1.f1305.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1305.focus();
					return false;
				}
				if(form1.f1306.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1306.focus();
					return false;
				}
				if(form1.f1307.value==''){
					alert("第十三条 选择1后内容必填！");
					form1.f1307.focus();
					return false;
				}
			}else if(form1.f1301.value=='2'){
				if(form1.f1308.value==''){
					alert("第十三条 选择2后内容必填！");
					form1.f1308.focus();
					return false;
				}
			}
			
			//第二十四条
			if(form1.f2401.value=='2'){
				if(form1.f2402.value==''){
					alert("第二十四条 选择2后内容必填！");
					form1.f2402.focus();
					return false;
				}
			}
			
			return true;
		}			
		
		function disableButton(){
			document.form1.submit.disabled=true;
			document.form1.save.disabled=true;
			var status=$("#status").val();
			if(status==1){
			//document.form1.return.disabled=true;
			}
		}
		
		function enableButton(){
			document.form1.submit.disabled=false;
			document.form1.save.disabled=false;
			var status=$("#status").val();
			if(status==1){
			//document.form1.return.disabled=false;
			}
		}					
</SCRIPT>
	</head>
	<body class="openDlg_style">
	<form name="form1" id="form1" method="post" action="">
		<input type="hidden" id="contractID" name="contractID" value="<%=request.getParameter("contractID") %>" />
		<input type="hidden" id="type" name="type" value="2" />
		<input type="hidden" id="pid" name="projectID" value="${cdvo.projectID}"/>
		<input type="hidden" id="sid" name="startID" value="${cdvo.startID}"/>
		<input type="hidden" id="houseID" name="houseID" value="${cdvo.houseID}"/>
		<input type="hidden" id="presellID" name="presellID" value="${cdvo.presellID}"/>
		<input type="hidden" id="cmd" name="cmd" value="" />
		<input type="hidden" id="status" value="${cdvo.status}" />
		<input type="hidden" id="maxPrice" value="${maxPrice}" />
		<input type="hidden" name="earnestID" value="${cs.earnestID}" />
		<center ><b STYLE="font-size:15pt" >青岛市商品房买卖合同（现售）</b></center>
		<center STYLE="font-size:12pt" >（合同号：${contractID }）</center>
		<br><br>
		<table width="95%" align="center">
			<tr><td>
					<table width=100%	>
						<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中 华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商 达成一致意见，签订本商品房买卖合同。</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td align="center">&nbsp;&nbsp;&nbsp;&nbsp;<b>第一章	合同当事人</b></td></tr>
		
	
			<tr><td>
				<table width=100%>
					<tr><td width=100>甲方(出卖人):</td>
							<td><input type="text"  size="50"  name="sellerName" maxlength="100" class="input3" readonly value="${svo.sellerName}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>住所：</td>
						<td><input type="text" name="sellerAddress" size="30" maxlength="100" class="input3" readonly value="${svo.sellerAddress}"></td>
						<td width=100>邮编：</td>
						<td><input type="text" name="sellerPostcode" size="30"  maxlength="6" class="input3"  readonly value="${svo.sellerPostcode}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>营业执照号码：</td>
						<td><input type="text" name="sellerBizcardcode" maxlength="50" class="input3" readonly value="${svo.sellerBizcardcode}"></td>
						<td width=100>资格证书号码：</td>
						<td><input type="text" name="sellerCertcode" maxlength="50" class="input3"  readonly value="${svo.sellerCertcode}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>法定代表人：</td>
						<td><input readonly type="text" name="sellerDelegate" maxlength="100" class="input3"  value="${svo.sellerDelegate}"></td>
						<td width=100>联系电话：</td>
						<td><input type="text" name="sellerDlgCall" maxlength="20" class="input2"  value="${svo.sellerDlgCall}"></td>
					</tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr>
						<td width=100>委托代理人：</td>
						<td><input type="text" name="sellerProxy" maxlength="50" class="input2"   value="${svo.sellerProxy}"></td>
						<td width=100>联系电话：</td>
						<td><input type="text" name="sellerProxyCall" maxlength="20" class="input2"   value="${svo.sellerProxyCall}">
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
						<td><input  value="<%=buyer.getAttribute("buyer_province_dict_name") %>" type="text" name="" maxlength="100" class="input3" readonly></td>
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
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第二章  商品房基本状况<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第一条  项目建设依据。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人以
						<input type="text" name="f0101" size="40" maxlength="100" class="input2" value="${cs.f0101}"  features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  土地取得方式"><font color="red">*</font>
						方式取得坐落于<input type="text" name="f0102" size="40" maxlength="100" class="input2" value="${cs.f0102}"  features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  地块号"><font color="red">*</font>地块的建设用地使用权。
						该地块
						<input type="text" name="f0103" size="40" maxlength="100" class="input2" value="${cs.f0103}"  features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  土地证类型"><font color="red">*</font>
						为<input type="text" name="f0104" size="30" value="${cs.f0104 }" maxlength="100" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  地块证号"><font color="red">*</font>，
						土地使用权面积为<input type="text" name="f0105" value="${cs.f0105 }" size="15" maxlength="50" class="input2" features="bmust=1,datatype=1,maxlength=50,showtitle=第一条  土地面积"><font color="red">*</font>平方米。
						买受人购买的商品房（以下简称该商品房）所占用的土地用途为<input type="text" name="f0106" size="20" value="${cs.f0106 }"  maxlength="50" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  土地用途"><font color="red">*</font>，
						土地使用权终止日期为
						<input type="text" class="input2" name="f0107" id="f0107" value="${cs.f0107 }" features="bmust=0,datatype=0,maxlength=50,showtitle=第一条  土地使用权终止日期"/>
						。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.出卖人经批准，在上述地块上建设的商品房项目核准名称为
						<input type="text" name="f0108" size="30" value="${cs.f0108}" maxlength="50" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  项目名称"><font color="red">*</font>，
						建设工程规划许可证号为<input type="text" name="f0109" size="40" value="${cs.f0109}"  maxlength="100" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  建设工程规划许可证号"><font color="red">*</font>，
						建筑工程施工许可证号为<input type="text" name="f0110" size="40"  value="${cs.f0110}" maxlength="100" class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第一条  建筑工程施工许可证号"><font color="red">*</font>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二条  销售依据。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房已取得不动产登记证明号为
						<input type="text" name="f0203" size="30" maxlength="50" value="${cs.f0203}"  class="input3" readonly features="bmust=1,datatype=0,maxlength=50,showtitle=第二条  不动产登记证明号"><font color="red">*</font>，
						房屋登记机构为
						<input type="text" name="f0205" size="30" maxlength="50" value="${cs.f0205}"  class="input3" readonly features="bmust=1,datatype=0,maxlength=50,showtitle=第二条  房屋登记机构"><font color="red">*</font>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第三条  商品房基本情况。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房的规划用途为<input type="text" name="f0301" size="20" maxlength="50" value="${cs.f0301 }" readonly  class="input3" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  用途"><font color="red">*</font>。
					</td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房所在建筑物的主体结构为<input type="text" name="f0302" size="20" maxlength="50" value="${cs.f0302}" readonly  class="input3" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  主体结构"><font color="red">*</font>，
							建筑总层数为<input type="text" name="f0303" size="10" maxlength="50" value="${cs.f0303}"  class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  建筑总层数"><font color="red">*</font>层，
							其中地上<input type="text" name="f0304" size="10" maxlength="50" value="${cs.f0304}"   class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  地上"><font color="red">*</font>层，
							地下<input type="text" name="f0305" size="10" maxlength="50" value="${cs.f0305}"   class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  地下"><font color="red">*</font>层
					</td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;3.该商品房为第一条规定项目中的<input type="text" name="f0306" size="50" maxlength="100" value="${cs.f0306 }"   class="input3" readonly features="bmust=1,datatype=0,maxlength=100,showtitle=第三条  房屋坐落"><font color="red">*</font>
						。该商品房的平面图见附件一。
					</td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;4.该商品房的房产测绘机构为<input type="text" name="f0307" size="30" maxlength="50" value="${cs.f0307}"   class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  测绘机构"><font color="red">*</font>，
							其实测建筑面积共<input type="text" name="f0308" size="15" maxlength="50" value="${cs.f0308}" readonly   class="input3" features="bmust=1,datatype=1,maxlength=50,showtitle=第三条  实测建筑面积"><font color="red">*</font>平方米，
							其中套内建筑面积<input type="text" name="f0309" size="15" maxlength="50" value="${cs.f0309}" readonly  class="input3" features="bmust=1,datatype=1,maxlength=50,showtitle=第三条  套内建筑面积"><font color="red">*</font>平方米，
							分摊共有建筑面积<input type="text" name="f0310" size="15" maxlength="50" value="${cs.f0310}" readonly  class="input3" features="bmust=1,datatype=1,maxlength=50,showtitle=第三条  分摊共有建筑面积"><font color="red">*</font>平方米。该商品房共用部位见附件二。
					</td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房层高为<input type="text" name="f0311" size="10" maxlength="50" value="${cs.f0311}" onkeyup="clearNoNum(this)"  class="input2" features="bmust=1,datatype=0,maxlength=50,showtitle=第三条  商品房层高"><font color="red">*</font>米，
							有<input type="text" name="f0312" size="10" maxlength="50" value="${cs.f0312}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  阳台"><font color="red">*</font>个阳台，
							其中<input type="text" name="f0313" size="10" maxlength="50" value="${cs.f0313}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  封闭式阳台"><font color="red">*</font>个阳台为封闭式，
							<input type="text" name="f0314" size="10" maxlength="50" value="${cs.f0314}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第三条  非封闭式阳台"><font color="red">*</font>个阳台为非封闭式。阳台是否封闭以规划设计文件为准。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第四条  抵押情况。</b></td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;与该商品房有关的抵押情况为
							<select id="N4_1" name="f0401" style="width:100px;"></select>
							<input type="hidden" id="N4_1id" value="${cs.f0401 }" />
						，
						抵押人：<input type="text" name="f0402" size="30" maxlength="50" value="${cs.f0402}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第四条  抵押人">，
						抵押权人:  <input type="text" name="f0403" size="30" maxlength="50" value="${cs.f0403}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第四条 抵押权人">， 
						抵押登记机构：<input type="text" name="f0404" size="30" maxlength="50" value="${cs.f0404}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第四条 抵押登记机构">，
						抵押登记日期：
						<input type="text" class="demo-input" name="f0405" id="f0405" placeholder="" value="${cs.f0405 }" />
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0405' //指定元素
							});
						</script>	
						，
						债务履行期限：<input type="text" name="f0406" size="30" maxlength="50" value="${cs.f0406}"   class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第四条 债务履行期限">。
						抵押权人同意该商品房转让的证明及关于抵押的相关约定见附三。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第五条  租赁情况。</b></td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;该商品房的租赁情况为
						<select id="N5_1" name="f0501" style="width:100px;" ></select>
						<input type="hidden" id="N5_1id" value="${cs.f0501 }" />
						，
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;出卖人已将该商品房出租，
						<select id="N5_2" name="f0502" style="width:100px;" ></select>
						<input type="hidden" id="N5_2id" value="${cs.f0502 }" />
						，
					</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;租赁期限：从
						<input type="text" class="demo-input" name="f0503" id="f0503" placeholder="" value="${cs.f0503 }" readonly="readonly"/>
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0503' //指定元素
							});
						</script>
							至
						<input type="text" class="demo-input" name="f0504" id="f0504" placeholder="" value="${cs.f0504 }" readonly="readonly"/>
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0504' //指定元素
							});
						</script>	
							。出卖人与买受人经协商一致，自本合同约定的交付日至租赁期限届满期间的房屋收益归
						<select id="N5_5" name="f0505" style="width:100px;" ></select>
						<input type="hidden" id="N5_5id" value="${cs.f0505 }" />
						所有。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f0506" size="100" value="${cs.f0506}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第五条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人提供的承租人放弃优先购买权的声明见附件四。 
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第六条  房屋权利状况承诺。</b></td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人对该商品房享有合法权利；</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房没有出售给除本合同买受人以外的其他人；</td></tr>
					<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;3.该商品房没有司法查封或其他限制转让的情况；</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.<input type="text" name="f0601" size="50" value="${cs.f0601}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第六条 4">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.<input type="text" name="f0602" size="50" value="${cs.f0602}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第六条 5">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;如该商品房权利状况与上述情况不符，导致不能完成房屋所有权转移登记的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，
						按照<input type="text" name="f0603" size="15" value="${cs.f0603}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第六条 ">%(不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付
						<select id="N6_4" name="f0604" style="width:100px;"></select>
						<input type="hidden" id="N6_4id" value="${cs.f0604 }" />
						的赔偿金。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第三章  商品房价款<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第七条 计价方式与价款。</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;出卖人与买受人按照下列第
						<select name="f0701" onChange="contractMoneyChoose(this.value);">
							<option value="1" <c:if test="${cs.f0701 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f0701 eq 2  }">selected</c:if>>2</option>
							<option value="3" <c:if test="${cs.f0701 eq 3  }">selected</c:if>>3</option>
							<option value="4" <c:if test="${cs.f0701 eq 4  }">selected</c:if>>4</option>
							<option value="5" <c:if test="${cs.f0701 eq 5  }">selected</c:if>>5</option>
						    <option value="6" <c:if test="${cs.f0701 eq 6  }">selected</c:if>>6</option>
						</select>种方式计算该商品房价款：
					</td></tr>
				</table>
			</td></tr>
			<tr ><td >&nbsp;&nbsp;&nbsp;&nbsp;1.按照套内建筑面积计算，该商品房单价为每平方米
				<select name="f0715">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0702" value="${cs.f0702}" size="15" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积单价">元，
				总价款为
				<select name="f0716">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0703" value="${cs.f0703}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  套内建筑面积总金额">元
				（大写<input type="text" name="f0704" size="50" value="${cs.f0704}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  套内建筑面积总金额大写">元整）。
				该商品房装修价格为每平方米
				<select name="f0717">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0718" value="${cs.f0718}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  装修标准">元，
				装修总价为人民币<input type="text" name="f0719" size="50" value="${cs.f0719}" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  装修总价">元
				（大写<input type="text" id="f0726" name="f0726" size="50" value="${cs.f0726}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  套内装修总价总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.按照建筑面积计算，该商品房单价（不包含房屋装修）为每平方米
				<select name="f0720">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0705" value="${cs.f0705}" size="15" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积单价">元，
				总价款（不包含房屋装修）为
				<select name="f0721">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0706" size="30" value="${cs.f0706}" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积总金额">元
				（大写<input type="text" name="f0707" size="50" maxlength="50" value="${cs.f0707}" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  建筑面积总金额大写">元整）。
				该商品房装修价格为每平方米
				<select name="f0722">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0708" value="${cs.f0708} " size="15" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积 装修单价">元，
				装修总价为人民币<input type="text" name="f0709" value="${cs.f0709}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  建筑面积  装修总价">元
				（大写<input type="text" id="f0727" name="f0727" size="50" value="${cs.f0727}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  建筑面积装修总价总金额大写">元整）。				
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.按照套计算，该商品房总价款（不包含房屋装修）为
				<select name="f0723">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0710" value="${cs.f0710}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  按套总金额">元
				（大写<input type="text" name="f0711" size="50" value="${cs.f0711}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  按套总金额大写">元整）。
				装修总价为人民币<input type="text" name="f0724" size="50" value="${cs.f0724}" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  装修总价">元
				（大写<input type="text" id="f0738" name="f0738" size="50" value="${cs.f0738}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  按套装修总价总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.按照
				<input type="text" name="f0712" size="50" value="${cs.f0712}" maxlength="50"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  4">计算，
				该商品房总价款为
				<select name="f0725">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0713" value="${cs.f0713}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第六条  4中总金额">元
				（大写<input type="text" name="f0714" size="50" value="${cs.f0714}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第六条  4中总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;5.按照套内建筑面积计算，该商品房单价为每平方米
				<select name="f0728">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0729" value="${cs.f0729}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  计价方式5 单价">元，
				总价款为
				<select name="f0730">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" id="f0731" name="f0731" value="${cs.f0731}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  计价方式5 总金额">元
				（大写<input type="text" id="f0732" name="f0732" size="50" value="${cs.f0732}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  计价方式5 总金额大写">元整）。
			</td></tr>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;6.按照建筑面积计算，该商品房单价为每平方米
				<select name="f0733">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" name="f0734" value="${cs.f0734}" size="15" maxlength="50"  class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  计价方式6 单价">元，
				总价款为
				<select name="f0735">
					<option value="人民币">人民币</option>
				</select>（币种）<input type="text" id="f0736" name="f0736" value="${cs.f0736}" size="30" maxlength="50" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第七条  计价方式6 总金额">元
				（大写<input type="text" id="f0737" name="f0737" size="50" value="${cs.f0737}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第七条  计价方式6 总金额大写">元整）。
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第八条 付款方式及期限</b></td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（一）签订本合同前，买受人已向出卖人支付定金
					<select name="f0825">
						<option value="人民币">人民币</option>
					</select>
					（币种）<input type="text" name="f0801" value="${cs.f0801}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第八条  订金金额">元
					（大写<input type="text" name="f0802" size="50" value="${cs.f0802}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条   订金金额大写">元整），
					该定金于<input type="text" name="f0803" value="${cs.f0803}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第八条">
						<select id="N8_5" name="f0804" style="width:100px;" onchange="form1.f0803.value=this.value;"></select>
						<input type="hidden" id="N8_5id" value="${cs.f0804 }" />时
						<input type="text" name="f0805" value="${cs.f0805}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第八条">
						<select id="N8_7" name="f0806" style="width:100px;" onchange="form1.f0805.value=this.value;"></select>
						<input type="hidden" id="N8_7id" value="${cs.f0806 }" />
						商品房价款。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（二）买受人采取下列第
						<select name="f0807" onchange="tk8_change(this.value);">
							<option value="1" <c:if test="${cs.f0807 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f0807 eq 2  }">selected</c:if>>2</option>
							<option value="3" <c:if test="${cs.f0807 eq 3  }">selected</c:if>>3</option>
							<option value="4" <c:if test="${cs.f0807 eq 4  }">selected</c:if>>4</option>
						</select>种方式付款：
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;1.一次性付款。买受人应当在
						<input type="text" class="demo-input" name="f0808" id="f0808" placeholder="" value="${cs.f0808 }" />
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0808' //指定元素
							});
						</script>
						日前支付该商品房全部价款。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.分期付款。买受人应当在
						<input type="text" class="demo-input" name="f0809" id="f0809" placeholder="" value="${cs.f0809 }" />
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0809' //指定元素
							});
						</script>
						日前分
						<input type="text" name="f0810" value="${cs.f0810}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  买受人付款方式2分期数">期支付该商品房全部价款，
						首期房价款<select name="f0826">
									<option value="人民币">人民币</option>
								</select>
						（币种）<input type="text" name="f0811" value="${cs.f0811 }" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第八条  买受人付款方式2首期房价款">元
						（大写<input type="text" name="f0812" size="50" value="${cs.f0812}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条   买受人付款方式2首期房价款金额大写">元整），
						应当于
						<input type="text" class="demo-input" name="f0813" id="f0813" placeholder="" value="${cs.f0813 }" />
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0813' //指定元素
							});
						</script>
						日前支付。
						<br><textarea class="input_text" name="f0814" rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第八条  买受人付款方式2补充">${cs.f0814}</textarea>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;3.贷款方式付款：
						<input type="text" name="f0815" value="${cs.f0815}" size="20" maxlength="20"  class="input2" features="bmust=0,datatype=0,maxlength=20,showtitle=>第八条 3.贷款方式付款">
						<select id="N8_18" name="f0816" style="width:100px;" ></select>
						<input type="hidden" id="N8_18id" value="${cs.f0816 }" />。
						买受人应当于
						<input type="text" class="demo-input" name="f0817" id="f0817" placeholder="" value="${cs.f0817 }" />
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f0817' //指定元素
							});
						</script>
						日前
						支付首期房价款<select name="f0827">
										<option value="人民币">人民币</option>
								  </select>
						（币种）<input type="text" name="f0818" value="${cs.f0818}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第八条  买受人付款方式3首期房价款">元
						（大写<input type="text" name="f0819" size="50" value="${cs.f0819}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条   买受人付款方式3首期房价款金额大写">元整），
						占全部房价款的<input type="text" name="f0820" value="${cs.f0820}" size="5" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  买受人付款方式3首付房价款占比"> ％。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;余款<select name="f0828">
								<option value="人民币">人民币</option>
							</select>
						（币种）<input type="text" name="f0821" value="${cs.f0821}" size="30" maxlength="50" onkeyup="clearNoNum(this)" class="input2" features="bmust=0,datatype=1,maxlength=50,showtitle=第八条  买受人付款方式3余款">元
						（大写<input type="text" name="f0822" size="50" value="${cs.f0822}" maxlength="50" readonly class="input3" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条   买受人付款方式3余款金额大写">元整），
						向<input type="text" name="f0823" value="${cs.f0823}" size="50" maxlength="100"  class="input2" features="bmust=0,datatype=0,maxlength=50,showtitle=第八条  买受人付款方式3贷款机构">（贷款机构）申请贷款支付。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;4.其他方式：
						<textarea class="input_text" name="f0824" rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第八条  买受人付款方式4其他">${cs.f0824}</textarea>
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;该商品房价款的计价方式、总价款、付款方式及期限的具体约定见附件五。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第九条  逾期付款责任</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;除不可抗力外，买受人未按照约定时间付款的，双方同意按照下列第
						<select name="f0901" onchange="tk9_change(this.value);">
							<option value="1" <c:if test="${cs.f0901 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f0901 eq 2  }">selected</c:if>>2</option>
						</select>种方式处理：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.按照逾期时间，分别处理（（1）和（2）不作累加）
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）逾期在<input type="text" name="f0902" size="15" value="${cs.f0902}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第九条 逾期1">日之内，
						买受人按日计算向出卖人支付逾期应付款万分之<input type="text" name="f0903" size="15" value="${cs.f0903}" maxlength="13" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=13,showtitle=第九条 逾期1 违约金">的违约金。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）逾期超过<input type="text" name="f0904" size="15" value="${cs.f0904}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第九条 逾期2">日（该期限应当与本条第（1）项中的期限相同）后，出卖人有 权解除合同。出卖人解除合同的，应当书面通知买受人。
						买受人应当自解除合同通知送达之日起<input type="text" name="f0905" size="15" value="${cs.f0905}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第九条 逾期2">日内按照累计应付款的
						<input type="text" name="f0906" size="15" value="${cs.f0906}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第九条 逾期2 违约金">%向出卖人支付违约金，同时，出卖人退还买受人已付全部房款（含已付贷款部分）。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人不解除合同的，买受人按日计算向出卖人支付逾期应付款万分之<input type="text" name="f0907" size="15" value="${cs.f0907}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第九条 ">（该比率不低于第（1）项中的比率）的违约金。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;本条所称逾期应付款是指依照第八条及附件五约定的到期应付款与该期实际已付款的差额；采取分期付款的，按照相应的分期应付款与该期的实际已付款的差额确定。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2.
						<textarea class="input_text" name="f0908"  rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第九条 处理方式2">${cs.f0908}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第四章  商品房交付条件与交付手续<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十条 商品房交付条件</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房交付时应当符合下列第1、2、<input type="text" name="f1001" size="15" value="${cs.f1001}" maxlength="10" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十条 ">项所列条件： 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房已取得建设工程竣工验收备案证明文件； 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房已取得房屋测绘报告；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1002" size="50" value="${cs.f1002}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十条 3">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.<input type="text" name="f1003" size="50" value="${cs.f1003}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十条 4">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，出卖人还需提供《住宅使用说明书》和《住宅质量保证书》。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十一条  商品房相关设施设备交付条件 </b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一) 基础设施设备
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.供水、排水：交付时供水、排水配套设施齐全，并与城市公共供水、排水管网连接。使用自建设施供水的，供水的水质符合国家规定的饮用水卫生标准，
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1101" size="100" value="${cs.f1101}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 1">;
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.供电：交付时纳入城市供电网络并正式供电，
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1102" size="100" value="${cs.f1102}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 2">;
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.供暖：交付时供热系统符合供热配建标准，使用城市集中供热的，纳入城市集中供热管网，
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1103" size="100" value="${cs.f1103}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 3">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.燃气：交付时完成室内燃气管道的敷设，并与城市燃气管网连接，保证燃气供应，
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1104" size="100" value="${cs.f1104}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 4">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.电话通信：交付时线路敷设到户； 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;6.有线电视：交付时线路敷设到户；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;7.宽带网络：交付时线路敷设到户。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;以上第1、2、3项由出卖人负责办理开通手续并承担相关费用；第4、5、6、7项需要买受人自行办理开通手续。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;如果在约定期限内基础设施设备未达到交付使用条件，双方同意按照下列第
						<select name="f1105">
							<option value="（1）" <c:if test="${cs.f1105 eq '（1）'  }">selected</c:if>>（1）</option>
							<option value="（2）" <c:if test="${cs.f1105 eq '（2）'  }">selected</c:if>>（2）</option>
						</select>
						种方式处理：
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）以上设施中第1、2、3、4项在约定交付日未达到交付条件的，出卖人按照本合同第十三条的约定承担逾期交付责任。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;第5项未按时达到交付使用条件的，出卖人按日向买受人支付<input type="text" name="f1106" size="15" value="${cs.f1106}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">元的违约金；第6项未按时达到交付使用条件的，
						出卖人按日向买受人支付<input type="text" name="f1107" size="15" value="${cs.f1107}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">元的违约金；
						第7项未按时达到交付使用条件的，出卖人按日向买受人支付<input type="text" name="f1108" size="15" value="${cs.f1108}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">元的违约金。
						出卖人采取措施保证相关设施于约定交付日后<input type="text" name="f1109" size="15" value="${cs.f1109}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">日之内达到交付使用条件。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;（2）
						<textarea class="input_text" name="f1110"  rows="6" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十一条  （一）（2）">${cs.f1110}</textarea>
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（二）公共服务及其他配套设施（以建设工程规划许可为准）
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.小区内绿地率：<input type="text" name="f1111" size="15" value="${cs.f1111}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1112" size="30" value="${cs.f1112}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.小区内非市政道路：<input type="text" name="f1113" size="15" value="${cs.f1113}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1114" size="30" value="${cs.f1114}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.规划的车位、车库：<input type="text" name="f1115" size="15" value="${cs.f1115}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1116" size="30" value="${cs.f1116}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.物业服务用房：<input type="text" name="f1117" size="15" value="${cs.f1117}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1118" size="30" value="${cs.f1118}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5、医疗卫生机构： <input type="text" name="f1119" size="15" value="${cs.f1119}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1120" size="30" value="${cs.f1120}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;6.幼儿园：<input type="text" name="f1121" size="15" value="${cs.f1121}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1122" size="30" value="${cs.f1122}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;7.学校：<input type="text" name="f1123" size="15" value="${cs.f1123}" maxlength="13" class="input_text" features="bmust=0,datatype=2,minlength=0,maxlength=13,showtitle=第十一条 ">&nbsp;
						达到：<input type="text" name="f1124" size="30" value="${cs.f1124}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;8.<input type="text" name="f1125" size="100" value="${cs.f1125}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">；
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;9.<input type="text" name="f1126" size="100" value="${cs.f1126}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;以上设施未达到上述条件的，双方同意按照以下方式处理：
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.小区内绿地率未达到上述约定条件的，<input type="text" name="f1127" size="30" value="${cs.f1127}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.小区内非市政道路未达到上述约定条件的，<input type="text" name="f1128" size="30" value="${cs.f1128}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.规划的车位、车库未达到上述约定条件的，<input type="text" name="f1129" size="30" value="${cs.f1129}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.物业服务用房未达到上述约定条件的，<input type="text" name="f1130" size="30" value="${cs.f1130}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.其他设施未达到上述约定条件的，<input type="text" name="f1131" size="30" value="${cs.f1131}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十一条 ">。
					</td></tr>
						<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;关于本项目内相关设施设备的具体约定见附件六。 
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十二条  交付时间和手续</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一)出卖人应当在
						<input type="text" class="demo-input" name="f1201" id="f1201" placeholder=""  value="${cs.f1201 }" readonly="readonly"  features="bmust=1,datatype=2,minlength=0,maxlength=13,showtitle=第十二条  交付时间"/><font color="red">*</font>
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f1201' //指定元素
							});
						</script>
						前向买受人交付该商品房。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二)该商品房达到第十条、第十一条约定的交付条件后，出卖人应当在交付日期届满前<input type="text" name="f1202" size="15" value="${cs.f1202}" maxlength="15" class="input2" features="bmust=1,datatype=3,minvalue=10,minlength=0,maxlength=50,showtitle=第十二条 "><font color="red">*</font>日〔不少于10日）将查验房屋的时间、办理交付手续的时间地点以及应当携带的证件材料的通知书面送达买受人。买受人未收到交付通知书的，以本合同约定 的交付日期届满之日为办理交付手续的时间，以该商品房所在地为办理交付手续的地点。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1203" size="100" value="${cs.f1203}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;交付该商品房时，出卖人应当出示满足第十条约定的证明文件。出卖人不出示证明文件或者出示的证明文件不齐全，不能满足第十条约定条件的，买受人有权拒绝接收，由此产生的逾期交付责任由出卖人承担，并按照第十三条处理。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三)	查验房屋
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.办理交付手续前，买受人有权对该商品房进行查验，出卖人不得以缴纳相关税 费或者签署物业管理文件作为买受人查验和办理交付手续的前提条件。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.买受人查验的该商品房存在下列除地基基础和主体结构外的其他质量问题的，
						由出卖人按照有关工程和产品质量规范、标准自查验次日起<input type="text" name="f1204" size="15" value="${cs.f1204}" maxlength="13" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">日内负责修复，并承担修复费用，修复后再行交付。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）屋面、墙面、地面渗漏或开裂等；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）管道堵塞；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）门窗翘裂、五金件损坏；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）灯具、电器等电气设备不能正常使用；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（5）<input type="text" name="f1205" size="100" value="${cs.f1205}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（6）<input type="text" name="f1206" size="100" value="${cs.f1206}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.查验该商品房后，双方应当签署商品房交接单。由于买受人原因导致该商品房 未能按期交付的，双方同意按照以下方式处理：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）<input type="text" name="f1207" size="100" value="${cs.f1207}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）<input type="text" name="f1208" size="100" value="${cs.f1208}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十二条 ">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十三条  逾期交付责任</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（除不可抗力外，出卖人未按照第十二条约定的时间将该商品房交付买受人的，双 方同意按照下列第
						<select name="f1301" onchange="tk13_change(this.value);">
							<option value="1" <c:if test="${cs.f1301 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f1301 eq 2  }">selected</c:if>>2</option>
						</select>
						种方式处理：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.按照逾期时间，分别处理（（1）和（2）不作累加）。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(1）逾期在<input type="text" name="f1302" size="15" value="${cs.f1302}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">日之内〔该期限应当不多于第九条第1 (1）项中的期限），自第 十二条约定的交付期限届满之次日起至实际交付之日止，出卖人按日计算向买受人支付全部房价款万分之
						<input type="text" name="f1303" size="15" value="${cs.f1303}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">的违约金〔该违约金比率应当不低于第九条第1 (1）项中的比率)。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）逾期超过<input type="text" name="f1304" size="15" value="${cs.f1304}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">日〔该期限应当与本条第（1）项中的期限相同）后，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），
						并自买受人付款之日起，按照<input type="text" name="f1305" size="15" value="${cs.f1305}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">%(不低于中国人民银行公布的同期贷款基准利率）计算给付利息；
						同时，出卖人按照全部房价款的<input type="text" name="f1306" size="15" value="${cs.f1306}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">%向买受人支付违约金。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;买受人要求继续履行合同的，合同继续履行，出卖人按日计算向买受人支付全部 房价款万分之<input type="text" name="f1307" size="15" value="${cs.f1307}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十三条 ">(该比率应当不低于本条第1 (1）项中的比率）的违约金。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2、
						<textarea class="input_text" name="f1308"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十三条 处理方式2">${cs.f1308}</textarea>。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第五章  商品房质量及保修责任<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十四条  商品房质量</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一）地基基础和主体结构
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人承诺该商品房地基基础和主体结构合格，并符合国家及行业标准。
						经检测不合格的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖 人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款〔含已付 贷款部分），并自买受人付款之日起，
						按照<input type="text" name="f1401" size="15" value="${cs.f1401}" maxlength="15" class="input2" features="bmust=1,datatype=1,minlength=0,maxlength=50,showtitle=第十四条 "><font color="red">*</font>%(不低于中国人民银行公布的同 期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付
						<select id="N14_2" name="f1402" style="width:100px;"></select>
						<input type="hidden" id="N14_2id" value="${cs.f1402 }" />
						的赔偿金。因此而发生的检测费用由出卖人承担。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，<input type="text" name="f1403" size="50" value="${cs.f1403}" maxlength="100" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 "><font color="red">*</font>。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）其他质量问题
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房质量应当符合有关工程质量规范、标准和施工图设计文件的要求。发现除地基基础和主体结构外质量问题的，双方按照以下方式处理：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）及时更换、修理；如给买受人造成损失的，还应当承担相应赔偿责任。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea class="input_text" name="f1404"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十四条  (二）其他质量问题">${cs.f1404}</textarea>。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）经过更换、修理，仍然严重影响正常使用的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退 还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，
						按照<input type="text" name="f1405" size="15" value="${cs.f1405}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">%(不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失 的，由出卖人承担相应赔偿责任。因此而发生的检测费用由出卖人承担。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;买受人不解除合同的，<input type="text" name="f1406" size="50" value="${cs.f1406}" maxlength="100" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 "><font color="red">*</font>。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三）装饰装修及设备标准
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房应当使用合格的建筑材料、构配件和设备，装置、装修、装饰所用材料的产品质量必须符合国家的强制性标准及双方约定的标准。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;不符合上述标准的，买受人有权要求出卖人按照下列第（1）
						、<input type="text" name="f1407" size="15" value="${cs.f1407}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">方 式处理（可多选）：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）及时更换、修理；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）出卖人赔偿双倍的装饰、设备差价；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）<input type="text" name="f1408" size="100" value="${cs.f1408}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）<input type="text" name="f1409" size="100" value="${cs.f1409}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;具体装饰装修及相关设备标准的约定见附件七。 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(四）室内空气质量、建筑隔声和民用建筑节能措施
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.该商品房室内空气质量符合
						<select id="N14_11" name="f1410" style="width:100px;"></select>
						<input type="hidden" id="N14_11id" value="${cs.f1410 }" />
						标准，
						标准名称：<input type="text" name="f1411" size="30" value="${cs.f1411}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 空气质量 标准名称">，
						标准文号：<input type="text" name="f1412" size="30" value="${cs.f1412}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 空气质量 标准文号">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，建筑隔声情况符合
						<select id="N14_14" name="f1413" style="width:100px;"></select>
						<input type="hidden" id="N14_14id" value="${cs.f1413 }" />
						标准，
						标准名称：<input type="text" name="f1414" size="30" value="${cs.f1414}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 隔声情况 标准名称">，
						标准文号：<input type="text" name="f1415" size="30" value="${cs.f1415}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 隔声情况 标准文号">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房室内空气质量或建筑隔声情况经检测不符合标准，由出卖人负责整改，整改后仍不符合标准的，买受人有权解除合同。买受人解除合同的，应当书面通知出 卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，
						按照<input type="text" name="f1416" size="15" value="${cs.f1416}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">%〔不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人承担相应赔偿责任。经检测不符合标准的，检测费用由出卖人承担，整改后再次检测发生的费用仍由 出卖人承担。因整改导致该商品房逾期交付的，出卖人应当承担逾期交付责任。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房应当符合国家有关民用建筑节能强制性标准的要求。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;未达到标准的，出卖人应当按照相应标准要求补做节能措施，并承担全部费用；给买受人造成损失的，出卖人应当承担相应赔偿责任。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f1417" size="50" value="${cs.f1417}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十四条 ">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十五条  保修责任</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一)商品房实行保修制度。该商品房为住宅的，出卖人自该商品房交付之日起，按照《住宅质量保证书》承诺的内容承担相应的保修责任。该商品房为非住宅的，双方应当签订补充协议详细约定保修范围、保修期限和保修责任等内容。具体内容见附件八。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）下列情形，出卖人不承担保修责任：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.因不可抗力造成的房屋及其附属设施的损害；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.因买受人不当使用造成的房屋及其附属设施的损害；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1501" size="100" value="${cs.f1501}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十五条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三）在保修期内，买受人要求维修的书面通知送达出卖人<input type="text" name="f1502" size="15" value="${cs.f1502}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十五条 ">日内，出卖人既不履行保修义务也不提出书面异议的，买受人可以自行或委托他人进行维修，维修费用及维修期间造成的其他损失由出卖人承担。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十六条  质量担保</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人不按照第十四条、第十五条约定承担相关责任的，
						由<input type="text" name="f1601" size="25" value="${cs.f1601}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十六条 ">承担连带责任。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;关于质量担保的证明见附件九。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第六章  房屋登记<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十七条  房屋登记</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一）双方同意共同向房屋登记机构申请办理该商品房的房屋所有权转移登记。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）因出卖人的原因，买受人未能在该商品房交付之日起<input type="text" name="f1701" size="15" value="${cs.f1701}" maxlength="15" class="input2" features="bmust=1,datatype=1,minlength=0,maxlength=50,showtitle=第十七条 "><font color="red">*</font>日内取得该商品房的房屋所有权证书的，
						双方同意按照下列第
						<select name="f1702">
							<option value="1" <c:if test="${cs.f1702 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f1702 eq 2  }">selected</c:if>>2</option>
						</select>
						种方式处理：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自 买受人付款之日起，
						按照<input type="text" name="f1703" size="15" value="${cs.f1703}" maxlength="15" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十七条 ">% （不低于中国人民银行公布的同期贷款基准利率） 计算给付利息。买受人不解除合同的，自买受人应当完成房屋所有权登记的期限届满之次日起至实际完成房屋所有权登记之日止，
						出卖人按日计算向买受人支付全部房价款万分之<input type="text" name="f1704" size="15" value="${cs.f1704}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十七条 ">的违约金。
					</td></tr>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;2、
						<textarea class="input_text" name="f1705"  rows="8" style="width:85%" features="bmust=0,datatype=0,maxlength=2000,showtitle=第十七条">${cs.f1705}</textarea>。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三）因买受人的原因未能在约定期限内完成该商品房的房屋所有权转移登记的， 出卖人不承担责任。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第七章  物业管理<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十八条  物业管理</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一)出卖人依法选聘的前期物业服务企业为<input type="text" name="f1801" size="50" value="${cs.f1801}" maxlength="100" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第十八条 "><font color="red">*</font>。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）物业服务时间从
						<input type="text" class="demo-input" name="f1802" id="f1802" placeholder="" value="${cs.f1802 }" readonly="readonly" features="bmust=1,datatype=2,minlength=0,maxlength=50,showtitle=第十八条 "/><font color="red">*</font>
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f1802' //指定元素
							});
						</script>
						到
						<input type="text" class="demo-input" name="f1803" id="f1803" placeholder="" value="${cs.f1803 }" readonly="readonly" features="bmust=1,datatype=2,minlength=0,maxlength=50,showtitle=第十八条 "/><font color="red">*</font>
						<script>
							//执行一个laydate实例
							laydate.render({
							  elem: '#f1803' //指定元素
							});
						</script>
						。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三）物业服务期间，物业收费计费方式为
						<select id="N18_4" name="f1804" style="width:100px;"></select>
						<input type="hidden" id="N18_4id" value="${cs.f1804 }" />
						。物业 服务费为<input type="text" name="f1805" size="15" value="${cs.f1805}" maxlength="15" class="input2" features="bmust=1,datatype=1,minlength=0,maxlength=50,showtitle=第十八条 "><font color="red">*</font>元/月·平方米（建筑面积）。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(四）买受人同意由出卖人选聘的前期物业服务企业代为查验并承接物业共用部位、共用设施设备，出卖人应当将物业共用部位、共用设施设备承接查验的备案情况书面告知买受人。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(五）买受人已详细阅读前期物业服务合同和临时管理规约，同意由出卖人依法选聘的物业服务企业实施前期物业管理，遵守临时管理规约。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(六）业主大会设立前适用该章约定。业主委员会成立后，由业主大会决定选聘或续聘物业服务企业。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房前期物业服务合同、临时管理规约见附件十。
					</td></tr>
				</table>
			</td></tr>
			<tr><td align="center"><b STYLE="font-size:12pt" ><br>第八章  其他事项<br></b></td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第十九条  建筑物区分所有权</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一)买受人对其建筑物专有部分享有占有、使用、收益和处分的权利。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）以下部位归业主共有：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.建筑物的基础、承重结构、外墙、屋顶等基本结构部分，通道、楼梯、大堂等 公共通行部分，消防、公共照明等附属设施、设备，避难层、设备层或者设备间等结构部分；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.该商品房所在建筑区划内的道路（属于城镇公共道路的除外）、绿地（属于城镇公共绿地或者明示属于个人的除外）、占用业主共有的道路或者其他场地用于停放汽车的车位、物业服务用房；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1901" size="100" value="${cs.f1901}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十九条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(三）双方对其他配套设施约定如下:
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.规划的车位、车库：<input type="text" name="f1902" size="50" value="${cs.f1902}" maxlength="100" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第十九条 "><font color="red">*</font>；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2. 会所：<input type="text" name="f1903" size="50" value="${cs.f1903}" maxlength="100" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第十九条 "><font color="red">*</font>；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.<input type="text" name="f1904" size="50" value="${cs.f1904}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第十九条 ">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十条  税费</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;双方应当按照国家的有关规定，向相应部门缴纳因该商品房买卖发生的税费。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十一条  销售和使用承诺</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.出卖人承诺不采取分割拆零销售、返本销售或者变相返本销售的方式销售商品房。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.出卖人承诺按照规划用途进行建设和出售，不擅自改变该商品房使用性质，并按照规划用途办理房屋登记。出卖人不得擅自改变与该商品房有关的共用部位和设施的使用性质。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.出卖人承诺对商品房的销售，不涉及依法或者依规划属于买受人共有的共用部位和设施的处分。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.出卖人承诺已将遮挡或妨碍房屋正常使用的情况告知买受人。具体内容见附件十一。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.买受人使用该商品房期间，不得擅自改变该商品房的用途、建筑主体结构和承重结构。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;6.<input type="text" name="f2101" size="100" value="${cs.f2101}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十一条 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;7.<input type="text" name="f2102" size="100" value="${cs.f2102}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十一条 ">。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十二条  送达</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人和买受人保证在本合同中记载的通讯地址、联系电话均真实有效。任何根据本合同发出的文件，均应采用书面形式，以
						<select id="N22_2" name="f2201" style="width:100px;" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第二十二条  邮递方式"></select><font color="red">*</font>
						<input type="hidden" id="N22_2id" value="${cs.f2201 }" />
						方式送达对方。任何一方变更通讯地址、联系电话的，
						应在变更之日起<input type="text" name="f2202" size="15" value="${cs.f2202}" maxlength="15" class="input2" features="bmust=1,datatype=1,minlength=0,maxlength=50,showtitle=第二十二条 "><font color="red">*</font>日内书面通知对方。变更的一方未履行通知义务导致送达不能的，应承担相应的法律责任。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十三条  买受人信息保护</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;出卖人对买受人信息负有保密义务。非因法律、法规规定或国家安全机关、公安机关、检察机关、审判机关、纪检监察部门执行公务的需要，未经买受人书面同意， 出卖人及其销售人员和相关工作人员不得对外披露买受人信息，或将买受人信息用于履行本合同之外的其他用途。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十四条  争议解决方式</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;本合同在履行过程中发生的争议，由双方当事人协商解决，也可通过消费者协会等相关机构调解；或按照下列第
						<select name="f2401" onchange="tk24_change(this.value);">
							<option value="1" <c:if test="${cs.f2401 eq 1  }">selected</c:if>>1</option>
							<option value="2" <c:if test="${cs.f2401 eq 2  }">selected</c:if>>2</option>
						</select>
						种方式解决：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.依法向房屋所在地人民法院起诉。 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.提交<input type="text" name="f2402" size="30" value="${cs.f2402}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十四条 ">仲裁委员会仲裁。 
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十五条  补充协议</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;对本合同中未约定或约定不明的内容，双方可根据具体情况签订书面补充协议（补充协议见附件十二）。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;补充协议中含有不合理的减轻或免除本合同中约定应当由出卖人承担的责任，或不合理的加重买受人责任、排除买受人主要权利内容的，仍以本合同为准。
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>第二十六条  合同生效</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;本合同自双方签字或盖章之日起生效。本合同的解除应当采用书面形式。 
						本合同及附件共<input type="text" name="f2601" size="15" value="${cs.f2601}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 ">页，
						一式<input type="text" name="f2602" size="15" value="${cs.f2602}" maxlength="50" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 "><font color="red">*</font>份，
						其中出卖人<input type="text" name="f2603" size="15" value="${cs.f2603}" maxlength="50" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 "><font color="red">*</font>份，
						买受人<input type="text" name="f2604" size="15" value="${cs.f2604}" maxlength="50" class="input2" features="bmust=1,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 "><font color="red">*</font>份， 
						[<input type="text" name="f2605" size="15" value="${cs.f2605}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 ">]
						<input type="text" name="f2606" size="15" value="${cs.f2606}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 ">份，
						[<input type="text" name="f2607" size="15" value="${cs.f2607}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 ">]
						<input type="text" name="f2608" size="15" value="${cs.f2608}" maxlength="50" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=第二十六条 ">份。合同附件与本合同具有同等法律效力。
					</td></tr>
				</table>
			</td></tr>
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
						<textarea class="input_text" name="content4902" rows="15" style="width:100%">${attachmap['4902'] }</textarea>
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
						<textarea class="input_text" name="content4903" rows="15" style="width:100%">${attachmap['4903'] }</textarea>
					</td>
					</tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件四  出卖人提供的承租人放弃优先购买权的声明</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4904" rows="15" style="width:100%">${attachmap['4904'] }</textarea>
					</td>
					</tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件五  关于该商品房价款的计价方式、总价款、付款方式及期限的具体约定</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4905" rows="15" style="width:100%">${attachmap['4905'] }</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件六  关于本项目内相关设施、设备的具体约定</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.相关设施的位置及用途
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.其他约定
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4906" rows="15" style="width:100%">${attachmap['4906'] }</textarea>
					</td></tr>
					<tr><td ><br><br></td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件七  关于装饰装修及相关设备标准的约定</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;交付的商品房达不到本附件约定装修标准的，按照本合同第十四条第（三）款约定处理。出卖人未经双方约定增加的装置、装修、装饰，视为无条件赠送给买受人。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;双方就装饰装修主要材料和设备的品牌、产地、规格、数量等内容约定如下： 
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.外墙：
						<select id="FJ7_1" name="f9001" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_1id" value="${cs.f9001 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9002" size="50" value="${cs.f9002}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.起居室：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）内墙：
						<select id="FJ7_3" name="f9003" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_3id" value="${cs.f9003 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9004" size="50" value="${cs.f9004}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）顶棚：
						<select id="FJ7_5" name="f9005" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_5id" value="${cs.f9005 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9006" size="50" value="${cs.f9006}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）室内地面：
						<select id="FJ7_7" name="f9007" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_7id" value="${cs.f9007 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp<input type="text" name="f9008" size="50" value="${cs.f9008}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.厨房：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）地面：
						<select id="FJ7_9" name="f9009" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_9id" value="${cs.f9009 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9010" size="50" value="${cs.f9010}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）墙面：
						<select id="FJ7_11" name="f9011" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_11id" value="${cs.f9011 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9012" size="50" value="${cs.f9012}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）顶棚：
						<select id="FJ7_13" name="f9013" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_13id" value="${cs.f9013 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9014" size="50" value="${cs.f9014}" maxlength="13" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）厨具：<input type="text" name="f9015" size="50" value="${cs.f9015}" maxlength="13" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.卫生间：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）地面：
						<select id="FJ7_16" name="f9016" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_16id" value="${cs.f9016 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9017" size="50" value="${cs.f9017}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）墙面：
						<select id="FJ7_18" name="f9018" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_18id" value="${cs.f9018 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9019" size="50" value="${cs.f9019}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（3）顶棚：
						<select id="FJ7_20" name="f9020" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_20id" value="${cs.f9020 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9021" size="50" value="${cs.f9021}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（4）卫生器具：<input type="text" name="f9022" size="50" value="${cs.f9022}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.阳台：
						<select id="FJ7_23" name="f9023" style="width:100px;" ></select>
						<input type="hidden" id="FJ7_23id" value="${cs.f9023 }" />
						；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9024" size="50" value="${cs.f9024}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;6.电梯：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（1）品牌：<input type="text" name="f9025" size="50" value="${cs.f9025}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;（2）型号：<input type="text" name="f9026" size="50" value="${cs.f9026}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;7.管道：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9027" size="50" value="${cs.f9027}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;8.窗户：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9028" size="50" value="${cs.f9028}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件七 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;9.其他：
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="f9029" rows="10" style="width:100%">${cs.f9029 }</textarea>
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件八  关于保修范围、保修期限和保修责任的约定</b></td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房为住宅的，出卖人应当提供《住宅质量保证书》；该商品房为非住宅的，双方可参照《住宅质量保证书》中的内容对保修范围、保修期限和保修责任等进行约定。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;该商品房的保修期自房屋交付之日起计算，关于保修期限的约定不应低于《建设工程质量管理条例》第四十条规定的最低保修期限。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(一)保修项目、期限及责任的约定
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;1.地基基础和主体结构：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：<input type="text" name="f9101" size="30" value="${cs.f9101}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">(不得低于设计文件规定的该工程的合理使用年限)
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9102" size="100" value="${cs.f9102}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;2.屋面防水工程、有防水要求的卫生间、房间和外墙面的防渗漏：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：<input type="text" name="f9103" size="30" value="${cs.f9103}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">(不得低于5年)
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9104" size="100" value="${cs.f9104}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;3.供热、供冷系统和设备：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：<input type="text" name="f9105" size="30" value="${cs.f9105}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">(不得低于2个采暖期、供冷期)
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9106" size="100" value="${cs.f9106}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;4.电气管线、给排水管道、设备安装：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：<input type="text" name="f9107" size="30" value="${cs.f9107}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">(不得低于2年)；
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9108" size="100" value="${cs.f9108}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;5.装修工程：
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;保修期限为：<input type="text" name="f9109" size="30" value="${cs.f9109}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">(不得低于2年)
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="f9110" size="100" value="${cs.f9110}" maxlength="100" class="input2" features="bmust=0,datatype=0,minlength=0,maxlength=50,showtitle=附件八 ">。
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;6.其他：
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="f9111" rows="10" style="width:100%" features="bmust=0,datatype=0,minlength=0,maxlength=2000,showtitle=附件八  6.其他">${cs.f9111 }</textarea>
					</td></tr>
					<tr><td>
						&nbsp;&nbsp;&nbsp;&nbsp;(二）其他约定
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="f9112" rows="10" style="width:100%" features="bmust=0,datatype=0,minlength=0,maxlength=2000,showtitle=附件八  (二）其他约定">${cs.f9112 }</textarea>
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件九  关于质量担保的证明</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4909" rows="10" style="width:100%">${attachmap['4909'] }</textarea>
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件十  关于物业管理的约定</b></td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;1.前期物业服务合同 
					</td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;2.临时管理规约
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4910" rows="10" style="width:100%">${attachmap['4910'] }</textarea>
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件十一  出卖人关于遮挡或妨碍房屋正常使用情况的说明</b></td></tr>
					<tr><td >
						&nbsp;&nbsp;&nbsp;&nbsp;(如:该商品房公共管道检修口、柱子、变电箱等有遮挡或妨碍房屋正常使用的情况)
					</td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4911" rows="10" style="width:100%">${attachmap['4911'] }</textarea>
					</td></tr>
				</table>
			</td></tr>
			<tr><td>
				<table width=100%	>
					<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件十二  补充协议</b></td></tr>
					<tr><td align="center">
						<textarea class="input_text" name="content4912" rows="10" style="width:100%">${attachmap['4912'] }</textarea>
					</td></tr>
				</table>
			</td></tr>
			
			<c:choose>
			<c:when test="${xgList!= null && fn:length(xgList) > 0}">
			<table width=100%	>
			<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<b>附件十三  购房资格查验证明</b></td></tr>
			
				<c:forEach items="${xgList}" var="xg">
				<tr><td align="left" >
				&nbsp;&nbsp;&nbsp;&nbsp;编号：<b>${xg.limitSaleID}</b>
				<input type="hidden" name="xgID" value="${xg.limitSaleID}"/>
				</td></tr>
				</c:forEach>
			</table>	
			</c:when>
			<c:otherwise>
				<input type="hidden" name="xgID" value=""/>
			</c:otherwise>
			</c:choose>
			<!-- 合同附件 end -->

	</table>
			<div align="center">
			    <input type="button" name="save" value="保存" onclick="javascript:{this.disabled=true;gotoSave();}" />
			    <c:if  test="${cdvo.status=='0'}">
			    <input type="button" name="submit" value="提交" onclick="doSubmit()" />
			    </c:if>
	    		<c:if  test="${cdvo.status=='1'}">
			    <input type="button" name="submit" value="确认" onclick="doSubmit()" />
			    </c:if>
			    <c:if test="${cdvo.status=='1'}">
			   	<input type="button" name="return" value="退回" onclick="doReturn()" />
			   	</c:if>
			    <input type="button" name="close" value="关闭" onclick="doClose()" />
			    <br/>
			</div>
		<input type="hidden" name="fujiancount" value="12">   
	</form>
	</body>
</html>

