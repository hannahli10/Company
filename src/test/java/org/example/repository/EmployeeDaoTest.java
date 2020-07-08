package org.example.repository;

import org.example.ApplicationBootstrap;
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
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)

public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    private String employeeString ="HR1";
    private Employee e1;
    private Department d1;



    @Before   //save
    public void setUp(){
        d1 = new Department();
        d1.setName("Sales");
        d1.setDescription("random description");
        d1.setLocation("US");
        departmentDao.save(d1);

//        employeeDao =new EmployeeDaoImpl();
//        AccountDao = new AccountDaoImpl();
        e1 = new Employee();
        e1.setName(employeeString);
        e1.setFirstName("Anni");
        e1.setLastName("Zhang");
        e1.setEmail("annizhang@gamil.com");
        e1.setAddress("US");
        employeeDao.save(e1,d1);

    }
    @After     //delete
    public void tearDown() {
//        logic 2 delete record in one inverse side
        employeeDao.delete(e1);
//        logic 1 delete record in many owning side
        departmentDao.delete(d1);
    }

    @Test
    public void getEmployeeTest(){
        List<Employee> employees = employeeDao.getEmployees();
        int expectedNumOfEmployee = 1;
//      Employees.forEach(emol-> System.out.println(emol));
        Assert.assertEquals(expectedNumOfEmployee, employees.size());
    }
}
