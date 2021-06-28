package com.netcom.nkestate.system.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.system.dao.ActionListDAO;
import com.netcom.nkestate.system.vo.ActionListVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * Created by Administrator on 2018/9/7.
 */
public class ActionListBO extends MiniBO{
    private static Logger logger = Logger.getLogger(ActionListBO.class.getName());

    public static List<ActionListVO> actionsublist = new ArrayList<ActionListVO>();
    ActionListDAO actionListDAO = new ActionListDAO();

    public List<ActionListVO> getActionsublist()throws Exception{
        try{
            openDAO(actionListDAO);
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("valid","=","1"));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("id",MetaOrder.ASC));
            List<ActionListVO> actionList = actionListDAO.search(ActionListVO.class,params,orders,null);
            if(actionList!=null&&!actionList.isEmpty()){
                return actionList;
            }else{
                return new ArrayList<ActionListVO>();
            }
        }catch (Exception e){
            logger.error(e);
            return new ArrayList<ActionListVO>();
        }finally {
            closeDAO(actionListDAO);
        }
    }

    public static void loadactionsublist() throws Exception{
        try{
            synchronized (actionsublist){
                if(actionsublist.isEmpty()){
                    actionsublist = new ActionListBO().getActionsublist();
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static boolean isActionSub(SmUserVO smUserVO,String pathInfo){
        try{
            boolean flag = true;
            if(smUserVO!=null&&pathInfo!=null){
				List<SmObjectVO> objectList = smUserVO.getObjectList();
                if(objectList!=null&&!objectList.isEmpty()){
                    String actionIDStr = "";
                    for(ActionListVO actionListVO:actionsublist){
                        String subUrl = actionListVO.getSubUrl();
                        subUrl = subUrl.toLowerCase().split("\\.")[0];
                        pathInfo = pathInfo.toLowerCase().split("\\.")[0];
                        if(subUrl!=null&&pathInfo.equals(subUrl)){
                            actionIDStr = actionListVO.getActionID()+";";
                        }
                    }
                    if(!"".equals(actionIDStr)){
                        int i = 0;
                        for(SmObjectVO objectVO:objectList){
                            if(actionIDStr.contains(objectVO.getObjectId()+";")){
                                i++;
                                break;
                            }
                        }
                        if(i==0){
                            return false;
                        }
                    }
                }
            }
            return flag;
        }catch (Exception e){
            logger.error(e.getMessage());
            return  false;
        }
    }
}
