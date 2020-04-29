package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.entity.Employee;
import jdk.nashorn.internal.ir.EmptyNode;
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

//       createEmployee(session);
//        getEmployeeById(session);
//        updateEmployeeById(session);
        deleteEmployeeById(session);

        return "added";
    }

    private void createEmployee(Session session) {
        Employee employee = employee();

        session.beginTransaction();

        session.persist(employee);

        session.getTransaction().commit();
    }

    private void getEmployeeById(Session session) {
        Employee emp = (Employee) session.get(Employee.class, 2);

        if (emp != null) {
            System.out.println(emp);
        }
        else {
            System.out.println("employee with this id not exists");
        }
    }

    private void updateEmployeeById(Session session) {
        Employee emp = (Employee) session.get(Employee.class, 2);

        if (emp != null) {
            emp.setEmail("jhonDoe@k.com");
            emp.setSalary(550D);

            session.beginTransaction();

            session.update(emp);

            session.getTransaction().commit();
        }
        else {
            System.out.println("employee with this id not exists");
        }
    }


    private void deleteEmployeeById(Session session) {
        Employee emp = (Employee) session.get(Employee.class, 2);

        if (emp != null) {
            session.beginTransaction();

            session.delete(emp);

            session.getTransaction().commit();
        }
        else {
            System.out.println("employee with this id not exists");
        }
    }
}
