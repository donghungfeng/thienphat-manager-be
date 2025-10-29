package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Issue;
import com.example.zalo_manager.entity.Report;
import com.example.zalo_manager.model.dto.ReportDto;
import com.example.zalo_manager.model.request.ReportCreateReq;
import com.example.zalo_manager.model.request.ReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.ReportRepository;
import com.example.zalo_manager.service.ReportService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends BaseServiceImpl<Report> implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    protected BaseRepository getRepository() {
        return reportRepository;
    }

    @Override
    public BaseResponse create(ReportCreateReq req) {
        return BaseResponse.success(MapperUtil.map(reportRepository.save(MapperUtil.map(req, Report.class)), ReportDto.class));
    }

    @Override
    public BaseResponse update(ReportUpdateReq req) {
        Report report = reportRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (report == null){
            return BaseResponse.success(req, HttpStatus.INTERNAL_SERVER_ERROR.value(),"report không tồn tại");
        }
        return BaseResponse.success(MapperUtil.map(reportRepository.save(MapperUtil.mapValue(req, report)), ReportDto.class));
    }

}
