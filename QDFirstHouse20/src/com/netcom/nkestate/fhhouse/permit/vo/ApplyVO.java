package com.netcom.nkestate.fhhouse.permit.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "APPLY_T", sequence = "", id = DBModel.AssignedID)
public class ApplyVO  extends AbstractBaseVO {
	private  long transactionID;/*申请案件ID*/
	private  long districtID;/*区县ID*/
	private  int state;/*状态 0：受理  1：提交   2：终审   3，缮证  -9：删除，-1，归档*/
	private  int isCenter;/*是否中心案件  0空：否   1：是*/
	private  String location;/*申请坐落*/
	private  String applicant;/*申请人*/
	private  int applicantCardtype;/*申请人证件类型*/
	private  String applicantCardno;/*申请人证件号码*/
	private  int applicantNationality;/*申请人国籍*/
	private  int applicantNative;/*申请人户籍*/
	private  String applicantAddr;/*申请人联系地址*/
	private  String applicantTel;/*申请人电话*/
	private  String applicantZip;/*申请人邮编*/
	private  String agentName;/*代理人*/
	private  int agentCardType;/*代理人证件类型*/
	private  String agentCardno;/*代理人证件号码*/
	private  String agentAddress;/*代理人地址*/
	private  String agentTel;/*代理人电话*/
	private  String agentZip;/*代理人邮编*/
	private  Date acceptDate;/*受理时间*/
	private  long acceptUser;/*受理人员*/
	private  Date auditDate;/*受理提交时间*/
	private  long auditUser;/*受理提交人员*/
	private  String auditOpinion;/*受理提交意见*/
	private  Date passDate;/*终审时间*/
	private  long passUser;/*终审人员*/
	private  Date printDate;/*缮证时间*/
	private  long printUser;/*缮证人员*/
	private  Date archiveDate;/*归档时间*/
	private  long archiveUser;/*归档人员*/
	private  String passOpinion;/*终审意见*/
	private  int passResult;/*终审结果  1：审核通过 ， -1：审核不通过*/
	private  Date modifyDate;/*记录修改数据*/
	private  long modifyUser;/*记录修改人员*/
    private  String memo;/*备注*/
	private  String imagePath;
    
