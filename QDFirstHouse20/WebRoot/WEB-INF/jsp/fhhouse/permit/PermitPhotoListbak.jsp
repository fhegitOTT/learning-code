 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>标准收件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="doPermitEdit()">保存</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="doPermitDel()">取消</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:100px" onclick="gotoPhoto()">全选</a>&nbsp;&nbsp;
		</div>
		<div id="p" class="easyui-panel" title="<c:if test="${logo!='edit'}">收件材料列表</c:if>" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<form name="tableForm" id="tableForm" method="post"  action="">
				<%  
					String htmlview = String.valueOf(request.getAttribute("htmlView"));
					if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						   out.println(htmlview);
					}
				%>
				<div>
					<div  style="border-bottom: 0; padding: 0px;">
						<table style="width: 100%;">
							<tr>
								<td style="width: 100%;">补充收件</td>
								<td style="white-space: nowrap;"><a class="easyui-linkbutton" iconCls="icon-add" onclick="addRow">增加</a> 
							</tr>
						</table>
					</div>
				</div>
				<table id ="extraFile" class="easyui-datagrid" style="width:100%;height:250px"	 data-options="url:'datagrid_data.json',fitColumns:true,singleSelect:true">
	   				 <thead>
	        			<tr>
				            <th data-options="field:'fileName',width:100">文件名称</th>
				            <th data-options="field:'fileCount',width:100">页数</th>
				            <th data-options="field:'fileType',width:100">文件类型</th>
				            <th data-options="field:'fileFlag',width:100">标志</th>
				            <th data-options="field:'remark',width:100">备注</th>
				            <th data-options="field:'delete',width:100">删除</th>
			       		 </tr>
			    	</thead>
				</table>
			</form>
		</div>
			
			<div align="center" id="buttonID">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:110px" onclick="doBack()">返回</a>&nbsp;&nbsp;
			</div>
		<script type="text/javascript">	
		$(function(){
		});

		function addRow(){
			var data=$('#extraFile').datagrid('getData');
			var len = data.rows.length;
		    var newRow = {fileName:"",transactionID:transactionID,districtID:districtID,fileKind:3,fileCount:1,fileType:1,fileFlag:1,receivedFlag:1};
		    $('#extraFile').datagrid('appendRow',{
		        index: len,  // 索引从0开始
		        row: newRow
		    });
		}
		
		//获取选择ID
		function findSelectID(){
			var houseIDs="";
			$.each($('input:checkbox'),function(){
					if(this.checked){
						houseIDs += $(this).val()+",";
					}
				});
			return houseIDs;
		}

		function doSave(){
			//获取选中的houseID.
			var houseIDs = findSelectID();
			var houseArr = houseIDs.split(',');
			if(houseArr == null || houseArr.length == 0){
				$.messager.alert("提示","请先选择房屋！");
				return;
			}
			
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSaveHouseRelate.action",
				data : {"houseIDs":houseIDs,"permitID":${permitID},"transID":${transID}},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						doBack();
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("查询异常！");
				}
		});
		}

		function doBack(){
			window.location = "<%=path%>/inner/permitmanage/gotoPermitAddQueryList.action";
		}

		function query(){
			//清空原下拉框数据
			$('#locationID').combobox('setValue','');
			//$('#buildingID').combobox('setValue','');
			var roadName = $("#roadName").textbox("getValue");
			var laneName = $("#laneName").textbox("getValue");
			var subLaneName = $("#subLaneName").textbox("getValue");
			var streetNumber = $("#streetNumber").textbox("getValue");
			if(roadName==""&& laneName==""&& subLaneName==""&&streetNumber==""){
	    	     alert("请输入查询条件，再点击查询！","提示");
	    	     $("#roadName").focus();
	    	     return;
	    	}
			var districtID = $('#districtID').combobox('getValue');
			var data = {"districtID": districtID,"roadName":roadName,"laneName":laneName,"subLaneName":subLaneName,"streetNumber":streetNumber};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSearchLocations.action",
				data : data,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var locations = data[0].data;
						if(locations!=null&&locations.length>0){
							if(locations.length>1000){
								$.messager.alert("提示","结果数据过多,请精确查找！");
								return;
							}
							//console.log(locations);
							$("#locationID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#locationID').combobox('loadData',locations);
							$("#locationID").combobox({
								onChange:function(n,o){
								queryBuilding();
								}
							});
							$('#locationID').combobox('select',locations[0].id);
							//queryBuilding(districtID);
						}else{
							$.messager.alert("提示","未查询到对应的坐落信息。");
							return;
						}
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("查询异常！");
				}
		});
		}

		function queryBuilding(){
			var districtID = $('#districtID').combobox('getValue');
			var locationID = $('#locationID').combobox('getValue'); 
			var json = {"districtID": districtID,"locationID":locationID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSearchBuildingsByLocationID.action",
				data : json,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var buildings = data[0].data;
						if(buildings!=null && buildings.length>0){
							//if(buildings.length>1000){
							//	$.messager.alert("提示","结果数据过多,请精确查找！");
							//	return;
							//}
							//console.log(buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#buildingID').combobox('loadData',buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									queryHouse();
								}
							});
							$('#buildingID').combobox('select',buildings[0].id);
						}else{
							$.messager.alert("提示","未查询到对应的幢号。");
							return;
						}
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("查询幢号信息异常！");
				}
		});
		}

		function queryHouse(){
			var districtID = $('#districtID').combobox('getValue');
			var buildingID = $('#buildingID').combobox('getValue'); 
			var json = {"districtID": districtID,"buildingID":buildingID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSearchHousesByBuildingID.action",
				data : json,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						var houses = data[0].data;
						if(data[0]!=null && houses>0){
							document.getElementById("houseGrid").innerHTML = data[0].htmlView;
						}else{
							$.messager.alert("提示","未查询到对应的房屋。");
							return;
						}
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert("查询房屋信息异常！");
				}
			});
		}
		
	</script>
</body>
</html>
