/**
 *<p>DictionaryDAO.java</p>
 *
 *<p>��Ŀ��ƣ�������Ŀ</p>
 *<p>ϵͳ��ƣ������������ʽ���ϵͳ</p>
 *<p>����������DictionaryDAO-ϵͳ����ƽ̨�ֵ�?��DAO</p>
 *
 *<p>��˾��ƣ��Ϻ��Ͽ��Ƽ����޹�˾</p> 
 *<p>��   ��: ����</p>
 *<p>��������: 2014-7-31</p>
 *
 */
package com.netcom.nkestate.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.framework.cache.DictionaryCache;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.dao.ModelUtil;
import com.netcom.nkestate.framework.vo.DictVO;

@Repository
public class DictionaryDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(DictionaryDAO.class.getName());

	/**
	 * 功能描述：获取字典表数据
	 * @param tableName
	 * @param isValid
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> getDictionaryList(String tableName,boolean isValid,String code) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT * FROM " + tableName);
			sb.append(" where 1=1");
			//判断是否有效记录
			if(isValid){
				sb.append(" and status=1 ");
			}
			if(code != null && !"".equals(code)){
				sb.append(" and code = ? ");
				params.add(String.valueOf(code));
			}
			sb.append(" order by code ");

			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：获取缓存字典表内容
	 * @param tableName
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> getDictList(String tableName,Class cl) throws Exception {
		try{
			List<String> primaryKeys = new ArrayList<String>();
			List<DBMeta> meta = ModelUtil.findMetas(cl, primaryKeys);
			return DictionaryCache.getDict(tableName, conn, meta.get(0));
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：获取用户操作区县字典表数据
	 * @param userDistinct
	 * @param isValid
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> getUserDistinctList(int userDistinct,boolean isValid,String code) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" select distinct a.* ");
			sb.append(" from ct_district a,ct_district_range b ");
			sb.append(" where a.code = b.targetdistrictid ");
			sb.append(" and b.districtid = ? ");
			params.add(String.valueOf(userDistinct));
			//判断是否有效记录
			if(isValid){
				sb.append(" and a.status=1 ");
			}
			if(code != null && !"".equals(code)){
				sb.append(" and a.code = ? ");
				params.add(String.valueOf(code));
			}
			sb.append(" order by a.code ");

			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述 ：查询新版合同字典表信息
	 * @param dictype
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> getNewDict(String dictype) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" select t.name from ct_fcontract_dic t ");
			sb.append(" where t.dictype=? ");
			params.add(String.valueOf(dictype));
			sb.append(" order by t.code ");
			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
