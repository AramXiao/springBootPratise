package org.spring.web.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="t_dept")
public class Dept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="person_count")
    private String personCount;

    public Dept() {
    }

    public Dept(int id) {
        this.id = id;
    }

    public Dept(String name) {
        this.name = name;
    }

    public Dept(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }
}
