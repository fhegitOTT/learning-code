<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@page import="com.netcom.nkestate.common.StringStamper"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 	SmUserVO vo = (SmUserVO) request.getSession().getAttribute("LgUser");
 	long userID = vo.getUserId();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建商品房上网维护</title>
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
		var userID = "<%=userID %>";
	 	function closeWindows(){
			window.close();
	 	}

		//获取选择ID
		function addOnLine(){
			var rows = $('#dg').datagrid('getChecked');//getSelections
			if(rows.length <= 0){
				$.messager.alert("提示","请选择房屋");
				return;
			}
			disableButton();
			$.messager.confirm('提示','确认纳入网上签约？',function(r){    
    			if (r){    
       			    var entities='';
					for(var i=0;i<rows.length;i++){
						entities = entities + JSON.stringify(rows[i]);
					}
					if(entities != '' && entities.length > 0){
						$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : "<%=path%>/inner/permitmanage/doAddOnLine.action",
							data : {"entities":entities},
							dataType : "json",
							beforeSend:function(XMLHttpRequest){
				             	$.messager.progress({ 
								       title: '提示', 
								       msg: '操作正在进行中，请稍候……', 
								       text: '' 
								});
		         			},
							success : function(data){
								$.messager.progress('close');
								if(data[0].result=="success"){
									alert(data[0].message);
								}else{
									alert(data[0].message);
								}
								$('#dg').datagrid('reload');
								//$("#addOnLineBtn").linkbutton('enable');
							},
							error : function(){
								alert("保存异常！");
							}
						});	
					}		
    			} else{
    				enableButton();
    			}  
			});  
		}

		
		function addOffLine(){
			var rows = $('#dg').datagrid('getChecked');//getSelections
			if(rows.length <= 0){
				$.messager.alert("提示","请选择房屋");
				return;
			}
			disableButton();
			$.messager.confirm('提示','确认不纳入网上签约？',function(r){    
    			if (r){ 
					var entities='';
					for(var i=0;i<rows.length;i++){
						entities = entities + JSON.stringify(rows[i]);
					}
					if(entities != '' && entities.length > 0){
						$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : "<%=path%>/inner/permitmanage/doAddOffLine.action",
							data : {"entities":entities},
							dataType : "json",
							beforeSend:function(XMLHttpRequest){
				             	$.messager.progress({ 
								       title: '提示', 
								       msg: '操作正在进行中，请稍候……', 
								       text: '' 
								});
		         			},
							success : function(data){
							 	$.messager.progress('close');
								if(data[0].result=="success"){
									alert(data[0].message);
								}else{
									alert(data[0].message);
								}
								$('#dg').datagrid('reload');
								//$("#addOffLineBtn").linkbutton('enable');
							},
							error : function(){
								alert("保存异常！");
							}
						});	
					}
			} else{
				enableButton();
			} 
			}); 		
	 	}
		
		function enableButton(){
			$("#addOffLineBtn").linkbutton('enable');
			$("#addOnLineBtn").linkbutton('enable');
		}
		function disableButton(){
			$("#addOffLineBtn").linkbutton('disable');
			$("#addOnLineBtn").linkbutton('disable');
		}
	</script>
</head>
<body id="body-layout" >
	<input type="hidden" name="permitID" id="permitID" value="${permit.permitID}"/>
	<div id="houseList" class="easyui-panel" title="<c:if test="${logo!='edit'}">选择的列表</c:if>">
				<%  
					//String htmlView = String.valueOf(request.getAttribute("htmlView"));
					//if(htmlView != null && !"".equals(htmlView) && !htmlView.equals("null")){
					//	   out.println(htmlView);
					//}
				%>
				
		<table id="dg" class="easyui-datagrid" style="width:100%;height:500px"
			data-options="idField:'houseID',rownumbers:true,singleSelect:false,checkOnSelect:true,url:'<%=path%>/inner/permitmanage/getOnLineHouseList.action?houseIDs=${houseIDs}',method:'post',onLoadSuccess:function(data){enableButton();}">
				<thead>
					<tr style="width: 100%">
						<th data-options="field:'saleFlag',checkbox:true ">检查</th>
						<th data-options="field:'houseID',width:300, editor:false,align:'center'">房屋编号</th>
						<th data-options="field:'room_number',width:300,align:'center',editor:false">室号</th>
						<th data-options="field:'saleFlagStr',width:300,align:'center',editor:false">上网标志</th>
					</tr>
				</thead>
		</table>
	</div>
	<br/>
	<div align="center">
		<a href="javascript:addOnLine()" id="addOnLineBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:150px">纳入网上签约</a>&nbsp;&nbsp;
		<a href="javascript:addOffLine()" id="addOffLineBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'" style="width:150px">不纳入网上签约</a>&nbsp;&nbsp;
		<a href="javascript:closeWindows()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px">退出</a>&nbsp;&nbsp;
	</div>
</body>
</html>
