package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.*;
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

    public void joinUsingCriteriaBuilder() {
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        root.join("person");
        /*Join<Phone, Person> personJoin = root.join("person");
        Join<Phone, Call> callsJoin = root.join("calls");*/

        //root.fetch("person", JoinType.LEFT);
        //root.fetch("calls", JoinType.LEFT);

        criteriaQuery.where(builder.isNotEmpty(root.get("calls")));
        //criteriaQuery.where(builder.equal(callsJoin.get("duration"), 120));

        TypedQuery<Phone> query = em.createQuery(criteriaQuery);

        List<Phone> resultList = query.getResultList();
        System.err.println("Join using criteria builder");
        for (Phone phone : resultList) {
            System.err.println("Phone Details: ==========================");
            System.out.println(phone.getId() + "\t" + phone.getNumber() + "\t" + phone.getType().toString());

            System.out.println("Person Details: ==========================");
            Person person = phone.getPerson();
            System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getName() + "\t"
                    + person.getCreatedOn() + "\t" + person.getAddress() + "\t" + person.getVersion());

            List<Call> calls = phone.getCalls();
            System.out.println("Phone call details: ==========================");
            for (Call call : calls) {
                System.out.println(call.getId() + "\t" + call.getDuration() + "\t" + call.getTimestamp());
            }
        }

        /*CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        Join<Person, Phone> personJoin = root.join("phones", JoinType.INNER);
        Join<Phone, Call> callsJoin = personJoin.join("calls", JoinType.INNER);

        //criteriaQuery.where(builder.equal(callsJoin.get("duration"), 120));
        //criteriaQuery.where(builder.equal(root.get("id"), 3));
        //criteriaQuery.select(root);
        criteriaQuery.where(builder
                        .and(
                                builder.equal(root.get("id"), 3),
                                builder.equal(callsJoin.get("duration"), 30)
                        )
        );

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        List<Person> resultList = query.getResultList();
        for (Person person : resultList) {
            System.out.println(person);

            List<Phone> phones = person.getPhones();
            for (Phone phone : phones) {
                System.out.println( "\t\t\t"+ phone);

                List<Call> calls = phone.getCalls();
                for (Call call : calls) {
                    System.out.println("\t\t\t\t\t\t"+ call);
                }
            }
        }*/

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

    public void saveSampleDataPhoneCall() {
        entityManager.getTransaction().begin();

        Person person1 = new Person();
        person1.setName("Shofiullah");
        person1.setNickName("Babor");
        person1.setAddress("Dhaka");
        person1.setCreatedOn(new Date());
        person1.setVersion(1);

        Phone phone1 = new Phone();
        phone1.setNumber("018147776");
        phone1.setType(PhoneType.MOBILE);
        phone1.setPerson(person1);

        person1.getPhones().add(phone1);

        Call call1 = new Call();
        call1.setDuration(30);
        call1.setPhone(phone1);
        call1.setTimestamp(new Date());

        Call call2 = new Call();
        call2.setDuration(20);
        call2.setPhone(phone1);
        call2.setTimestamp(new Date());

        phone1.getCalls().add(call1);
        phone1.getCalls().add(call2);

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

        Call call3 = new Call();
        call3.setDuration(60);
        call3.setPhone(phone2);
        call3.setTimestamp(new Date());

        Call call4 = new Call();
        call4.setDuration(50);
        call4.setPhone(phone2);
        call4.setTimestamp(new Date());

        phone2.getCalls().add(call3);
        phone2.getCalls().add(call4);

        Phone phone3 = new Phone();
        phone3.setNumber("022909742");
        phone3.setType(PhoneType.LAND_LINE);
        phone3.setPerson(person2);

        person2.getPhones().add(phone2);
        person2.getPhones().add(phone3);

        Call call5 = new Call();
        call5.setDuration(120);
        call5.setPhone(phone3);
        call5.setTimestamp(new Date());

        phone3.getCalls().add(call5);

        entityManager.persist(person1);
        entityManager.persist(person2);


        entityManager.getTransaction().commit();
    }
}
