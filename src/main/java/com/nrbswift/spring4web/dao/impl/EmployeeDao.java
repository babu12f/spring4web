package com.nrbswift.spring4web.dao.impl;

import com.nrbswift.spring4web.dao.IEmployeeDao;
import com.nrbswift.spring4web.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao implements IEmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(employee);
        System.out.println("Employee Created With Id : " + id);

        session.getTransaction().commit();
    }

    @Override
    public Employee fetchEmployeeById(int id) {
        Session session = sessionFactory.openSession();

        Employee emp = (Employee) session.get(Employee.class, id);

        if (emp != null) {
            System.out.println(emp);
        }
        else {
            System.out.println("employee with this id not exists");
        }

        return emp;
    }

    @Override
    public void updateEmployeeById(int id, Double salary) {
        Session session = sessionFactory.openSession();

        Employee emp = (Employee) session.get(Employee.class, id);

        if (emp != null) {
            emp.setSalary(salary);

            session.beginTransaction();

            session.update(emp);

            session.getTransaction().commit();
        }
        else {
            System.out.println("employee with this id not exists");
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        Employee emp = (Employee) session.get(Employee.class, id);

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
