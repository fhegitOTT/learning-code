package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.dao.DBModel;

/**
 * 当日登录日志表 因属性相同，继承与登陆日志表
 */
@DBModel(tablename = "LOG_LOGIN_TODAY_T", sequence = "Seq_Log_Login_ID", id = DBModel.SequenceID)
public class LogLoginTodayVO extends LogLoginVO{
	
	
}
