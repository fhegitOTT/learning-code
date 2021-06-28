package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SS_CONTRACT_FHE_CONTRACT", sequence = "", id = DBModel.AssignedID)
public class ContractDealFHEVO extends AbstractBaseVO {//extends FHVO

	private long contractID;//合同ID  YYYY+m_sequence表中的T_CONTRACT记录序号
	private long projectID;//项目ID
	private long startID;//开盘ID
	private long houseID;//房屋ID  一期中为BARCODE        
	private int status;//状态		"CODE500 0:编辑状态、1:待签、2:已签、4:已登记、5:撤销（原来的内部撤销）、6:已领证、7:系统自动撤销（定金定合同过期）、8:已付定金、10:已受理、11:转签"
	private String sellerName;//甲方名称
	private String buyerName;//乙方名称


	private String xx0101;//合同ID  YYYY+m_sequence表中的T_CONTRACT记录序号
	private String xx0102;//项目ID 
	private String xx0103;//开盘ID
	private String xx0104;//房屋ID  一期中为BARCODE    
	private String xx0105;//房屋ID  一期中为BARCODE   
	private String xx0201;//状态		"CODE500 0:编辑状态、1:待签、2:已签、4:已登记、5:撤销（原来的内部撤销）、6:已领证、7:系统自动撤销（定金定合同过期）、8:已付定金、10:已受理、11:转签"
	private String xx0202;//甲方名称
	private String xx0301;//乙方名称
	private String xx0302;//房屋坐落
	private String xx0303;//乙方名称
	private String xx0304;//房屋坐落
	private String xx0305;//乙方名称
	private String xx0306;//房屋坐落


	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PROJECT_ID", name = "项目ID", type = "long")
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);
	}


	@DBMeta(column = "START_ID", name = "开盘ID", type = "long")
	public long getStartID() {
		return startID;
	}

	public void setStartID(long startID) {
		this.startID = startID;
		this.settings.put("startID", startID);
	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "STATUS", name = "状态", type = "int", dict_table = "CT_500")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	public String getStatusStr() {
		if(status == 1){
			return "草签";
		}else{
			return "已签";
		}
	}



	@DBMeta(column = "SELLER_NAME", name = "甲方名称", type = "String")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
		this.settings.put("sellerName", sellerName);
	}

	@DBMeta(column = "BUYER_NAME", name = "乙方名称", type = "String")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
		this.settings.put("buyerName", buyerName);
	}


	@DBMeta(column = "XX01_01", name = "甲方名称", type = "String")
	public String getXx0101() {
		return xx0101;
	}


	public void setXx0101(String xx0101) {
		this.xx0101 = xx0101;
	}

	@DBMeta(column = "XX01_02", name = "甲方名称", type = "String")
	public String getXx0102() {
		return xx0102;
	}


	public void setXx0102(String xx0102) {
		this.xx0102 = xx0102;
	}

	@DBMeta(column = "XX01_03", name = "甲方名称", type = "String")
	public String getXx0103() {
		return xx0103;
	}


	public void setXx0103(String xx0103) {
		this.xx0103 = xx0103;
	}

	@DBMeta(column = "XX01_04", name = "甲方名称", type = "String")
	public String getXx0104() {
		return xx0104;
	}


	public void setXx0104(String xx0104) {
		this.xx0104 = xx0104;
	}

	@DBMeta(column = "XX01_05", name = "甲方名称", type = "String")
	public String getXx0105() {
		return xx0105;
	}


	public void setXx0105(String xx0105) {
		this.xx0105 = xx0105;
	}

	@DBMeta(column = "XX02_01", name = "甲方名称", type = "String")
	public String getXx0201() {
		return xx0201;
	}


	public void setXx0201(String xx0201) {
		this.xx0201 = xx0201;
	}

	@DBMeta(column = "XX02_02", name = "甲方名称", type = "String")
	public String getXx0202() {
		return xx0202;
	}


	public void setXx0202(String xx0202) {
		this.xx0202 = xx0202;
	}

	@DBMeta(column = "XX03_01", name = "甲方名称", type = "String")
	public String getXx0301() {
		return xx0301;
	}


	public void setXx0301(String xx0301) {
		this.xx0301 = xx0301;
	}

	@DBMeta(column = "XX03_02", name = "甲方名称", type = "String")
	public String getXx0302() {
		return xx0302;
	}


	public void setXx0302(String xx0302) {
		this.xx0302 = xx0302;
	}

	@DBMeta(column = "XX03_03", name = "甲方名称", type = "String")
	public String getXx0303() {
		return xx0303;
	}


	public void setXx0303(String xx0303) {
		this.xx0303 = xx0303;
	}

	@DBMeta(column = "XX03_04", name = "甲方名称", type = "String")
	public String getXx0304() {
		return xx0304;
	}


	public void setXx0304(String xx0304) {
		this.xx0304 = xx0304;
	}

	@DBMeta(column = "XX03_05", name = "甲方名称", type = "String")
	public String getXx0305() {
		return xx0305;
	}


	public void setXx0305(String xx0305) {
		this.xx0305 = xx0305;
	}

	@DBMeta(column = "XX03_06", name = "甲方名称", type = "String")
	public String getXx0306() {
		return xx0306;
	}


	public void setXx0306(String xx0306) {
		this.xx0306 = xx0306;
	}


}
