package org.spring.web.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:book.properties")
@ConfigurationProperties(prefix = "book")
@ApiModel
public class Book {
    @ApiModelProperty(value = "书籍id",name = "b.id", required = true, example = "1")
    private int id;
    @ApiModelProperty(value = "书籍名字",name = "b.name", required = true, example = "三国演义")
    private String name;
    @ApiModelProperty(value="作者",dataType = "Author")
    private  Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
