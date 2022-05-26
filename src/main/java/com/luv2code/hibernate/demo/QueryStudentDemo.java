package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {
        // create Session Factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query students
            System.out.println("Student who has first name Sandra !");
            theStudents = session.createQuery("from Student s where s.firstName='Sandra'").getResultList();

            displayStudents(theStudents);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        }finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent: theStudents){
            System.out.println(tempStudent);
        }
    }
}
