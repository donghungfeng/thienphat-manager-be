package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.model.request.DepartmentCreateReq;
import com.example.zalo_manager.model.request.DepartmentUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.repository.DepartmentRepository;
import com.example.zalo_manager.service.CompanyService;
import com.example.zalo_manager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    protected BaseRepository<Department> getRepository() {
        return departmentRepository;
    }

    @Override
    public BaseResponse create(DepartmentCreateReq req) {
        Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
        if (company == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
        }
        if (departmentRepository.existsByNameAndCompany(req.getName(), company)){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Phòng ban thuộc công ty đã tồn tại");
        }
        Department department = Department
                .builder()
                .name(req.getName())
                .company(company)
                .build();
        return BaseResponse.success(department);
    }

    @Override
    public BaseResponse update(DepartmentUpdateReq req) {
        Department department = departmentRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (department == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Phòng ban không tồn tại");
        }
        Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
        if (company == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
        }
        if (departmentRepository.existsByNameAndCompany(req.getName(), company)){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Phòng ban thuộc công ty đã tồn tại");
        }
        department.setName(req.getName());
        department.setCompany(company);
        return BaseResponse.success(departmentRepository.save(department));
    }
}
