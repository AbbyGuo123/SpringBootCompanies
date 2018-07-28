package com.oocl.companies.Service;

import com.oocl.companies.Model.Companies;
import com.oocl.companies.Model.Employee;

import java.util.List;

public interface CompaniesService {
    public List<Companies> getAllCompanies();
    public Companies getCompaniesByName(String name);
    public List<Companies> getCompaniesByPage(int page,int pageSize);
    public Boolean addCompanies(Companies companies);
    public List<Companies> modifyCompanies(Companies companies);
    public Boolean deleteCompanies(String name);
}
