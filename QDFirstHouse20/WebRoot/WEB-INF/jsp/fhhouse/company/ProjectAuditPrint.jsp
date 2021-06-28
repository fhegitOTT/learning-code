
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.ProjectVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.PresellVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.BuildingVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	ProjectVO pvo = (ProjectVO)request.getAttribute("pvo");
	List<PresellVO> presellList = (List)request.getAttribute("presellList");
	List<BuildingVO> buildingList = (List)request.getAttribute("buildingList");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质审核-项目审核打印</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			td {font-size: 14px}
		</style>
		<script type="text/javascript">
			
		</script>
	</head>
	<body onselectstart="return false">
		<table class=css width="600" border="0" cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td width="100%" height="22">
					<div align="center">
						<font color="#000000" size="+2"><strong>青岛市网上房地产合同备案入网资质审核表<br/><br/></strong></font>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table class=css width="90%" style="border-collapse: collapse;" style="border-collapse:collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" border="1" align="center" cellpadding="2" cellspacing="0">
						<tr>
							<td width="111" height="138">
								<p align="center">
									<font color="#000000">项目信息</font>
								</p>
								<p align="center">
									&nbsp;
								</p>
							</td>

							<td height="138" colspan="5" valign="top" width="406">
								<br/>
									<table class=css width="95%" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" border="1" cellspacing="0" cellpadding="0" align="center">
										<tr>
											<td height="21" colspan="3">
												<div align="center">
													<p>
														${pvo.projectName}
													</p>
												</div>
											</td>
										</tr>
										<tr>
											<td height="23" width="32%">
												<div align="right">
													<font color="#000000">项目地址：&nbsp; </font>
												</div>
											</td>
											<td height="23" width="68%">
												<font>${pvo.projectAdr}</font>
											</td>
										</tr>
										<tr>
											<td height="21" width="32%">
												<div align="right">
													<font color="#000000"> 项目名称：&nbsp; </font>
												</div>
											</td>
											<td height="21" width="68%">
												<font>${pvo.projectName}</font>
											</td>
										</tr>
										<tr>
											<td height="16" width="32%">
												<div align="right">
													<font color="#000000">所在区市：&nbsp; </font>
												</div>
											</td>
											<td height="16" width="68%">
												<font><%=pvo.getAttribute("districtid_dict_name") %></font>
											</td>
										</tr>
										<tr>
											<td height="17" width="32%">
												<div align="right">
													<font color="#000000"> &nbsp;&nbsp;土地使用权出让合同编号：&nbsp; </font>
												</div>
											</td>
											<td height="17" width="68%">
												<font>${pvo.lotNum}</font>
											</td>
										</tr>
										<tr>
											<td height="12" width="32%">
												<div align="right">
													甲方信息：
												</div>
											</td>
											<td class="input_right" width="68%">
												<c:choose>
													<c:when test="${companyList !=null}">
														<c:forEach var="comp" items="${companyList}" varStatus="status">
															<p>
																${comp.name}
															</p>
														</c:forEach>
													</c:when>
												</c:choose>
											</td>
										</tr>
									</table>
									<br>
							</td>
						</tr>

						<tr cellpadding="2" cellspacing="0">
							<td width="111" height="120">
								<p align="center">
									<font color="#000000">操作员信息</font>
								</p>
							</td>

							<td width="406" height="120" colspan="5" valign="top">
								<br>
									<table class=css width="95%" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" border="1" cellpadding="2" cellspacing="0" align="center">
										<tr>
											<td width="20%">
												<p align="center">
													<font color="#000000">操作帐号</font>
												</p>
											</td>

											<td width="20%">
												<p align="center">
													<font color="#000000">姓名</font>
												</p>
											</td>

											<td width="20%">
												<p align="center">
													<font color="#000000">证件名称</font>
												</p>
											</td>

											<td width="20%">
												<p align="center">
													<font color="#000000">证件号码</font>
												</p>
											</td>

											<td width="20%">
												<p align="center">
													<font color="#000000">身份证号码</font>
												</p>
											</td>
										</tr>
										<c:choose>
											<c:when test="${signerList !=null}">
												<c:forEach var="item" items="${signerList}" varStatus="status">
													<tr bgcolor="E6F8E3" class="css">
														<td class="input_right" width="20%" align="center">
															<font><c:out value="${item.loginName}" /></font>
														</td>
														<td class="input_right" width="20%" align="center">
															<font><c:out value="${item.name}" /></font>
														</td>
														<td class="input_right" width="20%" align="center">
															<font><c:out value="${item.cardName}" /></font>
														</td>
														<td class="input_right" width="20%" align="center">
															<font><c:out value="${item.cardCode}" /></font>
														</td>
														<td class="input_right" width="20%" align="center">
															<font><c:out value="${item.brokercert}" /></font>
														</td>
													</tr>
												</c:forEach>
											</c:when>
										</c:choose>
									</table>
							</td>
						</tr>

						<tr bordercolordark="#000000" bordercolorlight="#000000">
							<td width="111" height="122">
								<p align="center">
									<font color="#000000">预<br/>售<br/>许<br/>可<br/>证<br/>信<br/>息</font>
								</p>
							</td>

							<td width="406" height="122" colspan="5" valign="top">
								<br>
									<table class=css width="98%" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" border="1" cellpadding="0" cellspacing="0" align="center">
										<tr>
											<td width="35%" height="17">
												<p align="center">
													<font color="#000000">预售许可证（开盘单元）</font>
												</p>
											</td>

											<td width="65%" height="17">
												<p align="center">
													<font color="#000000">相关楼幢</font>
												</p>
											</td>
										</tr>
										<%if(presellList!=null&&presellList.size()>0){ 
               		 										for(PresellVO prevo:presellList){
               		 											if(prevo.getAttribute("start_code")!=null){
               		 									%>
										<tr>
											<td class="input_right" width="35%"><%=prevo.getPresell_Desc() %>(<%=prevo.getAttribute("start_code") %>)</td>
											<td class="input_right" width="65%">
												<%if(buildingList!=null&&buildingList.size()>0){
                  									for(BuildingVO bVO:buildingList){
                  										if(prevo.getAttribute("start_id").equals(bVO.getAttribute("start_id"))){
                  							%>
												<%=bVO.getBuilding_Name() %><br />
												<%
                  										}
                  									}
                  								}
                  							%>
											</td>
										</tr>
										<%
                  							}
               		 						}
											}
               		 						%>
									</table>
							</td>
						</tr>

						<tr>
							<td width="111" height="143">
								<p align="center">
									<font color="#000000">初<br/>审<br/>意<br/>见</font>
								</p>
							</td>

							<td width="406" height="143" colspan="5">
									<p>${pvo.firstMark}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审人：${pvo.firstCensor}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审日期：<%=new DateUtil().parseDateTime3(String.valueOf(pvo.getFirstAuditDate())) %></p>
							</td>
						</tr>

						<tr>
							<td width="111" height="130">
								<p align="center">
									<font color="#000000">复<br/>审<br/>意<br/>见</font>
								</p>
							</td>

							<td width="406" height="130" colspan="5">
								<p>${pvo.finalMark}</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终审人：${pvo.finalCensor}</p>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审日期：
								<%String date1 = new DateUtil().parseDateTime3(String.valueOf(pvo.getFinalAuditDate()));
									if(date1!=null&&date1!=""){
								%>
								<%=date1 %>
								<%} %>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
