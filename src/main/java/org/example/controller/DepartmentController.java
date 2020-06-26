//package org.example.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//// @RequestMapping(value = "/department")
//public class DepartmentController {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    @RequestMapping(value ="/department", method = RequestMethod.GET)
//    public void getDepartments(){
//        logger.debug("i am in the department controller");
//    }
//    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
//    public void getDepartmentById(@PathVariable (name="id") Long id){
//        logger.debug("i am in the department controller get by"+id);
//    }
//
//}
