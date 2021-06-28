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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.Page;

public class HtmlTableUtil2 {

	/**
	 * 功能描述：创建HTML表格，不带分页
	 * @param tableProperty
	 * 表对象
	 * @param data
	 * 数据集合
	 * @return
	 */
	public static String createHtmlTable(TableProperty tableProperty,List data) throws Exception {

		// 获取列表column集合
		List listColumns = tableProperty.getColumns();

		StringBuffer sb = new StringBuffer();

		String bodyDivHeight = tableProperty.getBodyDivHeight() == null ? "300px"
 : tableProperty.getBodyDivHeight();// 获取div的高度
		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		sb.append("<div id=\"divHead\" style=\"overflow:hidden\">\n");
		sb.append(headHtml);
		sb.append("</div>\n");
		sb.append("<div id=\"divBody\" style=\"height:" + bodyDivHeight + "; overflow:auto\">\n");
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</div>\n");
		sb.append("<!--执行js，调整head、end的长度于body的长度相同-->\n");
		sb.append("<script type=\"text/javascript\"> f_scroll_NoPage('divHead','divBody');</script>\n");

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

		String bodyDivHeight = tableProperty.getBodyDivHeight() == null ? "300px" : tableProperty.getBodyDivHeight();// 获取div的高度
		// 拼接表头字符串并返回
		String headHtml = getHeadHtml(tableProperty, listColumns);
		//		sb.append("<div style=\"position:relative;\">\n");
		sb.append("<div id=\"divHead\" style=\"overflow:hidden\">\n");
		sb.append(headHtml);
		sb.append("</div>\n");
		sb.append("<div id=\"divBody\" style=\"height:" + bodyDivHeight + "; overflow:auto\">\n");
		// 判断数据是否有值
		if(data != null && !data.isEmpty()){
			// 拼接表数据字符串并返回
			String bodyHtml = getBodyHtml(tableProperty, listColumns, data);
			sb.append(bodyHtml);
		}
		sb.append("</div>\n");
		sb.append("<div id=\"divEnd\" style=\"overflow:hidden\">\n");
		//		sb.append("<div id=\"divEnd\" style=\"position:absolute;bottom:0;overflow:hidden\">\n");
		// 拼接分页字符串并返回
		String pageHtml = getPageHtml(tableProperty, page);
		sb.append(pageHtml);
		sb.append("</div>\n");
		//		sb.append("</div>\n");
		sb.append("<!--执行js，调整head、end的长度于body的长度相同-->\n");
		sb.append("<script type=\"text/javascript\"> f_scroll('divHead','divBody','divEnd');</script>\n");

		return sb.toString();
	}

