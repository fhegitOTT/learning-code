package com.netcom.nkestate.fhhouse.company.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SIGNER", sequence = "SEQ_SIGNER_ID", id = DBModel.SequenceID)
public class SignerVO extends FHVO {

	private long signer_ID; // 	签约人ID
	private String loginName; // 	登陆名
	private String pwd; // 	密码
	private String name; // 	姓名
	private String cardName; // 	执业证件名称
	private String cardCode; // 	执业证号码
	private String comp_ID; // 	企业ID
	private String brokercert; // 	身份证号码
	private int isEnable; // 	有无效区分
	private int status; // 	状态
	private String agent_ID; // 	代理企业ID
	private String firstCensor; // 	初审人
	private String firstAuditDate; // 	初审日期
	private String firstMark; // 	初审备注(初审意见)
	private String finalCensor; // 	终审人
	private String finalAuditDate; // 	终审日期
	private String finalMark; // 	终审备注
	private int changed; // 	密码修改区分
	private String repeal_Comment; // 	撤销备注
	private String publishCensor; // 发布人
	private Date publishDate; //  	发布日期
	private String publishMark; //  	发布备注
	private int canSeal; //  	1,允许盖章，0，不允许盖章
	private String keyID; //  	keyID

	@DBMeta(column = "Signer_ID", name = "签约人ID", type = "long", primarykey = "true", can_update = "false")
	public long getSigner_ID() {
		return signer_ID;
	}

	public void setSigner_ID(long Signer_ID) {
		this.signer_ID = Signer_ID;
		this.settings.put("signer_ID", signer_ID);
	}

	@DBMeta(column = "LoginName", name = "登陆名", type = "String")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String LoginName) {
		this.loginName = LoginName;
		this.settings.put("loginName", loginName);
	}

	@DBMeta(column = "Pwd", name = "密码", type = "String")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String Pwd) {
		this.pwd = Pwd;
		this.settings.put("pwd", pwd);
	}

	@DBMeta(column = "Name", name = "姓名", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "CardName", name = "执业证件名称", type = "String")
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String CardName) {
		this.cardName = CardName;
		this.settings.put("cardName", cardName);
	}

	@DBMeta(column = "CardCode", name = "执业证号码", type = "String")
	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String CardCode) {
		this.cardCode = CardCode;
		this.settings.put("cardCode", cardCode);
	}

	@DBMeta(column = "Comp_ID", name = "企业ID", type = "String")
	public String getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(String Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "Brokercert", name = "身份证号码", type = "String")
	public String getBrokercert() {
		return brokercert;
	}

	public void setBrokercert(String Brokercert) {
		this.brokercert = Brokercert;
		this.settings.put("brokercert", brokercert);
	}

	@DBMeta(column = "IsEnable", name = "有无效区分", type = "int", dict_table = "CT_535")
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int IsEnable) {
		this.isEnable = IsEnable;
		this.settings.put("isEnable", isEnable);
	}

	@DBMeta(column = "Status", name = "状态", type = "int", dict_table = "CT_313")
	public int getStatus() {
		return status;
	}

	public void setStatus(int Status) {
		this.status = Status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "Agent_ID", name = "代理企业ID", type = "String")
	public String getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(String Agent_ID) {
		this.agent_ID = Agent_ID;
		this.settings.put("agent_ID", agent_ID);
	}

	@DBMeta(column = "FirstCensor", name = "初审人", type = "String")
	public String getFirstCensor() {
		return firstCensor;
	}

	public void setFirstCensor(String FirstCensor) {
		this.firstCensor = FirstCensor;
		this.settings.put("firstCensor", firstCensor);
	}

	@DBMeta(column = "FirstAuditDate", name = "初审日期", type = "String")
	public String getFirstAuditDate() {
		return firstAuditDate;
	}

	public void setFirstAuditDate(String FirstAuditDate) {
		this.firstAuditDate = FirstAuditDate;
		this.settings.put("firstAuditDate", firstAuditDate);
	}

	@DBMeta(column = "FirstMark", name = "初审备注", type = "String")
	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String FirstMark) {
		this.firstMark = FirstMark;
		this.settings.put("firstMark", firstMark);
	}

	@DBMeta(column = "FinalCensor", name = "终审人", type = "String")
	public String getFinalCensor() {
		return finalCensor;
	}

	public void setFinalCensor(String FinalCensor) {
		this.finalCensor = FinalCensor;
		this.settings.put("finalCensor", finalCensor);
	}

	@DBMeta(column = "FinalAuditDate", name = "终审日期", type = "String")
	public String getFinalAuditDate() {
		return finalAuditDate;
	}

	public void setFinalAuditDate(String FinalAuditDate) {
		this.finalAuditDate = FinalAuditDate;
		this.settings.put("finalAuditDate", finalAuditDate);
	}

	@DBMeta(column = "FinalMark", name = "终审备注", type = "String")
	public String getFinalMark() {
		return finalMark;
	}

	public void setFinalMark(String FinalMark) {
		this.finalMark = FinalMark;
		this.settings.put("finalMark", finalMark);
	}

	@DBMeta(column = "Changed", name = "密码修改区分", type = "int", dict_table = "CT_007")
	public int getChanged() {
		return changed;
	}

	public void setChanged(int Changed) {
		this.changed = Changed;
		this.settings.put("changed", changed);
	}

	@DBMeta(column = "Repeal_Comment", name = "撤销备注", type = "String")
	public String getRepeal_Comment() {
		return repeal_Comment;
	}

	public void setRepeal_Comment(String Repeal_Comment) {
		this.repeal_Comment = Repeal_Comment;
		this.settings.put("repeal_Comment", repeal_Comment);
	}

	@DBMeta(column = "publishCensor", name = "发布人", type = "String")
	public String getPublishCensor() {
		return publishCensor;
	}

	public void setPublishCensor(String publishCensor) {
		this.publishCensor = publishCensor;
		this.settings.put("publishCensor", publishCensor);
	}

	@DBMeta(column = "publishDate", name = "发布日期", type = "Date")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		this.settings.put("publishDate", publishDate);
	}

	@DBMeta(column = "publishMark", name = "发布备注", type = "String")
	public String getPublishMark() {
		return publishMark;
	}

	public void setPublishMark(String publishMark) {
		this.publishMark = publishMark;
		this.settings.put("publishMark", publishMark);
	}
	@DBMeta(column = "canSeal", name = "是否可以盖章", type = "int")
	public int getCanSeal() {
		return canSeal;
	}

	public void setCanSeal(int canSeal) {
		this.canSeal = canSeal;
		this.settings.put("canSeal", canSeal);
	}
	@DBMeta(column = "keyID", name = "keyID", type = "String")
	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
		this.settings.put("keyID", keyID);
	}


}
