/**
 *<p>DataBaseUtil.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：数据库操作工具</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-1-22</p>
 *
 */
package com.netcom.nkestate.framework.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.cache.DictionaryCache;
import com.netcom.nkestate.framework.cache.ModelCache;
import com.netcom.nkestate.framework.util.CharSet;



public class DBSupport {
	
	//static private String DBCharSet = "ISO8859-1";
	static private String DBCharSet = "GBK";
	
	static public String getDBCharSet() {
		return DBCharSet;
	}


	static Logger logger = Logger.getLogger(DBSupport.class.getName());
	
	// 设置参数字段值
	public static void setParameterValue(PreparedStatement pstmt, int index,String type, Object value, String DbName) throws Exception {
		if(value == null){
			pstmt.setString(index, null);
		}else if("string".equals(type.toLowerCase())){ // 字符型
			pstmt.setString(index, toDBCharSet((String) value));
		} else if("int".equals(type.toLowerCase())){
			pstmt.setInt(index, Integer.parseInt(String.valueOf(value)));			
		}else if("long".equals(type.toLowerCase())){			
			pstmt.setLong(index, Long.valueOf(String.valueOf( value)));
		}else if("double".equals(type.toLowerCase())){
			pstmt.setDouble(index, Double.valueOf(String.valueOf(value)));
		}else if("float".equals(type.toLowerCase())){
			pstmt.setFloat(index, Float.valueOf(String.valueOf(value)));
		} else if("date".equals(type.toLowerCase()) || "datetime".equals(type.toLowerCase())){	
			if(value!=null)
				pstmt.setTimestamp(index, new java.sql.Timestamp((((Date) value)).getTime()));
			else
				pstmt.setTimestamp(index, null);		
		} else if("text".equals(type.toLowerCase())){
			pstmt.setString(index, (String) value);
		} else if("blob".equals(type.toLowerCase())){			
			InputStream in = new ByteArrayInputStream((byte[]) value);
			//pstmt.setBinaryStream(index, in, (int)((byte[]) value).length);			
			pstmt.setBytes(index, (byte[]) value);
			
		}else if("clob".equals(type.toLowerCase())){
			pstmt.setBytes(index, ((String) value).getBytes());
		} else {
			throw new Exception("DBExtendUtil.setParameterValue()不支持数据库" + type + "类型");
		}
	}
	
