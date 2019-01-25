package com.levelp.example;

import javax.persistence.EntityManager;

import java.util.List;

import static com.levelp.example.User.FIND_BY_LOGIN_QUERY;

public class UsersDAO {
    private EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }

    public Engineer createEngineer(String login) {
        Engineer engineer = new Engineer(login);
        em.persist(engineer);
        return engineer;
    }

    public User findUser(String login) {
//        return em.createQuery("from User where login = :lll", User.class)
//                .setParameter("lll", login)
//                .getSingleResult();

        return em.createNamedQuery(FIND_BY_LOGIN_QUERY, User.class)
                .setParameter("lll", login)
                .getSingleResult();
    }

    public List<User> listUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
