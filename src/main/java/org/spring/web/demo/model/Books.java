package org.spring.web.demo.model;

import org.spring.web.demo.support.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(
        name = "test.yml",
        value = {"classpath:book.yaml"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        factory = YamlPropertySourceFactory.class
)
@ConfigurationProperties(prefix = "books")
public class Books {
    List<Book> bookList= new ArrayList<>();

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookList=" + bookList +
                '}';
    }
}
