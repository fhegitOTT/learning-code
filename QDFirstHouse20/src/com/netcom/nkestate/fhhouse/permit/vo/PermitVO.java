/**
 * <p>PermitVO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 许可证VO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "permit_t", sequence = "SEQ_PermitID", id = DBModel.SequenceID)
public class PermitVO extends AbstractBaseVO {
	protected long permitID; // 许可证编号(SEQ_PermitID)
	protected long transactionID; // 登记编号
	protected int districtID; // 区县编号
	protected String permitNo; // 许可证号
	protected int permitType; // 许可证类型(销售或预售)(common.CT_PERMITIONCERT_Type)
	protected Date ADate; // 项目开始日期
	protected String AAddress; // 国内预售地点
	protected int ASale; // 国内预售方式(common.CT_Presale_Type)
	protected String AAgent; // 国内预售代理单位
	protected String ALinker; // 国内预售地址电话
	protected String BAddress; // 境外预售地点
	protected int BSale; // 境外预售方式(CT_Presale_Type)
	protected String BAgent; // 境外预售代理
	protected String BLinker; // 境外预售地址电话
	protected double PArea; // 预计竣工面积
	protected int PSets; // 预计竣工套数
	protected Date PDate; // 预计竣工日期
	protected double RArea; // 实际竣工面积
	protected int RSets; // 实际竣工套数
	protected Date RDate; // 实际竣工日期
	protected String CNo; // 购取合同首号
	protected int CSets; // 购取合同份数
	protected int DType; // 房屋类型(CT_HType)
	protected int EType; // 建筑类型(CT_EType)
	protected int FType; // 房屋结构(CT_BUILDING_MATER)
	protected String HNo; // 房屋幢号
	protected double HFloor; // 层数
	protected double HSets; // 套数
	protected double LBuildArea; // 总建筑面积
	protected double inRoomArea; // 其中住宅面积
	protected double PSaleUnit; // 计划预售价
	protected int currency; // 币种(CT_CURRENCY_Type)
	protected double PSale1; // 许可面积
	protected double PSet1; // 许可套数
	protected double PSale2; // 销售面积
	protected double PSet2; // 销售套数
	protected int status; // 许可证状态,与主表applyVO 保持一致
	protected String remark; // 备注
	protected String movingPackage; // 迁移备注
	protected String bargainCode; // 土地使用权出让合同编号
	protected String landUsage; // 土地用途
	protected String location; // 坐落地址
	protected Date passDate; // 审批日期
	protected int ptFlag; // 配套商品房
//	protected int companyID; // 公司编号
//	protected long projectID; // 项目编号
	protected String projectName; // 项目名称
	protected String companyName; // 公司名称
	protected long modifyUser;
	protected Date modifyDate;

	@DBMeta(column = "permitID", name = "许可证编号", type = "long", primarykey = "true")
	public long getPermitID() {
		return permitID;
	}

	public void setPermitID(long permitID) {
		this.permitID = permitID;
		this.settings.put("permitID", permitID);
	}

	@DBMeta(column = "transactionID", name = "登记编号", type = "long")
	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "int",dict_table="ct_district")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "permitNo", name = "许可证号", type = "String")
	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
		this.settings.put("permitNo", permitNo);
	}

	@DBMeta(column = "permitType", name = "许可证类型", type = "int")
	public int getPermitType() {
		return permitType;
	}

	public void setPermitType(int permitType) {
		this.permitType = permitType;
		this.settings.put("permitType", permitType);
	}

	
	@DBMeta(column = "ADate", name = "项目开始日期", type = "Date")
	public Date getADate() {
		return ADate;
	}

	public void setADate(Date aDate) {
		ADate = aDate;
		this.settings.put("ADate", ADate);
	}

	@DBMeta(column = "AAddress", name = "国内预售地点", type = "String")
	public String getAAddress() {
		return AAddress;
	}

	public void setAAddress(String AAddress) {
		this.AAddress = AAddress;
		this.settings.put("AAddress", AAddress);
	}

	@DBMeta(column = "ASale", name = "国内预售方式", type = "int")
	public int getASale() {
		return ASale;
	}

	public void setASale(int ASale) {
		this.ASale = ASale;
		this.settings.put("ASale", ASale);
	}

	@DBMeta(column = "AAgent", name = "国内预售代理单位", type = "String")
	public String getAAgent() {
		return AAgent;
	}

	public void setAAgent(String AAgent) {
		this.AAgent = AAgent;
		this.settings.put("AAgent", AAgent);
	}

	@DBMeta(column = "ALinker", name = "国内预售地址电话", type = "String")
	public String getALinker() {
		return ALinker;
	}

	public void setALinker(String ALinker) {
		this.ALinker = ALinker;
		this.settings.put("ALinker", ALinker);
	}

	@DBMeta(column = "BAddress", name = "境外预售地点", type = "String")
	public String getBAddress() {
		return BAddress;
	}

	public void setBAddress(String BAddress) {
		this.BAddress = BAddress;
		this.settings.put("BAddress", BAddress);
	}

	@DBMeta(column = "BSale", name = "境外预售方式", type = "int")
	public int getBSale() {
		return BSale;
	}

	public void setBSale(int BSale) {
		this.BSale = BSale;
		this.settings.put("BSale", BSale);
	}

	@DBMeta(column = "BAgent", name = "境外预售代理", type = "String")
	public String getBAgent() {
		return BAgent;
	}

	public void setBAgent(String BAgent) {
		this.BAgent = BAgent;
		this.settings.put("BAgent", BAgent);
	}

	@DBMeta(column = "BLinker", name = "境外预售地址电话", type = "String")
	public String getBLinker() {
		return BLinker;
	}

	public void setBLinker(String BLinker) {
		this.BLinker = BLinker;
		this.settings.put("BLinker", BLinker);
	}

	@DBMeta(column = "PArea", name = "预计竣工面积", type = "double")
	public double getPArea() {
		return PArea;
	}

	public void setPArea(double PArea) {
		this.PArea = PArea;
		this.settings.put("PArea", PArea);
	}

	@DBMeta(column = "PSets", name = "预计竣工套数", type = "int")
	public int getPSets() {
		return PSets;
	}

	public void setPSets(int PSets) {
		this.PSets = PSets;
		this.settings.put("PSets", PSets);
	}

	
	@DBMeta(column = "PDate", name = "预计竣工日期", type = "Date")
	public Date getPDate() {
		return PDate;
	}

	public void setPDate(Date pDate) {
		PDate = pDate;
		this.settings.put("PDate", PDate);
	}

	@DBMeta(column = "RArea", name = "实际竣工面积", type = "double")
	public double getRArea() {
		return RArea;
	}

	public void setRArea(double RArea) {
		this.RArea = RArea;
		this.settings.put("RArea", RArea);
	}

	@DBMeta(column = "RSets", name = "实际竣工套数", type = "int")
	public int getRSets() {
		return RSets;
	}

	public void setRSets(int RSets) {
		this.RSets = RSets;
		this.settings.put("RSets", RSets);
	}

	@DBMeta(column = "RDate", name = "实际竣工日期", type = "Date")
	public Date getRDate() {
		return RDate;
	}

	public void setRDate(Date RDate) {
		this.RDate = RDate;
		this.settings.put("RDate", RDate);
	}

	@DBMeta(column = "CNo", name = "购取合同首号", type = "String")
	public String getCNo() {
		return CNo;
	}

	public void setCNo(String CNo) {
		this.CNo = CNo;
		this.settings.put("CNo", CNo);
	}

	@DBMeta(column = "CSets", name = "购取合同份数", type = "int")
	public int getCSets() {
		return CSets;
	}

	public void setCSets(int CSets) {
		this.CSets = CSets;
		this.settings.put("CSets", CSets);
	}

	@DBMeta(column = "DType", name = "房屋类型", type = "int")
	public int getDType() {
		return DType;
	}

	public void setDType(int DType) {
		this.DType = DType;
		this.settings.put("DType", DType);
	}

	@DBMeta(column = "EType", name = "建筑类型", type = "int")
	public int getEType() {
		return EType;
	}

	public void setEType(int EType) {
		this.EType = EType;
		this.settings.put("EType", EType);
	}

	@DBMeta(column = "FType", name = "房屋结构", type = "int")
	public int getFType() {
		return FType;
	}

	public void setFType(int FType) {
		this.FType = FType;
		this.settings.put("FType", FType);
	}

	@DBMeta(column = "HNo", name = "房屋幢号", type = "String")
	public String getHNo() {
		return HNo;
	}

	public void setHNo(String HNo) {
		this.HNo = HNo;
		this.settings.put("HNo", HNo);
	}

	@DBMeta(column = "HFloor", name = "层数", type = "double")
	public double getHFloor() {
		return HFloor;
	}

	public void setHFloor(double HFloor) {
		this.HFloor = HFloor;
		this.settings.put("HFloor", HFloor);
	}

	@DBMeta(column = "HSets", name = "套数", type = "double")
	public double getHSets() {
		return HSets;
	}

	public void setHSets(double HSets) {
		this.HSets = HSets;
		this.settings.put("HSets", HSets);
	}

	@DBMeta(column = "LBuildArea", name = "总建筑面积", type = "double")
	public double getLBuildArea() {
		return LBuildArea;
	}

	public void setLBuildArea(double LBuildArea) {
		this.LBuildArea = LBuildArea;
		this.settings.put("LBuildArea", LBuildArea);
	}

	@DBMeta(column = "inRoomArea", name = "其中住宅面积", type = "double")
	public double getInRoomArea() {
		return inRoomArea;
	}

	public void setInRoomArea(double inRoomArea) {
		this.inRoomArea = inRoomArea;
		this.settings.put("inRoomArea", inRoomArea);
	}

	@DBMeta(column = "PSaleUnit", name = "计划预售价", type = "double")
	public double getPSaleUnit() {
		return PSaleUnit;
	}

	public void setPSaleUnit(double PSaleUnit) {
		this.PSaleUnit = PSaleUnit;
		this.settings.put("PSaleUnit", PSaleUnit);
	}

	@DBMeta(column = "currency", name = "币种", type = "int")
	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
		this.settings.put("currency", currency);
	}

	@DBMeta(column = "PSale1", name = "许可面积", type = "double")
	public double getPSale1() {
		return PSale1;
	}

	public void setPSale1(double PSale1) {
		this.PSale1 = PSale1;
		this.settings.put("PSale1", PSale1);
	}

	@DBMeta(column = "PSet1", name = "许可套数", type = "double")
	public double getPSet1() {
		return PSet1;
	}

	public void setPSet1(double PSet1) {
		this.PSet1 = PSet1;
		this.settings.put("PSet1", PSet1);
	}

	@DBMeta(column = "PSale2", name = "销售面积", type = "double")
	public double getPSale2() {
		return PSale2;
	}

	public void setPSale2(double PSale2) {
		this.PSale2 = PSale2;
		this.settings.put("PSale2", PSale2);
	}

	@DBMeta(column = "PSet2", name = "销售套数", type = "double")
	public double getPSet2() {
		return PSet2;
	}

	public void setPSet2(double PSet2) {
		this.PSet2 = PSet2;
		this.settings.put("PSet2", PSet2);
	}

	@DBMeta(column = "status", name = "许可证状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "remark", name = "备注", type = "String")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		this.settings.put("remark", remark);
	}

	@DBMeta(column = "movingPackage", name = "迁移备注", type = "String")
	public String getMovingPackage() {
		return movingPackage;
	}

	public void setMovingPackage(String movingPackage) {
		this.movingPackage = movingPackage;
		this.settings.put("movingPackage", movingPackage);
	}

	@DBMeta(column = "bargainCode", name = "土地使用权出让合同编号", type = "String")
	public String getBargainCode() {
		return bargainCode;
	}

	public void setBargainCode(String bargainCode) {
		this.bargainCode = bargainCode;
		this.settings.put("bargainCode", bargainCode);
	}

	@DBMeta(column = "landUsage", name = "土地用途", type = "String")
	public String getLandUsage() {
		return landUsage;
	}

	public void setLandUsage(String landUsage) {
		this.landUsage = landUsage;
		this.settings.put("landUsage", landUsage);
	}

	@DBMeta(column = "location", name = "坐落地址", type = "String")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
	}

	@DBMeta(column = "passDate", name = "审批日期", type = "Date")
	public Date getPassDate() {
		return passDate;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
		this.settings.put("passDate", passDate);
	}

	@DBMeta(column = "ptFlag", name = "配套商品房", type = "int")
	public int getPtFlag() {
		return ptFlag;
	}

	public void setPtFlag(int ptFlag) {
		this.ptFlag = ptFlag;
		this.settings.put("ptFlag", ptFlag);
	}


	/*@DBMeta(column = "companyID", name = "公司编号", type = "int")
	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
		this.settings.put("companyID", companyID);
	}*/

	/*@DBMeta(column = "projectID", name = "项目编号", type = "long")
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);
	}*/

	@DBMeta(column = "projectName", name = "项目名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "companyName", name = "公司名称", type = "String")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		this.settings.put("companyName", companyName);
	}
	
	

	@DBMeta(column = "modifyUser", name = "修改人员", type = "long")
	public long getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
		this.settings.put("modifyUser", modifyUser);
	}
	@DBMeta(column = "modifyDate", name = "修改日期", type = "Date")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		this.settings.put("modifyDate", modifyDate);
	}

	public int getYgFlag() {
		return 0;
	}


	public String getCertNo() {
		return this.getPermitNo();
	}

	public int getIsPrecert() {
		return 0;
	}

	public void setCertNo(String certNO) {
		this.setPermitNo(certNO);
	}

	public long getRightID() {
		return this.getPermitID();
	}

	public void setRegUserID(long userID) {

	}

	public String getCertSerialNum() {
		return null;
	}

	public int getHolderCertType() {
		return 0;
	}

	public void setStartDate(Date day) {

	}

	public int getRealRightType() {
		return 0;
	}

	public void setCertSerialNum(String certSerialNum) {

	}
	
	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getADate2() {
		Date date = ADate;
		if(date != null){
			return DateUtil.formatDate(date);
		}else{
			return "";
		}
	}
	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getPDate2() {
		Date date = PDate;
		if(date != null){
			return DateUtil.formatDate(date);
		}else{
			return "";
		}
	}
	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getPassDate2() {
		Date date = passDate;
		if(date != null){
			return DateUtil.formatDate(date);
		}else{
			return "";
		}
	}
	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getRDate2() {
		Date date = RDate;
		if(date != null){
			return DateUtil.formatDate(date);
		}else{
			return "";
		}
	}
	
	public String getStatusName() {
		int sta = status;
		String name ="";
		switch (sta) {
		case 1:
			name = "受理";
			break;
		case 2:
			name = "审核";
			break;
		case 3:
			name = "发证";
			break;
		case -1:
			name = "归档";
			break;

		default:
			break;
		}
		
		return name;
	}
	
}
