package com.netcom.nkestate.fhhouse.interfaces.bo;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.BaseUniRealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.dao.UniRealestateDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;

public class UniRealestateBO extends BaseUniRealestateBO{
	static Logger logger = Logger.getLogger(UniRealestateBO.class.getName());

	private UniRealestateDAO estateDao = new UniRealestateDAO();

	/**
	 * 功能描述：插入sms短信
	 * @param messageVO
	 * @return
	 * @throws Exception
	 */
	public long addSMS(DocMessageVO messageVO) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.addMessage(messageVO);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
}
