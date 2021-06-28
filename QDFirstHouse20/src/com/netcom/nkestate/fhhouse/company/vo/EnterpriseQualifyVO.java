package com.netcom.nkestate.fhhouse.company.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "ENTERPRISEQUALIFY", sequence = "SEQ_COMP_ID", id = DBModel.SequenceID)
public class EnterpriseQualifyVO extends FHVO {

	private long comp_ID; // 	企业ID
	private String comp_Code; // 	全国企业唯一码
	private String comp_SealID; // 	企业章ID
	private String comp_SealPwd; // 	企业章ID密码
	private String delegate_SealID; // 	企业法人章ID
	private String delegate_SealPwd; // 	企业法人章ID密码
	private int type; // 	企业类型       code303：1、房地产开发企业 2、房地产经纪组织
	private String name; // 	企业名称
	private int provinceCode; // 	所在省市
	private int district; // 	所在区县
	private String post; // 	邮编
	private String tel; // 	联系电话
	private String fax; // 	传真
	private String bizregister_Num; // 	法人营业执照注册号
	private int regtype_Num; // 	登记注册类型
	private String bizend_Date; // 	营业执照到期日
	private String bizreg_Date; // 	工商注册日
	private int aptitudeLevel; // 	资质等级
	private String aptitudeNum; // 	资质等级发证编号
	private String passDate; // 	资质证书发证日期
	private String effectstartDate; // 	资质证书有效起始日期
	private String effectendDate; // 	资质证书有效终止日期
	private double pureMoney; // 	净资产
	private double totalMoney; // 	总资产
	private double registerMoney; // 	注册资本
	private String manager; // 	总经理
	private String allnum; // 	有职称专业人员数
	private String highnum; // 	高级职称人数
	private String curnum; // 	在册人员总数
	private String midnum; // 	中级职称人数
	private String lownum; // 	初级职称人数
	private String passbizDate; // 	批准从事房地产开发经营日期
	private String legalManCode; // 	法人代码
	private String workScope; // 	经营范围
	private String regadr; // 	注册地址
	private String email; // 	电子信箱
	private String bizadr; // 	经营地址
	private int bizdistrict; // 	经营地址所在区县
	private String delegate; // 	法人代表
	private String dlg_Call; // 	法人代表联系电话
	private String dlg_Cardname; // 	法人代表证据名称
	private String dlg_Cardcode; // 	法人代表证件号码
	private String contactadr; // 	联系地址
	private String dlg_Postcode; // 	邮政编码
	private String proxy; // 	代理人
	private String proxy_Call; // 	代理人电话
	private String proxy_Cardname; // 	代理人证件名称
	private String proxy_Cardcode; // 	代理人证件号码
	private String proxy_Contact; // 	代理人联系地址
	private String proxy_Postcode; // 	代理人邮编
	private String firstCensor; // 	初审人
	private String firstAuditDate; // 	初审日期
	private String firstMark; // 	初审备注（初审意见）
	private String finalCensor; // 	终审人
	private String finalAuditDate; // 	终审日期
	private String finalMark; // 	终审备注
	private int status; // 	当前状态      code308
	private String publishCensor; // 发布人
	private Date publishDate; //  	发布日期
	private String publishMark; //  	发布备注

