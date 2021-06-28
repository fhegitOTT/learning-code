package com.netcom.nkestate.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.netcom.nkestate.fhhouse.FHConstant;


public class BaseAction {

	final static public String ERROR_System = "/error/Error";//系统出错

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
	}

	/**
	 * 功能描述：获取用户操作区县列表
	 * @param userDistrictID
	 * @return
	 */
	protected List<Object> getUserDistricts(int userDistrictID) {
		List<Object> districtList = new ArrayList<Object>();
		if(userDistrictID == FHConstant.REG_DISTRICT_TYPE_SH || userDistrictID == FHConstant.REG_DISTRICT_TYPE_SN
				|| userDistrictID == FHConstant.REG_DISTRICT_TYPE_SB || userDistrictID == FHConstant.REG_DISTRICT_TYPE_SF
				|| userDistrictID == FHConstant.REG_DISTRICT_TYPE_LC){
			districtList.add(String.valueOf(FHConstant.REG_DISTRICT_TYPE_SH));
			districtList.add(String.valueOf(FHConstant.REG_DISTRICT_TYPE_SN));
			districtList.add(String.valueOf(FHConstant.REG_DISTRICT_TYPE_SB));
			districtList.add(String.valueOf(FHConstant.REG_DISTRICT_TYPE_SF));
			districtList.add(String.valueOf(FHConstant.REG_DISTRICT_TYPE_LC));
		}else{
			districtList.add(String.valueOf(userDistrictID));
		}

		return districtList;

	}

	/**
	 * 功能描述：获取用户sql查询的区县条件
	 * @param userDistrictID
	 * @return
	 */
	protected String getUserSqlDistricts(int userDistrictID) {
		String str = "";
		if(userDistrictID == FHConstant.REG_DISTRICT_TYPE_SH || userDistrictID == FHConstant.REG_DISTRICT_TYPE_SN
				|| userDistrictID == FHConstant.REG_DISTRICT_TYPE_SB || userDistrictID == FHConstant.REG_DISTRICT_TYPE_SF
				|| userDistrictID == FHConstant.REG_DISTRICT_TYPE_LC){
			str = "(0,2,3,5,13)";
		}else{
			str = "(" + userDistrictID + ")";
		}

		return str;
	}
}
