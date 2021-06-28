 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
	String contextRoot = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>房屋信息页面</title>
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all"/>


</head>
<body id="body-layout" onload="" >
	<form id="hostInfo" name="hostInfo" method="post" action="<%=path%>/outer/signcontractFHE/createContractFHE.action" align="center">
		<table id="hostTab" style="width:50%;background-color: #7DD5FF" align="center"> 
			<tr>
				<input type="hidden" id="projectID" name="projectID" value="${projectID }" />
				<input type="hidden" id="startID" name="startID" value="${startID }" />
				<input type="hidden" name="houseId" value="${houseId }" />
				<input type="hidden" name="type" id="type" value="" />
				<input type="hidden" name="earnestID" id="earnestID" value="${earnestID }" />
				<input type="hidden" name="ctype" value="${ctype }" />
			</tr>
			<tr>
				<td class="input_left" colspan="5" align="center" style="width:100%;background-color: #77aadd">
				<font color="white" size="15" ><strong>房屋信息查询结果</strong></font>
				</td>
			</tr>
			<%
				CHFlatVO chFlatVO=(CHFlatVO)request.getAttribute("chFlatVO");
				if(chFlatVO!=null){
				List<CHFlatVO> arList=(List)request.getAttribute("arList");
				if(arList!=null && arList.size()>0){
					CHFlatVO realvo=arList.get(0);
					
	        	List<HouseVO> permitList=(List)request.getAttribute("permitList");
	        	if(permitList!=null && permitList.size()>0){
	        		HouseVO hvo=permitList.get(0);
	         %> 	
         
			<tr >
				<td  bgcolor="#5BADFF" width="100%" rowspan="11" align="center"><font style="color: white">房<br/>屋<br/>信<br/>息</font></td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF" width="80%"><font style="color: white">幢号</font></td>
				<td  width="20%" style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="doorNum" id="doorNum"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("doornum")==null?"":chFlatVO.getAttribute("doornum") %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">房屋坐落</font></td>
				<td style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="location" id="location"  data-options="validType:'length[0,100]',editable:false"  value="<%=realvo.getAttribute("location")==null?"":realvo.getAttribute("location") %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td  bgcolor="#5BADFF"><font style="color: white">建筑面积</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="buildingArea" id="buildingArea"  data-options="validType:'length[0,50]',editable:false"  value="<%=chFlatVO.getAttribute("buildingarea")==null?"":chFlatVO.getAttribute("buildingarea") %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">房屋类型</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="houseType" id="houseType"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housetype")==null?"":chFlatVO.getAttribute("housetype")%>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">房屋所有人</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="sourceID" id="sourceID"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("own")==null?"":chFlatVO.getAttribute("own")%>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF" width="15%"><font style="color: white">部位</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="part" id="part"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("part")==null?"":chFlatVO.getAttribute("part") %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">区市编号</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="districtid" id="districtid"  data-options="validType:'length[0,30]',editable:false"  value="<%=hvo.getDistrictID() %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">层数</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="totalFloors" id="totalFloors"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("totalfloors")==null?"":chFlatVO.getAttribute("totalfloors") %>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">房屋结构</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="houseArch" id="houseArch"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housearch")==null?"":chFlatVO.getAttribute("housearch")%>" style="width: 300px"></input>
				</td>
			</tr>
			<tr>
				<td bgcolor="#5BADFF"><font style="color: white">竣工日期</font></td>
				<td  style="background-color: #aaccdd">
					<input class="easyui-textbox" type="text" name="finishEndDate" id="finishEndDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("finisheddate")==null?"":chFlatVO.getAttribute("finisheddate")%>" style="width: 300px"></input>
				</td>
			</tr>
				<%
		}
		}
			}
		 %>
		 
			<div align="center">
			<table width="50%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="E2EEFC">
				<tr>
				  <td align="center" style="background-color:#4E9EEB;">
				   <input type="submit"   value="合同签订" name="submit" />
			        <!--  <input type="button"   value="合同签订" name="sign"  onclick="doSignContract()"  /> -->
			         <input type="button"   value="关闭" name="close" onclick="parent.parent.parent.$('#modifyPassword').dialog('close');" />
			           &nbsp;&nbsp;  &nbsp;&nbsp;   &nbsp;&nbsp;  &nbsp;&nbsp;
				  </td>
				 </tr>
			</table>
			</div>
	

		</table>
	</form>
	<script type="text/javascript">	
		//合同签订
	function doSignContract(){
 		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : "<%=path%>/outer/signcontractFHE/signContractCheckFHE.action",
			data : $("#hostInfo").serialize(),
			dataType : "json",
			success : function(data){
				if(data[0].result=="success"){
					//hostInfo.submit();
					var url = "<%=path%>/outer/signcontractFHE/createContractFHE.action?"+$("#hostInfo").serialize();
					parent.parent.parent.openDialog("合同签约合同",url,700,400);
				}else{
					alert(data[0].message);
				}
			},
			error : function(){
				alert("检查失败！");
			}
		});
 	}
 	
</script>
</body>
</html>
