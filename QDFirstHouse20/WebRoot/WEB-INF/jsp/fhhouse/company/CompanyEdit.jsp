 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>开发企业编辑页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>		
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}
			
			tr{
				background-color:white;
			}		
		</style>
		

	<script type="text/javascript">

	 	function doBack(){
	 		var a=$("input[name='status']").val();
	 		if( a=='0'){
				window.location = "<%=path%>/inner/companymanage/gotoCompanyAddQuery.action";
	 		}else{
				window.location = "<%=path%>/inner/companymanage/gotoCompanyUpdateQuery.action";
	 		}
			//window.location = "<%=path%>/inner/companymanage/doCompanyEditBack.action";
	 	}
	 	
	 	function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		if(!checkData()){
	 			return;
	 		}	 		
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/companymanage/doCompanySaveCheck.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							frmInfo.submit();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("检查失败！");
					}
				});
	 	}
	 	
	 	function doCheckSeaID(flag){
	 		var json = "";
	 		var comp_ID = $("#comp_ID").val();
	 		if(flag == 1){//1、企业
	 			var comp_SealID = $("#comp_SealID").textbox("getValue");
	 			var comp_SealPwd = $("#comp_SealPwd").textbox("getValue");
	 			if(comp_SealID == '' || comp_SealPwd == ''){
	 				alert("企业章或者密码不能为空！");
	 				return;
	 			}
	 			json = {"flag":flag,"comp_ID":comp_ID,"sealID":comp_SealID,"sealPwd":comp_SealPwd};
	 		}else{//2、法人
	 			var delegate_SealID = $("#delegate_SealID").textbox("getValue");
		 		var delegate_SealPwd = $("#delegate_SealPwd").textbox("getValue");
		 		if(delegate_SealID == '' || delegate_SealPwd == ''){
	 				alert("企业法人章或者密码不能为空！");
	 				return;
	 			}
	 			json = {"flag":flag,"comp_ID":comp_ID,"sealID":delegate_SealID,"sealPwd":delegate_SealPwd};
	 		}
	 		$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/ContractPdf/createSealPdf.action",
				data : json ,
				dataType : "json",
				success : function(data){
					if(data[0].result=="1"){
						window.open("<%=path%>/inner/ContractPdf/previewSeal.action?comp_ID="+comp_ID+"&flag="+flag);
					}else{
						alert(data[0].msg);
					}
				},
				error : function(){
					alert("检查失败！");
				}
			});
	 	}
	 	
	 	function doFirstSubmit(){
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/companymanage/doCompanyFirstSubmit.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							doBack();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("提交审核失败！");
					}
				});
	 	}
	 	
	 	function gotoSigner(){
			window.location="<%=path%>/inner/companymanage/gotoSignerEditList.action?compId="+frmInfo.comp_ID.value;
			
		}
		
		function gotoProject(){
			window.location="<%=path%>/inner/projectmanage/gotoProjectEditList.action?logo=${logo}&compId="+frmInfo.comp_ID.value;
		}
	
		
		function checkData(){
			var effectstartDate = $("#effectstartDate").datebox("getValue");
	 		var effectendDate = $("#effectendDate").datebox("getValue");
	 		if(effectstartDate!=null && effectendDate!=""){
	 			 if(effectstartDate>effectendDate){
	 				alert("资质证书失效时间不能在生效时间之前！");	
					return false;
		 		 }
	 		}
		/*	//身份证验证
	        var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        	var d=$('#dlg_Cardcode').val();
        	if(!pattern.test(d) && d!=''){
        		alert("请输入正确的身份证号码！");
	            $("#dlg_Cardcode").textbox("textbox").focus();
	            return false;   		        		
        	}*/

	 		return true;
		}
		function doCopy(newval,oldval){
			$("#legalManCode").textbox("setValue",newval);
		}
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
	<input type="hidden" name="comp_ID" id="comp_ID" value="${eqvo.comp_ID}"/>
	<input type="hidden" name="status" value="${eqvo.status}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="5"><font style="color: white"></font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">企业名称</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="name" id="name"  data-options="required:true,validType:'length[0,30]'"  value="${eqvo.name}" style="width: 500px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">企业全国唯一编码</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="comp_Code" id="comp_Code"  data-options="required:true,validType:'length[0,30]',onChange:doCopy"  value="${eqvo.comp_Code}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">法人代码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="legalManCode" id="legalManCode"  data-options="required:true,validType:'length[0,30]'"  value="${eqvo.legalManCode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">企业章</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="comp_SealID" id="comp_SealID"  data-options="validType:'length[0,30]'"  value="${eqvo.comp_SealID}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">企业章密码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="comp_SealPwd" id="comp_SealPwd"  data-options="validType:'length[0,30]'"  value="${eqvo.comp_SealPwd}" style="width: 150px"></input>
				<a href="javascript:doCheckSeaID(1)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">验证</a>&nbsp;&nbsp;
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">法人章</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="delegate_SealID" id="delegate_SealID"  data-options="validType:'length[0,30]'"  value="${eqvo.delegate_SealID}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">法人章密码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="delegate_SealPwd" id="delegate_SealPwd"  data-options="validType:'length[0,30]'"  value="${eqvo.delegate_SealPwd}" style="width: 150px"></input>
				<a href="javascript:doCheckSeaID(2)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">验证</a>&nbsp;&nbsp;
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">企业类型</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="type" id="type" value="${eqvo.type }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_303',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在省市</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="provinceCode" id="provinceCode" value="${eqvo.provinceCode }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_524',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="8" align="center"><font style="color: white">营<br/>业<br/>执<br/>照<br/>信<br/>息</font></td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">法人营业执照注册号</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bizregister_Num" id="bizregister_Num"  data-options="required:true,validType:'length[0,30]'"  value="${eqvo.bizregister_Num}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">登记注册类型</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="regtype_Num" id="regtype_Num" value="${eqvo.regtype_Num}"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_302',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">营业执照注册地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="regadr" id="regadr"  data-options="required:true,validType:'length[0,50]'"  value="${eqvo.regadr}" style="width: 300px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在区市</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="district" id="district" value="${eqvo.district }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_DISTRICT',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">工商注册日</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="bizreg_Date" id="bizreg_Date" data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.bizreg_Date2}" ></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">营业执照到期日</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="bizend_Date" id="bizend_Date"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.bizend_Date2}"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">法人代表</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="delegate" id="delegate"  data-options="required:true,validType:'length[0,20]'"  value="${eqvo.delegate}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Call" id="dlg_Call"  data-options="required:true,validType:'length[0,20]'"  value="${eqvo.dlg_Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">身份证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="dlg_Cardname" id="dlg_Cardname"  data-options="required:true,validType:'length[0,15]'"  value="${eqvo.dlg_Cardname}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">身份证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Cardcode" id="dlg_Cardcode"  data-options="required:true,validType:'length[0,30]'"  value="${eqvo.dlg_Cardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:true,validType:'length[0,50]'"  value="${eqvo.contactadr}" style="width: 250px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Postcode" id="dlg_Postcode"  data-options="required:true,validType:'length[0,10]'"  value="${eqvo.dlg_Postcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">总经理</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="manager" id="manager"  data-options="required:false,validType:'length[0,20]'"  value="${eqvo.manager}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">注册资本(元)</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="registerMoney" id="registerMoney"  data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12"  value="${eqvo.registerMoney}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">总资产(元)</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="totalMoney" id="totalMoney"  data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12"  value="${eqvo.totalMoney}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">净资产(元)</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="pureMoney" id="pureMoney"  data-options="required:true,validType:'length[0,15]'" precision="2" max="9999999999.99" size="12"  value="${eqvo.pureMoney}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="6" align="center"><font style="color: white">经<br/>营<br/>处<br/>所<br/>及<br/>代<br/>理<br/>人<br/>信<br/>息</font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">经营地址(办公地址)</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bizadr" id="bizadr"  data-options="required:true,validType:'length[0,50]'"  value="${eqvo.bizadr}" style="width: 250px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">所在区市</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="bizdistrict" id="bizdistrict" value="${eqvo.bizdistrict }"
					data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="tel" id="tel"  data-options="required:true,validType:'length[0,20]'"  value="${eqvo.tel}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="post" id="post"  data-options="required:true,validType:'length[0,10]'"  value="${eqvo.post}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">传真</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="fax" id="fax"  data-options="validType:'length[0,20]'"  value="${eqvo.fax}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电子信箱</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="email" id="email"  data-options="validType:'length[0,50]'"  value="${eqvo.email}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy" id="proxy"  data-options="validType:'length[0,20]'"  value="${eqvo.proxy}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Call" id="proxy_Call"  data-options="validType:'length[0,20]'"  value="${eqvo.proxy_Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy_Cardname" id="proxy_Cardname"  data-options="validType:'length[0,20]'"  value="${eqvo.proxy_Cardname}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Cardcode" id="proxy_Cardcode"  data-options="validType:'length[0,20]'"  value="${eqvo.proxy_Cardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy_Contact" id="proxy_Contact"  data-options="validType:'length[0,30]'"  value="${eqvo.proxy_Contact}" style="width: 250px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Postcode" id="proxy_Postcode"  data-options="validType:'length[0,10]'"  value="${eqvo.proxy_Postcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="3" align="center"><font style="color: white">开<br/>发<br/>资<br/>质<br/>信<br/>息</font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">资质等级</font></td>
			<td class="input_right" >
				<input class="easyui-combobox" name="aptitudeLevel" id="aptitudeLevel" value="${eqvo.aptitudeLevel }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_301',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">资质证书发证编号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="aptitudeNum" id="aptitudeNum"  data-options="required:true,validType:'length[0,30]'"  value="${eqvo.aptitudeNum}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">资质证书发证日期</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="passDate" id="passDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.passDate2}"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">批准从事房地产开发经营日期</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="passbizDate" id="passbizDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.passbizDate2}"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">资质证书有效起始日期</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="effectstartDate" id="effectstartDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.effectstartDate2}"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">资质证书有效终止日期</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="effectendDate" id="effectendDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.effectendDate2}"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="2" align="center"><font style="color: white">人<br/>员<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">在册人员总数</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="curnum" id="curnum"  data-options="validType:'length[0,10]'"  value="${eqvo.curnum}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">高级职称人数</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="highnum" id="highnum"  data-options="validType:'length[0,10]'"  value="${eqvo.highnum}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">中级职称人数</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="midnum" id="midnum"  data-options="validType:'length[0,10]'"  value="${eqvo.midnum}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">初级职称人数</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="lownum" id="lownum"  data-options="validType:'length[0,10]'"  value="${eqvo.lownum}" style="width: 150px"></input>
			</td>
		</tr>
		
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">经营范围：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="6" name="workScope" id="workScope" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${eqvo.workScope}</textarea>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">初审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${eqvo.firstMark}</textarea>
			</td> 
		</tr>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
		<c:if test="${cmd=='edit'}">
		<a href="javascript:doFirstSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交审核</a>&nbsp;&nbsp;
		<a href="javascript:gotoProject()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">项目信息</a>&nbsp;&nbsp;
		<a href="javascript:gotoSigner()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px">签约人信息</a>&nbsp;&nbsp;
		</c:if>
	</div>
	<br/>
	</form>
</body>
</html>
