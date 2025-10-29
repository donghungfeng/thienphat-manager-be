package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.CompanyReport;
import com.example.zalo_manager.model.request.CompanyReportCreateReq;
import com.example.zalo_manager.model.request.CompanyReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface CompanyReportService extends BaseService<CompanyReport>{
    BaseResponse create(CompanyReportCreateReq req);
    BaseResponse update(CompanyReportUpdateReq req);
}
