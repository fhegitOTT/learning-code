package com.netcom.nkestate.fhhouse.permit.vo;


import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "House_Sale_Log_t", sequence = "SEQ_HouseSaleLog", id = DBModel.SequenceID)
public class HouseSaleLogVO  extends AbstractBaseVO {
	protected long logID;
	protected long transactionID;
	protected int districtID;
	protected long houseID;
	protected int saleFlag;//1,可售，0不可售
	protected Date modifyDate;
	protected long modifyUser;
	protected String memo;
	
	@DBMeta(column = "logID", name = "收件编号", type = "long", primarykey = "true")
	public long getLogID() {
		return logID;
	}
	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}
	@DBMeta(column = "transactionID", name = "案件编号", type = "long")
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}
	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public int getDistrictID() {
		return districtID;
	}
	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}
	@DBMeta(column = "houseID", name = "房屋编号", type = "long")
	public long getHouseID() {
		return houseID;
	}
	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}
	@DBMeta(column = "saleFlag", name = "是否可售", type = "int")
	public int getSaleFlag() {
		return saleFlag;
	}
	public void setSaleFlag(int saleFlag) {
		this.saleFlag = saleFlag;
		this.settings.put("saleFlag", saleFlag);
	}
	@DBMeta(column = "modifyDate", name = "区县编号", type = "Date")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		this.settings.put("modifyDate", modifyDate);
	}
	@DBMeta(column = "modifyUser", name = "更新人", type = "long")
	public long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
		this.settings.put("modifyUser", modifyUser);
	}
	@DBMeta(column = "memo", name = "备注", type = "String")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
		this.settings.put("memo", memo);
	}
	
	
}
