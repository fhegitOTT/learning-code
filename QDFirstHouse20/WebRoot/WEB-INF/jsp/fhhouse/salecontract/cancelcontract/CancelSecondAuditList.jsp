 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%
 	String path = request.getContextPath();
 	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质录入列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	<script type="text/javascript">	
		
		function doContractReview(ID){
			parent.window.location="<%=path%>/inner/contractmanage/gotoContractCancelEdit.action?cID="+ID;
		}
		
		
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<div align="right">
	</div>
		<div id="p" class="easyui-panel" title="申请撤销合同列表"
					    style="width:95%;height:95%;background:#fafafa;"
					    data-options="iconCls:'icon-collapse',fit:true,noheader:false">
		
					<form name="tableForm" id="tableForm" method="post"  action="">
					<input type="hidden" name="contractId" value="${contractId }" />
					<input type="hidden" name="sellerName" value="${sellerName }" />
					<input type="hidden" name="status" value="${status }" />
					<input type="hidden" name="buyerName" value="${buyerName }" />
					<input type="hidden" name="reason" value="${reason }" />
					<input type="hidden" name="confirmDate1" value="${confirmDate1 }" />
					<input type="hidden" name="confirmDate2" value="${confirmDate2 }" />
<%
						String htmlview = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview!=null && !"".equals(htmlview) && !htmlview.equals("null")){
						    out.println(htmlview);
					}%>
					</form>
		</div>
</body>
</html>
