<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约人许可证权限查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>

		<style type=text/css>
			* {color: #555;}
			body {font-size: 12px;margin: 0;font: Arial, Helvetica, "宋体", sans-serif;}
			.toplogo {width: auto;line-height: 40px;margin-left: 10px;display: inline;float: left;overflow-y: hidden;}
			/*最底部*/
			.bottom {overflow-y: hidden}
			.bottom_ban {width: auto;line-height: 40px;margin-left: 18px;display: inline;float: left;overflow-y: hidden;}
			.bottom_shi {width: auto;line-height: 40px;margin-right: 20px;display: inline;float: right;overflow-y: hidden;}
		</style>

	<script type="text/javascript">
	 	function doSearch(id){
	 		window.location="<%=path%>/inner/limitbuyhousemanage/gotoLimitBuyHouseList.action?building_id="+id;
	 	}
	</script>
	</head>
	<body id="body-layout" class="easyui-layout" style="margin-right: 1px; margin-left: 1px">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',title:'限购房屋-楼幢列表',iconCls:'icon-ok'">
				<form method="post" action="">
					<div>
						<%
							String htmlview = String.valueOf(request.getAttribute("htmlView"));
							if (htmlview != null && !"".equals(htmlview) && !htmlview.equals("null")) {
								out.println(htmlview);
							}
						%>
					</div>
					<div align="center">
						<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返 回</a>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
