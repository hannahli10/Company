package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Department;
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
    private String depString = "HR1";
    private Department d1;

    @Before   //save
    public void setUp() {
        d1 = new Department();
        d1.setName(depString);
        d1.setDescription("random description");
        d1.setLocation("US");
        departmentDao.save(d1);
    }

    @After     //delete
    public void tearDown() {
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
//        assertEquals(department);
//        assertEquals(department.getEmployees().size()>0);
//    }
}
