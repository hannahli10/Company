package org.example.service;

import org.example.model.Department;
import org.example.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    public Department save(Department department) {
        return departmentDao.save(department);
    }

    public List<Department> getDepartments(){
        return departmentDao.getDepartments();
    }

    public Department getBy(Long id){
        return departmentDao.getBy(id);
    }

    public boolean delete(Department department){
        return departmentDao.delete(department);
    }

    public Department update(Department department) {
        return departmentDao.update(department);
    }

    public Department getDepartmentEagerBy(Long id){
        return departmentDao.getDepartmentEagerBy(id);
    }
}