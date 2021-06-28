/**
 *<p>HtmlTable.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2014-2-8</p>
 *
 */
package com.netcom.nkestate.framework.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.netcom.nkestate.framework.Page;

public class HtmlTableUtil {

	/**
	 * 功能描述：创建HTML表格，不带分页
	 * @param tableProperty
	 * 表对象
	 * @param data
	 * 数据集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createHtmlTable(TableProperty tableProperty,List data) throws Exception {

		// 获取列表column集合
		List listColumns = tableProperty.getColumns();

		StringBuffer sb = new StringBuffer();

		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		sb.append(headHtml);
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</table>\n");
		
		String sortField = tableProperty.getSortField() == null ? ""
 : tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType() == null ? ""
 : tableProperty.getSortType(); // NULL，Asc, DESC
		sb.append("<input type=\"hidden\" id=\"SortType\" name=\"SortType\" value=\""
 + sortType + "\"/>\n");// 排序方式
		sb.append("<input type=\"hidden\" id=\"SortField\" name=\"SortField\" value=\""
 + sortField + "\"/>\n");// 排序的列名
		return sb.toString();
	}

	/**
	 * 功能描述：创建HTML表格，带分页
	 * @param tableProperty
	 * 表对象
	 * @param data
	 * 数据集合
	 * @param page
	 * 分页对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createHtmlTable(TableProperty tableProperty,List data,Page page) throws Exception {

		// 获取列表column集合
		List listColumns = tableProperty.getColumns();

		StringBuffer sb = new StringBuffer();

		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		sb.append(headHtml);
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</table>\n");
		// 拼接分页字符串并返回
		String pageHtml = getPageHtml(tableProperty, page);
		sb.append(pageHtml);
		
		return sb.toString();
	}

	/**
	 * 功能描述：创建HTML表格，带分页
	 * @param tableProperty
	 * 表对象
	 * @param data
	 * 数据集合
	 * @param page
	 * 分页对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createHtmlTableForEasyUI(TableProperty tableProperty,List data,Page page) throws Exception {

		// 获取列表column集合
		List listColumns = tableProperty.getColumns();

		StringBuffer sb = new StringBuffer();

		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		sb.append(headHtml);
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</table>\n");
		// 拼接分页字符串并返回
		String pageHtml = getPageHtmlForEasyUI(tableProperty, page);
		sb.append(pageHtml);
		
		return sb.toString();
	}
	
	/**
	 * 功能描述：创建HTML表格，带分页
	 * @param tableProperty
	 * 表对象
	 * @param data
	 * 数据集合
	 * @param page
	 * 分页对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createHtmlTableForEasyUI(TableProperty tableProperty,List data,Page page, String contractID) throws Exception {

		// 获取列表column集合
		List listColumns = tableProperty.getColumns();

		StringBuffer sb = new StringBuffer();

		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		sb.append(headHtml);
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</table>\n");
		// 拼接分页字符串并返回
		String pageHtml = getPageHtmlForEasyUI(tableProperty, page, contractID);
		sb.append(pageHtml);
		
		return sb.toString();
	}

	/**
	 * 功能描述：添加表头
	 * @param sb
	 * @param tableProperty
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String getHeadHtml(TableProperty tableProperty,List listColumns) throws Exception {
		StringBuffer sb = new StringBuffer();

		String tableStyle = tableProperty.getTableStyle() == null ? "class='default_tableCss'" : tableProperty.getTableStyle(); // 获得表样式
		String rowStyle = tableProperty.getRowStyle() == null ? "class='default_row_tr'" : tableProperty.getRowStyle(); // 获得行样式
		String controlType = tableProperty.getControlType(); // 控件类型checkbox、radio
		String controlName = tableProperty.getControlName(); // checkbox名称
		String controlStyle = tableProperty.getControlStyle() == null ? "style='width:5%;'" : tableProperty.getControlStyle();// 控件的样式
		String sortField = tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType(); // NULL，Asc, DESC
		boolean rowIndexStatus = tableProperty.isRowIndexStauts();// 是否显示序号列
		String tableID = tableProperty.getTableID() == null ? "" : "id=\"" + tableProperty.getTableID() + "\"";
		String tableName = tableProperty.getTableID() == null ? "" : "name=\"" + tableProperty.getTableName() + "\"";

		sb.append("<table " + tableID + " " + tableName + " " + tableStyle + ">\n");
		sb.append("<tr " + rowStyle + " >\n");

		// 判断控件信息
		// 判断多选框
		if(controlType != null && controlType.equalsIgnoreCase("checkbox")){
			sb.append("<th " + controlStyle + " >");
			sb.append("<input type='" + controlType + "' name='" + controlName + "' onclick='checkAll(this)'/>全选");
			sb.append("</th>\n");
		}
		// 判断单选框
		if(controlType != null && controlType.equalsIgnoreCase("radio")){
			sb.append("<th " + controlStyle + " >");
			sb.append("单选");
			sb.append("</th>\n");
		}

		// 判断序号显示
		if(rowIndexStatus){
			sb.append("<th " + controlStyle + " >");
			sb.append("序号");
			sb.append("</th>\n");
		}

		// 循环表头数据
		for(int i = 0; i < listColumns.size(); i++){
			HtmlTD htmlTD = (HtmlTD) listColumns.get(i);
			if(htmlTD.isHidden()){
				sb.append("<th style='display:none' />");
				break;
			}
			String filedName = htmlTD.getValuefield();
			// 默认10%的布局十分有问题，自适应即可
			//String tdStyle = htmlTD.getStyle() == null ? "style='width:10%'" : htmlTD.getStyle();
			//tdStyle = tdStyle.equals("") ? "style='width:10%'" : tdStyle;
			String tdStyle = htmlTD.getStyle() == null ? "" : htmlTD.getStyle();
			
			// 判断是否有排序
			if(!tableProperty.isEnableSort() || htmlTD.getSortFiled() == null){
				sb.append("<th " + tdStyle + ">");
			}else{
				sb.append("<th class='sortable' " + tdStyle + " onclick=\"getSort('" + htmlTD.getSortFiled() + "')\">");
			}
			sb.append(htmlTD.getTitle());
			if(sortField != null && sortField.equalsIgnoreCase(htmlTD.getSortFiled())){
				// 判断是否排序，并添加↓、↑
				if(sortType != null && sortType.equalsIgnoreCase("desc")){
					sb.append("▽");
				}else if(sortType != null && sortType.equalsIgnoreCase("asc")){
					sb.append("△");
				}
			}
			sb.append("</th>\n");
		}
		sb.append("</tr>\n");
		
		return sb.toString();
	}

	/**
	 * 功能描述：添加数据列html
	 * @param sb
	 * @param tableProperty
	 * @param listColumns
	 * 列集合
	 * @param jsonArray
	 * 数据集合
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getBodyHtml(TableProperty tableProperty,List listColumns,List data) throws Exception {
		StringBuffer sb = new StringBuffer();
		String controlType = tableProperty.getControlType(); // 控件类型checkbox、radio
		String controlName = tableProperty.getControlName(); // checkbox名称
		List<String> controlValueField = tableProperty.getControlValueField(); // checkbox的值域
		List<Rule> showControlRule = tableProperty.getShowControlRule(); // checkbox的值域

		boolean rowIndexStatus = tableProperty.isRowIndexStauts();// 是否显示序号列
		Integer valueMaxLength = tableProperty.getValueMaxLength();// 字符串显示最大长度
		Map<String , Map<String , Object>> fieldAliasMap = tableProperty.getFieldAliasMap();// 根据字段获取别名

		int rowIndex = 1;

		if(data == null || data.size() < 1){
			return "";
		}

		for(int i = 0; i < data.size(); i++){
			Object vo = data.get(i);
			String value = getControlValue(controlValueField, vo);
			// 针对行，判断是否添加单双击事件
			String trHtml = getTrEventParamHtml(tableProperty, vo);
			
			boolean canShow = isShow(tableProperty.getShowControlRule(),vo);
			
			sb.append(trHtml);

			if(controlType != null && controlType.equalsIgnoreCase("checkbox")){
				sb.append("<td>");
				if(canShow){
					sb.append("<input type='" + controlType + "' id='" + controlName + "' name='" + controlName + "' value='" + value + "'/>");
				}
				sb.append("</td>\n");
			}
			if(controlType != null && controlType.equalsIgnoreCase("radio")){
				sb.append("<td>");
				if(canShow){
					sb.append("<input type='" + controlType + "' name='" + controlName + "' value='" + value + "'>");
				}
				sb.append("</td>\n");
			}

			// 判断序号显示
			if(rowIndexStatus){
				sb.append("<td>");
				sb.append(rowIndex);
				sb.append("</td>\n");
				rowIndex++;
			}

			// 获取列
			String columHtml = getColumnsValue(listColumns, vo, valueMaxLength, fieldAliasMap);
			sb.append(columHtml);
			sb.append("</tr>\n");
		}

		return sb.toString();
	}

	/**
	 * 功能描述：添加page分页html
	 * @param sb
	 * @param tableProperty
	 * @param page
	 */
	private static String getPageHtml(TableProperty tableProperty,Page page) throws Exception {
		StringBuffer sb = new StringBuffer();
		String tableStyle = tableProperty.getTableStyle() == null ? "class='default_tableCss'" : tableProperty.getTableStyle(); // 获得表样式
		String rowStyle = tableProperty.getRowStyle() == null ? "class='default_row_tr'" : tableProperty.getRowStyle(); // 获得行样式
		String sortField = tableProperty.getSortField() == null ? "" : tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType() == null ? "" : tableProperty.getSortType(); // NULL，Asc, DESC

		sb.append("<table " + tableStyle + ">\n");
		sb.append("<tr " + rowStyle + ">\n");
		sb.append("<td width='55%' align=\"left\">");
		sb.append("第" + page.getCurrentPage() + "页 ");
		sb.append("(共" + page.getPageCount() + "页," + page.getRecordCount() + "条记录)");
		if(page.hasPre()){
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(1)'>首页</a> ");
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(" + (page.getCurrentPage() - 1) + ")'>上页</a> ");
		}else{
			sb.append(" 首页 ");
		}
		sb.append(" | ");
		if(page.hasNext()){
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(" + (page.getCurrentPage() + 1) + ")' >下页</a> ");
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(" + page.getPageCount() + ")' >末页</a> ");
		}else{
			sb.append(" 末页 ");
		}
		sb.append("</td>\n");
		sb.append("<td width='45%' align=\"right\">");
		sb.append(" 每页 ");
		sb.append(" <input type='text' id=\"PageSize\" name=\"PageSize\" size='1' maxlength='3' onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value='"
						+ page.getPageSize() + "'/> ");
		sb.append(" 条 ");
		sb.append(" 转到 ");
		sb.append(" <input type=\"text\" id=\"CurrentPage\" name=\"CurrentPage\" size='1' maxlength='3' ");
		sb.append(" onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value=\"" + page.getCurrentPage() + "\"/> ");
		sb.append(" 页 ");
		sb.append("<input class='Control' type='button' value='转到' onclick='GoToPage()'>");
		sb.append("</td>\n");
		sb.append("</tr>\n");

		// ------------------------------隐藏分页属性信息及其他-------------------------------------
		sb.append("<input type=\"hidden\" id=\"RecordCount\" name=\"RecordCount\" value=\"" + page.getRecordCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"PageCount\" name=\"PageCount\" value=\"" + page.getPageCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"SortType\" name=\"SortType\" value=\"" + sortType + "\"/>\n");// 排序方式
		sb.append("<input type=\"hidden\" id=\"SortField\" name=\"SortField\" value=\"" + sortField + "\"/>\n");// 排序的列名
		sb.append("</table>\n");

		return sb.toString();
	}

