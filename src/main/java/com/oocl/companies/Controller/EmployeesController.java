package com.oocl.companies.Controller;

import com.oocl.companies.Model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
//    @Autowired
//    private EmployeesService employeesService;

    List<Employee> employees = new ArrayList<>();
    public EmployeesController() {
        employees.add(new Employee(1,"a",1,"man",1));
        employees.add(new Employee(2,"a",1,"man",1));
    }

    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return employees;
    }
}
