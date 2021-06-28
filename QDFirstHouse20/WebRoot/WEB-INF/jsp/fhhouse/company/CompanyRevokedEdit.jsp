 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO"%>
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
	 	function doCancel(){
			window.location.href="<%=path%>/inner/companymanage/doCompanyCancel.action?status=${eqvo.status}&compID="+${eqvo.comp_ID};
	 	}
	 	
		function doCopy(newval,oldval){
			$("#legalManCode").textbox("setValue",newval);
		}
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="comp_ID" value="${eqvo.comp_ID}"/>
	<input type="hidden" name="status" value="${eqvo.status}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="3"><font style="color: white"></font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">企业名称</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="name" id="name"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.name}" style="width: 500px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">企业全国唯一编码</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="comp_Code" id="comp_Code"  data-options="required:true,validType:'length[0,30]',onChange:doCopy,editable:false"  value="${eqvo.comp_Code}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">法人代码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="legalManCode" id="legalManCode"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.legalManCode}" style="width: 150px"></input>
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
				<input class="easyui-textbox" type="text" name="bizregister_Num" id="bizregister_Num"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.bizregister_Num}" style="width: 150px"></input>
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
				<input class="easyui-textbox" type="text" name="regadr" id="regadr"  data-options="required:true,validType:'length[0,50]',editable:false"  value="${eqvo.regadr}" style="width: 300px"></input>
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
				<input class="easyui-textbox" type="text" name="delegate" id="delegate"  data-options="required:true,validType:'length[0,20]',editable:false"  value="${eqvo.delegate}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Call" id="dlg_Call"  data-options="required:true,validType:'length[0,20]',editable:false"  value="${eqvo.dlg_Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">身份证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="dlg_Cardname" id="dlg_Cardname"  data-options="required:true,validType:'length[0,15]',editable:false"  value="${eqvo.dlg_Cardname}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">身份证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Cardcode" id="dlg_Cardcode"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.dlg_Cardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:true,validType:'length[0,50]',editable:false"  value="${eqvo.contactadr}" style="width: 250px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlg_Postcode" id="dlg_Postcode"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.dlg_Postcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">总经理</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="manager" id="manager"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${eqvo.manager}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">注册资本(元)</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="registerMoney" id="registerMoney"  data-options="required:true,validType:'length[0,15]',editable:false" precision="2" max="9999999999.99" size="12"  value="${eqvo.registerMoney}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">总资产(元)</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="totalMoney" id="totalMoney"  data-options="required:true,validType:'length[0,15]',editable:false" precision="2" max="9999999999.99" size="12"  value="${eqvo.totalMoney}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">净资产(元)</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="pureMoney" id="pureMoney"  data-options="required:true,validType:'length[0,15]',editable:false" precision="2" max="9999999999.99" size="12"  value="${eqvo.pureMoney}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="6" align="center"><font style="color: white">经<br/>营<br/>处<br/>所<br/>及<br/>代<br/>理<br/>人<br/>信<br/>息</font></td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">经营地址(办公地址)</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bizadr" id="bizadr"  data-options="required:true,validType:'length[0,50]',editable:false"  value="${eqvo.bizadr}" style="width: 250px"></input>
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
				<input class="easyui-textbox" type="text" name="tel" id="tel"  data-options="required:true,validType:'length[0,20]',editable:false"  value="${eqvo.tel}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="post" id="post"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${eqvo.post}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">传真</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="fax" id="fax"  data-options="validType:'length[0,20]',editable:false"  value="${eqvo.fax}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电子信箱</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="email" id="email"  data-options="validType:'length[0,50]',editable:false"  value="${eqvo.email}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy" id="proxy"  data-options="validType:'length[0,20]',editable:false"  value="${eqvo.proxy}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Call" id="proxy_Call"  data-options="validType:'length[0,20]',editable:false"  value="${eqvo.proxy_Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy_Cardname" id="proxy_Cardname"  data-options="validType:'length[0,20]',editable:false"  value="${eqvo.proxy_Cardname}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Cardcode" id="proxy_Cardcode"  data-options="validType:'length[0,20]',editable:false"  value="${eqvo.proxy_Cardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy_Contact" id="proxy_Contact"  data-options="validType:'length[0,30]',editable:false"  value="${eqvo.proxy_Contact}" style="width: 250px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy_Postcode" id="proxy_Postcode"  data-options="validType:'length[0,10]',editable:false"  value="${eqvo.proxy_Postcode}" style="width: 100px"></input>
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
				<input class="easyui-textbox" type="text" name="aptitudeNum" id="aptitudeNum"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.aptitudeNum}" style="width: 150px"></input>
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
				<input class="easyui-textbox" type="text" name="curnum" id="curnum"  data-options="validType:'length[0,10]',editable:false"  value="${eqvo.curnum}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">高级职称人数</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="highnum" id="highnum"  data-options="validType:'length[0,10]',editable:false"  value="${eqvo.highnum}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">中级职称人数</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="midnum" id="midnum"  data-options="validType:'length[0,10]',editable:false"  value="${eqvo.midnum}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">初级职称人数</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="lownum" id="lownum"  data-options="validType:'length[0,10]',editable:false"  value="${eqvo.lownum}" style="width: 150px"></input>
			</td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">经营范围：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="6" name="workScope" id="workScope" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" readonly="readonly">${eqvo.workScope}</textarea>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2" ><font style="color: white">操作人员资料：</font></td>
        	<td class="input_right" colspan="3">
        		<%
						String htmlview1 = String.valueOf(request.getAttribute("htmlView1"));
						if(htmlview1!=null && !"".equals(htmlview1) && !htmlview1.equals("null")){
						    out.println(htmlview1);
					}%>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2" ><font style="color: white">企业项目信息表：</font></td>
        	<td class="input_right" colspan="3">
        		<%
						String htmlview2 = String.valueOf(request.getAttribute("htmlView2"));
						if(htmlview2!=null && !"".equals(htmlview2) && !htmlview2.equals("null")){
						    out.println(htmlview2);
					}%>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2" ><font style="color: white">预售许可证信息及开盘单元：</font></td>
        	<td class="input_right" colspan="3">
        		<%
						String htmlview3 = String.valueOf(request.getAttribute("htmlView3"));
						if(htmlview3!=null && !"".equals(htmlview3) && !htmlview3.equals("null")){
						    out.println(htmlview3);
					}%>
			</td> 
		</tr>
		<%
			List<EnterpriseQualifyVO> complist=(List)request.getAttribute("complist");
			for(EnterpriseQualifyVO compVO:complist){
		 %>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"  colspan="2"><font style="color: white">初审人</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="firstCensor" id="firstCensor"  data-options="required:true,validType:'length[0,30]',onChange:doCopy,editable:false"  value="<%=compVO.getFirstCensor() %>" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">初审日期</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="firstAuditDate" id="firstAuditDate"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.firstDate}" style="width: 150px"></input>
			</td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">初审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]',editable:false" readonly="readonly">${eqvo.firstMark}</textarea>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%" colspan="2"><font style="color: white">复审人</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="finalCensor" id="finalCensor"  data-options="required:true,validType:'length[0,30]',onChange:doCopy,editable:false"  value="<%=compVO.getFinalCensor() %>" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">复审日期</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="finalAuditDate" id="finalAuditDate"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${eqvo.finalDate}" style="width: 150px"></input>
			</td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">复审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="finalMark" id="finalMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]',editable:false" readonly="readonly">${eqvo.finalMark}</textarea>
			</td> 
		</tr>
		<%
			}
		 %>
	</table>
	
	
	<br/>
	<div align="center">
		<c:if test="${eqvo.status==2}">
		<a href="javascript:doCancel()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">撤销资质</a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${eqvo.status==5}">
		<a href="javascript:doCancel()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">恢复资质</a>&nbsp;&nbsp;
		</c:if>
		<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
		
	</div>
	<br/>
	</form>
</body>
</html>