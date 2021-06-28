<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约人信息</title>
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
	 	
	</script>
	</head>
	<body>
		<table cellpadding="5px" style="padding-left: 50px">
			<tr>
				<td colspan="2" style="font-size: 18px; text-align: center">
					签约人信息
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						签约人账号：
					</div>
				</td>
				<td>
					${signer.loginName }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						签约人姓名：
					</div>
				</td>
				<td>
					${signer.name }
				</td>
			</tr>

			<tr>
				<td>
					<div align="right">
						签约人证件名称：
					</div>
				</td>
				<td>
					${signer.cardName }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						签约人证件号：
					</div>
				</td>
				<td>
					${signer.cardCode }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						签约人身份证号：
					</div>
				</td>
				<td>
					${signer.brokercert }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						挂靠的企业代码：
					</div>
				</td>
				<td>
					<a href="javascript:void(0)" onclick="doSearchCompany(${signer.comp_ID },${signerID })" style="color: red;text-decoration:underline;">${eqVo.comp_Code }</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:doClose();" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">关闭</a>
				</td>
			</tr>
		</table>
	</body>
</html>
