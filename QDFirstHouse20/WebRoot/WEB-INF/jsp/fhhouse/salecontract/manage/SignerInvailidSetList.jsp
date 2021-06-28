 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String basePath = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>外网签约人列表</title>
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
		function doValid(status){
	 		var val = findSelectID();
			if(val==''){
				alert("请先选择！");
				return;
			}
			//alert(val);
			if(confirm('确定修改该签约人有效状态吗?')){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/outer/manage/doSignerInvailidSet.action?signerId="+val+"&invailid="+status,
					data : "{}",
					dataType: "json",
					success : function(data){
						if(data[0].result=="success"){
							alert("修改成功");
							window.location=window.location;
						}else{
							alert(data[0].message);
							//alertify.error(data[0].message);
						}
					},
					error : function(){
						alertify.error("修改失败。");
					}
				});
			}
			
		}
		
		//获取选择ID
		function findSelectID(){
			var selID="";
			var tempIDS = document.getElementsByName("selSigner");
			var sel = 0;
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked){
					sel+=1;
					//alert(pays[i].value);
					selID = tempIDS[i].value;
				}
			}
			if(sel!=1){
				return "";
			}else{
				return selID;
			}
		}
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div id="p" class="easyui-panel" title="签约人列表"
					    style="width:100%;height:100%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:true">
					
					<form name="tableForm" id="tableForm" method="post"  action="">
						<input type="hidden" name="p_isFromQuery" id="p_isFromQuery" value="1"/>
					<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
						}
					%>
					</form>
					<div align="center">
						<a href="javascript:doValid(1)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">有效</a>
						<a href="javascript:doValid(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">无效</a>
					</div>
		</div>
		
</body>
</html>
