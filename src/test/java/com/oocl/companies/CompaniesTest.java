package com.oocl.companies;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.ServiceImpl.CompaniesServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompaniesTest {
    @Autowired
    private CompaniesServiceImpl companiesService= new CompaniesServiceImpl();
    @Test
    public void should_return_employees_when_call_get_all_Employees(){
        List<Companies> employees = companiesService.getAllCompanies();
        assertThat(employees.size(),not(0));
    }
}
