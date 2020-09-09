package org.example.repository;

import org.example.model.Employee;
import org.example.model.Role;
import org.example.model.User;
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

@Repository  //Bean
public class RoleDaoImpl implements RoleDao{   //create
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Role save(Role role) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            session.save(role);
            transaction.commit();  //3. hibernate commit transaction
            session.close();
            return role;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //4. hibernate rollback transaction
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Role getById(Long id) {
        String hql = "FROM Role as r where r.id = :Id";// :Id is placeholder
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            org.hibernate.query.Query<Role> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Role result = query.uniqueResult();
            session.close();
            return result;

        }catch (HibernateException e){
            logger.error("session close exception try again...",e);
            return null;
        }
    }

    @Override
    public Role getRoleByName (String name) {
        return null;
    }


    @Override
    public boolean delete (Role role){    //delete
        String hql = "DELETE Role as role where role.id = :Id";// :Id is placeholder
        int deletedCount = 0;
        Transaction transaction = null; //1. hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            org.hibernate.query.Query<Role> query = session.createQuery(hql);
            query.setParameter("Id", role.getId());
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
    public List<Role> findAllRoles() {//retrieve,read
        String hql ="From Role";   //create hibernate query
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  //create session, need hibernateUtil singleton method
        Session s = sessionFactory.openSession();   //create session through session factory
        List<Role> result =new ArrayList<>(); //initialize List result
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



