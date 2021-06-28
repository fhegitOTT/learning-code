/**
 *<p>DictionaryCache.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-5-28</p>
 *
 */
package com.netcom.nkestate.framework.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBSupport;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.bo.DictionaryBO;

public class DictionaryCache  {
	static Logger logger = Logger.getLogger(DictionaryCache.class.getName());
	
	protected static SyncObject m_lock = new SyncObject();
	
	private static Map<String , List> m_dict = new HashMap<String , List>();

	/**
	 * 功能描述：获得字典表的值
	 * @param tableName
	 * @param code
	 * @param conn
	 * @param meta
	 * @return
	 */
	public static String getDictValue(String tableName, String code, Connection conn, DBMeta meta) {
		if(!m_dict.containsKey(tableName.toLowerCase()))
			loadDictionaryValue(tableName.toLowerCase(), conn, meta);
		
		if(m_dict.containsKey(tableName.toLowerCase())){
			List list = m_dict.get(tableName.toLowerCase());
			for(int i = 0; i < list.size(); i++){
				DictVO vo = (DictVO) list.get(i);
				if(String.valueOf(vo.getCode()).equals(code))
					return vo.getName();
			}
		}
		return null;
	}


	/**
	 * 获取整张字典表的数据
	 * @param tableName
	 * @param conn
	 * @param meta
	 * @return
	 */
	public static List<DictVO> getDict(String tableName,Connection conn,DBMeta meta){
		if(!m_dict.containsKey(tableName.toLowerCase()))
			loadDictionaryValue(tableName.toLowerCase(), conn, meta);
		
		if(m_dict.containsKey(tableName.toLowerCase())){
			List list = m_dict.get(tableName.toLowerCase());
			return list;
		}
		return null;
		
	}
	
	
	
	// 加载字段缓存
	private static void loadDictionaryValue(String tableName, Connection conn, DBMeta meta) {
		String code = meta.dict_table_code_column();
		String name = meta.dict_table_name_column();
		String sql = "select " + code + "," + name + " from " + tableName + " where status=1 ";
		List<DictVO> list = new ArrayList<DictVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			logger.info("加载字典缓存：" + sql);
			//m_lock.lock();
			synchronized (tableName){
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()){
					DictVO vo = new DictVO();
					vo.setCode(Integer.parseInt(rs.getString(1)));
					vo.setName(DBSupport.toAppCharSet(rs.getString(2)));
					list.add(vo);
				}
				m_dict.put(tableName, list);
			}
		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
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
	}

	/**
	 * 功能描述：缓存加载字典数据
	 * @throws Exception
	 */
	public static void loadSysDict() throws Exception {
		DictionaryBO dictBo = new DictionaryBO();
		try{
			for(String tableName : Constant.SysDictNameList){
				List<DictVO> dictList = dictBo.getDictionaryList(tableName, false, null);
				Constant.SysDictMap.put(tableName, dictList);
				System.out.println(tableName + "加载成功！");
			}
			
		}catch (Exception e){
			throw e;
		}

	}
}
