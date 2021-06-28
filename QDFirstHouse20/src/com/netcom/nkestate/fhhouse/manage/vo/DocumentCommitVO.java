package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "DOCUMENTCOMMIT", sequence = "", id = DBModel.AssignedID)
public class DocumentCommitVO extends FHVO {

	private long document_ID; // 	件袋ID
	private int comp_Info_Num; // 	COMP_INFO_NUM
	private int agent_Info_Num; // 	AGENT_INFO_NUM
	private int signer_Reg_Num; // 	SIGNER_REG_NUM
	private int signer_Auth_Num; // 	SIGNER_AUTH_NUM
	private int bizcard_Num; // 	BIZCARD_NUM
	private int orgcard_Num; // 	ORGCARD_NUM
	private int tax_Reg_Num; // 	TAX_REG_NUM
	private int corp_Identity_Num; // 	CORP_IDENTITY_NUM
	private int signer_Identity_Num; // 	SIGNER_IDENTITY_NUM
	private int qualify_Num; // 	QUALIFY_NUM
	private int org_Backup_Num; // 	ORG_BACKUP_NUM
	private int agent_Contract_Num; // 	AGENT_CONTRACT_NUM
	private int price_Appl_Num; // 	PRICE_APPL_NUM
	private int project_Appl_Num; // 	PROJECT_APPL_NUM
	private int part_List_Num; // 	PART_LIST_NUM
	private int real_Cert_Num; // 	REAL_CERT_NUM
	private int mapping_Num; // 	MAPPING_NUM
	private int presell_Cert_Num; // 	PRESELL_CERT_NUM
	private int plan_Map_Num; // 	PLAN_MAP_NUM
	private int house_Mod_Num; // 	HOUSE_MOD_NUM
	private int part_Mod_Num; // 	PART_MOD_NUM
	private int signer_Mod_Auth_Num; // 	SIGNER_MOD_AUTH_NUM
	private int signer_Mod_Identity_Num; // 	SIGNER_MOD_IDENTITY_NUM
	private int agent_Identity_Num; // 	AGENT_IDENTITY_NUM
	private int movement_Cert_Num; // 	MOVEMENT_CERT_NUM
	private int explanation_Num; // 	EXPLANATION_NUM
	private int image_Num; // 	IMAGE_NUM
	private int etc_Num; // 	其他


	@DBMeta(column = "Document_ID", name = "件袋ID", type = "long", primarykey = "true", can_update = "false")
	public long getDocument_ID() {
		return document_ID;
	}

	public void setDocument_ID(long Document_ID) {
		this.document_ID = Document_ID;
		this.settings.put("document_ID", document_ID);
	}

	@DBMeta(column = "Comp_Info_Num", name = "COMP_INFO_NUM", type = "int")
	public int getComp_Info_Num() {
		return comp_Info_Num;
	}

	public void setComp_Info_Num(int Comp_Info_Num) {
		this.comp_Info_Num = Comp_Info_Num;
		this.settings.put("comp_Info_Num", comp_Info_Num);
	}

	@DBMeta(column = "Agent_Info_Num", name = "AGENT_INFO_NUM", type = "int")
	public int getAgent_Info_Num() {
		return agent_Info_Num;
	}

	public void setAgent_Info_Num(int Agent_Info_Num) {
		this.agent_Info_Num = Agent_Info_Num;
		this.settings.put("agent_Info_Num", agent_Info_Num);
	}

	@DBMeta(column = "Signer_Reg_Num", name = "SIGNER_REG_NUM", type = "int")
	public int getSigner_Reg_Num() {
		return signer_Reg_Num;
	}

	public void setSigner_Reg_Num(int Signer_Reg_Num) {
		this.signer_Reg_Num = Signer_Reg_Num;
		this.settings.put("signer_Reg_Num", signer_Reg_Num);
	}

	@DBMeta(column = "Signer_Auth_Num", name = "SIGNER_AUTH_NUM", type = "int")
	public int getSigner_Auth_Num() {
		return signer_Auth_Num;
	}

	public void setSigner_Auth_Num(int Signer_Auth_Num) {
		this.signer_Auth_Num = Signer_Auth_Num;
		this.settings.put("signer_Auth_Num", signer_Auth_Num);
	}

	@DBMeta(column = "Bizcard_Num", name = "BIZCARD_NUM", type = "int")
	public int getBizcard_Num() {
		return bizcard_Num;
	}

	public void setBizcard_Num(int Bizcard_Num) {
		this.bizcard_Num = Bizcard_Num;
		this.settings.put("bizcard_Num", bizcard_Num);
	}

	@DBMeta(column = "Orgcard_Num", name = "ORGCARD_NUM", type = "int")
	public int getOrgcard_Num() {
		return orgcard_Num;
	}

