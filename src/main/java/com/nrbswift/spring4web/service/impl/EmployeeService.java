package com.nrbswift.spring4web.service.impl;

import com.nrbswift.spring4web.dao.impl.EmployeeDao;
import com.nrbswift.spring4web.entity.Employee;
import com.nrbswift.spring4web.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.fetchEmployeeById(id);
    }

    @Override
    public void updateEmployeeById(int id, Double salary) {
        employeeDao.updateEmployeeById(id, salary);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeDao.deleteEmployeeById(id);
    }
}
