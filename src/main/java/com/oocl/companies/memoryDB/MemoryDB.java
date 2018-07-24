package com.oocl.companies.memoryDB;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MemoryDB {
    private List<Employee> employees = new ArrayList<>();
    private List<Companies> companies = new ArrayList<>();

    public MemoryDB() {
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        companies.add(new Companies("a",employees.size(),employees));
        employees.add(new Employee(3,"c",1,"male",1));
        employees.add(new Employee(4,"d",1,"male",1));
        companies.add(new Companies("b",employees.size(),employees));
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

    public List<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Companies> companies) {
        this.companies = companies;
    }
    public void addCompanies(Companies companies) {
        this.companies .add(companies);
    }
}
