package com.nrbswift.spring4web.service;

import com.nrbswift.spring4web.entity.Employee;

public interface IEmployeeService {
    void createEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void updateEmployeeById(int id, Double salary);
    void deleteEmployeeById(int id);
}
