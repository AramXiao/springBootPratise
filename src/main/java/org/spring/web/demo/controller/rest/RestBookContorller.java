package org.spring.web.demo.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.spring.web.demo.controller.BaseController;
import org.spring.web.demo.model.Author;
import org.spring.web.demo.model.Book;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags="书籍管理接口", consumes="autho,book", produces = "json")

public class RestBookContorller extends BaseController {

    @PostMapping("/addbook")
    @ApiOperation("添加书籍")
    public String addBook(@ModelAttribute("a") Author author, @ModelAttribute("b")Book book){
        System.out.println(book);
        book.setAuthor(author);
        System.out.println(book);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg","update Successfully");
        return objectToJSON(resultMap);
    }


    @PostMapping("/addAuthor")
    @ApiOperation("添加作者")
    public String addBook(@ModelAttribute("a") Author author){
        System.out.println(author);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("msg","update Successfully");
        return objectToJSON(resultMap);
    }

}
