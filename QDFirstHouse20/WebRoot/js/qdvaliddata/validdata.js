function do_change(val,typeid) {
	   typeid="money";
        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(val))
            alert( "input is not a number");
        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
            val += "00";
        var p = val.indexOf('.');
        if (p >= 0)
            val = val.substring(0, p) + val.substr(p+1, 2);
            unit = unit.substr(unit.length - val.length);
        for (var i=0; i < val.length; i++)
            str += '零壹贰叁肆伍陆柒捌玖'.charAt(val.charAt(i)) + unit.charAt(i);
        return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}

function IsBlank(str){
	if(MyTrim(str)==""){
		return true;
	}else{
		return false;
    }   
}

function IsValidDate(data){
	time=doTime(data);
	if(isNaN(data)&&!isNaN(time)){
		return true;
	}else{
		return false;
    }
}

function MyTrim(str){
	//return str.trim();
	return str.replace(/(^[\s\n\t]+|[\s\n\t]+$)/g, "");
}

function DateComp(d1,d2){
	if(IsValidDate(d1)&&IsValidDate(d2)){
		if(doTime(d1)>doTime(d2)){
			return true;
	     }else{
	    	 return false;
	     }
	}else{
		if(IsValidDate(d1)){
			return true;
		}else{
			if(IsValidDate(d2)){
				return true;
			}else{
				return false;
			}
		}
	}
}


function formatDbl(val){
	return Math.round(val);
}
function doTime(time){
	var time1 = time.toString();  
	return new Date(Date.parse(time1.replace(/-/g,"/"))).getTime(); 
}



function GetSpecialValue(s,s2)
{
	var sResult = "";
	var tmps ;
	var s = s.toLowerCase();
	var dataset = s.split(",")
	if(dataset==null || dataset.length==0) return "";
	for(var i=0;i<dataset.length;i++)
	{
		if(dataset[i].indexOf(s2)>=0)
		{
			sResult = MyTrim(dataset[i].substring(dataset[i].indexOf("=")+1));
			break;
		}
	}
	return sResult;
}

function isString(str){
   if(str.indexOf("\'") != -1 || str.indexOf("<") != -1 ||　str.indexOf(">") != -1 ||　str.indexOf("\"") != -1){
       return false;
   }else{
   	   return true;
   }
}

function numberCheck(val){
    var searchStr="^[\\d]+"
    var re = new RegExp(searchStr , "g");
    var result =true;
    if(val!=""){
        result=(val.match(re)==val);
    }
    return result;
}

