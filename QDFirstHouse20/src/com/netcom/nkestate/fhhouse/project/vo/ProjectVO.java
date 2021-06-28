/*
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发项目VO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 

package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PROJECT", sequence = "SEQ_PROJECT_ID", id = DBModel.SequenceID)
public class ProjectVO extends FHVO {

	private long project_ID; //  项目ID
	private int status; //  项目状态
	private String projectName; //  项目名称
	private String projectAdr; //  项目地址
	private String lotNum; //  土地使用权出让合同编号
	private int districtID; //  所在区县编号
	private String firstCensor; //  初审人
	private String firstAuditDate; //  初审日期
	private String firstMark; //  初审备注(初审意见)
	private String finalCensor; //  终审人
	private String finalAuditDate; //  终审日期
	private String finalMark; //  终审备注
	private String publishCensor; // 发布人
	private Date publishDate; //  	发布日期
	private String publishMark; //  	发布备注

	@DBMeta(column = "project_ID", name = "项目ID", type = "long", primarykey = "true", can_update = "false")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long project_ID) {
		this.project_ID = project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "status", name = "项目状态", type = "int", dict_table = "CT_309")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "projectName", name = "项目名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "projectAdr", name = "项目地址", type = "String")
	public String getProjectAdr() {
		return projectAdr;
	}

	public void setProjectAdr(String projectAdr) {
		this.projectAdr = projectAdr;
		this.settings.put("projectAdr", projectAdr);
	}

	@DBMeta(column = "lotNum", name = "土地使用权出让合同编号", type = "String")
	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
		this.settings.put("lotNum", lotNum);
	}

	@DBMeta(column = "districtID", name = "所在区县编号", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "firstCensor", name = "初审人", type = "String")
	public String getFirstCensor() {
		return firstCensor;
	}

	public void setFirstCensor(String firstCensor) {
		this.firstCensor = firstCensor;
		this.settings.put("firstCensor", firstCensor);
	}

	@DBMeta(column = "firstAuditDate", name = "初审日期", type = "String")
	public String getFirstAuditDate() {
		return firstAuditDate;
	}

	public void setFirstAuditDate(String firstAuditDate) {
		this.firstAuditDate = firstAuditDate;
		this.settings.put("firstAuditDate", firstAuditDate);
	}

	@DBMeta(column = "firstMark", name = "初审备注", type = "String")
	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String firstMark) {
		this.firstMark = firstMark;
		this.settings.put("firstMark", firstMark);
	}

	@DBMeta(column = "finalCensor", name = "终审人", type = "String")
	public String getFinalCensor() {
		return finalCensor;
	}

	public void setFinalCensor(String finalCensor) {
		this.finalCensor = finalCensor;
		this.settings.put("finalCensor", finalCensor);
	}

	@DBMeta(column = "finalAuditDate", name = "终审日期", type = "String")
	public String getFinalAuditDate() {
		return finalAuditDate;
	}

	public void setFinalAuditDate(String finalAuditDate) {
		this.finalAuditDate = finalAuditDate;
		this.settings.put("finalAuditDate", finalAuditDate);
	}

	@DBMeta(column = "finalMark", name = "终审备注", type = "String")
	public String getFinalMark() {
		return finalMark;
	}

	public void setFinalMark(String finalMark) {
		this.finalMark = finalMark;
		this.settings.put("finalMark", finalMark);
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


}
