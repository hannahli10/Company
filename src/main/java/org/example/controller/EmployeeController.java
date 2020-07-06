package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")

public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value ="", method = RequestMethod.GET)
    public List<Employee> getEmployees(){
            logger.debug("i am in the account controller");
            return employeeService.getEmployees();
        }
        //department/1 GET
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public Employee getByEmployee(@PathVariable(name="id") Long id) {
            logger.debug("i am in the department controller get by" + id);
            return employeeService.getBy(id);
        }

        @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
        public Employee updateEmployee(@PathVariable("id")Long id,@RequestParam("name")String name){
            Employee e = employeeService.getBy(id);
            e.setName(name);
            e = employeeService.update(e);
            return e;
        }
    }

