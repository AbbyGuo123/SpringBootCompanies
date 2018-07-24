package com.oocl.companies.ServiceImpl;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
import com.oocl.companies.memoryDB.MemoryDB;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    private MemoryDB memoryDB =new MemoryDB();
    public List<Companies> getAllCompanies(){
        List<Companies> companies = memoryDB.getCompanies();
        return companies;
    }
    public Companies getCompaniesByName(String name){
        List<Companies> companies = memoryDB.getCompanies();
        Companies companies1 = companies.stream().filter(e->e.getCompanyName()==name).collect(Collectors.toList()).get(0);
        return companies1;
    }
    public List<Companies> getCompaniesByPage(int page,int pageSize){
        int first = (page-1)*pageSize;
        int last = page*pageSize;
        List<Companies> companies = memoryDB.getCompanies();
        List<Companies> filterCompanies = new ArrayList<>();
        for(int i=first;i<last;i++){
            filterCompanies.add(companies.get(i));
        }
        return filterCompanies;
    }

    public List<Companies> addCompanies( Companies companies){
        memoryDB.addCompanies(companies);
        List<Companies> companies1 = memoryDB.getCompanies();
        return companies1;
    }

    public List<Companies> modifyCompanies(Companies companies){
        List<Companies> companies1 = memoryDB.getCompanies();
        for(int i=0;i<companies1.size();i++){
            if(companies1.get(i).getCompanyName()==companies.getCompanyName()){
                companies1.get(i).setEmployees(companies.getEmployees());
                companies1.get(i).setEmployeesNumber(companies.getEmployeesNumber());
            }
        }
        return companies1;
    }
    /**
    public List<Employee> deleteEmployee(int id){
        List<Employee> employees = memoryDB.getEmployees();
        Employee employee = employees.stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);
        employees.remove(employee);
        return employees;
    }**/
}
