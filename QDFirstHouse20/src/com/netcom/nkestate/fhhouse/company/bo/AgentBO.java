package com.netcom.nkestate.fhhouse.company.bo;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.company.dao.AgentDAO;


public class AgentBO extends MiniBO {

	static Logger logger = Logger.getLogger(AgentBO.class.getName());

	private AgentDAO agentDAO = new AgentDAO();

}
