package com.nrbswift.spring4web.dto;

import java.util.Date;

public class EmployeeDTO {

    private String employeeName;

    private Date doj;

    private Double salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeName, Date doj, Double salary) {
        this.employeeName = employeeName;
        this.doj = doj;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO { " +
                "employeeName='" + employeeName + '\'' +
                ", doj=" + doj +
                ", salary=" + salary +
                " }";
    }
}
