<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	
	String contextRoot = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>房屋查询</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<STYLE type=text/css>

</STYLE>
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/new.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/common.css" type="text/css">
<link rel="stylesheet" href="<%=contextRoot%>/zh/css/default.css" type="text/css">
<script type="text/javascript" src="<%=contextRoot%>/js/application.js"></script>

<script type="text/javascript">
	function doChange(obj){
	
		 var b=$(obj).is(':checked');
		 var a = confirm("确定要改变房屋状态吗?");
		 var houseId=$(obj).next("input").val();
  		 if(!a){
  		 	if(b==true){
  		 		$(obj).removeAttr("checked");
  		 	}else{
  		 		$(obj).prop("checked","true");
  		 	}	
  		 	return false;
  		}
  		$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=contextRoot%>/inner/house/changeStatus.action?status="+b+"&houseId="+houseId,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						//$("#openDL").dialog('close');
						window.location.reload();
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("房屋操作失败！");
				}
			});	
	}
	
	function doBack(){
		window.location = "<%=contextRoot%>/inner/house/buildingInfo.action?startID=${startID}&projectID=${projectID}";
	}
	
	
</script>
</HEAD>

<body bgcolor="5E9B5E">
<table width="100%">
<tr>
</tr>
</table>
    <table width="800" border="0" align="center" cellpadding="0" class=css>
    <tr> 
      <td height=18 bgcolor="#CC99CC" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="60" style="font-size: 14;">已付定金</td>
      <td height=18 bgcolor="#FFFF00" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="60" style="font-size: 14;">已签房屋</td>
      <td height=18 bgcolor="#CC3300" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="67" style="font-size: 14;">已登记房屋</td>
      <td height=18 bgcolor="#E2EEFC" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="130" style="font-size: 14;">未纳入本开盘单元销售的房屋</td>
      <td height=18 bgcolor="#00FF99" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="59" style="font-size: 14;">可售房屋</td>
      <td height=18 width="17" bgcolor="#999999">&nbsp;</td>
      <td height=18 valign="bottom" width="78" style="font-size: 14;">表示人工干预不可售</td>
      
    </tr>
  </table>
  <table width="396" border="0" align="center" cellpadding="0" class=css>
    <tr>
      <td height=18  width="20"><font color="#003300">
        <input disabled  name=houseid_check47 TYPE=checkbox VALUE=5000000034448 checked>
        </font></td>
      <td height=18 valign="bottom" width="100" style="font-size: 14;" >表示人工干预可售</td>
      <td height=18  width="20"><font color="#003300">
        <input disabled name=houseid_check372 TYPE=checkbox VALUE=5000000034420>
        </font></td>
      <td height=18 valign="bottom" width="120" style="font-size: 14;">表示人工干预不可售</td>
  </tr>
</table>

 <table border="0" align="center" cellpadding="2" cellspacing="1" class="bottomblue" >
    
   	<div align="center">
      <p>
		<FONT color=#003300>&nbsp;<strong font style="font-size: 20;">房屋列表</strong></FONT>
	  </p>
     </div>
  	<c:forEach items="${list1}" var="b">
  	
    <tr class="css">
    <c:forEach items="${list}" var="a">
  		<c:if test="${a.floor==b.floor}">
  		
			<c:if test="${a.status==2}">
 	 		 <td bgcolor="#FFFF00" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
 	  		<c:if test="${a.status==3}">
 	 		 <td bgcolor="#CC3300" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
			<c:if test="${a.status==4 && a.manual_Status==1}">
 	 		 <td bgcolor="#00FF99" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  				<input type="checkbox" name="status"  id="status" checked="checked" onclick="doChange(this)" value="${a.house_ID}"/>
 	  				<input type="hidden" name="houseID" id="houseID" value="${a.house_ID}" />
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
			<c:if test="${a.status==4 && a.manual_Status==0}">
 	 		 <td bgcolor="#999999" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  				<input type="checkbox" name="status"  id="status" onclick="doChange(this)" value="${a.house_ID}"/>
 	  				<input type="hidden" name="houseID" id="houseID" value="${a.house_ID}" />
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
 	  		<c:if test="${a.status==8}">
 	 		 <td bgcolor="#CC99CC" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
 	  		<c:if test="${a.status==9}">
 	 		 <td bgcolor="#E2EEFC" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		
 	  		</c:if>
 	  	 </c:forEach>
 	  		
      </tr>
      	
      	</c:forEach>
 
    </table>  
  <P align=center>
  	<a href="javascript:doSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">确定</a>
	<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
  </P>
</BODY>
</HTML>
