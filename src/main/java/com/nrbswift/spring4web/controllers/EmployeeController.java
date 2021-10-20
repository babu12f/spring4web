package com.nrbswift.spring4web.controllers;

import com.nrbswift.spring4web.service.EmployeeServiceCriteria;
import com.nrbswift.spring4web.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServiceCriteria employeeServiceCriteria;

    @Autowired
    PersonService personService;

    @GetMapping("/empTest")
    @ResponseBody
    public String testMethod() {

        /*employeeServiceCriteria.getEmployees();

        employeeServiceCriteria.getSingleCol();

        employeeServiceCriteria.getSingleColAggregation();

        employeeServiceCriteria.projectionMultiCol();

        employeeServiceCriteria.projectionDTO();

        employeeServiceCriteria.projectionTuple();*/

        /**
         * only one time call to set some default data
         *
         *
          personService.saveSampleData();

         * */

        personService.getMultiRootData();

        return "testEmployee";
    }

}
