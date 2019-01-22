package com.levelp.example;

import javax.persistence.EntityManager;

public class SubjectsDAO {
    private EntityManager em;

    public SubjectsDAO(EntityManager em) {
        this.em = em;
    }

    public Subject createSubject(
            Engineer engineer,
            SubjKind kind,
            String cadNum,
            String address) {
        Subject subj = new Subject(cadNum, address);
        subj.setEngineer(engineer);
        subj.setKind(kind);
        em.persist(subj);
        return subj;
    }
}
