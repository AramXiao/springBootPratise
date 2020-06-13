package org.spring.web.demo.controller.rest;

import org.spring.web.demo.dao.jpa.UserDao;
import org.spring.web.demo.dao.mapper.UserMapper;
import org.spring.web.demo.model.BaseResponse;
import org.spring.web.demo.model.User;
import org.spring.web.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/users/jdbc/adduser")
    public BaseResponse addUser(User user){
        System.out.println(user);
        BaseResponse baseResponse = new BaseResponse();
        try{
            if(user.getId()>0){
                userService.updateUser(user);
            }else{
                userService.addUser2(user);
            }

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("user",user);
            baseResponse.setData(dataMap);
            baseResponse.setMsg("添加user成功");
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
        }

        return baseResponse;
    }

    @GetMapping("/users/jdbc/getAllUsers")
    public BaseResponse getAllUsers(){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, List<User>> dataMap = new HashMap<>();
        try{
            dataMap.put("users", userService.getAllUers());
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }

        return baseResponse;
    }

    @GetMapping("/users/jdbc/getUserById")
    public BaseResponse getUserById(Integer id){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, User> dataMap = new HashMap<>();
        try{
            dataMap.put("users", userService.getUserById(id));
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }

        return baseResponse;
    }

    @GetMapping("/users/jdbc/deleteUserById")
    public BaseResponse deleteUserById(Integer id){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, Object> dataMap = new HashMap<>();
        try{
            dataMap.put("isSuccess", userService.deleteUserById(id));
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }

        return baseResponse;
    }


    @GetMapping("/getUsers")
    public BaseResponse getUserList(){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, List<User>> dataMap = new HashMap<>();
        try{
//            dataMap.put("users", userService.getAllUers());
            dataMap.put("users", userMapper.getAllUser());
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }

        return baseResponse;
    }

    @GetMapping("/getByUserId")
    public BaseResponse getByUserId(@RequestParam Long id){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, User> dataMap = new HashMap<>();
        try{
//            dataMap.put("users", userService.getAllUers());
            User user = userMapper.getUserById(id);
            System.out.println(user);
            dataMap.put("user", user);
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }
        return baseResponse;
    }


    @GetMapping("/jpa/getMaxIdUserByJPA")
    public BaseResponse getMaxIdUserByJPA(){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, User> dataMap = new HashMap<>();
        try{
//            dataMap.put("users", userService.getAllUers());
            User user = userDao.maxIdUser();
            System.out.println(user);
            dataMap.put("user", user);
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }
        return baseResponse;
    }


    @PostMapping("/jpa/addUser")
    public BaseResponse addUserByJPA(User user){
        System.out.println(user);
        BaseResponse baseResponse = new BaseResponse();
        try{
            Map<String, Object> dataMap = new HashMap<>();
            userDao.save(user);
            dataMap.put("user",user);
            baseResponse.setData(dataMap);
            baseResponse.setMsg("添加user成功");
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);
        }

        return baseResponse;
    }

    @PutMapping("/updateUserById")
    public BaseResponse updateUserById(User user){
        BaseResponse baseResponse = new BaseResponse();
        Map<String, String> dataMap = new HashMap<>();
        try{
//            dataMap.put("users", userService.getAllUers());
            int result = userMapper.updateUserById(user);
            if(result>0){
                dataMap.put("isUpdate", "yes");
            }
            baseResponse.setData(dataMap);
            baseResponse.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            baseResponse.setMsg(e.getMessage());
            baseResponse.setCode(BaseResponse.RESPONSE_ERROR_CODE);

        }
        return baseResponse;
    }

    @DeleteMapping("/deleteUser")
    public BaseResponse deleteUser(@RequestParam Integer userId){
        BaseResponse resposne = new BaseResponse();

        try{
            Integer result = userMapper.deleteUserById(userId);
            if(result>0){
                resposne.setMsg("delete successfully");
            }else{
                resposne.setMsg("no user delete");
            }
            resposne.setCode(BaseResponse.RESPONSE_SUCCESS_CODE);

        }catch(Exception e){
            e.printStackTrace();
            resposne.setMsg(e.getMessage());
            resposne.setCode(BaseResponse.RESPONSE_ERROR_CODE);
        }
        return resposne;
    }




}