	/**
	 * 功能描述：添加表头
	 * @param sb
	 * @param tableProperty
	 * @param list
	 * @return
	 */
	private static String getHeadHtml(TableProperty tableProperty,List listColumns) throws Exception {
		StringBuffer sb = new StringBuffer();

		String tableStyle = tableProperty.getTableStyle() == null ? "class='default_tableCss'" : tableProperty.getTableStyle(); // 获得表样式
		String rowStyle = tableProperty.getRowStyle() == null ? "class='default_row_tr'" : tableProperty.getRowStyle(); // 获得行样式
		String controlType = tableProperty.getControlType(); // 控件类型checkbox、radio
		String controlStyle = tableProperty.getControlStyle() == null ? "style='width:5%;'" : tableProperty.getControlStyle();// 控件的样式
		String sortField = tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType(); // NULL，Asc, DESC
		boolean rowIndexStatus = tableProperty.isRowIndexStauts();// 是否显示序号列

		sb.append("<table " + tableStyle + ">\n");
		sb.append("<tr " + rowStyle + " >\n");

		// 判断控件信息
		// 判断多选框
		if(controlType != null && controlType.equalsIgnoreCase("checkbox")){
			sb.append("<th " + controlStyle + " >");
			sb.append("<input type='" + controlType + "' name='" + controlType + "' onclick='checkAll(this)'/>全选");
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
			String tdStyle = htmlTD.getStyle() == null ? "style='width:10%'" : htmlTD.getStyle();
			tdStyle = tdStyle.equals("") ? "style='width:10%'" : tdStyle;
			// 判断是否有排序
			if(sortField == null){
				sb.append("<th " + tdStyle + ">");
			}else{
				sb.append("<th " + tdStyle + " onclick=\"getSort('" + htmlTD.getValuefield() + "')\">");
			}
			sb.append(htmlTD.getTitle());
			if(sortField != null && sortField.equalsIgnoreCase(htmlTD.getValuefield())){
				// 判断是否排序，并添加↓、↑
				if(sortType != null && sortType.equalsIgnoreCase("desc")){
					sb.append("↓");
				}else if(sortType != null && sortType.equalsIgnoreCase("asc")){
					sb.append("↑");
				}
			}
			sb.append("</th>\n");
		}
		sb.append("</tr>\n");
		
		sb.append("</table>\n");

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
	public static String getBodyHtml(TableProperty tableProperty,List listColumns,List data) throws Exception {
		StringBuffer sb = new StringBuffer();
		// 将数据集合解析为json数组
		JSONArray jsonArray = JSONArray.fromObject(data);
		String tableStyle = tableProperty.getTableStyle() == null ? "class='default_tableCss'" : tableProperty.getTableStyle(); // 获得表样式
		String controlType = tableProperty.getControlType(); // 控件类型checkbox、radio
		String controlName = tableProperty.getControlName(); // checkbox名称
		String controlStyle = tableProperty.getControlStyle() == null ? "style='width:5%;'" : tableProperty.getControlStyle();// 控件的样式
		List<String> controlValueField = tableProperty.getControlValueField(); // checkbox的值域
		boolean rowIndexStatus = tableProperty.isRowIndexStauts();// 是否显示序号列
		int rowIndex = 1;

		sb.append("<table " + tableStyle + ">\n");
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject jobj = (JSONObject) jsonArray.get(i);// 循环json数组，获取json对象
			boolean isBaseVo = false;
			AbstractBaseVO baseVO = null;
			try{
				baseVO = (AbstractBaseVO) data.get(i);
				isBaseVo = true;
			}catch (Exception e){
				System.out.println("####[转换AbstractBaseVO对象失败，可能该类未继承AbstractBaseVO类]" + e);
			}

			String value = getControlValue(controlValueField, jobj, listColumns, baseVO, isBaseVo);

			// 针对行，判断是否添加单双击事件
			String trHtml = getTrEventParamHtml(tableProperty, jobj, listColumns, baseVO, isBaseVo);
			sb.append(trHtml);

			if(controlType != null && controlType.equalsIgnoreCase("checkbox")){
				sb.append("<td " + controlStyle + ">");
				sb.append("<input type='" + controlType + "' name='" + controlName + "' value='" + value + "'/>");
				sb.append("</td>\n");
			}
			if(controlType != null && controlType.equalsIgnoreCase("radio")){
				sb.append("<td " + controlStyle + ">");
				sb.append("<input type='" + controlType + "' name='" + controlName + "' value='" + value + "'>");
				sb.append("</td>\n");
			}

			// 判断序号显示
			if(rowIndexStatus){
				sb.append("<td " + controlStyle + ">");
				sb.append(rowIndex);
				sb.append("</td>\n");
				rowIndex++;
			}

			// 获取列
			String columHtml = getColumnsValue(listColumns, jobj, baseVO, isBaseVo);
			sb.append(columHtml);
			sb.append("</tr>\n");
		}
		sb.append("</table>\n");

		return sb.toString();
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
	private static String getTrEventParamHtml(TableProperty tableProperty,JSONObject jobj,List listColumns,AbstractBaseVO baseVO,boolean isBaseVo)
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
					String paramStr = getParamStr(trEventParams, jobj, listColumns, baseVO, isBaseVo);
					if(!paramStr.equals("")){
						sb.append(paramStr);
					}
				}
				sb.append(")\">\n");
			}else if(trEventType.equalsIgnoreCase("ondblclick")){
				sb.append("<tr " + rowStyle + " onclick=\"changeColor(this)\" ondblclick=\"" + trEventName + "(");
				if(trEventParams != null && !trEventParams.isEmpty()){
					String paramStr = getParamStr(trEventParams, jobj, listColumns, baseVO, isBaseVo);
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
	private static String getParamStr(List<String> trEventParams,JSONObject jobj,List listColumns,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		String paramStr = "";
		for(int i = 0; i < trEventParams.size(); i++){
			if(trEventParams.size() == 1){
				String field = trEventParams.get(i);
				Object obj = getFieldObjectValue(jobj, listColumns, field, baseVO, isBaseVo);
				if(obj == null || obj.equals("null")){
					obj = "";
				}
				paramStr = "'" + String.valueOf(obj) + "'";
				break;
			}
			String field = trEventParams.get(i);
			Object obj = getFieldObjectValue(jobj, listColumns, field, baseVO, isBaseVo);
			String value = "";
			if(obj == null || obj.equals("null")){
				obj = "";
			}
			value = "'" + String.valueOf(obj) + "'";
			paramStr += value;
			if(!value.equals("") && i < trEventParams.size() - 1){
				paramStr += ",";
			}
		}

		return paramStr;
	}

	/**
	 * 功能描述：添加page分页html
	 * @param sb
	 * @param tableProperty
	 * @param page
	 */
	private static String getPageHtml(TableProperty tableProperty,Page page) throws Exception {
		StringBuffer sb = new StringBuffer();
		String tableStyle = tableProperty.getTableStyle() == null ? "class='default_tableCss'"
 : tableProperty.getTableStyle(); // 获得表样式
		String rowStyle = tableProperty.getRowStyle() == null ? "class='default_row_tr'"
 : tableProperty.getRowStyle(); // 获得行样式
		String sortField = tableProperty.getSortField(); // 排序字段
		String sortType = tableProperty.getSortType(); // NULL，Asc, DESC

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
			// sb.append(" 上页 ");
		}
		sb.append(" | ");
		if(page.hasNext()){
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(" + (page.getCurrentPage() + 1) + ")' >下页</a> ");
			sb.append(" <a href='javascript:void(0);' onclick='nextPage(" + page.getPageCount() + ")' >末页</a> ");
		}else{
			sb.append(" 末页 ");
			// sb.append(" 下页 ");
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
	 * 功能描述：根据参数拼接列数据
	 * @param sb
	 * @param listColumns
	 * @param jobj
	 * @param baseVO
	 * @param isBaseVo
	 */
	private static String getColumnsValue(List listColumns,JSONObject jobj,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {

		StringBuffer sb = new StringBuffer();
		// 循环列数据
		for(int j = 0; j < listColumns.size(); j++){
			HtmlTD htmlTD = (HtmlTD) listColumns.get(j);
			String tdStyle = htmlTD.getStyle() == null ? "style='width:10%'" : htmlTD.getStyle();
			tdStyle = tdStyle.equals("") ? "style='width:10%'" : tdStyle;
			String dateType = htmlTD.getDateType();
			String formatting = htmlTD.getFormatting();
			sb.append("<td " + tdStyle + ">");
			
			// 判断link
			if(htmlTD.getLink() != null){
				// 判断取列的link字符串
				String linkStr = getLink(jobj, listColumns, htmlTD, baseVO, isBaseVo);
				sb.append(linkStr);
			}

			// 判断日期格式化格式化
			if(dateType != null && (dateType.equalsIgnoreCase("date") || dateType.equalsIgnoreCase("dateTime")) && formatting != null){
				sb.append(getFieldVlaueForDate(jobj, htmlTD, baseVO, isBaseVo));
			}else if(dateType != null && (dateType.equalsIgnoreCase("double") || (dateType.equalsIgnoreCase("float")))){
				sb.append(getFieldVlaueForFormat(jobj, htmlTD, baseVO, isBaseVo));
			}else{
				sb.append(getFiledValue(jobj, htmlTD, baseVO, isBaseVo));
			}

			// 添加link
			if(htmlTD.getLink() != null){
				sb.append("</a>");
			}
			sb.append("</td>\n");
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
	private static String getLink(JSONObject jobj,List listColumns,HtmlTD htmlTD,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		List<String> listFiled = htmlTD.getLinkparam();
		String param = "";
		for(int k = 0; k < listFiled.size(); k++){
			if(k > 0){
				param += ",";
			}
			String fieldStr = listFiled.get(k);
			Object obj = getFieldObjectValue(jobj, listColumns, fieldStr, baseVO, isBaseVo);// 获取格式化后的对象值
			param += "'" + obj + "'";
		}
		String linkStr = "<a href=\"javascript:" + htmlTD.getLink() + "(" + param + ")\" >";
		return linkStr;
	}

	/**
	 * 功能描述：根据定指定列名，在列集合中获取该列并格式化列值返回
	 * @param jobj
	 * @param listColumns
	 * @param fieldStr
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 * @throws Exception
	 */
	private static Object getFieldObjectValue(JSONObject jobj,List listColumns,String fieldStr,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		Object obj = jobj.get(fieldStr);
		if(obj == null || obj.equals("") && isBaseVo){
			obj = baseVO.getAttribute(fieldStr);// 获取map中的值
		}
		// 循环列数据
		for(int j = 0; j < listColumns.size(); j++){
			HtmlTD htmlTD = (HtmlTD) listColumns.get(j);
			String fieldValue = htmlTD.getValuefield();// 获取列名
			if(fieldValue.equals(fieldStr)){
				String dateType = htmlTD.getDateType();
				String formatting = htmlTD.getFormatting();
				// 判断日期格式化格式化
				if(dateType != null && (dateType.equalsIgnoreCase("date") || dateType.equalsIgnoreCase("dateTime")) && formatting != null){
					obj = String.valueOf(getFieldVlaueForDate(jobj, htmlTD, baseVO, isBaseVo));
				}else{
					obj = String.valueOf(getFiledValue(jobj, htmlTD, baseVO, isBaseVo));
				}
				break;
			}
		}
		return obj;
	}

	/**
	 * 功能描述：判断去列日期格式化值
	 * @param jobj
	 * @param htmlTD
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 */
	private static Object getFieldVlaueForDate(JSONObject jobj,HtmlTD htmlTD,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		String fieldValue = htmlTD.getValuefield();// 获取列名
		String formatting = htmlTD.getFormatting();
		String defaultValue = htmlTD.getDefaultValue();
		SimpleDateFormat sdf;
		Object obj = jobj.get(fieldValue);
		// 获取的值为null,且能转化为AbstractBaseVO类
		if(obj == null || obj.equals("") && isBaseVo){
			obj = baseVO.getAttribute(fieldValue);
		}
		// 判断是否为空
		if(obj == null || obj.equals("") || obj.equals("null")){
			// 判断是否有默认值
			if(defaultValue != null){
				obj = defaultValue;
			}else{
				obj = "";
			}
		}else{
			Date date = (Date) JSONObject.toBean((JSONObject) obj, java.util.Date.class);
			sdf = new SimpleDateFormat(formatting);
			obj = sdf.format(date);
		}

		return obj;
	}

	private static Object getFieldVlaueForFormat(JSONObject jobj,HtmlTD htmlTD,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		String fieldValue = htmlTD.getValuefield();// 获取列名
		String defaultValue = htmlTD.getDefaultValue();
		String formatting = htmlTD.getFormatting();
		Object obj = jobj.get(fieldValue);
		// 获取的值为null,且能转化为AbstractBaseVO类
		if(obj == null || obj.equals("") && isBaseVo){
			obj = baseVO.getAttribute(fieldValue);
		}
		// 判断是否为空
		if(obj == null || obj.equals("") || obj.equals("null")){
			// 判断是否有默认值
			if(defaultValue != null){
				obj = defaultValue;
			}else{
				obj = "";
			}
		}else{
			DecimalFormat df = new DecimalFormat(formatting);
			obj = df.format(obj);
		}
		return obj;
	}

	/**
	 * 功能描述：判断取列值
	 * @param jobj
	 * @param htmlTD
	 * @param baseVO
	 * @param isBaseVo
	 * @return
	 */
	private static Object getFiledValue(JSONObject jobj,HtmlTD htmlTD,AbstractBaseVO baseVO,boolean isBaseVo) throws Exception {
		String fieldValue = htmlTD.getValuefield();// 获取列名
		String defaultValue = htmlTD.getDefaultValue();
		Object obj = jobj.get(fieldValue);
		if(obj == null || obj.equals("") && isBaseVo){// jsonObject中key不存在则取的值为null，存在key可以取到""
			obj = baseVO.getAttribute(fieldValue);
		}
		// baseVO没取到值==null，则是，JSONObject取值可能为""

		if( (obj == null || obj.equals("") || obj.equals("null"))){
			if(defaultValue != null){
				obj = defaultValue;
			}else{
				obj = "";
			}
		}

		return obj;// 获取列值
	}

	@SuppressWarnings("unused")
	private static String getControlValue(List<String> controlValueField,JSONObject jobj,List listColumns,AbstractBaseVO baseVO,boolean isBaseVo)
			throws Exception {
		String strValue = "";
		// 根据指定列名，查询列对应的值
		if(controlValueField != null && !controlValueField.isEmpty()){
			for(int i = 0; i < controlValueField.size(); i++){
				if(controlValueField.size() == 1){
					String field = controlValueField.get(i);
					strValue = String.valueOf(getFieldObjectValue(jobj, listColumns, field, baseVO, isBaseVo));
					break;
				}
				String field = controlValueField.get(i);
				String value = String.valueOf(getFieldObjectValue(jobj, listColumns, field, baseVO, isBaseVo));
				strValue += field + ":" + value+"#";
			}
		}
		return strValue;
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
