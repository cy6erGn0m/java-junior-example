package com.levelp.example.web;

import com.levelp.example.UsersDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class MyApplicationListener implements ServletContextListener {
    private EntityManagerFactory factory;
    private EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();

        event.getServletContext().setAttribute("em", em);

        initUsers();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (em != null) {
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    private void initUsers() {
        em.getTransaction().begin();
        try {
            new UsersDAO(em).createEngineer("test-engineer");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
