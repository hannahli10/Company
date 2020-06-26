package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Account;
import org.example.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)

public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private AccountDao accountDao;

//    private EmployeeDao employeeDao = new EmployeeDaoImpl();
//    private AccountDao accountDao = new AccountDaoImpl();
    private Employee e1;
    private Account a1;
    private Account a2;
    private String employeeString ="HR1";

    @Before   //save
    public void setUp(){
//        employeeDao =new EmployeeDaoImpl();
//        AccountDao = new AccountDaoImpl();
        e1 = new Employee();
        e1.setName(employeeString);
        e1.setFirstName("Anni");
        e1.setLastName("Zhang");
        e1.setEmail("annizhang@gamil.com");
        e1.setAddress("US");
        employeeDao.save(e1,Long.valueOf(1));


        a1 = new Account();
        a1.setAccountType("saving account");
//        a1.setBalance(BigDecimal.valueOf(1000));
//        //update accounts set employee_id=1 where account.accountType='checking account';
//        //a1.setEmployee_id(e1.getId);
        a1.setEmployee(e1);
        accountDao.save(a1,Long.valueOf(1));
        a2 = new Account();
        a2.setAccountType("checking account");
        a2.setEmployee(e1);
        accountDao.save(a2,Long.valueOf(2));
    }
    @After     //delete
    public void tearDown() {

        //logic 1 delete record in many owning side
//        accountDao.delete(a1);
//        accountDao.delete(a2);
        //logic 2 delete record in one inverse side
        employeeDao.delete(e1);
    }

    @Test
    public void getEmployeeTest(){
        List<Employee> employees = employeeDao.getEmployees();
        int expectedNumOfEmployee = 1;

//      Employees.forEach(emol-> System.out.println(emol));
        Assert.assertEquals(expectedNumOfEmployee, employees.size());
    }
}
