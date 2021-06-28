/**
 * Function: 通用公共类
 * Author : ryz
 * Date : 20140331
 **/

/****
显示弹出信息框体
Params:
	title: 显示框的标题信息
	msg : 用于提示的信息
	icon : 显示的图标有: error,question,info,warning
Author: ryz
****/
function alertMsg(title, msg, icon)
{
	$.messager.alert(title?title:"提示", msg?msg:"操作失败", icon?icon:"error");
}

//返回上一步方法
function goBack()
{
	history.back();
}

//跳转到指定的URL
function goUrl(url)
{
	location.href = url;
}

$(function()
{
	//退回到上一步操作
	$("#form_goBack").bind(
	{
		click : function()
		{
			goBack();
		}
	});
});