package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

       createEmployee(session);
//        getEmployeeById(session);
//        updateEmployeeById(session);
//        deleteEmployeeById(session);


        return "added";
    }

    @RequestMapping("/dc/{id}")
    @ResponseBody
    public String dc(@PathVariable("id") Integer id) {
        dirtyCheck(id);

        return "dirty check";
    }

    private void dirtyCheck(Integer id) {
        Session session = sessionFactory.openSession();
        Employee emp = (Employee) session.get(Employee.class, id);
        session.beginTransaction();

        emp.setSalary((double) 531);

        session.getTransaction().commit();
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
