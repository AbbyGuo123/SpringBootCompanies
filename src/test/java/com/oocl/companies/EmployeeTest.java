package com.oocl.companies;

import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import com.oocl.companies.ServiceImpl.EmployeeServiceImpl;
import com.oocl.companies.memoryDB.MemoryDB;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmployeeTest {
//    @Autowired
    private EmployeeServiceImpl  employeeServiceImpl= new EmployeeServiceImpl();
    @Test
    public void should_return_employees_when_call_get_all_Employees(){
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        assertThat(employees.size(),not(0));
    }
    @Test
    public void should_return_employees_when_call_get_Employees_id(){
        Employee employee = employeeServiceImpl.getEmployeeById(1);
        assertThat(employee.getId(),is(1));
    }
    @Test
    public void should_return_filterEmployees_when_call_getEmployeesByPage_input_page_1_pageSize_2(){
        List<Employee> employees = employeeServiceImpl.getEmployeesByPage(1,2);
        assertThat(employees.size(),is(2));
        assertThat(employees.get(0).getId(),is(1));
    }

    @Test
    public void should_return_filterEmployees_when_call_getEmployeesByGender_input_gender_male(){
        List<Employee> employees = employeeServiceImpl.getEmployeesByGender("male");
        assertThat(employees.get(0).getGender(),is("male"));
    }
    @Test
    public void should_return_employees_when_call_addEmployee(){
        MemoryDB memoryDB = new MemoryDB();
        int size = memoryDB.getEmployees().size();
        List<Employee> employees = employeeServiceImpl.addEmployee(new Employee(20,"1",2,"female",1));
        assertThat(employees.size(),is(size+1));
    }
    @Test
    public void should_return_employees_when_call_modifyEmployee(){
        List<Employee> employees = employeeServiceImpl.modifyEmployee(new Employee(1,"123",2,"female",1));
        assertThat(employees.get(0).getName(),is("123"));
    }
}
