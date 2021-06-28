 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.AgentVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 	AgentVO agvo=(AgentVO)request.getAttribute("agvo");
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
	
		function getValue(){  
		    var radio = document.getElementsByName("status");  
		    for (i=0; i<radio.length; i++) {  
		        if (radio[i].checked) {  
		            if(radio[i].value==1){
		            	$("textarea[name='finalMark']").val("同意");
		            }if(radio[i].value==2){
		            	$("textarea[name='finalMark']").val("不同意");
		            }
		        }  
		    }  
		}  

		function reset(){
	 		$("#frmInfo").form('clear');
		}
	 	
	 	function doSave(){
	 		var a=$("input[name='status']:checked").val();
	 		var b=$("textarea[name='finalMark']").val();
	 		var c=$("input[name='agentID']").val();
	 		b= encodeURI(b);	//将中文参数进行二次编码方可传参
	 		if(a=="1" || a=="2"){
	 			window.location="<%=path%>/inner/agentmanage/submitCheck.action?status="+a+"&finalMark="+b+"&agentID="+c;
	 		}else{
	 			alert("请输入复审意见后进行提交！");
	 			return;
	 		}
	 	}
	 	
	 	function doFirstSubmit(){
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/agentmanage/doAgentFirstSubmit.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							window.location.href="<%=path%>/inner/agentmanage/gotoAgentAddQuery.action";
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
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/agentmanage/submitCheck.action">
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="agentID" value="${agvo.agentID}"/>
	<input type="hidden" name="status" value="${agvo.status}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">企业全国唯一编码</font></td>
			<td class="input_right" colspan="3">
				${agvo.agentCode}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">企业名称</font></td>
			<td class="input_right" width="30%">
				${agvo.name}
			</td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">法人代码</font></td>
			<td class="input_right">
				${agvo.legalManCode }
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">所在省市</font></td>
			<td class="input_right">
				<%=agvo.getAttribute("provincecode_dict_name") %>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">所在区市</font></td>
			<td class="input_right">
				<%=agvo.getAttribute("district_dict_name") %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">办公地址(注册地址)</font></td>
			<td class="input_right" >
				${agvo.regadr}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				${agvo.post}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系电话</font></td>
			<td class="input_right" >
				${agvo.tel }
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">经营地址</font></td>
			<td class="input_right">
				${agvo.bizadr}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">传真</font></td>
			<td class="input_right" >
				${agvo.fax }
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电子信箱</font></td>
			<td class="input_right">
				${agvo.email}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">法人营业执照注册号</font></td>
			<td class="input_right" >
				${agvo.bizRegisterNum }
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">登记注册类型</font></td>
			<td class="input_right">
				<%=agvo.getAttribute("regtype_num_dict_name") %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">营业执照到期日</font></td>
			<td class="input_right" >
				${agvo.bizEndDate2 }
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">工商注册日</font></td>
			<td class="input_right">
				${agvo.bizRegDate2}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">法人代表</font></td>
			<td class="input_right" >
				${agvo.delegate}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">法人代表联系电话</font></td>
			<td class="input_right">
				${agvo.dlgCall}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称</font></td>
			<td class="input_right" >
				${agvo.dlgCardname}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件号码</font></td>
			<td class="input_right">
				${agvo.dlgCardcode}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联系地址</font></td>
			<td class="input_right" >
				${agvo.contactadr}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮政编码</font></td>
			<td class="input_right">
				${agvo.dlgPostcode}
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人</font></td>
			<td class="input_right" >
				${agvo.proxy}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人电话</font></td>
			<td class="input_right">
				${agvo.proxyCall }
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人身份证件名称</font></td>
			<td class="input_right" >
				${agvo.proxyCardname}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人身份证件号码</font></td>
			<td class="input_right">
				${agvo.proxyCardcode }
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人联系地址</font></td>
			<td class="input_right" >
				${agvo.proxyContact}
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代理人邮编</font></td>
			<td class="input_right">
				${agvo.proxyPostcode }
			</td>
		</tr>
		<tr>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">经营范围：</font></td>
        	<td class="input_right" colspan="3">
				<input class="easyui-textbox" data-options="multiline:true,editable:false" value="${agvo.workScope}" style="width:580px;height:70px">
			</td> 
		</tr>
		<tr>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">初审意见：</font></td>
        	<td class="input_right" colspan="3">
				<input class="easyui-textbox" data-options="multiline:true,editable:false" value="${agvo.firstMark }" style="width:580px;height:70px">
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">初审人</font></td>
			<td class="input_right">
				${agvo.firstCensor }
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">初审日期</font></td>
			<td class="input_right">
				${agvo.firstDate}
			</td>
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">复审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="finalMark" id="finalMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${agvo.finalMark}</textarea>
				<label><input name="status" type="radio" value="1" onclick="getValue()"/>同意 </label> 
				<label><input name="status" type="radio" value="2" onclick="getValue()"/>不同意 </label> 
			</td> 
		</tr>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">确定</a>&nbsp;&nbsp;
		<!--<a href="javascript:reset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">全部重写</a>-->&nbsp;&nbsp;
		<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	</form>
</body>
</html>
