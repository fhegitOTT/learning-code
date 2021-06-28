/**
 * <p>TempRHouseRightView.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017年3月15日<p>
 * 
 */
package com.netcom.nkestate.fhhouse.interfaces.vo;

import com.netcom.nkestate.framework.dao.DBModel;


@DBModel(tablename = "VW_TEMP_R_LAND_RIGHT", sequence = "", id = DBModel.AssignedID)
public class TempRLandRightView extends RLandRightView {

	@Override
	public int getDbFlag() {
		return 0;
	}
}
