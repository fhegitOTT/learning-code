 <%@ page contentType = "text/html; charset=UTF-8" %>
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

		function reset(){
			//document.getElementById("frmInfo").reset(); 
	 		$("#frmInfo").form('clear');
		}
	 	
	 	function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		var a=$("input[name='status']").val();
	 		if(a!=0){
	 			alert("代理商不处于编辑状态，不能编辑修改！");
	 			return;
	 			}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/agentmanage/doAgentSaveCheck.action",
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
	 	
	 	function doFirstSubmit(){
	 		var a=$("input[name='logo']").val();
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/agentmanage/doAgentFirstSubmit.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							if(a=="part"){
								window.location.href="<%=path%>/inner/agentmanage/gotoAgentAddQuery.action";
							}if(a=="all"){
								window.location.href="<%=path%>/inner/agentmanage/gotoAgentUpdateQuery.action";
							}
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
			window.location="<%=path%>/inner/companymanage/gotoProjectEditList.action?compId="+frmInfo.comp_ID.value;
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

	 		return true;
		}
		function doCopy(newval,oldval){
			$("#legalManCode").textbox("setValue",newval);
		}
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/agentmanage/doAgentSave.action">
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="agentID" value="${agvo.agentID}"/>
	<input type="hidden" name="status" value="${agvo.status}"/>
	<input type="hidden" name="logo" value="${logo}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">企业名称</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="name" id="name"  data-options="required:true,validType:'length[0,30]'"  value="${agvo.name}" style="width: 500px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">企业全国唯一编码</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="agentCode" id="agentCode"  data-options="required:true,validType:'length[0,30]',onChange:doCopy"  value="${agvo.agentCode}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">法人代码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="legalManCode" id="legalManCode"  data-options="required:true,validType:'length[0,30]'"  value="${agvo.legalManCode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在省市</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="cprovProvinceCode" id="cprovProvinceCode" value="${agvo.cprovProvinceCode }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_524',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在区市</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="district" id="district" value="${agvo.district }"
					data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">办公地址(注册地址)</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="regadr" id="regadr"  data-options="required:true,validType:'length[0,50]'"  value="${agvo.regadr}" style="width: 300px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="post" id="post" value="${agvo.post}"
					data-options="required:true,validType:'length[0,30]'" style="width: 100px" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="tel" id="tel"  data-options="required:true,validType:'length[0,50]'"  value="${agvo.tel}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">经营地址</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="bizadr" id="bizadr" value="${agvo.bizadr }"
					data-options="required:true,validType:'length[0,50]'" style="width: 300px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">传真</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="fax" id="fax" data-options="required:false,validType:'length[0,20]'"  value="${agvo.fax}"  style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电子信箱</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="email" id="email"  data-options="required:false,validType:'length[0,20]'"  value="${agvo.email}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">法人营业执照注册号</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bizRegisterNum" id="bizRegisterNum"  data-options="required:true,validType:'length[0,20]'"  value="${agvo.bizRegisterNum}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">登记注册类型</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="regTypeNum" id="regTypeNum" value="${agvo.regTypeNum}"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_302',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">营业执照到期日</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="bizEndDate" id="bizEndDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">工商注册日</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="bizRegDate" id="bizRegDate"  data-options="required:true,validType:'length[0,10]',editable:false"  value="${agvo.bizRegDate2}"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">法人代表</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="delegate" id="delegate"  data-options="required:true,validType:'length[0,50]'"  value="${agvo.delegate}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">法人代表联系电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgCall" id="dlgCall"  data-options="required:true,validType:'length[0,20]'"  value="${agvo.dlgCall}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">身份证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="dlgCardname" id="dlgCardname"  data-options="required:true,validType:'length[0,20]'"  value="${agvo.dlgCardname}" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">身份证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgCardcode" id="dlgCardcode"  data-options="required:true,validType:'length[0,30]'" value="${agvo.dlgCardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:true,validType:'length[0,50]'" value="${agvo.contactadr}" style="width: 300px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgPostcode" id="dlgPostcode"  data-options="required:true,validType:'length[0,30]'" value="${agvo.dlgPostcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy" id="proxy"  data-options="required:false,validType:'length[0,15]'"  value="${agvo.proxy}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人电话</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxyCall" id="proxyCall" value="${agvo.proxyCall }" data-options="required:false,validType:'length[0,20]'" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人身份证件名称</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyCardname" id="proxyCardname"  data-options="required:false,validType:'length[0,20]'"  value="${agvo.proxyCardname}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人身份证件号码</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxyCardcode" id="proxyCardcode" value="${agvo.proxyCardcode }" data-options="required:false,validType:'length[0,30]'" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人联系地址</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyContact" id="proxyContact"  data-options="required:false,validType:'length[0,50]'"  value="${agvo.proxyContact}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人邮编</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxyPostcode" id="proxyPostcode"  data-options="required:false,validType:'length[0,30]'"  value="${agvo.proxyPostcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">经营范围：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="6" name="workScope" id="workScope" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${agvo.workScope}</textarea>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">初审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${agvo.firstMark}</textarea>
			</td> 
		</tr>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
		<c:if test="${cmd=='edit'}">
		<c:if test="${agvo.status!=5}">
		<a href="javascript:doFirstSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交审核</a>&nbsp;&nbsp;
		</c:if>
		</c:if>
		<a href="javascript:reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">全部重写</a>&nbsp;&nbsp;
		<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	</form>
</body>
</html>
