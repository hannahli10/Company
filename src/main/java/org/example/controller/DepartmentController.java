package org.example.controller;


import org.example.model.Department;
import org.example.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/department")

public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DepartmentService departmentService;
    //department    GET
    @RequestMapping(value ="", method = RequestMethod.GET)
    public List<Department> getDepartments(){
        logger.warn("i am in the account controller");
        return departmentService.getDepartments();
    }
    //department/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable(name="id") Long id) {
        logger.warn("i am in the department controller get by" + id);
        return departmentService.getBy(id);
    }
   //department/1?name=HR1  PATCH
    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public Department updateDepartment(@PathVariable("id")Long id,@RequestParam("name")String name){
        logger.warn("i am in the department controller update " + id + name);
        Department d = departmentService.getBy(id);
        d.setName(name);
        d = departmentService.update(d);
        return d;
    }
    //department  POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create (@RequestBody Department newObject) {
        logger. warn(newObject.toString());
    }
}
