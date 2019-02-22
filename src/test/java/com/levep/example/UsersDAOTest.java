package com.levep.example;

import com.levelp.example.Engineer;
import com.levelp.example.User;
import com.levelp.example.UsersDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UsersDAOTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsersDAO dao;

    @Test
    public void testCreateEngineer() {
        Engineer engineer = dao.createEngineer("engineer1");

        assertNotEquals(0L, engineer.getId());
        assertEquals("engineer1", engineer.getLogin());

        Engineer found = em.find(Engineer.class, engineer.getId());
        assertNotNull(found);
        assertEquals("engineer1", found.getLogin());

        User foundByLogin = dao.findUser("engineer1");
        assertNotNull(foundByLogin);
        if (!(foundByLogin instanceof Engineer)) {
            fail("Expected an instance of Engineer but " + foundByLogin.getClass().getSimpleName() + " found");
        }
    }
}
