//package org.example.repository;
//
//import org.example.model.Department;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class DepartmentDao2Test {
//
//    private DepartmentDao departmentDao;
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Before
//    public void setUp(){
//        departmentDao = new DepartmentDao() {
//            @Override
//            public Department save(Department department) {
//                return null;
//            }
//
//            @Override
//            public List<Department> getDepartments() {
//                return null;
//            }
//
//            @Override
//            public Department getBy(Long id) {
//                return null;
//            }
//
//            @Override
//            public boolean delete(Department department) {
//                return false;
//            }
//        };
//    }
//
//    @After
//    public void tearDown(){
//        departmentDao = null;
//    }
//
//    @Test
//    public void getDepartmentsTest() {
//        logger.debug("Start unit test for getDepartmentsTest");
//        List<Department> departmentList = departmentDao.getDepartments();
//        assertEquals(4, departmentList.size());
//    }
//}
//
//
//
