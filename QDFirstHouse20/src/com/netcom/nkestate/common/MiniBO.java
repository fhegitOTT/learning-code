package com.netcom.nkestate.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.netcom.nkestate.framework.AbstractBaseBO;
import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.IModel;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;

public class MiniBO extends AbstractBaseBO {

	private MiniDAO miniDAO = new MiniDAO();

	public IModel find(IModel vo) throws Exception {
		try{
			openDAO(miniDAO);
			vo = miniDAO.find(vo);
			return vo;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：根据ID查找一个对象,必须是单一主键
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel find(Class clazz,long id) throws Exception {
		try{
			openDAO(miniDAO);
			return miniDAO.find(clazz, id);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public long add(IModel vo) throws Exception {
		try{
			openDAO(miniDAO);
			long id = miniDAO.add(vo);
			return id;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：批量插入记录
	 * @param voList
	 * @return
	 */
	public int addBatch(List voList) throws Exception {
		try{
			int count = 0;
			openDAO(miniDAO);
			for(int i = 0; i < voList.size(); i++){
				IModel vo = (IModel) voList.get(i);
				miniDAO.add(vo);
				count++;
			}
			return count;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：更新单条记录，只更新设置值得部分。
	 * @param vo
	 * @return 更新的记录条数
	 * @throws Exception
	 */
	public long update(IModel vo) throws Exception {
		try{
			openDAO(miniDAO);
			long id = miniDAO.updateBySettings(vo);
			return id;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public long updateByTemplate(IModel update,IModel template) throws Exception {
		try{
			openDAO(miniDAO);
			long id = miniDAO.updateByTemplate(update, template);
			return id;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：更新单条记录，更新所有字段
	 * @param vo
	 * @return 更新的记录条数
	 * @throws Exception
	 */
	public long updateAll(IModel vo) throws Exception {
		try{
			openDAO(miniDAO);
			long id = miniDAO.update(vo);
			return id;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：删除单条记录。
	 * @param vo
	 * @return 删除的记录条数
	 * @throws Exception
	 */
	public int delete(IModel vo) throws Exception {
		try{
			openDAO(miniDAO);
			int n = miniDAO.delete(vo);
			return n;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：查询多条记录。
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return List
	 * @throws Exception
	 */
	public List search(Class clazz,List<MetaFilter> params,List<MetaOrder> orders,Page page) throws Exception {
		try{
			openDAO(miniDAO);
			List list = miniDAO.searchByAttibutes(clazz, params, orders, page);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public List search(Class clazz,List<MetaFilter> params) throws Exception {
		List list = null;
		try{
			openDAO(miniDAO);
			list = miniDAO.searchByAttibutes(clazz, params, null, null);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeDAO(miniDAO);
		}
		return list;
	}

	/**
	 * 功能描述：查询多条记录。
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return List
	 * @throws Exception
	 */
	public List searchByTemplate(IModel template,List<MetaOrder> orders,Page page) throws Exception {
		try{
			openDAO(miniDAO);
			List<MetaFilter> params = new ArrayList<MetaFilter>();

			Map<String , Object> setting = template.getSettings();
			for(Map.Entry<String , Object> entry : setting.entrySet()){
				params.add(new MetaFilter(entry.getKey(), "=", entry.getValue()));
			}
			AbstractBaseVO vo = (AbstractBaseVO) template;
			List list = miniDAO.searchByAttibutes(vo.getClass(), params, orders, page);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：查询多条记录。
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return List
	 * @throws Exception
	 */
	public List searchByTemplate(IModel template) throws Exception {
		return this.searchByTemplate(template, null, null);
	}

	public int update(Class clazz,long id,List<MetaField> fields) throws Exception {
		int result;
		try{
			openDAO(miniDAO);
			result = miniDAO.update(clazz, id, fields);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public long update(Class clazz,List<MetaField> whereFields,List<MetaField> fields) throws Exception {
		long result;
		try{
			openDAO(miniDAO);
			result = miniDAO.update(clazz, whereFields, fields);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public long delete(Class clazz,List<MetaFilter> whereFields) throws Exception {
		long result;
		try{
			openDAO(miniDAO);
			result = miniDAO.delete(clazz, whereFields);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	public long deleteByTemplate(IModel template) throws Exception {
		long result;
		try{
			List<MetaFilter> params = new ArrayList<MetaFilter>();

			Map<String , Object> setting = template.getSettings();
			for(Map.Entry<String , Object> entry : setting.entrySet()){
				Object value = entry.getValue();
				if(value == null){
					params.add(new MetaFilter(entry.getKey(), "is", null));
				}else{
					params.add(new MetaFilter(entry.getKey(), "=", entry.getValue()));
				}
			}
			AbstractBaseVO vo = (AbstractBaseVO) template;

			openDAO(miniDAO);
			result = miniDAO.delete(vo.getClass(), params);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：取Sequence的值
	 * @param sequenceCode
	 * @return
	 * @throws Exception
	 */
	protected String getSequenceLpadValue(String sequenceCode,int length,String pad) throws Exception {
		String result;
		try{
			openDAO(miniDAO);
			result = miniDAO.getSequenceLpadValue(sequenceCode, length, pad);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

	/**
	 * 功能描述：取Sequence的值
	 * @param sequenceCode
	 * @return
	 * @throws Exception
	 */
	protected long getSequenceValue(String sequenceCode) throws Exception {
		long result;
		try{
			openDAO(miniDAO);
			result = miniDAO.getSequenceValue(sequenceCode);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(miniDAO);
		}
	}

}
