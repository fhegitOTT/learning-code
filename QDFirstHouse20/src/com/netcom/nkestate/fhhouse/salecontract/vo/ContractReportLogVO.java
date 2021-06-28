package com.netcom.nkestate.fhhouse.salecontract.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

/**
 * 合同打印日志表
 */
@DBModel(tablename = "CONTRACT_REPORT_LOG", sequence = "SEQ_ReportLogID", id = DBModel.SequenceID)
public class ContractReportLogVO extends AbstractBaseVO {

	private long reportLogID;
	private long contractID;
	private int userType;
	private long userID;
	private Date reportDate;

	@DBMeta(column = "reportLogID", name = "日志ID", type = "long", primarykey = "true", can_update = "false")
	public long getReportLogID() {
		return reportLogID;
	}

	public void setReportLogID(long reportLogID) {
		this.reportLogID = reportLogID;
		this.settings.put("reportLogID", reportLogID);
	}

	@DBMeta(column = "contractID", name = "合同号", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "userType", name = "用户类型", type = "int")
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
		this.settings.put("userType", userType);
	}

	@DBMeta(column = "userID", name = "用户id", type = "long")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "reportDate", name = "打印日期", type = "Date")
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
		this.settings.put("reportDate", reportDate);
	}


}