	public void setOrgcard_Num(int Orgcard_Num) {
		this.orgcard_Num = Orgcard_Num;
		this.settings.put("orgcard_Num", orgcard_Num);
	}

	@DBMeta(column = "Tax_Reg_Num", name = "TAX_REG_NUM", type = "int")
	public int getTax_Reg_Num() {
		return tax_Reg_Num;
	}

	public void setTax_Reg_Num(int Tax_Reg_Num) {
		this.tax_Reg_Num = Tax_Reg_Num;
		this.settings.put("tax_Reg_Num", tax_Reg_Num);
	}

	@DBMeta(column = "Corp_Identity_Num", name = "CORP_IDENTITY_NUM", type = "int")
	public int getCorp_Identity_Num() {
		return corp_Identity_Num;
	}

	public void setCorp_Identity_Num(int Corp_Identity_Num) {
		this.corp_Identity_Num = Corp_Identity_Num;
		this.settings.put("corp_Identity_Num", corp_Identity_Num);
	}

	@DBMeta(column = "Signer_Identity_Num", name = "SIGNER_IDENTITY_NUM", type = "int")
	public int getSigner_Identity_Num() {
		return signer_Identity_Num;
	}

	public void setSigner_Identity_Num(int Signer_Identity_Num) {
		this.signer_Identity_Num = Signer_Identity_Num;
		this.settings.put("signer_Identity_Num", signer_Identity_Num);
	}

	@DBMeta(column = "Qualify_Num", name = "QUALIFY_NUM", type = "int")
	public int getQualify_Num() {
		return qualify_Num;
	}

	public void setQualify_Num(int Qualify_Num) {
		this.qualify_Num = Qualify_Num;
		this.settings.put("qualify_Num", qualify_Num);
	}

	@DBMeta(column = "Org_Backup_Num", name = "ORG_BACKUP_NUM", type = "int")
	public int getOrg_Backup_Num() {
		return org_Backup_Num;
	}

	public void setOrg_Backup_Num(int Org_Backup_Num) {
		this.org_Backup_Num = Org_Backup_Num;
		this.settings.put("org_Backup_Num", org_Backup_Num);
	}

	@DBMeta(column = "Agent_Contract_Num", name = "AGENT_CONTRACT_NUM", type = "int")
	public int getAgent_Contract_Num() {
		return agent_Contract_Num;
	}

	public void setAgent_Contract_Num(int Agent_Contract_Num) {
		this.agent_Contract_Num = Agent_Contract_Num;
		this.settings.put("agent_Contract_Num", agent_Contract_Num);
	}

	@DBMeta(column = "Price_Appl_Num", name = "PRICE_APPL_NUM", type = "int")
	public int getPrice_Appl_Num() {
		return price_Appl_Num;
	}

	public void setPrice_Appl_Num(int Price_Appl_Num) {
		this.price_Appl_Num = Price_Appl_Num;
		this.settings.put("price_Appl_Num", price_Appl_Num);
	}

	@DBMeta(column = "Project_Appl_Num", name = "PROJECT_APPL_NUM", type = "int")
	public int getProject_Appl_Num() {
		return project_Appl_Num;
	}

	public void setProject_Appl_Num(int Project_Appl_Num) {
		this.project_Appl_Num = Project_Appl_Num;
		this.settings.put("project_Appl_Num", project_Appl_Num);
	}

	@DBMeta(column = "Part_List_Num", name = "PART_LIST_NUM", type = "int")
	public int getPart_List_Num() {
		return part_List_Num;
	}

	public void setPart_List_Num(int Part_List_Num) {
		this.part_List_Num = Part_List_Num;
		this.settings.put("part_List_Num", part_List_Num);
	}

	@DBMeta(column = "Real_Cert_Num", name = "REAL_CERT_NUM", type = "int")
	public int getReal_Cert_Num() {
		return real_Cert_Num;
	}

	public void setReal_Cert_Num(int Real_Cert_Num) {
		this.real_Cert_Num = Real_Cert_Num;
		this.settings.put("real_Cert_Num", real_Cert_Num);
	}

	@DBMeta(column = "Mapping_Num", name = "MAPPING_NUM", type = "int")
	public int getMapping_Num() {
		return mapping_Num;
	}

	public void setMapping_Num(int Mapping_Num) {
		this.mapping_Num = Mapping_Num;
		this.settings.put("mapping_Num", mapping_Num);
	}

	@DBMeta(column = "Presell_Cert_Num", name = "PRESELL_CERT_NUM", type = "int")
	public int getPresell_Cert_Num() {
		return presell_Cert_Num;
	}

	public void setPresell_Cert_Num(int Presell_Cert_Num) {
		this.presell_Cert_Num = Presell_Cert_Num;
		this.settings.put("presell_Cert_Num", presell_Cert_Num);
	}

