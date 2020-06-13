package org.spring.web.demo.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.spring.web.demo.service.HelloService;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloJob2 extends QuartzJobBean {
    HelloService helloService;

    public HelloJob2() {
        super();
    }

    public void setHelloService(HelloService helloService){
        this.helloService = helloService;
    }

    public HelloService getHelloService(){
        return this.helloService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        helloService.sayHello();
    }

}

