package com.oocl.companies.Controller;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;
//    GET       /companies    #获取company列表
//    GET       /companies/1  #获取某个具体company
//    GET       /companies/1/employees  #获取某个具体company下所有employee列表
//    GET       /companies/page/1/pageSize/5  #分页查询，page等于1，pageSize等于5
//    POST      /companies    #增加一个company
//    PUT       /companies/1  #更新某个company
//    DELETE    /companies/1  #删除某个company以及名下所有employees

@GetMapping("")
public List<Companies> getAllCompanies(){
    return companiesService.getAllCompanies();
}

    @GetMapping("/{id}")
    public Companies getCompaniesByID(@PathVariable int id){
        Companies companies = companiesService.getCompaniesById(id);
        return companies;
    }
    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Companies> getCompaniesByPage(@PathVariable int page,@PathVariable int pageSize){
        List<Companies> companies = companiesService.getCompaniesByPage(page,pageSize);
        return companies;
    }
}
