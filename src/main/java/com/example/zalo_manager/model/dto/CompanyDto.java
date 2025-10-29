package com.example.zalo_manager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
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
    private String hasArising;
    private OtherUserDto accountant; // Kế toán viên
    private OtherUserDto supporter; // Hỗ trợ
    private OtherUserDto generalAccountant; // Kế toán tổng hợp
    private OtherUserDto collaborator; // Cộng tác viên
    private String invoiceLink;
    private String invoiceUsername;
    private String invoicePassword;
    private Integer invoicePurchasedCount;
    private Integer invoiceUsedCount;
    private Integer invoiceRemainingCount;
    private Integer invoiceStatus;
    private String electronicTaxPassword;
    private String electronicOrderPassword;
    private LocalDate digitalSignatureExpiryDate;
    private Integer remainingDays;
    private String totalZaloLink;
    private String insuranceLink;
    private String insuranceUsername;
    private String insurancePassword;
    private String bankInfo1;
    private String bankInfo2;
    private String bankInfo3;
    private String bankInfo4;
    private String bankInfo5;
}
