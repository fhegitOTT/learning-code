(function(jQuery) {
	$.fn.grid = function(opt) {
		var gridobj={
			options:{
				  ContextName:"",
				  url:"",
				  div:"griddiv",
				  titleTableID:"",
				  dataTableID:"",
				  width:"100",
				  height:"200",
				  chkdisp:true,
				  ScaleUnit:"%",//可以是px
				  Columns:[],
				  data:null,
				  perpageflag:true,
				  queryfrmid:"",//查询表单的form的id,保存查询条件
				  pageHidden:"",//在表单中放一个hidden,保存当前页的值
				  pagerowHidden:"",//在表单中放一个hidden,保存每页记录数的值
				  pagetotal:0,
				  single:true,
				  onClick:$.noop,
				  onDblClick:$.noop,
				  onLoadSuccess:$.noop  //$.noop是一个空函数.如果调用的时候没有传递这个回调函数，就用$.noop来代替执行。
				},	
				
				createTH:function() {
				   
				  	var th="<table width=\"100%\" align=\"center\" id=\""+this.options.titleTableID+"\" cellpadding=\"0\" cellspacing=\"0\">";
				  	th+="<tr>";
				  	th+="";
				  	if(this.options.chkdisp){
				  		th+="<td align=\"center\" width=\"30\"><input type=\"checkbox\" id=\""+this.options.div+"allId\"/></td>";
				  	}
				  	th+="";
				  	$.each(this.options.Columns,function(i,n){
				  		if(n.Hide!="true"){
							th+="<td align=\""+n.Align+"\" width=\""+n.Width+"\">"+n.Caption+"</td>";
						}
					});
				  	
				  	th+="</tr>";
				  	th+="</table>";
				  	return th;
				  },
						  
				  setOver:function(obj){
					  	
					  	obj.css("highless");
					  },
					  
				  setOut:function(obj){
					  	obj.css("row");
					  },
					  
				  createTBody:function() {
					   	var tbody="<input type=\"hidden\" id=\"gchkarry_all\" name=\"gchkarry_all\"/>";
					  	tbody+="<table width=\"100%\" align=\"center\"  id=\""+this.options.dataTableID+"\" cellpadding=\"0\" cellspacing=\"0\">";
					  	
					  	var datalist=this.options.data.DataList;
					  	var options=this.options;
					  	var trcount=0;
					  	 $.each(datalist,function(key,value){
					  	 		var data=value;
					  	 		var i=0;
					  	 		var his=1;
					  	 		
					  	 		tbody+="<tr class=\"row\" id=\"tr_"+(trcount+1)+"\">";
					  	 		trcount+=1;
					  	 		if(options.chkdisp){
					  	 			tbody+="<td align=\"center\" width=\"30\"><input type=\"checkbox\" name=\""+options.div+"box\"/>";
					  	 		}
					  	 		
					  	 		
					  	 		$.each(data,function(key,value){
					  	 			if(options.Columns[i].Hide!="true"){
					  	 				if(his==0){
					  	 					tbody+="<td align=\""+options.Columns[i].Align+"\" width=\""+options.Columns[i].Width+"\"><span>"+data[options.Columns[i].Code]+"</span></td>";
					  	 				}
									}else{
										tbody+="<input type=\"hidden\" id=\"k_"+value+"\" name=\""+options.div+"colkey\" value=\""+data[options.Columns[i].Code]+"\" /></td>";
										his=0;
									}
					  	 			i+=1;
						      });
						      tbody+="</tr>";
							});
							tbody+="</table>";
							this.options.height=23*(trcount+2);
							
							return tbody;
					  },
						  
			  createPaging:function(){
				 	var p="";
				 	var ContextName=this.options.ContextName;
				 	var div=this.options.div;
				 	var data=this.options.data;
				 	
				 	p+="<table style=\"width:100%\" id=\"pagingTable\" cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" align=\"center\"><tr>";
					//p+="     <td width=\"30\">&nbsp;</td>";
					p+="     <td align=\"center\" width=\"100\">";
					p+="     <img id=\""+div+"p_first\" src=\""+ContextName+"/res/systemx/js/datagrid/fe-8.png\" style=\"cursor:pointer\" onMouseOver=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-4.png'\" onMouseOut=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-8.png'\" width=\"10\" height=\"10\"/>&nbsp;&nbsp;";
					p+="     <img id=\""+div+"p_pre\" src=\""+ContextName+"/res/systemx/js/datagrid/fe-6.png\" style=\"cursor:pointer\"  onMouseOver=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-2.png'\" onMouseOut=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-6.png'\" width=\"10\" height=\"10\"/>&nbsp;&nbsp;";
					p+="     <img id=\""+div+"p_next\" src=\""+ContextName+"/res/systemx/js/datagrid/fe-5.png\" style=\"cursor:pointer\"  onMouseOver=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-1.png'\" onMouseOut=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-5.png'\" width=\"10\" height=\"10\"/>&nbsp;&nbsp;";
					p+="     <img id=\""+div+"p_last\" src=\""+ContextName+"/res/systemx/js/datagrid/fe-7.png\" style=\"cursor:pointer\"  onMouseOver=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-3.png'\" onMouseOut=\"this.src='"+ContextName+"/res/systemx/js/datagrid/fe-7.png'\" width=\"10\" height=\"10\"/>&nbsp;&nbsp;</td>";
					p+="     <td>(第"+data.PageNo+"页,共"+data.PageCount+"页/"+data.RowCount+"条记录)</td>";
					p+="     <td align=\"right\">每页";
					p+="       <input id=\""+div+"_pagerows\" value=\""+data.PageRows+"\" size=\"4\" type=\"text\" class=\"rowno\"/>";
					p+="       条记录 | 转到第";
					p+="       <input id=\""+div+"_pageno\" value="+data.PageNo+" size=\"4\" type=\"text\" class=\"rowno\"/>";
					p+="       页<input type=\"button\" id=\""+div+"_pagego\" value=\"go\" class=\"button\"/></td>";
					//p+="     <td width=\"30\"></td>";
					p+="</tr></table>";
					
				  	this.options.pagetotal=data.PageCount;
				 	return p;
				 },
									 
				 pageGO:function(){
					 	var options=this.options;
					 	var obj=this;
					 	var rowcount=options.data.RowCount;
					  	var pagerowstxt=$("#"+options.div+"_pagerows");
					  	var pagetnoxt=$("#"+options.div+"_pageno");
					  	var hpagerows=$("#"+options.pagerowHidden);
					  	var hpage=$("#"+options.pageHidden);
					  	
					  	hpagerows.val(pagerowstxt.val());
					  	hpage.val(pagetnoxt.val());
					  	var pagerows = hpagerows.val();
					  	var pageno = hpage.val();
					  	if(pagerows<=0){
					  		alert("每页显示条数必须大于0，请重新输入！");
					  		return false;
					  	}
					  	if(pageno<=0){
					  		alert("页码必须大于0，请重新输入！");
					  		return false;
					  	}
					  	var pagecount=Math.round(parseFloat(rowcount/pagerows)+parseFloat(0.5));
					  	
					  	if(pageno>pagecount){
					  		alert("您输入的页码和每页显示条数有误，超过了总记录数，请重新输入！");
					  		return false;
					  	}
					  	
					  		this.showMask();
					 		var frm=$("#"+options.queryfrmid);
					 		var frmdata="";
					 		if(typeof(frm)!= undefined){
					 			frmdata=frm.serialize();
					 		} 
					 		$.post(options.url,frmdata, function(data) {
					 			
								var ajaxdata = eval("(" + data + ")");
								options.data=ajaxdata;
								obj.create();
								obj.hiddenMask();
							}, "text");
					  	
					  },
										  
										  
			 tofirst:function() {
				 	var options=this.options;
				 	var obj=this;
				 	var cp=parseInt($("#"+options.pageHidden).val());
				 	if(cp==1){
				 		return false;
				 	}else{
				 		this.showMask();
				 		var frm=$("#"+options.queryfrmid);
				 		var frmdata="";
				 		if(typeof(frm)!= undefined){
				 			$("#"+options.pageHidden).val(1);
				 			frmdata=frm.serialize();
				 		} 
				  
				 		$.post(options.url,frmdata, function(data) {
				 			
							var ajaxdata = eval("(" + data + ")");
							options.data=ajaxdata;
							obj.create();
							
							obj.hiddenMask();
						}, "text");
				 	}
				 },
				 topre:function() {
				 	var options=this.options;
				 	var obj=this;
				 	var cp=parseInt($("#"+options.pageHidden).val());
				 	if(cp==1){
				 		return false;
				 	}else{
				 		this.showMask();
				 		
				 		var frm=$("#"+options.queryfrmid);
				 		var frmdata="";
				 		if(typeof(frm)!= undefined){
				 			$("#"+options.pageHidden).val(cp-1);
				 			frmdata=frm.serialize();
				 		}
				 		
				 		$.post(options.url,frmdata, function(data) {
							var ajaxdata = eval("(" + data + ")");
							options.data=ajaxdata;
							obj.create();
							obj.hiddenMask();
						}, "text");
				 	}
				 	
				 },
				 tonext:function() {
				 	var options=this.options;
				 	var obj=this;
				 	var cp=parseInt($("#"+options.pageHidden).val());
				 	
				 	if(cp==options.pagetotal){
				 		return false;
				 	}else{
				 		this.showMask();
				 		
				 		var frm=$("#"+options.queryfrmid);
				 		var frmdata="";
				 		
				 		if(typeof(frm)!= undefined){
				 			$("#"+options.pageHidden).val(cp+1);
				 			frmdata=frm.serialize();
				 		}
				 		var hpagerows=$("#"+this.options.pagerowHidden);
				 		
				 		$.post(options.url,frmdata, function(data) {
				 			
							var ajaxdata = eval("(" + data + ")");
							options.data=ajaxdata;
							obj.create();
							obj.hiddenMask();
						}, "text");
				 	}
				 	
				 },
				 tolast:function() {
				 	var options=this.options;
				 	var obj=this;
				 	var cp=parseInt($("#"+options.pageHidden).val());
				 	if(cp==options.pagetotal){
				 		return false;
				 	}else{
				 		this.showMask();
				 		
				 		var frm=$("#"+options.queryfrmid);
				 		var frmdata="";
				 		if(typeof(frm)!= undefined){
				 			$("#"+options.pageHidden).val(options.pagetotal);
				 			frmdata=frm.serialize();
				 		}
				 		$.post(options.url,frmdata, function(data) {
							var ajaxdata = eval("(" + data + ")");
							options.data=ajaxdata;
							obj.create();
							obj.hiddenMask();
						}, "text");
				 	}
				 	
				 },
												 
				 paginginit:function(){
					 	var div=this.options.div;
					 	var obj=this;
					  	var p_first=$("#"+div+"p_first");
					  	p_first.bind('click', function(event){
					      	obj.tofirst();
					    });
					    var p_pre=$("#"+div+"p_pre");
					  	p_pre.bind('click', function(event){
					      	obj.topre();
					    });
					    var p_next=$("#"+div+"p_next");
					  	p_next.bind('click', function(event){
					      	obj.tonext();
					    });
					    var p_last=$("#"+div+"p_last");
					  	p_last.bind('click', function(event){
					      	obj.tolast();
					    });
					    var pagego=$("#"+div+"_pagego");
					    pagego.bind('click', function(event){
					      	obj.pageGO();
					    });
					  },
					  
					  
				  showMask:function(){
				   		var options=this.options;
				   		var mask=$("#gridmaskdiv");
				   		if(typeof(mask)== undefined){
				   			$("#aaa").append("<div id=\"gridmaskdiv\" class=\"tblmask\"><img src='css/img/loading1.gif'/><br></br><p>数据加载中...</p></div>");
				   			mask.css({position:"absolute",zIndex:1000,top:divtop,left:divleft});
				   		}else{
					   		var div=$("#"+options.div);
					   		var offset = div.position();
					   		var divtop=offset.top+ "px";
							var divleft=offset.left+ "px";
							
							mask.width(div.width());
							mask.height(div.height());
							
					   		$("#gridmaskdiv").css("display","block");
					   		
				   		}
				   		
				   	  },	  
												 
			   	  hiddenMask:function(){
				   	  
			   	  },
			   	  
			   	  setFormData:function(formdata){
				   	  	options.formdata=formdata;
				   	  },
					   	  
			   	  updateAll:function(){
				   	  	updateHeight();
				    	updateWidth();
				   	  },
				   	  

							    
			   	  updateWidth:function() {
				    	var options=this.options;
					    //更新第一列的宽度
					    var dataTableTdsFirst = this.dataTable.find('tr.row:first td');
					    dataTableTdsFirst.each(function(i){
					      if(typeof(options.Columns[i].Width)== "number")
					        $(this).width(options.Columns[i].Width);
					    });
							    							    
			    //隔行变色
				var dataTableTrs = this.dataTable.find('tr.row');
				dataTableTrs.each(function(i){
				  if((i+1)%2==0)
					$(this).addClass('high');
				});
							
				//设置表头宽度
			    var titleTableTds = this.titleTable.find('tr td');
			    titleTableTds.each(function(i){
			    	if(typeof(options.Columns[i].Width)== "number"){
			    		
			    		$(this).width(options.Columns[i].Width);
			    	}
			    });
			    
				
			   	
			    //更新表格宽度(判断是否需要显示滚动条)
			    if(this.dataDiv.get(0).clientHeight < this.dataDiv.get(0).scrollHeight) {
			    
			      this.titleTable.width(this.titleDiv.width()-15);
			      this.dataTable.width(this.titleDiv.width()-15);
			      //如果显示滚动条,则需要再次更新第一列宽度
			      dataTableTdsFirst.each(function(i){
			        if(typeof(options.Columns[i].Width)== "number")
			          $(this).width(options.Columns[i].Width);
			        else//取表头的字段宽度
			          $(this).width(titleTableTds.eq(i).width());
			      });
			    } else {
			      this.titleTable.width("100%");
			      this.dataTable.width("100%");
			    }
			  },
			  updateHeight:function() {
		   		
			    switch(typeof(this.options.height)) {
			      case 'number' : this.dataDiv.height(parseInt($("#"+this.options.pagerowHidden).val())*32);break;
			      case 'function' : this.dataDiv.height(this.options.height());break;
			    }
			  },
			  
			  checkinit:function(){
				  	var options=this.options;
				  	var trs=this.trs;
				  	var obj=this;
				    this.allBox = $('#'+options.div+"allId");
				    if(options.single==true)
				    	this.allBox.hide();
				    this.boxes = $('input:checkbox[name="'+options.div+'box"]');
				    var aTable = this.boxes.eq(0).parents('TABLE');
				    aTable.addClass('unselect');
				    aTable.bind('selectstart', function(){return false;});
				    var trs=$("tr.row");
				    trs.each(function(){
				      
				      $(this).unbind('click');
				      $(this).bind('click', function(event){
				      	obj.checkLine(this, event);
				      	if(options.onClick){options.onClick(obj.getCheckedLine(this,event));}
				      });
				      $(this).bind('dblclick', function(event){
				      	if(options.onDblClick){options.onDblClick(obj.getCheckedLine(this,event));}
				      });
				    });
				    /*
				    this.boxes.each(function(){
				      var pTr = $(this).parents('TR');
				      
				      trs.push(pTr);
				      pTr.unbind('click');
				      pTr.bind('click', function(event){
				      	
				      	obj.checkLine(this, event);
				      	if(options.onClick){options.onClick(obj.getCheckedLine(this,event));}
				      });
				      pTr.bind('dblclick', function(event){
				      	if(options.onDblClick){options.onDblClick(obj.getCheckedLine(this,event));}
				      });
				    });
				    */
				    
				    this.allBox.unbind('click');
				    this.allBox.bind('click', function(){
				      obj.checkAll();
				    });
				  },
				  
			  getCheckedCode:function(){
			  		var options=this.options;
				  	var s="";
				  	this.boxes = $('input:checkbox[name="'+options.div+'box"]');
				  	this.colkeys = $('input:hidden[name=\"'+options.div+'colkey\"]'); 	
				  	for(var i=0; i<this.boxes.length; i++)
				    {
				     	if(this.boxes.eq(i).is(':checked')){
				     		s+=","+this.colkeys.eq(i).val();
				     	}
				    }
				    if(s.length>0){s=s.substring(1);}
				    
				    return s;
				  },
				  getCheckedLine:function(trObj, e){	  		
				  	var eve = window.event ? window.event : e;
				    var srcElement = eve.srcElement || eve.target;
				    var jTr = $(trObj);
				    var kobj=jTr.find('input:hidden[name='+this.options.div+'colkey]');
				    return kobj.val();
				  },
					  
			  checkLine:function(trObj, e){
			    	var options=this.options;
				    var eve = window.event ? window.event : e;
				    var srcElement = eve.srcElement || eve.target;
				  
				    
				    var jTr = $(trObj);
				    
				    var jTable = jTr.parents('TABLE');
				   
				    var jBox = jTr.find('input:checkbox[name="'+options.div+'box"]');
				    
				    
				    if(!eve.ctrlKey && !eve.shiftKey && (srcElement.tagName=='TD' || srcElement.tagName=='SPAN') && srcElement.type!='checkbox')
				    {	
				      if(!jBox.is(':checked'))
				      {		    	  
				       // jBox.attr('checked', true);
				    	jBox.prop('checked', true);
				    	
				      	if(this.options.single==true){
				      		jTable.find('tr.selected').each(function(){
				      			$(this).removeClass('selected');
						        $(this).find('input:checkbox[name="'+options.div+'box"]').removeAttr('checked');
						    });
				      	}
				        
				      }else{
				    	  jTr.removeClass('selected');
					      jTr.find('input:checkbox[name="'+options.div+'box"]').removeAttr('checked');
					    }
				    }
				    else if(eve.ctrlKey && (srcElement.tagName=='TD' || srcElement.tagName=='SPAN') && srcElement.type!='checkbox')
				    {
				      if(jBox.is(':checked')){
				    	  jBox.removeAttr('checked');
				      }		    	  		      		        
				      else{
				    	//  jBox.attr('checked', true);
				    	  jBox.prop('checked', true);
				      }
				        
				    }
				   
				    else if(eve.shiftKey)
				    {		
				      var selecteds = jTable.find('tr.selected');
				      
				      if(selecteds.length>0)
				      {
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
				        var s_index = this.boxes.index(selecteds.eq(0).find('input:checkbox[name="'+options.div+'box"]'));
				        
				        var e_index = this.boxes.index(jBox);
				       
				        if(s_index>e_index)
				        {
				          s_index += e_index;
				          e_index = s_index - e_index;
				          s_index -= e_index;
				        }
				        if(this.options.single==true){
				        	jTable.find('tr.selected').each(function(){
				        		$(this).removeClass('selected');
						        $(this).find('input:checkbox[name="'+options.div+'box"]').removeAttr('checked');
						    });
				        }
				        
				        
				        for(var i=s_index; i<=e_index; i++)
				        {
				       //   this.boxes.eq(i).attr('checked', true);
				          this.boxes.eq(i).prop('checked', true);
				          this.boxes.eq(i).parents('TR').addClass('selected');
				        }
				      }
				      else
				      {
				        
				        if(jBox.is(':checked'))
				          jBox.removeAttr('checked');
				        else{
				        //	jBox.attr('checked', true);
				        	jBox.prop('checked', true);
				        }
				          
				      }
				    }else{
				    	
				    	if(jBox.is(':checked'))
					    {		    	  
					    	jBox.prop('checked', true);
					    	
					      	if(this.options.single==true){
					      		jTable.find('tr.selected').each(function(){
					      			$(this).removeClass('selected');
							        $(this).find('input:checkbox[name="'+options.div+'box"]').removeAttr('checked');
							    });
					      	}
					        
					    }else{
					    	  jTr.removeClass('selected');
						      jTr.find('input:checkbox[name="'+options.div+'box"]').removeAttr('checked');
						}
				    }
				    
				  
				    if(jBox.eq(0).is(':checked'))
				    {jTr.eq(0).addClass('selected');}
				    else
				     {jTr.eq(0).removeClass('selected');}
				    
				    if($('input:checkbox[name="'+options.div+'box"]:checked').length==this.boxes.length){
				    //	this.allBox.attr('checked', true);
				    	this.allBox.prop('checked', true);
				    }  
				    else
				      this.allBox.removeAttr('checked');
				
				  },
						  
				  getChecked:function(){
					  	return this.getCheckedCode();
					  },
							  
							  
				  checkAll:function(){
				  		var options=this.options;
				  		var trs=$("#"+options.dataTableID).find("tr");
					    if(this.allBox.is(':checked'))
					    {
					      for(var i=0; i<this.boxes.length; i++)
					      {
					    	//this.boxes.eq(i).attr('checked', true);
					        this.boxes.eq(i).prop('checked', true);
					      }
					      for(var i=0; i<trs.length; i++)
					      {
					        trs.eq(i).addClass('selected');
					      }
					    }else{
					      for(var i=0; i<this.boxes.length; i++)
					      {
					        this.boxes.eq(i).removeAttr('checked');
					      }
					      for(var i=0; i<trs.length; i++)
					      {
					    	  trs.eq(i).removeClass('selected');
					      }
					    }
					  
					   }
					}//gridobj
			   	  
				gridobj.options = $.extend(gridobj.options, opt);
					var frm=$("#"+gridobj.options.queryfrmid);
					var frmdata="";
					if(typeof(frm)!= undefined){
						 frmdata=frm.serialize();
					} 
					
					$.ajax({
						type: "POST",
						url: gridobj.options.url,
						data:frmdata,
						dataType: "text",
						cache:false,
						success: function(data, textStatus){  //在 AJAX 请求成功时执行函数。它是一个 Ajax 事件。
							
							gridobj.options.data=eval("(" + data + ")");   //val() 函数可计算某个字符串，并执行其中的的 JavaScript 代码。
							
							gridobj.create();
							
						}
					});


		return gridobj;
	};
})(jQuery);