function CheckDataValid(objform)
{
  //alert('A');
  for(var i=0;i< objform.elements.length;i++)
  {
     var obj = objform.elements[i];
	 
     if(obj.tagName.toLowerCase()=="input" || obj.tagName.toLowerCase()=="select" || obj.tagName.toLowerCase()=="textarea")
     {

        var features = obj.getAttribute("features");                           
        if(features==null || features=="") continue;
        //alert(features);
        var datatype = GetSpecialValue(features,"datatype");	//数据类型
        var bmust =  GetSpecialValue(features,"bmust");			//是否必输
        var maxlength = GetSpecialValue(features,"maxlength");	//最大长度
        var minlength = GetSpecialValue(features,"minlength");	//最小长度
        var maxvalue =  GetSpecialValue(features,"maxvalue");	//最大值
        var minvalue =  GetSpecialValue(features,"minvalue");	//最小值
        var minselected = GetSpecialValue(features,"minselected");	//最小选择数量
        var maxselected = GetSpecialValue(features,"maxselected");	//最大选择数量
        var showtitle =  GetSpecialValue(features,"showtitle");	//提示信息	
        //alert(showtitle);
        if(bmust=="1" || bmust=="yes")  //必输内容
        {
			if(IsBlank(obj.value))
			{
				alert("请输入" + showtitle);
				obj.focus();
				return false;
			}
			
			//判断CheckBox和Radio
			if(obj.type.toLowerCase()=="radio" || obj.type.toLowerCase()=="checkbox")
			{
				var SelectedCount = 0;
				
				for(j=0;j<document.all(obj.name).length;j++)
				{
				    if(document.all(obj.name)(j).checked) 
				    {
				       SelectedCount++;				       
				    }
				   
				}
				if (SelectedCount==0)
				{
					if (document.all(obj.name).checked)
					{
						SelectedCount++;		
					}
				}
				if(SelectedCount==0)
				{
					alert("请选择" + showtitle + " ！");
					try{ obj.focus();}catch(e){}
					return false;
				}
				if(obj.type.toLowerCase()=="checkbox" && !isNaN(minselected) && SelectedCount<minselected*1)
				{
					alert("请至少选择"+minselected+"个"+showtitle);
					try{ obj.focus();}catch(e){}
					return false;
				}
				if(obj.type.toLowerCase()=="checkbox" && !isNaN(maxselected) && SelectedCount>maxselected*1)
				{
					alert("至多选择"+maxselected+"个"+showtitle);
					try{ obj.focus();}catch(e){}
					return false;
				}
			}
			
        } //End bmust
        
        if(obj.tagName.toLowerCase()=="select")   //如果是select，则不作后面的数据校验
        {
			//alert(obj.multiple);
			if(obj.multiple=="undefine" || !obj.multiple) continue;
			var SelectedCount = 0;
			for(var ii=0;ii<obj.options.length;ii++)
			{
				if(obj.options(ii).selected) SelectedCount++;
			}
			if(!isNaN(minselected) && minselected*1!=0 && SelectedCount<minselected*1)
			{
				alert("请至少选择"+minselected+"个"+showtitle);
				obj.focus();
				return false;
			}
			if(!isNaN(maxselected) && maxselected*1!=0 && SelectedCount>maxselected*1)
			{
				alert("至多选择"+maxselected+"个"+showtitle);
				obj.focus();
				return false;
			}
			continue;
			
        }
        
	   
	
        if(datatype=="0" || datatype=="s"  || datatype=="str" || datatype=="string") //字符类型
        {
			if(!isString(obj.value)){
				alert("请不要输入包含如下字符　\'  \"  <  >　的" + showtitle + "，请重新输入 ");
				obj.focus();
				return false;
			}
			if(minvalue!="" && obj.value<minvalue)	
			{
				alert(showtitle + "不能小于" + minvalue);
				obj.focus();
				return false;
			}
			if(maxvalue!="" && obj.value>maxvalue)	
			{
				alert(showtitle + "不能大于" + maxvalue);
				obj.focus();
				return false;
			}
			if(!isNaN(minlength) && obj.value.length<minlength*1 && minlength*1!=0)
			{
				alert(showtitle + "的长度必须大于等于" + minlength);
				obj.focus();
				return false;
			}
			if(!isNaN(maxlength) && obj.value.length>maxlength*1 && maxlength*1!=0)
			{
				alert(showtitle + "的长度不能超过" + maxlength);
				
				obj.focus();
				return false;
			}
        }
        else if(datatype=="1" || datatype=="n" || datatype=="number") //数字类型
        {
		
			if(!IsBlank(obj.value) && isNaN(obj.value))
			{
				alert("请正确输入" + showtitle + "(数字)");
				obj.focus();
				return false;
			}
			if(!isNaN(minvalue) && !IsBlank(obj.value) && obj.value<minvalue*1 && minvalue!=0)	
			{
				alert(showtitle + "不能小于" + minvalue);
				obj.focus();
				return false;
			}
			if(!isNaN(maxvalue) && !IsBlank(obj.value) && obj.value>maxvalue*1 && maxvalue!=0)	
			{
				alert(showtitle + "不能大于" + maxvalue);
				obj.focus();
				return false;
			}
			if(!isNaN(minlength) && !IsBlank(obj.value) && obj.value.length<minlength*1 && minlength*1!=0)
			{
				alert(showtitle + "的长度必须大于等于" + minlength);
				obj.focus();
				return false;
			}
			
			if(!isNaN(maxlength) && !IsBlank(obj.value) && formatDbl(obj.value).length>maxlength*1 && maxlength*1!=0)
			{
				alert(showtitle + "的长度不能超过" + maxlength);
				obj.focus();
				return false;
			}
        }
        else if(datatype=="2" || datatype=="d" || datatype=="date" || datatype=="DATE") //日期类型
        {
			if(!IsBlank(obj.value) && !IsValidDate(obj.value))
			{
				alert("请正确输入" + showtitle + "(yyyy-mm-dd)");
				obj.focus();
				return false;
			}
			if(IsValidDate(minvalue) && IsValidDate(obj.value) && DateComp(minvalue,obj.value))	
			{
				alert(showtitle + "不能小于" + minvalue);
				obj.focus();
				return false;
			}
			if(IsValidDate(maxvalue) && IsValidDate(obj.value) && DateComp(obj.value,maxvalue))	
			{
				alert(showtitle + "不能大于" + maxvalue);
				obj.focus();
				return false;
			}
        }
        else if(datatype=="3" || datatype=="i" || datatype=="integer" || datatype=="INTEGER") //整型
    	{
    		if(!IsBlank(obj.value) && !numberCheck(obj.value))
			{
				alert("请正确输入" + showtitle + "(整型数字)");
				obj.focus();
				return false;
			}
    		if(!isNaN(minvalue) && !IsBlank(obj.value) && obj.value<minvalue*1 && minvalue!=0)	
			{
				alert(showtitle + "不能小于" + minvalue);
				obj.focus();
				return false;
			}
			if(!isNaN(maxvalue) && !IsBlank(obj.value) && obj.value>maxvalue*1 && maxvalue!=0)	
			{
				alert(showtitle + "不能大于" + maxvalue);
				obj.focus();
				return false;
			}
			if(!isNaN(minlength) && !IsBlank(obj.value) && obj.value.length<minlength*1 && minlength*1!=0)
			{
				alert(showtitle + "的长度必须大于等于" + minlength);
				obj.focus();
				return false;
			}

			if(!isNaN(maxlength) && !IsBlank(obj.value) && formatDbl(obj.value).length>maxlength*1 && maxlength*1!=0)
			{
				alert(showtitle + "的长度不能超过" + maxlength);
				obj.focus();
				return false;
			}
					
    	}
        //end data type
        
     }
     //if(obj.tagName.toLowerCase()=="select")
  }
  return true;
}