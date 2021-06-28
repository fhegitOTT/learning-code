
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同主模板查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript">
		$(function(){
			//加载项目列表
			$('#projectID').combobox({    
		    	url:'<%=basePath%>/outer/contracttemplatemanage/getUserProjectJson.action',    
		    	valueField:'code',    
		    	textField:'name',
		    	editable:false,
		    	onChange:function(newValue,oldValue){
		    		$('#startID').combobox('setValue','');
					$('#startID').combobox('reload','<%=basePath%>/outer/contracttemplatemanage/getUserPresellJson.action?projectID='+newValue);
		    	}
			});
			//加载选择预售许可证/权属证明下拉列表样式
			$('#startID').combobox({    
		  		url:'',    
		   		valueField:'code',    
		    	textField:'name',
		    	editable:false,
			}); 
		});
		//查询列表
		function doQuery(){
			frmInfo1.submit();
		}
		//新增合同主模板
		function doAdd(){
			var projectID = $('#projectID').combobox('getValue');
			var startID = $('#startID').combobox('getValue');
			var type = $('#type').combobox('getValue');
			if(projectID==""||projectID==null){
				alert("请选择项目！");
				return;
			}
			if(startID==""||startID==null){
				alert("请选择预售许可证/权属证明！");
				return;
			}
			if(type==""||type==null){
				alert("请选择项目合同类型！");
				return;
			}
			var url = "<%=basePath%>/outer/contracttemplatemanage/addTemplate.action?opera=add&type="+type+"&projectID="+projectID+"&startID="+startID;
			openDialog("主模板新增",url,600,500);
		}
		//弹出窗口并加载标签页
		function openDialog(tempTitle,url,tempWidth,tempHeight){
			$("#openDL").dialog({
				title : tempTitle,
				width : tempWidth,
				height: tempHeight,
				modal:true,
				maximizable:true,
				content:'<iframe frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
				onBeforeClose: function () {
        		if($('#isAdd').val()!="yes"){
        			if(confirm('模板并未保存，是否关闭？')){
                		$('#openDL').tabs('close', tempTitle);
                		doQuery();
						return true;
                    }else{
                    	return false;
                    }
        		}else{
        			$('#openDL').tabs('close', tempTitle);
            		$('#isAdd').val('');
            		doQuery();
            		return true;
        		}
            } 
			});
			//$("#openDL").dialog('open');
			$("#openDL").dialog('center');
			//addTab(tempTitle, url); 
		}
		//关闭弹窗
	 	function doBack(){
	 		$("#openDL").dialog('close');
	 	}
		//新增标签页
	 	function addTab(title, url){
	    	if ($('#openDL').tabs('exists', title)){
	    		$('#openDL').tabs('select', title);
	    	} else {
	    		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:550px;"></iframe>';
	    		$('#openDL').tabs('add',{
	    			title:title,
	    			content:content,
	    			closable:false
	    		});
	    	}
	    }
		//更新标签页
	 	function doUpdate(title){
	    	if ($('#openDL').tabs('exists', title)) {
	            $('#openDL').tabs('select', title);
	            var selTab = $('#openDL').tabs('getSelected');
	            var url = $(selTab.panel('options').content).attr('src');
	            $('#openDL').tabs('update', {
	                tab: selTab,
	                options: {
	                    content:'<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:550px;"></iframe>',
	                }
	            })
	        }
	    }
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'合同主模板查询',iconCls:'icon-ok'" style="background-color: #d9e4f2;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',split:true,border:false" style="height: 90px; padding-top: 5px;" id="div1">
						<form name="frmInfo1" id="form1" target="listfrm" method="post" action="<%=basePath%>/outer/contracttemplatemanage/queryTemplateList.action">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="1">
								<tr>
									<td>
										<table width="100%" border="0" align="center">
											<tr>
												<td>
													<div align="right">
														选择项目：
													</div>
												</td>
												<td>
													<div align="left">
														<input name="projectID" id="projectID" style="width: 180px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														选择预售许可证/权属证明：
													</div>
												</td>
												<td>
													<div align="left">
														<input name="startID" id="startID" style="width: 180px;"/>
													</div>
												</td>
												<td>
													<div align="right">
														<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
													</div>
												</td>
												<td>
													<div align="right">
														<select class="easyui-combobox" id="type" data-options="editable:false,panelHeight:'auto'">
															<option value="1">预售合同模板</option>	
															<option value="2">出售合同模板</option>	
														</select>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="javascript:doAdd()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px">新	建</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<input type="hidden" id="isAdd" value=""/> 
						</form>
					</div>
					<div data-options="region:'center',border:false">
						<iframe frameborder="0" height="97%" width="100%" id="listfrm" name="listfrm" src="<%=basePath%>/outer/contracttemplatemanage/queryTemplateList.action"></iframe>
					</div>
					<div id="openDL" class="easyui-tabs" data-options="fit:true"></div>
				</div>
			</div>
		</div>
	</body>
</html>