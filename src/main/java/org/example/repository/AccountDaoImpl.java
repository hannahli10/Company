package org.example.repository;

import org.example.model.Account;
import org.example.model.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{   //create
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Account save(Account account,Long id) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session =sessionFactory.openSession();
//      Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try{
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            Employee employee = employeeDao.getBy(id);
            account.setEmployee(employee);
            session.save(account);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return account;
        }catch (Exception e){
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            logger.error("failure to insert record",e);
            session.close();
            return null;
        }
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session s =sessionFactory.openSession();
//        s.save(account);
//        return account;
    }

    @Override
    public List<Account> getAccounts() {     //retrieve,read
        String hql ="From Account";   //create hibernate query
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  //create session, need hibernateUtil singleton method
        Session s = sessionFactory.openSession();   //create session through session factory
        List<Account> result =new ArrayList<>(); //initialize List result
        try {
            Query query = s.createQuery(hql);  // create query object
            result = query.list();   //list query object and put it into result
        }catch (HibernateException e){
            logger.error("session close exception try again...",e);
        }finally {
            s.close();    //session need to be closed
        }
        return result;
    }

    @Override
    public Account getBy(Long id) {
        String hql = "FROM Account a where a.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Account> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Account result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
              return null;
        }
   }

    @Override
    public boolean delete(Account account) {    //delete
        String hql = "DELETE Account as account where account.id = :Id";// :Id is placeholder
        int deletedCount = 0;
        Transaction transaction = null; //1. hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session =sessionFactory.openSession();
        try{
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            Query<Account> query = session.createQuery(hql);
            query.setParameter("Id",account.getId());
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
