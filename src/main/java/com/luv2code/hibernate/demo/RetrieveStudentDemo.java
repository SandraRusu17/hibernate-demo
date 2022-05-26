package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class RetrieveStudentDemo {
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
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            // ADD NEW CODE

            // find out the student's id : primary key
            System.out.println("Student's id : " + tempStudent.getId());

            // get a new session and start the transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve the student based on id : primary key
            System.out.println("\nGet the student with id :" + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete " + myStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }finally {
            factory.close();
        }
    }
}
