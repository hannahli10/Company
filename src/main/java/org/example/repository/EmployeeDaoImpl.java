package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Department;
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


@Repository  //Bean
public class EmployeeDaoImpl implements EmployeeDao{   //create
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DepartmentDao departmentDao;

    @Override
    public Employee save(Employee employee, Department department) {
        Transaction transaction = null; // 1.hibernate declare transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session =sessionFactory.openSession();
//      Session session =HibernateUtil.getSessionFactory().openSession(); //same as line 16+17
        try{
            transaction = session.beginTransaction(); //2. hibernate declare transaction
            employee.setDepartment(department);
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
    public Employee getEmployeeEagerBy(Long id){
        //  select * from employees as emp left join employees as e on a.account_id=dep.id where emp.id=:Id
        String hql = "FROM Employee e LEFT JOIN FETCH e.accounts where e.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Employee result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
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
        }finally {
            s.close();    //session need to be closed
        }
        return result;
    }

    @Override
    public Employee getBy(Long id){   //update
        String hql = "FROM Employee e where e.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Employee result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
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

