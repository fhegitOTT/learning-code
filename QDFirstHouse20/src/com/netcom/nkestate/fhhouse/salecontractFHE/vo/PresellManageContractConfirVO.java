package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_MANAGE_CONTRACT_CONFIR", sequence = "", id = DBModel.AssignedID)
public class PresellManageContractConfirVO extends AbstractBaseVO {

	private long contractNum; //  	合同编号
	private String opcode; //  	预售合同号
	private long houseNum; //  	房屋编号
	private String buyerName; //  	买方姓名
	private String buyerAcctName; //  	买方指定个人账户
	private String buyerAcct; //  	买方个人帐号
	private String buyerIDType; //  	买方帐号类型
	private String buyerIDCode; //  	买方证件号码
	private String sellerName; //  	房地产商名称
	private String sellerCopertate; //  	房地产商法人代表
	private String sellerTel; //  	法人代表联系电话
	private String sellerLicense; //  	房地产商营业执照
	private String agent; //  	代理人
	private String agentTel; //  	代理人联系电话
	private String manageAcct; //  	监管帐号
	private String manageAcctName; //  	监管帐号对应的户名
	private String manageBankName; //  	监管账户的开户行
	private double contractamt; //  	合同金额
	private double superscalaramt; //  	超标款金额
	private double houseArea; //  	住房建筑面积
	private String projectName; //  	项目名称
	private String projectAddress; //  	项目地址
	private String houseInfo; //  	房屋楼栋信息
	private String houseType; //  	房屋类型
	private double superscalarprice; //  	超标面积价款
	private String specialAcctName; //  	上缴财政专用账户名
	private String specialAcct; //  	上缴财政专用帐号
	private String signDate; //  	签约日期
	private String dataKind; //  	合同状态	1、代表新增，0、撤销，2、修改
	private String proFlag; //		PRO_FLAG
	private double totalAmt; //  	房款总价
	private double deposit; //  	定金
	private String depositDate; //  	定金到位日期
	private double downpay; //  	首付款
	private String downpayDate; //  	首付款到位日期
	private double loan; //  	贷款
	private String loanDate; //  	贷款到位日期
	private String loanFrom; //  	贷款来源
	private double otheRamt; //  	其他款项
	private String str3; //  	备用
	private Date confirmTime; //  	确认时间

	@DBMeta(column = "CONTRACTNUM", name = "合同编号", type = "long", primarykey = "true", can_update = "false")
	public long getContractNum() {
		return contractNum;
	}

	public void setContractNum(long contractNum) {
		this.contractNum = contractNum;
		this.settings.put("contractNum", contractNum);
	}

