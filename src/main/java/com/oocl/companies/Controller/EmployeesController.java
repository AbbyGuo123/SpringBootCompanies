package com.oocl.companies.Controller;

import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;
//    GET       /employees    #获取employee列表
//    GET       /employees/1  #获取某个具体employee
//    GET       /employees/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
//    GET       /employees/male   #筛选出所有男性Employee
//    POST      /employees    #增加一个employee
//    PUT       /employees/1  #更新某个employee
//    DELETE    /employees/1  #删除某个employee

    public EmployeesController() {

    }

//    @GetMapping("")
//    public List<Employee> getAllEmployees(){
//        return employees;
//    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }

}
