package org.spring.web.demo.scheduler;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloJob {

    public void hello(){
        System.out.println("HelloJob-->"+new Date());
    }
}
