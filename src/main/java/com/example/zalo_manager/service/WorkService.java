package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.response.BaseResponse;

import java.time.LocalDate;

public interface WorkService extends BaseService<Work>{

    BaseResponse infor(String username, LocalDate date);
}
