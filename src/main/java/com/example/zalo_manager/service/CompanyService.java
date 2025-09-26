package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.model.request.CompanyCreateReq;
import com.example.zalo_manager.model.request.CompanyUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface CompanyService extends BaseService<Company> {
    BaseResponse create(CompanyCreateReq req);
    BaseResponse update(CompanyUpdateReq req);
}
