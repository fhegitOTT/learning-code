
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.ProjectVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) request.getAttribute("eqvo");
	List<ProjectVO> projectList = (List<ProjectVO>) request.getAttribute("projectList");
	List<StartUnitVO> startunitList = (List<StartUnitVO>) request.getAttribute("startunitList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质审核-企业审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<style type=text/css>
			td {font-size: 14px}
		</style>

	</head>
	<body onselectstart="return false">
		<div align=center>
			<table width="658" border="0" cellpadding="2" cellspacing="0" align="center">
				<tr height="25">
					<td width="100%" height="22">
						<div align="center">
							<font color="#000000" size="+2">
								<strong>青岛市网上房地产合同备案入网资质审核表<br /><br /> </strong>
							</font>
						</div>
					</td>
				</tr>
				<tr height="25">
					<td>
						<table width="95%" height="800" border="1" align="center" cellpadding="2" cellspacing="0" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
							<tr height="25">
								<td rowspan="2" height="51" width="21">
								<!--<div align="right">
										<font size="3" color="#000000">企业类型：</font>
									</div>
									<div align="left"><%=eqvo.getAttribute("type_dict_name") %></div>-->
								</td>
								<td width="101" height="25" align=right>
									<font size="3" color="#000000">
										<span id="LBL_COMP_CODE">企业全国唯一编码</span>
									</font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.comp_Code} </font>
								</td>
								<td align="right" width="82">
									<font size="3" color="#000000"><span
										id="LBL_LEGALMANCODE">法人代码</span>
									</font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.legalManCode} </font>
								</td>
							</tr>
							<tr height="25">
								<td height="25" width="101" align=right>
									<font size="3" color="#000000">
										<span id="LBL_NAME">企业名称</span>
									</font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.name} </font>
								</td>
								<td width="82" align=right>
									<div align="right">
										<font size="3" color="#000000">
											<span id="LBL_PROVINCECODE">所在省市</span>
										</font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"><%=eqvo.getAttribute("provincecode_dict_name")%></font>
								</td>
							</tr>
							<tr height="25">
								<td height="25" rowspan="8" width="21" align=center>
									<font size="3" color="#000000">营业执照信息</font>
								</td>
								<td height="25" width="101" align=right>
									<font size="3" color="#000000">
										<span id="LBL_BIZREGISTER_NUM">法人营业执<br />照注册号</span>
									</font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.bizregister_Num}</font><font size="3" color="#000000"> </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span
											id="LBL_REGTYPE_NUM">登记注册类型</span>
										</font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> <%=eqvo.getAttribute("regtype_num_dict_name")%></font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_REGADR">营业执照注册地址</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.regadr} </font><font size="3" color="#000000"> </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_DISTRICT">所在区市</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> <%=eqvo.getAttribute("district_dict_name")%></font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000">
										<span id="LBL_BIZREG_DATE">工商注册日</span>
									</font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.bizreg_Date2} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000">
											<span id="LBL_BIZEND_DATE">营业执照到期日</span>
										</font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.bizend_Date2} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_DELEGATE">法人代表</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.delegate} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_DLG_CALL">联系电话</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.dlg_Call} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000">
										<span id="LBL_DLG_CARDNAME">身份证件名称</span>
									</font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.dlg_Cardname} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000">
											<span id="LBL_DLG_CARDCODE">身份证件号码</span>
										</font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.dlg_Cardcode} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_CONTACTADR">联系地址</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.contactadr} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_DLG_POSTCODE">邮政编码</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.dlg_Postcode} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" bordercolor="#000000" width="101">
									<font size="3" color="#000000"><span id="LBL_MANAGER">总经理</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.manager} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_REGISTERMONEY">注册资本</span></font>
									</div>
								</td>
								<td width="219" bordercolor="#000000">
									<font size="3" color="#000000"> <%=new BigDecimal(eqvo.getRegisterMoney()).toPlainString()%></font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101" bordercolor="#000000">
									<font size="3" color="#000000"><span id="LBL_TOTALMONEY">总资产</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> <%=new BigDecimal(eqvo.getTotalMoney()).toPlainString()%></font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_PUREMONEY">净资产</span></font>
									</div>
								</td>
								<td width="219" bordercolor="#000000">
									<font size="3" color="#000000"> <%=new BigDecimal(eqvo.getPureMoney()).toPlainString()%></font>
								</td>
							</tr>
							<tr height="25">
								<td rowspan="6" width="21" align=center>
									<font size="3" color="#000000">经营处所及代理人信息</font>
								</td>
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_BIZADR">经营地址（办公地址）</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.bizadr} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_BIZDISTRICT">所在区市</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> <%=eqvo.getAttribute("bizdistrict_dict_name")%></font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" height="25" width="101">
									<font size="3" color="#000000"><span id="LBL_TEL">联系电话</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.tel} </font>
								</td>
								<td align="right" width="82" align="right">
									<font size="3" color="#000000"><span id="LBL_POST">邮政编码</span></font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.post} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" height="25" width="101">
									<font size="3" color="#000000"><span id="LBL_FAX">传真</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.fax} </font>
								</td>
								<td align="right" width="82" align="right">
									<font size="3" color="#000000"><span id="LBL_EMAIL">电子信箱</span></font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.email} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" height="25" width="101">
									<font size="3" color="#000000"><span id="LBL_PROXY">代理人</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.proxy} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_PROXY_CALL">联系电话</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.proxy_Call} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_PROXY_CARDNAME">证件名称</span></font>
									</div>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.proxy_Cardname} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_PROXY_CARDCODE">证件号码</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.proxy_Cardcode} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" height="25" width="101">
									<font size="3" color="#000000"><span id="LBL_PROXY_CONTACT">联系地址</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.proxy_Contact} </font>
								</td>
								<td align="right" height="25" width="82" align=right>
									<font size="3" color="#000000"><span id="LBL_PROXY_POSTCODE">邮政编码</span></font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.proxy_Postcode} </font>
								</td>
							</tr>
							<tr height="25">
								<td height="77" rowspan="3" width="21" align=center>
									<font size="3" color="#000000">开发资质信息</font>
								</td>
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_APTITUDELEVEL">资质等级</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> <%=eqvo.getAttribute("aptitudelevel_dict_name")%></font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_APTITUDENUM">资质证书发证编号</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.aptitudeNum} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" height="25" width="101">
									<font size="3" color="#000000"><span id="LBL_PASSDATE">资质证书发证日期</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.passDate2} </font>
								</td>
								<td align="right" width="82">
									<font size="3" color="#000000"><span id="LBL_PASSBIZDATE">批准从事房地产开发经营日期</span></font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.passbizDate2} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_EFFECTSTARTDATE">资质证书有效<br />起始日期</span> </font>
									</div>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.effectstartDate2}</font>
								</td>
								<td align="right" height="25" width="82">
									<font size="3" color="#000000"><span id="LBL_EFFECTENDDATE">资质证书有效<br />终止日期</span></font>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.effectendDate2}</font>
								</td>
							</tr>
							<tr height="25">
								<td height="25" bordercolor="#000000" rowspan="2" width="21" align=center>
									<font size="3" color="#000000">人员信息</font>
								</td>
								<td align="right" height="25" bordercolor="#000000" width="101">
									<font size="3" color="#000000"><span id="LBL_CURNUM">在册人员总数</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.curnum} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_HIGHNUM">高级职称人数</span></font>
									</div>
								</td>
								<td bordercolor="#000000" width="219">
									<font size="3" color="#000000"> ${eqvo.highnum} </font>
								</td>
							</tr>
							<tr height="25">
								<td align="right" width="101">
									<font size="3" color="#000000"><span id="LBL_MIDNUM">中级职称人数</span></font>
								</td>
								<td width="210">
									<font size="3" color="#000000"> ${eqvo.midnum} </font>
								</td>
								<td align="right" width="82">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_LOWNUM">初级职称人数</span></font>
									</div>
								</td>
								<td width="219">
									<font size="3" color="#000000"> ${eqvo.lownum} </font>
								</td>
							</tr>
							<tr height="25">
								<td colspan="2">
									<div align="right">
										<font size="3" color="#000000"><span id="LBL_WORKSCOPE">经营范围</span></font>
									</div>
								</td>
								<td height="80" colspan="3">
									<font size="3" color="#000000"> ${eqvo.workScope} </font>
								</td>
							</tr>
						</table>
						<br />

						<table width="95%" border="1" align="center" cellpadding="2" cellspacing="0" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
							<tr height="25">
								<td width="128" height="160">
									<p align="center"><font size="3" color="#000000">操</font></p>
									<p align="center"><font size="3" color="#000000">作</font></p>
									<p align="center"><font size="3" color="#000000">员</font></p>
									<p align="center"><font size="3" color="#000000">资</font></p>
									<p align="center"><font size="3" color="#000000">料 </font></p>
								</td>

								<td height="160" colspan="5" valign="top">
									<br />
									<table width="98%" border="1" cellpadding="0" cellspacing="0" align="center" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000">
										<tr height="25">
											<td width="20%"><p align="center">操作帐号</p></td>
											<td width="20%"><p align="center">姓名</p></td>
											<td width="20%"><p align="center">证件名称</p></td>
											<td width="20%"><p align="center">证件号码</p></td>
											<td width="20%"><p align="center">身份证号码</p></td>
										</tr>
										<c:choose>
											<c:when test="${signerList !=null}">
												<c:forEach var="item" items="${signerList}" varStatus="status">
													<tr>
														<td width="20%" align="center">${item.loginName}</td>
														<td width="20%" align="center">${item.name}</td>
														<td width="20%" align="center">${item.cardName}</td>
														<td width="20%" align="center">${item.cardCode}</td>
														<td width="20%" align="center">${item.brokercert}</td>
													</tr>
												</c:forEach>
											</c:when>
										</c:choose>
									</table>
								</td>
							</tr>

							<tr height="25">
								<td width="128" height="160" class="css">
									<p align="center"><font size="3" color="#000000">企</font></p>
									<p align="center"><font size="3" color="#000000">业</font></p>
									<p align="center"><font size="3" color="#000000">项</font></p>
									<p align="center"><font size="3" color="#000000">目</font></p>
									<p align="center"><font size="3" color="#000000">信</font></p>
									<p align="center"><font size="3" color="#000000">息</font></p>
									<p align="center"><font size="3" color="#000000">表</font></p>
								</td>
								<td height="160" colspan="5" valign="top">
									<br />
									<table width="98%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" align="center">
										<tr height="25">
											<td width="35%"><p align="center">项目名称</p></td>
											<td width="65%"><p align="center">预售许可证列表（开盘单元）</p></td>
										</tr>
										<%if(projectList!=null&&projectList.size()>0){ 
               		 							for(ProjectVO pvo:projectList){
               		 								
               		 						%>
										<tr>
											<td width="35%" align="center"><%=pvo.getProjectName() %></td>
											<td width="65%" align="center">
											<%
                  									if(pvo.getAttribute("start_id")!=null){
                  								%>
                  									<%=pvo.getAttribute("psdesc") %>
                  								<%		
                  									}
                  								%>
											</td>
										</tr>
										<%
               		 							}
               		 						}
               		 						%>
									</table>
								</td>
							</tr>
							<tr height="25">
								<td width="128" height="160" class="css">
									<p align="center"><font size="3" color="#000000">预售许可证信</font></p>
									<p align="center"><font size="3" color="#000000">息及开盘单元</font></p>
								</td>

								<td height="160" colspan="5" valign="top">
									<br />
									<table width="98%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;" BORDERCOLORLIGHT="#000000" BORDERCOLORDARK="#000000" align="center">
										<tr height="25">
											<td width="35%" height="17">
												<p align="center"><font size="3" color="#000000">预售许可证（开盘单元）</font></p>
											</td>
											<td width="65%" height="17">
												<p align="center"><font size="3" color="#000000">相关楼幢</font></p>
											</td>
										</tr>
										<%if(projectList!=null&&projectList.size()>0){ 
               		 							for(ProjectVO pvo:projectList){
               		 							if(pvo.getAttribute("start_id")!=null){
               		 						%>
										<tr>
											<td width="35%"><%=pvo.getAttribute("psdesc") %></td>
											<td width="65%">
												<%if(startunitList!=null&&startunitList.size()>0){
                  									for(StartUnitVO stVO:startunitList){
                  										if(Long.parseLong(pvo.getAttribute("start_id").toString())==(Long)stVO.getStart_ID()){
                  							%>
												<%=stVO.getAttribute("building_name") %><br />
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

							<tr height="25">
								<td width="128" height="160" class="css">
									<p align="center"><font size="3" color="#000000">初</font></p>
									<p align="center"><font size="3" color="#000000">审</font></p>
									<p align="center"><font size="3" color="#000000">意</font></p>
									<p align="center"><font size="3" color="#000000">见</font></p>
								</td>
								<%
									List<EnterpriseQualifyVO> list=(List)request.getAttribute("complist");
										for(EnterpriseQualifyVO eco:list){
									 %>
								<td height="160" colspan="5" class="css">
									<p>${eqvo.firstMark}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审人：<%=eco.getFirstCensor() %></p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审日期：<%=new DateUtil().parseDateTime3(String.valueOf(eqvo.getFirstAuditDate())) %></p>
								</td>
							</tr>
							<tr height="25">
								<td width="128" height="160" class="css">
									<p align="center"><font size="3" color="#000000">复</font></p>
									<p align="center"><font size="3" color="#000000">审</font></p>
									<p align="center"><font size="3" color="#000000">意</font></p>
									<p align="center"><font size="3" color="#000000">见</font></p>
								</td>

								<td name=finalMark id=finalMark height="160" colspan="5" class="css">
										<p>${eqvo.finalMark}</p>
										<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审人：<%=eco.getFinalCensor()==null?"":eco.getFinalCensor() %></p>
										<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审日期：
										<%String date1 = new DateUtil().parseDateTime3(String.valueOf(eqvo.getFinalAuditDate()));
										if(date1!=null&&date1!=""){
										%>
										<%=date1 %>
										<%} %>
										</p>
								</td>
								<%
									}
								 %>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
