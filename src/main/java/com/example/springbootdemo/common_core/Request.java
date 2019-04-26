package com.example.springbootdemo.common_core;


/**
 * Request
 *  请求参数封装
 * @author zhengzhongpeng
 * @date 2019/4/26 13:54
 */
public class Request<T> extends BaseRequest {

    private static final long serialVersionUID = -6404175682261130838L;

    /**
     * 参数数据
     */
    private T data;

    public Request() {
    }

    public Request(T data) {
        super();
        this.data = data;
    }

    public Request(T data,ActionInfo actionInfo) {
        super(actionInfo);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
