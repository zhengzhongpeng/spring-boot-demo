package com.example.springbootdemo.common_core;

import java.io.Serializable;

/**
 * BaseRequest
 *
 * @author zhengzhongpeng
 * @date 2019/4/26 14:01
 */
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 5442486208650471368L;

    /**
     * 系统Id
     */
    private String sId;

    /**
     * 操作人
     */
    private ActionInfo actionInfo;

    public BaseRequest() {
    }

    public BaseRequest(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public BaseRequest(String sId, ActionInfo actionInfo) {
        this.sId = sId;
        this.actionInfo = actionInfo;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }
}
