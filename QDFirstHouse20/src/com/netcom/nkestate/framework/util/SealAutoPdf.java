package com.netcom.nkestate.framework.util;

import java.awt.Color;
import java.io.File;

import org.apache.commons.io.FileUtils;

import cfca.paperless.client.bean.AddDateTextBean;
import cfca.paperless.client.bean.SealUserInfo;
import cfca.paperless.client.bean.SignLocation;
import cfca.paperless.client.servlet.PaperlessClient;
import cfca.paperless.util.GUID;
import cfca.paperless.util.IoUtil;
import cfca.paperless.client.util.PaperlessClientUtil;
import cfca.paperless.client.util.PwdEncryptUtil;
import cfca.paperless.client.util.StrategyUtil;
import cfca.paperless.util.StringUtil;


/**
 * @Description Pdf自动化签章接口，使用示例。
 * 1，操作对象为本地待处理的单个PDF文件。
 * 2，使用服务端管理页面中预先制作的印章进行签章。
 * 3，签章类型支持4种，1=空白标签签章,2=坐标签章,3=关键字签章，4=位置标识。
 * 4，返回成功签章的PDF文件。
 * 
 * @Author gcf
 * @Date 2019-9-13
 * @CodeReviewer TODO
 */
public class SealAutoPdf {