    @DBMeta(column = "transactionID", name = "案件编号", type = "long", primarykey = "true")
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}
	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public long getDistrictID() {
		return districtID;
	}
	public void setDistrictID(long districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}
	@DBMeta(column = "state", name = "状态", type = "int")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		this.settings.put("state", state);
	}
	@DBMeta(column = "isCenter", name = "", type = "int")
	public int getIscenter() {
		return isCenter;
	}
	public void setIscenter(int isCenter) {
		this.isCenter = isCenter;
		this.settings.put("isCenter", isCenter);
	}
	@DBMeta(column = "location", name = "", type = "String")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
	}
	@DBMeta(column = "applicant", name = "", type = "String")
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
		this.settings.put("applicant", applicant);
	}
	@DBMeta(column = "applicantCardtype", name = "", type = "int")
	public int getApplicantCardtype() {
		return applicantCardtype;
	}
	public void setApplicantCardtype(int applicantCardtype) {
		this.applicantCardtype = applicantCardtype;
		this.settings.put("applicantCardtype", applicantCardtype);
	}
	@DBMeta(column = "applicantCardno", name = "", type = "String")
	public String getApplicantCardno() {
		return applicantCardno;
	}
	public void setApplicantCardno(String applicantCardno) {
		this.applicantCardno = applicantCardno;
		this.settings.put("applicantCardno", applicantCardno);
	}
	@DBMeta(column = "applicantNationality", name = "", type = "int")
	public int getApplicantNationality() {
		return applicantNationality;
	}
	public void setApplicantNationality(int applicantNationality) {
		this.applicantNationality = applicantNationality;
		this.settings.put("applicantNationality", applicantNationality);
	}
	@DBMeta(column = "applicantNative", name = "", type = "int")
	public int getApplicantNative() {
		return applicantNative;
	}
	public void setApplicantNative(int applicantNative) {
		this.applicantNative = applicantNative;
		this.settings.put("applicantNative", applicantNative);
	}
	@DBMeta(column = "applicantAddr", name = "", type = "String")
	public String getApplicantAddr() {
		return applicantAddr;
	}
	public void setApplicantAddr(String applicantAddr) {
		this.applicantAddr = applicantAddr;
		this.settings.put("applicantAddr", applicantAddr);
	}
	@DBMeta(column = "applicantTel", name = "", type = "String")
	public String getApplicantTel() {
		return applicantTel;
	}
	public void setApplicantTel(String applicantTel) {
		this.applicantTel = applicantTel;
		this.settings.put("applicantTel", applicantTel);
	}
	@DBMeta(column = "applicantZip", name = "", type = "String")
	public String getApplicantZip() {
		return applicantZip;
	}
	public void setApplicantZip(String applicantZip) {
		this.applicantZip = applicantZip;
		this.settings.put("applicantZip", applicantZip);
	}
	@DBMeta(column = "agentName", name = "", type = "String")
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
		this.settings.put("agentName", agentName);
	}
	@DBMeta(column = "agentCardType", name = "", type = "int")
	public int getAgentCardType() {
		return agentCardType;
	}
	public void setAgentCardType(int agentCardType) {
		this.agentCardType = agentCardType;
		this.settings.put("agentCardType", agentCardType);
	}
	@DBMeta(column = "agentCardno", name = "", type = "String")
	public String getAgentCardno() {
		return agentCardno;
	}
	public void setAgentCardno(String agentCardno) {
		this.agentCardno = agentCardno;
		this.settings.put("agentCardno", agentCardno);
	}
	@DBMeta(column = "agentAddress", name = "", type = "String")
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
		this.settings.put("agentAddress", agentAddress);
	}
	@DBMeta(column = "agentTel", name = "", type = "String")
	public String getAgentTel() {
		return agentTel;
	}
	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
		this.settings.put("agentTel", agentTel);
	}
	@DBMeta(column = "agentZip", name = "", type = "String")
	public String getAgentZip() {
		return agentZip;
	}
	public void setAgentZip(String agentZip) {
		this.agentZip = agentZip;
		this.settings.put("agentZip", agentZip);
	}
	@DBMeta(column = "acceptDate", name = "", type = "Date")
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
		this.settings.put("acceptDate", acceptDate);
	}
	@DBMeta(column = "acceptUser", name = "", type = "long")
	public long getAcceptUser() {
		return acceptUser;
	}
	public void setAcceptUser(long acceptUser) {
		this.acceptUser = acceptUser;
		this.settings.put("acceptUser", acceptUser);
	}
	@DBMeta(column = "auditDate", name = "", type = "Date")
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
		this.settings.put("auditDate", auditDate);
	}
	@DBMeta(column = "auditUser", name = "", type = "long")
	public long getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(long auditUser) {
		this.auditUser = auditUser;
		this.settings.put("auditUser", auditUser);
	}
	@DBMeta(column = "auditOpinion", name = "", type = "String")
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
		this.settings.put("auditOpinion", auditOpinion);
	}
	@DBMeta(column = "passDate", name = "", type = "Date")
	public Date getPassDate() {
		return passDate;
	}
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
		this.settings.put("passDate", passDate);
	}
	@DBMeta(column = "passUser", name = "", type = "long")
	public long getPassUser() {
		return passUser;
	}
	public void setPassUser(long passUser) {
		this.passUser = passUser;
		this.settings.put("passUser", passUser);
	}
	@DBMeta(column = "passOpinion", name = "", type = "String")
	public String getPassOpinion() {
		return passOpinion;
	}
	public void setPassOpinion(String passOpinion) {
		this.passOpinion = passOpinion;
		this.settings.put("passOpinion", passOpinion);
	}
	@DBMeta(column = "passResult", name = "", type = "int")
	public int getPassResult() {
		return passResult;
	}
	public void setPassResult(int passResult) {
		this.passResult = passResult;
		this.settings.put("passResult", passResult);
	}
	@DBMeta(column = "modifyDate", name = "", type = "Date")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		this.settings.put("modifyDate", modifyDate);
	}
	@DBMeta(column = "modifyUser", name = "", type = "long")
	public long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
		this.settings.put("modifyUser", modifyUser);
	}
	@DBMeta(column = "memo", name = "", type = "String")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
		this.settings.put("memo", memo);
	}
    
	@DBMeta(column = "imagePath", name = "照片路径", type = "String")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		this.settings.put("imagePath", imagePath);
	}
	@DBMeta(column = "printDate", name = "", type = "Date")
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
		this.settings.put("printDate", printDate);
	}
	@DBMeta(column = "printUser", name = "", type = "long")
	public long getPrintUser() {
		return printUser;
	}
	public void setPrintUser(long printUser) {
		this.printUser = printUser;
		this.settings.put("printUser", printUser);
	}
	@DBMeta(column = "archiveDate", name = "", type = "Date")
	public Date getArchiveDate() {
		return archiveDate;
	}
	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
		this.settings.put("archiveDate", archiveDate);
	}
	@DBMeta(column = "archiveUser", name = "", type = "long")
	public long getArchiveUser() {
		return archiveUser;
	}
	public void setArchiveUser(long archiveUser) {
		this.archiveUser = archiveUser;
		this.settings.put("archiveUser", archiveUser);
	}
	
}
