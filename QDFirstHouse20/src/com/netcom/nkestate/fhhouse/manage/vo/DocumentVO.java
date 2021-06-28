package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "DOCUMENT", sequence = "", id = DBModel.AssignedID)
public class DocumentVO extends FHVO {

	private long document_Id; // 	件袋ID
	private String document_Title; // 	件袋标题
	private String position; // 	位置
	private String project_Name; // 	项目名
	private String company_Name; // 	公司名
	private String content; // 	内容
	private int status; // 	收件状态	CODE300 0:未退回，1:已退回
	private String company_Address; // 	企业联系地址
	private String company_Phone; // 	企业联系电话
	private String apply_Proj_Addr; // 	申报项目地址
	private int proj_Loca_Region; // 	项目所处环线区分	CODE311 0:内环以内、1:中内环间、2:中外环间、3:外环以外
	private String agent; // 	代理人
	private String agent_Phone; // 	代理人联系电话
	private int districtid; // 	所属区县编号	CT_DISTRICT
	private String document_Fax; // 	传真
	private String document_Phone; // 	联系电话


	@DBMeta(column = "Document_Id", name = "件袋ID", type = "long", primarykey = "true", can_update = "false")
	public long getDocument_Id() {
		return document_Id;
	}

	public void setDocument_Id(long Document_Id) {
		this.document_Id = Document_Id;
		this.settings.put("document_Id", document_Id);
	}

	@DBMeta(column = "Document_Title", name = "件袋标题", type = "String")
	public String getDocument_Title() {
		return document_Title;
	}

	public void setDocument_Title(String Document_Title) {
		this.document_Title = Document_Title;
		this.settings.put("document_Title", document_Title);
	}

	@DBMeta(column = "Position", name = "位置", type = "String")
	public String getPosition() {
		return position;
	}

	public void setPosition(String Position) {
		this.position = Position;
		this.settings.put("position", position);
	}

	@DBMeta(column = "Project_Name", name = "项目名", type = "String")
	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String Project_Name) {
		this.project_Name = Project_Name;
		this.settings.put("project_Name", project_Name);
	}

	@DBMeta(column = "Company_Name", name = "公司名", type = "String")
	public String getCompany_Name() {
		return company_Name;
	}

	public void setCompany_Name(String Company_Name) {
		this.company_Name = Company_Name;
		this.settings.put("company_Name", company_Name);
	}


	@DBMeta(column = "Content", name = "内容", type = "String")
	public String getContent() {
		return content;
	}

	public void setContent(String Content) {
		this.content = Content;
		this.settings.put("content", content);
	}

	@DBMeta(column = "Status", name = "收件状态", type = "int", dict_table = "CT_300")
	public int getStatus() {
		return status;
	}

	public void setStatus(int Status) {
		this.status = Status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "Company_Address", name = "企业联系地址", type = "String")
	public String getCompany_Address() {
		return company_Address;
	}

	public void setCompany_Address(String Company_Address) {
		this.company_Address = Company_Address;
		this.settings.put("company_Address", company_Address);
	}

	@DBMeta(column = "Company_Phone", name = "企业联系电话", type = "String")
	public String getCompany_Phone() {
		return company_Phone;
	}

	public void setCompany_Phone(String Company_Phone) {
		this.company_Phone = Company_Phone;
		this.settings.put("company_Phone", company_Phone);
	}

	@DBMeta(column = "Apply_Proj_Addr", name = "申报项目地址", type = "String")
	public String getApply_Proj_Addr() {
		return apply_Proj_Addr;
	}

	public void setApply_Proj_Addr(String Apply_Proj_Addr) {
		this.apply_Proj_Addr = Apply_Proj_Addr;
		this.settings.put("apply_Proj_Addr", apply_Proj_Addr);
	}

	@DBMeta(column = "Proj_Loca_Region", name = "项目所处环线区分", type = "int", dict_table = "CT_311")
	public int getProj_Loca_Region() {
		return proj_Loca_Region;
	}

	public void setProj_Loca_Region(int Proj_Loca_Region) {
		this.proj_Loca_Region = Proj_Loca_Region;
		this.settings.put("proj_Loca_Region", proj_Loca_Region);
	}

	@DBMeta(column = "Agent", name = "代理人", type = "String")
	public String getAgent() {
		return agent;
	}

	public void setAgent(String Agent) {
		this.agent = Agent;
		this.settings.put("agent", agent);
	}

	@DBMeta(column = "Agent_Phone", name = "代理人联系电话", type = "String")
	public String getAgent_Phone() {
		return agent_Phone;
	}

	public void setAgent_Phone(String Agent_Phone) {
		this.agent_Phone = Agent_Phone;
		this.settings.put("agent_Phone", agent_Phone);
	}

	@DBMeta(column = "Districtid", name = "所属区县编号", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int Districtid) {
		this.districtid = Districtid;
		this.settings.put("districtid", districtid);
	}

	@DBMeta(column = "Document_Fax", name = "传真", type = "String")
	public String getDocument_Fax() {
		return document_Fax;
	}

	public void setDocument_Fax(String Document_Fax) {
		this.document_Fax = Document_Fax;
		this.settings.put("document_Fax", document_Fax);
	}

	@DBMeta(column = "Document_Phone", name = "联系电话", type = "String")
	public String getDocument_Phone() {
		return document_Phone;
	}

	public void setDocument_Phone(String Document_Phone) {
		this.document_Phone = Document_Phone;
		this.settings.put("document_Phone", document_Phone);
	}



}
