package com.oocl.companies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.companies.Controller.CompaniesController;
import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompaniesController.class)
public class CompaniesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CompaniesService companiesService;

    @Test
    public void should_return_companies_when_call_get_all_Companies() throws Exception{
        //given
       List<Employee> employees = new ArrayList<>();
       List<Companies> companies = new ArrayList<>();
       employees.add(new Employee(1,"a",1,"male",1));
       employees.add(new Employee(2,"b",1,"male",1));
       companies.add(new Companies("a",employees.size(),employees));

       //when
        given(this.companiesService.getAllCompanies())
                .willReturn(companies);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/companies"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].companyName",is("a")))
                .andExpect(jsonPath("$[0].employeesNumber",is(employees.size())))
                .andExpect(jsonPath("$[0].employees",hasSize(2)));
    }

    @Test
    public void should_return_companies_when_call_get_Name() throws Exception{
        //given
        String companyName = "a";
        List<Employee> employees = new ArrayList<>();
        List<Companies> companiesList = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        Companies companies = new Companies(companyName,employees.size(),employees);
        companiesList.add(companies);
        //when
        given(this.companiesService.getCompaniesByName(companyName)).willReturn(companies);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/companies/{0}",companyName));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName",is(companyName)))
                .andExpect(jsonPath("$.employeesNumber",is(employees.size())))
                .andExpect(jsonPath("$.employees",hasSize(2)));

    }

    //getEmployeeByCompaniesName

    @Test
    public void should_return_filterCompanies_when_call_getCompaniesByPage_input_page_1_pageSize_2() throws Exception{
        //given
        String companyName = "a";
        List<Employee> employees = new ArrayList<>();
        List<Companies> companiesList = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        Companies companies = new Companies(companyName,employees.size(),employees);
        companiesList.add(companies);
        //when
        given(this.companiesService.getCompaniesByPage(1,1)).willReturn(companiesList);
        //then
        ResultActions resultActions = this.mockMvc.perform(get("/companies/page/{0}/pageSize/{1}",1,1));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].companyName",is("a")))
                .andExpect(jsonPath("$[0].employeesNumber",is(employees.size())))
                .andExpect(jsonPath("$[0].employees",hasSize(2)));

    }

    @Test
    public void should_return_isCreated_when_call_addCompanies() throws Exception{
        //given
        Companies companies = new Companies("a11",2);

        when(companiesService.addCompanies(any(Companies.class))).thenReturn(true);
        //when
        ResultActions result = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(companies)));
        //then
        result.andExpect(status().isCreated())
        .andDo(print());

    }
    @Test
    public void should_return_badRequest_when_call_addCompanies() throws Exception{
        //given
        Companies companies = new Companies("a11",2);

        when(companiesService.addCompanies(any(Companies.class))).thenReturn(false);
        //when
        ResultActions result = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(companies)));
        //then
        result.andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    public void should_return_companies_when_call_modifyCompanies()throws Exception{
        //given
        String companyName = "a";
        List<Employee> employees = new ArrayList<>();
        List<Companies> companiesList = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        Companies companies = new Companies(companyName,employees.size(),employees);
        companiesList.add(companies);

        when(companiesService.modifyCompanies(any(Companies.class))).thenReturn(companiesList);
        //when
        ResultActions result = mockMvc.perform
                (put("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(companies))
                );
        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].companyName",is("a")))
                .andExpect(jsonPath("$[0].employeesNumber",is(employees.size())))
                .andExpect(jsonPath("$[0].employees",hasSize(2)));

    }
    @Test
    public void should_return_noContent_when_call_delete_employee_id() throws Exception{
        //given
        String companyName = "a";
        List<Employee> employees = new ArrayList<>();
        List<Companies> companiesList = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        Companies companies = new Companies(companyName,employees.size(),employees);
        companiesList.add(companies);
        //when
        given(this.companiesService.deleteCompanies(companyName)).willReturn(true);
        //then
        ResultActions resultActions = this.mockMvc.perform(delete("/companies/{0}",companyName));

        resultActions.andExpect(status().isNoContent());


    }
    @Test
    public void should_return_notFound_when_call_delete_employee_id() throws Exception{
        //given
        String companyName = "a";
        List<Employee> employees = new ArrayList<>();
        List<Companies> companiesList = new ArrayList<>();
        employees.add(new Employee(1,"a",1,"male",1));
        employees.add(new Employee(2,"b",1,"male",1));
        Companies companies = new Companies(companyName,employees.size(),employees);
        companiesList.add(companies);
        //when
        given(this.companiesService.deleteCompanies(companyName)).willReturn(false);
        //then
        ResultActions resultActions = this.mockMvc.perform(delete("/companies/{0}",companyName));

        resultActions.andExpect(status().isNotFound());


    }

}
