package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Company;
import com.example.zalo_manager.entity.CompanyReport;
import com.example.zalo_manager.entity.Report;
import com.example.zalo_manager.entity.User;
import com.example.zalo_manager.model.dto.company_report.CompanyReportDto;
import com.example.zalo_manager.model.request.CompanyReportCreateReq;
import com.example.zalo_manager.model.request.CompanyReportUpdateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.*;
import com.example.zalo_manager.service.CompanyReportService;
import com.example.zalo_manager.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyReportServiceImpl extends BaseServiceImpl<CompanyReport> implements CompanyReportService {
    @Autowired
    private CompanyReportRepository companyReportRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository<CompanyReport> getRepository() {
        return companyReportRepository;
    }

    @Override
    public BaseResponse create(CompanyReportCreateReq req) {
        CompanyReport companyReport = MapperUtil.map(req, CompanyReport.class);
        Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
        if (company == null) {
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
        }
        companyReport.setCompany(company);
        Report report = reportRepository.findAllByIdAndIsActive(req.getReportId(), 1);
        if (report == null) {
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Báo cáo không tồn tại");
        }
        companyReport.setReport(report);
        if (req.getAccountantId() != null) {
            User accountant = userRepository.findAllByIdAndIsActive(req.getAccountantId(), 1);
            if (accountant == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Accountant không tồn tại");
            }
            if (accountant.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản accountant đã bị khóa");
            }
            companyReport.setAccountant(accountant);
        }

        if (req.getSupporterId() != null) {
            User supporter = userRepository.findAllByIdAndIsActive(req.getSupporterId(), 1);
            if (supporter == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Supporter không tồn tại");
            }
            if (supporter.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài supporter khoản đã bị khóa");
            }
            companyReport.setSupporter(supporter);
        }

        if (req.getGeneralAccountantId() != null) {
            User generalAccount = userRepository.findAllByIdAndIsActive(req.getGeneralAccountantId(), 1);
            if (generalAccount == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "generalAccount không tồn tại");
            }
            if (generalAccount.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài generalAccount khoản đã bị khóa");
            }
            companyReport.setGeneralAccountant(generalAccount);
        }
        return BaseResponse.success(MapperUtil.map(companyReportRepository.save(companyReport), CompanyReportDto.class));
    }

    @Override
    public BaseResponse update(CompanyReportUpdateReq req) {
        CompanyReport companyReport = companyReportRepository.findAllByIdAndIsActive(req.getId(), 1);
        if (companyReport == null) {
            return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Báo cáo công ty không tồn tại");
        }
        companyReport = MapperUtil.mapValue(req, companyReport);
        if (req.getCompanyId() != null) {
            Company company = companyRepository.findAllByIdAndIsActive(req.getCompanyId(), 1);
            if (company == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Công ty không tồn tại");
            }
            companyReport.setCompany(company);
        }
        if (req.getReportId() != null) {
            Report report = reportRepository.findAllByIdAndIsActive(req.getReportId(), 1);
            if (report == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Báo cáo không tồn tại");
            }
            companyReport.setReport(report);
        }
        if (req.getAccountantId() != null) {
            User accountant = userRepository.findAllByIdAndIsActive(req.getAccountantId(), 1);
            if (accountant == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Accountant không tồn tại");
            }
            if (accountant.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài khoản accountant đã bị khóa");
            }
            companyReport.setAccountant(accountant);
        }

        if (req.getSupporterId() != null) {
            User supporter = userRepository.findAllByIdAndIsActive(req.getSupporterId(), 1);
            if (supporter == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Supporter không tồn tại");
            }
            if (supporter.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài supporter khoản đã bị khóa");
            }
            companyReport.setSupporter(supporter);
        }

        if (req.getGeneralAccountantId() != null) {
            User generalAccount = userRepository.findAllByIdAndIsActive(req.getGeneralAccountantId(), 1);
            if (generalAccount == null) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "generalAccount không tồn tại");
            }
            if (generalAccount.getStatus() == -1) {
                return BaseResponse.fail(req, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Tài generalAccount khoản đã bị khóa");
            }
            companyReport.setGeneralAccountant(generalAccount);
        }
        return BaseResponse.success(MapperUtil.map(companyReportRepository.save(companyReport), CompanyReportDto.class));
    }
}
