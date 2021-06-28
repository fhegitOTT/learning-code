/**
 * <p>ActionCache.java </p>
 *
 * <p></p>
 * <p><p>  
 * <p><p>
 *
 * <p><p> 
 * <p>创建人: Administrator</p>
 * <p>创建时间: 2017-01-10<p>
 * 
 */ 
package com.netcom.nkestate.system.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.cache.SyncObject;
import com.netcom.nkestate.system.vo.SmObjectVO;



public class ActionCache {

	static Logger logger = Logger.getLogger(ActionCache.class.getName());

	protected static SyncObject m_lock = new SyncObject();	

	private static List<SmObjectVO> sys_objects = new ArrayList<SmObjectVO>();
	
	

	//
	public static void load() throws Exception {

	}

	//
	public static void reload() throws Exception {

	}

}
