package org.example.repository;
import org.example.ApplicationBootstrap;
import org.example.model.Account;
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

public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;

//    private AccountDao accountDao = new AccountDaoImpl();
    private Account a1;
    private String accountString ="saving account";



    @Before   //save
    public void setUp(){

        accountDao = new AccountDaoImpl();
            a1 = new Account();
            a1.setAccountType(accountString);
            a1.setBalance("1000.00");
            accountDao.save(a1,Long.valueOf(1));
    }
    @After     //delete
    public void tearDown() {
//        accountDao.delete(testObject);
        accountDao.delete(a1);
    }

    @Test
    public void getAccountsTest(){
        List<Account> accounts = accountDao.getAccounts();
        int expectedNumOfAccount = 1;

//        departments.forEach(Account -> System.out.println(Account));
        Assert.assertEquals(expectedNumOfAccount, accounts.size());
    }
}
