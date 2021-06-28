 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>代理商录入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		
		
	<script type="text/javascript">
		
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
		
	 	function doClose(){
	 		$("#openDL").dialog("close");
	 	}
	 	
		function doSearchCompany(compID,signerID){
	 		var url = "<%=basePath%>/inner/contractquery/gotoCompanyInfo?compID="+compID+"&signerID="+signerID;
	 		openDialog("签约企业信息",url,500,400);
	 	}
	 	
	 	function doBack(signerID){
	 		var url = "<%=basePath%>/inner/contractquery/gotoSignerInfo.action?signerID="+signerID;
	 		openDialog("签约人信息",url,400,400);
	 	}
	 	
		function doQuery(){
			var val  = $('input[name="condition"]:checked').val();
			if(val==0){
				var a=$("input[name='contractID']").val();
				if(a==null || a==""){
					alert("请输入合同编号！");
					return;
				}
	 		}
			document.frmInfo1.action = "<%=basePath%>/inner/contractquery/searchContract.action?cmd="+val;  
			document.frmInfo1.submit();
		}
		
		
		function doSearch(){
			var val  = $('input[name="condition"]:checked').val();
			if(val==0){
				$("#a").css("display","");
				$("#b").css("display","none");
				$("#c").css("display","none");
			}if(val==1){
				$("#b").css("display","");
				$("#a").css("display","none");
				$("#c").css("display","none");
			}if(val==2){
				$("#c").css("display","");
				$("#a").css("display","none");
				$("#b").css("display","none");
			}
			
		}

		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'合同查询',iconCls:'icon-ok'" style="background-color:#d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height:145px;padding-top:5px;" id="div1"> 
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="">
						<div align="center">
							<input name="condition" type="radio" onclick="doSearch()" value="0" checked="checked"/><font size="4">按合同编号查询:</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="condition" type="radio" onclick="doSearch()" value="1" /><font size="4">按项目信息查询:</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="condition" type="radio" onclick="doSearch()" value="2" /><font size="4">按甲方信息查询:</font>
						</div>
						<div id="a" align="center" style="display:;padding-top: 5px;">
							<div style="padding-top: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同编号：<input class="easyui-numberbox" type="text" name="contractID" id="contractID" style="width:150px" data-options="required:true"/>
							&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a></div>
						</div>
						
						<div id="b" style="display:none;padding-top: 5px;">
							
							<div style="padding-top: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开盘编号：<input class="easyui-textbox" type="text" name="startID" id="startID" style="width:150px" />&nbsp;&nbsp;
							&nbsp;项目名称：<input class="easyui-textbox" type="text" name="projectName" id="projectName" style="width:150px"/>&nbsp;&nbsp;
							&nbsp;初签时间：<input class="easyui-datebox" type="text" name="signDate1" id="signDate1"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;
							&nbsp;到&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="signDate2" id="signDate2"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/><br/></div>
							
							<div style="padding-top: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同类别：<input class="easyui-combobox" name="type1" id="type1" value=""
											data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_516',valueField:'code',textField:'name',
											multiple:false,editable:false,formatter: formatNullForCombobox"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;确认时间：<input class="easyui-datebox" type="text" name="confirmDate1" id="confirmDate1"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;
							&nbsp;到&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="confirmDate2" id="confirmDate2"  data-options="validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;合同状态：<input class="easyui-combobox" name="status1" id="status1" value="-999"
									data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_500&hasFirst=1',valueField:'code',textField:'name',
									multiple:false,editable:false,required:true,formatter: formatNullForCombobox"  style="width:150px"/><br/></div>
							
							<div style="padding-top: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区/市：<input class="easyui-combobox" name="district" id="district" value="-999"
											data-options="url:'<%=basePath%>/system/getUserDistinctJson.action?hasFirst=1',valueField:'code',textField:'name',
											multiple:false,editable:false,required:true,formatter: formatNullForCombobox"  style="width:150px"/>&nbsp;&nbsp;
							<input class="easyui-textbox" type="text" name="road" id="road"  style="width:150px"/>&nbsp;路&nbsp;
							<input class="easyui-textbox" type="text" name="laneName" id="laneName"  style="width:150px"/>&nbsp;号&nbsp;
							<input class="easyui-textbox" type="text" name="subLane" id="subLane"  style="width:150px"/>&nbsp;栋&nbsp;
							<input class="easyui-textbox" type="text" name="buildingNumber" id="buildingNumber" style="width:150px" />&nbsp;单元&nbsp;
							&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a></div>
						</div>
						
						<div id="c" style="display:none;padding-top: 5px;">
							<div style="padding-top: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲方名称：<input class="easyui-textbox" type="text" name="sellerName" id="sellerName"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;企业代码：<input class="easyui-textbox" type="text" name="comp_Code" id="comp_Code"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;甲方住所：<input class="easyui-textbox" type="text" name="sellerAddress" id="sellerAddress"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;资质证书号码：<input class="easyui-textbox" type="text" name="sellerCertcode" id="sellerCertcode"  style="width:150px"/><br/></div>
							
 							<div style="padding-top: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：<input class="easyui-textbox" type="text" name="sellerDlgCall" id="sellerDlgCalld"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;法定代表人：<input class="easyui-textbox" type="text" name="sellerDelegate" id="sellerDelegate"  style="width:150px"/>&nbsp;&nbsp;
							&nbsp;委托代理人：<input class="easyui-textbox" type="text" name="sellerProxy" id="sellerProxy"  style="width:150px"/><br/></div>
							
							<div style="padding-top: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初签时间：<input class="easyui-datebox" type="text" name="signDate3" id="signDate3"  data-options="required:false,validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;
							&nbsp;到&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="signDate4" id="signDate4"  data-options="required:false,validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>
							&nbsp;合同类别：<input class="easyui-combobox" name="type2" id="type2" value="${agvo.regTypeNum}"
											data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_516',valueField:'code',textField:'name',
											multiple:false,editable:false,required:false,formatter: formatNullForCombobox"  style="width:150px"/>&nbsp;&nbsp;<br/></div>	
											
							<div style="padding-top: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认时间：<input class="easyui-datebox" type="text" name="confirmDate3" id="confirmDate3"  data-options="required:false,validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>&nbsp;
							&nbsp;到&nbsp;&nbsp;<input class="easyui-datebox" type="text" name="confirmDate4" id="confirmDate4"  data-options="required:false,validType:'length[0,10]',editable:false"  value="${agvo.bizEndDate2}"  style="width:150px"/>
							&nbsp;合同状态：<input class="easyui-combobox" name="status2" id="status2" value="-999"
											data-options="url:'<%=basePath%>/system/getDictionaryJson.action?dictName=CT_500&hasFirst=1',valueField:'code',textField:'name',
											multiple:false,editable:false,required:true,formatter: formatNullForCombobox"  style="width:150px"/>
							&nbsp;&nbsp;<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a></div>	
						</div>
						
						
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src=""></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="openDL"></div>	
</body>
</html>
