package com.levep.example;

import com.levelp.example.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SubjectsDAOTest {
    @Autowired
    private UsersDAO users;

    @Autowired
    private SubjectsDAO subjects;

    @Test
    public void testCreateSubj() {
        Engineer engineer = users.createEngineer("e2");
        Subject subject = new Subject("78:0001:0001", "addr1");
        subject.setKind(SubjKind.BUILDING);
        subject.setEngineer(engineer);
        subject.setSquare(1);

        subjects.save(subject);

        Page<Subject> foundPage =
                subjects.findByEngineer_IdAndSquareGreaterThanEqualOrderBySquare(engineer.getId(), 1,
                        PageRequest.of(0, 10));

        List<Subject> foundSubjects = foundPage.get().collect(Collectors.toList());
        System.out.println(foundSubjects);

        assertTrue(foundPage.get().anyMatch(e -> e.getId() == subject.getId()));

        Subject findBy = new Subject();
        findBy.setEngineer(engineer);

//        subjects.findAll(Example.of(findBy, ExampleMatcher.matching()...));
    }

    @Test
    public void testFindEngineersSubjs() {
        Engineer engineer = users.createEngineer("e3");

        Subject subject = new Subject("78:0001:0001", "addr2");
        subject.setKind(SubjKind.BUILDING);
        subject.setEngineer(engineer);
        subject.setSquare(1);

        subjects.save(subject);
        engineer.setSubjects(singletonList(subject));

        List<Subject> found = engineer.getSubjects();
        System.out.println(found);
        assertNotNull(found);
        assertNotEquals(0, found.size());
        assertSame(subject, found.get(0));
    }
}
