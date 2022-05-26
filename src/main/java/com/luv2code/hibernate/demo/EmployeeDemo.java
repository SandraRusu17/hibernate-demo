package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Employee.class)
                                            .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 1;
            // save object in data base

            // create employee object
            Employee employee1 = new Employee("Sandra", "Rusu", "NASA");
            Employee employee2 = new Employee("Ana", "D", "Google");
            Employee employee3 = new Employee("Corina", "V", "Facebook");

            // start the transaction
            session.beginTransaction();

            // save the employee
            System.out.println("Save the employee :");
            session.save(employee1);
            session.save(employee2);
            session.save(employee3);

            // retrieve employee by primary key
            Employee myEmployee = session.get(Employee.class, studentId);
            System.out.println("The retrieved employee :" + myEmployee);

            // change the company for an employee
            myEmployee.setCompany("Stefanini");

            // find employee for a specific company
            List<Employee> employees = session.createQuery("from Employee e where e.company ='Google'").getResultList();

            // delete employee by a primary key
            session.delete(myEmployee);

            // print the employees for a specific company
            System.out.println(" All employees for a specific company : ");
            displayEmployees(employees);

            // commit the transaction
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
