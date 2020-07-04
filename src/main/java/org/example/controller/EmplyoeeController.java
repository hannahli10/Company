package org.example.controller;


import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")

public class EmplyoeeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value ="", method = RequestMethod.GET)
    public void getEmployees(){
        logger.debug("i am in the employee controller");
    }
    //employee/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable(name="id") Long id) {
            logger.debug("i am in the employee controller get by" + id);
            return employeeService.getBy(id);
    }
}

