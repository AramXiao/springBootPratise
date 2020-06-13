package org.spring.web.demo.model;

import java.util.HashMap;
import java.util.Map;

public class UserMap extends Object{
    Map<String, User> users = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
