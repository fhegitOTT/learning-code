/**
 * Function: �����б� 
 * Author : ryz
 * Date : 20140331
 **/
var commonPattern = 
{
	patternNumberAndChar : "^\\w+$" //ƥ��[A-Za-z0-9_]	
};


/**
 *Function: У��ֵ�Ƿ�ƥ��������ʽ
 *Params: ��ҪУ���ֵ ������ʽ
 *Return: ƥ�䷵��true ��ƥ�䷵��false 
 *Author: ryz
 *Date: 20140331
 * **/
function checkValByReg(val, pattern)
{
	var patternReg = new RegExp(pattern);
	return patternReg.test(val);
}



