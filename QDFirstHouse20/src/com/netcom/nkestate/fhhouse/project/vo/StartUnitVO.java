package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;

@DBModel(tablename = "START_UNIT", sequence = "SEQ_START_ID", id = DBModel.SequenceID)
public class StartUnitVO extends FHVO {

	private long start_ID; //  开盘ID
	private long presell_ID; //  预售许可证ID
	private String document_ID; //  件袋ID
	private String start_Code; //  开盘编号
	private String initial_Date; //  第一次开盘日期
	private String initial_Time; //  第一次开盘时间
	private String start_Date; //  开盘日期
	private String start_Time; //  开盘时间
	private int isSalable; //  开盘区分	CODE532 0:未开盘、1:已开盘、2:暂停销售
	private int status; //  审核状态	"CODE310 0:编辑、1:提交（待审）、2:终审通过、4:终审不通过、5:撤销、6:撤销恢复
	private String bookingOffice; //  售楼处地址
	private String bookingPhone; //  售楼处电话
	private long houseCount; //  房屋套数
	private String firstCensor; //  初审人
	private String firstAuditDate; //  初审日期
	private String firstMark; //  初审备注(初审意见)
	private String finalCensor; //  终审人
	private String finalAuditDate; //  终审日期
	private String finalMark; //  终审备注
	private String publishCensor; // 发布人
	private Date publishDate; //  	发布日期
	private String publishMark; //  	发布备注

	@DBMeta(column = "start_ID", name = "开盘ID", type = "long", primarykey = "true", can_update = "false")
	public long getStart_ID() {
		return start_ID;
	}

	public void setStart_ID(long start_ID) {
		this.start_ID = start_ID;
		this.settings.put("start_ID", start_ID);
	}

	@DBMeta(column = "presell_ID", name = "预售许可证ID", type = "long")
	public long getPresell_ID() {
		return presell_ID;
	}

	public void setPresell_ID(long presell_ID) {
		this.presell_ID = presell_ID;
		this.settings.put("presell_ID", presell_ID);
	}

	@DBMeta(column = "document_ID", name = "件袋ID", type = "String")
	public String getDocument_ID() {
		return document_ID;
	}

	public void setDocument_ID(String document_ID) {
		this.document_ID = document_ID;
		this.settings.put("document_ID", document_ID);
	}

	@DBMeta(column = "start_Code", name = "开盘编号", type = "String")
	public String getStart_Code() {
		return start_Code;
	}

	public void setStart_Code(String start_Code) {
		this.start_Code = start_Code;
		this.settings.put("start_Code", start_Code);
	}

	@DBMeta(column = "initial_Date", name = "第一次开盘日期", type = "String")
	public String getInitial_Date() {
		return initial_Date;
	}

	public void setInitial_Date(String initial_Date) {
		this.initial_Date = initial_Date;
		this.settings.put("initial_Date", initial_Date);
	}

	@DBMeta(column = "initial_Time", name = "第一次开盘时间", type = "String")
	public String getInitial_Time() {
		return initial_Time;
	}

	public void setInitial_Time(String initial_Time) {
		this.initial_Time = initial_Time;
		this.settings.put("initial_Time", initial_Time);
	}

	@DBMeta(column = "start_Date", name = "开盘日期", type = "String")
	public String getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
		this.settings.put("start_Date", start_Date);
	}

	@DBMeta(column = "start_Time", name = "开盘时间", type = "String")
	public String getStart_Time() {
		return start_Time;
	}

	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
		this.settings.put("start_Time", start_Time);
	}

	@DBMeta(column = "isSalable", name = "开盘区分", type = "int", dict_table = "CT_532")
	public int getIsSalable() {
		return isSalable;
	}

	public void setIsSalable(int isSalable) {
		this.isSalable = isSalable;
		this.settings.put("isSalable", isSalable);
	}

	@DBMeta(column = "status", name = "审核状态", type = "int", dict_table = "CT_310")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "bookingOffice", name = "售楼处地址", type = "String")
	public String getBookingOffice() {
		return bookingOffice;
	}

	public void setBookingOffice(String bookingOffice) {
		this.bookingOffice = bookingOffice;
		this.settings.put("bookingOffice", bookingOffice);
	}

	@DBMeta(column = "bookingPhone", name = "售楼处电话", type = "String")
	public String getBookingPhone() {
		return bookingPhone;
	}

	public void setBookingPhone(String bookingPhone) {
		this.bookingPhone = bookingPhone;
		this.settings.put("bookingPhone", bookingPhone);
	}

	@DBMeta(column = "houseCount", name = "房屋套数", type = "long")
	public long getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(long houseCount) {
		this.houseCount = houseCount;
		this.settings.put("houseCount", houseCount);
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


	public Date getInitialTime() {
		Date updateTime = null;
		if(initial_Date != null && !"".equals(initial_Date)){
			String date = initial_Date + StringUtil.getFullwidthStr(initial_Time, 6);
			try{
				updateTime = DateUtil.parseDateTime2(date);
			}catch (Exception e){
			}
		}
		return updateTime;
	}

	public String getInitialTimeStr() {

		return DateUtil.formatDateTime(this.getInitialTime());
	}

	public Date getStartTime() {
		Date updateTime = null;
		if(start_Date != null && !"".equals(start_Date)){
			String date = start_Date + StringUtil.getFullwidthStr(start_Time, 6);
			try{
				updateTime = DateUtil.parseDateTime2(date);
			}catch (Exception e){
			}
		}
		return updateTime;
	}

	public String getStartTimeStr() {

		return DateUtil.formatDateTime(this.getStartTime());
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getInitialDate2() {
		String str = initial_Date;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public String getStartDate2() {
		String str = start_Date;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public String getReason() {
		return "历史记录";
	}
}
