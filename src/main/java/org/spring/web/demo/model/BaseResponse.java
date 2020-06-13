package org.spring.web.demo.model;

import java.util.Map;

public class BaseResponse<T> {
    public static String RESPONSE_SUCCESS_CODE = "SUCCESS";
    public static String RESPONSE_ERROR_CODE = "ERROR";
    private String code;
    private String msg;
    private Map<String,T> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String,T> getData() {
        return data;
    }

    public void setData(Map<String,T> data) {
        this.data = data;
    }
}
