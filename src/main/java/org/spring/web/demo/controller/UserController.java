package org.spring.web.demo.controller;

import org.spring.web.demo.dao.jpa.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController extends  BaseController{
    @Autowired
    UserDao userDao;


    @RequestMapping("/user/list")
    public String userlist(@RequestParam(defaultValue = "1") Integer pageNum, Model model){
        String view = "user";
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        PageRequest pageRequest = PageRequest.of(pageNum-1, 3, sort);
        Page pageInfo = userDao.findAll(pageRequest);
        model.addAttribute("pageInfo",pageInfo);
        return view;
    }
}
