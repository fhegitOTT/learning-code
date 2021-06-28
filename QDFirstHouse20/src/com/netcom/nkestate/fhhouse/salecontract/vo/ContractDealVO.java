package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "CONTRACTDEAL", sequence = "", id = DBModel.AssignedID)
public class ContractDealVO extends FHVO {

	private long contractID;//合同ID  YYYY+m_sequence表中的T_CONTRACT记录序号
	private long projectID;//项目ID
	private long presellID;//预售许可证/产证ID
	private long startID;//开盘ID
	private long houseID;//房屋ID  一期中为BARCODE        
	private int status;//状态		"CODE500 0:编辑状态、1:待签、2:已签、4:已登记、5:撤销（原来的内部撤销）、6:已领证、7:系统自动撤销（定金定合同过期）、8:已付定金、10:已受理、11:转签"
	private String signer;//销售人帐号
	private String signDate;//草签或提交时间
	private String confirmer;//合同确认人账号
	private String confirmDate;//签约确认时间
	private String operator;//内网操作员帐号
	private String operateDate;//状态变迁日期
	private int type;//合同类型     	 CODE516 1:预售合同、2:出售合同、3:定金合同
	private String brokercert;//销售员身份证
	private long landUseCode;//房屋用途代码
	private int landType;//备案系统房屋用     	CODE530 0:住宅、1:办公、2:商铺、3:其他
	private int deletedFlag;//逻辑删除标志		CODE537 0:未删除、1:已删除
	private String area;//房屋建筑面积
	private String money;//总房价款
	private String sellerName;//甲方名称
	private String buyerName;//乙方名称
	private String location;//房屋坐落
	private String road;//路
	private String alley;//号+栋
	private String buildingNumber;//单元
	private String laneName;//号（对应大机弄）
	private String subLane;//栋（对应大机支弄）
	private String contractversion; // 合同版本
	private String waitday; // 预定天数
	private String templateId;//2020.5.26 模板id

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

	@DBMeta(column = "PRESELL_ID", name = "预售许可证/产证ID", type = "long")
	public long getPresellID() {
		return presellID;
	}

	public void setPresellID(long presellID) {
		this.presellID = presellID;
		this.settings.put("presellID", presellID);
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

	@DBMeta(column = "SIGNER", name = "销售人帐号", type = "String")
	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
		this.settings.put("signer", signer);
	}

	@DBMeta(column = "SIGNDATE", name = "草签或提交时间", type = "String")
	public String getSignDate() {
		return signDate;
	}

	public String getSignDate1(){
		String signdateStr = null;
		try{
			if(signDate != null){
				signdateStr = DateUtil.formatDate(DateUtil.parseDateTime2(String.valueOf(signDate)));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return signdateStr;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
		this.settings.put("signDate", signDate);
	}

	@DBMeta(column = "CONFIRMER", name = "合同确认人账号", type = "String")
	public String getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
		this.settings.put("confirmer", confirmer);
	}

	@DBMeta(column = "CONFIRMDATE", name = "签约确认时间", type = "String")
	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
		this.settings.put("confirmDate", confirmDate);
	}

	@DBMeta(column = "OPERATOR", name = "内网操作帐号", type = "String")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
		this.settings.put("operator", operator);
	}

	@DBMeta(column = "OPERATEDATE", name = "状态变迁日期", type = "String")
	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
		this.settings.put("operateDate", operateDate);
	}

	@DBMeta(column = "TYPE", name = "合同类型", type = "int", dict_table = "CT_516")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.settings.put("type", type);
	}

	@DBMeta(column = "BROKERCERT", name = "销售员身份证", type = "String")
	public String getBrokercert() {
		return brokercert;
	}

	public void setBrokercert(String brokercert) {
		this.brokercert = brokercert;
		this.settings.put("brokercert", brokercert);
	}

	@DBMeta(column = "LAND_USE_CODE", name = "房屋用途代码", type = "long", dict_table = "CT_540")
	public long getLandUseCode() {
		return landUseCode;
	}

	public void setLandUseCode(long landUseCode) {
		this.landUseCode = landUseCode;
		this.settings.put("landUseCode", landUseCode);
	}

	@DBMeta(column = "LAND_TYPE", name = "备案系统房屋用途代码", type = "int", dict_table = "CT_530")
	public int getLandType() {
		return landType;
	}

	public void setLandType(int landType) {
		this.landType = landType;
		this.settings.put("landType", landType);
	}

	@DBMeta(column = "DELETEDFLAG", name = "逻辑删除标志", type = "int", dict_table = "CT_537")
	public int getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(int deletedFlag) {
		this.deletedFlag = deletedFlag;
		this.settings.put("deletedFlag", deletedFlag);
	}

	@DBMeta(column = "AREA", name = "房屋建筑面积", type = "String")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "MONEY", name = "总房价款", type = "String")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
		this.settings.put("money", money);
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

	@DBMeta(column = "LOCATION", name = "房屋坐落", type = "String")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
	}

	@DBMeta(column = "ROAD", name = "路", type = "String")
	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
		this.settings.put("road", road);
	}

	@DBMeta(column = "ALLEY", name = "号+栋", type = "String")
	public String getAlley() {
		return alley;
	}

	public void setAlley(String alley) {
		this.alley = alley;
		this.settings.put("alley", alley);
	}

	@DBMeta(column = "BUILDINGNUMBER", name = "单元", type = "String")
	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
		this.settings.put("buildingNumber", buildingNumber);
	}

	@DBMeta(column = "LANE_NAME", name = "号（对应大机弄）", type = "String")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
		this.settings.put("laneName", laneName);
	}

	@DBMeta(column = "SUB_LANE", name = "栋（对应大机支弄）", type = "String")
	public String getSubLane() {
		return subLane;
	}

	public void setSubLane(String subLane) {
		this.subLane = subLane;
		this.settings.put("subLane", subLane);
	}

	@DBMeta(column = "CONTRACTVERSION", name = "合同版本", type = "String")
	public String getContractversion() {
		return contractversion;
	}

	public void setContractversion(String contractversion) {
		this.contractversion = contractversion;
		this.settings.put("contractversion", contractversion);
	}

	@DBMeta(column = "waitday", name = "预定天数", type = "String")
	public String getWaitday() {
		return waitday;
	}

	public void setWaitday(String waitday) {
		this.waitday = waitday;
		this.settings.put("waitday", waitday);
	}
	
	
	@DBMeta(column = "templateId", name = "模板id", type = "String")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
		this.settings.put("templateId", templateId);
	}

	public String getConfirmDate1() {
		String confirmDate1 = this.confirmDate;
		if(confirmDate1 != null && !"".equals(confirmDate1)){
			try{
				confirmDate1 = DateUtil.formatDate(DateUtil.parseDateTime2(confirmDate1));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return confirmDate1;
	}
}
