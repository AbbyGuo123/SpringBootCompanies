package com.oocl.companies.memoryDB;

import com.oocl.companies.Model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MemoryDB {
    private List<Employee> employees = new ArrayList<>();

    public MemoryDB() {
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"a",1,"male",1));
        employees.add(new Employee(3,"a",1,"male",1));
        employees.add(new Employee(4,"a",1,"male",1));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void addEmployee(Employee employee) {
        this.employees .add(employee);
    }
}
