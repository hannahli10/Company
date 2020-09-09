package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class DepartmentDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
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
    @Test
    public void getDepartmentByIdTest(){
        Long deptId = Long.valueOf(1);
        Department department = departmentDao.getBy(deptId);
        Assert.assertEquals(deptId,department.getId());

    }


//    @Test
//    public void getDepartmentEagerByTest(){
//        logger.debug("Start unit test for getCustomerEagerByTest...");
//        Department department = departmentDao.getDepartmentEagerBy(d1.getId());
//        Assert.assertNotNull(department);
//        Assert.assertEquals(department.getName(),d1.getName());
//        Assert.assertTrue(department.getEmployees().size()>0);
//    }
}
