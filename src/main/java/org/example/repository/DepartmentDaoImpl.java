package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {   //create
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Department save(Department department) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//      Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            session.save(department);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return department;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
        //  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //  Session s =sessionFactory.openSession();
        //  s.save(department);
        //  return department;
    }
    public Department getDepartmentEagerBy(Long id){
     //  select * from departments as dep left join employees as e on a.employee_id=dep.id where dep.id=:Id
        String hql = "FROM Department d LEFT JOIN FETCH d.employees where d.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Department result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }


    @Override
    public List<Department> getDepartments() {     //retrieve,read
        String hql = "From Department";   //create hibernate query
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  //create session, need hibernateUtil singleton method
        Session s = sessionFactory.openSession();   //create session through session factory
        List<Department> result = new ArrayList<>(); //initialize List result
        try {
            Query query = s.createQuery(hql);  // create query object
            result = query.list();   //list query object and put it into result
        } catch (HibernateException e) {
            logger.error("session close exception try again...", e);
        } finally {
            s.close();    //session need to be closed
        }
        return result;
    }

    @Override
    public Department getBy(Long id) {//update
        String hql = "FROM Department d where d.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Department result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

        @Override
        public boolean delete (Department dep){    //delete
            String hql = "DELETE Department as dep where dep.id = :Id";// :Id is placeholder
            int deletedCount = 0;
            Transaction transaction = null; //1. hibernate declare transaction
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            try {
                transaction = session.beginTransaction(); //2. hibernate declare transaction
                Query<Department> query = session.createQuery(hql);
                query.setParameter("Id", dep.getId());
                deletedCount = query.executeUpdate();
                transaction.commit();  //3. hibernate commit transaction
                session.close();
                return deletedCount >= 1 ? true : false;

            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
                session.close();
                logger.error("failure to insert record", e);
            }
            return false;
        }
    }
