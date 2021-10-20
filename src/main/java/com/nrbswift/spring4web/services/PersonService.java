package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Component
public class PersonService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public void getMultiRootData() {
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class );

        Root<Person> personRoot = criteriaQuery.from(Person.class);
        Root<Partner> partnerRoot = criteriaQuery.from(Partner.class);

        criteriaQuery.multiselect(personRoot, partnerRoot);

        Predicate personRestriction = builder.and(
                builder.equal(personRoot.get("address"), "Feni"),
                builder.isNotEmpty(personRoot.get("phones") )
        );

        Predicate partnerRestriction = builder.and(
                builder.like(partnerRoot.get("name" ), "%My%" ),
                builder.equal(partnerRoot.get("version"), 1)
        );

        criteriaQuery.where(builder.and(personRestriction, partnerRestriction));

        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();
        System.err.println("Selection form multi Root");
        for (Tuple tuple : tuples) {
            Person person = (Person)tuple.get(0);

            if(person != null){
                System.out.println(person);

                List<Phone> phones = person.getPhones();
                for (Phone phone : phones) {
                    System.out.println(phone.getId() + "\t" + phone.getNumber() + "\t"+ phone.getType().toString());
                }
            }

            Partner partner = (Partner)tuple.get(1);
            System.out.println(partner);
        }

    }

    public void saveSampleData() {
        entityManager.getTransaction().begin();

        Person person1 = new Person();
        person1.setName("Shofiullha");
        person1.setNickName("Babor");
        person1.setAddress("Dhaka");
        person1.setCreatedOn(new Date());
        person1.setVersion(1);

        Phone phone1 = new Phone();
        phone1.setNumber("0181423894");
        phone1.setType(PhoneType.MOBILE);
        phone1.setPerson(person1);

        person1.getPhones().add(phone1);

        Person person2 = new Person();
        person2.setName("Nadim");
        person2.setNickName("Dalim");
        person2.setAddress("Feni");
        person2.setCreatedOn(new Date());
        person2.setVersion(1);

        Phone phone2 = new Phone();
        phone2.setNumber("809865430");
        phone2.setType(PhoneType.MOBILE);
        phone2.setPerson(person2);

        Phone phone3 = new Phone();
        phone3.setNumber("382901380912");
        phone3.setType(PhoneType.LAND_LINE);
        phone3.setPerson(person2);

        person2.getPhones().add(phone2);
        person2.getPhones().add(phone3);

        Partner partner = new Partner();
        partner.setName("My Partner");
        partner.setVersion(1);

        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(partner);

        entityManager.getTransaction().commit();
    }
}
