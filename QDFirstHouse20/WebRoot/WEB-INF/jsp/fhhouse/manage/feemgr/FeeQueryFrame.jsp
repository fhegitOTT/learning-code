<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业费用查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>

		<style type=text/css>
			* {color: #555;}
			body {font-size: 12px;margin: 0;font: Arial, Helvetica, "宋体", sans-serif;}
			.toplogo {width: auto;line-height: 40px;margin-left: 10px;display: inline;float: left;overflow-y: hidden;}
			/*最底部*/
			.bottom {overflow-y: hidden}
			.bottom_ban {width: auto;line-height: 40px;margin-left: 18px;display: inline;float: left;overflow-y: hidden;}
			.bottom_shi {width: auto;line-height: 40px;margin-right: 20px;display: inline;float: right;overflow-y: hidden;}
		</style>

	<script type="text/javascript">
		
	 	function doQuery(){
	 		if($("#frmInfo").form('validate')){
		 		document.frmInfo.submit();
		 	}else{
		 		alert('请输入正确的条件！');
		 	}
	 	}
	 	function doClean(){
	 		$("#frmInfo").form('clear');
	 	}
	 	function doPrint(){
	 		var compid = $("#compID",document.frames('listfrm').document).val();
	 		if(compid!=""&&compid!=null){
	 			var url = "<%=path%>/inner/feemanage/gotoFeePrint.action?comp_Id="+compid;
	 			window.open(url,"_blank"," directories=no, status=no,resizable=no, copyhistory=yes, width=800, height=500");
	 		}else{
	 			alert("企业不明确，不能打印!");
	 		}
	 	}
	 	$.extend($.fn.validatebox.defaults.rules, {
			equaldDate : {
				validator : function(value, param) {
					var start = $(param[0]).datebox('getValue'); //获取开始时间    
					return value > start; //有效范围为当前时间大于开始时间    
				},
				message : '结束日期应大于开始日期!' //匹配失败消息  
			}
		});
	 
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'费用查询',iconCls:'icon-ok'" style="background-color: #d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height: 15%; padding-top: 5px; background-color: #d9e4f2;" id="div1">
						<form name="frmInfo" id="frmInfo" target="listfrm" style="height: 100%" method="post" action="<%=path%>/inner/feemanage/gotoFeeQueryList.action">
							<table width="95%">
								<tr>
									<td>
										<div align="right">
											法人代码：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="legalManCode" id="legalManCode" type="text" value="${legalManCode }" />
									</td>
									<td>
										<div align="right">
											操作帐号：
										</div>
									</td>
									<td>
										<input class="easyui-textbox" name="operator" id="operator" value="${operator }" />
									</td>
									<td>
										<div align="right">
											查询范围：
										</div>
									</td>
									<td>
										<select id="feeRange" name="feeRange" class="easyui-combobox">
											<option value="">全部</option>
											<option value="1">消费明细</option>
											<option value="0">交退费明细</option>
										</select> 
									</td>
								</tr>
								<tr>
									<td>
										<div align="right">
											开始时间范围从：
										</div>
									</td>
									<td>
										<input class="easyui-datebox" name="startDate" id="startDate" size="10" maxlength="8" value="${startDate }" />
										到
										<input class="easyui-datebox" name="endDate" id="endDate" size="10" maxlength="8" value="${endDate }" data-options="validType:'equaldDate[\'#startDate\']'"/>
									</td>
									<td>
										<div align="right">
											流水号：
										</div>
									</td>
									<td>
										<input class="easyui-numberbox" type="text" name="exchangeID" value="${exchangeID }" />
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										<div style="float: right">
											<a href="javascript:doClean()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width: 80px">重	置</a>
											<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查 	询</a>
											<a href="javascript:doPrint()" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width: 80px">打 	印</a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="95%" width="100%" id="listfrm" name="listfrm"></iframe>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
