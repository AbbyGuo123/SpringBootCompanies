package com.oocl.companies.Controller;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;

//    GET       /companies/1/employees  #获取某个具体company下所有employee列表

//    DELETE    /companies/1  #删除某个company以及名下所有employees

    @GetMapping("")
    public List<Companies> getAllCompanies(){
    return companiesService.getAllCompanies();
}

    @GetMapping("/{name}")
    public Companies getCompaniesByName(@PathVariable String name){
        Companies companies = companiesService.getCompaniesByName(name);
        return companies;
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Companies> getCompaniesByPage(@PathVariable int page,@PathVariable int pageSize){
        List<Companies> companies = companiesService.getCompaniesByPage(page,pageSize);
        return companies;
    }

    @PostMapping("")
    public List<Companies> addCompanies(@RequestBody Companies companies){
        List<Companies> companies1= companiesService.addCompanies(companies);
        return companies1;
    }
    @PutMapping("")
    public List<Companies> modifyCompanies(@RequestBody Companies companies){
        List<Companies> companies1 = companiesService.modifyCompanies(companies);
        return companies1;
    }
}
