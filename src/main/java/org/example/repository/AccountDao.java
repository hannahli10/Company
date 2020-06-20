package org.example.repository;

import org.example.model.Account;

import java.util.List;

public interface AccountDao {
    Account save(Account account);//create
    List<Account> getAccounts();//retrieve,read
    Account getBy(Long id);//update
    boolean delete(Account account);  //delete;
}
