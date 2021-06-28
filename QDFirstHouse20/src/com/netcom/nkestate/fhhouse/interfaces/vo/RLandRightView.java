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

@DBModel(tablename = "VW_R_LAND_RIGHT", sequence = "", id = DBModel.AssignedID)
public class RLandRightView extends AbstractBaseVO implements IRUnitRightView {

	protected long lotID; // 地块编号
	protected int districtID; // 区县编号
	protected int rightType; // 权利类别
	protected long transactionID; // 登记编号
	protected long rightID; // 产权编号
	protected int isFinish; // 流程办结标志
	protected int ygFlag; // 单双方预告标志
	protected int rightStatus;
	protected int dbFlag = 1;
	protected long putinTransactionID; //  后手登记编号

	public int getDbFlag() {
		return dbFlag;
	}

	public int setDbFlag(int dbFlag) {
		return this.dbFlag = dbFlag;
	}
	@DBMeta(column = "lotID", name = "地块编号", type = "long")
	public long getLotID() {
		return lotID;
	}

	public void setLotID(long lotID) {
		this.lotID = lotID;
		this.settings.put("lotID", lotID);
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
		return this.lotID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nankang.qdestate.right.IRUnitRightView#getIsPrecert()
	 */
	public int getIsPrecert() {
		// TODO 自动生成的方法存根
		return 0;
	}
}
