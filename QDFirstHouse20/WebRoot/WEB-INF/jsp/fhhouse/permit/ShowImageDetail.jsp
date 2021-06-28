<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<%@page import="com.netcom.nkestate.common.Constant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
 	String path = request.getContextPath();
	String transactionID = String.valueOf(request.getParameter("transactionID"));
	String districtID = String.valueOf(request.getParameter("districtID"));
	String imageID = String.valueOf(request.getParameter("imageID"));
	String userID = String.valueOf(request.getParameter("userID"));
	String secretKey = String.valueOf(request.getParameter("secretKey"));
	String serviceUrl = Constant.imageServerURL;

	String TargetUrl = serviceUrl+"/service/permit/showImage?transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey;
	//System.out.println(TargetUrl);
 %>
<!-- 
  - Author(s): gcf
  - Date: 2019-01-15 11:09:55
  - Description:
-->
<head>
<title>查看影像资料</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script type="text/javascript" src="<%=path%>/js/application.js"></script>
</head>
<script>   
	  function beginDrag(elementToDrag,event) {   
	    
	  //计算元素原左上角与鼠标的距离   
	  //moveHandler要这值   
	    
	  var   delatX=event.clientX-parseInt(elementToDrag.style.left);   
	  var   delatY=event.clientY-parseInt(elementToDrag.style.top);   
	    
	  //注册响应mousemove和mousedown事件后的mouseup事件的处理程序   
	    
	  if(document.addEventListener)   {     //2级DOM事件模型   
		  //注册捕捉事件处理程序。   
		  document.addEventListener("mousemove",moveHandler,true);   
		  document.addEventListener("mouseup",upHandler,true);   
	  }   
	  else   if(document.attachEvent)   {   //IE5+   的事件模型   
		  //在IE事件模型中，我们不能捕捉事件，所以只有当事件起泡到这些处理程序时,   
		  //它们才被触发.   假设不存在干涉元素,   处理了事件后它们就停止传播   
		  document.attachEvent("onmousemove",moveHandler);   
		  document.attachEvent("onmouseup",upHandler);   
	  }   
	  else   {   //IE4事件模型   
		  //IE4我们不能使用attachEvent方法,所以存储了以前赋予的处理   
		  //程序后,直接赋予新的事件处理程序,这样可以恢复旧的处理程序.   
		  //注意,这样依赖于事件起泡.   
		  var   oldmovehandler=document.onmousemove;   
		  var   olduphandler=document.onmouseup;   
		  document.onmousemove=moveHandler;   
		  document.onmouseup=upHandler;   
	  }   
	    
	  //我们处理了该事件,不要再让其他元素看见.   
	  if(event.stopPropagation)   event.stopPropagation();   //2   级DOM   
	  else   event.cancelBubble=true; //IE   
	    
	  //下面禁止执行默认动作   
	  if(event.preventDefault)   event.preventDefault();     //2级DOM   
	  else   event.returnValue=false; //IE   
	    
	  /*     这是元素被拖动时捕捉mousemove事件的处理程序.   
	    *     它负责移动元素   
	    */   
	    
	  function   moveHandler(e)     {   
		  if(!e)   e=window.event; //IE事件模型;   
		  //把元素移动到鼠标当前的位置,根据初始鼠标点击的偏移量进行调整   
		  elementToDrag.style.left=(e.clientX-delatX)+"px";   
		  elementToDrag.style.top=(e.clientY-delatY)+"px";   
		  //不要再让其他元素看到该事件.   
		  if(e.stopPropagation)   e.stopPropagation();     //2级DOM   
		  else   e.cancelBubble=true; //IE   
	  }   
	    
	  /*     这是捕捉拖移结束最后发生的mouseup事件的处理程序.   
	    */   
	  function   upHandler(e)   {   
		  if(!e)   e=window.event; //IE事件模型.   
		  //注销捕捉事件程序.   
		  if(document.removeEventListener)   { //DOM事件模型   
		  document.removeEventListener("mouseup",upHandler,true);   
		  document.removeEventListener("mousemove",moveHandler,true);   
		  }   
		  else   if(document.detachEvent)   { //IE5+   事件模型   
		  document.detachEvent("onmouseup",upHandler);   
		  document.detachEvent("onmousemove",moveHandler);   
		  }   
		  else     { //IE事件模型   
		  document.onmouseup=olduphandler;   
		  document.onousemove=oldmovehandler;   
		  }   
		  //不要再让事件进一步传播.   
		  if(e.stopPropagation)   e.stopPropagation();     //2级DOM   
		  else   e.cancelBubble=true; //IE   
	  }   
	    
	}
  </script>
