============== HtmlTableUtil.java方法说明(范例使用，参照framework.html.TableTest.java类) =========================

注：使用该类的jsp页面需要引入两个文件/css/table_default_style.css,/js/common.js
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/table_default_style.css">
    <script src="<%=path %>/js/common.js" type="text/javascript"></script>

body中添加
不分页
onload="f_scroll_NoPage('divHead','divBody');"
分页
onload="f_scroll('divHead','divBody','divEnd');"


根据需要自选调用分页或不分页方法。
1.不带分页方法
	/**
	 * 功能描述：创建HTML表格，不带分页
	 * @param tableProperty 表属性对象
	 * @param data VO数据集合
	 * @return
	 */
  	public static String createHtmlTable(TableProperty tableProperty,List data) throws Exception

2.带分页方法
  	/**
	 * 功能描述：创建HTML表格，带分页
	 * @param tableProperty 表属性对象
	 * @param data VO数据集合
	 * @param page 分页对象
	 * @return
	 */
  	public static String createHtmlTable(TableProperty tableProperty,List data,Page page) throws Exception

3.参数说明：
    TableProperty tableProperty：表对象
	1).可以根据需要自己设定特殊样式【参数tableStyle为表样式、rowStyle为行样式、cellStyle为单元格样式】
		private String tableStyle = null; //表格Style
		private String rowStyle = null; // 行Style
		private String cellStyle = null; //单元格Style，暂时未扩展应用
		使用方式：通过set属性对象设定
		
		Example:
			tableProperty.setTableStyle(String "class=abc"), HTML输出为 <table class=abc></table>
			tableProperty.setTableStyle(String "style='width:20%'"), HTML输出为 <table style='width:20%'></table>
		
	2).可以根据需要设定是否添加控件【参数controlType为控件类型、controlName为控件名称、controlValueField为控件所传的参数名集合、controlStyle为样式】
		private String controlType = null; //控件类型checkbox、radio
		private String controlName; //checkbox名称
		private String controlValueField; //checkbox的值域	
		private String controlStyle; //checkbox的样式	
		使用方式:
		通过addSelectControl(String controlType,String controlName,String controlValueField,String controlStyle)设定
		
		Example:
			String controlType = "checkbox";
			String controlName = "checkbox";//控件名称自定义
			//		String controlType = "radio";
			//		String controlName = "radio";
			//String controlvalueField = "userId";//设置一个参数
			List<String> controlvalueField = new ArrayList<String>();//设置控件的参数值，参数个数根据需求自定义
			controlvalueField.add("userId");
			controlvalueField.add("password");
			
			tableProperty.addSelectControl(controlType, controlName, controlvalueField, controlStyle);
			
			String controlStyle = "class='default_headerOut' style='width:5%;'";//定义样式style内容，根据需求自己定义
		HTML输出为
			一个参数输出格式为：<td><input type='checkbox' name='checkbox' value='1001'/></td>
			多个参数输出格式为：<td><input type='checkbox' name='checkbox' value='userId:1001#password:密码30#'/></td>
		
	
	3).可以根据需要设定是否设定排序方式【参数sortField为VO的属性，sortType为排序方式NULL，Asc, DESC】
		private String sortField = null; //排序字段
		private String sortType = null; //NULL，Asc, DESC
		使用方式：通过set属性对象设定
		
		Example:
			String sortField = "userId";
			String sortType = "desc";//asc对应"↑",desc对应"↓"    
		HTML输出为
			<th onclick="getSort('password')">列名↓</th>
			<th onclick="getSort('password')">列名↑</th>
			
			
	3.1).可以整体设置列表是否可以排序，默认为可排序，如果修改为不可排序，调用以下代码。
		tableProperty.setEnableSort(false);
		
			
		
	4).设置title的格式【参数title为VO属性的中文字义，参数key值与vo中的属性大小写对应】
		String column1 = "{title:'序号',key:'userId'}";
		String column2 = "{title:'名称',key:'userName'}";
		String titles = column1+"#"+column2+"#";
		
		将字符串解析成json返回
		String[] strArr = titles.split("#");
		List<String> strList = new ArrayList<String>();
		for(int i = 0; i < strArr.length; i++){
			strList.add(strArr[i]);
		}
		JSONArray json = JSONArray.fromObject(strList);
			
		通过循环json,获取表头及属性名,并自定义列的格式
		for(int i = 0; i < json.size(); i++){
			String field = (String) (json.getJSONObject(i)).get("key"); //获取属性key
			String title = (String) (json.getJSONObject(i)).get("title"); //获取表头内容key
		}
	
	5).设置显示序号【参数为boolean类型，true为显示序号，false为不显示，默认设置为false不显示】
		Example:
			tableProperty.setRowIndexStauts(true);//设置显示序号
		HTML输出为
			<th>序列</th>
			<td>1</td>
		
	6).设置列表体显示的高度(根据页面高度自行调整，默认300px，用于设置表头跟分页固定不动)【参数为一个固定数值不是百分比】
		String bodyDivHeight = "400px";
		tableProperty.setBodyDivHeight(bodyDivHeight);//设置div数据列显示高度
		
		HTML输出为
			表头：<div id="divHead" style="overflow:hidden">......</div>
			数据：<div id="divBody" style="height:400px; overflow:auto">......</div>
			分页：<div id="divEnd" style="overflow:hidden">......</div>
			<!--执行js，调整head、end的长度于body的长度相同-->
			<script type="text/javascript"> f_scroll("divHead","divBody","divEnd");</script>
	
	7).设置所有列显示最大长度，超出后用...表示,鼠标放在上面，会显示全部内容【参数为一个Integer数值】
		int maxLength = 15;//显示长度超过15后，仅显示15的长度加...
		tableProperty.setValueMaxLength(maxLength);//设置列表TD显示内容最大长度，超过用...省略
		
		HTML输出为
			<td title='2014-08-25 13:03:00'>2014-08-25 13:0...</td>
	
	8).设置为行添加事件【参数trEventParams：行事件属性名集合用于传参,eventType：行事件方法类型,eventName：行事件方法名】
		List<String> trEventParams = new ArrayList<String>();//创建事件参数
		trEventParams.add("userId");//参数1
		trEventParams.add("password");//参数2
		String eventType = "ondblclick";
		String eventName = "db";
		tableProperty.setTrEventType(eventType);//事件类型
		tableProperty.setTrEventName(eventName);//事件方法名称
		tableProperty.setTrEventParams(trEventParams);//事件参数
		
		注：eventName方法需要自己定义，参数个数跟设置的数量一致 
		
		HTML输出为
			<tr onclick="changeColor(this)" ondblclick="db('userId对应的值','password对应的值')">......</tr>
	
	9).创建列对象方法，目前提供：
		addColumn(String title,String field);
		addColumn(String title,String field,String style);
		addColumn(String title,String field,String defaultValue,String style);
		addColumn(String title,String field,String link,List<String> linkparam);
		addColumn(String title,String field,String link,List<String> linkparam,String style);
		addColumn(String title,String field,String DateType,String formatting,String style);
		addColumn(String title,String field,String link,List<String> linkparam,String defaultValue,String style);
		addColumn(String title,String field,String DateType,String formatting,String defaultValue,String style);
	
	所有的addColumn方法均返回HtmlTD对象，用来进一步设置。
	
	HtmlTD htmlTD列对象：
		private String title; //标题
		private String valuefield; //值域，对应VO对象中的属性名称,如districtid,也可以是<%=districtid%>,或<%=floors%>/<%=totalfloor%>
		private String style; //
		private String link; //链接，一般只传入JavaScript方法名
		private List<String> linkparam = null; //链接参数 属性名称
	
		private String DateType = null; //Date, DateTime
		private String formatting = null; //格式化
		private String defaultValue;//设定默认值
		
		1).可以指定某列添加样式
		
			Example:
				String valuefield = "userName";//定义需要设置样式的列属性
				String style = "class='default_headerOut'";	HTML输出为<th class="default_headerOut">列值</th>
				String style = "style='width:10%;'";		HTML输出为<th style='width:10%;'>列值</th>
			
		2).可以指定某列添加链接
		
			Example:
				String fieldLink = "userName";//定义添加link的属性
				String link = "show";//定义js方法名称
				List<String> linkparam = new ArrayList<String>();
				linkparam.add("userId");
				linkparam.add("password");
			HTML输出为
				<td><a href="javascript:show('userId对应的值','password对应的值')" >userName显示内容</a></td>
			注：show方法需要自己在页面添加
		
		3).可以指定日期某列格式化形式(以后会有扩展)
			
			Example:
				String fieldDate = "validDate";//定义需要格式化日期的属性
				String DateType = "Date";	//定义对应的格式       "date","datetime"(不区分大小写)
				String formatting = "yyyy-MM-dd HH:mm:ss";//"date" 对应"yyyy-MM-dd" "datetime"对应"yyyy-MM-dd HH:mm:ss"
			HTML输出为
				"date"：<td>2014-07-24 15:11:05</td>
				"datetime":<td>2014-07-24</td>
				
			扩展1 double、float类型格式化
			Example: 
				//String fieldType = "float";
				String fieldType = "double";
				String format = "#,##0.00";
				tableProperty.addColumn(title, field, fieldType, format, defaultValue, style);
			HTML输出为
				//如值1.000000000001E8格式化后结果
				<td>100,000,000.00</td>	
			
		4).可以指定某列为空时的默认值
			
			Example:
				String valuefield = "userName";//指定为空时显示默认值的列属性
				String defaultValue = "默认值";//设定默认值内容，判定范围null、""、"null"
			HTML输出为<td>默认值</td>
			
		5).设置排序字段
			
			默认以值域作为排序字段。对于一些配置了字典表的列，值域对应的并不是表的字段，需要手动设置。
			
			Example:
				HtmlTD td = tableProperty.addColumn("数据状态", "state_dict_name","style='width:120px;'");
				td.setSortFiled("state");
		
		6).设置是否可排序
		
			可以单独设置某一列是否可以排序，默认为可排序。如果设置了整体不能排序，这个设置无效。
			
			Example:
				HtmlTD td = tableProperty.addColumn("数据状态", "state_dict_name","style='width:120px;'");
				td.disableSort();
		
		注：指定参数列为日期类型时，必须设定指定列的类型DateType及格式化形式formatting
		
		HtmlTD提供不同的构造方法来给属性赋值。
		public HtmlTD(String title, String valuefield)
		public HtmlTD(String title, String valuefield, String style)
		public HtmlTD(String title, String valuefield, String defaultValue, String style)
		public HtmlTD(String title, String valuefield, String link, List<String> linkparam, String style)
		public HtmlTD(String title, String valuefield, String link, List<String> linkparam, String defaultValue, String style)
		public HtmlTD(String title, String valuefield, String DateType, String formatting, String style)
		public HtmlTD(String title, String valuefield, String DateType, String formatting, String defaultValue, String style)
	

		
