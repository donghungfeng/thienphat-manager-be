package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<Company> {
    boolean existsByName(String name);
    boolean existsByTaxCode(String taxCode);
    boolean existsByAddress(String address);
    boolean existsByPhone(String phone);
}
