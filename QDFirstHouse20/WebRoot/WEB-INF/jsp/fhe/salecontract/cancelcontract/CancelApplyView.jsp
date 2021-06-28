 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 	BuyerInfoVO bvo=(BuyerInfoVO)request.getAttribute("bvo");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同撤销申请页面</title>
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
			

	 	function doSubmit(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		document.frmInfo.submit();
			//var url="<%=path%>/inner/contractmanage/cancelApplySubmit.action";
	 		//window.openDialog("合同撤销申请",url,600,200);
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
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/contractmanage/cancelApplySubmit.action">
	<input type="hidden" name="contractID" value="${cdvo.contractID}"/>
	<br/>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  style="font-size:12px;" frame="border">
          <tr>
            <td height="22" width="66%"><font color="#003300" style="font-size: 14px;font-weight: bold;">合同编号：${cdvo.contractID } 
              </font></td>
          </tr>
    </table>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">房地坐落部位</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="location" id="location"  data-options="required:true,validType:'length[0,200]',editable:false"  value="${cdvo.location}" style="width: 500px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">签订日期</font></td>
			<td class="input_right">
				<input class="easyui-datebox" type="text" name="confirmDate" id="confirmDate"  data-options="required:true,validType:'length[0,50]',editable:false"  value="${confirmDate}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">房屋面积：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="area" id="area"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${cdvo.area}" style="width: 100px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">总价：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="cost" id="cost"  data-options="required:true,validType:'length[0,30]',editable:false"  value="${cdvo.money}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">申 请 人：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proposer1" id="proposer1"  data-options="required:true,validType:'length[0,50]',editable:false"  value="${svo.sellerName}" style="width: 300px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proposer1Call" id="proposer1Call"  data-options="required:true,validType:'length[0,30]'"  value="" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proposer1CardName" id="proposer1CardName"  data-options="required:false,validType:'length[0,50]'"  value="" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proposer1CardCode" id="proposer1CardCode" value=""
					data-options="required:false,validType:'length[0,30]'" style="width: 150px" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proposer1Address" id="proposer1Address"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${svo.sellerAddress}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proposer1PostCode" id="proposer1PostCode" value="${svo.sellerPostcode }"
					data-options="required:false,validType:'length[0,50]',editable:false" style="width: 100px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代 理 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy1" id="proxy1" data-options="required:false,validType:'length[0,20]'"  value=""  style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy1Call" id="proxy1Call"  data-options="required:false,validType:'length[0,20]'"  value="" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy1CardName" id="proxy1CardName"  data-options="required:false,validType:'length[0,20]'"  value="" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy1CardCode" id="proxy1CardCode"  data-options="required:false,validType:'length[0,20]'"  value="" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy1Address" id="proxy1Address"  data-options="required:false,validType:'length[0,50]'" value="" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proxy1PostCode" id="proxy1PostCode"  data-options="required:false,validType:'length[0,50]'" value="" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">申 请 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proposer2" id="proposer2"  data-options="required:false,validType:'length[0,50]',editable:false"  value="${bvo.buyerName}" style="width: 300px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proposer2Call" id="proposer2Call"  data-options="required:false,validType:'length[0,20]',editable:false"  value="${bvo.buyerCall}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proposer2CardName" id="proposer2CardName"  data-options="required:false,validType:'length[0,20]',editable:false"  value="<%=bvo.getAttribute("buyer_cardname_dict_name") %>" style="width: 150px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proposer2CardCode" id="proposer2CardCode"  data-options="required:false,validType:'length[0,30]',editable:false" value="${bvo.buyerCardcode}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proposer2Address" id="proposer2Address"  data-options="required:false,validType:'length[0,50]',editable:false" value="${bvo.buyerAddress}" style="width: 300px"></input>
			</td>
			<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="proposer2PostCode" id="proposer2PostCode"  data-options="required:false,validType:'length[0,30]',editable:false" value="${bvo.buyerPostcode}" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">代 理 人：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy2" id="proxy2"  data-options="required:false,validType:'length[0,15]'"  value="" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">电话：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxy2Call" id="proxy2Call" value="" data-options="required:false,validType:'length[0,20]'" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">身份证件名称：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy2CardName" id="proxy2CardName"  data-options="required:false,validType:'length[0,20]'"  value="" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">号码：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" name="proxy2CardCode" id="proxy2CardCode" value="" data-options="required:false,validType:'length[0,30]'" style="width: 150px"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">联 系 地 址：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="proxy2Address" id="proxy2Address"  data-options="required:false,validType:'length[0,50]'"  value="" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">邮编：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name=proxy2PostCode id="proxy2PostCode"  data-options="required:false,validType:'length[0,30]'"  value="" style="width: 100px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left " bgcolor="#5BADFF"><font style="color: white">备注：</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="3" name="cancelComment" id="cancelComment" cols="70" class="easyui-validatebox" data-options="required:false,validType:'length[0,100]'"></textarea>
			</td>
		</tr>
		<tr>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">请输入经办意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="6" name="firstNotion" id="firstNotion" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"></textarea>
			</td> 
		</tr>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交审核</a>&nbsp;&nbsp;
		<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	</form>
	<div id="openDL"></div>
	
</body>
</html>
