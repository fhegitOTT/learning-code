/**
 * @author 孙宇
 */

var sy = $.extend({}, sy);/* 全局对象 */

sy.changeTheme = function(themeName) {/* 更换主题 */
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href;
	if(url!=null){
		href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	}
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
	$.cookie('easyuiThemeName', themeName, {
		path: "/",expires : 7
	});
};
if ($.cookie('easyuiThemeName')) {
	sy.changeTheme($.cookie('easyuiThemeName'));
}