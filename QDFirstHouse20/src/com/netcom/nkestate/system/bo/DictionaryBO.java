/**
 *<p>DictionaryBO.java</p>
 *
 *<p>��Ŀ��ƣ�������Ŀ</p>
 *<p>ϵͳ��ƣ������������ʽ���ϵͳ</p>
 *<p>����������DictionaryBO-ϵͳ����ƽ̨�ֵ�?��BO</p>
 *
 *<p>��˾��ƣ��Ϻ��Ͽ��Ƽ����޹�˾</p> 
 *<p>��   ��: ����</p>
 *<p>��������: 2014-7-31</p>
 *
 */
package com.netcom.nkestate.system.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.dao.DictionaryDAO;

@Service
public class DictionaryBO extends MiniBO {


	private DictionaryDAO dictDao = new DictionaryDAO();
	static Logger logger = Logger.getLogger(DictionaryBO.class.getName());

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
			openDAO(dictDao);
			return dictDao.getDictionaryList(tableName, isValid, code);
		}catch (Exception e){
			logger.error("加载字典表出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(dictDao);
		}
	}


	/**
	 * 功能描述：获取缓存字典表内容
	 * @param tableName
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> getDict(String tableName) throws Exception {
		try{
			openDAO(dictDao);
			List<DictVO> list = dictDao.getDictList(tableName, DictVO.class);
			return list;
		}catch (Exception e){
			throw e;
		}finally{
			closeDAO(dictDao);
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
			openDAO(dictDao);
			List<DictVO> list = dictDao.getUserDistinctList(userDistinct, isValid, code);
			return list;
		}catch (Exception e){
			throw e;
		}finally{
			closeDAO(dictDao);
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
			openDAO(dictDao);
			List<DictVO> list = dictDao.getNewDict(dictype);
			return list;
		}catch (Exception e){
			throw e;
		}finally{
			closeDAO(dictDao);
		}
	}

	/**
	 * 根据房屋用途获取房屋分类数据
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public int getHouseLandType(String code) throws Exception {
		try{
			openDAO(dictDao);
			int landType = FHConstant.LAND_TYPE_ETC;
			if(code == null || "".equals(code)){
				return landType;
			}

			List<DictVO> list = dictDao.getDictionaryList("CT_540", false, code);
			if(list != null && list.size() > 0){
				DictVO vo = list.get(0);
				if(vo.getAttribute("type") != null){
					landType = Integer.parseInt(vo.getAttribute("type").toString());
				}
			}
			return landType;
		}catch (Exception e){
			logger.error("根据房屋用途获取房屋分类数据出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(dictDao);
		}
	}

	/**
	 * 功能描述：获取字典表数据
	 * @param tableName
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDictName(String tableName,String code) throws Exception {
		String name = "";
		if(tableName == null || "".equals(tableName) || code == null || "".equals(code) || "null".equals(code)){
			return name;
		}
		try{
			if(Constant.SysDictMap.containsKey(tableName.toUpperCase())){
				List<DictVO> list = Constant.SysDictMap.get(tableName.toUpperCase());
				if(list != null && list.size() > 0){
					for(DictVO vo : list){
						if(vo.getCode() == Integer.parseInt(code)){
							name = vo.getName();
							if(name == null){
								name = "";
							}
						}
					}
				}
			}

		}catch (Exception e){
			logger.error("获取字典表数据出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return name;
	}
}
