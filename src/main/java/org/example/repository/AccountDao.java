package org.example.repository;

import org.example.model.Account;
import org.example.model.Employee;

import java.util.List;

public interface AccountDao {
    Account save(Account account, Employee employee);//create
    List<Account> getAccounts();//retrieve,read
    Account getBy(Long id);//update
    boolean delete(Account account);  //delete;
    Account update(Account account);
}
