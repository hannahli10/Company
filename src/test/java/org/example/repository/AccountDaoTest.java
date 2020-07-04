package org.example.repository;
import org.example.ApplicationBootstrap;
import org.example.model.Account;
import org.example.model.Department;
import org.example.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)

public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;
    private Account a1;
    private String accountString ="saving account";
    private Employee e1;
    private Department d1;
    private BigDecimal accountBalance = new BigDecimal("1000.00");

    @Before   //save
    public void setUp(){
        d1 = new Department();
//        d1.setName("HR1");
//        d1.setDescription("random description");
//        d1.setLocation("US");
     //   departmentDao.save(d1);
        e1 = new Employee();
        e1.setName("Anni Zhang");
        e1.setFirstName("Anni");
        e1.setLastName("Zhang");
        e1.setEmail("annizhang@gamil.com");
        e1.setAddress("US");
        employeeDao.save(e1,d1);
        accountDao = new AccountDaoImpl();
            a1 = new Account();
            a1.setAccountType(accountString);
//            a1.setBalance(new BigDecimal(1000.00));
            a1.setBalance(accountBalance);
            accountDao.save(a1,e1);
    }
    @After     //delete
    public void tearDown() {
        accountDao.delete(a1);
        employeeDao.delete(e1);
        departmentDao.delete(d1);
    }

    @Test
    public void getAccountsTest(){
        List<Account> accounts = accountDao.getAccounts();
        int expectedNumOfAccount = 1;

//        departments.forEach(Account -> System.out.println(Account));
        Assert.assertEquals(expectedNumOfAccount, accounts.size());
    }
}
