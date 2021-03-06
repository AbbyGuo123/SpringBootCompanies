package com.oocl.companies.Controller;

import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }
    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Employee> getEmployeesByPage(@PathVariable int page,@PathVariable int pageSize){
        List<Employee> employees = employeeService.getEmployeesByPage(page,pageSize);
        return employees;
    }
//    @GetMapping("/{gender}")
//    public List<Employee> getEmployeesByGender(@PathVariable String gender){
//        List<Employee> employees = employeeService.getEmployeesByGender(gender);
//        return employees;
//    }

    @PostMapping("")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        if(employeeService.addEmployee(employee)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("")
    public ResponseEntity modifyEmployee(@RequestBody Employee employee){
       if(employeeService.modifyEmployee(employee)){
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id){

        List<Employee> employees = employeeService.deleteEmployee(id);
        return employees;
    }

}
