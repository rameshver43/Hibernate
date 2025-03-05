package org.example.hibernate;

import org.example.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // create student

        // save : hibernate

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("Session Factory: " + sessionFactory);

        Student student = new Student();
        student.setName("Mohan");
        student.setCollege("IIT Jodhpur");
        student.setActive(true);
        student.setPhone("1234512345");
        student.setAbout("This is dummy student");
        student.setFathername("DK Singh");

        Session session = sessionFactory.openSession();

        Transaction transaction  = null;
        try {
            transaction = session.beginTransaction();
            //session.persist(student);
            transaction.commit();
            System.out.println("Student Saved successfully!");
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            if(transaction!=null){
                transaction.rollback();
            }
            else
            {
                ex.printStackTrace();
            }
        }
        finally {
            session.close();
        }

    }
}
