package com.levelp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SubjectsDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Subject createSubject(
            Engineer engineer,
            SubjKind kind,
            String cadNum,
            String address) {
        Subject subj = new Subject(cadNum, address);
        subj.setEngineer(engineer);
        subj.setKind(kind);
        subj.setSquare(20);
        em.persist(subj);
        return subj;
    }
}
