 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.company.vo.SignerVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.*"%>
<%@ page import="com.netcom.nkestate.system.vo.SmObjectVO"%>
<%@ page import="com.netcom.nkestate.fhhouse.project.vo.*"%>
 <%
 	String path = request.getContextPath();
 	SignerVO signerVO = (SignerVO)request.getAttribute("signerVO");
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

	 	function doBack(){
			window.location="<%=path%>/inner/companymanage/gotoSignerEditList.action?compId="+frmInfo.comp_ID.value;
	 	}
	 	
	 	function doSave(){
	 		if(!$("#frmInfo").form("validate")){
	 			return;
	 		}
	 		if(frmInfo.cmd.value=='edit'&&frmInfo.status.value!='0'){
	 			if(!confirm('修改签约人信息需要重新审核，是否继续?')) {
	 				return;
	 			}
	 		}
	 		var len=document.forms["frmInfo"].SELECTEDBUILDING.options.length;
	 		if(len>0){
	 			for (var kk=0;kk<len;kk++){
	 			    //alert("kk="+kk);
	 				document.forms["frmInfo"].SELECTEDBUILDING.options[kk].selected=true;
	 			}
	 		}
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/companymanage/doSignerSave.action",
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							//doBack();
							alert("保存成功！");
							window.location="<%=path%>/inner/companymanage/gotoSignerEdit.action?cmd=edit&signerId="+data[0].message;
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("保存失败！");
					}
				});
	 	}
	 	
		function doProjectQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/companymanage/getSignerProjectListJson.action?compId="+frmInfo.comp_ID.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#PROJECT_ID').empty();
						$('#PROJECT_ID').append(data[0].projectList);
						doPresellQuery();
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
				alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
		
		function doPresellQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/companymanage/getStartUnitListJson.action?compId="+frmInfo.comp_ID.value+"&projectId="+frmInfo.PROJECT_ID.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#START_ID').empty();
						$('#START_ID').append(data[0].startunitList);
						doBuildingQuery();
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
				alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
		
		function doBuildingQuery(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/companymanage/getStartBuidlingListJson.action?compId="+frmInfo.comp_ID.value+"&projectId="+frmInfo.PROJECT_ID.value+"&startId="+frmInfo.START_ID.value,
				data : "{}",
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						$('#BUILDING').empty();
						$('#BUILDING').append(data[0].buidlingList);
						
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
				alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		
		}
			

		
		function doAgentClean(){
			//alert(1);
			$("#agentname").textbox("setValue","");
			frmInfo.agent_ID.value="";
		}
		
		function doSelectAll(obj) {
			var frm = document.forms["frmInfo"];
	
			for (var i=0;i<frm.elements.length;i++)
			{
				var e=frm.elements[i];
	
				if ((e.name != 'allBox') && (e.type=='checkbox'))
				{
					if (obj.checked == true) {
						e.checked = true;
					} else {
						e.checked = false;
					}
				}
			}
		}
		
		function del(){
			var opts1=new Array();
			var ss=document.forms["frmInfo"].SELECTEDBUILDING.selectedIndex;
			//alert(ss);
			if(ss==-1){return;}
			//if(ss==0){return;} 
			var aa=document.forms["frmInfo"].SELECTEDBUILDING.options[ss].text;
			var bb=document.forms["frmInfo"].SELECTEDBUILDING.options[ss].value;		 
			opts1[0]=new Option(aa,bb);
			var i;
			var lengthOptions =document.forms["frmInfo"].BUILDING.length;
			for(i = 0;i<lengthOptions;i++){
				 
				if(document.forms["frmInfo"].BUILDING.options[i].value == bb){
				with(document.forms["frmInfo"].SELECTEDBUILDING){
				options[document.forms["frmInfo"].SELECTEDBUILDING.selectedIndex]=null;}
				return;}
			}
			with(document.forms["frmInfo"].SELECTEDBUILDING){
				options[document.forms["frmInfo"].SELECTEDBUILDING.selectedIndex]=null;
			}
			with(document.forms["frmInfo"].BUILDING){
			options[document.forms["frmInfo"].BUILDING.options.length]=opts1[0];
			}
		}
		
		function add(){
			var opts2=new Array();
			  	var ss=document.forms["frmInfo"].BUILDING.selectedIndex;
			  	if(ss==-1){return;}		 
			  	var aa=document.forms["frmInfo"].BUILDING.options[ss].text;
			 	var bb=document.forms["frmInfo"].BUILDING.options[ss].value;	  	 		 
				opts2[0]=new Option(aa,bb);		
				var i;
				var lengthOptions =document.forms["frmInfo"].SELECTEDBUILDING.length;
				for(i = 0;i<lengthOptions;i++){
					if(document.forms["frmInfo"].SELECTEDBUILDING.options[i].value == bb){
					with(document.forms["frmInfo"].BUILDING){
			   		options[document.forms["frmInfo"].BUILDING.selectedIndex]=null;}
					return;}
				}
			  	with(document.forms["frmInfo"].SELECTEDBUILDING){
			    	options[document.forms["frmInfo"].SELECTEDBUILDING.options.length]=opts2[0];
			  	}
			  	with(document.forms["frmInfo"].BUILDING){
			   		options[document.forms["frmInfo"].BUILDING.selectedIndex]=null;
			  	}
		  
		}
		function addall(){
			delall();
			var state=1;
			var len=document.forms["frmInfo"].BUILDING.options.length;
			if(len==0){return;}		 
			var opts2;
			  for(var i=0;i<len;i++){
				opts2=new Array();
				var aa=document.forms["frmInfo"].BUILDING.options[i].text;
				var bb=document.forms["frmInfo"].BUILDING.options[i].value;
				for(var j=1;j<document.forms["frmInfo"].SELECTEDBUILDING.options.length;j++){
				  if(document.forms["frmInfo"].SELECTEDBUILDING.options[j].value==bb){
				   state=0;		  
				   break;
				  }
				}
				if(state==1){
				   opts2[0]=new Option(aa,bb);
				   with(document.forms["frmInfo"].SELECTEDBUILDING){
					 options[document.forms["frmInfo"].SELECTEDBUILDING.options.length]=opts2[0];
					 
				   }
				}
				state=1;
			  }
			  with(document.forms["frmInfo"].BUILDING){
					options.length=0;
			  }   
		}
		
		function delall(){	 
			var state=1;
			var len=document.forms["frmInfo"].SELECTEDBUILDING.options.length;
			//alert(len);
			if(len==0){return;} 
			var opts2;
			  for(var i=0;i<len;i++){
				opts2=new Array();
				var aa=document.forms["frmInfo"].SELECTEDBUILDING.options[i].text;
				var bb=document.forms["frmInfo"].SELECTEDBUILDING.options[i].value;
				for(var j=0;j<document.forms["frmInfo"].BUILDING.options.length;j++){
				  if(document.forms["frmInfo"].BUILDING.options[j].value==bb){
				   state=0;		  
				   break;
				  }
				}
				if(state==1){
				   opts2[0]=new Option(aa,bb);
				   with(document.forms["frmInfo"].BUILDING){
					 options[document.forms["frmInfo"].BUILDING.options.length]=opts2[0];
				   }
				}
				state=1;
			  }
			  var aa=document.forms["frmInfo"].SELECTEDBUILDING.options[0].text;
			  var bb=document.forms["frmInfo"].SELECTEDBUILDING.options[0].value;
			  opts2[0]=new Option(aa,bb);
			  with(document.forms["frmInfo"].SELECTEDBUILDING){
					options.length=0;
					//options[0]=opts2[0];
		
			  }   
		
		}
		
	 	
	 	function gotoAgentSelect(agentCode,agentName){
	 		openDialog("代理商选择","<%=path%>/inner/companymanage/gotoAgentSelectQuery.action?agentCode="+agentCode+"&agentName="+agentName,750,450);
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
		function doSelAgent(agentID,agentName){
			//alert(11);
			//alert(agentID);
			//alert(agentName);
			$("#agentname").textbox("setValue",agentName);
			frmInfo.agent_ID.value=agentID;
			$("#openDL").dialog('close');
		}
		
		//撤销
		function doCancel(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/companymanage/doSignerCancel.action",
				data : $("#frmInfo").serialize(),
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert('撤销成功');
						doBack();
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		}
		
		//恢复
		function doRepeal(){
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/companymanage/doSignerRepeal.action?signer_ID="+frmInfo.signer_ID.value,
				data : {},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert('撤销恢复成功');
						doBack();
					}else{
						alert(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
					alert(errorThrown);	
					alertify.error("获取失败。");
				}
			});
		}
		
		function doFirstSubmit(){
	 		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/companymanage/doSignerFirstSubmit.action?signer_ID="+frmInfo.signer_ID.value,
					data : $("#frmInfo").serialize(),
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							alert('提交审核成功');
							doBack();
						}else{
							alert(data[0].message);
						}
					},
					error : function(){
						alert("提交审核失败！");
					}
				});
	 	}
	 	
		
	</script>