	@DBMeta(column = "Comp_ID", name = "企业ID", type = "long", primarykey = "true", can_update = "false")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "Comp_Code", name = "全国企业唯一码", type = "String")
	public String getComp_Code() {
		return comp_Code;
	}

	public void setComp_Code(String Comp_Code) {
		this.comp_Code = Comp_Code;
		this.settings.put("comp_Code", comp_Code);
	}

	@DBMeta(column = "Type", name = "企业类型", type = "int", dict_table = "CT_303")
	public int getType() {
		return type;
	}

	public void setType(int Type) {
		this.type = Type;
		this.settings.put("type", type);
	}

	@DBMeta(column = "Name", name = "企业名称", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "ProvinceCode", name = "所在省市", type = "int", dict_table = "CT_524")
	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int ProvinceCode) {
		this.provinceCode = ProvinceCode;
		this.settings.put("provinceCode", provinceCode);
	}

	@DBMeta(column = "District", name = "所在区县", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrict() {
		return district;
	}

	public void setDistrict(int District) {
		this.district = District;
		this.settings.put("district", district);
	}

	@DBMeta(column = "Post", name = "邮编", type = "String")
	public String getPost() {
		return post;
	}

	public void setPost(String Post) {
		this.post = Post;
		this.settings.put("post", post);
	}

	@DBMeta(column = "Tel", name = "联系电话", type = "String")
	public String getTel() {
		return tel;
	}

	public void setTel(String Tel) {
		this.tel = Tel;
		this.settings.put("tel", tel);
	}

	@DBMeta(column = "Fax", name = "传真", type = "String")
	public String getFax() {
		return fax;
	}

	public void setFax(String Fax) {
		this.fax = Fax;
		this.settings.put("fax", fax);
	}

	@DBMeta(column = "Bizregister_Num", name = "法人营业执照注册号", type = "String")
	public String getBizregister_Num() {
		return bizregister_Num;
	}

	public void setBizregister_Num(String Bizregister_Num) {
		this.bizregister_Num = Bizregister_Num;
		this.settings.put("bizregister_Num", bizregister_Num);
	}

	@DBMeta(column = "Regtype_Num", name = "登记注册类型", type = "int", dict_table = "CT_302")
	public int getRegtype_Num() {
		return regtype_Num;
	}

	public void setRegtype_Num(int Regtype_Num) {
		this.regtype_Num = Regtype_Num;
		this.settings.put("regtype_Num", regtype_Num);
	}

	@DBMeta(column = "Bizend_Date", name = "营业执照到期日", type = "String")
	public String getBizend_Date() {
		return bizend_Date;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getBizend_Date2() {
		String str = bizend_Date;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setBizend_Date(String Bizend_Date) {
		this.bizend_Date = Bizend_Date;
		this.settings.put("bizend_Date", bizend_Date);
	}

	@DBMeta(column = "Bizreg_Date", name = "工商注册日", type = "String")
	public String getBizreg_Date() {
		return bizreg_Date;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getBizreg_Date2() {
		String str = bizreg_Date;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setBizreg_Date(String Bizreg_Date) {
		this.bizreg_Date = Bizreg_Date;
		this.settings.put("bizreg_Date", bizreg_Date);
	}

	@DBMeta(column = "AptitudeLevel", name = "资质等级", type = "int", dict_table = "CT_301")
	public int getAptitudeLevel() {
		return aptitudeLevel;
	}

	public void setAptitudeLevel(int AptitudeLevel) {
		this.aptitudeLevel = AptitudeLevel;
		this.settings.put("aptitudeLevel", aptitudeLevel);
	}

	@DBMeta(column = "AptitudeNum", name = "资质等级发证编号", type = "String")
	public String getAptitudeNum() {
		return aptitudeNum;
	}

	public void setAptitudeNum(String AptitudeNum) {
		this.aptitudeNum = AptitudeNum;
		this.settings.put("aptitudeNum", aptitudeNum);
	}

	@DBMeta(column = "PassDate", name = "资质证书发证日期", type = "String")
	public String getPassDate() {
		return passDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getPassDate2() {
		String str = passDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setPassDate(String PassDate) {
		this.passDate = PassDate;
		this.settings.put("passDate", passDate);
	}

	@DBMeta(column = "EffectstartDate", name = "资质证书有效起始日期", type = "String")
	public String getEffectstartDate() {
		return effectstartDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getEffectstartDate2() {
		String str = effectstartDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setEffectstartDate(String EffectstartDate) {
		this.effectstartDate = EffectstartDate;
		this.settings.put("effectstartDate", effectstartDate);
	}

	@DBMeta(column = "EffectendDate", name = "资质证书有效终止日期", type = "String")
	public String getEffectendDate() {
		return effectendDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getEffectendDate2() {
		String str = effectendDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setEffectendDate(String EffectendDate) {
		this.effectendDate = EffectendDate;
		this.settings.put("effectendDate", effectendDate);
	}

	@DBMeta(column = "PureMoney", name = "净资产", type = "double")
	public double getPureMoney() {
		return pureMoney;
	}

	public void setPureMoney(double PureMoney) {
		this.pureMoney = PureMoney;
		this.settings.put("pureMoney", pureMoney);
	}

	@DBMeta(column = "TotalMoney", name = "总资产", type = "double")
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double TotalMoney) {
		this.totalMoney = TotalMoney;
		this.settings.put("totalMoney", totalMoney);
	}

	@DBMeta(column = "RegisterMoney", name = "注册资本", type = "double")
	public double getRegisterMoney() {
		return registerMoney;
	}

	public void setRegisterMoney(double RegisterMoney) {
		this.registerMoney = RegisterMoney;
		this.settings.put("registerMoney", registerMoney);
	}

	@DBMeta(column = "Manager", name = "总经理", type = "String")
	public String getManager() {
		return manager;
	}

	public void setManager(String Manager) {
		this.manager = Manager;
		this.settings.put("manager", manager);
	}

	@DBMeta(column = "Allnum", name = "有职称专业人员数", type = "String")
	public String getAllnum() {
		return allnum;
	}

	public void setAllnum(String Allnum) {
		this.allnum = Allnum;
		this.settings.put("allnum", allnum);
	}

	@DBMeta(column = "Highnum", name = "高级职称人数", type = "String")
	public String getHighnum() {
		return highnum;
	}

	public void setHighnum(String Highnum) {
		this.highnum = Highnum;
		this.settings.put("highnum", highnum);
	}

	@DBMeta(column = "Curnum", name = "在册人员总数", type = "String")
	public String getCurnum() {
		return curnum;
	}

	public void setCurnum(String Curnum) {
		this.curnum = Curnum;
		this.settings.put("curnum", curnum);
	}

	@DBMeta(column = "Midnum", name = "中级职称人数", type = "String")
	public String getMidnum() {
		return midnum;
	}

	public void setMidnum(String Midnum) {
		this.midnum = Midnum;
		this.settings.put("midnum", midnum);
	}

	@DBMeta(column = "Lownum", name = "初级职称人数", type = "String")
	public String getLownum() {
		return lownum;
	}

	public void setLownum(String Lownum) {
		this.lownum = Lownum;
		this.settings.put("lownum", lownum);
	}

	@DBMeta(column = "PassbizDate", name = "批准从事房地产开发经营日期", type = "String")
	public String getPassbizDate() {
		return passbizDate;
	}

	/**
	 * 功能描述：获取YYYY-MM-DD日期类型
	 * @return
	 */
	public String getPassbizDate2() {
		String str = passbizDate;
		if(str != null && !"".equals(str) && str.length() == 8){
			str = DateUtil.parseDateTime3(str);
		}else{
			str = "";
		}
		return str;
	}

	public void setPassbizDate(String PassbizDate) {
		this.passbizDate = PassbizDate;
		this.settings.put("passbizDate", passbizDate);
	}

	@DBMeta(column = "LegalManCode", name = "法人代码", type = "String")
	public String getLegalManCode() {
		return legalManCode;
	}

	public void setLegalManCode(String LegalManCode) {
		this.legalManCode = LegalManCode;
		this.settings.put("legalManCode", legalManCode);
	}

	@DBMeta(column = "WorkScope", name = "经营范围", type = "String")
	public String getWorkScope() {
		return workScope;
	}

	public void setWorkScope(String WorkScope) {
		this.workScope = WorkScope;
		this.settings.put("workScope", workScope);
	}

	@DBMeta(column = "Regadr", name = "注册地址", type = "String")
	public String getRegadr() {
		return regadr;
	}

	public void setRegadr(String Regadr) {
		this.regadr = Regadr;
		this.settings.put("regadr", regadr);
	}

	@DBMeta(column = "Email", name = "电子信箱", type = "String")
	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
		this.settings.put("email", email);
	}

	@DBMeta(column = "Bizadr", name = "经营地址", type = "String")
	public String getBizadr() {
		return bizadr;
	}

	public void setBizadr(String Bizadr) {
		this.bizadr = Bizadr;
		this.settings.put("bizadr", bizadr);
	}

	@DBMeta(column = "Bizdistrict", name = "经营地址所在区县", type = "int", dict_table = "CT_DISTRICT")
	public int getBizdistrict() {
		return bizdistrict;
	}

	public void setBizdistrict(int Bizdistrict) {
		this.bizdistrict = Bizdistrict;
		this.settings.put("bizdistrict", bizdistrict);
	}

	@DBMeta(column = "Delegate", name = "法人代表", type = "String")
	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(String Delegate) {
		this.delegate = Delegate;
		this.settings.put("delegate", delegate);
	}

	@DBMeta(column = "Dlg_Call", name = "法人代表联系电话", type = "String")
	public String getDlg_Call() {
		return dlg_Call;
	}

	public void setDlg_Call(String Dlg_Call) {
		this.dlg_Call = Dlg_Call;
		this.settings.put("dlg_Call", dlg_Call);
	}

	@DBMeta(column = "Dlg_Cardname", name = "法人代表联系名称", type = "String")
	public String getDlg_Cardname() {
		return dlg_Cardname;
	}

	public void setDlg_Cardname(String Dlg_Cardname) {
		this.dlg_Cardname = Dlg_Cardname;
		this.settings.put("dlg_Cardname", dlg_Cardname);
	}

	@DBMeta(column = "Dlg_Cardcode", name = "法人代表证件号码", type = "String")
	public String getDlg_Cardcode() {
		return dlg_Cardcode;
	}

	public void setDlg_Cardcode(String Dlg_Cardcode) {
		this.dlg_Cardcode = Dlg_Cardcode;
		this.settings.put("dlg_Cardcode", dlg_Cardcode);
	}

	@DBMeta(column = "Contactadr", name = "联系地址", type = "String")
	public String getContactadr() {
		return contactadr;
	}

	public void setContactadr(String Contactadr) {
		this.contactadr = Contactadr;
		this.settings.put("contactadr", contactadr);
	}

	@DBMeta(column = "Dlg_Postcode", name = "邮政编码", type = "String")
	public String getDlg_Postcode() {
		return dlg_Postcode;
	}

	public void setDlg_Postcode(String Dlg_Postcode) {
		this.dlg_Postcode = Dlg_Postcode;
		this.settings.put("dlg_Postcode", dlg_Postcode);
	}

	@DBMeta(column = "Proxy", name = "代理人", type = "String")
	public String getProxy() {
		return proxy;
	}

	public void setProxy(String Proxy) {
		this.proxy = Proxy;
		this.settings.put("proxy", proxy);
	}

	@DBMeta(column = "Proxy_Call", name = "代理人电话", type = "String")
	public String getProxy_Call() {
		return proxy_Call;
	}

	public void setProxy_Call(String Proxy_Call) {
		this.proxy_Call = Proxy_Call;
		this.settings.put("proxy_Call", proxy_Call);
	}

	@DBMeta(column = "Proxy_Cardname", name = "代理人证件名称", type = "String")
	public String getProxy_Cardname() {
		return proxy_Cardname;
	}

	public void setProxy_Cardname(String Proxy_Cardname) {
		this.proxy_Cardname = Proxy_Cardname;
		this.settings.put("proxy_Cardname", proxy_Cardname);
	}

	@DBMeta(column = "Proxy_Cardcode", name = "代理人证件号码", type = "String")
	public String getProxy_Cardcode() {
		return proxy_Cardcode;
	}

	public void setProxy_Cardcode(String Proxy_Cardcode) {
		this.proxy_Cardcode = Proxy_Cardcode;
		this.settings.put("proxy_Cardcode", proxy_Cardcode);
	}

	@DBMeta(column = "Proxy_Contact", name = "代理人联系地址", type = "String")
	public String getProxy_Contact() {
		return proxy_Contact;
	}

	public void setProxy_Contact(String Proxy_Contact) {
		this.proxy_Contact = Proxy_Contact;
		this.settings.put("proxy_Contact", proxy_Contact);
	}

	@DBMeta(column = "Proxy_Postcode", name = "代理人邮编", type = "String")
	public String getProxy_Postcode() {
		return proxy_Postcode;
	}

	public void setProxy_Postcode(String Proxy_Postcode) {
		this.proxy_Postcode = Proxy_Postcode;
		this.settings.put("proxy_Postcode", proxy_Postcode);
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

	@DBMeta(column = "Status", name = "当前状态", type = "int", dict_table = "CT_308")
	public int getStatus() {
		return status;
	}

	public void setStatus(int Status) {
		this.status = Status;
		this.settings.put("status", status);
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
	@DBMeta(column = "comp_SealID", name = "企业章ID", type = "String")
	public String getComp_SealID() {
		return comp_SealID;
	}

	public void setComp_SealID(String comp_SealID) {
		this.comp_SealID = comp_SealID;
		this.settings.put("comp_SealID", comp_SealID);
	}
	@DBMeta(column = "delegate_SealID", name = "企业法人章ID", type = "String")
	public String getDelegate_SealID() {
		return delegate_SealID;
	}

	public void setDelegate_SealID(String delegate_SealID) {
		this.delegate_SealID = delegate_SealID;
		this.settings.put("delegate_SealID", delegate_SealID);
	}
	@DBMeta(column = "comp_SealPwd", name = "企业章ID密码", type = "String")
	public String getComp_SealPwd() {
		return comp_SealPwd;
	}

	public void setComp_SealPwd(String comp_SealPwd) {
		this.comp_SealPwd = comp_SealPwd;
		this.settings.put("comp_SealPwd", comp_SealPwd);
	}
	@DBMeta(column = "delegate_SealPwd", name = "企业法人章ID密码", type = "String")
	public String getDelegate_SealPwd() {
		return delegate_SealPwd;
	}

	public void setDelegate_SealPwd(String delegate_SealPwd) {
		this.delegate_SealPwd = delegate_SealPwd;
		this.settings.put("delegate_SealPwd", delegate_SealPwd);
	}
	
	
	
}
