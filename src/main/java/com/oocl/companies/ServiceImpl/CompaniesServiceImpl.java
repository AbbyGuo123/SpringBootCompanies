package com.oocl.companies.ServiceImpl;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;
import com.oocl.companies.Service.CompaniesService;
import com.oocl.companies.memoryDB.MemoryDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    private MemoryDB memoryDB =new MemoryDB();
    public List<Companies> getAllCompanies(){
        List<Companies> companies = memoryDB.getCompanies();
        return companies;
    }
}
