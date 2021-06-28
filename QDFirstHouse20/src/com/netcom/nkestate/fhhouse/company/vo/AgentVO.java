package com.netcom.nkestate.fhhouse.company.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "AGENT", sequence = "SEQ_AGENT_ID", id = DBModel.SequenceID)
public class AgentVO extends FHVO {

	private long agentID;//	代理企业编号
	private String agentCode;//	全国企业唯一码
	private String name;//	代理企业名称
	private int cprovProvinceCode;//	所在省市
	private int district;//	所在区县
	private String post;//	邮编
	private String tel;//	联系电话
	private String fax;//	传真
	private String bizRegisterNum;//	法人营业执照注册号
	private int regTypeNum;//	登记注册类型
	private String bizEndDate;//	营业执照到期日
	private String bizRegDate;//	工商注册日
	private String legalManCode;//	投资者法人代码或身份证号
	private String workScope;//	经营范围
	private String regadr;//	注册地址
	private String email;//	电子信箱
	private String bizadr;//	经营地址
	private String delegate;//	法人代表
	private String dlgCall;//	法人代表联系电话
	private String dlgCardname;//	法人代表证件名称
	private String dlgCardcode;//	法人代表证件号码
	private String contactadr;//	联系地址
	private String dlgPostcode;//	邮政编码
	private String proxy;//	代理人
	private String proxyCall;//	代理人电话
	private String proxyCardname;//	代理人证件名称
	private String proxyCardcode;//	代理人证件号码
	private String proxyContact;//	代理人联系地址
	private String proxyPostcode;//	代理人邮编
	private String firstCensor;//	初审人
	private String firstAuditDate;//	初审日期
	private String firstMark;//	初审备注（初审意见）
	private String finalCensor;//	终审人
	private String finalAuditDate;//	终审日期
	private String finalMark;//	终审备注
	private int status;//	当前状态


	@DBMeta(column = "Agent_Id", name = "代理企业编号", type = "long", primarykey = "true", can_update = "false")
	public long getAgentID() {
		return agentID;
	}

	public void setAgentID(long agentID) {
		this.agentID = agentID;
		this.settings.put("agentID", agentID);
	}

