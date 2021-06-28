 <%@ page contentType = "text/html; charset=UTF-8" %>
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
	
		
		function showNoteEdit(noteID){
	 		parent.location = "<%=basePath%>/system/queryNote.action?noteID="+noteID+"&opFlag=edit";
		}
		
		function showNoteDetail(){						
			var noteID = $("input[name='selNote']:checked").val();
			if(noteID==null || noteID==""){
				alert("请先选择要编辑的数据");
				return;
			}
			parent.location = "<%=basePath%>/system/queryNote.action?noteID="+noteID+"&opFlag=edit";
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div id="p" class="easyui-panel" title="系统列表"
					    style="width:100%;height:100%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:true">

					<form name="tableForm" id="tableForm" method="post"  action="<%=basePath %>/system/queryNotes.action">
						<input type="hidden" name="p_isFromQuery" id="p_isFromQuery" value="1"/>
					<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
						}
					%>
					</form>
		</div>
</body>
</html>
