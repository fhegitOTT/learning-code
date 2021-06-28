<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>网签合同基本信息</title>
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
	<body style="margin-right: 1px; margin-left: 1px">
		<table width="600" border="0" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td align="center" colspan="2">
					<h3>
						网签合同基本信息
					</h3>
				</td>
			</tr>
			<tr>
				<td align="left" class="css">
					合同编号:${cdVo.contractID }
				</td>
				<td align="right" class="css">
					签定时间:${confirmDateStr }
				</td>
			</tr>
		</table>
		<table align="center" cellpadding="1" border="0">
			<tr>
				<td bgcolor="#000000">
					<table width="600" border="0" align="center" cellpadding="3" cellspacing="1" class="css">
						<tr>
							<td bgcolor="#FFFFFF">
								房屋坐落部位
							</td>
							<td bgcolor="#FFFFFF" colspan="5">
								${cdVo.location }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" width="97">
								建筑面积
							</td>
							<td bgcolor="#FFFFFF" width="104">
								${cdVo.area }
							</td>
							<td bgcolor="#FFFFFF" width="60">
								土地面积
							</td>
							<td bgcolor="#FFFFFF" width="98">
								${cdVo.area }
							</td>
							<td bgcolor="#FFFFFF" width="95">
								房地产总价
							</td>
							<td bgcolor="#FFFFFF" width="*">
								${cdVo.money }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								房屋类型
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${cdVo.landType }
							</td>
							<td bgcolor="#FFFFFF">
								结构
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								房地产权证号
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${cdVo.presellID }
							</td>
							<td bgcolor="#FFFFFF">
								预售许可证号
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${cdVo.presellID }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								甲方
							</td>
							<td bgcolor="#FFFFFF" colspan="3">
								${cdVo.sellerName }
							</td>
							<td bgcolor="#FFFFFF">
								电话
							</td>
							<td bgcolor="#FFFFFF">
								${eqVo.tel }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								证件名称
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								营业执照
							</td>
							<td bgcolor="#FFFFFF">
								号码
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${eqVo.bizregister_Num }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								乙方
							</td>
							<td bgcolor="#FFFFFF" colspan="3">
								${cdVo.buyerName }
							</td>
							<td bgcolor="#FFFFFF">
								电话
							</td>
							<td bgcolor="#FFFFFF">
								${biVo.buyerCall }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								证件名称
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${buyercardname }
							</td>
							<td bgcolor="#FFFFFF">
								号码
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${biVo.buyerCardcode }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								草签日期
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${signDateStr }
							</td>
							<td bgcolor="#FFFFFF">
								撤消日期
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${CancelCensorVO.finalDate }
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								撤消确认人员
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${CancelCensorVO.finalCensor }
							</td>
							<td bgcolor="#FFFFFF">
								提交撤消人员
							</td>
							<td bgcolor="#FFFFFF" colspan="2">
								${CancelCensorVO.firstCensor }
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
