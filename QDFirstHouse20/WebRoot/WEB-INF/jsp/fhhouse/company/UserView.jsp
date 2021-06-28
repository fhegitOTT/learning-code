
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	List<SignerVO> signerList = (List) request.getAttribute("signerList");
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
			td {font-size: 14px}
			#newpage{page-break-after: always}
		</style>
		<script type="text/javascript">
			function doBack(){
		 		window.location="<%=path%>/inner/companymanage/gotoCompanyAuditQuery.action";
			}
			function doPrint(){
				bdhtml=window.document.body.innerHTML;
		        sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符
		        eprnstr="<!--endprint-->"; //结束打印标识字符串
		        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容
		        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
		        window.document.body.innerHTML=prnhtml; //把需要打印的指定内容赋给body.innerHTML
		        window.print(); //调用浏览器的打印功能打印指定区域
		        window.document.body.innerHTML=bdhtml; // 最后还原页面
			}
		</script>
	</head>
	<body  onselectstart="return false">
		<form name="AuditingForm" method="post" action="" onsubmit="return false;">
			<%
				if(signerList != null && signerList.size() > 0){
			%>
			<table width="600"  border="1" cellspacing="0px" align="center" cellpadding="0" cellspacing="1">
			<!--startprint-->
			<%
					int i = 0;
					for(SignerVO sVo : signerList){
						i++;
			%>
				<tr>
					<td height="22">
						<div align="center">
							<font><strong>网上签约人个人帐号信息</strong></font>
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<br />
						<p align="center"> 企业名称：<%=sVo.getAttribute("compName")%></p>
						<p align="center"> 法人代码：<%=sVo.getAttribute("legalManCode")%></p>
						<table width="90%" height="30" border="1" cellspacing="0px" align="center" cellpadding="2" cellspacing="1">
							<tr>
								<th width="25%">
									<p align="center">姓名</p>
								</th>
								<th width="25%">
									<p align="center">证件号码</p>
								</th>
								<th width="25%">
									<p align="center">操作帐号</p>
								</th>
								<th>
									<p align="center">操作密码</p>
								</th>
							</tr>
							<tr>
								<td width="25%">
									<p align="center"><%=sVo.getName()%></p>
								</td>
								<td width="25%" height="30">
									<p align="center"><%=sVo.getBrokercert()%></p>
								</td>
								<td width="25%">
									<p align="center"><%=sVo.getLoginName()%></p>
								</td>
								<td>
									<p align="center"><%=sVo.getPwd()%></p>
								</td>
							</tr>
						</table>
						<p align="center">
							<font color="2B7619"> 注：首次登录必须修改原始密码</font>
						</p>
						<br />
					</td>
				</tr>
				<% if(i%3==0){ %>
				<tr><td><p id="newpage"></p></td></tr>
				<%
				}}
				%>
				<!--endprint-->
				<tr>
					<td height="22">
						<div align="center">
							<div align="center" id=PrintDiv>
									<a href="javascript:doPrint()" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width: 80px">打印</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width: 80px">返回</a>
							</div>
						</div>
					</td>
				</tr>
			</table>
			
			<%
				}else{
			%>
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="1" class="bottom">
				<tr>
					<td height="22" align="center">
						<br />
						<p align="center" class="css">
							<font color="2B7619"><strong>本次操作没有新的签约人产生</strong></font>
						</p>
						<div align="center" id=PrintDiv>
							<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width: 80px">返回</a>
						</div>
					</td>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</body>
</html>
