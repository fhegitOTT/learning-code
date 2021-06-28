<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约企业信息</title>
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
					签约企业信息
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						企业名称：
					</div>
				</td>
				<td>
					${epqVo.name }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						注册地址：
					</div>
				</td>
				<td>
					${epqVo.regadr }
				</td>
			</tr>

			<tr>
				<td>
					<div align="right">
						经营地址：
					</div>
				</td>
				<td>
					${epqVo.bizadr }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						电子邮箱：
					</div>
				</td>
				<td>
					${epqVo.email }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						法人代表：
					</div>
				</td>
				<td>
					${epqVo.delegate }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						法人代表联系电话：
					</div>
				</td>
				<td>
					${epqVo.dlg_Call }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						法人代表证件名称：
					</div>
				</td>
				<td>
					${epqVo.dlg_Cardname }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						法人代表证件号码：
					</div>
				</td>
				<td>
					${epqVo.dlg_Cardcode }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						联系地址：
					</div>
				</td>
				<td>
					${epqVo.contactadr }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						邮编：
					</div>
				</td>
				<td>
					${epqVo.dlg_Postcode }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人：
					</div>
				</td>
				<td>
					${epqVo.proxy }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人电话：
					</div>
				</td>
				<td>
					${epqVo.proxy_Call }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人证件名称：
					</div>
				</td>
				<td>
					${epqVo.proxy_Cardname }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人证件号码：
					</div>
				</td>
				<td>
					${epqVo.proxy_Cardcode }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人联系地址：
					</div>
				</td>
				<td>
					${epqVo.proxy_Contact }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						代理人邮编：
					</div>
				</td>
				<td>
					${epqVo.proxy_Postcode }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						申请日期：
					</div>
				</td>
				<td>
					${epqVo.firstAuditDate }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						初审人：
					</div>
				</td>
				<td>
					${epqVo.firstCensor }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						初审日期：
					</div>
				</td>
				<td>
					${epqVo.firstAuditDate }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						终审人：
					</div>
				</td>
				<td>
					${epqVo.finalCensor }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						终审日期：
					</div>
				</td>
				<td>
					${epqVo.finalAuditDate }
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						当前状态：
					</div>
				</td>
				<td>
					${status }
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:doBack(${signerID });" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返 回</a>
				</td>
			</tr>
		</table>
	</body>
</html>
