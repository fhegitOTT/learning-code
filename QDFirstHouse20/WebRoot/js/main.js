/*---------------------------------------------------------------*/
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
/*---------------------------------------------------------------*/
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
/*---------------------------------------------------------------*/

function MM_preloadImages() { //v3.0
  var d=document;
  if(d.images){ 
   if(!d.MM_p){ 
		  d.MM_p=new Array();
   }
   var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
   for(i=0; i<a.length; i++){
    if (a[i].indexOf("#")!=0){ 
    	d.MM_p[j]=new Image; 
    	d.MM_p[j++].src=a[i];
    }
   }
  }
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
/*------------------------show/hide tab------------------------------------*/
function divChange(divOpen,divId){
if (OpenLab=="")
	{
	OpenLab=divOpen;
	}
   OpenLab.style.display="none";
   divId.style.display="block"; 
   OpenLab=divId;
   
}
/*------------------------show/hide left------------------------------------*/
function ShowHide(tableId,imageID,showImage,hideImage){
if(tableId.style.display=="block"){
   tableId.style.display="none";
   document[imageID].src="images/"+showImage+".gif";
}
 else  {
 tableId.style.display="block";
 document[imageID].src="images/"+hideImage+".gif";
 }
}
/*----------------------------- menu left----------------------------------*/
function menuShow(MenuId,MenuId2){

if(MenuId.style.display=="none")  {

   MenuId.style.display="block";

  }
}
function menuHide(MenuId,MenuId2){

if(MenuId.style.display=="block")  {

   MenuId.style.display="none";

}
}

function menuChange(MenuId,MenuId2){

if(MenuId.style.display=="block"){
menuHide(MenuId,MenuId2);
whichOpen="";

 if(whichContinue)
 whichContinue.click();
 
whichcontinue="";
  }
  else
    if(whichOpen) {
   whichContinue=MenuId2;
      whichOpen.click();
}
else

{
   menuShow(MenuId,MenuId2);
   whichOpen=MenuId2;
   whichContinue="";
}
}
/*----------------------------- menu top----------------------------------*/
function LabChange(LabId,ImageName){

if(LabId!=LabOpen){
document[LabId].src="images/lab"+ImageName+"_swap.gif";
document.getElementById("d"+ImageName).style.display="block";

document[LabOpen].src="images/lab"+CloseImg+".gif";
document.getElementById("d"+CloseImg).style.display="none";
LabOpen=LabId;
CloseImg=ImageName;

if(whichOpen) {
      whichOpen.click();
}
}
}


//<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) /*with (navigator)*/ {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->
function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}
function MM_showHideLayers() { //v3.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'block':(v='hide')?'none':v; }
    obj.display=v; }
}
//-->
function goBack() {
location.href = "javascript:history.back()";
}

function checknumber(String) 
{ 
	var Letters = "1234567890"; 
	var i; 
	var c; 
	for( i = 0; i < String.length; i ++ ) 
	{ 
		c = String.charAt( i ); 
		if (Letters.indexOf( c ) ==-1) 
		{ 
			return true; 
			break;
		} 
	} 
	return false; 
} 
function checkfloat(val_num)
{
	if (parseFloat(val_num) == val_num)
	{
	  return false;
	}
	else
		{
			return true ;
		}
} 

function checkNumABC(val){
	var Letters = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-"; 
	var i; 
	var c; 
	for( i = 0; i < val.length; i ++ ) 
	{ 
		c = val.charAt( i ); 
		if (Letters.indexOf( c ) ==-1) 
		{ 
			return true; 
			break;
		} 
	} 
	return false; 
}

function checkInt(val_num)
{
	var tmp=parseInt(val_num);
	if ( (tmp== val_num)&&(tmp>=0))
	{
	  return true;
	}
	return false;
}

