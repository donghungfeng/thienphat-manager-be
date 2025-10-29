package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Report;
import com.example.zalo_manager.model.request.ReportCreateReq;
import com.example.zalo_manager.model.request.ReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface ReportService extends BaseService<Report>{
    BaseResponse create(ReportCreateReq req);
    BaseResponse update(ReportUpdateReq req);
}
