 <%@ page contentType = "text/html; charset=UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String basePath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业入网名单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="<%=basePath%>/js/application.js"></script>	 
</head>
<body >
 
<table width="659" border="0" align="center"
                 cellpadding="0" cellspacing="1"  class="css">
  <tr  height="25"> 
    <td colspan="11" align="center" nowrap> 
      <div align="center"><b><font size="5">青岛市房地产开发企业网上备案认证收件收据</font></b></div>
    </td>
  </tr>
  <tr > 
    <td colspan="4"><table width="100%" border="0" class=css>
        <tr>
           
          <td  align="right">
            <br>
            <font size="4">收件编号： <strong>${dvo.document_Id }</strong></font></td>
        </tr>
      </table></td>
  </tr>
  </table>
  
<table width=625 border="1" cellspacing="0" cellpadding="0"  align="center"  bordercolor="#000000"  style="border-collapse:collapse;" class="css">
  <tr>
    <td height="705"> 
      <table  width="646"  border="1" align="center"  cellpadding="0" cellspacing="0" bordercolor="#000000"  style="border-collapse:collapse;" >
        <tr > 
          <td  height="27" width="115" align="center" valign="middle"> <font size="3">申请企业</font> 
          </td>
          <td colspan="3"><font size="3">&nbsp;${dvo.company_Name }</font></td>
        </tr>
        <tr > 
          <td height="27" width="115" align="center" valign="middle"> <font size="3">售楼处地址</font> 
          </td>
          <td width="309"><font size="3">&nbsp;${dvo.company_Address }</font></td>
          <td width="117" align="center"> <font size="3">售楼处电话</font> </td>
          <td width="95"><font size="3">&nbsp;${dvo.company_Phone }</font></td>
        </tr>
        <tr > 
          <td height="27" width="115" align="center"><font size="3">申报项目地址</font></td>
          <td colspan="3"><font size="3">&nbsp;${dvo.apply_Proj_Addr }</font></td>
        </tr>
        <tr > 
          <td height="27" width="115" align="center" valign="middle"> <font size="3">项目名称</font> 
          </td>
          <td width="309"><font size="3">&nbsp;${dvo.project_Name }</font></td>
          <td width="117" align="center"> <font size="3">项目所处的范围</font> </td>
          <td width="95"><font size="3">
	      	 <c:if test="${dvo.districtid==0 }">市中心</c:if>
	      	 <c:if test="${dvo.districtid==2 }">市南区</c:if>
	      	 <c:if test="${dvo.districtid==3 }">市北区</c:if>
	      	 <c:if test="${dvo.districtid==5 }">四方区</c:if>
	      	 <c:if test="${dvo.districtid==13 }">李沧区</c:if>
       	 	 <c:if test="${dvo.districtid==11 }">黄岛区</c:if>
   	 		 <c:if test="${dvo.districtid==12 }">崂山区</c:if>
   	 		 <c:if test="${dvo.districtid==14 }">城阳区</c:if>
   	 		 <c:if test="${dvo.districtid==82 }">即墨市</c:if>
   	 		 <c:if test="${dvo.districtid==83 }">平度市</c:if>
   	 		 <c:if test="${dvo.districtid==81 }">胶州市 </c:if>
   	 		 <c:if test="${dvo.districtid==84 }">胶南市</c:if>
   	 		 <c:if test="${dvo.districtid==85 }">莱西市</c:if>
   	 		 <c:if test="${dvo.districtid==21 }">高新区</c:if>
   	 		 <c:if test="${dvo.districtid==22 }">前湾保税港区</c:if>
          </font></td>
        </tr>
        <tr > 
          <td height="27" width="115" align="center" valign="middle"> <font size="3">代 
            理 人</font> </td>
          <td width="309"> 
            <div align="left"><font size="3"> &nbsp;${dvo.agent }</font></div>
          </td>
          <td width="117" align="center"> <font size="3">联系电话</font> </td>
          <td width="95"><font size="3">${dvo.agent_Phone }</font></td>
        </tr>
      </table>
      <table  align='center' width="100%" border="1" cellpadding="0" cellspacing="0"  style="border-collapse:collapse;" bordercolor="#000000" class=css>
        <tr > 
          <td colspan="2" height="20"> 
            <div align="center"><font size="3"><strong>提交文件名称</strong></font></div>
          </td>
          <td width="7%" height="20"> 
            <div align="center"><font size="3"><strong>份数</strong></font></div>
          </td>
          <td width="15%" height="20"> 
            <div align="center"><font size="3"><strong>备注</strong></font></div>
          </td>
        </tr>
        <tr > 
          <td width="4%" rowspan="12"> 
            <p align="center"><font size="3">企</font></p>
            <p align="center"><font size="3">业</font></p>
            <p align="center"><font size="3">认</font></p>
            <p align="center"><font size="3">证</font></p>
          </td>
          <td width="74%" height="22" valign="middle"><font size="3">[表一] 房地产开发企业情况表（原件）</font></td>
          <td width="7%"> 
            <div align="center"><font size="3">${dcvo.comp_Info_Num }</font></div>
          </td>
          <td rowspan="28" width="15%"> 
            <div align="center">${dvo.content } </div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表二] 房地产代理企业情况表（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.agent_Info_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表六] 开发企业或代理企业网上房地产操作人员申报表（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.signer_Reg_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表七] 操作人员网上操作权限申报表（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.signer_Auth_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 营业执照（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.bizcard_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 组织机构代码证（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.orgcard_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 税务注册证明（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.tax_Reg_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 法定代表人身份证（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.corp_Identity_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 网上操作人员的销售员证书、身份证（复印件）及照片</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.signer_Identity_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 房地产开发企业资质证书（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.qualify_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 房地产经纪组织备案表（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.org_Backup_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 代理销售合同（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.agent_Contract_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td rowspan="8" width="4%"> 
            <p align="center"><font size="3">项</font></p>
            <p align="center"><font size="3">目</font></p>
            <p align="center"><font size="3">认</font></p>
            <p align="center"><font size="3">证</font></p>
          </td>
          <td height="22" width="74%" valign="middle" ><font size="3">[表三] 预、销售房地产网上备案项目按幢售价申报表（原件）</font></td>
          <td width="7%"> 
            <div align="center"><font size="3">${dcvo.price_Appl_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表四] 预、销售房地产网上备案项目申报表（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.project_Appl_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表五] 预、销售房地产项目已售未登记部位清单（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.part_List_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 房地产权证（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.real_Cert_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 房地产测绘报告（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.mapping_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 预售许可证（复印件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.presell_Cert_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 小区规划平面图</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.plan_Map_Num }</font></div>
          </td>
        </tr>
		<tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 小区鸟瞰图、房屋立面效果彩图和各户型平面图（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.image_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td rowspan="4" width="4%"> 
            <p align="center"><font size="3">变</font></p>
            <p align="center"><font size="3">更</font></p>
          </td>
          <td height="22" width="74%" valign="middle" ><font size="3">[表八] 房地产开发企业商品房屋入网认证变更申请表（原件）</font></td>
          <td width="7%"> 
            <div align="center"><font size="3">${dcvo.house_Mod_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 房屋部位的变更状况的情况说明（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.part_Mod_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">[表九] 网上房地产操作人员权限变更申请表（原件）</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.signer_Mod_Auth_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3"> 网上操作人员的销售员证书、身份证（复印件）及照片</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.signer_Mod_Identity_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td rowspan="4" width="4%"><font size="3"> 
            <p align="center">其</p><p align="center">他</p></font></td>
          <td width="74%" height="22" valign="middle" ><font size="3"> 委托书（原件）及代理人身份证（复印件）</font></td>
          <td width="7%"> 
            <div align="center"><font size="3">${dcvo.agent_Identity_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">区市一级相关建委、建设局或房地局出具的动迁房源有关证明</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.movement_Cert_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">保留说明一份 </font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.explanation_Num }</font></div>
          </td>
        </tr>
        <tr > 
          <td width="74%" height="22" valign="middle" ><font size="3">其他(详见备注)</font></td>
          <td width="7%" > 
            <div align="center"><font size="3">${dcvo.etc_Num }</font></div>
          </td>
        </tr>
      </table>
	 
	  <table  align='center' width="646" border="1" cellpadding="0" cellspacing="0"  style="border-collapse:collapse;" bordercolor="#000000" class=css>
        <tr > 
          <td height="30" width="86" align="center" valign="middle"> <font size="3">收件人</font> 
          </td>
          <td width="360"><font size="3">${smUserVO.displayName }</font></td>
          <td width="95" align="center" valign="middle"> <font size="3">收件日期</font> 
          </td>
          <td width="95"><font size="3">${date }</font></td>
  </tr>
  <tr > 
          <td height="30" width="86" align="center" valign="middle"> <font size="3">传 
            真</font> </td>
          <td width="360"><font size="3">${dvo.document_Fax }</font></td>
          <td width="95" align="center" valign="middle"> <font size="3">联系电话</font> 
          </td>
          <td width="95"><font size="3">${dvo.document_Phone }</font></td>
  </tr>
</table>
	</td>
  </tr>
</table>
<div align="left"> 
  <table align="center" width="645" border="0" class=css>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td width="130"><font size="3"><b>注意事项</b><font>：</td>
      <td width="505">1.商品房上市销售前，请补交商品房上市（入网）销售申请暨承诺书（原件）<br>2.售楼处请配置公共上网电脑用于购房人即时查询楼盘销控状态</td>
    </tr>
    <tr> 
      <td width="130">&nbsp;</td>
      <td width="505"><br>
        </td>
    </tr>
  </table>
</div>
</body>
</html>
