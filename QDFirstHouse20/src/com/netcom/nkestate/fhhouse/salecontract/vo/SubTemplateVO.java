package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SUBTEMPLATE", sequence = "SEQ_SUBTEMPLATE_ID", id = DBModel.SequenceID)
public class SubTemplateVO extends FHVO {

	private long subtmplID;//	流水号
	private String subtmplName;//	子模版名
	private long projectID;//	项目ID
	private long startID;//	开盘ID
	private int type;//	模版类型	CODE:517  1:预售模版、2:出售模版、7:交接书模版
	private long articleType;//	模版条款类型	"CODE:517_1 && 517_2 && 517_7(一期中为FIELDNAME)"
	private byte[] content;//	内容

	@DBMeta(column = "SUBTMPL_ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getSubtmplID() {
		return subtmplID;
	}

	public void setSubtmplID(long subtmplID) {
		this.subtmplID = subtmplID;
		this.settings.put("subtmplID", subtmplID);
	}

	@DBMeta(column = "SUBTMPLNAME", name = "子模板名", type = "String")
	public String getSubtmplName() {
		return subtmplName;
	}

	public void setSubtmplName(String subtmplName) {
		this.subtmplName = subtmplName;
		this.settings.put("subtmplName", subtmplName);
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

	@DBMeta(column = "TYPE", name = "模板类型", type = "int", dict_table = "CT_517")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.settings.put("type", type);
	}

	@DBMeta(column = "ARTICLETYPE", name = "模板条款类型", type = "long")
	public long getArticleType() {
		return articleType;
	}

	public void setArticleType(long articleType) {
		this.articleType = articleType;
		this.settings.put("articleType", articleType);
	}

	@DBMeta(column = "CONTENT", name = "内容", type = "byte[]")
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
		this.settings.put("content", content);
	}

}
