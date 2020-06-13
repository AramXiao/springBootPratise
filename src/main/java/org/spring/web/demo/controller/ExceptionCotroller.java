package org.spring.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionCotroller {
    @RequestMapping("/test/exception")
    public String login(Model model){
        int count = 0;
        //模拟计算当前id
        int id = 1000/count;
        model.addAttribute("id", id);
        return "login";
    }
}
