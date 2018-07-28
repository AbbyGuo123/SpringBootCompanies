package com.oocl.companies.Controller;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;

    @GetMapping
    public List<Companies> getAllCompanies(){
        System.out.print(companiesService.getAllCompanies().toString());
        return companiesService.getAllCompanies();
    }

    @GetMapping("/{name}")
    public Companies getCompaniesByName(@PathVariable String name){
        Companies companies = companiesService.getCompaniesByName(name);
        return companies;
    }
    @GetMapping("/{name}/employees")
    public List<Employee> getEmployeeByCompaniesName(@PathVariable String name){
        List<Employee> employees = companiesService.getCompaniesByName(name).getEmployees();
        return employees;
    }

    @GetMapping("/page/{page}/pageSize/{pageSize}")
    public List<Companies> getCompaniesByPage(@PathVariable int page,@PathVariable int pageSize){
        List<Companies> companies = companiesService.getCompaniesByPage(page,pageSize);
        return companies;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity addCompanies(@Valid  @RequestBody Companies companies){
        if (companiesService.addCompanies(companies)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PutMapping("")
    public List<Companies> modifyCompanies(@RequestBody Companies companies){
        List<Companies> companies1 = companiesService.modifyCompanies(companies);
        return companies1;
    }
    @DeleteMapping("/{name}")
    public ResponseEntity deleteCompanies(@PathVariable String name){
        if(companiesService.deleteCompanies(name)){
            return  ResponseEntity.noContent().build();
        }
        else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
