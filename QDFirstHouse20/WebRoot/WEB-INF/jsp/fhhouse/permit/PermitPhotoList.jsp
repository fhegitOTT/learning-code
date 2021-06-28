<%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.common.StringStamper"%>
<%@page import="com.netcom.nkestate.framework.util.DateUtil"%>
<%@page import="com.netcom.nkestate.common.Constant"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	SmUserVO vo = (SmUserVO) request.getSession().getAttribute("LgUser");
    String transactionID = request.getAttribute("transID").toString();
	String districtID = request.getAttribute("districtID").toString();
	String strDate = DateUtil.format(DateUtil.getSysDate(), "yyyyMMdd");
	String tempDistrictID = "";
	if(districtID!=null && !"".equals(districtID)){
		if(districtID.trim().length() == 1){
			tempDistrictID = "0"+districtID;
		}else{
			tempDistrictID = districtID;
		}
	}
	String key = StringStamper.encode(transactionID + tempDistrictID+strDate);
	//System.out.println("key=="+key);
	//System.out.println("keycode=="+StringStamper.decode(key));
	String imageServerURL = Constant.imageServerURL;
	long userID = vo.getUserId();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>标准收件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=path%>/js/application.js"></script>
	
</head>
<body id="body-layout" class="easyui-layout"  style="margin-right:1px;margin-left:1px;">
		<input type="hidden" name ="fileName" id ="fileName" value="123"/>
		<div align="center">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="doSavePhoto()">保存</a>&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:120px" onclick="doPrintFile()">打印收件清单</a>&nbsp;&nbsp;
			<!--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:100px" onclick="doCancel()">取消</a>&nbsp;&nbsp;-->
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:100px" onclick="append()">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:100px" onclick="removeit()">删除</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:100px" onclick="uploadPhoto()">上传图片</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:110px" onclick="doBack()">返回</a>&nbsp;&nbsp;
			<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" style="width:100px" onclick="reject()">Reject</a> -->
		</div>
		<div id="p" class="easyui-panel" title="<c:if test="${logo!='edit'}">收件材料列表</c:if>" style="width:95%;height:95%;background:#fafafa;" data-options="iconCls:'icon-collapse',fit:true,noheader:false">
			<table id="dg" class="easyui-datagrid" style="width:100%;height:500px"
			data-options="idField:'fileID',rownumbers:true,singleSelect:false,checkOnSelect:true,url:'<%=path%>/inner/permitmanage/getApplyFileList.action?permitID=${permitID}',method:'post'
			,onClickRow:onClickRow,onLoadSuccess:function(data){
				if(data){
					$.each(data.rows,function(index,item){
							if(item.receivedFlag == 1){
								$('#dg').datagrid('checkRow',index); 
							}
					})
				}
			},onCheck:function(rowIndex,rowData){
				rowData.receivedFlag = 1;
			},onUncheck:function(rowIndex,rowData){
				rowData.receivedFlag = 0;
			},onCheckAll:function(rows){
				checkAllOrNot(rows,1);
			},onUncheckAll:function(rows){
				checkAllOrNot(rows,0);
			}">
				<thead>
					<tr>
						<th data-options="field:'receivedFlag',checkbox:true ">检查</th>
						<th data-options="field:'fileName',width:300, editor:{type:'textbox'},sortable:true">文件名称</th>
						<th data-options="field:'fileCount',width:150,align:'center',editor:{type:'numberbox',options:{precision:0}}">页数</th>
						<th data-options="field:'fileType',width:150,align:'center',formatter:unitformatter, editor:{
							type:'combobox',
							options:{
								data:FileTypeName,valueField:'value',textField:'text',required:true
							}
						}">文件类型</th>
						<th data-options="field:'remark',width:300,align:'center', editor:{type:'textbox'}">备注</th>
					</tr>
				</thead>
			</table>
		</div>
			
		<script type="text/javascript">	
		var secretKey = "<%=key%>";
		var transactionID = "<%=transactionID %>";
    	var districtID = "<%=districtID %>";
    	var ClientPicturePath="C:\\shanghai_qingdao\\" + transactionID;//收件图片目录
    	var imageServerURL = "<%=imageServerURL%>";
    	var userID = "<%=userID%>";
    	var isOldTransaction =false;
		var fso;
    	var ctlShell;
		try {
			fso = new ActiveXObject("Scripting.FileSystemObject");
			ctlShell = new ActiveXObject("NKSHELL.ShellCtrl");
		} catch(e) {
			alert("创建上传控件出错<br>请确认使用IE浏览器并且下载控件安装<br>安装控件后，需要重新进入案件信息页面进行提交操作");
		}

		$(function(){
			//$('#fileName').val("321");
    		if(transactionID.length == 12){
    			isOldTransaction = true;
    		}
    	});

		function checkAllOrNot(rows,num){
			for(var i=0;i<rows.length;i++){
				rows[i].receivedFlag = num;
			}
		}
		
		var FileTypeName = [{"value":1,"text":"原件正本"},{"value":2,"text":"正本复印件"},{"value":3,"text":"原件副本"},{"value":4,"text":"副本复印件"}];

		function unitformatter(value,rowData,rowIndex){
			if(value == 0){
				return;
			}
			for(var i = 0;i<FileTypeName.length;i++){
				if(FileTypeName[i].value == value){
					return FileTypeName[i].text;
				}
			}
		}

		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'fileID'});
				//var fileName = $('#dg').getValue('fileName');
				//$('#dg').datagrid('getRows')[editIndex]['fileName'] = fileName;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{fileID:'0',receivedFlag:'1',transactionID:'${transID}',districtID:'${districtID}'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			//if (editIndex == undefined){return}
			//$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
			//editIndex = undefined;
			var entities='';
			var rows = $('#dg').datagrid('getChecked');
			for(var i = 0;i<rows.length;i++){
				if(rows[i].receivedFlag == 1 && rows[i].fileID > 0){
					entities = entities + JSON.stringify(rows[i]);
				}
			}
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doDeleteFileByID.action",
				data : {"entities":entities},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						window.location.reload();
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("删除异常！");
				}
			});	
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}

		function doCancel(){
			$('#dg').datagrid('clearSelections');
			$('#dg').datagrid('clearChecked');
			}

		function doSavePhoto(){
			//获取选中的houseID.
			accept();
			var rows = $('#dg').datagrid('getRows');
			var entities='';
			for(var i=0;i<rows.length;i++){
				entities = entities + JSON.stringify(rows[i]);
			}
			
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/doSavePhotoList.action",
				data : {"entities":entities},
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						alert(data[0].message);
						$('#dg').datagrid('reload');
						setTimeout(function(){callback();},1000);
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("保存异常！");
				}
			});
		}

		function doBack(){
			window.location = "<%=path%>/inner/permitmanage/gotoPermitQueryFrame.action?cmd=${cmd}";
		}

		function callback(){
			var staContent = "";
			var transactionID = '${transID}';
			var rows = $('#dg').datagrid('getChecked');
			for(var i = 0;i<rows.length;i++){
				if(rows[i].receivedFlag == 1 && rows[i].fileID > 0){
					staContent += rows[i].fileID+":*:"+rows[i].fileName+":*:"+rows[i].fileCount+":*:"+getFileTypeName(rows[i].fileType)+":*:"+1+":*:"+"#$#";	
				}
			}
			var content = staContent +"@@&&$$**";
			//将收件内容保存到C:\shanghai_qingdao\serialno.txt中。
			writeTxt(transactionID+"\r\n"+content);
		}
		
		function doPrintFile(){
			var iWidth = 900;//弹出窗口的宽度;
			var iHeight = 800;//弹出窗口的高度;
			var iTop = (window.screen.height-30-iHeight)/2;//获得窗口的垂直位置;
			var iLeft = (window.screen.width-10-iWidth)/2;//获得窗口的水平位置;
			var TargetUrl = "<%=path%>/inner/permitmanage/gotoPermitFileList.action?transID=${transID}&districtID=${districtID}&permitID=${permitID}";
			window.open(TargetUrl,"_blank",'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');	
		}
		
		function writeTxt(txt){
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var strFile = "C:\\shanghai_qingdao\\serialno.txt"; 
			if (!fso.FolderExists("C:\\shanghai_qingdao")){
				fso.CreateFolder ("C:\\shanghai_qingdao");
			}
			// 检查文件是否存在 
			if (fso.FileExists(strFile)){ 
			  	fso.DeleteFile(strFile);
			}
			// 创建文本文件 
			var f = fso.CreateTextFile(strFile, true); 
			f.write(txt); 
			f.Close(); // 关闭文件 
    	}
		

		function getFileTypeName(value){
			if(value == 0){
				return;
			}
			for(var i = 0;i<FileTypeName.length;i++){
				if(FileTypeName[i].value == value){
					 return FileTypeName[i].text;
				}
			}
		}

		function uploadPhoto(){
			getFileNameList();
		}
		function  getFileNameList(){
			if(isOldTransaction){//老案件
		 		 if(fso.FolderExists(ClientPicturePath)){
		 	  		var fldr = fso.GetFolder(ClientPicturePath);
			        var ff = new Enumerator(fldr.Files);  
			        var fileName = '';  
			        var name = "";
			        //获取filename的值
			        var ff2 = new Enumerator(fldr.Files);
			        for(; !ff2.atEnd(); ff2.moveNext()){  
				        name = fso.GetFileName(ff2.item());
				        if(name.indexOf("&") != -1 ){
			           		fileName += name + "&&";  
			           	}
			        }
		 	  		$('#fileName').val(fileName);
		 	  		//检查收件图片个数以及格式
		 	  		checkAccFile();
		 	  }else{
		 	  		alert("客户机不存在此目录"+"C:\\shanghai_qingdao\\"+transactionID);
		 	  		return false;
		 	  }
		 	}else{
		 	  if(fso.FolderExists(ClientPicturePath)){
		 	  		var fldr = fso.GetFolder(ClientPicturePath);
			        var fileName = '';  
			        var name = "";
			        //获取filename的值
			        var ff2 = new Enumerator(fldr.Files);
			        for(; !ff2.atEnd(); ff2.moveNext()){  
				        name = fso.GetFileName(ff2.item());
				        if(name.indexOf("&") != -1 && name.length == 22){
			           		fileName += name + "&&";  
			           	}
			        }
			        $('#fileName').val(fileName);
		 	  		//检查收件图片个数以及格式
		 	  		checkAccFile();
		 	  }else{
		 	  		alert("客户机不存在此目录"+"C:\\shanghai_qingdao\\"+transactionID);
		 	  		return false;
		 	  }
		 	  }	 	
		}

		function checkAccFile(){
			var fileName = $('#fileName').val();
			var data = {transactionID:transactionID,districtID:districtID,filenameList:fileName};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : "<%=path%>/inner/permitmanage/checkStandFile.action",
				data : data,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						//alert(data[0].message);
						uploadFileToFTP();
					}else{
						alert(data[0].message);
					}
				},
				error : function(){
					alert("检查异常！");
				}
			});
		}

		function uploadFileToFTP(){
			//得到文件目录
			var folder = fso.GetFolder(ClientPicturePath);
			var fc = new Enumerator(folder.files);
			var filename = "";
			var newfilename = "";
			try{
				for (; !fc.atEnd(); fc.moveNext()){
					filename = fso.GetFileName(fc.item());
					if(isOldTransaction){//老案件上传全部文件
						if(filename.indexOf("&") != -1 ){
							newfilename = ClientPicturePath+"\\"+filename.replace("&","@@@@");
							fso.MoveFile(ClientPicturePath+"\\"+filename,newfilename);
						}
					}else{//新案件上传全部   图片名称的3-4位为 “1” 的图片文件 ，01&10000001001_01.jpg
						if(filename.indexOf("&") != -1 && filename.substr(3,1) == '1'){
							newfilename = ClientPicturePath+"\\"+filename.replace("&","@@@@");
							fso.MoveFile(ClientPicturePath+"\\"+filename,newfilename);
						}
					}
					
				}
			}catch(e){
				alert(e);
			}			
			var filecount = ctlShell.GetFileCount(ClientPicturePath,"*.jpg");
			sleepll(500);
			var TargetUrl = imageServerURL+"/service/permit/upload?sourceType=0&fileCount=" + filecount+"&transactionID="+transactionID +"&districtID=" + districtID + "&userID=" + userID +"&secretKey="+secretKey;
			//上传文件
			try {
				var ret = ctlShell.UploadFolder(TargetUrl,ClientPicturePath,"*.jpg","true");
			}catch(e) {
				alert(e);
			}
			var fc1 = new Enumerator(folder.files);
			for (; !fc1.atEnd(); fc1.moveNext()){
				filename = fso.GetFileName(fc1.item());
				if(filename.indexOf("@@@@")!=-1){
					newfilename = ClientPicturePath+"\\"+filename.replace("@@@@","&");
					fso.MoveFile(ClientPicturePath+"\\"+filename,newfilename);
				}
			}
		}

		function sleepll(n){
  			 var start=new Date().getTime();
  			 while(true) if(new Date().getTime()-start>n) break;
		}
		
	</script>
</body>
</html>
