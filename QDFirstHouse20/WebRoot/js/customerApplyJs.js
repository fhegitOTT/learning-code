$(document).ready(function (){
	$(".tab_1 #form1_ok").click(function(){
			var url = '../customermgr/addCustomer!addCustomer.action';
			
			
			
			if($(".tab_1 #loginName").attr("value") == '')
    		{
    			$(".tab_1 #loginName").css("background-color","#FFFFCC");
    			$(".tab_1 #loginNameMess").html("<img src=../image/cancel.png />"+"登录名不可为空");
    			return false;
    		}
    		else{
    			$(".tab_1 #loginName").css("background-color","#D6D6FF");
    			$(".tab_1 #loginNameMess").html("<img src=../image/ok.png />");
 
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#loginName").val())){
    		    $(".tab_1 #loginName").css("background-color","#FFFFCC");
    			$(".tab_1 #loginNameMess").html("<img src=../image/cancel.png />"+"登录名只可包含任意字母、数字、下划线及中文");
    			return false;
    		}else{
    			$(".tab_1 #loginName").css("background-color","#D6D6FF");
    			$(".tab_1 #loginNameMess").html("<img src=../image/ok.png />");
    			
    		}
    		
    		if(($(".tab_1 #loginName").attr("value").length<2) || ($("#loginName").attr("value").length>20)){
    		    $(".tab_1 #loginName").css("background-color","#FFFFCC");
    			$(".tab_1 #loginNameMess").html("<img src=../image/cancel.png />"+"登录名长度只可为2~20位");
    			return false;
    		}else{
    			$(".tab_1 #loginName").css("background-color","#D6D6FF");
    			$(".tab_1 #loginNameMess").html("<img src=../image/ok.png />");
    			
    		}
			
			var loginNameVal = $(".tab_1 #loginName").val();
			
			if($("#flag").val() != 0){
    				$(".tab_1 #loginName").css("background-color","#FFFFCC");
    				$(".tab_1 #loginNameMess").html("<img src=../image/cancel.png />"+"登录名重复！");
    				return false;
			}else{
					$(".tab_1 #loginName").css("background-color","#D6D6FF");
    				$(".tab_1 #loginNameMess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_1 #merchantName").attr("value") =="")
    		{
    			$(".tab_1 #merchantName").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/cancel.png />"+"用户名不可为空");
    			return;
    		}
    		else{
    			$(".tab_1 #merchantName").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#merchantName").val())){
    		    $(".tab_1 #merchantName").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/cancel.png />"+"用户名只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_1 #merchantName").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_1 #merchantName").attr("value").length<2) || ($("#merchantName").attr("value").length>20)){
    		    $(".tab_1 #merchantName").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/cancel.png />"+"用户名长度只可为2~20位");
    			return;
    		}else{
    			$(".tab_1 #merchantName").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNameMess").html("<img src=../image/ok.png />");
    		}
			
			if($(".tab_1 #linkMan").attr("value") =="")
    		{
    			$(".tab_1 #linkMan").css("background-color","#FFFFCC");
    			$(".tab_1 #linkManMess").html("<img src=../image/cancel.png />"+"联系人不可为空");
    			return;
    		}
    		else{
    			$(".tab_1 #linkMan").css("background-color","#D6D6FF");
    			$(".tab_1 #linkManMess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#linkMan").val())){
    		    $(".tab_1 #linkMan").css("background-color","#FFFFCC");
    			$(".tab_1 #linkManMess").html("<img src=../image/cancel.png />"+"联系人只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_1 #linkMan").css("background-color","#D6D6FF");
    			$(".tab_1 #linkManMess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_1 #linkMan").attr("value").length<2) || ($("#linkMan").attr("value").length>20)){
    		    $(".tab_1 #linkMan").css("background-color","#FFFFCC");
    			$(".tab_1 #linkManMess").html("<img src=../image/cancel.png />"+"联系人长度只可为2~20位");
    			return;
    		}else{
    			$(".tab_1 #linkMan").css("background-color","#D6D6FF");
    			$(".tab_1 #linkManMess").html("<img src=../image/ok.png />");
    		}
			
			if($(".tab_1 #linkPhone").attr("value") =="")
    		 {
    		 	$(".tab_1 #linkPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/cancel.png />"+"联系电话不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #linkPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_1 #linkPhone").attr("value").length != 8){
    		 	$(".tab_1 #linkPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/cancel.png />"+"联系电话号码必须为8位");
    			return;
    		 }else{
    		 	$(".tab_1 #linkPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^\d+$/.test($("#linkPhone").val())){
    		 	$(".tab_1 #linkPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/cancel.png />"+"联系电话号码只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_1 #linkPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #linkPhoneMess").html("<img src=../image/ok.png />");
    		 }
			
			 if($(".tab_1 #callPhone").attr("value") =="")
    		 {
    		 	$(".tab_1 #callPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #callPhoneMess").html("<img src=../image/ok.png />");
    		 }else{
    		 	if($(".tab_1 #callPhone").attr("value").length != 11){
    		 	$(".tab_1 #callPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #callPhoneMess").html("<img src=../image/cancel.png />"+"手机电话号码必须为11位");
    			return;
	    		 }else{
	    		 	$(".tab_1 #callPhone").css("background-color","#D6D6FF");
	    			$(".tab_1 #callPhoneMess").html("<img src=../image/ok.png />");
	    		 }
	    		 if(!/^\d+$/.test($("#callPhone").val())){
	    		 	$(".tab_1 #callPhone").css("background-color","#FFFFCC");
	    			$(".tab_1 #callPhoneMess").html("<img src=../image/cancel.png />"+"手机号码只能是数字");
	    			return;
	    		 }else
	    		 {
	    		 	$(".tab_1 #callPhone").css("background-color","#D6D6FF");
	    			$(".tab_1 #callPhoneMess").html("<img src=../image/ok.png />");
	    		 }
    		 } 
			
			if($(".tab_1 #personIdCardNo").attr("value") =="")
    		 {
    		 	$(".tab_1 #personIdCardNo").css("background-color","#FFFFCC");
    			$(".tab_1 #personIdCardNoMess").html("<img src=../image/cancel.png />"+"身份证号码不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #personIdCardNo").css("background-color","#D6D6FF");
    			$(".tab_1 #personIdCardNoMess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_1 #personIdCardNo").attr("value").length != 18){
    		 	$(".tab_1 #personIdCardNo").css("background-color","#FFFFCC");
    			$(".tab_1 #personIdCardNoMess").html("<img src=../image/cancel.png />"+"身份证号码必须为18位");
    			return;
    		 }else{
    		 	$(".tab_1 #personIdCardNo").css("background-color","#D6D6FF");
    			$(".tab_1 #personIdCardNoMess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_1 #terminalPhone").attr("value") =="")
    		 {
    		 	$(".tab_1 #terminalPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/cancel.png />"+"绑定手机不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #terminalPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_1 #terminalPhone").attr("value").length != 11){
    		 	$(".tab_1 #terminalPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/cancel.png />"+"绑定手机必须为11位");
    			return;
    		 }else{
    		 	$(".tab_1 #terminalPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#terminalPhone").val())){
    		 	$(".tab_1 #terminalPhone").css("background-color","#FFFFCC");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/cancel.png />"+"绑定手机只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_1 #terminalPhone").css("background-color","#D6D6FF");
    			$(".tab_1 #terminalPhoneMess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_1 #bankName").attr("value") =="")
    		 {
    		 	$(".tab_1 #bankName").css("background-color","#FFFFCC");
    			$(".tab_1 #bankNameMess").html("<img src=../image/cancel.png />"+"开户银行不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #bankName").css("background-color","#D6D6FF");
    			$(".tab_1 #bankNameMess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_1 #bankName").attr("value").length<4) || ($("#bankName").attr("value").length>40)){
    		    $(".tab_1 #bankName").css("background-color","#FFFFCC");
    			$(".tab_1 #bankNameMess").html("<img src=../image/cancel.png />"+"开户银行长度只可为4~40位");
    			return;
    		 }else{
    			$(".tab_1 #bankName").css("background-color","#D6D6FF");
    			$(".tab_1 #bankNameMess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($("#bankBranch").attr("value") =="")
    		 {
    		 	$(".tab_1 #bankBranch").css("background-color","#FFFFCC");
    			$(".tab_1 #bankBranchMess").html("<img src=../image/cancel.png />"+"支行名不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #bankBranch").css("background-color","#D6D6FF");
    			$(".tab_1 #bankBranchMess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_1 #bankBranch").attr("value").length<4) || ($("#bankBranch").attr("value").length>40)){
    		    $(".tab_1 #bankBranch").css("background-color","#FFFFCC");
    			$(".tab_1 #bankBranchMess").html("<img src=../image/cancel.png />"+"支行名长度只可为4~40位");
    			return;
    		 }else{
    			$(".tab_1 #bankBranch").css("background-color","#D6D6FF");
    			$(".tab_1 #bankBranchMess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_1 #accountName").attr("value") =="")
    		 {
    		 	$(".tab_1 #accountName").css("background-color","#FFFFCC");
    			$(".tab_1 #accountNameMess").html("<img src=../image/cancel.png />"+"开户户名不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #accountName").css("background-color","#D6D6FF");
    			$(".tab_1 #accountNameMess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_1 #accountName").attr("value").length<2) || ($("#accountName").attr("value").length>10)){
    		    $(".tab_1 #accountName").css("background-color","#FFFFCC");
    			$(".tab_1 #accountNameMess").html("<img src=../image/cancel.png />"+"开户户名长度只可为2~10位");
    			return;
    		 }else{
    			$(".tab_1 #accountName").css("background-color","#D6D6FF");
    			$(".tab_1 #accountNameMess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_1 #bankCardNo").attr("value") =="")
    		 {
    		 	$(".tab_1 #bankCardNo").css("background-color","#FFFFCC");
    			$(".tab_1 #bankCardNoMess").html("<img src=../image/cancel.png />"+"法人账号不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #bankCardNo").css("background-color","#D6D6FF");
    			$(".tab_1 #bankCardNoMess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_1 #bankCardNo").attr("value").length<2) || ($("#bankCardNo").attr("value").length>50)){
    		    $(".tab_1 #bankCardNo").css("background-color","#FFFFCC");
    			$(".tab_1 #bankCardNoMess").html("<img src=../image/cancel.png />"+"法人账号长度只可为2~50位");
    			return;
    		 }else{
    			$(".tab_1 #bankCardNo").css("background-color","#D6D6FF");
    			$(".tab_1 #bankCardNoMess").html("<img src=../image/ok.png />");
    		 }
    	
    		  if($(".tab_1 #zipCode").attr("value") =="")
    		 {
    		 	$(".tab_1 #zipCode").css("background-color","#D6D6FF");
    			$(".tab_1 #zipCodeMess").html("<img src=../image/ok.png />");
    		 }else{
    		 	if($(".tab_1 #zipCode").attr("value").length != 6){
    		 	$(".tab_1 #zipCode").css("background-color","#FFFFCC");
    			$(".tab_1 #zipCodeMess").html("<img src=../image/cancel.png />"+"邮编必须为6位");
    			return;
	    		}else{
	    			$(".tab_1 #zipCode").css("background-color","#D6D6FF");
	    			$(".tab_1 #zipCodeMess").html("<img src=../image/ok.png />");
	    			}
	    		if(!/^[0-9\s]+$/.test($("#zipCode").val())){
	    		 	$(".tab_1 #zipCode").css("background-color","#FFFFCC");
	    			$(".tab_1 #zipCodeMess").html("<img src=../image/cancel.png />"+"邮编只能是数字");
	    			return;
	    		}else{
	    		 	$(".tab_1 #zipCode").css("background-color","#D6D6FF");
	    			$(".tab_1 #zipCodeMess").html("<img src=../image/ok.png />");
	    		 	}
    		 }
    	
			if($(".tab_1 #merchantAddr").attr("value") =="")
    			{
    			$(".tab_1 #merchantAddr").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/cancel.png />"+"装机地址不可为空");
    			return;
    			}
    		else{
    			$(".tab_1 #merchantAddr").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#merchantAddr").val())){
    		    $(".tab_1 #merchantAddr").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/cancel.png />"+"装机地址只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_1 #merchantAddr").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_1 #merchantAddr").attr("value").length<4) || ($("#merchantAddr").attr("value").length>60)){
    		    $(".tab_1 #merchantAddr").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/cancel.png />"+"装机地址长度只可为4~60位");
    			return;
    		}else{
    			$(".tab_1 #merchantAddr").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantAddrMess").html("<img src=../image/ok.png />");
    		}		
    	
    		 if($(".tab_1 #merchantNum").attr("value") =="")
    		 {
    		 	$(".tab_1 #merchantNum").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/cancel.png />"+"装机数不能为空");
    			return;
    		 }else{
    		 	$(".tab_1 #merchantNum").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_1 #merchantNum").attr("value") =="0")
    		 {
    		 	$(".tab_1 #merchantNum").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/cancel.png />"+"装机数不能为0");
    			return;
    		 }else{
    		 	$(".tab_1 #merchantNum").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/ok.png />");
    		 } 
			 if(($(".tab_1 #merchantNum").attr("value").length<1) || ($("#merchantNum").attr("value").length>10)){
    		 	$(".tab_1 #merchantNum").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/cancel.png />"+"装机数只可为1~10位");
    			return;
    		 }else{
    		 	$(".tab_1 #merchantNum").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#merchantNum").val())){
    		 	$(".tab_1 #merchantNum").css("background-color","#FFFFCC");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/cancel.png />"+"装机数量只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_1 #merchantNum").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantNumMess").html("<img src=../image/ok.png />");
    		 }
			
			if($(".tab_1 #annualIncome").val()==0){
				$(".tab_1 #annualIncome").css("background-color","#FFFFCC");
				$(".tab_1 #annualIncomeMess").html("<img src=../image/cancel.png />"+"年收入不能为空");
				return;
			}else{
				$(".tab_1 #annualIncome").css("background-color","#D6D6FF");
    			$(".tab_1 #annualIncomeMess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_1 #posUserType").val()==0){
				$(".tab_1 #posUserType").css("background-color","#FFFFCC");
				$(".tab_1 #posUserTypeMess").html("<img src=../image/cancel.png />"+"用户类型不能为空");
				return;
			}else{
				$(".tab_1 #posUserType").css("background-color","#D6D6FF");
    			$(".tab_1 #posUserTypeMess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_1 #posCodeType").val()==0){
				$(".tab_1 #posCodeType").css("background-color","#FFFFCC");
				$(".tab_1 #posCodeTypeMess").html("<img src=../image/cancel.png />"+"号码类型不能为空");
				return;
			}else{
				$(".tab_1 #posCodeType").css("background-color","#D6D6FF");
    			$(".tab_1 #posCodeTypeMess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_1 #businessType").val()==0){
				$(".tab_1 #businessType").css("background-color","#FFFFCC");
				$(".tab_1 #businessTypeMess").html("<img src=../image/cancel.png />"+"营业类别不能为空");
				return;
			}else{
				$(".tab_1 #businessType").css("background-color","#D6D6FF");
    			$(".tab_1 #businessTypeMess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_1 #city").val()==0){
				$(".tab_1 #city").css("background-color","#FFFFCC");
				$(".tab_1 #cityMess").html("<img src=../image/cancel.png />"+"装机城市不能为空");
				return;
			}else{
				$(".tab_1 #city").css("background-color","#D6D6FF");
    			$(".tab_1 #cityMess").html("<img src=../image/ok.png />");
			}
			
			if($('#CertificatesPhoto1').val()==""){
				$(".tab_1 #Certificates1").css("background-color","#FFFFCC");
				$(".tab_1 #CertificatesPhoto1Mess").html("<img src=../image/cancel.png />"+"营业执照副本未检测或未上传");
				return;
			}else{
				$(".tab_1 #Certificates1").css("background-color","#D6D6FF");
   				$(".tab_1 #CertificatesPhoto1Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #CertificatesPhoto2').val()==""){
				$(".tab_1 #Certificates2").css("background-color","#FFFFCC");
				$(".tab_1 #CertificatesPhoto2Mess").html("<img src=../image/cancel.png />"+"税务登记证不能为空");
				return;
			}else{
				$(".tab_1 #Certificates2").css("background-color","#D6D6FF");
    			$(".tab_1 #CertificatesPhoto2Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #CertificatesPhoto3').val()==""){
				$(".tab_1 #Certificates3").css("background-color","#FFFFCC");
				$(".tab_1 #CertificatesPhoto3Mess").html("<img src=../image/cancel.png />"+"组织机构代码证不能为空");
				return;
			}else{
				$(".tab_1 #Certificates3").css("background-color","#D6D6FF");
    			$(".tab_1 #CertificatesPhoto3Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #CertificatesPhoto4').val()==""){
				$(".tab_1 #Certificates4").css("background-color","#FFFFCC");
				$(".tab_1 #CertificatesPhoto4Mess").html("<img src=../image/cancel.png />"+"法人身份证不能为空");
				return;
			}else{
				$(".tab_1 #Certificates4").css("background-color","#D6D6FF");
    			$(".tab_1 #CertificatesPhoto4Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #CertificatesPhoto5').val()==""){
				$(".tab_1 #Certificates5").css("background-color","#FFFFCC");
				$(".tab_1 #CertificatesPhoto5Mess").html("<img src=../image/cancel.png />"+"开户许可证不能为空");
				return;
			}else{
				$(".tab_1 #Certificates5").css("background-color","#D6D6FF");
    			$(".tab_1 #CertificatesPhoto5Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #merchantPhoto1').val()==""){
				$(".tab_1 #merchant1").css("background-color","#FFFFCC");
				$(".tab_1 #merchantPhoto1Mess").html("<img src=../image/cancel.png />"+"商户注册登记表不能为空");
				return;
			}else{
				$(".tab_1 #merchant1").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantPhoto1Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #merchantPhoto2').val()==""){
				$(".tab_1 #merchant2").css("background-color","#FFFFCC");
				$(".tab_1 #merchantPhoto2Mess").html("<img src=../image/cancel.png />"+"特约商户受理业务协议不能为空");
				return;
			}else{
				$(".tab_1 #merchant2").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantPhoto2Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #merchantPhoto3').val()==""){
				$(".tab_1 #merchant3").css("background-color","#FFFFCC");
				$(".tab_1 #merchantPhoto3Mess").html("<img src=../image/cancel.png />"+"商户注册登记表不能为空");
				return;
			}else{
				$(".tab_1 #merchant3").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantPhoto3Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #merchantPhoto4').val()==""){
				$(".tab_1 #merchant4").css("background-color","#D6D6FF");
				$(".tab_1 #merchantPhoto4Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_1 #merchant4").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantPhoto4Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #merchantPhoto5').val()==""){
				$(".tab_1 #merchant5").css("background-color","#D6D6FF");
				$(".tab_1 #merchantPhoto5Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_1 #merchant5").css("background-color","#D6D6FF");
    			$(".tab_1 #merchantPhoto5Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #otherPhoto1').val()==""){
				$(".tab_1 #other1").css("background-color","#FFFFCC");
				$(".tab_1 #otherPhoto1Mess").html("<img src=../image/cancel.png />"+"公司外景不能为空");
				return;
			}else{
				$(".tab_1 #other1").css("background-color","#D6D6FF");
    			$(".tab_1 #otherPhoto1Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #otherPhoto2').val()==""){
				$(".tab_1 #other2").css("background-color","#FFFFCC");
				$(".tab_1 #otherPhoto2Mess").html("<img src=../image/cancel.png />"+"公司门牌不能为空");
				return;
			}else{
				$(".tab_1 #other2").css("background-color","#D6D6FF");
    			$(".tab_1 #otherPhoto2Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #otherPhoto3').val()==""){
				$(".tab_1 #other3").css("background-color","#FFFFCC");
				$(".tab_1 #otherPhoto3Mess").html("<img src=../image/cancel.png />"+"公司内景不能为空");
				return;
			}else{
				$(".tab_1 #other3").css("background-color","#D6D6FF");
    			$(".tab_1 #otherPhoto3Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #otherPhoto4').val()==""){
				$(".tab_1 #other4").css("background-color","#FFFFCC");
				$(".tab_1 #otherPhoto4Mess").html("<img src=../image/cancel.png />"+"公司收银台不能为空");
				return;
			}else{
				$(".tab_1 #other4").css("background-color","#D6D6FF");
    			$(".tab_1 #otherPhoto4Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_1 #otherPhoto5').val()==""){
				$(".tab_1 #other5").css("background-color","#D6D6FF");
				$(".tab_1 #otherPhoto5Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_1 #other5").css("background-color","#D6D6FF");
    			$(".tab_1 #otherPhoto5Mess").html("<img src=../image/ok.png />");
			}
			
			var formData={
				loginName:$('.tab_1 #loginName').val(),
				merchantName:$('.tab_1 #merchantName').val(),
				linkMan:$('.tab_1 #linkMan').val(),
				
				linkPhone:$('.tab_1 #linkPhone').val(),
				mobile:$('.tab_1 #callPhone').val(),
				personIdCardNo:$('.tab_1 #personIdCardNo').val(),
				
				terminalPhone:$('.tab_1 #terminalPhone').val(),
				bankName:$('.tab_1 #bankName').val(),
				bankBranch:$('.tab_1 #bankBranch').val(),
			
				accountName:$('.tab_1 #accountName').val(),
				bankCardNo:$('.tab_1 #bankCardNo').val(),
				zipCode:$('.tab_1 #zipCode').val(),
			
				merchantAddr:$('.tab_1 #merchantAddr').val(),
				merchantNum:$('.tab_1 #merchantNum').val(),
				annualIncome:$('.tab_1 #annualIncome').val(),
				
				posUserType:$('.tab_1 #posUserType').val(),
				posCodeType:$('.tab_1 #posCodeType').val(),
				businessType:$('.tab_1 #businessType').val(),
				
				city:$('.tab_1 #city').val(),
				userGroupId:$('.tab_1 #userGroupId').val(),
				organNo:$('.tab_1 #organNo').val(),
				cropId:$('.tab_1 #cropId').val(),
				
				certificatesPhoto1:$('.tab_1 #CertificatesPhoto1').val(),
				certificatesPhoto2:$('.tab_1 #CertificatesPhoto2').val(),
				certificatesPhoto3:$('.tab_1 #CertificatesPhoto3').val(),
				certificatesPhoto4:$('.tab_1 #CertificatesPhoto4').val(),
				certificatesPhoto5:$('.tab_1 #CertificatesPhoto5').val(),
				
				merchantPhoto1:$('.tab_1 #merchantPhoto1').val(),
				merchantPhoto2:$('.tab_1 #merchantPhoto2').val(),
				merchantPhoto3:$('.tab_1 #merchantPhoto3').val(),
				merchantPhoto4:$('.tab_1 #merchantPhoto4').val(),
				merchantPhoto5:$('.tab_1 #merchantPhoto5').val(),
				
				otherPhoto1:$('.tab_1 #otherPhoto1').val(),
				otherPhoto2:$('.tab_1 #otherPhoto2').val(),
				otherPhoto3:$('.tab_1 #otherPhoto3').val(),
				otherPhoto4:$('.tab_1 #otherPhoto4').val(),
				otherPhoto5:$('.tab_1 #otherPhoto5').val()
			}
			$.post(url,formData,function(data){
				alert(data);
			});
		});
		
		$(".tab_2 #form2_ok").click(function(){
			var url = '../customermgr/addCustomer!addCustomer.action';
			if($(".tab_2 #loginName2").val() == "")
    		{
    			$(".tab_2 #loginName2").css("background-color","#FFFFCC");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/cancel.png />"+"登录名不可为空");
    			return false;
    		}
    		else{
    			$(".tab_2 #loginName2").css("background-color","#D6D6FF");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#loginName2").val())){
    		    $(".tab_2 #loginName2").css("background-color","#FFFFCC");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/cancel.png />"+"登录名只可包含任意字母、数字、下划线及中文");
    			return false;
    		}else{
    			$(".tab_2 #loginName2").css("background-color","#D6D6FF");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_2 #loginName2").attr("value").length<2) || ($("#loginName").attr("value").length>20)){
    		    $(".tab_2 #loginName2").css("background-color","#FFFFCC");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/cancel.png />"+"登录名长度只可为2~20位");
    			return false;
    		}else{
    			$(".tab_2 #loginName2").css("background-color","#D6D6FF");
    			$(".tab_2 #loginName2Mess").html("<img src=../image/ok.png />");
    		}
    		
			if($("#flag").val() != 0){
    				$(".tab_2 #loginName2").css("background-color","#FFFFCC");
    				$(".tab_2 #loginName2Mess").html("<img src=../image/cancel.png />"+"登录名重复！");
    				return false;
			}else{
					$(".tab_2 #loginName2").css("background-color","#D6D6FF");
    				$(".tab_2 #loginName2Mess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_2 #merchantName2").attr("value") =="")
    		{
    			$(".tab_2 #merchantName2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/cancel.png />"+"用户名不可为空");
    			return;
    		}
    		else{
    			$(".tab_2 #merchantName2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#merchantName2").val())){
    		    $(".tab_2 #merchantName2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/cancel.png />"+"用户名只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_2 #merchantName2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_2 #merchantName2").attr("value").length<2) || ($("#merchantName").attr("value").length>20)){
    		    $(".tab_2 #merchantName2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/cancel.png />"+"用户名长度只可为2~20位");
    			return;
    		}else{
    			$(".tab_2 #merchantName2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantName2Mess").html("<img src=../image/ok.png />");
    		}
			
			if($(".tab_2 #linkMan2").attr("value") =="")
    		{
    			$(".tab_2 #linkMan2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/cancel.png />"+"联系人不可为空");
    			return;
    		}
    		else{
    			$(".tab_2 #linkMan2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#linkMan2").val())){
    		    $(".tab_2 #linkMan2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/cancel.png />"+"联系人只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_2 #linkMan2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_2 #linkMan2").attr("value").length<2) || ($("#linkMan").attr("value").length>20)){
    		    $(".tab_2 #linkMan2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/cancel.png />"+"联系人长度只可为2~20位");
    			return;
    		}else{
    			$(".tab_2 #linkMan2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkMan2Mess").html("<img src=../image/ok.png />");
    		}
			
			if($(".tab_2 #linkPhone2").attr("value") =="")
    		 {
    		 	$(".tab_2 #linkPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/cancel.png />"+"联系电话不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #linkPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_2 #linkPhone2").attr("value").length != 8){
    		 	$(".tab_2 #linkPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/cancel.png />"+"联系电话号码必须为8位");
    			return;
    		 }else{
    		 	$(".tab_2 #linkPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#linkPhone2").val())){
    		 	$(".tab_2 #linkPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/cancel.png />"+"联系电话号码只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_2 #linkPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #linkPhone2Mess").html("<img src=../image/ok.png />");
    		 }
			
			 if($(".tab_2 #callPhone2").attr("value") =="")
    		 {
    		 	$(".tab_2 #callPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #callPhone2Mess").html("<img src=../image/ok.png />");
    		 }else{
    		 	if($(".tab_2 #callPhone2").attr("value").length != 11){
    		 	$(".tab_2 #callPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #callPhone2Mess").html("<img src=../image/cancel.png />"+"手机电话号码必须为11位");
    			return;
	    		 }else{
	    		 	$(".tab_2 #callPhone2").css("background-color","#D6D6FF");
	    			$(".tab_2 #callPhone2Mess").html("<img src=../image/ok.png />");
	    		 }
	    		 if(!/^[0-9\s]+$/.test($("#callPhone2").val())){
	    		 	$(".tab_2 #callPhone2").css("background-color","#FFFFCC");
	    			$(".tab_2 #callPhone2Mess").html("<img src=../image/cancel.png />"+"手机号码只能是数字");
	    			return;
	    		 }else
	    		 {
	    		 	$(".tab_2 #callPhone2").css("background-color","#D6D6FF");
	    			$(".tab_2 #callPhone2Mess").html("<img src=../image/ok.png />");
	    		 }
    		 } 
			
			if($(".tab_2 #personIdCardNo2").attr("value") =="")
    		 {
    		 	$(".tab_2 #personIdCardNo2").css("background-color","#FFFFCC");
    			$(".tab_2 #personIdCardNo2Mess").html("<img src=../image/cancel.png />"+"身份证号码不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #personIdCardNo2").css("background-color","#D6D6FF");
    			$(".tab_2 #personIdCardNo2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_2 #personIdCardNo2").attr("value").length != 18){
    		 	$(".tab_2 #personIdCardNo2").css("background-color","#FFFFCC");
    			$(".tab_2 #personIdCardNo2Mess").html("<img src=../image/cancel.png />"+"身份证号码必须为18位");
    			return;
    		 }else{
    		 	$(".tab_2 #personIdCardNo2").css("background-color","#D6D6FF");
    			$(".tab_2 #personIdCardNo2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_2 #terminalPhone2").attr("value") =="")
    		 {
    		 	$(".tab_2 #terminalPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/cancel.png />"+"绑定手机不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #terminalPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_2 #terminalPhone2").attr("value").length != 11){
    		 	$(".tab_2 #terminalPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/cancel.png />"+"绑定手机必须为11位");
    			return;
    		 }else{
    		 	$(".tab_2 #terminalPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#terminalPhone2").val())){
    		 	$(".tab_2 #terminalPhone2").css("background-color","#FFFFCC");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/cancel.png />"+"绑定手机只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_2 #terminalPhone2").css("background-color","#D6D6FF");
    			$(".tab_2 #terminalPhone2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_2 #bankName2").attr("value") =="")
    		 {
    		 	$(".tab_2 #bankName2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankName2Mess").html("<img src=../image/cancel.png />"+"开户银行不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #bankName2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankName2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_2 #bankName2").attr("value").length<4) || ($("#bankName").attr("value").length>40)){
    		    $(".tab_2 #bankName2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankName2Mess").html("<img src=../image/cancel.png />"+"开户银行长度只可为4~40位");
    			return;
    		 }else{
    			$(".tab_2 #bankName2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankName2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($("#bankBranch2").attr("value") =="")
    		 {
    		 	$(".tab_2 #bankBranch2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankBranch2Mess").html("<img src=../image/cancel.png />"+"支行名不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #bankBranch2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankBranch2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_2 #bankBranch2").attr("value").length<4) || ($("#bankBranch").attr("value").length>40)){
    		    $(".tab_2 #bankBranch2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankBranch2Mess").html("<img src=../image/cancel.png />"+"支行名长度只可为4~40位");
    			return;
    		 }else{
    			$(".tab_2 #bankBranch2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankBranch2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_2 #accountName2").attr("value") =="")
    		 {
    		 	$(".tab_2 #accountName2").css("background-color","#FFFFCC");
    			$(".tab_2 #accountName2Mess").html("<img src=../image/cancel.png />"+"开户户名不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #accountName2").css("background-color","#D6D6FF");
    			$(".tab_2 #accountName2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_2 #accountName2").attr("value").length<2) || ($("#accountName").attr("value").length>10)){
    		    $(".tab_2 #accountName2").css("background-color","#FFFFCC");
    			$(".tab_2 #accountName2Mess").html("<img src=../image/cancel.png />"+"开户户名长度只可为2~10位");
    			return;
    		 }else{
    			$(".tab_2 #accountName2").css("background-color","#D6D6FF");
    			$(".tab_2 #accountName2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		 if($(".tab_2 #bankCardNo2").attr("value") =="")
    		 {
    		 	$(".tab_2 #bankCardNo2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankCardNo2Mess").html("<img src=../image/cancel.png />"+"法人账号不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #bankCardNo2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankCardNo2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if(($(".tab_2 #bankCardNo2").attr("value").length<2) || ($("#bankCardNo").attr("value").length>50)){
    		    $(".tab_2 #bankCardNo2").css("background-color","#FFFFCC");
    			$(".tab_2 #bankCardNo2Mess").html("<img src=../image/cancel.png />"+"法人账号长度只可为2~50位");
    			return;
    		 }else{
    			$(".tab_2 #bankCardNo2").css("background-color","#D6D6FF");
    			$(".tab_2 #bankCardNo2Mess").html("<img src=../image/ok.png />");
    		 }
    	
    		  if($(".tab_2 #zipCode2").attr("value") =="")
    		 {
    		 	$(".tab_2 #zipCode2").css("background-color","#D6D6FF");
    			$(".tab_2 #zipCode2Mess").html("<img src=../image/ok.png />");
    		 }else{
    		 	if($(".tab_2 #zipCode2").attr("value").length != 6){
    		 	$(".tab_2 #zipCode2").css("background-color","#FFFFCC");
    			$(".tab_2 #zipCode2Mess").html("<img src=../image/cancel.png />"+"邮编必须为6位");
    			return;
	    		}else{
	    			$(".tab_2 #zipCode2").css("background-color","#D6D6FF");
	    			$(".tab_2 #zipCode2Mess").html("<img src=../image/ok.png />");
	    			}
	    		if(!/^[0-9\s]+$/.test($("#zipCode2").val())){
	    		 	$(".tab_2 #zipCode2").css("background-color","#FFFFCC");
	    			$(".tab_2 #zipCode2Mess").html("<img src=../image/cancel.png />"+"邮编只能是数字");
	    			return;
	    		}else{
	    		 	$(".tab_2 #zipCode2").css("background-color","#D6D6FF");
	    			$(".tab_2 #zipCode2Mess").html("<img src=../image/ok.png />");
	    		 	}
    		 }
    	
    		if($(".tab_2 #fax").val()==""){
    			$(".tab_2 #fax").css("background-color","#FFFFCC");
    			$(".tab_2 #faxMess").html("<img src=../image/cancel.png />"+"传真不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #fax").css("background-color","#D6D6FF");
    			$(".tab_2 #faxMess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_2 #fax").attr("value").length != 8){
    		 	$(".tab_2 #fax").css("background-color","#FFFFCC");
    			$(".tab_2 #faxMess").html("<img src=../image/cancel.png />"+"传真必须为8位");
    			return;
    		 }else{
    		 	$(".tab_2 #fax").css("background-color","#D6D6FF");
    			$(".tab_2 #faxMess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#fax").val())){
    		 	$(".tab_2 #fax").css("background-color","#FFFFCC");
    			$(".tab_2 #faxMess").html("<img src=../image/cancel.png />"+"传真只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_2 #fax").css("background-color","#D6D6FF");
    			$(".tab_2 #faxMess").html("<img src=../image/ok.png />");
    		 }
    	
			if($(".tab_2 #merchantStaff").val()==""){
				$(".tab_2 #merchantStaff").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantStaffMess").html("<img src=../image/cancel.png />"+"员工人数不能为空");
    			return;
    		 }else if($(".tab_2 #merchantStaff").attr("value") =="0")
    		 {
    		 	$(".tab_2 #merchantStaff").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantStaffMess").html("<img src=../image/cancel.png />"+"员工人数不能为0");
    			return;
    		 }else if(!/^[0-9\s]+$/.test($("#merchantStaff").val())){
    		 	$(".tab_2 #merchantStaff").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantStaffMess").html("<img src=../image/cancel.png />"+"员工人数只能是数字");
    			return;
    		 }else{
    		 	$(".tab_2 #merchantStaff").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantStaffMess").html("<img src=../image/ok.png />");
    		 }
    		 
    		if($(".tab_2 #legalPerson").val()==""){
    			$(".tab_2 #legalPerson").css("background-color","#FFFFCC");
    			$(".tab_2 #legalPersonMess").html("<img src=../image/cancel.png />"+"法人姓名不能为空");
    			return;
    		}else{
    		 	$(".tab_2 #legalPerson").css("background-color","#D6D6FF");
    			$(".tab_2 #legalPersonMess").html("<img src=../image/ok.png />");
    		 }
    		
    		if($(".tab_2 #businessLicenseNo").val()==""){
    			$(".tab_2 #businessLicenseNo").css("background-color","#FFFFCC");
    			$(".tab_2 #businessLicenseNoMess").html("<img src=../image/cancel.png />"+"营业执照不能为空");
    			return;
    		}else{
    		 	$(".tab_2 #businessLicenseNo").css("background-color","#D6D6FF");
    			$(".tab_2 #businessLicenseNoMess").html("<img src=../image/ok.png />");
    		 }
    		 if($(".tab_2 #merchantAddr2").attr("value") ==""){
    			$(".tab_2 #merchantAddr2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/cancel.png />"+"装机地址不可为空");
    			return;
    		}else{
    			$(".tab_2 #merchantAddr2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/ok.png />");
    		}
    		if(!/^[a-zA-Z0-9\u4e00-\u9fa5_\s]+$/.test($("#merchantAddr2").val())){
    		    $(".tab_2 #merchantAddr2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/cancel.png />"+"装机地址只可包含任意字母、数字、下划线及中文");
    			return;
    		}else{
    			$(".tab_2 #merchantAddr2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/ok.png />");
    		}
    		
    		if(($(".tab_2 #merchantAddr2").attr("value").length<4) || ($("#merchantAddr").attr("value").length>60)){
    		    $(".tab_2 #merchantAddr2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/cancel.png />"+"装机地址长度只可为4~60位");
    			return;
    		}else{
    			$(".tab_2 #merchantAddr2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantAddr2Mess").html("<img src=../image/ok.png />");
    		}		
    	
    		 if($(".tab_2 #merchantNum2").attr("value") =="")
    		 {
    		 	$(".tab_2 #merchantNum2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/cancel.png />"+"装机数不能为空");
    			return;
    		 }else{
    		 	$(".tab_2 #merchantNum2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/ok.png />");
    		 } 
    		 if($(".tab_2 #merchantNum2").attr("value") =="0")
    		 {
    		 	$(".tab_2 #merchantNum2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/cancel.png />"+"装机数不能为0");
    			return;
    		 }else{
    		 	$(".tab_2 #merchantNum2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/ok.png />");
    		 } 
			 if(($(".tab_2 #merchantNum2").attr("value").length<1) || ($("#merchantNum2").attr("value").length>10)){
    		 	$(".tab_2 #merchantNum2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/cancel.png />"+"装机数只可为1~10位");
    			return;
    		 }else{
    		 	$(".tab_2 #merchantNum2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/ok.png />");
    		 }
    		 if(!/^[0-9\s]+$/.test($("#merchantNum2").val())){
    		 	$(".tab_2 #merchantNum2").css("background-color","#FFFFCC");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/cancel.png />"+"装机数量只能是数字");
    			return;
    		 }else
    		 {
    		 	$(".tab_2 #merchantNum2").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantNum2Mess").html("<img src=../image/ok.png />");
    		 }
			
			if($(".tab_2 #annualIncome2").val()==0){
				$(".tab_2 #annualIncome2").css("background-color","#FFFFCC");
				$(".tab_2 #annualIncome2Mess").html("<img src=../image/cancel.png />"+"年收入不能为空");
				return;
			}else{
				$(".tab_2 #annualIncome2").css("background-color","#D6D6FF");
    			$(".tab_2 #annualIncome2Mess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_2 #posUserType2").val()==0){
				$(".tab_2 #posUserType2").css("background-color","#FFFFCC");
				$(".tab_2 #posUserType2Mess").html("<img src=../image/cancel.png />"+"用户类型不能为空");
				return;
			}else{
				$(".tab_2 #posUserType2").css("background-color","#D6D6FF");
    			$(".tab_2 #posUserType2Mess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_2 #posCodeType2").val()==0){
				$(".tab_2 #posCodeType2").css("background-color","#FFFFCC");
				$(".tab_2 #posCodeType2Mess").html("<img src=../image/cancel.png />"+"号码类型不能为空");
				return;
			}else{
				$(".tab_2 #posCodeType2").css("background-color","#D6D6FF");
    			$(".tab_2 #posCodeType2Mess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_2 #businessType2").val()==1){
				$(".tab_2 #businessType2").css("background-color","#FFFFCC");
				$(".tab_2 #businessType2Mess").html("<img src=../image/cancel.png />"+"营业类别不能为空");
				return;
			}else{
				$(".tab_2 #businessType2").css("background-color","#D6D6FF");
    			$(".tab_2 #businessType2Mess").html("<img src=../image/ok.png />");
			}
			
			if($(".tab_2 #city2").val()==0){
				$(".tab_2 #city2").css("background-color","#FFFFCC");
				$(".tab_2 #city2Mess").html("<img src=../image/cancel.png />"+"装机城市不能为空");
				return;
			}else{
				$(".tab_2 #city2").css("background-color","#D6D6FF");
    			$(".tab_2 #city2Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #CertificatesPhoto21').val()==""){
				$(".tab_2 #Certificates21").css("background-color","#FFFFCC");
				$(".tab_2 #CertificatesPhoto21Mess").html("<img src=../image/cancel.png />"+"营业执照副本不能为空");
				return;
			}else{
				$(".tab_2 #Certificates21").css("background-color","#D6D6FF");
    			$(".tab_2 #CertificatesPhoto21Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #CertificatesPhoto22').val()==""){
				$(".tab_2 #Certificates22").css("background-color","#FFFFCC");
				$(".tab_2 #CertificatesPhoto22Mess").html("<img src=../image/cancel.png />"+"税务登记证不能为空");
				return;
			}else{
				$(".tab_2 #Certificates22").css("background-color","#D6D6FF");
    			$(".tab_2 #CertificatesPhoto22Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #CertificatesPhoto23').val()==""){
				$(".tab_2 #Certificates23").css("background-color","#FFFFCC");
				$(".tab_2 #CertificatesPhoto3Mess").html("<img src=../image/cancel.png />"+"组织机构代码证不能为空");
				return;
			}else{
				$(".tab_2 #Certificates23").css("background-color","#D6D6FF");
    			$(".tab_2 #CertificatesPhoto23Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #CertificatesPhoto24').val()==""){
				$(".tab_2 #Certificates24").css("background-color","#FFFFCC");
				$(".tab_2 #CertificatesPhoto24Mess").html("<img src=../image/cancel.png />"+"法人身份证不能为空");
				return;
			}else{
				$(".tab_2 #Certificates24").css("background-color","#D6D6FF");
    			$(".tab_2 #CertificatesPhoto24Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #CertificatesPhoto25').val()==""){
				$(".tab_2 #Certificates25").css("background-color","#FFFFCC");
				$(".tab_2 #CertificatesPhoto25Mess").html("<img src=../image/cancel.png />"+"开户许可证不能为空");
				return;
			}else{
				$(".tab_2 #Certificates25").css("background-color","#D6D6FF");
    			$(".tab_2 #CertificatesPhoto25Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #merchantPhoto21').val()==""){
				$(".tab_2 #merchant21").css("background-color","#FFFFCC");
				$(".tab_2 #merchantPhoto21Mess").html("<img src=../image/cancel.png />"+"商户注册登记表不能为空");
				return;
			}else{
				$(".tab_2 #merchant21").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantPhoto21Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #merchantPhoto22').val()==""){
				$(".tab_2 #merchant22").css("background-color","#FFFFCC");
				$(".tab_2 #merchantPhoto22Mess").html("<img src=../image/cancel.png />"+"特约商户受理业务协议不能为空");
				return;
			}else{
				$(".tab_2 #merchant22").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantPhoto22Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #merchantPhoto23').val()==""){
				$(".tab_2 #merchant23").css("background-color","#FFFFCC");
				$(".tab_2 #merchantPhoto23Mess").html("<img src=../image/cancel.png />"+"商户注册登记表不能为空");
				return;
			}else{
				$(".tab_2 #merchant23").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantPhoto23Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #merchantPhoto24').val()==""){
				$(".tab_2 #merchant24").css("background-color","#D6D6FF");
				$(".tab_2 #merchantPhoto24Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_2 #merchant24").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantPhoto24Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #merchantPhoto25').val()==""){
				$(".tab_2 #merchant25").css("background-color","#D6D6FF");
				$(".tab_2 #merchantPhoto25Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_2 #merchant25").css("background-color","#D6D6FF");
    			$(".tab_2 #merchantPhoto25Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #otherPhoto21').val()==""){
				$(".tab_2 #other21").css("background-color","#FFFFCC");
				$(".tab_2 #otherPhoto21Mess").html("<img src=../image/cancel.png />"+"公司外景不能为空");
				return;
			}else{
				$(".tab_2 #other21").css("background-color","#D6D6FF");
    			$(".tab_2 #otherPhoto21Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #otherPhoto22').val()==""){
				$(".tab_2 #other22").css("background-color","#FFFFCC");
				$(".tab_2 #otherPhoto22Mess").html("<img src=../image/cancel.png />"+"公司门牌不能为空");
				return;
			}else{
				$(".tab_2 #other22").css("background-color","#D6D6FF");
    			$(".tab_2 #otherPhoto22Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #otherPhoto23').val()==""){
				$(".tab_2 #other23").css("background-color","#FFFFCC");
				$(".tab_2 #otherPhoto23Mess").html("<img src=../image/cancel.png />"+"公司内景不能为空");
				return;
			}else{
				$(".tab_2 #other23").css("background-color","#D6D6FF");
    			$(".tab_2 #otherPhoto23Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #otherPhoto24').val()==""){
				$(".tab_2 #other24").css("background-color","#FFFFCC");
				$(".tab_2 #otherPhoto24Mess").html("<img src=../image/cancel.png />"+"公司收银台不能为空");
				return;
			}else{
				$(".tab_2 #other24").css("background-color","#D6D6FF");
    			$(".tab_2 #otherPhoto24Mess").html("<img src=../image/ok.png />");
			}
			
			if($('.tab_2 #otherPhoto24').val()==""){
				$(".tab_2 #other24").css("background-color","#D6D6FF");
				$(".tab_2 #otherPhoto24Mess").html("<img src=../image/ok.png />");
				return;
			}else{
				$(".tab_2 #other24").css("background-color","#D6D6FF");
    			$(".tab_2 #otherPhoto24Mess").html("<img src=../image/ok.png />");
			}
    		 
			var formData={
				loginName:$('.tab_2 #loginName2').val(),
				merchantName:$('.tab_2 #merchantName2').val(),
				linkMan:$('.tab_2 #linkMan2').val(),
				
				linkPhone:$('.tab_2 #linkPhone2').val(),
				mobile:$('.tab_2 #callPhone2').val(),
				personIdCardNo:$('.tab_2 #personIdCardNo2').val(),
				
				terminalPhone:$('.tab_2 #terminalPhone2').val(),
				bankName:$('.tab_2 #bankName2').val(),
				bankBranch:$('.tab_2 #bankBranch2').val(),
			
				accountName:$('.tab_2 #accountName2').val(),
				bankCardNo:$('.tab_2 #bankCardNo2').val(),
				zipCode:$('.tab_2 #zipCode2').val(),
				
				fax:$(".tab_2 #fax").val(),
				merchantStaff:$(".tab_2 #merchantStaff").val(),
				legalPerson:$(".tab_2 #legalPerson").val(),
				businessLicenseNo:$(".tab_2 #businessLicenseNo").val(),
			
				merchantAddr:$('.tab_2 #merchantAddr2').val(),
				merchantNum:$('.tab_2 #merchantNum2').val(),
				
				posUserType:$('.tab_2 #posUserType2').val(),
				posCodeType:$('.tab_2 #posCodeType2').val(),
				businessType:$('.tab_2 #businessType2').val(),
				
				city:$('.tab_2 #city2').val(),
				userGroupId:$('.tab_2 #userGroupId2').val(),
				organNo:$('.tab_2 #organNo2').val(),
				cropId:$('.tab_2 #cropId2').val(),
				
				CertificatesPhoto1:$('.tab_2 #CertificatesPhoto21').val(),
				CertificatesPhoto2:$('.tab_2 #CertificatesPhoto22').val(),
				CertificatesPhoto3:$('.tab_2 #CertificatesPhoto23').val(),
				CertificatesPhoto4:$('.tab_2 #CertificatesPhoto24').val(),
				CertificatesPhoto5:$('.tab_2 #CertificatesPhoto25').val(),
				
				merchantPhoto1:$('.tab_2 #merchantPhoto21').val(),
				merchantPhoto2:$('.tab_2 #merchantPhoto22').val(),
				merchantPhoto3:$('.tab_2 #merchantPhoto23').val(),
				merchantPhoto4:$('.tab_2 #merchantPhoto24').val(),
				merchantPhoto5:$('.tab_2 #merchantPhoto25').val(),
				
				otherPhoto1:$('.tab_2 #otherPhoto21').val(),
				otherPhoto2:$('.tab_2 #otherPhoto22').val(),
				otherPhoto3:$('.tab_2 #otherPhoto23').val(),
				otherPhoto4:$('.tab_2 #otherPhoto24').val(),
				otherPhoto5:$('.tab_2 #otherPhoto25').val()
			}
			$.post(url,formData,function(data){
				alert(data);
			});
		});	
	
})