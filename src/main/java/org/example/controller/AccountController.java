package org.example.controller;

import org.example.model.Account;
import org.example.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")

public class AccountController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountService accountService;
    @RequestMapping(value ="", method = RequestMethod.GET)
    public void getAccounts(){
        logger.debug("i am in the account controller");
    }
    //account/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable(name="id") Long id) {
        logger.debug("i am in the account controller get by" + id);
        return accountService.getBy(id);
    }
}

