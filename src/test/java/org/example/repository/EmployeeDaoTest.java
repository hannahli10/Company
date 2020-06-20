package org.example.repository;

import org.example.model.Account;
import org.example.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EmployeeDaoTest {
    private EmployeeDao employeeDao;
    private AccountDao accountDao;
    private Employee e1;
    private Account a1;
    private Account a2;

    @Before   //save
    public void setUp(){
//        employeeDao =new EmployeeDaoImpl();
//        AccountDao = new AccountDaoImpl();
        e1 = new Employee();
        e1.setName("Anni");
        e1.setFirstName("Anni");
        e1.setLastName("Zhang");
        e1.setEmail("annizhang@gamil.com");
        e1.setAddress("US");
        e1=employeeDao.save(e1);

        a1 = new Account();
        a1.setAccountType("saving account");
//        a1.setBalance(1000.00);
//        //update employees set department_id=1 where employee.name='zhang3';
//        //e1.setDepartment_id(d1.getId);
        a1.setEmployee(e1);
        accountDao.save(a1);
        a2 = new Account();
        a2.setAccountType("li4");
        a2.setEmployee(e1);
        accountDao.save(a2);
    }
    @After     //delete
    public void tearDown() {

        //logic 1 delete record in many owning side
        accountDao.delete(a1);
        accountDao.delete(a2);
        //logic 2 delete record in one inverse sideemployeeDao.delete(e1);
    }

    @Test
    public void getEmployeeTest(){
        List<Employee> employees = employeeDao.getEmployees();
        int expectedNumOfEmployee = 5;

//      Employees.forEach(emol-> System.out.println(emol));
        Assert.assertEquals(expectedNumOfEmployee, employees.size());
    }
}