	/**
	 * 功能描述：添加page分页html
	 * @param sb
	 * @param tableProperty
	 * @param page
	 */
	private static String getPageHtmlForEasyUI(TableProperty tableProperty,Page page,String contractID) throws Exception {
		StringBuffer sb = new StringBuffer();
		String sortField = tableProperty.getSortField() == null ? "" : tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType() == null ? "" : tableProperty.getSortType(); // NULL，Asc, DESC

		sb.append("<div class='datagrid-pager pagination'>\n");
		sb.append("<table border='0' cellspacing='0' cellpadding='0'><tbody><tr>\n");
		
		if(page.hasPre()){
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0)' onclick='nextPage(1)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-first'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0)' onclick='nextPage(" + (page.getCurrentPage() - 1) + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-prev'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
		}else{
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-first'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-prev'>&nbsp;</span>");
			sb.append("</span></a></td>");
		}
		sb.append("<td><div class='pagination-btn-separator'></div></td>");
		if(page.hasNext()){
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='nextPage(" + (page.getCurrentPage() + 1) + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-next'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='nextPage(" + page.getPageCount() + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-last'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
		}else{
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-next'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-last'>&nbsp;</span>");
			sb.append("</span></a></td>");
		}
		sb.append("<td><div class='pagination-btn-separator'></div></td>");
		
