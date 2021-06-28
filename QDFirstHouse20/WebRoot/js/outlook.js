var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath+"/";


$(function(){
	//InitLeftMenu(_menus1);
	tabClose();
	tabCloseEven();




/*	$('#tabs').tabs('add',{
		title:'',
		content:createFrame('')
	}).tabs({
        onSelect: function (title) {
            var currTab = $('#tabs').tabs('getTab', title);
            var iframe = $(currTab.panel('options').content);

			var src = iframe.attr('src');
			if(src)
				$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

        }
    });*/

})


//清空面板
function emptyMenu() {
/*	alert(0+"a")
	var panels = $('#nav').accordion('panels');
	alert(0+"b")
	var arr=[];
	alert(0+"c")
	alert(panels.length)
	for(var i=0;i<panels.length;i++){
		alert(i+"=")
	  var oo= panels[i].panel('options').title;
		alert(oo);
		arr[i]=oo;
	}
	arr.forEach(del);
	
	function del(t) {
		alert("p")
		//var t = panels[i].panel('options').title;
		$("#nav").accordion('remove', t);
		
	}*/
	//alert(0)
	var panels = $('#nav').accordion('panels');
	//alert(2)
	var len=panels.length;
	//alert(3)
	for ( var i = 0; i < len; ) {
		var t = panels[i].panel('options').title;
		$("#nav").accordion('remove', t);
		len=len-1;
	 }
}




// 初始化左侧
function InitLeftMenu(_menus) {
	$("#nav").accordion({animate:false});
	var status = 0;
	var menuTotalList = '';
    $.each(_menus.menus, function(i, n) {
    	var menulist ='';
		menulist +='<ul style="  margin:0px;padding:0px;">';
		if(n.url!="" && n.url!=undefined){
			status = 1;
			menulist += '<li><div><a ref="'+n.menuid+'" rel="' +basePath+ n.url + '"><span class="icon '+n.icon+'" >&nbsp;</span><span class="nav">' + n.menuname + '</span></a></div></li> ';
		}
		if(n.menus!=undefined){
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div><a ref="'+o.menuid+'" rel="' +basePath+ o.url + '"><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
	        })
		}
		menulist += '</ul>';
		if(n.url=="" || n.url==undefined){
		$('#nav').accordion('add', {
	        title: n.menuname,
	        content: menulist,
	        iconCls: 'icon ' + n.icon
        });
		}
		menuTotalList += menulist;
    });
    
    if(status==1){
    	$('#nav').accordion('add', {
	        title: ' ',
	        content: menuTotalList,
	        iconCls: 'icon ' 
        });
    	
    }

    $('.easyui-accordion li').click(function(){
		var tabTitle = $(this).find('a').children('.nav').text();
		var url = $(this).find('a').attr("rel");
		var menuid = $(this).find('a').attr("ref");
		addTab(tabTitle,url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).children('div').addClass("selected");
	}).hover(function(){
		$(this).children('div').addClass("hover");
	},function(){
		$(this).children('div').removeClass("hover");
	});
    
    
/*	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		addTab(tabTitle,url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});*/

	//选中第一个
	var panels = $('#nav').accordion('panels');
	//alert(panels.length)
	var t = panels[0].panel('options').title;
	//alert(t)
    $('#nav').accordion('select', t);
    
    
/*	$('#nav').accordion({
		onSelect: function(title,index){
			var panels = $('#nav').accordion('panels');
			var tabTitle = panels[index].find('.nav:first').text();
		    var url = panels[index].find('li a:first').attr('rel');
		    panels[index].find('li:first').children("div").addClass("selected");
		    addTab(tabTitle,url);
	}
	});*/
    
    //选中第一个连接
    if(panels.length>0){
	    var tabTitle = panels[0].find('.nav:first').text();
	    var url = panels[0].find('li a:first').attr('rel');
	    panels[0].find('li:first').children("div").addClass("selected");
    	addTab(tabTitle,url);
    }
    
    
}


//获取左侧导航的图标
function getIcon(menuid,NavMenu){
	//alert("getIcon")
	var icon = 'icon ';
	$.each(NavMenu.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function addTab(subtitle,url){
	//alert("addtab")
	if(!$('#tabs').tabs('exists',subtitle)){
		//alert("addtab1")
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true
			//icon:icon
		});
	}else{
		//alert("addtab2")
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
		var currentTab = $('#tabs').tabs('getSelected');
		var url = $(currentTab.panel('options')).attr('href');
		$('#tabs').tabs('update', {
			tab: currentTab,
			options: {
			href: url
		}
		});
		currentTab.panel('refresh');
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
