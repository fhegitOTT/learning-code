 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.system.vo.SmObjectVO"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>楼幢维护页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/fh.css"/>
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

	 	function doBack(){
			window.location="<%=path%>/inner/projectmanage/gotoPresellEditList.action?projectId="+frmInfo.projectId.value+"&compId="+frmInfo.compId.value;
	 	}
	 	
	 	function doRemove() {
	 		var obj = frmInfo.startBuilding;
			if(obj.length < 1) {
				return;
			}
			
			var j=0;
			var length =obj.length;
			for(i = 0;i<length;i++){
				if(obj.options[i].selected == true){j++;}			 
			}
			
			if(j >1) {
				alert("楼幢移出时每次只能选择一个楼幢。");
				return;
			}
	
			if(obj.value == "") {
				alert("请选择楼幢。");
				return;
			}
			if(frmInfo.startSatuts.value!='0'){
				if(!confirm('该楼幢删除后将无法恢复，并且预售许可证需要重新审核，是否继续?')) {
					return;
				}
			}
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/projectmanage/doDelStartBuilding.action",
				data : $("#frmInfo").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#startBuilding').empty();
						$('#startBuilding').append(data[0].startBuildingList);
						
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
			
		}
		function doUpdate() {
			var obj = frmInfo.startBuilding;
			if(obj.length < 1) {
				return;
			}
			
			if(obj.value == "") {
				alert("请选择楼幢。");
				return;
			}
			if(frmInfo.startSatuts.value!='0'){
				if(!confirm('该楼幢修改后，开盘单元需要重新审核，是否继续?')) {
					return;
				}
			}
			
			frmInfo.selcmd.value=1;
			frmInfo.submit();
		    
		}

	 	function doQuery(){
	 		var roadname = $("#roadname").textbox("getValue");
	 		var lanename = $("#lanename").textbox("getValue");
	 		var sublane = $("#sublane").textbox("getValue");
	 		var streetnumber = $("#streetnumber").textbox("getValue");
	 		if((roadname==null || roadname=='')&&(lanename==null || lanename=='')
	 			&&(sublane==null || sublane=='')&&(streetnumber==null || streetnumber=='')
	 		){
	 			alert("请输入坐落！");
	 			return;
	 		}
	 		
	 		$('#queryBuilding').empty();
	 	
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/projectmanage/queryLocationList.action",
				data : $("#frmInfo").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#queryLocation').empty();
						$('#queryLocation').append(data[0].queryLocationList);
						
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
		
		function doSelectLocation(locationId){
	 		if(locationId==null || locationId==''){
	 			$('#queryBuilding').empty();
	 			return;
	 		}

			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/projectmanage/queryBuildingList.action?locationId="+locationId,
				data : {},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#queryBuilding').empty();
						$('#queryBuilding').append(data[0].queryBuildingList);
						
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
		
		
		function doStartBuildingQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/projectmanage/getStartBuildingList.action?startId="+frmInfo.startId.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#startBuilding').empty();
						$('#startBuilding').append(data[0].startBuildingList);
						
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
		
		function doAdd() {
			var obj = frmInfo.queryBuilding;
			if(obj.length < 1) {
				return;
			}
	
			if(obj.value == "") {
				alert("请选择楼幢。");
				return;
			}
	
			frmInfo.selcmd.value=2;
			frmInfo.submit();
		}
		
		//查询楼栋全部选择 
		function selectAll(){
		 
			var i;
			var len = frmInfo.queryBuilding.length;
			 
			for(i = 0;i<len;i++){
				frmInfo.queryBuilding.options[i].selected = true;
			}		 
		}
		//添加全部楼栋
		function doAddAll() {
			selectAll();
			doAdd();
		}
		
		function gotoHouseSelect(buildingId){
			//alert(buildingId);
			var url ="<%=path%>/inner/projectmanage/gotoHouseList.action?startId="+frmInfo.startId.value+"&buildingId="+buildingId;
			//window.open(url);
 			openDialog("房屋关联",url,750,420);
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
		//房屋全选
		function doSelectAll(obj) {
			var frm = document.forms["houselistfrm"];
	
			for (var i=0;i<frm.elements.length;i++)
			{
				var e=frm.elements[i];
	
				if ((e.name != 'allBox') && (e.type=='checkbox') && (e.disabled == false))
				{
					if (obj.checked == true) {
						e.checked = true;
					} else {
						e.checked = false;
					}
				}
			}
		}
		
		function doHouseSelSave(){
			$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/projectmanage/doHouseSelSave.action",
					data : $("#houselistfrm").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert(data[0].message);
							$("#openDL").dialog('close');
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("房屋关联选择出错");
					}
				});
		}
		
	</script>
