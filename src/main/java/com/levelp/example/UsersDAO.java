package com.levelp.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.levelp.example.User.FIND_BY_LOGIN_QUERY;

@Service
@Transactional
public class UsersDAO {
    @PersistenceContext
    protected EntityManager em;

    public Engineer createEngineer(String login) {
        Engineer engineer = new Engineer(login);
        em.persist(engineer);
        return engineer;
    }

    public User createUser(String kind, String login) {
        User user;

        switch (kind) {
            case "admin":
                user = new Admin(login);
                break;
            case "client":
                user = new Client(login);
                break;
            case "engineer":
                user = new Engineer(login);
                break;
            default:
                return null;
        }

        em.persist(user);

        return user;
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

    public void delete(long id) {
        User user = em.find(User.class, id);
        if (user == null) throw new EntityNotFoundException("User with id " + id + " is not found");
        em.remove(user);
    }
}
