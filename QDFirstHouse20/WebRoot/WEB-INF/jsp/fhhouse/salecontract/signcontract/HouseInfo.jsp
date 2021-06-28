 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO"%>
<%@page import="com.netcom.nkestate.fhhouse.project.vo.HouseVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>房屋信息页面</title>
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

	//获取合同模板
	function mychange(){
    	$('#template')[0].style.display="";
    	$('#template').empty();
    	var type= $("select[name='type']").val();
    	if (type=="1"||type=="2"){
    		$.ajax({
    			type : "POST",
    			contentType : "application/x-www-form-urlencoded;charset=utf-8",
    			url : '<%=path%>/outer/signcontract/findTemplate.action',  
    			data : {projectID:$('#projectID').val(),startID:$('#startID').val(),type:type},
    			dataType : "json",
    			success : function(data){
    				$('#template').append('<option value=0 selected>不使用合同模板</option>');
    				if(data.length>0){
    					$.each(data,function (index,option){
    						$('#template').append('<option value='+option.code+'>'+option.name+'</option>');
    					});
    				}
    			},
    			error : function(){
    				alert("查询项目列表出错！");
    			}
    		});
    	}
    	else{
    		$('#template')[0].style.display="none";
    	}
	}
	
	//合同签订
	function doSignContract(){
		var a= $("select[name='type']").val();
		$("#type").val(a);
		if(a==0){
			alert("请选择合同类型!");
			return;
		} 		
 		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : "<%=path%>/outer/signcontract/signContractCheck.action",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data[0].result=="success"){
					form1.submit();
				}else{
					alert(data[0].message);
				}
			},
			error : function(){
				alert("检查失败！");
			}
		});
		//window.location="<%=path%>/outer/signcontract/presell.action";
 	}
 	
 	function doContractTypeQuery(){
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			url : "<%=path%>/inner/signcontract/doContractTypeQuery.action?houseId="+form1.houseId.value,
			data : "{}",
			dataType : "json",
			success : function(data){
				if(data[0].result=="success"){
					$('#type').empty();
					$('#type').append(data[0].startBuildingList);
					
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
</script>
</head>
<body id="body-layout" onload="">
	<form method="post" action="<%=path%>/outer/signcontract/createContract.action" name="form1" id="form1"  onsubmit="return false;">
	<input type="hidden" id="projectID" name="projectID" value="${projectID }" />
	<input type="hidden" id="startID" name="startID" value="${startID }" />
	<input type="hidden" name="houseId" value="${houseId }" />
	<input type="hidden" name="type" id="type" value="" />
	<input type="hidden" name="earnestID" id="earnestID" value="${earnestID }" />
	<input type="hidden" name="ctype" value="${ctype }" />
	<br/>
	<table class="input_table" cellpadding="5" cellspacing="1px" style="width:100%;background-color: #7DD5FF">
		<tr height="25">
			<td class="input_left" colspan="5" align="center"><font color="#000000" size="5"><strong>房屋权属查询结果</strong></font></td>
		</tr>
		
		<%CHFlatVO chFlatVO=(CHFlatVO)request.getAttribute("chFlatVO");
			if(chFlatVO!=null){
		 %>		
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" width="5%" rowspan="4" align="center"><font style="color: white">房<br/>屋<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">幢号</font></td>
			<td class="input_right" width="30%">
				<input class="easyui-textbox" type="text" name="doorNum" id="doorNum"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("doornum")==null?"":chFlatVO.getAttribute("doornum") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF" width="15%"><font style="color: white">部位</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="part" id="part"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("part")==null?"":chFlatVO.getAttribute("part") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">建筑面积</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="buildingArea" id="buildingArea"  data-options="validType:'length[0,50]',editable:false"  value="<%=chFlatVO.getAttribute("buildingarea")==null?"":chFlatVO.getAttribute("buildingarea") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">层数</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="totalFloors" id="totalFloors"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("totalfloors")==null?"":chFlatVO.getAttribute("totalfloors") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋类型</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="houseType" id="houseType"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housetype")==null?"":chFlatVO.getAttribute("housetype")%>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋结构</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="houseArch" id="houseArch"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("housearch")==null?"":chFlatVO.getAttribute("housearch")%>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">产权来源</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="sourceID" id="sourceID"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("sourceid")==null?"":chFlatVO.getAttribute("sourceid")%>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">竣工日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="finishEndDate" id="finishEndDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=chFlatVO.getAttribute("finisheddate")==null?"":chFlatVO.getAttribute("finisheddate")%>" style="width: 300px"></input>
			</td>
		</tr>
		<%
			}
		 %>
		
		<%CHFlatVO landInfo=(CHFlatVO)request.getAttribute("landInfo");
			if(landInfo!=null){
		 %>	
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="3" align="center"><font style="color: white">房<br/>屋<br/>土<br/>地<br/>状<br/>况</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">地号</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="lotCode" id="lotCode"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("lotcode")==null?"":landInfo.getAttribute("lotcode") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">使用期限</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="allDate" id="allDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("limittime")==null?"":landInfo.getAttribute("limittime") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">批准用途</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="permitUsage" id="permitUsage"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("permitusage")==null?"":landInfo.getAttribute("permitusage") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">总面积</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="landArea" id="landArea"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("landarea")==null?"":landInfo.getAttribute("landarea") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">共用面积</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="blockArea" id="blockArea"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("asignedlandarea")==null?"":landInfo.getAttribute("asignedlandarea") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">使用权来源</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="landSource" id="landSource"  data-options="validType:'length[0,30]',editable:false"  value="<%=landInfo.getAttribute("landsourceStr")==null?"":landInfo.getAttribute("landsourceStr") %>" style="width: 300px"></input>
			</td>
		</tr>
		
		<%
				}
		 %>
		 
 		<%
			List<CHFlatVO> arList=(List)request.getAttribute("arList");
			if(arList!=null && arList.size()>0){
				for(int i=0;i<arList.size();i++){
					CHFlatVO realvo=arList.get(i);
		 %>		
		 length=<%=arList.size() %>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="5" align="center"><font style="color: white">产<br/>权<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权利人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="realName" id="realName"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("ownerName")==null?"":realvo.getAttribute("ownerName") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权证或证明号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="realNo" id="realNo"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("realno")==null?"":realvo.getAttribute("realno") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">共有人与共有情况</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="allRealName" id="allRealName"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("otherName")==null?"":realvo.getAttribute("otherName") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋坐落</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="location" id="location"  data-options="validType:'length[0,100]',editable:false"  value="<%=realvo.getAttribute("location")==null?"":realvo.getAttribute("location") %>" style="width: 400px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">受理日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="startDate" id="startDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("startdate")==null?"":realvo.getAttribute("startdate")  %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="passDate" id="passDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("passdate")==null?"":realvo.getAttribute("passdate")  %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">注销日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="putinDate" id="putinDate"  data-options="validType:'length[0,30]',editable:false"  value="" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">登记小类</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="tradeType" id="tradeType"  data-options="validType:'length[0,30]',editable:false"  value="<%=realvo.getAttribute("typebid")==null?"":realvo.getAttribute("typebid") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">备注</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="6" name="remark" id="remark" cols="100" class="easyui-validatebox" data-options="validType:'length[0,30]',editable:false" disabled="disabled"><%=realvo.getAttribute("memo")==null?"":realvo.getAttribute("memo") %></textarea>
			</td>
		</tr>
		<%
				}
			}
		 %>	
		 
 		<%
			List<CHFlatVO> aoList=(List)request.getAttribute("aoList");
			if(aoList!=null && aoList.size()>0){
				for(int i=0;i<aoList.size();i++){
					CHFlatVO othervo=aoList.get(i);
		 %>	
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="6" align="center"><font style="color: white">他<br/>项<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权利种类</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="otherRightType" id="otherRightType"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("otherrighttypeid")==null?"":othervo.getAttribute("otherrighttypeid") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">债权金额</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="debtAccount" id="debtAccount"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("loanyuanvalue")==null?"":othervo.getAttribute("loanyuanvalue") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房产坐落</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="otherRightPart" id="otherRightPart"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("location")==null?"":othervo.getAttribute("location") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权证或证明号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="otherRightNo" id="otherRightNo"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("otherno")==null?"":othervo.getAttribute("otherno") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">他项权利人</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="ownerName" id="ownerName"  data-options="validType:'length[0,100]',editable:false"  value="<%=othervo.getAttribute("name")==null?"":othervo.getAttribute("name") %>" style="width: 750px"></input>
			</td>
		</tr>	
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">设定日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="planStartDate" id="planStartDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("planstartdate")==null?"":DateUtil.parseDateTime3(othervo.getAttribute("planstartdate").toString()) %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">结束日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="planEndDate" id="planEndDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("planenddate")==null?"":DateUtil.parseDateTime3(othervo.getAttribute("planenddate").toString()) %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">受理日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="startDate" id="startDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("startdate")==null?"":DateUtil.parseDateTime3(othervo.getAttribute("startdate").toString()) %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="passDate" id="passDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=othervo.getAttribute("passdate")==null?"":DateUtil.parseDateTime3(othervo.getAttribute("passdate").toString()) %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">备注</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="6" name="remark" id="remark" cols="100" class="easyui-validatebox" data-options="validType:'length[0,30]',editable:false" disabled="disabled"><%=othervo.getAttribute("memo")==null?"":othervo.getAttribute("memo") %></textarea>
			</td>
		</tr>	
		<%
				}
			}
		 %>	

		<%
			List<CHFlatVO> alList=(List)request.getAttribute("alList");
			if(alList!=null && alList.size()>0){
				for(int i=0;i<alList.size();i++){
					CHFlatVO limitvo=alList.get(i);
		 %>	
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="5" align="center"><font style="color: white">限<br/>制<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">被限制人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="nameb" id="nameb"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("bname")==null?"":limitvo.getAttribute("bname") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">限制人</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="names" id="names"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("name")==null?"":limitvo.getAttribute("name") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">房屋坐落</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="limitPart" id="limitPart"  data-options="validType:'length[0,100]',editable:false"  value="<%=limitvo.getAttribute("location")==null?"":limitvo.getAttribute("location") %>" style="width: 400px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权证或证明号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="limitNo" id="limitNo"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("limitno")==null?"":limitvo.getAttribute("limitno") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">设定日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="planStartDate" id="planStartDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("planstartdate")==null?"":DateUtil.parseDateTime3(limitvo.getAttribute("planstartdate").toString()) %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">结束日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="planEndDate" id="planEndDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("planenddate")==null?"":DateUtil.parseDateTime3(limitvo.getAttribute("planenddate").toString()) %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">受理日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="startDate" id="startDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("startdate")==null?"":DateUtil.parseDateTime3(limitvo.getAttribute("startdate").toString()) %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="passDate" id="passDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=limitvo.getAttribute("passdate")==null?"":DateUtil.parseDateTime3(limitvo.getAttribute("passdate").toString()) %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">备注</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="6" name="remark" id="remark" cols="100" class="easyui-validatebox" data-options="validType:'length[0,30]',editable:false" disabled="disabled"></textarea>
			</td>
		</tr>
		<%
				}
			}
		 %>	
		 
		<%
			List<CHFlatVO> ahList=(List)request.getAttribute("ahList");
			if(ahList!=null && ahList.size()>0){
				for(int i=0;i<ahList.size();i++){
					CHFlatVO hirevo=ahList.get(i);
		 %>	
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" rowspan="6" align="center"><font style="color: white">租<br/>赁<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">出租人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="lessor" id="lessor"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("name")==null?"":hirevo.getAttribute("name") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">出租类型</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="lendType" id="lendType"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("hiretype")==null?"":hirevo.getAttribute("hiretype") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">承租人</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="lessee" id="lessee"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("hirename")==null?"":hirevo.getAttribute("hirename") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">租赁用途</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="lendusage" id="lendusage"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("hireuse")==null?"":hirevo.getAttribute("hireuse") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">坐落</font></td>
			<td class="input_right" colspan="3">
				<input class="easyui-textbox" type="text" name="limitPart" id="limitPart"  data-options="validType:'length[0,100]',editable:false"  value="<%=hirevo.getAttribute("location")==null?"":hirevo.getAttribute("location") %>" style="width: 750px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">期限</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="timeBetween" id="timeBetween"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("limittime")==null?"":hirevo.getAttribute("limittime") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">权证或证明号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="rightNo" id="rightNo"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("hireno")==null?"":hirevo.getAttribute("hireno") %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">受理日期</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="startDate" id="startDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("startdate")==null?"":DateUtil.parseDateTime3(hirevo.getAttribute("startdate").toString()) %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">核准日期</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="passDate" id="passDate"  data-options="validType:'length[0,30]',editable:false"  value="<%=hirevo.getAttribute("passdate")==null?"":DateUtil.parseDateTime3(hirevo.getAttribute("passdate").toString()) %>" style="width: 300px"></input>
			</td>
		</tr>
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">备注</font></td>
			<td class="input_right" colspan="3">
				<textarea rows="6" name="remark" id="remark" cols="100" class="easyui-validatebox" data-options="validType:'length[0,30]',editable:false" disabled="disabled"></textarea>
			</td>
		</tr>
		<%
				}
			}
		 %>	
        <%
        	List<HouseVO> permitList=(List)request.getAttribute("permitList");
        	if(permitList!=null && permitList.size()>0){
        		for(HouseVO hvo:permitList){
         %> 
		<tr height="25">
			<td class="input_left" bgcolor="#5BADFF" align="center"><font style="color: white">许<br/>可<br/>证<br/>信<br/>息</font></td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">许可证号</font></td>
			<td class="input_right" >
				<input class="easyui-textbox" type="text" name="permitNO" id="permitNO"  data-options="validType:'length[0,30]',editable:false"  value="<%=hvo.getAttribute("permitno")==null?"":hvo.getAttribute("permitno") %>" style="width: 300px"></input>
			</td>
			<td class="input_left" bgcolor="#5BADFF"><font style="color: white">区市编号</font></td>
			<td class="input_right">
				<input class="easyui-textbox" type="text" name="districtid" id="districtid"  data-options="validType:'length[0,30]',editable:false"  value="<%=hvo.getDistrictID() %>" style="width: 300px"></input>
			</td>
		</tr>				
        <%
        			}
        		}
         %>		 	 
	</table>
	
	
	<br/>
	<div align="center">
	<table width="600" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="E2EEFC">
		<tr>
			<td align="center" ><!--
			<%
				String status = request.getParameter("status");
				if(status!=null&&!"8".equals(status)){
			%>
			<select id="type" size="1" name="type" onchange="mychange()">
	          	<option value="0" selected="selected">---请选择合同类型----</option>
	            <option value="1">商品房预售合同</option>
	            <option value="2">商品房出售合同</option>
	            <option value="3">商品房认购协议</option>
	          </select>
			<%
				}else{
			%>
			<select id="type" size="1" name="type" onchange="mychange()">
	          	<option value="0" selected="selected">---请选择合同类型----</option>
	            <option value="1">商品房预售合同</option>
	            <option value="2">商品房出售合同</option>
	          </select>
			<%
				}
			%>
	    		-->

	    	<c:if test="${ctype=='1'}">
		    	<select id="type" size="1" name="type" onchange="mychange()">
		          	<option value="0" selected="selected">---请选择合同类型----</option>
		            <option value="1">商品房预售合同</option>
		            <c:if test="${houseStatus!='8'}">
		            <option value="3">商品房认购协议</option>
		            </c:if>
   		        </select>
	    	</c:if>
	    	<c:if test="${ctype=='2'}">
		    	<select id="type" size="1" name="type" onchange="mychange()">
		          	<option value="0" selected="selected">---请选择合同类型----</option>
		            <option value="2">商品房出售合同</option>
    		         <c:if test="${houseStatus!='8'}">
		            <option value="3">商品房认购协议</option>
		            </c:if>
		        </select>
	    	</c:if>
	    	<c:if test="${ctype=='-1'}">
	    		<select id="type" size="1" name="type" >
		          	<option value="0" selected="selected">---请选择合同类型----</option>
		        </select>
	    	</c:if>
          &nbsp;&nbsp;&nbsp;&nbsp;
           <select id="template" size="1" name="template"  style="width:130px;display:none"></select>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="button"   value="进入合同签订" name="operation"  onclick="doSignContract()" class="bottomblue" style="background-color:#4E9EEB;"/>
          <input type="button"   value="关闭" name="B2" onclick="parent.$('#openDL').dialog('close');" class="bottomblue" style="background-color:#4E9EEB;"/>
          <p></p>
		  </td>
		 </tr>
	</table>
	</div>
	<br/>
	</form>
</body>
</html>
