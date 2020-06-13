package org.spring.web.demo.controller;

import com.alibaba.fastjson.JSON;

public class BaseController {

    public String objectToJSON(Object obj){
        return JSON.toJSONString(obj);
    }
}
