package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SubjectsDAO {
    private final EntityManager em;

    @Autowired
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
