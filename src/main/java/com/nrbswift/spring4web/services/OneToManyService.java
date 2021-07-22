package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.Department;
import com.nrbswift.spring4web.entities.Document;
import com.nrbswift.spring4web.entities.Employee;
import com.nrbswift.spring4web.entities.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

@Component
public class OneToManyService {

    private EntityManagerFactory entityManagerFactory;

    public OneToManyService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void runOneToMany() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        /*Employee e1 = new Employee();
        e1.setName("EMP 1");

        Department d1 = new Department();
        d1.setName("DEP 1");
        d1.setEmployees(new ArrayList<>());
        d1.getEmployees().add(e1);

        em.persist(e1);
        em.persist(d1);*/

        Person p1 = new Person();
        p1.setName("P2");
        p1.setDocuments(new ArrayList<>());

        Document d1 = new Document();
        d1.setName("DOC 1");

        Document d2 = new Document();
        d2.setName("DOC 2");

        d1.setPerson(p1);
        d2.setPerson(p1);
        p1.getDocuments().add(d1);
        p1.getDocuments().add(d2);

        //em.persist(p1);
        em.persist(d1);
        //em.persist(d2);



        em.getTransaction().commit();
        em.close();
    }
}