	@DBMeta(column = "Plan_Map_Num", name = "PLAN_MAP_NUM", type = "int")
	public int getPlan_Map_Num() {
		return plan_Map_Num;
	}

	public void setPlan_Map_Num(int Plan_Map_Num) {
		this.plan_Map_Num = Plan_Map_Num;
		this.settings.put("plan_Map_Num", plan_Map_Num);
	}

	@DBMeta(column = "House_Mod_Num", name = "HOUSE_MOD_NUM", type = "int")
	public int getHouse_Mod_Num() {
		return house_Mod_Num;
	}

	public void setHouse_Mod_Num(int House_Mod_Num) {
		this.house_Mod_Num = House_Mod_Num;
		this.settings.put("house_Mod_Num", house_Mod_Num);
	}

	@DBMeta(column = "Part_Mod_Num", name = "PART_MOD_NUM", type = "int")
	public int getPart_Mod_Num() {
		return part_Mod_Num;
	}

	public void setPart_Mod_Num(int Part_Mod_Num) {
		this.part_Mod_Num = Part_Mod_Num;
		this.settings.put("part_Mod_Num", part_Mod_Num);
	}

	@DBMeta(column = "Signer_Mod_Auth_Num", name = "SIGNER_MOD_AUTH_NUM", type = "int")
	public int getSigner_Mod_Auth_Num() {
		return signer_Mod_Auth_Num;
	}

	public void setSigner_Mod_Auth_Num(int Signer_Mod_Auth_Num) {
		this.signer_Mod_Auth_Num = Signer_Mod_Auth_Num;
		this.settings.put("signer_Mod_Auth_Num", signer_Mod_Auth_Num);
	}

	@DBMeta(column = "Signer_Mod_Identity_Num", name = "SIGNER_MOD_IDENTITY_NUM", type = "int")
	public int getSigner_Mod_Identity_Num() {
		return signer_Mod_Identity_Num;
	}

	public void setSigner_Mod_Identity_Num(int Signer_Mod_Identity_Num) {
		this.signer_Mod_Identity_Num = Signer_Mod_Identity_Num;
		this.settings.put("signer_Mod_Identity_Num", signer_Mod_Identity_Num);
	}

	@DBMeta(column = "Agent_Identity_Num", name = "AGENT_IDENTITY_NUM", type = "int")
	public int getAgent_Identity_Num() {
		return agent_Identity_Num;
	}

	public void setAgent_Identity_Num(int Agent_Identity_Num) {
		this.agent_Identity_Num = Agent_Identity_Num;
		this.settings.put("agent_Identity_Num", agent_Identity_Num);
	}

	@DBMeta(column = "Movement_Cert_Num", name = "MOVEMENT_CERT_NUM", type = "int")
	public int getMovement_Cert_Num() {
		return movement_Cert_Num;
	}

	public void setMovement_Cert_Num(int Movement_Cert_Num) {
		this.movement_Cert_Num = Movement_Cert_Num;
		this.settings.put("movement_Cert_Num", movement_Cert_Num);
	}

	@DBMeta(column = "Explanation_Num", name = "EXPLANATION_NUM", type = "int")
	public int getExplanation_Num() {
		return explanation_Num;
	}

	public void setExplanation_Num(int Explanation_Num) {
		this.explanation_Num = Explanation_Num;
		this.settings.put("explanation_Num", explanation_Num);
	}

	@DBMeta(column = "Image_Num", name = "IMAGE_NUM", type = "int")
	public int getImage_Num() {
		return image_Num;
	}

	public void setImage_Num(int Image_Num) {
		this.image_Num = Image_Num;
		this.settings.put("image_Num", image_Num);
	}

	@DBMeta(column = "Etc_Num", name = "其他", type = "int")
	public int getEtc_Num() {
		return etc_Num;
	}

	public void setEtc_Num(int Etc_Num) {
		this.etc_Num = Etc_Num;
		this.settings.put("etc_Num", etc_Num);
	}

	@DBMeta(column = "CrePerson", name = "作成者", type = "String")
	public String getCrePerson() {
		return crePerson;
	}

	public void setCrePerson(String CrePerson) {
		this.crePerson = CrePerson;
		this.settings.put("crePerson", crePerson);
	}

	@DBMeta(column = "CreDate", name = "作成日期", type = "long")
	public long getCreDate() {
		return creDate;
	}

	public void setCreDate(long CreDate) {
		this.creDate = CreDate;
		this.settings.put("creDate", creDate);
	}

	@DBMeta(column = "CreTime", name = "作成时间", type = "long")
	public long getCreTime() {
		return creTime;
	}

	public void setCreTime(long CreTime) {
		this.creTime = CreTime;
		this.settings.put("creTime", creTime);
	}



}
