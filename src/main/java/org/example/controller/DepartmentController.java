package org.example.controller;


import org.example.model.Department;
import org.example.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping(value ="", method = RequestMethod.GET)
    public void getDepartments(){
        logger.debug("i am in the department controller");
    }
    //department/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable(name="id") Long id) {
        logger.debug("i am in the department controller get by" + id);
        return departmentService.getBy(id);
    }
//    //
//    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
//    public Department updateDepartment(@PathVariable("id")Long id,@RequestParam("name")String name){
//        Department d = departmentService.getBy(id);
//        d.setName(name);
//        d = departmentService.update(d);
//        return d;
//    }
}
