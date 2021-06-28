package com.netcom.nkestate.system.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;

@DBModel(tablename = "NON_LOGIN_TIME", sequence = "SEQ_NON_LOGIN_TIME", id = DBModel.SequenceID)
public class NonLoginTimeVO extends FHVO {

	private long Id; //  流水ID
	private String title; //  标题
	private String startDate; //  开始日期
	private long startTime; //  开始时间
	private String endDate; //  结束日期
	private long endTime; //  结束时间
	private long cacheTime; //  缓冲时间
	private long type; //  类型

	@DBMeta(column = "Id", name = "流水ID", type = "long", primarykey = "true", can_update = "false")
	public long getId() {
		return Id;
	}

	public void setId(long Id) {
		this.Id = Id;
		this.settings.put("Id", Id);
	}

	@DBMeta(column = "Title", name = "标题", type = "String")
	public String getTitle() {
		return title;
	}

	public void setTitle(String Title) {
		this.title = Title;
		this.settings.put("title", title);
	}

	@DBMeta(column = "Start_Date", name = "开始日期", type = "String")
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getStartDate2() {
		String str = startDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setStartDate(String StartDate) {
		this.startDate = StartDate;
		this.settings.put("startDate", startDate);
	}

	@DBMeta(column = "Start_Time", name = "开始时间", type = "long")
	public long getStartTime() {
		return startTime;
	}

	/**
	 * 功能描述：获取HH:mm时间类型
	 * @return
	 */
	public String getStartTime2() {
		String strTime = String.valueOf(startTime);
		if(strTime == null || strTime.trim().equals(""))
			return null;
		try{
			SimpleDateFormat datef;
			int len = 6;
			strTime = StringUtil.getFullwidthStr(strTime, len);

			datef = new SimpleDateFormat("HHmmss");

			Date date = datef.parse(strTime);

			SimpleDateFormat datef2 = new SimpleDateFormat("HH:mm");

			return datef2.format(date);
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 功能描述：获取YYYY-MM-DD HH:mm 类型
	 * @return
	 */
	public String getStartDateTime() {
		String date = this.getStartDate2();
		String time = this.getStartTime2();
		return date + " " + time;
	}
	public void setStartTime(long StartTime) {
		this.startTime = StartTime;
		this.settings.put("startTime", startTime);
	}

	@DBMeta(column = "End_Date", name = "结束日期", type = "String")
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getEndDate2() {
		String str = endDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setEndDate(String EndDate) {
		this.endDate = EndDate;
		this.settings.put("endDate", endDate);
	}

	@DBMeta(column = "End_Time", name = "结束时间", type = "long")
	public long getEndTime() {
		return endTime;
	}

	/**
	 * 功能描述：获取HH:mm时间类型
	 * @return
	 */
	public String getEndTime2() {
		String strTime = String.valueOf(endTime);
		if(strTime == null || strTime.trim().equals(""))
			return null;
		try{
			SimpleDateFormat datef;
			int len = 6;
			strTime = StringUtil.getFullwidthStr(strTime, len);
			datef = new SimpleDateFormat("HHmmss");

			Date date = datef.parse(strTime);

			SimpleDateFormat datef2 = new SimpleDateFormat("HH:mm");

			return datef2.format(date);
		}catch (Exception e){
			return null;
		}
	}
	public void setEndTime(long EndTime) {
		this.endTime = EndTime;
		this.settings.put("endTime", endTime);
	}

	/**
	 * 功能描述：获取YYYY-MM-DD HH:mm 类型
	 * @return
	 */
	public String getEndDateTime() {
		String date = this.getEndDate2();
		String time = this.getEndTime2();
		return date + " " + time;
	}

	@DBMeta(column = "Cache_Time", name = "缓冲时间", type = "long")
	public long getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(long CacheTime) {
		this.cacheTime = CacheTime;
		this.settings.put("cacheTime", cacheTime);
	}

	@DBMeta(column = "Type", name = "类型", type = "long", dict_table = "CT_401")
	public long getType() {
		return type;
	}

	public void setType(long Type) {
		this.type = Type;
		this.settings.put("type", type);
	}

}
