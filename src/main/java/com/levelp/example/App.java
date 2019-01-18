package com.levelp.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();

            try {
                Engineer engineer = createEngineer(em, "user1");
                createSubject(em, engineer, "78:00001:1111", "Street, 1");

                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        } finally {
            em.close();
            factory.close();
        }
    }

    private static Engineer createEngineer(EntityManager em, String login) {
        Engineer engineer = new Engineer(login);
        em.persist(engineer);
        return engineer;
    }

    private static Subject createSubject(EntityManager em,
                                         Engineer engineer,
                                         String cadNum,
                                         String address) {
        Subject subj = new Subject(cadNum, address);
        subj.setEngineer(engineer);
        em.persist(subj);
        return subj;
    }
}
