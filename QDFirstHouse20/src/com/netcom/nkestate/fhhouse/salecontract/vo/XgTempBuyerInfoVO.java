package com.netcom.nkestate.fhhouse.salecontract.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "XG_TEMPBUYERINFO", sequence = "", id = DBModel.AssignedID)
public class XgTempBuyerInfoVO extends AbstractBaseVO {

	private long limitSaleID; //  	编号 ID
	private long buyerRightID; //  	申请人ID
	private String buyerName; //  	购房人姓名
	private String tel; //  	联系电话
	private int buyerDistrictID; //  	购房人房屋区县
	private int districtID; //  	出具证明区县
	private String address; //  	拟购住房地址
	private int buyHouseType; //  	购房类别		1, 新建住房 2, 存量住房
	private int buyerOwnHouse; //  	购房人家庭拥有住房情况		 1, '2套及以上住房' 2, '1套住房' 3, '无住房'
	private Date fillDate; //  	填表日期
	private Date entryDate; //  	提交日期
	private int isDate; //  	是否超七天
	private int userID; //  	用户id
	private Date printDate; //  	打印时间
	private String memo; //  	备注
	private int houseNum; //  	房屋套数
	private int handleFlag; //  	编号状态
	private int isConfirm; //  	是否提交
	private Date confirmDate; //  	提交时间
	private int isInvalID; //  	是否作废
	private int confirmMan; //  	提交人
	private int printMan; //  	打印人
	private long usingState; //  	住房申请表的使用状态	0编号有效，1非本市家庭身份证无效，2购房人在合同乙方中不存在，3合同提交。（海信新添加字段）
	private String usingTime; //  	使用时间
	private String buyerCardno; //  	非本市家庭购房人身份证
	private String confirmName; //  	提交人姓名
	private String printName; //  	打印人姓名
	private int domicileType; //  	房型

	@DBMeta(column = "LIMITSALEID", name = "编号 ID", type = "long", primarykey = "true", can_update = "false")
	public long getLimitSaleID() {
		return limitSaleID;
	}

	public void setLimitSaleID(long limitSaleID) {
		this.limitSaleID = limitSaleID;
		this.settings.put("limitSaleID", limitSaleID);
	}

	@DBMeta(column = "BUYERRIGHTID", name = "申请人ID", type = "long")
	public long getBuyerRightID() {
		return buyerRightID;
	}

	public void setBuyerRightID(long buyerRightID) {
		this.buyerRightID = buyerRightID;
		this.settings.put("buyerRightID", buyerRightID);
	}

