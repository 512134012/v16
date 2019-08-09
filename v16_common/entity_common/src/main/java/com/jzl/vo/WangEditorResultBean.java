package com.jzl.vo;

import java.io.Serializable;

/**
 * 项目名：v16
 * HAPPY JAVA ！
 * Create by jiangzonglin on 2019-08-07 下午 2:57
 */
public class WangEditorResultBean implements Serializable {

    private String errno;
    private String[] data;

    public WangEditorResultBean(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public WangEditorResultBean() {
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
