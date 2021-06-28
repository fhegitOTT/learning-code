 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.system.vo.SmObjectVO"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>签约人编辑页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<style type=text/css>
			*{color: #555;} 
			body{font-size:12px; margin:0; font:Arial, Helvetica,"宋体", sans-serif; }
			.toplogo{width:auto;line-height:40px;margin-left:10px; display:inline;float:left; overflow-y:hidden;}
			/*最底部*/
			.bottom{ overflow-y:hidden}
			.bottom_ban{width:auto;line-height:40px;margin-left:18px; display:inline;float:left; overflow-y:hidden;}
			.bottom_shi{width:auto;line-height:40px;margin-right:20px; display:inline;float:right; overflow-y:hidden;}
			
			tr{
				background-color:white;
			}		
		</style>
		

	<script type="text/javascript">
	 	function doPrint(){
	 		var a=$("input[name='status']:checked").val();
	 		var b=$("textarea[name='finalMark']").val();
	 		b = encodeURI(b);
			window.open("<%=path%>/inner/companymanage/signerAuditPrint.action?status="+a+"&finalMark="+b);
	 	}
	 	
		function getValue(){
		    var radio = document.getElementsByName("status");  
		    for (i=0; i<radio.length; i++) {  
		        if (radio[i].checked) {  
		            if(radio[i].value==1){
		            	$("textarea[name='finalMark']").val("同意");
		            }if(radio[i].value==2){
		            	$("textarea[name='finalMark']").val("不同意");
		            }
		        }  
		    }  
		}  

	 	function doSave(){
	 		var a=$("input[name='status']:checked").val();
	 		var signerId=$("input[name='signerId']").val();
	 		var status=$("input[name='status']").val();
	 		var b=$("textarea[name='finalMark']").val();
	 		b = encodeURI(b);
	 		if(a==1 || a==2){
		 		window.location="<%=path%>/inner/companymanage/signerAudit.action?option="+a+"&signerId="+signerId+"&status="+status+"&finalMark="+b;
	 		}else{
	 			alert("请输入复审意见后再提交！");
	 			window.history.back;
	 		}
	 	}
	 	
	 	function doSure(){
	 		var a=$("input[name='option']:checked").val();
	 		var signerId=$("input[name='signerId']").val();
		 	window.location="<%=path%>/inner/companymanage/signerPublish.action?option="+a+"&signerId="+signerId;
	 	}
		
	</script>
</head>
<body>
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<%
			List<SignerVO> list1=(List<SignerVO>)request.getAttribute("list1");
			for(SignerVO svo:list1){
		 %>
	<input type="hidden" name="signerId" value="<%=svo.getSigner_ID() %>"/>
	<input type="hidden" name="status" value="<%=svo.getStatus() %>"/>
		<tr height="25">
			<td class="input_right" bgcolor="#5BADFF" width="15%" rowspan="6"><font style="color: white">基本信息 </font></td>
			<td class="input_right" bgcolor="E6F8E3" width="10%"><font style="color: #003300">姓名：</font></td>
			<td class="input_right">
				<%=svo.getName() %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">证件名称：</font></td>
			<td class="input_right" width="30%">
				<%=svo.getCardName() %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">证件号码：</font></td>
			<td class="input_right" width="30%">
				<%=svo.getCardCode() %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">身份证号码：</font></td>
			<td class="input_right" width="30%">
				<%=svo.getBrokercert() %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">所属代理商：</font></td>
			<td class="input_right" width="30%">
			<%
			if(svo.getAttribute("agentname")!=null){ 
			%>
				<%=svo.getAttribute("agentname") %>
			<%
				}
			 %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">所属企业：</font></td>
			<td class="input_right" width="30%">
				<%=svo.getAttribute("compname") %>
			</td>
		</tr>

		<tr height="25">
			<td class="input_right" bgcolor="#5BADFF"><font style="color: white">权限列表</font></td>
			<td colspan="2">
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#003300" class="css">
					<%
					List<SmObjectVO> objectList = (List<SmObjectVO>)request.getAttribute("objectList");
					int rownum = 4;//总列数
					int num=1;//当前列数
					int rowsize = 1;//记录数
					for(SmObjectVO objectvo:objectList){
						//判断是否已经关联
						Object signer_id = objectvo.getAttribute("signer_id");
						boolean hasCheck = false;
						if(signer_id!=null && !"".equals(signer_id)){
							hasCheck = true;
						}
						//判断是否第一条
						if(num==1){
							%>
								<tr align =left   style="bgcolor:'E6F8E3';">
							<%
						}
					%>
					
					<td>
						<font color='#003300'><input type="checkbox" name="actionID" value="<%=objectvo.getObjectId() %>" <%if(hasCheck){%>checked<%} %>  disabled="disabled" /><%=objectvo.getName() %></font>
					</td>
					<%
						num++;
						rowsize++;
						//判断当前行的列数是否已经到设置数量
						if(num>rownum && rowsize<=objectList.size()){
							%>
								</tr>
							<%
							num = 1;
						}
					}
					//判断是否存在记录
					if(rowsize>1){
						//判断是否补齐行列
						if(num<=rownum){
							for(int k=num; k<=rownum; k++ ){
							%>
								<td>&nbsp;</td>
							<%
							}
						}
						%>
							</tr>
						<%
					}
					%>
				</table>
			</td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">受权项目信息</font>
			</td>
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp;
        		<%
						String htmlview1 = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview1!=null && !"".equals(htmlview1) && !htmlview1.equals("null")){
						    out.println(htmlview1);
					}%>			
			</td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">受权预售许可证信息</font>
			</td>
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp;
        		<%
						String htmlview2 = String.valueOf(request.getAttribute("htmlView1"));
						if(htmlview2!=null && !"".equals(htmlview2) && !htmlview2.equals("null")){
						    out.println(htmlview2);
					}%>			
			</td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF" rowspan="3">
				<font style="color: white">初审意见</font>
			</td>
			<td height="25" bgcolor="E6F8E3" colspan="2">
				<font color="#003300">初审人：${svo.firstCensor }
				</font>
			</td>
		</tr>
		<tr> 
			<td bgcolor="E6F8E3"  colspan="2">
				<font color="#003300">初审日期：<%=DateUtil.parseDateTime3(String.valueOf(svo.getFirstAuditDate())) %>
				</font>
			</td>
		</tr>
		<tr> 
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp; 
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${svo.firstMark }</textarea>
		</td>
		</tr>

		 <c:if test="${status=='audit'}">
		<tr> 
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">复审意见</font>
			</td>
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp;
				<textarea rows="4" name="finalMark" id="finalMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"></textarea>
				<label><input name="status" type="radio" value="1" onclick="getValue()"/>同意 </label> 
				<label><input name="status" type="radio" value="2" onclick="getValue()"/>不同意 </label>			 
			</td>
		</tr>
		</c:if>
		<c:if test="${status=='publish'}">
		<tr> 
			<td class="input_right" bgcolor="#5BADFF" rowspan="3">
				<font style="color: white">复审意见</font>
			</td>
			<td height="25" bgcolor="E6F8E3" colspan="2">
				<font color="#003300">复审人：${svo.finalCensor }
				</font>
			</td>
		</tr>
		<tr> 
			<td bgcolor="E6F8E3"  colspan="2">
				<font color="#003300">复审日期：<%=DateUtil.parseDateTime3(String.valueOf(svo.getFinalAuditDate())) %>
				</font>
			</td>
		</tr>
		<tr> 
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp; 
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" readonly="readonly">${svo.finalMark }</textarea>
		</td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">信息发布</font>
			</td>
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp;
				<label><input name="option" type="radio" value="1" onclick="getValue1()" checked="checked"/>同意 </label> 
				<label><input name="option" type="radio" value="2" onclick="getValue1()"/>不同意 </label>			 
			</td>
		</tr>
		</c:if>
		<%
			}
		 %>

	</table>
	
	
	<br/>
	<div align="center">
		<c:if test="${status=='audit'}">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px">确定</a>
		<a href="javascript:doPrint()" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">打印</a>
		</c:if>
		<c:if test="${status=='publish'}">
		<a href="javascript:doSure()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px">确定</a>
		</c:if>
		<a href="javascript:history.go(-1)" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
	</div>
	<br/>
	<div id="openDL"></div>
	</form>
</body>
</html>
