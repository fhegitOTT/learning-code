
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
//	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    long contractID = Long.parseLong(request.getAttribute("contractID").toString());


%>
<head>



    <script type="text/javascript">

        // 生成pdf申请单
        // 1表示权利申请人申请单
        function doOk() {

            var contractID = <%=contractID%>
            var checkBox = encodeURI($('input[name="checkBox"]:checked').val());
            var radio = encodeURI($('input[name="radio"]:checked').val());
          /*  var strFeatures = "height=600px,width=1000px,center=Yes,resizable=yes,status=no,scrollbars=yes";*/

                        window.open("<%=path %>/inner/contractquery/registerPrint.action?contractID=" + contractID + "&checkBox="+checkBox+"&radio="+radio);


/*
            window.location.href = "<%=path %>/ycsq/apply/applyer/applicationPrint?contractID="+contractID+"&pdfID="+"1"+"&pdfType="+"1"+"&checkBox="+checkBox+"&radio="+radio;
*/
           /* var href = "<%=path %>/ycsq/apply/applyer/selectApplicationPrint?docID="+docID;*/

        }

        function isChecked() {
            if($('#check8').attr("checked")=="checked"){
                $('#check8').attr("checked",false),

                    $('#ischecked').hide()
                    $('#yj_shi').attr("checked",false),
                    $('#yj_fou').attr("checked",false)
            }else {
                $('#check8').attr("checked",true),

                    $('#ischecked').show()
            }
        }
    </script>

    <title>申请单打印选择</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="<%=path%>/js/application.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/js/ycsqcx-public.js"></script>--%>
    <%--<script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>--%>
    <style type="text/css"> td {
        padding-left: 5px;
    } </style>
</head>
<body style="width: 100%;height: 95%;font-size:10.0pt" onload="doPrint()">


<%-- 平板调用控件--%>

<form action="" name="frmInfo" id="frmIn">

    <table align="center" border=0 cellpadding=0 cellspacing=0 width=90%>
        <tr height=16 align="center">
            <td height=45>
                <div style="font-size: 22px"><input name="checkBox" type="checkbox" value="1">&nbsp;&nbsp;是否添加遗失声明</div>
            </td>
        </tr>
        <tr height=16 align="center">
            <td height=45>
                <div style="font-size: 22px"> <input type=checkbox name=check_box id=check8 value=8  onclick="isChecked();"/>&nbsp;&nbsp;是否声明小微企业
                </div>
            </td>
        </tr>
        <tr height=16 align="center">
            <td height=45 style="display: none" name="ischecked" id="ischecked">
                <div style="font-size: 22px">
                    本公司属于：<input name="radio" id="yj_shi" style="width: 20px;" type="radio"  value="1">小型企业<input name="radio" id="yj_fou" style="width: 20px;" type="radio" value="2">微型企业</div>
            </td>
        </tr>
        <tr height=16 align="center">
            <td height=45>
                <div style="font-size: 22px"> <input type=button name=onok value="  确 定  " onclick="doOk();"></div>
            </td>
        </tr>
    </table>

 <%--
    <input name="checkBox" type="checkbox" value="1">是否添加遗失声明</br>
    本公司属于：<input name="radio" id="yj_shi" style="width: 20px;" type="radio" checked="checked" value="1">是<input name="radio" id="yj_fou" style="width: 20px;" type="radio" value="2">否</td>
    </br>
    <input type=button name=onok value="  确 定  " onclick="doOk();">--%>
</form>
</body>
</html>