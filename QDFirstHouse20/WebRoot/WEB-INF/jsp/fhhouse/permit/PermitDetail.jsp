<%@page import="com.netcom.nkestate.common.StringStamper"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>预售许可证详细信息</title>
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
	 	function doPass(){
		 	var opinion = $("#passOpinion").val();
		 	//$("textarea[name='passOpinion']").val("同意");
		 	var result = 0;
		 	var radio = document.getElementsByName("opinion");  
		    for (var i=0; i<radio.length; i++) {  
		        if (radio[i].checked) {  
		            if(radio[i].value==1){
		            	result = 1;
		            }if(radio[i].value==2){
		            	result = 2;
		            }
		        }  
		    } 

		    if(result == 0 || opinion ==''){
		    	$.messager.alert("提示","请先选择审核意见！");
				return;
			} 
		 	
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/permitmanage/doPermitAuditPass.action",
					data : {"permitID":'${permitID}',"transactionID":'${transactionID}',"opinion":opinion,"result":result},
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							//$.messager.alert("提示信息",data[0].message);
							alert(data[0].message);
							doBack();
						}else{
							//$.messager.alert("提示信息",data[0].message);
							alert("提示信息",data[0].message);
							return;
						}
					},
					error : function(){
						alert("审核异常！");
					}
			});
	 	}
	 	
		function setOpinion(){  
		    var radio = document.getElementsByName("opinion");  
		    for (var i=0; i<radio.length; i++) {  
		        if (radio[i].checked) {  
		            if(radio[i].value==1){
		            	$("textarea[name='passOpinion']").val("同意");
		            }if(radio[i].value==2){
		            	$("textarea[name='passOpinion']").val("不同意");
		            }
		        }  
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

		function doOpenImageList(imageID){
			//openDialog("收件资料","<%=path%>/inner/permitmanage/gotoImageList.action?fileID="+fileID+"&transactionID=${transactionID}",400,400);
			//var TargetUrl = "<%=path%>/inner/permitmanage/gotoImageList.action?cmd=${cmd}&imageID="+imageID+"&transactionID=${transactionID}";
			//window.open(TargetUrl,"_blank","toolbar=no,menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
			
			var TargetUrl = "<%=path%>/inner/permitmanage/ShowImage.action?imageID="+imageID+"&transactionID=${transactionID}";
			window.open(TargetUrl,"_blank","toolbar=no,menubar=no, scrollbars=no, resizable=yes,location=no, status=no");		
		}

		$(function(){
			updateArea();
		});	
		function updateArea(){
   			var num=0;
   			var flarea = getNewTotal(6,2);
   			var planFlarea = getNewTotal(8,2);
   			var cellarflarea = getNewTotal(7,2);
   			$("#span_flarea").html(flarea);
   			$("#span_cellarflarea").html(cellarflarea);
   			$("#span_planflarea").html(planFlarea);
   			$("table.default_tableCss2").find("tr").each(function (){
   				num=num+1;
   			})
   			if(num>0){
   				num=num-1;
   			}
   			$("#span_num").html(num);
   			
		}
		//计算条数
 		function getNewTotal(columns,scale){
	 		var total = 0;
	 		
	 		var param = Math.pow(10, scale);
	 		
	 		$("table.default_tableCss2").find("tr").each(function () {
	            var arrtd = $(this).children();
	            var tdContext = $.trim(arrtd.eq(columns).text());
	            if(isNaN(tdContext)){
	            	tdContext = 0;
	 			}
	            total += param * tdContext;
	         })
	        
	 		total = Math.round(total) / param;
	 		
	 		return total;
 		
 		}

 		//打印
		function doPrintPermit(){
			var TargetUrl = "<%=path%>/inner/permitmanage/gotoPermitInfoMenu.action?cmd=print&permitID=${permitID}";
			window.open(TargetUrl,"","height=600px,width=1000px,toolbar=no,menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");	
 	 	}
		//提交归档
 	 	function doSubmitPermit(){
 	 		$.messager.confirm("操作提示","确认提交归档？",function(data){
				if(data){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/doSubmitPermitArc.action",
						data : {"permitID":'${permitID}',"transactionID":'${transactionID}'},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								$.messager.alert("提示信息",data[0].message);
								//parent.window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
								doBack();
							}else{
								$.messager.alert("提示信息",data[0].message);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							$.messager.alert("提示信息","提交异常！");
						}
					});
				}
 	 		});
 	 	 }
 	 	 
		//回退审核
 	 	function doBackAudit(){
 	 		$.messager.confirm("操作提示","确认回退审核？",function(data){
				if(data){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : "<%=path%>/inner/permitmanage/doBackAuditPermit.action",
						data : {"permitID":'${permitID}',"transactionID":'${transactionID}'},
						dataType : "json",
						success : function(data){
							if(data[0].result=="success"){
								alert(data[0].message);
								//parent.window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
								doBack();
							}else{
								alert(data[0].message);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							$.messager.alert("提示信息","提交异常！");
						}
					});
				}
 	 		});
 	 	 }
	 	 
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="">
	<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
	<input type="hidden" name="transID" id="transID" value="${transactionID}"/>
	<input type="hidden" name="status" value="${permit.status}"/>
	<input type="hidden" name="permitID" id="permitID" value="${permit.permitID}"/>
	<br/>
	<div>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left input_required" bgcolor="#5BADFF" width="15%"><font style="color: white">许可证号：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="permitNo" id="permitNo"  data-options="validType:'length[0,50]',editable:false"  value="${permit.permitNo}" style="width: 150px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">所在区市：</font></td>
			<td class="input_right">
				<input class="easyui-combobox" name="districtID" id="districtID" value="${permit.districtID }"
					data-options="url:'<%=path%>/system/getDictionaryJson.action?dictName=CT_DISTRICT',valueField:'code',textField:'name',
					multiple:false,editable:false,formatter: formatNullForCombobox" readonly="readonly"/>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">交易编号：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="transactionID" id="transactionID"  data-options="validType:'length[0,15]',editable:false"  value="${permit.transactionID}" style="width: 150px"></input>
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
				<input class="easyui-datebox" type="text" name="ADate" id="ADate" data-options="validType:'length[0,10]',editable:false" readonly="readonly" value="${permit.ADate2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售地点：</font></td>
			<td class="input_right">
			<input class="easyui-textbox" type="text" name="AAddress" id="AAddress"  data-options="validType:'length[0,200]',editable:false"  value="${permit.AAddress}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售方式：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="ASale" id="ASale" data-options="validType:'length[0,2]',editable:false"  value="${permit.ASale}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售代理单位：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="AAgent" id="AAgent"  data-options="validType:'length[0,200]',editable:false"  value="${permit.AAgent}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">国内预售地址电话：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="ALinker" id="ALinker" data-options="validType:'length[0,2]',editable:false"  value="${permit.ALinker}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售地点：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="BAddress" id="BAddress"  data-options="validType:'length[0,200]',editable:false"  value="${permit.BAddress}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售方式：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="BSale" id="BSale" data-options="validType:'length[0,2]',editable:false"  value="${permit.BSale}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售代理：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="BAgent" id="BAgent"  data-options="validType:'length[0,200]',editable:false"  value="${permit.BAgent}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">境外预售地址电话：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="BLinker" id="BLinker" data-options="validType:'length[0,100]',editable:false"  value="${permit.BLinker}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工面积：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="PArea" id="PArea"  data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.PArea}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工套数：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="PSets" id="PSets"  data-options="validType:'length[0,6]',editable:false"  value="${permit.PSets}" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">预计竣工日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="PDate" id="PDate" data-options="validType:'length[0,10]',editable:false" readonly="readonly" value="${permit.PDate2}" ></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工面积：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="RArea" id="RArea"  data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.RArea}" style="width: 150px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工套数：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="RSets" id="RSets"  data-options="validType:'length[0,6]',editable:false"  value="${permit.RSets}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">实际竣工日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="RDate" id="RDate" data-options="validType:'length[0,10]',editable:false" readonly="readonly"  value="${permit.RDate2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">购取合同首号：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="CNo" id="CNo"  data-options="validType:'length[0,50]',editable:false"  value="${permit.CNo}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">购取合同份数：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="CSets" id="CSets"  data-options="precision:0,validType:'length[0,4]',editable:false"  value="${permit.CSets}" style="width: 150px"></input>
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
				<input class="easyui-textbox" type="text" name="HNo" id="HNo" data-options="validType:'length[0,2000]',editable:false"  value="${permit.HNo}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">层数：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="HFloor" id="HFloor"  data-options="precision:1,validType:'length[0,4]',editable:false"  value="${permit.HFloor}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">套数：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="HSets" id="HSets" data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.HSets}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">总建筑面积：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="LBuildArea" id="LBuildArea"  data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.LBuildArea}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">住宅面积：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="inRoomArea" id="inRoomArea" data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.inRoomArea}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">计划预售价：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="PSaleUnit" id="PSaleUnit"  data-options="precision:2,validType:'length[0,15]',editable:false"  value="${permit.PSaleUnit}" style="width: 300px"></input>
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
				<input class="easyui-textbox" type="text" name="PSale1" id="PSale1"  data-options="precision:2,validType:'length[0,16]',editable:false"  value="${permit.PSale1}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">许可套数：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="PSet1" id="PSet1" data-options="validType:'length[0,10]',editable:false"  value="${permit.PSet1}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">销售面积：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="PSale2" id="PSale2"  data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.PSale2}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">销售套数：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="PSet2" id="PSet2" data-options="precision:2,validType:'length[0,12]',editable:false"  value="${permit.PSet2}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期：</font></td>
			<td class="input_right" >
				<input class="easyui-datebox" type="text" name="passDate" id="passDate" data-options="validType:'length[0,10]',editable:false" readonly="readonly" value="${permit.passDate2}" ></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left" bgcolor="#5BADFF" ><font style="color: white">备注：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="remark" id="remark" cols="70" class="easyui-validatebox" data-options="validType:'length[0,2000]',editable:false">${permit.remark}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">土地使用权出让合同编号：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="bargainCode" id="bargainCode" data-options="validType:'length[0,200]',editable:false"  value="${permit.bargainCode}" ></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">土地用途：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="landUsage" id="landUsage"  data-options="validType:'length[0,200]',editable:false"  value="${permit.landUsage}" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left input_required" bgcolor="#5BADFF" ><font style="color: white">坐落地点：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="location" id="location" cols="70" class="easyui-validatebox" data-options="validType:'length[0,200]',editable:false">${permit.location}</textarea>
			</td> 
		</tr>
		<tr height="25">
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">公司：</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="companyName" id="companyName"  data-options="validType:'length[0,400]',editable:false"  value="${permit.companyName}" style="width: 200px"></input>
			</td>
			<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">项目名称：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="projectName" id="projectName"  data-options="validType:'length[0,100]',editable:false"  value="${permit.projectName}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="50">
			<td class="input_left" bgcolor="#5BADFF" ><font style="color: white">数据迁移备注：</font></td>
        	<td class="input_right"  colspan="3">
				<textarea rows="2" name="movingPackage" id="movingPackage" cols="70" class="easyui-validatebox" data-options="validType:'length[0,3000]',editable:false">${permit.movingPackage}</textarea>
			</td> 
		</tr>
		
	</table>
	</div>
	<br/>
	<div id="photo" class="easyui-panel" title="<c:if test="${logo!='edit'}">收件材料列表</c:if>">
				<%  
					String fileHtmlView = String.valueOf(request.getAttribute("fileHtmlView"));
					if(fileHtmlView!=null && !"".equals(fileHtmlView) && !fileHtmlView.equals("null")){
						   //System.out.println(fileHtmlView);
						   //fileHtmlView = fileHtmlView.replace("default_tableCss","default_tableCss1");
						   out.println(fileHtmlView);
					}
				%>
	</div>
	<br/>
	<div id="house" class="easyui-panel" title="<c:if test="${logo!='edit'}">已关联的房屋列表</c:if>" >
				<%  
					String houseHtmlView = String.valueOf(request.getAttribute("houseHtmlView"));
					if(houseHtmlView!=null && !"".equals(houseHtmlView) && !houseHtmlView.equals("null")){
						houseHtmlView = houseHtmlView.replace("default_tableCss","default_tableCss2");
						//System.out.println(houseHtmlView);
						out.println(houseHtmlView);
					}
				%>
	</div>
	<div id="countDiv">
			     	<table id="totaltable">
			     	<tr><td align="right">合计>房屋总建筑面积（单位：平方米）：</td><td> <span id="span_flarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋总地下面积：</td><td> <span id="span_cellarflarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋总预测面积：</td><td><span id="span_planflarea"></span>&nbsp;&nbsp;</td>
			     		<td align="right">房屋套数：</td><td><span id="span_num"></span></td>
			     	</tr>
			     	</table>
	</div>
	<br/>
	<c:if test="${cmd == 'audit'}">
		<div id="pp" class="easyui-panel" title="审核意见">
			<label><input name="opinion" type="radio" value="1" onclick="setOpinion()"/>同意 </label> 
			<label><input name="opinion" type="radio" value="2" onclick="setOpinion()"/>不同意 </label> 
			<br/>
			<textarea rows="4" name="passOpinion" id="passOpinion" cols="90" class="easyui-validatebox" data-options="validType:'length[0,100]'">${apply.passOpinion}</textarea>
		</div>
	</c:if>
		<br/>
		<div align="center">
			<c:if test="${cmd == 'audit'}">
				<a href="javascript:doPass()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px">审核</a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${cmd == 'print'}">
				<a href="javascript:doPrintPermit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px">打印</a>&nbsp;&nbsp;
				<a href="javascript:doSubmitPermit()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:100px">提交归档</a>&nbsp;&nbsp;
				<a href="javascript:doBackAudit()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:100px">回退审核</a>&nbsp;&nbsp;
			</c:if>
			    <a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:100px">返回</a>&nbsp;&nbsp;
		</div>
	</form>
</body>
</html>
