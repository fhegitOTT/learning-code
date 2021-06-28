jQuery.param=function( a ) {
	var s = [ ];
	var encode=function(str){
		str=encodeURL(str);
		return str;
	};
	function add( key, value ){
		s[ s.length ] = encode(key) + '=' + encode(value);
	};
	// If an array was passed in, assume that it is an array
	// of form elements
	if ( jQuery.isArray(a) || a.jquery )
		// Serialize the form elements
		jQuery.each( a, function(){
			add( this.name, this.value );
		});

	// Otherwise, assume that it's an object of key/value pairs
	else
		// Serialize the key/values
		for ( var j in a )
			// If the value is an array then the key names need to be repeated
			if ( jQuery.isArray(a[j]) )
				jQuery.each( a[j], function(){
					add( j, this );
				});
			else
				add( j, jQuery.isFunction(a[j]) ? a[j]() : a[j] );

	// Return the resulting serialization
	return s.join("&").replace(/%20/g, "+");
}

		
		

	function encodeURL(s) {
		var img = document.createElement("img");
		// escapeDBC 对多字节字符编码的函数
		function escapeDBC(s) {
			if (!s) return ""
			if (window.ActiveXObject) {
			// 如果是 ie, 使用 vbscript
				execScript('SetLocale "zh-cn"', 'vbscript');
				return s.replace(/[\d\D]/g, function($0) {
						window.vbsval = "";
						execScript('window.vbsval=Hex(Asc("' + $0 + '"))', "vbscript");
						return "%" + window.vbsval.slice(0,2) + "%" + window.vbsval.slice(-2);
				});
			}
			// 其它浏览器利用浏览器对请求地址自动编码的特性
			img.src = "nothing.action?separator=" + s;
			return img.src.split("?separator=").pop();
		}
		// 把 多字节字符 与 单字节字符 分开，分别使用 escapeDBC 和 encodeURIComponent 进行编码
		return s.replace(/([^\x00-\xff]+)|([\x00-\xff]+)/g, function($0, $1, $2) {
				return escapeDBC($1) + encodeURIComponent($2||'');
		});
	}