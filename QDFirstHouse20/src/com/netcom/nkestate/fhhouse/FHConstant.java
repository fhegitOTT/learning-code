/**
 * <p>FHConstant.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 一手房常量类<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse;


public class FHConstant {

	/**
	 * 平台系统id
	 */
	static public int APP_FH_INNER = 100;//青岛市新建商品房网上备案管理系统（原绿网系统）
	static public int APP_FH_OUTER = 101;//青岛市新建商品房网上备案签约系统（原蓝网系统）

	/** 系统生成seq/合同号 */
	static public String SEQ_NAME_CONTRACTID = "CONTRACTID";
	/** 系统生成seq/收件ID */
	static public String SEQ_NAME_DOCUMENTID = "DOCUMENTID";
	/** 系统生成seq/登陆名 */
	static public String SEQ_NAME_LOGINNAME = "LOGINNAME";

	/** 内外网区分/内网 */
	public static final int WEBACTION_TYPE_INNER = 0;
	/** 内外网区分/外网 */
	public static final int WEBACTION_TYPE_OUTER = 1;

	/** 交易类型/交费 */
	public static final int EXCHANGE_TYPE_RESERVE = 1;
	/** 交易类型/退款 */
	public static final int EXCHANGE_TYPE_REFUNDMENT = 2;
	/** 交易类型/冻结 */
	public static final int EXCHANGE_TYPE_FREEZE = 3;
	/** 交易类型/解冻 */
	public static final int EXCHANGE_TYPE_UNFREEZE = 4;
	/** 交易类型/查询 */
	public static final int EXCHANGE_TYPE_QUERY = 5;


	/** 系统日志执行状态/成功 */
	public static final int EXEC_STATUS_SUCCESS = 0;
	/** 系统日志执行状态/执行中 */
	public static final int EXEC_STATUS_EXECUTING = 1;
	/** 系统日志执行状态/失败 */
	public static final int EXEC_STATUS_FAILED = 2;

	/** 外网密码修改区分/已修改 */
	public static final int OUTWEB_PWD_IS_CHANGED = 0;
	/** 外网密码修改区分/未修改 */
	public static final int OUTWEB_PWD_NO_CHANGE = 1;

	/** 公告弹出状态/不弹出显示 */
	public static final int BULLETIN_POPUP_OFF = 0;
	/** 公告弹出状态/弹出显示 */
	public static final int BULLETIN_POPUP_ON = 1;

	/** 收件状态/未退回 */
	public static final int DOCUMENT_STATUS_NO_CANCEL = 0;
	/** 收件状态/已退回 */
	public static final int DOCUMENT_STATUS_CANCEL = 1;

	/** 企业已撤销未撤销区分/未撤销 */
	public static final int COMP_ISREPEALED_FALSE = 2;
	/** 企业已撤销未撤销区分/已撤销 */
	public static final int COMP_ISREPEALED_TRUE = 5;

	/** 开盘方式/定期开盘 */
	public static final int START_MODE_SCHEDULE = 0;
	/** 开盘方式/立即开盘 */
	public static final int START_MODE_IMMEDIATELY = 1;
	/** 开盘方式/不开盘 */
	public static final int START_MODE_NOSTART = 2;
	/** 开盘方式/暂停销售 */
	public static final int START_MODE_STOPED = 3;

	/** 代理商已撤销未撤销区分/未撤销 */
	public static final int AGENT_ISREPEALED_FALSE = 2;
	/** 代理商已撤销未撤销区分/已撤销 */
	public static final int AGENT_ISREPEALED_TRUE = 5;

	/** 代理商状态/编辑 */
	public static final int AGENT_STATUS_EDIT = 0;
	/** 代理商状态/提交（待审） */
	public static final int AGENT_STATUS_SUBMIT = 1;
	/** 代理商状态/终审通过 */
	public static final int AGENT_STATUS_PASSED = 7;
	/** 代理商状态/终审不通过 */
	public static final int AGENT_STATUS_NOPASSED = 8;
	/** 代理商状态/撤销 */
	public static final int AGENT_STATUS_CANCEL = 5;
	/** 代理商状态/撤销恢复 */
	public static final int AGENT_STATUS_RECOVER = 6;
	/** 代理商状态/信息已发布 */
	public static final int AGENT_STATUS_PERMITTED = 2;
	/** 代理商状态/放行不通过 */
	public static final int AGENT_STATUS_NOPERMITTED = 4;

	/** 企业状态/编辑 */
	public static final int COMP_STATUS_EDIT = 0;
	/** 企业状态/提交（待审） */
	public static final int COMP_STATUS_SUBMIT = 1;
	/** 企业状态/终审通过 */
	public static final int COMP_STATUS_PASSED = 7;
	/** 企业状态/终审不通过 */
	public static final int COMP_STATUS_NOPASSED = 8;
	/** 企业状态/撤销 */
	public static final int COMP_STATUS_CANCEL = 5;
	/** 企业状态/撤销恢复 */
	public static final int COMP_STATUS_RECOVER = 6;
	/** 企业状态/信息已发布 */
	public static final int COMP_STATUS_PERMITTED = 2;
	/** 企业状态/发布失败 */
	public static final int COMP_STATUS_NOPERMITTED = 4;
	
	
	/** 预售许可状态/受理 */
	public static final int PERMIT_STATUS_ACCEPT = 1;
	/** 预售许可状态/审核 */
	public static final int PERMIT_STATUS_AUDIT = 2;
	/** 预售许可状态/缮证 */
	public static final int PERMIT_STATUS_PRINT = 3;
	/** 预售许可状态/归档 */
	public static final int PERMIT_STATUS_ARCHIVED = -1;

	/** 项目状态/编辑 */
	public static final int PROJECT_STATUS_EDIT = 0;
	/** 项目状态/提交（待审） */
	public static final int PROJECT_STATUS_SUBMIT = 1;
	/** 项目状态/终审通过 */
	public static final int PROJECT_STATUS_PASSED = 7;
	/** 项目状态/撤销 */
	public static final int PROJECT_STATUS_CANCEL = 3;
	/** 项目状态/终审不通过 */
	public static final int PROJECT_STATUS_NOPASSED = 8;
	/** 项目状态/撤销恢复 */
	public static final int PROJECT_STATUS_RECOVER = 5;
	/** 项目状态/信息已发布 */
	public static final int PROJECT_STATUS_PERMITTED = 2;
	/** 项目状态/发布失败 */
	public static final int PROJECT_STATUS_NOPERMITTED = 4;

	/** 开盘单元状态/编辑 */
	public static final int START_UNIT_STATUS_EDIT = 0;
	/** 开盘单元状态/提交（待审） */
	public static final int START_UNIT_STATUS_SUBMIT = 1;
	/** 开盘单元状态/终审通过 */
	public static final int START_UNIT_STATUS_PASSED = 7;
	/** 开盘单元状态/终审不通过 */
	public static final int START_UNIT_STATUS_NOPASSED = 8;
	/** 开盘单元状态/撤销 */
	public static final int START_UNIT_STATUS_CANCEL = 5;
	/** 开盘单元状态/撤销恢复 */
	public static final int START_UNIT_STATUS_RECOVER = 6;
	/** 开盘单元状态/信息已发布 */
	public static final int START_UNIT_STATUS_PERMITTED = 2;
	/** 开盘单元状态/发布失败 */
	public static final int START_UNIT_STATUS_NOPERMITTED = 4;


	/** 签约人状态/编辑 */
	public static final int SIGNER_STATUS_EDIT = 0;
	/** 签约人状态/提交（待审） */
	public static final int SIGNER_STATUS_SUBMIT = 1;
	/** 签约人状态/终审通过 */
	public static final int SIGNER_STATUS_PASSED = 7;
	/** 签约人状态/撤销 */
	public static final int SIGNER_STATUS_CANCEL = 3;
	/** 签约人状态/终审不通过 */
	public static final int SIGNER_STATUS_NOPASSED = 8;
	/** 签约人状态/撤销恢复 */
	public static final int SIGNER_STATUS_RECOVER = 5;
	/** 签约人状态/信息已发布 */
	public static final int SIGNER_STATUS_PERMITTED = 2;
	/** 签约人状态/发布失败 */
	public static final int SIGNER_STATUS_NOPERMITTED = 4;


	/** 大机房屋状态/纳入网上销售 */
	public static final int DAJI_HOUSE_STATUS_ONLINE = 1;
	/** 大机房屋状态/已登记 */
	public static final int DAJI_HOUSE_STATUS_REGISTED = 2;
	/** 大机房屋状态/未纳入网上销售 */
	public static final int DAJI_HOUSE_STATUS_UNONLINE = 3;
	/** 大机房屋状态/撤销登记 */
	public static final int DAJI_HOUSE_STATUS_CANCEL = 4;
	/** 大机房屋状态/已受理 */
	public static final int DAJI_HOUSE_STATUS_ACCEPTED = 5;

	/** 资质审核同意区分/同意 */
	public static final int COMP_ISAGREE_TRUE = 7;
	/** 资质审核同意区分/不同意 */
	public static final int COMP_ISAGREE_FALSE = 8;

	/** 资质审核类型/开盘单元送审 */
	public static final int AUDIT_TYPE_START_UNIT = 0;
	/** 资质审核类型/签约人送审 */
	public static final int AUDIT_TYPE_SIGNER = 1;
	/** 资质审核类型/项目送审 */
	public static final int AUDIT_TYPE_PROJECT = 2;
	/** 资质审核类型/企业送审 */
	public static final int AUDIT_TYPE_COMPANY = 4;

	/** 数据发布类型/开盘单元信息发布 */
	public static final int DATAPERMIT_TYPE_START_UNIT = 0;
	/** 数据发布类型/签约人信息发布 */
	public static final int DATAPERMIT_TYPE_SIGNER = 1;
	/** 数据发布类型/项目信息发布 */
	public static final int DATAPERMIT_TYPE_PROJECT = 2;
	/** 数据发布类型/企业信息发布 */
	public static final int DATAPERMIT_TYPE_COMPANY = 4;
	/** 数据发布类型/代理商信息发布 */
	public static final int DATAPERMIT_TYPE_AGENT = 5;

	/** 数据放行同意区分/同意 */
	public static final int DATAPERMIT_ISAGREE_TRUE = 2;
	/** 数据放行同意区分/不同意 */
	public static final int DATAPERMIT_ISAGREE_FALSE = 4;

	/** 登陆时间类型/每天 */
	public static final int LOGIN_TIME_TYPE_EVERYDAY = 0;
	/** 登陆时间类型/一次有效 */
	public static final int LOGIN_TIME_TYPE_ONLYONCE = 1;

	/** 合同状态/编辑状态 */
	public static final int CONTRACT_STATUS_EDIT = 0;
	/** 合同状态/待签 */
	public static final int CONTRACT_STATUS_WAIT4SIGN = 1;
	/** 合同状态/已签 */
	public static final int CONTRACT_STATUS_SIGNED = 2;
	/** 合同状态/已登记 */
	public static final int CONTRACT_STATUS_REGISTED = 4;
	/** 合同状态/撤销 */
	public static final int CONTRACT_STATUS_ABOLISH = 5;
	/** 合同状态/已领证 */
	public static final int CONTRACT_STATUS_CERTIFICATED = 6;
	/** 合同状态/系统自动撤销（定金定合同过期） */
	public static final int CONTRACT_STATUS_AUTOCANCEL = 7;
	/** 合同状态/已付定金 */
	public static final int CONTRACT_STATUS_EARNEST = 8;
	/** 合同状态/已受理 */
	public static final int CONTRACT_STATUS_ACCEPTED = 10;
	/** 合同状态/转签 */
	public static final int CONTRACT_STATUS_RESIGNED = 11;

	/** 付款种类/按时间付款 */
	public static final int PAYMENT_TYPE_TIME = 0;
	/** 付款种类/按条件付款 */
	public static final int PAYMENT_TYPE_CONDITION = 1;

	/** 付款方式/一次性付款 */
	public static final int PAYMENT_MODE_NONINSTALLMENT = 0;
	/** 付款方式/分期付款 */
	public static final int PAYMENT_MODE_BORROW = 1;

	/** 贷款方式/不贷款 */
	public static final int BORROW_MODE_NOBORROW = 0;
	/** 贷款方式/组合贷款 */
	public static final int BORROW_MODE_COMBINATION = 1;
	/** 贷款方式/公积金贷款 */
	public static final int BORROW_MODE_FUND = 2;
	/** 贷款方式/商业贷款 */
	public static final int BORROW_MODE_BIZ = 3;
	/** 贷款方式/其他 */
	public static final int BORROW_MODE_ETC = 4;

	/** 监管付款方式/自有资金支付 */
	public static final int HISENSE_PAYMENT_TYPE_OWNERPAY = 2;
	/** 监管付款方式/按揭贷款支付 */
	public static final int HISENSE_PAYMENT_TYPE_LOANPAY = 3;

	/** 监管贷款方式/商业贷款 */
	public static final int HISENSE_LOAN_TYPE_SYDK = 1;
	/** 监管贷款方式/公积金贷款 */
	public static final int HISENSE_LOAN_TYPE_GJJDK = 2;
	/** 监管贷款方式/组合贷款 */
	public static final int HISENSE_LOAN_TYPE_ZHDK = 3;

	/** 合同类型/预售合同 */
	public static final int CONTRACT_TYPE_PRESELL = 1;
	/** 合同类型/出售合同 */
	public static final int CONTRACT_TYPE_SELL = 2;
	/** 合同类型/认购协议 */
	public static final int CONTRACT_TYPE_EARNEST = 3;

	/** 房屋状态/已签（黄） */
	public static final int HOUSE_STATUS_SIGNED = 2;
	/** 房屋状态/已登记（红） */
	public static final int HOUSE_STATUS_REGISTED = 3;
	/** 房屋状态/可售（绿） */
	public static final int HOUSE_STATUS_CANSELL = 4;
	/** 房屋状态/已付定金（粉红） */
	public static final int HOUSE_STATUS_EARNEST = 8;
	/** 房屋状态/未纳入网上销售（白） */
	public static final int HOUSE_STATUS_UNACTIVE = 9;

	/** 人工干预状态/强制不可售 */
	public static final int MANUAL_STATUS_CANNOTSELL = 0;
	/** 人工干预状态/强制可售 */
	public static final int MANUAL_STATUS_CANSELL = 1;

	/** 已签合同状态/已签 */
	public static final int SIGNED_CONTRACT_STATUS_SIGNED = 2;
	/** 已签合同状态/已登记 */
	public static final int SIGNED_CONTRACT_STATUS_REGISTED = 4;
	/** 已签合同状态/撤销 */
	public static final int SIGNED_CONTRACT_STATUS_ABOLISH = 5;
	/** 已签合同状态/过期 */
	public static final int SIGNED_CONTRACT_STATUS_AUTOCANCEL = 7;
	/** 已签合同状态/已受理 */
	public static final int SIGNED_CONTRACT_STATUS_ACCEPTED = 10;
	/** 已签合同状态/转签 */
	public static final int SIGNED_CONTRACT_STATUS_RESIGNED = 11;

	/** 费用明细类型/交退费明细 */
	public static final int FEE_DETAIL_TYPE_FEE_DETAIL = 0;
	/** 费用明细类型/消费明细 */
	public static final int FEE_DETAIL_TYPE_CONSUME_DETAIL = 1;

	/** 房屋用途/住宅 */
	public static final int LAND_TYPE_APARTMENT = 1;
	/** 房屋用途/办公 */
	public static final int LAND_TYPE_OFFICE = 2;
	/** 房屋用途/商铺 */
	public static final int LAND_TYPE_SHOP = 3;
	/** 房屋用途/其他 */
	public static final int LAND_TYPE_ETC = 4;

	/** 开盘区分/未开盘 */
	public static final int ISSALABLE_OFF = 0;
	/** 开盘区分/已开盘 */
	public static final int ISSALABLE_ON = 1;
	/** 开盘区分/暂停销售 */
	public static final int ISSALABLE_STOP = 2;

	/** 合同内部撤销审核状态/复核 */
	public static final int CONTRACT_JUDGE_STATUS_WAITING = 1;
	/** 合同内部撤销审核状态/信息已发布 */
	public static final int CONTRACT_JUDGE_STATUS_PERMITTED = 2;
	/** 合同内部撤销审核状态/审核不通过 */
	public static final int CONTRACT_JUDGE_STATUS_FAILED = 3;
	/** 合同内部撤销审核状态/审批 */
	public static final int CONTRACT_JUDGE_STATUS_EARLY_PASSED = 4;
	/** 合同内部撤销审核状态/审批退回 */
	public static final int CONTRACT_JUDGE_STATUS_BACK = 5;
	/** 合同内部撤销审核状态/审批通过 */
	public static final int CONTRACT_JUDGE_STATUS_PASSED = 6;
	/** 合同内部撤销审核状态/发布失败 */
	public static final int CONTRACT_JUDGE_STATUS_NOPERMITTED = 7;

	/** 签约人有无效区分/无效 */
	public static final int SIGNER_VALID_OFF = 0;
	/** 签约人有无效区分/有效 */
	public static final int SIGNER_VALID_ON = 1;

	/** 交接书状态/已撤销 */
	public static final int DELIVER_CONTRACT_STATUS_INVALID = 0;
	/** 交接书状态/已签 */
	public static final int DELIVER_CONTRACT_STATUS_VALID = 1;

	/** 逻辑删除标志/未删除 */
	public static final int LOGIC_DELETED_UNDELETED = 0;
	/** 逻辑删除标志/已删除 */
	public static final int LOGIC_DELETED_DELETED = 1;

	/** 合同类型在登记表申请书中对应的种类/期房买卖登记 */
	public static final int CONTRACT_REG_TYPE_PLAN = 1;
	/** 合同类型在登记表申请书中对应的种类/现房买卖登记 */
	public static final int CONTRACT_REG_TYPE_SELL = 2;
	/** 合同类型在登记表申请书中对应的种类/其他转移登记 */
	public static final int CONTRACT_REG_TYPE_ETC = 3;

	/** 报批状态/受理 */
	public static final int ENTER_CENSOR_STATUS_ACCEPTED = 1;
	/** 报批状态/复核 */
	public static final int ENTER_CENSOR_STATUS_EARLY_CENSOR = 2;
	/** 报批状态/审批 */
	public static final int ENTER_CENSOR_STATUS_FINAL_CENSOR = 3;
	/** 报批状态/复核退回 */
	public static final int ENTER_CENSOR_STATUS_EARLY_FAILED = 4;
	/** 报批状态/审批通过 */
	public static final int ENTER_CENSOR_STATUS_FINAL_SUCCESSED = 5;
	/** 报批状态/审批退回 */
	public static final int ENTER_CENSOR_STATUS_FINAL_FAILED = 6;

	/** 行政区域区分/市中心 */
	public static final int REG_DISTRICT_TYPE_SH = 0;
	/** 行政区域区分/市南区 */
	public static final int REG_DISTRICT_TYPE_SN = 2;
	/** 行政区域区分/市北区 */
	public static final int REG_DISTRICT_TYPE_SB = 3;
	/** 行政区域区分/四方区 */
	public static final int REG_DISTRICT_TYPE_SF = 5;
	/** 行政区域区分/李沧区 */
	public static final int REG_DISTRICT_TYPE_LC = 13;
	/** 行政区域区分/黄岛区 */
	public static final int REG_DISTRICT_TYPE_HD = 11;
	/** 行政区域区分/崂山区 */
	public static final int REG_DISTRICT_TYPE_LS = 12;
	/** 行政区域区分/城阳区 */
	public static final int REG_DISTRICT_TYPE_CY = 14;
	/** 行政区域区分/即墨市 */
	public static final int REG_DISTRICT_TYPE_JM = 82;
	/** 行政区域区分/平度市 */
	public static final int REG_DISTRICT_TYPE_PD = 83;
	/** 行政区域区分/胶州市 */
	public static final int REG_DISTRICT_TYPE_JZ = 81;
	/** 行政区域区分/胶南市 */
	public static final int REG_DISTRICT_TYPE_JN = 84;
	/** 行政区域区分/莱西市 */
	public static final int REG_DISTRICT_TYPE_LX = 85;
	/** 行政区域区分/高新区 */
	public static final int REG_DISTRICT_TYPE_GX = 21;
	/** 行政区域区分/前湾保税港区 */
	public static final int REG_DISTRICT_TYPE_BS = 22;

	/** 登记注册类型/国有 */
	public static final int REGISTER_TYPE_GY = 1;
	/** 登记注册类型/集体 */
	public static final int REGISTER_TYPE_JT = 2;
	/** 登记注册类型/股份合作 */
	public static final int REGISTER_TYPE_GFHZ = 3;
	/** 登记注册类型/国有联营 */
	public static final int REGISTER_TYPE_GYJY = 4;
	/** 登记注册类型/集体联营 */
	public static final int REGISTER_TYPE_JTJY = 5;
	/** 登记注册类型/国有与集体联营 */
	public static final int REGISTER_TYPE_GYYJTLY = 6;
	/** 登记注册类型/其它联营 */
	public static final int REGISTER_TYPE_QTLY = 7;
	/** 登记注册类型/国有独资公司 */
	public static final int REGISTER_TYPE_GYDZGS = 8;
	/** 登记注册类型/其他有限责任公司 */
	public static final int REGISTER_TYPE_QTYXZRGS = 9;
	/** 登记注册类型/股份有限公司 */
	public static final int REGISTER_TYPE_GFYXGS = 10;
	/** 登记注册类型/私营独资 */
	public static final int REGISTER_TYPE_SYDZ = 11;
	/** 登记注册类型/私营合伙 */
	public static final int REGISTER_TYPE_SYHH = 12;
	/** 登记注册类型/私营有限责任公司 */
	public static final int REGISTER_TYPE_SYYXZRGS = 13;
	/** 登记注册类型/私营股份有限公司 */
	public static final int REGISTER_TYPE_SYGFYXGS = 14;
	/** 登记注册类型/其他内资企业 */
	public static final int REGISTER_TYPE_QTNZQY = 15;
	/** 登记注册类型/港澳台商投资合资经营 */
	public static final int REGISTER_TYPE_GATSTZHZJY = 16;
	/** 登记注册类型/港澳台商投资合作经营 */
	public static final int REGISTER_TYPE_GATSTZHZUOJY = 17;
	/** 登记注册类型/港澳台商投资独资经营 */
	public static final int REGISTER_TYPE_GATSTZDZJY = 18;
	/** 登记注册类型/港澳台商投资股份有限经营 */
	public static final int REGISTER_TYPE_GATSTZGFYXJY = 19;
	/** 登记注册类型/外商投资合资经营 */
	public static final int REGISTER_TYPE_WSTZHZJY = 20;
	/** 登记注册类型/外商投资合作经营 */
	public static final int REGISTER_TYPE_WSTZHZUOJY = 21;
	/** 登记注册类型/外商投资独资 */
	public static final int REGISTER_TYPE_WSTZDZ = 22;
	/** 登记注册类型/外商投资股份有限公司 */
	public static final int REGISTER_TYPE_WSTZGFYXGS = 23;

	/** 企业类型/房地产开发企业 */
	public static final int COMP_TYPE_ENTERPRISE = 1;
	/** 企业类型/房地产经纪组织 */
	public static final int COMP_TYPE_ORGANIZATION = 2;

	/** 资质等级/一级 */
	public static final int QUALIFIED_GRADE_FIRST = 1;
	/** 资质等级/二级 */
	public static final int QUALIFIED_GRADE_SECOND = 2;
	/** 资质等级/三级 */
	public static final int QUALIFIED_GRADE_THIRD = 3;
	/** 资质等级/四级 */
	public static final int QUALIFIED_GRADE_FOURTH = 4;
	/** 资质等级/暂定级 */
	public static final int QUALIFIED_GRADE_TEMP = 5;

	/** 许可证区分/预售许可证 */
	public static final int LICENCE_DIVISION_PRESELL = 0;
	/** 许可证区分/权属证明 */
	public static final int LICENCE_DIVISION_PROPERTY = 1;

	/** 房屋不限购状态/否 */
	public static final int HOUSE_NOXGSTATE_YES = 0;
	/** 房屋不限购状态/是 */
	public static final int HOUSE_NOXGSTATE_NO = 1;

	/** 新版合同字典表类型定义 */
	public static final String FH_CONTRACT_DIC_10 = "DIC_10";//土地取得方式
	public static final String FH_CONTRACT_DIC_11 = "DIC_11";//地块证号
	public static final String FH_CONTRACT_DIC_12 = "DIC_12";//商品房的规划用途
	public static final String FH_CONTRACT_DIC_13 = "DIC_13";//商品房抵押情况
	public static final String FH_CONTRACT_DIC_14 = "DIC_14";//出卖人支付赔偿金类型
	public static final String FH_CONTRACT_DIC_15 = "DIC_15";//标准来源
	public static final String FH_CONTRACT_DIC_16 = "DIC_16";//寄送方式
	public static final String FH_CONTRACT_DIC_17 = "DIC_17";//物业收费计费方式
	public static final String FH_CONTRACT_DIC_18 = "DIC_18";//贷款方式
	public static final String FH_CONTRACT_DIC_20 = "DIC_20";//外墙类型
	public static final String FH_CONTRACT_DIC_21 = "DIC_21";//起居室内墙类型
	public static final String FH_CONTRACT_DIC_22 = "DIC_22";//起居室顶棚类型
	public static final String FH_CONTRACT_DIC_23 = "DIC_23";//起居室室内地面类型
	public static final String FH_CONTRACT_DIC_24 = "DIC_24";//厨房地面类型
	public static final String FH_CONTRACT_DIC_25 = "DIC_25";//厨房墙面类型
	public static final String FH_CONTRACT_DIC_26 = "DIC_26";//厨房顶棚类型
	public static final String FH_CONTRACT_DIC_27 = "DIC_27";//卫生间地面类型
	public static final String FH_CONTRACT_DIC_28 = "DIC_28";//卫生间墙面类型
	public static final String FH_CONTRACT_DIC_29 = "DIC_29";//卫生间顶棚类型
	public static final String FH_CONTRACT_DIC_30 = "DIC_30";//阳台类型
	public static final String FH_CONTRACT_DIC_31 = "DIC_31";//商品房获取的文件
	public static final String FH_CONTRACT_DIC_32 = "DIC_32";//文件号
	public static final String FH_CONTRACT_DIC_33 = "DIC_33";//机构
	public static final String FH_CONTRACT_DIC_34 = "DIC_34";//商品房租赁情况
	public static final String FH_CONTRACT_DIC_35 = "DIC_35";//出租情况
	public static final String FH_CONTRACT_DIC_36 = "DIC_36";//房屋租赁受益人

	/** 附件序号/新版预售合同附件二 */
	public static final int ATTACH_SERIAL_PFJ2 = 3902;
	/** 附件序号/新版预售合同附件三 */
	public static final int ATTACH_SERIAL_PFJ3 = 3903;
	/** 附件序号/新版预售合同附件四 */
	public static final int ATTACH_SERIAL_PFJ4 = 3904;
	/** 附件序号/新版预售合同附件五 */
	public static final int ATTACH_SERIAL_PFJ5 = 3905;
	/** 附件序号/新版预售合同附件八 */
	public static final int ATTACH_SERIAL_PFJ8 = 3908;
	/** 附件序号/新版预售合同附件九 */
	public static final int ATTACH_SERIAL_PFJ9 = 3909;
	/** 附件序号/新版预售合同附件十 */
	public static final int ATTACH_SERIAL_PFJ10 = 3910;
	/** 附件序号/新版预售合同附件十一 */
	public static final int ATTACH_SERIAL_PFJ11 = 3911;

	/** 附件序号/新版出售合同附件二 */
	public static final int ATTACH_SERIAL_SFJ2 = 4902;
	/** 附件序号/新版出售合同附件三 */
	public static final int ATTACH_SERIAL_SFJ3 = 4903;
	/** 附件序号/新版出售合同附件四 */
	public static final int ATTACH_SERIAL_SFJ4 = 4904;
	/** 附件序号/新版出售合同附件五 */
	public static final int ATTACH_SERIAL_SFJ5 = 4905;
	/** 附件序号/新版出售合同附件六 */
	public static final int ATTACH_SERIAL_SFJ6 = 4906;
	/** 附件序号/新版出售合同附件九 */
	public static final int ATTACH_SERIAL_SFJ9 = 4909;
	/** 附件序号/新版出售合同附件十 */
	public static final int ATTACH_SERIAL_SFJ10 = 4910;
	/** 附件序号/新版出售合同附件十一 */
	public static final int ATTACH_SERIAL_SFJ11 = 4911;
	/** 附件序号/新版出售合同附件十二 */
	public static final int ATTACH_SERIAL_SFJ12 = 4912;

	/** 新版预售合同版本号 */
	public static final String NEW_PC_CONTRACTVERSION = "111";
	/** 新版出售合同版本号 */
	public static final String NEW_SC_CONTRACTVERSION = "211";
	/** 旧版预售合同版本号 */
	public static final String OLD_PC_CONTRACTVERSION1 = "101";
	/** 旧版预售合同版本号 */
	public static final String OLD_PC_CONTRACTVERSION2 = "102";
	/** 旧版预售合同版本号 */
	public static final String OLD_PC_CONTRACTVERSION3 = "103";
	/** 旧版出售合同版本号 */
	public static final String OLD_SC_CONTRACTVERSION1 = "201";
	/** 旧版出售合同版本号 */
	public static final String OLD_SC_CONTRACTVERSION2 = "202";
	/** 定金合同版本号 */
	public static final String EC_CONTRACTVERSION = "301";
}
