package service;

import org.example.model.Department;
import org.example.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
@Autowired
private DepartmentDao departmentDao;
    public Department save(Department department){
        return departmentDao.save(department);
    }   //create
    public List<Department> getDepartments(){
        return departmentDao.getDepartments();
    }  //retrieve,read
    public Department getBy(Long id){
        return departmentDao.getBy(id);
    }  //update
    public boolean delete(Department department){
        return departmentDao.delete(department);
    };//delete
    public Department getDepartmentEagerBy(Long id){
        return departmentDao.getDepartmentEagerBy(id);
    };
}
