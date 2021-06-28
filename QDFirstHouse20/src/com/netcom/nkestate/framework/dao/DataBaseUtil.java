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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.DataGrid;
import com.netcom.nkestate.framework.Page;

public class DataBaseUtil {
	
	static Logger logger = Logger.getLogger(DataBaseUtil.class.getName());

	/**
	 * 功能描述：新增一个对象
	 * @param clazz
	 * @param conn
	 * @return 新增对象的ID，如果是复合主键返回值永远为1
	 * @throws Exception
	 */
	public static long add(AbstractBaseVO vo, Connection conn) throws Exception {
		/*
		 * 根据注解类型返回方法的指定类型注解
		 */
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");
		
		DBModel annotation = (DBModel)vo.getClass().getAnnotation(DBModel.class);
		int ID = annotation.id(); // 主键生成方式
		String sequence = annotation.sequence(); // 数据库序列

		List<String> primarykeys = new ArrayList<String>();
		Map<DBMeta,Object> columns = new HashMap<DBMeta,Object>();
		
		ModelUtil.parseVO(vo, primarykeys, columns);
		
		if(ID == DBModel.SequenceID && (sequence == null || sequence.trim().equals(""))) //
			throw new Exception(vo.getClass().getName() + "缺少Sequence");
				
		long id = DBSupport.doAdd(vo, annotation, columns, conn);

		vo = find(vo, conn); // 重新加载所有数据
		
		return id;
	}

	/**
	 * 功能描述：批量增加对象
	 * @param list
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int addBatch(List<AbstractBaseVO> list,Connection conn) throws Exception {
		AbstractBaseVO vo = null;
		if(list == null){
			return -1;
		}
		if(list.size() > 0){
			vo = (AbstractBaseVO) list.get(0);
		}else{
			return 0;
		}
		/*
		 * 根据注解类型返回方法的指定类型注解
		 */
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

		DBModel annotation = vo.getClass().getAnnotation(DBModel.class);
		int ID = annotation.id(); // 主键生成方式
		String sequence = annotation.sequence(); // 数据库序列

		List<String> primarykeys = new ArrayList<String>();
		Map<DBMeta , Object> columns = new HashMap<DBMeta , Object>();

		ModelUtil.parseVO(vo, primarykeys, columns);

		if(ID == DBModel.SequenceID && (sequence == null || sequence.trim().equals(""))) //
			throw new Exception(vo.getClass().getName() + "缺少Sequence");

