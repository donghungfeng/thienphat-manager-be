package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.model.request.CompanyCreateReq;
import com.example.zalo_manager.model.request.CompanyUpdateReq;
import com.example.zalo_manager.model.request.DepartmentCreateReq;
import com.example.zalo_manager.model.request.DepartmentUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController<Department, Department> {
    @Autowired
    DepartmentService departmentService;

    public DepartmentController() {
        super(Department.class);
    }

    @Override
    protected BaseService<Department> getService() {
        return departmentService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid DepartmentCreateReq req){
        return departmentService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid DepartmentUpdateReq req){
        return departmentService.update(req);
    }
}
