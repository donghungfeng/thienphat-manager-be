package com.example.zalo_manager.repository;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department>{
    boolean existsByNameAndCompany(String name,Company company);
    boolean existsByNameAndCompanyAndIdNot(String name,Company company,Long id);
}
