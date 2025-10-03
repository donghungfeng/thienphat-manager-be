package com.example.zalo_manager.service;

import com.example.zalo_manager.entity.ScheduleJob;
import com.example.zalo_manager.model.request.ScheduleJobCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;

public interface ScheduleJobService extends BaseService<ScheduleJob>{
    BaseResponse create(ScheduleJobCreateReq req);
    public void schedule(ScheduleJob job);
}
