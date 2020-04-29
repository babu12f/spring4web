package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private Employee employee() {
        Employee employee = new Employee();
        employee.setEmployeeName("jhon doe");
        employee.setEmail("jhon@gmail.com");
        employee.setSalary(100000.00);
        employee.setDoj(new Date());

        return employee;
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "spring";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String showHome() {

        Employee employee = employee();

       Session session = sessionFactory.openSession();
       session.beginTransaction();

//        Integer id = (Integer) session.save(employee);
//        System.out.println(id);
//        session.persist(employee);
        session.saveOrUpdate(employee);

       session.getTransaction().commit();

        return "added";
    }
}
