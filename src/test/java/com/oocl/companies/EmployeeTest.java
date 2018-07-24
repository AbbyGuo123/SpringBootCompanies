package com.oocl.companies;

import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import com.oocl.companies.ServiceImpl.EmployeeServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmployeeTest {
//    @Autowired
    private EmployeeServiceImpl  employeeServiceImpl= new EmployeeServiceImpl();
    @Test
    public void should_return_employees_when_call_get_Employees_id(){
        Employee employee = employeeServiceImpl.getEmployeeById(1);
        assertThat(employee.getId(),is(1));

    }
}
