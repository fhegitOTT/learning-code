var aDwBig    = new Array("Ǫ","��","ʰ");
var aDwSmall  = new Array("Ǫ","��","ʰ");
var aDw4Break = new Array("Ԫ","��","��","����");
var aNumBig   = new Array("��","Ҽ","��","��","��","��","½","��","��","��");

//�ж��ǲ��Ǹ�����
function LM_isNumber(inputString)
{
	var c;
	for(var i=0;i<inputString.length;i++)
	{
		c=inputString.charAt(i);
		if((c < "0" || c > "9") && c != ".") 
			return(false);
	}
	return(true);
}

function LM_TransNum2MoneyCN(str)
{
	//�ж��Ƿ�Ϊ��Ч��Сд�ʽ�
	if(!LM_isNumber(str))
	{
		alert("��Ч��Сд���");
		return -1;
	}
	
	var indexDot = str.indexOf(".");
	if(str.indexOf(".",indexDot+1) != -1)
	{
		alert("��ЧС����");
		return -1;
	}
	//ȡ��С����ǰ���ַ������������֣�
	var strInt="";
	var strDig="";
	var result="";
	var temp="";
	if(indexDot == -1)
	{
		strInt = str;
		strDig = "";
	}
	else
	{
		strInt = str.substr(0,indexDot);
		strDig = str.substr(indexDot + 1, str.length);
	}

	if(strInt.length > 16)
	{
		alert("���ȹ�����");
		return -1;
	}
	
	if(strDig.length > 2)
	{
		alert("����С��λ������!");
		return -2;
	}
	//��������
	if(strInt != 0)
		result = LM_TransInt(strInt);
	
	if(strDig !=0)
	{
		var jiao = strDig.substr(0,1);
		var fen = strDig.substr(1,1);
		result += aNumBig[jiao];
		if(jiao != 0)
			result += "��";
		if(fen != 0)
		{
			result += aNumBig[fen];
			result += "��";
		}
	}
	if(result =="")
		result = "";//��Ԫ��
	else
		result += "��";
	return result;
}
function LM_TransInt(strInt)
{
	var result="";
	var temp="";
	var notZeroThenAdd = false;
	var dealLen = strInt.length;
	var n = dealLen % 4;
	if(n == 0) 
		n=4;
	temp = strInt.substr(0,n);
	for(i=n;i<4;i++)
		temp = "0" + temp;
	result = LM_Format4Num(temp,dealLen > 4,false,false);
	result += aDw4Break[Math.floor((dealLen-1)/4)]
	
	if(temp.substr(3,1) == "0")
		notZeroThenAdd =true;
	else
		notZeroThenAdd =false;

	dealLen -= n;
	var needYuan=false;
	while(dealLen >= 4)
	{
		temp = strInt.substr(n,4)
		var tempResult = LM_Format4Num(temp,dealLen > 4,true,notZeroThenAdd);
		if(tempResult == aNumBig[0])
		{
			notZeroThenAdd = true;
			if(dealLen == 4) 
				needYuan = true;
		}
		else
		{
			result +=tempResult;
			result += aDw4Break[dealLen/4 -1]
		}
		if(temp.substr(3,1) == "0")
			notZeroThenAdd =true;
		else
			notZeroThenAdd =false;
		dealLen -= 4;
		n += 4;
	}
	if(needYuan)
		result += aDw4Break[0];
	return result;
}

function LM_Format4Num(str,bBig,needZeroBefore,notZeroThenAddZero)
{	//��ʽ��4λ����
	var result="";
	if(str == "0000")
	{
		if(needZeroBefore)
			return aNumBig[0];
		else
			return "";
	}
	//Ǫλ
	var qian=str.substr(0,1);
	var bai=str.substr(1,1);
	var shi=str.substr(2,1);
	var ge=str.substr(3,1);
	//alert("qian" + qian +" bai" + bai + " shi" + shi + " ge" + ge);
	if(qian == "0")
	{
		if(needZeroBefore)
			result += aNumBig[0];
	}
	else
	{
		result += aNumBig[qian];
		result += bBig ? aDwBig[0]: aDwSmall[0];
	}
	//��
	if(bai != "0")
	{
		result += aNumBig[bai];
		result += bBig ? aDwBig[1]: aDwSmall[1];
	}
	//ʰ
	if(shi != "0")
	{
		if(bai == "0" && qian != "0")
			result += aNumBig[0];
		result += aNumBig[shi];
		result += bBig ? aDwBig[2]: aDwSmall[2];
	}
	//��
	if(ge != "0")
	{
		if(shi == "0" && bai != "0" || (shi == "0" && bai == "0" && qian != "0"))
			result += aNumBig[0];		
		result += aNumBig[ge];
	}
	
	if(notZeroThenAddZero && qian != "0")
	{
		result = aNumBig[0] + result;
	}
	//alert(result);
	return result;
}
//���øú�������
function Small2Big(name1,name2)
{
	var tmp = LM_TransNum2MoneyCN(document.all(name1).value);
	if(tmp == -1 || tmp == -2)
	{
		document.all(name1).focus();
		document.all(name2).value = "";
	}
	else
		document.all(name2).value = tmp;
}
