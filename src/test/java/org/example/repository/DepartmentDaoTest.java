package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

public class DepartmentDaoTest {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    private Employee e1;
    private Employee e2;
    private Department d1;
    private String depString ="HR1";


    @Before   //save
    public void setUp(){
        //logic 1 save record in one side
      // departmentDao = new DepartmentDaoImpl();
      // employeeDao = new EmployeeDaoImpl();
        d1 = new Department();
        d1.setName(depString);
        d1.setDescription("random description");
        d1.setLocation("US");
        d1=departmentDao.save(d1);
        // d1.setEmployee([feixiong,xiaolu])
        //d1.setEmployee([ryo])
        //d1.setEmployees();
        //logic 2 save record in many side
        e1 = new Employee();
        e1.setName("zhang3");
        e1.setAddress("us");
//        //update employees set department_id=1 where employee.name='zhang3';
//        //e1.setDepartment_id(d1.getId);
        e1.setDepartment(d1);
        employeeDao.save(e1);
        e2 = new Employee();
        e2.setName("li4");
        e2.setDepartment(d1);
        employeeDao.save(e2);
    }
    @After     //delete
    public void tearDown() {
        //logic 1 delete record in many owning side
        employeeDao.delete(e1);
        employeeDao.delete(e2);
        //logic 2 delete record in one inverse side
        departmentDao.delete(d1);
    }

    @Test
    public void getDepartmentsTest(){
        List<Department> departments = departmentDao.getDepartments();
        int expectedNumOfDept = 5;

//        departments.forEach(dept -> System.out.println(dept));
        Assert.assertEquals(expectedNumOfDept, departments.size());
    }
}
