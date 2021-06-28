/**
 * <p>ChargeVO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Dennis</p>
 * <p>创建时间: 2017-4-6<p>
 * 
 */
package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "CHARGE", sequence = "SEQ_CHARGE", id = DBModel.SequenceID)
public class ChargeVO extends FHVO {

	private long ID;//流水号
	private long signerID;//签约人ID
	private long houseID;//房屋ID
	private double chargeCount;//收费金额
	private double balance;//交易后余额

	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "SIGNER_ID", name = "签约人ID", type = "long")
	public long getSignerID() {
		return signerID;
	}

	public void setSignerID(long signerID) {
		this.signerID = signerID;
		this.settings.put("signerID", signerID);
	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "CHARGECOUNT", name = "收费金额", type = "double")
	public double getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(double chargeCount) {
		this.chargeCount = chargeCount;
		this.settings.put("chargeCount", chargeCount);
	}

	@DBMeta(column = "BALANCE", name = "交易后余额", type = "double")
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
		this.settings.put("balance", balance);
	}

}
