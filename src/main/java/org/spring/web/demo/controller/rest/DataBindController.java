package org.spring.web.demo.controller.rest;

import org.spring.web.demo.model.BaseResponse;
import org.spring.web.demo.model.User;
import org.spring.web.demo.model.UserList;
import org.spring.web.demo.model.UserMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
class RestDataBindController {

    @GetMapping("/getInt")
    public BaseResponse getInt(int id){
        BaseResponse baseResponse = new BaseResponse();
        try{
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            baseResponse.setData(param);
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }
    }

//    @GetMapping("/getUser")
//    public BaseResponse getUser(String name, Integer age){
//        BaseResponse baseResponse = new BaseResponse();
//        try{
//            Map<String, Object> result = new HashMap<>();
//            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
//            result.put("name",name);
//            result.put("age",age);
//            baseResponse.setData(result);
//            baseResponse.setMsg("获取参数成功");
//        }catch(Exception e){
//            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
//            baseResponse.setMsg(e.getMessage());
//        }finally {
//            return baseResponse;
//        }
//    }

    @GetMapping("/getUser")
    public BaseResponse getUser(User user){
        BaseResponse baseResponse = new BaseResponse();
        try{
            Map<String, Object> result = new HashMap<>();
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            result.put("user", user);
            baseResponse.setData(result);
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }
    }

    //list参数绑定，需要先封装到bean里面
    //使用规则：列表属性名[number].{属性}，复合对象: 列表属性名[number].复合对象.{属性}
    @PostMapping("/getUsers")
    public BaseResponse getUsers(UserList users){
        BaseResponse baseResponse = new BaseResponse();
        try{
            Map<String, Object> result = new HashMap<>();
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            result.put("user", users);
            baseResponse.setData(result);
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }
    }

    //Map对象绑定，需要先封装到bean里面
    //使用规则：引用@RequestBody标签标识参数，使用json构建请求体
    @PostMapping("/getUserMap")
    public BaseResponse getUsers(@RequestBody UserMap users){
        BaseResponse baseResponse = new BaseResponse();
        try{
            Map<String, Object> result = new HashMap<>();
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            baseResponse.setData(users.getUsers());
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }
    }


    @GetMapping("/getName")
    public BaseResponse getName(@RequestParam(defaultValue = "test") String name){
        BaseResponse baseResponse = new BaseResponse();
        String paramName = name.trim();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            resultMap.put("name", name);
            baseResponse.setData(resultMap);
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }

    }


    //自定义参数名，参数名可以与Controller的参数名不一致
    @GetMapping("/getName2")
    public BaseResponse getName2(@RequestParam(value = "username") String name){
        BaseResponse baseResponse = new BaseResponse();
        String paramName = name.trim();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
            resultMap.put("name", name);
            baseResponse.setData(resultMap);
            baseResponse.setMsg("获取参数成功");
        }catch(Exception e){
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
            baseResponse.setMsg(e.getMessage());
        }finally {
            return baseResponse;
        }

    }
}