	@DBMeta(column = "AGENT_CODE", name = "全国企业唯一码", type = "String")
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
		this.settings.put("agentCode", agentCode);
	}

	@DBMeta(column = "Name", name = "代理企业名称", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "provinceCode", name = "所在省市", type = "int", dict_table = "CT_524")
	public int getCprovProvinceCode() {
		return cprovProvinceCode;
	}

	public void setCprovProvinceCode(int cprovProvinceCode) {
		this.cprovProvinceCode = cprovProvinceCode;
		this.settings.put("cprovProvinceCode", cprovProvinceCode);
	}

	@DBMeta(column = "district", name = "所在区县", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
		this.settings.put("district", district);
	}

	@DBMeta(column = "Post", name = "邮编", type = "String")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
		this.settings.put("post", post);
	}

	@DBMeta(column = "Tel", name = "联系电话", type = "String")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
		this.settings.put("tel", tel);
	}

	@DBMeta(column = "Fax", name = "传真", type = "String")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
		this.settings.put("fax", fax);
	}

	@DBMeta(column = "BIZREGISTER_NUM", name = "法人营业执照注册号", type = "String")
	public String getBizRegisterNum() {
		return bizRegisterNum;
	}

	public void setBizRegisterNum(String bizRegisterNum) {
		this.bizRegisterNum = bizRegisterNum;
		this.settings.put("bizRegisterNum", bizRegisterNum);
	}

	@DBMeta(column = "REGTYPE_NUM", name = "登记注册类型", type = "int", dict_table = "CT_302")
	public int getRegTypeNum() {
		return regTypeNum;
	}

	public void setRegTypeNum(int regTypeNum) {
		this.regTypeNum = regTypeNum;
		this.settings.put("regTypeNum", regTypeNum);
	}

	@DBMeta(column = "BIZEND_DATE", name = "营业执照到期日", type = "String")
	public String getBizEndDate() {
		return bizEndDate;
	}

	public void setBizEndDate(String bizEndDate) {
		this.bizEndDate = bizEndDate;
		this.settings.put("bizEndDate", bizEndDate);
	}

	@DBMeta(column = "BIZREG_DATE", name = "工商注册日", type = "String")
	public String getBizRegDate() {
		return bizRegDate;
	}

	public void setBizRegDate(String bizRegDate) {
		this.bizRegDate = bizRegDate;
		this.settings.put("bizRegDate", bizRegDate);
	}

	@DBMeta(column = "LEGALMANCODE", name = "投资者法人代码或身份证号", type = "String")
	public String getLegalManCode() {
		return legalManCode;
	}

	public void setLegalManCode(String legalManCode) {
		this.legalManCode = legalManCode;
		this.settings.put("legalManCode", legalManCode);
	}

	@DBMeta(column = "WorkScope", name = "经营范围", type = "String")
	public String getWorkScope() {
		return workScope;
	}

	public void setWorkScope(String workScope) {
		this.workScope = workScope;
		this.settings.put("workScope", workScope);
	}

	@DBMeta(column = "Regadr", name = "注册地址", type = "String")
	public String getRegadr() {
		return regadr;
	}

	public void setRegadr(String regadr) {
		this.regadr = regadr;
		this.settings.put("regadr", regadr);
	}

	@DBMeta(column = "Email", name = "电子信箱", type = "String")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.settings.put("email", email);
	}

	@DBMeta(column = "Bizadr", name = "经营地址", type = "String")
	public String getBizadr() {
		return bizadr;
	}

	public void setBizadr(String bizadr) {
		this.bizadr = bizadr;
		this.settings.put("bizadr", bizadr);
	}

	@DBMeta(column = "Delegate", name = "法人代表", type = "String")
	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
		this.settings.put("delegate", delegate);
	}

	@DBMeta(column = "DLG_CALL", name = "法人代表联系电话", type = "String")
	public String getDlgCall() {
		return dlgCall;
	}

	public void setDlgCall(String dlgCall) {
		this.dlgCall = dlgCall;
		this.settings.put("dlgCall", dlgCall);
	}

	@DBMeta(column = "DLG_CARDNAME", name = "法人代表证件名称", type = "String")
	public String getDlgCardname() {
		return dlgCardname;
	}

	public void setDlgCardname(String dlgCardname) {
		this.dlgCardname = dlgCardname;
		this.settings.put("dlgCardname", dlgCardname);
	}

	@DBMeta(column = "DLG_CARDCODE", name = "法人代表证件号码", type = "String")
	public String getDlgCardcode() {
		return dlgCardcode;
	}

	public void setDlgCardcode(String dlgCardcode) {
		this.dlgCardcode = dlgCardcode;
		this.settings.put("dlgCardcode", dlgCardcode);
	}

	@DBMeta(column = "CONTACTADR", name = "联系地址", type = "String")
	public String getContactadr() {
		return contactadr;
	}

	public void setContactadr(String contactadr) {
		this.contactadr = contactadr;
		this.settings.put("contactadr", contactadr);
	}

	@DBMeta(column = "DLG_POSTCODE", name = "邮政编码", type = "String")
	public String getDlgPostcode() {
		return dlgPostcode;
	}

	public void setDlgPostcode(String dlgPostcode) {
		this.dlgPostcode = dlgPostcode;
		this.settings.put("dlgPostcode", dlgPostcode);
	}

	@DBMeta(column = "Proxy", name = "代理人", type = "String")
	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
		this.settings.put("proxy", proxy);
	}

	@DBMeta(column = "PROXY_CALL", name = "代理人电话", type = "String")
	public String getProxyCall() {
		return proxyCall;
	}

	public void setProxyCall(String proxyCall) {
		this.proxyCall = proxyCall;
		this.settings.put("proxyCall", proxyCall);
	}

	@DBMeta(column = "PROXY_CARDNAME", name = "代理人证件名称", type = "String")
	public String getProxyCardname() {
		return proxyCardname;
	}

	public void setProxyCardname(String proxyCardname) {
		this.proxyCardname = proxyCardname;
		this.settings.put("proxyCardname", proxyCardname);
	}

	@DBMeta(column = "PROXY_CARDCODE", name = "代理人证件号码", type = "String")
	public String getProxyCardcode() {
		return proxyCardcode;
	}

	public void setProxyCardcode(String proxyCardcode) {
		this.proxyCardcode = proxyCardcode;
		this.settings.put("proxyCardcode", proxyCardcode);
	}

	@DBMeta(column = "PROXY_CONTACT", name = "代理人联系地址", type = "String")
	public String getProxyContact() {
		return proxyContact;
	}

	public void setProxyContact(String proxyContact) {
		this.proxyContact = proxyContact;
		this.settings.put("proxyContact", proxyContact);
	}

	@DBMeta(column = "PROXY_POSTCODE", name = "代理人邮编", type = "String")
	public String getProxyPostcode() {
		return proxyPostcode;
	}

	public void setProxyPostcode(String proxyPostcode) {
		this.proxyPostcode = proxyPostcode;
		this.settings.put("proxyPostcode", proxyPostcode);
	}

	@DBMeta(column = "FIRSTCENSOR", name = "初审人", type = "String")
	public String getFirstCensor() {
		return firstCensor;
	}

	public void setFirstCensor(String firstCensor) {
		this.firstCensor = firstCensor;
		this.settings.put("firstCensor", firstCensor);
	}

	@DBMeta(column = "FirstAuditDate", name = "初审日期", type = "String")
	public String getFirstAuditDate() {
		return firstAuditDate;
	}

	public void setFirstAuditDate(String firstAuditDate) {
		this.firstAuditDate = firstAuditDate;
		this.settings.put("firstAuditDate", firstAuditDate);
	}

	@DBMeta(column = "FirstMark", name = "初审备注（初审意见）", type = "String")
	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String firstMark) {
		this.firstMark = firstMark;
		this.settings.put("firstMark", firstMark);
	}

	@DBMeta(column = "FinalCensor", name = "终审人", type = "String")
	public String getFinalCensor() {
		return finalCensor;
	}

	public void setFinalCensor(String finalCensor) {
		this.finalCensor = finalCensor;
		this.settings.put("finalCensor", finalCensor);
	}

	@DBMeta(column = "FinalAuditDate", name = "终审日期", type = "String")
	public String getFinalAuditDate() {
		return finalAuditDate;
	}

	public void setFinalAuditDate(String finalAuditDate) {
		this.finalAuditDate = finalAuditDate;
		this.settings.put("finalAuditDate", finalAuditDate);
	}

	@DBMeta(column = "FinalMark", name = "终审备注", type = "String")
	public String getFinalMark() {
		return finalMark;
	}

	public void setFinalMark(String finalMark) {
		this.finalMark = finalMark;
		this.settings.put("finalMark", finalMark);
	}

	@DBMeta(column = "Status", name = "当前状态", type = "int", dict_table = "CT_307")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	/**
	 * 功能描述：将yyyymmdd格式日期转为yyyy-mm-dd格式
	 * @return
	 */
	public String getFirstDate() {
		return DateUtil.parseDateTime3(String.valueOf(this.firstAuditDate));
	}

	public String getFinalDate() {
		return DateUtil.parseDateTime3(String.valueOf(this.finalAuditDate));
	}

	public String getBizEndDate2() {
		return DateUtil.parseDateTime3(String.valueOf(this.bizEndDate));
	}

	public String getBizRegDate2() {
		return DateUtil.parseDateTime3(String.valueOf(this.bizRegDate));
	}
}
