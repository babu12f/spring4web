package com.nrbswift.spring4web.services;


import com.nrbswift.spring4web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserService {

    /**
     * For Entity Manger we can autowired or PersistenceContext both are ok
     *
     * */
    @Autowired
//    @PersistenceContext
    private EntityManager entityManager;

    public UserService() {
    }

    public List<User> getUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    public User saveUser() {
        entityManager.getTransaction().begin();

        User user = new User("username", "name", "password", "email", true);

        entityManager.persist(user);

        entityManager.getTransaction().commit();

        return user;
    }
}