package org.example.hibernate;

import org.example.hibernate.entities.Certificate;
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
    public static void startAPI(){
        StudentService studentService = new StudentService();
        Student student = new Student();
        student.setName("Rahul Sharma");
        student.setCollege("IIT Delhi");
        student.setActive(true);
        student.setPhone("1234512345");
        student.setAbout("This is dummy student");
        student.setFathername("DK Singh");
//        studentService.saveStudent(student);
       // System.out.println("Student: " + studentService.getById(5));
        //System.out.println(studentService.updateStudent(5,student));
       // studentService.deleteStudent(5);
        //System.out.println(studentService.getStudentByCollegeCriteria("IIT Jodhpur"));
        System.out.println(studentService.getStudentWithPagination(1,10));
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

//        // create student
//
//        // save : hibernate
//
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        System.out.println("Session Factory: " + sessionFactory);
//
//        Student student = new Student();
//        student.setName("Rahul");
//        student.setCollege("IIT Kanpur");
//        student.setActive(true);
//        student.setPhone("1234512345");
//        student.setAbout("This is dummy student");
//        student.setFathername("DK Singh");
//
//        Session session = sessionFactory.openSession();
//
//        Transaction transaction  = null;
//        try {
//            transaction = session.beginTransaction();
//            session.persist(student);
//            transaction.commit();
//            System.out.println("Student Saved successfully!");
//        }catch (Exception ex){
//            System.out.println("Error: " + ex.getMessage());
//            if(transaction!=null){
//                transaction.rollback();
//            }
//            else
//            {
//                ex.printStackTrace();
//            }
//        }
//        finally {
//            session.close();
//        }
        startAPI();
    }
}
