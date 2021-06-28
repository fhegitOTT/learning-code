package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH1", sequence = "", id = DBModel.AssignedID)
public class Attach1VO extends FHVO {

	private long contractID; //  	合同ID
	private int paymentType; //  	付款种类	CODE501 0:按时间付款、1:按条件付款
	private int paymentMode; //  	付款方式	CODE502 0:一次性付清、1:分期付款
	private int borrowMode; //  	贷款方式	CODE503 0:不贷款、1:组合贷款、2:公积金贷款、3:商业贷款、4:其他
	private byte[] content; //  	条件付款内容

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PAYMENTTYPE", name = "付款种类", type = "int", dict_table = "CT_501")
	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
		this.settings.put("paymentType", paymentType);
	}

	@DBMeta(column = "PAYMENTMODE", name = "付款方式", type = "int", dict_table = "CT_502")
	public int getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(int paymentMode) {
		this.paymentMode = paymentMode;
		this.settings.put("paymentMode", paymentMode);
	}

	@DBMeta(column = "BORROWMODE", name = "贷款方式", type = "int", dict_table = "CT_503")
	public int getBorrowMode() {
		return borrowMode;
	}

	public void setBorrowMode(int borrowMode) {
		this.borrowMode = borrowMode;
		this.settings.put("borrowMode", borrowMode);
	}

	@DBMeta(column = "CONTENT", name = "条件付款内容", type = "Clob")
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
		this.settings.put("content", content);
	}

}