</head>
<body id="body-layout" onload="doProjectQuery();">
	<form id="frmInfo" name="frmInfo" method="post" action="<%=path%>/inner/companymanage/doCompanySave.action">
	<input type="hidden" name="cmd" value="<%=HttpSafeUtil.encodeForHTMLAttribute(request.getAttribute("cmd")) %>"/>
	<input type="hidden" name="comp_ID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(signerVO==null?"":signerVO.getComp_ID()) %>"/>
	<input type="hidden" name="signer_ID" value="<%=HttpSafeUtil.encodeForHTMLAttribute(signerVO==null?"":signerVO.getSigner_ID()) %>"/>
	<input type="hidden" name="status" value="<%=HttpSafeUtil.encodeForHTMLAttribute(signerVO==null?"":signerVO.getStatus()) %>"/>
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_right" bgcolor="#5BADFF" width="15%" rowspan="7"><font style="color: white">基本信息 </font></td>
			<td class="input_right input_required" bgcolor="E6F8E3" width="10%"><font style="color: #003300">姓名：</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="name" id="name"  data-options="required:true,validType:'length[0,10]'"  value="${signerVO.name}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="E6F8E3"><font style="color: #003300">证件名称：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="cardName" id="cardName"  data-options="required:true,validType:'length[0,10]'"  value="${signerVO.cardName}" style="width: 150px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="E6F8E3"><font style="color: #003300">证件号码：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="cardCode" id="cardCode"  data-options="required:true,validType:'length[0,20]'"  value="${signerVO.cardCode}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left input_required" bgcolor="E6F8E3"><font style="color: #003300">身份证号码：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="brokercert" id="brokercert"  data-options="required:true,validType:'length[0,20]'"  value="${signerVO.brokercert}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">请选择代理商：</font></td>
			<td class="input_right" width="30%">
				<input type="hidden" name="agent_ID" id="agent_ID" value="${signerVO.agent_ID}"/>
				<input class="easyui-textbox" type="text" name="agentname" id="agentname"  data-options="required:false,validType:'length[0,30]',editable:false"  value="${agentName}" style="width: 250px"></input>
				&nbsp;<a href="javascript:gotoAgentSelect('','')" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:60px">...</a>
				&nbsp;<a href="javascript:doAgentClean()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="width:60px">清空</a>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">keyID：</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="keyID" id="keyID"  data-options="validType:'length[0,20]'"  value="${signerVO.keyID}" style="width: 200px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="E6F8E3"><font style="color: #003300">是否允许盖章：</font></td>
			<td class="input_right" width="30%">
				<input type="radio" name="canSeal" id="canSeal"  value="1" ${signerVO.canSeal == 1 ? 'checked' :''} />允许
				<input type="radio" name="canSeal" id="canSeal"  value="0" ${signerVO.canSeal == 0 ? 'checked' :''} />不允许
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
						<font color='#003300'><input type="checkbox" name="actionID" value="<%=objectvo.getObjectId() %>" <%if(hasCheck){%>checked<%} %>   /><%=objectvo.getName() %></font>
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
								<td></td>
							<%
							}
						}
						%>
							</tr>
						<%
					}
					%>
					
					<tr align = right bgcolor='E6F8E3'>
						<td colspan="<%=rownum %>">
						<font size=2 color="#000000"><strong>全选</strong></font><input type="checkbox" name="allBox" onClick="doSelectAll(this);">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF" rowspan="3">
				<font style="color: white">公司开发的项目和楼幢</font>
			</td>
			<td height="25" bgcolor="E6F8E3" colspan="2">
				<font color="#003300">请选项目：
				<select id="PROJECT_ID" name="PROJECT_ID" onchange='doPresellQuery()'>
					
				</select>
				</font>
			</td>
		</tr>
		<tr> 
			<td bgcolor="E6F8E3"  colspan="2">
				<font color="#003300">请选许可证：
				<select id="START_ID" name="START_ID" onchange='doBuildingQuery()'>
					
					
				</select>
				</font>
			</td>
		</tr>
		<tr> 
			<td  height="130" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp; 
				<select id="BUILDING" name="BUILDING" size='4' multiple style="width:500px">
					
				</select>
			<a href="javascript:add()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px">添加</a>
			<a href="javascript:addall()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px">全部添加</a>
			</font></td>
		</tr>
		<tr> 
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">有权签约的楼幢列表</font>
			</td>
			<td  height="100" bgcolor="E6F8E3" colspan="2"><font color="#003300">&nbsp;&nbsp;&nbsp; 
				<select id="SELECTEDBUILDING" name="SELECTEDBUILDING" size='4' multiple style="width:500px">
					<% 
					List<ProPreBldSignerVO> signerBuildingList = (List<ProPreBldSignerVO>)request.getAttribute("signerBuildingList");
					if(signerBuildingList!=null && signerBuildingList.size()>0){
						for(ProPreBldSignerVO signerBuildingVO:signerBuildingList){
					%>
						<option value="<%=signerBuildingVO.getStart_ID()+"_"+signerBuildingVO.getBuilding_ID() %>"><%=signerBuildingVO.getAttribute("building_name") %></option>
					<% 
						}
					}
					%>
				</select>
			<a href="javascript:del()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">删除</a>
			<a href="javascript:delall()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:80px">全部删除</a>
			</font></td>
		</tr>
		<c:if test="${cmd=='edit'&&backFlag=='edit'}">
			<td class="input_right" bgcolor="#5BADFF">
				<font style="color: white">撤销备注</font>
			</td>
			<td class="input_right" colspan="2">
				<textarea rows="4" name="repeal_Comment" id="repeal_Comment" cols="50" class="easyui-validatebox" data-options="validType:'length[0,50]'">${eqvo.repeal_Comment}</textarea>
			</td> 
		</c:if>
	</table>
	
	
	<br/>
	<div align="center">
		<a href="javascript:doSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">保存</a>&nbsp;&nbsp;
		<a href="javascript:doBack()" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>&nbsp;&nbsp;
		<c:if test="${cmd=='edit'&&backFlag=='edit'}">
		<a href="javascript:doFirstSubmit()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">提交审核</a>&nbsp;&nbsp;
		<a href="javascript:doCancel()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">撤消</a>&nbsp;&nbsp;
		<a href="javascript:doRepeal()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px">恢复</a>&nbsp;&nbsp;
		</c:if>
	</div>
	<br/>
	<div id="openDL"></div>
	</form>
</body>
</html>
