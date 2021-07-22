package com.nrbswift.spring4web.services;

import com.nrbswift.spring4web.entities.Company;
import com.nrbswift.spring4web.entities.Detail;
import com.nrbswift.spring4web.entities.Price;
import com.nrbswift.spring4web.entities.Product;
import com.nrbswift.spring4web.entities.embeddables.Address;
import com.nrbswift.spring4web.entities.enums.Currency;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class PriceService {

    private EntityManagerFactory entityManagerFactory;

    public PriceService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void savePrice() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        /*Price price = new Price();
        price.setAmount(200);
        price.setCurrency(Currency.USD);
        em.persist(price);*/

        /*Company c = new Company();
        c.setName("ABC");
        c.setAddress(new Address());

        c.getAddress().setNumber("4");
        c.getAddress().setStreet("Some Street");
        c.getAddress().setCity("London");

        em.persist(c);*/

        /*Company c1 = new Company();
        c1.setName("XYZ");
        c1.setStreet("Street 1");
        c1.setNumber("A1");
        c1.setDetails("DETAILS");
        em.persist(c1);*/

        //Product fp = em.find(Product.class, 6);

        //System.out.println(fp.getDetail().getKcal());

        /*Product p = new Product();
        p.setName("Berry blue");
        p.setPrice(5);
        //em.persist(p);

        Detail d = new Detail();
        d.setKcal(400);

        d.setProduct(p);
        p.setDetail(d);

        em.persist(d);*/

        em.getTransaction().commit();
        em.close();
    }

}
