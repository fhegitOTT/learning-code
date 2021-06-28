<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同买方签字发送页面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<style type="text/css">
			
		</style>
	    <script language='javascript' for="myPad" event="OnShowPdfSignExCompleted(result,confirmResult, iSignType, signPicBase64, signData, fingerImageBase64, fingerFeatureBase64, message ,runningnumber)">
	        OnShowPdfSignExCompleted(result, confirmResult, iSignType, signPicBase64, signData, fingerImageBase64, fingerFeatureBase64, message, runningnumber);
	    </script>
		<script type="text/javascript">
			var contractID="<%=request.getParameter("contractID") %>";
			//平板签字
			//平板初始化
			var HidHelperUtils;
			var isInit = false;
			var pdfInfo;
			var buyerNames;
			var count = 0;
		    function init() {
				if (!isInit) {
		           isInit = true;
		           HidHelperUtils = document.getElementById('myPad');
		           if (HidHelperUtils) {
		               //HidHelperUtils.attachEvent("OnShowPdfSignExCompleted", OnShowPdfSignExCompleted);
		               return true;
		           }else {
		               return false;
		           }
				}
		    }
		    
		    //开启服务并签字
		    function startSign(contractID){
		    	if (init()) {
	                var result = HidHelperUtils.Start();
	               	if(result==0){
	               		signMethod(contractID);
	               	} 
            	}
		    }
		    
		    //签名
			function signMethod1(){
			   
	    		var result = HidHelperUtils.ShowPdfSignExAsync(60, "请"+buyerNames[count]+"点击签名后签字，并按指纹", pdfInfo, 3, 0, 0, 0, 0, 0, 1);	
	    		count+=1;
			
			}	
		    
		    //签名
			function signMethod(contractID){
			    $.ajax({
		    		type: "POST",
		    		url: "<%=basePath%>/inner/ContractPdf/getContractInfo.action",
		    		data: {"contractID":contractID},
		        	dataType: "json",
		        	success : function(data){
		        		if(data[0].result==1){
		        			buyerNames = data[0].buyerNames
			        		pdfInfo = data[0].pdf64Str;
			        		if(pdfInfo != ""){		
			        			//var result = HidHelperUtils.ShowPdfSignAsync(60, pdfInfo, 0, 0, 0, 0, 0, 60);
					    		//var result = HidHelperUtils.ShowPdfSignExAsync(60, "请点击签名后签字，并按指纹", pdfInfo, 3, 0, 0, 0, 0, 0, 60);
					    		var result = HidHelperUtils.ShowPdfSignExAsync(60, "请"+buyerNames[count]+"点击签名后签字，并按指纹", pdfInfo, 3, 0, 0, 0, 0, 0, 1);	
					    		count+=1;
					    	}else{
					    		alert("请与管理员联系，签名失败！");
					    		return;
					    	}
		        		}else {
		        			alert(data[0].msg);
		        			return;
		        		}
		        	},
					error : function(){
						alert("请与管理员联系，签名失败！");
						return;
					}
		   		})
			
			}	
			
				
			//签名后回调函数
			function OnShowPdfSignExCompleted(result, confirmResult, iSignType, signPicBase64, signData, fingerImageBase64, fingerFeatureBase64, message, runningnumber) {
				$.ajax({
			    		type: "POST",
			    		url: "<%=basePath%>/inner/ContractPdf/signEnquirePic.action",
			    		data: {"contractID":contractID,"base64pic":signPicBase64,"fingerImageBase64":fingerImageBase64,"serial":count},
			        	dataType: "json",
			        	success : function(data){
			        		//显示请求平板接口是否成功
							if(data[0].result == 1){
								if(buyerNames.length>count){
									signMethod1();
								}else{
									alert("签名已完成，请刷新！");
									return;
								}
		
							}else{
							 	alert("签名失败。");
							 	return;
							}
			        	},
						error : function(){
							alert("请与管理员联系，签名失败！");
							return;
						}
			   	})
			   	
		    }
		    
			//生成pdf
			function checkCreatePdf(contractID){
		 		if(contractID > 0 ){
		 			if(confirm("确认生成电子合同？")){
		 				$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : '<%=basePath%>/inner/ContractPdf/checkCreatePdf.action',  
							data : {"contractID":contractID},
							dataType : "json",
							success : function(data){
								if(data[0].result=='1'){//没有签过字
									doCreatePdf(contractID,0);
								}else{//有签过字的，给出提示
									$.messager.confirm('提示',data[0].msg,function(r){    
						    			if(r){//确认的话，就生成。    
						    				doCreatePdf(contractID,1);
						    			}
									}); 
								}
							},
							error : function(){
								alert("检查是否签字异常！");
							}
						});
		 			}
		 		}
			}
		
		 	function doCreatePdf(contractID,ifForce){
		 		if(contractID > 0 ){
		 			$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/inner/ContractPdf/doCreateSignPdf.action',  
						data : {"contractID":contractID,"flag":1,"ifForce":ifForce},
						dataType : "json",
						beforeSend:function(XMLHttpRequest){
			             	$.messager.progress({ 
							       title: '提示', 
							       msg: '正在生成pdf，请稍候……', 
							       text: '' 
							});
	         			},
						success : function(data){
							$.messager.progress('close');
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doUpdate('电子合同');
							}else{
								alert(data[0].msg);
								return;
							}
						},
						error : function(){
							$.messager.progress('close');
							alert("生成pdf异常！");
						}
					});
		 		}
			}
		 	function refresh(){
		 		parent.doUpdate('电子合同');
		 	}
			//盖章，1、先判断是否有pdf 2、再判断是否签完字，签完字才能盖章，
		 	function doSealPdf(contractID){
		 		if(contractID > 0 ){
		 			$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/inner/ContractPdf/openContractSealCheck.action',  
						data : {"contractID":contractID},
						dataType : "json",
						success : function(data){
							if(data[0].result == '1'){
								openDialog("盖章","<%=basePath%>/inner/ContractPdf/openContractSeal.action?contractID="+contractID,contractID);
								//window.open("<%=basePath%>/inner/ContractPdf/openContractSeal.action?contractID="+contractID);
							}else{
								alert(data[0].message);
								return;
							}
						},
						error : function(){
							alert("打开盖章功能异常！");
						}
					});
		 		}
			}
		 	//盖章，1、先判断是否有pdf 2、再判断是否签完字，签完字才能盖章，
		 	function doAutoSealPdf(contractID){
		 		if(contractID > 0 ){
		 			if(confirm("确认进行自动盖章？")){
		 				$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : '<%=basePath%>/inner/ContractPdf/openContractSealCheck.action',  
							data : {"contractID":contractID},
							dataType : "json",
							success : function(data){
								if(data[0].result == '1'){
									//自动获取当前用户所在机构的章，并签章。
									startAutoSealPdf(contractID);
								}else{
									alert(data[0].message);
									return;
								}
							},
							error : function(){
								alert("自动盖章异常！");
							}
						});
		 			}
		 		}
			}
		 	
		 	function startAutoSealPdf(contractID){
		 		if(contractID > 0 ){
		 			$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/inner/ContractPdf/startAutoSealPdf.action',  
						data : {"contractID":contractID},
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doUpdate('电子合同');
							}else{
								alert(data[0].msg);
								return;
							}
						},
						error : function(){
							$.messager.progress('close');
							alert("盖章异常！");
						}
					});
		 		}
			}
		 	function openDialog(tempTitle,url,contractID){
				var height=window.screen.height-100;
				var width=window.screen.width-100;
				var strFeatures = new String();
				var strURL = "<%=basePath%>/inner/ContractPdf/openDialog.action";
				strFeatures="dialogHeight:"+height+"px;dialogWidth:"+width+"px;center:Yes;toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no";
				var ret = window.showModalDialog(strURL,url,strFeatures);
				if(ret != undefined  && ret != 'undefined' && ret == 'close'){
					deleteFile(contractID);//删除工程目录里的pdf文件副本。
				}
			}
		 	//删除工程目录里的pdf文件副本
			function deleteFile(contractID){
	        	$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/inner/ContractPdf/deleteContractPdfBak.action",
					data : {"contractID":contractID},
					dataType : "json",
					success : function(data){
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$.messager.alert("提示","删除异常！");
					}
				});
			}
			function doSendSms(contractID){
				var serial = $(".row_selected").children("td").eq(0).text();
				if(serial==null||serial==""){
					alert("请选择起始乙方！");
					return;
				}
				if(confirm('确定发送短信吗?')){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontract/doSendSms.action',  
						data : {"contractID":contractID,"serial":serial},
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doUpdate('电子合同');
							}else{
								alert(data[0].msg);
							}
						},
						error : function(){
							alert("短信发送出错！");
						}
					});
				}
			}
			
			function doPreviewPdf(contractID){
				if(contractID > 0 ){
		 			$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/inner/ContractPdf/checkSignPdf.action',  
						data : {"contractID":contractID},
						dataType : "json",
						success : function(data){
							if(data[0].result=='1'){//有签字合同
								window.open("<%=basePath%>/inner/ContractPdf/doPreviewSignPdf.action?contractID="+contractID+"&flag=1");
							}else{//没有签字合同直接提示
								alert(data[0].msg);
								return;
							}
						},
						error : function(){
							alert("预览PDF签字合同出现异常！");
						}
					});
		 		}
		 	}
		</script>
	</head>
	<body >
	<object id="myPad" style="left: 0px; width: 10px; top: 0px; height: 10px; display: none" classid="clsid:433E8BB0-CAB7-4490-B395-5D7A27BB6D6C"></object>
		<form id="buyerinfo" >
			<input type="button" name="buyer" value="生成PDF"  onclick="checkCreatePdf('<%=request.getParameter("contractID") %>');"/>
			<%-- <input type="button" name="buyer" value="手机签字"  onclick="doSendSms('<%=request.getParameter("contractID") %>');"/> --%>
			<input type="button" name="buyer" value="平板签字"  onclick="startSign('<%=request.getParameter("contractID") %>');"/>
			<input type="button" name="preview" value="预览PDF"  onclick="doPreviewPdf('<%=request.getParameter("contractID") %>');"/>
			<%-- <input type="button" name="preview" value="手动盖章"  onclick="doSealPdf('<%=request.getParameter("contractID") %>');"/> --%>
			<input type="button" name="preview" value="自动盖章"  onclick="doAutoSealPdf('<%=request.getParameter("contractID") %>');"/>
			<input type="button" name="preview" value="刷  新"  onclick="refresh('<%=request.getParameter("contractID") %>');"/>
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}%>
		</form>
		<div id="openDL"></div>
	</body>
</html>