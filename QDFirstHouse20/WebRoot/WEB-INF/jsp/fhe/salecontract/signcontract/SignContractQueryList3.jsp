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

	function doHouseView(obj1,obj2){
		var projectID = $('#projectID').val();
		var startID = $('#startID').val();
		if(obj2==8){
			$('#status').val(obj2);
			var url="<%=contextRoot%>/outer/signcontractFHE/contractSellerCheckFHE.action?projectID="+projectID+"&houseId="+obj1;
			window.openDialog("合同甲方密码确认",url,400,300);
			//openNewDialog("合同甲方密码确认",url,400,300);
		}else{
			//2018-12-05 added by gcf 新增检查登记房屋是否包含抵押。
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=contextRoot%>/outer/signcontractFHE/doQueryCheckOtherFHE.action",
				data : {"houseID":obj1},
				dataType : "json",
				success : function(data){
					if(data[0].result == "success"){//没有抵押
						//window.open("<%=contextRoot%>/outer/signcontractFHE/doHouseViewFHE.action?projectID="+projectID+"&startID"+startID+"&houseId="+obj1);
						var url = "<%=contextRoot%>/outer/signcontractFHE/doHouseViewFHE.action?projectID="+projectID+"&startID="+startID+"&houseId="+obj1+"&status="+obj2;
						parent.openDialog("合同签约",url,100,100);
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

	function doQuery1(){
	 		var a=$("input[name='pwd']").val();
	 		var b=$("input[name='houseId']").val();
	 		var c=$("input[name='sellerPwd']").val();
	 		if(a==""||a==null){
	 			alert("请输入密码！");
	 			return;
	 			}
	 		if(a!=c){
	 			alert("输入的密码不正确！请重新输入!");
	 			$("#pwd").textbox("setValue", "");
	 			return;
	 			}
			var url="<%=contextRoot%>/outer/signcontract/contractBuyerCheck.action?houseId="+b;
			window.openDialog("合同乙方密码确认",url,400,300);
		}
		
		function doQuery2(){
			var projectID = $('#projectID').val();
			var startID = $('#startID').val();
 			var a=[];
 			var b=[];
  			$("input[name='pwd1']").each(function(){ 
      		 		a.push(this.value);
  			})
  			$("input[name='realPwd']").each(function(){ 
      		 		b.push(this.value);
  			})
	 		var c=$("input[name='houseId']").val();
			for (var i = 0 ; i < a.length; i++){
				for (var i = 0 ; i < b.length; i++){
					if(a[i]==""||a[i]==null){
						alert("请输入所有用户的密码！");
						return;
					}
					if(a[i]!=b[i]){
						var val=i+1;
						alert("第"+val+"个用户密码输入错误！请重新输入！");
						a[i]=="";
						return;
					}
				}
			}
			$("#openDL").dialog('close');
	 		//window.open("<%=contextRoot%>/outer/signcontract/doHouseView.action?projectID=${projectID}&startID=${startID}&houseId="+c+"&status="+obj2);
			var url = "<%=contextRoot%>/outer/signcontract/doHouseView.action?projectID="+projectID+"&startID="+startID+"&houseId="+c;
			parent.openDialog("合同签约",url,400,300);
			$('#status').val('');
		}
		
		function doReset1(){
	 		$("#form2").form('clear');
		}
		
		function doReset2(){
	 		//document.getElementById("pwd1").value="";
	 		$("#form3").form('clear');
		}
		parent.enabledButton();
</script>
</HEAD>
<body>
<table width="100%">
<tr>
	<td>
		<form>
			<input type="hidden" name="projectID" id="projectID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("projectID")) %>" />
			<input type="hidden" name="startID" id="startID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("startID")) %>" />
			<input type="hidden" id="status" name="status" value="" />
		</form>
	</td>
</tr>
</table>

<table width="800" border="0" align="center" cellpadding="0" class=css>
    <tr> 
      <td height=18 bgcolor="#CC3300" width="17">&nbsp;</td>
      <td height=18 valign="bottom" width="67" style="font-size: 14;">已登记房屋</td>
      <td height=18 bgcolor="#FFFF00" width="5"  >&nbsp;</td>
      <td height=18 valign="bottom" width="60" style="font-size: 14;">已签房屋</td>
      <td height=18 bgcolor="#00FF99" width="5">&nbsp;</td>
      <td height=18 valign="bottom" width="59" style="font-size: 14;">可售房屋</td>
      
    </tr>
  </table>
<div align="center">
	<font color=#003300>&nbsp;<strong style="font-size: 20;">房屋列表</strong></font>
</div>
 <table border="0" align="center" cellpadding="2" cellspacing="1" class="bottomblue" >
  	<c:forEach items="${list1}" var="b">
    <tr class="css">
    <c:forEach items="${list}" var="a">
  		<c:if test="${a.floor==b.floor}">
			<c:if test="${a.status==2}"> <%--已签房屋， 黄色#FFFF00 --%>
 	 		 <td bgcolor="#FFFF00" height=30 width="100" align=center>
 	  			<font style="font-size: 14;color:black;">
 	  				${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
 	  		<%--已登记房屋  红色#CC3300--%>
 	  		<c:if test="${a.status==3}">  
 	 		 <td bgcolor="#CC3300" height=30 width="200" align=center>
 	  			<font style="font-size: 14;color:black;">
 	  				${a.room_Number }
 	  			</font>
 	  		</td>
 	  		</c:if>
			<c:if test="${a.status==4}"> <%--可售房屋  绿色#00FF99--%>
 	 		 <td bgcolor="#00FF99" height=30 width="100" align=center>
 	  			<font style="font-size: 14;color:black;">
 	  				<a href="javascript:doHouseView(${a.house_ID},${a.status})" style="text-decoration: underline;color:black;" >${a.room_Number }</a> 	  		
	  			</font>
 	  		</td>
 	  		</c:if>
 	  		</c:if>
 	  	 </c:forEach>
      </tr>
      	</c:forEach>
 
    </table>  
  <P align=center>
  </P>
 <div id="openDL"></div>	
</body>
</html>