完整例子：
	调用HtmlTableUtil类的分页方法，需要创建参数如下
	1.表对象,根据需求填充TableProperty：
		TableProperty tableProperty = new TableProperty();
	
	2.数据集合：
		List data = new ArrayList();
	
	3.分页对象：
		Page page = new Page();
	
	通过类名直接调用
	未分页：String html = HtmlTableUtil.createHtmlTable(tableProperty,data);
	分页：String pageHtml = HtmlTableUtil.createHtmlTable(tableProperty,data,page);
	将返回的String字符串在页面上输出即可
	
输出HTML：
<div id="divHead" style="overflow:hidden">
 	<table class='default_tableCss'>
		<tr class='default_row_tr' >
			<th style='width:5%;' >
				<input type='checkbox' name='checkbox' onclick='checkAll(this)'/>全选
			</th>
			<th style='width:5%;' >序列</th>
			<th style='width:10%;' onclick="getSort('userId')">序号↓</th>
			<th style='width:20%;' onclick="getSort('userName')">名称</th>
			<th style='width:10%;' onclick="getSort('sex')">性别</th>
			<th style='width:20%;' onclick="getSort('validDate')">日期</th>
			<th style='width:10%;' onclick="getSort('alias')">别名</th>
		</tr>
	</table>
</div>
<div id="divBody" style="height:400px; overflow:auto">
	<table class='default_tableCss'>
		<tr class='default_row_tr' onclick='changeColor(this)' ondblclick="db('1001','密码30')" >
			 <td style='width:10%;'>
				<input type='checkbox' name='checkbox' value='userId:1001#password:密码30#'/>
			</td>
			 <td style='width:10%;'>1</td>
			 <td style='width:10%;'>1001</td>
			 <td style='width:10%;'><a href="javascript:show('1001','密码30','2014-07-25 13:23:39')" >[map姓名]1</a></td>
			 <td style='width:10%;'>0</td>
			 <td style='width:10%;'>2014-07-25 13:23:39</td>
			 <td style='width:10%;'>map小王1</td>
			<td style='width:10%;'>100,000,000.00</td>
			<td style='width:10%;'>100,000,000.00</td>
		</tr>
		<tr class='default_row_tr' onclick='changeColor(this)' ondblclick="db('1002','密码29')" >
			 <td style='width:10%;'>
				<input type='checkbox' name='checkbox' value='userId:1002#password:密码29#'/>
			</td>
			 <td style='width:10%;'>2</td>
			 <td style='width:10%;'>1002</td>
			 <td style='width:10%;'><a href="javascript:show('1002','密码29','2014-07-25 13:23:39')" >[map姓名]2</a></td>
			 <td style='width:10%;'>1</td>
			 <td style='width:10%;'>2014-07-25 13:23:39</td>
			 <td style='width:10%;'>map小王2</td>
		</tr>
	</table>