		sb.append("<td><span style='padding-left: 6px;'>每页</span></td>");
		sb.append("<td><input type='text' id='PageSize' name='PageSize' size='1' maxlength='3' onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value='"+page.getPageSize() + "'/></td>");
		sb.append("<td><span style='padding-right: 6px;'>条</span></td>");
		sb.append("<td><span style='padding-left: 6px;'>转到</span></td>");
		sb.append("<td>");
		sb.append(" <input type=\"text\" id=\"CurrentPage\" name=\"CurrentPage\" size='1' maxlength='3' ");
		sb.append(" onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value=\"" + page.getCurrentPage() + "\"/> ");
		sb.append("</td>");
		sb.append("<td><span style='padding-right: 6px;'>页</span></td>");
		
		sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='GoToPage()' group=''> ");
		sb.append("<span class='l-btn-left l-btn-icon-left'>");
		sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
		sb.append("<span class='l-btn-icon icon-search'>&nbsp;</span>");
		sb.append("</span></a></td>");
		
		sb.append("</tr></tbody></table>");
		sb.append("<div class='pagination-info'>第" + page.getCurrentPage() + "页(共" + page.getPageCount() + "页," + page.getRecordCount() + "条记录)</div><div style='clear: both;'></div>");

		// ------------------------------隐藏分页属性信息及其他-------------------------------------
		sb.append("<input type=\"hidden\" id=\"RecordCount\" name=\"RecordCount\" value=\"" + page.getRecordCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"PageCount\" name=\"PageCount\" value=\"" + page.getPageCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"SortType\" name=\"SortType\" value=\"" + sortType + "\"/>\n");// 排序方式
		sb.append("<input type=\"hidden\" id=\"SortField\" name=\"SortField\" value=\"" + sortField + "\"/>\n");// 排序的列名
		sb.append("<input type=\"hidden\" id=\"contractID\" name=\"contractID\" value=\"" + contractID + "\"/>\n");//合同号
		sb.append("</div>\n");

