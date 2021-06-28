	function loadPage(name){
			var _CurrentPage = $.cookie(name+"_CurrentPage");
	 		var _PageSize = $.cookie(name+"_PageSize");
	 		var _SortType = $.cookie(name+"_SortType");
			var _SortField = $.cookie(name+"_SortField");
	 		
	 		if(_CurrentPage != null && _CurrentPage!=""){
	 			$("form").append("<input type='hidden' name='_CurrentPage' value='"+_CurrentPage+"'>")
	 			         .append("<input type='hidden' name='_PageSize' value='"+_PageSize+"'>");
	 			         
	 			$.cookie(name+"_CurrentPage","",{path:'/'});   
	 			$.cookie(name+"_PageSize","",{path:'/'});
	 		}
	 		
	 		if(_SortField != null && _SortField!=""){
	 			$("form").append("<input type='hidden' name='_SortField' value='"+_SortField+"'>")
	 			         .append("<input type='hidden' name='_SortType' value='"+_SortType+"'>");
	 			         
	 			$.cookie(name+"_SortField","",{path:'/'});   
	 			$.cookie(name+"_SortType","",{path:'/'});
	 		}
	 		
	 		var params = $.cookie(name+"params");
	 		var values = $.cookie(name+"values");
	 		
	 		if(values != null && values != ""){
	 			var paramarray = params.split(",");
	 			
		 		var valuearray = values.split(",");
		 		
		 		$.each(paramarray,function(index,param){
		 			$("input[name='"+param+"']").val(valuearray[index]);
		 			$("input[name='"+param+"']").prev().val(valuearray[index])
		 		})
		 		$.cookie(name+"params","",{path:'/'});   
	 			$.cookie(name+"values","",{path:'/'});
	 		}
		}	
	
	
	function clearPage(){
 		$("input[name=_CurrentPage]").remove();
 		$("input[name=_PageSize]").remove();
 		$("input[name=_SortField]").remove();
 		$("input[name=_SortType]").remove();
 	}
	
	function setPage(name,params){
		var _CurrentPage = $("#_CurrentPage").val();
 		var _PageSize = $("#_PageSize").val();
 		
 		var _SortType = $("#_SortType").val();
		var _SortField = $("#_SortField").val();
 		
 		$.cookie(name+"_CurrentPage",_CurrentPage,{path:'/'});
 		$.cookie(name+"_PageSize",_PageSize,{path:'/'});
 		$.cookie(name+"_SortType",_SortType,{path:'/'});
 		$.cookie(name+"_SortField",_SortField,{path:'/'});
 		
 		if(params != null && params != ""){
	 		var paramarray = params.split(",");
	 		var valuearray = new Array(paramarray.length);
	 		$.each(paramarray,function(index,param){
	 			var val = $("input[name='"+param+"']").val();
	 			
	 			valuearray[index] = val;
	 		})
	 		
	 		var values = valuearray.join(",");
	 		
	 		$.cookie(name+"params",params,{path:'/'});
	 		$.cookie(name+"values",values,{path:'/'});
 		}
 		
 		top.addCookie(name);
 		
	}
	
	
	var cookieArray = new Array();
	function addCookie(name){
		cookieArray.push(name)
	}
	
	function clearCookie(){
		while(cookieArray.length > 0){
			var item = cookieArray.pop();
			$.cookie(item+"_CurrentPage","",{path:'/'});  
 			$.cookie(item+"_PageSize","",{path:'/'});
 			$.cookie(item+"_SortType","",{path:'/'});
 			$.cookie(item+"_SortField","",{path:'/'});
 			$.cookie(item+"params","",{path:'/'});   
 			$.cookie(item+"values","",{path:'/'});
		}
	}