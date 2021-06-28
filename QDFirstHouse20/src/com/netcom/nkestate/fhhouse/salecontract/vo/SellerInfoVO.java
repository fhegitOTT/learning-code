package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SELLERINFO", sequence = "", id = DBModel.AssignedID)
public class SellerInfoVO extends FHVO {

	private long contractID;//合同ID
	private long compID;//公司ID
	private int serial;//甲方顺序	从1开始采番 eg:第一个甲方=1，第二个甲方=2
	private String sellerName;//甲方名称
	private String sellerBizcardcode;//营业执照名称
	private String sellerAddress;//地址
	private String sellerPostcode;//邮编
	private String sellerDelegate;//法定代表人
	private String sellerDlgCall;//法人电话
	private String sellerProxy;//代理人
	private String sellerProxyCall;//代理人电话
	private String sellerCertcode;//甲方资质证书号

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "COMP_ID", name = "公司ID", type = "long", primarykey = "true", can_update = "false")
	public long getCompID() {
		return compID;
	}

	public void setCompID(long compID) {
		this.compID = compID;
		this.settings.put("compID", compID);
	}

	@DBMeta(column = "SERIAL", name = "甲方顺序", type = "int")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}

	@DBMeta(column = "SELLER_NAME", name = "甲方名称", type = "String")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
		this.settings.put("sellerName", sellerName);
	}

	@DBMeta(column = "SELLER_BIZCARDCODE", name = "营业执照号码", type = "String")
	public String getSellerBizcardcode() {
		return sellerBizcardcode;
	}

	public void setSellerBizcardcode(String sellerBizcardcode) {
		this.sellerBizcardcode = sellerBizcardcode;
		this.settings.put("sellerBizcardcode", sellerBizcardcode);
	}

	@DBMeta(column = "SELLER_ADDRESS", name = "地址", type = "String")
	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
		this.settings.put("sellerAddress", sellerAddress);
	}

	@DBMeta(column = "SELLER_POSTCODE", name = "邮编", type = "String")
	public String getSellerPostcode() {
		return sellerPostcode;
	}

	public void setSellerPostcode(String sellerPostcode) {
		this.sellerPostcode = sellerPostcode;
		this.settings.put("sellerPostcode", sellerPostcode);
	}

	@DBMeta(column = "SELLER_DELEGATE", name = "法定代表人", type = "String")
	public String getSellerDelegate() {
		return sellerDelegate;
	}

	public void setSellerDelegate(String sellerDelegate) {
		this.sellerDelegate = sellerDelegate;
		this.settings.put("sellerDelegate", sellerDelegate);
	}

	@DBMeta(column = "SELLER_DLG_CALL", name = "法人电话", type = "String")
	public String getSellerDlgCall() {
		return sellerDlgCall;
	}

	public void setSellerDlgCall(String sellerDlgCall) {
		this.sellerDlgCall = sellerDlgCall;
		this.settings.put("sellerDlgCall", sellerDlgCall);
	}

	@DBMeta(column = "SELLER_PROXY", name = "代理人", type = "String")
	public String getSellerProxy() {
		return sellerProxy;
	}

	public void setSellerProxy(String sellerProxy) {
		this.sellerProxy = sellerProxy;
		this.settings.put("sellerProxy", sellerProxy);
	}

	@DBMeta(column = "SELLER_PROXY_CALL", name = "代理人电话", type = "String")
	public String getSellerProxyCall() {
		return sellerProxyCall;
	}

	public void setSellerProxyCall(String sellerProxyCall) {
		this.sellerProxyCall = sellerProxyCall;
		this.settings.put("sellerProxyCall", sellerProxyCall);
	}

	@DBMeta(column = "SELLER_CERTCODE", name = "甲方资质证书号", type = "String")
	public String getSellerCertcode() {
		return sellerCertcode;
	}

	public void setSellerCertcode(String sellerCertcode) {
		this.sellerCertcode = sellerCertcode;
		this.settings.put("sellerCertcode", sellerCertcode);
	}

}
