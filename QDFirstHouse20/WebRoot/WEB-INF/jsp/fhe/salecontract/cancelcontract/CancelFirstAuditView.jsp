 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 	CancelCensorVO cvo=(CancelCensorVO)request.getAttribute("cvo");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同撤销审核页面</title>
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
			window.location.href="<%=path%>/inner/contractmanage/cancelFirstAudit.action";
		}
				
		function goback(){
			window.location.href="<%=path%>/inner/contractmanage/cancelFirstAudit.action";
		}
			
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

	 	function doSave(){
	 		var a=$("input[name='status']:checked").val();
	 		var b=$("textarea[name='finalMark']").val();
	 		b= encodeURI(b);
	 		if(a==1 || a==2){
	 			var url="<%=path%>/inner/contractmanage/doFirstSubmit.action?ID=${cvo.ID}&status=${cvo.status}&option="+a+"&finalMark="+b;
		 		window.openDialog("合同复审",url,600,200);
	 		}else{
	 			alert("请输入复审意见后再提交！");
	 			window.history.back;
	 		}
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
		
		}
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/contractmanage/doSecondSubmit.action">
	<input type="hidden" name="cmd" value="${cmd}"/>
	<input type="hidden" name="ID" value="${cvo.ID}"/>
	<input type="hidden" name="status" value="${cvo.status}"/>
	<input type="hidden" name="cID" value="${cvo.contractID}"/>
	<br/>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  style="font-size:12px;" frame="border">
          <tr>
            <td height="22" width="66%"><font color="#003300" style="font-size: 14px;font-weight: bold;">合同编号：${cvo.contractID } 
              </font></td>
            <td height="22" align="right" width="34%"><font color="#003300" style="font-size: 14px;font-weight: bold;">签订日期：${cvo.confirmDate1 }</font></td>
          </tr>
    </table>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房地坐落部位</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="name" id="name"  data-options="required:false,validType:'length[0,200]',editable:false"  value="${cvo.location}" style="width: 650px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">房屋面积：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="agentCode" id="agentCode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.area}" style="width: 100px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">总价：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="legalManCode" id="legalManCode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.cost}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">申 请 人：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="regadr" id="regadr"  data-options="required:true,validType:'length[0,50]',editable:false,editable:false"  value="${cvo.proposer1}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="legalManCode" id="legalManCode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.proposer1Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="regadr" id="regadr"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${cvo.proposer1CardName}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="post" id="post" value="${cvo.proposer1CardCode}"
					data-options="required:false,validType:'length[0,30]'" style="width: 150px" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="tel" id="tel"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${cvo.proposer1Address}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="bizadr" id="bizadr" value="${cvo.proposer1PostCode }"
					data-options="required:false,validType:'length[0,50]'" style="width: 100px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代 理 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="fax" id="fax" data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proxy1}"  style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="email" id="email"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proxy1Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bizRegisterNum" id="bizRegisterNum"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proxy1CardName}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="bizRegisterNum" id="bizRegisterNum"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proxy1CardCode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:false,validType:'length[0,50]',editable:false" value="${cvo.proxy1Address}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:false,validType:'length[0,50]',editable:false" value="${cvo.proxy1PostCode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">申 请 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="delegate" id="delegate"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${cvo.proposer2}" style="width: 300px"></input>
			</td>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgCall" id="dlgCall"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proposer2Call}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="dlgCardname" id="dlgCardname"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proposer2CardName}" style="width: 150px"></input>
			</td>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgCardcode" id="dlgCardcode"  data-options="required:false,validType:'length[0,30]',editable:false" value="${cvo.proposer2CardCode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="contactadr" id="contactadr"  data-options="required:false,validType:'length[0,50]',editable:false" value="${cvo.proposer2Address}" style="width: 300px"></input>
			</td>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="dlgPostcode" id="dlgPostcode"  data-options="required:false,validType:'length[0,30]',editable:false" value="${cvo.proposer2PostCode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代 理 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy" id="proxy"  data-options="required:false,validType:'length[0,15]',editable:false"  value="${cvo.proxy2}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxyCall" id="proxyCall" value="${cvo.proxy2Call }" data-options="required:false,validType:'length[0,20]',editable:false" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyCardname" id="proxyCardname"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${cvo.proxy2CardName}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxyCardcode" id="proxyCardcode" value="${cvo.proxy2CardCode }" data-options="required:false,validType:'length[0,30]',editable:false" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyContact" id="proxyContact"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${cvo.proxy2Address}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxyPostcode" id="proxyPostcode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.proxy2PostCode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">备注：</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="3" name="workScope" id="workScope" cols="70" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]',editable:false" readonly="readonly">${cvo.cancelComment}</textarea>
			</td>
		</tr>
		<tr>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">经办意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="6" name="workScope" id="workScope" cols="70" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]',editable:false" readonly="readonly">${cvo.firstNotion}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">经办人：</font></td>
			<td class="input_right" >
			<%
				List<CancelCensorVO> list=(List)request.getAttribute("list");
				for(CancelCensorVO ccvo:list){
			 %>
				<input class="easyui-textbox" type="text" name="proxyContact" id="proxyContact"  data-options="required:false,validType:'length[0,50]',editable:false"  value="<%=ccvo.getFirstCensor()%>" style="width: 150px"></input>

			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">日期：</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="proxyPostcode" id="proxyPostcode"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${cvo.firstDate1}" style="width: 100px"></input>
			</td>
		</tr>
		<c:choose>
		<c:when test="${cvo.status==1 || cvo.status==5}">
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">请输入复核意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="finalMark" id="finalMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"></textarea>
				<label><input name="status" type="radio" value="1" onclick="getValue()"/>同意 </label> 
				<label><input name="status" type="radio" value="2" onclick="getValue()"/>不同意 </label> 
			</td> 
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">复核意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]',editable:false" readonly="readonly">${cvo.earlyNotion}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">复核人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyContact" id="proxyContact"  data-options="required:false,validType:'length[0,50]',editable:false"  value="<%=ccvo.getEarlyCensor()%>" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">日期：</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="proxyPostcode" id="proxyPostcode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.earlyDate}" style="width: 100px"></input>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
		<c:if test="${cvo.status==5 || cvo.status==6}">
		<tr>
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">审批意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" readonly="readonly">${cvo.finalNotion}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">审批人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxyContact" id="proxyContact"  data-options="required:false,validType:'length[0,50]',editable:false"  value="<%=ccvo.getFinalCensor()%>" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">日期：</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="proxyPostcode" id="proxyPostcode"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${cvo.finalDate}" style="width: 100px"></input>
			</td>
		</tr>		
		</c:if>
			<%
				}
			 %>
	</table>
	
	
	<br/>
	<div align="center">
		<c:if test="${cvo.status==1 || cvo.status==5}">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交</a>&nbsp;&nbsp;
		</c:if>
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	</form>
	<div id="openDL"></div>
	
</body>
</html>