<body  style="height: 98%;padding: 0px;margin: 0px;width:98%;">
	<!--修改图片访问方式 -->
 	<img id=dd src="<%=TargetUrl%>" style="position:absolute;left:0px;top:0px;" ondblclick="createPopuButton()" onmousedown="beginDrag(this,event);" onload="setBase()"> 
	<script type="text/javascript">
		  var imagewidth,imageheight;
		  var firsthandWidth = dd.width;
		  var firsthandHeight = dd.height;
		  var i=0;
		  var rate=1;
		  var currentA = 0;
	      var currentB = 0;
		  
		  var imageID = '<%=imageID %>';
		  var imageIDs = '${imageIDs}';
		  var TargetUrl = '<%=TargetUrl %>';
		  var preImageID = "";
		  var nextImageID = "";
		  var imageArr = new Array();
		  imageArr = imageIDs.split(',');
		  
		  //上一张方法
		   function preImage(){
		   		var index = $.inArray(imageID,imageArr);
				if(index > 0 && index <= (imageArr.length-1)){
					preImageID = imageArr[index-1];
				}		
		   
				if(preImageID == ''){
					alert("已经是第一张！");
				}else{
					TargetUrl = TargetUrl.substring(0,TargetUrl.indexOf("imageID=")+8) + preImageID + TargetUrl.substring(TargetUrl.indexOf("userID=")-1);
					//alert(TargetUrl);
					document.getElementById("dd").src = TargetUrl;
					imageID = preImageID;
					preImageID = "";
				}
		  }
		  //下一张方法
		  function nextImage(){
				var index = $.inArray(imageID,imageArr);
				if(index < imageArr.length-1){
					nextImageID = imageArr[index+1];
				}	
		   
				if(nextImageID == ''){
					alert("已经是最后一张！");
				}else{
					TargetUrl = TargetUrl.substring(0,TargetUrl.indexOf("imageID=")+8) + nextImageID + TargetUrl.substring(TargetUrl.indexOf("userID=")-1);
					//alert(TargetUrl);
					document.getElementById("dd").src = TargetUrl;
					imageID = nextImageID;
					nextImageID = "";
				}
				
		  }
		  
		  //alert(firsthandWidth+"*"+firsthandHeight);
		  function setBase(){
		  	firsthandWidth = dd.width;
		  	firsthandHeight = dd.height;
		  	//alert(firsthandWidth+"*"+firsthandHeight);
		  }
		  function myload(){
				imagewidth=document.all.dd.width;
				imageheight=document.all.dd.height;
				//alert("imagewidth="+imagewidth+"\r\nimageheight="+imageheight);
				if(imagewidth > 960)
				{
					imageheight = imageheight*(960/imagewidth);
					imagewidth = 960;
					//imageheight = 720;
					//alert(imageheight);
					document.all.dd.image.width=imagewidth;
					document.all.dd.image.height=imageheight;
					
					imagewidth=document.dd.image.width;
					imageheight=document.dd.image.height;
				}
			}
			function turnA(){
				i++;
				if(i>4){i=1};
		  		dd.style.filter="progid:DXImageTransform.Microsoft.BasicImage(Rotation="+i+")";
			} 
			function turnB(){
				i--;
				if(i<0){i=3};
		  		dd.style.filter="progid:DXImageTransform.Microsoft.BasicImage(Rotation="+i+")"; 
			}
			
			function turnANew(){
				var ieVerson = IEVersion();
		        if (ieVerson == 11 || ieVerson == -1 || ieVerson == 'edge'){//如果浏览器为IE11或者其他内核浏览器。
					currentA = (currentA+90)%360;
		            document.getElementById("dd").style.transform = 'rotate('+currentA+'deg)';
				}
				 //如果浏览器为IE8
	            if (ieVerson == 8){   
					turnA();
				}     
        	};
		
			function turnBNew(){ 
				var ieVerson = IEVersion();
	         	if (ieVerson == 11 || ieVerson == -1  || ieVerson == 'edge'){//如果浏览器为IE11或者其他内核浏览器。
					currentB = (currentB-90)%360;
	                document.getElementById("dd").style.transform = 'rotate('+currentB+'deg)';
			    }
			    //如果浏览器为IE8
	           if (ieVerson == 8){   
				 	turnB();
			    }     
	        };
		
		function IEVersion() {
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
            var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
            var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
            if(isIE) {
                var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                reIE.test(userAgent);
                var fIEVersion = parseFloat(RegExp["$1"]);
                if(fIEVersion == 7) {
                    return 7;
                } else if(fIEVersion == 8) {
                    return 8;
                } else if(fIEVersion == 9) {
                    return 9;
                } else if(fIEVersion == 10) {
                    return 10;
                } else {
                    return 6;//IE版本<=7
                }   
            } else if(isEdge) {
                return 'edge';//edge
            } else if(isIE11) {
                return 11; //IE11  
            }else{
                return -1;//不是ie浏览器
            }
        }
			
			function resume(){
				i=0;
				dd.style.filter="progid:DXImageTransform.Microsoft.BasicImage(Rotation="+i+")";
				dd.width = firsthandWidth;
				dd.height = firsthandHeight;
				dd.style.left=0;
				dd.style.top=0
				rate=1;
			}
			function zoomIn(){
				if(rate>=4){
					alert("已经很大了!");
					return;
				}else if(rate>=1){
					rate = rate+0.5;
				}else{
					rate = rate+0.1;
				}
				dd.width = firsthandWidth * rate;
				dd.height = firsthandHeight * rate;
			}
			function zoomOut(){
				if(rate>1){
					rate = rate-0.5;
				}else if(firsthandWidth * rate<50 || rate<=0.11){
					alert("已经很小了!");
					return;
				}else{
					rate = rate-0.1;
				}
				dd.width = firsthandWidth * rate;
				dd.height = firsthandHeight * rate;
			}
			function adaptToPageWidh(){
				if(i%2>0){
					var tmpRate = document.body.clientWidth/firsthandHeight;
					dd.height = document.body.clientWidth;
					dd.width = firsthandWidth * tmpRate;
					rate = tmpRate;
				}else{
					var tmpRate = document.body.clientWidth/firsthandWidth;
					dd.width = document.body.clientWidth;
					dd.height = firsthandHeight * tmpRate;
					rate = tmpRate;
				}
				dd.style.left=0;
				dd.style.top=0
			}
			function adaptToPage(pageWidth,pageHeight){
				if(i%2>0){
					var tmpRate1 = pageWidth/firsthandHeight;
					var tmpRate2 = pageHeight/firsthandWidth;
					if(tmpRate1<tmpRate2){
						dd.height = pageWidth;
						dd.width = firsthandWidth * tmpRate1;
						rate=tmpRate1;
					}else{
						dd.height = firsthandHeight * tmpRate2;
						dd.width = pageHeight;
						rate=tmpRate2;
					}
				}else{
					var tmpRate1 = pageWidth/firsthandWidth;
					var tmpRate2 = pageHeight/firsthandHeight;
					if(tmpRate1<tmpRate2){
						dd.width = pageWidth;
						dd.height = firsthandHeight * tmpRate1;
						rate=tmpRate1;
					}else{
						dd.width = firsthandWidth * tmpRate2;
						dd.height = pageHeight;
						rate=tmpRate2;
					}
				}
				dd.style.left=0;
				dd.style.top=0
			}
			function adaptToWindows(){
				adaptToPage(document.body.clientWidth,document.body.clientHeight);
			}
			function createPopuButton(){
				var oPopup = window.createPopup();
				var oPopBody = oPopup.document.body;
				var nMenus=0;
				oPopup.document.body.bgColor = "#7394D7;";
				var sHTML=""; 
				sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.resume()'> 还 原 </button><br>";
		  		sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.zoomIn()'> 放 大 </button><br>";
		 	 	sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.zoomOut()'>缩小</button><br>";
		  		sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.adaptToPageWidh()'>最适合宽度</button><br>";
		  		sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.adaptToWindows()'>最适合页面</button><br>";
		  		sHTML += "<button style='width: 80' onclick='javascript:document.parentWindow.parent.adaptToPage(678,960)'>最适合A4</button><br>";
		  		sHTML += "<button style='width: 80' name='顺时钟' value='顺时钟'  onclick='javascript:document.parentWindow.parent.turnA()'>顺时针</button><br>";
				sHTML += "<button style='width: 80' name='逆时钟' value='逆时钟' onclick='javascript:document.parentWindow.parent.turnB()'>逆时钟</button><br>";
				oPopup.document.body.innerHTML = sHTML;
				oPopup.show(event.x+window.screenLeft,event.y+window.screenTop,80,185);
			}
			
			function myprint()
			{
				window.focus();
				window.print();	
				
				imagewidth=document.all.dd.width;
				imageheight=document.all.dd.height;
				//alert("imagewidth="+imagewidth+"\r\nimageheight="+imageheight);
				if(imagewidth > 960)
				{
					imageheight = imageheight*(960/imagewidth);
					imagewidth = 960;
					//imageheight = 720;
					//alert(imageheight);
					dd.width=imagewidth;
					dd.height=imageheight;
					
					imagewidth=dd.width;
					imageheight=dd.Height;
				}
				
			}

    </script>
</body>
</html>