    public static int sealPdf(String filePath,String userName,String sealCode,String sealPassword,SignLocation signLocation,int imageSize,String comp_code) throws Exception {
    	int result = 0;
        long timeStart = System.currentTimeMillis();// 开始时间

        System.out.println("SealAutoPdfTest01 START");
        
        PaperlessClient clientBean = new PaperlessClient(PaperlessConfig.url, 10000, 60000);// 无纸化基本接口的访问URL
 
//        String filePath = "./TestData/file.pdf";
 
        
        String pdfSuffix = IoUtil.getFileNameSuffix(filePath);
        if(!PaperlessConfig.PDF_FILE_SUFFIX.equals(pdfSuffix)){
            throw new Exception("所选文件不是pdf文件");
        }
        
        int pageNo = 1;
        
        byte[] pdfFileData = FileUtils.readFileToByteArray(new File(filePath));

        // 保存到临时PDF表中的pdfId，符合唯一约束（可以为空）pdfId值长度为 18-32位
        // 不为空时，则在服务端的临时PDF表[TEMP_FILE_PDF]中，指定表中字段PDF_ID为此值，保存PDF文件数据，只保留1个小时，超时则清除。
        // 为空时，则不会在服务端保存PDF文件数据。
        String savedPdfId = GUID.generateId();
        //String savedPdfId = "201810161605540739521934892221";
        System.out.println("临时PDF表保存的pdfId=" + savedPdfId);
        if(StringUtil.isNotEmpty(savedPdfId) && (savedPdfId.length() < 18 || savedPdfId.length() > 32)){
            throw new Exception("savedPdfId 的长度必须在18-32之间，否则pdf参数不被认为是临时ID");
        }
        
        // 印章编码和印章密码，需要在无纸化管理系统中添加此印章的信息，登录管理页面->选择电子印章管理->选择印章管理->添加印章信息（需要先添加相应的印模信息和印章证书信息）
//        String sealCode = "ym201909559474786";//还一个可用的  yz2019053137241172
        //String sealPassword = PwdEncryptUtil.encrypto("111111");//后台设置的管理密码

        // 签章杂项信息
        // ************************************************************
        SealUserInfo sealUserInfo = new SealUserInfo(userName, "青岛", "一手房签章");// 签章人，签章地点，签章理由
        
        //sealUserInfo.setFillOpacity(1.0f);// 透明度0-1.0f，默认为1.0f，可以为空
        sealUserInfo.setVisible(true);// 是否显示，true or false，默认为true，可以为空
        
        //sealUserInfo.setBusinessCode("0123456789");// 业务码：可以是验证码、查询码，可以为空
        //sealUserInfo.setBusinessCodeStyle("font-size:9;x-ratio:0.1;y-ratio:0.7");// 业务码显示样式，可以为空
        
        // 设置印章显示大小，可以不设置。比如：100，代表100px。比如需要把章显示为4cm（圆章且DPI为96）时，传入151即可。（并且imageScale配置为0.87）
        sealUserInfo.setSealImageSize(imageSize);
        // ************************************************************

        // 签章类型（不能为空），1=空白标签签章,2=坐标签章,3=关键字签章，4=位置标识
        // ************************************************************
        // 1=按空白标签域签章
 
         //SignLocation signLocation = new SignLocation();
         //signLocation.setType("1");// 1=空白标签签章
   
        // 2=按坐标签章
        // 页数，按坐标签章时不能为空；
        // 左侧的x坐标（单位：像素）；左侧的y坐标（单位：像素）；
        // SignLocation signLocation = new SignLocation(1, 110, 210);
        
        // 3=按关键字签章
        // 关键字，按关键字签章时不能为空；
        // 位置风格：（上:U；下:D；左:L；右:R；中:C）；默认：C；
        // 横轴偏移，默认为0（单位：像素）；纵轴偏移，默认为0（单位：像素）
       // SignLocation signLocation = new SignLocation("出卖人（签字或盖章）", "R", 10, 0);
        // 关键字位置索引（1：第一个位置；2：第二个位置；0：默认全部位置盖章，支持1、2、1-3、3-9格式，如果输入非法数字或者负数当做0处理，如果输入的数字大于关键字数量时就在最后一个位置盖章处理）
       // signLocation.setKeywordPositionIndex("1");
        
        // 4=按位置标识签章（需要提前在服务端管理页面上登录业务模板和签章位置标识信息）
        //SignLocation signLocation = new SignLocation("BizLoc001");// 签章位置标识编码
        // ************************************************************
        /**
         * 是否在图片上添加日期；默认为false不添加;为true时，在图片下部添加日期，日期格式为："yyyy年MM月dd日"
         */
        AddDateTextBean addDateTextBean = new AddDateTextBean(false,"",15,30,false,-30,-10,90);
        
        addDateTextBean = new AddDateTextBean(false,"",15,30,false,-30,-10,90,"F0CCff");
 
        // 生成签章策略文件
        String sealStrategyXml = StrategyUtil.createSealStrategyXml(sealCode, sealPassword, sealUserInfo, signLocation,addDateTextBean);
 
        System.out.println(sealStrategyXml);
        
        // 操作员编码或者机构号
        String operatorCode = comp_code;
        // 渠道编码，可以为空
        String channelCode = PaperlessConfig.channelCode;
        
        // 获取时间戳的方式。默认值为0。0：实时访问CFCA 时间戳服务；1：使用从CFCA购置并在本地部署的时间戳服务器产品；
        String timestampChannel = "1";
        //密码是否密文传输，默认为false-原文传输，true-密文传输
        String isEncrypt = PaperlessConfig.isEncrypt;

        // 取得签章后的PDF文件数据
        byte[] sealedPdfData = clientBean.sealAutoPdf(pdfFileData, savedPdfId, sealStrategyXml, operatorCode, channelCode, timestampChannel,isEncrypt);

        String errorRsString = PaperlessClientUtil.isError(sealedPdfData);

        //String outputPdfPath = "./TestData/output/SealAutoPdfTest01.pdf";
        
        // 处理结果为正常，保存签章后的PDF文件到本地目录下
        if ("".equals(errorRsString)) {
            System.out.println("SealAutoPdfTest01 END OK");
            
            System.out.println(sealedPdfData.length);
            IoUtil.write(filePath, sealedPdfData);
            
            System.out.println("VerifyPdfSeal START");
            
            byte[] pdfFile = FileUtils.readFileToByteArray(new File(filePath));//./TestData/output/signAutoPdf-file.pdf.pdf

            String verifyStrategyXml = "<Request><SealVerifyType>2</SealVerifyType></Request>"; // 验章策略文件

            String results = clientBean.verifyPdfSeal(pdfFile, verifyStrategyXml, operatorCode, PaperlessConfig.channelCode);
            
            String code = StringUtil.getXmlField(results, "Code");

            // 错误码（Code不等于200时为异常）
            if ("200".equals(code)) {
                System.out.println("VerifyPdfSeal  END OK");
                System.out.println(result);
            } else {
                // 处理结果为异常，打印出错误码和错误信息
                System.out.println("VerifyPdfSeal  END NG");
                System.out.println(result);
            }
            
        } else {
            // 处理结果为异常，打印出错误码和错误信息
            System.out.println("SealAutoPdfTest01 END NG");
            System.out.println(new String(sealedPdfData, "UTF-8"));
        }
 
        long timeEnd = System.currentTimeMillis();// 结束时间
        System.out.println("##########" + "time used:" + (timeEnd - timeStart) + "##########");
        result = 1;
		return result;
    }

