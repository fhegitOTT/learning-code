<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String contextRoot = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>合同签约房屋列表</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type=text/css>

</style>
<script type="text/javascript" src="<%=contextRoot%>/js/application.js"></script>
<script type="text/javascript">
	function doHouseView(house_ID,status){
		var projectID = ${projectID};
		var startID = ${startID};
		$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=contextRoot%>/outer/signcontractFHE/doQueryCheckOtherFHE.action",
				data : {"houseID":house_ID},
				dataType : "json",
				success : function(data){	
				
					if(data[0].result == "success"){//没有抵押
						var url = "<%=contextRoot%>/outer/signcontractFHE/doHouseViewFHE.action?projectID="+projectID+"&startID="+startID+"&houseId="+house_ID+"&status="+status;
						parent.openDialogFHE("合同签约2",url);
					}else{
						alert(data[0].msg);
						return;
					}
				},
				error : function(){
					alert("检查失败！");
				}
			});
			
	}

</script>
</HEAD>
<body>
<div align="center" style="height: 281px; ">
<table id="houseList">
	<tr id="paramHide">
		<input type="hidden" id="projectID" name="projectID" value="<%=request.getAttribute("projectID") %>"/>
		<input type="hidden" id="startID" name="startID" value="<%=request.getAttribute("startID") %>"/>
	</tr>
	<tr id="explain">
		  <td height=15 bgcolor="#CC3300" width="40">&nbsp;</td>
	      <td height=15 valign="bottom" width="67" style="font-size: 14;">已登记房屋</td>
	      <td height=15 bgcolor="#FFFF00" width="40"  >&nbsp;</td>
	      <td height=15 valign="bottom" width="60" style="font-size: 14;">已签房屋</td>
	      <td height=15 bgcolor="#0FF000" width="40">&nbsp;</td>
	      <td height=15 valign="bottom" width="59" style="font-size: 14;">可售房屋</td>
	</tr>
	<tr id="houseList">
		<table id="hosts">
		<% String bgColor="#0FF000"; %>
		<c:forEach items="${list1}" var="floor">
			<tr>
			<c:forEach items="${list}" var="host">
				<c:if test="${floor.floor==host.floor}">
					<c:if test="${host.status==2 }">
						<% bgColor="#FFFF00"; %>
					</c:if>
					<c:if test="${host.status==3 }">
						<% bgColor="#CC3300"; %>
					</c:if>
				
					<td bgcolor=<%=bgColor %> height=30 width="100" align=center>
						<c:if test="${host.status==4 }">
						 	 <a href="javascript:doHouseView(${host.house_ID},${host.status})" style="text-decoration: underline;color:black;" >${host.room_Number }</a> 	 
						</c:if>
						<c:if test="${host.status!=4 }">
			 	  			${host.room_Number }
			 	  		</c:if>
			 	  	</td>
				</c:if>
			</c:forEach>
			</tr>
		</c:forEach>
		</table>
	</tr>
</table>
</div>

</body>

</html>
