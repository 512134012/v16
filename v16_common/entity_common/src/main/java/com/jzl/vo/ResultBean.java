package com.jzl.vo;

import java.io.Serializable;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-06 下午 4:22
 */
public class ResultBean<T> implements Serializable {

    /**
     * 返回数据的状态码
     */
    private String statusCode;

    /**
     * 返回数据，提供前端使用
     */
    private T data;

    public ResultBean(String statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResultBean() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
