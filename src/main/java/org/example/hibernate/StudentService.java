package org.example.hibernate;

import org.example.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void saveStudent(Student student) {
        try(Session session  = sessionFactory.openSession()) {
            Transaction beginTransaction = session.beginTransaction();
            session.persist(student);
            beginTransaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Student getById(long studentId){
        try(Session session  = sessionFactory.openSession()) {
            return session.get(Student.class, studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student updateStudent(long studentId, Student student){
        try(Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Student oldStudent = session.get(Student.class, studentId);

            if(oldStudent!=null){
                oldStudent.setName(student.getName());
                // update more information

                oldStudent = session.merge(oldStudent);
            }
            transaction.commit();
            return oldStudent;
        }
    }

    public void deleteStudent(long studentId){
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if(student!=null)
                session.remove(student);
            transaction.commit();
        }
    }
}
