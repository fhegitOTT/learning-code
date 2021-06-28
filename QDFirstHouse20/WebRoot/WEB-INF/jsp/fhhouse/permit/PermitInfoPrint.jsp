<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ page import = "java.util.*,java.text.*" %>
<%@page import="com.netcom.nkestate.fhhouse.permit.vo.PermitVO"%>
<%@page import="com.netcom.nkestate.common.CharSet"%>
<%@page import="com.netcom.nkestate.framework.util.StringUtil"%>
<%@page import="com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO"%>
<%@page import="com.netcom.nkestate.system.vo.SmUserVO"%>
<%@page import="com.netcom.nkestate.fhhouse.permit.action.PermitAction"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String cmd =request.getAttribute("cmd") == null ? "" : (String)request.getAttribute("cmd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	List PreSaleHouseVOs = (List)request.getAttribute("houseList");
	PermitVO permitVO = request.getAttribute("permitVO") == null ? new PermitVO() : (PermitVO)request.getAttribute("permitVO");
	int printFlag = Integer.parseInt(request.getAttribute("printFlag").toString());//1--正文，2--副本，3--存根
	SmUserVO vo = (SmUserVO) request.getSession().getAttribute("LgUser");
	String transactionID = CharSet.toWebCharSet(request.getAttribute("transactionID")); 
	String districtID = CharSet.toWebCharSet(request.getAttribute("districtID")); 
	String permitID = CharSet.toWebCharSet(request.getAttribute("permitID")); 
	//PermitAction action = new PermitAction();
	//action.printPermit(transactionID,districtID,permitID,vo);
try{
 %>
<!-- 
  - Author(s): gcf
  - Date: 2019-01-15 17:05:29
  - Description:
-->
<head>
<title>许可证打印</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<%!
//判断字符串实际长度
int getStrActualLen(String str)
{
	char[] arrchar = str.trim().toCharArray();
	int tmpChar = 0;
	int strlen = 0;
	for(int i=0;i<arrchar.length;i++)
	{
		tmpChar = (int)arrchar[i];
		if (tmpChar > 256) {
			strlen = strlen + 2 ;
		} else {
			strlen = strlen + 1 ;
		}
	}
	return strlen;
}
%>
<%
String strhead="";


String permitNO="";		//预售许可证号

String companyName="";    	//公司名称
String projectName="";		//项目名称

String bargainCode="";		//土地使用权出让合同编号
String landUsage="";		//土地用途
String location="";    		//坐落地点
String remark="";    		//备注

String passDate="";    		//审批通过日期
int ptFlag=0;			//配套商品房标志

String RealNo="";
String PermitDate="";
String flArea="";
String cellarArea="";

permitNO = CharSet.toWebCharSet(permitVO.getPermitNo());
companyName = CharSet.toWebCharSet(permitVO.getCompanyName());
projectName = CharSet.toWebCharSet(permitVO.getProjectName());
bargainCode = CharSet.toWebCharSet(permitVO.getBargainCode());
landUsage = CharSet.toWebCharSet(permitVO.getLandUsage());
location = CharSet.toWebCharSet(permitVO.getLocation());
remark = CharSet.toWebCharSet(permitVO.getRemark());
Date tempDate = permitVO.getPassDate();
if(tempDate!=null){
	passDate = sdf1.format(permitVO.getPassDate());
}
ptFlag = permitVO.getPtFlag();
if (ptFlag == 1) {
	remark = "配套商品房";
}

//分析预售许可证号
String tmpChar= "";
String strOne = "";
String strTwo = "";
String strThree = "";
String strtmp = "";

if (permitNO != null && !permitNO.equals("")) {
	strOne=permitNO.substring(0,1);
	strTwo=permitNO.substring(1,5);
	strThree=permitNO.substring(5);
}

//若坐落长度超过40则分拆坐落
int maxloclen = 40 ;
String toploc = "";
String nextloc= "";
int locationlen = getStrActualLen(location.trim());
if (locationlen > maxloclen) 
{
	char[] arrchar = location.trim().toCharArray();
	String curChar = "";
	for(int i=0;i<arrchar.length;i++)
	{
		curChar = String.valueOf(arrchar[i]);
		int toploclen = getStrActualLen(toploc.trim());
		if (toploclen < maxloclen) {
			if (toploc.equals("")) {
				toploc = curChar;	
			} else {
				toploc = toploc + curChar;	
			}	
		} else {
			if (nextloc.equals("")) {
				nextloc = curChar;
			} else {
				nextloc = nextloc + curChar;
			}	
		}
	}
}

RealNo = CharSet.toWebCharSet(request.getAttribute("realNO"));

flArea = CharSet.toWebCharSet(request.getAttribute("flarea"));
cellarArea = CharSet.toWebCharSet(request.getAttribute("cellarArea"));

PermitDate = passDate;
//解析发证日期
String[] YearMonthDay;
String curyear = "";
String curmonth = "";
String curday = "";
if (PermitDate != null && !PermitDate.equals("")) {
	YearMonthDay = StringUtil.split(PermitDate,"-");
	curyear = YearMonthDay[0];
	curmonth = YearMonthDay[1];
	curday = YearMonthDay[2];
}


int totalsize = PreSaleHouseVOs.size();

int partsmaxlen = 16 ;  	    	     //室号最大长度,不可超出,否则转到下一栏位
boolean hasSecond = false;               //表示是否有一房屋归到下一栏位中
boolean hasFinished = false;		     //表示是否全部结束
int curpage = 0;			     //页码
int appendrows = 0;			     //附加空行数
int topvalue = 978;             //page add top value

%>
<body topmargin=14 leftmargin=30 onload="javascript:on_load()">
<%
int startX = 0;
int startY = 0;
do {
	curpage++;
	//String curtop1 = Integer.toString(120 + (curpage-1) * topvalue)+"PX";
	///String curtop2 = Integer.toString(166 + (curpage-1) * topvalue)+"PX";
	//String curtop3 = Integer.toString(223 + (curpage-1) * topvalue)+"PX";
	
	//第2页开始加上换行符
	if(curpage > 1) { %>
	    <TABLE CELLSPACING=0 CELLPADDING=0 style="page-break-before:always">
		<tr>
		<td></td>
		</tr>
		</table>		
<%	}  %>

<div style="position:absolute;top:<%=(125+startY)%>PX;left:<%=(490+startX)%>PX;Z-INDEX:0;">
	<TABLE CELLSPACING=0 CELLPADDING=0>
		<tr>
		<td colspan="2" style="FONT-SIZE:24px" align=right>
			<b><% if (printFlag == 2){%>(副本)<%} else if (printFlag == 3){%>(存根)<%}%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</B>
		</td>
		</tr>
	</table>
</div>
<div style="position:absolute;top:<%=(176+startY)%>PX;left:<%=(313+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td width=110 style="font-face:黑体"><b>青房注字</td>
			<td width=88><%=strTwo%></td>
			<td>第<%=strThree%></td>
		</tr>
	</table>
</div>
<div style="position:absolute;top:<%=(233+startY)%>PX;left:<%=(55+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td colspan="2"><font size="4" face="黑体"><%=companyName%></td>
		</tr>
	</table>
</div>
<%
String item1="";
String item2="";
String item3="";
String item4="";

String tmpItem1="";
String tmpItem2="";
String tmpItem3="";
String tmpItem4="";
String tmpItemLast="";

String strBargainCode=bargainCode;
int theConNoLen = 23;   //一行长度
int TotalLen_ConNo = strBargainCode.length();
int LineNum = 0;
int circle_i = 0;
//土地使用权出让合同编号总长度是否大于每行的长度
if(TotalLen_ConNo>theConNoLen)
{
	//计算共分多少行
	LineNum = TotalLen_ConNo/theConNoLen;   
	circle_i = 0;
	
	for(circle_i=0;circle_i<LineNum;circle_i++)
	{
		String s1 = strBargainCode.substring(theConNoLen*circle_i,theConNoLen*(circle_i+1));
		if (circle_i==0) {
			tmpItem1 = s1;
		} else if (circle_i==1) {
			tmpItem2 = s1;
		} else if (circle_i==2) {
			tmpItem3 = s1;
		} else if (circle_i==3) {
			tmpItem4 = s1;
		}
	}
	
	if(TotalLen_ConNo%theConNoLen>0)
	{
		String s1 = strBargainCode.substring(theConNoLen*circle_i);
		tmpItemLast = s1;
	}
	
	if (LineNum == 1) {
		item2 = tmpItem1;
		item4 = tmpItemLast;	
	} else if (LineNum == 2) {
		if (tmpItemLast.trim().equals("")) {
			item2 = tmpItem1;
			item4 = tmpItem2;
		} else {
			item1 = tmpItem1;
			item2 = tmpItem2;
			item4 = tmpItemLast;	
		}
	} else if (LineNum == 3) {
		if (tmpItemLast.trim().equals("")) {
			item1 = tmpItem1;
			item2 = tmpItem2;
			item4 = tmpItem3;
		} else {
			item1 = tmpItem1;
			item2 = tmpItem2;
			item3 = tmpItem3;
			item4 = tmpItemLast;
		}
	} else if (LineNum >=4) {
		item1 = tmpItem1;
		item2 = tmpItem2;
		item3 = tmpItem3;		 
		item4 = tmpItem4 + tmpItemLast;	
	}	
} else {
	tmpItem1 = strBargainCode;
	item2 = tmpItem1;
}
//String curtop4 = Integer.toString(383 + (curpage-1) * topvalue)+"PX";
//String curtop5 = Integer.toString(403 + (curpage-1) * topvalue)+"PX";
//String curtop6 = Integer.toString(423 + (curpage-1) * topvalue)+"PX";
//String curtop7 = Integer.toString(443 + (curpage-1) * topvalue)+"PX";
%>
<!--出让合同编号1-->
<div style="position:absolute;top:<%=(393+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td colspan="2">&nbsp;<%=item1%></td>
		</tr>
	</table>
</div>
<!--出让合同编号2-->
<div style="position:absolute;top:<%=(413+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td colspan="2">&nbsp;<%=item2%></td>
		</tr>
	</table>
</div>
<!--出让合同编号3-->
<div style="position:absolute;top:<%=(433+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td colspan="2">&nbsp;<%=item3%></td>
		</tr>
	</table>
</div>
<!--出让合同编号4-->
<div style="position:absolute;top:<%=(443+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td colspan="2">&nbsp;<%=item4%></td>
		</tr>
	</table>
</div>
<%
String itemRealNo1 = "";
String itemRealNo2 = "";

String tmpItemRealNo1="";
String tmpItemRealNo2="";
String tmpItemRealNoLast="";

String strRealNo = RealNo;

int theRealNoLen = 23;   //一行长度
int TotalLen_RealNo = strRealNo.length();
int rn_LineNum = 0;
int rn_circle_i = 0;
//房地产权证编号总长度是否大于每行的长度
if(TotalLen_RealNo>theRealNoLen)
{
	//计算共分多少行
	rn_LineNum = TotalLen_RealNo/theRealNoLen;   
	rn_circle_i = 0;
	
	for(rn_circle_i=0;rn_circle_i<rn_LineNum;rn_circle_i++)
	{
		String s1 = strRealNo.substring(theRealNoLen*rn_circle_i,theRealNoLen*(rn_circle_i+1));		
		if (rn_circle_i==0) {
			tmpItemRealNo1 = s1;
		} else if (rn_circle_i==1) {
			tmpItemRealNo2 = s1;
		} 
	}
	
	if(TotalLen_RealNo%theRealNoLen>0)
	{
		String s1 = strRealNo.substring(theRealNoLen*rn_circle_i);		
		tmpItemRealNoLast = s1;
	}
	
	if (rn_LineNum == 1) {
		itemRealNo1 = tmpItemRealNo1;
		itemRealNo2 = tmpItemRealNoLast;	
	} else if (LineNum >=2) {
		itemRealNo1 = tmpItemRealNo1;		 
		itemRealNo2 = tmpItemRealNo2 + tmpItemRealNoLast;	
	}	
} else {
	tmpItemRealNo1 = strRealNo;
	itemRealNo1 = tmpItemRealNo1;
}
//String curtop8 = Integer.toString(486 + (curpage-1) * topvalue)+"PX";
//String curtop9 = Integer.toString(526 + (curpage-1) * topvalue)+"PX";
///String curtop10 = Integer.toString(566 + (curpage-1) * topvalue)+"PX";
//String curtop11 = Integer.toString(607 + (curpage-1) * topvalue)+"PX";
%>
<!--权证编号1-->
<div style="position:absolute;top:<%=(496+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=itemRealNo1%></td>
		</tr>
	</table>
</div>
<!--权证编号2-->
<div style="position:absolute;top:<%=(536+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=itemRealNo2%></td>
		</tr>
	</table>
</div>
<!--项目名称-->
<div style="position:absolute;top:<%=(576+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=projectName%></td>
		</tr>
	</table>
</div>
<!--项目坐落-->
<div style="position:absolute;top:<%=(617+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<% if (!toploc.equals("") && !nextloc.equals("")) {%><%=toploc%><br><br>&nbsp;<%=nextloc%><%} else {%><%=location%><br><br>&nbsp;<%}%></td>
		</tr>
	</table>
</div>
<%
//String curtop12 = Integer.toString(689 + (curpage-1) * topvalue)+"PX";
//String curtop13 = Integer.toString(730 + (curpage-1) * topvalue)+"PX";
//String curtop14 = Integer.toString(770 + (curpage-1) * topvalue)+"PX";
//String curtop15 = Integer.toString(808 + (curpage-1) * topvalue)+"PX";
%>
<!--用途-->
<div style="position:absolute;top:<%=(698+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=landUsage%></td>
		</tr>
	</table>
</div>
<!--地上面积-->
<div style="position:absolute;top:<%=(740+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=flArea%></td>
		</tr>
	</table>
</div>
<!--地下面积-->
<div style="position:absolute;top:<%=(780+startY)%>PX;left:<%=(285+startX)%>PX;Z-INDEX:0;">
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td>&nbsp;<%=cellarArea%></td>
		</tr>
	</table>
</div>
<!--备注-->
<div style="position:absolute;top:<%=(818+startY)%>PX;left:<%=(175+startX)%>PX;Z-INDEX:0;" name=备注>
	<table cellspacing=0 cellpadding=0 border=0 width="500">
		<tr>
			<td style="FONT-SIZE:16px">&nbsp;<%=remark%></td>
		</tr>
	</table>
</div>
<%
//String curtop16 = Integer.toString(883 + (curpage-1) * topvalue)+"PX";
//String curtop17 = Integer.toString(932 + (curpage-1) * topvalue)+"PX";
%>
<div style="position:absolute;top:<%=(893+startY)%>PX;left:<%=(270+startX)%>PX;Z-INDEX:0;" name=发证机关>
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<td style="FONT-SIZE:18px"><font face="黑体"><%=strhead%><br></td>
		</tr>
	</table>
</div>
<div style="position:absolute;top:<%=(942+startY)%>PX;left:<%=(315+startX)%>PX;Z-INDEX:0;" name=日期>
	<table cellspacing=0 cellpadding=0 border=0>
		<tr>
			<tr><td width=110><%=curyear%></td><td width=130><%=curmonth%></td><td width=80><%=curday%></td>
		</tr>
	</table>
</div>
<%
//String curtop18 = Integer.toString(10 + (curpage-1) * topvalue)+"PX";
%>
<div style="position:absolute;top:<%=(15+startY)%>PX;left:<%=(690+startX)%>PX;Z-INDEX:0;">

<table cellspacing=0 cellpadding=0>
<td width="780" valign="top">
					<table cellspacing="0" cellpadding="0" width=100% height="890">
						<tr height=35><td align=center><font size="5" face="黑体">(核准预售许可的商品房明细表)</td></tr>
						
						<tr>
							<td>
							<table cellspacing=1 cellpadding=1 width=100% bgcolor=black  border=0 >
			
				<tr bgcolor=white><td align=center>幢号</td><td align=center>名义层</td><td align=center>室号</td><td align=center>房屋类型</td><td align=center>幢号</td><td align=center>名义层</td><td align=center>室号</td><td align=center>房屋类型</td></tr>
				<%
  
if (PreSaleHouseVOs!=null){
	if (!PreSaleHouseVOs.isEmpty()){
    		int listsize = PreSaleHouseVOs.size();
    		int i=0;
    		Iterator it = PreSaleHouseVOs.iterator();
    		hasSecond = false;
    		int j=0;
    		int rows = 0;
    		
    		String tmpbuildingnumber="";    			
    		String tmpfloor="";
    		String tmproomnumber="";     			
    		String tmpbuildingtype=""; 
    		String tmpbuildingtypeid=""; 
    			
    		String tmpbuildingnumber1="";    			
    		String tmpfloor1="";
    		String tmproomnumber1="";     			
    		String tmpbuildingtype1=""; 
    		String tmpbuildingtypeid1=""; 
    			
    		String tmpbuildingnumber2="";    
    		String tmpfloor2="";
    		String tmproomnumber2="";     			
    		String tmpbuildingtype2=""; 
    		String tmpbuildingtypeid2=""; 
    		//整行循环
    		while (it.hasNext()) {
    			if (hasSecond == false) {   	
    				i++;				 
	      			CHFlatVO houseVO = (CHFlatVO)it.next();
	      			//保存到临时变量里
	      			tmpbuildingnumber = houseVO.getAttribute("buildingNumber") == null ? "" : String.valueOf(houseVO.getAttribute("buildingNumber"));
	         		tmpfloor = CharSet.toWebCharSet(houseVO.getFloor_Name());
	         		tmproomnumber = CharSet.toWebCharSet(houseVO.getRoom_Number());
	         		tmpbuildingtype = houseVO.getAttribute("buildingType") == null ? "" : String.valueOf(houseVO.getAttribute("buildingType"));      		
	         		tmpbuildingtypeid = CharSet.toWebCharSet(houseVO.getBuilding_Type());
	         		
	         		//保存到记录1中
	         		tmpbuildingnumber1 = tmpbuildingnumber;
	         		tmpfloor1 = tmpfloor;
	         		tmproomnumber1 = tmproomnumber;
	         		tmpbuildingtype1 = tmpbuildingtype;
	         		tmpbuildingtypeid1 = tmpbuildingtypeid;      
         		} else {
         			//保存到记录1中
	         		tmpbuildingnumber1 = tmpbuildingnumber;
	         		tmpfloor1 = tmpfloor;
	         		tmproomnumber1 = tmproomnumber;
	         		tmpbuildingtype1 = tmpbuildingtype;
	         		tmpbuildingtypeid1 = tmpbuildingtypeid;     
         		}
         		tmpbuildingnumber = "";
	         	tmpfloor = "";
	         	tmproomnumber = "";
	         	tmpbuildingtype = "";
	         	tmpbuildingtypeid = "";    		
         		
         		j=0;
         		//一行左列循环
         		while (it.hasNext()){
         			i++;
         			CHFlatVO houseVO = (CHFlatVO)it.next();
      			
	         		tmpbuildingnumber = houseVO.getAttribute("buildingNumber") == null ? "" : String.valueOf(houseVO.getAttribute("buildingNumber"));
	         		tmpfloor = CharSet.toWebCharSet(houseVO.getFloor_Name());
	         		tmproomnumber = CharSet.toWebCharSet(houseVO.getRoom_Number());
	         		tmpbuildingtype = houseVO.getAttribute("buildingType") == null ? "" : String.valueOf(houseVO.getAttribute("buildingType"));      		
	         		tmpbuildingtypeid = CharSet.toWebCharSet(houseVO.getBuilding_Type());
	         		//和记录1比较,相同的幢号,层数和房屋类型则将室号合并
	         		//否则将记录保存到记录2中,用于下则比较
	         		if (tmpbuildingnumber.equals(tmpbuildingnumber1) && tmpfloor.equals(tmpfloor1) && tmpbuildingtypeid.equals(tmpbuildingtypeid1)){	         			
	         			if (tmproomnumber1.equals("")) {	         				
	         				tmproomnumber1 = tmproomnumber.trim();
	         			} else {	         				
	         				int frontstrlen = getStrActualLen(tmproomnumber1.trim());
	         				int backstrlen = getStrActualLen(tmproomnumber.trim());
	         				int curtotallen = frontstrlen + backstrlen ;
	         				if (curtotallen > partsmaxlen) {	         					
	         					tmpbuildingnumber2 = tmpbuildingnumber;
         						tmpfloor2 = tmpfloor;
         						tmproomnumber2 = tmproomnumber;
         						tmpbuildingtype2 = tmpbuildingtype;
         						tmpbuildingtypeid2 = tmpbuildingtypeid;  
         						tmproomnumber = "";
         						hasSecond = true;
	         					break;	
	         				} else {	         					
	         					tmproomnumber1 = tmproomnumber1.trim() + "," + tmproomnumber.trim();	 
	         					tmproomnumber = "";   				
	         				}
	         			}
	         		} else {	         			
	         			tmpbuildingnumber2 = tmpbuildingnumber;
         				tmpfloor2 = tmpfloor;
         				tmproomnumber2 = tmproomnumber;
         				tmpbuildingtype2 = tmpbuildingtype;
         				tmpbuildingtypeid2 = tmpbuildingtypeid;  
         				tmproomnumber = "";
         				hasSecond = true;
	         			break;	
	         		}        		       		
	         		j++;
	         			        			
		         	if (j==3) {
		         		hasSecond = false;
		         		break;	
		         	}
	         		        		
         		}
         		if (hasSecond == false && j!=0) {   	    	
    					if (it.hasNext()){ 
         					i++;
			      			CHFlatVO houseVO = (CHFlatVO)it.next();
			      			//保存到临时变量里
			         		tmpbuildingnumber = houseVO.getAttribute("buildingNumber") == null ? "" : String.valueOf(houseVO.getAttribute("buildingNumber"));
			         		tmpfloor = CharSet.toWebCharSet(houseVO.getFloor_Name());
			         		tmproomnumber = CharSet.toWebCharSet(houseVO.getRoom_Number());
			         		tmpbuildingtype = houseVO.getAttribute("buildingType") == null ? "" : String.valueOf(houseVO.getAttribute("buildingType"));      		
			         		tmpbuildingtypeid = CharSet.toWebCharSet(houseVO.getBuilding_Type());
			         		
			         		//保存到记录1中
			         		tmpbuildingnumber2 = tmpbuildingnumber;
			         		tmpfloor2 = tmpfloor;
			         		tmproomnumber2 = tmproomnumber;
			         		tmpbuildingtype2 = tmpbuildingtype;
			         		tmpbuildingtypeid2 = tmpbuildingtypeid;      
			         		tmproomnumber = "";
	         			}
         		}      		
         		j=0;
         		
         		//一行右列循环
         		while (it.hasNext()){
         			i++;
         			CHFlatVO houseVO = (CHFlatVO)it.next();
      			
	         		tmpbuildingnumber = houseVO.getAttribute("buildingNumber") == null ? "" : String.valueOf(houseVO.getAttribute("buildingNumber"));
	         		tmpfloor = CharSet.toWebCharSet(houseVO.getFloor_Name());
	         		tmproomnumber = CharSet.toWebCharSet(houseVO.getRoom_Number());
	         		tmpbuildingtype = houseVO.getAttribute("buildingType") == null ? "" : String.valueOf(houseVO.getAttribute("buildingType"));      		
	         		tmpbuildingtypeid = CharSet.toWebCharSet(houseVO.getBuilding_Type());
	         		
	         		if (tmpbuildingnumber.equals(tmpbuildingnumber2) && tmpfloor.equals(tmpfloor2) && tmpbuildingtypeid.equals(tmpbuildingtypeid2)){
	         			if (tmproomnumber2.equals("")) {
	         				tmproomnumber2 = tmproomnumber.trim();
	         			} else {
	         				int frontstrlen = getStrActualLen(tmproomnumber2.trim());
	         				int backstrlen = getStrActualLen(tmproomnumber.trim());
	         				int curtotallen = frontstrlen + backstrlen ;
	         				if (curtotallen > partsmaxlen) {
         						hasSecond = true;
	         					break;	
	         				} else {
	         					tmproomnumber2 = tmproomnumber2 + "," + tmproomnumber.trim();
	         					tmproomnumber = "";	         				
	         				}
	         			}
	         		} else {
	         			hasSecond = true;		         			
	         			break;
	         		}        		       		
	         		j++;
	         		if (hasSecond == true) {
	         			if (j==4) {
	         				hasSecond = false;
	         				break;	
	         			}
	         		} else {
	         			if (j==4) {
         					hasSecond = false;
	         				break;	
	         			}	         			
	         		}	
         		}   
         		rows++;  
         		appendrows  = 27 - rows;   		
%>
				<tr height=30 bgcolor=white>					
					<td align=center><%=tmpbuildingnumber1%></td>
					<td align=center><%=tmpfloor1%></td>
					<td>&nbsp;<%=tmproomnumber1%></td>
					<td align=center><%=tmpbuildingtype1%></td>
					<td align=center><%=tmpbuildingnumber2%></td>
					<td align=center><%=tmpfloor2%></td>
					<td>&nbsp;<%=tmproomnumber2%></td>
					<td align=center><%=tmpbuildingtype2%></td>
				</tr>
<% 
			//打印完毕,清空临时变量
			tmpbuildingnumber1 = "";
			tmpfloor1 = "";
			tmproomnumber1 = "";
			tmpbuildingtype1 = "";
			tmpbuildingnumber2 = "";
			tmpfloor2 = "";
			tmproomnumber2 = "";
			tmpbuildingtype2 = "";		
			if (rows == 27) 
			{
	         		PreSaleHouseVOs.subList(0,i-1).clear();
	         		hasFinished = false ;
	         		break;	
			} 					 						
     }        
     //hasSecond为TRUE表示记录没有全部打印完毕，listsize == i表示记录已经全部读出。
     if (tmproomnumber!=null && !tmproomnumber.equals("") && hasSecond == true && listsize == i) {
     %>
     			<tr height=30 bgcolor=white>
     				<td align=center><%=tmpbuildingnumber%></td>
					<td align=center><%=tmpfloor%></td>
					<td>&nbsp;<%=tmproomnumber%></td>
					<td align=center><%=tmpbuildingtype%></td>
					<td align=center>&nbsp;</td>
					<td align=center>&nbsp;</td>
					<td>&nbsp;(以下空白)</td>
					<td align=center>&nbsp;</td>	
					
									
					
				</tr>
     <%	
     }           	
         	if (listsize == i) {
         		hasFinished = true ;
         		%>
         		<tr height=30 bgcolor=white>					
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;(以下空白)</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
			</tr>
         		<%
         		for (int r=0;r<=appendrows-1;r++){
         		%>
         		<tr height=30 bgcolor=white>					
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
			</tr>
         		<%	
         		}
         	} else {
         		hasFinished = false ;
         		%>
         		<tr height=30 bgcolor=white>					
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;(续下页)</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;</td>
			</tr>
         		<%
         	}         	
         }
         
}
%>
			</table>
		</td>
	</tr>	
</table>

<table width=765 cellspacing=0 cellpadding=0>
	<tr height=5><td></td></tr>
	<tr><td align=right>(第<%=curpage%>页)&nbsp;&nbsp;&nbsp;</td></tr>
</table>
</table>
</div>
<%
} while (!PreSaleHouseVOs.isEmpty() && hasFinished==false);
%>

	<script type="text/javascript">
    	function MyPrint()
		{
			window.focus();
			window.print();
		}

		function on_load()
		{
		   if(<%="print".equals(cmd)%>)
		   {
		       MyPrint();
		   }
		}
    </script>
</body>
<%}catch(Exception e){
	e.printStackTrace();
	out.print(e.getMessage());
} %>
</html>