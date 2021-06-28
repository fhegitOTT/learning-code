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
				  perpageflag:true,  //显示分页条
				  queryfrmid:"",//查询表单的form的id,保存查询条件
				  pageHidden:"",//在表单中放一个hidden,保存当前页的值
				  pagerowHidden:"",//在表单中放一个hidden,保存每页记录数的值
				  pagetotal:0,
				  single:true,
				  onClick:$.noop,
				  onDblClick:$.noop,
				  onLoadSuccess:$.noop
				},	
				
				
				titleTable:null,
				dataTable:null,
				titleDiv:null,
				dataDiv:null,
				trs:[],
				
				
				create:function(){
					var th=this.createTH();
					var tbody=this.createTBody();
					
					//var paging="";
					//if(this.options.perpageflag){paging=this.createPaging();}
					
					//var tbl=th+tbody+paging;
					var tbl=th+tbody;
					var div=$("#"+this.options.div)
					div.html(tbl);  //设置或返回被选元素的内容
					  	
					$("#tblarea").val(tbl);
				 	this.init();
					//this.hiddenMask();
					
					if(this.options.onLoadSuccess){this.options.onLoadSuccess();}
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
				
				init:function(tbl) {
			    	this.titleTable = $("#"+this.options.titleTableID);  //$(“#id”) 不管对象是否存在都会返回 object 。
					this.dataTable = $("#"+this.options.dataTableID);
				    this.titleTable.wrap('<DIV class="tableColumn" id="titlediv"></DIV>');  //wrap() 方法把每个被选元素放置在指定的 HTML 内容或元素中。
				    this.titleDiv = this.titleTable.parent();
				    
				    this.dataTable.wrap('<DIV class="tableContent" id="dtbldiv"></DIV>');
				    this.dataDiv = this.dataTable.parent();
				    
					//this.updateHeight();
						
				   // this.updateWidth();
				    
				    //this.checkinit();
				    //this.paginginit();
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
				  
				  
				  
				 
				 
		} //gridobj
		
		
				gridobj.options = $.extend(gridobj.options, opt); //将一个或多个对象的内容合并到目标对象。  /* object2 合并到 object1 中 */
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
					success: function(data, textStatus){
						
						gridobj.options.data=eval("(" + data + ")");
						
						gridobj.create();
						
					}
				});


		return gridobj;
	};
})(jQuery);