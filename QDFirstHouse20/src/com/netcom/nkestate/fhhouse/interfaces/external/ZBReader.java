package com.netcom.nkestate.fhhouse.interfaces.external;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.HttpUtil;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ZBQueryLogVO;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.util.DateUtil;
public class ZBReader {

	/**
	 * 功能描述：获取json字符串
	 * @param interfaceName
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private String getJson(String interfaceName,List<MetaField> params) throws Exception {
		String baseUrl = Constant.ZBURL;
		String postData = "";
		for(MetaField field : params){
			postData += field.getField() + "=" + field.getValue() + "&";
		}
		if(postData.length() > 0){
			postData = postData.substring(0, postData.length() - 1);
		}
		String responseStr = HttpUtil.sendPost(baseUrl + interfaceName, postData);
		//		JSONObject jsonObj = JSONObject.fromObject(responseStr);
		return responseStr;
		//		String result = jsonObj.getString("result");
		//		if(result.equals("1")){
		//			return responseStr;
		//		}else if(result.equals("0")){
		//			return null;
		//		}else{
		//			String msg = jsonObj.getString("msg");
		//			throw new Exception(msg);
		//		}
	}


	/**
	 * 功能描述：将json对象转换成map对象
	 * @param object
	 * @return
	 */
	private HashMap<String , String> toHashMap(Object object) {
		HashMap<String , String> data = new HashMap<String , String>();
		// 将json字符串转换成jsonObject  
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象  
		while (it.hasNext()){
			String key = String.valueOf(it.next());
			String value = String.valueOf(jsonObject.get(key));
			data.put(key, value);
		}
		return data;
	}

	/**
	 * 功能描述：获取令牌
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public HashMap getToken(String username,String password) throws Exception {
		HashMap<String , String> map = new HashMap<String , String>();
		String response = "";
		String request = "username=" + username + ",password=" + password;
		try{
			List<MetaField> params = new ArrayList<MetaField>();
			params.add(new MetaField("username", username));
			params.add(new MetaField("password", password));

			String interfaceName = "/services/zbsync/Login";

			response = getJson(interfaceName, params);
			if(response == null){
				return null;
			}else{
				JSONObject jsonObj = JSONObject.fromObject(response);
				map = this.toHashMap(jsonObj);
				if("-1".equals(map.get("result").toString())){//-1表示接口异常，
					return null;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//保存日志
			SignContractBO sBo = new SignContractBO();
			ZBQueryLogVO logVo = new ZBQueryLogVO();
			logVo.setRequest(request);
			logVo.setResponse(response);
			logVo.setQueryDate(DateUtil.getSysDate());
			sBo.add(logVo);
		}
		return map;
	}

	/**
	 * 功能描述：根据姓名和身份证获取住保中间库数据
	 * @param name
	 * @param cardNo
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public HashMap getZBHouseInfo(String name,String cardNo,String token) throws Exception {
		HashMap<String , String> map = new HashMap<String , String>();
		String response = "";
		String request = "name=" + name + ",cardNo=" + cardNo + ",token=" + token;
		try{
			List<MetaField> params = new ArrayList<MetaField>();
			params.add(new MetaField("name", name));
			params.add(new MetaField("cardNo", cardNo));
			params.add(new MetaField("token", token));

			String interfaceName = "/services/zbsync/checkZBSyncHouse";

			response = getJson(interfaceName, params);
			if(response == null){
				return null;
			}else{
				JSONObject jsonObj = JSONObject.fromObject(response);
				map = this.toHashMap(jsonObj);
				if("-1".equals(map.get("result").toString())){
					return null;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			//保存日志
			SignContractBO sBo = new SignContractBO();
			ZBQueryLogVO logVo = new ZBQueryLogVO();
			logVo.setName(name);
			logVo.setCardNo(cardNo);
			logVo.setRequest(request);
			logVo.setResponse(response);
			logVo.setQueryDate(DateUtil.getSysDate());
			sBo.add(logVo);
		}
		return map;
	}
}
