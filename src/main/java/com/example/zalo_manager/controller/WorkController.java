package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Work;
import com.example.zalo_manager.model.request.WorkActionReq;
import com.example.zalo_manager.model.request.WorkUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.WorkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping("work")
public class WorkController extends BaseController<Work, Work>{
    @Autowired
    WorkService workService;

    public WorkController() {
        super(Work.class);
    }

    @Override
    protected BaseService<Work> getService() {
        return workService;
    }

    @GetMapping("info")
    public BaseResponse infor(@RequestParam String username,
                              @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        return workService.infor(username, date);
    }

    @PostMapping("action")
    public BaseResponse action(@RequestBody @Valid WorkActionReq req){
        return workService.action(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid WorkUpdateReq req){
        return workService.update(req);
    }
}
