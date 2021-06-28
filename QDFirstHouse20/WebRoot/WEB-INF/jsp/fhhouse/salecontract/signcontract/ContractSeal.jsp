 <%@ page contentType = "text/html; charset=UTF-8" %>
<%@page import="com.netcom.nkestate.security.HttpSafeUtil"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>盖章</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
        <style>
                 * {margin: 0; padding: 0;}
         html,body {height: 100%; width: 100%;}
        .fontSize1 {font-size: 65%;     font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;}
        .fontSize2 {font-size: 70%;     font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;}
        .fontSize3 {font-size: 75%;     font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;}
        .fakehlink {cursor: pointer; text-decoration: underline; font-weight: normal; line-height: 26px; color: #0066CC;}
        .plaintext {line-height: 28px}
        .blankline {height: 5px}
        .remaining {width: 100%; position: absolute; top: 72px; bottom: 0px;}
        </style>
        <script type="text/javascript" src="<%=path%>/js/easyui/jquery.min.js"></script>
        <script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>

        <!-- web page script -->
        <script type="text/javascript">

        var bShowToolbar = true;
        var bShowSaveAs = true;
        var bShowPrint = true;
        var bEnableCueBanner = false;
        var nEnableSidebar = 0;
        var gSigAppearanceType = "";
        var gWritingImage = "";
        var gKeyImage = "";
        var gPngSealImage = "iVBORw0KGgoAAAANSUhEUgAAAKAAAACgCAMAAAC8EZcfAAACplBMVEUAAAD/AAD/AAD/AAD/AADVAADbAADfACDjABzmABroABfqABXrABTtABLuABHvABDwAA/jAA7kAA3mAA3oAAzpABbqABXrABTsABPtABLlABLmABHmABDnABDoAA/pAA/pAA/qAA7rABTrABTsABPmABPnABLnABLoABHoABHpABHpABDqABDqABDrAA/rAA/rAA/nABPnABPoABPoABLpABLpABLpABHqABHqABHnABDpABPpABLqABLqABHqABHrABHoABHoABHoABDpABDpABDqABLqABLoABLoABHoABHpABHpABHpABDpABDqABDqABDqABDqABLoABLoABLoABLoABHpABHpABHpABHpABHqABHqABHoABDoABLpABLpABLpABLpABHpABHqABHqABHoABHoABHoABDpABDpABDpABDpABLpABLpABLqABHqABHoABHoABHpABHpABHpABHpABDpABDpABDqABLqABLoABLoABHpABHpABHpABHpABHpABHpABHpABHqABDqABDoABDoABLpABLpABLpABHpABHpABHpABHpABHpABHqABHqABHoABHpABHpABDpABDpABLpABHpABHpABHqABHoABHoABHpABHpABHpABHpABDpABDpABLpABLqABHoABHpABHpABHpABHpABHpABHpABHpABHpABHpABDqABDoABLoABHpABHpABHpABHpABHpABHpABHpABHpABHqABHoABHpABHpABDpABLpABHpABHpABHpABHpABHpABHqABHpABHpABHpABHpABHpABHpABDpABLpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABHpABGm2vFTAAAA4XRSTlMAAQIDBAYHCAkKCwwNDg8QERITFBYXGBobHB0eHyAhIiMlJicoKSorLC0uLzAxMjM0NTY3ODk6Ozw9QERGSElKS0xNTlBRVVZXWFpbXF1eX2BhYmNkZWZnaGlqa2xwcXJzdHV2d3h6e3x9fn+AgYKEhYaHiImLjI2Oj5CRk5SVlpeYmZqbnJ2en6ChoqOkpaanqKmqq6ytr7CxsrS1tre4uru8vb7AwcLD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";
        
        var pfxCert = "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";
        var pfxPwd = "111111";
        
        // Create PDFCtrl according to IE architecture

        function OnLoad() {
            try {
                var PDFCtrlPlaceHolder = document.getElementById("PDFCtrlPlaceHolder");

                if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
                    if (window.navigator.cpuClass == "x86") {
                        PDFCtrlPlaceHolder.innerHTML = "<object id=\"PDFCtrl\" codebase=\"<%=path%>/help/tools/TrustSignPDFPlugin.Standard.x86.cab#version=3,2,1,9\" classid=\"clsid:6F60FE31-F827-4295-8AC4-775EF7478A6A\" width=\"100%\" height=\"100%\"><param name=\"showToolbar\" value=true></object>";
                    }
                    else {
                        PDFCtrlPlaceHolder.innerHTML = "<object id=\"PDFCtrl\" codebase=\"<%=path%>/help/tools/TrustSignPDFPlugin.Standard.x64.cab#version=3,2,1,9\" classid=\"clsid:6F60FE31-F827-4295-8AC4-775EF7478A6A\" width=\"100%\" height=\"100%\"><param name=\"showToolbar\" value=true></object>";
                    }
                }
                else {
                    alert("请使用 IE 浏览器！");
                }
            }
            catch (e) {
                alert(e);
                return;
            }
        }

        // PDF Control Ready Event

        function OnCtrlReady() {
            try {
                PDFCtrl.GetVersion();
                PDFCtrl.SetSignCert(pfxCert, pfxPwd);
                PDFCtrl.SetPKCS11Module("UlanPKCS11.Enterprise.dll|UlanPKCS11.dll|CFCA_UKEY_P11.dll");
                PDFCtrl.SetSM2CSPList("CFCA FOR UKEY CSP v1.1.0|CFCA Ulan CSP v4.0");
                PDFCtrl.SetTimestampServer("");
                PDFCtrl.SetImageBehindText(true);
                PDFCtrl.CompatibleWith2ndGenerationKey(true);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        
        /* PDF Control Method */

        // Control appearance

        function ToggleToolbar() {
            bShowToolbar = !bShowToolbar;
            try {
                PDFCtrl.ToggleToolbar(bShowToolbar);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ToggleButton_SaveAs() {
            bShowSaveAs = !bShowSaveAs;
            try {
                PDFCtrl.ToggleButton_SaveAs(bShowSaveAs);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ToggleButton_Print() {
            bShowPrint = !bShowPrint;
            try {
                PDFCtrl.ToggleButton_Print(bShowPrint);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function EnableSidebar() {
            nEnableSidebar = nEnableSidebar == 0 ? 2 : 0;
            try {
                PDFCtrl.EnableSidebar(nEnableSidebar);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function EnableCueBanner() {
            bEnableCueBanner = !bEnableCueBanner;
            try {
                PDFCtrl.EnableCueBanner(bEnableCueBanner);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        
        // File operations

        function OpenFileDialog() {
            document.getElementById("openfile").click();
        }
        
        function OpenLocalFile(file) {
            try {
                PDFCtrl.OpenLocalFile(file.value);
                
                var node = file.cloneNode(true);
                file.parentNode.replaceChild(node, file);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function OpenWebFile() {
        	var contractID = '${contractID}';
            try {
            	//读取pdf到指定的项目路径里
            	$.ajax({
					type : "POST",
					contentType : "application/x-www-form-urlencoded;charset=utf-8",
					url : "<%=path%>/inner/ContractPdf/doMovePDF.action",
					data : {"contractID":contractID},
					dataType : "json",
					success : function(data){
						if(data[0].result=="1"){
							PDFCtrl.OpenWebFile("<%=basePath%>pdf/"+contractID+".pdf");
						}else{
							alert(data[0].message);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						alert("打开网络pdf异常！");
					}
				});
                
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function UploadCurrentFile() {
            try {
            	var contractID = '${contractID}';
                var response = PDFCtrl.UploadCurrentFile("<%=basePath%>/servlet/UploadFileServlet?contractID="+contractID);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function CloseFile() {
            try {
                PDFCtrl.CloseFile();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        // Page operations

        function GoToFirstPage() {
            try {
                PDFCtrl.GoToFirstPage();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GoToLastPage() {
            try {
                PDFCtrl.GoToLastPage();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GoToPrevPage() {
            try {
                PDFCtrl.GoToPrevPage();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GoToNextPage() {
            try {
                PDFCtrl.GoToNextPage();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GetPageInfo() {
            try {
                var pageNo = PDFCtrl.GetCurrentPageNo();
                var pageCount = PDFCtrl.GetPageCount();
                var width = PDFCtrl.GetPageWidth(pageNo);
                var height = PDFCtrl.GetPageHeight(pageNo);
                alert("页码：" + pageNo + " / " + pageCount + "\n宽度：" + width + "\n高度：" + height);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GoToPage() {
            try {
                var pageNo = document.getElementById("PageNo").value;
                if ("" == pageNo) {
                    alert("请输入页码！");
                    return;
                }
                PDFCtrl.GoToPage(pageNo);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GoToBookmark() {
            try {
                var bookmark = document.getElementById("Bookmark").value;
                if ("" == bookmark) {
                    alert("请输入书签名！");
                    return;
                }
                PDFCtrl.GoToBookmark(bookmark);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        
        // Zoom operations

        function ZoomTo(factor) {
            try {
                PDFCtrl.ZoomTo(factor);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ZoomOut() {
            try {
                PDFCtrl.ZoomOut();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ZoomIn() {
            try {
                PDFCtrl.ZoomIn();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ZoomValue() {
            try {
                var zoom = PDFCtrl.ZoomValue();
                alert("当前缩放率: " + zoom + "%");
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        // Print operations

        function GetDefaultPrinter() {
            try {
                var printerName = PDFCtrl.GetDefaultPrinter();

                document.getElementById("PrinterName").value = printerName;
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        function PrintFile() {
            try {
                var printerName = document.getElementById("PrinterName").value;
                var printOptions = document.getElementById("PrintOptions").value;

                PDFCtrl.PrintFile(printerName, printOptions);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        // Extend screen operations

        function DuplicateToExtendScreen() {
            try {
                PDFCtrl.DuplicateDocToExtendScreen();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function CloseExtendScreen() {
            try {
                PDFCtrl.RemoveDocOnExtendScreen();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        // Certificate operations

        function SelectSignCert() {
            try {
                var subjectDN = document.getElementById("SubjectDN").value;
                var issuerDN = document.getElementById("IssuerDN").value;
                var certSN = document.getElementById("CertSN").value;

                PDFCtrl.SelectSignCert(subjectDN, issuerDN, certSN, "");
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GetSignCertInfo() {
            try {
                var certInfo = "证书持有者CN:\t"     + PDFCtrl.GetSignCertInfo("SubjectCN")
                             + "\n证书持有者DN:\t"   + PDFCtrl.GetSignCertInfo("SubjectDN")
                             + "\n证书颁发者DN:\t"   + PDFCtrl.GetSignCertInfo("IssuerDN")
                             + "\n证书序列号:\t"     + PDFCtrl.GetSignCertInfo("SerialNumber")
                             + "\n起始有效期:\t"     + PDFCtrl.GetSignCertInfo("ValidFrom")
                             + "\n截止有效期:\t"     + PDFCtrl.GetSignCertInfo("ValidTo")
                             + "\n起始有效期UTC:\t"  + PDFCtrl.GetSignCertInfo("UTCValidFrom")
                             + "\n截止有效期UTC:\t"  + PDFCtrl.GetSignCertInfo("UTCValidTo")
                             + "\n证书算法:\t"       + PDFCtrl.GetSignCertInfo("CertAlgorithm")
                             + "\n证书内容:\n"       + PDFCtrl.GetSignCertInfo("CertContent");

                alert(certInfo);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }
        
        function SetUseExternalSigner() {
            try {
                PDFCtrl.CompatibleWith2ndGenerationKey(false);
                PDFCtrl.SetUseExternalSigner("RSA");
                
                alert("已启用外部签名模式！");
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        // Trigger place signature

        function TriggerPlaceSignature() {
            try {
                gSigAppearanceType = GetSelectValue("AppearanceType");
                
                if ("invisible" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Invisible();
                }
                else if ("text" == gSigAppearanceType) {
                    PDFCtrl.DragToPlaceSignature();
                }
                else if ("handwriting" == gSigAppearanceType) {
                    var width  = PDFCtrl.GetExtendScreenWidth();
                    var height = PDFCtrl.GetExtendScreenHeight();
                    PDFCtrl.SetHandwritingDialogPosition(width - 550, height - 350, 500, 300);
                    PDFCtrl.SetHandwritingDialogTransparent(80);
                    gWritingImage = PDFCtrl.OpenHandwritingDialog(7);
                    PDFCtrl.ClickToPlaceSignature(gWritingImage);
                }
                else if ("image" == gSigAppearanceType ||
                    "image_repeat_signing" == gSigAppearanceType ||
                    "image_batch_signing" == gSigAppearanceType) {
                    PDFCtrl.ClickToPlaceSignature(gPngSealImage);
                }
                else if ("keyImage" == gSigAppearanceType ||
                    "keyImage_repeat_signing" == gSigAppearanceType ||
                    "keyImage_batch_signing" == gSigAppearanceType) {
                    gKeyImage = PDFCtrl.ReadKeyImage();
                    PDFCtrl.ClickToPlaceSignature(gKeyImage);
                }
                else if ("crossPageSeal_right" == gSigAppearanceType) {
                    PDFCtrl.SignCrossPageSeal(1, gPngSealImage, 0, 0, 0.2, 0.2, 250, false);
                }
                else if ("crossPageSeal_right2" == gSigAppearanceType) {
                    PDFCtrl.SignCrossPageSeal(1, gPngSealImage, 0, 0, 0.2, 0.2, 350, true);
                }
                else if ("crossPageSeal_left_right" == gSigAppearanceType) {
                    PDFCtrl.SignCrossPageSeal(2, gPngSealImage, 0, 0, 0.4, 0.6, 450, false);
                }
                else if ("crossPageSeal_left" == gSigAppearanceType) {
                    PDFCtrl.SignCrossPageSeal(3, gPngSealImage, 0, 0, 0.2, 0.2, 150, false);
                }
                else if ("crossPageSeal_top_bottom" == gSigAppearanceType) {
                    PDFCtrl.SignCrossPageSeal(4, gPngSealImage, 0, 0, 0.3, 0.7, 300, false);
                }
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function ResetMouseAction() {
            try {
                PDFCtrl.ResetMouseAction();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function RemoveLastSignature() {
            try {
                PDFCtrl.RemoveLastSignature();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }
        
        function RemoveAllSignatures() {
            try {
                PDFCtrl.RemoveAllSignatures();
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }
        
        function SignKeyword() {
            try {
                var keyword = document.getElementById("Keyword").value;
                var count = PDFCtrl.FindKeyword(keyword, false);
                if (0 === count) {
                    alert("没有找到关键字！");
                    return;
                }

                for (var i = 0; i < count; i++) {
                    var findResult = PDFCtrl.GetFindResult(i);
                    var x = (findResult.x0 + findResult.x1) / 2;
                    var y = (findResult.y0 + findResult.y1) / 2;
                    PDFCtrl.AddSignaturePosition(findResult.pageNo, x, y);
                    findResult = null;
                }

                PDFCtrl.SignFile_Image(0, 0, 0, gPngSealImage);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }
		
        function GetSigFieldInfo() {
            try {
                var count = PDFCtrl.GetSigFieldCount();
                var info = "空白签名域个数：" + count;
                for (var i = 0; i < count; i++) {
                    var fieldName = PDFCtrl.GetSigFieldName(i);
                    info += "\n" + fieldName;
                }
                alert(info);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function GetSignatureInfo() {
            try {
                var count = PDFCtrl.GetSignatureCount();
                var info = "数字签名个数：" + count;

                for (var i = 0; i < count; i++) {
                    var signature = PDFCtrl.VerifySignature(i);
                    var result = signature.GetSignCertInfo('SubjectCN');
                    info += "\n\n签名者：" + result;
                    result = signature.GetSignatureStatus();
                    info += "\n签名状态：" + (result === 0 ? "ok" : "Error");
                    result = signature.GetSignCertStatus();
                    info += "\n证书状态：" + (result === 0 ? "ok" : result === 1 ? "TimeInvalid" : result === 2 ? "UntrustedSigner" : "Error");
                    result = signature.GetSignTime();
                    info += "\n签名时间：" + result;
                    result = signature.GetTimestampStatus();
                    info += "\n时间戳状态：" + (result === -1 ? "NoTimestamp" : result === 0 ? "ok" : result === 2 ? "UntrustedTSA" : "Error");
                    if (result !== -1) {
                        result = signature.GetTSACertInfo("SubjectCN");
                        info += "\n时间戳证书：" + result;
                    }
                    signature = null;
                }
                alert(info);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        /* PDF Control Event Handler */

        // Sign file
        function OnClickPoint(nPageNo, x, y) {
            try {
                if ("handwriting" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Image(nPageNo, x, y, gWritingImage);
                }
                else if ("image" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Image(nPageNo, x, y, gPngSealImage);
                }
                else if ("image_repeat_signing" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Image(nPageNo, x, y, gPngSealImage);
                    PDFCtrl.ClickToPlaceSignature(gPngSealImage);
                }
                else if ("image_batch_signing" == gSigAppearanceType) {
                    var count = PDFCtrl.GetPageCount();
                    for (var i = 1; i <= count; i++) {
                        PDFCtrl.AddSignaturePosition(i, x, y);
                    }
                    PDFCtrl.SignFile_Image(0, 0, 0, gPngSealImage);
                }
                else if ("keyImage" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Image(nPageNo, x, y, gKeyImage);
                    //PDFCtrl.SignFile_KeyImage(nPageNo, x, y);
                }
                else if ("keyImage_repeat_signing" == gSigAppearanceType) {
                    PDFCtrl.SignFile_Image(nPageNo, x, y, gKeyImage);
                    PDFCtrl.ClickToPlaceSignature(gKeyImage);
                }
                else if ("keyImage_batch_signing" == gSigAppearanceType) {
                    var count = PDFCtrl.GetPageCount();
                    for (var i = 1; i <= count; i++) {
                        PDFCtrl.AddSignaturePosition(i, x, y);
                    }
                    PDFCtrl.SignFile_Image(0, 0, 0, gKeyImage);
                    // PDFCtrl.SignFile_KeyImage(0, 0, 0);
                }
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        function OnDragRect(nPageNo, x0, y0, x1, y1) {
            try {
                if ("text" == gSigAppearanceType) {
                    var signer = PDFCtrl.GetSignCertInfo("SubjectCN");
                    PDFCtrl.SignFile_Text(nPageNo, x0, y0, x1, y1, signer);
                }
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }


        // Sign blank field
        function OnClickField(strFieldName) {
            try {
                var appearanceType = GetSelectValue("AppearanceType");

                if ("text" == appearanceType) {
                    var signer = PDFCtrl.GetSignCertInfo("SubjectCN");                    
                    PDFCtrl.SignField_Text(strFieldName, signer);
                }
                else if ("handwriting" == appearanceType) {
                    var writingImage = PDFCtrl.OpenHandwritingDialog(5);
                    var writingData  = PDFCtrl.GetHandwritingData();
                    PDFCtrl.SignField_Image(strFieldName, writingImage);
                }
                else if ("image" == appearanceType ||
                    "image_repeat_signing" == appearanceType ||
                    "image_batch_signing" == appearanceType) {
                    PDFCtrl.SignField_Image(strFieldName, gPngSealImage);
                }
                else if ("keyImage" == appearanceType ||
                    "keyImage_repeat_signing" == appearanceType ||
                    "keyImage_batch_signing" == appearanceType) {
                    PDFCtrl.SignField_KeyImage(strFieldName);
                }
                else {
                    alert("Unsupported!");
                }
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
        }

        // Sign hash
        function OnSignHash(strSignAlg, strHashAlg, strBase64Hash, bAttached, strUserParam) {
            var signature;
            try {
                alert("收到控件的外部签名请求，参数如下：\n\nstrSignAlg\t=  " + strSignAlg + "\nstrHashAlg\t=  " + strHashAlg + "\nstrBase64Hash\t=  " + strBase64Hash + "\nbAttached\t=  " + bAttached + "\nstrUserParam\t=  " + strUserParam);
                
                // This is an example of external signer implementation, you should implement your own.
                PDFCtrl.SetSignCert(pfxCert, pfxPwd);
                signature = PDFCtrl.SignHashPKCS7(strSignAlg, strHashAlg, strBase64Hash, bAttached, strUserParam);
            }
            catch (e) {
                var LastErrorDesc = PDFCtrl.GetLastErrorDesc();
                alert(LastErrorDesc);
            }
            return signature;
        }

        function GetSelectValue(name) {
            var e = document.getElementById(name);
            return e.options[e.selectedIndex].value;
        }

        </script>

        <script type="text/javascript" for="PDFCtrl" event="NotifyCtrlReady()">
            OnCtrlReady();
        </script>

        <script type="text/javascript" for="PDFCtrl" event="NotifyDragRect(nPageNo, x0, y0, x1, y1)">
            OnDragRect(nPageNo, x0, y0, x1, y1);
        </script>

        <script type="text/javascript" for="PDFCtrl" event="NotifyClickPoint(nPageNo, x, y)">
            OnClickPoint(nPageNo, x, y);
        </script>

        <script type="text/javascript" for="PDFCtrl" event="NotifyClickField(strFieldName)">
            OnClickField(strFieldName);
        </script>
        
        <script type="text/javascript" for="PDFCtrl" event="NotifySignHash(strSignAlg, strHashAlg, strBase64Hash, bAttached, strUserParam)">
            OnSignHash(strSignAlg, strHashAlg, strBase64Hash, bAttached, strUserParam);
        </script>

    </head>

    <body bgcolor="#FFFFFF" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" onload="javascript:OnLoad();">
        <div>
            <!--titlebar start-->
            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#48A33A">
                <tr>
                    <td bgcolor="#48A33A" valign="top" width="250" rowspan="2">
                        <img src="data:image/png;base64,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" alt="CFCA(R) TrustSignPDF" border="0" />
                    </td>
                    <td bgcolor="#48A33A" valign="middle" height="20" align="right" class="fontSize1" nowrap="true" style="color:white;">
                        CFCA TrustSignPDF ActiveX Control Sample  
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#48A33A" valign="top" height="40" align="right">
                        <a href="http://www.cfca.com.cn/" target="_blank">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHAAAAAoCAYAAAAmPX7RAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAATySURBVHhe7Vn5UxRHFM7fZVK5U4kpo2KOKk2CuQtzaEpTSTQmpSnUaCIhkVLjFeS+BEUKXUQEQQ6Re7mCXAsqy02Q5XDDy3w9Pbs9vbOrv/Zuf9RXO3S/N73TX/eb12+fei19DWmqSy2g4tQCKk4toOLUAipOLaDi1AIqTi2g4tQCKk4toOLUAipOLaDi1AIqTi2g4oxqATdkv0hJtQeovP8K9U/30eziDPVOdlF+ZybFF25y9DnWcISO304yeJRONP7OeLIxWeAfdOrOn7TLlRDiu6P0E8p1p9Gde/U05ZukiQUvVQ/foEPVP4XYOvF0Uwpltp+j862nKLXlL9pT/rWjncioFRATP21MYjis+Ffo+2vbbT7fuLbx3scDIlt+H158m+pHa3iPM2qGK21jyTxSs59bBtEz0eloKzIqBbzWX8qnIDJWjb938tYG/LI7UnnP4/FW7qvMZ+fVBFr2L/HWyEhtORkYSyQixdKjRW4VREwKWNxTwB8/iMZ7dVTUnUt9Uz28JYhjDb8GfFseNPJWE96HD6jWU2XwJvu85alkzGr/m9m/d2EjtwwCux7f4UpfMW8JYtI3QWvTnw6MZzHcgsP4sq3MqBLwu7Iv+aMHcbhmn81mbmmW95go6S1i7RuNXbDsX+atJn6s2GXzlTk4088tTWDHxGW/FOhHGJeBccR7JFx+n/eEwvfIZ7N1YlQJODZ/nz+6CdfdkhCbtNbTdHeq10hq/qEBI7FJrjvE2vde38m9TEBo2VfkgZt7uaUJORyD717YwHa9OV4ftY410brMZ202QzMD/A5ETfdvG9/5Mv+PaNEIq6KtE6NGQFkAYEvBG462TkT2J2J6cYry3Bl0sTuPLvXks7CIzNSyxwIQgYkX7/ckTKo9yL1NbC1607ZrfSsLjn4io0ZA7DYRT5IAiOyecHPP8KgaKme2HxgTLePbsi9C7hmJSFxWV//j3ubuQ1v9SDCbjSkBPbOD/LFN4BzlZOfEzfnruFdkfFX6EbNH2BUxv/yvERqfC7mvTDGBqRh0cW8TyXW/sPPp9YGrvCXGBMQLX0Ri1Z4Qmzx3OjWM3mJZJVY8zl5oT6z6gXuZ8K/62USiAIDPigFXINkBszvOc0sTeL9ZfRY/vbSZnQ3rRqrZeLhen/U86/u8ZCv3NIFQjRCOhZDedoa3xpiAeOGLgFhi/0Ep6QC2l37M+gq7cniLic7xdpuvTFnAh8vzITbDUkRoG2sO9HnmhnirKdLu8h2sSjQ652FVGAsxJaCcVGAXIYxCuIKuLN4ahHj+EycUwG4Q7y3z58rd3DKIDm8rHa7eR0drE9mOFDG+4A2ET/loAcHONKWwa7y3cdi3EFMCOpWiwgEhy/JDpioDJTXx3jJfz3iGJhfGuXVk4PAeXxjH/DblvMIWlgVkuijnYfcB2LVnm4+zayCmBASLuu2hUAYmyjr3WbRWv4j1WS/YbJz4WfEWVqyOBNQ/rbMhzn/ywT+j7SzdGCzj/5nI6UjjVybkcWVGlYAg3ifI8BAWcRgfmRtm5a+Uht8oLuflEHuEPJSyQCQr55pPhNiEI6ouCIEIn9hNKKO5vW1sIcm/VmAXwg4JDRIbHHu2lcSzEh1KfSDGR4EACRZ+0bCOLZEYdQLGGrWAilMLqDi1gIpTC6g4tYCKUwuoOLWAilMLqDi1gIpTC6g019D/2KKn4ig7o+YAAAAASUVORK5CYII=" border="0" alt="www.cfca.com.cn" />
                        </a>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#FFFFFF" valign="top" height="2"></td>
                    <td bgcolor="#FFFFFF" valign="top" height="2"></td>
                </tr>
                <tr>
                    <td bgcolor="#48A33A" valign="top" height="2"></td>
                    <td bgcolor="#48A33A" valign="top" height="2"></td>
                </tr>
                <tr>
                    <td bgcolor="#FFFFFF" valign="top" height="8"></td>
                    <td bgcolor="#FFFFFF" valign="top" height="8"></td>
                </tr>
            </table>
            <!--titlebar end-->
        </div>
         
        <div class="remaining">         
            <!--main body start-->
            <table width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td valign="top" width="8"></td>
                    <td valign="top" width="180">
                    
                        <!--left-side start-->
                        <table cellpadding="0" cellspacing="0" border="0" width="180" bgcolor="#E8E8E8">
                            
                            <!-- Perform Document Task -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">文档操作</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <input type="file" id="openfile" accept="application/pdf" style="display:none" onchange="OpenLocalFile(this)">
                                    <div class="fakehlink" onclick="OpenFileDialog()">打开本地文档</div>
                                    <div class="fakehlink" onclick="OpenWebFile()">打开网络文档</div>
                                    <div class="fakehlink" onclick="UploadCurrentFile()">上传文档</div>
                                    <div class="fakehlink" onclick="CloseFile()">关闭文档</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                                                    
                            <!-- Sign Certificate -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">签名证书</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="证书持有者" id="SubjectDN"/></div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="证书颁发者" id="IssuerDN"/></div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="证书序列号" id="CertSN"/></div>
                                    <div class="fakehlink" onclick="SelectSignCert()">选择签名证书</div>
                                    <div class="fakehlink" onclick="GetSignCertInfo()">获取证书信息</div>
                                    <div class="fakehlink" onclick="SetUseExternalSigner()">启用外部签名</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                            
                            <!-- Perform Signature Task -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">文档签名</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="blankline"></div>
                                    <select style="width:160; font-size:9pt" id="AppearanceType">
                                        <option value="invisible">不可见签名</option>
                                        <option value="text">人名章</option>
                                        <option value="handwriting">手写签字</option>
                                        <option value="image" selected="selected">外传图片单次签章</option>
                                        <option value="image_repeat_signing">外传图片连续签章</option>
                                        <option value="image_batch_signing">外传图片批量签章</option>
                                        <option value="keyImage">Key内图片单次签章</option>
                                        <option value="keyImage_repeat_signing">Key内图片连续签章</option>
                                        <option value="keyImage_batch_signing">Key内图片批量签章</option>
                                        <option value="crossPageSeal_left">左骑缝章</option>
                                        <option value="crossPageSeal_right">右骑缝章</option>
                                        <option value="crossPageSeal_right2">右骑缝章(双面打印)</option>
                                        <option value="crossPageSeal_left_right">左右骑缝章</option>
                                        <option value="crossPageSeal_top_bottom">上下骑缝章</option>
                                    </select>
                                    <div class="fakehlink" onclick="TriggerPlaceSignature()">触发签名</div>
                                    <div class="fakehlink" onclick="ResetMouseAction()">重置光标</div>
                                    <div class="fakehlink" onclick="RemoveLastSignature()">移除最后签名</div>
                                    <div class="fakehlink" onclick="RemoveAllSignatures()">移除所有签名</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>

                            <!-- Sign Keyword -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">关键字签章</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="关键字" id="Keyword"/></div>
                                    <div class="fakehlink" onclick="SignKeyword()">关键字签章</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                            
                            <!-- Signature Information -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">签名信息</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="fakehlink" onclick="GetSigFieldInfo()">获取空白签名域</div>
                                    <div class="fakehlink" onclick="GetSignatureInfo()">验证文档签名</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>

                            <!-- Perform Print Task -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">打印操作</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="fakehlink" onclick="GetDefaultPrinter()">获取默认打印机</div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="打印机名" id="PrinterName"/></div>
                                    <div style="padding:5px 0px"> <input type="text" style="width:160" placeholder="打印参数" id="PrintOptions"/></div>
                                    <div class="fakehlink" onclick="PrintFile()">打印文档</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                        
                            <!-- Control Appearance -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">控件外观</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="fakehlink" onclick="ToggleToolbar()">显示/隐藏工具栏</div>
                                    <div class="fakehlink" onclick="ToggleButton_SaveAs()">显示/隐藏"另存为"</div>
                                    <div class="fakehlink" onclick="ToggleButton_Print()">显示/隐藏"打印"</div>
                                    <div class="fakehlink" onclick="EnableSidebar()">启用/禁用侧边栏</div>
                                    <div class="fakehlink" onclick="EnableCueBanner()">启用/禁用签名状态栏</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                            
                            <!-- Perform Extend Screen Task -->
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">扩展屏显示</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="fakehlink" onclick="DuplicateToExtendScreen()">打开扩展屏显示</div>
                                    <div class="fakehlink" onclick="CloseExtendScreen()">关闭扩展屏显示</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                            
                            <!-- Supported Events -->
                            <!--
                            <tr>
                                <td width="10" height="20" bgcolor="#48A33A"></td>
                                <td bgcolor="#48A33A" class="fontSize3" width="170">
                                    <b style="color:white;">支持事件</b>
                                </td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td class="fontSize2" width="170">
                                    <div class="blankline"></div>
                                    <div class="plaintext">NotifyCtrlReady</div>
                                    <div class="plaintext">NotifyDragRect</div>
                                    <div class="plaintext">NotifyClickPoint</div>
                                    <div class="plaintext">NotifyClickField</div>
                                    <div class="blankline"></div>
                                </td>
                            </tr>
                            -->
                        </table>
                        <!--left-side end-->
                    </td>
                    <td valign="top" width="8"></td>
                    <td valign="top">
                        <!--right-side start-->
                        <table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
                        
                            <!-- PDF control window -->
                            <tr>
                                <td valign="top" id="PDFCtrlPlaceHolder" colspan="17"></td>
                            </tr>
                            
                            <!-- HTML toolbar -->
                            <tr class="fontSize3" align="center" height="23" bgcolor="#E8E8E8">
                                <td width="40" class="fakehlink" onclick="GoToFirstPage()">首页</td>
                                <td width="60" class="fakehlink" onclick="GoToPrevPage()">上一页</td>
                                <td width="60" class="fakehlink" onclick="GoToNextPage()">下一页</td>
                                <td width="40" class="fakehlink" onclick="GoToLastPage()">尾页</td>
                                <td width="80" class="fakehlink" onclick="GetPageInfo()">页信息</td>
                                <td width="60" class="fakehlink" onclick="GoToPage()" align="right">跳转到页</td>
                                <td width="60">: <input type="text" id="PageNo" style="width: 45px" onkeydown='if(event.keyCode==13){GoToPage();}' onkeyup="value=this.value.replace(/\D+/g,'')" /></td>
                                <td width="80" class="fakehlink" onclick="GoToBookmark()" align="right">跳转到书签</td>
                                <td width="110">: <input type="text" id="Bookmark" style="width: 98px" onkeydown='if(event.keyCode==13){GoToBookmark();}' /></td>
                                <td width="30"></td>
                                <td width="70" class="fakehlink" onclick="ZoomTo(-2)">适合宽度</td>
                                <td width="70" class="fakehlink" onclick="ZoomTo(-1)">单页模式</td>
                                <td width="30"></td>
                                <td width="45" class="fakehlink" onclick="ZoomOut()">缩小</td>
                                <td width="45" class="fakehlink" onclick="ZoomIn()">放大</td>
                                <td width="60" class="fakehlink" onclick="ZoomValue()">缩放率</td>
                                <td align="left">   
                                    <select id="zoom" onchange="ZoomTo(this.value)">
                                        <option value="6400">6400%</option>
                                        <option value="3200">3200%</option>
                                        <option value="1600">1600%</option>
                                        <option value="800">800%</option>
                                        <option value="600">600%</option>
                                        <option value="400">400%</option>
                                        <option value="300">300%</option>
                                        <option value="200">200%</option>
                                        <option value="150">150%</option>
                                        <option value="100" selected="selected">100%</option>
                                        <option value="75">75%</option>
                                        <option value="50">50%</option>
                                        <option value="25">25%</option>
                                        <option value="12.5">12.5%</option>
                                        <option value="10">10%</option>
                                    </select>
                                </td>
                            </tr>

                        </table>
                        <!--right-side end-->
                    </td>
                    <td valign="top" width="8"></td>
                </tr>
                <tr bgcolor="#FFFFFF" height="10">
                    <td></td>
                </tr>
            </table>
            <!--main body end-->    
        </div>
    </body>
</html>

