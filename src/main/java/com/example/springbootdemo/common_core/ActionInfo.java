package com.example.springbootdemo.common_core;

import java.io.Serializable;

/**
 * ActionInfo
 *  操作人信息
 * @author zhengzhongpeng
 * @date 2019/4/26 13:58
 */
public class ActionInfo implements Serializable {

    private static final long serialVersionUID = -3773186969275889142L;

    /**
     * 操作人Id
     */
    private Long userId;

    /**
     * 操作人名称
     */
    private String userName;

    public ActionInfo() {
    }

    public ActionInfo(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