	@DBMeta(column = "OPCODE", name = "预售合同号", type = "String")
	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
		this.settings.put("opcode", opcode);
	}

	@DBMeta(column = "HOUSENUM", name = "房屋编号", type = "long")
	public long getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(long houseNum) {
		this.houseNum = houseNum;
		this.settings.put("houseNum", houseNum);
	}

	@DBMeta(column = "BUYERNAME", name = "买方姓名", type = "String")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
		this.settings.put("buyerName", buyerName);
	}

	@DBMeta(column = "BUYERACCTNAME", name = "买方指定个人账户", type = "String")
	public String getBuyerAcctName() {
		return buyerAcctName;
	}

	public void setBuyerAcctName(String buyerAcctName) {
		this.buyerAcctName = buyerAcctName;
		this.settings.put("buyerAcctName", buyerAcctName);
	}

	@DBMeta(column = "BUYERACCT", name = "买方个人帐号", type = "String")
	public String getBuyerAcct() {
		return buyerAcct;
	}

	public void setBuyerAcct(String buyerAcct) {
		this.buyerAcct = buyerAcct;
		this.settings.put("buyerAcct", buyerAcct);
	}

	@DBMeta(column = "BUYERIDTYPE", name = "买方帐号类型", type = "String")
	public String getBuyerIDType() {
		return buyerIDType;
	}

	public void setBuyerIDType(String buyerIDType) {
		this.buyerIDType = buyerIDType;
		this.settings.put("buyerIDType", buyerIDType);
	}

	@DBMeta(column = "BUYERIDCODE", name = "买方证件号码", type = "String")
	public String getBuyerIDCode() {
		return buyerIDCode;
	}

	public void setBuyerIDCode(String buyerIDCode) {
		this.buyerIDCode = buyerIDCode;
		this.settings.put("buyerIDCode", buyerIDCode);
	}

	@DBMeta(column = "SELLERNAME", name = "房地产商名称", type = "String")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
		this.settings.put("sellerName", sellerName);
	}

	@DBMeta(column = "SELLERCOPERTATE", name = "房地产商法人代表", type = "String")
	public String getSellerCopertate() {
		return sellerCopertate;
	}

	public void setSellerCopertate(String sellerCopertate) {
		this.sellerCopertate = sellerCopertate;
		this.settings.put("sellerCopertate", sellerCopertate);
	}

	@DBMeta(column = "SELLERTEL", name = "法人代表联系电话", type = "String")
	public String getSellerTel() {
		return sellerTel;
	}

	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
		this.settings.put("sellerTel", sellerTel);
	}

	@DBMeta(column = "SELLERLICENSE", name = "房地产商营业执照", type = "String")
	public String getSellerLicense() {
		return sellerLicense;
	}

	public void setSellerLicense(String sellerLicense) {
		this.sellerLicense = sellerLicense;
		this.settings.put("sellerLicense", sellerLicense);
	}

	@DBMeta(column = "AGENT", name = "代理人", type = "String")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
		this.settings.put("agent", agent);
	}

	@DBMeta(column = "AGENTTEL", name = "代理人联系电话", type = "String")
	public String getAgentTel() {
		return agentTel;
	}

	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
		this.settings.put("agentTel", agentTel);
	}

	@DBMeta(column = "MANAGEACCT", name = "监管帐号", type = "String")
	public String getManageAcct() {
		return manageAcct;
	}

	public void setManageAcct(String manageAcct) {
		this.manageAcct = manageAcct;
		this.settings.put("manageAcct", manageAcct);
	}

	@DBMeta(column = "MANAGEACCTNAME", name = "监管帐号对应的户名", type = "String")
	public String getManageAcctName() {
		return manageAcctName;
	}

	public void setManageAcctName(String manageAcctName) {
		this.manageAcctName = manageAcctName;
		this.settings.put("manageAcctName", manageAcctName);
	}

	@DBMeta(column = "MANAGEBANKNAME", name = "监管账户的开户行", type = "String")
	public String getManageBankName() {
		return manageBankName;
	}

	public void setManageBankName(String manageBankName) {
		this.manageBankName = manageBankName;
		this.settings.put("manageBankName", manageBankName);
	}

	@DBMeta(column = "CONTRACTAMT", name = "合同金额", type = "double")
	public double getContractamt() {
		return contractamt;
	}

	public void setContractamt(double contractamt) {
		this.contractamt = contractamt;
		this.settings.put("contractamt", contractamt);
	}

	@DBMeta(column = "SUPERSCALARAMT", name = "超标款金额", type = "double")
	public double getSuperscalaramt() {
		return superscalaramt;
	}

	public void setSuperscalaramt(double superscalaramt) {
		this.superscalaramt = superscalaramt;
		this.settings.put("superscalaramt", superscalaramt);
	}

	@DBMeta(column = "HOUSEAREA", name = "住房建筑面积", type = "double")
	public double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(double houseArea) {
		this.houseArea = houseArea;
		this.settings.put("houseArea", houseArea);
	}

	@DBMeta(column = "PROJECTNAME", name = "项目名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "PROJECTADDRESS", name = "项目地址", type = "String")
	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
		this.settings.put("projectAddress", projectAddress);
	}

	@DBMeta(column = "HOUSEINFO", name = "房屋楼栋信息", type = "String")
	public String getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
		this.settings.put("houseInfo", houseInfo);
	}

	@DBMeta(column = "HOUSETYPE", name = "房屋类型", type = "String")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
		this.settings.put("houseType", houseType);
	}

	@DBMeta(column = "SUPERSCALARPRICE", name = "超标面积价款", type = "double")
	public double getSuperscalarprice() {
		return superscalarprice;
	}

	public void setSuperscalarprice(double superscalarprice) {
		this.superscalarprice = superscalarprice;
		this.settings.put("superscalarprice", superscalarprice);
	}

	@DBMeta(column = "SPECIALACCTNAME", name = "上缴财政专用账户名", type = "String")
	public String getSpecialAcctName() {
		return specialAcctName;
	}

	public void setSpecialAcctName(String specialAcctName) {
		this.specialAcctName = specialAcctName;
		this.settings.put("specialAcctName", specialAcctName);
	}

	@DBMeta(column = "SPECIALACCT", name = "上缴财政专用帐号", type = "String")
	public String getSpecialAcct() {
		return specialAcct;
	}

	public void setSpecialAcct(String specialAcct) {
		this.specialAcct = specialAcct;
		this.settings.put("specialAcct", specialAcct);
	}

	@DBMeta(column = "SIGNDATE", name = "签约日期", type = "String")
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
		this.settings.put("signDate", signDate);
	}

	@DBMeta(column = "DATA_KIND", name = "合同状态", type = "String")
	public String getDataKind() {
		return dataKind;
	}

	public void setDataKind(String dataKind) {
		this.dataKind = dataKind;
		this.settings.put("dataKind", dataKind);
	}

	@DBMeta(column = "PRO_FLAG", name = "proFlag", type = "String")
	public String getProFlag() {
		return proFlag;
	}

	public void setProFlag(String proFlag) {
		this.proFlag = proFlag;
		this.settings.put("proFlag", proFlag);
	}

	@DBMeta(column = "TOTALAMT", name = "房款总价", type = "double")
	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
		this.settings.put("totalAmt", totalAmt);
	}

	@DBMeta(column = "DEPOSIT", name = "定金", type = "double")
	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
		this.settings.put("deposit", deposit);
	}

	@DBMeta(column = "DEPOSITDATE", name = "定金到位日期", type = "String")
	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
		this.settings.put("depositDate", depositDate);
	}

	@DBMeta(column = "DOWNPAY", name = "首付款", type = "double")
	public double getDownpay() {
		return downpay;
	}

	public void setDownpay(double downpay) {
		this.downpay = downpay;
		this.settings.put("downpay", downpay);
	}

	@DBMeta(column = "DOWNPAYDATE", name = "首付款到位日期", type = "String")
	public String getDownpayDate() {
		return downpayDate;
	}

	public void setDownpayDate(String downpayDate) {
		this.downpayDate = downpayDate;
		this.settings.put("downpayDate", downpayDate);
	}

	@DBMeta(column = "LOAN", name = "贷款", type = "double")
	public double getLoan() {
		return loan;
	}

	public void setLoan(double loan) {
		this.loan = loan;
		this.settings.put("loan", loan);
	}

	@DBMeta(column = "LOANDATE", name = "贷款到位日期", type = "String")
	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
		this.settings.put("loanDate", loanDate);
	}

	@DBMeta(column = "LOANFROM", name = "贷款来源", type = "String")
	public String getLoanFrom() {
		return loanFrom;
	}

	public void setLoanFrom(String loanFrom) {
		this.loanFrom = loanFrom;
		this.settings.put("loanFrom", loanFrom);
	}

	@DBMeta(column = "OTHERAMT", name = "其他款项", type = "double")
	public double getOtheRamt() {
		return otheRamt;
	}

	public void setOtheRamt(double otheRamt) {
		this.otheRamt = otheRamt;
		this.settings.put("otheRamt", otheRamt);
	}

	@DBMeta(column = "STR3", name = "备用", type = "String")
	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
		this.settings.put("str3", str3);
	}

	@DBMeta(column = "CONFIRM_TIME", name = "确认时间", type = "Date")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
		this.settings.put("confirmTime", confirmTime);
	}

}
