package com.levelp.example.web;

import com.levelp.example.User;

import java.util.Date;
import java.util.List;

public class IndexPageBean {
    private final String currentUserName;
    private final Date currentDate;
    private final List<User> users;

    public IndexPageBean(Date currentDate, List<User> users, String currentUserName) {
        this.currentDate = currentDate;
        this.users = users;
        this.currentUserName = currentUserName;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }
}
