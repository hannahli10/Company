package org.example.repository;

import org.example.model.Department;
import java.util.List;

public interface DepartmentDao {
    Department save(Department department);//create
    List <Department> getDepartments();//retrieve,read
    Department getBy(Long id);//update
    boolean delete(Department department);//delete
    Department getDepartmentEagerBy(Long id);


}
