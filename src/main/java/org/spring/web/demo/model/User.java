package org.spring.web.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String username;
    @Column(name="age")
    private String age;
    @JoinColumn(name="dept_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Dept dept;
    @Column(name="address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {

        String output =  "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +

                ", address='" + address + '\'' +
                '}';
        if(dept!=null) {
            output += ", dept.id=" + dept.getId() +
                    ", dept.name=" + dept.getName();
        }
        return output;
    }
}
