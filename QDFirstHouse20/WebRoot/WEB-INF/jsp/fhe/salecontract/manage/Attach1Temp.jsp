 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.Attach1TemplateVO"%>
<%@page import="com.netcom.nkestate.fhhouse.salecontract.vo.Attach1MoneyTempVO"%>
<%@page import="java.util.List"%>
 <%
 	String basePath = request.getContextPath();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>出售合同模板浏览</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/RMBUtil.js"></script>
		<style type="text/css">
			
		</style>
		<script type="text/javascript">
			$(function(){
				addPaymentmode();
				addBorrowmode();
			});
			
			function addPaymentmode(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_502',
					dataType : "json",
					success : function(data){
						$('#paymentmode').empty();
						var paymentmodeID = $('#paymentmodeID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(option.code==paymentmodeID){
									$('#paymentmode').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#paymentmode').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询付款方式出错！");
					}
				});
			}
			
			function changePaymentmode(payment){
				if(payment!=null&&payment==0){
					$('#attach1money')[0].style.disabled = true;
					//$("#btn").attr("disabled", true); 
				}else{
					$("#attach1money").attr("disabled", false); 
				}
			}
			
			function addBorrowmode(){
				$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : '<%=basePath%>/system/getDictionaryJson.action',  
					data : 'dictName=CT_503',
					dataType : "json",
					success : function(data){
						$('#borrowmode').empty();
						var borrowmodeID = $('#borrowmodeID').val();
						if(data.length>0){
							$.each(data,function (index,option){
								if(option.code==borrowmodeID){
									$('#borrowmode').append('<option value='+option.code+' selected>'+option.name+'</option>');
								}else{
									$('#borrowmode').append('<option value='+option.code+'>'+option.name+'</option>');
								}
							});
						}
					},
					error : function(){
						alert("查询借款方式出错！");
					}
				});
			}

			function openDialog(tempTitle,url,tempWidth,tempHeight){
				$("#openDL").show().dialog({
					title : tempTitle,
					width : tempWidth,
					height: tempHeight,
					modal:true
				});
				$("#openDL").dialog('open');
				$("#openDL").dialog('center');
				$('#openDL').dialog('refresh', url);
			
			}
			
		 	function doBack(){
		 		$("#openDL").dialog('close');
		 	}
			
			function addAttach1Money(templateID){
				var url = "<%=basePath%>/outer/contracttemplatemanage/operaAttach1MoneyTemp.action?templateID="+templateID;
				openDialog("新增付款项",url,700,200);
			}
			
			function modifyAttach1Money(id,serial){
				var url = "<%=basePath%>/outer/contracttemplatemanage/operaAttach1MoneyTemp.action?ID="+id+"&serial="+serial;
				openDialog("修改付款项",url,700,200);
			}
			
			function deleteAttach1Money(ID,serial){
				if(ID!=null&&ID!=''&&serial!=null&&serial!=''){
					if(confirm('确认删除付款项'+serial+'吗？')){
						$.ajax({
							type : "POST",
							contentType : "application/x-www-form-urlencoded;charset=utf-8",
							url : '<%=basePath%>/outer/contracttemplatemanage/deleteAttach1MoneyTemp.action',  
							data : 'ID='+ID+'&serial='+serial,
							dataType : "json",
							success : function(data){
								if(data[0].result=='success'){
									alert(data[0].msg);
									parent.doUpdate('附件一模板');
								}else{
									alert(data[0].msg);
								}
							},
							error : function(){
								alert("删除乙方出错！");
							}
						});
					}
				}
			}
			
			function addAttach1(){
				var templateID = $('#templateID').val();
				if(templateID!=null&&templateID!=''){
					$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/contracttemplatemanage/saveAttach1temp.action',  
						data : $('#attach1temp').serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								parent.doUpdate('附件一模板');
							}else{
								alert(data[0].msg);
							}
						},
						error : function(){
							alert("保存附件一出错！");
						}
					});
				}
			}

			function small2big(t1,t2){
				var num = $('#'+t1).val();
				var n = Number(num);
				if(!isNaN(num)){
					if(num == 0){
						$('#'+t2).val('');
					}else{
						var aa = _format(n);
						$('#'+t2).val(aa);
					}
				}else {
					alert("请输入数字");
				}
			}
			
			function doSave(){
				var templateID = $('#templateID').val();
				var serial = $('#serial').val();
				if(serial==''){
					$('#serial').val('0');
				}
		    	if(templateID!=null&&templateID!=''){
		    		$.ajax({
						type : "POST",
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						url : '<%=basePath%>/outer/contracttemplatemanage/saveAttach1MoneyTemp.action',  
						data : $('#a1moneytemp').serialize(),
						dataType : "json",
						success : function(data){
							if(data[0].result=='success'){
								alert(data[0].msg);
								doBack();
								parent.doUpdate('附件一模板');
							}else{
								alert(data[0].msg);
							}
						},
						error : function(){
							alert("付款项保存出错！");
						}
					});
			    }
			}
		</script>
	</head>
	<body >
		<form id="attach1temp" name="attach1temp" method="post" action="">
		<table  cellspacing=16 cellpadding=0 border=0 align="center">
			<tr>
				<td>
					<p style="text-indent: 0px; line-height: 200%" align="center">
						<b><font size=5>附&nbsp; 件&nbsp; 一</font></b>
						<br/>
						<font size=3>付款方式和付款期限</font>
					</p>
					<p align=center>
						------------------------------------------------
						<br/>
					</p>
					<% 
						Attach1TemplateVO a1tempVo = (Attach1TemplateVO)request.getAttribute("a1tempVo"); 
						if(a1tempVo!=null){
					%>
					<p style="width:100%;">
						<input type="hidden" id="templateID" name="templateID" value="<%=a1tempVo.getTemplateID() %>" />
						<input type="radio" name="paymentType" value="0" <c:if test="<%=a1tempVo.getPaymentType()==0 %>">checked</c:if> />
						<span class="style4">按时间付款</span>
						<br/><br/>
						付款方式：&nbsp;
						<select id="paymentmode" name="paymentMode" style="width:100px" onchange="changePaymentmode(this.value)"><option value="1">CT_502</option></select>
						<input type="hidden" id="paymentmodeID" value="<%=a1tempVo.getPaymentMode() %>" />
						&nbsp;&nbsp;&nbsp; 贷款方式：&nbsp;
						<select id="borrowmode" name="borrowMode"  style="width:100px"><option value="1">CT_503</option></select>
						<input type="hidden" id="borrowmodeID" value="<%=a1tempVo.getBorrowMode() %>" />
						<br/><br/><br/>
						<%
							List<Attach1MoneyTempVO> a1moneyList = (List<Attach1MoneyTempVO>)request.getAttribute("a1moneyList");
							if(a1moneyList!=null&&a1moneyList.size()>0){
								for(Attach1MoneyTempVO a1moneytemp:a1moneyList){
						%>
								<%=a1moneytemp.getSerial() %>、乙方于
				  				<input readonly="readonly" type="text" size="8" value="<%=a1moneytemp.getPaymentDate()!=0?a1moneytemp.getPaymentDate():"" %>"/>
								前向甲方支付<c:if test="<%=a1moneytemp.getSerial()==1 %>">首期</c:if>房款人民币
								<input readonly="readonly" type="text" size="20" maxlength="20" value="<%=a1moneytemp.getMoney()!=0?a1moneytemp.getMoney():"" %>"/>
								元；
								<br/><br/>
								(大写)：
								<input readonly="readonly" type="text" size="68" maxlength="20" value="<%=a1moneytemp.getMoneyCn()!=null?a1moneytemp.getMoneyCn():"" %>"/>
								。
								<br/><br/>
								<input type="button" value="修改"  onclick="modifyAttach1Money('<%=a1moneytemp.getID() %>','<%=a1moneytemp.getSerial() %>');"/>
								<c:if test="<%=a1moneytemp.getSerial()>2 %>">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" name="buyer" value="删除"  onclick="deleteAttach1Money('<%=a1moneytemp.getID() %>','<%=a1moneytemp.getSerial() %>');"/>
								</c:if>
								<br/><br/>
						<%
								}
							}
						%>
						<input type="button" id="attach1money" name="attach1money" value="新增付款项"  onclick="addAttach1Money('<%=request.getParameter("templateID")%>');"/>
					</p>
					<p style="width:100%;">
						<br/>备注<br/><br/>
						<textarea name="timecontent" cols="70" rows="40"></textarea>
					</p>
					<p style="width:100%;">
						<input type="radio" name="paymentType" value="1"  <c:if test="<%=a1tempVo.getPaymentType()==1 %>">checked</c:if>/>
						<span class="style4">按条件付款</span><br/><br/><br/>
						<textarea name="content" cols="70" rows="40"><c:if test="<%=a1tempVo.getPaymentType()==1 %>"><%=a1tempVo.getContent()==null?"":new String(a1tempVo.getContent()) %></c:if></textarea>
					</p>
					<%
						}
					%>
				</td>
			</tr>
		</table>
			<div align="center">
				<input type="button" name="submit" onclick="addAttach1();" value="保存附件一"/>
				<input type="reset" name="reset" value="重置"/>
			</div>
		</form>
		<div id="openDL"></div>
	</body>
</html>