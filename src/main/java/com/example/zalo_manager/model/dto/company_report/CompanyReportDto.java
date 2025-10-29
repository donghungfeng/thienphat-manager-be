package com.example.zalo_manager.model.dto.company_report;

import com.example.zalo_manager.model.dto.ReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReportDto {
    Long id;
    private CompanyReportCompanyDto company;
    private ReportDto report;
    private CompanyReportUserDto accountant; // Kế toán viên
    private CompanyReportUserDto supporter; // Hỗ trợ
    private CompanyReportUserDto generalAccountant; // Kế toán tổng hợp
    private int status;
    private String note;
}
