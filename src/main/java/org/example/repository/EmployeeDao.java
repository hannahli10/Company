package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import java.util.List;

public interface EmployeeDao {
    Employee save(Employee employee, Department department);//create
    List<Employee> getEmployees();//retrieve,read
    Employee getBy(Long id);//update
    boolean delete(Employee employee);//delete
    Employee getEmployeeEagerBy(Long id);
    Employee update(Employee employee);
}
