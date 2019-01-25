package com.levelp.example.web;

import com.levelp.example.User;
import com.levelp.example.UsersDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TestBean {
    private EntityManager em;
    private UsersDAO users;

    public void setup(EntityManager em) {
        this.em = em;
        this.users = new UsersDAO(em);
    }

    public List<User> getUsers() {
        return users.listUsers();
    }
}
