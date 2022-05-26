package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {
    public static void main(String[] args) {
        // create Session Factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;

            // find out the student's id : primary key
            System.out.println("Get student with id : " + studentId);

            // get a new session and start the transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // get the student with studentId
            Student theStudent = session.get(Student.class, studentId);
            System.out.println("Updating student ...");

            // delete the student
//            System.out.println("Deleting the student..." + theStudent);
//            session.delete(theStudent);

            // delete student with id =2
            System.out.println("Deleting the student with id = 2");
            session.createQuery("delete from Student where id = 2").executeUpdate();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done !");

        } finally {
            factory.close();
        }
    }
}
