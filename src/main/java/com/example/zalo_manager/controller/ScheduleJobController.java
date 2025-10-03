package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.ScheduleJob;
import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.model.request.ScheduleJobCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.ScheduleJobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("schedule")
public class ScheduleJobController extends BaseController<ScheduleJob, ScheduleJob>{
    public ScheduleJobController() {
        super(ScheduleJob.class);
    }
    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    protected BaseService<ScheduleJob> getService() {
        return scheduleJobService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid ScheduleJobCreateReq req){
        return scheduleJobService.create(req);
    }
}
