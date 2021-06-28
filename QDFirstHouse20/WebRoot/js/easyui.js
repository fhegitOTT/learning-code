$.extend($.fn.tabs.methods, {
    /**
     * 绑定双击事件
     * @param {Object} jq
     * @param {Object} caller 绑定的事件处理程序
     */
    bindDblclick: function(jq, caller){
        return jq.each(function(){
            var that = this;
            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs').delegate('li', 'dblclick.tabs', function(e){
                if (caller && typeof(caller) == 'function') {
                    var title = $(this).text();
                    var index = $(that).tabs('getTabIndex', $(that).tabs('getTab', title));
                    caller(index, title);
                }
            });
        });
    },
    /**
     * 解除绑定双击事件
     * @param {Object} jq
     */
    unbindDblclick: function(jq){
        return jq.each(function(){
            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs');
        });
    }
});

/**
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};
