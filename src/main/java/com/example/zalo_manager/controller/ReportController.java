package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.Report;
import com.example.zalo_manager.model.dto.ReportDto;
import com.example.zalo_manager.model.request.ReportCreateReq;
import com.example.zalo_manager.model.request.ReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("report")
public class ReportController extends BaseController<Report, ReportDto> {
    @Autowired
    private ReportService reportService;

    public ReportController() {
        super(ReportDto.class);
    }

    @Override
    protected BaseService<Report> getService() {
        return reportService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid ReportCreateReq req){
        return reportService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid ReportUpdateReq req){
        return reportService.update(req);
    }
}
