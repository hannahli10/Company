package org.example.repository;

import org.example.model.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{   //create
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Employee save(Employee employee) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session =sessionFactory.openSession();
//      Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try{
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            session.save(employee);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return employee;
        }catch (Exception e){
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            logger.error("failure to insert record",e);
            session.close();
            return null;
        }
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session s =sessionFactory.openSession();
//        s.save(emplyee);
//        return employee;
    }

    @Override
    public List<Employee> getEmployees() {     //retrieve,read
        String hql ="From Employee";   //create hibernate query
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  //create session, need hibernateUtil singleton method
        Session s = sessionFactory.openSession();   //create session through session factory
        List<Employee> result =new ArrayList<>(); //initialize List result
        try {
            Query query = s.createQuery(hql);  // create query object
            result = query.list();   //list query object and put it into result
            s.close();    //session need to be closed
        }catch (HibernateException e){
            logger.error("session close exception try again...",e);
            s.close();
        }
        return result;
    }

    @Override
    public Employee getBy(Long id){   //update
        return null;
    }

    @Override
    public boolean delete(Employee employee) {    //delete
        String hql = "DELETE Employee as employee where employee.id = :Id";// :Id is placeholder
        int deletedCount = 0;
        Transaction transaction = null; //1. hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session =sessionFactory.openSession();
        try{
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id",employee.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return deletedCount >= 1 ? true : false;

        }catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            session.close();
            logger.error("failure to insert record", e);
        }
        return false;
    }
}

