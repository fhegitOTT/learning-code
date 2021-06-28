<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>收件信息录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript">
		</script>	
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}
			
			.input_required:before{
				content:"* ";
				color:red;
			}
			tr{
				background-color:white;
			}
			
			.a{
				font-size:14px;
				height:26;
			}
			.b{
				font-size:16px;
			}		
		</style>
	<script type="text/javascript">
		function reset(){
	 		$("#frmInfo").form('clear');
		}
		
	 	function doDocSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
			document.getElementById("frmInfo").submit();
	 	}
	 	
	 	function doSelect1(){
	 		var a=$("input[name='Box1']").is(':checked');
	 		if(a==true){
	 		   	$('#comp_Info_Num').numberbox('setValue','1');
	 		   	$('#agent_Info_Num').numberbox('setValue','1');
	 		   	$('#signer_Reg_Num').numberbox('setValue','1');
 		 		$('#signer_Auth_Num').numberbox('setValue','1');
	 		   	$('#bizcard_Num').numberbox('setValue','1');
	   			$('#orgcard_Num').numberbox('setValue','1');
	   			$('#tax_Reg_Num').numberbox('setValue','1');
	   			$('#corp_Identity_Num').numberbox('setValue','1');
	   			$('#signer_Identity_Num').numberbox('setValue','1');
	   			$('#qualify_Num').numberbox('setValue','1');
	   			$('#org_Backup_Num').numberbox('setValue','1');
	   			$('#agent_Contract_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#comp_Info_Num').numberbox('setValue','0');
	 		   	$('#agent_Info_Num').numberbox('setValue','0');
	 		   	$('#signer_Reg_Num').numberbox('setValue','0');
 		 		$('#signer_Auth_Num').numberbox('setValue','0');
	 		   	$('#bizcard_Num').numberbox('setValue','0');
	   			$('#orgcard_Num').numberbox('setValue','0');
	   			$('#tax_Reg_Num').numberbox('setValue','0');
	   			$('#corp_Identity_Num').numberbox('setValue','0');
	   			$('#signer_Identity_Num').numberbox('setValue','0');
	   			$('#qualify_Num').numberbox('setValue','0');
	   			$('#org_Backup_Num').numberbox('setValue','0');
	   			$('#agent_Contract_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect2(){
	 		var a=$("input[name='Box2']").is(':checked');
	 		if(a==true){
	 		   	$('#price_Appl_Num').numberbox('setValue','1');
	 		   	$('#project_Appl_Num').numberbox('setValue','1');
	 		   	$('#part_List_Num').numberbox('setValue','1');
 		 		$('#real_Cert_Num').numberbox('setValue','1');
	 		   	$('#mapping_Num').numberbox('setValue','1');
	   			$('#presell_Cert_Num').numberbox('setValue','1');
	   			$('#plan_Map_Num').numberbox('setValue','1');
	   			$('#image_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#price_Appl_Num').numberbox('setValue','0');
	 		   	$('#project_Appl_Num').numberbox('setValue','0');
	 		   	$('#part_List_Num').numberbox('setValue','0');
 		 		$('#real_Cert_Num').numberbox('setValue','0');
	 		   	$('#mapping_Num').numberbox('setValue','0');
	   			$('#presell_Cert_Num').numberbox('setValue','0');
	   			$('#plan_Map_Num').numberbox('setValue','0');
	   			$('#image_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect3(){
	 		var a=$("input[name='Box3']").is(':checked');
	 		if(a==true){
	 		   	$('#house_Mod_Num').numberbox('setValue','1');
	 		   	$('#part_Mod_Num').numberbox('setValue','1');
	 		   	$('#signer_Mod_Auth_Num').numberbox('setValue','1');
 		 		$('#signer_Mod_Identity_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#house_Mod_Num').numberbox('setValue','0');
	 		   	$('#part_Mod_Num').numberbox('setValue','0');
	 		   	$('#signer_Mod_Auth_Num').numberbox('setValue','0');
 		 		$('#signer_Mod_Identity_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect4(){
	 		var a=$("input[name='Box4']").is(':checked');
	 		if(a==true){
	 		   	$('#agent_Identity_Num').numberbox('setValue','1');
	 		   	$('#movement_Cert_Num').numberbox('setValue','1');
	 		   	$('#explanation_Num').numberbox('setValue','1');
 		 		$('#etc_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#agent_Identity_Num').numberbox('setValue','0');
	 		   	$('#movement_Cert_Num').numberbox('setValue','0');
	 		   	$('#explanation_Num').numberbox('setValue','0');
 		 		$('#etc_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
	</script>
	<script type="text/javascript">
	
		function reset(){
	 		$("#frmInfo").form('clear');
			//document.getElementById("frmInfo").reset(); 
		}
		
		function gotoPrint(){
			window.open("<%=basePath%>/inner/document/documentPrint.action?document_Id="+${dvo.document_Id });
		}
		
	 	function doDocSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
			document.getElementById("frmInfo").submit();
	 	}	
	 	
	 	function doSelect1(){
	 		var a=$("input[name='Box1']").is(':checked');
	 		if(a==true){
	 		   	$('#comp_Info_Num').numberbox('setValue','1');
	 		   	$('#agent_Info_Num').numberbox('setValue','1');
	 		   	$('#signer_Reg_Num').numberbox('setValue','1');
 		 		$('#signer_Auth_Num').numberbox('setValue','1');
	 		   	$('#bizcard_Num').numberbox('setValue','1');
	   			$('#orgcard_Num').numberbox('setValue','1');
	   			$('#tax_Reg_Num').numberbox('setValue','1');
	   			$('#corp_Identity_Num').numberbox('setValue','1');
	   			$('#signer_Identity_Num').numberbox('setValue','1');
	   			$('#qualify_Num').numberbox('setValue','1');
	   			$('#org_Backup_Num').numberbox('setValue','1');
	   			$('#agent_Contract_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#comp_Info_Num').numberbox('setValue','0');
	 		   	$('#agent_Info_Num').numberbox('setValue','0');
	 		   	$('#signer_Reg_Num').numberbox('setValue','0');
 		 		$('#signer_Auth_Num').numberbox('setValue','0');
	 		   	$('#bizcard_Num').numberbox('setValue','0');
	   			$('#orgcard_Num').numberbox('setValue','0');
	   			$('#tax_Reg_Num').numberbox('setValue','0');
	   			$('#corp_Identity_Num').numberbox('setValue','0');
	   			$('#signer_Identity_Num').numberbox('setValue','0');
	   			$('#qualify_Num').numberbox('setValue','0');
	   			$('#org_Backup_Num').numberbox('setValue','0');
	   			$('#agent_Contract_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect2(){
	 		var a=$("input[name='Box2']").is(':checked');
	 		if(a==true){
	 		   	$('#price_Appl_Num').numberbox('setValue','1');
	 		   	$('#project_Appl_Num').numberbox('setValue','1');
	 		   	$('#part_List_Num').numberbox('setValue','1');
 		 		$('#real_Cert_Num').numberbox('setValue','1');
	 		   	$('#mapping_Num').numberbox('setValue','1');
	   			$('#presell_Cert_Num').numberbox('setValue','1');
	   			$('#plan_Map_Num').numberbox('setValue','1');
	   			$('#image_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#price_Appl_Num').numberbox('setValue','0');
	 		   	$('#project_Appl_Num').numberbox('setValue','0');
	 		   	$('#part_List_Num').numberbox('setValue','0');
 		 		$('#real_Cert_Num').numberbox('setValue','0');
	 		   	$('#mapping_Num').numberbox('setValue','0');
	   			$('#presell_Cert_Num').numberbox('setValue','0');
	   			$('#plan_Map_Num').numberbox('setValue','0');
	   			$('#image_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect3(){
	 		var a=$("input[name='Box3']").is(':checked');
	 		if(a==true){
	 		   	$('#house_Mod_Num').numberbox('setValue','1');
	 		   	$('#part_Mod_Num').numberbox('setValue','1');
	 		   	$('#signer_Mod_Auth_Num').numberbox('setValue','1');
 		 		$('#signer_Mod_Identity_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#house_Mod_Num').numberbox('setValue','0');
	 		   	$('#part_Mod_Num').numberbox('setValue','0');
	 		   	$('#signer_Mod_Auth_Num').numberbox('setValue','0');
 		 		$('#signer_Mod_Identity_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
 		function doSelect4(){
	 		var a=$("input[name='Box4']").is(':checked');
	 		if(a==true){
	 		   	$('#agent_Identity_Num').numberbox('setValue','1');
	 		   	$('#movement_Cert_Num').numberbox('setValue','1');
	 		   	$('#explanation_Num').numberbox('setValue','1');
 		 		$('#etc_Num').numberbox('setValue','1');
	 		}else{
	 		   	$('#agent_Identity_Num').numberbox('setValue','0');
	 		   	$('#movement_Cert_Num').numberbox('setValue','0');
	 		   	$('#explanation_Num').numberbox('setValue','0');
 		 		$('#etc_Num').numberbox('setValue','0');
	 			}
	 		}
	 		
</script>	
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<form id="frmInfo" name="frmInfo" method="post" action="<%=basePath%>/inner/document/doDocSave.action">
		<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
		<input type="hidden" name="documentId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("documentId")) %>"/>
	<br>
	
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF" align="center">
		<tr>
		    <td colspan="5" height="29"> <div align="center"><span class="a"><strong>收件信息录入</strong></span></div>
      		<div align="center"></div>
     		<div align="center"></div>
      		<div align="center"></div></td>
		</tr> 
  
		<tr height="25" >
        	<td class="input_left" bgcolor="#5BADFF" width="20%"><font style="color: black">收件标题：</font></td>
        	<td class="input_right" bgcolor="white" width="30%">
        		<input name="document_Title" class="easyui-textbox" type="text" data-options="validType:'length[0,30]'" value="${dvo.document_Title }"  style="width: 200px"></input>
        	</td>
        	<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: black">存放位置：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="position" class="easyui-textbox" type="text" data-options="validType:'length[0,30]'" value="${dvo.position }"  style="width: 200px"></input>
        	</td>
			<!--<td class="input_left" bgcolor="white" width="20%" ><font style="color: white"></font></td>-->
		</tr>
		<tr height="25" >
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">申请企业：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="company_Name" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.company_Name }"  style="width: 150px"></input>
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">售楼处地址：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="company_Address" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.company_Address }"  style="width: 300px"></input>
        	</td>
		</tr>
		<tr height="25" >
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">售楼处电话：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="company_Phone" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.company_Phone }"  style="width: 150px"></input>
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">申报项目地址：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="apply_Proj_Addr" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.apply_Proj_Addr }"  style="width: 300px"></input>
        	</td>
		</tr>
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">项目所处的范围：</font></td>
        	<td class="input_right" bgcolor="white">
				<input class="easyui-combobox" name="districtid" id="districtid" value="${dvo.districtid }"
					data-options="url:'<%=basePath%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">项目名称：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="project_Name" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.project_Name }"  style="width: 200px"></input>
        	</td>
		</tr>
		<tr height="25" >
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">代理人：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="agent" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.agent}"  style="width: 150px"></input>
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">联系电话：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="agent_Phone" class="easyui-numberbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.agent_Phone }"  style="width: 150px"></input>
        	</td>
		</tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="4"> <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>提交文件名称</strong> </div></td>
          <td width="20%"><div align="center"> <strong>份数</strong> </div></td>
        </tr>
		 
        <tr bgcolor="E6F8E3"> 
          <td width="3%" rowspan="12"><p align="center" class="b">企</p>
            <p align="center" class="b">业</p>
            <p align="center" class="b">认</p>
            <p align="center" class="b">证</p>
			<p align="center"><input type="checkbox" name="Box1" onClick="doSelect1(this);"> </p>
			</td>
          <td colspan="3" class="a"><span id="LBL_COMP_INFO_NUM">[表一] 房地产开发企业情况表（原件）</td>
          <td><div align="center"> 
              <input name="comp_Info_Num" id="comp_Info_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.comp_Info_Num }" />
			</div></td>               
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_AGENT_INFO_NUM">[表二] 房地产代理企业情况表（原件）</td>
          <td><div align="center"> 
              <input name="agent_Info_Num" id="agent_Info_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.agent_Info_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_SIGNER_REG_NUM">[表六] 开发企业或代理企业网上房地产操作人员申报表（原件）</td>
          <td><div align="center"> 
              <input name="signer_Reg_Num" id="signer_Reg_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.signer_Reg_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_SIGNER_AUTH_NUM">[表七] 操作人员网上操作权限申报表（原件）</td>
          <td><div align="center"> 
              <input name="signer_Auth_Num" id="signer_Auth_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.signer_Auth_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_BIZCARD_NUM">营业执照（复印件）</td>
          <td><div align="center"> 
              <input name="bizcard_Num" id="bizcard_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.bizcard_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_ORGCARD_NUM">组织机构代码证（复印件）</td>
          <td><div align="center"> 
              <input name="orgcard_Num" id="orgcard_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.orgcard_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_TAX_REG_NUM">税务注册证明（复印件）</td>
          <td><div align="center"> 
              <input name="tax_Reg_Num" id="tax_Reg_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.tax_Reg_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_CORP_IDENTITY_NUM"> 法定代表人身份证（复印件）</td>
          <td><div align="center"> 
              <input name="corp_Identity_Num" id="corp_Identity_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.corp_Identity_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_SIGNER_IDENTITY_NUM">网上操作人员的销售员证书、身份证（复印件）及照片</td>
          <td><div align="center"> 
              <input name="signer_Identity_Num" id="signer_Identity_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.signer_Identity_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_QUALIFY_NUM">房地产开发企业资质证书（复印件）</td>
          <td><div align="center"> 
              <input name="qualify_Num" id="qualify_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.qualify_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_ORG_BACKUP_NUM">房地产经纪组织备案表（复印件）</td>
          <td><div align="center"> 
              <input name="org_Backup_Num" id="org_Backup_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.org_Backup_Num}" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_AGENT_CONTRACT_NUM">代理销售合同（复印件）</td>
          <td><div align="center"> 
              <input name="agent_Contract_Num" id="agent_Contract_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.agent_Contract_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td rowspan="8"><p align="center" class="b">项</p>
            <p align="center" class="b">目</p>
            <p align="center" class="b">认</p>
            <p align="center" class="b">证</p>
			<p align="center"><input type="checkbox" name="Box2" onClick="doSelect2(this);"></p>
			</td>
          <td height="15" colspan="3" class="a"><span id="LBL_PRICE_APPL_NUM">[表三] 预、销售房地产网上备案项目按幢售价申报表（原件）</td>
          <td><div align="center"> 
              <input name="price_Appl_Num" id="price_Appl_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.price_Appl_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td height="18" colspan="3" class="a"><span id="LBL_PROJECT_APPL_NUM">[表四] 预、销售房地产网上备案项目申报表（原件）</td>
          <td><div align="center"> 
              <input name="project_Appl_Num" id="project_Appl_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.project_Appl_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_PART_LIST_NUM">[表五] 预、销售房地产项目已售未登记部位清单（原件）</td>
          <td><div align="center"> 
              <input name="part_List_Num" id="part_List_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.part_List_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_REAL_CERT_NUM"> 房地产权证（复印件）</td>
          <td><div align="center"> 
              <input name="real_Cert_Num" id="real_Cert_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.real_Cert_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_MAPPING_NUM">房地产测绘报告（复印件）</td>
          <td><div align="center"> 
              <input name="mapping_Num" id="mapping_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.mapping_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_PRESELL_CERT_NUM"> 预售许可证（复印件）</td>
          <td><div align="center"> 
              <input name="presell_Cert_Num" id="presell_Cert_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.presell_Cert_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_PLAN_MAP_NUM">小区规划平面图</td>
          <td><div align="center"> 
              <input name="plan_Map_Num" id="plan_Map_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.plan_Map_Num }" />
            </div></td>
        </tr>
		<tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_IMAGE_NUM">小区鸟瞰图、房屋立面效果彩图和各户型平面图（原件）</td>
          <td><div align="center"> 
              <input name="image_Num" id="image_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体"  value="${dcvo.image_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3" bgcolor="#000400"> 
          <td rowspan="4">
		    <p align="center" class="b">变</p>
            <p align="center" class="b">更</p>
			<p align="center"><input type="checkbox" name="Box3" onClick="doSelect3(this);"></p>
			</td>
          <td height="14" colspan="3" class="a"><span id="LBL_HOUSE_MOD_NUM">[表八] 房地产开发企业商品房屋入网认证变更申请表（原件）</td>
          <td><div align="center"> 
              <input name="house_Mod_Num" id="house_Mod_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.house_Mod_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"> <span id="LBL_PART_MOD_NUM">房屋部位的变更状况的情况说明（原件）</td>
          <td><div align="center"> 
              <input name="part_Mod_Num" id="part_Mod_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.part_Mod_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_SIGNER_MOD_AUTH_NUM">[表九] 网上房地产操作人员权限变更申请表（原件）</td>
          <td><div align="center"> 
              <input name="signer_Mod_Auth_Num" id="signer_Mod_Auth_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.signer_Mod_Auth_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td height="18" colspan="3" class="a"><span id="LBL_SIGNER_MOD_IDENTITY_NUM"> 网上操作人员的销售员证书、身份证（复印件）及照片</td>
          <td><div align="center"> 
              <input name="signer_Mod_Identity_Num" id="signer_Mod_Identity_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.signer_Mod_Identity_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td rowspan="4"> 
		    <p align="center" class="b">其</p>
            <p align="center" class="b">它</p>
			<p align="center" class="b"><input type="checkbox" name="Box4" onClick="doSelect4(this);"></p></td>
          <td colspan="3" class="a"> <span id="LBL_AGENT_IDENTITY_NUM">委托书（原件）及代理人身份证（复印件）</td>
          <td><div align="center"> 
              <input name="agent_Identity_Num" id="agent_Identity_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.agent_Identity_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td colspan="3" class="a"><span id="LBL_MOVEMENT_CERT_NUM">区市一级相关建委、建设局或房地局出具的动迁房源有关证明</td>
          <td><div align="center"> 
              <input name="movement_Cert_Num" id="movement_Cert_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.movement_Cert_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3">
          <td colspan="3" class="a"><span id="LBL_EXPLANATION_NUM">保留说明一份</td>
          <td><div align="center">
              <input name="explanation_Num" id="explanation_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.explanation_Num }" />
            </div></td>
        </tr>
        <tr bgcolor="E6F8E3"> 
          <td height="18" colspan="3" class="a"><span id="LBL_ETC_NUM">其他(详见备注)</td>
          <td><div align="center">
              <input name="etc_Num" id="etc_Num" type="text" class="easyui-numberbox" style="width:30px;height:16px;font-family:宋体" value="${dcvo.etc_Num }" />
            </div></td>
        </tr>
        
         <tr bgcolor="E6F8E3"> 
		    <td height="30"> <div align="center"><span id="LBL_CONTENT" class="b">备注</span>：</div></td>
		    <td colspan="4"> <textarea wrap="soft"  class="box" name="content" cols="120" rows="10">${dvo.content }</textarea></td>
  		</tr>
		<tr height="25" >
        	<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: black">收件人：</font></td>
        	<td class="input_right" bgcolor="white">
        		${smUserVO.displayName }
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF" width="20%"><font style="color: black">收件日期：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		${date }
        	</td>
		</tr>
		<tr height="25" >
        	<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: black">传真：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="document_Fax" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.document_Fax }"  style="width: 200px"></input>
        	</td>
        	<td class="input_left input_required" bgcolor="#5BADFF" width="20%"><font style="color: black">联系电话：</font></td>
        	<td class="input_right" bgcolor="white" colspan="2">
        		<input name="document_Phone" class="easyui-numberbox" type="text" data-options="required:true,validType:'length[0,30]'" value="${dvo.document_Phone }"  style="width: 200px"></input>
        	</td>
		</tr>
	</table>	
		<br/>
		
		<div align="center">
		<c:if test="${cmd=='add'}">
			<a href="javascript:doDocSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
			<a href="javascript:reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重置</a>
			<a href="<%=basePath%>/inner/document/docList.action" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">一览查询</a>
		</c:if>	
		<c:if test="${cmd=='edit'}">
			<a href="javascript:doDocSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">修改</a>
			<a href="javascript:reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">重置</a>
			<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		</c:if>
		<c:if test="${cmd=='show'}">
			<a href="javascript:gotoPrint()" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">打印</a>
			<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		</c:if>		
		</div>
	</form>
</body>
</html>
