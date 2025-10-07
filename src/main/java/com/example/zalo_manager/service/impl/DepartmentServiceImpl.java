package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.model.request.DepartmentCreateReq;
import com.example.zalo_manager.model.request.DepartmentUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CompanyRepository;
import com.example.zalo_manager.repository.DepartmentRepository;
import com.example.zalo_manager.service.CompanyService;
import com.example.zalo_manager.service.DepartmentService;
import com.example.zalo_manager.util.MapperUtil;
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
        Department department = MapperUtil.map(req, Department.class);
        return BaseResponse.success(departmentRepository.save(department));
    }

    @Override
    public BaseResponse update(DepartmentUpdateReq req) {
        Department department = departmentRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (department == null){
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Phòng ban không tồn tại");
        }
        return BaseResponse.success(departmentRepository.save(MapperUtil.mapValue(req, department)));
    }
}
