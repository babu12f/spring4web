package com.nrbswift.spring4web.service;

import com.nrbswift.spring4web.dto.EmployeeDTO;
import com.nrbswift.spring4web.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class EmployeeServiceCriteria {

    /*@Autowired
    private EntityManager entityManager;*/

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void getEmployees() {
        System.err.println("Entity Selection");

        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder  builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("employeeName"),"babor"));

        //Query query = em.createQuery(criteriaQuery);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        List<Employee> employeeList = query.getResultList();

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public void getSingleCol() {
        System.err.println("single query");

        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root.get("employeeName"));

        TypedQuery<String> query = em.createQuery(criteriaQuery);

        List<String> employeeNames = query.getResultList();

        employeeNames.forEach(System.out::println);
    }

    public void getSingleColAggregation() {
        System.err.println("single query aggregation");

        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        //criteriaQuery.select(builder.max(root.get("salary").as(Double.class)));
        criteriaQuery.select(builder.max(root.get("salary")));

        TypedQuery<Double> query = em.createQuery(criteriaQuery);

        Double maxSalary = query.getSingleResult();

        System.out.println("Max Salary of Employees is: " + maxSalary);
    }


    public void projectionMultiCol() {
        System.err.println("multi projection");
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        /*
        * 1st way
        * */
        //criteriaQuery.select(builder.array(root.get("employeeName"), root.get("email"), root.get("salary")));
        //criteriaQuery.multiselect(root.get("employeeName"), root.get("email"), root.get("salary"));

        /*
        * 2nd way
        * */
        Path<Object> namePath = root.get("employeeName");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");
        //criteriaQuery.select(builder.array(namePath, emailPath, salaryPath));
        criteriaQuery.multiselect(namePath, emailPath, salaryPath);

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

        List<Object[]> selectedData = query.getResultList();

        for (Object[] emp : selectedData) {
            String empId = (String) emp[0];
            String empEmail = (String) emp[1];
            double salary = (double) emp[2];

            System.out.println("Employee Name: " + empId + " Employee Email: " + empEmail + " Salary: " + salary);
        }
    }

    public void projectionDTO() {
        System.err.println("DTO Projection");

        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<EmployeeDTO> criteriaQuery = builder.createQuery(EmployeeDTO.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        /*
        * 1st way
        * */
        /*criteriaQuery.select(builder.construct(EmployeeDTO.class,
                root.get("employeeName"), root.get("doj"), root.get("salary")));*/

        /*
        * 2nd way
        * */
        Path<Object> namePath = root.get("employeeName");
        Path<Object> dojPath = root.get("doj");
        Path<Object> salaryPath = root.get("salary");
        criteriaQuery.select(builder.construct(EmployeeDTO.class, namePath, dojPath, salaryPath));

        TypedQuery<EmployeeDTO> query = em.createQuery(criteriaQuery);

        List<EmployeeDTO> employeeDTOList = query.getResultList();

        employeeDTOList.forEach(System.out::println);
    }

    public void projectionTuple() {
        System.err.println("Tuple Projection");

        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        //criteriaQuery.multiselect(root.get("employeeId"), root.get("email"));

        Path<Object> idPath = root.get("employeeId");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");

        criteriaQuery.multiselect(idPath, emailPath, salaryPath);

        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> tupleList = query.getResultList();

        for (Tuple tuple : tupleList) {
            Integer id = (Integer) tuple.get(idPath);
            String email = (String) tuple.get(emailPath);
            Double salary = (double) tuple.get(salaryPath);
            System.out.println("Employee ID: " + id + ", Email: " + email + " Salary : " + salary);
        }
    }

}
