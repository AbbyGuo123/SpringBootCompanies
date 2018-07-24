package com.oocl.companies.Service;

import com.oocl.companies.Model.Companies;

import java.util.List;

public interface CompaniesService {
    public List<Companies> getAllCompanies();
    public Companies getCompaniesByName(String name);
    public List<Companies> getCompaniesByPage(int page,int pageSize);
    public List<Companies> addCompanies(Companies companies);
    public List<Companies> modifyCompanies(Companies companies);
    //public List<Companies> deleteCompanies(int id);
}
