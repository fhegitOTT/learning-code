<%@page import="com.netcom.nkestate.common.StringStamper"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
   	String path = request.getContextPath();
   	SmUserVO vo = (SmUserVO) request.getSession().getAttribute("LgUser");
 	long userID = vo.getUserId();
 	String transactionID = String.valueOf(request.getAttribute("transactionID") == null ? "":request.getAttribute("transactionID").toString());
	String districtID = String.valueOf(request.getAttribute("districtID") == null ? "":request.getAttribute("districtID").toString());
 	String strDate = DateUtil.format(DateUtil.getSysDate(), "yyyyMMdd");
	String tempDistrictID = "";
	if(districtID!=null && !"".equals(districtID)){
		if(districtID.trim().length() == 1){
			tempDistrictID = "0"+districtID;
		}else{
			tempDistrictID = districtID;
		}
	}
	String secretKey = StringStamper.encode(transactionID + tempDistrictID + strDate);
	String imageID = String.valueOf(request.getAttribute("imageID") == null ? "":request.getAttribute("imageID").toString());
	String TargetUrl = path+"/inner/permitmanage/ShowImageDetail.action?transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey; 

 %>
<!-- 
  - Author(s): gcf
  - Date: 2019-01-51 11:08:00
  - Description:
-->
<head>
<title>查看影像资料</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body>
	<input type=button class="button" name=btn1 value=" 顺时针 " onclick="fileclient.turnANew()">
    <input type=button class="button" name=btn2 value=" 逆时针 " onclick="fileclient.turnBNew()">
    <input type=button class="button" name=btn3 value=" 放 大 " onclick="fileclient.zoomIn()">
    <input type=button class="button" name=btn4 value=" 缩 小 " onclick="fileclient.zoomOut()">
     <%if(imageID!=null && !"".equals(imageID)){%>
    <input type=button class="button" name=btn5 value=" 上一页 " onclick="fileclient.preImage()">
    <input type=button class="button" name=btn6 value=" 下一页 " onclick="fileclient.nextImage()">
	<%} %>
    <input type=button class="button" name=btn4 value="最适合页面" onclick="fileclient.adaptToWindows()">
    <input type=button class="button" name=btn4 value="最适合宽度" onclick="fileclient.adaptToPageWidh()">
    <input type=button class="button" name=btn4 value="最适合A4 " onclick="fileclient.adaptToPage(678,960)">  
    <input type=button class="button" name=btn4 value=" 还 原 " onclick="fileclient.resume()">  
    <input type=button class="button" name=btn4 value=" 打 印 " onclick="fileclient.myprint()">
   
    <iframe src="<%=TargetUrl%>" id="fileclient" width="1500px" height="1200px"  name="fileclient" framespacing="0" frameborder="0" bgcolor="lightgreen" scrolling=auto></iframe>

	<script type="text/javascript">
    </script>
</body>
</html>