	@DBMeta(column = "BUYERNAME", name = "购房人姓名", type = "String")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
		this.settings.put("buyerName", buyerName);
	}

	@DBMeta(column = "TEL", name = "联系电话", type = "String")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
		this.settings.put("tel", tel);
	}

	@DBMeta(column = "BUYERDISTRICTID", name = "购房人房屋区县", type = "int")
	public int getBuyerDistrictID() {
		return buyerDistrictID;
	}

	public void setBuyerDistrictID(int buyerDistrictID) {
		this.buyerDistrictID = buyerDistrictID;
		this.settings.put("buyerDistrictID", buyerDistrictID);
	}

	@DBMeta(column = "DISTRICTID", name = "出具证明区县", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "ADDRESS", name = "拟购住房地址", type = "String")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		this.settings.put("address", address);
	}

	@DBMeta(column = "BUYHOUSETYPE", name = "购房类别", type = "int")
	public int getBuyHouseType() {
		return buyHouseType;
	}

	public void setBuyHouseType(int buyHouseType) {
		this.buyHouseType = buyHouseType;
		this.settings.put("buyHouseType", buyHouseType);
	}

	@DBMeta(column = "BUYEROWNHOUSE", name = "购房人家庭拥有住房情况", type = "int")
	public int getBuyerOwnHouse() {
		return buyerOwnHouse;
	}

	public void setBuyerOwnHouse(int buyerOwnHouse) {
		this.buyerOwnHouse = buyerOwnHouse;
		this.settings.put("buyerOwnHouse", buyerOwnHouse);
	}

	@DBMeta(column = "FILLDATE", name = "填表日期", type = "Date")
	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
		this.settings.put("fillDate", fillDate);
	}

	@DBMeta(column = "ENTRYDATE", name = "提交日期", type = "Date")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
		this.settings.put("entryDate", entryDate);
	}

	@DBMeta(column = "ISDATE", name = "是否超七天", type = "int")
	public int getIsDate() {
		return isDate;
	}

	public void setIsDate(int isDate) {
		this.isDate = isDate;
		this.settings.put("isDate", isDate);
	}

	@DBMeta(column = "USERID", name = "用户id", type = "int")
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "PRINTDATE", name = "打印时间", type = "Date")
	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
		this.settings.put("printDate", printDate);
	}

	@DBMeta(column = "MEMO", name = "备注", type = "String")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
		this.settings.put("memo", memo);
	}

	@DBMeta(column = "HOUSENUM", name = "房屋套数", type = "int")
	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
		this.settings.put("houseNum", houseNum);
	}

	@DBMeta(column = "HANDLEFLAG", name = "编号状态", type = "int")
	public int getHandleFlag() {
		return handleFlag;
	}

	public void setHandleFlag(int handleFlag) {
		this.handleFlag = handleFlag;
		this.settings.put("handleFlag", handleFlag);
	}

	@DBMeta(column = "ISCONFIRM", name = "是否提交", type = "int")
	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
		this.settings.put("isConfirm", isConfirm);
	}

	@DBMeta(column = "CONFIRMDATE", name = "提交时间", type = "Date")
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
		this.settings.put("confirmDate", confirmDate);
	}

	@DBMeta(column = "ISINVALID", name = "是否作废", type = "int")
	public int getIsInvalID() {
		return isInvalID;
	}

	public void setIsInvalID(int isInvalID) {
		this.isInvalID = isInvalID;
		this.settings.put("isInvalID", isInvalID);
	}

	@DBMeta(column = "CONFIRMMAN", name = "提交人", type = "int")
	public int getConfirmMan() {
		return confirmMan;
	}

	public void setConfirmMan(int confirmMan) {
		this.confirmMan = confirmMan;
		this.settings.put("confirmMan", confirmMan);
	}

	@DBMeta(column = "PRINTMAN", name = "打印人", type = "int")
	public int getPrintMan() {
		return printMan;
	}

	public void setPrintMan(int printMan) {
		this.printMan = printMan;
		this.settings.put("printMan", printMan);
	}

	@DBMeta(column = "USINGSTATE", name = "住房申请表的使用状态", type = "long")
	public long getUsingState() {
		return usingState;
	}

	public void setUsingState(long usingState) {
		this.usingState = usingState;
		this.settings.put("usingState", usingState);
	}

	@DBMeta(column = "USINGTIME", name = "使用时间", type = "String")
	public String getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(String usingTime) {
		this.usingTime = usingTime;
		this.settings.put("usingTime", usingTime);
	}

	@DBMeta(column = "BUYERCARDNO", name = "非本市家庭购房人身份证", type = "String")
	public String getBuyerCardno() {
		return buyerCardno;
	}

	public void setBuyerCardno(String buyerCardno) {
		this.buyerCardno = buyerCardno;
		this.settings.put("buyerCardno", buyerCardno);
	}

	@DBMeta(column = "CONFIRMNAME", name = "提交人姓名", type = "String")
	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
		this.settings.put("confirmName", confirmName);
	}

	@DBMeta(column = "PRINTNAME", name = "打印人姓名", type = "String")
	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
		this.settings.put("printName", printName);
	}

	@DBMeta(column = "DOMICILETYPE", name = "房型", type = "int")
	public int getDomicileType() {
		return domicileType;
	}

	public void setDomicileType(int domicileType) {
		this.domicileType = domicileType;
		this.settings.put("domicileType", domicileType);
	}

}
