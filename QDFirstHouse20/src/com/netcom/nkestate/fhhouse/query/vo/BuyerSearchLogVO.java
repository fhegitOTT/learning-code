package com.netcom.nkestate.fhhouse.query.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "BUYERSEARCH_LOG_T", sequence = "SEQ_BUYERSEARCH_LOGID", id = DBModel.SequenceID)
public class BuyerSearchLogVO extends AbstractBaseVO {

	private long logID;//
	private long searchUserID;//
	private String searchUserName;//
	private Date searchDate;//
	private String IPAddress;//
	private String buyerName;//
	private String buyerCardcode;//
	private String buyerBirth;//
	private String buyerNationality;//
	private String buyerProxy;//
	private String buyerCardname;//
	private String buyerType;//
	private String buyerProvince;//
	private String buyerAddress;//

	@DBMeta(column = "LogID", name = "LogID", type = "long", primarykey = "true", can_update = "false")
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}

	@DBMeta(column = "SearchUserID", name = "SearchUserID", type = "long")
	public long getSearchUserID() {
		return searchUserID;
	}

	public void setSearchUserID(long searchUserID) {
		this.searchUserID = searchUserID;
		this.settings.put("searchUserID", searchUserID);
	}

	@DBMeta(column = "SearchUserName", name = "SearchUserID", type = "String")
	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
		this.settings.put("searchUserName", searchUserName);
	}

	@DBMeta(column = "SearchDate", name = "SearchDate", type = "Date")
	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
		this.settings.put("searchDate", searchDate);
	}

	@DBMeta(column = "IPAddress", name = "IPAddress", type = "String")
	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		this.IPAddress = iPAddress;
		this.settings.put("IPAddress", IPAddress);
	}

	@DBMeta(column = "buyer_name", name = "BuyerName", type = "String")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
		this.settings.put("buyerName", buyerName);
	}

	@DBMeta(column = "buyer_cardcode", name = "BuyerCardcode", type = "String")
	public String getBuyerCardcode() {
		return buyerCardcode;
	}

	public void setBuyerCardcode(String buyerCardcode) {
		this.buyerCardcode = buyerCardcode;
		this.settings.put("buyerCardcode", buyerCardcode);
	}

	@DBMeta(column = "buyer_birth", name = "BuyerBirth", type = "String")
	public String getBuyerBirth() {
		return buyerBirth;
	}

	public void setBuyerBirth(String buyerBirth) {
		this.buyerBirth = buyerBirth;
		this.settings.put("buyerBirth", buyerBirth);
	}

	@DBMeta(column = "buyer_nationality", name = "BuyerNationality", type = "String")
	public String getBuyerNationality() {
		return buyerNationality;
	}

	public void setBuyerNationality(String buyerNationality) {
		this.buyerNationality = buyerNationality;
		this.settings.put("buyerNationality", buyerNationality);
	}

	@DBMeta(column = "buyer_proxy", name = "BuyerProxy", type = "String")
	public String getBuyerProxy() {
		return buyerProxy;
	}

	public void setBuyerProxy(String buyerProxy) {
		this.buyerProxy = buyerProxy;
		this.settings.put("buyerProxy", buyerProxy);
	}

	@DBMeta(column = "buyer_cardname", name = "BuyerCardname", type = "String")
	public String getBuyerCardname() {
		return buyerCardname;
	}

	public void setBuyerCardname(String buyerCardname) {
		this.buyerCardname = buyerCardname;
		this.settings.put("buyerCardname", buyerCardname);
	}

	@DBMeta(column = "buyer_type", name = "BuyerType", type = "String")
	public String getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(String buyerType) {
		this.buyerType = buyerType;
		this.settings.put("buyerType", buyerType);
	}

	@DBMeta(column = "buyer_province", name = "BuyerProvince", type = "String")
	public String getBuyerProvince() {
		return buyerProvince;
	}

	public void setBuyerProvince(String buyerProvince) {
		this.buyerProvince = buyerProvince;
		this.settings.put("buyerProvince", buyerProvince);
	}

	@DBMeta(column = "buyer_address", name = "BuyerAddress", type = "String")
	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
		this.settings.put("buyerAddress", buyerAddress);
	}

}
