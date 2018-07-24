package com.oocl.companies.Service;

import com.oocl.companies.Model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployeeById(int id);
    public List<Employee> getAllEmployees();
    public List<Employee> getEmployeesByPage(int page,int pageSize);
}
