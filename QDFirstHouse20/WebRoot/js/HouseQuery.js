/**
 * 楼盘查询
 */

$(function(){
			$("#roadName").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#laneName").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#subLaneName").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#streetNumber").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#lotID").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#lotNumber").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			$("#houseIDs").textbox({inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
				keyup:function(e){
					if(e.keyCode == 13){
						query();
					}
				}
			})
			});
			
			$("input[name='chooseType']").change(function(){
				//var checkvalue = $("input[name='chooseType']:checked").val(); 
				$('#locationID').combobox('setValue','');
				$('#buildingID').combobox('setValue','');
				$("#span_lot").hide();
				$("#span_landuse").hide();
				$("#span_location").show();
				var checkvalue = this.value; 
				if(checkvalue=='1'){//房屋坐落查询
					document.getElementById("locationNameDiv").style.display="block";
					document.getElementById("lotNumberDiv").style.display="none";
					document.getElementById("houseIDDiv").style.display="none";
					document.getElementById("locationDiv").style.display="block";
					$("#span_location").show();
					$("#span_lot").hide();
					$("#span_landuse").hide();
					//document.getElementById("roadName").focus();
				}else if(checkvalue=='2'){
					document.getElementById("locationNameDiv").style.display="none";
					document.getElementById("lotNumberDiv").style.display="block";
					document.getElementById("houseIDDiv").style.display="none";
					document.getElementById("locationDiv").style.display="block";
					$("#span_location").hide();
					$("#span_lot").show();
					$("#span_landuse").show();
					//document.getElementById("lotID").focus();
				}else if(checkvalue=='3'){
					document.getElementById("locationNameDiv").style.display="none";
					document.getElementById("lotNumberDiv").style.display="none";
					document.getElementById("houseIDDiv").style.display="block";
					document.getElementById("locationDiv").style.display="block";
					//document.getElementById("houseIDs").focus();
				}
			});

			$("input[name='chooseType'][value=1]").attr("checked",true);
		});
		
		//获取选择ID
		function findSelectID(){
			var houseIDs="";
			var tempIDS = document.getElementsByName("houseID");
			for(var i=0;i<tempIDS.length;i++){
				if(tempIDS[i].checked && tempIDS[i].value != 'on'){
					houseIDs += tempIDS[i].value+",";
				}
			}
			return houseIDs;
		}

		function getDistrictID(){
			var district = $("#districtID").textbox("getValue");
			if(district==""||district==null){
				district=0;
			}
			return parseInt(district, 10);	
		}
		
		function queryHouseWithLandUse(){
			var buildingID = $('#buildingID').combobox('getValue');
			var districtID = $('#districtID').combobox('getValue');
			var landuseID = $('#landuseComboBox').combobox('getValue');
			if(buildingID==""){
	    		return;
	    	}
	    	
	    	if(landuseID == ""){
	    		queryHouse();
	    	}else{
	    		var json = {"landuseID":landuseID,"buildingID" : buildingID,"districtID" : districtID};
	    		$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : path+"/inner/permitmanage/doSearchHousesByBuildingIDAndLanduse.action",
					data : json,
					dataType : "json",
					success : function(data){
						if(data[0].result=="success"){
							var houses = data[0].data;
							if(data[0]!=null && houses>0){
								document.getElementById("houseGrid").innerHTML = data[0].htmlView;
							}
							//else{
							//	showInfo("未查询到对应的房屋。");
							//	return;
							//}
						}else{
							showInfo(data[0].message);
							document.getElementById("houseGrid").innerHTML ="";
							return;
						}
					},
					error : function(){
					  $("#loading").empty();
						showInfo("查询房屋异常！");
					}
			 });
	      }
	    	
		}
		
		//根据lotID获取宗地用途
		function loadLandUse(lotID){
			var json = {"lotID":lotID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchLandUseByLotID.action",
				data : json,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						var landUse = data[0].data;
						if(landUse!=null && landUse.length>0){
							$("#landuseComboBox").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#landuseComboBox').combobox('loadData',landUse);
							$("#landuseComboBox").combobox({
								onChange:function(n,o){
									queryHouseWithLandUse();
								}
							});
							//$('#landuseComboBox').combobox('select',landUse[0].id);
						}
						//else{
						//	showInfo("未查询到对应的幢号。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
				  $("#loading").empty();
					showInfo("查询幢号信息异常！");
				}
		});
		}
		
		function query(){
			$('#locationID').combobox('setValue','');
			$('#buildingID').combobox('setValue','');
			document.getElementById("houseGrid").innerHTML = "";
			var chooseTypeID = $("input[name='chooseType']:checked").val(); 
			switch (chooseTypeID){
				case "1":
			  		queryLocation();
			  		break;
			  	case "2":
			  		queryLots();
			  		break;
			  	case "3":
			  		queryByHouseID();
			  		break;
			  	default: 
			  		break;
			}
		}
		var tempBuildingID=0;
		function queryByHouseID(){
			var houseIDs = $("#houseIDs").textbox("getValue");
			var districtID = $('#districtID').combobox('getValue');
			if(houseIDs == ""){
 			   $.messager.alert("提示","请输入房屋编号，再点击查询！");
 			   return;
 			}
 			if(isNaN(houseIDs)){
		       $.messager.alert("提示","请输入数字");
		       return;
	    	}
 			if((houseIDs+"").length > 19){
		    	 $.messager.alert("提示","输入房屋编号过长，无法查询");
		    	 return;
	    	}
			if(districtID == "" || districtID == null){
				$.messager.alert("提示","请先选择区市！");
				return;
			}
			var json = {"houseID" : trim(houseIDs),"districtID" : districtID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchLocationsByHouseID.action",
				data : json,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var locations = data[0].data;
						if(locations!=null&&locations.length>0){
							if(locations.length>1000){
								showInfo("结果数据过多,请精确查找！");
								return;
							}
							$("#locationID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#locationID').combobox('loadData',locations);
							$("#locationID").combobox({
								onChange:function(n,o){
								queryBuilding();
								}
							});
							$('#locationID').combobox('select',locations[0].id);
							//queryBuilding(districtID);
						}
						//else{
						//	showInfo("未查询到对应的坐落信息。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
					$.messager.alert("提示信息","查询异常！");
				}
			});
			
		}
		
		function queryLots(){
			//清空原下拉框数据
			$('#lotComboBox').combobox('setValue','');
			$('#buildingID').combobox('setValue','');
			$('#landuseComboBox').combobox('setValue','');
			var lotID = $("#lotID").textbox("getValue");
			var lotNumber = $("#lotNumber").textbox("getValue");
			if(lotID==""&& lotNumber==""){
				 $.messager.alert("提示","请输入查询条件，再点击查询！");
	    	     $("#lotID").focus();
	    	     return;
	    	}
	    	lotID=trim(lotID);
	    	lotNumber=trim(lotNumber);
    		if (lotID == "")
    			lotID = "";
    		if((lotID+"").length>19){
		    	 return $.messager.alert("提示","输入过长，无法查询");
	    	}
			var districtID = $('#districtID').combobox('getValue');
			if(districtID == "" || districtID == null){
				$.messager.alert("提示","请先选择区市！");
				return;
			}
			var data = {"lotID": lotID,"lotNumber":lotNumber,"districtID":districtID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchLots.action",
				data : data,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						var lands = data[0].data;
						if(lands!=null&&lands.length>0){
							if(lands.length>1000){
								showInfo("结果数据过多,请精确查找！");
								return;
							}
							//console.log(locations);
							$("#lotComboBox").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#lotComboBox').combobox('loadData',lands);
							$("#lotComboBox").combobox({
								onChange:function(n,o){
								queryBuildingByLotID();
								}
							});
							$('#lotComboBox').combobox('select',lands[0].id);
							loadLandUse(lands[0].id);
						}
						//else{
						//	showInfo("未查询到对应的宗地信息。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
					$.messager.alert("提示信息","查询异常！");
				}
		});
		}
		
		//地号查询幢号
    	function queryBuildingByLotID(){
    		var districtID = $('#districtID').combobox('getValue');
			var lotID = $('#lotComboBox').combobox('getValue'); 
			
			if (lotID == "")
    			lotID = "";
    		if((lotID+"").length>19){
	    		 enableButton();
		    	 return nui.alert("输入过长，无法查询","提示");
	    	}
	    	
	    	var json = {"districtID": districtID,"lotID":lotID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchBuildingsByLotID.action",
				data : json,
				dataType : "json",
				beforeSend:function(XMLHttpRequest){
	             	$.messager.progress({ 
					       　　title: '提示', 
					       　　msg: '正在加载楼幢信息，请稍候……', 
					       　　text: '' 
					});
         		},
				success : function(data){
				    $.messager.progress('close');
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var buildings = data[0].data;
						if(buildings!=null && buildings.length>0){
							//if(buildings.length>1000){
							//	$.messager.alert("提示","结果数据过多,请精确查找！");
							//	return;
							//}
							//console.log(buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#buildingID').combobox('loadData',buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									queryHouse();
								}
							});
							$('#buildingID').combobox('select',buildings[0].id);
						}
						//else{
						//	showInfo("未查询到对应的幢号。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
				  $("#loading").empty();
					showInfo("查询幢号信息异常！");
				}
		});
    	}
		
 
		function queryLocation(){
			//清空原下拉框数据
			$('#locationID').combobox('setValue','');
			//$('#buildingID').combobox('setValue','');
			var roadName = $("#roadName").textbox("getValue");
			var laneName = $("#laneName").textbox("getValue");
			var subLaneName = $("#subLaneName").textbox("getValue");
			var streetNumber = $("#streetNumber").textbox("getValue");
			if(roadName==""&& laneName==""&& subLaneName==""&&streetNumber==""){
				 $.messager.alert("提示","请输入查询条件，再点击查询！");
	    	     $("#roadName").focus();
	    	     return;
	    	}
			var districtID = $('#districtID').combobox('getValue');
			if(districtID == "" || districtID == null){
				$.messager.alert("提示","请先选择区市！");
				return;
			}
			var data = {"districtID": districtID,"roadName":roadName,"laneName":laneName,"subLaneName":subLaneName,"streetNumber":streetNumber};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchLocations.action",
				data : data,
				dataType : "json",
				success : function(data){
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var locations = data[0].data;
						if(locations!=null&&locations.length>0){
							if(locations.length>1000){
								showInfo("结果数据过多,请精确查找！");
								return;
							}
							//console.log(locations);
							$("#locationID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#locationID').combobox('loadData',locations);
							$("#locationID").combobox({
								onChange:function(n,o){
								queryBuilding();
								}
							});
							$('#locationID').combobox('select',locations[0].id);
							//queryBuilding(districtID);
						}
						//else{
						//	showInfo("未查询到对应的坐落信息。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
					$.messager.alert("提示信息","查询异常！");
				}
			});
		}

		function showInfo(msg){
			$.messager.show({title:"提示",msg:msg,showType:'slide',timeout:2000});
		}

		function queryBuilding(){
			var districtID = $('#districtID').combobox('getValue');
			var locationID = $('#locationID').combobox('getValue'); 
			var json = {"districtID": districtID,"locationID":locationID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchBuildingsByLocationID.action",
				data : json,
				dataType : "json",
				beforeSend:function(XMLHttpRequest){
	             	$.messager.progress({ 
					       　　title: '提示', 
					       　　msg: '正在加载楼幢信息，请稍候……', 
					       　　text: '' 
					});
         		},
				success : function(data){
				    $.messager.progress('close');
					if(data[0].result=="success"){
						//alert(data[0].data.length);
						var buildings = data[0].data;
						if(buildings!=null && buildings.length>0){
							//if(buildings.length>1000){
							//	$.messager.alert("提示","结果数据过多,请精确查找！");
							//	return;
							//}
							//console.log(buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									return;
								}
							});
							$('#buildingID').combobox('loadData',buildings);
							$("#buildingID").combobox({
								onChange:function(n,o){
									queryHouse();
								}
							});
							$('#buildingID').combobox('select',buildings[0].id);
						}
						//else{
						//	showInfo("未查询到对应的幢号。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(){
				  $("#loading").empty();
					showInfo("查询幢号信息异常！");
				}
		});
		}

		
		function queryHouse(){
			var districtID = $('#districtID').combobox('getValue');
			var buildingID = $('#buildingID').combobox('getValue'); 
			var json = {"districtID": districtID,"buildingID":buildingID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/doSearchHousesByBuildingID.action",
				data : json,
				dataType : "json",
				beforeSend:function(XMLHttpRequest){
	             	$.messager.progress({ 
					       　　title: '提示', 
					       　　msg: '正在加载房屋信息，请稍候……', 
					       　　text: '' 
					});
         		},
				success : function(data){
				    $.messager.progress('close');
					if(data[0].result=="success"){
						var houses = data[0].data;
						if(data[0]!=null && houses>0){
							var houseIDs = $("#houseIDs").textbox("getValue");
							var html = data[0].htmlView;
							if( houseIDs != ''){
								var houseStr = "<td>"+houseIDs+"</td>";
								var houseStr2 = "<td><font color=red>"+houseIDs+"</font></td>";
								html = html.replace(houseStr,houseStr2);
							}
							html = html.replace("<table","<table id='tbl'");
							document.getElementById("houseGrid").innerHTML = html;
						}
						//else{
						//	showInfo("未查询到对应的房屋。");
						//	return;
						//}
					}else{
						showInfo(data[0].message);
						return;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					showInfo("查询房屋信息异常！");
				}
			});
		}
		
		function queryAllRights(){
			var districtID = $('#districtID').combobox('getValue');
			$("#tbl tr").each(function (e){
				if(e>0){
					var houseID = $("#tbl").find("tr").eq(e).find("td").eq(2)[0].innerText;
					var obj = $("#tbl").find("tr").eq(e)[0];
					showTransInfo(houseID,districtID,obj);
				}
			});
		}
		
		function showTransInfo(houseID,districtID,obj){
			//var rowIndex = $(obj).index();
			//$(obj).find("td")[6].innerHTML="自定义信息";
			
			//根据houseID和districtID 查询产权抵押限制租赁文件信息
			var json = {"districtID": districtID,"houseID":houseID};
			$.ajax({
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				url : path+"/inner/permitmanage/getRights.action",
				data : json,
				dataType : "json",
				success : function(data){
					//console.log(data);
					if(data[0].result=="success"){
						showRightInfo(obj,data[0]);
					}else{
						showInfo(data[0].message);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					showInfo("查询房屋信息异常！");
				}
			});
		}
		
		function getHrefFront(str){
			return "<a href='#' onclick='javascript:showTransFrame("+str+")'>";
		}
		
		function appendRightInfo(obj,rightArr,index_td){
			var hrefEnd = "</a>";
			var hrefS = "";
			if(rightArr!=null && rightArr.length > 0){
				for(var a=0;a<rightArr.length;a++){
					var right = rightArr[a];
					if(right.transactionID == 0){
						continue;
					}
					var transIDs = "";
					var dbflag = right.dbFlag;
					var isprecert = right.isPrecert;
					var isfinish = right.isFinish;
					var ygflag = right.ygFlag;
					var lotFlag = right.lotFlag;
					if(isprecert==1){
				      transIDs+="预";
				    }
				    if(ygflag==1){
				    	transIDs+="(单方)";
				    }else if(ygflag==2){
				    	transIDs+="(双方)";
				    }
				    if(lotFlag==1){
				    	transIDs+="地";
				    }
				    transIDs += right.transactionID;
					if(dbflag==0){
				    	transIDs+="(办)";
				    }else if(isfinish!=-1){
				    	transIDs+="(未)";
				    }
					hrefS += getHrefFront(right.transactionID) + transIDs + hrefEnd;
					if(a != rightArr.length-1){
						hrefS += "<br>";
					}
				}
				$(obj).find("td")[index_td].innerHTML = hrefS;
			}
		}
		
		function showRightInfo(obj,data){
			//预告
			//var permitArr = data.permits;
			//appendRightInfo(obj,permitArr,5);
			//产权
			var realArr = data.reals;
			appendRightInfo(obj,realArr,6);
			//抵押
			var otherArr = data.others;
			appendRightInfo(obj,otherArr,7);
			//限制
			var limitArr = data.limits;
			appendRightInfo(obj,limitArr,8);
			//租赁
			var hireArr = data.hires;
			appendRightInfo(obj,hireArr,9);
			//文件
			var fileArr = data.files;
			appendRightInfo(obj,fileArr,10);
		}
		
		function showTransFrame(transactionID){
		  //alert($(obj).text());
		  //var transactionID = $(obj).text();
		  var iTop = (window.screen.availHeight-650)/2; //获得窗口的垂直位置;
		  var iLeft = (window.screen.availWidth-1300)/2; //获得窗口的水平位置;
		  var strFeatures = "height=650, width=1300, top="+iTop+", left="+iLeft+", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no";
		  window.open(path+"/inner/permitmanage/checkInfoFrame.action?transactionID="+transactionID+"&URL="+djUrl,"checkAudit", strFeatures);	
		}