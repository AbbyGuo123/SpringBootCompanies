package com.oocl.companies;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.ServiceImpl.CompaniesServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompaniesTest {
    @Autowired
    private CompaniesServiceImpl companiesServiceImpl= new CompaniesServiceImpl();
    @Test
    public void should_return_employees_when_call_get_all_Employees(){
        List<Companies> employees = companiesServiceImpl.getAllCompanies();
        assertThat(employees.size(),not(0));
    }
    @Test
    public void should_return_filterCompanies_when_call_getCompaniesByPage_input_page_1_pageSize_2(){
        List<Companies> companies = companiesServiceImpl.getCompaniesByPage(1,2);
        assertThat(companies.size(),is(2));
    }
}
