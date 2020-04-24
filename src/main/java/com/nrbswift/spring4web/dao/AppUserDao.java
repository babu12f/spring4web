package com.nrbswift.spring4web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class AppUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public AppUser findUserByUsername(String username) {
        return (AppUser) session()
                .createCriteria(AppUser.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    public void registerUser(AppUser appUser) {
        session().save(appUser);
    }
}
