/**
 * <p>RHouseRightView.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017年3月15日<p>
 * 
 */
package com.netcom.nkestate.fhhouse.interfaces.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "VW_R_HOUSE_RIGHT", sequence = "", id = DBModel.AssignedID)
public class RHouseRightView extends AbstractBaseVO implements IRUnitRightView {
	protected long houseID; // 房屋编号
	protected int districtID; // 区县编号
	protected int rightType; // 权利类别
	protected long transactionID; // 登记编号
	protected long rightID; // 产权编号
	protected int isFinish; // 流程办结标志
	protected int isPrecert; // 预售标志
	protected int ygFlag; // 单双方预告标志
	protected int rightStatus;
	protected int dbFlag = 1;
	protected long putinTransactionID; //  后手登记编号
	protected int lotFlag;

	public int getDbFlag() {
		return dbFlag;
	}

	public void setDbFlag(int dbFlag) {
		this.dbFlag = dbFlag;
	}

	public long getPutinTransactionID() {
		return putinTransactionID;
	}

	public void setPutinTransactionID(long putinTransactionID) {
		this.putinTransactionID = putinTransactionID;
	}

	public int getLotFlag() {
		return lotFlag;
	}

	public void setLotFlag(int lotFlag) {
		this.lotFlag = lotFlag;
	}

	@DBMeta(column = "houseID", name = "房屋编号", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "rightType", name = "权利类别", type = "int")
	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
		this.settings.put("rightType", rightType);
	}

	@DBMeta(column = "transactionID", name = "登记编号", type = "long")
	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}

	@DBMeta(column = "rightID", name = "产权编号", type = "long")
	public long getRightID() {
		return rightID;
	}

	public void setRightID(long rightID) {
		this.rightID = rightID;
		this.settings.put("rightID", rightID);
	}

	@DBMeta(column = "isFinish", name = "流程办结标志", type = "int")
	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
		this.settings.put("isFinish", isFinish);
	}

	@DBMeta(column = "isPrecert", name = "预售标志", type = "int")
	public int getIsPrecert() {
		return isPrecert;
	}

	public void setIsPrecert(int isPrecert) {
		this.isPrecert = isPrecert;
		this.settings.put("isPrecert", isPrecert);
	}

	@DBMeta(column = "ygFlag", name = "单双方预告标志", type = "int")
	public int getYgFlag() {
		return ygFlag;
	}

	public void setYgFlag(int ygFlag) {
		this.ygFlag = ygFlag;
		this.settings.put("ygFlag", ygFlag);
	}

	public int getRightStatus() {
		return rightStatus;
	}


	public void setRightStatus(int rightStatus) {
		this.rightStatus = rightStatus;
	}

	public long getUnitID() {
		return this.houseID;
	}
}
