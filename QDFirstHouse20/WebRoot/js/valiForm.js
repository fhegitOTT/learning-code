function isNull(element,title){
	if($(element).val()==""){
		$.messager.alert("提示信息",title);
		$(element).css("background-color","#FFFFCC");
		return true;
	}else{
		return false;
	}
}
function changeCSS(element){
	$(element).css("background-color","");
}