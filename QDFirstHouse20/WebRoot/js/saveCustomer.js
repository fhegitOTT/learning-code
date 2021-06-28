
		 $.ajaxFileUpload 
        (
            { 
               	url:'../customermgr/saveCustomer!saveCustomer.action',
                type:"POST",
                fileElementId:'CertificatesPhoto1',
                dataType: 'json',
                data: {
                		loginName:$("#loginName").val(),
					},     
                success: function (data, status) 
                {
               	   window.location.reload();
                   $.messager.alert('提示信息','操作成功');
                  
                }, 
                error: function (data, status, e)
                { 
                    $.messager.alert('提示信息','操作失败');
                    return false;
                }
            } 
        ) 
	
	$(function(){
	  var ei = $("#large1");
	  ei.hide();
	  $("#img1").mousemove(function(e){
	          ei.css({top:e.pageY,left:e.pageX}).html('<img style="border:1px solid gray;" src="' + this.src + '" />').show();
	  }).mouseout( function(){
	          ei.hide("slow");
	  })
	  $("#img1").change(function(){
	          $("#CertificatesPhoto1").attr("src","file:///"+getPath(document.getElementById("CertificatesPhoto1")));
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
