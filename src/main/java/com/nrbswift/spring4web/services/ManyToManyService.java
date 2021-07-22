package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.Professor;
import com.nrbswift.spring4web.entities.Student;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

@Component
public class ManyToManyService {

    private EntityManagerFactory entityManagerFactory;

    public ManyToManyService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void runManyToMany() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Professor p1 = new Professor();
        p1.setName("Prof 1");

        Student s1 = new Student();
        s1.setName("Stud 1");

        p1.setStudents(new ArrayList<>());
        p1.getStudents().add(s1);

        s1.setProfessors(new ArrayList<>());
        s1.getProfessors().add(p1);

        em.persist(p1);

        em.getTransaction().commit();
        em.close();
    }
}
