/**
 * Function: ͨ�ù�����
 * Author : ryz
 * Date : 20140331
 **/

/****
��ʾ������Ϣ����
Params:
	title: ��ʾ��ı�����Ϣ
	msg : ������ʾ����Ϣ
	icon : ��ʾ��ͼ����: error,question,info,warning
Author: ryz
****/
function alertMsg(title, msg, icon)
{
	$.messager.alert(title?title:"��ʾ", msg?msg:"����ʧ��", icon?icon:"error");
}

//������һ������
function goBack()
{
	history.back();
}

//��ת��ָ����URL
function goUrl(url)
{
	location.href = url;
}

$(function()
{
	//�˻ص���һ������
	$("#form_goBack").bind(
	{
		click : function()
		{
			goBack();
		}
	});
});