package com.netcom.nkestate.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netcom.nkestate.framework.vo.DictVO;

public class Constant {

	/*static public String MiniConnectionJNDI = "java:comp/env/jdbc/QDFHConnection"; //业务数据库
	static public String RealestateConnectionJNDI = "java:comp/env/jdbc/RealestateConnection"; //内外登记数据库
	static public String HouseFundConnectionJNDI = "java:comp/env/jdbc/HouseFundConnection"; //房屋维修基金数据库
*/
	static public String MiniConnectionJNDI = "java:comp/env/jdbc/QDFHConnection"; //业务数据库
	static public String RealestateConnectionJNDI = "java:comp/env/jdbc/RealestateConnection"; //内外登记数据库
	static public String HouseFundConnectionJNDI = "java:comp/env/jdbc/HouseFundConnection"; //房屋维修基金数据库
	public static String UnirealestateJNDI = "java:comp/env/jdbc/UnirealestateConnection";
	
	static public String AttachmentURL = ""; //附件存储地址
	static public String ContractURL = "D:/QDFH/contractpdf"; // 合同打印存储路径 
	//static public String ContractURL = "D:/QDFH/contractpdf"; // 合同打印存储路径 
	static public String ContractPdfPath = "E:/Git/FirstHouse20/QDFirstHouse20/WebRoot/pdf"; //项目中pdf文件夹的路径
	static public String housePlanURL = "http://192.168.1.65:7001/QDQDCH//services/gis/prehouseplan?planID=";
	static public String ZBURL = "";
	static public String username = "";
	static public String password = "";
	static public String defaultPass = "670Z1472#aX@@02a$cZa32$22Qa4Q6ZX";//默认000000
	static public String REG_SMALL_CODE = ""; //初始登记类别
	static public String TempFilePath = ""; //临时文件存储路径
	static public String permitTypeBID = ""; //预售许可登记小类
	public static String imageServerURL="";//图片服务器路径
	public static String SmsURL="";//短信内容里的URL
	
		
	static final public Map<String,String> ContentTypeMap = new HashMap<String,String>();

	//系统字段表
	static final public Map<String , List<DictVO>> SysDictMap = new HashMap<String , List<DictVO>>();
	//系统加载字典表清单
	static final public List<String> SysDictNameList = new ArrayList<String>();

	//权利类别
	static public final int RightType_Real = 1; // 房地产权
	static public final int RightType_LandOwnerReal = 101; // 宗地所有权
	static public final int RightType_LandUseReal = 102; // 宗地使用权
	static public final int RightType_Real2 = 130; // （所有权宗地）房地产权
	static public final int RightType_Real_Parcel = 140; // 承包经营权
	static public final int RightType_Real_Forestry = 150; // 森林林木所有权
	static public final int RightType_Real_Sea = 160; // 海域，无居民海岛使用权
	static public final int RightType_Real_Struct = 161; // （海域、无居民海岛）构建筑物所有权

	static public final int RightType_Other = 2; // 抵押
	static public final int RightType_Limit = 3; // 查封
	static public final int RightType_Hire = 4; // 租赁
	static public final int RightType_Permit = 5; // 许可证
	static public final int RightType_Registout = 6; // 注销
	static public final int RightType_FileReg = 7; // 文件备案
	static public final int RightType_Dissent = 9; // 异议
	static public final int RightType_Servitude = 10; // 地役权

	static public final int RightType_Correct = 8; // 更正
	static public final int RightType_Replace = 801; // 补换证

