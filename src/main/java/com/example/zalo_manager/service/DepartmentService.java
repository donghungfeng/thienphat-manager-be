package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Department;
import com.example.zalo_manager.model.request.DepartmentCreateReq;
import com.example.zalo_manager.model.request.DepartmentUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface DepartmentService extends BaseService<Department> {
    BaseResponse create(DepartmentCreateReq req);
    BaseResponse update(DepartmentUpdateReq req);
}
