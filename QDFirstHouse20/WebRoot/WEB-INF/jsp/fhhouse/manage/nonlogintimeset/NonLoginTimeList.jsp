<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>非登录时间一览</title>
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
		
		//新增登录设置
		function doAdd(){
			openDialog("登录设置新增","<%=path%>/inner/nonLoginTime/gotoNonLoginTimeAdd.action",800,400);
		}
		
		//修改登录设置
		function doModify(id){
			openDialog("登录设置修改","<%=path%>/inner/nonLoginTime/gotoNonLoginTimeModify.action?id="+id,800,400);
			$('#resetBtn').linkbutton('disable');
		}
		
		//删除登录设置
		function doDelete(id){
			var statu = confirm("确定要删除该条设置吗?");
			 if(!statu){
			   return false;
			  }
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/nonLoginTime/deleteNonLoginTime.action",
				data: "id="+id,
				dataType : "json",
				success : function(msg){
					if(msg[0].result=="success"){
						alert(msg[0].message);
						window.location.reload(true);
					}else{
						alert(msg[0].message);
					}
				},
				error : function(){
					alert("登录设置删除失败");
				}
			});
		}
		//重置输入的内容
		function doReset(){
			$('#frmInfo').form('clear');
			
		}
		//关闭对话框
		function doClose(){
			$("#openDL").dialog('close');
			window.location.reload(true); 
		}
	
		//保存登录设置
		function doSave(){
			if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/nonLoginTime/saveNonLoginTime.action",
					data : $("#frmInfo").serialize(),
					cache: false,
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							$("#openDL").dialog('close');
							window.location.reload(true);
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("登录设置保存失败");
					}
				});
	 	}
	
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div id="p" class="easyui-panel" title="登录设置" style="width: 100%; height: 100%; background: #fafafa;"
			data-options="iconCls:'icon-collapse',fit:true,noheader:true">
			<form name="tableForm" id="tableForm" method="post" action="">
				<%
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					    out.println(htmlview);
					}
				%>
			</form>
			<div style="width:100%;text-align:center;">
				<a href="javascript:doAdd()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加登录设置</a>
			</div>
		</div>
		<br/>
		
		<div id="openDL"></div>
	</body>
</html>
