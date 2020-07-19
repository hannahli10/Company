package org.example.repository;
import org.example.model.User;
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

@Repository  //Bean
public class UserDaoImpl implements UserDao{   //create
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User save(User user) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            session.save(user);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public User findById(Integer id) {
        String hql = "FROM User as u where u.id = :Id";// :Id is placeholder
        Transaction transaction = null; //1. hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            org.hibernate.query.Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return query.uniqueResult();
        }catch (HibernateException e){
        logger.error("session close exception try again...",e);
        return null;
        }
    }
    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserByCredentials(String email, String password) {
        String hql = "FROM User as u where lower(u.email) = :email and u.password = :password";
        logger.warn(String.format("User email: %s, password: %s, email,password"));

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery(hql);
            query.setParameter("email",email.toLowerCase().trim());
            query.setParameter("password",password);
            return query.uniqueResult();
        }
        catch (Exception e){
            logger.error ("can't find user record or session");
        }
        return null;
    }

    @Override
    public boolean delete (User user){    //delete
        String hql = "DELETE User as user where user.id = :Id";// :Id is placeholder
        int deletedCount = 0;
        Transaction transaction = null; //1. hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            org.hibernate.query.Query<User> query = session.createQuery(hql);
            query.setParameter("Id", user.getId());
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

    @Override
    public List<User> findAllUsers() {
        String hql ="From User";   //create hibernate query
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  //create session, need hibernateUtil singleton method
        Session s = sessionFactory.openSession();   //create session through session factory
        List<User> result =new ArrayList<>(); //initialize List result
        try {
            Query query = s.createQuery(hql);  // create query object
            result = query.list();   //list query object and put it into result
            s.close();    //session need to be closed
        }catch (HibernateException e){
            logger.error("session close exception try again...",e);
        }finally {
            s.close();    //session need to be closed
        }
        return result;
    }
}
