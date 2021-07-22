package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ProductService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void saveProduct() {
        EntityManager em1 = entityManagerFactory.createEntityManager();

        Product p1 = new Product();
        p1.setId(201);
        p1.setName("Apple");
        p1.setPrice(10.4);
        /*p1.setExpirationDate(new Date());*/

        entityManager.getTransaction().begin();

        entityManager.persist(p1); // adding the instance in the context

        entityManager.getTransaction().commit();
        entityManager.close();


        Product p2 = new Product();
        p2.setId(202);
        p2.setName("Apple");
        p2.setPrice(10.4);
        /*p2.setExpirationDate(new Date());*/

        EntityManager em2 = entityManagerFactory.createEntityManager();

        em2.getTransaction().begin();

        em2.persist(p2); // adding the instance in the context

        em2.getTransaction().commit();
        em2.close();
    }

}
