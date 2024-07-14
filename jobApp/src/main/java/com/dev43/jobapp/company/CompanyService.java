package com.dev43.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompanyById(Long id,Company company);
    void createCompany(Company company);
}
