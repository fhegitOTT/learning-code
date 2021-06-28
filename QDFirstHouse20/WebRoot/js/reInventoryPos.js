$(document).ready(function (){
		jQuery.ajaxSetup ({cache:false});
		$("#merchantName").autocomplete("../repairmgr/repairOrder!autoCompleteMerchantName.action",{
			minChars: 1, 　　　　　　　　//激活自动完成的输入字符数
			max:20,
    		width: 150,
			httpMethod:"POST",
    		matchContains: "word",　　  //只要包含输入字符就会显示提示 
    		autoFill: true,　　　　　　　　//自动填充输入框
    		dataType: 'json', 　　　　　　//数据格式
    		extraParams:{     
				merchantName: function(){   
					return $("#merchantName").val();    //url的参数传递  
                }     
 	        }, 
    		//进行对返回数据的格式处理  
            parse: function(data){
            	   var rows = []; 
            	  // alert(data.length); 
	               for(var i=0; i<data.length; i++){    
				   		rows[rows.length] = {  
                        	data:data[i],  
				        	value:data[i],  
							//result里面显示的是要返回到列表里面的值    
                        	result:data[i]  
						};  
	               }             
                   return rows;  
            }, 
    		formatItem:function(row,i,max){
    			
    			var item=row;
    			return item;
    		}
		}).result(function(event,item){
				var url="../repairmgr/repairOrder!ajaxMerchantName.action";
    			var formData={
					merchantName:item
				};
				$.post(
					url,
					formData,
					function(data){
						var obj = jQuery.parseJSON(data);
						$("#merchantTitle").val(obj.merchantTitle);
						$("#merchantAddr").val(obj.merchantAddr);
						$("#linkPhone").val(obj.linkPhone);
						$("#merchantType").val(obj.merchantType);
						$("#merchantId").val(obj.merchantId);
						$('#edcMerchantNo').combobox({
							url:'../repairmgr/repairOrder!comBoBoxMerchantNo.action?merchantId='+$('#merchantId').val(),
							editable:false,
							valueField:'id',
							textField:'no',
							onSelect:function(record){
								$('#edcMerchantTradeId').val(record.id);
								$('#edcTerminalNo').combobox({
									url:'../repairmgr/repairOrder!comBoBoxTerminalNo.action?merchantTradeId='+record.id,
									editable:false,
									valueField:'id',
									textField:'no',
									onSelect:function(record){
										$('#edcTerminalId').val(record.id);
									}
								});
							}
						});
						$('#dccMerchantNo').combobox({
							url:'../repairmgr/repairOrder!comBoBoxMerchantNo.action?merchantId='+$('#merchantId').val(),
							editable:false,
							valueField:'id',
							textField:'no',
							onSelect:function(record){
								$('#dccMerchantTradeId').val(record.id);
								$('#dccTerminalNo').combobox({
									url:'../repairmgr/repairOrder!comBoBoxTerminalNo.action?merchantTradeId='+record.id,
									editable:false,
									valueField:'id',
									textField:'no',
									onSelect:function(record){
										$('#dccTerminalId').val(record.id);
										var url='../repairmgr/repairOrder!autoSerialNumber.action';
										var formData={
											dccTerminalId:record.id,
											edcTerminalId:$('#edcTerminalId').val()
										}
										$.post(
											url,
											formData,
											function(data){
												var obj=jQuery.parseJSON(data);
												if(obj.length<1){  //新增
													$('#serialNumberUp').val("");
													$('#cmItemIdUp').val("");
													$('#serialNumDown').val("");
													$('#cmItemIdDown').val("");								
													$.messager.alert('信息提示',"无手柄底座可以撤机!");
												}
												for(i=0;i<obj.length;i++){
													if(obj[i].upAndDown==1 || obj[i].upAndDown==3){
														$('#serialNumberUp').val(obj[i].no);
														$('#cmItemIdUp').val(obj[i].cmItemId);
													}
													if(obj[i].upAndDown==2 || obj[i].upAndDown==3){
														$('#serialNumDown').val(obj[i].no);
														$('#cmItemIdDown').val(obj[i].cmItemId);
													}
												}
										});
										
									}
								});
							}
						});
				});
		});
	});
	
 function reSavePos(){
		 $.ajaxFileUpload 
        (
            { 
               	url:'../repairmgr/reInventory!reSave.action',
                type:"POST",
                fileElementId:'file',
                dataType: 'json',
                data: {
                		serialNumberUp:$("#serialNumberUp").val(),
                		serialNumDown:$("#serialNumDown").val(),
                		edcTerminalId:$('#edcTerminalNo').combobox('getValue'),
						dccTerminalId:$('#dccTerminalNo').combobox('getValue'),
           				checkUserId:$("#checkUserId").val(),
           				memo:$("#memo").val()
					},     
                success: function (data, status) 
                { 	
               	   window.location.reload();
                   $.messager.alert('提示信息','操作成功');
                  
                }, 
                error: function (data, status, e)
                { 
                    $.messager.alert('提示信息','操作失败');
                }
            } 
        )
        
        return false; 
	}
	
	$(function(){
	  var ei = $("#large");
	  ei.hide();
	  $("#img1").mousemove(function(e){
	          ei.css({top:e.pageY,left:e.pageX}).html('<img style="border:1px solid gray;" src="' + this.src + '" />').show();
	  }).mouseout( function(){
	          ei.hide("slow");
	  })
	  $("#file").change(function(){
	          $("#img1").attr("src","file:///"+getPath(document.getElementById("file")));
	  })
	});
	
	function getPath(obj)  
{  
  if(obj)  
    {  
 
    if (window.navigator.userAgent.indexOf("MSIE")>=1)  
      {  
        obj.select();  
 
      return document.selection.createRange().text;  
      }  
 
    else if(window.navigator.userAgent.indexOf("Firefox")>=1)  
      {  
      if(obj.files)  
        {  
 
        return obj.files.item(0).getAsDataURL();  
        }  
      return obj.value;  
      }  
    return obj.value;  
    }  
}