</div>
<div id="divEnd" style="overflow:hidden">
	<table class='default_tableCss'>
		<tr class='default_row_tr'>
			<td width='55%' align="left">
				第1页 (共2页,30条记录) 首页  |  
				<a href='javascript:void(0);' onclick='nextPage(2)' >下页</a>
				<a href='javascript:void(0);' onclick='nextPage(2)' >末页</a> 
			</td>
			<td width='45%' align="right">
	 			  每页  
	 			<input type='text' id="_PageSize" name="_PageSize" size='1' maxlength='3' onkeyup='check(this)' onkeydown='chenge(this)' value='20'/>
	 			  条记录  到第 
				<input type="text" id="_CurrentPage" size='1' maxlength='3' onkeyup='check(this)' onkeydown='chenge(this)' value="1"/>
				  页 
				<input name='GoToPage' class='Control' type='button' value='转到' onclick='GoToPage()'>
			</td>
		</tr>
		<input type="hidden" id="_RecordCount" name="_RecordCount" value="30"/>
		<input type="hidden" id="_PageCount" name="_PageCount" value="2"/>
		<input type="hidden" id="_SortType" name="_SortType" value="desc"/>
		<input type="hidden" id="_SortField" name="_SortField" value="userId"/>
	</table>
</div>
<!--执行js，调整head、end的长度于body的长度相同-->
<script type="text/javascript"> f_scroll("divHead","divBody","divEnd");</script>


