package com.netcom.nkestate.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netcom.nkestate.framework.AbstractBaseDAO;
import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.DataBaseFactory;
import com.netcom.nkestate.framework.IModel;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.dao.ModelUtil;

public class MiniDAO extends AbstractBaseDAO  {

	/**
	 * 功能描述：打开数据库连接
	 */	
	public void connect() throws Exception {
		this.conn = DataBaseFactory.getConnection(Constant.MiniConnectionJNDI);
		this.commitFlag = conn.getAutoCommit();
	}

	/**
	 * 功能描述：更新对象的指定属性
	 * @param <T>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(Class clazz,long id,List<MetaField> fields) throws Exception {
		return DataBaseUtil.update(clazz, id, fields, conn);
	}

	/**
	 * 功能描述：据条件批量更新同类对象
	 * @param clazz
	 * @param whereFileds
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	public long update(Class clazz,List<MetaField> whereFileds,List<MetaField> fields) throws Exception {
		return DataBaseUtil.update(clazz, whereFileds, fields, conn);
	}

	/**
	 * 功能描述：据条件批量更新同类对象
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int delete(Class clazz,List<MetaFilter> params) throws Exception {
		return DataBaseUtil.delete(clazz, params, conn);
	}

	/**
	 * 根据对象属性查询列表
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List searchByAttibutes(Class clazz,List<MetaFilter> params,List<MetaOrder> orders ,Page page) 
		throws Exception {
		try	{
			List list = DataBaseUtil.findByAttibutes(clazz, params, orders, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：更新一个对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateNotNullField(IModel model) throws Exception {
		return DataBaseUtil.updateWithoutNullField((AbstractBaseVO) model, conn);
	}

	/**
	 * 功能描述：查询列表
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List search(Class clazz,List<MetaFilter> params,List<MetaOrder> orders,Page page) throws Exception {
		try{
			List list = DataBaseUtil.findByAttibutes(clazz, params, orders, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：根据主键更新一个对象，只更新设置值得部分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long update2(IModel model) throws Exception {
		return this.updateBySettings(model);
	}

	/**
	 * 功能描述：根据主键更新一个对象，只更新设置值得部分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected long updateBySettings(IModel model) throws Exception {
		long result = 0;
		try{
			AbstractBaseVO vo = (AbstractBaseVO) model;
			/*
			 * 根据注解类型返回方法的指定类型注解
			 */
			if(!vo.getClass().isAnnotationPresent(DBModel.class))
				throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

			DBModel annotation = vo.getClass().getAnnotation(DBModel.class);

			List<String> primarykeys = new ArrayList<String>();
			Map<DBMeta , Object> columns = new HashMap<DBMeta , Object>();
			ModelUtil.parseVO(vo, primarykeys, columns); // 解析

			Map<String , Object> setting = model.getSettings();
			List<MetaField> fields = new ArrayList<MetaField>();
			for(Map.Entry<String , Object> entry : setting.entrySet()){
				fields.add(new MetaField(entry.getKey(), entry.getValue()));
			}

			if(primarykeys.size() == 1){

				String value = ModelUtil.getValueByColumn(vo, primarykeys.get(0)).toString();
				result = DataBaseUtil.update(vo.getClass(), value, fields, conn);
			}else{
				List<MetaField> whereFileds = new ArrayList<MetaField>();
				for(String key : primarykeys){
					String value = ModelUtil.getValueByColumn(vo, key).toString();
					String field = ModelUtil.getFieldNameByColumn(vo.getClass(), key);
					whereFileds.add(new MetaField(field, value));
				}
				result = DataBaseUtil.update(vo.getClass(), whereFileds, fields, conn);
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：根据主键更新一个对象，只更新设置值得部分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected long updateByTemplate(IModel updateValue,IModel template) throws Exception {
		long result = 0;
		try{
			AbstractBaseVO vo = (AbstractBaseVO) updateValue;
			/*
			 * 根据注解类型返回方法的指定类型注解
			 */
			if(!vo.getClass().isAnnotationPresent(DBModel.class))
				throw new Exception(vo.getClass().getName() + "缺失DBModel配置信息");

			DBModel annotation = vo.getClass().getAnnotation(DBModel.class);

			List<String> primarykeys = new ArrayList<String>();
			Map<DBMeta , Object> columns = new HashMap<DBMeta , Object>();
			ModelUtil.parseVO(vo, primarykeys, columns); // 解析

			Map<String , Object> setting = updateValue.getSettings();
			List<MetaField> fields = new ArrayList<MetaField>();
			for(Map.Entry<String , Object> entry : setting.entrySet()){
				fields.add(new MetaField(entry.getKey(), entry.getValue()));
			}

			Map<String , Object> settingTemplate = template.getSettings();
			List<MetaField> whereFileds = new ArrayList<MetaField>();
			for(Map.Entry<String , Object> entry : settingTemplate.entrySet()){
				whereFileds.add(new MetaField(entry.getKey(), entry.getValue()));
			}
			result = DataBaseUtil.update(vo.getClass(), whereFileds, fields, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：取Sequence的值
	 * @param sequenceCode
	 * @return
	 * @throws Exception
	 */
	public String getSequenceLpadValue(String sequenceCode,int length,String pad) throws Exception {
		try{
			return DataBaseUtil.getLpadSequenceValue(sequenceCode, length, pad, conn);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：取Sequence的值
	 * @param sequenceCode
	 * @return
	 * @throws Exception
	 */
	public long getSequenceValue(String sequenceCode) throws Exception {
		try{
			return DataBaseUtil.getSequenceID(sequenceCode, conn);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：通过模板批量删除数据
	 * @param template
	 * @return
	 * @throws Exception
	 */
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

			result = DataBaseUtil.delete(vo.getClass(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	

}