function enter2tab()
{
  var e = document.activeElement;
  var b = (e.tagName == "INPUT"
    && window.event.keyCode == 13
    && ( e.type == "text"
    || e.type == "password"
    || e.type == "checkbox"
    || e.type == "radio"
    || e.type == "select")
    || e.tagName=="SELECT");

  var a = document.forms[0].elements, n=-1;
  for(var i=0; i<a.length; i++)
  {
    if(a[i]==e) n=i;
    if(n>-1 && b && n+1<a.length)
    {
      if(!a[n+1].disabled)
      {
        //a[n+1].focus();
        a[n+1].select();
        window.event.keyCode    = 0;
        window.event.returnValue= false;
        return;
      }
      n=i;
    }
  }
}
function verifyDate(str)
{
	thePat=/^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$||^\d{4}-(0?[1-9]|1[0-2])$/;
	if(thePat.test(str)){
		return   true;
        }else{
		return   false;
	}
}


function isEmpty(fData)
{
    if(fData==null)
		return true;
	var qdata=fData.replace(/(^\s*)|(\s*$)/g, "")
	if(qdata=="")
		return true;
	return false;
}

function countId(checkId) {
	var j = 0;
	var l = 0;
	var frm=document.forms[0];
	l = frm.elements.length;
	for (var i = 0; i < l; i++) {
		var e = frm.elements[i];
		if (e.name == checkId && e.checked == true)
			j++;
	}
	return j;
}
/**-----------------------------------------------------------
* 鍚嶃�銆�О锛歩sBigStr
* 鍔�   鑳斤細鍒ゆ柇瀛楃涓叉槸鍚﹁秴杩囦簡棰濆畾鐨勯暱搴�姹夊瓧鎸変袱涓瓧绗﹁绠�
* 鍏ュ彛鍙傛暟锛歠Data锛氳妫�煡鐨勬暟鎹紱maxLen锛氳瀹氱殑鏈�ぇ闀垮害
* 鍑哄彛鍙傛暟锛歍rue锛氳秴杩囦簡鏈�ぇ闀垮害                              
*           False锛氭病鏈夎秴杩囨渶澶ч暱搴�
* -------------------------------------------------------------
**/
function isBigStr(fData,maxLen){	
    if(fData!=null && fData.replace(/[^\x00-\xff]/g,"**").length>maxLen){
    	return true;
    }else{
    	return false;
    }
}

/**-----------------------------------------------------------
* 鍚嶃�銆�О锛歩sCheckChina
* 鍔�   鑳斤細鍒ゆ柇瀛楃涓叉槸鍚﹀惈鏈夋眽瀛�
* 鍏ュ彛鍙傛暟锛歠Data锛氳妫�煡鐨勬暟鎹璞★紱msg锛氭彁绀哄瓧绗︿覆
* 鍑哄彛鍙傛暟锛歳eturn  杩斿洖                      
*           
* -------------------------------------------------------------
**/
function isCheckChina(fData,msg){	
	  obj= document.all(fData);
    if(obj!=null && obj.value.replace(/[^\x00-\xff]/g,"**").length!=obj.value.length){
    	alert(msg);
    	obj.selected;
    	return;
    }else{

    }
}

