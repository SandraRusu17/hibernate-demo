package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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

            // create students objects
            System.out.println("Creating 3 student objects ...");
            Student tempStudent1 = new Student("Sandra", "Rusu", "sandra.rusu@gmail.com");
            Student tempStudent2 = new Student("Ana", "Sescu", "ana@gmail.com");
            Student tempStudent3 = new Student("Irina", "Ciobanu", "irina@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the students
            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }finally {
            factory.close();
        }
    }
}
