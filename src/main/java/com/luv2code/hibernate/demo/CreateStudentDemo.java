package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {
    public static void main(String[] args) {
        // create Session Factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save the Java object

            // create student object
            System.out.println("Creating a new student object ...");
            Student tempStudent = new Student("Sandra", "Rusu", "sandra.rusu@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student
            System.out.println("Saving the student...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }finally {
            factory.close();
        }
    }
}
