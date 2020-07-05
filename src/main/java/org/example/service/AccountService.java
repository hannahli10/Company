package org.example.service;

import org.example.model.Account;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account save(Account account, Employee employee) {
        return accountDao.save(account, employee);
    }

    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    public Account getBy(Long id) {
        return accountDao.getBy(id);
    }

    public boolean delete(Account account){
        return accountDao.delete(account);
    }

    public Account update(Account account) {
        return accountDao.update(account);
    }
}
