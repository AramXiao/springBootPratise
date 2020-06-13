package org.spring.web.demo.support;

import org.spring.web.demo.util.ExceptionUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception e){
        ModelAndView mv = new ModelAndView();
        e.printStackTrace();
        mv.addObject("msg", e.getMessage());
        mv.addObject("msgDetail", ExceptionUtil.getExceptionAllInfo(e));
        mv.setViewName("myerror");
        return mv;
    }

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

    //将带有前缀是b的数据绑定到b中
    @InitBinder("b")
    public void b(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
    }

    //将带有前缀是a的数据绑定到b中
    @InitBinder("a")
    public void a(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }

}
