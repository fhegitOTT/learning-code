<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约人许可证权限维护</title>
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
		function doCompanyQuery(){
			$("#comp_Id").find("option").remove();
			$("#project").find("option").remove(); 
			$("#signer").find("option").remove(); 
			$("#presell").find("option").remove(); 
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/presellmanage/queryCompany.action",
				data : "compName="+frmInfo.compName.value,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#comp_Id').empty();
						$('#comp_Id').append(data[0].companyList);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alertify.error("企业获取失败。");
				}
			});
		
		}
		
		function doSignerAndProjectQuery(){
			$("#project").find("option").remove(); 
			$("#signer").find("option").remove(); 
			$("#presell").find("option").remove();
			doSignerQuery();
			doProjectQuery();
		}
	
		function doSignerQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/presellmanage/querySignerByCompany.action?compId="+frmInfo.comp_Id.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#signer').empty();
						$('#signer').append(data[0].signerList);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){	
					alertify.error("员工获取失败。");
				}
			});
		}
		
		function doProjectQuery(){
			
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/presellmanage/queryProjectByCompany.action?compId="+frmInfo.comp_Id.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#project').empty();
						$('#project').append(data[0].projectList);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alertify.error("项目获取失败。");
				}
			});
		}
		
		function doPresellQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/presellmanage/queryPresellByProject.action?projectId="+frmInfo.project.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#presell').empty();
						$('#presell').append(data[0].presellList);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alertify.error("许可证信息获取失败。");
				}
			});
		
		}
		function doSave(){
			var signerId = $("#signer").val();
			var acceptno = $("#presell").val();
			if(signerId.length>1){
				alert("请选择一个员工进行关联许可证！");
				return;
			}
			if(signerId!=null && signerId!="" && acceptno!=""){
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/presellmanage/saveUserProjectSet.action",
					data : $("#frmInfo").serialize(),
					cache: false,
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("登录设置保存失败");
					}
				});
			}else{
				alert("请选择员工及许可证！");
			}
		}
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px" onload="doCompanyQuery();">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'签约人许可证权限维护',iconCls:'icon-ok'" style="width:80%;" align="center">
				
				<form id="frmInfo" method="post" action="">
					<div align="right" > 
						企业名称：<input class="easyui-text" type="text" name="compName" id="compName"/>
						&nbsp;&nbsp;
						<a href="javascript:doCompanyQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查　询</a>
					</div>
					<div style="background-color:#d9e4f2;width:100%">
					<table cellspacing="20" align="center">
						<tr>
							<td>
								<div align="right">
								请选择开发企业：
								</div>
							</td>
							<td>
								<select id="comp_Id" name="comp_Id" onchange='doSignerAndProjectQuery()' style="width:300px;"></select>
							</td>
							<td>
								<div align="right">
								请选择项目：
								</div>
							</td>
							<td>
								<select id="project" name="project" onchange='doPresellQuery()' style="width:300px;"></select>
							</td>
						</tr>
						<tr>
							<td>
								<div align="right">
								员工信息：
								</div>
							</td>
							<td>
								<select multiple="multiple" name="signer" id="signer" style="width:300px;height:200px"></select>
							</td>
							<td>
								<div align="right">
								许可证信息：
								</div>
							</td>
							<td>
								<select multiple="multiple" name="presell" id="presell" style="width:300px;height:200px"></select>
							</td>
						</tr>
					</table>
					<div align="center"> 
						<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保	存</a>
					</div>
					<br/>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
