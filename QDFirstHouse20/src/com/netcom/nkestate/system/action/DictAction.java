/**
 *<p>SmUserAction.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2016-2-4</p>
 *
 */
package com.netcom.nkestate.system.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping(value = "/system")
public class DictAction {


	/**
	 * 功能描述 ：查询字典表信息
	 * @return
	 */
	@RequestMapping(value = "/getDictionaryJson")
	@ResponseBody
	public JSONArray getDictionaryJson(HttpSession httpSession,HttpServletRequest request) {
		DictionaryBO dictBO = new DictionaryBO();
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<DictVO> list = null;
		JSONObject jo;
		String dictName = request.getParameter("dictName");
		String hasFirst = request.getParameter("hasFirst");//是否增加空值 1：是
		try{
			if(Constant.SysDictMap.containsKey(dictName.toUpperCase())){
				list = Constant.SysDictMap.get(dictName.toUpperCase());
			}else{
				//list = dictBO.getDict(dictName);
			}
			if("1".equals(hasFirst)){
				DictVO nullVO = new DictVO();
				nullVO.setCode(-999);
				nullVO.setName("");
				jo = JSONObject.fromObject(nullVO);
				jsonList.add(jo);
			}
			if(list != null && list.size() > 0){
				for(DictVO vo : list){
					jo = JSONObject.fromObject(vo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述 ：查询字典表信息
	 * @return
	 */
	@RequestMapping(value = "/getUserDistinctJson")
	@ResponseBody
	public JSONArray getUserDistinctJson(HttpSession session,HttpServletRequest request) {
		DictionaryBO dictBO = new DictionaryBO();
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<DictVO> list;
		JSONObject jo;
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String hasFirst = request.getParameter("hasFirst");//是否增加空值 1：是
			list = dictBO.getUserDistinctList(vo.getRegionId(), true, null);
			if("1".equals(hasFirst)){
				DictVO nullVO = new DictVO();
				nullVO.setCode(-999);
				nullVO.setName("");
				jo = JSONObject.fromObject(nullVO);
				jsonList.add(jo);
			}
			if(list != null && list.size() > 0){
				for(DictVO dvo : list){
					jo = JSONObject.fromObject(dvo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述 ：查询合同条款下拉字典表信息
	 * @return
	 */
	@RequestMapping(value = "/getNewDictionaryJson")
	@ResponseBody
	public JSONArray getNewDictionaryJson(HttpSession httpSession,HttpServletRequest request) {
		DictionaryBO dictBO = new DictionaryBO();
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<DictVO> list = new ArrayList<DictVO>();
		JSONObject jo;
		String dictype = request.getParameter("dictype");
		if(dictype == null){
			dictype = "";
		}
		try{
			if(Constant.SysDictMap.containsKey("CT_FCONTRACT_DIC")){
				List<DictVO> list1 = Constant.SysDictMap.get("CT_FCONTRACT_DIC");
				for(DictVO vo : list1){
					if(dictype.equalsIgnoreCase(vo.getAttribute("dictype").toString())){
						list.add(vo);
					}
				}
			}else{
				//list = dictBO.getNewDict(dictype);
			}
			if(list != null && list.size() > 0){
				for(DictVO vo : list){
					jo = JSONObject.fromObject(vo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

}
