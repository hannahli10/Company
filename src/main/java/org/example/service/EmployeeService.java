package org.example.service;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    public Employee save(Employee employee, Department department){
        return employeeDao.save(employee,department);}

    public List<Employee> getEmployees(){
        return employeeDao.getEmployees();}

    public Employee getBy(Long id){
        return employeeDao.getBy(id);}

    public boolean delete(Employee employee){
        return employeeDao.delete(employee);}

    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }


//    public Employee getEmployeeEagerBy(Long id){
//        return employeeDao.getEmployeeEagerBy(id);
//    }

}
