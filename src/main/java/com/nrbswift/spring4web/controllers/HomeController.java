package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.entity.Employee;
import com.nrbswift.spring4web.service.impl.EmployeeService;
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

    @Autowired
    EmployeeService employeeService;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public static String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private Employee employee() {
        Employee employee = new Employee();
        employee.setEmployeeName("jhon doe");
        employee.setEmail(randomAlphaNumeric(6) + "@gmail.com");
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

        createEmployee();
//        getEmployeeById();
//        updateEmployeeById();
//        deleteEmployeeById();


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

    private void createEmployee() {
        employeeService.createEmployee(employee());
    }

    private void getEmployeeById() {
        employeeService.getEmployeeById(4);
    }

    private void updateEmployeeById() {
        employeeService.updateEmployeeById(4, 41247D);
    }


    private void deleteEmployeeById() {
       employeeService.deleteEmployeeById(4);
    }
}
