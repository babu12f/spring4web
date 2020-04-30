package com.nrbswift.spring4web.dao;

import com.nrbswift.spring4web.entity.Employee;

public interface IEmployeeDao {
    void addEmployee(Employee employee);
    Employee fetchEmployeeById(int id);
    void updateEmployeeById(int id, Double salary);
    void deleteEmployeeById(int id);
}
