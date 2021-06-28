 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.StartUnitVO"%>
<%@page import="com.netcom.nkestate.system.vo.UserinfoVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 	StartUnitVO svo = (StartUnitVO)request.getAttribute("svo");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>开发企业编辑页面</title>
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
		function showBuilding(building_id){
			window.open("<%=path%>/inner/companymanage/houseViewList.action?buildingId="+building_id);
		}
	
	 	function doPrint(){
	 		//var a=$("input[name='status']:checked").val();
	 		var b=$("textarea[name='finalMark']").val();
	 		b = encodeURI(b);
			window.open("<%=path%>/inner/companymanage/startUnitAuditPrint.action?status="+b);
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
	 		var b=$("textarea[name='finalMark']").val();
	 		b = encodeURI(b);
	 		if(a==1 || a==2){
		 		window.location="<%=path%>/inner/companymanage/startUnitAudit.action?status="+a+"&startId="+${svo.start_ID}+"&finalMark="+b;
	 		}else{
	 			alert("请输入复审意见后再提交！");
	 			window.history.back;
	 		}
	 	}
	 	
	 	function doSure(){
	 		var a=$("input[name='option']:checked").val();
	 		var startId=$("input[name='startID']").val();
		 	window.location="<%=path%>/inner/companymanage/startUnitPublish.action?option="+a+"&startId="+startId;
	 	}
	</script>
</head>
<body id="body-layout" >
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<input type="hidden" name="startID" value="${svo.start_ID}"/>
	<input type="hidden" name="status" value="${eqvo.status}"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="5" align="center"><font style="color: white">开<br/>盘<br/>单<br/>元<br/>审<br/>核</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">开盘编号：</font></td>
			<td class="input_right" colspan="3">
				${svo.start_Code }
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">类型选择：</font></td>
			<td class="input_right" width="30%">
				<%if("0".equals(svo.getAttribute("type").toString())){ %>
					预售许可证
				<%}if("1".equals(svo.getAttribute("type").toString())){ %>
					权属证明
				<%} %>
			</td>
			<td class="input_right" colspan="2">
				<%=svo.getAttribute("presell_desc") %>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">收件编号：</font></td>
			<td class="input_right" colspan="3">
				${svo.document_ID }
			</td>
		</tr>
		<c:if test="${list4!=null}">
			<c:forEach items="${list4}" var="a">
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">项目甲方：</font></td>
			<td class="input_right" colspan="3">
				${a.name }
			</td>
		</tr>
			</c:forEach>
		</c:if>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">备注：</font></td>
			<td class="input_right" colspan="3">
				${svo.firstMark }
			</td>
		</tr>
		<tr>
        	<td class="input_right" colspan="5">
        		<%
						String htmlview1 = String.valueOf(request.getAttribute("htmlView"));
						if(htmlview1!=null && !"".equals(htmlview1) && !htmlview1.equals("null")){
						    out.println(htmlview1);
					}%>
			</td> 
		</tr>
		<tr>
			<td class="input_left" bgcolor="#5BADFF" colspan="2" ><font style="color: white">操作员信息：</font></td>
        	<td class="input_right" colspan="3">
        		<%
						String htmlview2 = String.valueOf(request.getAttribute("htmlView1"));
						if(htmlview2!=null && !"".equals(htmlview2) && !htmlview2.equals("null")){
						    out.println(htmlview2);
					}%>
			</td> 
		</tr>
		
		<tr>
			<td class="input_left" bgcolor="#5BADFF" colspan="2" rowspan="3"><font style="color: white">初审意见：</font></td>
			<td class="input_right" width="15%"  colspan="3">初审人：${svo.firstCensor }</td>
		</tr>
		<tr>
			<td class="input_right" width="15%" colspan="3">初审日期：${firstDate }</td>
		</tr>
		<tr>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${svo.firstMark }</textarea>
			</td> 
		</tr>
		<c:if test="${status=='audit'}">
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">复审意见：</font></td>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="finalMark" id="finalMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"></textarea>
				<label><input name="status" type="radio" value="1" onclick="getValue()"/>同意 </label> 
				<label><input name="status" type="radio" value="2" onclick="getValue()"/>不同意 </label>
			</td> 
		</tr>
		</c:if>
		<c:if test="${status=='publish'}">
		<tr>
			<td class="input_left" bgcolor="#5BADFF" colspan="2" rowspan="3"><font style="color: white">复审意见：</font></td>
			<td class="input_right" width="15%"  colspan="3">复审人：${svo.finalCensor }</td>
		</tr>
		<tr>
			<td class="input_right" width="15%" colspan="3">复审日期：${finalDate }</td>
		</tr>
		<tr>
        	<td class="input_right" colspan="3">
				<textarea rows="4" name="firstMark" id="firstMark" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'">${svo.finalMark }</textarea>
			</td> 
		</tr>
		<tr>
			<td class="input_left input_required" bgcolor="#5BADFF" colspan="2"><font style="color: white">信息发布：</font></td>
        	<td class="input_right" colspan="3">
				<label><input name="option" type="radio" value="1" onclick="getValue1()" checked="checked"/>同意 </label> 
				<label><input name="option" type="radio" value="2" onclick="getValue1()"/>不同意 </label>
			</td> 
		</tr>
		</c:if>
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
	</form>
</body>
</html>