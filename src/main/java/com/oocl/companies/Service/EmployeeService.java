package com.oocl.companies.Service;

import com.oocl.companies.Model.Employee;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployeeById(int id);
    public List<Employee> getAllEmployees();
    public List<Employee> getEmployeesByPage(int page,int pageSize);
    public List<Employee> getEmployeesByGender(String gender);
    public List<Employee> addEmployee(@RequestBody Employee employee);
    public List<Employee> modifyEmployee(Employee employee);
}
