 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同买方编辑页面</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<style type="text/css">
			.td1{align:center;
				background-color:red;}
		</style>

	</head>
	<body >
		<form id="buyerinfo" >
			<input type="button" name="buyer" value="新增乙方2"  onclick="doAdd(${contractID});"/>
			<input type="button" name="buyer" value="删除乙方"  onclick="delUserConfirm();"/>
 			<%-- <input type="button" name="doBack2" onclick="goBack(${contractID})" value="返回"/> --%>
			<%-- htmlView =  <%=String.valueOf(request.getAttribute("htmlView")) %> --%>
			buyerList =  <%=String.valueOf(request.getSession().getAttribute("buyerList")) %>
			<%
				String htmlview = String.valueOf(request.getAttribute("htmlView"));
				if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
					out.println(htmlview);
				}%>
				
		<table id="search_list" width="100%" align="center" border="1" style=";">

	    	<tr class="tr1">
	    		<th align="center" width="10%" style="background:#76b; color:#FFF"><input type="checkbox" id="allBox" name="allBox"  onclick="javascript:checkAll();"/></th>
	    		<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >合同编号</th>
				<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >序号</th>
	    		<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >名称</th>
	    	 	<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >性别</th>
	    	 	<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >住址</th>
	    	 	<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >邮箱</th>
	    	 	<th align="center" width="10%" height="30px" style="background:#76b; color:#FFF" align="center" >电话</th>
	    	 </tr>
			 <tr>
			 
				<table id="search_list_content" width="100%" align="center" border="1" border-collapse="collapse" border-spacing= "0">
			    	 <c:forEach  var="h" items="${sessionScope.buyerList }" begin="0" > 
			    		 <tr name="checkTr" class="NoSelected">
			    		 	<td align="center" width="10%">
			    		 		<input type="checkbox" class="box"  name="box" value="${h.serial}" onclick="javascript:checkOne();"/>
			    		 	</td>
			    			<%-- <td id="fId" class="td1" width="120px"   value="${h.regionID}" align="center" onclick="javascript:editUserJSP('${h.peopleUserID}');">${h.regionID}</td> --%>
			    			<td id="fId" class="td1" width="10%"   value="${h.contractID}" align="center" >${h.contractID}</td>		
			    			<td id="fId" class="td1" width="10%"   value="${h.serial}" align="center" >${h.serial}</td>	
			    			<td id="fId" class="td1" width="10%"   value="${h.buyerName}" align="center" >${h.buyerName}</td>
							<td id="fNameLogin" class="td1" width="10%"  value="${h.buyerSex}" align="center" >${h.buyerSex}</td>
							<td id="fNameShow" class="td1" width="10%"   value="${h.buyerAddress}" align="center" >${h.buyerAddress}</td>
							<td id="fTele" class="td1" width="10%"  value="${h.buyerPostcode}" align="center" >${h.buyerPostcode}</td>
							<td id="fEmail" class="td1" width="10%"  value="${h.buyerCall}" align="center" >${h.buyerCall}</td>				     		</td>
						</tr>
				 	</c:forEach> 
			 	</table>
		 	 </tr>		
			</table>	
				
		</form>
		<div id="dialogFHE"></div>
		<script type="text/javascript">
			var isCheckAll = false;
			var allBox = $("#allBox");
			var boxes =$('input:checkbox[name="box"]');
			var trs=$("#search_list_content").find("tr");
			
			function checkAll(){
			    if(allBox.is(':checked'))
			    {
				      for(var i=0; i<boxes.length; i++)
				      {
				        boxes.eq(i).prop('checked', true);
				      }
			    }else{
				      for(var i=0; i<boxes.length; i++)
				      {
				        boxes.eq(i).removeAttr('checked');
				      }
		    	}
			}
			
				
			function goBack(contractID){
				var url = "<%=basePath%>/outer/signcontractFHE/createContractFHEBack.action?contractID="+contractID;
				parent.parent.openDialog("乙方新增4",url,700,400);
			}	

				
		 	function doAdd(contractID){
		 	alert("1234")
				var url = "<%=basePath%>/outer/signcontractFHE/operaBuyerInfoFHEAdd.action?contractID="+contractID;
				parent.parent.openDialog("乙方新增5",url,700,400);
				//$('#dialogFHE').dialogdialog('refresh', url);
			}
			
			
			function closeDialog(contractID){
				parent.parent.dialog('close');
			}		
				
			function doModify(contractID,serial){
				var url = "<%=basePath%>/outer/signcontractFHE/operaBuyerInfoFHEADD.action?contractID="+contractID+"&serial="+serial;
				//parent.openDialog("乙方修改",url,900,600);
				$('#dialogFHE').dialogdialog('refresh', url);
			}
			function delUserConfirm(){
				var length=$('input[name=box]:checked').length;
				alert("delUserConfirm length="+length)
				if(length==0){
					if(confirm("请选择要删除的用户！")){
					}
				}else{
					//执行删除方法
					doDelete(${contractID});
				  }
			 }
			function doDelete(contractID){
				var chk_value =[];  
			   $('input[name=box]:checked').each(function(){  
				   chk_value.push($(this).val());
			   });
 				alert("doDelete length="+chk_value.length)
				/*if(serial==1){
					alert('请勿删除第一顺序的乙方！请修改！');
					return false;
				}*/
				//if(confirm('确定删除'+buyerName+'吗?')){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/signcontractFHE/deleteBuyerFHE.action?contractID='+contractID,  
						data : {chk_value:chk_value},
						dataType : "json",
						success : function(data){
							parent.go1(contractID);
							//location.reload();
							//window.location.href='<%=basePath%>/outer/signcontractFHE/deleteBuyerFHE.action?contractID='+contractID+"&chk_value="+chk_value; 
							/* if(data[0].result=='success'){
								alert(data[0].msg);
								location.reload();
								parent.doUpdate('乙方编辑');
							}else{
							
								alert(data[0].msg1);
							} */
						},
						error : function(){
							alert("删除乙方出错！");
						}
					});
				//}
			}
		</script>
	
	</body>
</html>