	static{
		ContentTypeMap.put("jpg", "image/jpeg");
		ContentTypeMap.put("bmp", "image/bmp");
		ContentTypeMap.put("doc", "application/msword");
		ContentTypeMap.put("docx", "application/msword");
		ContentTypeMap.put("xls", "application/vnd.ms-excel");
		ContentTypeMap.put("xlsx", "application/vnd.ms-excel");
		ContentTypeMap.put("pdf", "application/pdf");
		
		ContentTypeMap.put("zip", "application/zip");
		ContentTypeMap.put("rar", "application/octet-stream");
		ContentTypeMap.put("7z", "application/octet-stream");

	}

	
	static{
		SysDictNameList.add("CT_000");
		SysDictNameList.add("CT_001");
		SysDictNameList.add("CT_002");
		SysDictNameList.add("CT_003");
		SysDictNameList.add("CT_004");
		SysDictNameList.add("CT_005");
		SysDictNameList.add("CT_006");
		SysDictNameList.add("CT_007");
		SysDictNameList.add("CT_008");
		SysDictNameList.add("CT_200");
		SysDictNameList.add("CT_300");
		SysDictNameList.add("CT_301");
		SysDictNameList.add("CT_302");
		SysDictNameList.add("CT_303");
		SysDictNameList.add("CT_304");
		SysDictNameList.add("CT_305");
		SysDictNameList.add("CT_306");
		SysDictNameList.add("CT_307");
		SysDictNameList.add("CT_308");
		SysDictNameList.add("CT_309");
		SysDictNameList.add("CT_310");
		SysDictNameList.add("CT_311");
		SysDictNameList.add("CT_312");
		SysDictNameList.add("CT_313");
		SysDictNameList.add("CT_314");
		SysDictNameList.add("CT_315");
		SysDictNameList.add("CT_316");
		SysDictNameList.add("CT_317");
		SysDictNameList.add("CT_318");
		SysDictNameList.add("CT_319");
		SysDictNameList.add("CT_400");
		SysDictNameList.add("CT_401");
		SysDictNameList.add("CT_402");
		SysDictNameList.add("CT_403");
		SysDictNameList.add("CT_500");
		SysDictNameList.add("CT_501");
		SysDictNameList.add("CT_502");
		SysDictNameList.add("CT_502_1");
		SysDictNameList.add("CT_503");
		SysDictNameList.add("CT_503_1");
		SysDictNameList.add("CT_504");
		SysDictNameList.add("CT_505");
		SysDictNameList.add("CT_506");
		SysDictNameList.add("CT_507");
		SysDictNameList.add("CT_508");
		SysDictNameList.add("CT_509");
		SysDictNameList.add("CT_509_1");
		SysDictNameList.add("CT_510");
		SysDictNameList.add("CT_511");
		SysDictNameList.add("CT_513");
		SysDictNameList.add("CT_514");
		SysDictNameList.add("CT_515");
		SysDictNameList.add("CT_516");
		SysDictNameList.add("CT_517");
		SysDictNameList.add("CT_517_1");
		SysDictNameList.add("CT_517_2");
		SysDictNameList.add("CT_517_7");
		SysDictNameList.add("CT_518");
		SysDictNameList.add("CT_519");
		SysDictNameList.add("CT_520");
		SysDictNameList.add("CT_521");
		SysDictNameList.add("CT_522");
		SysDictNameList.add("CT_523");
		SysDictNameList.add("CT_524");
		SysDictNameList.add("CT_525");
		SysDictNameList.add("CT_526");
		SysDictNameList.add("CT_527");
		SysDictNameList.add("CT_528");
		SysDictNameList.add("CT_529");
		SysDictNameList.add("CT_530");
		SysDictNameList.add("CT_531");
		SysDictNameList.add("CT_532");
		SysDictNameList.add("CT_533");
		SysDictNameList.add("CT_534");
		SysDictNameList.add("CT_535");
		SysDictNameList.add("CT_536");
		SysDictNameList.add("CT_537");
		SysDictNameList.add("CT_538");
		SysDictNameList.add("CT_539");
		SysDictNameList.add("CT_540");
		SysDictNameList.add("CT_541");
		SysDictNameList.add("CT_542");
		SysDictNameList.add("CT_543");
		SysDictNameList.add("CT_544");
		SysDictNameList.add("CT_545");
		SysDictNameList.add("CT_546");
		SysDictNameList.add("CT_547");
		SysDictNameList.add("CT_548");
		SysDictNameList.add("CT_549");
		SysDictNameList.add("CT_550");
		SysDictNameList.add("CT_551");
		SysDictNameList.add("CT_552");
		SysDictNameList.add("CT_553");
		SysDictNameList.add("CT_DISTRICT");
		SysDictNameList.add("CT_FCONTRACT_DIC");
		SysDictNameList.add("CT_XG_BUYERSOWNHOUSE");
		SysDictNameList.add("CT_XG_BUYHOUSETYPE");
		SysDictNameList.add("CT_XG_CARDTYPE");
		SysDictNameList.add("CT_XG_DOMICILE");
		SysDictNameList.add("CT_XG_MEMBERTYPE");
		SysDictNameList.add("CT_ETYPE");//许可证用房屋类型字典
		SysDictNameList.add("CT_HTYPE");//许可证用建筑类型字典
		SysDictNameList.add("CT_CURRENCY_TYPE");//许可证用币别
		SysDictNameList.add("CT_FILE_TYPE");//文件类型
	}


	//判断是否是图片文件
	static public boolean isImageFile(String fileType) {
		if(fileType == null)
			return false;
		fileType = fileType.toLowerCase();
		if("jpg".equals(fileType) || "jpeg".equals(fileType) || "png".equals(fileType) || "ico".equals(fileType) || "bmp".equals(fileType))
			return true;
		return false;
	}
		
}
