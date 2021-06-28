 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 	String basePath = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>平台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}			
		</style>
		

	<script type="text/javascript">
		$(document).ready(function(){
			$("input[name=notpasscount]").each(function(i,e){
				if($(this).val() != "0"){
					$(this).closest("tr").css("background","#ffeedd");
				}else{
					var html = $(this).closest("tr").find("td:eq(7)").find("a").html();
					$(this).closest("tr").find("td:eq(7)").html(html);
				}
			});
		});
		
		//编辑公告
		function showBulletin(bID){
	 		openDialog("公告编辑","<%=basePath%>/inner/bulletin/showBulletin.action?bID="+bID,650,400);
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
		
		//新增公告
		function add(bID){
			openDialog("公告创建","<%=basePath%>/inner/bulletin/add.action?bID="+bID,650,400);
		}
		
		//删除公告
		function delBulletin(bID){
			//parent.location = "<%=basePath%>/inner/bulletin/delBulletin.action?bID="+bID;
			var statu = confirm("确定要删除该条公告吗?");
			 if(!statu){
			   return false;
			  }
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=basePath%>/inner/bulletin/delBulletin.action?bID="+bID,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						//$("#openDL").dialog('close');
						window.location.reload(true);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("公告删除失败");
				}
			});
		}
		
		//取消操作
		function cancel(){
			$("#openDL").dialog('close'); 
		}
	
		//保存修改的公告
		function doNoteSave(){
			if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		//var a=$("input[name='status']").val();
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/inner/bulletin/saveOrUpdate.action",
					data : $("#frmInfo").serialize(),	//序列化表格内容转化为字符串
					cache: false,
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							$("#openDL").dialog('close');
							window.location.reload();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("公告修改失败");
					}
				});
	 	}
		
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div id="p" class="easyui-panel" title="系统列表"
					    style="width:100%;height:100%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:true">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="add()">新增公告</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
		</table>
					<form name="tableForm" id="tableForm" method="post"  action="">
						<input type="hidden" name="p_isFromQuery" id="p_isFromQuery" value="1"/>
					<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
						}
					%>
					</form>
		</div>
		<div id="openDL">
    	
    </div>
</body>
</html>
