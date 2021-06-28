 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同买方编辑页面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<style type="text/css">
			
		</style>
		<script type="text/javascript">
			$(function(){
				addBuyerNationality();
				addBuyerProvince();
				addBuyerType();
				addBuyerSex();
				addBuyerCardName();
			});
			function doSaveBuyer(contractID,serial){
				//乙方撤销密码验证
				if($('#passwd').val()==null||$('#passwd').val()==''){
					alert("请输入乙方撤销密码！");
		            $("#passwd").focus();
		            return false; 
				}
				var pwd=document.getElementById("passwd").value;
				if(pwd.length<6){
					alert("请输入不少于6位的密码！");
		            $("#passwd").focus();
		            return false; 
				}
				if($('#buyerCardcode').val()==null||$('#buyerCardcode').val()==''){
					alert("请输入证件号码！");
		            $("#buyerCardcode").focus();
		            return false; 
				}
				//身份证验证
		        var cardname=$("#cardname").val();
		        var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		        var bCardcode=$('#buyerCardcode').val();
		        if(cardname=='1'){
		        	if(!pattern.test(bCardcode) && bCardcode!=''){
		        		alert("请输入正确的身份证号码！");
			            $("#buyerCardcode").focus();
			            return false;   		        		
		        	}
		        }
		        if(bCardcode!=''){
		        	if(cardname=='30'|| cardname=='31'){
		        		if(!checkGATIdcard(bCardcode)){
		        			$("#buyerCardcode").focus();
			            	return false;
		        		}
		        	}
		        }
		        
		        
				var contractID = $('#contractID').val();
				var serial = $('#serial').val();
				if(serial==''){
					$('#serial').val('0');
				}
		    	if(contractID!=null&&contractID!=''){
		    		$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontract/saveBuyerInfo.action',  
						data : $('#buyer').serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doBack();
								parent.doUpdate('乙方编辑');
							}else{
								alert(data[0].msg);
							}
						},
						error : function(){
							alert("乙方保存出错！");
						}
					});
			    }
			}
			function addBuyerNationality(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_504',
					dataType : "json",
					success : function(data){
						$('#nationality').empty();
						var nationalityID= $('#nationalityID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(nationalityID!='0'&&option.code==nationalityID){
									$('#nationality').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#nationality').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询国籍列表出错！");
					}
				});
			}
			function changeNationality(nationalityID){
				if(nationalityID!=1){
					$('#provinceID').val('-99')
					addBuyerProvince();
				}else{
					$('#provinceID').val('-1')
					addBuyerProvince();
				}
			}
			function addBuyerProvince(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_524',
					dataType : "json",
					success : function(data){
						$('#province').empty();
						var provinceID = $('#provinceID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(provinceID!=''&&option.code==provinceID){
									$('#province').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#province').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询省市列表出错！");
					}
				});
			}
			function addBuyerType(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_525',
					dataType : "json",
					success : function(data){
						$('#type').empty();
						var typeID = $('#typeID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(typeID==option.code){
									$('#type').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#type').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询买方类型出错！");
					}
				});
			}
			function addBuyerSex(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_505',
					dataType : "json",
					success : function(data){
						$('#sex').empty();
						var sexID = $('#sexID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(sexID==option.code){
									$('#sex').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#sex').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
							
							if(sexID==''){
								$('#sex').append('<option value=-99 selected></option>');
							}else{
								$('#sex').append('<option value=-99></option>');
							}
						}
					},
					error : function(){
						alert("查询性别列表出错！");
					}
				});
			}
			function addBuyerCardName(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_506',
					dataType : "json",
					success : function(data){
						$('#cardname').empty();
						var cardnameID = $('#cardnameID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(cardnameID==option.code){
									$('#cardname').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#cardname').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询证件类型列表出错！");
					}
				});
			}

			$.fn.datebox.defaults.formatter = function(date){
			    var y = date.getFullYear();
			    var m = date.getMonth()+1;
			    var d = date.getDate();
			    return y+""+(m<10?('0'+m):m)+""+(d<10?('0'+d):d);
			};
			$.fn.datebox.defaults.parser = function(s){
			    var y = parseInt(s.substring(0,4));
			    var m = parseInt(s.substring(4,6));
			    var d = parseInt(s.substring(6,8));
			    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			        return new Date(y,m-1,d);
			    } else {
			        return new Date();
			    }
			};
			
			//港澳台居住证检查
			function checkGATIdcard(idcard)
			{
			    var Errors=new Array(
			    "验证通过!",
			    "居住证号码位数不对!",
			    "居住证号码出生日期超出范围或含有非法字符!",
			    "居住证号码校验错误!",
			    "居住证地区非法!"
			    );
			    var area={83:"台湾",81:"香港",82:"澳门"};
			    var idcard,Y,JYM;
			    var S,M;
			    var idcard_array = new Array();
			    idcard_array = idcard.split("");
			    //????
			    if(area[parseInt(idcard.substr(0,2))]==null) 
			    {
			        alert(Errors[4]);
			        return false;
			    }
			    //???????????
			    switch(idcard.length){
			        case 15:
			            if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
			                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//??????????
			            } else {
			                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//??????????
			            }
			            if(ereg.test(idcard)) 
			                return true;
			            else 
			                {
			                alert(Errors[2]);
			                return false;
			            }
			            break;
			        case 18:
			        //18???????
			        //?????????? 
			        //????:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			        //????:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			        if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
			            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//???????????????
			        } else {
			            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//???????????????
			        }
			        //??????????
			        if(ereg.test(idcard)){
			        //?????
			            S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
			            + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
			            + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
			            + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
			            + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
			            + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
			            + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
			            + parseInt(idcard_array[7]) * 1 
			            + parseInt(idcard_array[8]) * 6
			            + parseInt(idcard_array[9]) * 3 ;
			            Y = S % 11;
			            M = "F";
			            JYM = "10X98765432";
			            M = JYM.substr(Y,1);//?????
			            if(M == idcard_array[17].toUpperCase()) {
			                return true;
			            }else 
			            {
			                alert(Errors[3]);
			                return false;
			            }
			        }else{ 
			            alert(Errors[2]);
			            return false;
			        }
			        break;
			    default:
			        alert(Errors[1]);
			        break;
			    }
			    return false;
			}
		</script>
	</head>
	<body >
		<form id="buyer" name="buyer" method="post" action="<%=basePath%>/outer/signContractmanage/saveBuyerInfo.action">
			<input type="hidden" id="contractID" name="contractID" value="<%=request.getParameter("contractID") %>"/>
			<input type="hidden" id="serial" name="serial" value="${buyer.serial }"/>
			<table  cellspacing=16 cellpadding=0 border=0 align="center">
				<tr>
					<td>
						乙方合同撤销密码： <input type="text" id="passwd" name="passwd" size="15" style="width:125" value="${passwd }"/>
					</td>
				</tr>
				<tr>
					<td>
						受让方(乙方)： <input type="text" name="buyerName" size="50" style="width:300" value="${buyer.buyerName }"/>
					</td>
				</tr>
				<tr>
					<td>
						国籍： <select style="width:150px" id="nationality" name="buyerNationality" onchange="changeNationality(this.value)">
								<option value="CODE504"></option>
							 </select>
						<input type="hidden" id="nationalityID" value="${buyer.buyerNationality }" />
						居住（注册）所在省市： <select style="width:150px" id="province" name="buyerProvince">
											<option value="code524"></option>
										 </select>
						<input type="hidden" id="provinceID" value="${buyer.buyerProvince }" />
					</td>
				</tr>
				<tr>
					<td>
						个人/公司： <select style="width:90px" id="type" name="buyerType"><option value="code525"></option></select>
						<input type="hidden" id="typeID" value="${buyer.buyerType }" />
						性别：<select style="width:90px" id="sex" name="buyerSex"><option value="CODE505"></option></select>
						<input type="hidden" id="sexID" value="${buyer.buyerSex }" />
						出生年月日： 
								  <input class="easyui-datebox" type="text" name="buyerBirth" id="buyerBirth"  data-options="validType:'length[0,10]',editable:false"  value="${buyer.buyerBirth}"  style="width:150px"/>
					</td>
				</tr>
				<tr>
					<td>
						住所(址)： <input type="text" name="buyerAddress" style="width:185" maxlength="100" size="45" value="${buyer.buyerAddress }"/>
						邮编： <input type="text" name="buyerPostcode" size="14" style="width:145" maxlength="10" value="${buyer.buyerPostcode }"/>
					</td>
				</tr>
				<tr>
					<td>
						证件名称： <select style="width:150px" id="cardname" name="buyerCardname"><option value="CODE506"></option></select>
						<input type="hidden" id="cardnameID" value="${buyer.buyerCardname }" />
						号码： <input type="text" id="buyerCardcode" name="buyerCardcode" maxlength="20" style="width:80" size="22" value="${buyer.buyerCardcode }"/>
					</td>
				</tr>
				<tr>
					<td>
						联系电话： <input type="text" name="buyerCall" size="14" style="width:122" maxlength="20" value="${buyer.buyerCall }"/>
					</td>
				</tr>
				<tr>
					<td>
						委托/法定代理人： <input type="text" name="buyerProxy" size="50" maxlength="50" style="width:300" value="${buyer.buyerProxy }"/>
					</td>
				</tr>
				<tr>
					<td>
						住所(址)： <input type="text" name="buyerProxyAdr" size="37" maxlength="100" style="width:185" value="${buyer.buyerProxyAdr }"/>
						联系电话：<input type="text" name="buyerProxyCall" size="16" style="width:122" maxlength="20" value="${buyer.buyerProxyCall }"/>
					</td>
				</tr>
			</table>
			<div align="center">
				<input type="button" name="submit" onclick="doSaveBuyer();" value="保存"/>
				<input type="reset" name="reset" value="重置"/>
			</div>
		</form>
	</body>
</html>