/**-----------------------------------------------------------
* 鍚嶃�銆�О锛歝ompareDate
* 鍔�   鑳斤細姣旇緝杈撳叆鏃ユ湡鍜屽綋鍓嶇郴缁熸棩鏈熺殑澶у皬
* 鍏ュ彛鍙傛暟锛歠Data锛氳緭鍏ョ殑鏃ユ湡 鏍煎紡 yyyy-mm-dd
* 鍑哄彛鍙傛暟锛歍rue锛�杈撳叆鏃ユ湡澶т簬褰撳墠绯荤粺鏃ユ湡                             
*           False锛氳緭鍏ユ棩鏈熷皬浜庡綋鍓嶇郴缁熸棩鏈�
* -------------------------------------------------------------
**/
function compareDate(fDate){	
    var now = new Date();
    var mm=now.getMonth() + 1;
    var dd=now.getDate();
       if(mm<10){
	       mm ="0"+mm;
	       }
	       if(dd<10){
	       dd ="0"+dd;
	       } 
   var today =now.getYear()+"/"+mm+"/"+dd;
   if (fDate.replace(/-/g,"\/")<=today)
       {
            return true;
         }else{
            return false;
          }
}
/**-----------------------------------------------------------
* 鍚嶃�銆�О锛欳heckAll
* 鍔�   鑳斤細閫変腑鎴栬�闈為�涓墍鏈夌殑妫�煡妗�
* 鍏ュ彛鍙傛暟锛歠Data锛氳緭鍏ョ殑鏃ユ湡 鏍煎紡 yyyy-mm-dd
* 鍑哄彛鍙傛暟锛歍rue锛�杈撳叆鏃ユ湡澶т簬褰撳墠绯荤粺鏃ユ湡                             
*           False锛氳緭鍏ユ棩鏈熷皬浜庡綋鍓嶇郴缁熸棩鏈�
* -------------------------------------------------------------
**/
function CheckAll() {
	var form=document.forms[0];
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if (e.name == "editid" && !e.disabled)
            e.checked = form.ckAll.checked;
    }
}

/**-----------------------------------------------------------
* 鍚嶃�銆�О锛欳heckAllObj
* 鍔�   鑳斤細閫変腑鎴栬�闈為�涓墍鏈夌殑妫�煡妗�
* 鍏ュ彛鍙傛暟锛歠Data锛氳緭鍏ョ殑鏃ユ湡 鏍煎紡 yyyy-mm-dd
* 鍑哄彛鍙傛暟锛歍rue锛�杈撳叆鏃ユ湡澶т簬褰撳墠绯荤粺鏃ユ湡                             
*           False锛氳緭鍏ユ棩鏈熷皬浜庡綋鍓嶇郴缁熸棩鏈�
* -------------------------------------------------------------
**/
function CheckAllObj(checkname,checkvalue) {
	var form=document.forms[0];
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if (e.name == checkname)
            e.checked = checkvalue;
    }
}
/**-----------------------------------------------------------
* 鍚嶃�銆�О锛欿eyDown
* 鍔�   鑳斤細鍦╦sp椤甸潰涓婄‘璁ゆ彁浜�
* -------------------------------------------------------------
**/
function QueryKeyDown(){ 
	var form = document.forms[0]; 
	if(event.keyCode==13){//Ctrl + Enter 鏈夋晥 
		Search();
	} 
}

function KeyDown(){ 
	var form = document.forms[0]; 
	if(event.keyCode==13){//Ctrl + Enter 鏈夋晥 
		Save();
	} 
}
function showWaitMsg(){
	var IfrRef = document.getElementById('DivShim');
	var DivRef = document.getElementById('waitmsg');
	DivRef.style.pixelTop = (document.body.offsetHeight - 120) / 2 + document.body.scrollTop;
	DivRef.style.pixelLeft = (document.body.offsetWidth - 360) / 2 + document.body.scrollLeft;
	DivRef.style.display = "block";
	IfrRef.style.width = DivRef.offsetWidth;
	IfrRef.style.height = DivRef.offsetHeight;
	IfrRef.style.top = DivRef.style.top;
	IfrRef.style.left = DivRef.style.left;
	IfrRef.style.zIndex = DivRef.style.zIndex - 1;
	IfrRef.style.display = "block";
	document.getElementById('sbar').width = 1;
	times = setInterval('showloading();',100);
}
function hideWaitMsg(){
	var IfrRef = document.getElementById('DivShim');
	var DivRef = document.getElementById('waitmsg');
	DivRef.style.display = "none";
	IfrRef.style.display = "none";
}
function showloading()
{
	if (document.getElementById('sbar').width>356)
	{
		document.getElementById('sbar').width=1
	}
	else
	{
		document.getElementById('sbar').width += 2;
	}
}
       
