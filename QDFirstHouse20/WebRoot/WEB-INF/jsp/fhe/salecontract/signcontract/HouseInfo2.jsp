 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>房屋信息页面</title>
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

	//获取合同模板
	function mychange(){
    	$('#template')[0].style.display="";
    	$('#template').empty();
    	var type= $("select[name='type']").val();
    	if (type=="1"||type=="2"){
    		$.ajax({
    			type : "POST",
    			contentType : "application/x-www-form-urlencoded;charset=utf-8",
    			url : '<%=path%>/outer/signcontract/findTemplate.action',  
    			data : {projectID:$('#projectID').val(),startID:$('#startID').val(),type:type},
    			dataType : "json",
    			success : function(data){
    				$('#template').append('<option value=0 selected>不使用合同模板</option>');
    				if(data.length>0){
    					$.each(data,function (index,option){
    						$('#template').append('<option value='+option.code+'>'+option.name+'</option>');
    					});
    				}
    			},
    			error : function(){
    				alert("查询项目列表出错！");
    			}
    		});
    	}
    	else{
    		$('#template')[0].style.display="none";
    	}
	}
	
	//合同签订
	function doSignContract(){
		var a= $("select[name='type']").val();
		$("#type").val(a);
		if(a==0){
			alert("请选择合同类型!");
			return;
		} 		
 		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : "<%=path%>/outer/signcontractFHE/signContractCheckFHE.action",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data[0].result=="success"){
					form1.submit();
				}else{
					alert(data[0].message);
				}
			},
			error : function(){
				alert("检查失败！");
			}
		});
		//window.location="<%=path%>/outer/signcontract/presell.action";
 	}
 	
 	function doContractTypeQuery(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : "<%=path%>/inner/signcontract/doContractTypeQuery.action?houseId="+form1.houseId.value,
			data : "{}",
			dataType : "json",
			success : function(data){
				if(data[0].result=="success"){
					$('#type').empty();
					$('#type').append(data[0].startBuildingList);
					
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
</script>
</head>
<body id="body-layout" onload="" >
	<form method="post" action="<%=path%>/outer/signcontractFHE/createContractFHE.action" name="form1" id="form1" onsubmit="return false;">
	<input type="hidden" id="projectID" name="projectID" value="${projectID }" />
	<input type="hidden" id="startID" name="startID" value="${startID }" />
	<input type="hidden" name="houseId" value="${houseId }" />
	<input type="hidden" name="type" id="type" value="" />
	<input type="hidden" name="earnestID" id="earnestID" value="${earnestID }" />
	<input type="hidden" name="ctype" value="${ctype }" />
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" colspan="5" align="center" style="width:100%;background-color: #77aadd"><font color="#0055ff" size="5" ><strong>房屋信息查询结果</strong></font></td>
		</tr>
		
		<%CHFlatVO chFlatVO=(CHFlatVO)request.getAttribute("chFlatVO");
			if(chFlatVO!=null){
			List<CHFlatVO> arList=(List)request.getAttribute("arList");
			if(arList!=null && arList.size()>0){
				CHFlatVO realvo=arList.get(0);
        	List<HouseVO> permitList=(List)request.getAttribute("permitList");
        	if(permitList!=null && permitList.size()>0){
        		HouseVO hvo=permitList.get(0);
         %> 		
         <tr >
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="11" align="center"><font style="color: white">房<br/>屋<br/>信<br/>息</font></td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">幢号</font></td>
			<td class="input_right" width="30%" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="doorNum" id="doorNum"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("doornum")==null?"":chFlatVO.getAttribute("doornum") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋坐落</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="location" id="location"  data-options="validType:'length[0,100]',editable:false"  value="<%=realvo.getAttribute("location")==null?"":realvo.getAttribute("location") %>" style="width: 400px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">建筑面积</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="buildingArea" id="buildingArea"  data-options="validType:'length[0,50]',editable:false"  value="<%=chFlatVO.getAttribute("buildingarea")==null?"":chFlatVO.getAttribute("buildingarea") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋类型</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="houseType" id="houseType"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housetype")==null?"":chFlatVO.getAttribute("housetype")%>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋所有人</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="sourceID" id="sourceID"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("own")==null?"":chFlatVO.getAttribute("own")%>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">部位</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="part" id="part"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("part")==null?"":chFlatVO.getAttribute("part") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">区市编号</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="districtid" id="districtid"  data-options="validType:'length[0,30]',editable:false"  value="<%=hvo.getDistrictID() %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">层数</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="totalFloors" id="totalFloors"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("totalfloors")==null?"":chFlatVO.getAttribute("totalfloors") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋结构</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="houseArch" id="houseArch"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housearch")==null?"":chFlatVO.getAttribute("housearch")%>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">竣工日期</font></td>
			<td class="input_right" style="background-color: #aaccdd">
				<input class="easyui-textbox" type="text" name="finishEndDate" id="finishEndDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("finisheddate")==null?"":chFlatVO.getAttribute("finisheddate")%>" style="width: 300px"></input>
			</td>
		</tr>
		<%
		}
		}
			}
		 %>
		
	</table>
	
	
	<br/>
	<div align="center">
	<table width="600" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="E2EEFC">
		<tr>
		  <td align="center" >
          <input type="button"   value="进入合同签订" name="operation"  onclick="doSignContract()" class="bottomblue" style="background-color:#4E9EEB;"/>
          <input type="button"   value="关闭" name="B2" onclick="parent.$('#openDL').dialog('close');" class="bottomblue" style="background-color:#4E9EEB;"/>
          <p></p>
		  </td>
		 </tr>
	</table>
	</div>
	<br/>
	</form>
</body>
</html>
