package com.netcom.nkestate.fhhouse.permit.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "R_House_Permit_T", description = "房屋许可证关联表", sequence = "", id = DBModel.AssignedID)
// R_House_Permit_T 房屋许可证关联表
public class RHousePermitVO extends AbstractBaseVO {
	protected long permitID; // 许可证编号
	protected long transactionID; // 登记号
	protected long houseID; // 房屋编号
	protected int districtID; // 区县编号
	protected int isFinish; // 流程结束标志
//	protected long contractNo; // 合同号(纳入网上销售（12个9）)
	protected int saleFlag; //
//	protected long putinTransactionID; //  后手登记编号
	protected Date modifyDate;
	protected long modifyUser;
	protected long room_number;
	@DBMeta(column = "permitID", name = "许可证编号", type = "long", primarykey = "true")
	public long getPermitID() {
		return permitID;
	}

	public void setPermitID(long permitID) {
		this.permitID = permitID;
		this.settings.put("permitID", permitID);
	}

	@DBMeta(column = "transactionID", name = "登记号", type = "long", primarykey = "true")
	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}

	@DBMeta(column = "houseID", name = "房屋编号", type = "long", primarykey = "true")
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

	@DBMeta(column = "isFinish", name = "流程结束标志", type = "int")
	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
		this.settings.put("isFinish", isFinish);
	}

	/*@DBMeta(column = "contractNo", name = "合同号", type = "long")
	public long getContractNo() {
		return contractNo;
	}

	public void setContractNo(long contractNo) {
		this.contractNo = contractNo;
		this.settings.put("contractNo", contractNo);
	}*/
	
	@DBMeta(column = "saleFlag", name = "", type = "int")
	public int getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(int saleFlag) {
		this.saleFlag = saleFlag;
		this.settings.put("saleFlag", saleFlag);
	}

	@DBMeta(column = "modifyDate", name = "修改时间", type = "Date")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		this.settings.put("modifyDate", modifyDate);
	}
	@DBMeta(column = "modifyUser", name = "修改人", type = "long")
	public long getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
		this.settings.put("modifyUser", modifyUser);
	}
	
	public String getSaleFlagStr(){
		long flag = saleFlag;
		if(flag == 1){
			return "是";
		}else{
			return "否";
		}
	}

	public long getRoom_number() {
		return room_number;
	}

	public void setRoom_number(long roomNumber) {
		room_number = roomNumber;
	}
	
	

}
