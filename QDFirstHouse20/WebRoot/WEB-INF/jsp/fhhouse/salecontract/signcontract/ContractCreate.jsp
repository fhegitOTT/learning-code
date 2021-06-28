 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
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
		<style type="text/css">
			body{overflow:hidden;} 
		</style>
		<script type="text/javascript">
		$(function(){
			openTab();
		});
		
		function openTab(){
			$('#buyer').click();
			$('#cont').click();
			$('#attach4').click();
			$('#buyer').click();
		}
		
	    function addTab(title, url){
	    	if ($('#tt').tabs('exists', title)){
	    		$('#tt').tabs('select', title);
	    	} else {
	    		var content = '<iframe frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	    		$('#tt').tabs('add',{
	    			title:title,
	    			content:content,
	    			closable:false
	    		});
	    	}
	    }
	    
	    function closeTab(title){
	    	$('#tt').tabs('close', title);
		}

	    function doUpdate(title){
	    	if ($('#tt').tabs('exists', title)) {
	            $('#tt').tabs('select', title);
	            var selTab = $('#tt').tabs('getSelected');
	            var url = $(selTab.panel('options').content).attr('src');
	            $('#tt').tabs('update', {
	                tab: selTab,
	                options: {
	                    content:'<iframe frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
	                }
	            })
	        }
	    }
	    
	    function doReload(){
	    	$('#tt').tabs('close', '乙方编辑');
	    	$('#tt').tabs('close', '合同编辑');
	    	//$('#tt').tabs('close', '附件一编辑');
	    	openTab();
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
			//$('#openDL').dialog('refresh', url);

			var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="yes"></iframe>';
            $('#openDL').dialog({
                content: content,
                noheader: false,
                border: true,
                resizable: false,//定义对话框是否可调整尺寸。
                maximized: false,//默认最大化
                modal: true,
			});
		}
	 	function doBack(){
	 		$("#openDL").dialog('close');
	 	}
		</script>
	</head>
	<body >
	    <div id="tt" class="easyui-tabs" data-options="fit:true"  style="width:100%;">
	    	
	    </div>
		<div>
			<a href="#" id="buyer" class="easyui-linkbutton" onclick="addTab('乙方编辑','<%=basePath%>/outer/signcontract/editBuyer1.action?contractID=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("contractID")) %>&earnestID=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("earnestID")) %>')" style="display:none;">乙方编辑</a>
			<a href="#" id="cont" class="easyui-linkbutton" onclick="addTab('合同编辑','<%=basePath%>/outer/signcontract/gotoSignContract.action?projectID=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("projectID")) %>&startID=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("startID")) %>&houseId=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("houseId")) %>&template=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("template")) %>&type=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("type")) %>&contractID=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("contractID")) %>&ctype=<%=HttpSafeUtil.encodeForHTMLAttribute(request.getParameter("ctype")) %>')" style="display:none;">合同编辑</a>
			<c:if test="${type==1}">
	    	<a href="#" id="attach4" class="easyui-linkbutton" onclick="addTab('附件四编辑','<%=basePath%>/outer/signcontract/editAttach4.action?contractID=${contractID }')" style="display:none;">附件四编辑</a>
	    	</c:if>
	    </div>
	    <div id="openDL"></div>
	</body>
</html>