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

public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

//    private DepartmentDao departmentDao = new DepartmentDaoImpl();
//    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    private Employee e1;
    private Employee e2;
    private Department d1;
    private String depString = "HR1";


    @Before   //save
    public void setUp() {
        //logic 1 save record in one side
        // departmentDao = new DepartmentDaoImpl();
        // employeeDao = new EmployeeDaoImpl();
        d1 = new Department();
        d1.setName(depString);
        d1.setDescription("random description");
        d1.setLocation("US");
        departmentDao.save(d1);
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
        employeeDao.save(e1,Long.valueOf(1));
        e2 = new Employee();
        e2.setName("li4");
        e2.setDepartment(d1);
        employeeDao.save(e2,Long.valueOf(2));
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
    public void getDepartmentsTest() {
        List<Department> departments = departmentDao.getDepartments();
        int expectedNumOfDept = 5;

//        departments.forEach(dept -> System.out.println(dept));
        Assert.assertEquals(expectedNumOfDept, departments.size());
    }
//    @Test
//    public void getDepartmentEagerByTest(){
//        Department department = departmentDao.getDepartmentEagerBy(d1.getId());
//        assertNotnull(department);
//        AassertEquals(department);
//        assertEquals(department.getEmployees().size()>0);
//    }
}
