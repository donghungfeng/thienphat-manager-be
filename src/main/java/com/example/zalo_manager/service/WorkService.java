package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.request.WorkActionReq;
import com.example.zalo_manager.model.request.WorkUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;

import java.time.LocalDate;

public interface WorkService extends BaseService<Work>{
    BaseResponse infor(String username, LocalDate date);
    BaseResponse action(WorkActionReq req);
    BaseResponse update(WorkUpdateReq req);
}