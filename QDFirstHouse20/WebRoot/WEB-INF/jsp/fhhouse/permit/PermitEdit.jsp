<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>预售许可证</title>
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
			window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
	 	}
	 	function disableButton(){
	 		document.getElementById("saveButton").disabled=true; 
	 	  }
	 	  
	 	  function enableButton(){
	 		 document.getElementById("saveButton").disabled=false; 
	 	  }
	 	function doSave(){
	 		disableButton();
	 		if(!$("#frmInfo").form("validate")){
	 			enableButton();
	 			return;
	 		}
	 		var distID = $("#districtID").combobox("getValue"); 	
	 		var permitType = $("#permitType").combobox("getValue"); 	
	 		if(distID == 0){
	 			$.messager.alert("提示","不能选择市中心,请重新选择区市！");
	 			$("#districtID").focus();
	 			enableButton();
				return;	
		 	}
	 		if(permitType == 0){
	 			$.messager.alert("提示","请选择许可证类型！");
	 			$("#permitType").focus();
	 			enableButton();
				return;	
		 	}
		 	//$('#saveButton').attr('disabled',"true");//添加disabled属性
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/permitmanage/doPermitSubmitCheck.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							//alert(data[0].message);
							enableButton();
							frmInfo.submit();
							
							//$('#saveButton').removeAttr("disabled");// 移除disabled属性
						}else{
							alert(data[0].message);
							enableButton();
						}
					},
					error : function(){
						alert("保存异常！");
						enableButton();
					}
			});
			
			
	 	}
	 	
	 	function doFirstSubmit(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		$.messager.confirm("操作提示","您确定要提交到审核？",function(data){
				if(data){
					var pID = $("#permitID").val();
			 		var tID = $("#transID").val();
			 		$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : "<%=path%>/inner/permitmanage/doPermitSubmit.action",
							data : {"permitID":pID,"transactionID":tID},
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
								alert("提交异常！");
							}
					});
				}
				});
	 	}
	 	
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/permitmanage/doPermitSave.action">
	<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
	<input type="hidden" name="transID" id="transID" value="${permit.transactionID}"/>
	<input type="hidden" name="status" value="${permit.status}"/>
	<input type="hidden" name="permitID" id="permitID" value="${permit.permitID}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">许可证号：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="permitNo" id="permitNo"  data-options="validType:'length[0,50]',required:true"  value="${permit.permitNo}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在区市：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="districtID" id="districtID" value="${permit.districtID }"
					data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox,required:true"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">交易编号：</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="transactionID" id="transactionID"  data-options="validType:'length[0,15]',editable:false"  value="${permit.transactionID}" style="width: 150px;"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">许可证类型：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="permitType" id="permitType" value="${permit.permitType }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_538',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">项目开始日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="ADate" id="ADate" data-options="validType:'length[0,10]',editable:false"  value="${permit.ADate2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售地点：</font></td>
			<td class="input_right">
			<input class="easyui-textbox" type="text" name="AAddress" id="AAddress"  data-options="validType:'length[0,200]'"  value="${permit.AAddress}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售方式：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="ASale" id="ASale" data-options="validType:'length[0,2]'"  value="${permit.ASale}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售代理单位：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="AAgent" id="AAgent"  data-options="validType:'length[0,200]'"  value="${permit.AAgent}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售地址电话：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="ALinker" id="ALinker" data-options="validType:'length[0,100]'"  value="${permit.ALinker}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售地点：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="BAddress" id="BAddress"  data-options="validType:'length[0,200]'"  value="${permit.BAddress}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售方式：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="BSale" id="BSale" data-options="validType:'length[0,2]'"  value="${permit.BSale}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售代理：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="BAgent" id="BAgent"  data-options="validType:'length[0,200]'"  value="${permit.BAgent}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售地址电话：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="BLinker" id="BLinker" data-options="validType:'length[0,100]'"  value="${permit.BLinker}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工面积：</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="PArea" id="PArea"  data-options="precision:2,validType:'length[0,12]'"  value="${permit.PArea}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工套数：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSets" id="PSets"  data-options="validType:'length[0,6]'"  value="${permit.PSets}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="PDate" id="PDate" data-options="validType:'length[0,10]',editable:false"  value="${permit.PDate2}" ></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工面积：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="RArea" id="RArea"  data-options="precision:2,validType:'length[0,12]'"  value="${permit.RArea}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工套数：</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="RSets" id="RSets"  data-options="validType:'length[0,6]'"  value="${permit.RSets}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="RDate" id="RDate" data-options="validType:'length[0,10]',editable:false"  value="${permit.RDate2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">购取合同首号：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="CNo" id="CNo"  data-options="validType:'length[0,50]'"  value="${permit.CNo}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">购取合同份数：</font></td>
			<td class="input_right">
				<input class="easyui-numberbox" type="text" name="CSets" id="CSets"  data-options="precision:0,validType:'length[0,4]'"  value="${permit.CSets}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋类型：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="DType" id="DType" value="${permit.DType }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_HTYPE',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">建筑类型：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="EType" id="EType" value="${permit.EType }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_ETYPE',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋结构：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="FType" id="FType" value="${permit.FType }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_543',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋幢号：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="HNo" id="HNo" data-options="validType:'length[0,2000]'"  value="${permit.HNo}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">层数：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="HFloor" id="HFloor"  data-options="precision:1,validType:'length[0,4]'"  value="${permit.HFloor}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">套数：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="HSets" id="HSets" data-options="precision:2,validType:'length[0,12]'"  value="${permit.HSets}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">总建筑面积：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="LBuildArea" id="LBuildArea"  data-options="precision:2,validType:'length[0,12]'"  value="${permit.LBuildArea}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">住宅面积：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="inRoomArea" id="inRoomArea" data-options="precision:2,validType:'length[0,12]'"  value="${permit.inRoomArea}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">计划预售价：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSaleUnit" id="PSaleUnit"  data-options="precision:2,validType:'length[0,15]'"  value="${permit.PSaleUnit}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">币种：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="currency" id="currency" value="${permit.currency}"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_CURRENCY_TYPE',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">许可面积：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSale1" id="PSale1"  data-options="precision:2,validType:'length[0,16]'"  value="${permit.PSale1}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">许可套数：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSet1" id="PSet1" data-options="precision:2,validType:'length[0,12]'"  value="${permit.PSet1}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">销售面积：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSale2" id="PSale2"  data-options="precision:2,validType:'length[0,12]'"  value="${permit.PSale2}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">销售套数：</font></td>
			<td class="input_right" >
				<input class="easyui-numberbox" type="text" name="PSet2" id="PSet2" data-options="precision:2,validType:'length[0,12]'"  value="${permit.PSet2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="passDate" id="passDate" data-options="validType:'length[0,10]'"  value="${permit.passDate2}" ></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left" bgcolor="#5BADFF" ><font style="color: white">备注：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="remark" id="remark" cols="70" class="easyui-validatebox" data-options="validType:'length[0,2000]'">${permit.remark}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">土地使用权出让合同编号：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bargainCode" id="bargainCode" data-options="precision:0,validType:'length[0,200]'"  value="${permit.bargainCode}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">土地用途：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="landUsage" id="landUsage"  data-options="validType:'length[0,200]'"  value="${permit.landUsage}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left" bgcolor="#5BADFF" ><font style="color: white">坐落地点：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="location" id="location" cols="70" class="easyui-validatebox" data-options="validType:'length[0,200]'">${permit.location}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">公司：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="companyName" id="companyName"  data-options="validType:'length[0,400]',required:true"  value="${permit.companyName}" style="width: 200px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">项目名称：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="projectName" id="projectName"  data-options="validType:'length[0,100]',required:true"  value="${permit.projectName}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left" bgcolor="#5BADFF" ><font style="color: white">数据迁移备注：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="movingPackage" id="movingPackage" cols="70" class="easyui-validatebox" data-options="validType:'length[0,3000]'">${permit.movingPackage}</textarea>
			</td> 
		</tr>
		
	</table>
	<c:if test="${cmd != 'add'}">
		<c:if test="${passOpinion != null}">
		<div id="pp" class="easyui-panel" title="审核意见">
			<textarea rows="4"  cols="90" class="easyui-validatebox" data-options="validType:'length[0,500]'">${passOpinion}</textarea>
		</div>
		</c:if>
	</c:if>
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" id="saveButton">保存</a>&nbsp;&nbsp;
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
		<c:if test="${cmd=='edit'}">
			<a href="javascript:doFirstSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交审核</a>&nbsp;&nbsp;
		</c:if>
	</div>
	</form>
</body>
</html>
