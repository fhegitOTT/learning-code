var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath+"/";



//初始化左侧
function InitLeftMenu(_menus) {
	$("#nav").accordion({animate:false});
	var status = 0;
	var menuTotalList = '';
    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
		if(n.orl!="" && n.url!=undefined){
			status = 1;
			menulist += '<li><div><a ref="'+n.menuid+'" rel="' + basePath + n.url + '"><span class="icon '+n.icon+'" >&nbsp;</span><span class="nav">' + n.menuname + '</span></a></div></li> ';
		}
		if(n.menus!=undefined){
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div><a ref="'+o.menuid+'" rel="' + basePath + o.url + '"><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
	        })
		}
		menulist += '</ul>';
		if(n.orl=="" || n.url==undefined){
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
		//var icon = getIcon(menuid,icon);

		//addTab(tabTitle,url,icon);
		showUrl(tabTitle,url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).children('div').addClass("selected");
	}).hover(function(){
		$(this).children('div').addClass("hover");
	},function(){
		$(this).children('div').removeClass("hover");
	});
	
	//选中第一个
	var panels = $('#nav').accordion('panels');
	if(panels.length>0){
		var t = panels[0].panel('options').title;
		 var list=$("ul");
		 if(list.length>0){
	         $("ul:eq(0) li:first").children("div").addClass("selected");
		 }
	    $('#nav').accordion('select', t);
	}
  //accordion   选中事件
	$('#nav').accordion({
		onSelect: function(title,index){
			var panels = $('#nav').accordion('panels');
			var tabTitle = panels[index].find('.nav:first').text();
		    var url = panels[index].find('li a:first').attr('rel');
		    panels[index].find('li:first').children("div").addClass("selected");
		    showUrl(tabTitle,url);
	}
	});
    //选中第一个连接
    if(panels.length>0){
	    var tabTitle = panels[0].find('.nav:first').text();
	    var url = panels[0].find('li a:first').attr('rel');
	    showUrl(tabTitle,url);
    }
}



function showUrl(tabTitle,url){
	//清除列表缓存
	clearCookie();	
	$("#workFrame").attr("src",url);
	//$("#subtitle").html(node.text);
	//$("#breadcrumbs-ul").html("<li><span class='ui-icon ui-icon-home' style='font-family: \"宋体\"'>&nbsp;&nbsp;</span></li>");
	//delNav();
	//addNav(tabTitle,url,true);
	
}

function addNav(name,url,isboot){
	var lastName = $("#breadcrumbs-ul a:last").html();
	//if(lastName == name){
	//	return;
	//}

	var html = "<li class='active'><a href='javascript:void(0)'>"+name+"</a></li>";
	$("#breadcrumbs-ul li:last").removeClass("active");
	$("#breadcrumbs-ul").append(html);
	
	if(isboot){
		$("#breadcrumbs-ul a:last").click(function(){
			if($(this).parent().is(".active")){
				//alert(1)
			}else{
				$("#workFrame").attr("src",url);
				$(this).parent().nextAll().remove();
			}
		});
	}else{
		$("#breadcrumbs-ul a:last").click(function(){
			if($(this).parent().is(".active")){
				//alert(1)
			}else{
				$("#workFrame").attr("src",url);
				$(this).parent().nextAll().remove();
			}
		});
	}
}


function delNav(){
	$("#breadcrumbs-ul li:last").remove();
}





