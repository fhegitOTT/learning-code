package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL", sequence = "SEQ_PRESELL_ID", id = DBModel.SequenceID)
public class PresellVO extends FHVO {

	private long presell_ID; //  预售许可证/大产证ID
	private long project_ID; //  项目ID
	private String presell_Code; //  预售许可证/大产证编号
	private String presell_Desc; //  预售许可证/大产证描述
	private String presell_Area; //  区县
	private String presell_Year; //  年号
	private String prefix; //  产证描述中的前缀
	private int type; //  许可证/大产证区分

	@DBMeta(column = "presell_ID", name = "预售许可证/大产证ID", type = "long", primarykey = "true", can_update = "false")
	public long getPresell_ID() {
		return presell_ID;
	}

	public void setPresell_ID(long presell_ID) {
		this.presell_ID = presell_ID;
		this.settings.put("presell_ID", presell_ID);
	}

	@DBMeta(column = "project_ID", name = "项目ID", type = "long")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long project_ID) {
		this.project_ID = project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "presell_Code", name = "预售许可证/大产证编号", type = "String")
	public String getPresell_Code() {
		return presell_Code;
	}

	public void setPresell_Code(String presell_Code) {
		this.presell_Code = presell_Code;
		this.settings.put("presell_Code", presell_Code);
	}

	@DBMeta(column = "presell_Desc", name = "预售许可证/大产证描述", type = "String")
	public String getPresell_Desc() {
		return presell_Desc;
	}

	public void setPresell_Desc(String presell_Desc) {
		this.presell_Desc = presell_Desc;
		this.settings.put("presell_Desc", presell_Desc);
	}

	@DBMeta(column = "presell_Area", name = "区县", type = "String")
	public String getPresell_Area() {
		return presell_Area;
	}

	public void setPresell_Area(String presell_Area) {
		this.presell_Area = presell_Area;
		this.settings.put("presell_Area", presell_Area);
	}

	@DBMeta(column = "presell_Year", name = "年号", type = "String")
	public String getPresell_Year() {
		return presell_Year;
	}

	public void setPresell_Year(String presell_Year) {
		this.presell_Year = presell_Year;
		this.settings.put("presell_Year", presell_Year);
	}

	@DBMeta(column = "prefix", name = "产证描述中的前缀", type = "String")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
		this.settings.put("prefix", prefix);
	}

	@DBMeta(column = "type", name = "许可证/大产证区分", type = "int", dict_table = "CT_312")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.settings.put("type", type);
	}

}
