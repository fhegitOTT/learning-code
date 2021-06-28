 <%@ page contentType = "text/html; charset=UTF-8" %>
 <html>
<head>
	<link rel="stylesheet" href="layui/css/layui.css" />
</head>

<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
	  <legend>卡片风格的Tab</legend>
	</fieldset>
	 
	<div class="layui-tab layui-tab-card">
	  <ul class="layui-tab-title">
	    <li class="layui-this">网站设置</li>
	    <li>用户管理</li>
	    <li>权限分配</li>
	    <li>商品管理</li>
	    <li>订单管理</li>
	  </ul>
	  <div class="layui-tab-content" style="height: 100px;">
	    <div class="layui-tab-item layui-show">默认宽度是相对于父元素100%适应的，你也可以固定宽度。</div>
	    <div class="layui-tab-item">2</div>
	    <div class="layui-tab-item">3</div>
	    <div class="layui-tab-item">4</div>
	    <div class="layui-tab-item">5</div>
	    <div class="layui-tab-item">6</div>
	  </div>
	</div>
	 
	<script type="text/javascript" src="layui/layui.js" ></script>
<script>
layui.use('element', function(){
  var $ = layui.jquery,
  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

  
  element.on('tab(test)', function(elem){
    location.hash = 'test='+ $(this).attr('lay-id');
  });
  
});
</script>
</body>
</html>