package org.spring.web.demo.config;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.spring.web.demo.scheduler.HelloJob2;
import org.spring.web.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

//@Configuration
public class QuartzConfig {
    //MethodInvokingJobDetailFactoryBean
    //设置TargetClass和TargetMethod
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("helloJob");
        bean.setTargetMethod("hello");
        return bean;
    }


    //使用时注入HelloService
    //这里值得思考，为什么不用Spring进行注入，而是通过定义FactoryBean的时候注入？
    //FactoryBean进行注入Service算是灵活性的一种体现，比如，现在的job是可以通过多种配置进行定义的，这是候控制权应该交还给用户，用户在使用的时候才进行指定
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(HelloJob2.class);
        JobDataMap map = new JobDataMap();
        map.put("helloService", helloService());
        bean.setJobDataMap(map);
        return bean;
    }


    @Bean
    public HelloService helloService(){
        return new HelloService();
    }

    //定义Trigger
    //Trigger有多种，这里实现2种方式，一种简单Trigger - SimpleTriggerFactoryBean, 另一种cronTrigger - CronTriggerFactoryBean
    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/10 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }


    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();

        try {
            bean.setStartTime(new Date());
            bean.setRepeatCount(5);
            bean.setJobDetail((JobDetail) methodInvokingJobDetailFactoryBean().getObject());
            bean.setRepeatInterval(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTrigger().getObject(), simpleTriggerFactoryBean().getObject());
        return bean;
    }

}
