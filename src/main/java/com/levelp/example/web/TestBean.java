package com.levelp.example.web;

import com.levelp.example.User;
import com.levelp.example.UsersDAO;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestBean {
    private EntityManager em;
    private UsersDAO users;

    public Date getCurrentDate() {
        return new Date();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
        this.users = new UsersDAO(em);
    }

    public List<User> getUsers() {
        return users.listUsers();
    }
}
