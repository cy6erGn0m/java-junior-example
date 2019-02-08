package com.levep.example;

import com.levelp.example.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SubjectsDAOTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private UsersDAO users;

    @Autowired
    private SubjectsDAO subjects;

    @Test
    public void testCreateSubj() {
        em.getTransaction().begin();

        Engineer engineer = users.createEngineer("e2");
        Subject subject = subjects.createSubject(engineer, SubjKind.BUILDING, "78:0001:0001", "addr1");

        em.getTransaction().commit();
    }

    @Test
    public void testFindEngineersSubjs() {
        em.getTransaction().begin();

        Engineer engineer = users.createEngineer("e3");
        Subject subject = subjects.createSubject(engineer, SubjKind.BUILDING, "79:0001:0001", "addr2");
        engineer.setSubjects(Arrays.asList(subject));

        em.getTransaction().commit();

        em.refresh(engineer);

        List<Subject> found = engineer.getSubjects();
        System.out.println(found);
        assertNotNull(found);
        assertNotEquals(0, found.size());
        assertSame(subject, found.get(0));
    }
}