    /**
     * 签章
     *
     * @param filePath        pdf路径
     * @param outputPdfPath   保存路径
     * @param signLocation    签章位置
     * @param sealImageBase64 自定义章图片(也可以传入签字图片的base64,但是会覆盖签章系统里的章,)
     * @throws Exception
     */
    public static void signPdf(String filePath, String outputPdfPath, SignLocation signLocation, byte[] sealImageBase64, String comp_code, String sealCode, String sealPassword) throws Exception {
        long timeStart = System.currentTimeMillis();
        System.out.println("SealAutoPdf START");
        // 无纸化基本接口的访问URL
        PaperlessClient clientBean = new PaperlessClient(PaperlessConfig.url, 10000, 60000);

        String pdfSuffix = IoUtil.getFileNameSuffix(filePath);
        if (!PaperlessConfig.PDF_FILE_SUFFIX.equals(pdfSuffix)) {
            throw new Exception("所选文件不是pdf文件");
        }

        byte[] pdfFileData = FileUtils.readFileToByteArray(new File(filePath));
        byte[] sealedPdfData = signPdf(pdfFileData, signLocation, sealImageBase64, comp_code,sealCode,sealPassword);

        System.out.println("SealAutoPdf END OK");

        System.out.println(sealedPdfData.length);
        IoUtil.write(outputPdfPath, sealedPdfData);

        System.out.println("VerifyPdfSeal START");

        byte[] pdfFile = FileUtils.readFileToByteArray(new File(outputPdfPath));
        // 验章策略文件
        String verifyStrategyXml = "<Request><SealVerifyType>2</SealVerifyType></Request>";

        String result = clientBean.verifyPdfSeal(pdfFile, verifyStrategyXml, comp_code, PaperlessConfig.channelCode);

        String code = StringUtil.getXmlField(result, "Code");

        // 错误码（Code不等于200时为异常）
        if ("200".equals(code)) {
            System.out.println("VerifyPdfSeal  END OK");
            System.out.println(result);
        } else {
            // 处理结果为异常，打印出错误码和错误信息
            System.out.println("VerifyPdfSeal  END NG");
            System.out.println(result);
        }

    }
    
