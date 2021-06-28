/**
 * Function: 正则列表 
 * Author : ryz
 * Date : 20140331
 **/
var commonPattern = 
{
	patternNumberAndChar : "^\\w+$" //匹配[A-Za-z0-9_]	
};


/**
 *Function: 校验值是否匹配正则表达式
 *Params: 需要校验的值 正则表达式
 *Return: 匹配返回true 不匹配返回false 
 *Author: ryz
 *Date: 20140331
 * **/
function checkValByReg(val, pattern)
{
	var patternReg = new RegExp(pattern);
	return patternReg.test(val);
}



