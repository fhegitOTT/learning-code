<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登记信息查看页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js">
		</script>
	</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px">
		<div class="easyui-layout" data-options="fit:true">
			<div class="easyui-tabs" id="tabs" data-options="fit:true" >
				<div title="登记案件信息"   closable="false" style="padding: 10px;">
					<iframe height="100%" id="djinfo"  width="100%" src="<%=path%>/transaction/forwardFrame.action?transactionID=${transactionID}" rel="noreferrer" ></iframe>
				</div>
			</div>
		</div>
<div id="openDL"></div>
</body>
</html>
