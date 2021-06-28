/**
 * <p>DataGrid.java </p>
 *
 * <p>系统名称: 海神管理系统<p>  
 * <p>功能描述: 二纬数据列表<p>
 *
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-1-9<p>
 * 
 */ 
package com.netcom.nkestate.framework;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataGrid {

	private List<Map[]> list = new ArrayList<Map[]>();

	public DataGrid() {
	}

	public void clear() {
		list.clear();
	}

	public int rows() {
		return list.size();
	}

	public int columns() {
		if(list.size() > 0){
			Map[] maps = list.get(0);
			return maps.length;
		}
		return 0;
	}


	/**
	 * 功能描述：填充数据
	 * @param rs
	 * @param rsMeta
	 * @throws Exception
	 */
	public void fillData(ResultSet rs,ResultSetMetaData rsMeta) throws Exception {
		int columnCount = rsMeta.getColumnCount();
		while (rs.next()){
			Map[] maps = new HashMap[columnCount];
			for(int i = 0; i < columnCount; i++){
				Map<String , Object> map = new HashMap<String , Object>();
				map.put(rsMeta.getColumnName(i + 1).toLowerCase(), rs.getObject(i + 1));
				map.put("key000", rs.getObject(i + 1));
				maps[i] = map;
			}
			list.add(maps);
		}
	}

	/**
	 * 功能描述：根据行、列取值，从0开始
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public Object getValue(int row,int column) throws Exception {
		if(row > list.size())
			throw new Exception("超出行数范围");
		Map[] maps = list.get(row);
		if(column > maps.length)
			throw new Exception("超出列数范围");

		return maps[column].get("key000");
	}

	/**
	 * 功能描述：根据行号、列名取值
	 * @param row
	 * @param columnName
	 * @return
	 * @throws Exception
	 */
	public Object getValue(int row,String columnName) throws Exception {
		if(row > list.size())
			throw new Exception("超出行数范围");
		Map[] maps = list.get(row);
		for(Map map : maps){
			if(map.containsKey(columnName.toLowerCase()))
				return map.get(columnName.toLowerCase());
		}
		throw new Exception("没有找到相应的列");
	}

	public String getString(int row,int column) throws Exception {
		Object obj = getValue(row, column);
		if(obj==null){
			return null;
		}
		return String.valueOf(obj);
	}

	public String getString(int row,String columnName) throws Exception {
		Object obj = getValue(row, columnName);
		if(obj==null){
			return null;
		}
		return String.valueOf(obj);
	}

	public double getDouble(int row,int column) throws Exception {
		Object obj = getValue(row, column);
		if(obj==null){
			return 0;
		}
		return Double.parseDouble(String.valueOf(obj));
	}

	public double getDouble(int row,String columnName) throws Exception {
		Object obj = getValue(row, columnName);
		if(obj==null){
			return 0;
		}
		return Double.parseDouble(String.valueOf(obj));
	}

	public long getLong(int row,int column) throws Exception {
		Object obj = getValue(row, column);
		if(obj==null){
			return 0;
		}
		return Long.parseLong(String.valueOf(obj));
	}

	public long getLong(int row,String columnName) throws Exception {
		Object obj = getValue(row, columnName);
		if(obj==null){
			return 0;
		}
		return Long.parseLong(String.valueOf(obj));
	}

	public long getInt(int row,int column) throws Exception {
		Object obj = getValue(row, column);
		if(obj==null){
			return 0;
		}
		return Integer.parseInt(String.valueOf(obj));
	}

	public long getInt(int row,String columnName) throws Exception {
		Object obj = getValue(row, columnName);
		if(obj==null){
			return 0;
		}
		return Integer.parseInt(String.valueOf(obj));
	}

}
