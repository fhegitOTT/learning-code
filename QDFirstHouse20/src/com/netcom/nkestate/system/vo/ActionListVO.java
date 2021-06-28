package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

/**
 * Created by Administrator on 2018/9/7.
 */
@DBModel(tablename = "ACTION_LIST_T", sequence = "", id = DBModel.AssignedID)
public class ActionListVO  extends AbstractBaseVO {
    private long ID;
    private long actionID;
    private String subUrl;
    private String memo;
    private int valid;//是否有效  1：有效

    @DBMeta(column = "ID", name = "子菜单ID", type = "long", primarykey = "true")
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @DBMeta(column = "actionID", name = "菜单ID", type = "long")
    public long getActionID() {
        return actionID;
    }

    public void setActionID(long actionID) {
        this.actionID = actionID;
    }

    @DBMeta(column = "subUrl", name = "子菜单路径", type = "String")
    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }

    @DBMeta(column = "memo", name = "子菜单描述", type = "String")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @DBMeta(column = "valid", name = "是否有效", type = "int")
    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
