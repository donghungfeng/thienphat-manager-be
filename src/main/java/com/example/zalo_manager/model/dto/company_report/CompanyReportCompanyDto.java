package com.example.zalo_manager.model.dto.company_report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReportCompanyDto {
    private Long id;
    private String code;
    private String name;
    private String shortName;
    private LocalDate establishedDate;
    private String representativeName;
    private String director;
    private String chiefAccountantName;
    private String taxCode;
    private String taxDepartmentName;
    private String address;
    private String phone;
    private String email;
    private String businessField;
    private String note;
    private Integer status;
}