		return sb.toString();
	}

	/**
	 * 功能描述：添加page分页html
	 * @param sb
	 * @param tableProperty
	 * @param page
	 */
	private static String getPageHtmlForEasyUI(TableProperty tableProperty,Page page) throws Exception {
		StringBuffer sb = new StringBuffer();
		String sortField = tableProperty.getSortField() == null ? "" : tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType() == null ? "" : tableProperty.getSortType(); // NULL，Asc, DESC

		sb.append("<div class='datagrid-pager pagination'>\n");
		sb.append("<table border='0' cellspacing='0' cellpadding='0'><tbody><tr>\n");
		
		if(page.hasPre()){
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0)' onclick='nextPage(1)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-first'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0)' onclick='nextPage(" + (page.getCurrentPage() - 1) + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-prev'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
		}else{
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-first'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-prev'>&nbsp;</span>");
			sb.append("</span></a></td>");
		}
		sb.append("<td><div class='pagination-btn-separator'></div></td>");
		if(page.hasNext()){
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='nextPage(" + (page.getCurrentPage() + 1) + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-next'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='nextPage(" + page.getPageCount() + ")' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-last'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
		}else{
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-next'>&nbsp;</span>");
			sb.append("</span></a></td>");
			
			sb.append("<td><a class='l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled' href='javascript:void(0)' group=''> ");
			sb.append("<span class='l-btn-left l-btn-icon-left'>");
			sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
			sb.append("<span class='l-btn-icon pagination-last'>&nbsp;</span>");
			sb.append("</span></a></td>");
		}
		sb.append("<td><div class='pagination-btn-separator'></div></td>");
		
		sb.append("<td><span style='padding-left: 6px;'>每页</span></td>");
		sb.append("<td><input type='text' id='PageSize' name='PageSize' size='1' maxlength='3' onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value='"+page.getPageSize() + "'/></td>");
		sb.append("<td><span style='padding-right: 6px;'>条</span></td>");
		sb.append("<td><span style='padding-left: 6px;'>转到</span></td>");
		sb.append("<td>");
		sb.append(" <input type=\"text\" id=\"CurrentPage\" name=\"CurrentPage\" size='1' maxlength='3' ");
		sb.append(" onkeyup='check_NaN(this);keySubmit(event);' onkeydown='check_NaN(this);keySubmit(event);' value=\"" + page.getCurrentPage() + "\"/> ");
		sb.append("</td>");
		sb.append("<td><span style='padding-right: 6px;'>页</span></td>");
		
		sb.append("<td><a class='l-btn l-btn-small l-btn-plain ' href='javascript:void(0);' onclick='GoToPage()' group=''> ");
		sb.append("<span class='l-btn-left l-btn-icon-left'>");
		sb.append("<span class='l-btn-text l-btn-empty'>&nbsp;</span>");
		sb.append("<span class='l-btn-icon icon-search'>&nbsp;</span>");
		sb.append("</span></a></td>");
		
		sb.append("</tr></tbody></table>");
		sb.append("<div class='pagination-info'>第" + page.getCurrentPage() + "页(共" + page.getPageCount() + "页," + page.getRecordCount() + "条记录)</div><div style='clear: both;'></div>");

		// ------------------------------隐藏分页属性信息及其他-------------------------------------
		sb.append("<input type=\"hidden\" id=\"RecordCount\" name=\"RecordCount\" value=\"" + page.getRecordCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"PageCount\" name=\"PageCount\" value=\"" + page.getPageCount() + "\"/>\n");
		sb.append("<input type=\"hidden\" id=\"SortType\" name=\"SortType\" value=\"" + sortType + "\"/>\n");// 排序方式
		sb.append("<input type=\"hidden\" id=\"SortField\" name=\"SortField\" value=\"" + sortField + "\"/>\n");// 排序的列名
		sb.append("</div>\n");

		return sb.toString();
	}
	
	/**
	 * 功能描述：判断是否是隐藏字段
	 * @param title
	 * @param unShowFields
	 * @return
	 * @throws Exception
	 */
	private static boolean isUnShowFiled(String title,List<String> unShowFields) throws Exception {
		boolean isUnShowField = true;
		for(int j = 0; j < unShowFields.size(); j++){
			String filedName = unShowFields.get(j);
			if(title.equalsIgnoreCase(filedName)){
				isUnShowField = false;
				break;
			}
		}
		return isUnShowField;
	}

	/**
	 * 功能描述：对行进行判断，是否添加单双击事件，返回tr字符串
	 * @param tableProperty
	 * @param jobj
	 * @param listColumns
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static String getTrEventParamHtml(TableProperty tableProperty,Object vo)
			throws Exception {
		String rowStyle = tableProperty.getRowStyle() == null ? "class='default_row_tr'" : tableProperty.getRowStyle(); // 获得行样式
		String trEventType = tableProperty.getTrEventType();
		List<String> trEventParams = tableProperty.getTrEventParams();
		String trEventName = tableProperty.getTrEventName();

		StringBuffer sb = new StringBuffer();
		if(trEventType != null && trEventName != null){
			if(trEventType.equalsIgnoreCase("onclick")){
				sb.append("<tr " + rowStyle + " onclick=\"changeColor(this);" + trEventName + "(");
				if(trEventParams != null && !trEventParams.isEmpty()){
					String paramStr = getParamStr(trEventParams, vo);
					if(!paramStr.equals("")){
						sb.append(paramStr);
					}
				}
				sb.append(")\">\n");
			}else if(trEventType.equalsIgnoreCase("ondblclick")){
				sb.append("<tr " + rowStyle + " onclick=\"changeColor(this)\" ondblclick=\"" + trEventName + "(");
				if(trEventParams != null && !trEventParams.isEmpty()){
					String paramStr = getParamStr(trEventParams, vo);
					if(!paramStr.equals("")){
						sb.append(paramStr);
					}
				}
				sb.append(")\">\n");
			}
		}else{
			sb.append("<tr " + rowStyle + " onclick=\"changeColor(this)\" >\n");
		}

		return sb.toString();
	}

	/**
	 * 功能描述：对List参数循环获取拼接 多个参数做处理 "'a','b','c'"
	 * @param param
	 * @param jobj
	 * @param listColumns
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static String getParamStr(List<String> trEventParams,Object vo) throws Exception {
		String paramStr = "";
		for(int i = 0; i < trEventParams.size(); i++){
			if(trEventParams.size() == 1){
				String fieldName = trEventParams.get(i);
				String fieldValue = ClassSupport.getVOValue(vo, fieldName);
				paramStr = "'" + fieldValue + "'";
				break;
			}
			String fieldName = trEventParams.get(i);
			String fieldValue = ClassSupport.getVOValue(vo, fieldName);
			String value = "";
			value = "'" + fieldValue + "'";
			paramStr += value;
			if(!value.equals("") && i < trEventParams.size() - 1){
				paramStr += ",";
			}
		}

		return paramStr;
	}

	/**
	 * 功能描述：根据参数拼接列数据
	 * @param sb
	 * @param listColumns
	 * @param jobj
	 * @param baseVO
	 * @param isBaseVo
	 */
	@SuppressWarnings("unchecked")
	private static String getColumnsValue(List listColumns,Object vo,Integer valueMaxLength,Map<String , Map<String , Object>> fieldAliasMap) throws Exception {

		StringBuffer sb = new StringBuffer();
		// 循环列数据
		for(int j = 0; j < listColumns.size(); j++){
			HtmlTD htmlTD = (HtmlTD) listColumns.get(j);
			String filedName = htmlTD.getValuefield();
			String filedValue = String.valueOf(getFieldValue(vo, htmlTD));

			// 获取别名的值
			if(fieldAliasMap != null && fieldAliasMap.containsKey(filedName)){
				Map<String , Object> map = fieldAliasMap.get(filedName);
				filedValue = map.get(filedValue) == null ? "" : String.valueOf(map.get(filedValue));
			}
			
			if(htmlTD.isHidden()){
				sb.append("<td style='display:none'><input type='hidden' name='"+filedName+"' value='"+filedValue+"' /></td>");
			}else{
				String filedValueSubString = "";
				boolean isSub = false;
				if(valueMaxLength!=null){
					int valueLength = filedValue.length();
					if(valueLength > valueMaxLength){
						filedValueSubString = filedValue.substring(0, valueMaxLength)+"...";
						sb.append("<td title='" + filedValue + "'>");
						isSub = true;
					}else{
						sb.append("<td>");
					}
				}else{
					sb.append("<td>");
				}
				
				// 判断取列的link字符串
				if(htmlTD.getLink() != null){
					String linkStr = getLink(vo, listColumns, htmlTD);
					sb.append(linkStr);
				}

				if(isSub){
					sb.append(filedValueSubString);
				}else{
					sb.append(filedValue);
				}

				// 添加link
				if(htmlTD.getLink() != null){
					sb.append("</a>");
				}
				sb.append("</td>\n");
			}
			
			
		}
		return sb.toString();
	}

	/**
	 * 功能描述：判断取列的link字符串
	 * @param jobj
	 * @param htmlTD
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String getLink(Object vo,List listColumns,HtmlTD htmlTD) throws Exception {
		List<String> listFiled = htmlTD.getLinkparam();
		String param = "";
		for(int k = 0; k < listFiled.size(); k++){
			if(k > 0){
				param += ",";
			}
			String fieldName = listFiled.get(k);
			HtmlTD htmlTD2 = getHtmlTD(listColumns, fieldName);
			String str = "";
			if(htmlTD2 == null)
			{
				str = ClassSupport.getVOValue(vo, fieldName);
			}else
			{
				str = getFieldValue(vo, htmlTD2);
			}

			param += "'" + str + "'";
		}
		String linkStr = "<a onclick=\"" + htmlTD.getLink() + "(" + param + ")\" href=\"javascript:void(0);\" >";
		return linkStr;
	}

	/**
	 * 功能描述：判断取列值
	 * @param jobj
	 * @param htmlTD
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 */
	private static String getFieldValue(Object vo,HtmlTD htmlTD) throws Exception {
		String fieldName = htmlTD.getValuefield();// 获取列名
		String formatting = htmlTD.getFormatting();
		String fieldValue = "";
		String defaultValue = htmlTD.getDefaultValue() == null ? "" : htmlTD.getDefaultValue();
		if(formatting != null){
			fieldValue = ClassSupport.getVOValue(vo, fieldName, formatting, defaultValue);
		}else{
			fieldValue = ClassSupport.getVOValue(vo, fieldName, defaultValue);
		}

		if(fieldValue == null || "".equals(fieldValue)){
			fieldValue = getCalculateFieldValue(fieldName, vo, defaultValue);
		}

		return fieldValue;// 获取列值
	}

	// 将<%=filename%>替换为真实的值
	private static String getCalculateFieldValue(String fieldName,Object vo,String defaultValue) throws Exception {
		String prefix = "<%=";
		String suffix = "%>";
		String fieldName2 = fieldName;
		int start = fieldName2.indexOf(prefix);
		int end = fieldName2.indexOf(suffix);

		if(! (start >= 0 && end >= 0))
			return defaultValue == null ? "" : defaultValue;

		while (start >= 0 && end >= 0){
			String field = fieldName2.substring(start + prefix.length(), end);
			String fieldValue = ClassSupport.getVOValue(vo, field, defaultValue);

			fieldName2 = fieldName2.replace(prefix + field + suffix, fieldValue);

			start = fieldName2.indexOf(prefix, start + 1);
			end = fieldName2.indexOf(suffix, end + 1);
		}
		return fieldName2;
	}

	/**
	 * 功能描述：获取控件的值
	 * @param controlValueField
	 * @param vo
	 * @param listColumns
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static String getControlValue(List<String> controlValueField,Object vo) throws Exception {
		String strValue = "";
		// 根据指定列名，查询列对应的值
		if(controlValueField != null && !controlValueField.isEmpty()){
			for(int i = 0; i < controlValueField.size(); i++){
				if(controlValueField.size() == 1){
					String fieldName = controlValueField.get(i);
					strValue = ClassSupport.getVOValue(vo, fieldName);
					break;
				}
				//------------------------------------
				String fieldName = controlValueField.get(i);
				String value = "";
				value = ClassSupport.getVOValue(vo, fieldName);
				strValue += fieldName + ":" + value+"#";
			}
		}
		return strValue;
	}

	/**
	 * 功能描述：获取控件的值
	 * @param controlValueField
	 * @param vo
	 * @param listColumns
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean isShow(List<Rule> showControlRule,Object vo) throws Exception {
		boolean show = true;
		// 根据指定列名，查询列对应的值
		if(showControlRule != null && !showControlRule.isEmpty()){
			for(int i = 0; i < showControlRule.size(); i++){
				
				Rule rule = showControlRule.get(i);
				String fieldName = rule.getField();
				String value = "";
				value = ClassSupport.getVOValue(vo, fieldName);
				show = rule.checkValue(value);
				if(!show){
					break;
				}
			}
		}
		return show;
	}

	/**
	 * 功能描述：在vo中根据属性名称获取属性值
	 * @param vo
	 * @param listColumns
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static HtmlTD getHtmlTD(List listColumns,String fieldName) throws Exception {
		// 循环列数据
		for(int j = 0; j < listColumns.size(); j++){
			HtmlTD htmlTD = (HtmlTD) listColumns.get(j);
			String fieldValue = htmlTD.getValuefield();// 获取列名
			if(fieldValue.equals(fieldName)){
				return htmlTD;
			}
		}
		return null;
	}

	/**
	 * 功能描述：将字符串解析成json返回
	 * @param titles
	 * @return
	 */
	public static JSONArray getJson(String titles,String split) throws Exception {
		String[] strArr = titles.split(split);
		List<String> strList = new ArrayList<String>();
		for(int i = 0; i < strArr.length; i++){
			strList.add(strArr[i]);
		}
		JSONArray json = JSONArray.fromObject(strList);
		return json;
	}

}
