package com.oocl.companies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.companies.Controller.EmployeesController;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
public class EmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EmployeeService employeeService;


    @Test
    public void should_return_employees_when_call_get_all_Employees ()throws Exception{
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        //when
        when(employeeService.getAllEmployees()).thenReturn(employees);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$",hasSize(2)));
    }
    @Test
    public void should_return_employees_when_call_get_Employees_id()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(new Employee(2,"b",1,"male",1));
        //when
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/employees/{0}",1));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("a")))
                .andExpect(jsonPath("$.gender",is("male")))
                .andExpect(jsonPath("$.salary",is(1)));
    }
    @Test
    public void should_return_filterEmployees_when_call_getEmployeesByPage_input_page_1_pageSize_2()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(new Employee(2,"b",1,"male",1));
        //when
        when(employeeService.getEmployeesByPage(1,2)).thenReturn(employees);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/employees/page/{0}/pageSize/{1}",1,2));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

//    @Test
//    public void should_return_filterEmployees_when_call_getEmployeesByGender_input_gender_male()throws Exception{
//        //given
//        Employee employee =new Employee(1,"a",1,"male",1);
//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee);
//        employees.add(new Employee(2,"b",1,"male",1));
//        //when
//        when(employeeService.getEmployeesByGender("male")).thenReturn(employees);
//        //then
//        ResultActions resultActions = this.mockMvc.perform(get("/employees/{0}","male"));
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(2)));
//    }
    @Test
    public void should_return_isCreated_when_call_addEmployee()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        //when
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(true);
        //then
        ResultActions resultActions = this.mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        resultActions.andExpect(status().isCreated());
    }
    @Test
    public void should_return_badRequest_when_call_addEmployee()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        //when
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(false);
        //then
        ResultActions resultActions = this.mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        resultActions.andExpect(status().isBadRequest());
    }
    @Test
    public void should_return_noContent_when_call_modifyEmployee()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        //when
        when(employeeService.modifyEmployee(any(Employee.class))).thenReturn(true);
        //then
        ResultActions resultActions = this.mockMvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        resultActions.andExpect(status().isNoContent());
    }
    @Test
    public void should_return_notFound_when_call_modifyEmployee()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        //when
        when(employeeService.modifyEmployee(any(Employee.class))).thenReturn(false);
        //then
        ResultActions resultActions = this.mockMvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));
        resultActions.andExpect(status().isNotFound());
    }
    @Test
    public void should_return_employees_when_call_delete_employee_id()throws Exception{
        //given
        Employee employee =new Employee(1,"a",1,"male",1);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(new Employee(2,"b",1,"male",1));
        //when
        when(employeeService.deleteEmployee(1)).thenReturn(employees);
        //then
        ResultActions resultActions = this.mockMvc.perform(delete("/employees/{0}",1));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("a")))
                .andExpect(jsonPath("$[0].gender",is("male")))
                .andExpect(jsonPath("$[0].salary",is(1)));
    }
}
