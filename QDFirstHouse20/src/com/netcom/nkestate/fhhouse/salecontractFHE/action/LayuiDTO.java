package com.netcom.nkestate.fhhouse.salecontractFHE.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netcom.nkestate.framework.Page;

public class LayuiDTO {

	//layui数据表格
	public static Map<String , Object> createTableDTO(List list,Page page) {
		Map<String , Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", page == null ? 0 : page.getRecordCount());
		map.put("data", list);
		return map;
	}

	//layui数据表格
	public static Map<String , Object> createTableDTO(List list) {
		Map<String , Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list == null ? 0 : list.size());
		map.put("data", list);
		return map;
	}

	//出错了
	public static Map<String , Object> error(String msg) {
		Map<String , Object> map = new HashMap<>();
		map.put("code", -1);
		map.put("msg", msg);
		return map;
	}

	//出错了
	public static Map<String , Object> message(int code,String msg) {
		Map<String , Object> map = new HashMap<>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	//消息
	public static Map<String , Object> message(String result,String message) {
		Map<String , Object> map = new HashMap<>();
		map.put("result", result);
		map.put("message", message);
		return map;
	}
}