    /**
     * @param pdfFileData
     * @param signLocation
     * @param sealImageBase64
     * @return
     * @throws Exception
     */
    public static byte[] signPdf(byte[] pdfFileData, SignLocation signLocation, byte[] sealImageBase64, String comp_code, String sealCode, String sealPassword) throws Exception {
        long timeStart = System.currentTimeMillis();
        try {

            System.out.println("SealAutoPdf START");
            System.out.println("PaperlessConfig.url = " + PaperlessConfig.url);
            // 无纸化基本接口的访问URL
            PaperlessClient clientBean = new PaperlessClient(PaperlessConfig.url, 10000, 60000);

            int pageNo = 1;

            // 保存到临时PDF表中的pdfId，符合唯一约束（可以为空）pdfId值长度为 18-32位
            // 不为空时，则在服务端的临时PDF表[TEMP_FILE_PDF]中，指定表中字段PDF_ID为此值，保存PDF文件数据，只保留1个小时，超时则清除。
            // 为空时，则不会在服务端保存PDF文件数据。
            String savedPdfId = GUID.generateId();
            //String savedPdfId = "201810161605540739521934892221";
            System.out.println("临时PDF表保存的pdfId=" + savedPdfId);
            boolean pdfIdIsNotEmpty = StringUtil.isNotEmpty(savedPdfId);
            boolean pdfIdLengthFlag = savedPdfId.length() < 18 || savedPdfId.length() > 32;
            if (pdfIdIsNotEmpty && pdfIdLengthFlag) {
                throw new Exception("savedPdfId 的长度必须在18-32之间，否则pdf参数不被认为是临时ID");
            }

            // 印章编码和印章密码，需要在无纸化管理系统中添加此印章的信息，登录管理页面->选择电子印章管理->选择印章管理->添加印章信息（需要先添加相应的印模信息和印章证书信息）
            // String sealCode = "yz2019062481998900";// 还一个可用的 yz2019053137241172
            // 后台设置的管理密码
            sealPassword = PwdEncryptUtil.encrypto(sealPassword);

            // 签章杂项信息
            // ************************************************************
            // 签章人，签章地点，签章理由
            SealUserInfo sealUserInfo = new SealUserInfo("张三", "Qingdao", "电子签字");
            //大小
            sealUserInfo.setSealImageSize(30);
            // sealUserInfo.setFillOpacity(1.0f);// 透明度0-1.0f，默认为1.0f，可以为空
            // 是否显示，true or false，默认为true，可以为空
            sealUserInfo.setVisible(true);

            // sealUserInfo.setBusinessCode("0123456789");// 业务码：可以是验证码、查询码，可以为空
            // sealUserInfo.setBusinessCodeStyle("font-size:9;x-ratio:0.1;y-ratio:0.7");// 业务码显示样式，可以为空

            // 设置印章显示大小，可以不设置。比如：100，代表100px。比如需要把章显示为4cm（圆章且DPI为96）时，传入151即可。（并且imageScale配置为0.87）
            //sealUserInfo.setSealImageSize(100);
            // ************************************************************

            // 签章类型（不能为空），1=空白标签签章,2=坐标签章,3=关键字签章，4=位置标识
            // ************************************************************
            // 1=按空白标签域签章

            // SignLocation signLocation = new SignLocation();
            // signLocation.setType("1");// 1=空白标签签章

            // 2=按坐标签章
            // 页数，按坐标签章时不能为空；
            // 左侧的x坐标（单位：像素）；左侧的y坐标（单位：像素）；
            //signLocation = new SignLocation(1, 110, 210);

            // 3=按关键字签章
            // 关键字，按关键字签章时不能为空；
            // 位置风格：（上:U；下:D；左:L；右:R；中:C）；默认：C；
            // 横轴偏移，默认为0（单位：像素）；纵轴偏移，默认为0（单位：像素）
            ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //signLocation = new SignLocation("销售员证书号", "R", 10, 0);
            // 关键字位置索引（1：第一个位置；2：第二个位置；0：默认全部位置盖章，支持1、2、1-3、3-9格式，如果输入非法数字或者负数当做0处理，如果输入的数字大于关键字数量时就在最后一个位置盖章处理）
            //signLocation.setKeywordPositionIndex("1");

            // 4=按位置标识签章（需要提前在服务端管理页面上登录业务模板和签章位置标识信息）
            // SignLocation signLocation = new SignLocation("BizLoc001");// 签章位置标识编码
            // ************************************************************
            //是否在图片上添加日期；默认为false不添加;为true时，在图片下部添加日期，日期格式为："yyyy年MM月dd日"
            AddDateTextBean addDateTextBean = new AddDateTextBean(false, "", 15, 30, false, -30, -10, 90, "F0CCff");

            // 生成签章策略文件
            String sealStrategyXml = StrategyUtil.createSealStrategyXml(sealCode, sealPassword, sealImageBase64, sealUserInfo, signLocation, addDateTextBean);
            System.out.println(sealStrategyXml);

            // 操作员编码或者机构号
            String operatorCode = comp_code;
            // 渠道编码，可以为空
            String channelCode = PaperlessConfig.channelCode;

            // 获取时间戳的方式。默认值为0。0：实时访问CFCA 时间戳服务；1：使用从CFCA购置并在本地部署的时间戳服务器产品；
            String timestampChannel = "1";
            // 密码是否密文传输，默认为false-原文传输，true-密文传输
            String isEncrypt = PaperlessConfig.isEncrypt;

            // 取得签章后的PDF文件数据
            byte[] sealedPdfData = clientBean.sealAutoPdf(pdfFileData, savedPdfId, sealStrategyXml, operatorCode, channelCode, timestampChannel, isEncrypt);

            String errorRsString = PaperlessClientUtil.isError(sealedPdfData);

            // 处理结果为正常，保存签章后的PDF文件到本地目录下
            if ("".equals(errorRsString)) {
                return sealedPdfData;

            } else {
                // 处理结果为异常，打印出错误码和错误信息
                System.out.println("SealAutoPdf END NG");
                System.out.println(new String(sealedPdfData, "UTF-8"));
                throw new Exception(new String(sealedPdfData, "UTF-8"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            long timeEnd = System.currentTimeMillis();
            System.out.println("##########" + "time used:" + (timeEnd - timeStart) + "##########");
        }

    }
}
