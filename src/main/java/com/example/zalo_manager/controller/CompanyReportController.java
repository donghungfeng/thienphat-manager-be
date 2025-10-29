package com.example.zalo_manager.controller;

import com.example.zalo_manager.entity.CompanyReport;
import com.example.zalo_manager.model.dto.company_report.CompanyReportDto;
import com.example.zalo_manager.model.request.CompanyReportCreateReq;
import com.example.zalo_manager.model.request.CompanyReportUpdateReq;
import com.example.zalo_manager.model.request.ReportCreateReq;
import com.example.zalo_manager.model.request.ReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.service.BaseService;
import com.example.zalo_manager.service.CompanyReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("company_report")
public class CompanyReportController extends BaseController<CompanyReport, CompanyReportDto> {
    @Autowired
    private CompanyReportService companyReportService;

    public CompanyReportController() {
        super(CompanyReportDto.class);
    }

    @Override
    protected BaseService<CompanyReport> getService() {
        return companyReportService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody @Valid CompanyReportCreateReq req){
        return companyReportService.create(req);
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody @Valid CompanyReportUpdateReq req){
        return companyReportService.update(req);
    }
}
