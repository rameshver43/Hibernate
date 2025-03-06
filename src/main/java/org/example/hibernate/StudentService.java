package org.example.hibernate;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

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

    //HQL [JPA] -> native query
    // database independent

    // get all student using hql
    public List<Student> getAllStudentHQL(){
        try(Session session = sessionFactory.openSession()){
            String getHQL = "FROM Student";
            Query<Student> query =  session.createQuery(getHQL, Student.class);
            return query.list();
        }
    }

    // getStudent By name

    public Student getStudentByNameHQL(String name){
        try(Session session = sessionFactory.openSession()){
            String getByNameHql = "FROM Student WHERE name = :studentName";
            Query<Student> query = session.createQuery(getByNameHql, Student.class);
            query.setParameter("studentName",name);
            return query.uniqueResult();
        }
    }

    //criteria api
    // get All student of same college
    public List<Student> getStudentByCollegeCriteria(String college){
        try(Session session = sessionFactory.openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

           CriteriaQuery<Student> query =  criteriaBuilder.createQuery(Student.class);
           Root<Student> root = query.from(Student.class);

           query.select(root)
                   .where(criteriaBuilder.equal(root.get("college"), college));

           Query<Student> query1 = session.createQuery(query);
           return query1.getResultList();
        }
    }

    //Pagination using hql
    public List<Student> getStudentWithPagination(int pageNo, int pageSize){
        try(Session session = sessionFactory.openSession()){
            String pagiQuery = "FROM Student";
            Query<Student> query = session.createQuery(pagiQuery, Student.class);

            query.setFirstResult((pageNo-1)*pageSize);
            query.setMaxResults(pageSize);

            return query.list();
        }
    }
}
