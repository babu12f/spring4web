package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.service.EmployeeServiceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServiceCriteria employeeServiceCriteria;

    @GetMapping("/empTest")
    @ResponseBody
    public String testMethod() {

        employeeServiceCriteria.getEmployees();

        employeeServiceCriteria.getSingleCol();

        employeeServiceCriteria.getSingleColAggregation();

        employeeServiceCriteria.projectionMultiCol();

        employeeServiceCriteria.projectionDTO();

        employeeServiceCriteria.projectionTuple();

        return "testEmployee";
    }

}
