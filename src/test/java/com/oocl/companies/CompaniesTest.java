package com.oocl.companies;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.ServiceImpl.CompaniesServiceImpl;
import com.oocl.companies.memoryDB.MemoryDB;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CompaniesTest {
    @Autowired
    private CompaniesServiceImpl companiesServiceImpl= new CompaniesServiceImpl();
    @Test
    public void should_return_companies_when_call_get_all_Employees(){
        List<Companies> employees = companiesServiceImpl.getAllCompanies();
        assertThat(employees.size(),not(0));
    }
    @Test
    public void should_return_companies_when_call_get_Employees_Name(){
        Companies companies = companiesServiceImpl.getCompaniesByName("a");
        assertThat(companies.getCompanyName(),is("a"));
    }
    @Test
    public void should_return_filterCompanies_when_call_getCompaniesByPage_input_page_1_pageSize_2(){
        List<Companies> companies = companiesServiceImpl.getCompaniesByPage(1,2);
        assertThat(companies.size(),is(2));
    }

    @Test
    public void should_return_companies_when_call_addCompanies(){
        MemoryDB memoryDB = new MemoryDB();
        int size = memoryDB.getCompanies().size();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3,"c",1,"male",1));
        employees.add(new Employee(4,"d",1,"male",1));
        Companies companies = new Companies("b",employees.size(),employees);
        List<Companies> companiess = companiesServiceImpl.addCompanies(companies);
        assertThat(companiess.size(),is(size+1));
    }
}
