<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String basePath = request.getContextPath();
    String checkBox = request.getAttribute("checkBox").toString();
    String radio = request.getAttribute("radio").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>青岛市房地产登记申请书</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <script type="text/javascript" src="<%=basePath%>/js/application.js"></script>
</head>
<body >

<table width="639" border="0" align="center" cellpadding="0" cellspacing="1" style="border-collapse:separate; border-spacing:0px 5px;">
    <tr>
        <td colspan="11" align="center">
            <div align="center"><b><font size="5">不动产登记申请书</font></b></div>
        </td>
    </tr>
    <tr>
        <td>
            <div align="left"><font size="3">申请方式:&nbsp;&nbsp;
                <input type="checkbox" name="" />双方申请&nbsp;&nbsp;
                <input type="checkbox" name="" />单方申请&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                申请时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
            </font></div>
        </td>
    </tr>
    <tr >
        <td>
            <div align="left"><font size="3">登记种类:&nbsp;&nbsp;
                <input type="checkbox" name="" />预告登记&nbsp;&nbsp;
                <input type="checkbox" name="" />转移登记&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </font></div>
        </td>
    </tr>
</table>

<table width=625 border="1" cellspacing="0" cellpadding="0"  align="center"  bordercolor="#000000"  style="border-collapse:collapse;">
    <tr>
        <td height="175">
            <table  width="646"  border="1" align="center"  cellpadding="0" cellspacing="0" bordercolor="#000000"  style="border-collapse:collapse;" >
                <tr >
                    <td  height="27" width="600" align="center" valign="middle"> <font size="3">房地坐落部位 </font>
                    </td>
                    <td colspan="3"><font size="3">&nbsp;${cdVo.location }</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center"><font size="3">不动产权属证号/电子权证号(登记证明号)</font></td>
                    <td colspan="3"><font size="3">&nbsp;</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">申请人</font></td>
                    <td width="300"><font size="3">&nbsp;${bvo.buyerName }</font></td>
                    <td width="117" align="center"> <font size="3">申请人</font> </td>
                    <td width="300"><font size="3">${svo.sellerName}</font></td>
                </tr>

                <%-- <tr >
                   <td height="27" width="115" align="center" valign="middle"> <font size="3">身份证件名称</font> </td>
                   <td width="300"><font size="3">&nbsp;${buyerCardName}</font></td>
                   <td width="150" align="center"> <font size="3">身份证件名称</font> </td>
                   <td width="300"><font size="3">营业执照</font></td>
                 </tr>--%>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">证件号码</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;${bvo.buyerCardcode }</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">证件号码</font> </td>
                    <td width="300"><font size="3">${svo.sellerBizcardcode }</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">联系电话</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;${bvo.buyerCall }</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">联系电话</font> </td>
                    <td width="300"><font size="3">${svo.sellerDlgCall }</font></td>
                </tr>
                <%-- <tr >
                   <td height="27" width="115" align="center" valign="middle"> <font size="3">联系地址</font> </td>
                   <td width="300">
                     <div align="left"><font size="3"> &nbsp;${bvo.buyerAddress }</font></div>
                   </td>
                   <td width="150" align="center"> <font size="3">联系地址</font> </td>
                   <td width="300"><font size="3">${svo.sellerAddress }</font></td>
                 </tr>  --%>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">代  理 人</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;${bvo.buyerProxy }</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">代  理 人</font> </td>
                    <td width="300"><font size="3">&nbsp;</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">身份证明号码</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;</font></div>
                    </td>
                    <td width="300" align="center"> <font size="3">身份证明号码</font> </td>
                    <td width="300"><font size="3"></font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">代理权限</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">代理权限</font> </td>
                    <td width="300"><font size="3">&nbsp;</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">联系电话</font> </td>
                    <td width="300">
                        <div align="left"><font size="3"> &nbsp;${bvo.buyerProxyCall }</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">联系电话</font> </td>
                    <td width="300"><font size="3">&nbsp;</font></td>
                </tr>
                <tr >
                    <td height="27" width="115" align="center" valign="middle"> <font size="3">选择邮寄服务</font> </td>
                    <td width="300">
                        <div align="center"><font size="3"><input type="checkbox" name="" /> &nbsp;是</font></div>
                    </td>
                    <td width="150" align="center"> <font size="3">邮件收件人</font> </td>
                    <td width="300"><font size="3">&nbsp;</font></td>
                </tr>
                <tr>
                    <td  height="57" width="70" align="center" valign="middle"><font size="3">邮寄地址及联系电话</font></td>
                    <td colspan=3 align="left">

                    </td>
                </tr>
                <%--<tr >
                  <td height="27" width="115" align="center" valign="middle"> <font size="3">联系地址</font> </td>
                  <td width="300">
                    <div align="left"><font size="3"> &nbsp;${bvo.buyerProxyAdr }</font></div>
                  </td>
                  <td width="150" align="center"> <font size="3">联系地址</font> </td>
                  <td width="300"><font size="3">&nbsp;</font></td>
                </tr> --%>

                <%
                    if ("1".equals(checkBox)) {
                %>
                <tr>
                    <td  height="57" width="70" align="center" valign="middle"><font size="3">遗失声明</font></td>
                    <td colspan=3 align="left">
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;因保管不善，______________号不动产权证书或不动产登记证明遗失（灭失），根 据《不动产登记暂行条例实施细则》第二十二条的规定声明该不动产权证书或不动产登记证明作废。 特此声明。</div>
                        <div align="center">声明人：</div>
                    </td>
                </tr>
                <%
                    }
                %>

                <%
                    if ("1".equals(radio)) {
                %>
                <tr>
                    <td  height="57" width="70" align="center" valign="middle"><font size="3">小微企业承诺</font></td>
                    <td colspan=3 align="left">
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本公司郑重承诺：对照《统计上大中小微型企业划分办法》及《金融业企业划型标准规定》，本公司属于 ☑小型企业 /□微型企业。
                            本公司保证对上述承诺的真实性负责。如有虚假，本公司愿意补缴已免收的不动产登记费，自愿承担相关 法律责任及被纳入失信企业名单的后果。</div>
                        <div align="center">承诺企业：</div>
                    </td>
                </tr>
                <%
                }else if("2".equals(radio)){
                %>
                <tr>
                    <td  height="57" width="70" align="center" valign="middle"><font size="3">小微企业承诺</font> </td>
                    <td colspan=3 align="left">
                        <div><font size="3">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本公司郑重承诺：对照《统计上大中小微型企业划分办法》及《金融业企业划型标准规定》，本公司属于 □小型企业 /☑微型企业。
                            本公司保证对上述承诺的真实性负责。如有虚假，本公司愿意补缴已免收的不动产登记费，自愿承担相关 法律责任及被纳入失信企业名单的后果。</font></div>
                        <div align="center"><font size="3">承诺企业：</font></div>
                    </td>
                </tr>
                <%
                    }
                %>




                <tr >
                    <td  height="57" width="70" align="center" valign="middle"> <font size="3">备注</font>
                    </td>
                    <td colspan="3"><font size="3">&nbsp;${memo }</font></td>
                </tr>
                <tr >
                    <td colspan="4">
                        <div align="center"><font size="3">申请人承诺</font></div>
                        <div>本表填写内容及提交的所有材料真实、合法、有效；因虚假而导致的法律责任，概由申请人承担，与登记机关无关，申请人已充分知晓所申请办理登记的不动产在登记簿上记载的抵押、异议、查封等情况，
                            若有书面材料需送达申请人的，可按本表填写的联系地址邮寄送达。</div>
                    </td>
                </tr>
                <tr >
                    <td colspan="4" style="border-bottom-style: none">
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;padding-left: 20px;"><font size="3">申请人:</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;"><font size="3">(签&nbsp;章)</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;"><font size="3">申请人:</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 20%;"><font size="3">(签&nbsp;章)</font></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="border: none">
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;padding-left: 20px;"><font size="3">法定代表人:</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;"><font size="3">(签&nbsp;章)</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 25%;"><font size="3">法定代表人:</font></div>
                        <div style="height: 75px;padding-top: 45px;float: left;width: 20%;"><font size="3">(签&nbsp;章)</font></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="border-top-style: none">
                        <div style="height: 70px;padding-top: 45px;float: left;width: 25%;padding-left: 20px;"><font size="3">代理人:</font></div>
                        <div style="height: 70px;padding-top: 45px;float: left;width: 25%;"><font size="3">(签&nbsp;章)</font></div>
                        <div style="height: 70px;padding-top: 45px;float: left;width: 25%;"><font size="3">代理人:</font></div>
                        <div style="height: 70px;padding-top: 45px;float: left;width: 20%;"><font size="3">(签&nbsp;章)</font></div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!--<div align="center" style="padding-top: 20px;">
	<a href="javascript: window.print()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px">打印</a>
	<a href="javascript:window.close()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:80px">关闭</a>
</div>
--></body>
</html>