		int count = DBSupport.doAddBatch(list, annotation, conn);
		return count;
	}

	/**
	 * 更新一个对象
	 */
	public static int update(AbstractBaseVO vo, Connection conn) throws Exception {
		/*
		 * 根据注解类型返回方法的指定类型注解
		 */
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");
		
		DBModel annotation = (DBModel)vo.getClass().getAnnotation(DBModel.class);
		
		List<String> primarykeys = new ArrayList<String>();
		Map<DBMeta,Object> columns = new HashMap<DBMeta,Object>();
		
		ModelUtil.parseVO(vo, primarykeys, columns); // 解析
		
		return DBSupport.doUpdate(vo, annotation, columns, conn); // 执行更新
		
	}

	/**
	 * 功能描述：更新VO对象中非NULL的字段（仅适用于单主键字段）
	 * @param vo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int updateWithoutNullField(AbstractBaseVO vo,Connection conn) throws Exception {
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

		List<MetaField> fields = new ArrayList<MetaField>();
		String id = ModelUtil.getNotNullFields(vo, fields);

		return update(vo.getClass(), id, fields, conn); // 执行更新

	}

	/**
	 * 功能描述：更新对象中的部分属性
	 * @param clazz
	 * @param id
	 * @param fields
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int update(Class clazz, long id, List<MetaField> fields, Connection conn) throws Exception {
		return update(clazz, String.valueOf(id), fields, conn);
	}

	/**
	 * 功能描述：更新对象中的部分属性
	 * @param clazz
	 * 对象类型
	 * @param fields
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int update(Class clazz, String id, List<MetaField> fields, Connection conn) throws Exception {
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		if(fields == null || fields.size() == 0)
			throw new Exception(clazz.getName() + "没有要更新的属性值");
		
		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);
		if(primaryKeys.size() == 0 || primaryKeys.size() > 1)
			throw new Exception(clazz.getName() + "没有主键或主键数量大于1");
		
		String dbname = getDataBaseName(conn);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 拼接SQL语句
		StringBuffer sb = new StringBuffer();
		sb.append("update " + tableName + " set ");		
		try{
			Iterator<MetaField> it = fields.iterator();
			while (it.hasNext()){
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				if(meta == null){
					throw new Exception(clazz.getName() + "没有找到" + field.getField() + "对应的数据库字段");
				}
				sb.append(meta.column() + "=?,");				
			}
			if(sb.lastIndexOf(",") == sb.length() - 1){
				sb.delete(sb.length() - 1, sb.length());
			}
			sb.append(" where " + primaryKeys.get(0) + "=?");
			logger.debug("SQL:" + sb.toString());
			int index = 1;
			pstmt = conn.prepareStatement(sb.toString());
			it = fields.iterator();
			while (it.hasNext()){
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				String primarykey = meta.primarykey();
				String can_update = meta.can_update();
				String type = meta.type().toLowerCase();
				DBSupport.setParameterValue(pstmt, index++, type, field.getValue(), dbname);				
			}
			pstmt.setString(index++, id);
			
			return pstmt.executeUpdate();

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sb.toString());
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}		
	}

	/**
	 * 功能描述：根据条件批量更新同类对象
	 * @param clazz
	 * @param whereFileds
	 * @param modifyFields
	 * @param conn
	 * @return 修改的记录数
	 * @throws Exception
	 */
	public static long update(Class clazz, List<MetaField> whereFileds, List<MetaField> modifyFields, Connection conn) throws Exception {
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");

		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		if(modifyFields == null || modifyFields.size() == 0)
			throw new Exception(clazz.getName() + "没有要更新的属性值");
		if(whereFileds == null || whereFileds.size() == 0)
			throw new Exception(clazz.getName() + "没有要更新的条件值");

		String dbname = getDataBaseName(conn);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 拼接SQL语句
		StringBuffer sb = new StringBuffer();
		sb.append("update " + tableName + " set ");
		try{
			// 更新的字段
			Iterator<MetaField> it = modifyFields.iterator();
			while (it.hasNext()){ 
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				if(meta == null){
					throw new Exception(clazz.getName() + "没有找到" + field.getField() + "对应的数据库字段");
				}
				sb.append(meta.column() + "=?,");
			}
			if(sb.lastIndexOf(",") == sb.length() - 1){
				sb.delete(sb.length() - 1, sb.length());
			}
			sb.append(" where ");
			// 条件的字段
			it = whereFileds.iterator();
			while (it.hasNext()){
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				if(meta == null){
					throw new Exception(clazz.getName() + "没有找到" + field.getField() + "对应的数据库字段");
				}
				sb.append(meta.column() + "=? and ");
			}
			if(sb.lastIndexOf("and ") == sb.length() - 4){
				sb.delete(sb.length() - 4, sb.length());
			}
			logger.debug("Update SQL:" + sb.toString());
			int index = 1;
			pstmt = conn.prepareStatement(sb.toString());
			it = modifyFields.iterator();
			while (it.hasNext()){
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				String primarykey = meta.primarykey();
				String can_update = meta.can_update();
				String type = meta.type().toLowerCase();
				DBSupport.setParameterValue(pstmt, index++, type, field.getValue(), dbname);
			}
			
			it = whereFileds.iterator();
			while (it.hasNext()){
				MetaField field = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, field.getField());
				String primarykey = meta.primarykey();
				String can_update = meta.can_update();
				String type = meta.type().toLowerCase();
				DBSupport.setParameterValue(pstmt, index++, type, field.getValue(), dbname);
			}
			
			return pstmt.executeUpdate();

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sb.toString());
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}
	}
		
	// 得到数据库产品类型,如 oracle, sql server等
	static private String getDataBaseName(Connection conn) throws Exception {
		DatabaseMetaData meta = conn.getMetaData();
		return meta.getDatabaseProductName().toLowerCase();
		//System.out.println("Class:" + meta.getClass().getName());
		//System.out.println("Driver:" + meta.getDriverName());
		//System.out.println("DataBase:" + meta.getDatabaseProductName());
		//System.out.println("DataBase Version:" + meta.getDatabaseProductVersion());		
   }

	/**
	 * 功能描述：根据ID加载一个对象
	 * @param clazz
	 * 对象Class类型
	 * @param id
	 * 对象ID
	 * @param conn
	 * 数据库连接对象
	 * @return
	 * @throws Exception
	 */
	public static AbstractBaseVO find(Class clazz, long id, Connection conn) throws Exception {
		return DBSupport.doFind(clazz, String.valueOf(id), conn);
	}

	/**
	 * 功能描述：根据ID加载一个对象
	 * @param clazz
	 * @param id
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static AbstractBaseVO find(Class clazz, String id, Connection conn) throws Exception {
		return DBSupport.doFind(clazz, id, conn);
	}

	/**
	 * 功能描述：根据复合主键加载一个对象
	 * @param clazz
	 * 类型
	 * @param key
	 * Map是VO属性key和值Value对应
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static AbstractBaseVO find(Class clazz, Map<String,String> keys, Connection conn) throws Exception {
		AbstractBaseVO vo = null;
		
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		DBModel annotation = (DBModel)clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();		
		List<DBMeta> metas = ModelUtil.findMetas(clazz,primaryKeys);
		if(primaryKeys.size()!=keys.size())
			throw new Exception(clazz.getName() + "参数数量与数据库主键数量不一致");
					
		// 拼接SQL语句
		StringBuffer sb = new StringBuffer("select * from " + tableName + " where ");
		for(int i=0; i<primaryKeys.size(); i++) {
			sb.append(primaryKeys.get(i) + " = ? and ");
		}		
		String sql = sb.toString();
		sql = sql.substring(0,sql.length()-"and ".length());
		logger.debug(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try{
			pstmt = conn.prepareStatement(sql);
			int j = 1;
			for(int i=0; i<primaryKeys.size(); i++) {
				String keyColumn = primaryKeys.get(i);
				String field = ModelUtil.getFieldNameByColumn(clazz,keyColumn);
				if(field==null)
					throw new Exception(clazz.getName() + "里没有找到" + keyColumn + "对应的get方法");

				String value = keys.get(field) == null ? keys.get(field.toLowerCase()) : keys.get(field);
				pstmt.setString(j++, DBSupport.toDBCharSet(value));
				logger.debug("field=" + field + ",value=" + value);

			}			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = DBSupport.loadVO(clazz, rs, conn);
				DBSupport.loadDictionaryValue(vo, metas, conn); // 加载字典表
			}
			
		} catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
	}
	
	private static boolean equals(String o1, String o2) {
		return o1.toLowerCase().equals(o2.toLowerCase());
	}

	/**
	 * 功能描述： 根据VO对象中的主键值查询对象
	 * @param vo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static AbstractBaseVO find(AbstractBaseVO vo, Connection conn) throws Exception {
		List<String> primaryKeys = ModelUtil.findPrimaryKeys(vo.getClass());
		Map<String , String> keys = new HashMap<String , String>();
		for(String key : primaryKeys){
			String field = ModelUtil.getFieldNameByColumn(vo.getClass(), key);
			String value = String.valueOf(ModelUtil.getValueByColumn(vo, key));
			keys.put(field, value);
		}		
		vo = find(vo.getClass(), keys, conn);
		return vo;
	}

	/**
	 * 功能描述：根据参数查询对象
	 * @param clazz
	 * 类型
	 * @param params
	 * Map是VO中的属性key和值Value
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<AbstractBaseVO> findByAttibutes(Class clazz,List<MetaFilter> params,Connection conn) throws Exception {
		return findByAttibutes(clazz, params, null, null, null, conn);
	}
	
	//
	public static List<AbstractBaseVO> findByAttibutes(Class clazz, List<MetaFilter> params, Page page, Connection conn) throws Exception {
		return findByAttibutes(clazz, params, null, page, null, conn);
	}
	
	public static List<AbstractBaseVO> findByAttibutes(Class clazz, List<MetaFilter> params, List<MetaOrder> orders, Connection conn) throws Exception {
		return findByAttibutes(clazz, params, null, null, orders, conn);
	}

	public static List<AbstractBaseVO> findByAttibutes(Class clazz,List<MetaFilter> params,List<MetaOrder> orders,Page page,Connection conn) throws Exception {
		return findByAttibutes(clazz, params, null, page, orders, conn);
	}

	/**
	 * 功能描述：根据参数查询对象
	 * @param clazz
	 * 类型
	 * @param params
	 * Map是VO中的属性key和值Value
	 * @param orders
	 * 排序字段
	 * @param page分页对象
	 * , page为空时查询全部记录
	 * @param nkSql
	 * 类似hql语句,用于组织多种复杂条件,格式为 vo属性1>... or (vo属性2<.. and vo属性2>..)
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<AbstractBaseVO> findByAttibutes(Class clazz, List<MetaFilter> params, String nkSql, Page page, List<MetaOrder> orders, Connection conn)
			throws Exception {
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);

		List<AbstractBaseVO> list = new ArrayList<AbstractBaseVO>(); // 返回值

		// 拼接SQL语句
		int index = 0;
		String DBName = getDataBaseName(conn);
		StringBuffer sb = new StringBuffer("select * from " + tableName);
		Iterator<MetaFilter> it = params.iterator();
		while (it.hasNext()){
			MetaFilter filter = it.next();
		
			DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
			if(meta == null)
				throw new Exception(clazz.getName() + "中属性" + filter.getField() + "不存在 ");
			if(isNull(filter.getComparer()))
				throw new Exception("MetaFilter中比较器Comparetor为空 ");

			index++;
			if(index == 1){
				sb.append(" where " + geneWhereSQL(meta, filter, DBName));
			}else{
				sb.append(" and " + geneWhereSQL(meta, filter, DBName));
			}
		}
		// HQL语句处理
		if(nkSql != null && !nkSql.trim().equals("")){  
			String sql = geneWhereSQL(clazz, nkSql, DBName);
			if(params != null && params.size() > 0){
				sb.append(" and " + sql);
			}else{
				sb.append(" where " + sql);
			}
		}
		
		String countSql = new String("select count(1) toltalnum from ( " + sb.toString() + " )");
		
		// 排序字段
		if(orders != null && orders.size() > 0){
			sb.append(" order by ");
			Iterator<MetaOrder> it2 = orders.iterator();					
			while (it2.hasNext()){
				MetaOrder order = it2.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, order.getField());
				sb.append(meta.column() + " " + order.getOrder() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}

		String sql = sb.toString();
		System.out.println("findByAttibutes-------------sql=" + sql+" tableName="+tableName);
		logger.debug(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			long recordCount = 0;
			//判断是否分页
			if(page != null){
				pstmt = conn.prepareStatement(countSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				it = params.iterator();
				index = 1;
				while (it.hasNext()){
					MetaFilter filter = it.next();
					DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
					String comparer = filter.getComparer().trim().toLowerCase();
					Object value = filter.getValue();
					if(meta != null && !"is".equals(comparer) && !"in".equals(comparer)){
						if(value instanceof java.util.Date){
							pstmt.setTimestamp(index++, new java.sql.Timestamp( ( ((Date) value)).getTime()));
						}else if(value instanceof java.lang.Long){
							pstmt.setLong(index++, Long.valueOf(String.valueOf(value)));
						}else if(value instanceof java.lang.Integer){
							pstmt.setInt(index++, Integer.valueOf(String.valueOf(value)));
						}else if(value instanceof java.lang.Double){
							pstmt.setDouble(index++, Double.valueOf(String.valueOf(value)));
						}else if(value instanceof java.lang.Float){
							pstmt.setFloat(index++, Float.valueOf(String.valueOf(value)));
						}else{
							pstmt.setString(index++, DBSupport.toDBCharSet(String.valueOf(filter.getValue())));
						}
					}
				}
				rs = pstmt.executeQuery();
				if(rs.next()){
					recordCount = rs.getLong("toltalnum");
				}
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(recordCount <= 0){
					return list;
				}

				page.setRecordCount(recordCount);
				long startRow = (page.getCurrentPage() - 1) * page.getPageSize();
				long endRow = (page.getCurrentPage()) * page.getPageSize();
				if(startRow >= recordCount){
					return list;
				}
				//分页sql处理
				sql = "select * from ( SELECT aa1.*,rownum as tablern from (" + sql + ")aa1 ) aa2 ";
				sql += " where aa2.tablern>" + startRow + " and aa2.tablern<=" + endRow;
			}


			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			it = params.iterator();
			index = 1;
			while (it.hasNext()){
				MetaFilter filter = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
				String comparer = filter.getComparer().trim().toLowerCase();
				Object value = filter.getValue();
				if(meta != null && !"is".equals(comparer) && !"in".equals(comparer)){
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(index++, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(index++, Long.valueOf(String.valueOf(value)));						
					}else if(value instanceof java.lang.Integer){
						pstmt.setInt(index++, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(index++, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(index++, Float.valueOf(String.valueOf(value)));		
					}else{
						pstmt.setString(index++, DBSupport.toDBCharSet( String.valueOf(filter.getValue())));						
					}
				}
			}
			rs = pstmt.executeQuery();


			while (rs.next()){
				AbstractBaseVO vo = DBSupport.loadVO(clazz, rs, conn);
				DBSupport.loadDictionaryValue(vo, metas, conn); // 加载字典表
				list.add(vo);
			}

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 判断字符串对象是否为空
	private static boolean isNull(String s) {
		if(s==null || s.trim().equals("")) 
			return true;
		return false;
	}
	
	// 生成SQL语句，主要用于生成查询条件
	private static String geneWhereSQL(DBMeta meta,MetaFilter filter,String DBName) {
		String sql = null;
		if("is".equals(filter.getComparer().toLowerCase().trim())){
			sql = meta.column() + " " + filter.getComparer() + " " + filter.getValue();
		} else if("in".equals(filter.getComparer().toLowerCase().trim())){
			Object value = filter.getValue();
			if(value instanceof String[]){
				String[] temp = (String[]) value;
				StringBuffer sb = new StringBuffer();
				sb.append("(");
				for(String str : temp){
					sb.append("'").append(str).append("',");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(")");
				sql = meta.column() + " " + filter.getComparer() + " " + sb.toString();
			}else if(value instanceof List){
				List temp = (List) value;
				StringBuffer sb = new StringBuffer();
				sb.append("(");
				for(Object str : temp){
					sb.append("'").append(String.valueOf(str)).append("',");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append(")");
				sql = meta.column() + " " + filter.getComparer() + " " + sb.toString();
			}else{
				sql = meta.column() + " " + filter.getComparer() + " " + filter.getValue();
			}
		} else {
			sql = meta.column() + " " + filter.getComparer() + " ? ";
		}
		return sql;
	}
	
	// 解析nkSql语句
	private static String geneWhereSQL(Class clazz, String nkSql, String dbname) {
		String sql = nkSql;
		String[] ss = nkSql.split(" ");
		for(String s : ss){
			if(s == null || "".trim().equals(s))
				continue;
			DBMeta meta = ModelUtil.getColumnByField(clazz, s);
			if(meta != null){
				String dbfield = meta.column();
				sql.replaceAll(s, dbfield);
			}
		}
		return sql;
	}

	/**
	 * 功能描述：执行SQL语句，返回List
	 * @param SQL
	 * SQL语句中带有查询条件占位符
	 * @param params
	 * 参数值
	 * @param clazz
	 * 返回的类对象
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<AbstractBaseVO> select(String sql,List<Object> params,Class clazz,Connection conn) throws Exception {
		return select(sql, params, clazz, null, conn);
	}

	/**
	 * 功能描述：分页查询
	 * @param sql
	 * @param params
	 * @param clazz
	 * @param page
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<AbstractBaseVO> select(String sql,List<Object> params,Class clazz,Page page,Connection conn) throws Exception {
		return select(sql, params, null, clazz, page, conn);
	}

	/**
	 * 功能描述：分页查询
	 * @param sql
	 * @param params
	 * @param clazz
	 * @param page
	 * 分页信息
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<AbstractBaseVO> select(String sql,List<Object> params,List<MetaOrder> orders,Class clazz,Page page,Connection conn) throws Exception {
		if(!sql.toLowerCase().trim().startsWith("select"))
			throw new Exception("非法的查询语句," + sql);
		List<AbstractBaseVO> list = new ArrayList<AbstractBaseVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try{
			String dbname = getDataBaseName(conn);			
			if("oracle".equals(dbname)){
				// 将来针对不同数据库做特定处理
			}

			long recordCount = 0;
			//判断是否分页
			if(page != null){
				String countSql = new String(sql);
				countSql = "select count(1) toltalnum from ( " + countSql + " )";
				pstmt = conn.prepareStatement(countSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if(params != null && params.size() > 0){
					for(int i = 0; i < params.size(); i++){
						Object value = params.get(i);
						if(value instanceof Date){
							pstmt.setTimestamp(i + 1, new java.sql.Timestamp( ( ((Date) value)).getTime()));
						}else if(value instanceof Long){
							pstmt.setLong(i + 1, Long.valueOf(String.valueOf(value)));
						}else if(value instanceof Integer){
							pstmt.setInt(i + 1, Integer.valueOf(String.valueOf(value)));
						}else if(value instanceof Double){
							pstmt.setDouble(i + 1, Double.valueOf(String.valueOf(value)));
						}else if(value instanceof Float){
							pstmt.setFloat(i + 1, Float.valueOf(String.valueOf(value)));
						}else{
							pstmt.setString(i + 1, DBSupport.toDBCharSet(String.valueOf(value))); //默认为字符
						}
					}
				}
				rs = pstmt.executeQuery();
				if(rs.next()){
					recordCount = rs.getLong("toltalnum");
				}
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(recordCount <= 0){
					return list;
				}
			}

			sql = rebuildOrderSQL(sql, orders); // 增加排序字段 daobin 2015.08.26

			//判断是否分页
			if(page != null){
				page.setRecordCount(recordCount);
				long startRow = (page.getCurrentPage() - 1) * page.getPageSize();
				long endRow = (page.getCurrentPage()) * page.getPageSize();
				if(startRow >= recordCount){
					return list;
				}
				sql = "select * from ( SELECT aa1.*,rownum as tablern from (" + sql + ")aa1 ) aa2 ";
				sql += " where aa2.tablern>" + startRow + " and aa2.tablern<=" + endRow;
			}

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(params != null && params.size() > 0){
				for(int i = 0; i < params.size(); i++){
					Object value = params.get(i);
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(i + 1, Long.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Integer){
						pstmt.setInt(i + 1, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(i + 1, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(i + 1, Float.valueOf(String.valueOf(value)));
					}else{
						pstmt.setString(i + 1, DBSupport.toDBCharSet(String.valueOf(value))); //默认为字符
					}

				}
			}
			rs = pstmt.executeQuery();
			

			
			List<String> primaryKeys = new ArrayList<String>();
			List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);
			
			while (rs.next()){
				AbstractBaseVO vo = DBSupport.loadVO(clazz, rs, conn);
				if(metas != null && metas.size() > 0){
					DBSupport.loadDictionaryValue(vo, metas, conn); // 加载字典表
				}
				list.add(vo);
			}

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	// 得到排序SQL
	static private String rebuildOrderSQL(String sql,List<MetaOrder> orders) {
		if(orders == null || orders.size() == 0)
			return sql;

		StringBuffer sb = new StringBuffer("select * from (" + sql + ")");
		if(orders != null && orders.size() > 0){
			int i = 0;
			for(MetaOrder order : orders){
				i++;
				if(i == 1){
					sb.append(" order by " + order.getField());
					if(order.getOrder() != null && !order.getOrder().equals(""))
						sb.append(" " + order.getOrder());
				}else{
					sb.append(" ," + order.getField());
					if(order.getOrder() != null && !order.getOrder().equals(""))
						sb.append(" " + order.getOrder());
				}

			}
		}
		return sb.toString();
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
		return DBSupport.loadVO(clazz, rs,conn);
	}

	/**
	 * 功能描述：加载一个对象，并自动加载字典表
	 * @param clazz
	 * 类型
	 * @param rs
	 * 结果集
	 * @return
	 * @throws Exception
	 */
	static public AbstractBaseVO loadVOWithDictValue(Class clazz, ResultSet rs, Connection conn) throws Exception {
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);
		AbstractBaseVO vo = DBSupport.loadVO(clazz, rs, conn);
		DBSupport.loadDictionaryValue(vo, metas, conn); // 加载字典表
		return vo;
	}

	/**
	 * 功能描述：删除一个对象
	 * @param vo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int delete(AbstractBaseVO vo, Connection conn) throws Exception {
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

		DBModel annotation = (DBModel) vo.getClass().getAnnotation(DBModel.class);
		StringBuffer sb = new StringBuffer();
		sb.append("delete from " + annotation.tablename() + " where ");
		List<String> keys = ModelUtil.findPrimaryKeys(vo.getClass());
		Iterator<String> it = keys.iterator();
		while (it.hasNext()){
			String key = it.next();
			sb.append(" " + key + "=? and");
		}
		// 处理SQL语句
		String sql = sb.toString();
		if(sql.endsWith(" and"))
			sql = sql.substring(0, sql.length() - " and".length());
		logger.debug("Delete SQL=" + sql);
		PreparedStatement pstmt = null;
		try{
			// 数据库操作准备
			String dbname = getDataBaseName(conn);
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < keys.size(); i++){
				Object keyValue = ModelUtil.getValueByColumn(vo, keys.get(i));
				DBSupport.setParameterValue(pstmt, index++, "String", String.valueOf(keyValue), dbname);
			}
			return pstmt.executeUpdate();
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(pstmt);
		}
	}

	/**
	 * 功能描述：删除一个对象
	 * @param clazz
	 * @param id
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int delete(Class clazz, long id, Connection conn) throws Exception {
		return delete(clazz, String.valueOf(id), conn);
	}

	/**
	 * 功能描述：删除一个对象
	 * @param clazz
	 * @param id
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int delete(Class clazz, String id, Connection conn) throws Exception {
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");

		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);		
		List<String> keys = ModelUtil.findPrimaryKeys(clazz);
		if(keys.size()!=1) 
			throw new Exception(clazz.getName() + "主键不止一个，不能执行此删除操作");
		
		StringBuffer sb = new StringBuffer();
		sb.append("delete from " + annotation.tablename() + " where " + keys.get(0) + "=?");		
		PreparedStatement pstmt = null;
		try{
			// 数据库操作准备
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sb.toString());
			throw e;
		}finally{
			close(pstmt);
		}		
	}

	/**
	 * 功能描述：根据复合主键删除对象
	 * @param clazz
	 * @param keys
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int delete(Class clazz, Map<String , String> keys, Connection conn) throws Exception {
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);
		if(primaryKeys.size() != keys.size())
			throw new Exception(clazz.getName() + "参数数量与数据库主键数量不一致");

		// 拼接SQL语句
		StringBuffer sb = new StringBuffer("delete from " + tableName + " where ");
		for(int i = 0; i < primaryKeys.size(); i++){
			if(i == 0){
				sb.append(primaryKeys.get(i) + " = ? ");
			}else{
				sb.append(" and " + primaryKeys.get(i) + " = ? ");
			}
		}
		String sql = sb.toString();		
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(sql);
			int j = 1;
			for(int i = 0; i < primaryKeys.size(); i++){
				String keyColumn = primaryKeys.get(i);
				String field = ModelUtil.getFieldNameByColumn(clazz, keyColumn);
				if(field == null)
					throw new Exception(clazz.getName() + "里没有找到" + keyColumn + "对应的get方法");
				if(equals(keyColumn, field)){
					String value = keys.get(field) == null ? keys.get(field.toLowerCase()) : keys.get(field);
					pstmt.setString(j++, DBSupport.toDBCharSet(value));
				}
			}
			int ret = pstmt.executeUpdate();
			if(ret > 1)
				throw new Exception(clazz.getName() + "删除的记录数大于1");
			
			return ret;

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(pstmt);
		}
	}

	/**
	 * 功能描述：根据过滤条件删除一组对象
	 * @param clazz
	 * @param params
	 * @param conn
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public static int delete(Class clazz, List<MetaFilter> params, Connection conn) throws Exception {
		// 根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		if(params == null || params.size() == 0)
			throw new Exception(clazz.getName() + "缺少删除条件");
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); // 数据库表名

		// 解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);

		// 拼接SQL语句
		int index = 0;
		String DBName = getDataBaseName(conn);
		StringBuffer sb = new StringBuffer("delete from " + tableName);
		Iterator<MetaFilter> it = params.iterator();
		while (it.hasNext()){
			MetaFilter filter = it.next();
			DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
			if(meta == null)
				throw new Exception(clazz.getName() + "中属性" + filter.getField() + "不存在 ");
			if(isNull(filter.getComparer()))
				throw new Exception("MetaFilter中比较器Comparetor为空 ");

			index++;
			if(index == 1){
				sb.append(" where " + geneWhereSQL(meta, filter, DBName));
			}else{
				sb.append(" and " + geneWhereSQL(meta, filter, DBName));
			}
		}		

		String sql = sb.toString();
		logger.debug(sql);
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(sql);
			it = params.iterator();
			index = 1;
			while (it.hasNext()){
				MetaFilter filter = it.next();
				Object value = filter.getValue();
				DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
				if(meta != null){
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(index++, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else{
						pstmt.setString(index++, DBSupport.toDBCharSet( String.valueOf(filter.getValue())));
					}
				}
			}
			
			return pstmt.executeUpdate();
			
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(pstmt);
		}		
	}

	/**
	 * 功能描述：执行SQL语句
	 * @param sql
	 * @param params
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static int execute(String sql,List<Object> params,Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(DBSupport.toDBCharSet(sql));
			if(params != null && params.size() > 0){
				for(int i = 0; i < params.size(); i++){
					Object value = params.get(i);
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(i + 1, Long.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Integer){
						pstmt.setInt(i + 1, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(i + 1, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(i + 1, Float.valueOf(String.valueOf(value)));
					}else{
						pstmt.setString(i + 1, DBSupport.toDBCharSet(String.valueOf(value))); //默认为字符
					}
				}
			}
			return pstmt.executeUpdate();

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(pstmt);
		}		
	}

	/**
	 * 功能描述：执行SQL语句，返回结果记录集合
	 * @param sql
	 * @param params
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql,List<Object> params,Connection conn,PreparedStatement pstmt) throws Exception {
		try{
			if(pstmt == null)
				pstmt = conn.prepareStatement(DBSupport.toDBCharSet(sql));
			if(params != null && params.size() > 0){
				for(int i = 0; i < params.size(); i++){
					Object value = params.get(i);
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(i + 1, Long.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Integer){
						pstmt.setInt(i + 1, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(i + 1, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(i + 1, Float.valueOf(String.valueOf(value)));
					}else{
						pstmt.setString(i + 1, DBSupport.toDBCharSet(String.valueOf(value))); //默认为字符
					}

				}
			}
			
			return pstmt.executeQuery();
			
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			
		}
	}

	/**
	 * 功能描述：执行SQL语句，返回结果记录集合(String做了转码处理)
	 * @param sql
	 * @param params
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String , Object>> executeQuery(String sql,List<String> params,Connection conn) throws Exception {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(DBSupport.toDBCharSet(sql));
			if(params != null && params.size() > 0){
				for(int i = 0; i < params.size(); i++){
					Object value = params.get(i);
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(i + 1, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(i + 1, Long.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Integer){
						pstmt.setInt(i + 1, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(i + 1, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(i + 1, Float.valueOf(String.valueOf(value)));
					}else{
						pstmt.setString(i + 1, DBSupport.toDBCharSet(String.valueOf(value))); //默认为字符
					}
				}
			}
			
			rs = pstmt.executeQuery();
			ResultSetMetaData rm = rs.getMetaData();
			int columnCount = rm.getColumnCount();
			while (rs.next()){
				Map<String , Object> map = new HashMap<String , Object>();
				for(int i = 1; i <= columnCount; i++){
					String columnName = rm.getColumnName(i);
					Object value = rs.getObject(i);
					if(value != null && value instanceof String){
						map.put(columnName.toLowerCase(), DBSupport.toAppCharSet(value.toString()));
					}else{
						map.put(columnName.toLowerCase(), value);
					}
				}
				list.add(map);
			}

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 功能描述：查询Sequence的值,只适用于Oracle
	 * @param sequenceName
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static long getSequenceID(String sequenceName, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("select " + sequenceName + ".nextval as id from dual");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getLong(1);

			return 0;
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage());
			throw e;
		}finally{
			close(pstmt);
		}
	}

	/**
	 * 功能描述：查询Sequence的值,左侧可补位，如“000001”，只适用于Oracle
	 * @param sequenceName
	 * @param length
	 * @param pad
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static String getLpadSequenceValue(String sequenceName, int length, String pad, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("select lpad(" + sequenceName + ".nextval," + length + ",'"  + pad + "') as id from dual");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getString(1);

			return "";
		}catch (SQLException e){
			logger.error("Message:" + e.getMessage());
			throw e;
		}finally{
			close(pstmt);
		}
	}

	/**
	 * 功能描述：查询Sequence的值,只适用于Oracle
	 * @param vo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static long getSequenceID(AbstractBaseVO vo, Connection conn) throws Exception {
		if(!vo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

		DBModel annotation = (DBModel) vo.getClass().getAnnotation(DBModel.class);
		int ID = annotation.id(); // 主键生成方式
		String sequence = annotation.sequence(); // 数据库序列
		if(sequence == null || "".equals(sequence.trim()))
			throw new Exception(vo.getClass().getName() + "缺失Sequence信息");

		return getSequenceID(sequence, conn);
	}
	
	
	// 关闭对象
	private static void close(ResultSet rs) {
		try{
			rs.close();
		}catch (Exception e){
		}
	}

	// 关闭对象
	private static void close(PreparedStatement pstmt) {
		try{
			pstmt.close();
		}catch (Exception e){
		}
	}

	/**
	 * 功能描述：统计
	 * @param clazz
	 * @param stats统计字段
	 * @param groupfields分组字段
	 * @param params条件字段
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static DataGrid statByAttibutes(Class clazz,List<MetaStatistic> stats,List<String> groupfields,List<MetaFilter> params,Connection conn) throws Exception {
		//根据注解类型返回方法的指定类型注解
		if(!clazz.isAnnotationPresent(DBModel.class))
			throw new Exception(clazz.getName() + "缺失DBModel配置信息");
		DBModel annotation = (DBModel) clazz.getAnnotation(DBModel.class);
		String tableName = annotation.tablename(); //数据库表名		

		//解析数据库字段，得到主键
		List<String> primaryKeys = new ArrayList<String>();
		List<DBMeta> metas = ModelUtil.findMetas(clazz, primaryKeys);

		DataGrid grid = new DataGrid(); //返回值
		StringBuffer sbStat = new StringBuffer();
		StringBuffer sbHaving = new StringBuffer();
		StringBuffer sbWhere = new StringBuffer();
		StringBuffer sbGroup = new StringBuffer();
		StringBuffer sbGroup2 = new StringBuffer();

		//统计字段
		for(MetaStatistic stat : stats){
			DBMeta meta = ModelUtil.getColumnByField(clazz, stat.getField());
			if(meta == null)
				sbStat.append(" " + stat.getMethod() + "(" + stat.getField() + ") as " + stat.getAlias() + ",");
			else
				sbStat.append(" " + stat.getMethod() + "(" + meta.column() + ") as " + stat.getAlias() + ",");

			if(stat.getHaving() != null){
				sbHaving.append(" " + stat.getHaving() + ",");
			}
		}
		if(sbStat.lastIndexOf(",") > 0)
			sbStat.deleteCharAt(sbStat.lastIndexOf(","));
		if(sbHaving.lastIndexOf(",") > 0)
			sbHaving.deleteCharAt(sbHaving.lastIndexOf(","));

		//拼接SQL语句		
		int index = 0;
		String DBName = getDataBaseName(conn);
		Iterator<MetaFilter> it = params.iterator();
		while (it.hasNext()){
			MetaFilter filter = it.next();
			DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
			if(meta == null)
				throw new Exception(clazz.getName() + "中属性" + filter.getField() + "不存在 ");
			if(isNull(filter.getComparer()))
				throw new Exception("MetaFilter中比较器Comparetor为空 ");

			index++;
			if(index == 1){
				sbWhere.append(" where " + geneWhereSQL(meta, filter, DBName));
			}else{
				sbWhere.append(" and " + geneWhereSQL(meta, filter, DBName));
			}
		}

		if(groupfields != null && groupfields.size() > 0){
			sbGroup.append(" group by ");
			for(String field : groupfields){
				sbGroup.append(field + ",");
			}
			if(sbGroup.lastIndexOf(",") > 0)
				sbGroup.deleteCharAt(sbGroup.lastIndexOf(","));
		}

		if(groupfields != null && groupfields.size() > 0){
			for(String field : groupfields){
				sbGroup2.append(field + ",");
			}
		}

		String sql = "select " + sbGroup2 + sbStat + " from " + tableName + sbWhere + sbGroup + sbHaving;
		logger.debug(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			it = params.iterator();
			index = 1;
			while (it.hasNext()){
				MetaFilter filter = it.next();
				DBMeta meta = ModelUtil.getColumnByField(clazz, filter.getField());
				String comparer = filter.getComparer().trim().toLowerCase();
				Object value = filter.getValue();
				if(meta != null && !"is".equals(comparer) && !"in".equals(comparer)){
					if(value instanceof java.util.Date){
						pstmt.setTimestamp(index++, new java.sql.Timestamp( ( ((Date) value)).getTime()));
					}else if(value instanceof java.lang.Long){
						pstmt.setLong(index++, Long.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Integer){
						pstmt.setLong(index++, Integer.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Double){
						pstmt.setDouble(index++, Double.valueOf(String.valueOf(value)));
					}else if(value instanceof java.lang.Float){
						pstmt.setFloat(index++, Float.valueOf(String.valueOf(value)));
					}else{
						pstmt.setString(index++, DBSupport.toDBCharSet(String.valueOf(filter.getValue())));
					}
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsMeta = rs.getMetaData();

			grid.fillData(rs, rsMeta);

		}catch (SQLException e){
			logger.error("Message:" + e.getMessage() + ",SQL=" + sql);
			throw e;
		}finally{
			close(rs);
			close(pstmt);
		}
		return grid;
	}
}
