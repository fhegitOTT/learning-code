/**
 *<p>TableTitle.java</p>
 *
 *<p>项目名称：银川房产项目</p>
 *<p>系统名称：存量房交易资金监管系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2014-7-15</p>
 *
 */
package com.netcom.nkestate.framework.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableProperty {
	int columnCount = 0;
	
	private List<HtmlTD> columns = new ArrayList<HtmlTD>();
	
	private String tableStyle = null; //表格Style
	private String rowStyle = null; // 行Style
	private String cellStyle = null; //单元格Style
	
	private String controlType = null; //控件类型checkbox、radio
	private String controlName; //checkbox名称
	private List<String> controlValueField; //checkbox的值域	
	
	private List<Rule> showControlRule;
	
	private String controlStyle; //checkbox的样式	

	private String sortField = null; //排序字段
	private String sortType = null; //NULL，Asc, DESC

	private boolean rowIndexStauts = false;//序列号

	private String bodyDivHeight;//数据列div高度

	private String trEventType;//单双击事件
	private String trEventName;//事件名称
	private List<String> trEventParams;//参数

	private Integer valueMaxLength;//设定显示长度，超出部分显示...

	private Map<String , Map<String , Object>> fieldAliasMap = null;//值别名map集合   如：1 男  0 女    map中存key为字段名，value为别名map

	private String tableID;

	private String tableName;
	
	private boolean enableSort = true;

	/**
	 * 功能描述：获取列集合
	 * @return
	 */
	public List getColumns() {
		return columns;
	}

	/**
	 * 功能描述：获取表格样式
	 * @return
	 */
	public String getTableStyle() {
		return tableStyle;
	}

	/**
	 * 功能描述：获取行样式
	 * @return
	 */
	public String getRowStyle() {
		return rowStyle;
	}

	/**
	 * 功能描述：获取列样式
	 * @return
	 */
	public String getCellStyle() {
		return cellStyle;
	}

	/**
	 * 功能描述：获取排序字段
	 * @return
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * 功能描述：获取排序类型
	 * @return
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 功能描述：获取控件类型
	 * @return
	 */
	public String getControlType() {
		return controlType;
	}

	/**
	 * 功能描述：获取控件名
	 * @return
	 */
	public String getControlName() {
		return controlName;
	}

	/**
	 * 功能描述：获取控件值
	 * @return
	 */
	public List<String> getControlValueField() {
		return controlValueField;
	}

	/**
	 * 功能描述：控件样式
	 * @return
	 */
	public String getControlStyle() {
		return controlStyle;
	}

	/**
	 * 功能描述：设置表格的Style
	 * @param tableStyle
	 */
	public void setTableStyle(String tableStyle) {
		this.tableStyle = tableStyle;
	}

	/**
	 * 功能描述：设置行的Style
	 * @param rowStyle
	 */
	public void setRowStyle(String rowStyle) {
		this.rowStyle = rowStyle;
	}

	/**
	 * 功能描述：设置列的Style
	 * @param cellStyle
	 */
	public void setCellStyle(String cellStyle) {
		this.cellStyle = cellStyle;
	}

	/**
	 * 功能描述：设置排序字段
	 * @param sortField
	 * @param sortType
	 */
	public void setSort(String sortField,String sortType) {
		this.sortField = sortField;
		this.sortType = sortType;
	}

	/**
	 * 功能描述：获取序号显示状态,true显示序号,false不显示序号,默认不显示
	 * @return
	 */
	public boolean isRowIndexStauts() {
		return rowIndexStauts;
	}

	/**
	 * 功能描述：设置序号显示状态,true显示序号,false不显示序号,默认不显示
	 * @return
	 */
	public void setRowIndexStauts(boolean rowIndexStauts) {
		this.rowIndexStauts = rowIndexStauts;
	}

	/**
	 * 功能描述：获取数据列显示高度
	 * @return
	 */
	public String getBodyDivHeight() {
		return bodyDivHeight;
	}

	/**
	 * 功能描述：根据设置数据列DIV显示高度，有初始默认高度
	 * @param bodyDivHeight
	 */
	public void setBodyDivHeight(String bodyDivHeight) {
		this.bodyDivHeight = bodyDivHeight;
	}

	/**
	 * 功能描述：获取tr中添加的事件类型
	 * @return
	 */
	public String getTrEventType() {
		return trEventType;
	}

	/**
	 * 功能描述：设定tr中的事件类型
	 * @param trEventType
	 */
	public void setTrEventType(String trEventType) {
		this.trEventType = trEventType;
	}

	/**
	 * 功能描述：获取事件方法名称
	 * @return
	 */
	public String getTrEventName() {
		return trEventName;
	}

	/**
	 * 功能描述：设定事件方法名称
	 * @param trEventName
	 */
	public void setTrEventName(String trEventName) {
		this.trEventName = trEventName;
	}

	/**
	 * 功能描述：获取事件参数
	 * @return
	 */
	public List<String> getTrEventParams() {
		return trEventParams;
	}

	/**
	 * 功能描述：获取事件参数
	 * @param trParamList
	 */
	public void setTrEventParams(List<String> trEventParams) {
		this.trEventParams = trEventParams;
	}

	/**
	 * 功能描述：获取显示长度
	 * @return
	 */
	public Integer getValueMaxLength() {
		return valueMaxLength;
	}

	/**
	 * 功能描述：设置字符串显示长度（全部的字段）
	 * @param valueMaxLength
	 */
	public void setValueMaxLength(Integer valueMaxLength) {
		this.valueMaxLength = valueMaxLength;
	}

	/**
	 * 功能描述：获取别名的map集合
	 * @return
	 */
	public Map<String , Map<String , Object>> getFieldAliasMap() {
		return fieldAliasMap;
	}

	/**
	 * 功能描述：设置别名map集合
	 * @param fieldAliasMap
	 */
	public void setFieldAliasMap(Map<String , Map<String , Object>> fieldAliasMap) {
		this.fieldAliasMap = fieldAliasMap;
	}

	/**
	 * 功能描述：设置tableID
	 * @return
	 */
	public String getTableID() {
		return tableID;
	}


	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	/**
	 * 功能描述：设置tableName
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 功能描述：在第一列增加选择控件CheckBox、radio
	 * @param controlType 控件类型radio,checkbox
	 * @param controlName 名称
	 * @param valueField 值域，对应VO的属性
	 */
	public void addSelectControl(String controlType,String controlName,List<String> controlValueField,String controlStyle) {
		this.controlType = controlType;
		this.controlName = controlName;
		this.controlValueField = controlValueField;
		this.controlStyle = controlStyle;
	}

	/**
	 * 功能描述：在第一列增加选择控件CheckBox、radio
	 * @param controlType
	 * @param controlName
	 * @param controlValueField
	 * @param controlStyle
	 */
	public void addSelectControl(String controlType,String controlName,String controlValueField,String controlStyle) {
		this.controlType = controlType;
		this.controlName = controlName;
		this.controlValueField = new ArrayList<String>();
		this.controlValueField.add(controlValueField);
		this.controlStyle = controlStyle;
	}
	
	
	/**
	 * 功能描述：在第一列增加选择控件CheckBox、radio
	 * @param controlType 控件类型radio,checkbox
	 * @param controlName 名称
	 * @param valueField 值域，对应VO的属性
	 */
	public void addSelectControl(String controlType,String controlName,List<String> controlValueField,String controlStyle,List<Rule> showControlRule) {
		this.controlType = controlType;
		this.controlName = controlName;
		this.controlValueField = controlValueField;
		this.controlStyle = controlStyle;
		this.setShowControlRule(showControlRule);
	}

	
	

	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 */
	public HtmlTD addColumn(String title,String field) {
		HtmlTD td = new HtmlTD(title, field);
		columns.add(td);
		return td;
	}
	
	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 */
	public HtmlTD addColumn(String title,String field,boolean isHidden) {
		HtmlTD td = new HtmlTD(title, field, isHidden);
		columns.add(td);
		return td;
	}
	
	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param style
	 * 样式
	 */
	public HtmlTD addColumn(String title,String field,String style) {
		HtmlTD td = new HtmlTD(title, field, style);
		columns.add(td);
		return td;
	}

	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield
	 * 名
	 * @param style
	 * 样式
	 * @param defaultValue
	 * 默认值
	 */
	public HtmlTD addColumn(String title,String field,String defaultValue,String style) {
		HtmlTD td = new HtmlTD(title, field, defaultValue, style);
		columns.add(td);
		return td;
	}

	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param link
	 * 链接对应js方法
	 * @param linkparam
	 * 链接对应参数
	 */
	public HtmlTD addColumn(String title,String field,String link,List<String> linkparam) {
		HtmlTD td = new HtmlTD(title, field, link, linkparam, null);
		columns.add(td);
		return td;
	}
	
	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param link
	 * 链接对应js方法
	 * @param linkparam
	 * 链接对应参数
	 * @param style
	 * 样式
	 */
	public HtmlTD addColumn(String title,String field,String link,List<String> linkparam,String style) {
		HtmlTD td = new HtmlTD(title, field, link, linkparam, style);
		columns.add(td);
		return td;
	}
	
	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param DateType
	 * 是否日期类型
	 * @param formatting
	 * 格式化
	 * @param style
	 * 样式
	 */
	public HtmlTD addColumn(String title,String field,String DateType,String formatting,String style) {
		HtmlTD td = new HtmlTD(title, field, DateType, formatting, style);
		columns.add(td);
		return td;
	}

	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param link
	 * 链接对应js方法
	 * @param linkparam
	 * 链接对应参数
	 * @param defaultValue
	 * 默认值
	 * @param style
	 * 样式
	 */
	public HtmlTD addColumn(String title,String field,String link,List<String> linkparam,String defaultValue,String style) {
		HtmlTD td = new HtmlTD(title, field, link, linkparam, defaultValue, style);
		columns.add(td);
		return td;
	}

	/**
	 * 功能描述：创建列对象
	 * @param title
	 * @param valuefield名
	 * @param DateType
	 * 是否日期类型
	 * @param formatting
	 * 格式化
	 * @param defaultValue
	 * 默认值
	 * @param style
	 * 样式
	 */
	public HtmlTD addColumn(String title,String field,String DateType,String formatting,String defaultValue,String style) {
		HtmlTD td = new HtmlTD(title, field, DateType, formatting, defaultValue, style);
		columns.add(td);
		return td;
	}

	public void setShowControlRule(List<Rule> showControlRule) {
		this.showControlRule = showControlRule;
	}

	public List<Rule> getShowControlRule() {
		return showControlRule;
	}

	public void setEnableSort(boolean enableSort) {
		this.enableSort = enableSort;
	}

	public boolean isEnableSort() {
		return enableSort;
	}


}