	// 处理Clob大字段
	public static int handleClob(String tableName ,List<Column> listClob ,List<Column> listKey, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		String dbname = conn.getMetaData().getDatabaseProductName();
		
		StringBuffer sbSql = new StringBuffer();
		StringBuffer sbWhere = new StringBuffer();
		try {
			// 拼接SQL语句
			sbSql.append("select " );
			sbWhere.append(" from " + tableName + " where ");
			Iterator<Column> it = listClob.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				sbSql.append( column.getColumn() + " ,");				
			}
			String strSql = sbSql.toString();
			if(strSql.endsWith(",")) 
				strSql = strSql.substring(0,strSql.length()-1);
			
			it = listKey.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				sbWhere.append(" " + column.getColumn() + " = ? and");				
			}
			String strWhere = sbWhere.toString();			
			if(strWhere.endsWith("and")) 
				strWhere = strWhere.substring(0,strWhere.length()-"and".length());
			sql = strSql + strWhere + " for update" ;
				
			
			// 数据库操作准备
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			it = listKey.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				setParameterValue(pstmt, index++ , column.getType(), column.getValue(),dbname);				
			}			
			rs = pstmt.executeQuery(); // 执行数据库操作
			if(rs.next()) {
				for(int i=0;i<listClob.size();i++) {
					Column column = listClob.get(i);
					Object value = column.getValue();
					String ClobType = rs.getClob(column.getColumn()).getClass().getName().toLowerCase();
					// if(rs.getClob(column.getColumn()) instanceof
					// oracle.sql.CLOB) { //Oracle数据库
					if("oracle".equals(dbname.toLowerCase())){ // Oracle数据库
						if(ClobType.startsWith("weblogic")){ // Weblogic环境
							Clob clob = rs.getClob(i + 1);							
							if(value instanceof byte[]) // 如果值就是字节数组
								clob.setString(1, new String((byte[]) value));
							else
								clob.setString(1, (String) column.getValue());
						}else{
							oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob(i + 1);
							char[] c = null;
							if(value instanceof byte[]) // 如果值就是字节数组
								c = (new String((byte[]) value)).toCharArray();
							else
								c = value == null ? null : ((String) value).toCharArray();
							Writer stream = clob.getCharacterOutputStream();
							stream.write(c);
							stream.flush();
							stream.close();		
						}
										
					} else {
						Clob clob = rs.getClob(i+1); 						
						if(value instanceof byte[]) // 如果值就是字节数组
							clob.setString(0, new String((byte[])value));
						else	
							clob.setString(0, (String)column.getValue());						
					}					
				}
				
			}
			
			
		} catch (Exception e){
			System.out.println("Error at DataBaseUtil.handleClob()," + e.getMessage() + ",SQL:" + sql);
			e.printStackTrace();
			throw e;
		} finally {
			try { pstmt.close();} catch (Exception e) {}			
		}		
		return 0;
	}
	
	// 处理Blob大字段
	public static int handleBlob(String tableName ,List<Column> listBlob ,List<Column> listKey, Connection conn) throws Exception {
		String dbname = conn.getMetaData().getDatabaseProductName();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		StringBuffer sbSql = new StringBuffer();
		StringBuffer sbWhere = new StringBuffer();
		try {
			// 拼接SQL语句
			sbSql.append("select " );
			sbWhere.append(" from " + tableName + " where ");
			Iterator<Column> it = listBlob.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				sbSql.append( column.getColumn() + " ,");				
			}
			String strSql = sbSql.toString();
			if(strSql.endsWith(",")) 
				strSql = strSql.substring(0,strSql.length()-1);
			
			it = listKey.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				sbWhere.append(" " + column.getColumn() + " = ? and");				
			}
			String strWhere = sbWhere.toString();			
			if(strWhere.endsWith("and")) 
				strWhere = strWhere.substring(0,strWhere.length()-"and".length());
			sql = strSql + strWhere + " for update" ;
				
			
			// 数据库操作准备
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			it = listKey.iterator();
			while(it.hasNext()) {
				Column column = it.next();				
				setParameterValue(pstmt, index++ , column.getType(), column.getValue(),dbname);				
			}			
			rs = pstmt.executeQuery(); // 执行数据库操作
			if(rs.next()) {
				for(int i=0;i<listBlob.size();i++) {
					Column column = listBlob.get(i);
					Object value = column.getValue();
					
					String BlobType = rs.getBlob(column.getColumn()).getClass().getName().toLowerCase();
					//weblogic.jdbc.wrapper.Blob_oracle_sql_BLOB
					// if(rs.getBlob(column.getColumn()) instanceof
					// oracle.sql.BLOB) { //Oracle数据库
					if("oracle".equals(dbname.toLowerCase())){ // Oracle数据库
						if(BlobType.startsWith("weblogic")){ // Weblogic环境
							Blob blob = rs.getBlob(i + 1);
							blob.setBytes(1L, (byte[]) value);						
						}else{
							oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(i + 1);;
							OutputStream out = blob.getBinaryOutputStream();
							out.write((byte[]) value);
							out.flush();
							out.close();
						}
					} else {
						Blob blob = rs.getBlob(i+1); 	
						//InputStream in = blob.getBinaryStream();
						blob.setBytes(1L, (byte[]) value);
					}					
				}
				
			}			
			
		} catch (Exception e){
			System.out.println("Error at DBSupport.handleBlob()," + e.getMessage() + ",SQL:" + sql);
			e.printStackTrace();
			throw e;
		} finally {
			try { pstmt.close();} catch (Exception e) {}			
		}		
		return 0;
	}
	
	// 得到数据库名称
	static private String getDataBaseName(Connection conn) throws Exception {
		DatabaseMetaData meta = conn.getMetaData();
		return meta.getDatabaseProductName().toLowerCase();
	}
	
	// 根据sequence得到序列值
	private static long getNextID(String sequenceName, Connection conn) throws SQLException {
		long nextID = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select " + sequenceName + ".nextval as id from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				nextID = rs.getLong(1);
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage());
			throw e;
		}finally{
			try{
				rs.close();
			}catch (Exception e){
			}
			try{
				pstmt.close();
			}catch (Exception e){
			}
		}
		return nextID;
	}

	/**
	 * 功能描述：取自增长的值，字段最大值+1
	 * @param tableName
	 * 表名
	 * @param fieldName
	 * 字段名
	 * @return
	 * @throws SQLException
	 */
	private static long getNextID(String tableName, String fieldName, Connection conn) throws SQLException {
		long nextID = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select max(" + fieldName + ") +1 as id from " + tableName;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				nextID = rs.getLong(1) == 0 ? 1 : rs.getLong(1);
			else
				nextID = 1;

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage());
			throw e;
		}finally{
			try{
				rs.close();
			}catch (Exception e){
			}
			try{
				pstmt.close();
			}catch (Exception e){
			}
		}
		return nextID;
	}
	
	// 执行新增操作
	// 返回 主键值
	public static long doAdd(AbstractBaseVO vo, DBModel annotation, Map<DBMeta , Object> columns, Connection conn) throws Exception {
		String tableName = annotation.tablename(); // 表名
		String description = annotation.description(); // 描述
		int IDType = annotation.id(); // 主键生成方式
		String sequence = annotation.sequence(); // 数据库序列
		String dbname = getDataBaseName(conn);
		String PKColumnName = null; //主键字段名称
		String PKColumnType = null; //主键字段类型
		logger.debug("dbname:" + dbname);

		PreparedStatement pstmt = null;
		long id = 0;

		List<Column> listClob = new ArrayList<Column>();
		List<Column> listBlob = new ArrayList<Column>();
		List<Column> listKey = new ArrayList<Column>();

		String sql = null;
		StringBuffer sbColumn = new StringBuffer();
		StringBuffer sbValue = new StringBuffer();
		try{
			// 拼接SQL语句
			sbColumn.append("insert into " + tableName + "(");
			sbValue.append(" values(");
			Iterator<DBMeta> it = columns.keySet().iterator();
			while (it.hasNext()){
				DBMeta meta = it.next();
				String column = meta.column();
				String type = meta.type();
				Object value = columns.get(meta);
				String defaultValue = meta.default_Value();
				
				sbColumn.append(column + ",");
				if("oracle".equals(dbname) && "clob".equals(type.toLowerCase())){
					sbValue.append("empty_clob(),");
				}else if("oracle".equals(dbname) && "blob".equals(type.toLowerCase())){
					sbValue.append("empty_blob(),");
				}else if(!"".equals(defaultValue) && (value == null || "0".equals(String.valueOf(value)))){
					sbValue.append(defaultValue + ",");
				}else{
					sbValue.append("?,");
				}
				
			}
			String strColumn = sbColumn.toString();
			String strValue = sbValue.toString();
			if(strColumn.endsWith(","))
				strColumn = strColumn.substring(0, strColumn.length() - 1);
			strColumn = strColumn + ")";
			if(strValue.endsWith(","))
				strValue = strValue.substring(0, strValue.length() - 1);
			strValue = strValue + ")";
			sql = strColumn + strValue;
			logger.debug("SQL:" + sql);

			// 数据库操作准备
			int index = 1;
			if("oracle".equals(dbname)) {
				pstmt = conn.prepareStatement(sql); //Oracle不支持Statement.RETURN_GENERATED_KEYS
			} else {
				pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			}
			it = columns.keySet().iterator();
			while (it.hasNext()){
				DBMeta meta = it.next();
				String column = meta.column();
				String type = meta.type();
				String primarykey = meta.primarykey();
				Object value = columns.get(meta);
				String defaultValue = meta.default_Value();
				if(primarykey.equals("true")){ // 主键
					if(IDType == DBModel.SequenceID){
						value = String.valueOf(getNextID(sequence, conn));
						id = Long.parseLong((String) value);
						if("int".equals(type.toLowerCase()))
							ModelUtil.setValue(vo, column, (int)id);
						else
							ModelUtil.setValue(vo, column, id);
					}else if(IDType == DBModel.AutoIncreaseID){
						value = String.valueOf(getNextID(tableName, column, conn));
						id = Long.parseLong((String) value);
						if("int".equals(type.toLowerCase()))
							ModelUtil.setValue(vo, column, (int)id);
						else
							ModelUtil.setValue(vo, column, id);
					}else if(IDType == DBModel.AssignedID){
						if(value instanceof java.lang.String)
							id = Long.MAX_VALUE; // 字符型主键，返回最大值
						else if(value instanceof java.util.Date)
							id = Long.MAX_VALUE - 1; // 日期型主键，返回最大值-1
						else
							id = Long.parseLong(String.valueOf(value));						
					}else if(IDType == DBModel.AutoIncreaseID2){ // sqlserver,mysql自增长auto_increment
						PKColumnName = column; 
						PKColumnType = type;
					}
				}
				if("oracle".equals(dbname) && "clob".equals(type.toLowerCase()) && value != null){
					listClob.add(new Column(column, type, value));
					continue;
				}else if("oracle".equals(dbname) && "blob".equals(type.toLowerCase()) && value != null){
					listBlob.add(new Column(column, type, value));
					continue;
				}else if(primarykey.equals("true")){
					listKey.add(new Column(column, type, value));
				}
				
				if(!"".equals(defaultValue) && (value == null || "0".equals(String.valueOf(value)))) // 有默认值，就不用
					;
				else
					DBSupport.setParameterValue(pstmt, index++, type, value, dbname);
			}

			pstmt.executeUpdate(); // 执行数据库操作

			// 处理Clob字段
			if(listClob.size() > 0){
				DBSupport.handleClob(tableName, listClob, listKey, conn);
				if(conn.getAutoCommit()){
					conn.commit();
				}
			}

			// 处理Blob字段
			if(listBlob.size() > 0){
				DBSupport.handleBlob(tableName, listBlob, listKey, conn);
				if(conn.getAutoCommit()){
					conn.commit();
				}
			}
			
			if(IDType == DBModel.AutoIncreaseID2){ //取出数据库自增长字段的最大值
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					id = rs.getLong(1);				
				}				
				if("int".equals(PKColumnType.toLowerCase()))
					ModelUtil.setValue(vo, PKColumnName, (int)id);
				else
					ModelUtil.setValue(vo, PKColumnName, id);
			}

		}catch (Exception e){
			logger.error("Message:" + e.getMessage() + ",SQL:" + sql);
			logger.error("TableName:" + tableName + ",VO:" + vo.getClass().toString());
			e.printStackTrace();
			throw e;
		}finally{
			try{
				pstmt.close();
			}catch (Exception e){
			}
			sbColumn = null;
			sbValue = null;
			listClob = null;
			listBlob = null;
			listKey = null;
		}
		return id;
	}

	/**
	 * 功能描述：批量插入(不支持clob和blob)
	 * @param vo
	 * @param annotation
	 * @param columns
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int doAddBatch(List<AbstractBaseVO> list,DBModel annotation,Connection conn) throws Exception {
		AbstractBaseVO vo = null;
		if(list == null){
			return -1;
		}
		if(list.size() > 0){
			vo = (AbstractBaseVO) list.get(0);
		}else{
			return 0;
		}
		List<String> primarykeys = new ArrayList<String>();
		Map<DBMeta , Object> columns = new HashMap<DBMeta , Object>();
		ModelUtil.parseVO(vo, primarykeys, columns);


		String tableName = annotation.tablename(); // 表名
		int IDType = annotation.id(); // 主键生成方式
		String sequence = annotation.sequence(); // 数据库序列
		String dbname = getDataBaseName(conn);
		String PKColumnName = null; //主键字段名称
		String PKColumnType = null; //主键字段类型
		logger.debug("dbname:" + dbname);

		PreparedStatement pstmt = null;
		long id = 0;
		int size = list.size();

		String sql = null;
		StringBuffer sbColumn = new StringBuffer();
		StringBuffer sbValue = new StringBuffer();
		try{
			// 拼接SQL语句
			sbColumn.append("insert into " + tableName + "(");
			sbValue.append(" values(");
			Iterator<DBMeta> it = columns.keySet().iterator();
			while (it.hasNext()){
				DBMeta meta = it.next();
				String column = meta.column();
				Object value = columns.get(meta);
				String defaultValue = meta.default_Value();

				sbColumn.append(column + ",");
				if(!"".equals(defaultValue) && (value == null || "0".equals(String.valueOf(value)))){
					sbValue.append(defaultValue + ",");
				}else{
					sbValue.append("?,");
				}

			}
			String strColumn = sbColumn.toString();
			String strValue = sbValue.toString();
			if(strColumn.endsWith(","))
				strColumn = strColumn.substring(0, strColumn.length() - 1);
			strColumn = strColumn + ")";
			if(strValue.endsWith(","))
				strValue = strValue.substring(0, strValue.length() - 1);
			strValue = strValue + ")";
			sql = strColumn + strValue;
			logger.debug("SQL:" + sql);

			// 数据库操作准备
			if("oracle".equals(dbname)){
				pstmt = conn.prepareStatement(sql); //Oracle不支持Statement.RETURN_GENERATED_KEYS
			}else{
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			for(int i = 0; i < list.size(); i++){
				int index = 1;
				primarykeys = new ArrayList<String>();
				columns = new HashMap<DBMeta , Object>();
				vo = (AbstractBaseVO) list.get(i);
				ModelUtil.parseVO(vo, primarykeys, columns);
				it = columns.keySet().iterator();
				while (it.hasNext()){
					DBMeta meta = it.next();
					String column = meta.column();
					String type = meta.type();
					String primarykey = meta.primarykey();
					Object value = columns.get(meta);
					String defaultValue = meta.default_Value();
					if(primarykey.equals("true")){ // 主键
						if(IDType == DBModel.SequenceID){
							value = String.valueOf(getNextID(sequence, conn));
							id = Long.parseLong((String) value);
							if("int".equals(type.toLowerCase()))
								ModelUtil.setValue(vo, column, (int) id);
							else
								ModelUtil.setValue(vo, column, id);
						}else if(IDType == DBModel.AutoIncreaseID){
							value = String.valueOf(getNextID(tableName, column, conn));
							id = Long.parseLong((String) value);
							if("int".equals(type.toLowerCase()))
								ModelUtil.setValue(vo, column, (int) id);
							else
								ModelUtil.setValue(vo, column, id);
						}else if(IDType == DBModel.AssignedID){
							if(value instanceof java.lang.String)
								id = Long.MAX_VALUE; // 字符型主键，返回最大值
							else if(value instanceof java.util.Date)
								id = Long.MAX_VALUE - 1; // 日期型主键，返回最大值-1
							else
								id = Long.parseLong(String.valueOf(value));
						}else if(IDType == DBModel.AutoIncreaseID2){ // sqlserver,mysql自增长auto_increment
							PKColumnName = column;
							PKColumnType = type;
						}
					}

					if(!"".equals(defaultValue) && (value == null || "0".equals(String.valueOf(value)))) // 有默认值，就不用
						;
					else
						DBSupport.setParameterValue(pstmt, index++, type, value, dbname);
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch(); // 执行数据库操作
			if(IDType == DBModel.AutoIncreaseID2){ //取出数据库自增长字段的最大值
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					id = rs.getLong(1);
				}
				if("int".equals(PKColumnType.toLowerCase()))
					ModelUtil.setValue(vo, PKColumnName, (int) id);
				else
					ModelUtil.setValue(vo, PKColumnName, id);
			}

		}catch (Exception e){
			logger.error("Message:" + e.getMessage() + ",SQL:" + sql);
			logger.error("TableName:" + tableName + ",VO:" + vo.getClass().toString());
			e.printStackTrace();
			throw e;
		}finally{
			try{
				pstmt.close();
			}catch (Exception e){
			}
			sbColumn = null;
			sbValue = null;
		}
		return size;
	}

	// 执行更新操作
	// 返回 更新的记录数
	public static int doUpdate(AbstractBaseVO vo, DBModel annotation, Map<DBMeta , Object> columns, Connection conn) throws Exception {
		String tableName = annotation.tablename(); // 表名
		String description = annotation.description(); // 描述
		String dbname = getDataBaseName(conn);

		PreparedStatement pstmt = null;
		int ret = 0;

		String sql = null;
		StringBuffer sbSql = new StringBuffer("update " + tableName + " set ");
		StringBuffer sbWhere = new StringBuffer(" where ");

		try{
			List listValue = new ArrayList();
			List listValue2 = new ArrayList();
			List<String> listType = new ArrayList<String>();
			List<String> listType2 = new ArrayList<String>();

			List<Column> listClob = new ArrayList<Column>();
			List<Column> listBlob = new ArrayList<Column>();
			List<Column> listKey = new ArrayList<Column>();

			// 拼接SQL语句
			Iterator<DBMeta> it = columns.keySet().iterator();
			while (it.hasNext()){
				DBMeta meta = it.next();
				String column = meta.column();
				String primarykey = meta.primarykey();
				String can_update = meta.can_update();
				String type = meta.type();
				Object value = columns.get(meta);
				if(primarykey.equals("true")){
					sbWhere.append(" " + column + " = ? and");
					listValue2.add(value);
					listType2.add(type);
					listKey.add(new Column(column, type, value));
				}else if(can_update.equals("true")){ // 不能更新主键字段和标识为不可更新字段
					if("clob".equals(type.toLowerCase()) && value != null){
						listClob.add(new Column(column, type, value));
						continue;
					}else if("blob".equals(type.toLowerCase()) && value != null){
						listBlob.add(new Column(column, type, value));
						continue;
					}
					sbSql.append(column + " = ? ,");
					listValue.add(value);
					listType.add(type);
				}
			}
			listValue.addAll(listValue2);
			listType.addAll(listType2);

			// 处理SQL语句
			String str = sbSql.toString();
			if(str.endsWith(","))
				str = str.substring(0, str.length() - ",".length());
			String str2 = sbWhere.toString();
			if(str2.endsWith(" and"))
				str2 = str2.substring(0, str2.length() - " and".length());
			sql = str + str2;
			
			logger.debug(sql);

			// 数据库操作准备
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < listValue.size(); i++){
				String type = listType.get(i);
				Object value = listValue.get(i);
				DBSupport.setParameterValue(pstmt, index++, type, value, dbname);
			}

			ret = pstmt.executeUpdate(); // 执行更新

			// 处理Clob字段
			if(listClob.size() > 0){
				DBSupport.handleClob(tableName, listClob, listKey, conn);
				if(conn.getAutoCommit()){
					conn.commit();
				}
			}

			// 处理Blob字段
			if(listBlob.size() > 0){
				DBSupport.handleBlob(tableName, listBlob, listKey, conn);
				if(conn.getAutoCommit()){
					conn.commit();
				}
			}

		}catch (Exception e){
			logger.error("Message," + e.getMessage() + ",SQL:" + sql);
			logger.error("TableName:" + tableName + ",VO:" + vo.getClass().toString());
			throw e;
		}finally{
			try{
				pstmt.close();
			}catch (Exception e){
			}
			sbSql = null;
			sbWhere = null;
		}
		return ret;
	}
	
	// 根据ID查找对象
	public static AbstractBaseVO doFind(Class clazz, String id, Connection conn) throws Exception {
		AbstractBaseVO vo = null;

		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);
		if(primaryKeys.size() == 0 || primaryKeys.size() > 1)
			throw new Exception(clazz.getName() + "没有主键或主键数量大于1");

		// 拼接SQL语句
		String sql = "select * from " + tableName + " where " + primaryKeys.get(0) + "=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				vo = loadVO(clazz, rs, conn);
				loadDictionaryValue(vo, metas, conn); // 加载字典表
			}

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			try{
				rs.close();
			}catch (Exception e){
			}
			try{
				pstmt.close();
			}catch (Exception e){
			}
		}

		return vo;
	}

	/**
	 * 功能描述：加载一个对象
	 * @param clazz
	 * 类型
	 * @param rs
	 * 结果集
	 * @return
	 * @throws Exception
	 */
	static public AbstractBaseVO loadVO(Class clazz,ResultSet rs,Connection conn) throws Exception {
		AbstractBaseVO vo = (AbstractBaseVO) clazz.newInstance();
		ResultSetMetaData rsMeta = rs.getMetaData();
		for(int i = 1; i <= rsMeta.getColumnCount(); i++){
			String column = rsMeta.getColumnName(i); // 字段名称
			Object value = rs.getObject(column);
			Map map = findSetMethod(vo, column);
			DBMeta meta = (DBMeta) map.get("meta");
			Method method = (Method) map.get("method");
			if(method == null){
				setNoneDBFieldValue(vo, column, value); // 没有对应方法，设置到Map属性中
				continue;
			}
			if(value == null)
				continue;			

			//logger.debug("LoadVO Class:" + clazz.getName() + ",column:" + column);
			// 赋值
			if("Int".toLowerCase().equals(meta.type().toLowerCase())){
				method.invoke(vo, Integer.parseInt( (value.toString())));
			}else if("long".toLowerCase().equals(meta.type().toLowerCase())){				
				method.invoke(vo, Long.parseLong( (value.toString())));
			}else if("double".toLowerCase().equals(meta.type().toLowerCase()) || "float".toLowerCase().equals(meta.type().toLowerCase())){
				method.invoke(vo, Double.parseDouble( (value.toString())));
			}else if("String".toLowerCase().equals(meta.type().toLowerCase())){
				method.invoke(vo, toAppCharSet(value.toString()));
			}else if("date".toLowerCase().equals(meta.type().toLowerCase()) || "datetime".toLowerCase().equals(meta.type().toLowerCase())){
				value = rs.getTimestamp(column);
				Timestamp time = (Timestamp) value;
				Date date = time == null ? null : new java.util.Date(time.getTime());
				method.invoke(vo, date);
			}else if("blob".toLowerCase().equals(meta.type().toLowerCase())){
				Object blobObj = rs.getBlob(column);
				String clsname = blobObj.getClass().getName();
				if( (value instanceof oracle.sql.BLOB && value != null) || clsname.startsWith("weblogic")){
					//weblogic下的blob处理(后面条件)
					//oracle.sql.BLOB blob = (oracle.sql.BLOB) value;						
					InputStream in = rs.getBinaryStream(column);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int n;
					while ( (n = in.read(buffer)) != -1){
						out.write(buffer, 0, n);
					}
					byte[] b = out.toByteArray();
					method.invoke(vo, b);
					in.close();
					
				}else{
					Blob blob = (Blob) value;
				}
			}else if("clob".toLowerCase().equals(meta.type().toLowerCase())){
				if(value instanceof oracle.sql.CLOB){
					//oracle.sql.CLOB clob = (oracle.sql.CLOB) value;
					Clob clob = (Clob) value;
					//Clob content = rs.getClob("content");//java.sql.Clob

					String detailinfo = "";
					if(clob != null){
						detailinfo = clob.getSubString((long) 1, (int) clob.length());

					}
					if(clob == null){
						method.invoke(vo, null);
						continue;
					}
					InputStream in = rs.getBinaryStream(column);
					//ByteArrayOutputStream out = new ByteArrayOutputStream();
					//byte[] buffer = new byte[1024];
					//int n;
					//while ( (n = in.read(buffer)) != -1){
					//	out.write(buffer, 0, n);
					//}
					//byte[] b = out.toByteArray();
					method.invoke(vo, detailinfo.getBytes());
					in.close();
				}else{
					Clob clob = (Clob) value;
					if(clob == null){
						method.invoke(vo, null);
						continue;
					}
					if(clob != null){
						String detailinfo = clob.getSubString((long) 1, (int) clob.length());
						method.invoke(vo, detailinfo.getBytes());
					}
//					InputStream in = clob.getAsciiStream();
//					ByteArrayOutputStream out = new ByteArrayOutputStream();
//					byte[] buffer = new byte[1024];
//					int n;
//					while ( (n = in.read(buffer)) != -1){
//						out.write(buffer, 0, n);
//					}
//					byte[] b = out.toByteArray();
//					method.invoke(vo, b);
//					in.close();
				}

			}else{
				method.invoke(vo, value); // 执行set方法
			}
		}
				
		loadVOObject(vo, rs, conn);// 加载映射对象 daobin 2015.08.19

		return vo;
	}


	// 查找set方法
	static private Map findSetMethod(AbstractBaseVO vo, String column) {
		Map map = new HashMap();
		String setMethodName = null;
		DBMeta meta = null;
		//Method[] methods = ModelUtil.getMetaMethods(vo.getClass());
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); //daobin 2015.8.10
		for(Method method : methods){
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && method.getName().startsWith("get")){
				meta = method.getAnnotation(DBMeta.class);
				if(meta.column().toLowerCase().equals(column.toLowerCase())){
					map.put("meta", meta);
					setMethodName = "set" + method.getName().substring("set".length());
					break;
				}
			}
		}
		if(setMethodName == null)
			return map;
		
		//System.out.println("==setMethodName==:" + setMethodName);
		//Method[] methods2 = ModelUtil.getSettingMethods(vo.getClass());
		Method[] methods2 = ModelCache.getSettingMethods(vo.getClass());
		for(Method method : methods2){			
			//System.out.println("setMethod:" + method.getName());
			if(method.getName().equals(setMethodName)){
				///System.out.println("******setMethod*****:" + method.getName());
				map.put("method", method);
				break;
			}
		}

		return map;
	}

	// 对没有映射数据库的字段赋值(blob类型字段未完善)
	static private void setNoneDBFieldValue(AbstractBaseVO vo, String column, Object value) {
		Method[] methods = ModelUtil.getSettingMethods(vo.getClass());
		for(Method method : methods){
			if(method.getName().toLowerCase().equals("set" + column.toLowerCase())){
				try{
					Class[] classes = method.getParameterTypes();
					
					if(value == null)
						continue;	
					// 赋值
					if(classes[0].equals(int.class)){
						method.invoke(vo, Integer.parseInt( (value.toString())));
					}else if(classes[0].equals(long.class)){				
						method.invoke(vo, Long.parseLong( (value.toString())));
					}else if(classes[0].equals(double.class) || classes[0].equals(float.class)){
						method.invoke(vo, Double.parseDouble( (value.toString())));
					}else if(classes[0].equals(String.class)){
						method.invoke(vo, toAppCharSet(value.toString()));
					}else if(classes[0].equals(Date.class)){
						Timestamp time = (Timestamp) value;
						Date date = time == null ? null : new java.util.Date(time.getTime());
						method.invoke(vo, date);
					}else if(classes[0].equals(Blob.class)){
						if(value instanceof oracle.sql.BLOB && value != null){
							//oracle.sql.BLOB blob = (oracle.sql.BLOB) value;						
//							InputStream in = rs.getBinaryStream(column);
//							ByteArrayOutputStream out = new ByteArrayOutputStream();
//							byte[] buffer = new byte[1024];
//							int n;
//							while ( (n = in.read(buffer)) != -1){
//								out.write(buffer, 0, n);
//							}
//							byte[] b = out.toByteArray();
//							method.invoke(vo, b);
//							in.close();
							
						}else{
							Blob blob = (Blob) value;
						}
					}else if(classes[0].equals(Clob.class)){
						if(value instanceof oracle.sql.CLOB){
							oracle.sql.CLOB clob = (oracle.sql.CLOB) value;
							if(clob == null){
								method.invoke(vo, null);
								continue;
							}
							long len = clob.length();
							char arr[] = new char[(int) len];
							int readednum = clob.getChars(1, (int) len, arr);
							StringBuffer sb = new StringBuffer();
							sb.append(arr);
							String str = sb.toString();
							method.invoke(vo, str);
						}else{
							Clob clob = (Clob) value;
							String str = clob == null ? null : clob.getSubString(1, (int) clob.length());
							method.invoke(vo, str);
						}

					}else{
						method.invoke(vo, value); // 执行set方法
					}
					return;
				}catch (Exception e){
					logger.error("Message," + e.getMessage() + ",Class:" + vo.getClass() + ",method:" + method.getName() + ",value:" + value);
				}
			}
		}
		if(value != null && value instanceof java.lang.String)
			vo.setAttribute(column.toLowerCase(), toAppCharSet(value.toString())); // 没有对应方法，设置到Map属性中
		else
			vo.setAttribute(column.toLowerCase(), value); // 没有对应方法，设置到Map属性中
	}
	
	
	// 加载字典表的值
	public static void loadDictionaryValue(AbstractBaseVO vo,List<DBMeta> metas,Connection conn) throws Exception {		
		try{
			Iterator<DBMeta> it = metas.iterator();
			while (it.hasNext()){
				DBMeta meta = it.next();
				String dict_table = meta.dict_table();
				String column = meta.column();
				if(dict_table != null && !"".equals(dict_table.trim())){
					String code = String.valueOf(ModelUtil.getValueByColumn(vo, column));
					if(code != null && !"".equals(code)){
						String value = DictionaryCache.getDictValue(dict_table, code, conn, meta);
						ModelUtil.setDictionaryValue(vo, column, value); // 将字典值设置到VO中
					}
				}
			} //End while
		}catch (Exception e){
			logger.error("Message:" + e.getMessage());
			throw e;
		}finally{
			
		}
	}
	
	// 转码
	public static String toAppCharSet(String str) {
		if("ISO8859-1".equals(DBCharSet)){
			return CharSet.ISOtoGBK(str);
		}
		return str;
	}
	
	// 转码
	public static String toDBCharSet(String str) {
		if(str == null)
			return null;
		if("ISO8859-1".equals(DBCharSet)){
			return CharSet.GBKtoISO(str);
		}
		return str;
	}
	
	//转码
	public static String toCharSet(String str) throws UnsupportedEncodingException{
		if(str==null){
			return null;
		}else{
			return new String(str.getBytes("iso-8859-1"), "GBK");
		}
	}
	
	
	// 加载VO中映射的Object对象
	static private void loadVOObject(AbstractBaseVO vo,ResultSet rs,Connection conn) throws Exception {
		Method[] getMethods = ModelCache.getMetaMethods(vo.getClass());
		for(Method getMethod : getMethods){
			boolean hasAnnotation = getMethod.isAnnotationPresent(MappingVO.class);
			if(hasAnnotation && getMethod.getName().startsWith("get")){
				MappingVO obj = getMethod.getAnnotation(MappingVO.class);
				Class clazz = obj.clazz();
				String fkColumn = obj.fkColumn();
				String AutoLoad = obj.autoload();
				if(vo.equals(clazz)) // 不能加载自己的类
					continue;
				if("false".equals(AutoLoad)) // 不做加载
					continue;
				String fkValue = rs.getString(fkColumn); // 外键值
				if(fkValue == null)
					continue;

				AbstractBaseVO mapVO = doFind(clazz, fkValue, conn);
				
				String setMethodName = "set" + getMethod.getName().substring("get".length());
				Method[] setMethods = ModelCache.getSettingMethods(vo.getClass());
				for(Method setmethod : setMethods){
					if(setmethod.getName().equals(setMethodName)){
						if(mapVO == null){
							mapVO = (AbstractBaseVO) clazz.newInstance(); // 为方便，如果关联对象为空，创建一个空对象
						}
						setmethod.invoke(vo, mapVO);
					}
				}
			}
		}
	}

}

