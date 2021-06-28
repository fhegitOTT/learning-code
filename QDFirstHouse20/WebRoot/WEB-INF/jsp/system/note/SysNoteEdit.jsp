 <%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%@ page language="java" import="java.util.*" %>
 <%@ page language="java" import="com.netcom.nkestate.system.vo.AttachmentVO"%>
 <%@ page language="java" import="com.netcom.nkestate.system.vo.AttachmentFileVO"%>
 <%@ page language="java" import="com.netcom.nkestate.system.bo.AttachmentBO"%>
 <%@ page language="java" import="com.netcom.nkestate.common.StringStamper"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>平台管理系统</title>
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
			
			.input_required:before{
				content:"* ";
				color:red;
			}
			tr{
				background-color:white;
			}			
		</style>
		

	<script type="text/javascript">
	 	var opFlag = "${opFlag}";	 	
	 	
	 	function doBack(){
	 		window.location = "<%=path%>/system/queryNotes.action";
	 	}
	 	
	 	function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		var url = "<%=path%>/system/updateNote.action";
	 		if(opFlag=="add"){
	 			url = "<%=path%>/system/saveNote.action";
	 		}
	 		var validDate = $("#validDate").datebox('getValue');	 		
	 		var inValidDate = $("#inValidDate").datebox('getValue');
	 		if(inValidDate!=null && inValidDate!=""){
		 		if(isDateAOldB(validDate,inValidDate)){
		 			alert("失效时间不能小于生效时间");
		 			return;
		 		}
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : url,
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert("保存成功！");
							location= "<%=path%>/system/queryNotes.action";
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("保存失败！");
					}
			});
	 	}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	</script>
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<form id="frmInfo" name="frmInfo" action="<%=path%>/system/saveNote.action">
	<%	
		String opFlag = request.getParameter("opFlag");
		if("edit".equals(opFlag)){
	 %>
		<input type="hidden" name="noteID" id="noteID" value="${noteVO.noteID}"></input>
		<input type="hidden" name="userID" id="userID" value="${noteVO.userID}"></input>
	<% 	} %>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:60%;background-color: #7DD5FF" align="center">   
		<tr>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">标题：</font></td>
        	<td class="input_right" ><input class="easyui-textbox" type="text" name="subject" value="${noteVO.subject}" data-options="required:true,validType:'length[0,50]'" style="width: 300px"></input></td>
		</tr>
		
		<tr>
        	<td class="input_left  input_required" bgcolor="#5BADFF"><font style="color: white">生效日期：</font></td>
        	<td class="input_right">
        		<input class="easyui-datebox" type="text" name="validDate" id="validDate"  data-options="required:true,validType:'length[0,50]',editable:false"  value="<fmt:formatDate value='${noteVO.validDate}' pattern='yyyy-MM-dd'/>"/>
        	</td>
        	
		</tr>
		<tr>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">失效日期：</font></td>
        	<td class="input_right" >
				<input class="easyui-datebox" type="text" name="inValidDate" id="inValidDate"  data-options="required:false,validType:'length[0,50]',editable:false"  value="<fmt:formatDate value='${noteVO.inValidDate}' pattern='yyyy-MM-dd'/>"/>
			</td>
        </tr>
        <tr>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: white">公告内容：</font></td>
        	<td class="input_right" >
				<textarea rows="6" name="content" cols="70" class="easyui-validatebox" data-options="required:true,validType:'length[0,4000]'">${noteVO.content}</textarea>
			</td>        		
		</tr>
	</table>	
		<br/>
	</form>
	<%
		if("edit".equals(opFlag)){
	%>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:60%;background-color: #7DD5FF" align="center">   
	<tr><td valign="top">
	<div data-options="region:'center',border:false" style="width:99%;">
		<iframe frameborder="0" width="100%" height="300px" scrolling="auto" id="list" name="list" src="<%=path%>/attachment/forward.action?businessID=${noteVO.noteID}&businessType=0&url=/company/attachment/AttachmentUpload"></iframe>
	</div>
	</td>
	</tr>
	</table>
	<%} %>

	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
	</div>	
</body>
</html>
