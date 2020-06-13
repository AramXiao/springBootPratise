package org.spring.web.demo.controller;

import org.spring.web.demo.model.Book;
import org.spring.web.demo.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;

@Controller
public class BookController extends BaseController{
    @Autowired
    private Books books;


    @RequestMapping("/book")
    public String booklist(Model model){
        Collections.sort(books.getBookList(), new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                //按书本id正序排列
                return o1.getId()-o2.getId();
                //按书本id逆序排列
                //return o2.getId()-o1.getId();
    }
});
        System.out.println("md-->"+ model.getAttribute("md"));
        model.addAttribute("bookList", books.getBookList());
        return "book";
    }



}
