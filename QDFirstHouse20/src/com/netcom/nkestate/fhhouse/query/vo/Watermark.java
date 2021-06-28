package com.netcom.nkestate.fhhouse.query.vo;

import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class Watermark extends PdfPageEventHelper {
	
	Font font = null;
	
	private String waterCont;//水印内容 

	public Watermark() {

	}

	public Watermark(String waterCont) {
		this.waterCont = waterCont;
	}


	public void onEndPage(PdfWriter writer,Document document) {
		try{
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			if(this.waterCont != null && !"".equals(this.waterCont)){
				float size = 800 / this.waterCont.length();
				font = new Font(bfChinese, size, Font.BOLD, new GrayColor(0.85f));
			}else{
				font = new Font(bfChinese, 80, Font.BOLD, new GrayColor(0.85f));
			}

		}catch (DocumentException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}

		ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_CENTER, new Phrase(this.waterCont == null ? "hello world" : this.waterCont, font), 297.5f, 421, writer.getPageNumber() % 2 == 1 ? 57 : -57);
		/*图片水印
		PdfContentByte d = new PdfContentByte(writer);
		try{
			Image image = Image.getInstance("g:\\2.jpg");
			image.setAbsolutePosition(-200, -100);
			image.setBorderColor(Color.blue);
			d.addImage(image);
		}catch (Exception e){
			e.printStackTrace();
		}
		 */
	}

}
