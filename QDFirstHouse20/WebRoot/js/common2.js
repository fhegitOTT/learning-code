

/*
 * datagrid 自定义列选选项；
 * */
var cmenu;
function createColumnMenu(datagrid){
	cmenu = $('<div/>').appendTo('body');
	cmenu.menu({
		onClick: function(item){
			if (item.iconCls == 'icon-ok'){
				$('#'+datagrid).datagrid('hideColumn', item.name);
				cmenu.menu('setIcon', {
					target: item.target,
					iconCls: ''
				});
			} else {
				$('#'+datagrid).datagrid('showColumn', item.name);
				cmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
	var fields = $('#'+datagrid).datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		var field = fields[i];
		var col = $('#'+datagrid).datagrid('getColumnOption', field);
		if(col.hidden){
			cmenu.menu('appendItem', {
				text: col.title,
				name: field,
				iconCls: ''
			});
		}else{
			cmenu.menu('appendItem', {
				text: col.title,
				name: field,
				iconCls: 'icon-ok'
			});
		}
	}
}


/*
 * 显示cell被截断内容
 * 
 */
function doCellTips(datagrid) {
    $('#'+datagrid).datagrid('doCellTip', {
        onlyShowInterrupt : true,
        position : 'bottom',
        maxWidth : '200px',
        specialShowFields : [ {
            field : 'status',
            showField : 'statusDesc'
        } ],
        tipStyler : {
            'backgroundColor' : '#ffffff',
            borderColor : '#000000',
            boxShadow : '1px 1px 3px #292929'
        }
    	});
	}
/*
 * 日期序列格式化
 * 
 */
function dataFormatter(value){
	for(var item in value){  
        if(item=='time'){  //item 表示Json串中的属性，如'name'  
            var date =new Date(value[item]);//key所对应的value  
            var year = date.getFullYear();//年
            var month = date.getMonth()+1;//月
            var day = date.getDate();//日
            var hours = date.getHours();//时
            var minutes = date.getMinutes();//分
            var seconds	= date.getSeconds();//秒
            //return year+'-'+(month<10?'0'+month:month)+'-'+(day<10?'0'+day:day)+' '+(hours<10?'0'+hours:hours)+':'+(minutes<10?'0'+minutes:minutes)+':'+(seconds<10?'0'+seconds:seconds);
            return year+'-'+(month<10?'0'+month:month)+'-'+(day<10?'0'+day:day);
        }  
	}  
	return "";
}

//列表字典表加载
var pathName = document.location.pathname;
var index = pathName.substr(1).indexOf("/");
var contextPath = pathName.substr(0,index+1);
function loadDict(dictName,value){
	var name ;
	$.ajax({
		url: contextPath +"/ComplaintAction/queryDictList.action?dictName="+dictName,
		data:'',
		async: false, 
		dataType:'json',
		type:'POST',
			success:function(jsonArr){
				$.each(jsonArr, function(idx, obj) {
				   if(obj.code == value){
				   		name=obj.name;
				   }
				});
			}
   		});
		return name;
}

//列表字典表加载
function loadUserName(value){
	var user ;
	$.ajax({
		url: contextPath +"/dictajax/dictajax!getUserJson?userid="+value,
		data:'',
		async: false, 
		dataType:'json',
		type:'POST',
			success:function(jsonArr){
				$.each(jsonArr, function(idx, obj) {
					   if(obj.id == value){
						   user=obj.name;
					   }
				});
			}
   		});
	return user;
}
