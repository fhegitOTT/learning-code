package com.netcom.nkestate.fhhouse.salecontract.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "Log_ZBQuery_t", sequence = "SEQ_Log_ZBQuery_t", id = DBModel.SequenceID)
public class ZBQueryLogVO extends AbstractBaseVO {

	private long logID;//日志ID
	private String name;//姓名
	private String cardNo;//证件号
	private String request;//请求内容
	private String response;//响应内容
	private Date queryDate;//查询时间

	@DBMeta(column = "logID", name = "日志ID", type = "long", primarykey = "true", can_update = "false")
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}

	@DBMeta(column = "request", name = "请求内容", type = "String")
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
		this.settings.put("request", request);
	}

	@DBMeta(column = "response", name = "响应内容", type = "String")
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
		this.settings.put("response", response);
	}

	@DBMeta(column = "queryDate", name = "响应内容", type = "Date")
	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
		this.settings.put("queryDate", queryDate);
	}

	@DBMeta(column = "name", name = "姓名", type = "String")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "cardNo", name = "证件号", type = "String")
	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
		this.settings.put("cardNo", cardNo);
	}

}
