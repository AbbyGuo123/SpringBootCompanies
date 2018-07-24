package com.oocl.companies.ServiceImpl;

import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import com.oocl.companies.memoryDB.MemoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private MemoryDB memoryDB =new MemoryDB();
    public Employee getEmployeeById(int id){
        List<Employee> employees = memoryDB.getEmployees();
        Employee employee = employees.stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);
        return employee;
    }
}
