/**
 *<p>HtmlTD.java</p>
 *
 *<p>项目名称：银川房产项目</p>
 *<p>系统名称：存量房交易资金监管系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: nankanga099</p>
 *<p>创建日期: 2014-7-17</p>
 *
 */
package com.netcom.nkestate.framework.html;

import java.util.List;

public class HtmlTD {

	private String title; //标题
	private String valuefield; //值域，对应VO对象中的属性名称
	private String style; //
	private String link; //链接，一般只传入JavaScript方法名
	private List<String> linkparam = null; //链接参数 属性名称

	private String DateType = null; //Date, DateTime
	private String formatting = null; //格式化
	private String defaultValue;//设定默认值，判定范围null、""、"null"
	
	private String sortFiled;
	
	private boolean isHidden = false;
	
	/**
	 * @param title
	 * @param valuefield名
	 */
	public HtmlTD(String title, String valuefield) {
		this.title = title;
		this.valuefield = valuefield;
		this.setSortFiled(valuefield);
	}
	
	/**
	 * @param title
	 * @param valuefield名
	 */
	public HtmlTD(String title, String valuefield,boolean isHidden) {
		this.title = title;
		this.valuefield = valuefield;
		this.setHidden(isHidden);
		this.setSortFiled(valuefield);
	}
	

	/**
	 * @param title
	 * @param valuefield名
	 * @param style 样式
	 */
	public HtmlTD(String title, String valuefield, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.style = style;
		this.setSortFiled(valuefield);
	}

	/**
	 * @param title
	 * @param valuefield名
	 * @param style 样式
	 * @param defaultValue 默认值
	 */
	public HtmlTD(String title, String valuefield, String defaultValue, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.defaultValue = defaultValue;
		this.style = style;
		this.setSortFiled(valuefield);
	}

	/**
	 * @param title
	 * @param valuefield名
	 * @param link 链接对应js方法
	 * @param linkparam 链接对应参数
	 * @param style 样式
	 */
	public HtmlTD(String title, String valuefield, String link, List<String> linkparam, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.style = style;
		this.link = link;
		this.linkparam = linkparam;
		this.setSortFiled(valuefield);
	}

	/**
	 * @param title
	 * @param valuefield名
	 * @param link 链接对应js方法
	 * @param linkparam 链接对应参数
	 * @param defaultValue 默认值
	 * @param style 样式
	 */
	public HtmlTD(String title, String valuefield, String link, List<String> linkparam, String defaultValue, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.link = link;
		this.linkparam = linkparam;
		this.defaultValue = defaultValue;
		this.style = style;
		this.setSortFiled(valuefield);
	}

	/**
	 * @param title
	 * @param valuefield 名
	 * @param DateType 是否日期类型
	 * @param formatting 格式化
	 * @param style 样式
	 */
	public HtmlTD(String title, String valuefield, String DateType, String formatting, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.style = style;
		this.DateType = DateType;
		this.formatting = formatting;
		this.setSortFiled(valuefield);
	}

	/**
	 * @param title
	 * @param valuefield 名
	 * @param DateType 是否日期类型
	 * @param formatting 格式化
	 * @param defaultValue 默认值
	 * @param style 样式
	 */
	public HtmlTD(String title, String valuefield, String DateType, String formatting, String defaultValue, String style) {
		this.title = title;
		this.valuefield = valuefield;
		this.DateType = DateType;
		this.formatting = formatting;
		this.defaultValue = defaultValue;
		this.style = style;
		this.setSortFiled(valuefield);
	}
	
	public void disableSort(){
		this.sortFiled = null;
	}
	
	public String getTitle() {
		return title;
	}

	public String getValuefield() {
		return valuefield;
	}

	public String getStyle() {
		return style;
	}

	public String getLink() {
		return link;
	}

	public List<String> getLinkparam() {
		return linkparam;
	}

	public String getDateType() {
		return DateType;
	}

	public String getFormatting() {
		return formatting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setSortFiled(String sortFiled) {
		this.sortFiled = sortFiled;
	}

	public String getSortFiled() {
		return sortFiled;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public boolean isHidden() {
		return isHidden;
	}

}
