package org.spring.web.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Entry hello method");
        return "hello https";
    }

    @GetMapping("/set")
    public String set(HttpSession session){
        session.setAttribute("user", "javaboy");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session){
        return session.getAttribute("user") + ":" + port;

    }
}
