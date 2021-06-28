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
 	String transactionID = request.getAttribute("transactionID").toString();
	String districtID = request.getAttribute("districtID").toString();
 	String strDate = DateUtil.format(DateUtil.getSysDate(), "yyyyMMdd");
	String tempDistrictID = "";
	if(districtID!=null && !"".equals(districtID)){
		if(districtID.trim().length() == 1){
			tempDistrictID = "0"+districtID;
		}else{
			tempDistrictID = districtID;
		}
	}
	//System.out.println("transactionID=="+transactionID);
	//System.out.println("tempDistrictID=="+tempDistrictID);
	//System.out.println("strDate=="+strDate);
	String key = StringStamper.encode(transactionID + tempDistrictID + strDate);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>图片信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>		
		<style type="text/css">
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
		var secretKey = "<%=key %>";
		var transactionID = "<%=transactionID %>";
		var districtID = "<%=districtID %>";
	 	function closeWindows(){
			//window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
			window.close();
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

		function showImageDetail(imageID,diskfilename){
			//var TargetUrl = "ShowImage.jsp?transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey+"&imageFile="+encodeURI(encodeURI(diskfilename.replace('&','-')));
			var TargetUrl = "<%=path%>/inner/permitmanage/ShowImage.action?transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey;
			
			window.open(TargetUrl,"_blank","toolbar=no,menubar=no, scrollbars=no, resizable=yes,location=no, status=no");	
		}
	</script>
</head>
<body id="body-layout" >
	<input type="hidden" name="permitID" id="permitID" value="${permit.permitID}"/>
	<div id="photo" class="easyui-panel" title="<c:if test="${logo!='edit'}">收件材料列表</c:if>">
				<%  
					String htmlView = String.valueOf(request.getAttribute("htmlView"));
					if(htmlView!=null && !"".equals(htmlView) && !htmlView.equals("null")){
						   out.println(htmlView);
					}
				%>
	</div>
	<br/>
	<div align="center">
		<a href="javascript:closeWindows()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px">关闭</a>&nbsp;&nbsp;
	</div>
</body>
</html>
