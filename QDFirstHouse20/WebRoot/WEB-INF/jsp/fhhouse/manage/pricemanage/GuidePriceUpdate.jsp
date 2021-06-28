 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>房屋基准价格幅度修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	
		
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
		
		function gotoProject(){
	 		var val = findSelectID();
	 		if(val=='[]'){
				alert("请先选择！");
				return;
			}
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		if($("#nHouseAverage").val()==''){
	 			$('#nHouseAverage').textbox('setValue','0');
	 		}
	 		if($("#nBandAverage").val()==''){
	 			$('#nBandAverage').textbox('setValue','0.0');
	 		}
	 		if($("#nBandAverageXia").val()==''){
	 			$('#nBandAverageXia').textbox('setValue','0.0');
	 		}
	 		
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=basePath%>/inner/Pricemanage/saveGuidePrcie.action?compId="+val,
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
						alert("基准价与幅度修改失败");
					}
				});
		}	
		//获取选择ID数组
		function findSelectID(){
 			var arr=[];
  			$("input[type='checkbox']:checked").each(function(){ 
      		 		arr.push(this.value);
  			})
		  	var json = JSON.stringify(arr);//数组转换成json，都在了，数组和json
		  	return json;
			//alert(json);
		}
		function gotoBack(){
	 		window.location="<%=basePath%>/inner/Pricemanage/gotoGuideHouseByIdList.action?project_id=${project_id}";
	 	}
	</script>
</head>
<body id="body-layout" class="easyui-layout" style="overflow:auto;margin-right:1px;margin-left:1px;">
<form name="frmInfo" id="frmInfo" method="post" action="<%=basePath%>/inner/Pricemanage/GuideHouseUpdateByIdList.action">
<input type="hidden" name="project_id" value="${project_id}" />
<input type="hidden" name="building_ID" value="${building_ID}" />
		<div align="center">
		<tr>
			<td>
				基准价：<input class="easyui-textbox" type="text" name="nHouseAverage" id="nHouseAverage" value="0"></input>
				上幅度：<input class="easyui-textbox" type="text" name="nBandAverage" id="nBandAverage" value="0"></input>
			        下幅度：<input class="easyui-textbox" type="text" name="nBandAverageXia" id="nBandAverageXia" value="0.2"></input>请填入0~1之间的幅度
			   
			</td>
    	</tr> 
    	</div>
    	<div align="center">
    	<tr>
		    <td align="center">
		       <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoProject()">修改</a>&nbsp;&nbsp;
		    </td>
		    <td align="center">
			   <a href="javascript:gotoBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>&nbsp;&nbsp;
		    </td>
		</tr>
		</div>
		<div align="center">
    	<tr>
		    <td align="center">
		       <font style="color:red;font-size:14px;">注：2019年2月*日后录入房屋基准价格为0时，房屋价格将不受监管；之前房屋基准价格为0的房屋依旧纳入监管。</font>
		    </td>
		</tr>
		</div>
		<div id="p" class="easyui-panel" title="房屋管理 " style="width:100%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
				<%  String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}%>
			
		</div>
		</form>
</body>
</html>
