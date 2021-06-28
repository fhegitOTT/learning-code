$(function() { 
	$("#list tr:odd").css("background-color", "#ECF5FF"); //隔行改变背景色
	$("#selectAll").click(function() { 
		if ($(this).prop("checked") == true) { // 全选 
			//$(":checkbox").each( function() { //可以对.net服务器控件有效
			//$("input[@name='id']").each(function() { 
				//$(this).attr("checked", true); 
			//}); 
			//$(":checkbox").attr('checked', ''); //jquery 1.7.2适用
			$(":checkbox[name='id']").prop("checked", true);
		} else { // 取消全选 
			//$(":checkbox").each( function() { //可以对.net服务器控件有效
			//$(":checkbox").each(function() { 
				//$(this).attr("checked", false); 
			//}); 
			//$(":checkbox").attr('checked', this.checked); //jquery 1.7.2适用
			$(":checkbox[name='id']").prop("checked", false);
		} 
	}); 

	$(".order").click(function() { 
		var src=$(this).children("img").attr("src");
		if(src.indexOf('arrow_up')>0){
			$(this).children("img").attr("src",src.replace('arrow_up','arrow_down'));
		}else
			$(this).children("img").attr("src",src.replace('arrow_down','arrow_up'));
	});  

	$(".cutprefix").each(function() {  
        var inText = $(this).text();  
        if (inText.length > 40) {  
            $(this).text(inText.substr(0, 40) + "...");
        }  
    });  
	$(".cutsuffix").each(function() {  
        var inText = $(this).text();  
        if (inText.length > 40) {  
            $(this).text("..."+inText.substr(15));
        }  
    }); 
	//选中当前行的checkbox
	$("#list tr:odd").each(function(){
		$(this).click(function(){
			var e = $(this).children("td").children(":checkbox");
			if(e[0].checked) {
				e.prop("checked", false);
			} else {
				e.prop("checked", true);
			}
		});
		$(this).hover(function(){
			$(this).css("background-color", "#7c8577");
		}, function(){
			$(this).css("background-color", "#ECF5FF");
		});
	});
	
	$("#list tr:even").each(function(){
		$(this).click(function(){
			var e = $(this).children("td").children(":checkbox");
			if(e[0].checked) {
				e.prop("checked", false);
			} else {
				e.prop("checked", true);
			}
		});
		$(this).hover(function(){
			$(this).css("background-color", "#7c8577");
		}, function(){
			$(this).css("background-color", "#ffffFF");
		});
	});
}); 

function del()
{
	var ids='';
	$('input[name="id"]:checked').each(function(){
		ids+=$(this).val()+',';
	});
	ids=ids.substring(0, ids.length-1);
	if(ids==''){
		alert('请先选择您要删除的行！');
		return;
	}else if(confirm('警告：确认要将  '+ids+' 放入回收站吗？' + '\r' +'\r' + '一旦删除，还可以从回收站取回！')){
		if(ids!=''){
			$.ajax({
			 	url: 'del?t='+new Date().getTime(),
			 	type: 'POST',
			 	dataType: 'json',
			 	data: {ids:ids},
			 	error: function(){alert('服务器异常！');},
			 	success: function(result){
			 		window.location.reload();
				}
			}); 
		}
	}

}
function view(id,status)
{
	var status_="显示";
	if(status=='1')
		status_="隐藏";
	if(confirm('警告：确认要这行 '+status_+' 吗？')){
		$.ajax({
			 url: 'view?t='+new Date().getTime(),
			 type: 'POST',
			 dataType: 'json',
			 data: {id:id,s:status},
			 error: function(){alert('服务器异常！');},
			 success: function(result){
			 	alert(result.info);
			 	window.location.reload();
			}
		}); 
	}

}
function bdel()
{
	var ids='';
	$('input[name="id"]:checked').each(function(){
		ids+=$(this).val()+',';
	});
	if(ids!=''){
		ids=ids.substring(0, ids.length-1);
		$.ajax({
		 	url: 'bdel?t='+new Date().getTime(),
		 	type: 'POST',
		 	dataType: 'json',
		 	data: {ids:ids},
		 	error: function(){alert('服务器异常！');},
		 	success: function(result){
		 		alert(result);
		 		window.location.reload();
			}
		}); 
	}else
		alert('请先选择您要删除的行！');
}
function getQueryString(url,name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = url.match(reg);
    if (r != null) return unescape(r[2]); return null;
}
function goto_(url)
{
	var t=url.substr(url.indexOf("?")+1);
	$.cookie("pageNo",getQueryString(t,"pageNo"));//记住页号
	window.location.href=url;
	return false;
}
function export_(name)
{
	easyDialog.open({
		  container : {
		    header : '导出设定',
		    content : '<table><tr style="height:28px;"><td>表&nbsp;&nbsp;&nbsp;名: </td><td><input type="text" size="25" id="name" value="'+name+'" /></td></tr><tr style="height:28px;"><td>类&nbsp;&nbsp;&nbsp;型: </td><td><input type="radio" id="lx" class="radio"  name="lx" value="1"  checked="checked"  />EXCEL<input type="radio" class="radio"  name="lx" value="2"/>WORD<input type="radio" class="radio"  name="lx" value="3"/>PDF<input type="radio" class="radio"  name="lx" value="4"/>CVS</td></tr></table>',
		    yesFn : exportBtn,
		    noFn : true
		  }
	});
}
var exportBtn = function(){
	if($("#name").val()!=''){
		//var type = $("input:[name=lx]:radio:checked").val();
		//var type = $('input[@name=lx][@checked]').val();
		alert($("#p").val());
		var type = $('input:radio[name=lx]:checked').val();
		window.location.href='export?t='+new Date().getTime()+'&name='+encodeURI($("#name").val())+'&type='+type+'&p='+$("#p").val();
		return false;

	}else{
		alert("请填写完毕!");
		return false;
	}
};
function submitSearchForm(){
	$.cookie("pageNo",null);
	$('#myform').action=list;
	$('#myform').submit();
}

$(document).ready(
	function(){
		var pageNo =$.cookie("pageNo");//读取页号
		if(pageNo!=null){
			$("#changePage").val(pageNo);
		}
	}
);