</head>
<body id="body-layout" onload="doStartBuildingQuery();">
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/projectmanage/gotoStartBuildingEditList.action" target="listfrm">
	<input type="hidden" name="compId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("compId")) %>"/>
	<input type="hidden" name="projectId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectId")) %>"/>
	<input type="hidden" name="startId" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startId")) %>"/>
	<input type="hidden" name="startSatuts" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startSatuts")) %>"/>
	<input type="hidden" name="selcmd" value="0"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td align="center" bgcolor="#5BADFF" width="15%" colspan="3"><font style="color: white">楼栋维护 </font></td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3" width="15%"><font style="color: #003300">目前关联楼栋：</font></td>
			<td width="60%" bgcolor="E6F8E3" >
				<select id="startBuilding" name="startBuilding" size='6' multiple style="width:500px">
					
				</select>
			</td>
			<td bgcolor="E6F8E3" >
				<a href="javascript:doRemove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:100px">楼幢移出</a>&nbsp;&nbsp;
				<a href="javascript:doUpdate()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px">楼幢修改</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td align="center" bgcolor="#5BADFF" width="15%" colspan="4"><font style="color: white">查询楼幢 </font></td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3" width="8%"><font style="color: #003300">选择区县：</font></td>
			<td width="25%">
				<input class="easyui-combobox" name="district" id="district"
					data-options="url:'<%=path%>/system/getUserDistinctJson.action',valueField:'code',textField:'name',
					multiple:false,editable:false,required:true,formatter: formatNullForCombobox" />
			</td>
			<td class="input_left" bgcolor="E6F8E3" width="8%"><font style="color: #003300">坐落：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="roadname" id="roadname" value="" style="width: 100px"></input>&nbsp;路
				<input class="easyui-textbox" type="text" name="lanename" id="lanename" value="" style="width: 80px"></input>&nbsp;号
				<input class="easyui-textbox" type="text" name="sublane" id="sublane" value="" style="width: 80px"></input>&nbsp;栋
				<input class="easyui-textbox" type="text" name="streetnumber" id="streetnumber" value="" style="width: 80px"></input>&nbsp;单元&nbsp;&nbsp;
				<a href="javascript:doQuery()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:60px">查询</a>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">选择坐落：</font></td>
			<td bgcolor="E6F8E3" colspan="3">
				<select id="queryLocation" name="queryLocation" style="width:80%" onchange="doSelectLocation(this.value);">
					
				</select>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">选择楼栋：</font></td>
			<td bgcolor="E6F8E3" colspan="3">
				<select id="queryBuilding" name="queryBuilding" size='4' multiple style="width:80%" ondblclick="doAdd(this);">
					
				</select>
				&nbsp;&nbsp;
				<a href="javascript:doAdd()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px">添加</a>&nbsp;&nbsp;
				<a href="javascript:doAddAll()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px">全部添加</a>
			</td>
		</tr>
	</table>
	<iframe frameborder="0" height="50%" width="100%" id="listfrm" name="listfrm" src=""></iframe>
	
	<br/>
	<div align="center">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px" onclick="doBack()">返回</a>&nbsp;&nbsp;
	</div>
	<div id="openDL"></div>
	</form>
</body>
</html>
