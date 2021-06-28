
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.ProjectVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.PresellVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.BuildingVO"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	ProjectVO pvo = new ProjectVO();
	if(request.getAttribute("pvo")!=null){
		pvo = (ProjectVO)request.getAttribute("pvo");
	}
	List<SignerVO> signerList = (List<SignerVO>)request.getAttribute("signerList");
	List<PresellVO> presellList = (List<PresellVO>)request.getAttribute("presellList");
	List<BuildingVO> buildingList = (List<BuildingVO>)request.getAttribute("buildingList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质审核-项目审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			* {color: #555;}
			tr {background-color: white;}
			td {font-size:14px}
		</style>
		<script type="text/javascript">
			function doBack(){
				window.location="<%=path%>/inner/companymanage/gotoCompanyPublishQuery.action";
			}
		</script>
	</head>
	<body id="body-layout" >
		<div align="center">
			<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/gotoProjectPublishPass.action ">
			<input type="hidden" name="id" id="id" value="${pvo.project_ID}" />
			<input type="hidden" name="status" id="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status")) %>" />
			<br />
			<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
				<tr>
					<td colspan="2">
						<h1 align="center">项目审核认证表</h1>
					</td>
				</tr>
					<tr>
						<td>
							<br/>
								<br/>
									<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
										<tr>
											<td class="input_left " bgcolor="#5BADFF" width="15%">
												<p align="center">
													<font style="color: white">项<br/>目<br/>信<br/>息</font>
												</p>
											</td>
											<td class="input_right" width="85%">
												<br/>
													<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
														<tr>
															<td colspan="2">
																<div align="center">
																	<p>${pvo.projectName}</p>
																</div>
															</td>
														</tr>
														<tr>
															<td class="input_left " bgcolor="#5BADFF" width="32%">
																<div align="right">
																	<font style="color: white">项目地址： </font>
																</div>
															</td>
															<td class="input_right" width="68%">
																<font>${pvo.projectAdr}</font>
															</td>
														</tr>
														<tr>
															<td class="input_left " bgcolor="#5BADFF" width="32%">
																<div align="right">
																	<font style="color: white"> 项目名称： </font>
																</div>
															</td>
															<td class="input_right" width="68%">
																<font>${pvo.projectName}</font>
															</td>
														</tr>
														<tr>
															<td class="input_left " bgcolor="#5BADFF" width="32%">
																<div align="right">
																	<font style="color: white">所在区市： </font>
																</div>
															</td>
															<td class="input_right" width="68%">
																<font><%=pvo.getAttribute("districtid_dict_name") %></font>
															</td>
														</tr>
														<tr>
															<td class="input_left " bgcolor="#5BADFF" width="32%">
																<div align="right">
																	<font style="color: white"> &nbsp;&nbsp;土地使用权出让合同编号：</font>
																</div>
															</td>
															<td class="input_right" width="68%">
																<font>${pvo.lotNum}</font>
															</td>
														</tr>
														<tr>
															<td class="input_left " bgcolor="#5BADFF" width="32%">
																<div align="right">
																	<font style="color: white">甲方信息：</font>
																</div>
															</td>
															<td class="input_right" width="68%">
																<c:choose>
																	<c:when test="${companyList !=null}">
																		<c:forEach var="comp" items="${companyList}" varStatus="status">
																			<p>${comp.name}</p>
																		</c:forEach>
																	</c:when>
																</c:choose>
															</td>
														</tr>
													</table>
													<br/>
											</td>
										</tr>
										<tr>
								<td class="input_left" bgcolor="#5BADFF" align="center">
									<font style="color: white">操<br />作<br />员<br />资<br />料</font>
								</td>
								<td class="input_right" valign="top">
									<br />
									<table class="input_table" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
                						<tr> 
                  							<td class="input_left"  width="20%" bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">操作帐号</font></p>
                 	 						</td>
                  							<td class="input_left"  width="20%" bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">姓名</font></p>
                  							</td>
                  							<td class="input_left"  width="20%" bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">证件名称</font></p> 
                  							</td>
                  							<td class="input_left"  width="20%" bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">证件号码</font></p>
                  							</td>
                  							<td class="input_left"  width="20%" bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">身份证号码</font></p>
                  							</td>
                						</tr>
             							<c:choose>
	           								<c:when test="${signerList !=null}">
              									<c:forEach var="item" items="${signerList}" varStatus="status">
                									<tr bgcolor="E6F8E3" class="css"> 
                  										<td class="input_right"  width="20%" align="center"><font><c:out value="${item.loginName}"/></font></td>
                  										<td class="input_right"  width="20%" align="center"><font><c:out value="${item.name}"/></font></td>
                  										<td class="input_right"  width="20%" align="center"><font><c:out value="${item.cardName}"/></font></td>
                  										<td class="input_right"  width="20%" align="center" ><font><c:out value="${item.cardCode}"/></font></td>
                  										<td class="input_right"  width="20%" align="center"><font><c:out value="${item.brokercert}"/></font></td>
                									</tr>
               									</c:forEach>
               								</c:when>
               							</c:choose>
              						</table>
								</td>
							</tr>
										<tr>
											<td class="input_left" bgcolor="#5BADFF" align="center">
												<p align="center">
													<font  style="color: white">预<br/>售<br/>许<br/>可<br/>证<br/>信<br/>息</font>
												</p>
											</td>
											<td class="input_right" valign="top">
												<br/>
													<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
														<tr> 
                  							<td class="input_left"  width="35%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">预售许可证（开盘单元）</font></p> 
                 	 						</td>
                  							<td class="input_left"  width="65%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">相关楼幢</font></p> 
                  							</td>
               		 					</tr>
														<%if(presellList!=null&&presellList.size()>0){ 
               		 										for(PresellVO prevo:presellList){
               		 											if(prevo.getAttribute("start_code")!=null){
               		 									%>
               		 						<tr>
                  								<td class="input_right"  width="35%" ><%=prevo.getPresell_Desc() %>(<%=prevo.getAttribute("start_code") %>)</td>
                  								<td class="input_right"  width="65%" >
                  							<%if(buildingList!=null&&buildingList.size()>0){
                  									for(BuildingVO bVO:buildingList){
                  										if(prevo.getAttribute("start_id").equals(bVO.getAttribute("start_id"))){
                  							%>
                              								<%=bVO.getBuilding_Name() %><br/>
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
								<td class="input_left" bgcolor="#5BADFF" align="center">
									<font style="color: white">初<br />审<br />意<br />见</font>
								</td>
								<td class="input_right" height="197">
									<p>${pvo.firstMark}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审人：${pvo.firstCensor}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审日期：
										<%String date1 = String.valueOf(pvo.getFirstAuditDate());
										if(date1.length()==8){
										%>
										<%=DateUtil.parseDateTime3(date1) %>
										<%} %>
									</p>
								</td>
							</tr>
								<tr>
								<td class="input_left" bgcolor="#5BADFF" align="center">
									<font style="color: white">复<br />审<br />意<br />见</font>
								</td>
								<td class="input_right" height="197">
									<p>${pvo.finalMark}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审人：${pvo.finalCensor}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审日期：
										<%String date2 = String.valueOf(pvo.getFinalAuditDate());
										if(date2.length()==8){
										%>
										<%=DateUtil.parseDateTime3(date2) %>
										<%} %>
									</p>
								</td>
							</tr>
							<tr> 
								<td class="input_left" bgcolor="#5BADFF" align="center">
									<font style="color: white">信息发布</font>
								</td>
								<td class="input_right">
									<label><input name="option" type="radio" value="1" />同意 </label> 
									<label><input name="option" type="radio" value="2" checked/>不同意 </label>			 
								</td>
							</tr>
						</table>
						<br />
						<br />
					</td>
				</tr>
			</table>
			<p align="center">
				<a href="javascript:document.frmInfo.submit()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width: 80px">确定</a>
				<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width: 80px">返回</a>
				<br />
			</p>
			</form>
		</div>
	</body>
</html>
