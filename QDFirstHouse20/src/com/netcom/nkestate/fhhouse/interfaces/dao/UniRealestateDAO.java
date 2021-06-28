package com.netcom.nkestate.fhhouse.interfaces.dao;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.BaseUniRealestateDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;

public class UniRealestateDAO extends BaseUniRealestateDAO {
	static Logger logger = Logger.getLogger(UniRealestateDAO.class.getName());

	public long addMessage(DocMessageVO message) throws Exception {
		return DataBaseUtil.add(message, conn);
	}
}
