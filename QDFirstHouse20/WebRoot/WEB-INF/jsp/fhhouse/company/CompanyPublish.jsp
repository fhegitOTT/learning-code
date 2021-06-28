
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page
	import="com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.ProjectVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) request.getAttribute("eqvo");
	List<ProjectVO> projectList = (List<ProjectVO>)request.getAttribute("projectList");
	List<StartUnitVO> startunitList = (List<StartUnitVO>)request.getAttribute("startunitList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资质审核-企业审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			* {color: #555;}
			body {font-size: 16px;margin: 0;font: Arial, Helvetica, "宋体", sans-serif;}
			.toplogo {width: auto;line-height: 40px;margin-left: 10px;display: inline;float: left;overflow-y: hidden;}
			/*最底部*/
			.bottom {overflow-y: hidden}
			.bottom_ban {width: auto;line-height: 40px;margin-left: 18px;display: inline;float: left;overflow-y: hidden;}
			.bottom_shi {width: auto;line-height: 40px;margin-right: 20px;display: inline;float: right;overflow-y: hidden;}
			tr {background-color: white;}
			td {font-size:14px}
		</style>
		<script type="text/javascript">
			function doBack(){
				window.location="<%=path%>/inner/companymanage/gotoCompanyPublishQuery.action";
			}
		</script>
	</head>
	<body id="body-layout">
		<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/gotoCompanyPublishPass.action">
			<input type="hidden" name="id" id="id" value="${eqvo.comp_ID}" />
			<input type="hidden" name= "status" id="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("status")) %>"/>
			<br />
			<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
				<tr>
					<td colspan="5">
						<h1 align="center">
							青岛市网上房地产申报资格认证
						</h1>
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							<font color="#003300"> <input type="radio" name="type" value="1" ${eqvo.type== 1? 'checked' :''} disabled />
								<label>
									房地产开发企业
								</label>
							</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#003300"> <input type="radio" name="type" value="2" ${eqvo.type== 2? 'checked' :''}  disabled />
								<label>
									房地产经纪组织
								</label> </font>
						</div>
						<br />
						<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
							<tr>
								<td class="input_left " bgcolor="#5BADFF" rowspan="2" width="5%"></td>
								<td class="input_left " bgcolor="#5BADFF" width="15%">
									<font style="color: white">企业全国唯一编码</font>
								</td>
								<td class="input_right" width="32%">
									${eqvo.comp_Code}
								</td>
								<td class="input_left  " bgcolor="#5BADFF" width="15%">
									<font style="color: white">法人代码</font>
								</td>
								<td class="input_right" width="33%">
									${eqvo.legalManCode}
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">企业名称</font>
								</td>
								<td class="input_right">
									${eqvo.name}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">所在省市</font>
								</td>
								<td class="input_right">
									<%=eqvo.getAttribute("provincecode_dict_name")%>
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" rowspan="8" align="center">
									<font style="color: white">营<br />业<br />执<br />照<br />信<br />息</font>
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">法人营业执照注册号</font>
								</td>
								<td class="input_right">
									${eqvo.bizregister_Num}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">登记注册类型</font>
								</td>
								<td class="input_right">
									<%=eqvo.getAttribute("regtype_num_dict_name")%>
								</td>
							</tr>
							<tr>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">营业执照注册地址</font>
								</td>
								<td class="input_right">
									${eqvo.regadr}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">所在区市</font>
								</td>
								<td class="input_right">
									<%=eqvo.getAttribute("district_dict_name")%>
								</td>
							</tr>
							<tr>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">工商注册日</font>
								</td>
								<td class="input_right">
									${eqvo.bizreg_Date2}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">营业执照到期日</font>
								</td>
								<td class="input_right">
									${eqvo.bizend_Date2}
								</td>
							</tr>
							<tr>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">法人代表</font>
								</td>
								<td class="input_right">
									${eqvo.delegate}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">联系电话</font>
								</td>
								<td class="input_right">
									${eqvo.dlg_Call}
								</td>
							</tr>
							<tr>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">身份证件名称</font>
								</td>
								<td class="input_right">
									${eqvo.dlg_Cardname}
								</td>
								<td class="input_left  " bgcolor="#5BADFF">
									<font style="color: white">身份证件号码</font>
								</td>
								<td class="input_right">
									${eqvo.dlg_Cardcode}
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">联系地址</font>
								</td>
								<td class="input_right">
									${eqvo.contactadr}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">邮政编码</font>
								</td>
								<td class="input_right">
									${eqvo.dlg_Postcode}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">总经理</font>
								</td>
								<td class="input_right">
									${eqvo.manager}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">注册资本(元)</font>
								</td>
								<td class="input_right">
									<%=new BigDecimal(eqvo.getRegisterMoney()).toPlainString()%>
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">总资产(元)</font>
								</td>
								<td class="input_right">
									<%=new BigDecimal(eqvo.getTotalMoney()).toPlainString()%>
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">净资产(元)</font>
								</td>
								<td class="input_right">
									<%=new BigDecimal(eqvo.getPureMoney()).toPlainString()%>
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" rowspan="6" align="center">
									<font style="color: white">经<br />营<br />处<br />所<br />及<br />代<br />理<br />人<br />信<br />息</font>
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">经营地址(办公地址)</font>
								</td>
								<td class="input_right">
									${eqvo.bizadr}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">所在区市</font>
								</td>
								<td class="input_right">
									<%=eqvo.getAttribute("bizdistrict_dict_name")%>
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">联系电话</font>
								</td>
								<td class="input_right">
									${eqvo.tel}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">邮政编码</font>
								</td>
								<td class="input_right">
									${eqvo.post}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">传真</font>
								</td>
								<td class="input_right">
									${eqvo.fax}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">电子信箱</font>
								</td>
								<td class="input_right">
									${eqvo.email}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">代理人</font>
								</td>
								<td class="input_right">
									${eqvo.proxy}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">联系电话</font>
								</td>
								<td class="input_right">
									${eqvo.proxy_Call}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">证件名称</font>
								</td>
								<td class="input_right">
									${eqvo.proxy_Cardname}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">证件号码</font>
								</td>
								<td class="input_right">
									${eqvo.proxy_Cardcode}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">联系地址</font>
								</td>
								<td class="input_right">
									${eqvo.proxy_Contact}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">邮政编码</font>
								</td>
								<td class="input_right">
									${eqvo.proxy_Postcode}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" rowspan="3" align="center">
									<font style="color: white">开<br />发<br />资<br />质<br />信<br />息</font>
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">资质等级</font>
								</td>
								<td class="input_right">
									<%=eqvo.getAttribute("aptitudelevel_dict_name")%>
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">资质证书发证编号</font>
								</td>
								<td class="input_right">
									${eqvo.aptitudeNum}
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">资质证书发证日期</font>
								</td>
								<td class="input_right">
									${eqvo.passDate2}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">批准从事房地产开发经营日期</font>
								</td>
								<td class="input_right">
									${eqvo.passbizDate2}
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">资质证书有效起始日期</font>
								</td>
								<td class="input_right">
									${eqvo.effectstartDate2}
								</td>
								<td class="input_left " bgcolor="#5BADFF">
									<font style="color: white">资质证书有效终止日期</font>
								</td>
								<td class="input_right">
									${eqvo.effectendDate2}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" rowspan="2"
									align="center">
									<font style="color: white">人<br />员<br />信<br />息</font>
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">在册人员总数</font>
								</td>
								<td class="input_right">
									${eqvo.curnum}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">高级职称人数</font>
								</td>
								<td class="input_right">
									${eqvo.highnum}
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">中级职称人数</font>
								</td>
								<td class="input_right">
									${eqvo.midnum}
								</td>
								<td class="input_left" bgcolor="#5BADFF">
									<font style="color: white">初级职称人数</font>
								</td>
								<td class="input_right">
									${eqvo.lownum}
								</td>
							</tr>
							<tr>
								<td class="input_left " bgcolor="#5BADFF" align="center"
									colspan="2">
									<font style="color: white">经营范围：</font>
								</td>
								<td class="input_right" colspan="3">
									${eqvo.workScope}
								</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" height="20px" colspan="5" style="border-left: 0px; border-right: 0px"></td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" align="center" colspan="2">
									<font style="color: white">操<br />作<br />员<br />资<br />料</font>
								</td>
								<td class="input_left" colspan="3" valign="top">
									<br />
									<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
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
								<td class="input_left" bgcolor="#5BADFF" align="center" colspan="2">
									<font style="color: white">企<br />业<br />项<br />目<br />信<br />息<br />表</font>
								</td>
								<td class="" colspan="3" valign="top">
									<br />
									<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
                						<tr> 
                  							<td class="input_left"  width="35%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">项目名称</font></p> 
                 	 						</td>
                  							<td class="input_left"  width="65%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">预售许可证列表（开盘单元）</font></p> 
                  							</td>
               		 					</tr>
               		 					
               		 						<%if(projectList!=null&&projectList.size()>0){ 
               		 							for(ProjectVO pvo:projectList){
               		 						%>
               		 						<tr>
               		 							<td class="input_right"  width="35%" align="center"><%=pvo.getProjectName() %></td>
                  								<td class="input_right"  width="65%" align="center"><%=pvo.getAttribute("psdesc") %></td>
                  							</tr>
               		 						<%
               		 							}
               		 						}
               		 						%>
               		 					
              						</table>
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" height="185" colspan="2">
									<p align="center">
										<font style="color: white">预售许可证信</font>
									</p>
									<p align="center">
										<font style="color: white">息及开盘单元</font>
									</p>
								</td>
								<td class="" colspan="3" valign="top">
									<br />
									<table class="input_table" cellpadding="5" cellspacing="1px" style="width: 95%; background-color: #7DD5FF" align="center">
                						<tr> 
                  							<td class="input_left"  width="35%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">预售许可证（开盘单元）</font></p> 
                 	 						</td>
                  							<td class="input_left"  width="65%"  bgcolor="#5BADFF"> 
                    							<p align="center"><font style="color: white">相关楼幢</font></p> 
                  							</td>
               		 					</tr>
               		 						<%if(projectList!=null&&projectList.size()>0){ 
               		 							for(ProjectVO pvo:projectList){
               		 						%>
               		 						<tr>
                  								<td class="input_right"  width="35%" ><%=pvo.getAttribute("psdesc") %></td>
                  								<td class="input_right"  width="65%" >
                  							<%if(startunitList!=null&&startunitList.size()>0){
                  									for(StartUnitVO stVO:startunitList){
                  										if(pvo.getAttribute("start_id")!=null && Long.parseLong(pvo.getAttribute("start_id").toString())==stVO.getStart_ID()){
                  							%>
                              								<%=stVO.getAttribute("building_name") %><br/>
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
               		 						%>
               		 					
              						</table>
								</td>
							</tr>
							<tr>
								<td class="input_left" bgcolor="#5BADFF" align="center" colspan="2">
									<font style="color: white">初<br />审<br />意<br />见</font>
								</td>
								<%
									List<EnterpriseQualifyVO> list=(List)request.getAttribute("complist");
										for(EnterpriseQualifyVO eco:list){
									 %>
								<td class="input_right" height="197" colspan="3">
									<p>
										${eqvo.firstMark}
									</p>
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审人：<%=eco.getFirstCensor() %>
									</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;初审日期：<%=new DateUtil().parseDateTime3(String.valueOf(eqvo.getFirstAuditDate())) %></p>
								</td>
							</tr>
								<tr>
								<td class="input_left" bgcolor="#5BADFF" align="center" colspan="2">
									<font style="color: white">复<br />审<br />意<br />见</font>
								</td>
								<td class="input_right" colspan="3">
									<p>${eqvo.finalMark}</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审人：<%=eco.getFinalCensor() %></p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复审日期：
										<%String date1 = new DateUtil().parseDateTime3(String.valueOf(eqvo.getFinalAuditDate()));
										if(date1!=null&&date1!=""){
										%>
										<%=date1 %>
										<%} %>
									</p>
								</td>
							</tr>
							<%
								}
							 %>
							<tr> 
								<td class="input_left" bgcolor="#5BADFF" align="center" colspan="2">
									<font style="color: white">信息发布</font>
								</td>
								<td class="input_right" colspan="3">&nbsp;&nbsp;&nbsp;
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
	</body>
</html>
