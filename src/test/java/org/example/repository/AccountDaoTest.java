package org.example.repository;
import org.example.model.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountDaoTest {
    private AccountDao accountDao;
    private Account a1;



    @Before   //save
    public void setUp(){
        //logic 1 save record in one side
        accountDao = new AccountDaoImpl();
        a1 = new Account();
        a1.setAccountType("saving account");
        a1.setBalance(BigDecimal.valueOf(1000));

        //User u = "12341"
        a1= accountDao.save(a1);
    }
    @After     //delete
    public void tearDown() {
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
