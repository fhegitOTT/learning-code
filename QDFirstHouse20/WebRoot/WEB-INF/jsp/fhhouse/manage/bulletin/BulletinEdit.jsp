<%@ page contentType = "text/html; charset=UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>内网公告查看</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
		<script type="text/javascript">
	
		</script>	
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
		
		
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;overflow: auto">
	<form id="frmInfo" name="frmInfo" action="">
		<input type="hidden" name="cmd" value="${cmd}"/>
		<input type="hidden" name="bID" value="${bID}"/>
	<br>
	
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:95%;background-color: #7DD5FF" align="center">   
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF" width=15%><font style="color: black">标题：</font></td>
        	<td class="input_right" bgcolor="white">
        		<input name="title" class="easyui-textbox" type="text" value="${bulletinVO.title}"  style="width: 450px"></input>
        	</td>
		</tr>
		
		<tr height="25">
        	<td class="input_left input_required" bgcolor="#5BADFF" width=25%><font style="color: black">是否弹出显示：</font></td>
        	<td class="input_right" bgcolor="white">
        	<%
				String cmd = request.getAttribute("cmd").toString();
				//System.out.println("cmd="+cmd);
				if("insert".equals(cmd)){ %>
        			<c:if test="${bulletinVO.status==null}">
        				<input name="status" id="status" type="checkbox" value="弹出" checked="checked"/>
        			</c:if>
        			<%}if("update".equals(cmd)){ %>
        			<c:if test="${bulletinVO.status==0}">
        				<input name="status" id="status" type="checkbox" value="不弹出" />
        			</c:if>
        			<c:if test="${bulletinVO.status==1}">
        				<input name="status" id="status" type="checkbox" value="弹出" checked="checked"/>
        			</c:if>
        			<%} %>
        		<!--
        		<input class="easyui-textbox" type="text" value="" data-options="editable:false" style="width: 150px"></input>
        		  -->
        	</td>
		</tr>
        <tr>
        	<td class="input_left input_required" bgcolor="#5BADFF"><font style="color: black">公告内容：</font></td>
        	<td class="input_right" bgcolor="white">
				<textarea rows="13" name="content" cols="55" class="easyui-validatebox" >${bulletinVO.content}</textarea>
			</td>        		
		</tr>
	</table>	
		<br/>
		
		<div align="center">
			<a href="javascript:doNoteSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交</a>
			<a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">取消</a>
		<!-- 
			<input type="submit" class="easyui-linkbutton" style="width:80px" value="提交" />
			<input type="reset" class="easyui-linkbutton" style="width:80px" value="重置"/>
			 -->
		</div>
	</form>
</body>
</html>
